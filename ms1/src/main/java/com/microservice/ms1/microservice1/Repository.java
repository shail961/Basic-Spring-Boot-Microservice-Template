package com.microservice.ms1.microservice1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Model, String > {
}
