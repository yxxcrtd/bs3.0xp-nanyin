/*
 * 文件名：IProdInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.info.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 产品基本信息Redis存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月18日
 * @see IProdInfo
 * @since 1.0
 */
public interface IProdInfo extends IBusiness {

    /**
     * {@inheritDoc}
     */
    @Override
    default String getKey() {
        return RedisKeyEnum.产品基本信息.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default String getField() {
        return getProdId();
    }

    /**
     * 取得 产品代码
     * 
     * @return 产品代码
     */
    String getProdId();

    /**
     * 取得 产品名称
     * 
     * @return 产品名称
     */
    String getProdName();

    /**
     * 取得 投资性质
     * 
     * @return 投资性质
     */
    String getInvestType();

    /**
     * 取得 开放类型
     * 
     * @return 开放类型
     */
    String getIssueMode();

    /**
     * 取得 开放频率
     * 
     * @return 开放频率
     */
    String getOpenType();

    /**
     * 取得 募集方式
     * 
     * @return 募集方式
     */
    String getRaiseType();

    /**
     * 取得 产品收益类型
     * 
     * @return 产品收益类型
     */
    String getProdType();

    /**
     * 取得 产品业绩比较基准
     * 
     * @return 产品业绩比较基准
     */
    BigDecimal getPcbAmt();

    /**
     * 取得 交易日历代码
     * 
     * @return 交易日历代码
     */
    String getCalendarCode();

    /**
     * 取得 起息日
     * 
     * @return 起息日
     */
    Date getVdate();

    /**
     * 取得 到期日
     * 
     * @return 到期日
     */
    Date getMdate();

    /**
     * 取得 投资经理姓名
     * 
     * @return 投资经理姓名
     */
    String getInvestMgr();

    /**
     * 取得 是否启动风险
     * 
     * @return 是否启动风险
     * @see
     * @since 1.0
     */
    String getRiskEnabled();
}
