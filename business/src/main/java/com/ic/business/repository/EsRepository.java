package com.ic.business.repository;

import com.ic.business.entity.es.Fruit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsRepository extends ElasticsearchRepository<Fruit, Long> {
}
