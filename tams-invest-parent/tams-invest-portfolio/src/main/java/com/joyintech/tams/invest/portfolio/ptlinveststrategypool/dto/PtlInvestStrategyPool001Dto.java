package com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.validator.PtlInvestStrategyPool001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@ValidLogic(targetClass = PtlInvestStrategyPool001Validator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class PtlInvestStrategyPool001Dto extends PtlInvStrategy implements ITableRow, ProcessEntity, GridRowDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 节点ID
     */
    private String elementId;

    /**
     * 资产二级分类
     */
    private String type2;

    /**
     * 资产三级分类
     */
    private String type3;

    /**
     * 资产四级分类
     */
    private String type4;

    /**
     * 资产五级分类
     */
    private String type5;

    /**
     * 比例（%）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 投资策略组合详情列表
     */
    private List<PtlInvestStrategyPoolDetailDto> ptlInvestStrategyPoolDetailList;

    /**
     * 指标
     */
    private String evalIndex;

    /**
     * 共通业务类型
     */
    private String commonType;

    /**
     * 行ID
     */
    private String rowId;

    /**
     * 是否有子节点
     */
    private String hasChildren;

    /**
     * 名称
     */
    private String name;

    /**
     * 树形结构id
     */
    private String assetTypeId;

    /**
     * 树形结构id
     */
    private String pAssetTypeId;

    /**
     * 是否最终节点判断
     */
    private String fiterFlag;

    /**
     * 是否新建
     */
    private String isNew;

    /**
     * 批次号
     */
    private String batchId;

    /**
     * 获取 batchId
     * 
     * @return batchId.
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 batchId
     * 
     * @param batchId batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getType4() {
        return type4;
    }

    public void setType4(String type4) {
        this.type4 = type4;
    }

    public String getType5() {
        return type5;
    }

    public void setType5(String type5) {
        this.type5 = type5;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<PtlInvestStrategyPoolDetailDto> getPtlInvestStrategyPoolDetailList() {
        return ptlInvestStrategyPoolDetailList;
    }

    public void setPtlInvestStrategyPoolDetailList(List<PtlInvestStrategyPoolDetailDto> ptlInvestStrategyPoolDetailList) {
        this.ptlInvestStrategyPoolDetailList = ptlInvestStrategyPoolDetailList;
    }

    @Override
    @NotEmpty(fieldName = "PtlInvestStrategyPool002.strategyName", groups = {
        ValidateGroupEdit.class})
    public String getStrategyName() {
        return super.getStrategyName();
    }

    @Override
    @NotEmpty(fieldName = "PtlInvestStrategyPool002.strategyType", groups = {
        ValidateGroupEdit.class})
    public String getStrategyType() {
        return super.getStrategyType();
    }

    @Override
    @NotEmpty(fieldName = "PtlInvestStrategyPool002.strategyDesc", groups = {
        ValidateGroupEdit.class})
    public String getStrategyDesc() {
        return super.getStrategyDesc();
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getEvalIndex() {
        return evalIndex;
    }

    public void setEvalIndex(String evalIndex) {
        this.evalIndex = evalIndex;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getStrategyId() {
        return super.getStrategyId();
    }

    public String getCommonType() {
        return commonType;
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    public void setCommonType(String commonType) {
        this.commonType = commonType;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void setOrder(int order) {
    }

    public String getId() {
        return this.getAssetTypeId();

    }

    public void setId(String id) {
        setAssetTypeId(id);

    }

    public String getParentId() {
        return this.getpAssetTypeId();
    }

    public void setParentId(String parentId) {
        setpAssetTypeId(parentId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isLeaf() {
        return StringUtil.equals(com.joyintech.framework.constant.DictCodeList.是否_是.getValue(),
            hasChildren);
    }

    @Override
    public void setLeaf(boolean leaf) {
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getpAssetTypeId() {
        return pAssetTypeId;
    }

    public void setpAssetTypeId(String pAssetTypeId) {
        this.pAssetTypeId = pAssetTypeId;
    }

    public String getFiterFlag() {
        return fiterFlag;
    }

    public void setFiterFlag(String fiterFlag) {
        this.fiterFlag = fiterFlag;
    }

    public String getType5Str() {
        String type5Str = StringUtil.EMPTY;
        List<String> list = StringUtil.splitToList(GlobalConstant.COMMA, this.getType5());
        if (!StringUtil.isEmptyList(list)) {
            for (String type : list) {
                type5Str = type5Str + SysDataUtil.getNameFromDataDict(DictCode.行内资产五级分类, type)
                           + GlobalConstant.COMMA;
            }
            type5Str = type5Str.substring(0, type5Str.lastIndexOf(GlobalConstant.COMMA));
        }
        return type5Str;
    }

    public String getType4Str() {
        String type4Str = StringUtil.EMPTY;
        List<String> list = StringUtil.splitToList(GlobalConstant.COMMA, this.getType4());
        if (!StringUtil.isEmptyList(list)) {
            for (String type : list) {
                type4Str = type4Str + SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, type)
                           + GlobalConstant.COMMA;
            }
            type4Str = type4Str.substring(0, type4Str.lastIndexOf(GlobalConstant.COMMA));
        }
        return type4Str;
    }

    public String getType3Str() {
        String type3Str = StringUtil.EMPTY;
        List<String> list = StringUtil.splitToList(GlobalConstant.COMMA, this.getType3());
        if (!StringUtil.isEmptyList(list)) {
            for (String type : list) {
                type3Str = type3Str + SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, type)
                           + GlobalConstant.COMMA;
            }
            type3Str = type3Str.substring(0, type3Str.lastIndexOf(GlobalConstant.COMMA));
        }
        return type3Str;
    }

    public String getType2Str() {
        String type2Str = StringUtil.EMPTY;
        List<String> list = StringUtil.splitToList(GlobalConstant.COMMA, this.getType2());
        if (!StringUtil.isEmptyList(list)) {
            for (String type : list) {
                type2Str = type2Str + SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, type)
                           + GlobalConstant.COMMA;
            }
            type2Str = type2Str.substring(0, type2Str.lastIndexOf(GlobalConstant.COMMA));
        }
        return type2Str;
    }

    public String getStrategyTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资策略类型, super.getStrategyType());
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

}
