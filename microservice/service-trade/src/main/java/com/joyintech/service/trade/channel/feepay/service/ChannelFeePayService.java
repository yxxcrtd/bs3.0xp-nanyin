/*
 * 文件名：ChannelAssetQueryService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月25日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.channel.feepay.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
        WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
        wflTrdProductDeal.setTradeId(tradeId);
        wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        wflTrdProductDeal = wflTrdProductDealMapper.selectByPrimaryKey(wflTrdProductDeal);
        if (wflTrdProductDeal != null) {
            TrdProductDeal deal = new TrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(deal, wflTrdProductDeal);
            baseDto.setTrdProductDeals(Arrays.asList(deal));
        }

        baseDto.setTrdProductDealAdds(channelFeePayMapper.selectAllDealAdds(baseDto.getTrdProductDeals()));
        baseDto.setPaTrdProductDeals(channelFeePayMapper.selectAllPaDeals(baseDto.getTrdProductDeals()));
        baseDto.setStlPayOrders(channelFeePayMapper.selectAllOrders(tradeId));
        baseDto.setPtlTrades(channelFeePayMapper.findPtlTrades(tradeId));
        return baseDto;
    }

    public ChannelFeePayDto getEffectiveData(String tradeId) {
        ChannelFeePayDto result = new ChannelFeePayDto();
        TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(tradeId);
        if (trdProductDeal != null) {
            BeanUtil.copyPropertiesIgnoreNull(result, trdProductDeal);
            List<TrdProductDealAdd> adds = channelFeePayMapper.selectAllEfDealAdds(Arrays.asList(new TrdProductDeal[]{trdProductDeal}));
            List<PaTrdProductDeal> paDeals = channelFeePayMapper.selectAllEfPaDeals(Arrays.asList(new TrdProductDeal[]{trdProductDeal}));
            List<StlPayOrders> orders = channelFeePayMapper.selectAllEfOrders(tradeId);
            FinProduct product = finProductMapper.selectByPrimaryKey(result.getFinprodId());
            if (product != null) {
                result.setFinprodMarketId(product.getFinprodMarketId());
                result.setChannelName(product.getFinprodName());
            }
            if (adds != null && !adds.isEmpty()) {
                result.setPortfolioId(adds.get(0).getPortfolioId());
            }
            if (paDeals != null && !paDeals.isEmpty()) {
                result.setTheoryInt(paDeals.get(0).getTheoryInt());
            }
            result.setOrders(orders);
        }
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
}
