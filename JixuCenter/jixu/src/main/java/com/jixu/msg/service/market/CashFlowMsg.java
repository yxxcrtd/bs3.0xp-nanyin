package com.jixu.msg.service.market;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.CouponData;
import com.jixu.common.types.InterestCpnMo;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.framework.msg.MsgError;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.CashFlowPar;
import com.orm.obj.CashFlowPayment;
import com.orm.obj.CashFlowRate;

/** 
 * @ClassName 		CashFlowMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class CashFlowMsg extends MsgExecuteService{
	
	@Autowired
	private ConfigService configService;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();

		int cpntype=Integer.valueOf(getFieldValue(fieldsHM, "cfType"));
		
		// 利率重置
		JSONArray rateArray=new JSONArray();
		if(fieldsHM.containsKey("rates")){
			 String cfcRate=JsonUtil.objToString(fieldsHM.get("rates"));
			 rateArray=JSONArray.parseArray(cfcRate);
		}
		
		// 付息计划
		JSONArray paymentArray=new JSONArray();
		if(fieldsHM.containsKey("payments")){
			 String cfcPayment=JsonUtil.objToString(fieldsHM.get("payments"));
			 paymentArray=JSONArray.parseArray(cfcPayment);
		}
		
		//浮动利率重置
		HashMap<String, Object> floadMap=null;
		if(fieldsHM.containsKey("floats")){
			String cfcFloats=JsonUtil.objToString(fieldsHM.get("floats"));
			floadMap=JSONObject.parseObject(cfcFloats, HashMap.class);
		}
		
		// 还本计划
		JSONArray parsArray = new JSONArray();
		if (fieldsHM.containsKey("pars")) {
			String cfcPars = JsonUtil.objToString(fieldsHM.get("pars"));
			parsArray = JSONArray.parseArray(cfcPars);
		}
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		String id="0";
		if(fieldsHM.get(MsgFields.id)!=null){
			id=getFieldValue(fieldsHM, MsgFields.id);
		}
		
		CashFlowCfg oldcfc=cashFlowCfgDao.loadCashFlowCfg(id);
		if(operation.equals(delete)){
			if(oldcfc==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
			}else{
				configService.deleteCashFlowCfg(oldcfc);
			}
			return;
		}
		String name=getFieldValue(fieldsHM, MsgFields.name);
		int recOrPay=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.type));
		
		Date firstDate=DateUtil.convertStr2Date2(getFieldValue(fieldsHM, "accurStartDate"));
		String holidaySD=getFieldValue(fieldsHM, "holiday");
		int activeFlag=Integer.valueOf(getFieldValue(fieldsHM, "status"));
		
		//头部计提
		int startNDayBase=Integer.valueOf(getFieldValue(fieldsHM, "basis"));
		int startNDays=Integer.valueOf(getFieldValue(fieldsHM, "accrStartN"));
		int startNDaysType=Integer.valueOf(getFieldValue(fieldsHM, "accrStartType"));
		
		//尾部计提
		int endNBase=Integer.valueOf(getFieldValue(fieldsHM, "accrEndbasis"));
		int endNDays=Integer.valueOf(getFieldValue(fieldsHM, "accrEndN"));
		int endNDaysType=Integer.valueOf(getFieldValue(fieldsHM, "accrEndType"));
		
		//计提利息
		boolean externalRateFlag=Boolean.valueOf(fieldsHM.get("externalRate").toString());
		//合同利率
		double couponRate=0;
		if(fieldsHM.get("contractRate")!=null&&!fieldsHM.get("contractRate").equals("")){
			String couponRateStr=getFieldValue(fieldsHM, "contractRate");
			couponRate=Double.valueOf(couponRateStr);
		}
		
		//固定计提
		double fixIncome=0;
		if(fieldsHM.get("fixedValue")!=null&&!fieldsHM.get("fixedValue").equals("")){
			String fixedValue=getFieldValue(fieldsHM, "fixedValue");
			fixIncome=Double.valueOf(fixedValue);
		}
		
		
		int intCode=Integer.valueOf(getFieldValue(fieldsHM, "dayCount"));
		
		//付款信息
		int paymentType=Integer.valueOf(getFieldValue(fieldsHM, "carryOver"));
		int cpnFreq=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.frequency));
		int cpnMo=0;
		if(cpnFreq==CouponData.Annual || cpnFreq==CouponData.Quarterly || cpnFreq==CouponData.SemiAnnual || cpnFreq==CouponData.Annual){
			cpnMo=Integer.valueOf(getFieldValue(fieldsHM, "paymentMonth"));
		}
		
		int cpnDate=0;
		if(cpnFreq!=CouponData.AtMaturity && cpnFreq!=CouponData.Scheduled){
			cpnDate=Integer.valueOf(getFieldValue(fieldsHM, "paymentDay"));
		}
		int cpnNDays=0;
		if(cpnFreq!=CouponData.AtMaturity && cpnFreq!=CouponData.Scheduled){
			cpnNDays=Integer.valueOf(getFieldValue(fieldsHM, "paymentN"));
		}
		
		int cpnNDaysType=Integer.valueOf(getFieldValue(fieldsHM, "paymentNType"));
		
		Object autoFlagObj=fieldsHM.get("autoFlag");
		boolean autoFlag=Boolean.valueOf(autoFlagObj.toString());
		
		Object autoInterestFlagObj=fieldsHM.get("autoInterestFlag");
		boolean autoInterestFlag=Boolean.valueOf(autoInterestFlagObj.toString());
		
		if(cpnFreq==CouponData.Monthly){
			cpnMo=InterestCpnMo.Monthly;
		}else if(cpnFreq==CouponData.AtMaturity || cpnFreq==CouponData.Scheduled){
			cpnDate=0;
		}
		
		CashFlowCfg cfcfg=new CashFlowCfg();
		cfcfg.setCashFlowName(name);
		cfcfg.setRecOrPay(recOrPay);
		cfcfg.setFirstIntDate(firstDate);
		cfcfg.setCpnType(cpntype);
		cfcfg.setHolidaySD(holidaySD);
		cfcfg.setActiveFlag(activeFlag);
		
		cfcfg.setStartNDaysBase(startNDayBase);
		cfcfg.setStartNDays(startNDays);
		cfcfg.setStartNDaysType(startNDaysType);
		
		cfcfg.setEndNDaysBase(endNBase);
		cfcfg.setEndNDays(endNDays);
		cfcfg.setEndNdaysType(endNDaysType);
		
		cfcfg.setExternalRateFlag(externalRateFlag);
		cfcfg.setCoupon(couponRate);
		cfcfg.setFixedIncome(fixIncome);
		cfcfg.setIntCode(intCode);
		
		cfcfg.setPaymentKind(paymentType);
		cfcfg.setCpnFreq(cpnFreq);
		cfcfg.setCpnMo(cpnMo);
		cfcfg.setCpnDate(cpnDate);
		cfcfg.setCpnDelayDateNum(cpnNDays);
		cfcfg.setCpnDelayDateType(cpnNDaysType);
		cfcfg.setAutoSetFlag(autoFlag);
		cfcfg.setAutoInterestFlag(autoInterestFlag);
		
		if(operation.equals(add)){
			configService.createCashFlowCfg(cfcfg);
			if(rateArray.size()>0){ //利率重置   这个地方只做一次性批量增加   利率的删除和修改在CashFlowController
				for (int i = 0; i < rateArray.size(); i++) {
					CashFlowRate  cfr=new CashFlowRate();
					cfr.setCfid(cfcfg.getCashFlowID());
					cfr.setCfDate(DateUtil.convertStr2Date2(rateArray.getJSONObject(i).getString("startDate")));
					cfr.setCfRate(rateArray.getJSONObject(i).getDoubleValue("rate"));
					configService.createCashFlowRate(cfr);
				}
			}
			if(paymentArray.size()>0){ //付息计划
				for (int i = 0; i < paymentArray.size(); i++) {
					CashFlowPayment cfp=new CashFlowPayment();
					cfp.setCfid(cfcfg.getCashFlowID());
					cfp.setPayDate(DateUtil.convertStr2Date2(paymentArray.getJSONObject(i).getString(MsgFields.settleDate)));
					configService.createCashFlowPayment(cfp);
				}
			}
			
			if(floadMap!=null&&floadMap.size()>0){
				floadMap.put("pid",String.valueOf(cfcfg.getCashFlowID()));
				floadMap.put(MsgFields.header,"cashFlowFloatMsg");
				floadMap.put(MsgFields.operation,"ADD");
				MsgContainer floatContainer=new MsgContainer();
				floatContainer.setFieldsHM(floadMap);
				MsgFactory.executeMsgBean(floatContainer);
			}
			
			if(parsArray.size()>0){ //还本计划
				for (int i = 0; i < parsArray.size(); i++) {
					CashFlowPar cfp=new CashFlowPar();
					cfp.setCfid(cfcfg.getCashFlowID());
					cfp.setPaymentDate(DateUtil.convertStr2Date2(parsArray.getJSONObject(i).getString("startDate")));
					cfp.setPaymentRate(parsArray.getJSONObject(i).getDoubleValue("parPayment"));
					configService.createCashFlowPar(cfp);
				}
			}
			msgcontainer.setEntityID(cfcfg.getCashFlowID());
			msgcontainer.setEntityObj(cfcfg);
		}else{
			
			if(floadMap!=null&&floadMap.size()>0){
				floadMap.put("pid",String.valueOf(oldcfc.getCashFlowID()));
				floadMap.put(MsgFields.header,"cashFlowFloatMsg");
				floadMap.put(MsgFields.operation,MsgFields.update);
				MsgContainer floatContainer=new MsgContainer();
				floatContainer.setFieldsHM(floadMap);
				floatContainer=MsgFactory.executeMsgBean(floatContainer);
				CashFlowCfg cfgfloat=(CashFlowCfg) floatContainer.getEntityObj();
				cfcfg.setVrBase(cfgfloat.getVrBase());
				cfcfg.setVrMethod(cfgfloat.getVrMethod());
				cfcfg.setVrBasisPtAdjustment(cfgfloat.getVrBasisPtAdjustment());
				cfcfg.setVrMax(cfgfloat.getVrMax());
				cfcfg.setVrMin(cfgfloat.getVrMin());
				cfcfg.setVrResetFreq(cfgfloat.getVrResetFreq());
				cfcfg.setVrResetMo(cfgfloat.getVrResetMo());
				cfcfg.setVrResetDate(cfgfloat.getVrResetDate());
				cfcfg.setVrResetNDays(cfgfloat.getVrResetNDays());
				cfcfg.setResetHolidaySched(cfgfloat.getResetHolidaySched());
				cfcfg.setVrDeterAdjMo(cfgfloat.getVrDeterAdjMo());
				cfcfg.setVrDeterAdjDays(cfgfloat.getVrDeterAdjDays());
			}
			
			BeanUtils.copyProperties(cfcfg, oldcfc, "cashFlowID");
			configService.updateCashFlowCfg(oldcfc);
			msgcontainer.setEntityID(oldcfc.getCashFlowID());
			msgcontainer.setEntityObj(oldcfc);
		}
	}
	
	
	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String messageName() {
		return "现金流消息";
	}
	
	@Override
	public String msgHeader() {
		// TODO Auto-generated method stub
		return null;
	}
}
