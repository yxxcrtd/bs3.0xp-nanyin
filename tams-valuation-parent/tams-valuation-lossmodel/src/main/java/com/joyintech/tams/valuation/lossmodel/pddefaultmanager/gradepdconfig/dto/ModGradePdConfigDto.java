/*
 * 文件名：ModGradePdConfigDto.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */

package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:评级PD维护实体类
 * @auther: yuzj
 * @date: 2021/7/1
 * @param:
 * @return:
 */
public class ModGradePdConfigDto  extends BaseEntity implements ITableRow, ProcessEntity  {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;


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
    private BigDecimal forwardYearPd;
    /**
     * 评级机构名称
     */
    private String gradeOrgName;
    /**
     * 评级类型名称
     */
    private String oTypeName;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 画面模式
     */
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
     * 选中的数据
     */
    private transient List<ModGradePdConfigDto> selectedItem;

    private String type;//导入是否覆盖 1覆盖，0不覆盖忽略



    public String getGradeOrgName() {
        return gradeOrgName;
    }

    public void setGradeOrgName(String gradeOrgName) {
        this.gradeOrgName = gradeOrgName;
    }

    public String getoTypeName() {
        return oTypeName;
    }

    public void setoTypeName(String oTypeName) {
        this.oTypeName = oTypeName;
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


    @NotNull(message = "前瞻前年度边际PD%不能为空!", groups = ValidateGroupEdit.class)
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

    @NotBlank(message = "年份不能为空！", groups = {ValidateGroupEdit.class})
    public String getVouchYear() {
        return vouchYear;
    }

    public void setVouchYear(String vouchYear) {
        this.vouchYear = vouchYear == null ? null : vouchYear.trim();
    }


    @NotBlank(message = "评级结果不能为空！", groups = {ValidateGroupEdit.class})
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

    public List<ModGradePdConfigDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ModGradePdConfigDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    public String getGradeResultStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.内部评级, this.getGradeResult());
    }
}
