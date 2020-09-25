package com.joyintech.service.asset.common.dao;

import com.joyintech.tams.common.base.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 自动加券
 * 更新业务表
 */
@Repository
public interface AutoBondCouponMapper {

    /**
     * 获取需要更新业务表的市场代码
     * @param resultList
     * @return
     */
    List<FinProduct> getIsUpdateFinprodMarketId(@Param("resultList") List<String> resultList);

    InterfPayhsrcsecinfo selectWindBondAssetBySecId(@Param("secid") String secid);

    /**
     * wind债券行权计划
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    List<InterfPayhsrcsecoption>  selectWindBondExercise(@Param("secid") String secid);

    /**
     * 债券付息计划
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    List<InterfPayhsrcsecschedule> selectWindBondSchedule(@Param("secid") String secid,@Param("maxTradeDate") Date maxTradeDate);

    /**
     * 获取WIND_浮息债报价表(债券)
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    List<InterfFloatcouprate> selectInterfFloatingcouponsrate(@Param("secid") String secid);

    /**
     * 获取交易日
     * @param finprodId
     * @return
     */
    Date getMaxTradeDate(@Param("finprodId") String finprodId);

    List<FinCashPlan> selectFinCashPlan(@Param("cashType1") String cashType1,@Param("cashType2") String cashType2,@Param("maxTradeDate") Date maxTradeDate, @Param("finprodId") String finprodId);

    void updateMdate(FinProduct finProduct);

    void deleteFinExercisePlan(@Param("finprodId") String finprodId, @Param("plateFormDate")Date plateFormDate);

    void deleteFinCashPlan(@Param("finprodId") String finprodId, @Param("plateFormDate")Date plateFormDate);

    List<String> selectSecIdForUpdate();
}
