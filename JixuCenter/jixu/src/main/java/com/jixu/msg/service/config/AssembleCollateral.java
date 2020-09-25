package com.jixu.msg.service.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.TranCollateral;

/** 
 * @ClassName 		AssembleCollateral
 * @author  		Pan Wei 
 * @Date 			2016年8月24日 下午1:28:00 	
 * @Description		[]  
 */

@Service("collat")
public class AssembleCollateral extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.Collat_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields=msgcontainer.getFields();
		List<TranCollateral> supList=new ArrayList<TranCollateral>();
		//获取费用分支
		for(String subMsg:fields){
			genMsgContainer(MsgTemplate.CollatSub_Header,subMsg, msgcontainer);
			TranCollateral collateral=null;
			if(msgcontainer.isCorrectFlag()){
				collateral=(TranCollateral)msgcontainer.getEntityObj();
			}else{
				return;
			}
			supList.add(collateral);
		}
		msgcontainer.setEntityObj(supList);
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/trade/tradeCollateral_add_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "抵押物操作";
	}
}
