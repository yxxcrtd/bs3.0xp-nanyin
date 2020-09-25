/*
 * 文件名：IIndicator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.indic.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joyintech.tams.invest.common.riskinfrds.info.entity.IProdInfo;
import org.springframework.util.CollectionUtils;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 指标信息Redis存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月16日
 * @see IIndicator
 * @since 1.0
 */
public interface IIndicator extends IIndividLimit, IBusiness {

    default String getKey() {
        return RedisKeyEnum.指标信息配置.key();
    }

    default String getField() {
        return getIndId();
    }

    /**
     * 取得 指标代码
     * 
     * @return 指标代码
     * @see
     * @since 1.0
     */
    String getIndId();

    /**
     * 取得 指标名称
     * 
     * @return 指标名称
     * @see
     * @since 1.0
     */
    String getIndName();

    /**
     * 取得 指标类型
     * 
     * @return 指标类型
     * @see
     * @since 1.0
     */
    String getIndType();

    /**
     * 取得 监督类型
     * 
     * @return 监督类型
     * @see
     * @since 1.0
     */
    String getSupType();

    /**
     * 取得 指标计算表达式
     * 
     * @return 指标计算表达式
     * @see
     * @since 1.0
     */
    String getExpr();

    /**
     * 取得 指标计算表达式带粒度
     *
     * @return 指标计算表达式
     * @see
     * @since 1.0
     */

    String getExprGrad();

    /**
     * 取得 指标是否绝对值，默认为false
     *
     * @return 绝对值，布尔
     * @see
     * @since 1.0
     */
    boolean getIsAbs();

    /**
     * 取得 指标监督时点
     * 
     * @return 指标监督时点
     * @see
     * @since 1.0
     */
    String getTimePoint();

    /**
     * 取得 指标计算参数列表
     * 
     * @return 指标计算参数列表
     * @see
     * @since 1.0
     */
    List<String> getIndParamList();

    /**
     * 取得 指标计算结果
     * 
     * @return 指标计算结果
     * @see
     * @since 1.0
     */
    Map<String, IIndicatorResult> getIndicatorResult();

    default IIndicatorResult getPtlIndicatorResult(String ptlCode) {
        Map<String, IIndicatorResult> resMap = getIndicatorResult();
        if (CollectionUtils.isEmpty(resMap)) {
            return null;
        }
        return resMap.get(ptlCode);
    }

    /**
     * 设置 指标计算结果
     * 
     * @param result 指标计算结果
     * @see
     * @since 1.0
     */
    void setIndicatorResult(Map<String, IIndicatorResult> result);

    /**
     * 取得 个性化联合设置信息
     * 
     * @return 个性化联合设置信息 <b>Map<组合(包)代码, List<指标代码>></b>
     * @see
     * @since 1.0
     */
    Map<String, Set<String>> getIndividUnite();

    /**
     * 取得 指定组合代码的个性化联合指标列表
     * 
     * @param ptlCode 组合代码
     * @return 指定组合代码的个性化联合指标列表
     * @see
     * @since 1.0
     */
    default Set<String> getPtlIndividUnite(String ptlCode) {
        Map<String, Set<String>> unite = getIndividUnite();
        if (CollectionUtils.isEmpty(unite)) {
            return new HashSet<>();
        }
        return unite.get(ptlCode);
    }

    /**
     * 取得 个性化特殊计算阈值信息（针对资产设置特定阈值）
     * 
     * @return 个性化特殊计算阈值信息 <b>Map<组合(包)代码, Map<资产代码, 个性化特殊计算阈值信息>></b>
     * @see
     * @since 1.0
     */
    Map<String, Map<String, IIndividLimit>> getIndividLimit();

    /**
     * 取得 指定组合代码的个性化条件设置信息
     * 
     * @param ptlCode 组合代码
     * @return 指定组合代码的个性化条件设置信息
     * @see
     * @since 1.0
     */
    default Map<String, IIndividLimit> getIndividLimit(String ptlCode) {
        Map<String, Map<String, IIndividLimit>> limit = getIndividLimit();
        if (CollectionUtils.isEmpty(limit)) {
            return new HashMap<>();
        }
        return limit.get(ptlCode);
    }

    /**
     * 取得 个性化特殊计算阈值信息（针对组合设置特定阈值）
     * 
     * @return 个性化特殊计算阈值信息 <b>Map<组合(包)代码, 个性化特殊计算阈值信息></b>
     * @see
     * @since 1.0
     */
    Map<String, IIndividLimit> getPtlIndividLimit();

    /**
     * 取得 指定组合代码的个性化条件设置信息（针对组合）
     * 
     * @param ptlCode 组合代码
     * @return 指定组合代码的个性化条件设置信息
     * @see
     * @since 1.0
     */
    default IIndividLimit getPtlIndividLimit(String ptlCode) {
        Map<String, IIndividLimit> limit = getPtlIndividLimit();
        if (CollectionUtils.isEmpty(limit)) {
            return null;
        }
        return limit.get(ptlCode);
    }

    /**
     * 取得 个性化剔除资产信息
     * 
     * @return 个性化剔除资产信息 <b>Map<组合(包)代码, List<资产代码>></b>
     * @see
     * @since 1.0
     */
    Map<String, Set<String>> getIndividAstDel();

    /**
     * 取得 指定组合代码的个性化剔除资产信息
     * 
     * @param ptlCode 组合代码
     * @return 指定组合代码的个性化剔除资产信息
     * @see
     * @since 1.0
     */
    default Set<String> getPtlIndividAstDel(String ptlCode) {
        Map<String, Set<String>> astDel = getIndividAstDel();
        if (CollectionUtils.isEmpty(astDel)) {
            return new HashSet<>();
        }
        return astDel.get(ptlCode);
    }

    /**
     * 除0是否异常
     * 
     * @param prodInfo 产品信息
     * @return true:异常、false:正常
     */
    boolean isExcpWhenDivZero(IProdInfo prodInfo);


}
