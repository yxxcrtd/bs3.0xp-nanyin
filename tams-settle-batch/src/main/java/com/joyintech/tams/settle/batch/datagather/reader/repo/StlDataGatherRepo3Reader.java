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
 * 获取债券质押式协议回购提前终止交易数据
 * @author 何阳阳
 * @date 2020/7/30
 * @see StlDataGatherRepo3Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherRepo3Reader extends StlDataGatherRepoBaseReader {

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
        // 回购类型
        searchParam.put("repoType", DictCodeList.回购类型_质押式协议回购.getValue());
        // 从成交数据明细表获取债券质押式协议回购提前终止、到期交易明细数据
        List<SettleTransDataDto> settleTransDataList = stlDataGatherMapper.selectEndSettleTransData(searchParam);

        List<SettleTransDataDto> tempSettleTransDataList = new ArrayList<>(settleTransDataList.size()*2);
        settleTransDataList.forEach(item -> {
            tempSettleTransDataList.addAll(Arrays.asList(splitTransData(item)));
        });
        // 成交数据明细分组
        Map<String, List<SettleTransDataDto>> resultMap = tempSettleTransDataList.stream().collect(JoyinCollectors.groupingBy(SettleTransDataDto::getTradeId));
        return resultMap.values().stream().map(InvestTradeReaderDto::new).collect(Collectors.toList());
    }

    @Override
    protected List<String> getPSList() {
        return Arrays.asList(DictCodeList.交易方向_提前终止.getValue());
    }
}
