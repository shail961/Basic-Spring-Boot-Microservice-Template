package com.microservice.ms2.microservice2;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ms1Response {

    private String id;
    private String field1;
    private Ms1NestedField field2;

}
