/*
 * 文件名：StlDataGatherFund2Reader.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年07月30日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.settle.batch.datagather.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SettleBatchPara;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeReaderDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleDelDealDetailDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;

/**
 * 基金分红处理
 * @author 王波
 * @date 2020年07月30日
 * @see StlDataGatherFund2Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherFund2Reader extends StlDataGatherReader {

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

        Map<String, Object> searchParam = new HashMap<>();
        searchParam.put("portfolioIdList", portfolioIdList);
        searchParam.put("dealDate", execInfo.getExecuteDate());
        setSearchParam(searchParam);
        
        List<InvestTradeReaderDto> res = new ArrayList<InvestTradeReaderDto>();
        
        InvestTradeReaderDto investTradeReaderDto = new InvestTradeReaderDto();
        
        // 除权公告数据
        List<SettleDelDealDetailDto> settleTransDataDelDtos = stlDataGatherMapper.selectFundDelSettleTransData(searchParam);
        if (settleTransDataDelDtos != null && settleTransDataDelDtos.size() > 0) {
	        investTradeReaderDto.setSettleTransDataDelDtoList(settleTransDataDelDtos);
        }

        List<SettleTransDataDto> tempSettleTransDataDtos = stlDataGatherMapper.selectFundSettleTransData(searchParam);
        if(CollectionUtils.isNotEmpty(tempSettleTransDataDtos)) {
            investTradeReaderDto.setSettleTransDataList(tempSettleTransDataDtos);

            res.add(investTradeReaderDto);
        }
        return res;
    }

    @Override
    protected void setSearchParam(Map<String, Object> searchParam) {
        // 交易方向列表
        searchParam.put("psList", Arrays.asList(DictCodeList.交易方向_现金分红.getValue(), DictCodeList.交易方向_红利再投.getValue()));
    }

    @Override
    protected boolean isSecType(String secType) {
        return false;
    }

    @Override
    protected boolean isCreateTrade(){
        return false;
    }
}
