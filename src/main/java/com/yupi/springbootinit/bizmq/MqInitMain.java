package com.yupi.springbootinit.bizmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 为了创建测试程序用到的交换机和队列（程序执行前执行一次）
 */
public class MqInitMain {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        String EXCHANGE_NAME="code_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String queueName="code_queue";
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,EXCHANGE_NAME,"my_routingKey");
    }
}
