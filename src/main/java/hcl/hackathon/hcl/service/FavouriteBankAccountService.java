package hcl.hackathon.hcl.service;

import hcl.hackathon.hcl.repository.FavouriteBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteBankAccountService {

    @Autowired
    private FavouriteBankAccountRepository favouriteBankAccountRepository;

}