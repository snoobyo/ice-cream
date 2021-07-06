package com.ic.business.util;

import com.ic.business.entity.DDRSensorTemperatureDTO;
import com.ic.common.util.JacksonUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EsUtil {

    private static RestHighLevelClient client;

    @Autowired
    public void setRestHighLevelClient(RestHighLevelClient client) {
        EsUtil.client = client;
    }

    public static boolean existsIndex(String indexName) {
        GetIndexRequest request = new GetIndexRequest(indexName);
        try {
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addIndex(String IndexName) {
        if (! existsIndex(IndexName)) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(IndexName);
            try {
                client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertBatch(String index, List<DDRSensorTemperatureDTO> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(index).id(item.getId().toString())
                .source(JacksonUtil.toJson(item), XContentType.JSON)));
        try {
            client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
