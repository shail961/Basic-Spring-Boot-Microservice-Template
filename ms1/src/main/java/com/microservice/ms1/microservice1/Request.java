package com.microservice.ms1.microservice1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private String id;
    private String field1;
    private NestedField field2;

}
