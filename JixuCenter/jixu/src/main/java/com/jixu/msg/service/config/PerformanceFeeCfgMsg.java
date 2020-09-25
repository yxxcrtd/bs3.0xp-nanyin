package com.jixu.msg.service.config;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.dao.intf.PerformanceFeeCfgDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.IssueInfo;
import com.orm.obj.PerformanceFeeCfg;

/** 
 * @ClassName 		CashFlowMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class PerformanceFeeCfgMsg extends MsgExecuteService{
	
	@Autowired
	private PerformanceFeeCfgDao performanceFeeCfgDao;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();

		int id=0;
		if(fieldsHM.get(MsgFields.id)!=null){
			Object idobj=fieldsHM.get(MsgFields.id);
			id=(int)idobj;
		}
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		PerformanceFeeCfg oldCfg=performanceFeeCfgDao.loadEntity(PerformanceFeeCfg.class, id);
		if(operation.equals(delete)){
			if(oldCfg==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
			}else{
				performanceFeeCfgDao.deleteEntity(oldCfg);
			}
			return;
		}
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
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
		
		int lotnum=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.contract));
		
		double benchMark=Double.valueOf(getFieldValue(fieldsHM, "benchMark"));
		
		int returnRateType=Integer.valueOf(getFieldValue(fieldsHM, "returnRateType"));
		int expenseBasisType=Integer.valueOf(getFieldValue(fieldsHM, "expenseBasisType"));
		double minLevel=Double.valueOf(getFieldValue(fieldsHM, "minLevel"));
		double maxLevel=Double.valueOf(getFieldValue(fieldsHM, "maxLevel"));
		
		Object clawBackFlagObj=fieldsHM.get("clawBackFlag");
		boolean clawBackFlag=Boolean.valueOf(clawBackFlagObj.toString());
		
		Object activeFlagObj=fieldsHM.get("activeFlag");
		boolean activeFlag=Boolean.valueOf(activeFlagObj.toString());
		
		int clawBackExpenseType=Integer.valueOf(getFieldValue(fieldsHM, "clawBackExpenseType"));
		
		double inPercentage=Double.valueOf(getFieldValue(fieldsHM, "inPercentage"));
		double outPercentage=Double.valueOf(getFieldValue(fieldsHM, "outPercentage"));
		String drAcct=getFieldValue(fieldsHM, "drAcct").split("\\|")[0];
		String crAcct=getFieldValue(fieldsHM, "crAcct").split("\\|")[0];;
		
		PerformanceFeeCfg newCfg=new PerformanceFeeCfg();
		newCfg.setPortnum(portnum);
		newCfg.setCusip(sec.getId());
		newCfg.setLotnum(lotnum);
		newCfg.setBenchMark(benchMark);
		newCfg.setReturnRateType(returnRateType);
		newCfg.setExpenseBasisType(expenseBasisType);
		newCfg.setMinLevel(minLevel);
		newCfg.setMaxLevel(maxLevel);
		newCfg.setClawBackFlag(clawBackFlag);
		newCfg.setClawBackExpenseType(clawBackExpenseType);
		newCfg.setInPercentage(inPercentage);
		newCfg.setOutPercentage(outPercentage);
		newCfg.setDrAcct(drAcct);
		newCfg.setCrAcct(crAcct);
		newCfg.setActiveFlag(activeFlag);
		
		if(operation.equals(add)){
			performanceFeeCfgDao.saveEntity(newCfg);
			msgcontainer.setObjID(newCfg.getId());
			msgcontainer.setEntityObj(newCfg);
		}else{
			BeanUtils.copyProperties(newCfg, oldCfg, "id");
			performanceFeeCfgDao.updatePerformanceFee(oldCfg);
			msgcontainer.setObjID(oldCfg.getId());
			msgcontainer.setEntityObj(oldCfg);
		}
	}
	
	
	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String messageName() {
		return "绩效费配置";
	}
	
	@Override
	public String msgHeader() {
		// TODO Auto-generated method stub
		return null;
	}
}
