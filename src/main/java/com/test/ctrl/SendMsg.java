package com.test.ctrl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.bean.MenuInfo;
import com.test.util.RabbitmqConf;

@Component
public class SendMsg {
	@Autowired
	private AmqpTemplate amqp;
	
	public void send(MenuInfo mi)
	{
		amqp.convertAndSend(RabbitmqConf.QUEUE1,mi);
	}
}
