package server;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import protoGen.MNPGrpc;
import protoGen.ObjectRequest;

public class GRPCClient {
    public static void main(String[] args) {
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forAddress("localhost", 9091).usePlaintext().build();

            MNPGrpc.MNPBlockingStub mnpBlockingStub = MNPGrpc.newBlockingStub(channel);

            ObjectRequest objectRequest = ObjectRequest.newBuilder().setAppId("1234").build();
            protoGen.ObjectResponse objectResponse = mnpBlockingStub.insert(objectRequest);
            System.out.println(objectResponse.getAppId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        channel.shutdown();
    }
}
