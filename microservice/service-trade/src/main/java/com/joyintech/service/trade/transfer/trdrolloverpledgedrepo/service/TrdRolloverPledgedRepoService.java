package com.joyintech.service.trade.transfer.trdrolloverpledgedrepo.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.trade.transfer.trdassetsproject.dto.FinDealPledgeDto;
import com.joyintech.service.trade.transfer.trdrolloverpledgedrepo.dao.TrdRolloverPledgedRepoMapper;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("backTrdRolloverPledgedRepoService")
public class TrdRolloverPledgedRepoService {

    @Autowired
    private TrdRolloverPledgedRepoMapper trdRolloverPledgedRepoMapper;
    
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 查询质押券信息
     * @param param 参数
     * @return 质押券信息
     */
    public List<FinDealPledgeDto> selectFinDealPledgeList(Map<String, Object> param) {
        return trdRolloverPledgedRepoMapper.selectFinDealPledgeList(param);
    }

    public List<FinDealPledgeDto> selectAlFinDealPledgeList(Map<String, Object> param) {
        return trdRolloverPledgedRepoMapper.selectAlFinDealPledgeList(param);
    }

    public List<SelectOptionDto> selectPortfolioId(Map<String, Object> param) {
        param.put("capacctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        List<SelectOptionDto> result = trdRolloverPledgedRepoMapper.selectPortfolioId(param);
        if(basePtlCashPositionInfoService != null){
            Iterator<SelectOptionDto> iterator =  result.iterator();
            while(iterator.hasNext()) {
            	SelectOptionDto dto = iterator.next();
                PtlCashPositionFlowDto pcpdto = new PtlCashPositionFlowDto();
                Date sysdate = DateUtil.parse((String)param.get("tradeDate"));
                pcpdto.setCdate(sysdate);
                List<String> acctTypeList = new ArrayList<>();
                acctTypeList.add(BussinessDictCodeList.资金账户类型_托管账户.getValue());
                acctTypeList.add(BussinessDictCodeList.资金账户类型_交易所资金账户.getValue());
                pcpdto.setAcctType(acctTypeList);
                pcpdto.setPortfolioId(dto.getId());
                pcpdto.setCcy((String)param.get("ccy"));
                BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(pcpdto);
                if(cashT.compareTo(new BigDecimal(0))<=0) {
                	iterator.remove();
                }
            }
        }
        return result;
    }
    public List<TrdProductDeal> selectDealBySplitTradeId(String splitTradeId) {
        return trdRolloverPledgedRepoMapper.selectDealBySplitTradeId(splitTradeId);
    }
}
