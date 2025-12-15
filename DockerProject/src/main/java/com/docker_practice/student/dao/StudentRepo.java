package com.docker_practice.student.dao;

import com.docker_practice.student.domain.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends MongoRepository<Students, String> {
}
