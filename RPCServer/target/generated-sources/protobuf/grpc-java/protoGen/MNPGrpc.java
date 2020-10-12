package protoGen;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: object_request.proto")
public final class MNPGrpc {

  private MNPGrpc() {}

  public static final String SERVICE_NAME = "protoGen.MNP";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protoGen.ObjectRequest,
      protoGen.ObjectResponse> METHOD_INSERT =
      io.grpc.MethodDescriptor.<protoGen.ObjectRequest, protoGen.ObjectResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "protoGen.MNP", "insert"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              protoGen.ObjectRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              protoGen.ObjectResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protoGen.Empty,
      protoGen.ObjectResponse> METHOD_RELOAD =
      io.grpc.MethodDescriptor.<protoGen.Empty, protoGen.ObjectResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "protoGen.MNP", "reload"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              protoGen.Empty.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              protoGen.ObjectResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protoGen.Destination,
      protoGen.ObjectRequest> METHOD_FIND_RMN_BY_DESTINATION =
      io.grpc.MethodDescriptor.<protoGen.Destination, protoGen.ObjectRequest>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "protoGen.MNP", "findRmnByDestination"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              protoGen.Destination.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              protoGen.ObjectRequest.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MNPStub newStub(io.grpc.Channel channel) {
    return new MNPStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MNPBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MNPBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MNPFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MNPFutureStub(channel);
  }

  /**
   */
  public static abstract class MNPImplBase implements io.grpc.BindableService {

    /**
     */
    public void insert(protoGen.ObjectRequest request,
        io.grpc.stub.StreamObserver<protoGen.ObjectResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_INSERT, responseObserver);
    }

    /**
     */
    public void reload(protoGen.Empty request,
        io.grpc.stub.StreamObserver<protoGen.ObjectResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RELOAD, responseObserver);
    }

    /**
     */
    public void findRmnByDestination(protoGen.Destination request,
        io.grpc.stub.StreamObserver<protoGen.ObjectRequest> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FIND_RMN_BY_DESTINATION, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_INSERT,
            asyncUnaryCall(
              new MethodHandlers<
                protoGen.ObjectRequest,
                protoGen.ObjectResponse>(
                  this, METHODID_INSERT)))
          .addMethod(
            METHOD_RELOAD,
            asyncUnaryCall(
              new MethodHandlers<
                protoGen.Empty,
                protoGen.ObjectResponse>(
                  this, METHODID_RELOAD)))
          .addMethod(
            METHOD_FIND_RMN_BY_DESTINATION,
            asyncUnaryCall(
              new MethodHandlers<
                protoGen.Destination,
                protoGen.ObjectRequest>(
                  this, METHODID_FIND_RMN_BY_DESTINATION)))
          .build();
    }
  }

  /**
   */
  public static final class MNPStub extends io.grpc.stub.AbstractStub<MNPStub> {
    private MNPStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MNPStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MNPStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MNPStub(channel, callOptions);
    }

    /**
     */
    public void insert(protoGen.ObjectRequest request,
        io.grpc.stub.StreamObserver<protoGen.ObjectResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INSERT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reload(protoGen.Empty request,
        io.grpc.stub.StreamObserver<protoGen.ObjectResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RELOAD, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findRmnByDestination(protoGen.Destination request,
        io.grpc.stub.StreamObserver<protoGen.ObjectRequest> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FIND_RMN_BY_DESTINATION, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MNPBlockingStub extends io.grpc.stub.AbstractStub<MNPBlockingStub> {
    private MNPBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MNPBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MNPBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MNPBlockingStub(channel, callOptions);
    }

    /**
     */
    public protoGen.ObjectResponse insert(protoGen.ObjectRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INSERT, getCallOptions(), request);
    }

    /**
     */
    public protoGen.ObjectResponse reload(protoGen.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RELOAD, getCallOptions(), request);
    }

    /**
     */
    public protoGen.ObjectRequest findRmnByDestination(protoGen.Destination request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FIND_RMN_BY_DESTINATION, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MNPFutureStub extends io.grpc.stub.AbstractStub<MNPFutureStub> {
    private MNPFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MNPFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MNPFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MNPFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protoGen.ObjectResponse> insert(
        protoGen.ObjectRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INSERT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protoGen.ObjectResponse> reload(
        protoGen.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RELOAD, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protoGen.ObjectRequest> findRmnByDestination(
        protoGen.Destination request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FIND_RMN_BY_DESTINATION, getCallOptions()), request);
    }
  }

  private static final int METHODID_INSERT = 0;
  private static final int METHODID_RELOAD = 1;
  private static final int METHODID_FIND_RMN_BY_DESTINATION = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MNPImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MNPImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INSERT:
          serviceImpl.insert((protoGen.ObjectRequest) request,
              (io.grpc.stub.StreamObserver<protoGen.ObjectResponse>) responseObserver);
          break;
        case METHODID_RELOAD:
          serviceImpl.reload((protoGen.Empty) request,
              (io.grpc.stub.StreamObserver<protoGen.ObjectResponse>) responseObserver);
          break;
        case METHODID_FIND_RMN_BY_DESTINATION:
          serviceImpl.findRmnByDestination((protoGen.Destination) request,
              (io.grpc.stub.StreamObserver<protoGen.ObjectRequest>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class MNPDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protoGen.ObjectRequestOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MNPGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MNPDescriptorSupplier())
              .addMethod(METHOD_INSERT)
              .addMethod(METHOD_RELOAD)
              .addMethod(METHOD_FIND_RMN_BY_DESTINATION)
              .build();
        }
      }
    }
    return result;
  }
}
