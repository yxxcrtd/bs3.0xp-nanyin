package com.joyintech.tams.invest.period.trdplanintduration.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdplanintduration.dto.TrdPlanInterestDurationProcessDto;
import com.joyintech.tams.invest.period.trdplanintduration.service.TrdPlanInterestDuration002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joyintech.tams.invest.period.trdplanintduration.service.TrdPlanInterestDuration002Service.genParams;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: 撤销校验
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdPlanInterestDurationEditDtoValidator implements BeanValidator {

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private TrdPlanInterestDuration002Service trdPlanInterestDuration002Service;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Override
    public List<ValidMessage> valid(Object value) {
        TrdPlanInterestDurationProcessDto dto = (TrdPlanInterestDurationProcessDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (!BussinessConstant.REVOKE.equals(dto.getOperateType())) {
            errorList.addAll(bussTrdTransferOrderService.check(periodCommonService.transformOrderDto(dto.getBussTrdTransferOrder())));
            // 删除 存在付息截止日比当前数据的付息截止日小的待处理数据 的check
//            dto.getFinAssetIntInfoList().forEach(finAssetIntInfo -> {
//                if (!periodCommonService.checkIfMinDate(DictCodeListExt1.金融产品类型_投管用_计划类资产, finAssetIntInfo.getPortfolioId(),
//                    dto.getFinprodId(), null, dto.getmDate())) {
//                    errorList.add(new ValidMessage(getMessage("PERIOD_E_0002")));
//                    return;
//                }
//            });
//            校验其前置的现金流信息所对应的交易 是否在流程中
            Map<String,Object> map = new HashMap<>();
            map.put("finprodId",dto.getFinprodId());
            map.put("mdate",dto.getmDate());
            List<String>tradeList = periodCommonService.getFlowDeal(map);
            if(tradeList!= null && tradeList.size()>0){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0027",GlobalConstant.SPLIT+getMessage("TrdLendDuration001.busiCode"), StringUtil.joinString(tradeList, GlobalConstant.COMMA))));
            }
        }
        else {
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(), dto.getBusiCode(), errorList);
            if (errorList.isEmpty()) {
                dto.setFinAssetIntInfoList(trdPlanInterestDuration002Service.findAssetIntInfo(genParams(dto)));
                dto.getFinAssetIntInfoList().forEach(finAssetIntInfo -> {
                    Map<String, Object> params = new HashMap<>();
                    params.put("portfolioId",  dto.getPortfolioId());
                    params.put("bizType", DictCodeListExt1.金融产品类型_投管用_计划类资产);
                    params.put("finprodId", dto.getFinprodId());
                    params.put("secManageAcctId", null);
                    params.put("mDate", dto.getmDate());
                    params.put("chlId", dto.getChlId());
                    if (!periodCommonService.checkIfMaxDate(params)) {
                        errorList.add(new ValidMessage(getMessage("PERIOD_E_0001")));
                        return;
                    }
                });
            }
        }
        return errorList;
    }

}
