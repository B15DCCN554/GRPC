// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package server;

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_server_LoginRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_server_LoginRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_server_APIResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_server_APIResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_server_Empty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_server_Empty_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\022\006server\"2\n\014LoginRequest\022\020\n\010" +
      "username\030\001 \001(\t\022\020\n\010password\030\002 \001(\t\"<\n\013APIR" +
      "esponse\022\027\n\017responseMessage\030\001 \001(\t\022\024\n\014resp" +
      "onseCode\030\002 \001(\005\"\007\n\005Empty2h\n\004user\0222\n\005login" +
      "\022\024.server.LoginRequest\032\023.server.APIRespo" +
      "nse\022,\n\006logout\022\r.server.Empty\032\023.server.AP" +
      "IResponseB\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_server_LoginRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_server_LoginRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_server_LoginRequest_descriptor,
        new java.lang.String[] { "Username", "Password", });
    internal_static_server_APIResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_server_APIResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_server_APIResponse_descriptor,
        new java.lang.String[] { "ResponseMessage", "ResponseCode", });
    internal_static_server_Empty_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_server_Empty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_server_Empty_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
