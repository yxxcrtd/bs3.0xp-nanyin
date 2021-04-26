package com.joyintech.tams.settle.batch.dealrevoke.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SettleBatchPara;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.settle.batch.datadeal.service.StlDownDataDealReaderService;
import com.joyintech.tams.settle.batch.dealrevoke.dto.DealRevokeParamDto;
import com.joyintech.tams.settle.batch.dealrevoke.service.InvestDealRevokeService;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 何阳阳
 * @date 2020/8/3
 * @see InvestDealRevokeReader
 * @since 1.0
 */
@Service
@Lazy
public class InvestDealRevokeReader extends BaseReader<DealRevokeParamDto> {
    @Autowired
    private InvestDealRevokeService investDealRevokeService;

    @Autowired
    private StlDownDataDealReaderService stlDownDataDealService;

    @Override
    public List<DealRevokeParamDto> createList() {
        // 批处理执行信息
        SysBatchExecInfo execInfo = getBatchExecuteInfo();
        // 日期
        Date tradeDate = execInfo.getExecuteDate();
        // 数据清算参数表
        List<SettleBatchPara> list = stlDownDataDealService.selectAllParam(tradeDate);
        // 撤销参数
        List<DealRevokeParamDto> revokeParamDtos = new ArrayList<>(list.size());

        // 按证券品种分组
        Map<String, List<SettleBatchPara>> listMap = list.stream().collect(JoyinCollectors.groupingBy(SettleBatchPara::getSecType));

        listMap.forEach((key, value) -> {
            // 提取当前分组中的组合代码
            List<String> portfolioIdList = value.stream().map(SettleBatchPara::getPortfolioId).filter(StringUtil::isNotEmpty).collect(Collectors.toList());
            // 根据证券品种分别调用不同的查询逻辑，查询交易编号和汇总交易编号
            if(StringUtil.equals(DictCodeListExt1.证券品种_债券.getValue(), key)) {
                // 汇总交易编号
                List<String> totalTradeIdList = investDealRevokeService.selectTotalTradeIdForBond(tradeDate, portfolioIdList);
                revokeParamDtos.add(new DealRevokeParamDto(key, tradeDate, portfolioIdList,null, totalTradeIdList));
            }
            else if(StringUtil.equals(DictCodeListExt1.证券品种_回购.getValue(), key)) {
                // 交易编号
                List<String> tradeIdList = investDealRevokeService.selectTradeIdForRepo(tradeDate, portfolioIdList);
                // 汇总交易编号
                List<String> totalTradeIdList = investDealRevokeService.selectTotalTradeIdForRepo(tradeDate, portfolioIdList);
                revokeParamDtos.add(new DealRevokeParamDto(key, tradeDate, portfolioIdList, tradeIdList, totalTradeIdList));
            }
            else if(StringUtil.equals(DictCodeListExt1.证券品种_股票.getValue(), key)) {
                // 交易编号
                List<String> tradeIdList = investDealRevokeService.selectTradeIdForShares(tradeDate, portfolioIdList);
                // 汇总交易编号
                List<String> totalTradeIdList = investDealRevokeService.selectTotalTradeIdForShares(tradeDate, portfolioIdList);
                revokeParamDtos.add(new DealRevokeParamDto(key, tradeDate, portfolioIdList, tradeIdList, totalTradeIdList));
            }
            else if(StringUtil.equals(DictCodeListExt1.证券品种_基金.getValue(), key)) {
                // 交易编号
                List<String> tradeIdList = investDealRevokeService.selectTradeIdForFund(tradeDate, portfolioIdList);
                // 汇总交易编号
                List<String> totalTradeIdList = investDealRevokeService.selectTotalTradeIdForFund(tradeDate, portfolioIdList);
                revokeParamDtos.add(new DealRevokeParamDto(key, tradeDate, portfolioIdList, tradeIdList, totalTradeIdList));
            }
        });
        return revokeParamDtos;
    }
}
