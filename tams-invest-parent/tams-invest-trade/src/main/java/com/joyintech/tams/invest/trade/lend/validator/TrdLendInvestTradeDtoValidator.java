/*
 * 文件名：TrdLendInvestTradeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.lend.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.bond.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.lend.dto.TrdLendInvestTradeDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 刘二通
 * @version 1.0 2018年11月1日
 * @see TrdLendInvestTradeDtoValidator
 * @since 1.0
 */
@Component
public class TrdLendInvestTradeDtoValidator implements TradeCommonValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
	@Autowired
	private TradeCommonService tradeCommonService;

	@Autowired
    private TrdBondInvestTrade002Service trdBondInvestTrade002Service;
    
	/**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        TrdLendInvestTradeDto inputDto = (TrdLendInvestTradeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (bussTrdTransferOrderService != null) {
            if (StringUtil.equals(inputDto.getIsGuarantee(), DictCodeList.是否_否.getValue())) {
                List<ValidMessage> list = bussTrdTransferOrderService.check(
                    inputDto.getBussTrdTransferOrder());
                errorList.addAll(list);
            }
        }

        // 起息日不能大于到期日
        if (inputDto.getFinTradeProduct() != null
            && inputDto.getFinTradeProduct().getVdate() != null
            && inputDto.getFinTradeProduct().getMdate() != null) {
            if (DateUtil.compareDate(inputDto.getFinTradeProduct().getVdate(),
                inputDto.getFinTradeProduct().getMdate()) > 0) {
                ValidMessage validMessage = new ValidMessage("finTradeProduct.vdate",
                    getMessage("FW_E_0009", getMessage("TrdLendInvestTrade002.vdate"),
                        getMessage("TrdLendInvestTrade002.mdate")));
                errorList.add(validMessage);
            }
        }

        // 2、在交易查询页面点击【交易录单】按钮进入拆借指令交易新建页面场合时：
        // 2.3交易画面与委托画面的校验
        // 交易方向为拆入时，拆入交易本金金额不能超过所输入的委托金额，拆入交易时利率%不能超过委托所输利率%；
        if (inputDto.getFinTradeProduct() != null
            && StringUtil.equals(inputDto.getBusiPs(),
                DictCodeList.回购拆借方向_拆入.getValue())) {
//            if (inputDto.getFinTradeProduct().getPrin() != null && inputDto.getEntrustNum() != null
//                && inputDto.getFinTradeProduct().getPrin().compareTo(inputDto.getEntrustNum()) > 0) {
//                ValidMessage validMessage = new ValidMessage("finTradeProduct.prin",
//                    getMessage("TRD_W_0007", getMessage("TrdLendInvestTrade002.prin"),
//                        getMessage("TrdLendInvestTrade002.entrustNum"),
//                        getMessage("TrdLendInvestTrade002.morethan")));
//                errorList.add(validMessage);
//            }
            if (inputDto.getFinTradeProduct().getIntRate() != null && inputDto.getEntrustIntRate() != null
                && inputDto.getFinTradeProduct().getIntRate().compareTo(inputDto.getEntrustIntRate()) > 0) {
                ValidMessage validMessage = new ValidMessage("finTradeProduct.intRate",
                    getMessage("TRD_W_0007", getMessage("TrdLendInvestTrade002.intRate"),
                        getMessage("TrdLendInvestTrade002.entrustIntRate"),
                        getMessage("TrdLendInvestTrade002.morethan")));
                errorList.add(validMessage);
            }
        }
        // 交易方向为拆出时，拆出交易本金金额不能超过所输入的委托金额，拆出交易时利率%不能低于委托所输利率%；
        else if (inputDto.getFinTradeProduct() != null
                 && StringUtil.equals(inputDto.getBusiPs(),
                     DictCodeList.回购拆借方向_拆出.getValue())) {
//            if (inputDto.getFinTradeProduct().getPrin() != null && inputDto.getEntrustNum() != null
//                && inputDto.getFinTradeProduct().getPrin().compareTo(inputDto.getEntrustNum()) > 0) {
//                ValidMessage validMessage = new ValidMessage("finTradeProduct.prin",
//                    getMessage("TRD_W_0007", getMessage("TrdLendInvestTrade002.prin"),
//                        getMessage("TrdLendInvestTrade002.entrustNum"),
//                        getMessage("TrdLendInvestTrade002.morethan")));
//                errorList.add(validMessage);
//            }
            if (inputDto.getFinTradeProduct().getIntRate() != null && inputDto.getEntrustIntRate() != null
                && inputDto.getFinTradeProduct().getIntRate().compareTo(inputDto.getEntrustIntRate()) < 0) {
                ValidMessage validMessage = new ValidMessage("finTradeProduct.intRate",
                    getMessage("TRD_W_0007", getMessage("TrdLendInvestTrade002.intRate"),
                        getMessage("TrdLendInvestTrade002.entrustIntRate"),
                        getMessage("TrdLendInvestTrade002.lessthan")));
                errorList.add(validMessage);
            }
        }

//        // 拆出 本金金额 <= 组合T日可用现金
//        if (inputDto.getFinTradeProduct() != null
//            && StringUtil.equals(inputDto.getBusiPs(),
//                DictCodeList.回购拆借方向_拆出.getValue())) {
//            if (inputDto.getFinTradeProduct().getPrin() != null
//                && inputDto.getAvalibleCash() != null
//                && inputDto.getFinTradeProduct().getPrin().compareTo(
//                    inputDto.getAvalibleCash()) > 0) {
//                ValidMessage validMessage = new ValidMessage("finTradeProduct.prin",
//                    getMessage("FW_E_0009", getMessage("TrdLendInvestTrade002.prin"),
//                        getMessage("TrdBondInvestTrade002.avalibleCash")));
//                errorList.add(validMessage);
//            }
//        }
        //     附件上传改为非必填 by zhuyongkang 20191010 start
        // 1、  交易录单时，需上传交易成交单（附件）；
//        if (StringUtil.isEmptyList(inputDto.getFileArray())) {
//            ValidMessage validMessage = new ValidMessage(getMessage("FW_E_0006", getMessage("FileCommon")));
//            errorList.add(validMessage);
//        }
//        交易对手必填 zhuyongkang
//        if(StringUtil.equals(inputDto.getOperateType(), "save") || StringUtil.equals(inputDto.getOperateType(), "submit")){
//            if(inputDto.getTrdInvestPersonInfo() != null && StringUtil.isEmpty(inputDto.getTrdInvestPersonInfo().getCounterId())){
//                ValidMessage validMessage = new ValidMessage("trdInvestPersonInfo.counterId",getMessage("FW_E_0006", getMessage("TrdDepositInvestTrade002.cno")));
//                errorList.add(validMessage);
//            }
//        }

        
        // 验证流程中是否存在该委托 
        if(StringUtil.isNotEmpty(inputDto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
        	String str = tradeCommonService.check(inputDto.getEntrustId(), "TrdLendInvestTrade002");
        	if(StringUtil.isNotEmpty(str)){ 
        		ValidMessage validMessage = new ValidMessage(str);
        		errorList.add(validMessage);
        	}
        }
        
        //验证是否超过可委托额度
        if(StringUtil.isNotEmpty(inputDto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
        	BaseEtrInvestInfoDto dto = trdBondInvestTrade002Service.getEntrustInfo(inputDto.getEntrustId());
    		if(inputDto.getFinTradeProduct().getPrin().compareTo(dto.getEtrEntrust().getEntrustAmt()) > 0){
    			 ValidMessage validMessage = new ValidMessage("finTradeProduct.prin",
    	                    "本金金额不能超过剩余委托金额");
    	                errorList.add(validMessage);
    		}
        }
        return errorList;
    }
}
