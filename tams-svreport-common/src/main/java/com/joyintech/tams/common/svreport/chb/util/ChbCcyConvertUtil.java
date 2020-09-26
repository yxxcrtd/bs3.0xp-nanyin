/*
 * 文件名：ChbDeadlineUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年12月2日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.common.svreport.chb.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbMstExchangeInfo;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.service.ChbCcyConvertService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 币种换算工具类
 * @author lingxq
 * @version 1.0 2019年02月10日
 * @see ChbCcyConvertUtil
 * @since 1.0
 */
public class ChbCcyConvertUtil {
	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ChbCcyConvertUtil.class);
    
    /**
     * 折算人民币金额
     * 
     * @param ccy 币种
     * @param amt 金额
     * @param settDate 交易支付日
     * @param rateMap 汇率集合
     * @return 折算人民币金额
     * @see
     * @since 1.0
     */
    @Deprecated
    public static BigDecimal changeCnyMoney(String ccy, BigDecimal amt, Date settDate,
                                       Map<String, BigDecimal> rateMap) {
        BigDecimal ccyAmt = BigDecimal.ZERO;
        if (StringUtil.isEmpty(ccy) || amt == null) {
            return ccyAmt;
        }
        if (StringUtil.isNotEmpty(ccy) && ccy.length() == 2) {
            ccy = SysDataUtil.getNameFromDataDict(DictCode.币种_中债, ccy);
        }
        if (DictCodeListExt1.币种_中债_人民币.getValue().equals(ccy)) {
            ccyAmt = ccyAmt.add(amt);
        }
        else if (StringUtil.isNotEmpty(ccy) && StringUtil.isNotEmpty(amt)) {
            String key = DateUtil.format(settDate) + StringUtil.SPACE + ccy + CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE) + DictCodeListExt1.币种_中债_人民币.getValue();
			// 汇率集合为空时，查询汇率表取最新汇率
			if (rateMap == null || rateMap.size() < 1 || !StringUtil.isNotEmpty(rateMap.get(key))) {
				getExchange(DictCodeListExt1.币种_中债_人民币.getValue(), settDate, rateMap);
			}
            // 折算人民币金额= 币种汇率*币种金额
            if (rateMap != null&&StringUtil.isNotEmpty(rateMap.get(key))) {
                ccyAmt = ccyAmt.add(amt.multiply(rateMap.get(key)));
            }
            else {
                ccyAmt = ccyAmt.add(amt);
            }
        }
        return ccyAmt;
    }
    
    /**
     * 获取汇率行情
     * 
     * @param ccy 折算币种
     * @param date 日期
     * @param map 汇率map
     * @see
     * @since 1.0
     */
    public static void getExchangeRate(String ccy, Date date,Map<String,BigDecimal> map) {
        ChbCcyConvertService chbCcyConvertService = SpringContextUtil.getBean(
        		ChbCcyConvertService.class);
        List<ChbMstExchangeInfo> list = chbCcyConvertService.getExchangerate(ccy, date);
        for (ChbMstExchangeInfo chbExchangerateDto : list) {
            map.put(DateUtil.format(date) + StringUtil.SPACE + chbExchangerateDto.getCcyOriginal(),
                chbExchangerateDto.getExchangePrice());
        }
    }
    
    /**
     * @Description 根据汇率换算金额
     * @param ccyOriginal 原币种
     * @param ccyConvert 折算币种
     * @param settDate 交易支付日
     * @param rateMap  汇率集合(key:日期+折算币种+汇率计算规则+原币种，value:汇率)
     * @param exchangeSource  汇率计算规则(01:开盘价，02:收盘价，03:最高价，04:最低价，05:中间价)
     * @return BigDecimal 折算后金额
     */
	public static BigDecimal exchangeMoney(String ccyOriginal, String ccyConvert, Date settDate, BigDecimal amt,
			Map<String, BigDecimal> rateMap, String exchangeSource) {
		BigDecimal ccyAmt = BigDecimal.ZERO;
		if (StringUtil.isEmpty(ccyOriginal) || StringUtil.isEmpty(ccyConvert) || amt == null || settDate == null
				|| exchangeSource == null) {
			return ccyAmt;
		}
		if (rateMap == null) {
			rateMap = new HashMap<String, BigDecimal>();
		}
		// 原币种与折算币种相同时
		if (ccyOriginal.equals(ccyConvert)) {
			ccyAmt = ccyAmt.add(amt);
		} else {
			String key = DateUtil.format(settDate) + StringUtil.SPACE + ccyOriginal + exchangeSource + ccyConvert;
			// 汇率集合为空时，查询汇率表取最新汇率
			if (rateMap == null || rateMap.size() < 1 || !StringUtil.isNotEmpty(rateMap.get(key))) {
				getExchange(ccyConvert, settDate, rateMap);
			}
			// 折算人民币金额=币种汇率*币种金额
			if (StringUtil.isNotEmpty(rateMap.get(key))) {
				ccyAmt = ccyAmt.add(amt.multiply(rateMap.get(key)));
			} else {
				ccyAmt = ccyAmt.add(amt);
			}
		}
		return ccyAmt;
	}
	
	/**
	 * @Description 获取汇率封装成map
	 * @param ccyConvert 折算币种
	 * @param date 汇率日期
	 * @param map 汇率集合
	 * @version 1.0 2019年3月13日
	 */
	private static void getExchange(String ccyConvert, Date date,Map<String,BigDecimal> map) {
        ChbCcyConvertService chbCcyConvertService = SpringContextUtil.getBean(
        		ChbCcyConvertService.class);
        // 根据折算币种和日期查询最新汇率
        List<ChbMstExchangeInfo> list = chbCcyConvertService.getExchangerate(ccyConvert, date);
        if(date==null || CollectionUtils.isEmpty(list)){
        	logger.warn("未取到当日汇率");
        	return;
        }
        // 将汇率封装成map
        for (ChbMstExchangeInfo chbExchangerateDto : list) {
        	if(!DateUtil.format(date).equals(DateUtil.format(chbExchangerateDto.getPublishDate()))){
        		logger.warn("未取到当日汇率");
        	}
        	// 开盘价
            map.put(DateUtil.format(date) + StringUtil.SPACE + chbExchangerateDto.getCcyOriginal() + "01" +chbExchangerateDto.getCcyConvert(),
                    chbExchangerateDto.getOpenPrice());
            // 收盘价
            map.put(DateUtil.format(date) + StringUtil.SPACE + chbExchangerateDto.getCcyOriginal() + "02" +chbExchangerateDto.getCcyConvert(),
                    chbExchangerateDto.getClosePrice());
            // 最高价
            map.put(DateUtil.format(date) + StringUtil.SPACE + chbExchangerateDto.getCcyOriginal() + "03" +chbExchangerateDto.getCcyConvert(),
                    chbExchangerateDto.getHighPrice());
            // 最低价
            map.put(DateUtil.format(date) + StringUtil.SPACE + chbExchangerateDto.getCcyOriginal() + "04" +chbExchangerateDto.getCcyConvert(),
                    chbExchangerateDto.getLowPrice());
            // 中间价
            map.put(DateUtil.format(date) + StringUtil.SPACE + chbExchangerateDto.getCcyOriginal() + "05" +chbExchangerateDto.getCcyConvert(),
                    chbExchangerateDto.getMidPrice());
        }
    }
}
