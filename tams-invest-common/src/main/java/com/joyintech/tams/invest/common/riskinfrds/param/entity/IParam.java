/*
 * 文件名：IParam.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.param.entity;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 指标参数存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月16日
 * @see IParam
 * @since 1.0
 */
public interface IParam extends IBusiness {

    /**
     * {@inheritDoc}
     */
    @Override
    default String getKey() {
        return RedisKeyEnum.指标参数配置.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default String getField() {
        return getParaCode() + GlobalConstant.COLON + getParaGranularity();
    }

    /**
     * 取得 参数代码
     * 
     * @return 参数代码
     * @see
     * @since 1.0
     */
    String getParaCode();

    /**
     * 取得 参数名称
     *
     * @return 参数名称
     */
    String getParaName();

    /**
     * 取得 参数粒度
     * 
     * @return 参数粒度
     * @see
     * @since 1.0
     */
    String getParaGranularity();

    /**
     * 取得 参数内容
     * 
     * @return 参数内容
     * @see
     * @since 1.0
     */
    String getContent();

    /**
     * 取得 Redis识别域
     * 
     * @return Redis识别域
     * @see
     * @since 1.0
     */
    String[] getRedisFieldArr();

    /**
     * 取得 参数实现类型
     * 
     * @return 参数实现类型
     * @see
     * @since 1.0
     */
    String getParaDisplayType();

    /**
     * 取得 刷新时是否能做交易
     * 
     * @return true:能、false:不能
     * @see
     * @since 1.0
     */
    boolean isFreshCanTrd();

    /**
     * 取得 是否枚举型参数
     * 
     * @return true:是、false:否
     * @see
     * @since 1.0
     */
    boolean isEnumType();

    /**
     * 是否更新Redis
     * @return
     */
    boolean isUpRedis();

    /**
     * 参数数据类型
     * @return
     */
    String getParamDataType();

    /**
     * 机构号
     * @return
     */
    String getInstitutionCode();

    /**
     * 参数计算方式
     * @return
     */
    String getParamMathType();

    /**
     * 参数为空取预值
     * @return
     */
    String getParamValueNone();
}
