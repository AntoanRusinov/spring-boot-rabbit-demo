package hcl.hackathon.hcl.config.rabbitmq.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MQExceptionMessage {

    private String messageId;
    private String cause;
    private String localizedMessage;
    private Exception exception;
    private MQAddFavouriteMessage message;

}