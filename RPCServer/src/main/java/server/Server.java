package server;

import common.RestHighLevelClientCustom;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Server {

    public static void main(String[] args) {

        RestHighLevelClient client = RestHighLevelClientCustom.getInstance();

        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("description", "this is a test");
        jsonMap.put("mnp", "this is a test");
        jsonMap.put("test", "this is a test");

        jsonMap.put("timestamp", System.currentTimeMillis());

        IndexRequest indexRequest = new IndexRequest("abc");
        indexRequest.id("1").source(jsonMap);
        try {
            RestHighLevelClientCustom.getInstance().index(indexRequest, RequestOptions.DEFAULT);
            System.out.println("id: " + indexRequest.id());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        DeleteRequest deleteRequest = new DeleteRequest("abc");
//        deleteRequest.id("109800");
//        try {
//            client.delete(deleteRequest, RequestOptions.DEFAULT);
//            //client.index(indexRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            client.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
