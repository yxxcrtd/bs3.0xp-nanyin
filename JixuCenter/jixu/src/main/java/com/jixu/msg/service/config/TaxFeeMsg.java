package com.jixu.msg.service.config;

import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.ExpenseBaseType;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.PortExpenseCfgDao;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioHdr;

/** 
 * @ClassName 		TaxFeeMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月11日 下午1:33:12 	
 * @Description		[]  
 */

@Service
public class TaxFeeMsg extends MsgExecuteService{
	
	@Autowired
	private PortExpenseCfgDao portExpenseCfgDao;
	
	@Override
	public String msgHeader() {
		return "TaxFeeMsg";
	}
	
	@Override
	protected void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		PortExpenseCfg oldExpense=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			int cfgID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
			oldExpense=portExpenseCfgDao.loadEntity(PortExpenseCfg.class, cfgID);;
		}
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		if(operation.toUpperCase().equals(delete)){
			portExpenseCfgDao.deleteEntity(oldExpense);
		}else{
			PortExpenseCfg expense=new PortExpenseCfg();
			expense.setFixFeeFlag(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.type)));
			expense.setPortFeeName(getFieldValue(fieldsHM, MsgFields.desc));
			expense.setPortfolio(new PortfolioHdr(getFieldValue(fieldsHM, "pid")));
			expense.setClassService(getFieldValue(fieldsHM, MsgFields.classname));
			
			expense.setBaseAcct(getFieldValue(fieldsHM, MsgFields.basisacct));
			expense.setDrAcct(getFieldValue(fieldsHM, MsgFields.dracct).split("\\|")[0]);
			expense.setCrAcct(getFieldValue(fieldsHM, MsgFields.cracct).split("\\|")[0]);
			
			if(!getFieldValue(fieldsHM, MsgFields.amount).equals(""))
				expense.setExpenseAmount(Double.valueOf(getFieldValue(fieldsHM, MsgFields.amount)));
			
			if(!getFieldValue(fieldsHM, MsgFields.min).equals(""))
				expense.setLowestFee(Double.valueOf(getFieldValue(fieldsHM, MsgFields.min)));
			
			if(!getFieldValue(fieldsHM, MsgFields.max).equals(""))
				expense.setHighestFee(Double.valueOf(getFieldValue(fieldsHM, MsgFields.max)));
			
			expense.setIntCode(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.intCode)));
			expense.setCpnfReq(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.frequency)));
			expense.setCpnMo(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.month)));
			
			if(!getFieldValue(fieldsHM, MsgFields.day).equals(""))
				expense.setCpnDate(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.day)));
			
			if(!getFieldValue(fieldsHM, MsgFields.start).equals(""))
				expense.setStartDate(DateUtil.convertStr2Date2(getFieldValue(fieldsHM, MsgFields.start)));
			
			if(!getFieldValue(fieldsHM, MsgFields.end).equals(""))
					expense.setExpireDate(DateUtil.convertStr2Date2(getFieldValue(fieldsHM, MsgFields.end)));
			
			boolean prenavFlag=Boolean.valueOf(fieldsHM.get(MsgFields.prenav).toString());
			if(prenavFlag){
				expense.setNewNavFeeFlag(ExpenseBaseType.NewAssetExpense);
			}else{
				expense.setNewNavFeeFlag(ExpenseBaseType.UnNewAssetExpense);
			}
			String assetstmp=JSONArray.toJSONString(fieldsHM.get(MsgFields.assetType)).replaceAll("\"", "");
			String assets=assetstmp.replaceAll("\\[", "").replaceAll("\\]", "");
			expense.setActionItems(assets);
			
			expense.setOrderNum(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.priority)));
			
			if(operation.toUpperCase().equals(add)){
				portExpenseCfgDao.saveEntity(expense);
				msgcontainer.setObjID(expense.getPortFeeID());
				msgcontainer.setEntityObj(expense);
			}else if(operation.toUpperCase().equals(update)){
				BeanUtils.copyProperties(expense, oldExpense, "portFeeID");
				
				portExpenseCfgDao.updateEntity(oldExpense);
				msgcontainer.setObjID(oldExpense.getPortFeeID());
				msgcontainer.setEntityObj(oldExpense);
			}
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
