package com.microservice.ms2.microservice2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    private final Repository repository;
    private final Mapper mapper;
    private final Ms1Client ms1Client;


    public Integer createRecord(Request request) {
        var record = this.repository.save(mapper.toModel(request));
        return record.getId();
    }

    public void updateRecord(Request request) {
        var customer = this.repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %s", request.getId())
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

    public Response findById(Integer id) {
        return this.repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException(String.format("No customer found with the provided ID: %s", id)));
    }

    public boolean existsById(Integer id) {
        return this.repository.findById(id)
                .isPresent();
    }

    public void deleteRecord(Integer id) {
        this.repository.deleteById(id);
    }

    public Ms1Response findMs1ById(String id) {
        return this.ms1Client.findById(id);
    }

    @KafkaListener(topics = "ms2-request")
    public void consumePaymentSuccessNotifications(Request request) {
        log.info("Kafka consuming data:"+request.getVariable1());
        repository.save(mapper.toModel(request));
        return;
    }

}
