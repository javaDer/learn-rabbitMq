package com.wwjswly.user.rabbitmq;

import com.wwjswly.user.entity.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserSender
 *
 * @author ZhaoFa
 * @date 2019-03-20 16:37
 **/
@Component
public class UserSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUser(User user) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(user.getMessageId());
        rabbitTemplate.convertAndSend("user-exchange", "user.us", user, correlationData);
    }
}
