/*
 * 文件名：TrdForwexInvestTradeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.forwex.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.common.dto.EtrInvestInfoDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.forwex.dto.TrdForwexInvestTradeDto;
import com.joyintech.tams.invest.trade.forwex.service.TrdForwexInvestTrade002Service;
import com.joyintech.tams.invest.trade.lend.service.TrdLendInvestTrade001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 外汇远期投资交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月4日
 * @see TrdForwexInvestTradeValidator
 * @since 1.0
 */
@Component
public class TrdForwexInvestTradeValidator implements TradeCommonValidator{

	 /**
     * TrdLendInvestTrade001Service
     */
    @Autowired
    private TrdLendInvestTrade001Service trdLendInvestTrade001Service;
    
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
	@Autowired
	private TradeCommonService tradeCommonService;
	// add by zhaodongdong 20191121 start
	/**
	 * trdForwexInvestTrade002Service
	 */
	@Autowired
	private TrdForwexInvestTrade002Service trdForwexInvestTrade002Service;
	// add by zhaodongdong 20191121 end
	@Override
	public List<ValidMessage> bussValid(Object value) {
		// TODO Auto-generated method stub
		TrdForwexInvestTradeDto inputDto = (TrdForwexInvestTradeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        //交易对手必填 add by zhuyongkang
        if (StringUtil.isEmpty(inputDto.getTrdInvestPersonInfo().getCounterId())){
            errorList.add(new ValidMessage("trdInvestPersonInfo.counterId",getMessage("TRD_SPOTEX_006")));
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
        
       // 限价模式  by 赵东东 start 2019.11.21
        if(StringUtil.isNotEmpty(inputDto.getEntrustId())){
        	// 获取委托信息
        	TrdForwexInvestTradeDto dto = trdForwexInvestTrade002Service.getDealInfoByEntrustId(inputDto.getEntrustId());
        String[] curPairArr = dto.getCurPair().split("/");
        // 当交易方向为买入且交易货币为货币对中的前一个货币，或交易方向为卖出且交易货币为货币对中的后一个货币时，远期全价为上限
        if((StringUtil.equals(dto.getPs(),DictCodeList.交易方向_买入.getValue())&&StringUtil.equals(curPairArr[0], dto.getCcy())) || 
        		(StringUtil.equals(dto.getPs(),DictCodeList.交易方向_卖出.getValue())&&StringUtil.equals(curPairArr[1], dto.getCcy()))
        		) {
        	if(inputDto.getUnitFprice().compareTo(dto.getUnitFprice()) == 1  ) {
        		 ValidMessage validMessage = new ValidMessage(getMessage("TRD_FORWEX_001"));
        		 errorList.add(validMessage);
        	}
        }
        //当交易方向为买入且交易货币为货币对中的后一个货币，或交易方向为卖出且交易货币为货币对中的前一个货币时，远期全价为下限
        if((StringUtil.equals(dto.getPs(),DictCodeList.交易方向_买入.getValue())&&StringUtil.equals(curPairArr[1], dto.getCcy())) || 
        		(StringUtil.equals(dto.getPs(),DictCodeList.交易方向_卖出.getValue())&&StringUtil.equals(curPairArr[0], dto.getCcy()))
        		) {
        	if(inputDto.getUnitFprice().compareTo(dto.getUnitFprice()) == -1  ) {
        		 ValidMessage validMessage = new ValidMessage(getMessage("TRD_FORWEX_003"));
        		 errorList.add(validMessage);
        	}
        }
        }
       // 限价模式  by 赵东东 end 2019.11.21

        
        //当清算模式为差额清算，货币对只可选择USD/CNY、EUR/CNY、JPY/CNY、GBP/CNY，期限只可选择1W、1M、3M、6M 和1Y
        if(DictCodeListExt2.结算方式_差额清算.getValue().equals(inputDto.getDeliveryType())) {
        	//货币对校验
        	if(!DictCodeList.货币对_USD_CNY.getValue().equals(inputDto.getCurPair())&&
        			!DictCodeList.货币对_EUR_CNY.getValue().equals(inputDto.getCurPair())&&
        			!DictCodeList.货币对_JPY_CNY.getValue().equals(inputDto.getCurPair())&&
        			!DictCodeList.货币对_GBP_CNY.getValue().equals(inputDto.getCurPair())) {
                errorList.add(new ValidMessage("curPair", getMessage("ETR_FORWEX_002")));
        	}
        	//外汇期限校验
        	if(!DictCodeListExt2.外汇期限_1W.getValue().equals(inputDto.getTermType())&&
        			!DictCodeListExt2.外汇期限_1M.getValue().equals(inputDto.getTermType())&&
        			!DictCodeListExt2.外汇期限_3M.getValue().equals(inputDto.getTermType())&&
        			!DictCodeListExt2.外汇期限_6M.getValue().equals(inputDto.getTermType())&&
        			!DictCodeListExt2.外汇期限_1Y.getValue().equals(inputDto.getTermType())) {
                errorList.add(new ValidMessage("termType", getMessage("ETR_FORWEX_004")));
        	}

        	//定价日非空校验
            if(!StringUtil.isNotEmpty(inputDto.getPricedDate())){
                errorList.add(new ValidMessage("pricedDate", getMessage("ETR_X_012", getMessage("TrdForwexInvestTrade.pricedDate"))));
            }
            //差额交割参考利率非空校验
            if(!StringUtil.isNotEmpty(inputDto.getRefRate())){
                errorList.add(new ValidMessage("refRate", getMessage("ETR_X_012", getMessage("TrdForwexInvestTrade.refRate"))));
            }
            
            // 到期交易
            if(StringUtil.equals(DictCodeList.是否_是.getValue(),inputDto.getIsDifSquEx())) {
            	 // 定价日即期汇率
                if(!StringUtil.isNotEmpty(inputDto.getPricedDateRate())){
                    errorList.add(new ValidMessage("pricedDateRate", getMessage("ETR_X_012", getMessage("TrdForwexInvestTrade.pricedDateRate"))));
                }
                // 差额收付方向
                if(!StringUtil.isNotEmpty(inputDto.getDifPs())){
                    errorList.add(new ValidMessage("difPs", getMessage("ETR_X_012", getMessage("TrdForwexInvestTrade.difPs"))));
                }
                // 差额收付金额
                if(!StringUtil.isNotEmpty(inputDto.getDifPayAmt())){
                    errorList.add(new ValidMessage("difPayAmt", getMessage("ETR_X_012", getMessage("TrdForwexInvestTrade.difPayAmt"))));
                }
                // 划款指令
                errorList.addAll(bussTrdTransferOrderService.check(inputDto.getBussTrdTransferOrder()));
            }

        // 差额清算账户联动 add by renping 20191031 start
            // 本方账户非空校验
            if (!StringUtil.isNotEmpty(inputDto.getDealAcct())) {
                errorList.add(new ValidMessage("dealAcct",
                    getMessage("ETR_X_012", getMessage("TrdForwexInvestTrade.dealAcct"))));
            }
            // 对手方非空校验
            if (!StringUtil.isNotEmpty(inputDto.getCorAcct())) {
                errorList.add(new ValidMessage("corAcct",
                    getMessage("ETR_X_012", getMessage("TrdForwexInvestTrade.corAcct"))));
            }
        }
        // 指令账户check增加 add by renping 20191031 start
        else {
            errorList.addAll(bussTrdTransferOrderService.check(inputDto.getBussTrdTransferOrder()));
        }
        // 指令账户check增加 add by renping 20191031 end
        // 差额清算账户联动 add by renping 20191031 start
        
     // 验证流程中是否存在该委托 
        if(StringUtil.isNotEmpty(inputDto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
        	String str = tradeCommonService.check(inputDto.getEntrustId(), "TrdForwexInvestTrade002");
        	if(StringUtil.isNotEmpty(str)){ 
        		ValidMessage validMessage = new ValidMessage(str);
        		errorList.add(validMessage);
        	}
        	EtrInvestInfoDto etrInvestInfoDto = tradeCommonService.findEtrInfo(inputDto.getEntrustId());
        	BigDecimal finishAmt = BigDecimal.ZERO;
        	if(etrInvestInfoDto.getEtrFinishStatusList()!=null){
        		for(EtrFinishStatus fin:etrInvestInfoDto.getEtrFinishStatusList()){
        			finishAmt=ArithmeticUtil.doAdd(finishAmt,fin.getFinishAmt());
        		}
        	}
        	 if (inputDto.getPrinAmt() != null 
        			 && inputDto.getPrinAmt().compareTo(ArithmeticUtil.subtract(etrInvestInfoDto.getEtrEntrust().getEntrustAmt(), finishAmt)) > 0) {
                 errorList.add(new ValidMessage("prinAmt",getMessage("交易货币金额不能大于剩余委托金额")));
             }
        }
        return errorList;
	}

}
