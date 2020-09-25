/*
 * 文件名：InterfBillInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ibillinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.ibillinfo.entity.InterfBillInEntity;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillOutEntity;

/**
 * 单据信息处理查询-lc0001成立单据；lc0002到期单据
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see InterfBillInfoMapper
 * @since 1.0
 */
@Repository
public interface InterfBillInfoMapper {

    /**
     * 根据市场代码获取金融产品代码
     * 
     * @param entity 接口输入
     * @return InterfFinProdDto 金融产品信息
     */
    InterfBillInEntity selectFinProdInfo(InterfBillInEntity entity);

    /**
     * 从开放计划表查询起息日
     * 
     * @param entity 查询条件
     * @return 起息日
     */
    String selectVdate(InterfBillInEntity entity);

    /**
     * 查询成立单据是否存在
     * 
     * @param entity 接口输入
     * @return String 汇总交易编号
     */
    String selectTotleTradeId(InterfBillInEntity entity);

    /**
     * 获取分行名称
     * 
     * @param acctBranchId 分行代码
     * @return 分行名称
     * @see
     * @since 1.0
     */
    String selectBranchgName(@Param("acctBranchId") String acctBranchId);

    /**
     * 查询金额和收益率
     * 
     * @param entity 查询条件
     * @return 金额和收益率
     */
    List<InterfBillOutEntity> selectAmtYield(InterfBillInEntity entity);
}
