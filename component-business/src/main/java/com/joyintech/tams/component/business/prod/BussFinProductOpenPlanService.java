/*
 * 文件名：BussFinProductOpenPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.component.business.prod.dto.BussFinProductOpenPlanDto;

/**
 * 产品开放计划组件接口
 * @author 张亮
 * @version 1.0 2018年9月3日
 * @see BussFinProductOpenPlanService
 * @since 1.0
 */
public interface BussFinProductOpenPlanService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductOpenPlanDto";

    /**
     * 根据现金流代码查询开放计划信息
     *
     * @param cashId 现金流代码
     * @return 开放计划信息
     * @see
     * @since 1.0
     */
    BussFinProductOpenPlanDto search(String cashId);

    /**
     * 删除开放规则信息
     * @param cashId 现金流id
     */
    void delete(String cashId);

    /**
     * 添加开放规则信息
     * @param bussFinProductOpenPlanDto 开放规则信息
     */
    void insert(BussFinProductOpenPlanDto bussFinProductOpenPlanDto);

    /**
     * 修改开放规则信息
     * @param bussFinProductOpenPlanDto 开放规则信息
     */
    void update(BussFinProductOpenPlanDto bussFinProductOpenPlanDto);

    /**
     * 判断首次开放日是否是节假日
     *
     * @param bussFinProductOpenPlanDto 开放信息
     * @return 是否是节假日
     * @see
     * @since 1.0
     */
    boolean checkFirstOpenDateIsHoliday(BussFinProductOpenPlanDto bussFinProductOpenPlanDto);

    /**
     * 生成开放计划一览
     *
     * @param bussFinProductOpenPlanDto 开放规则相关
     * @return 开放计划列表
     * @see
     * @since 1.0
     */
    List<FinOpenPlan> genOpenPlanList(BussFinProductOpenPlanDto bussFinProductOpenPlanDto);

    /**
     * 根据金融产品代码查询开放信息
     *
     * @param finprodId 金融产品代码
     * @return 开放信息
     * @see
     * @since 1.0
     */
    List<BussFinProductOpenPlanDto> queryOpenPlanInfoByFinprodId(String finprodId);

    /**
     * 根据金融产品代码查询开放计划列表
     *
     * @param finprodId 金融产品代码
     * @return 开放计划列表
     * @see
     * @since 1.0
     */
    List<FinOpenPlan> queryOpenPlanListByFinprodId(String finprodId);
}
