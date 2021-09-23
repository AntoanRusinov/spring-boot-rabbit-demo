package hcl.hackathon.hcl.repository;

import hcl.hackathon.hcl.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCustomerId(String customerId);

}