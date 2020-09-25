package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.IssueInfo;
import com.orm.obj.SecAcct;
import com.orm.obj.TranCollateral;

/** 
 * @ClassName 		AssembleCollat
 * @author  		Pan Wei 
 * @Date 			2016年8月22日 下午3:28:42 	
 * @Description		[]  
 */

@Service("collatSub")
public class AssembleCollateralSub extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.CollatSub_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields=msgcontainer.getFields();
		
		//CollatSub_Fields="SecAcct(),SecID(),Units";
		//获取费用分支
		TranCollateral collateral=new TranCollateral();
		
		genMsgContainer(MsgTemplate.SecAcct_Header,fields.get(0), msgcontainer);
		SecAcct secacct=null;
		if(msgcontainer.isCorrectFlag()){
			secacct=(SecAcct)msgcontainer.getEntityObj();
		}else{
			return;
		}
		
		genMsgContainer(MsgTemplate.SECID_Header,fields.get(1), msgcontainer);
		IssueInfo sec=null;
		if(msgcontainer.isCorrectFlag()){
			sec=(IssueInfo)msgcontainer.getEntityObj();
		}else{
			return;
		}
		
		double units=Double.valueOf(fields.get(2));
		
		collateral.setCusip(sec);
		collateral.setAmount(units);
		collateral.setLocation(secacct.getAcctID());
			
		msgcontainer.setEntityObj(collateral);
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return null;
	}
}
