/*
 * 文件名：ChannelAssetQueryService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月25日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.channel.feepay.service;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.channel.ChannelFeeInfoDto;
import org.apache.commons.collections.list.TransformedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.channel.feepay.dao.ChannelFeePayMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.channel.ChannelFeePayDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ProductTradeInfoDto;

/**
 * 通道资产查询service 〈功能详细描述〉
 * 
 * @author yangjm
 * @version 1.0 2019年3月18日
 * @see ChannelFeePayService
 * @since 1.0
 */
@Service
public class ChannelFeePayService extends BaseService<ChannelFeePayDto> {

    @Autowired
    private ChannelFeePayMapper channelFeePayMapper;

    public Page<ChannelFeePayDto> findPage(int page, int length, Map<String, Object> param) {
        param.put("BT_18", DictCodeList.业务类型_产品费用交易.getValue());
        param.put("SINGLE_FEE_TYPE_03", DictCodeListExt1.一次性费用类型_通道费.getValue());
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("YESNO_N", DictCodeList.是否_否.getValue());
        if (StringUtil.isNotEmpty(param.get("channelId"))) {
            param.put("channelId", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("channelId")));
        }
        else {
            param.put("channelId", null);
        }
        if (StringUtil.isNotEmpty(param.get("portfolioId"))) {
            param.put("portfolioId", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("portfolioId")));
        }
        else {
            param.put("portfolioId", null);
        }
        return queryForPageForSelectReturn(page, length, () -> channelFeePayMapper.findPage(param));
    }

    public Page<SelectOptionDto> findChannelId(int page, int length, Map<String, Object> param) {
        param.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        param.put("Y", DictCodeList.是否_是.getValue());
        return queryForPageForSelectReturn(page, length, () -> channelFeePayMapper.findChannelId(param));
    }

    public ChannelFeePayDto searchTheoryAmt(Map<String, Object> param) {
        param.put("SINGLE_FEE_TYPE_03", DictCodeListExt1.一次性费用类型_通道费.getValue());
        param.put("N", DictCodeList.是否_否.getValue());
        param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("C12", DictCodeListExt1.现金流二级类型_通道管理费.getValue());
        param.put("C13", DictCodeListExt1.现金流二级类型_通道托管费.getValue());
        param.put("C14", DictCodeListExt1.现金流二级类型_通道其他费.getValue());
        param.put("B09", DictCodeListExt1.一次性费用类型_通道费.getValue());
        return channelFeePayMapper.searchTheoryAmt(param);
    }

    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private FinProductMapper finProductMapper;

    public ProductTradeInfoDto findAllTrade(String tradeId) {
        ProductTradeInfoDto baseDto = new ProductTradeInfoDto();
        baseDto.setTrdProductDeals(new ArrayList<TrdProductDeal>());
        baseDto.setStlPayOrders(new ArrayList<StlPayOrdersDto>());
        baseDto.setTrdProductDealAdds(new ArrayList<TrdProductDealAdd>());
        baseDto.setPaTrdProductDeals(new ArrayList<PaTrdProductDeal>());
        WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
        wflTrdProductDeal.setTradeId(tradeId);
        wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        List<WflTrdProductDeal> wflTrdProductDealList = new ArrayList<>();
        wflTrdProductDealList = channelFeePayMapper.selectWflTrdProductDeal(wflTrdProductDeal);
        if(wflTrdProductDealList!=null && wflTrdProductDealList.size()>0) {
            for (int i = 0; i < wflTrdProductDealList.size(); i++) {
                WflTrdProductDeal wflTrdProductDealDto = new WflTrdProductDeal();
                wflTrdProductDealDto = wflTrdProductDealList.get(i);
                if (wflTrdProductDealDto != null) {
                    TrdProductDeal deal = new TrdProductDeal();
                    BeanUtil.copyPropertiesIgnoreNull(deal, wflTrdProductDealDto);
                    baseDto.getTrdProductDeals().add(deal);
                    StlPayOrdersDto stlPayOrdersDto = new StlPayOrdersDto();
                    BeanUtil.copyPropertiesIgnoreNull(stlPayOrdersDto, channelFeePayMapper.selectAllOrders(deal.getTradeId()).get(0));
                    baseDto.getStlPayOrders().add(stlPayOrdersDto);
                    baseDto.getTrdProductDealAdds().add(channelFeePayMapper.selectAllDealAdds(Arrays.asList(deal)).get(0));
                    baseDto.getPaTrdProductDeals().add(channelFeePayMapper.selectAllPaDeals(Arrays.asList(deal)).get(0));
                }
            }
        }
//        baseDto.setTrdProductDealAdds(channelFeePayMapper.selectAllDealAdds(baseDto.getTrdProductDeals()));
//        baseDto.setPaTrdProductDeals(channelFeePayMapper.selectAllPaDeals(baseDto.getTrdProductDeals()));
//        baseDto.setStlPayOrders(channelFeePayMapper.selectAllOrders(tradeId));
//        baseDto.setPtlTrades(channelFeePayMapper.findPtlTrades(tradeId));
        return baseDto;
    }

    public ChannelFeePayDto getEffectiveData(String tradeId) {
        ChannelFeePayDto result = new ChannelFeePayDto();
        result.setFeeInfoList(new ArrayList<ChannelFeeInfoDto>());
        List<TrdProductDeal> trdProductDealList = channelFeePayMapper.selectTrdProductDeal(tradeId);
//        TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(tradeId);
        if (trdProductDealList != null && trdProductDealList.size()>0) {
            BeanUtil.copyPropertiesIgnoreNull(result, trdProductDealList.get(0));
            List<TrdProductDealAdd> adds = channelFeePayMapper.selectAllEfDealAdds(trdProductDealList);
            List<PaTrdProductDeal> paDeals = channelFeePayMapper.selectAllEfPaDeals(trdProductDealList);
            List<StlPayOrders> orders = new ArrayList<>();
            FinProduct product = finProductMapper.selectByPrimaryKey(result.getFinprodId());
            if (product != null) {
                result.setFinprodMarketId(product.getFinprodMarketId());
                result.setChannelName(product.getFinprodName());
            }
//            if (adds != null && !adds.isEmpty()) {
//                result.setPortfolioId(adds.get(0).getPortfolioId());
//            }
            BigDecimal tradeAmt = new BigDecimal(0);
            for (TrdProductDeal dto : trdProductDealList) {
                if(dto.getTradeAmt()!=null) {
                    tradeAmt = dto.getTradeAmt().add(tradeAmt);
                }
                orders.add(channelFeePayMapper.selectAllEfOrders(dto.getTradeId()).get(0));
            }
            BigDecimal theoryInt = new BigDecimal(0);
            if (paDeals != null && !paDeals.isEmpty()) {
                for(PaTrdProductDeal dto:paDeals){
                    if(dto.getTheoryInt()!=null) {
                        theoryInt = dto.getTheoryInt().add(theoryInt);
                    }
                }
            }
            result.setTheoryInt(theoryInt);
            result.setTradeAmt(tradeAmt);
            result.setOrders(orders);

            Map<String, Object> param = new HashMap<>();
            List<SelectOptionDto> list = restTemplate.postForList(
                    ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/selectPortfolioId",param, SelectOptionDto.class);
            for(int i=0;i<trdProductDealList.size();i++){
                ChannelFeeInfoDto channelFeeInfoDto = new ChannelFeeInfoDto();
                channelFeeInfoDto.setPortfolioId(adds.get(i).getPortfolioId());
                channelFeeInfoDto.setPortFolioName(channelFeePayMapper.findPortfolioName(channelFeeInfoDto.getPortfolioId()));
                channelFeeInfoDto.setPayAmt(paDeals.get(i).getTheoryInt());
                channelFeeInfoDto.setActPayAmt(trdProductDealList.get(i).getTradeAmt());
                result.getFeeInfoList().add(channelFeeInfoDto);
            }
        }
        result.setTradeId(trdProductDealList.get(0).getTotleTradeId());
        return result;
    }

    public String findChannelFeePaied(Map<String, Object> param) {
        param.put("N", DictCodeList.是否_否.getValue());
        param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        if(StringUtil.isNotEmpty(param.get("mdate"))){
            param.put("mdate", new Date((Long) param.get("mdate")));
        }
        return channelFeePayMapper.findChannelFeePaied(param);
    }
    public String getCollectType(Map<String, Object> map) {
        return channelFeePayMapper.getCollectType(map);
    }

    public String getTradeId(String totleTradeId,String portfolioId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("totleTradeId",totleTradeId);
        map.put("portfolioId",portfolioId);
        return channelFeePayMapper.getTradeId(map);
    }

    public String findPortfolioName(String portfolioId) {
       return channelFeePayMapper.findPortfolioName(portfolioId);
    }

    public List<String> getTradeId1(String totleTradeId) {
        return channelFeePayMapper.getTradeId1(totleTradeId);
    }

    public int queryTrade(String totleTradeId) {
        return channelFeePayMapper.queryTrade(totleTradeId);
    }
}
