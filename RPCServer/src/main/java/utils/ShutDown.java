package utils;

import common.RestHighLevelClientCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ShutDown {
    private static final Logger LOG = LogManager.getLogger(ShutDown.class);

    public static void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if(ConnectRedis.getJedisSentinelPool() != null){
                    ConnectRedis.getJedisSentinelPool().close();
                }
                if(RestHighLevelClientCustom.getInstance() != null){
                    try {
                        RestHighLevelClientCustom.getInstance().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(ConnectDB.getDataSource() != null){
                    ConnectDB.getDataSource().close();
                }
                LOG.info("Shutdown.");
            }
        });
    }
}
