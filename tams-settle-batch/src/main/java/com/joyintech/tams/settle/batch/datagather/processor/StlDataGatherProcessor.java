package com.joyintech.tams.settle.batch.datagather.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.settle.batch.datagather.dao.StlDataGatherMapper;
import com.joyintech.tams.settle.batch.datagather.dto.*;
import com.joyintech.tams.settle.batch.datagather.service.StlDataGatherService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 何阳阳
 * @date 2020/7/16
 * @see StlDataGatherProcessor
 * @since 1.0
 */
public abstract class StlDataGatherProcessor extends BaseProcessor<NeedOperateEntity, InvestTradeWriteDto> {

    @Autowired
    private StlDataGatherMapper stlDataGatherMapper;

    @Autowired
    private StlDataGatherService stlDataGatherService;

    @Override
    public InvestTradeWriteDto process(NeedOperateEntity operateEntity) throws Exception {
        InvestTradeReaderDto item = (InvestTradeReaderDto)operateEntity;
        Date executeDate = this.getBatchExecuteInfo().getExecuteDate();
        // 根据InvestTradeReaderDto中的成交数据明细数据列表，构建一个InvestTradeWriteDto
        List<SettleTransDataDto> settleTransDataDtos = item.getSettleTransDataList();
        if(CollectionUtils.isNotEmpty(settleTransDataDtos)) {
            /*if(item.isCreateTrade()){
                //存续期
                InvestTradeWriteDto investTradeWriteDto = new InvestTradeWriteDto();
                // 金融产品交易明细
                List<TrdProductDealDto> dealList = generateProductDeal(settleTransDataDtos);
                // 金融产品交易汇总信息
                List<TotalTrdProductDealDto> dealTotalList = generateDealTotal(dealList);
                // 生成汇总交易
                investTradeWriteDto.setFinProductDealTotalList(dealTotalList);
                // 金融产品交易汇总附属信息
                List<TrdDealTotalAdd> totalAddList = generateDealTotalAdd(dealTotalList);
                // 金融产品交易实物汇总信息
                List<TrdFinProductBarterTotal> trdBarterTotalList = generateDealBarterTotal(dealTotalList);
                // 金融产品交易实物信息
                List<TrdFinProductBarter> trdBarterList = generateDealBarterList(dealList);
                // 组合交易
                List<PtlTrade> ptlTradeList = generatePtlTrade(dealList);
                // 金融产品交易费用明细
                List<TrdTradeFeeDetail> tradeFeeDetailList = generateTradeFeeDetail(dealList);
                // 划款指令明细
                List<StlPayOrders> stlPayOrdersList = generateStlPayOrdersList(dealTotalList, dealList, settleTransDataDtos);
                // 交易类金融信息
                List<FinTradeProductDto> tradeProductList = generateFinTradeProductList(dealList, settleTransDataDtos);
                // 回购质押品
                List<FinDealPledge> dealPledgeList = new ArrayList<FinDealPledge>();
                if (item.getSettleTransDataPledgeList() != null && item.getSettleTransDataPledgeList().size() > 0) {
                    dealPledgeList = generateFinDealPledgeList(dealList, item.getSettleTransDataPledgeList(), settleTransDataDtos);
                }
                // 委托完成度
                List<EtrFinishStatus> etrFinishStatusList = generateEtrFinishStatusList(dealTotalList, dealList);

                if(null != totalAddList) {
                    investTradeWriteDto.setTrdDealTotalAddList(totalAddList);
                }
                if(null != trdBarterTotalList) {
                    investTradeWriteDto.setTrdBarterTotalList(trdBarterTotalList);
                }
                if(null != trdBarterList) {
                    investTradeWriteDto.setTrdBarterList(trdBarterList);
                }
                if(null != dealList) {
                    investTradeWriteDto.setProductDealList(dealList);
                }
                if (null != ptlTradeList) {
                    investTradeWriteDto.setPtlTradeList(ptlTradeList);
                }
                if(null != tradeFeeDetailList) {
                    investTradeWriteDto.setTradeFeeDetailList(tradeFeeDetailList);
                }
                if(null != stlPayOrdersList) {
                    investTradeWriteDto.setStlPayOrdersList(stlPayOrdersList);
                }
                if(null != tradeProductList) {
                    investTradeWriteDto.setTradeProductList(tradeProductList);
                }
                if(null != dealPledgeList && !dealPledgeList.isEmpty()) {
                    investTradeWriteDto.setDealPledgeList(dealPledgeList);
                }
                if(null != etrFinishStatusList) {
                    investTradeWriteDto.setEtrFinishStatusList(etrFinishStatusList);
                }
                investTradeWriteDto.setSecType(getSecType());
                return investTradeWriteDto;
            }else{*/
                //交易数据  调用共通
                generateSettleTransData(settleTransDataDtos);
            /*}*/
        }
        return null;
    }

    protected abstract String getSecType();

    protected abstract List<String> getFinprodType();

    /**
     *  汇总成交数据
     * @param settleTransDataList
     * @return
     */
    protected void generateSettleTransData(List<SettleTransDataDto> settleTransDataList) {
        // modify by etliu 20211204 start
        // GZYHTZJY-711: 自动匹配应优先处理能够匹配到上行委托的数据，再根据业务字段匹配指令
        List<SettleTransDataDto> tempSettleTransDataList = new ArrayList<>();
        tempSettleTransDataList.addAll(settleTransDataList.stream().filter(
                x -> StringUtil.isNotBlank(x.getEntrustId())).collect(Collectors.toList()));
        tempSettleTransDataList.addAll(settleTransDataList.stream().filter(
                x -> StringUtil.isBlank(x.getEntrustId())).collect(Collectors.toList()));
        // modify by etliu 20211204 end

        List<ExchangeTransMatchCommonDto>  exchangeTransMatchCommons = new ArrayList<>();
        List<EtrEntrustDto> etrEntrustDtos = stlDataGatherMapper.listEtrEntrust(getFinprodType(),this.getBatchExecuteInfo().getExecuteDate());
        Map<String, List<EtrEntrustDto>> etrEntrustMap = etrEntrustDtos.stream().collect(Collectors.groupingBy(EtrEntrustDto::getBusiKey, Collectors.toList()));
        if(null!=etrEntrustDtos&&!StringUtil.isEmptyList(etrEntrustDtos)){
            // modify by etliu 20211204 start
            // GZYHTZJY-711: 自动匹配应优先处理能够匹配到上行委托的数据，再根据业务字段匹配指令
//            for (SettleTransDataDto settleTransDataDto : settleTransDataList) {
            for (SettleTransDataDto settleTransDataDto : tempSettleTransDataList) {
            // modify by etliu 20211204 end
                ExchangeTransMatchCommonDto exchangeTransMatchCommonDto = new ExchangeTransMatchCommonDto();
                BeanUtil.copyPropertiesIgnoreNull(exchangeTransMatchCommonDto,settleTransDataDto);
                exchangeTransMatchCommonDto.setMatchType("0");

                exchangeTransMatchCommonDto.setRemainNum(settleTransDataDto.getShareAmt());
                exchangeTransMatchCommonDto.setRemainAmount(settleTransDataDto.getFpriceAmt());
                exchangeTransMatchCommonDto.setSeqId(settleTransDataDto.getTradeId());

                if(StringUtil.isNotEmpty(settleTransDataDto.getEntrustId())){
                    // modify by etliu 20211210 start
                    // ZGZGST-4158: 重新跑清结算的批处理后，结算数据与当天全部的场内指令进行了自动匹配
//                    exchangeTransMatchCommonDto.setEntrustIdList(Arrays.asList(etrEntrustDtos.get(0).getEntrustId()));
                    exchangeTransMatchCommonDto.setEntrustIdList(Arrays.asList(settleTransDataDto.getEntrustId()));
                    // modify by etliu 20211210 end
                }else{
                    String busiKey = StringUtil.joinString(Arrays.asList(settleTransDataDto.getSecAcctId(),settleTransDataDto.getAcctNo(),settleTransDataDto.getFinprodId(), DateUtil.format(settleTransDataDto.getTradeDate()),settleTransDataDto.getTradePlat(),settleTransDataDto.getPs()),"_");
                    if(etrEntrustMap.containsKey(busiKey)){
                        List<EtrEntrustDto> currEtrEntrustList = etrEntrustMap.get(busiKey);
                        if(null!=currEtrEntrustList&&!StringUtil.isEmptyList(currEtrEntrustList)){
                            if(currEtrEntrustList.size()==1){
                                exchangeTransMatchCommonDto.setEntrustIdList(Arrays.asList(currEtrEntrustList.get(0).getEntrustId()));
                            }
                            if(currEtrEntrustList.size()>1){
                                List<String> entrustIds = currEtrEntrustList.stream().map(e -> e.getEntrustId()).collect(Collectors.toList());
                                //委托数量汇总
                                BigDecimal entrustNumTotal = currEtrEntrustList.stream().map(EtrEntrustDto::getEntrustNum).reduce(BigDecimal.ZERO, BigDecimal::add);
                                if(null!=settleTransDataDto.getShareAmt()&&settleTransDataDto.getShareAmt().compareTo(entrustNumTotal)>=0){
                                    exchangeTransMatchCommonDto.setEntrustIdList(entrustIds);
                                }else{
                                    //委托金额汇总
                                    BigDecimal entrustAmtTotal = currEtrEntrustList.stream().map(EtrEntrustDto::getEntrustAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                                    if(null!=settleTransDataDto.getTradeAmt()&&settleTransDataDto.getTradeAmt().compareTo(entrustAmtTotal)>=0){
                                        exchangeTransMatchCommonDto.setEntrustIdList(entrustIds);
                                    }
                                }
                            }
                        }
                    }
                }
                if(null!=exchangeTransMatchCommonDto&&!StringUtil.isEmptyList(exchangeTransMatchCommonDto.getEntrustIdList())){
                    exchangeTransMatchCommons.add(exchangeTransMatchCommonDto);
                }
            }
            stlDataGatherService.transactionMacthBatch(exchangeTransMatchCommons);
        }
    }

    /**
     * 组合交易
     * @param dealList 交易明细
     * @return 组合交易
     */
    protected List<PtlTrade> generatePtlTrade(List<TrdProductDealDto> dealList) {
    	return null;
    }

    /**
     * 汇总交易生成
     * @param dealList 交易明细
     * @return
     */
    protected List<TotalTrdProductDealDto> generateDealTotal(List<TrdProductDealDto> dealList) {
        return null;
    }
    
    /**
     * 汇总交易附属生成
     * @param dealList 汇总交易
     * @return
     */
    protected List<TrdDealTotalAdd> generateDealTotalAdd(List<TotalTrdProductDealDto> totleList) {
        return null;
    }
    
    /**
     * 实物汇总交易生成
     * @param dealList 实物汇总交易
     * @return
     */
    protected List<TrdFinProductBarterTotal> generateDealBarterTotal(List<TotalTrdProductDealDto> totleList) {
        return null;
    }
    
    /**
     * 实物交易生成
     * @param dealList 实物交易
     * @return
     */
    protected List<TrdFinProductBarter> generateDealBarterList(List<TrdProductDealDto> dealList) {
        return null;
    }
    

    /**
     * 金融产品交易明细
     * @param settleTransDataDtos 成交数据明细
     * @return
     */
    protected List<TrdProductDealDto> generateProductDeal(List<SettleTransDataDto> settleTransDataDtos) {
    	return null;
    }

    /**
     * 金融产品交易费用明细
     * @param dealList 交易明细
     * @return
     */
    protected List<TrdTradeFeeDetail> generateTradeFeeDetail(List<TrdProductDealDto> dealList) {
    	return null;
    }

    /**
     * 划款指令明细
     */
    protected List<StlPayOrders> generateStlPayOrdersList(List<TotalTrdProductDealDto> totalTrdProductDealDtoList, List<TrdProductDealDto> dealList, List<SettleTransDataDto> settleTransDataDtos) {
    	return null;
    }

    protected List<FinTradeProductDto> generateFinTradeProductList(List<TrdProductDealDto> dealList, List<SettleTransDataDto> settleTransDataDtos) {
        return null;
    }

    protected List<FinDealPledge> generateFinDealPledgeList(List<TrdProductDealDto> dealList, List<SettleTransDataPledge> settleTransDataPledgeList, List<SettleTransDataDto> settleTransDataDtos) {
        return null;
    }
    
    protected List<EtrFinishStatus> generateEtrFinishStatusList(List<TotalTrdProductDealDto> totalTrdProductDealDtoList, List<TrdProductDealDto> dealList){
    	return null;
    }
}
