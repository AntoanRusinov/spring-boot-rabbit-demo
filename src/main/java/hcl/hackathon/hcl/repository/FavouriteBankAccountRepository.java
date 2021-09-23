package hcl.hackathon.hcl.repository;

import hcl.hackathon.hcl.entities.FavouriteBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteBankAccountRepository extends JpaRepository<FavouriteBankAccount, Long> {

}