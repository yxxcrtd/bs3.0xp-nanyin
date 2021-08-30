/*
 * 文件名：AssetIntdateCarryoverMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.valuation.batch.cashflowdiscount.dao;

import com.joyintech.tams.common.base.entity.ModCurveResult;
import com.joyintech.tams.common.base.entity.ModRiskPremium;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.ModCurveDto;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.ModCurveResultDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 现金流贴现批处理
 * @author zznn
 * @date 20210706
 */
@Repository
public interface CashFlowDiscountMapper {

    /**
     * 查找贴现法曲线配置
     * @param param
     * @return
     */
    List<ModCurveDto> getAllModCurve(Map<String,Object> param);

    /**
     * 查找曲线代码
     * @param param
     * @return
     */
    List<String> getCurveCode(Map<String,Object> param);

    /**
     * 查找溢价信息
     * @param param
     * @return
     */
    ModRiskPremium getModRiskPremium(Map<String,Object> param);

    /**
     * 查找评级结果
     * @param curveCode
     * @return
     */
    String getGradeResult(@Param("curveCode") String curveCode);

    /**
     * 登录溢价
     * @param list
     */
    void insertBatchRiskPremium(List<ModRiskPremium> list);

    /**
     * 登录估值结果
     * @param list
     */
    void insertBatchCurveResult(List<ModCurveResult> list);

    /**
     * 登录估值结果明细
     * @param list
     */
    void insertBatchCurveResultDetail(List<ModCurveResultDetail> list);

    /**
     * 删除溢价
     * @param map
     */
    void deleteRiskPremium(Map<String,Object> map);

    /**
     * 删除估值结果
     * @param map
     */
    void deleteCurveResult(Map<String,Object> map);

    /**
     * 删除估值明细
     * @param map
     */
    void deleteCurveResultDetail(Map<String,Object> map);
}
