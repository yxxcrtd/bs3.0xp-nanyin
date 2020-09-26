/*
 * 文件名：TrdSpotexInvestTradeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.spotex.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.trade.spotex.service.TrdSpotexInvestTrade002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.spotex.dto.TrdSpotexInvestTradeDto;

/**
 * 外汇即期交易DtoValidator
 * 
 * @author wangguoxian
 * @version 1.0 2019年7月2日
 * @see TrdSpotexInvestTradeDtoValidator
 * @since 1.0
 */
@Transactional
@Component
public class TrdSpotexInvestTradeDtoValidator implements TradeCommonValidator {

    /**
     * TrdSpotexInvestTrade002Service
     */
    @Autowired
    private TrdSpotexInvestTrade002Service trdSpotexInvestTrade002Service;
    
    @Autowired
	private TradeCommonService tradeCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        TrdSpotexInvestTradeDto inputDto = (TrdSpotexInvestTradeDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        String entrustId = inputDto.getEntrustId();
        if (StringUtil.isNotEmpty(entrustId)){
            // 委托代码长度不超过50
            if (entrustId.length() > 50) {
                errorList.add(new ValidMessage("entrustId",
                        getMessage("TRD_SPOTEX_001", getMessage("TrdSpotexInvestTrade.entrustId"),
                                getMessage("TrdSpotexInvestTrade.entrustIdLength"))));
            }
            BaseEtrInvestInfoDto entrustDto = trdSpotexInvestTrade002Service.getEtrustDto(entrustId);
            if (entrustDto == null || inputDto.getPrinAmt() == null || inputDto.getUnitCprice() == null || StringUtil.isEmpty(inputDto.getPs())){
                return errorList;
            }
            EtrEntrust eedto = entrustDto.getEtrEntrust();
            EtrEntrustAdd etrAddDto = entrustDto.getEtrEntrustAdd();
            // 交易货币金额累积和不能大于外汇即期委托的交易货币金额
            BigDecimal avaliableEtrAmt = trdSpotexInvestTrade002Service.selAvailableEtrAmt(entrustId);
            if (avaliableEtrAmt.compareTo(inputDto.getPrinAmt()) < 0){
                errorList.add(new ValidMessage("prinAmt",getMessage("TRD_SPOTEX_005")));
            }
            // 验证流程中是否存在该委托 
            if(StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()){
            	String str = tradeCommonService.check(entrustId, "TrdSpotexInvestTrade002");
            	if(StringUtil.isNotEmpty(str)){ 
            		ValidMessage validMessage = new ValidMessage(str);
            		errorList.add(validMessage);
            	}
            }
            
            // 委托为限价模式，当交易方向为买入时，即期汇率为上限；反之即期汇率为下限。
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())){
                if (eedto.getPrice().compareTo(inputDto.getUnitCprice()) < 0){
                    errorList.add(new ValidMessage("unitCprice", getMessage("TRD_SPOTEX_003")));
                }

            }
            else if (StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),inputDto.getPs())){
                if (eedto.getPrice().compareTo(inputDto.getUnitCprice()) > 0){
                    errorList.add(new ValidMessage("unitCprice", getMessage("TRD_SPOTEX_004")));
                }
            }

        }
        if (StringUtil.equals(inputDto.getOperateType(),"save") || StringUtil.equals(inputDto.getOperateType(),"submit")){
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
            if (StringUtil.isEmpty(inputDto.getTrdInvestPersonInfo().getCounterId())){
                errorList.add(new ValidMessage("trdInvestPersonInfo.counterId",getMessage("TRD_SPOTEX_006")));
            }
            if(inputDto.getBussTrdTransferOrder() == null || inputDto.getBussTrdTransferOrder().size() < 1){
                errorList.add(new ValidMessage("bussTrdTransferOrder",getMessage("TRD_SPOTEX_007")));
            }
            else {
                inputDto.getBussTrdTransferOrder().forEach(bussTrdTransferOrderDto -> {
                    if (DictCodeListExt1.收支方向_支出.getValue().equals(bussTrdTransferOrderDto.getPs()) && StringUtil.isEmpty(bussTrdTransferOrderDto.getPayAcctNo())) {
                        errorList.add(new ValidMessage("bussTrdTransferOrder", "请输入付款账户"));
                    }
                    else if (DictCodeListExt1.收支方向_收入.getValue().equals(bussTrdTransferOrderDto.getPs()) && StringUtil.isEmpty(bussTrdTransferOrderDto.getRecAcctNo())) {
                        errorList.add(new ValidMessage("bussTrdTransferOrder", "请输入收款账户"));
                    }
                });
            }
        }


        return errorList;
    }

}
