// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: object_request.proto

package protoGen;

public final class ObjectRequestOuterClass {
  private ObjectRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protoGen_ObjectRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protoGen_ObjectRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protoGen_ObjectResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protoGen_ObjectResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protoGen_Empty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protoGen_Empty_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protoGen_Destination_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protoGen_Destination_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024object_request.proto\022\010protoGen\"\372\001\n\rObj" +
      "ectRequest\022\r\n\005appId\030\001 \001(\t\022\r\n\005mnpId\030\002 \001(\t" +
      "\022\023\n\013destination\030\003 \001(\t\022\022\n\nproviderId\030\004 \001(" +
      "\t\022\016\n\006status\030\005 \001(\t\022\021\n\tstartTime\030\006 \001(\t\022\022\n\n" +
      "createDate\030\007 \001(\t\022\022\n\nmodifyDate\030\010 \001(\t\022\022\n\n" +
      "createUser\030\t \001(\t\022\030\n\020originProviderId\030\n \001" +
      "(\t\022\024\n\014providerName\030\013 \001(\t\022\023\n\013requestTime\030" +
      "\014 \001(\003\"\225\001\n\016ObjectResponse\022\r\n\005appId\030\001 \001(\t\022" +
      "-\n\004code\030\002 \001(\0162\037.protoGen.ObjectResponse." +
      "Status\022\017\n\007message\030\003 \001(\t\022\024\n\014responseTime\030",
      "\004 \001(\003\"\036\n\006Status\022\006\n\002OK\020\000\022\014\n\010VALIDATE\020\001\"\007\n" +
      "\005Empty\"\"\n\013Destination\022\023\n\013destination\030\001 \001" +
      "(\t2\277\001\n\003MNP\022;\n\006insert\022\027.protoGen.ObjectRe" +
      "quest\032\030.protoGen.ObjectResponse\0223\n\006reloa" +
      "d\022\017.protoGen.Empty\032\030.protoGen.ObjectResp" +
      "onse\022F\n\024findRmnByDestination\022\025.protoGen." +
      "Destination\032\027.protoGen.ObjectRequestB\002P\001" +
      "b\006proto3"
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
    internal_static_protoGen_ObjectRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protoGen_ObjectRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protoGen_ObjectRequest_descriptor,
        new java.lang.String[] { "AppId", "MnpId", "Destination", "ProviderId", "Status", "StartTime", "CreateDate", "ModifyDate", "CreateUser", "OriginProviderId", "ProviderName", "RequestTime", });
    internal_static_protoGen_ObjectResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_protoGen_ObjectResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protoGen_ObjectResponse_descriptor,
        new java.lang.String[] { "AppId", "Code", "Message", "ResponseTime", });
    internal_static_protoGen_Empty_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_protoGen_Empty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protoGen_Empty_descriptor,
        new java.lang.String[] { });
    internal_static_protoGen_Destination_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_protoGen_Destination_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protoGen_Destination_descriptor,
        new java.lang.String[] { "Destination", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
