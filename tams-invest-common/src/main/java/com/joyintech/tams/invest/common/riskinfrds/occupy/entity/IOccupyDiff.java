/*
 * 文件名：IOccupyDiff.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.riskinfrds.occupy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;

import com.joyintech.framework.util.ArithmeticUtil;

/**
 * 差分预占信息
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月6日
 * @see IOccupyDiff
 * @since 1.0
 */
public interface IOccupyDiff extends Serializable {

    /**
     * 取得 初始预占额度
     * 
     * @return 初始预占额度
     */
    BigDecimal getFirstVal();


    /**
     * 设置初始预占额度
     */
    void setFirstVal(BigDecimal firstVal);

    /**
     * 取得差分预占额度
     * 
     * @return 差分预占额度
     */
    Map<String, BigDecimal> getDiffMap();

    /**
     * 设置差分额度列表
     * 
     * @param diffMap 差分额度列表
     */
    void setDiffMap(Map<String, BigDecimal> diffMap);

    /**
     * 添加差分流水
     * 
     * @param code 标识码
     * @param val 调整额度
     */
    default BigDecimal putDiffMap(String code, BigDecimal val) {
        Map<String, BigDecimal> diffMap = getDiffMap();
        if (CollectionUtils.isEmpty(diffMap)) {
            diffMap = new HashMap<>();
        }
        BigDecimal resVal = diffVal(val);
        diffMap.put(code, resVal);
        setDiffMap(diffMap);
        return resVal;
    }

    /**
     * 计算差分额度
     * 
     * @param val 调整额度
     * @return 差分额度
     */
    default BigDecimal diffVal(BigDecimal val) {
        BigDecimal allDiff = BigDecimal.ZERO;
        Map<String, BigDecimal> diffMap = getDiffMap();
        // 若差分预占列表不为空则计算所有差分总和
        if (!CollectionUtils.isEmpty(diffMap)) {
            allDiff = ArithmeticUtil.doAdd(
                diffMap.values().toArray(new BigDecimal[diffMap.size()]));
        }
        // 初始预占额度
        BigDecimal occupyVal = getFirstVal() != null ? getFirstVal() : BigDecimal.ZERO;
        // 计算差分额度
        return ArithmeticUtil.subtract(val, ArithmeticUtil.doAdd(occupyVal, allDiff));
    }

    /**
     * 根据差分标识获取差分额度
     * 
     * @param code 差分标识
     * @return 差分额度
     */
    default BigDecimal diffVal(String code) {
        Map<String, BigDecimal> diffOcpMap = getDiffMap();
        // 初始化差分列表
        if (!CollectionUtils.isEmpty(diffOcpMap)) {
            return diffOcpMap.getOrDefault(code, BigDecimal.ZERO);
        }
        return BigDecimal.ZERO;
    }

    /**
     * 删除差分额度
     * 
     * @param code 差分标识
     * @return 差分额度
     */
    default BigDecimal removeDiffVal(String code) {
        Map<String, BigDecimal> diffOcpMap = getDiffMap();
        // 初始化差分列表
        if (!CollectionUtils.isEmpty(diffOcpMap)) {
            return diffOcpMap.remove(code);
        }
        return BigDecimal.ZERO;
    }

    /**
     * 逆向预占
     * 
     * @param code 标识
     * @param val 调整额
     * @return 调整额
     */
    default BigDecimal reverseOcp(String code, BigDecimal val) {
        Map<String, BigDecimal> diffMap = getDiffMap();
        if (CollectionUtils.isEmpty(diffMap)) {
            diffMap = new HashMap<>();
        }
        BigDecimal resVal = val.negate();
        diffMap.put(code, resVal);
        setDiffMap(diffMap);
        return resVal;
    }
}
