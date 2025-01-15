package com.microservice.ms1.microservice1;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ms2Response {

    private Integer id;
    private String variable1;
    private String variable2;

}
