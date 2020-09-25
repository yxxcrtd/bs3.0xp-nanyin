package com.joyintech.tams.invest.trade.option.validator;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.option.dto.TrdOptionTrade002Dto;
import com.joyintech.tams.invest.trade.option.service.TrdOptionTrade002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 期权交易校验
 */
@Component
public class TrdOptionTrade002DtoValidator  implements TradeCommonValidator {
    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private TradeCommonService tradeCommonService;

    @Autowired
    private TrdOptionTrade002Service trdOptionTrade002Service;

    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;

    @Override
    public List<ValidMessage> bussValid(Object value) {
        TrdOptionTrade002Dto inputDto = (TrdOptionTrade002Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        String operateType = inputDto.getOperateType();

        //平台日
        Date paltDate = platformDate != null ? platformDate.getSysDate(): DateUtil.now();
        //起息日不能小于平台日
        if (inputDto.getVdate() != null && DateUtil.compareDate(inputDto.getVdate(), paltDate) < 0) {
            ValidMessage validMessage = new ValidMessage("vdate",
                    getMessage("FW_E_0008", getMessage("TrdOptionTrade001.vdate"),
                            getMessage("TrdOptionTrade001.platDate")));
            errorList.add(validMessage);
        }
        // 起息日不能大于到期日
        if (inputDto.getMdate()!= null && inputDto.getVdate() != null && DateUtil.compareDate(inputDto.getVdate(), inputDto.getMdate()) > 0) {
            ValidMessage validMessage = new ValidMessage("mdate",
                    getMessage("FW_E_0009", getMessage("TrdOptionTrade001.vdate"),
                            getMessage("TrdOptionTrade001.mdate")));
            errorList.add(validMessage);
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
	                    getMessage("FW_E_0006", getMessage("TrdOptionTrade001.remark")));
	            errorList.add(validMessage);
        	}
		}
        //中债新增 20200407 end

        //合约类型联动必输项
        String contractType = inputDto.getContractType();
        if(StringUtil.equals(DictCodeListExt3.结构类型_双向鲨鱼鳍.getValue(), contractType)){
            if(inputDto.getFinRateInfoStructure().getHighExercPrice() == null){
                ValidMessage validMessage = new ValidMessage("finRateInfoStructure.highExercPrice",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.highExercPrice")));
                errorList.add(validMessage);
            }
            if(inputDto.getFinRateInfoStructure().getHighHindPrice() == null){
                ValidMessage validMessage = new ValidMessage("finRateInfoStructure.highHindPrice",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.highHindPrice")));
                errorList.add(validMessage);
            }
            if(inputDto.getFinRateInfoStructure().getLowExercPrice() == null){
                ValidMessage validMessage = new ValidMessage("finRateInfoStructure.lowExercPrice",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.lowExercPrice")));
                errorList.add(validMessage);
            }
            if(inputDto.getFinRateInfoStructure().getLowHindPrice() == null){
                ValidMessage validMessage = new ValidMessage("finRateInfoStructure.lowHindPrice",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.lowHindPrice")));
                errorList.add(validMessage);
            }
        }else if(StringUtil.equals(DictCodeListExt3.结构类型_看涨鲨鱼鳍.getValue(), contractType)){
            if(inputDto.getFinRateInfoStructure().getExePrice() == null){
                ValidMessage validMessage = new ValidMessage("finRateInfoStructure.exePrice",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.exePrice")));
                errorList.add(validMessage);
            }
            if(inputDto.getFinRateInfoStructure().getKnockPrice() == null){
                ValidMessage validMessage = new ValidMessage("finRateInfoStructure.knockPrice",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.knockPrice")));
                errorList.add(validMessage);
            }
        }
        else{
            if(inputDto.getFinRateInfoStructure().getExePrice() == null){
                ValidMessage validMessage = new ValidMessage("finRateInfoStructure.exePrice",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.exePrice")));
                errorList.add(validMessage);
            }
        }

        //期权费类型联动检验非空
        if(StringUtil.equals(DictCodeListExt3.期权费支付类型_先付期权费.getValue(), inputDto.getOptFeePayType())){
            //先付期权费 支付日期不能为null
            if(inputDto.getPayDate() == null){
                ValidMessage validMessage = new ValidMessage("payDate",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.payDate")));
                errorList.add(validMessage);
            }
            else{
                //支付日不能小于平台日
                if (DateUtil.compareDate(inputDto.getPayDate(), paltDate) < 0) {
                    ValidMessage validMessage = new ValidMessage("payDate",
                            getMessage("FW_E_0008", getMessage("TrdOptionTrade002.payDate"),
                                    getMessage("TrdOptionTrade001.platDate")));
                    errorList.add(validMessage);
                }
                //支付日不能小于交易日
                else if (DateUtil.compareDate(inputDto.getPayDate(), inputDto.getTradeDate()) < 0) {
                    ValidMessage validMessage = new ValidMessage("payDate",
                            getMessage("FW_E_0008", getMessage("TrdOptionTrade002.payDate"),
                                    getMessage("TrdOptionTrade001.tradeDate")));
                    errorList.add(validMessage);
                }
            }
            //期权费不为空校验
            if(inputDto.getOptFee() == null){
                ValidMessage validMessage = new ValidMessage("optFee",
                        getMessage("FW_E_0006", getMessage("TrdOptionTrade002.optFee")));
                errorList.add(validMessage);
            }
            //输入划款指令校验
            if (bussTrdTransferOrderService != null) {
                if (StringUtil.equals(inputDto.getIsGuarantee(), DictCodeList.是否_否.getValue())) {
                    List<ValidMessage> list = bussTrdTransferOrderService.check(
                            inputDto.getBussTrdTransferOrder());
                    errorList.addAll(list);
                }
            }
        }
        //期权费不能小于0
        if(StringUtil.isNotEmpty(inputDto.getOptFee()) && new BigDecimal(0).compareTo(inputDto.getOptFee()) > 0){
            ValidMessage validMessage = new ValidMessage("optFee",
                    getMessage("FW_E_0008", getMessage("TrdOptionTrade002.optFee"), "0"));
            errorList.add(validMessage);
        }

        
        //期权费率不为空校验
        if(inputDto.getFinTradeProductAdd()==null||inputDto.getFinTradeProductAdd().getOptFeeRate() == null){
            ValidMessage validMessage = new ValidMessage("",
                    getMessage("FW_E_0006", getMessage("TrdOptionTrade002.optFeeRate")));
            errorList.add(validMessage);
        }
        //委托查询流程中与已完成额度  不得大于当前券面总额
        if(StringUtil.isNotEmpty(inputDto.getEntrustId())){
            BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(inputDto.getEntrustId());
            BigDecimal shareAmt = trdOptionTrade002Service.getPrinAmt(inputDto.getEntrustId());
            if(entrustNum.compareTo(ArithmeticUtil.doAdd(inputDto.getAmount(), shareAmt)) == -1) {
                ValidMessage validMessage = new ValidMessage("amount",
                        "可用委托份额不足,当前最多可委托份额为:"+ ArithmeticUtil.doSub(entrustNum, shareAmt));
                errorList.add(validMessage);
            }
        }


        return errorList;


    }

}
