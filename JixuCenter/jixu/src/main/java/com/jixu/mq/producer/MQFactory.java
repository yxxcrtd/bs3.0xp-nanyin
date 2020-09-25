package com.jixu.mq.producer;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.JMSObjectModel;
import com.jixu.common.type.MessageFields;
import com.orm.obj.PortfolioHdr;


@Service("mQFactory")
public class MQFactory {

	@Resource(name="commfuncService")
	protected CommFuncService comService;
	
	public String returnTopicName(JMSObjectModel model){
		
		String portfolio=model.getPortfolioCode();
		String topic=model.getContent().getClass().getSimpleName();
		//判断类是否实现了另一个类
		if(model.getContent() instanceof List){
			List<?> sendList=(List)model.getContent();
			if(sendList.size()>0){
				topic=sendList.get(0).getClass().getSimpleName();
			}
		}
		
		String topicName=topic+"_"+portfolio;
		return topicName;
	}
	
	public String returnDeleteTopic(String portnum){
		String topic="SynDeleteMsg";
		PortfolioHdr portfolio = comService.loadPortfolio(portnum);
		String topicName=topic+"_"+portfolio.getShortCode();
		return topicName;
	}
	
	public String returnDeleteContent2(String portnum,String startDate,String endDate){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put(MessageFields.operation, MessageFields.delete);
		map.put(MessageFields.portnum, String.valueOf(portnum));
		map.put(MessageFields.startDate,startDate);
		map.put(MessageFields.endDate, endDate);
		return map.toString();
	}
	
	public String returnDeleteContent(String portnum,String startDate){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put(MessageFields.operation, MessageFields.delete);
		map.put(MessageFields.portnum, String.valueOf(portnum));
		map.put(MessageFields.startDate,startDate);
		return map.toString();
	}
}
