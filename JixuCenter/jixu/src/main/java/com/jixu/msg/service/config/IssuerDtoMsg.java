package com.jixu.msg.service.config;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.ReturnResult;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.IssuerDto;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.Issuer;
import com.orm.obj.RPSecType;

/** 
 * 	@ClassName 		AssembleIssuer.java
 *	@author  		Pan Wei 
 *	@Date 			2016年8月9日 上午10:37:18
 *	@Description	[拼装Issuer消息]
 *	@Msg			SMFISSUER(Operation,IssuerCode,IssuerName,IssuerShortName,Country(),IssuerType())
 */
@Service("jxIssuerDtoMsg")
public class IssuerDtoMsg extends MsgExecuteService<IssuerDto>{
	
	@Override
	public String msgHeader() {
		return  null;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		
		IssuerDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), IssuerDto.class);
		//如果是删除  就跳过dto验证
		if(!dto.getOperation().equalsIgnoreCase(delete)){
			validateDto(msgcontainer, dto);
		}
		//dto验证结果判断
		if(!msgcontainer.isCorrectFlag())
			return;
		
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String header=getFieldValue(fieldsHM, MsgFields.header);
		msgcontainer.setHeader(header);
		String actionType=getFieldValue(fieldsHM, MsgFields.operation);

		//更新或删除之前根据id查询发行商
		Issuer oldIssuer=null;
		if(actionType.equalsIgnoreCase(delete) || actionType.equalsIgnoreCase(update)){
			oldIssuer=issuerDao.loadIssuer(dto.getId());
			if(oldIssuer == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.error6);
			}else if(actionType.equalsIgnoreCase(delete)){
				if(oldIssuer.getCusip().size()>0){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.issuer_exist));
				}
			}
		}
		//新建或在更新判断发行商名字有没有重复
		if (actionType.equalsIgnoreCase(add)|| actionType.equalsIgnoreCase(update)) {
			oldIssuer = issuerDao.loadIssuerByCode(dto.getName());
			if (oldIssuer != null) {
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.issuer_name_exist)+" :"+dto.getName());
			}
		}
		//逻辑验证结果判断
		if(!msgcontainer.isCorrectFlag())
			return;
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/issuer_add_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "发行商操作";
	}
	
	@Override
	public MsgContainer completeEntity(IssuerDto entity, MsgContainer msgcontainer) {
		String operation=entity.getOperation();
		if(operation.equalsIgnoreCase(delete)){
			Issuer oldIssuer=issuerDao.loadIssuer(entity.getId());
			issuerDao.deleteEntity(oldIssuer);
		}else{
			Issuer issuer=new Issuer();
			issuer.setCountry(entity.getCountry());
			issuer.setIssuerName(entity.getName());
			issuer.setIssuerShortName(entity.getName());
			RPSecType rpsectype=getRPSecType2(Integer.valueOf(entity.getType()), msgcontainer);
			issuer.setRpSecType(rpsectype);
			issuer.setUpdateTime(new Date());
			issuer.setIssuerCode(entity.getLname());
			
			if(operation.equalsIgnoreCase(add)){
				issuerDao.saveEntity(issuer);
				
				msgcontainer.setEntityID(String.valueOf(issuer.getIssuerID()));
				msgcontainer.setEntityObj(issuer);
			}else if(operation.equalsIgnoreCase(update)){
				Issuer oldIssuer=issuerDao.loadIssuer(entity.getId());
				BeanUtils.copyProperties(issuer, oldIssuer, "issuerID");
				issuerDao.updateEntity(oldIssuer);
				
				msgcontainer.setEntityID(String.valueOf(oldIssuer.getIssuerID()));
				msgcontainer.setEntityObj(oldIssuer);
			}
		}
		return msgcontainer;
	}
}
