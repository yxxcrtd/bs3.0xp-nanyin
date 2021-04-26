package com.joyintech.tams.settle.batch.dealrevoke.service;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.settle.batch.datagather.service.StlDataGatherService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 何阳阳
 * @date 2020/8/4
 * @see RepoRevokeService
 * @since 1.0
 */
@Service("09" + BaseRevokeService.REVOKE_BEAN_NAME)
@Lazy
public class RepoRevokeService implements BaseRevokeService {

    @Autowired
    private InvestDealRevokeService investDealRevokeService;

    @Autowired
    private StlDataGatherService stlDataGatherService;

    /**
     * 回购交易撤销
     * @param tradeIdList 交易编号
     * @param totalTradeIdList 汇总交易编号
     */
    @Override
    public void doRevoke(List<String> tradeIdList, List<String> totalTradeIdList) {
        // 参考债券质押式回购首期交易撤销逻辑，生成已生效的撤销交易数据，同时反向更新头寸信息、持仓信息以及委托完成信息。注意不要调用对外发送逻辑。
        if(CollectionUtils.isNotEmpty(tradeIdList)) {
            Map<String, Object> param = new HashMap<>();
            param.put("tradeIdList", tradeIdList);
            param.put("status", DictCodeListExt1.交易流程状态_撤销.getValue());
            param.put("busiType", DictCodeList.共通业务类型_质押式回购交易.getValue());
            stlDataGatherService.excutePosition(param);
            investDealRevokeService.revokeDeal(tradeIdList);
        }
        // 参考债券质押式回购到期续做、提前终止、到期交易撤销逻辑，生成已生效的撤销交易数据，同时反向更新头寸信息、持仓信息以及委托完成信息。注意不要调用对外发送逻辑。
        if(CollectionUtils.isNotEmpty(totalTradeIdList)) {
            Map<String, Object> param = new HashMap<>();
            param.put("totalTradeIdList", totalTradeIdList);
            param.put("status", DictCodeListExt1.交易流程状态_撤销.getValue());
            param.put("busiType", DictCodeList.共通业务类型_质押式回购交易.getValue());
            stlDataGatherService.excutePosition(param);
            investDealRevokeService.revokeDealTotal(totalTradeIdList);
        }
    }

    @Override
    public String getBusiType() {
        return DictCodeListExt1.证券品种_回购.getValue();
    }

}
