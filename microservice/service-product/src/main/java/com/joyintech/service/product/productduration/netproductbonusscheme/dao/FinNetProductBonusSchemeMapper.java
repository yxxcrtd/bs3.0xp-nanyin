/*
 * 文件名：FinProductModule002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productduration.netproductbonusscheme.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.service.product.productduration.netproductbonusscheme.dto.FinNetProductBonusDealDto;
import com.joyintech.service.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 净值型产品分红方案
 *
 * @author 刘伟
 * @version 1.0 2018年10月17日
 * @see FinNetProductBonusSchemeMapper
 * @since 1.0
 */
@Repository
public interface FinNetProductBonusSchemeMapper {
    /**
     * 产品代码的联想下拉框
     *
     * @param map 查询参数
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketId(Map<String,Object> map);

    /**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @see
     * @since 1.0
     */
    List<FinNetProductBonusSchemeDto> select(Map<String, Object> pageParam);

    /**
     * 产品代码的下拉框
     *
     * @param map 查询参数
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketIdList(Map<String,Object> map);

    /**
     * 查询产品简称和产品名称
     *
     * @param map 查询参数
     * @see
     * @since 1.0
     */
    FinNetProductBonusSchemeDto getAbbrAndName(Map<String,Object> map);

    /**
     * 查询分红方案编号
     *
     * @param map 查询参数
     * @see
     * @since 1.0
     */
    int selectBonuScalcFlag(Map<String,Object> map);

    /**
     * 产品是否已派发红利
     *
     * @param map 查询参数
     * @see
     * @since 1.0
     */
    String IsDividend(Map<String,Object> map);

    /**
     * 查询交易日历编号
     *
     * @param finprodId 金融产品代码
     * @return 查询结果
     * @see
     * @since 1.0
     */
    String selectCalendarId(String finprodId);

    /**
     * 验证同一除权日是否重复登录
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    int countDelDate(Map<String,Object> map);

    /**
     * 修改或双击查看界面
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    FinNetProductBonusSchemeDto selectInfo(Map<String,Object> map);

    /**
     * 查询基准日未分配余额
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    BigDecimal getTdyUnpaidIntincexp(Map<String,Object> map);

    /**
     * 查询净值参考日
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    Date getVluRefDate(Map<String,Object> map);

    /**
     * 查询参考日净值和份额
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    FinNetProductBonusSchemeDto getTdyUnitValueAndTdyShareAmt(Map<String,Object> map);

    /**
     * 查询产品是否存在有效的现金分红或红利再投交易
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    int isExistBonusDeal(Map<String, Object> map);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    String queryBonusMode(Map<String, Object> map);
    
    

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinNetProductBonusDealDto> queryBonusDetail(Map<String, Object> param);

	String queryBonusPlanId(Map<String, Object> map);

	FinNetProductBonusSchemeDto selectDetailInfo(Map<String, Object> map);

	String idBonusAmtConfirm(Map<String, Object> map);

	List<TrdFinProductDealTotal> queryAfterTrdFinProductDealTotal(FinNetProductBonusSchemeDto dto);
    /**
     * 查询产品是否已完全有效的现金分红或红利再投交易
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	BigDecimal isExceedBonusDeal(Map<String, Object> map);
	
	List<FinNetProductBonusSchemeDto> findCycleDataTableInfoByTradeId(Map<String, Object> param);
	
	List<FinNetProductBonusSchemeDto> findCycleDataTableInfo(Map<String, Object> param);
	
    String queryTradeIdByTotle(Map<String, Object> map);
}
