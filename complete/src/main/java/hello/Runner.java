package hello;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        String[] msgs= {"one","two","three","four","five","six"} ;
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.draqon", msgs);
        // rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.drqqon", "Hello from RabbitMQ Run!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
