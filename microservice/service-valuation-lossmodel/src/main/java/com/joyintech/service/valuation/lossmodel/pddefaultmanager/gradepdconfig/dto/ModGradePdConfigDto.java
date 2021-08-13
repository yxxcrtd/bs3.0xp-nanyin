/*
 * 文件名：ModGradePdConfigDto.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */

package com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;


public class ModGradePdConfigDto extends BaseEntity implements ITableRow, ProcessEntity  {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -904100591006148547L;


    /**
     * 流水号
     */
    private String seqNo;


    /**
     * 年份
     */
    private String vouchYear;


    /**
     * 评级结果（数据字典 评级结果）
     */
    private String gradeResult;

    /**
     * 前瞻前年度边际
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal forwardYearPd;

    /**
     * 评级PD类型
     */
    private String pdConfigType;

    /**
     * 操作类型
     */
    private String operateType;

    private String pageModel;

    /**
     * 画面Id
     */
    private String pageId;

    /**
     * elementId
     */
    private String elementId;

    /**
     * 导入是否覆盖 1覆盖，0不覆盖忽略
     */
    private String type;


    public String getPdConfigType() {
        return pdConfigType;
    }

    public void setPdConfigType(String pdConfigType) {
        this.pdConfigType = pdConfigType;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    public BigDecimal getForwardYearPd() {
        return forwardYearPd;
    }

    public void setForwardYearPd(BigDecimal forwardYearPd) {
        this.forwardYearPd = forwardYearPd;
    }

    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getVouchYear() {
        return vouchYear;
    }

    public void setVouchYear(String vouchYear) {
        this.vouchYear = vouchYear == null ? null : vouchYear.trim();
    }


    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult == null ? null : gradeResult.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
