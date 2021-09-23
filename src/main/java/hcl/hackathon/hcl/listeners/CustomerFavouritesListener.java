package hcl.hackathon.hcl.listeners;

import hcl.hackathon.hcl.config.rabbitmq.MQConfig;
import hcl.hackathon.hcl.config.rabbitmq.message.MQAddFavouriteMessage;
import hcl.hackathon.hcl.config.rabbitmq.message.MQExceptionMessage;
import hcl.hackathon.hcl.entities.FavouriteBankAccount;
import hcl.hackathon.hcl.service.BankService;
import hcl.hackathon.hcl.service.FavouriteBankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CustomerFavouritesListener {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private BankService bankService;

    @Autowired
    private FavouriteBankAccountService favouriteBankAccountService;

    @Retryable( value = Exception.class, maxAttempts = 1)
    @RabbitListener(queues = MQConfig.ADD_QUEUE)
    public void addBankToFavourites(MQAddFavouriteMessage message) {
        log.info("Message: " + message + " received from queue: " + MQConfig.ADD_QUEUE);

        try {
            String bankCode = extrapolateBankCode(message);
            String bankName = bankService.resolveBankNameByCode(bankCode);

            FavouriteBankAccount favouriteBankAccount = new FavouriteBankAccount();

            if (bankName == null || bankName.equals("")) {
                bankName = "The IBAN provided is not correct! Please, try again with a different one!";
                //  throw new Exception("We couldn't find such bank in our DB!");
            }

            favouriteBankAccount.setBank_name(bankName);
            favouriteBankAccount.setName(message.getAccountName());
            favouriteBankAccount.setIban(message.getIban());

            favouriteBankAccountService.addFavouriteBankAccount(favouriteBankAccount);
            log.info("Favourite bank added!");

        } catch (Exception exception) {
            log.info("Exception occurred: " + exception.getMessage());
            sendExceptionToQueue(exception, message);
        }

    }

    private void sendExceptionToQueue(Exception ex, MQAddFavouriteMessage message) {
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.KEEP_ROUTING,
                new MQExceptionMessage(UUID.randomUUID().toString(), ex.getCause().getMessage(), ex.getLocalizedMessage(), ex, message));
    }

    private String extrapolateBankCode(MQAddFavouriteMessage message) {
        return message.getIban().replace(" ", "").substring(4, 8);
    }

}