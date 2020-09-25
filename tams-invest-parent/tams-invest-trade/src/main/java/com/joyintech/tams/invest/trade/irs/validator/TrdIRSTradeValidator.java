/*
 * 文件名：TrdIRSTradeValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.irs.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.bond.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.irs.dto.TrdIRSTradeDto;
import com.joyintech.tams.invest.trade.lend.service.TrdLendInvestTrade001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 理论互换校验
 * 〈功能详细描述〉
 * @author admin
 * @version 1.0 2019年7月10日
 * @see TrdIRSTradeValidator
 * @since 1.0
 */
@Component
public class TrdIRSTradeValidator implements TradeCommonValidator{

	@Autowired
	private TrdLendInvestTrade001Service trdLendInvestTrade001Service;
	
	@Autowired
	private TrdBondInvestTrade002Service trdBondInvestTrade002Service;
	
	@Autowired
	private TradeCommonService tradeCommonService;
	
	@Override
	public List<ValidMessage> bussValid(Object value) {// TODO Auto-generated method stub
		TrdIRSTradeDto inputDto = (TrdIRSTradeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        //撤销校验
        if(StringUtil.equals(inputDto.getOperateType(), OperationType.REVOKE)) {
        	 // 存在流程中数据
            if (WorkFlowUtil.isNotFromToDoList()) {
                if (trdLendInvestTrade001Service.isInFlowDataExisted(inputDto.getTradeId())) {
                    ValidMessage validMessage = new ValidMessage(
                        getMessage("COMMON_E_0012", inputDto.getTradeId()));
                    errorList.add(validMessage);
                }
            }

            // 删除状态不允许撤回
            if (trdLendInvestTrade001Service.isRevokeDataExisted(inputDto.getTradeId())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                    getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                errorList.add(validMessage);
            }
        }
        //中债新增 20200407 start
        //持有目的
        if(StringUtil.isEmpty(inputDto.getHoldPurpose())){
            ValidMessage validMessage = new ValidMessage("holdPurpose",
                    getMessage("FW_E_0006", getMessage("TrdBondLendInvestTrade002.holdPurpose")));
            errorList.add(validMessage);
        }else if(StringUtil.equals(DictCodeListExt1.持有目的_其他.getValue(), inputDto.getHoldPurpose())){
        	if (StringUtil.isEmpty(inputDto.getRemark())) {
	        	ValidMessage validMessage = new ValidMessage("remark",
	                    getMessage("FW_E_0006", getMessage("TrdBondLendInvestTrade002.remark")));
	            errorList.add(validMessage);
        	}
		}
        //中债新增 20200407 end
        // 起息日 < 到期日  校验
        if (inputDto.getVdate() != null && inputDto.getMdate() != null) {
	        if (DateUtil.compareDate(inputDto.getVdate(),  inputDto.getMdate()) >= 0) {
	            ValidMessage validMessage = new ValidMessage("vdate",
	                getMessage("BUSS_COM_E_0035", getMessage("TrdIRSTrade.vdate"),
	                    getMessage("TrdIRSTrade.mdate")));
	            errorList.add(validMessage);
	        }
	    }
        

        if (inputDto.getTrdInvestPersonInfo() == null
            || StringUtil.isEmpty(inputDto.getTrdInvestPersonInfo().getCounterId())) {
            errorList.add(new ValidMessage("trdInvestPersonInfo.counterId",
                getMessage("FW_E_0006", getMessage("TrdIRSTrade.counterId"))));
        }
        
        // 委托带出得交易录单界面 校验
        if(StringUtil.isNotEmpty(inputDto.getEntrustId())){
            // 查询未完成的委托额度
            BaseEtrInvestInfoDto entrustInfo = trdBondInvestTrade002Service.getEntrustInfo(inputDto.getEntrustId());
            // 名义本金超过委托时的名义本金
            if (inputDto.getPrin() != null) {
                if (entrustInfo != null && entrustInfo.getEtrEntrust() != null
                    && entrustInfo.getEtrEntrust().getEntrustNum() != null
                    && ArithmeticUtil.multiply(inputDto.getPrin(),new BigDecimal(10000)).compareTo(
                        entrustInfo.getEtrEntrust().getEntrustNum()) > 0) {
                    ValidMessage validMessage = new ValidMessage("prin",
                        getMessage("TRD_W_0009", getMessage("TrdIRSTrade.prin")));
                    errorList.add(validMessage);
                }
            }
            
            // 验证流程中是否存在该委托 
            if(StringUtil.isNotEmpty(inputDto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
            	String str = tradeCommonService.check(inputDto.getEntrustId(), "TrdIRSTrade002");
            	if(StringUtil.isNotEmpty(str)){ 
            		ValidMessage validMessage = new ValidMessage(str);
            		errorList.add(validMessage);
            	}
            }
        	// 收取固定时 10
        	if(StringUtil.equals(inputDto.getIrsType(),DictCodeListExt2.互换方式_收取固定.getValue())) {
        		//互换方式为※收取固定时，画面.固定利率-利差/100小于委托时的固定利率
        		if (inputDto.getYield() != null&&inputDto.getSpreadRate()!=null
        				&& entrustInfo!=null && entrustInfo.getEtrEntrustAdd().getMatureYield()!=null
        				&& (inputDto.getYield().subtract(inputDto.getSpreadRate().divide(new BigDecimal(100)))).compareTo(
                                entrustInfo.getEtrEntrustAdd().getMatureYield()) < 0) {
                    ValidMessage validMessage = new ValidMessage("yield",
                        getMessage("TRD_IRS_001"));
                    errorList.add(validMessage);
        		}
        	}else {
        		//互换方式为※收取浮动时，画面.固定利率-利差/100大于委托时的固定利率
        		if (inputDto.getYield() != null&&inputDto.getSpreadRate()!=null
        				&& entrustInfo!=null && entrustInfo.getEtrEntrustAdd().getMatureYield()!=null
        				&& (inputDto.getYield().subtract(inputDto.getSpreadRate().divide(new BigDecimal(100)))).compareTo(
                                entrustInfo.getEtrEntrustAdd().getMatureYield()) > 0) {
                    ValidMessage validMessage = new ValidMessage("yield",
                        getMessage("TRD_IRS_002"));
                    errorList.add(validMessage);
        		}
        	}
        	
        	//进行验证：委托中的到期日区间不为空时，交易中到期日应等于委托中到期日区间范围内（含开始和结束日），若不满足，最终无法提交，给出相应提示：“请输入合理的到期日”；																					
        	if (inputDto.getMdate() != null
        			&& entrustInfo != null && entrustInfo.getEtrEntrustAdd() != null
                    && entrustInfo.getEtrEntrustAdd().getMdate() != null
                    && entrustInfo.getEtrEntrustAdd().getMdate2() != null
                    && (inputDto.getMdate().after(entrustInfo.getEtrEntrustAdd().getMdate2())
                    	||inputDto.getMdate().before(entrustInfo.getEtrEntrustAdd().getMdate()))) {
                    ValidMessage validMessage = new ValidMessage("mdate",
                        getMessage("TRD_IRS_003", getMessage("TrdIRSTrade.mdate")));
                    errorList.add(validMessage);
            }
        	
        	//进行验证：委托中的期限品种不为空时，交易中期限品种等于委托中期限品种，若不满足，最终无法提交，给出相应提示：“请输入合理的到期日”；	
        	if (inputDto.getTermType() != null
        			&& entrustInfo.getEtrEntrustAdd() != null 
        			&& entrustInfo.getEtrEntrustAdd().getTermType() != null
        			) {
        			// 委托中期限品种
    			    String termTypeEntrust = entrustInfo.getEtrEntrustAdd().getTermType();
    			    List<String> termTypeEntrustList = Arrays.asList(termTypeEntrust.split(","));
    			    if(!termTypeEntrustList.contains(inputDto.getTermType())) {
                        ValidMessage validMessage = new ValidMessage("termType",
                            getMessage("TRD_IRS_004", getMessage("TrdIRSTrade.termType")));
                        errorList.add(validMessage);
    			    }
            }
        }
        
        return errorList;
	}

}
