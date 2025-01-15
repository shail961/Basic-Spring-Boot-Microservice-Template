package com.microservice.ms2.microservice2;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Integer id;
    private String variable1;
    private String variable2;

}
