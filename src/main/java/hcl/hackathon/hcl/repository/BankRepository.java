package hcl.hackathon.hcl.repository;

import hcl.hackathon.hcl.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
