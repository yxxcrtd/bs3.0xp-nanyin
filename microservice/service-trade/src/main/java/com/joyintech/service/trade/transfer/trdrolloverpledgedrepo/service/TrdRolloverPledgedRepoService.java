package com.joyintech.service.trade.transfer.trdrolloverpledgedrepo.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.trdassetsproject.dto.FinDealPledgeDto;
import com.joyintech.service.trade.transfer.trdrolloverpledgedrepo.dao.TrdRolloverPledgedRepoMapper;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("backTrdRolloverPledgedRepoService")
public class TrdRolloverPledgedRepoService {

    @Autowired
    private TrdRolloverPledgedRepoMapper trdRolloverPledgedRepoMapper;

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
        return trdRolloverPledgedRepoMapper.selectPortfolioId(param);
    }
    public List<TrdProductDeal> selectDealBySplitTradeId(String splitTradeId) {
        return trdRolloverPledgedRepoMapper.selectDealBySplitTradeId(splitTradeId);
    }
}
