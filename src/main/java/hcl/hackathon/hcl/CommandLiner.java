package hcl.hackathon.hcl;

import com.fasterxml.jackson.core.JsonProcessingException;
import hcl.hackathon.hcl.entities.Bank;
import hcl.hackathon.hcl.entities.Customer;
import hcl.hackathon.hcl.entities.FavouriteBankAccount;
import hcl.hackathon.hcl.service.BankService;
import hcl.hackathon.hcl.service.ConverterService;
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
    PasswordEncoder passwordEncoder;
    @Autowired
    private BankService bankService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ConverterService converterService;

    @Override
    public void run(String... args) throws JsonProcessingException {
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

        // Antoan's test for converting json to xml
        FavouriteBankAccount favouriteBankAccount = new FavouriteBankAccount();
        favouriteBankAccount.setBank_name("My custom bank");
        favouriteBankAccount.setIban("IBAN 212415 5421358932");
        favouriteBankAccount.setName("Test");
        favouriteBankAccount.setId(11111L);

        String jsonInput = "{\n" +
                "    \"first_name\" : \"TRY\",\n" +
                "    \"last_name\" : \"HARD\",\n" +
                "    \"email_id\" : \"try123@hard.com\",\n" +
                "    \"primary_contact\" : \"123456\",\n" +
                "    \"secondary_contact\" : \"654321\",\n" +
                "    \"nickname\" : \"HCL\",\n" +
                "    \"personalIdNumber\" : \"67890\"\n" +
                "}";

        converterService.convertObjectToXml(favouriteBankAccount);
        converterService.convertJsonToXml(jsonInput);
    }

}