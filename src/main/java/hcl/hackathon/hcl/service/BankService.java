package hcl.hackathon.hcl.service;

import hcl.hackathon.hcl.entities.Bank;
import hcl.hackathon.hcl.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void insertBanks(List<Bank> banks) {
        bankRepository.saveAll(banks);
    }

    public long count() {
        return bankRepository.count();
    }

}