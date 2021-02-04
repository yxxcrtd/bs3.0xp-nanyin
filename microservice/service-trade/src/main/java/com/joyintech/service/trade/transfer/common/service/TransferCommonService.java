package com.joyintech.service.trade.transfer.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.transfer.common.dao.TransferCommonMapper;
import com.joyintech.service.trade.transfer.common.dto.TransferCommonDto;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("backTransferCommonService")
public class TransferCommonService extends BaseService {

    @Autowired
    private TransferCommonMapper transferCommonMapper;
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    public List<WflTrdProductDeal> selectDealByworkflowId(String workflowId) {
        return transferCommonMapper.selectDealByworkflowId(workflowId);
    }

    public List<TransferCommonDto> findAvaPortfolioInfo(Map<String, Object> param) {
        List<TransferCommonDto> resultList = new ArrayList<>();
        List<TransferCommonDto> result = new ArrayList<>();
        param.put("capacctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        resultList = transferCommonMapper.findAvaPortfolioInfo(param);
        for (TransferCommonDto dto:resultList) {
            PtlCashPositionFlowDto pcpdto = new PtlCashPositionFlowDto();
            Date sysdate = DateUtil.parse((String)param.get("tradeDate"));
            pcpdto.setCdate(sysdate);
            List<String> acctTypeList = new ArrayList<>();
            acctTypeList.add(BussinessDictCodeList.资金账户类型_托管账户.getValue());
            acctTypeList.add(BussinessDictCodeList.资金账户类型_交易所资金账户.getValue());
            pcpdto.setAcctType(acctTypeList);
            pcpdto.setPortfolioId(dto.getPortfolioId());
            pcpdto.setCcy((String)param.get("ccy"));
            if (basePtlCashPositionInfoService != null) {
                BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(pcpdto);
                if(StringUtil.isNotEmpty(cashT)&&BigDecimal.ZERO.compareTo(cashT)!=0){
                    dto.setAvailableCash(cashT);
                    result.add(dto);
                }
            }
        }
        return result;
    }
}
