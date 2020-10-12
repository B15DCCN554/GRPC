package daoImpl;

import common.LogCommon;
import common.RestHighLevelClientCustom;
import dao.MNP;
import entities.ObjectBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.GsonCustom;
import common.JsonCustom;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import syncer.RedisSyncer;

import java.util.stream.Collectors;

public class MNPImpl implements MNP {
    private static final Logger LOG = LogManager.getLogger(MNPImpl.class);
    private final static String INDEX = "mnp";
    private final static String TYPE = "_doc";

    @Override
    public String insert(ObjectBean objectBean) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        LOG.info("Begin insert data Elasticsearch");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("appId", objectBean.getAppId());
        jsonMap.put("mnpId", objectBean.getMnpId());
        jsonMap.put("destination", objectBean.getDestination());
        jsonMap.put("providerId", objectBean.getProviderId());
        jsonMap.put("status", objectBean.getStatus());
        jsonMap.put("startTime", objectBean.getStartTime());
        jsonMap.put("createDate", objectBean.getCreateDate());
        jsonMap.put("modifyDate", objectBean.getModifyDate());
        jsonMap.put("createUser", objectBean.getCreateUser());
        jsonMap.put("originProviderId", objectBean.getOriginProviderId());
        jsonMap.put("providerName", objectBean.getProviderName());
        jsonMap.put("requestTime", System.currentTimeMillis());

        IndexRequest indexRequest = new IndexRequest(INDEX,TYPE);
        indexRequest.id(objectBean.getDestination()+"00").source(jsonMap);
        try {
            RestHighLevelClientCustom.getInstance().index(indexRequest, RequestOptions.DEFAULT);
            System.out.println("id elastic: " + indexRequest.id());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LOG.error("Error insert to elasticsearch ", e);
        }
        LOG.info("End insert data Elasticsearch");
        ThreadContext.clearAll();
        return objectBean.getMnpId();
    }

    @Override
    public void inserts(List<ObjectBean> listMnp) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        if (listMnp == null) return;
        for (ObjectBean objectBean : listMnp) {
            insert(objectBean);
        }
        ThreadContext.clearAll();
    }

    @Override
    public ObjectBean findMnpByDestination(String destination) {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("destination", destination);
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(matchQueryBuilder);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);
        SearchResponse response = null;
        List<ObjectBean> results = new ArrayList<>();
        try {
            response = RestHighLevelClientCustom.getInstance().search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = response.getHits().getHits();
            System.out.println(GsonCustom.getInstance().toJson(searchHits));
            results = Arrays.stream(searchHits)
                    .map(hit -> {
                        try {
                            return JsonCustom.getObjectMapper().readValue(hit.getSourceAsString(), ObjectBean.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Search elasticsearch error: ", e);
            //search by redis
            LOG.info("Begin search redis");
            ObjectBean objectBean = new RedisSyncer().getObjectBeanByDestination(destination);
            results.add(objectBean);
            LOG.info("End search redis");
        }
        ThreadContext.clearAll();
        return results.size() == 0 ? null : results.get(0);
    }
}
