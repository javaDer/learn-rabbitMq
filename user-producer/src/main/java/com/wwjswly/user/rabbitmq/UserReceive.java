package com.wwjswly.user.rabbitmq;

import com.rabbitmq.client.Channel;
import com.wwjswly.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * UserSender
 *
 * @author ZhaoFa
 * @date 2019-03-20 16:37
 **/
@Component
@Slf4j
public class UserReceive {
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "user-queue", durable = "true"),
                    exchange = @Exchange(name = "user-exchange", durable = "true", type = "topic"),
                    key = "user.*")
    )
    @RabbitHandler
    public void onUserMessage(@Payload User user, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        log.info("接受到消息：" + user.getMessageId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
//        channel.basicAck(deliveryTag, false);
    }

}
