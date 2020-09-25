package com.joyintech.service.valuation.accounadjustmanage.checkcommon.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
/**
 * 对账明细Dto
 * 
 * @author 蒋传志
 * @version 1.0 2018年2月6日
 * @see ProductValuationReconciliationShowDto
 */
public class ProductValuationReconciliationShowDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5798656372374477949L;

    /**
     * 对账结果代码
     */
    private String compareId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 估值日
     */
    private Date compareDate;

    /**
     * 对比类型 本系统对比对方系统 对方系统对比本系统
     */
    private String compareType;

    /**
     * 对比行号
     */
    private Long compareRowno;

    /**
     * 本系统科目号
     */
    private String mySubjectId;

    /**
     * 本系统科目名称
     */
    private String mySubjectName;

    /**
     * 对方系统科目号
     */
    private String othersideSubjectId;

    /**
     * 对方系统科目名称
     */
    private String othersideSubjectName;

    /**
     * 备注
     */
    private String remark;

    /** 对账列结果 */
    private List<ReconcileProductColResult> colResultList;

    /**
     * 取得 对账结果代码
     * 
     * @return 对账结果代码
     */
    public String getCompareId() {
        return compareId;
    }

    /**
     * 设置 对账结果代码
     * 
     * @param compareId 对账结果代码
     */
    public void setCompareId(String compareId) {
        this.compareId = compareId == null ? null : compareId.trim();
    }

    /**
     * 取得 产品代码
     * 
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * 
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
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
     * 取得 估值日
     * 
     * @return 估值日
     */
    public Date getCompareDate() {
        return compareDate;
    }

    /**
     * 设置 估值日
     * 
     * @param compareDate 估值日
     */
    public void setCompareDate(Date compareDate) {
        this.compareDate = compareDate;
    }

    /**
     * 取得 对比类型 本系统对比对方系统 对方系统对比本系统
     * 
     * @return 对比类型 本系统对比对方系统 对方系统对比本系统
     */
    public String getCompareType() {
        return compareType;
    }

    /**
     * 设置 对比类型 本系统对比对方系统 对方系统对比本系统
     * 
     * @param compareType 对比类型 本系统对比对方系统 对方系统对比本系统
     */
    public void setCompareType(String compareType) {
        this.compareType = compareType == null ? null : compareType.trim();
    }

    /**
     * 取得 对比行号
     * 
     * @return 对比行号
     */
    public Long getCompareRowno() {
        return compareRowno;
    }

    /**
     * 设置 对比行号
     * 
     * @param compareRowno 对比行号
     */
    public void setCompareRowno(Long compareRowno) {
        this.compareRowno = compareRowno;
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
     * 取得 本系统科目名称
     * 
     * @return 本系统科目名称
     * @since 1.0
     */
    public String getMySubjectName() {
        return mySubjectName;
    }

    /**
     * 设置 本系统科目名称
     * 
     * @param mySubjectName 本系统科目名称
     * @since 1.0
     */
    public void setMySubjectName(String mySubjectName) {
        this.mySubjectName = mySubjectName;
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
     * 取得 备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 对账列结果
     * 
     * @return 对账列结果
     */
    public List<ReconcileProductColResult> getColResultList() {
        return colResultList;
    }

    /**
     * 设置 对账列结果
     * 
     * @param colResultList 对账列结果
     */
    public void setColResultList(List<ReconcileProductColResult> colResultList) {
        this.colResultList = colResultList;
    }

}
