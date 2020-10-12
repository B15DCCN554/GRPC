package server;

import common.LogCommon;
import common.Property;
import common.RestHighLevelClientCustom;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.logging.log4j.ThreadContext;
import service.MNPService;
import service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectDB;
import utils.ConnectRedis;
import utils.ShutDown;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class GRPCServer {

    static {
        System.setProperty("log4j.configurationFile", "./log4j2.xml");
    }
    private static final Logger LOG = LogManager.getLogger(GRPCServer.class);
    private static final String PORT_SERVER= "server_port";
    public static void main(String[] args) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        final Properties properties = Property.getInstance();
        Server server = ServerBuilder.forPort(
                Integer.parseInt(properties.getProperty(PORT_SERVER)))
                .addService(new MNPService())
                .addService(new UserService()).build();
        try {
            server.start();
            RestHighLevelClientCustom.getInstance();
            ConnectRedis.getJedisSentinelPool();
            ConnectDB.getDataSource();
            ShutDown.shutdownHook();
            System.out.println("Server started on port: "+server.getPort());
            LOG.info("Server started on port: "+server.getPort());
            server.awaitTermination();
        } catch (IOException e) {
            LOG.error("Error start server: ",e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            LOG.error("Error start server: ",e);
            e.printStackTrace();
        }
        ThreadContext.clearAll();
    }
}
