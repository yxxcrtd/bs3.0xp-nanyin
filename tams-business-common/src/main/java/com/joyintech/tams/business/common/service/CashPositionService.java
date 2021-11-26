package com.joyintech.tams.business.common.service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.business.common.dao.JoyinbusinessCommonMapper;
import com.joyintech.tams.business.common.dto.CashPositionFlowDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CashPositionService {

    @Autowired
    private JoyinbusinessCommonMapper joyinbusinessCommonMapper;
    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 查询指令可用现金
     *
     * @param dto
     * @return
     */
    public BigDecimal etrQryCommandAvailableCash(Map<String, Object> param){
        BigDecimal cashT = BigDecimal.ZERO;
        
        CashPositionFlowDto dto = new CashPositionFlowDto();
        List<String> acctTypeList = new ArrayList<>();
        dto.setAcctType(acctTypeList);

        dto.setCdate((Date) param.get("vdate"));
        dto.setPortfolioId((String)param.get("portfolioId"));
        dto.setCcy((String)param.get("ccy"));

        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        BigDecimal cashT3 = qryCommandAvailableCash(dto);
        cashT = ArithmeticUtil.doAdd(cashT, cashT3 == null ? BigDecimal.ZERO : cashT3);

        acctTypeList.clear();
        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        BigDecimal cashT4 = qryCommandAvailableCash(dto);
        cashT = ArithmeticUtil.doAdd(cashT, cashT4 == null ? BigDecimal.ZERO : cashT4);
        
        return cashT;
    }

    /**
     * 按账户类型依次查持仓类型_委托指令可用、持仓类型_委托指令预增、持仓类型_人工调整
     * @param dto
     * @return
     */
    private BigDecimal qryCommandAvailableCash(CashPositionFlowDto dto) {
        BigDecimal val = BigDecimal.ZERO;
        dto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
        BigDecimal availableCash = joyinbusinessCommonMapper.selectCommandAvailableCash(dto);
        
        dto.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
        BigDecimal advanceCash =  joyinbusinessCommonMapper.selectCommandAvailableCash(dto);
        
        dto.setHodingType(DictCodeListExt1.持仓类型_人工调整.getValue());
        BigDecimal 人工调整流水 = joyinbusinessCommonMapper.selectManualCashFlow(dto);
        
        if (availableCash != null) {
            val = ArithmeticUtil.doAdd(val, availableCash);
        }
        if (advanceCash != null) {
            val = ArithmeticUtil.doAdd(val, advanceCash);
        }
        if (availableCash == null && advanceCash == null){
            return null;
        }
        return ArithmeticUtil.doAdd(val, 人工调整流水);
    }
}
