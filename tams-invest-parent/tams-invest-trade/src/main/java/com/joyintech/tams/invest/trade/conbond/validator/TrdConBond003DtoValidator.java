/*
 * 文件名：TrdConBond003DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.conbond.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBond003Dto;
import com.joyintech.tams.invest.trade.conbond.service.TrdConBond003Service;

/**
 * 申购确认保存提交check
 *
 * @author wangpeng
 * @version 1.0 2019年11月14日
 * @see TrdConBond003DtoValidator
 * @since 1.0
 */
@Component
public class TrdConBond003DtoValidator implements TradeCommonValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * trdConBond003Service
     */
    @Autowired
    private TrdConBond003Service trdConBond003Service;
    
    @Autowired
	private TradeCommonService tradeCommonService;
    
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        TrdConBond003Dto inputDto = (TrdConBond003Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();

      //交易对手必填
        if (StringUtil.equals(inputDto.getOperateType(),"save") || StringUtil.equals(inputDto.getOperateType(),"submit")){
        	if (StringUtil.isEmpty(inputDto.getTrdInvestPersonInfo().getCounterId())){
                errorList.add(new ValidMessage("trdInvestPersonInfo.counterId",getMessage("TRD_SPOTEX_006")));
            }
        }
        // 划款指令
        if (bussTrdTransferOrderService != null) {
            	List<ValidMessage> list = bussTrdTransferOrderService.check(
                        inputDto.getBussTrdTransferOrder());
                    errorList.addAll(list);
        }


        // 画面.交割日 >= 标的类金融产品.到期日 时，提交报错
        if (inputDto.getSettleDate() != null && inputDto.getMdate() != null) {
            if (DateUtil.compareDate(inputDto.getSettleDate(), inputDto.getMdate()) >= 0) {
                ValidMessage validMessage = new ValidMessage("settleDate",
                    getMessage("BUSS_COM_E_0035", getMessage("TrdBondInvestTrade002.settleDate"),
                        getMessage("TrdBondInvestTrade002.mdate")));
                errorList.add(validMessage);
            }
        }

        //证券托管户 与通道二选一
        if(("".equals(inputDto.getChlId())||inputDto.getChlId()==null)&&("".equals(inputDto.getSecManageAcctId())||inputDto.getSecManageAcctId()==null)){
        	 ValidMessage validMessage = new ValidMessage(getMessage("TRD_E_0014",getMessage("invest.channelId")));
        	
        	errorList.add(validMessage);
        }

        if (StringUtil.isNotEmpty(inputDto.getProAvailableCash())
                && StringUtil.isNotEmpty(inputDto.getTradeAmt())
                && inputDto.getProAvailableCash().compareTo(inputDto.getTradeAmt()) < 0){
        	if(StringUtil.isNotEmpty(CacheUtil.getSysParam(BussinessConstant.TRD_CHECK_CASH_SWITCH))
                    && StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.TRD_CHECK_CASH_SWITCH), DictCodeListExt1.是或否_否.getValue())
                    && StringUtil.equals(inputDto.getPurchType(), DictCodeListExt2.申购类型_优先配售.getValue())){
            	//组合可用现金不足，无法提交交易
        		 ValidMessage validMessage = new ValidMessage("组合可用现金不足，无法提交交易!");
        		 errorList.add(validMessage);
        		
            }
        }
        if (StringUtil.isNotEmpty(inputDto.getSecManageAcctId())&& StringUtil.equals(inputDto.getPurchType(), DictCodeListExt2.申购类型_普通申购.getValue())
        		&& StringUtil.equals(inputDto.getPurchModle(), DictCodeListExt2.申购方式_网上申购.getValue())
        		&&StringUtil.isNotEmpty(inputDto.getTradeMarketCash())&& StringUtil.isNotEmpty(inputDto.getSupplementaryAmt())
                && inputDto.getTradeMarketCash().compareTo(inputDto.getSupplementaryAmt()) < 0){
        	if(StringUtil.isNotEmpty(CacheUtil.getSysParam(BussinessConstant.TRD_CHECK_CASH_SWITCH))
                    && StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.TRD_CHECK_CASH_SWITCH), DictCodeListExt1.是或否_否.getValue())){
            	//交易所资金可用现金不足，无法提交交易
        		ValidMessage validMessage = new ValidMessage("交易所资金可用现金不足，无法提交交易!");
             	errorList.add(validMessage);
        		
            }
        }

        return errorList;
    }

}
