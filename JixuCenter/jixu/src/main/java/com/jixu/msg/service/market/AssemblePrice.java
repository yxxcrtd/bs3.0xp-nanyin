package com.jixu.msg.service.market;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.types.PriceDecideType;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.IssueInfo;
import com.orm.obj.SecPrice;

/**
 *  @ClassName 		AssemblePrice.java
 *	@author  		Pan Wei 
 *	@Date 			2016年8月9日 上午10:37:18
 *	@Description	[拼装Issuer消息]
 * PRICINGDATA_Fields="Operation,PriceID,SECID(),Price Date,Price,Source";
 *
 */

@Service("assemblePriceMsg")
public class AssemblePrice extends MsgExecuteService{

	@Override
	public String msgHeader() {
		return MsgTemplate.PRICINGDATA_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);

		int priceid=0;
		SecPrice oldsecprice=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			priceid=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
			oldsecprice=secPriceDao.loadEntity(SecPrice.class, priceid);
		}
		
		//如果有priceid字段，删除特殊处理
		if(operation.equalsIgnoreCase(delete)){
			if(oldsecprice == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,getFieldValue(fieldsHM, MsgFields.header),operation));
			}else{
				secPriceDao.deleteEntity(oldsecprice);
			}
			return;
		}
		
		//获取股票
		String[] secids=getFieldValue(fieldsHM, "security").split("\\|");
		String secid="";
		if(secids.length==3){
			secid=secids[1];
		}else{
			secid=secids[0];
		}
		
		IssueInfo sec=getSecurity(secid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//校验日期
		String PirceDateStr=getFieldValue(fieldsHM, "date").replaceAll("-", "");
		Date priceDate=checkDateFormat(msgcontainer,msgHeader(),PirceDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//价格
		double price=Double.valueOf(getFieldValue(fieldsHM, MsgFields.price));
		
		int priceto=1;
		priceto=Integer.valueOf(getFieldValue(fieldsHM, "priceto"));
		
		if(PriceDecideType.priceDesideHM.get(priceto)==null){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error3,getFieldValue(fieldsHM, MsgFields.header),String.valueOf(priceto)));
			return;
		}
		
		
		String yieldStr=getFieldValue(fieldsHM,"yield").trim();
		double yield=0;
		if(!yieldStr.equals(""))
			yield=Double.valueOf(yieldStr);
		
		
		//获取数据源
		int sourceID=Integer.valueOf(getFieldValue(fieldsHM, "source"));
		InterfaceSource source = interfaceSourceDao.loadSource(sourceID);
		if (source == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setSource(source);
			msgcontainer.setEntityObj(source);
		}
		
		
		
		
		SecPrice secprice=new SecPrice();
		secprice.setCusip(sec);
		secprice.setLst_update(new Date());
		secprice.setPrice(price);
		secprice.setPrice_date(priceDate);
		secprice.setSourceID(source);
		secprice.setPriceto(priceto);
		secprice.setYield(yield);
		

		
		if(operation.equalsIgnoreCase(add)){
			//如果是创建  查询该价格在当天该证券下是否已经存在  
			oldsecprice=secPriceDao.loadSecPriceByParams(secprice);
			if(oldsecprice == null){
				secPriceDao.saveEntity(secprice);
				msgcontainer.setObjID(secprice.getId());
			}else{
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.error7);
			}
		}else if(operation.equalsIgnoreCase(update)){
			if(oldsecprice == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,msgcontainer.getHeader(),msgcontainer.getFields()));
			}else{
				BeanUtils.copyProperties(secprice, oldsecprice, "id");
				secPriceDao.updateEntity(oldsecprice);
				msgcontainer.setObjID(oldsecprice.getId());
			}
		}else if(operation.equalsIgnoreCase(delete)){
			if(oldsecprice == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,msgcontainer.getHeader(),msgcontainer.getFields()));
			}else{
				secPriceDao.deleteEntity(oldsecprice);
			}
		}else{
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error8,msgcontainer.getHeader(),operation));
		}
		
		return;
	}

	@Override
	public String messageForm() {
		return "/forms/market/price_edit_form";
	}

	@Override
	public String messageName() {
		return "价格操作";
	}
}
