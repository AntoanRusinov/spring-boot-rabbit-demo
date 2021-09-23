package hcl.hackathon.hcl.service;

import com.github.javafaker.Faker;
import hcl.hackathon.hcl.entities.Customer;
import hcl.hackathon.hcl.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public long count() {
        return customerRepository.count();
    }

    public void insertCustomers(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }

    public String generateCustomerId() {
        return new Faker().code().gtin13();
    }

}