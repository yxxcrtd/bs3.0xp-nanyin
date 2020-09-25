/*
 * 文件名：YieldComponent.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.yield;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.DateUtil.DurationType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.yield.dao.YieldMapper;
import com.joyintech.service.interf.yield.entity.YiledEntity;

/**
 * 计算年化收益率和涨跌幅计算
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see YieldComponent
 * @since 1.0
 */
@Component
public class YieldComponent {

    /**
     * 查询净值信息
     */
    @Autowired
    private YieldMapper yieldMapper;

    /**
     * 计算涨跌幅和年化收益率
     * 
     * @param prodId 金融产品代码
     * @param netVal 累计净值
     * @param cDate 净值日期
     * @param type Z-涨幅；Y-年化涨幅,L-历史
     * @param termValue 天数：1-日；7-周；30-月；90-季；180-半年；360-年
     * @return 涨跌幅和年化收益率
     * @throws ParseException 解析异常
     */

    public String getYield(@NotNull String prodId, @NotNull BigDecimal netVal,
                           @NotNull String cDate, @NotNull String type, String termValue)
        throws ParseException {
        // 将天数字符串转为数字
        int num = 0;
        if (StringUtil.isNotEmpty(termValue)) {
            num = StringUtil.integerValueOf(termValue);
        }
        BigDecimal yield = null;
        String vDate = "";// 开始日
        // 将日期转为日期工具能转换的格式 yyyy-MM-dd
        cDate = DateUtil.format(DateUtil.parse(cDate), DateUtil.DEFAULT_DATE_FORMAT_EN);
        Map<String, Object> map = new HashMap<>();
        // 若是计算历史涨跌幅，则需要查询本期天数
        if (InterfDictCodeList.涨幅_历史涨幅.getValue().equals(type)) {
            int termDays = yieldMapper.selectTermDay(prodId);
            vDate = DateUtil.addDay(cDate, -termDays);
        }
        else if (num <= 7) {// 一天或一周七天 前的日期
            vDate = DateUtil.addDay(cDate, -num);
        }
        else {// 月，季，半年，年 前的日期
            num = num / 30;
            vDate = DateUtil.format(DateUtil.addMonthDateStr(cDate, -num),
                DateUtil.DEFAULT_OTHER_FORMAT);
        }
        // 日期格式化 yyyyMMdd
        vDate = DateUtil.formatDate(vDate);
        // 获取前一个日期的累计净值的净值信息
        map.put("vDate", vDate);
        map.put("prodId", prodId);
        YiledEntity yiledEntity = yieldMapper.getYileEntity(map);
        // 判断小于开始日的净值是否存在
        if (!StringUtil.isNotEmpty(yiledEntity)
            || StringUtil.isEmpty(yiledEntity.getFinprodId())) {
            return StringUtil.EMPTY;
        }
        // 获取前一个日期的累计净值
        BigDecimal vNetVal = yiledEntity.getvTotalNetVal();
        // 两个日期直接间隔天数
        long btwnDays = DateUtil.getBetweenDate(yiledEntity.getValDate(), cDate, DurationType.天);

        // yield =(netVal- vNetVal)/netVal * 100 因为库表净值精度是14
        yield = netVal.subtract(vNetVal).divide(vNetVal, 14, BigDecimal.ROUND_DOWN).multiply(
            new BigDecimal(100));

        // 如果是年化
        if (btwnDays != 0 && (InterfDictCodeList.涨幅_历史涨幅.getValue().equals(type)
                              || InterfDictCodeList.涨幅_年涨幅.getValue().equals(type))) {
            // yield= yield / btwnDays * 365
            yield = yield.divide(new BigDecimal(btwnDays), 14, BigDecimal.ROUND_DOWN).multiply(
                new BigDecimal(365));
        }

        yield = yield.setScale(2, BigDecimal.ROUND_DOWN);// 截取保留两位小数
        return StringUtil.numberToString(yield);
    }

    /**
     * 获取本期天数
     * 
     * @param finProdId 金融产品代码
     * @return 本期天数
     * @see
     * @since 1.0
     */
    public int getTermDay(@NotNull String finProdId) {
        return yieldMapper.selectTermDay(finProdId);
    }

    /**
     * 计算 成立以来涨跌幅 （T日净值-1）*100%
     * 
     * @param totalNetValue 累计净值
     * @return 成立以来涨跌
     * @see
     * @since 1.0
     */
    public String sinceEstablishIncrease(BigDecimal totalNetValue) {
        String sinceEstablishIncrease = "";
        if (StringUtil.isNotEmpty(totalNetValue)) {
            sinceEstablishIncrease = StringUtil.numberToString(
                totalNetValue.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)));
        }
        return sinceEstablishIncrease;

    }
}
