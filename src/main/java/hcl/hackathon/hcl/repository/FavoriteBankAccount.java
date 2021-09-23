package hcl.hackathon.hcl.repository;

import hcl.hackathon.hcl.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteBankAccount extends JpaRepository<Customer, Long> {
}
