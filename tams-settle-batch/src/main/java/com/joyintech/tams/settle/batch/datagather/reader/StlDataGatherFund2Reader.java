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

        Map<String, Object> searchParam = new HashMap<>(4);
        searchParam.put("portfolioIdList", portfolioIdList);
        searchParam.put("dealDate", execInfo.getExecuteDate());
        setSearchParam(searchParam);
        
        List<InvestTradeReaderDto> res = new ArrayList<InvestTradeReaderDto>();
        
        InvestTradeReaderDto investTradeReaderDto = new InvestTradeReaderDto();
        
        // 除权公告数据（仅针对非货基的现金分红）
        List<SettleDelDealDetailDto> settleTransDataDelDtos = stlDataGatherMapper.selectFundDelSettleTransData(searchParam);
        if (settleTransDataDelDtos != null && settleTransDataDelDtos.size() > 0) {
	        investTradeReaderDto.setSettleTransDataDelDtoList(settleTransDataDelDtos);
        }
        
        // 公告基本信息按照资产代码分组
        Map<String, List<SettleDelDealDetailDto>> regMap = settleTransDataDelDtos.stream().collect(Collectors.groupingBy(x -> {
            return x.getFinprodId();
        }));
        // 现金分红和送转的相关交易数据
        searchParam.put("finprodIdList", new ArrayList<>(regMap.keySet()));
        // 检索货基红利再投以及非货基的现金分红数据（非货基的现金分红是除权日之后，现金分红当日生成数据。货基的红利再投和除权无关）
        List<SettleTransDataDto> tempSettleTransDataDtos = stlDataGatherMapper.selectCashBonusSettleTransData(searchParam);
        List<SettleTransDataDto> settleTransDataDtos = new ArrayList<SettleTransDataDto>();
        if (CollectionUtils.isNotEmpty(tempSettleTransDataDtos)) {
            // 交易数据根据资产代码分组
            Map<String, List<SettleTransDataDto>> dataMap = tempSettleTransDataDtos.stream().collect(Collectors.groupingBy(x -> {
                return x.getFinprodId();
            }));
            
            for (String key : dataMap.keySet()) {
                settleTransDataDtos.addAll(dataMap.get(key).stream().filter(x -> {
                    return ((StringUtil.equals(x.getPs(), DictCodeList.交易方向_现金分红.getValue())
                             && !StringUtil.isEmptyList(regMap.get(key))
                             && DateUtil.compareDate(x.getDealDate(), regMap.get(key).get(0).getRegDate()) == 0)// 权益登记日
                            || (!StringUtil.equals(x.getPs(), DictCodeList.交易方向_现金分红.getValue())
                                && DateUtil.compareDate(x.getTradeDate(), execInfo.getExecuteDate()) == 0));// 除权除息日
                }).collect(Collectors.toList()));
            }
        }
        if(CollectionUtils.isNotEmpty(settleTransDataDtos)) {
            investTradeReaderDto.setSettleTransDataList(settleTransDataDtos);

            res.add(investTradeReaderDto);
        }
        return res;
    }

    @Override
    protected void setSearchParam(Map<String, Object> searchParam) {
        // 业务类型
    	List<String> busiTypeList = new ArrayList<String>();
    	busiTypeList.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
    	busiTypeList.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
        searchParam.put("busiTypeList", busiTypeList);
        // 交易方向列表
        searchParam.put("psList", Arrays.asList(DictCodeList.交易方向_现金分红.getValue(), DictCodeList.交易方向_红利再投.getValue()));
    }

    @Override
    protected boolean isSecType(String secType) {
        return false;
    }
}
