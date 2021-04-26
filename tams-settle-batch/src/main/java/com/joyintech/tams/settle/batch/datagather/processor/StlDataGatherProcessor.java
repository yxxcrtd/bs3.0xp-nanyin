package com.joyintech.tams.settle.batch.datagather.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.settle.batch.datagather.dto.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/7/16
 * @see StlDataGatherProcessor
 * @since 1.0
 */
public abstract class StlDataGatherProcessor extends BaseProcessor<NeedOperateEntity, InvestTradeWriteDto> {

    @Override
    public InvestTradeWriteDto process(NeedOperateEntity operateEntity) throws Exception {
        InvestTradeReaderDto item = (InvestTradeReaderDto)operateEntity;
        // 根据InvestTradeReaderDto中的成交数据明细数据列表，构建一个InvestTradeWriteDto
        List<SettleTransDataDto> settleTransDataDtos = item.getSettleTransDataList();
        if(CollectionUtils.isNotEmpty(settleTransDataDtos)) {
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
        }
        return null;
    }

    protected abstract String getSecType();

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
