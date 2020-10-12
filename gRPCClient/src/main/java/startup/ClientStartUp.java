package startup;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import server.APIResponse;
import server.LoginRequest;
import server.userGrpc;

import java.util.concurrent.TimeUnit;

public class ClientStartUp {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

        userGrpc.userBlockingStub userBlockingStub = userGrpc.newBlockingStub(channel);

        LoginRequest loginRequest = LoginRequest.newBuilder().setUsername("TienLt").setPassword("Vnpay@123").build();
        APIResponse apiResponse = userBlockingStub.login(loginRequest);
        System.out.println(apiResponse.getResponseMessage());
//        try {
//            channel.awaitTermination(10, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        channel.shutdown();
        //

    }
}
