package dao;

import common.RestHighLevelClientCustom;
import entities.ObjectBean;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.List;

public interface MNP {
    String insert(ObjectBean objectBean);
    void inserts(List<ObjectBean> listMnp);
    ObjectBean findMnpByDestination(String destination);
}
