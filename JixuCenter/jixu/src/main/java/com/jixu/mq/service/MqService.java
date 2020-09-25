package com.jixu.mq.service;

import javax.jms.Message;

public interface MqService {
	
	public String destination();
	
	public void executeMQMsq(Message message);
}
