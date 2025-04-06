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
    public void testWorkQueue() throws InterruptedException {

        //1.隊列名
        String queueName = "work.queue";
        for (int i = 0; i < 50; i++) {

            String message = "hello,spring amqp!" + i;

            rabbitTemplate.convertAndSend(queueName, message);

            Thread.sleep(20);
        }


    }
    @Test
    public void testFanoutExchange() {
        // 交換機名稱
        String exchangeName = "hmall.fanout";
        // 消息
        String message = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
    
    @Test
    public void testSendDirectQueue() {
        // 交換機名稱
        String exchangeName = "hmall.direct";
        // 消息
        String message = "紅色警報！日本亂排核廢水，導致海洋生物變異，驚現哥斯拉！";
        // 發送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    @Test
    public void testSendTopicExchange() {
        // 交換機名稱
        String exchangeName = "hmall.topic";
        // 消息
        String message = "今天天氣好!";
        // 發送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }

}
