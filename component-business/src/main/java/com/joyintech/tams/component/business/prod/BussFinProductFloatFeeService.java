/*
 * 文件名：BussFinProductFloatFeeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleKey;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.component.business.prod.dto.FinProductFloatFeeDto;

/**
 * 浮动管理费业务组件
 *
 * @author 张亮
 * @version 1.0 2018年10月11日
 * @see BussFinProductFloatFeeService
 * @since 1.0
 */
public interface BussFinProductFloatFeeService {

    /**
     * 默认实体名
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductFloatFeeDto";

    /**
     * 查询费用品种信息
     *
     * @param map 参数
     * @return 费用品种信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map);

    /**
     * 根据费用代码查询费用基本信息
     *
     * @param feeId 费用代码
     * @return 费用基本信息
     * @see
     * @since 1.0
     */
    MstFeeinfo queryMstFeeinfoByFeeId(String feeId);

    /**
     * 删除 <功能详细描述>
     *
     * @param cashId 现金流代码
     * @return DB变更个数
     * @see
     * @since 1.0
     */
    int delete(String cashId);

    /**
     * 删除-报价管理时用 <功能详细描述>
     *
     * @param key 主键
     * @return DB变更个数
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(FinFloatCalcRuleKey key);

    /**
     * 查询浮动管理费信息
     *
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     * @see
     * @since 1.0
     */
    List<FinProductFloatFeeDto> queryFloatFeeDetailByFinprodId(String finprodId, String effDate);

    /**
     * 查询浮动管理费信息
     *
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     * @see
     * @since 1.0
     */
    BussFinProductFloatFeeDto queryFloatFeeInfoByFinprodId(String finprodId, String effDate);
    
    /**
     * 查询浮动管理费信息
     *
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     * @see
     * @since 1.0
     */
    List<BussFinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId(String finprodId, String effDate);

    /**
     * 根据工作流代码查询浮动管理费
     *
     * @param workflowId 工作流代码
     * @param beanName 浮动管理费
     * @return 浮动管理费
     * @see
     * @since 1.0
     */
    BussFinProductFloatFeeDto productFloatFeeInfoQueryByWflId(String workflowId, String beanName);

    /**
     * 根据费用信息生成计提计划
     *
     * @param finProductFeeInfoDto计提计划
     * @return 计提计划
     * @see
     * @since 1.0
     */
    List<FinCashPlan> generateFloatFeePlan(BussFinProductFloatFeeDto finProductFloatFeeDto);
}
