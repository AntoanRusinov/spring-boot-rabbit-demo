package hcl.hackathon.hcl;

import hcl.hackathon.hcl.entities.Bank;
import hcl.hackathon.hcl.entities.Customer;
import hcl.hackathon.hcl.service.BankService;
import hcl.hackathon.hcl.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Transactional
public class CommandLiner implements CommandLineRunner {

    @Autowired
    private BankService bankService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

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

        if (customerService.count() == 0) {
            customerService.insertCustomers(Arrays.asList(
                    new Customer(customerService.generateCustomerId(), passwordEncoder.encode("ADMIN"), new ArrayList<>()),
                    new Customer(customerService.generateCustomerId(), passwordEncoder.encode("USER"), new ArrayList<>())
            ));
        }
    }
}
