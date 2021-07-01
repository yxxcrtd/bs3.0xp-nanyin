package com.joyintech.tams.invest.period.trdsecoverdueduration.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration003Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.service.TrdSecOverdueDuration003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: TODO
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdSecOverdueDuration003DtoValidator implements PeriodCommonValidator {
    @Autowired
    private TrdSecOverdueDuration003Service service;
    /**
     * 业务流程校验service
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    private int compareTwoNum(BigDecimal one,BigDecimal two){
        if(one == null){
            one = BigDecimal.ZERO;
        }
        if(two == null){
            two = BigDecimal.ZERO;
        }
        return one.compareTo(two);
    }
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdSecOverdueDuration003Dto dto = (TrdSecOverdueDuration003Dto)value;
        if(StringUtil.equals(dto.getOperateType(),"revoke")){
            if(!StringUtil.equals(dto.getStatusValid(),DictCodeListExt1.有效状态_有效.getValue())){
                errorList.add(new ValidMessage(getMessage("FIN_ASSET_E_0006")));
            }
            WflValidInputParameter inputParameter = new WflValidInputParameter();
            inputParameter.setPageId("TrdSecOverdueDuration003");
            inputParameter.setBusinessDataPk1(dto.getTotleTradeId()+'R');
            inputParameter.setWorkflowId(dto.getWorkflowId());
            if(mstWorkflowCommonService.isInWflProcess(inputParameter)){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_MDATE_0002")));
            }
//            校验交易对手是否存在后续的有效转出交易
            Map<String,Object>checkMap = new HashMap<>();
            checkMap.put("portfolioId",dto.getCounterId());
            checkMap.put("transferDate", DateUtil.format(dto.getTransferDate()));
            checkMap.put("finprodId",dto.getFinprodId());
            if(service.checkCounterFlowDeal(checkMap)){
                errorList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
            }
        }
        else{
            //        罚息不可单独转让，即转让未支付本金和转让未支付利息不能都为0
            if(dto.getPenaltyIntamt()!=null&& !BigDecimal.ZERO.equals(dto.getPenaltyIntamt())&&(dto.getIntAmt()==null||dto.getIntAmt().equals(BigDecimal.ZERO))&&(dto.getPrinAmt()==null||dto.getPrinAmt().equals(BigDecimal.ZERO))){
                errorList.add(new ValidMessage(getMessage("TRD_ASSET_OVERD_E_0001")));
            }
//        未支付本金、转让未支付利息不可超过对应的持有值；
            if((compareTwoNum(dto.getTheoryInt(),dto.getIntAmt())<0)){
                errorList.add(new ValidMessage("intAmt",getMessage("BUSS_COM_E_0004",
                        getMessage("TrdSecOverdueDuration003.theoryInt"),getMessage("TrdSecOverdueDuration003.intAmt"))));
            }
            if((compareTwoNum(dto.getTheoryPrin(),dto.getPrinAmt())<0)){
                errorList.add(new ValidMessage("prinAmt",getMessage("BUSS_COM_E_0003",
                        getMessage("TrdSecOverdueDuration003.theoryPrin"),getMessage("TrdSecOverdueDuration003.prinAmt"))));
            }
            if((compareTwoNum(dto.getTheoryPenaltyInt(),dto.getPenaltyInt())<0)){
                errorList.add(new ValidMessage("penaltyInt",getMessage("BUSS_COM_E_0003",
                        getMessage("TrdSecOverdueDuration003.theoryPenaltyInt"),getMessage("TrdSecOverdueDuration003.penaltyInt"))));
            }
            if(dto.getTrdInvestPersonInfo()==null||StringUtil.isEmpty(dto.getTrdInvestPersonInfo().getCounterId())){
                errorList.add(new ValidMessage("trdInvestPersonInfo.counterId",getMessage("FW_E_0006",getMessage("BussTrdTransferOrder.counterId"))));
            }
            getValidMessage(errorList, dto);
        }
        return errorList;
    }

    private List<ValidMessage> getValidMessage(List<ValidMessage> errorList, TrdSecOverdueDuration003Dto dto) {
        WflValidInputParameter inputParameter = new WflValidInputParameter();
        inputParameter.setPageId("TrdSecOverdueDuration003");
        inputParameter.setWorkflowId(dto.getWorkflowId());
        inputParameter.setBusinessDataPk2(dto.getPortfolioId());
        inputParameter.setBusinessDataPk3(dto.getFinprodId());
        inputParameter.setBusinessDataPk4(dto.getSecAcctId());
        inputParameter.setBusinessDataPk5(dto.getBjBranchStr());
        inputParameter.setBusinessDataPk6(dto.getLiBranchStr());
        if(mstWorkflowCommonService.isInWflProcess(inputParameter)){
            errorList.add(new ValidMessage(getMessage("BUSS_COM_MDATE_0002")));
        }
        return errorList;
    }

}
