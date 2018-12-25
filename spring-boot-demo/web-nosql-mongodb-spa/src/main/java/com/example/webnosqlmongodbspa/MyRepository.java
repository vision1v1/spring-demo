package com.example.webnosqlmongodbspa;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MyRepository extends MongoRepository<Customer,String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
}
