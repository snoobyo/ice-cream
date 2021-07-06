package com.ic.business.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //交换机名称
    public static final String TEST_DIRECT_EXCHANGE = "test_direct_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "test_queue";

    //声明交换机
    @Bean("testDirectExchange")
    public Exchange topicExchange() {
        return ExchangeBuilder.directExchange(TEST_DIRECT_EXCHANGE).durable(true).build();
    }

    //声明队列
    @Bean("testQueue")
    public Queue itemQueue() {
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    //绑定队列和交换机
    @Bean
    public Binding itemQueueExchange(@Qualifier("testQueue") Queue queue,
                                     @Qualifier("testDirectExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("test.item").noargs();
    }

    /**
     * 配置启用rabbitmq事务
     * @param connectionFactory
     * @return
     */
    @Bean("rabbitTransactionManager")
    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }
}
