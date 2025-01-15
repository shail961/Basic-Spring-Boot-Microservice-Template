package com.microservice.ms1.microservice1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "microservice2",
        url = "${application.config.microservice2-url}"
)
public interface Ms2Client {

    @GetMapping("/{record-id}")
    Optional<Ms2Response> findRecordById(@PathVariable("record-id") Integer recordId);

}
