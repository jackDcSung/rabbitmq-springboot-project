package com.itheima.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfiguration {
    /**
     * 聲明交換機
     * @return Fanout類型交換機
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("hmall.fanout2");
    }

    /**
     * 第1個隊列
     */
    @Bean
    public Queue fanoutQueue3(){
        return new Queue("fanout.queue3");
    }

    /**
     * 綁定隊列和交換機
     */
    @Bean
    public Binding fanoutBindingQueue3(Queue fanoutQueue3, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }


    /**
     * 第2個隊列
     */
    @Bean
    public Queue fanoutQueue4(){
        return new Queue("fanout.queue4");
    }

    /**
     * 綁定隊列和交換機
     */
    @Bean
    public Binding bindingQueue4(){
        return BindingBuilder.bind(fanoutQueue4()).to(fanoutExchange());
    }
}