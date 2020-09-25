/*
 * 文件名：IAssetInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.info.entity;

import java.util.Date;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 资产基本信息Redis存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月18日
 * @see IAssetInfo
 * @since 1.0
 */
public interface IAssetInfo extends IBusiness {

    /**
     * {@inheritDoc}
     */
    @Override
    default String getKey() {
        return RedisKeyEnum.资产基本信息.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default String getField() {
        return getAssetCode();
    }

    /**
     * 取得 资产代码
     * 
     * @return 资产代码
     */
    String getAssetCode();

    /**
     * 取得 资产名称
     * 
     * @return 资产名称
     */
    String getAssetName();

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
     * 取得 系统资产大类(对应投管系统投管系统大类type8)
     * 
     * @return 系统资产大类(对应投管系统投管系统大类type8)
     */
    String getAssetType();

    /**
     * 取得 系统资产细类(对应投管系统投管系统大类标的：type6 非标的：type7)
     * 
     * @return 系统资产细类(对应投管系统投管系统大类标的：type6 非标的：type7)
     */
    String getAssetType2();

    /**
     * 取得 监管一级分类
     * 
     * @return 监管一级分类
     */
    String getType1();

    /**
     * 取得 监管二级分类
     * 
     * @return 监管二级分类
     */
    String getType2();

    /**
     * 取得 WIND一级分类
     * 
     * @return WIND一级分类
     */
    String getWindType1();

    /**
     * 取得 WIND二级分类
     * 
     * @return WIND二级分类
     */
    String getWindType2();

    /**
     * 取得 投资性质
     * 
     * @return 投资性质
     */
    String getInvestType();

    /**
     * 取得 交易日历代码
     * 
     * @return 交易日历代码
     */
    String getCalendarCode();

    /**
     * 取得 市场代码
     * 
     * @return 市场代码
     */
    String getMarketCode();

    /**
     * 取得 交易产所
     * 
     * @return 交易产所
     */
    String getTradePlace();

    /**
     * 取得 所属wind行业一级分类
     * 
     * @return 所属wind行业一级分类
     * @see
     * @since 1.0
     */
    String getIndustry();

    /**
     * 取得 所属wind行业二级分类
     * 
     * @return 所属wind行业二级分类
     * @see
     * @since 1.0
     */
    String getIndustry2();

    /**
     * 取得 所属债项评级信息
     * @return 所属债项评级信息
     */
    String getBondGradeType();

    /**
     * 取得 所属主体评级信息
     * @return 所属主体评级信息
     */
    String getBodyGradeType();

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    String getIssueType();

    /**
     * 取得 发行机构(处理过后的机构ID)
     * @return 发行机构
     */
    String getIssuerCode();

    /**
     * 取得 行内二级类型
     * @return 行内二级类型
     */
    String getType9();

    /**
     * 取得 行内三级类型
     * @return 行内三级类型
     */
    String getType10();

    /**
     * 取得 名单级别
     * @return
     */
    String getListLevel();

    /**
     * 取得 是否有关联方
     * @return
     */
    String getIsCorOrg();
}
