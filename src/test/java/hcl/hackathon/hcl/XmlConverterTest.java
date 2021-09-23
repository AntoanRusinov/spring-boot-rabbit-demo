package hcl.hackathon.hcl;

import com.fasterxml.jackson.core.JsonProcessingException;
import hcl.hackathon.hcl.entities.FavouriteBankAccount;
import hcl.hackathon.hcl.service.ConverterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This is a Javadoc
 *
 * @author AntoanRusinov
 */
@SpringBootTest
public class XmlConverterTest {

    @Autowired
    private ConverterService converterService;

    @Test
    void convertJsonToXml() throws JsonProcessingException {
        String jsonInput = "{\n" +
                "    \"first_name\" : \"TRY\",\n" +
                "    \"last_name\" : \"HARD\",\n" +
                "    \"email_id\" : \"try123@hard.com\",\n" +
                "    \"primary_contact\" : \"123456\",\n" +
                "    \"secondary_contact\" : \"654321\",\n" +
                "    \"nickname\" : \"HCL\",\n" +
                "    \"personalIdNumber\" : \"67890\"\n" +
                "}";

        String convertedXml = converterService.convertJsonToXml(jsonInput);
        Assertions.assertTrue(convertedXml.contains("<first_name>"));
        Assertions.assertTrue(convertedXml.contains("</first_name>"));

        Assertions.assertTrue(convertedXml.contains("<email_id>"));
        Assertions.assertTrue(convertedXml.contains("</email_id>"));
    }

    @Test
    void convertObjectToXml() throws JsonProcessingException {
        FavouriteBankAccount favouriteBankAccount = new FavouriteBankAccount();
        favouriteBankAccount.setBank_name("My custom bank");
        favouriteBankAccount.setIban("IBAN 212415 5421358932");
        favouriteBankAccount.setName("Test");
        favouriteBankAccount.setId(11111L);

        String convertedXml = converterService.convertObjectToXml(favouriteBankAccount);
        Assertions.assertTrue(convertedXml.contains("<bank_name>"));
        Assertions.assertTrue(convertedXml.contains("</bank_name>"));

        Assertions.assertTrue(convertedXml.contains("<iban>"));
        Assertions.assertTrue(convertedXml.contains("</iban>"));
    }

}