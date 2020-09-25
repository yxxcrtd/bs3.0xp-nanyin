package com.jixu.msg.service.config;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.util.MsgPackage;
import com.jixu.dao.intf.CoaaDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.Coaa;
import com.orm.obj.Coaahdr;

/** 
 * 	@ClassName 		AssembCoa.java
 *	@author  		Pan Wei 
 *	@Date 			2016年8月9日 上午10:37:18
 *	@Description	[拼装Issuer消息]
 *	@Msg			Coa(OPERATION,AcctNum,AcctName,COAHDR(),Category,CloseAcctNum)
 */
@Service("coaMsg")
public class AssembleCoa extends MsgExecuteService{
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.Coa_Header;
	}
	
	public String msgFields() {
		return null;
	};
	
	@Resource(name="msgPackage")
	private MsgPackage msgPackage;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		String header=msgcontainer.getHeader();
		List<String> fields = msgcontainer.getFields();
		
		String actionType=fields.get(0).toUpperCase();
		String acctnum=fields.get(1);
		String acctname=fields.get(2);
		
		genMsgContainer(MsgTemplate.CoaHdr_Header,fields.get(3),msgcontainer);
		Coaahdr coahdr=null;
		if(msgcontainer.isCorrectFlag()){
			coahdr=(Coaahdr)msgcontainer.getEntityObj();
		}else{
			return;
		}
		String coaahdrnum=coahdr.getId();
		
		Coaa oldcoa=coaaDao.loadCoa(coaahdrnum, acctnum);

		
		if(actionType.equalsIgnoreCase(delete) || actionType.equalsIgnoreCase(update)){
			if(oldcoa == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6+": " + acctnum, header,fields));
			}else if(actionType.equalsIgnoreCase(delete)){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error20+": " + acctnum, header,fields));
			}
		}else if(actionType.equalsIgnoreCase(add) && oldcoa !=null){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error7+": " + acctnum,header,fields));
		}
		
		if(!msgcontainer.isCorrectFlag())
			return;
		
		int category=Integer.valueOf(fields.get(4));
		String closeacctnum=fields.get(5);
		if(!closeacctnum.equals("")){
			Coaa closecoa=coaaDao.loadCoa(coaahdrnum, closeacctnum);
			if(closecoa == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6+": " + closeacctnum, header,fields));
				return;
			}
		}
		
		int acctnumLength=acctnum.length();
		int acctlevel=0;
		String tmpacctnum="";
		if(acctnumLength==4){
			acctlevel=1;
		}else if(acctnumLength==6){
			tmpacctnum=acctnum.substring(0, 4);
			acctlevel=2;
		}else if(acctnumLength==8){
			tmpacctnum=acctnum.substring(0, 6);
			acctlevel=3;
		}else if(acctnumLength==14){
			tmpacctnum=acctnum.substring(0, 8);
			acctlevel=4;
		}else{
			acctlevel=5;
		}
		
		if(!tmpacctnum.equals("")){
			Coaa parentCoa=coaaDao.loadCoa(coaahdrnum, tmpacctnum);
			if(parentCoa==null){
				String newParanentCoa=msgPackage.coaMsg("ADD", tmpacctnum, acctname, String.valueOf(coaahdrnum), String.valueOf(category), "");
				genMsgContainer(MsgTemplate.Coa_Header, newParanentCoa, msgcontainer);
				if(!msgcontainer.isCorrectFlag()){
					return;
				}
			}
		}
		
		
		Coaa newCOA=new Coaa();
		newCOA.setChartnum(coaahdrnum);
		newCOA.setAccntnum(acctnum);
		newCOA.setAccntname(acctname);
		newCOA.setAccntcategory(category);
		newCOA.setClosetoaccntnum(closeacctnum);
		newCOA.setAcctLevel(acctlevel);
		newCOA.setAcctisleaf(1);
		
		if(actionType.equalsIgnoreCase(add)){
			coaaDao.saveCOA(newCOA);
			msgcontainer.setObjID(newCOA.getId());
			
			List<Coaa> coaaList=coaaDao.loadCoaParent(coaahdrnum, acctnum);
			for(Coaa parCoaa:coaaList){
				if(parCoaa.getAcctisleaf()==1){
					parCoaa.setAcctisleaf(0);
					coaaDao.updateCOA(parCoaa);
				}
			}
			
		}else if(actionType.equalsIgnoreCase(update)){
			if(oldcoa == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,header,fields));
			}else{
				oldcoa.setAccntname(acctname);
				oldcoa.setAccntcategory(category);
				coaaDao.updateCOA(oldcoa);
			}
		}else{
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error8,header,actionType));
		}
		
		return;
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/coa_table_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "会计科目操作";
	}
}
