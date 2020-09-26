/*
 * 文件名：ReconcileSchemeSubjectConfigDto.java
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
 * 科目映射DTO
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeSubjectConfigDto
 * @since 1.0
 */
public class ValReconcileSchemeSubjectConfigDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5842607442527360042L;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 本系统科目号
     */
    private String mySubjectId;

    /**
     * 本系统科目名称
     */
    private String mySubjectName;

    /**
     * 是否自动生成子科目（本方系统）
     */
    private String myIsGenSubs;

    /**
     * 对方系统科目号
     */
    private String othersideSubjectId;

    /**
     * 对方系统科目名称
     */
    private String othersideSubjectName;

    /**
     * 是否自动生成子科目（对方系统） 参照数据字典
     */
    private String isGenSubs;

    /**
     * 区块类型
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
     * 取得 本系统科目号
     * 
     * @return 本系统科目号
     */
    public String getMySubjectId() {
        return mySubjectId;
    }

    /**
     * 设置 本系统科目号
     * 
     * @param mySubjectId 本系统科目号
     */
    public void setMySubjectId(String mySubjectId) {
        this.mySubjectId = mySubjectId == null ? null : mySubjectId.trim();
    }

    /**
     * 取得 对方系统科目号
     * 
     * @return 对方系统科目号
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
        this.othersideSubjectId = othersideSubjectId == null ? null : othersideSubjectId.trim();
    }

    /**
     * 取得 对方系统科目名称
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
        this.othersideSubjectName = othersideSubjectName == null ? null : othersideSubjectName.trim();
    }

    /**
     * 取得 是否自动生成子科目（对方系统） 参照数据字典
     * 
     * @return 是否自动生成子科目（对方系统） 参照数据字典
     */
    public String getIsGenSubs() {
        return isGenSubs;
    }

    /**
     * 设置 是否自动生成子科目（对方系统） 参照数据字典
     * 
     * @param isGenSubs 是否自动生成子科目（对方系统） 参照数据字典
     */
    public void setIsGenSubs(String isGenSubs) {
        this.isGenSubs = isGenSubs == null ? null : isGenSubs.trim();
    }

    public String getIsGenSubsFmt() {
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, isGenSubs))?
    			SysDataUtil.getNameFromDataDict(DictCode.是否, isGenSubs):isGenSubs;
//        return SysDataUtil.getNameFromDataDict(DictCode.是否, isGenSubs);
    }

    /**
     * 获取 区块类型
     * 
     * @return 区块类型
     */
    public String getBlockType() {
        return blockType;
    }

    /**
     * 设置 区块类型
     * 
     * @param blockType 区块类型
     */
    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    /**
     * 获取 本系统科目名称
     * 
     * @return 本系统科目名称
     */
    public String getMySubjectName() {
        return mySubjectName;
    }

    /**
     * 设置 本系统科目名称
     * 
     * @param mySubjectName 本系统科目名称
     */
    public void setMySubjectName(String mySubjectName) {
        this.mySubjectName = mySubjectName;
    }

    /**
     * 获取 是否自动生成子科目（本方系统）
     * 
     * @return 是否自动生成子科目（本方系统）
     */
    public String getMyIsGenSubs() {
        return myIsGenSubs;
    }

    /**
     * 设置 是否自动生成子科目（本方系统）
     * 
     * @param 是否自动生成子科目（本方系统）
     */
    public void setMyIsGenSubs(String myIsGenSubs) {
        this.myIsGenSubs = myIsGenSubs;
    }

    /**
     * 获取 区块类型数据字典
     */
    public String getBlockTypeFmt() {
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值对账配置_区块类型, blockType))?
    			SysDataUtil.getNameFromDataDict(DictCode.估值对账配置_区块类型, blockType):blockType;
//        return SysDataUtil.getNameFromDataDict(DictCode.估值对账配置_区块类型, blockType);
    }
    
    /**
     * 是否自动生成子科目（本方系统）
     */
    public String getMyIsGenSubsFmt() {
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, myIsGenSubs))?
    			SysDataUtil.getNameFromDataDict(DictCode.是否, myIsGenSubs):myIsGenSubs;
//        return SysDataUtil.getNameFromDataDict(DictCode.是否, myIsGenSubs);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ValReconcileSchemeSubjectConfigDto)) {
            return false;
        }
        ValReconcileSchemeSubjectConfigDto subject = (ValReconcileSchemeSubjectConfigDto)o;
        return Objects.equals(blockType, subject.blockType)
               && Objects.equals(mySubjectId, subject.mySubjectId)
               && Objects.equals(othersideSubjectId, subject.othersideSubjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockType, mySubjectId, othersideSubjectId);
    }

}
