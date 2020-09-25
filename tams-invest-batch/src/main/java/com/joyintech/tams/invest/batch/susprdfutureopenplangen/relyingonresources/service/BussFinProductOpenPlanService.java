/*
 * 文件名：BussFinProductOpenPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.service;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.dto.BussFinProductOpenPlanDto;

/**
 * 产品开放计划组件接口
 * @author 张亮
 * @version 1.0 2018年9月3日
 * @see BussFinProductOpenPlanService
 * @since 1.0
 */
public interface BussFinProductOpenPlanService {

    /**
     * 生成开放计划一览
     *
     * @param bussFinProductOpenPlanDto 开放规则相关
     * @return 开放计划列表
     * @see
     * @since 1.0
     */
    List<FinOpenPlan> genOpenPlanList(BussFinProductOpenPlanDto bussFinProductOpenPlanDto);

}
