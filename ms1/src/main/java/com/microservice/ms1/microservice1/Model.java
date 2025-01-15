package com.microservice.ms1.microservice1;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Model {

    @Id
    private String id;
    private String field1;
    private NestedField field2;
}
