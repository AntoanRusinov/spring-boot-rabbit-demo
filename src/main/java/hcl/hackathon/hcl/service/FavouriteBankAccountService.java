package hcl.hackathon.hcl.service;

import hcl.hackathon.hcl.entities.FavouriteBankAccount;
import hcl.hackathon.hcl.repository.FavouriteBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavouriteBankAccountService {

    @Autowired
    private FavouriteBankAccountRepository favouriteBankAccountRepository;

    public FavouriteBankAccount addFavouriteBankAccount(FavouriteBankAccount favouriteBankAccount) {
        return favouriteBankAccountRepository.save(favouriteBankAccount);
    }

    public Optional<FavouriteBankAccount> findById(Long id) {
        return favouriteBankAccountRepository.findById(id);
    }

    public void deleteById(Long id) {
        favouriteBankAccountRepository.deleteById(id);
    }

}