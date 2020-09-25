package com.jixu.msg.service.market;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.util.DateUtil;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.CashFlowDto;
import com.jixu.msg.dto.CashFlowFloatDto;
import com.jixu.msg.dto.CashFlowPaymentsDto;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.CashFlowPayment;

/** 
 * @ClassName 		CashFlowMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service("jxCashFlowDtoMsg")
public class CashFlowDtoMsg extends MsgExecuteService<CashFlowDto>{
	
	@Override
	public String msgHeader() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
	private ConfigService configService;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		
		CashFlowDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), CashFlowDto.class);
		String operation=dto.getOperation();
		
		CashFlowCfg oldcfc=null;
		if(operation.equalsIgnoreCase(delete) || operation.equalsIgnoreCase(update)){
			//获取现金流ID
			String id=dto.getId();
			oldcfc=cashFlowCfgDao.loadCashFlowCfg(id);
			if(oldcfc==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.error6);
				return;
			}
		}
		
		//如果是删除  就跳过dto验证
		if(!operation.equalsIgnoreCase(delete)){
			validateDto(msgcontainer, dto);
		}
		//dto验证结果判断
		if(!msgcontainer.isCorrectFlag())
			return;
		
	}
	
	@Override
	public MsgContainer completeEntity(CashFlowDto entity, MsgContainer msgcontainer) {
		String operation=entity.getOperation();
		if(operation.equalsIgnoreCase(delete)){
			CashFlowCfg oldcfc=cashFlowCfgDao.loadCashFlowCfg(entity.getId());
			configService.deleteCashFlowCfg(oldcfc);
			//现金流付息计划
			List<CashFlowPayment> cfpList=configService.loadCashFlowPaymentByCfID(oldcfc.getCashFlowID());
			for (CashFlowPayment cfp : cfpList) {
				configService.deleteCashFlowPayment(cfp);
			}
		}else{
			CashFlowCfg cfcfg=new CashFlowCfg();
			cfcfg.setCashFlowID(entity.getId());
			cfcfg.setCashFlowName(entity.getName());
			cfcfg.setRecOrPay(Integer.valueOf(entity.getType()));
			cfcfg.setFirstIntDate(entity.getAccurStartDate());
			cfcfg.setCpnType(Integer.valueOf(entity.getCfType()));
			cfcfg.setActiveFlag(Integer.valueOf(entity.getStatus()));
			cfcfg.setExternalRateFlag(false);
			cfcfg.setCoupon(Double.valueOf(entity.getContractRate()));
			cfcfg.setIntCode(Integer.valueOf(entity.getDayCount()));
			
			cfcfg.setCpnFreq(Integer.valueOf(entity.getFrequency()));
			cfcfg.setCpnMo(Integer.valueOf(entity.getPaymentMonth()));
			if(entity.getPaymentDay()!=null && !entity.getPaymentDay().equals("")){
				cfcfg.setCpnDate(Integer.valueOf(entity.getPaymentDay()));
			}
			cfcfg.setAutoSetFlag(Boolean.valueOf(entity.getAutoFlag()));
			cfcfg.setAutoInterestFlag(Boolean.valueOf(entity.getAutoInterestFlag()));
			
			
			if(operation.equalsIgnoreCase(add)){
				//现金流
				configService.createCashFlowCfg(cfcfg);
				//现金流付息计划
				List<CashFlowPaymentsDto> payments =entity.getPayments();
				for (CashFlowPaymentsDto cfpDto : payments) {
					CashFlowPayment cfp=new CashFlowPayment();
					cfp.setCfid(cfcfg.getCashFlowID());
					cfp.setPayDate(DateUtil.convertStr2Date2(cfpDto.getSettleDate()));
					configService.createCashFlowPayment(cfp);
				}
				
				//浮动利率
				CashFlowFloatDto floats=entity.getFloats();
				if(floats!=null){
					cfcfg.setVrBase(floats.getSecid());
					cfcfg.setVrMethod(Integer.valueOf(floats.getVrMethod()));
					cfcfg.setVrBasisPtAdjustment(Double.valueOf(floats.getVrBasisPtAdjustment()));
					cfcfg.setVrMax(Double.valueOf(floats.getMax()));
					cfcfg.setVrMin(Double.valueOf(floats.getMin()));
					cfcfg.setVrResetFreq(Integer.valueOf(floats.getFrequency()));
					cfcfg.setVrResetMo(Integer.valueOf(floats.getMonth()));
					cfcfg.setVrResetDate(Integer.valueOf(floats.getDay()));
					cfcfg.setVrResetNDays(Integer.valueOf(floats.getVrResetNDays()));
					cfcfg.setVrDeterAdjMo(Integer.valueOf(floats.getVrDeterAdjMo()));
					cfcfg.setVrDeterAdjDays(Integer.valueOf(floats.getVrDeterAdjDays()));
					configService.updateCashFlowCfg(cfcfg);
				}
				
				msgcontainer.setEntityID(cfcfg.getCashFlowID());
				msgcontainer.setEntityObj(cfcfg);
			}else {
				
				CashFlowCfg oldcfc=cashFlowCfgDao.loadCashFlowCfg(entity.getId());
				//现金流付息计划
				List<CashFlowPayment> cfpList=configService.loadCashFlowPaymentByCfID(oldcfc.getCashFlowID());
				for (CashFlowPayment cfp : cfpList) {
					configService.deleteCashFlowPayment(cfp);
				}
				List<CashFlowPaymentsDto> payments =entity.getPayments();
				for (CashFlowPaymentsDto cfpDto : payments) {
					CashFlowPayment cfp=new CashFlowPayment();
					cfp.setCfid(oldcfc.getCashFlowID());
					cfp.setPayDate(DateUtil.convertStr2Date2(cfpDto.getSettleDate()));
					configService.createCashFlowPayment(cfp);
				}
				
				//浮动利率
				CashFlowFloatDto floats=entity.getFloats();
				if(floats!=null){
					cfcfg.setVrBase(floats.getSecid());
					cfcfg.setVrMethod(Integer.valueOf(floats.getVrMethod()));
					cfcfg.setVrBasisPtAdjustment(Double.valueOf(floats.getVrBasisPtAdjustment()));
					cfcfg.setVrMax(Double.valueOf(floats.getMax()));
					cfcfg.setVrMin(Double.valueOf(floats.getMin()));
					cfcfg.setVrResetFreq(Integer.valueOf(floats.getFrequency()));
					cfcfg.setVrResetMo(Integer.valueOf(floats.getMonth()));
					cfcfg.setVrResetDate(Integer.valueOf(floats.getDay()));
					cfcfg.setVrResetNDays(Integer.valueOf(floats.getVrResetNDays()));
					cfcfg.setVrDeterAdjMo(Integer.valueOf(floats.getVrDeterAdjMo()));
					cfcfg.setVrDeterAdjDays(Integer.valueOf(floats.getVrDeterAdjDays()));
				}
				
				BeanUtils.copyProperties(cfcfg, oldcfc, "cashFlowID");
				configService.updateCashFlowCfg(oldcfc);
				msgcontainer.setEntityID(oldcfc.getCashFlowID());
				msgcontainer.setEntityObj(oldcfc);
			}
		}
		return msgcontainer;
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
}
