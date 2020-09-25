package com.joyintech.service.period.trdsecovertransfer.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.period.trdsecovertransfer.dao.TrdSecOverTransferMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationZrDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrdSecOverTransferService extends BaseService<TrdSecOverdueDurationZrDto> {
    @Autowired
    private TrdSecOverTransferMapper trdSecOverTransferMapper;

    public Page<TrdSecOverdueDurationZrDto> findPage(int page, int length, Map<String, Object> param) {
        setParam(param);
        return queryForPage(page,length,()->trdSecOverTransferMapper.selectPageData(param));
    }

    private void setParam(Map<String, Object> param) {
        param.put("STATUS_VALID_YX", DictCodeListExt1.有效状态_有效.getValue());
        param.put("STATUS_VALID_CX", DictCodeListExt1.有效状态_撤销.getValue());
        param.put("STATUS_VALID_LCZ", DictCodeListExt1.有效状态_流程中.getValue());
        param.put("PINAN_BUSINESS_TYPE_YDQZCZR", DictCodeListExt3.平安业务类型_已到期资产转让.getValue());
        param.put("PS_MC", DictCodeList.交易方向_卖出.getValue());
        param.put("YON_Y", DictCodeListExt1.是否_是.getValue());
        param.put("YON_N", DictCodeListExt1.是否_否.getValue());
        param.put("TRADE_STATUS_CS", DictCodeListExt1.交易状态_初始.getValue());
        String portfolioId = (String)param.get("portfolioId");
        String finprodId = (String)param.get("finprodId");
        String statusValid = (String)param.get("statusValid");
        String counterId = (String)param.get("counterId");
        String transferDateStart = (String)param.get("transferDateStart");
        String transferDateEnd = (String)param.get("transferDateEnd");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioId",StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        else{
            param.remove("portfolioId");
        }
        if(StringUtil.isNotEmpty(statusValid)){
            param.put("statusValid",StringUtil.splitToList(GlobalConstant.COMMA,statusValid));
        }
        else{
            param.remove("statusValid");
        }
        if(StringUtil.isNotEmpty(finprodId)){
            param.put("finprodId",StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        else{
            param.remove("finprodId");
        }
        if(StringUtil.isNotEmpty(counterId)){
            param.put("counterId",StringUtil.splitToList(GlobalConstant.COMMA,counterId));
        }
        else{
            param.remove("counterId");
        }
        if(StringUtil.isNotEmpty(transferDateStart)){
            param.put("transferDateStart", DateUtil.parse(transferDateStart));
        }
        else{
            param.remove("transferDateStart");
        }
        if(StringUtil.isNotEmpty(transferDateEnd)){
            param.put("transferDateEnd", DateUtil.parse(transferDateEnd));
        }
        else{
            param.remove("transferDateEnd");
        }
    }
}
