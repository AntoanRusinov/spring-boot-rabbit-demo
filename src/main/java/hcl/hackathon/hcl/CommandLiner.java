package hcl.hackathon.hcl;

import hcl.hackathon.hcl.entities.Bank;
import hcl.hackathon.hcl.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
@Transactional
public class CommandLiner implements CommandLineRunner {

    @Autowired
    private BankService bankService;

    @Override
    public void run(String... args) {
        if (bankService.count() == 0) {
            bankService.insertBanks(Arrays.asList(
                    new Bank("1234", "Nairobi Bank"),
                    new Bank("1235", "Denver Bank"),
                    new Bank("1236", "Moscow Bank"),
                    new Bank("1237", "Tokyo Bank")
            ));
        }
    }
}
