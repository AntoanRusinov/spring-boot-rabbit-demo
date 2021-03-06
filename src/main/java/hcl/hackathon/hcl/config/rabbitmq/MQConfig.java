package hcl.hackathon.hcl.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String EXCHANGE = "message_exchange";

    public static final String ADD_QUEUE = "add_message_queue";
    public static final String ADD_ROUTING = "add_message_routing";

    public static final String KEEP_QUEUE = "keep_message_queue";
    public static final String KEEP_ROUTING = "keep_message_routing";

    @Bean
    public Queue regQueue() {
        return new Queue(ADD_QUEUE);
    }

    @Bean
    public Queue mailQueue() {
        return new Queue(KEEP_QUEUE);
    }

    @Bean
    public TopicExchange mainTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding regBinding(Queue regQueue, TopicExchange mainTopicExchange) {
        return BindingBuilder
                .bind(regQueue)
                .to(mainTopicExchange)
                .with(ADD_ROUTING);
    }

    @Bean
    public Binding mailBinding(Queue mailQueue, TopicExchange mainTopicExchange) {
        return BindingBuilder
                .bind(mailQueue)
                .to(mainTopicExchange)
                .with(KEEP_ROUTING);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
