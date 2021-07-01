package com.joyintech.tams.invest.period.trdbondduration.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondPtlSecExerciseInfo;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration003ProcessDto;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration003Service;
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
public class TrdBondDuration003DtoValidator implements PeriodCommonValidator {

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private TrdBondDuration003Service trdBondDuration003Service;

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdBondDuration003ProcessDto dto = (TrdBondDuration003ProcessDto)value;
        if (dto.getSettleDate()!= null && dto.getPayDate()!= null && dto.getSettleDate().compareTo(dto.getPayDate()) <0) {
        	errorList.add(
                    new ValidMessage("settleDate", "资金到账日不能小于支付日！"));
        }
        errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));

        if (StringUtil.equals(dto.getOperateType(), "submit")) {
            if (StringUtil.isNotEmpty(dto.getFinprodMarketId())
                && StringUtil.isNotEmpty(dto.getSettleDate())){
                Map<String,Object> map = new HashMap<>();
                map.put("finprodMarketId",dto.getFinprodMarketId());
                map.put("chlId",dto.getChlId());
//                map.put("tradeDate",DateUtil.format(dto.getSettleDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
               List<String> dtos = trdBondDuration003Service.findProcessDtos(map);
               if (dtos.size() > 0){
                   errorList.add(
                           new ValidMessage("finprodMarketId", "此交易已存在！"));
               }
               map.put("pageId","TrdBondDuration003");
                map.put("finprodId",dto.getFinprodId());
                map.put("ps", DictCodeList.交易方向_行权_赎回.getValue());
                List<String> list = trdBondDuration003Service.findWorkflowProcessDtos(map);
                if(list.size()>0){
                    errorList.add(
                            new ValidMessage("finprodMarketId", "存在流程中数据！"));
                }
            }
            //组合列表实收利息之和=实收利息总额
            //组合列表到账金额之和=到账金额
            BigDecimal intAmtReal = BigDecimal.ZERO;
            BigDecimal settleAmt = BigDecimal.ZERO;
            if(dto.getTrdBondPtlSecExerciseInfos() != null && dto.getTrdBondPtlSecExerciseInfos().size() >0 ){
                for(TrdBondPtlSecExerciseInfo trdBondPtlSecExerciseInfo : dto.getTrdBondPtlSecExerciseInfos()){
                    intAmtReal = intAmtReal.add(trdBondPtlSecExerciseInfo.getIntAmtReal());
                    settleAmt  = settleAmt.add(trdBondPtlSecExerciseInfo.getSettleAmt());
                }
            }
            if(dto.getIntAmtReal() !=null && (intAmtReal.compareTo(dto.getIntAmtReal()) != 0)){
                errorList.add(new ValidMessage("金额明细信息列表实收利息之和必须等于金额信息中的实收利息总额"));
            }
            if(dto.getfPriceAmt() !=null && (settleAmt.compareTo(dto.getfPriceAmt()) != 0) ){
                errorList.add(new ValidMessage("金额明细信息列表组合列表到账金额之和必须等于金额信息中的到账金额"));
            }
        }
        return errorList;
    }
}
