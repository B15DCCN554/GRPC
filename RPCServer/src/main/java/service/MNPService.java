package service;

import common.GsonCustom;
import common.LogCommon;
import daoImpl.LoadDataDBImpl;
import daoImpl.MNPImpl;
import entities.ObjectBean;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import protoGen.*;
import syncer.RedisSyncer;
import validator.ObjectBeanValidate;

import java.net.InetAddress;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MNPService extends MNPGrpc.MNPImplBase {
    private static final Logger LOG = LogManager.getLogger(MNPService.class);
    final MNPImpl mnpImpl = new MNPImpl();
    final RedisSyncer redisSyncer = new RedisSyncer();
    final LoadDataDBImpl loadDataDB = new LoadDataDBImpl();

    @Override
    public void insert(protoGen.ObjectRequest request,
                       io.grpc.stub.StreamObserver<protoGen.ObjectResponse> responseObserver) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        LOG.info("Begin insert to elasticsearch");
        ObjectBean objectBean = setDataToBean(request);
        ObjectResponse.Builder response = ObjectResponse.newBuilder();
        Map<String, String> r = new ObjectBeanValidate().validateInput(objectBean);
        if (r.size() > 0) {
            response.setCode(ObjectResponse.Status.VALIDATE);
            response.setMessage(GsonCustom.getInstance().toJson(r));
        } else {
            //insert data into elasticsearch
            mnpImpl.insert(objectBean);
            //insert data into redis
            redisSyncer.saveData(objectBean);
            //
            response.setAppId(objectBean.getAppId());
            response.setCode(ObjectResponse.Status.OK);
            response.setResponseTime(System.currentTimeMillis());
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        LOG.info("End insert to elasticsearch");
        ThreadContext.clearAll();

    }

    @Override
    public synchronized void reload(Empty request, StreamObserver<ObjectResponse> responseObserver) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        LOG.info("Begin reload database into redis and elasticsearch");
//        LoadDataDBImpl.flag = false;
        ObjectResponse.Builder response = ObjectResponse.newBuilder();
//        loadDb();
//        SynElasticSearch();
//        SynRedis();
        //
        LoadDataDBImpl.getInstance().synAllData();

        response.setCode(ObjectResponse.Status.OK);
        response.setResponseTime(System.currentTimeMillis());
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        LOG.info("End reload database into redis and elasticsearch");
        ThreadContext.clearAll();
    }

//    private void SynRedis() {
//        //insert into redis
//        ExecutorService executorRedis = Executors.newSingleThreadExecutor();
//        executorRedis.submit(() -> {
//            String threadName = Thread.currentThread().getName();
//            while (true) {
//                if (LoadDataDBImpl.flag) {
//                    LOG.info("Begin synchronized data to redis");
//                    System.out.println("Start syn redis");
//                    redisSyncer.saveDatas(loadDataDB.getListMnp());
//                    System.out.println("Synchronized redis success");
//                    LOG.info("Synchronized data to redis success");
//                    break;
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        executorRedis.shutdown();
//    }

//    private void SynElasticSearch() {
//        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
//        //insert into elasticsearch
//        ExecutorService executorElastic = Executors.newSingleThreadExecutor();
//        executorElastic.submit(() -> {
//            String threadName = Thread.currentThread().getName();
//            while (true) {
//                if (LoadDataDBImpl.flag) {
//                    LOG.info("Begin synchronized data elasticsearch");
//                    System.out.println("Start syn elasticsearch");
//                    mnpImpl.inserts(loadDataDB.getListMnp());
//                    System.out.println("Synchronized elasticsearch success");
//                    LOG.info("Synchronized data elasticsearch success");
//                    break;
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        executorElastic.shutdown();
//        ThreadContext.clearAll();
//    }

    @Override
    public void findRmnByDestination(Destination request, StreamObserver<protoGen.ObjectRequest> responseObserver) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        LOG.info("Begin findRmnByDestination");
        ObjectRequest.Builder response = ObjectRequest.newBuilder();
        if (request.getDestination() == null || request.getDestination().isEmpty()) {
        } else {
            ObjectBean objectBean = mnpImpl.findMnpByDestination(request.getDestination());
            if (objectBean != null) {
                response.setMnpId(objectBean.getMnpId());
                response.setDestination(objectBean.getDestination());
                response.setProviderId(objectBean.getProviderId());
                response.setStatus(objectBean.getStatus());
                response.setStartTime(objectBean.getStartTime());
                response.setCreateDate(objectBean.getCreateDate());
                response.setModifyDate(converNullToEmpty(objectBean.getModifyDate()));
                response.setCreateUser(objectBean.getCreateUser());
                response.setOriginProviderId(objectBean.getOriginProviderId());
                response.setProviderName(objectBean.getProviderName());
            }
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        LOG.info("End findRmnByDestination");
        ThreadContext.clearAll();
    }

    private ObjectBean setDataToBean(protoGen.ObjectRequest request) {
        ObjectBean objectBean = new ObjectBean();
        objectBean.setAppId(request.getAppId());
        objectBean.setMnpId(request.getMnpId());
        objectBean.setDestination(request.getDestination());
        objectBean.setProviderId(request.getProviderId());
        objectBean.setStatus(request.getStatus());
        objectBean.setStartTime(request.getStartTime());
        objectBean.setCreateDate(request.getCreateDate());
        objectBean.setModifyDate(request.getModifyDate());
        objectBean.setCreateUser(request.getCreateUser());
        objectBean.setOriginProviderId(request.getOriginProviderId());
        objectBean.setProviderName(request.getProviderName());
        objectBean.setRequestTime(System.currentTimeMillis());
        return objectBean;
    }

    private void loadDb() {
        //load data from db
        ExecutorService executorLoadDB = Executors.newSingleThreadExecutor();
        executorLoadDB.submit(() -> {
            String threadName = Thread.currentThread().getName();
            loadDataDB.synAllData();
        });
        executorLoadDB.shutdown();
    }

    private String converNullToEmpty(String str) {
        return str == null ? "" : str;
    }
}
