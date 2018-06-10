package com.example.aba.test;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.aba.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(2);

    public CountDownLatch getLatch() {
        return latch;
    }

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    // 通过rabbitmq 自带的 Message 来接收 User
    @RabbitListener(queues = "queue1")
    public void receiveMessage(final Message message) {
        log.info("Received message as generic: {}", message.toString());
        latch.countDown();
    }

    // 自定义的 user 接收
    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(final User user) {
        log.info("Received message as specific class: {}", user);
        latch.countDown();
    }

}