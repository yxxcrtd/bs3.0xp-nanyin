package com.joyintech.tams.settle.batch.datagather.reader;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SettleBatchPara;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datadeal.service.StlDownDataDealService;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeReaderDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleDelDealDetailDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tams.settle.batch.datagather.service.StlDataGatherService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 分红和送转股票交易数据处理
 * 
 * @author chenzhuo
 * @date 2020/7/20
 * @see StlDataGatherShares2Reader
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherShares2Reader extends StlDataGatherReader {

    @Autowired
    private StlDownDataDealService stlDownDataDealService;

    @Autowired
    private StlDataGatherService stlDataGatherService;

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

        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("portfolioIdList", portfolioIdList);
        setSearchParam(searchParam);
        // 除权交易明细数据
        searchParam.put("startDate", execInfo.getExecuteDate());
        searchParam.put("endDate", execInfo.getExecuteDate());
        searchParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        List<SettleDelDealDetailDto> settleDelDealDetailDtoList = stlDataGatherService.selectSettleDelDealDetail(searchParam);

        if (CollectionUtils.isNotEmpty(settleDelDealDetailDtoList)) {
            // 公告基本信息按照资产代码分组
            Map<String, List<SettleDelDealDetailDto>> regMap = settleDelDealDetailDtoList.stream().collect(Collectors.groupingBy(x -> {
                return x.getFinprodId();
            }));
            
            // 现金分红和送转的相关交易数据
            searchParam.put("finprodIdList", new ArrayList<>(regMap.keySet()));
            List<SettleTransDataDto> tempSettleTransDataDtos = stlDataGatherMapper.selectCashBonusSettleTransData(searchParam);
            if (CollectionUtils.isNotEmpty(tempSettleTransDataDtos)) {
                // 交易数据根据资产代码分组
                Map<String, List<SettleTransDataDto>> dataMap = tempSettleTransDataDtos.stream().collect(Collectors.groupingBy(x -> {
                    return x.getFinprodId();
                }));

                List<SettleTransDataDto> settleTransDataDtos = new ArrayList<SettleTransDataDto>();
                for (String key : dataMap.keySet()) {
                    settleTransDataDtos.addAll(dataMap.get(key).stream().filter(x -> {
                        return ((StringUtil.equals(x.getPs(), DictCodeList.交易方向_现金分红.getValue())
                                 && !StringUtil.isEmptyList(regMap.get(key))
                                 && DateUtil.compareDate(x.getDealDate(), regMap.get(key).get(0).getRegDate()) == 0)// 权益登记日
                                || (StringUtil.equals(x.getPs(), DictCodeListStl.交易方向_送股.getValue())
                                    && !StringUtil.isEmptyList(regMap.get(key))
                                    && DateUtil.compareDate(x.getDealDate(), regMap.get(key).get(0).getDelDate()) == 0));// 除权除息日
                    }).collect(Collectors.toList()));
                }
                
                if (CollectionUtils.isNotEmpty(settleTransDataDtos)) {
                    List<InvestTradeReaderDto> resultList = new ArrayList<InvestTradeReaderDto>();
                    InvestTradeReaderDto investTradeReaderDto = new InvestTradeReaderDto();
                    investTradeReaderDto.setSettleTransDataList(settleTransDataDtos);
                    investTradeReaderDto.setSettleTransDataDelDtoList(settleDelDealDetailDtoList);
                    resultList.add(investTradeReaderDto);
                    return resultList;
                }
            }
        }

        return null;
    }

    @Override
    protected void setSearchParam(Map<String, Object> searchParam) {
        // 业务类型
        searchParam.put("busiType", DictCodeList.业务类型_股票交易.getValue());
        // 交易方向列表
        searchParam.put("psList",
            Arrays.asList(DictCodeList.交易方向_现金分红.getValue(), DictCodeListStl.交易方向_送股.getValue()));
    }

    @Override
    protected boolean isSecType(String secType) {
        return StringUtil.equals(DictCodeListExt1.证券品种_股票.getValue(), secType);
    }
}
