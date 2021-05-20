package com.example.springinit.producer;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderApp {
    private static final String EXCHANGE_NAME = "directExchanger";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            ((Channel) channel).exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            String message = "What do you want to publish today?";

//            channel.basicPublish(EXCHANGE_NAME, "programming.best-practices.java", null, message.getBytes("UTF-8"));
            channel.basicPublish(EXCHANGE_NAME, "java", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
