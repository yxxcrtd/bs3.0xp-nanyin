package com.jixu.msg.service.market;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.CashFlowCfg;

/** 
 * @ClassName 		CashFlowMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 */

@Service
public class CashFlowFloatMsg extends MsgExecuteService{
	
	@Autowired
	private ConfigService configService;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String cashflowid=getFieldValue(fieldsHM, "pid");
		CashFlowCfg cfg=cashFlowCfgDao.loadCashFlowCfg(cashflowid);
		
		String cusip=getFieldValue(fieldsHM, MsgFields.secid);
		
		//浮动利率的计算规则选择，相加基数，相乘系数，复利计算
		int vrMethod=Integer.valueOf(getFieldValue(fieldsHM, "vrMethod"));
		
		//浮动利率的辅助浮动规则，相乘系数，复利计算的辅助计算数值
		double vrBasisPtAdjustment=Double.valueOf(getFieldValue(fieldsHM, "vrBasisPtAdjustment"));
		
		//浮动利率的最大值
		double maxRate=0;
		if(!getFieldValue(fieldsHM, MsgFields.max).equals("")){
			maxRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.max));
		}
		
		//浮动利率的最小值
		double minRate=0;
		if(!getFieldValue(fieldsHM, MsgFields.min).equals("")){
			minRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.min));
		}
		
		//浮动利率的利率重置频率
		int frequency=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.frequency));
		
		//浮动利率重置的月份
		int month=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.month));
		
		//浮动利率重置的日期
		int day=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.day));
		
		//如果vrResetFreq选择了跟随付款日，这里则可以选择:无、下一个工作日，上一个工作日，修正的下一个工作日
		int vrResetNDays=Integer.valueOf(getFieldValue(fieldsHM, "vrResetNDays"));
		
		//利率重置的假期安排，用在浮动利率等
		String holidaySD=getFieldValue(fieldsHM, MsgFields.holidaySD);
		
		//浮动利率调整月
		int vrDeterAdjMo=Integer.valueOf(getFieldValue(fieldsHM, "vrDeterAdjMo"));
		
		//浮动利率调整日
		int vrDeterAdjDays=Integer.valueOf(getFieldValue(fieldsHM, "vrDeterAdjDays"));
		
		cfg.setVrBase(cusip);
		cfg.setVrMethod(vrMethod);
		cfg.setVrBasisPtAdjustment(vrBasisPtAdjustment);
		cfg.setVrMax(maxRate);
		cfg.setVrMin(minRate);
		cfg.setVrResetFreq(frequency);
		cfg.setVrResetMo(month);
		cfg.setVrResetDate(day);
		cfg.setVrResetNDays(vrResetNDays);
		cfg.setResetHolidaySched(holidaySD);
		cfg.setVrDeterAdjMo(vrDeterAdjMo);
		cfg.setVrDeterAdjDays(vrDeterAdjDays);
		
		configService.updateCashFlowCfg(cfg);
		msgcontainer.setEntityID(cfg.getCashFlowID());
		msgcontainer.setEntityObj(cfg);
	}
	
	
	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String messageName() {
		return "现金流利率消息";
	}
	
	@Override
	public String msgHeader() {
		// TODO Auto-generated method stub
		return "";
	}
}
