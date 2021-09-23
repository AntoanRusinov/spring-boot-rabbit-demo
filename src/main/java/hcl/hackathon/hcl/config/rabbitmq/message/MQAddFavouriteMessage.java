package hcl.hackathon.hcl.config.rabbitmq.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MQAddFavouriteMessage {

    private String messageId;
    private String accountName;
    private String iban;

}