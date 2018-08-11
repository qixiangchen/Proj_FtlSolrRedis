package com.test.util;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//RabbitMQ配置类，替代XML配置
@Configuration
public class RabbitmqConf {
	public static String QUEUE1 = "test_queue1";
	public static String EXCHANGE1 = "test_exg1";
	
	//生成队列test_queue1
	@Bean
	public Queue queue1()
	{
		return new Queue(QUEUE1);
	}
	
	//生成交换机test_exg1
	@Bean
	public DirectExchange exg1()
	{
		return new DirectExchange(EXCHANGE1);
	}
	
	//绑定队列与交换机
    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(queue1()).to(exg1()).with("java");
    }

	
}
