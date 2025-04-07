package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DirectConfiguration {
    /**
     * 聲明交換機
     * @return Fanout類型交換機
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("hmall.direct");
    }

    /**
     * 第1個隊列
     */
    @Bean
    public Queue directQueue1(){
        return new Queue("direct.queue1");
    }

    /**
     * 綁定隊列和交換機
     */
    @Bean
    public Binding directQueueBindingRed(Queue directQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("red");
    }



    @Bean
    public Binding directQueueBindingBlue(Queue directQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("yellow");
    }

}