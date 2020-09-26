/*
 * 文件名：CalculationCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.calculation.dao;

import com.joyintech.tams.common.base.entity.BokAmoConfig;
import com.joyintech.tams.valuation.batch.common.calculation.entity.ExchangeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhiang
 * @version 1.0 2018年5月31日
 * @see CalculationCommonMapper
 * @since 1.0
 */
@Repository
public interface CalculationCommonMapper {

//	/**
//	 * 汇率查询
//	 * @param 基础币种
//	 * @param 报价币种
//	 * @param 交易日
//	 * @return
//	 */
//    List<ExchangeInfo> queryExchangeRate(@Param("baseCcy") String 基础币种, @Param("tradeCcy") String 报价币种, @Param("tradeDate") Date 交易日);

    /**
     * 
     * 查询上一工作日
     * @param cdate 查询日期
     * @param isHoliday 是否节假日（0代表非节假日）
     * @param calendarId 交易日历
     * @return 日期
     * @see
     * @since 1.0
     */
    Date getCDate(@Param("cdate") Date cdate,@Param("isHoliday") String isHoliday,@Param("calendarId") String calendarId);
    /**
     * 
     * 是否节假日
     * @param cdate 查询日期
     * @param calendarId 交易日历
     * @return 是否节假日（0：工作日；其他：节假日）
     * @see
     * @since 1.0
     */
    String getIsHoliday(@Param("cdate") Date cdate,@Param("calendarId") String calendarId);
    /**
     * 查询上一开放日
     * @param finprodId 金融产品代码
     * @param cdate 查询日期
     * @return 上一开放日
     * @see
     * @since 1.0
     */
    Date getLastOpenDate(@Param("finprodId") String finprodId,@Param("cdate") Date cdate);
    
	/**
	 * 
	 * 摊销重算配置查询<一句话功能简述> <功能详细描述>
	 * 
	 * @param booksetId
	 * @param busiId
	 * @param cdate
	 * @return
	 * @see
	 * @since 1.0
	 */
    List<BokAmoConfig> getBokAmoConfig(@Param("booksetId") String booksetId, @Param("busiId") String busiId,
			@Param("cdate") Date cdate);
    /**
     * add by wujie on 20190730
     * @param map
     * @return
     */
    int tradeDealAccountCount(Map<String,Object> map);
}
