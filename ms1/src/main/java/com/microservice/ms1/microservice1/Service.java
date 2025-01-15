package com.microservice.ms1.microservice1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class Service {

    private final Repository repository;
    private final Mapper mapper;
    private final Ms2Client ms2Client;
    private final KafkaTemplate<String, Request> kafkaTemplate;


    public String createRecord(Request request) {
        var record = this.repository.save(mapper.toModel(request));
        return record.getId();
    }

    public void updateRecord(Request request) {
        var customer = this.repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot update record:: No record found with the provided ID: %s", request.getId())
                ));
        mapper.merge(customer, request);
        this.repository.save(customer);
    }

    public List<Response> findAllRecords() {
        return  this.repository.findAll()
                .stream()
                .map(this.mapper::toResponse)
                .collect(Collectors.toList());
    }

    public Response findById(String id) {
        return this.repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException(String.format("No record found with the provided ID: %s", id)));
    }

    public boolean existsById(String id) {
        return this.repository.findById(id)
                .isPresent();
    }

    public void deleteRecord(String id) {
        this.repository.deleteById(id);
    }

    public void produce(Ms2Requst request) {
        log.info("Sending request");
        Message<Ms2Requst> message = MessageBuilder
                .withPayload(request)
                .setHeader(TOPIC, "ms2-request")
                .build();

        kafkaTemplate.send(message);
    }

    public Ms2Response findById(Integer recordId){
        return ms2Client.findRecordById(recordId).orElseThrow();
    }
}
