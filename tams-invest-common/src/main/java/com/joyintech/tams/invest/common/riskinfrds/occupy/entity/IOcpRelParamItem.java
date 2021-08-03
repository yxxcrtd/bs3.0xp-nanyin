/*
 * 文件名：IOcpRelParamItem.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.occupy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;

/**
 * 预占/实占参数信息
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月27日
 * @see IOcpRelParamItem
 * @since 1.0
 */
public interface IOcpRelParamItem extends Serializable {

    /**
     * 取得 指标参数代码
     * 
     * @return 指标参数代码
     * @see
     * @since 1.0
     */
    String getParamCode();

    /**
     * 取得 参数值Redis存储Field
     * 
     * @return 参数值Redis存储Field
     * @see
     * @since 1.0
     */
    Set<String> getRedisFields();

    /**
     * 取得 预占/实占操作符号
     * 
     * @return 预占/实占操作符号
     * @see
     * @since 1.0
     */
    String getOccupyOper();

    /**
     * 取得 预占/实占的对象（金额、价格、数量）
     * 
     * @return 预占/实占的对象
     * @see
     * @since 1.0
     */
    String getOccupyItem();

    /**
     * 取得 预占/实占金额
     * 
     * @return 预占/实占金额
     * @see
     * @since 1.0
     */
    BigDecimal getOccupyVal();

    /**
     * 设置 预占/实占金额
     * 
     * @param occupyVal 预占/实占金额
     * @see
     * @since 1.0
     */
    void setOccupyVal(BigDecimal occupyVal);

    /**
     * 取得 实占超过预占的部分
     * 
     * @return 实占超过预占的部分
     * @see
     * @since 1.0
     */
    BigDecimal getOffsetVal();

    /**
     * 设置 实占超过预占的部分
     * 
     * @param offsetVal 实占超过预占的部分
     * @see
     * @since 1.0
     */
    void setOffsetVal(BigDecimal offsetVal);

    // add by zhangrui1 start
    /**
     * 取得 差分预占列表 <差分标识, 差分额度>
     * 
     * @return 差分预占列表
     */
    Map<String, IOccupyDiff> getDiffOcpMap();

    /**
     * 设置 差分预占列表
     * 
     * @param diffOcpMap 差分预占列表
     */
    void setDiffOcpMap(Map<String, IOccupyDiff> diffOcpMap);

    /**
     * 添加差分流水
     * 
     * @param code 标识码
     * @param val 调整额度
     */
    default BigDecimal putDiffOcp(String rdsKey, String code, BigDecimal val, boolean isReverse) {
        Set<String> rds = getRedisFields();
        Map<String, IOccupyDiff> diffOcpMap = getDiffOcpMap();
        // 初始化差分列表
        if (CollectionUtils.isEmpty(diffOcpMap)) {
            diffOcpMap = new HashMap<>();
        }
        IOccupyDiff ocpDiff = diffOcpMap.get(rdsKey);
        // 如果是第一次差分预占需要设置初始预占额度
        if (ocpDiff == null) {
            ocpDiff = new OccupyDiff();
            // 如果有这个redis识别域则标识有过预占，设置初始化预占额度为预占信息的预占额度
            if (rds.contains(rdsKey)) {
                ocpDiff.setFirstVal(ArithmeticUtil.doAdd(getOccupyVal(), getOffsetVal()));
            }
            // 否则设置初始化预占额度为0
            else {
                ocpDiff.setFirstVal(BigDecimal.ZERO);
            }
        }
        BigDecimal diffVal = isReverse ? ocpDiff.reverseOcp(code, val) : ocpDiff.putDiffMap(code,
            val);
        diffOcpMap.put(rdsKey, ocpDiff);
        setDiffOcpMap(diffOcpMap);
        return diffVal;
    }

    /**
     * 删除差分预占信息
     * 
     * @param rdsKey redis识别域
     * @param code 差分标识
     * @return 删除的额度
     */
    default BigDecimal removeDiffOcp(String rdsKey, String code) {
        Map<String, IOccupyDiff> diffOcpMap = getDiffOcpMap();
        // 初始化差分列表
        if (!CollectionUtils.isEmpty(diffOcpMap)) {
            IOccupyDiff ocpDiff = diffOcpMap.get(rdsKey);
            // 不存在差分预占时
            if (ocpDiff == null) {
                return BigDecimal.ZERO;
            }
            // 删除差分预占额度
            BigDecimal val = ocpDiff.removeDiffVal(code);
            if (CollectionUtils.isEmpty(ocpDiff.getDiffMap())) {
                diffOcpMap.remove(rdsKey);
                // 初始化预占额度与预占信息额度不相等时
                if (getOccupyVal().compareTo(ocpDiff.getFirstVal()) != 0) {
                    getRedisFields().remove(rdsKey);
                }
            }
            return val;
        }
        return BigDecimal.ZERO;
    }

    default BigDecimal diffVal(String rdsKey) {
        Map<String, IOccupyDiff> diffOcpMap = getDiffOcpMap();
        if (!CollectionUtils.isEmpty(diffOcpMap)) {
            IOccupyDiff ocpDiff = diffOcpMap.get(rdsKey);
            if (ocpDiff != null) {
                return ocpDiff.diffVal(BigDecimal.ZERO);
            }
        }
        return getOccupyVal();
    }

    default BigDecimal defaultDiffVal() {
        Map<String, IOccupyDiff> diffOcpMap = getDiffOcpMap();
        if (!CollectionUtils.isEmpty(diffOcpMap)) {
            Entry<String, IOccupyDiff> ocpDiff = diffOcpMap.entrySet().stream().findFirst().orElse(null);
            if (ocpDiff != null) {
                return ocpDiff.getValue().diffVal(BigDecimal.ZERO);
            }
        }
        return getOccupyVal();
    }
    // add by zhangrui1 end
    // add by zhangrui1 公式配置 start
    /**
     * 设置公式参数值
     * 
     * @param formulaVal 公式参数值
     */
    void setFormulaVal(BigDecimal formulaVal);

    /**
     * 取得 公式参数值
     * 
     * @return 公式参数值
     */
    BigDecimal getFormulaVal();

    /**
     * 设置 公式操作符
     * 
     * @param formulaOpr 公式操作符
     */
    void setFormulaOpr(String formulaOpr);

    /**
     * 取得 公式操作符
     * 
     * @return 公式操作符
     */
    String getFormulaOpr();

    default BigDecimal doFormula() {
        if (null != getFormulaVal() && StringUtil.isNotEmpty(getFormulaOpr())) {
            return CommonUtil.math(getFormulaVal(),
                ArithmeticUtil.doAdd(getOccupyVal(), getOffsetVal()), getFormulaOpr());
        }
        return getOccupyVal();
    }

    default BigDecimal doFormula(BigDecimal ocpVal) {
        if (null != getFormulaVal() && StringUtil.isNotEmpty(getFormulaOpr())) {
            return CommonUtil.math(getFormulaVal(), ocpVal, getFormulaOpr());
        }
        return ocpVal;
    }
    // add by zhangrui1 公式配置 end
}
