package com.joyintech.tams.invest.common.risktrial.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;


@Service
public class TradeCommUtil  {

	public static final List<String> 拆借_回购 = Arrays.asList(DictCodeListExt1.金融产品类型_投管用_拆入.getValue(),
            DictCodeListExt1.金融产品类型_投管用_拆出.getValue(),
            DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),
            DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue(),DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());

    public static final List<String> 拆出_卖出_赎回_逆回购_行权 = Arrays.asList(DictCodeList.交易方向_卖出.getValue(),
        // 一体化对应etliu start
//            DictCodeList.交易方向_赎回.getValue(),DictCodeList.交易方向_行权.getValue(),
        DictCodeList.交易方向_赎回.getValue(),DictCodeList.交易方向_行权_回售.getValue(), DictCodeList.交易方向_行权_赎回.getValue(),
            // 一体化对应etliu end
            DictCodeList.交易方向_赎回确认.getValue(), DictCodeList.回购拆借方向_拆出.getValue(),DictCodeList.回购拆借方向_逆回购.getValue());

 
    public static void tradeAmountDeal(InvestTradeTrialInput inputDto) {
    	Assert.notNull(inputDto, "参数不能为null");
    	Assert.isTrue(StringUtil.isNotEmpty(inputDto.getValDate()), "试算日期不能为空");
        Assert.isTrue(StringUtil.isNotEmpty(inputDto.getTradeDirection()), "交易方向不能为空");
        Assert.isTrue(StringUtil.isNotEmpty(inputDto.getAssetType()), "资产类型不能为空");
        Assert.isTrue(StringUtil.isNotEmpty(inputDto.getPortfolioCode()), "组合代码不能为空");
        if(!拆借_回购.contains(inputDto.getAssetType()))
        	Assert.isTrue(StringUtil.isNotEmpty(inputDto.getPortfolioCode()), "除拆借与回购之外,资产代码不能为空");
        if(拆出_卖出_赎回_逆回购_行权.contains(inputDto.getTradeDirection())){
        	inputDto.setTradeAmount(ArithmeticUtil.multiply(inputDto.getTradeAmount(), new BigDecimal(-1)));
        }
    }
       
}
