package common;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.Properties;

public class RestHighLevelClientCustom {
    private static final Logger LOG = LogManager.getLogger(Property.class);
    private static RestHighLevelClient instance;
    private final static String HOST_MASTER = "host_master";
    private final static String HOST_DATA_ONE = "host_data_one";
    private final static String HOST_DATA_TWO = "host_data_two";
    private final static String PORT = "port";
    private final static String SCHEME = "http";
    private final static String USER = "user";
    private final static String PASSWORD = "password";
    private final static Properties properties = Property.getInstance();
    public static RestHighLevelClient getInstance(){
        if(instance == null){
            synchronized (RestHighLevelClient.class){
                if(instance == null){

                    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(properties.getProperty(USER),properties.getProperty(PASSWORD)));

                    instance = new org.elasticsearch.client.RestHighLevelClient(
                            RestClient.builder(

                                    new HttpHost(properties.getProperty(HOST_MASTER), Integer.parseInt(properties.getProperty(PORT)), SCHEME),

                                    new HttpHost(properties.getProperty(HOST_DATA_ONE), Integer.parseInt(properties.getProperty(PORT)), SCHEME),

                                    new HttpHost(properties.getProperty(HOST_DATA_TWO), Integer.parseInt(properties.getProperty(PORT)), SCHEME))

                                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

                                        public HttpAsyncClientBuilder customizeHttpClient(

                                                final HttpAsyncClientBuilder httpAsyncClientBuilder) {

                                            return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                                        }
                                    })
                    );
                }
            }
        }
        return instance;
    }
}
