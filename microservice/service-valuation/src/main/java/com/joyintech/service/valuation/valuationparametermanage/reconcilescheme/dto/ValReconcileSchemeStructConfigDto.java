/*
 * 文件名：ReconcileSchemeStructConfigDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月23日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto;

import java.util.Objects;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;


/**
 * 结构映射DTO
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeStructConfigDto
 * @since 1.0
 */
public class ValReconcileSchemeStructConfigDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6603834976183432745L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 区块类型
     */
    private String blockType;

    /**
     * 本方列ID（在数据字典固化）
     */
    private String myColId;

    /**
     * 本方列说明（在数据字典固化）
     */
    private String myColRemark;

    /**
     * 对方列ID
     */
    private String othersideColId;

    /**
     * 对方列说明
     */
    private String othersideColRemark;

    /**
     * 取得 流水号
     * 
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * 
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * 
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 本方列ID（在数据字典固化）
     * 
     * @return 本方列ID（在数据字典固化）
     */
    public String getMyColId() {
        return myColId;
    }

    /**
     * 设置 本方列ID（在数据字典固化）
     * 
     * @param myColId 本方列ID（在数据字典固化）
     */
    public void setMyColId(String myColId) {
        this.myColId = myColId == null ? null : myColId.trim();
    }

    /**
     * 取得 本方列说明（在数据字典固化）
     * 
     * @return 本方列说明（在数据字典固化）
     */
    public String getMyColRemark() {
        return myColRemark;
    }

    /**
     * 设置 本方列说明（在数据字典固化）
     * 
     * @param myColRemark 本方列说明（在数据字典固化）
     */
    public void setMyColRemark(String myColRemark) {
        this.myColRemark = myColRemark == null ? null : myColRemark.trim();
    }

    /**
     * 取得 对方列ID
     * 
     * @return 对方列ID
     */
    public String getOthersideColId() {
        return othersideColId;
    }

    /**
     * 设置 对方列ID
     * 
     * @param othersideColId 对方列ID
     */
    public void setOthersideColId(String othersideColId) {
        this.othersideColId = othersideColId == null ? null : othersideColId.trim();
    }

    /**
     * 取得 对方列说明
     * 
     * @return 对方列说明
     */
    public String getOthersideColRemark() {
        return othersideColRemark;
    }

    /**
     * 设置 对方列说明
     * 
     * @param othersideColRemark 对方列说明
     */
    public void setOthersideColRemark(String othersideColRemark) {
        this.othersideColRemark = othersideColRemark == null ? null : othersideColRemark.trim();
    }

    /**
     * 取得 区块类型
     * 
     * @return 区块类型
     * @since 1.0
     */
    public String getBlockType() {
        return blockType;
    }

    /**
     * 设置 区块类型
     * 
     * @param blockType 区块类型
     * @since 1.0
     */
    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ValReconcileSchemeStructConfigDto)) {
            return false;
        }
        ValReconcileSchemeStructConfigDto struct = (ValReconcileSchemeStructConfigDto)o;
        return Objects.equals(blockType, struct.blockType)
               && Objects.equals(myColId, struct.myColId)
               && Objects.equals(othersideColId, struct.othersideColId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockType, myColId, othersideColId);
    }
}
