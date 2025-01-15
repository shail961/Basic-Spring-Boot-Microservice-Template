package com.microservice.ms2.microservice2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class Ms1Client {

    @Value("${application.config.microservice1-url}")
    private String ms1Url;
    private final RestTemplate restTemplate;

    public Ms1Response findById(String recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<Ms1Response> requestEntity = new HttpEntity<>( headers);
        ParameterizedTypeReference<Ms1Response> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<Ms1Response> responseEntity = restTemplate.exchange(
                ms1Url + "/"+recordId,
                GET,
                requestEntity,
                responseType
        );

        if (responseEntity.getStatusCode().isError()) {
            throw new RuntimeException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }
        return  responseEntity.getBody();
    }

}
