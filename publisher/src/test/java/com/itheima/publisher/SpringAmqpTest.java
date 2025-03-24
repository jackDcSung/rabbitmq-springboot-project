package com.itheima.publisher;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringAmqpTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testSimpleQueue() {

        //1.隊列名
        String queueName = "simple.queue";

        //2.發送消息
        String message = "hello,spring amqp!";


        rabbitTemplate.convertAndSend(queueName, message);


    }


    @Test
    public void testWorkQueue() {

        //1.隊列名
        String queueName = "work.queue";
        for (int i = 0; i < 50; i++) {

            String message = "hello,spring amqp!" + i;

            rabbitTemplate.convertAndSend(queueName, message);


        }


    }
    @Test
    public void testFanoutExchange() {
        // 交换机名称
        String exchangeName = "hmall.fanout";
        // 消息
        String message = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

}
