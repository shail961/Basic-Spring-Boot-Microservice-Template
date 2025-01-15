package com.microservice.ms1.microservice1;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String id;
    private String field1;
    private NestedField field2;

}
