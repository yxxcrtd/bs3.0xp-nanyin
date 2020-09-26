/*
 * 文件名：ReconcileSchemeStructConfigDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月23日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto;

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
 * @see ValReconcileSchemeNatureConfigDto
 * @since 1.0
 */
public class ValReconcileSchemeNatureConfigDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6603834976183432745L;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 对方系统科目号
     */
    private String othersideSubjectId;

    /**
     * 对方系统科目名称
     */
    private String othersideSubjectName;

    /**
     * 所属本系统的科目性质
     */
    private String myNatureId;

    /**
     * 所属本系统的科目性质名称
     */
    private String myNatureName;

    /**
     * 是否自动生成子科目（对方系统）
     */
    private String isGenSubs;

    /**
     * 区块类型 明细行 汇总行
     */
    private String blockType;

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
     * 获取 对方系统科目号
     * 
     * @return 对方系统科目号.
     */
    public String getOthersideSubjectId() {
        return othersideSubjectId;
    }

    /**
     * 设置 对方系统科目号
     * 
     * @param othersideSubjectId 对方系统科目号
     */
    public void setOthersideSubjectId(String othersideSubjectId) {
        this.othersideSubjectId = othersideSubjectId;
    }

    /**
     * 获取 对方系统科目名称
     * 
     * @return 对方系统科目名称
     */
    public String getOthersideSubjectName() {
        return othersideSubjectName;
    }

    /**
     * 设置 对方系统科目名称
     * 
     * @param othersideSubjectName 对方系统科目名称
     */
    public void setOthersideSubjectName(String othersideSubjectName) {
        this.othersideSubjectName = othersideSubjectName;
    }

    /**
     * 获取 所属本系统的科目性质
     * 
     * @return 所属本系统的科目性质
     */
    public String getMyNatureId() {
        return myNatureId;
    }

    /**
     * 设置 所属本系统的科目性质
     * 
     * @param myNatureId 所属本系统的科目性质
     */
    public void setMyNatureId(String myNatureId) {
        this.myNatureId = myNatureId;
    }

    /**
     * 获取 所属本系统的科目性质名称
     * 
     * @return 所属本系统的科目性质名称
     */
    public String getMyNatureName() {
        return myNatureName;
    }

    /**
     * 设置 所属本系统的科目性质名称
     * 
     * @param myNatureName 所属本系统的科目性质名称
     */
    public void setMyNatureName(String myNatureName) {
        this.myNatureName = myNatureName;
    }

    /**
     * 获取 是否自动生成子科目（对方系统）
     * 
     * @return 是否自动生成子科目（对方系统）
     */
    public String getIsGenSubs() {
        return isGenSubs;
    }

    /**
     * 设置 是否自动生成子科目（对方系统）
     * 
     * @param isGenSubs 是否自动生成子科目（对方系统）
     */
    public void setIsGenSubs(String isGenSubs) {
        this.isGenSubs = isGenSubs;
    }
    
    public String getIsGenSubsFmt() {
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, isGenSubs))?
    			SysDataUtil.getNameFromDataDict(DictCode.是否, isGenSubs):isGenSubs;
//        return SysDataUtil.getNameFromDataDict(DictCode.是否, isGenSubs);
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
     * 取得 区块类型数据字典
     * 
     * @return 区块类型数据字典
     * @since 1.0
     */
    public String getBlockTypeFmt() {
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值对账配置_区块类型, blockType))?
    			SysDataUtil.getNameFromDataDict(DictCode.估值对账配置_区块类型, blockType):blockType;
//        return SysDataUtil.getNameFromDataDict(DictCode.估值对账配置_区块类型, blockType);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ValReconcileSchemeNatureConfigDto)) {
            return false;
        }
        ValReconcileSchemeNatureConfigDto struct = (ValReconcileSchemeNatureConfigDto)o;
        return Objects.equals(schemeId, struct.schemeId)
               && Objects.equals(othersideSubjectId, struct.othersideSubjectId)
               && Objects.equals(myNatureId, struct.myNatureId)
               && Objects.equals(blockType, struct.blockType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeId, othersideSubjectId, myNatureId, blockType);
    }
}
