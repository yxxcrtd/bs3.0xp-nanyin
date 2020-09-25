package com.jixu.msg.service.market;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.FxRate;

/**
 * 组装汇率消息
 * FXRATE(ADD,RateID,Cur(),Fx Rate,Rate Date,Source())
 * 
 * @author Pan Wei
 *
 */

@Service("assembleFxRateMsg")
public class AssembleFxRate extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.FXRATE_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer)  throws NumberFormatException, MsgFieldLackException {
		
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);

		
		int rateid=0;
		if(fieldsHM.containsKey(MsgFields.id)){
			rateid=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
		}
		//如果有priceid字段，删除特殊处理
		if(rateid>0 && operation.equalsIgnoreCase(delete)){
			FxRate oldFxRate=fxRateDao.loadEntity(FxRate.class, rateid);
			if(oldFxRate == null){
				msgcontainer.setCorrectFlag(false);
				//msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,getFieldValue(fieldsHM, MsgFields.header),operation));
				msgcontainer.setErrorMsg(MsgError.error6);
			}else{
				fxRateDao.deleteEntity(oldFxRate);
			}
			return;
		}
		
		
		//外汇货币
		String currencyID=getFieldValue(fieldsHM, "local");
		getCurrency(currencyID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String baseCurrencyID=getFieldValue(fieldsHM, "functional");
		getCurrency(baseCurrencyID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double rate=Double.valueOf(getFieldValue(fieldsHM, "rate"));
		
		//校验日期
		String rateDateStr=getFieldValue(fieldsHM, "date").replaceAll("-", "");
		Date rateDate=checkDateFormat(msgcontainer,msgHeader(),rateDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		
		//获取数据源
		int sourceID = Integer.valueOf(getFieldValue(fieldsHM, "source"));
		InterfaceSource source = interfaceSourceDao.loadSource(sourceID);
		if (source == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		} 
		
		FxRate fxrate=new FxRate();
		fxrate.setCurrID(currencyID);
		fxrate.setBaseCurrID(baseCurrencyID);
		fxrate.setLstUpdate(new Date());
		fxrate.setRate(rate);
		fxrate.setRateDate(rateDate);
		fxrate.setSourceID(source);
		
		FxRate oldFxRate=fxRateDao.loadFxRate(fxrate);
		
		if(operation.equalsIgnoreCase(add)){
			if(oldFxRate == null){
				fxRateDao.saveFxRate(fxrate);
				msgcontainer.setObjID(fxrate.getId());
			}else{
				msgcontainer.setCorrectFlag(false);
				//msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error7,getFieldValue(fieldsHM, MsgFields.header),msgcontainer.getFields()));
				msgcontainer.setErrorMsg(MsgError.error7);
			}
		}else if(operation.equalsIgnoreCase(update)){
			if(oldFxRate == null){
				msgcontainer.setCorrectFlag(false);
				//msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,getFieldValue(fieldsHM, MsgFields.header),msgcontainer.getFields()));
				msgcontainer.setErrorMsg(MsgError.error6);
			}else{
				oldFxRate.setLstUpdate(fxrate.getLstUpdate());
				oldFxRate.setRate(fxrate.getRate());
				fxRateDao.updateFxRate(oldFxRate);
				msgcontainer.setObjID(oldFxRate.getId());
			}
		}else{
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error7,getFieldValue(fieldsHM, MsgFields.header),operation));
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/market/ex_edit_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "汇率操作";
	}
}
