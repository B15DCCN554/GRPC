package service;

import server.APIResponse;
import server.userGrpc;

public class UserService extends userGrpc.userImplBase {
    public void login(server.LoginRequest request,
                      io.grpc.stub.StreamObserver<server.APIResponse> responseObserver) {
        System.out.println("insider login");
        String username = request.getUsername();
        String password = request.getPassword();
        System.out.println("username: " + username + " password: " + password);

        APIResponse.Builder response = APIResponse.newBuilder();

        if ("TienLt".equalsIgnoreCase(username)) {
            response.setResponseCode(200).setResponseMessage("SUCCESS");
        } else {
            response.setResponseCode(201).setResponseMessage("FAILURE");
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    /**
     *
     */
    public void logout(server.Empty request,
                       io.grpc.stub.StreamObserver<server.APIResponse> responseObserver) {
    }
}
