package com.ic.business.entity.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "fruit", replicas = 0)
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {

    @Id
    private Long id;

    @Field
    private String name;

    @Field
    private Integer price;

    @Field(type = FieldType.Text)
    private String description;
}
