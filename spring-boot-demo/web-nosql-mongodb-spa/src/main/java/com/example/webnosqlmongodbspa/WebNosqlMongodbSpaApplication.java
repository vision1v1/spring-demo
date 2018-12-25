package com.example.webnosqlmongodbspa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebNosqlMongodbSpaApplication implements CommandLineRunner {

    @Autowired
    private MyRepository myRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebNosqlMongodbSpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myRepository.deleteAll();

        myRepository.save(new Customer("Alice", "Smith"));
        myRepository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");

        for (Customer customer : myRepository.findAll()) {
            System.out.println(customer);
        }

        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(myRepository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : myRepository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}

