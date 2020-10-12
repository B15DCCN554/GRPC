package server;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestElastic {
    public static void main(String[] args) {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("gtgt",

                "Vnpay@123"));

        RestHighLevelClient client = new RestHighLevelClient(

                RestClient.builder(

                        new HttpHost("10.22.7.141", 9200, "http"),

                        new HttpHost("10.22.7.142", 9200, "http"),

                        new HttpHost("10.22.7.144", 9200, "http"))

                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

                            public HttpAsyncClientBuilder customizeHttpClient(

                                    final HttpAsyncClientBuilder httpAsyncClientBuilder) {

                                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        })
        );

        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("description", "this is a test");

        jsonMap.put("timestamp", System.currentTimeMillis());

        IndexRequest indexRequest = new IndexRequest("testing").id("1").source(jsonMap);

        try {
            client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Indexed document to index \"%s\" with id %s%n",

                indexRequest.index(),

                indexRequest.id());

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
