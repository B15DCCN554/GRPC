package daoImpl;

import common.LogCommon;
import dao.LoadDataDB;
import entities.ObjectBean;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import syncer.MNPSynRedis;
import syncer.RedisSyncer;
import utils.ConnectDB;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadDataDBImpl implements LoadDataDB {
    private static final Logger LOG = LogManager.getLogger(LoadDataDBImpl.class);
    private static final String GET_MNP_PAGING2 = "{CALL PGK_MNP_TEST.GET_MNP_PAGING(?,?,?)}";
    private static final String COUNT_MNP = "{CALL PKG_MNP.MNP_COUNT(?)}";
    private static LoadDataDBImpl instance;
    private final int rowCountDefault = 1000;

    RedisSyncer redisSyncer = new RedisSyncer();
    MNPImpl mnpImpl = new MNPImpl();

    public static LoadDataDBImpl getInstance() {
        if (instance == null) {
            synchronized (LoadDataDBImpl.class) {
                if (instance == null) {
                    instance = new LoadDataDBImpl();
                }
            }
        }
        return instance;
    }

    public void synAllData() {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println("Begin Synchronized data from database to redis and elasticsearch");
        int numberLimit = countAllDataMNP() / rowCountDefault + 1;
        System.out.println("number data: " + numberLimit);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < numberLimit; i++) {
            executorService.submit(new MNPSynRedis((i * rowCountDefault + 1), rowCountDefault));
        }
        executorService.shutdown();
        System.out.println("End Synchronized data from database to redis and elasticsearch");
        ThreadContext.clearAll();
    }

    public void synLimitData(int rowNum, int rowCount) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println("Begin load data from database");
        try (Connection connection = ConnectDB.getDataSource().getConnection();
             CallableStatement stmt = connection.prepareCall(GET_MNP_PAGING2)) {
            stmt.setBigDecimal("P_ROWNUM", new BigDecimal(rowNum));
            stmt.setBigDecimal("P_ROWCOUNT", new BigDecimal(rowCount));
            stmt.registerOutParameter("P_CUR", OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject("P_CUR")) {
                while (rs.next()) {
                    ObjectBean objectBean = new ObjectBean();
                    objectBean.setMnpId(rs.getString("MNP_ID"));
                    objectBean.setDestination(rs.getString("DESTINATION"));
                    objectBean.setProviderId(rs.getString("PROVIDER_ID"));
                    objectBean.setStatus(rs.getString("STATUS"));
                    objectBean.setStartTime(rs.getString("START_TIME"));
                    objectBean.setCreateDate(rs.getString("CREATE_DATE"));
                    objectBean.setModifyDate(rs.getString("MODIFY_DATE"));
                    objectBean.setCreateUser(rs.getString("CREATE_USER"));
                    objectBean.setOriginProviderId(rs.getString("ORIGIN_PROVIDER_ID"));
                    objectBean.setProviderName(rs.getString("PROVIDER_NAME"));
                    //syn elasticsearch
                    mnpImpl.insert(objectBean);
                    //syn redis
                    redisSyncer.saveData(objectBean);
                }
            }
            LOG.info("End get all data MNP");
            System.out.println("Load data DB success!");
        } catch (Exception e) {
            LOG.error("Error get data api MNP: ", e);
        }
        ThreadContext.clearAll();
    }

    @Override
    public int countAllDataMNP() {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println("Begin count data from database");
        try (Connection connection = ConnectDB.getDataSource().getConnection();
             CallableStatement stmt = connection.prepareCall(COUNT_MNP)) {
            stmt.registerOutParameter("P_CUR", OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject("P_CUR")) {
                if (rs.next()) {
                    return rs.getInt("COUNT_DB");
                }
            }
            LOG.info("End get count data MNP");
        } catch (Exception e) {
            LOG.error("Error count data api MNP: ", e);
        }
        ThreadContext.clearAll();
        return 0;
    }

}

