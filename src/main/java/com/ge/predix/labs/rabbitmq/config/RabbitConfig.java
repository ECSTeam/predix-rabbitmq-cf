package com.ge.predix.labs.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ge.predix.labs.rabbitmq.web.HomeController;

/***
 * 
 * @author Sergey.Vyatkin@ge.com 
 *         Configuration for RabbitMQ Please see details
 *         http://docs.spring.io/autorepo/docs/spring-cloud/1.0.0.RC3/api/org/springframework/cloud/config/java/AbstractCloudConfig.html
 *
 */

@Configuration
@ComponentScan
public class RabbitConfig extends AbstractCloudConfig {


	@Bean
    public Queue queue() {
        // This queue has the following properties:
        // name: my_durable
        // durable: true
        // exclusive: false
        // auto_delete: false
        return new Queue(HomeController.QUEUE_NAME, true, false, false);
    }

       @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory().rabbitConnectionFactory());
        rabbitAdmin.declareQueue(queue());
        return rabbitAdmin;
    }


	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory().rabbitConnectionFactory());
	}
}
