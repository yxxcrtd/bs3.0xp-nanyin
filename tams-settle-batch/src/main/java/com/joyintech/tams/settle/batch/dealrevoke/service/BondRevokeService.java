package com.joyintech.tams.settle.batch.dealrevoke.service;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrSecPositionFlow;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.settle.batch.datagather.service.StlDataGatherService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 何阳阳
 * @date 2020/8/4
 * @see BondRevokeService
 * @since 1.0
 */
@Service("01" + BaseRevokeService.REVOKE_BEAN_NAME)
@Lazy
public class BondRevokeService implements BaseRevokeService {

    @Autowired
    private InvestDealRevokeService investDealRevokeService;

    @Autowired
    private StlDataGatherService stlDataGatherService;

    /**
     * 债券交易撤销
     * @param tradeIdMap 交易编号
     * @param totalTradeIdMap 汇总交易编号
     */
    @Override
    public void doRevoke(Map<String,String> tradeIdMap, Map<String,String> totalTradeIdMap) {
        // 参考债券交易撤销逻辑，生成已生效的撤销交易数据，同时反向更新头寸信息、持仓信息以及委托完成信息。注意不要调用对外发送逻辑。
        if(!totalTradeIdMap.isEmpty()) {
            List<String> totalTradeIdList =  totalTradeIdMap.entrySet().stream().filter(item-> !StringUtil.equals(item.getValue(), DictCodeList.交易方向_上市流通.getValue()))
                    .map(item->item.getKey()).collect(Collectors.toList());
            Map<String, Object> param = new HashMap<>();
            param.put("totalTradeIdList", totalTradeIdList);
            param.put("status", DictCodeListExt1.交易流程状态_撤销.getValue());
            param.put("busiType", DictCodeList.共通业务类型_债券类资产交易.getValue());
            stlDataGatherService.excutePosition(param);
            investDealRevokeService.revokeDealTotal(totalTradeIdList);
            List<String> ssltTotalTradeIdList =  totalTradeIdMap.entrySet().stream().filter(item-> StringUtil.equals(item.getValue(), DictCodeList.交易方向_上市流通.getValue()))
                    .map(item->item.getKey()).collect(Collectors.toList());
            List<String> tradeIds = investDealRevokeService.searchTradeIdsByTotalTradeIds(ssltTotalTradeIdList);
            if(CollectionUtils.isNotEmpty(tradeIds)){
                investDealRevokeService.deleteSsltSecPositionFlow(tradeIds);
                investDealRevokeService.deleteSsltEtrSecPositionFlow(tradeIds);
            }
            investDealRevokeService.revokeDealTotal(ssltTotalTradeIdList);
        }
    }

    @Override
    public String getBusiType() {
        return DictCodeListExt1.证券品种_债券.getValue();
    }
}
