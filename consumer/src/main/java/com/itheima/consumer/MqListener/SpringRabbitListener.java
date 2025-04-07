package com.itheima.consumer.MqListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
@Slf4j
public class SpringRabbitListener {
    // 利用RabbitListener來聲明要監聽的隊列信息
    // 將來一旦監聽的隊列中有了消息，就會推送給當前服務，調用當前方法，處理消息。
    // 可以看到方法體中接收的就是消息體的內容
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg){
        System.out.println("消費者收到 simple.queue 的消息：[" + msg + "] ");
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消費者1 收到了 work.queue 的消息：[" + msg + "] ");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消費者2 收到了 work.queue 的消息.......：[" + msg + "] ");
        Thread.sleep(200);
    }



    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        System.out.println("消費者1接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {
        System.out.println("消費者2接收到Fanout消息：【" + msg + "】");
    }

//    @RabbitListener(queues = "direct.queue1")
//    public void listenDirectQueue1(String msg) {
//        System.out.println("消費者1接收到direct.queue1的消息：【" + msg + "】");
//    }
//
//    @RabbitListener(queues = "direct.queue2")
//    public void listenDirectQueue2(String msg) {
//        System.out.println("消費者2接收到direct.queue2的消息：【" + msg + "】");
//    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1", durable = "true"),
            exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String msg) throws InterruptedException {
        System.out.println("消費者1 收到了 direct.queue1的消息: [" + msg +"] ");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2", durable = "true"),
            exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2(String msg) throws InterruptedException {
        System.out.println("消費者2 收到了 direct.queue2的消息: [" + msg +"] ");
    }



    @RabbitListener(queues = "topic.queue1")
    public void listenTopicQueue1(String msg){
        System.out.println("消費者1接收到topic.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "topic.queue2")
    public void listenTopicQueue2(String msg){
        System.out.println("消費者2接收到topic.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "object.queue")
    public void listenObject(Map<String,Object> msg){
        System.out.println("object.queue2的消息：【" + msg + "】");
    }
}
