package com.ic.business.controller;

import com.ic.business.entity.es.Fruit;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/es")
@RequiredArgsConstructor
public class EsController {

    private final ElasticsearchRestTemplate esRestTemplate;

    /**
     * 删除索引
     */
    @GetMapping("/index/delete/{index-name}")
    public Boolean delete(@PathVariable("index-name") String indexName) {
        return esRestTemplate.indexOps(IndexCoordinates.of(indexName)).delete();
    }

    /**
     * 新增数据
     */
    @GetMapping("/data/add/{id}")
    public void test(@PathVariable("id") Long id) {
        Fruit fruit = new Fruit(id, "芒果", 20, "芒果是杧果 [1]  （中国植物志）的通俗名（拉丁学名：Mangifera indica L.），芒果是一种原产印度的漆树科常绿大乔木，叶革质，互生；花小，杂性，黄色或淡黄色，成顶生的圆锥花序。核果大，压扁，长5-10厘米，宽3-4.5厘米，成熟时黄色，味甜，果核坚硬。");
        esRestTemplate.save(fruit);
    }

    /**
     * 查询数据
     */
    @GetMapping("/data/query/{description}")
    public void testQuery(@PathVariable("description") String description) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(description).field("description"))
                .build();
        SearchHits<Fruit> search = esRestTemplate.search(query, Fruit.class);
        List<SearchHit<Fruit>> searchHits = search.toList();
        searchHits.stream().forEach(e -> System.out.println(e.getContent()));
    }
}
