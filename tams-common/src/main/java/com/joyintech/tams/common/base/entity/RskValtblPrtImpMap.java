package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskValtblPrtImpMap extends BaseEntity {
    /**
     * 组合代码
     */
    private String ptlId;

    /**
     * 起始行

     */
    private Long startRow;

    /**
     * 科目代码列

     */
    private Long subjectCodeCol;

    /**
     * 指标结构映射编号

     */
    private String structMapId;

    /**
     * 汇总科目映射编号
     */
    private String sumsubMapId;

    /**
     * 资产科目映射编号
     */
    private String assetSubMapId;

    /**
     * 过滤器
     */
    private String filterId;
    
    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 组合代码
     * @param ptlId 组合代码
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId == null ? null : ptlId.trim();
    }

    /**
     * 取得 起始行

     * @return 起始行

     */
    public Long getStartRow() {
        return startRow;
    }

    /**
     * 设置 起始行

     * @param startRow 起始行

     */
    public void setStartRow(Long startRow) {
        this.startRow = startRow;
    }

    /**
     * 取得 科目代码列

     * @return 科目代码列

     */
    public Long getSubjectCodeCol() {
        return subjectCodeCol;
    }

    /**
     * 设置 科目代码列

     * @param subjectCodeCol 科目代码列

     */
    public void setSubjectCodeCol(Long subjectCodeCol) {
        this.subjectCodeCol = subjectCodeCol;
    }

    /**
     * 取得 指标结构映射编号

     * @return 指标结构映射编号

     */
    public String getStructMapId() {
        return structMapId;
    }

    /**
     * 设置 指标结构映射编号

     * @param structMapId 指标结构映射编号

     */
    public void setStructMapId(String structMapId) {
        this.structMapId = structMapId == null ? null : structMapId.trim();
    }

    /**
     * 取得 汇总科目映射编号
     * @return 汇总科目映射编号
     */
    public String getSumsubMapId() {
        return sumsubMapId;
    }

    /**
     * 设置 汇总科目映射编号
     * @param sumsubMapId 汇总科目映射编号
     */
    public void setSumsubMapId(String sumsubMapId) {
        this.sumsubMapId = sumsubMapId == null ? null : sumsubMapId.trim();
    }

    /**
     * 取得 资产科目映射编号
     * @return 资产科目映射编号
     */
    public String getAssetSubMapId() {
        return assetSubMapId;
    }

    /**
     * 设置 资产科目映射编号
     * @param assetSubMapId 资产科目映射编号
     */
    public void setAssetSubMapId(String assetSubMapId) {
        this.assetSubMapId = assetSubMapId == null ? null : assetSubMapId.trim();
    }

	public String getFilterId() {
		return filterId;
	}

	public void setFilterId(String filterId) {
		this.filterId = filterId;
	}
    
}