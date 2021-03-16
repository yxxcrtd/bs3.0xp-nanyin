package com.joyintech.tams.invest.period.trdbondduration.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: TODO
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdBondDuration002DtoValidator implements PeriodCommonValidator {

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdBondDuration002ProcessDto dto = (TrdBondDuration002ProcessDto)value;
        if (!BussinessConstant.REVOKE.equals(dto.getOperateType())) {
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
            if (dto.getTradeDate().compareTo(dto.getmDate()) < 0 && (DictCodeListExt1.交易场所_银行间.getValue().equals(dto.getTradeMarket()))) {
                errorList.add(new ValidMessage(getMessage("FIN_ASSET_E_0022")));
            }
            //交易所提前收息/还本，只支持全部结清
            if (dto.getTradeDate().compareTo(dto.getmDate()) < 0
                    && (DictCodeListExt1.交易场所_上海证券交易所.getValue().equals(dto.getTradeMarket())
                          ||DictCodeListExt1.交易场所_深圳证券交易所.getValue().equals(dto.getTradeMarket()))) {
                if(StringUtil.equals(dto.getIsClean(), DictCodeList.是否_否.getValue())){
                    errorList.add(new ValidMessage(getMessage("交易所债券提前提前收息/还本不支持不结清!")));
                }
            }
            // 删除 存在付息截止日比当前数据的付息截止日小的待处理数据 的check
//            else {
//                if (!periodCommonService.checkIfMinDate(DictCodeListExt1.金融产品类型_投管用_债券, dto.getPortfolioId(),
//                    dto.getFinprodId(), dto.getSecManageAcctId(), dto.getmDate())) {
//                    errorList.add(new ValidMessage(getMessage("PERIOD_E_0002")));
//                }
//            }
//            校验其前置的现金流信息所对应的交易 是否在流程中
            Map<String,Object> map = new HashMap<>();
            map.put("finprodId",dto.getFinprodId());
            map.put("mdate",dto.getmDate());
            map.put("chlId", dto.getSecManageAcctId());
            map.put("channel", dto.isChannel());
            List<String>tradeList = periodCommonService.getFlowDeal(map);
            if(tradeList!= null && tradeList.size()>0){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0027",GlobalConstant.SPLIT+getMessage("TrdLendDuration001.busiCode"), StringUtil.joinString(tradeList, GlobalConstant.COMMA))));
        }
        }
        else {
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(), dto.getBusiCode(), errorList);
            if (errorList.isEmpty()) {
                Map<String, Object> params = new HashMap<>();
                params.put("portfolioId",  dto.getPortfolioId());
                params.put("bizType", DictCodeListExt1.金融产品类型_投管用_债券);
                params.put("finprodId", dto.getFinprodId());
                params.put("secManageAcctId", !dto.isChannel() ? dto.getSecManageAcctId() : null);
                params.put("mDate", dto.getmDate());
                /*合并 南海个性化修改：行权赎回撤销校验报错  add by xr start*/
                if(null==dto.getmDate()){
                	params.put("mDate", dto.getPayDate());
                }
                /*合并  南海个性化修改：行权赎回撤销校验报错  add by xr end*/
                params.put("chlId", dto.isChannel()?dto.getSecManageAcctId():null);
                if (!periodCommonService.checkIfMaxDate(params)) {
                    errorList.add(new ValidMessage(getMessage("PERIOD_E_0001")));
                }
            }

            // 由于 行权撤销 变为 003 故需要重新校验是否存在撤销中流程
            if (errorList.isEmpty()) {
                if(StringUtil.equals(DictCodeListExt1.平安业务类型_债券类资产行权.getValue(), dto.getPaBussType())) {
                    WflValidInputParameter wflValidInputParameter  = new WflValidInputParameter();
                    wflValidInputParameter.setPageId("TrdBondDuration003");
                    wflValidInputParameter.setBusinessDataPk1(dto.getFinprodId());
                    if(StringUtil.isNotEmpty(dto.getWorkflowId())){
                        wflValidInputParameter.setWorkflowId(dto.getWorkflowId());
                    }
                    wflValidInputParameter.setBusinessDataPk2("T39");
                    wflValidInputParameter.setBusinessDataPk3(dto.getBusiCode());
                    if(StringUtil.isNotEmpty(dto.isChannel())) {
                        wflValidInputParameter.setBusinessDataPk4(dto.getSecManageAcctId());
                    }
                    wflValidInputParameter.setBusinessDataPk5(dto.getPayDate());

                    if(mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)){
                        //流程存在情况
                        errorList.add(new ValidMessage(getMessage("COMMON_E_0021","业务编号",dto.getBusiCode())));
                    }

                }
            }
        }
        return errorList;
    }
}
