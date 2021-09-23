package hcl.hackathon.hcl.service;

import hcl.hackathon.hcl.config.rabbitmq.MQConfig;
import hcl.hackathon.hcl.config.rabbitmq.message.MQAddFavouriteMessage;
import hcl.hackathon.hcl.controller.requests.AddFavouriteBankRequest;
import hcl.hackathon.hcl.entities.Bank;
import hcl.hackathon.hcl.repository.BankRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BankService {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private BankRepository bankRepository;

    public void insertBanks(List<Bank> banks) {
        bankRepository.saveAll(banks);
    }

    public long count() {
        return bankRepository.count();
    }

    public void addToFavourites(AddFavouriteBankRequest request) {
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.ADD_ROUTING,
                new MQAddFavouriteMessage(UUID.randomUUID().toString(), request.getName(), request.getIban()));
    }

    public String resolveBankNameByCode(String bankCode) {
        Bank bank = bankRepository.findByCode(bankCode);
        return bank.getBank_name();
    }

}