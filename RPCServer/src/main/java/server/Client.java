package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.GsonCustom;
import common.JsonCustom;
import common.RestHighLevelClientCustom;
import entities.ObjectBean;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    public static void main(String[] args) {

        RestHighLevelClient client = RestHighLevelClientCustom.getInstance();

        try {
            //getResponse = client.get(getRequest, RequestOptions.DEFAULT);

            QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("destination", "0386566020");

            SearchSourceBuilder builder = new SearchSourceBuilder()
                    .postFilter(matchQueryBuilder);

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
            searchRequest.source(builder);

            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = response.getHits().getHits();
            System.out.println(GsonCustom.getInstance().toJson(searchHits));
            List<ObjectBean> results =
                    Arrays.stream(searchHits)
                            .map(hit -> {
                                try {
                                    return JsonCustom.getObjectMapper().readValue(hit.getSourceAsString(),ObjectBean.class);
                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            })
                            .collect(Collectors.toList());

            System.out.println(results.get(0).getDestination());
            int totalShards = response.getTotalShards();
            System.out.println(totalShards);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.printf("Retrieved document: {Id: %s, Description: %s, Test Time: %s}%n",
//
//                getResponse.getId(),
//
//                getResponse.getSource().get("description"),
//
//                getResponse.getSource().get("timestamp"));
    }
}
