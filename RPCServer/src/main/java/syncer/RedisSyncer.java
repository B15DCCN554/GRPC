package syncer;

import common.JsonCustom;
import common.LogCommon;
import entities.ObjectBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import redis.clients.jedis.Jedis;
import utils.ConnectRedis;

import java.util.List;
import java.util.UUID;

public class RedisSyncer {
    private static final Logger LOG = LogManager.getLogger(RedisSyncer.class);

    public void saveData(ObjectBean objectBean) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        if (objectBean == null) return;
        try (Jedis redis = ConnectRedis.getJedisSentinelPool().getResource()) {
            int key = (int) ((Long.parseLong(objectBean.getDestination())) / 1000);
            String field = objectBean.getDestination();
            redis.hdel(String.valueOf(key), field);
            LOG.info("Start write data into redis with key: " + key + " filed " + field + " value: " + JsonCustom.convertObjectToJson(objectBean));
            redis.hset(String.valueOf(key), field, JsonCustom.convertObjectToJson(objectBean));
            LOG.info("End write data into redis");
            System.out.println("id redis: "+objectBean.getDestination());
            //System.out.println("data redis: " + redis.hget(String.valueOf(key), field));

        } catch (Exception e) {
            LOG.error("Error insert data to redis: ", e);
        }
        ThreadContext.clearAll();
    }

    public void saveDatas(List<ObjectBean> listMnp) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        if (listMnp == null) return;
        try (Jedis redis = ConnectRedis.getJedisSentinelPool().getResource()) {
            LOG.info("Start write data into redis");
            for (ObjectBean objectBean : listMnp) {
                long key = Long.parseLong(objectBean.getDestination()) / 1000;
                String field = objectBean.getDestination();
                redis.hdel(String.valueOf(key), field);
                LOG.info("Data with key: " + key + " filed " + field + " value: " + JsonCustom.convertObjectToJson(objectBean));
                redis.hset(String.valueOf(key), field, JsonCustom.convertObjectToJson(objectBean));
                LOG.info("Insert success");
                System.out.println("redis: "+objectBean.getDestination());
                //System.out.println("data redis: " + redis.hget(String.valueOf(key), field));
            }
            LOG.info("End write data into redis");
        } catch (Exception e) {
            LOG.error("Error insert data to redis: ", e);
        }
        ThreadContext.clearAll();
    }

    public ObjectBean getObjectBeanByDestination(String destination) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        LOG.info("Begin search by redis");
        ObjectBean objectBean = new ObjectBean();
        try (Jedis redis = ConnectRedis.getJedisSentinelPool().getResource()) {
            long key = Long.parseLong(destination) / 1000;
            String field = destination;
            System.out.println("data redis: " + redis.hget(String.valueOf(key), field));
            objectBean = JsonCustom.getObjectMapper().readValue(redis.hget(String.valueOf(key), field), ObjectBean.class);
        } catch (Exception e) {
            LOG.error("Error insert data to redis: ", e);
        }
        LOG.info("End search by redis");
        ThreadContext.clearAll();
        return objectBean;
    }
}
