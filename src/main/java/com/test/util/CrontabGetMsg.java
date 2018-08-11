package com.test.util;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rabbitmq.client.ConnectionFactory;
import com.test.bean.M2SInfo;
import com.test.bean.MenuInfo;
import com.test.service.IMenuService;

@Component
public class CrontabGetMsg {
	@Autowired
	private AmqpTemplate amqp;
	@Reference(check=false)
	private IMenuService serv;
	
	//秒 分 时 日 月 周 年
	//每五秒接收一次消息
	@Scheduled(cron="0/5 * * ? * *")
	public void getMsg()
	{
		//System.out.println("getMsg() "+new java.sql.Timestamp(System.currentTimeMillis()));
		try
		{
			Message msg = amqp.receive(RabbitmqConf.QUEUE1);
			if(msg == null)
				return;
			byte[] data = msg.getBody();
			Object obj = toObject(data);
			if(obj instanceof MenuInfo)
			{
				MenuInfo mi = (MenuInfo)obj;
				String sid = mi.getSid();
				Integer id = mi.getId();
				System.out.println("getMsg id="+id+",name="+mi.getName()+",sid="+sid);
				if(id == null)//新增
				{
					mi = serv.saveMenu(mi);
					System.out.println("saveMenu genId = "+mi.getId());
				}
				else//更新
				{
					serv.updateMenu(mi);
					//删除原来关联中间表数据
					serv.deleteM2S(mi.getId());
				}
				String[] dim = sid.split(",");
				for(String sid2:dim)
				{
					M2SInfo m2s = new M2SInfo();
					m2s.setSid(new Integer(sid2));
					m2s.setMid(mi.getId());
					serv.saveM2S(m2s);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Object toObject(byte[] data)
	{
		try
		{
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object obj = ois.readObject();
			bais.close();
			return obj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
