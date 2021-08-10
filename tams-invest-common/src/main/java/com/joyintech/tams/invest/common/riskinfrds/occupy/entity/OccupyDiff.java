/*
 * 文件名：OccupyDiff.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.riskinfrds.occupy.entity;

import java.math.BigDecimal;
import java.util.Map;


/**
 * 差分预占信息
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月6日
 * @see OccupyDiff
 * @since 1.0
 */
public class OccupyDiff implements IOccupyDiff {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -134072046419059993L;

    /**
     * 初始预占额度
     */
    private BigDecimal firstVal;

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getFirstVal() {
        return firstVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFirstVal(BigDecimal firstVal) {
        this.firstVal = firstVal;
    }

    /**
     * 差分预占额度列表
     */
    private Map<String, BigDecimal> diffMap;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, BigDecimal> getDiffMap() {
        return diffMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDiffMap(Map<String, BigDecimal> diffMap) {
        this.diffMap = diffMap;
    }
}
