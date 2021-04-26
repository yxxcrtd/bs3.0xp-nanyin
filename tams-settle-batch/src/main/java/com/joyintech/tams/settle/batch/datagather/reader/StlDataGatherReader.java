package com.joyintech.tams.settle.batch.datagather.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SettleBatchPara;
import com.joyintech.tams.settle.batch.datadeal.service.StlDownDataDealService;
import com.joyintech.tams.settle.batch.datagather.dao.StlDataGatherMapper;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeReaderDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 何阳阳
 * @date 2020/7/16
 * @see StlDataGatherReader
 * @since 1.0
 */
public abstract class StlDataGatherReader extends BaseReader<InvestTradeReaderDto> {

    @Autowired
    protected StlDownDataDealService stlDownDataDealService;
    @Autowired
    protected StlDataGatherMapper stlDataGatherMapper;

    @Override
    public List<InvestTradeReaderDto> createList() {
        // 批处理执行信息
        SysBatchExecInfo execInfo = getBatchExecuteInfo();
        // 数据清算参数表
        List<SettleBatchPara> list = stlDownDataDealService.selectAllParam(execInfo.getExecuteDate());
        // 组合代码List
        Set<String> portfolioIdList = new HashSet<>();
        // 是否处理对象
        StringBuffer buffer = new StringBuffer();
        list.forEach(item -> {
            // 证券品种
            if (StringUtil.isNotEmpty(item.getSecType()) && isSecType(item.getSecType())) {
            	buffer.append(item.getSecType());
                // 组合代码
                if (StringUtil.isNotEmpty(item.getPortfolioId())) {
                    portfolioIdList.add(item.getPortfolioId());
                }
            }
        });
        if (buffer.length() > 0) {
	        Map<String, Object> searchParam = new HashMap<>(4);
	        searchParam.put("portfolioIdList", portfolioIdList);
	        searchParam.put("dealDate", execInfo.getExecuteDate());
	        setSearchParam(searchParam);
	        List<SettleTransDataDto> settleTransDataDtos = stlDataGatherMapper.selectSettleTransData(searchParam);
	        Map<String,List<SettleTransDataDto>> tempMap = settleTransDataDtos.stream().collect(JoyinCollectors.groupingBy(SettleTransDataDto::getOrderId));
	        return tempMap.values().stream().map(InvestTradeReaderDto::new).collect(Collectors.toList());
        }
        else {
        	return new ArrayList<InvestTradeReaderDto>();
        }
    }

    /**
     * 设置查询参数
     * @param searchParam
     */
    protected abstract void setSearchParam(Map<String, Object> searchParam);

    /**
     * 是否为可处理的证券品种
     * @param secType 证券品种
     * @return
     */
    protected abstract boolean isSecType(String secType);
}