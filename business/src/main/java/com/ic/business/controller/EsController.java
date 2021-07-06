package com.ic.business.controller;

import com.ic.business.entity.DDRSensorTemperatureDTO;
import com.ic.business.util.EsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/es")
@RequiredArgsConstructor
public class EsController {

//    /**
//     * 删除索引
//     */
//    @GetMapping("/index/delete/{index-name}")
//    public Boolean delete(@PathVariable("index-name") String indexName) {
//        return esRestTemplate.indexOps(IndexCoordinates.of(indexName)).delete();
//    }
//
//    /**
//     * 新增数据
//     */
//    @GetMapping("/data/add/{id}")
//    public void test(@PathVariable("id") Long id) {
//        Fruit fruit = new Fruit(id, "芒果", 20, "芒果是杧果 [1]  （中国植物志）的通俗名（拉丁学名：Mangifera indica L.），芒果是一种原产印度的漆树科常绿大乔木，叶革质，互生；花小，杂性，黄色或淡黄色，成顶生的圆锥花序。核果大，压扁，长5-10厘米，宽3-4.5厘米，成熟时黄色，味甜，果核坚硬。");
//        esRestTemplate.save(fruit);
//    }
//
//    /**
//     * 查询数据
//     */
//    @GetMapping("/data/query/{description}")
//    public void testQuery(@PathVariable("description") String description) {
//        NativeSearchQuery query = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.queryStringQuery(description).field("description"))
//                .build();
//        SearchHits<Fruit> search = esRestTemplate.search(query, Fruit.class);
//        List<SearchHit<Fruit>> searchHits = search.toList();
//        searchHits.stream().forEach(e -> System.out.println(e.getContent()));
//    }

    /**
     * 新增索引
     */
    @GetMapping("/index/add/{index_name}")
    public void addIndex(@PathVariable("index_name") String indexName) {
        EsUtil.addIndex(indexName);
    }

    /**
     * 指定Index新增数据
     */
    @GetMapping("/document/add/{index_name}")
    public void addDocument(@PathVariable("index_name") String indexName) {
        List<DDRSensorTemperatureDTO> data = new ArrayList<>();
        DDRSensorTemperatureDTO temperatureDTO = new DDRSensorTemperatureDTO();
        temperatureDTO.setId(11L);
        temperatureDTO.setGmtCreate(new Date());
        temperatureDTO.setGmtModified(new Date());
        temperatureDTO.setCmdId("111");
        temperatureDTO.setComponentId("111");
        temperatureDTO.setLineTemperature(23.0f);
        temperatureDTO.setTime(new Date());
        temperatureDTO.setUnitNo(5);
        temperatureDTO.setUnitSum(5);
        data.add(temperatureDTO);
        EsUtil.insertBatch(indexName, data);
    }

//    /**
//     * 指定Index新增数据
//     */
//    @GetMapping("/document/query/{index_name}")
//    public void queryDocument(@PathVariable("index_name") String indexName) {
//        EsUtil.insertBatch(indexName, data);
//    }
}
