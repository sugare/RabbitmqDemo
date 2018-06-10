package com.example.aba.test;

import com.example.aba.AbaApplication;
import com.example.aba.config.RabbitmqConfig;
import com.example.aba.po.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        User u = new User();
        u.setAge(11);
        u.setName("song");
        rabbitTemplate.convertAndSend(RabbitmqConfig.topicExchangeName, "foo.bar.baz", u);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}