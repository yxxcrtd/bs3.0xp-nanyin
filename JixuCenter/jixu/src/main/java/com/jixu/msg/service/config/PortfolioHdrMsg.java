package com.jixu.msg.service.config;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.LotType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.CoaaGroup;
import com.orm.obj.ManagerGroup;
import com.orm.obj.PortfolioClient;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Province;

/** 
 * @ClassName 		AssemblePortfolioHdr
 * @author  		 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class PortfolioHdrMsg extends MsgExecuteService{
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		PortfolioHdr oldphdr=null;
		if(operation.equalsIgnoreCase(update)){
			String portnum=getFieldValue(fieldsHM, MsgFields.id);
			oldphdr=getPortfolio(portnum, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}else if(operation.equalsIgnoreCase(add)){
			//支持消息里直接传入投资组合的主键代码
			if(fieldsHM.containsKey(MsgFields.id)){
				Object idobj=fieldsHM.get(MsgFields.id);
				if(idobj!=null && !idobj.toString().equals("")){
					PortfolioHdr addport=portfolioHdrDao.loadPortfolio(idobj.toString());
					if(addport!=null){
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorMsg("投资组合主键已存在");
						return;
					}
				}
			}
		}
		
		int client=0;
		if(!JixuUserUtil.isJoyinTablesFlag())
			client=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.client));
		
		String name=getFieldValue(fieldsHM, MsgFields.name);
		
		String shortCode=getFieldValue(fieldsHM, MsgFields.code);
		if(operation.equalsIgnoreCase(add)){
			boolean portexistFlag=portfolioHdrDao.checkSamePortNameExist(shortCode);
			if(portexistFlag){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorCode(ReturnResult.port_sameCode_exist);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.port_sameCode_exist) + ": " + shortCode);
				return;
			}
		}
		
		int state=0;
		if(fieldsHM.containsKey(MsgFields.state)) {
			//可以接收省份没有传值进来
			String stateStr=getFieldValue(fieldsHM, MsgFields.state);
			if(stateStr!=null && !stateStr.trim().equals(""))
				state=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.state));
		}
		
		String currency=getFieldValue(fieldsHM, MsgFields.currency);
		
		int lot=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.lot));
		
		String holiday=getFieldValue(fieldsHM, MsgFields.holiday);
		
		String startDateStr=getFieldValue(fieldsHM, MsgFields.date);
		if(startDateStr.equals(""))
			startDateStr="2000-01-01";
		
		int type=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.type));
		
		String coa=getFieldValue(fieldsHM, MsgFields.coa);
		
		int glgroup=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.glgroup));
		
		int nav=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.nav));
		
		int weekendAccrual=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.weekendAccrual));
		
		int weekendVal=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.weekendVal));
		
		int acctDate=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.acctDate));
		
		int manager=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.manager));
		
		String navto1=getFieldValue(fieldsHM, MsgFields.navto1);
		
		String income2share=getFieldValue(fieldsHM, MsgFields.income2share);
		
		
		
		
		PortfolioHdr newPort=new PortfolioHdr();
		if(!JixuUserUtil.isJoyinTablesFlag())
			newPort.setPortfolioClient(client);
		
		newPort.setShortName(name);
		newPort.setShortCode(shortCode);
		if(state>0) {
			newPort.setProvince(new Province(state));
		}
		newPort.setCurID(currency);
		newPort.setLotSelection(lot);
		newPort.setHolidaySD(holiday);
		
		newPort.setInceptionDate(DateUtil.convertStr2Date2(startDateStr));
		newPort.setPortType(type);
		newPort.setCoaahdr(coa);
		newPort.setCoaaGroup(new CoaaGroup(glgroup));

		newPort.setNavPrecision(nav);
		newPort.setBookNBDays(weekendAccrual);
		newPort.setWkndValuationClose(weekendVal);
		newPort.setTradeSettleLedger(acctDate);
		newPort.setManagerGroup(manager);
		newPort.setJzgyFlag(Boolean.valueOf(navto1));
		newPort.setSyzfeFlag(Boolean.valueOf(income2share));
		
		
		newPort.setPortName(name);
		newPort.setSinglelot(LotType.SingleLot);
		
		newPort.setLastTranClose(newPort.getInceptionDate());
		newPort.setNextTranClose(newPort.getInceptionDate());
		newPort.setLastGLClose(newPort.getInceptionDate());
		newPort.setNextGLClose(newPort.getInceptionDate());
		
		int year=DateUtil.getYear(newPort.getInceptionDate());
		String endYearStr=year+"-12-31";
		newPort.setNextYealClose(DateUtil.convertStr2Date2(endYearStr));
		newPort.setLastActiveDate(new Date());
		
		
		
		if(operation.equalsIgnoreCase(add)){
			
			portfolioHdrDao.saveEntity(newPort);
			msgcontainer.setEntityID(newPort.getId());
			msgcontainer.setEntityObj(newPort);
		}else if(operation.equalsIgnoreCase(update)){
			
			BeanUtils.copyProperties(newPort, oldphdr,new String[]{"id"});
			portfolioHdrDao.updateEntity(oldphdr);
			msgcontainer.setEntityID(oldphdr.getId());
			msgcontainer.setEntityObj(oldphdr);
		}
		
	}
	
	@Override
	public String messageForm() {
		return null;
	}
	
	@Override
	public String messageName() {
		return "投资组合消息操作";
	}
	
	@Override
	public String msgHeader() {
		return null;
	}
}
