package com.microservice.ms1.microservice1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ms1")
@RequiredArgsConstructor
public class Controller {
    private final Service service;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid Request request
    ) {
        return ResponseEntity.ok(this.service.createRecord(request));
    }

    @PostMapping("/ms2")
    public ResponseEntity<Void> produce(@RequestBody @Valid Ms2Requst request
    ) {
        this.service.produce(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid Request request
    ) {
        this.service.updateRecord(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Response>> findAll() {
        return ResponseEntity.ok(this.service.findAllRecords());
    }

    @GetMapping("/exists/{record-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("record-id") String recordId
    ) {
        return ResponseEntity.ok(this.service.existsById(recordId));
    }

    @GetMapping(value = "/{record-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> findById(
            @PathVariable("record-id") String recordId
    ) {
        return ResponseEntity.ok(this.service.findById(recordId));
    }

    @DeleteMapping("/{record-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("record-id") String recordId
    ) {
        this.service.deleteRecord(recordId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/ms2/{record-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ms2Response> findMs2ById(
            @PathVariable("record-id") Integer recordId
    ) {
        return ResponseEntity.ok(this.service.findById(recordId));
    }

}
