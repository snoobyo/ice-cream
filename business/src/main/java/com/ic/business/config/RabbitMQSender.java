package com.ic.business.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class RabbitMQSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void init() {
        //启用事务模式,不能开确认回调
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
//        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

//    @Transactional(rollbackFor = Exception.class,transactionManager = "rabbitTransactionManager")
//    public void sendMsg(String msg) {
////        CorrelationData correlationData = new CorrelationData();
////        correlationData.setId("1");
//        rabbitTemplate.convertAndSend(RabbitMQConfig.TEST_DIRECT_EXCHANGE, "test.item.e", msg);
//    }

    public void sendMsg(String msg) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("999");
        rabbitTemplate.convertAndSend(RabbitMQConfig.TEST_DIRECT_EXCHANGE, "test.item", msg, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息发送成功");
        } else {
            String id = correlationData.getId();
            System.out.println(id + " 消息发送失败：" + s);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        String msg = new String(returnedMessage.getMessage().getBody());
        String exchange = returnedMessage.getExchange();
        String routingKey = returnedMessage.getRoutingKey();
        int replyCode = returnedMessage.getReplyCode();
        String replyText = returnedMessage.getReplyText();

        System.out.println(msg);
        System.out.println(exchange);
        System.out.println(routingKey);
        System.out.println(replyCode);
        System.out.println(replyText);
    }
}
