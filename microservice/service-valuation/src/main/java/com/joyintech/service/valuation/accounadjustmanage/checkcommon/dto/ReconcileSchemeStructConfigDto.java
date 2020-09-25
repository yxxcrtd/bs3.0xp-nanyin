package com.joyintech.service.valuation.accounadjustmanage.checkcommon.dto;

import java.util.Objects;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 结构映射DTO
 * 
 * @author 蒋传志
 * @version 1.0 2018年1月23日
 * @see ReconcileSchemeStructConfigDto
 * @since 1.0
 */
public class ReconcileSchemeStructConfigDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

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
     * 区块类型名
     */
    @SuppressWarnings("unused")
    private String blockTypeName;

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

    /**
     * 取得 区块类型名
     * 
     * @return 区块类型名
     * @since 1.0
     */
    public String getBlockTypeName() {
        return this.blockTypeName;
    }

    /**
     * 设置 区块类型名
     * 
     * @param blockTypeName 区块类型名
     * @since 1.0
     */
    public void setBlockTypeName(String blockTypeName) {
        this.blockTypeName = blockTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReconcileSchemeStructConfigDto)) {
            return false;
        }
        ReconcileSchemeStructConfigDto struct = (ReconcileSchemeStructConfigDto)o;
        return Objects.equals(blockType, struct.blockType) && Objects.equals(myColId, struct.myColId) && Objects.equals(othersideColId, struct.othersideColId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockType, myColId, othersideColId);
    }
}
