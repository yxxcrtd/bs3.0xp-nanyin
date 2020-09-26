package com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto;

import java.util.Objects;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 科目映射DTO
 * 
 * @author 蒋传志
 * @version 1.0 2018年1月23日
 * @see ReconcileSchemeSubjectConfigDto
 * @since 1.0
 */
public class ReconcileSchemeSubjectConfigDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

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
     * 是否自动生成子科目（对方系统） 本方
     */
    private String isMyGenSubs;

    /**
     * 是否自动生成子科目（对方系统） 本方 显示值
     */
    @SuppressWarnings("unused")
    private String isMyGenSubsView;

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
     * 是否自动生成子科目（对方系统） 显示值
     */
    @SuppressWarnings("unused")
    private String isGenSubsView;

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

    public String getMySubjectName() {
        return mySubjectName;
    }

    public void setMySubjectName(String mySubjectName) {
        this.mySubjectName = mySubjectName;
    }

    public String getIsMyGenSubsView() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, blockType);
    }

    public void setIsMyGenSubsView(String isMyGenSubsView) {
        this.isMyGenSubsView = isMyGenSubsView;
    }

    public String getIsGenSubsView() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, blockType);
    }

    public void setIsGenSubsView(String isGenSubsView) {
        this.isGenSubsView = isGenSubsView;
    }

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public String getBlockTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值对账配置_区块类型, blockType);
    }

    public void setBlockTypeName(String blockTypeName) {
        this.blockTypeName = blockTypeName;
    }

    public String getIsMyGenSubs() {
        return isMyGenSubs;
    }

    public void setIsMyGenSubs(String isMyGenSubs) {
        this.isMyGenSubs = isMyGenSubs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReconcileSchemeSubjectConfigDto)) {
            return false;
        }
        ReconcileSchemeSubjectConfigDto subject = (ReconcileSchemeSubjectConfigDto)o;
        return Objects.equals(blockType, subject.blockType) && Objects.equals(mySubjectId, subject.mySubjectId) && Objects.equals(othersideSubjectId, subject.othersideSubjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockType, mySubjectId, othersideSubjectId);
    }

}
