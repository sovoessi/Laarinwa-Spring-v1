package com.example.aktivist.mugshot;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MugshotRepository extends MongoRepository<Mugshot, String> {
}
