package com.joyintech.tams.settle.batch.datagather.reader.repo;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SettleBatchPara;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeReaderDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取债券质押式回购到期续作交易数据
 * @author 何阳阳
 * @date 2020/7/30
 * @see StlDataGatherRepo2Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherRepo2Reader extends StlDataGatherRepoBaseReader {

    @Override
    public List<InvestTradeReaderDto> createList() {
        // 批处理执行信息
        SysBatchExecInfo execInfo = getBatchExecuteInfo();
        // 数据清算参数表
        List<SettleBatchPara> list = stlDownDataDealService.selectAllParam(execInfo.getExecuteDate());
        // 组合代码List
        Set<String> portfolioIdList = new HashSet<>();

        list.forEach(item -> {
            // 证券品种
            if (StringUtil.isNotEmpty(item.getSecType()) && isSecType(item.getSecType())) {
                // 组合代码
                if (StringUtil.isNotEmpty(item.getPortfolioId())) {
                    portfolioIdList.add(item.getPortfolioId());
                }
            }
        });

        Map<String, Object> searchParam = new HashMap<>(4);
        searchParam.put("portfolioIdList", portfolioIdList);
        searchParam.put("dealDate", execInfo.getExecuteDate());
        setSearchParam(searchParam);
        // 成交数据明细表获取债券质押式回购到期续作（合同新开）交易明细数据
        List<SettleTransDataDto> settleTransDataList1 = stlDataGatherMapper.selectSettleTransData(searchParam);

        // 成交数据明细表获取债券质押式回购到期续作（续作前期到期）交易明细数据
        List<SettleTransDataDto> settleTransDataList2 = new ArrayList<>();
        // 交易方向列表
        searchParam.put("psList", Arrays.asList(DictCodeList.交易方向_到期.getValue()));
        List<String> tradeIdList = settleTransDataList1.stream().map(SettleTransDataDto::getTradeId).collect(Collectors.toList());
        int index = 0, size = 500, max = tradeIdList.size() / size;
        for(int i=0;i<max;i++) {
            index = (i + 1) * size;
            searchParam.put("tradeIdList", tradeIdList.subList(i*size, index));
            settleTransDataList2.addAll(stlDataGatherMapper.selectContinuationSettleTransData(searchParam));
        }
        searchParam.put("tradeIdList", tradeIdList.subList(index, tradeIdList.size()));
        settleTransDataList2.addAll(stlDataGatherMapper.selectContinuationSettleTransData(searchParam));

        // 成交编号和指令编号对应关系
        Map<String, String> tradeIdMap = settleTransDataList1.stream().collect(Collectors.toMap(SettleTransDataDto::getTradeId, SettleTransDataDto::getOrderId));

        // 成交数据明细合并后分组
        Map<String, List<SettleTransDataDto>> resultMap = settleTransDataList1.stream().collect(JoyinCollectors.groupingBy(SettleTransDataDto::getTradeId));
        settleTransDataList2.forEach(item -> {
            if(tradeIdMap.containsKey(item.getTradeId())) {
                // 补充指令编号
                item.setOrderId(tradeIdMap.get(item.getTradeId()));
                resultMap.get(item.getOrderId()).addAll(Arrays.asList(splitTransData(item)));
            }
        });

        return resultMap.values().stream().map(InvestTradeReaderDto::new).collect(Collectors.toList());
    }

    @Override
    protected List<String> getPSList() {
        return Arrays.asList(DictCodeList.交易方向_到期续做.getValue());
    }
}
