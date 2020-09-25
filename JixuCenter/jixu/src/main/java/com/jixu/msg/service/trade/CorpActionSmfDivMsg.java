package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.SmfDivDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.IssueInfo;
import com.orm.obj.SmfDiv;

/** 
 * @ClassName 		AssembleStockTran
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				StockTran(Operation,TaNum,TranType,SecID(),TradeDate,SetDate,TradeMarket,Broker(),BNK(),MANAGER(),PORTFOLIO(),UNITES,PRINCIPAL,ACCRUED,TRANFEE(),CASH,FXRate,INTATMAT,SETTLECASH())  
 */

@Service
public class CorpActionSmfDivMsg extends MsgExecuteService{
	
	@Autowired
	private SmfDivDao smfDivDao;
	
	@Override
	public String msgHeader() {
		return "";
	}
	
	@Override
	public String msgFields() {
		return "";
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		
		
		int divID=0;
		/*String divIDStr=getFieldValue(fieldsHM, MsgFields.id);
		if(divIDStr!=null && !divIDStr.equals("")){
			divID=Integer.valueOf(divIDStr);
		}
		*/
		SmfDiv oldSmfDiv=null;
		if(operation.equalsIgnoreCase(delete) || operation.equalsIgnoreCase(update)){
			if(divID==0){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error14,msgcontainer.getHeader(),"ID"));
				return;
			}else{
				oldSmfDiv=smfDivDao.loadEntity(SmfDiv.class,divID);
				if(oldSmfDiv==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,msgcontainer.getHeader(),"ID:"+divID));
					return;
				}else{
					//撤帐
					if(operation.equalsIgnoreCase(delete)){
						smfDivDao.deleteEntity(oldSmfDiv);
						return;
					}
				}
			}
		}else if(operation.equalsIgnoreCase(add)){
			if(divID>0){
				oldSmfDiv=smfDivDao.loadEntity(SmfDiv.class,divID);
				if(oldSmfDiv != null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error7,msgcontainer.getHeader(),"ID:"+divID));
				}
			}
		}else{
			msgcontainer.setCorrectFlag(false);
		}
		
		
		
		int caType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeType));
		
		String[] secids=getFieldValue(fieldsHM, MsgFields.secid).split("\\|");
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
		
		String recordDate=getFieldValue(fieldsHM, MsgFields.recordDate).replaceAll("-", "");
		checkDateFormat(msgcontainer,msgHeader(),recordDate);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String exDate=getFieldValue(fieldsHM, MsgFields.exDate).replaceAll("-", "");
		checkDateFormat(msgcontainer,msgHeader(),exDate);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDate=exDate;
		if(fieldsHM.containsKey(MsgFields.settleDate)){
			settleDate=getFieldValue(fieldsHM, MsgFields.settleDate);
			settleDate=settleDate.replaceAll("-", "");
		}
		
		checkDateFormat(msgcontainer,msgHeader(),settleDate);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		double divRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.alRate));
		
		SmfDiv div=new SmfDiv();
		div.setCusip(sec.getId());
		div.setCaType(caType);
		div.setRecordDate(DateUtil.convertStr2Date(recordDate));
		div.setExDate(DateUtil.convertStr2Date(exDate));
		div.setPayDate(DateUtil.convertStr2Date(settleDate));
		div.setRate(divRate);
		div.setLastUpdate(new Date());
		
		if(operation.equalsIgnoreCase(add)){
			smfDivDao.saveEntity(div);
			msgcontainer.setObjID(div.getId());
			msgcontainer.setEntityObj(div);
		}else if(operation.equalsIgnoreCase(update)){
			oldSmfDiv.setCusip(div.getCusip());
			oldSmfDiv.setCaType(caType);
			oldSmfDiv.setRecordDate(div.getRecordDate());
			oldSmfDiv.setExDate(div.getExDate());
			oldSmfDiv.setPayDate(div.getPayDate());
			oldSmfDiv.setRate(div.getRate());
			oldSmfDiv.setLastUpdate(new Date());
			
			smfDivDao.updateEntity(oldSmfDiv);
			
			msgcontainer.setObjID(oldSmfDiv.getId());
			msgcontainer.setEntityObj(oldSmfDiv);
		}
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
