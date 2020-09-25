/*
 * 文件名：PtlInvPortfolioStrategy001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.rddesign.proddevelopmanage.dto;

import java.util.Date;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PrdResearchSchemeWithBLOBs;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.rddesign.proddevelopmanage.valiator.ProdDevelopManageValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@ValidLogic(targetClass = ProdDevelopManageValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class ProdDevelopManageDto extends PrdResearchSchemeWithBLOBs implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4046652816657600040L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面模式
     */
    private String pageModel;

    /**
     * 产品设计系列名称
     */
    private String prdDesignName;

    /**
     * 更新时间
     */
    private String updateTimeStr;

    /**
     * 生成日
     */
    private String generateTimeStr;

    /**
     * 画面代码
     */
    private String pageId;

    /**
     * 文件代码
     */
    private String fileNo;

    /**
     * 获取 fileNo
     * 
     * @return fileNo.
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置 fileNo
     * 
     * @param fileNo fileNo
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取 generateTimeStr
     * 
     * @return generateTimeStr.
     */
    public String getGenerateTimeStr() {
        return generateTimeStr;
    }

    /**
     * 设置 generateTimeStr
     * 
     * @param generateTimeStr generateTimeStr
     */
    public void setGenerateTimeStr(String generateTimeStr) {
        this.generateTimeStr = generateTimeStr;
    }

    /**
     * 获取 updateTimeStr
     * 
     * @return updateTimeStr.
     */
    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    /**
     * 设置 updateTimeStr
     * 
     * @param updateTimeStr updateTimeStr
     */
    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    /**
     * 获取 prdDesignName
     * 
     * @return prdDesignName.
     */
    public String getPrdDesignName() {
        return prdDesignName;
    }

    /**
     * 设置 prdDesignName
     * 
     * @param prdDesignName prdDesignName
     */
    public void setPrdDesignName(String prdDesignName) {
        this.prdDesignName = prdDesignName;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 取得 生成时间
     * 
     * @return 生成时间
     */
    @Override
    @NotEmpty(fieldName = "ProdDevelopManage001.generateTime", groups = {ValidateGroupEdit.class})
    public Date getGenerateTime() {
        return super.getGenerateTime();
    }

    /**
     * 取得 产品设计编号
     * 
     * @return 产品设计编号
     */
    @Override
    @NotEmpty(fieldName = "ProdDevelopManage001.prdDesignId", groups = {ValidateGroupEdit.class})
    public String getPrdDesignId() {
        return super.getPrdDesignId();
    }

    /**
     * 取得 是否通过,通过，不通过，条件通过
     * 
     * @return 是否通过,通过，不通过，条件通过
     */
    @Override
    @NotEmpty(fieldName = "ProdDevelopManage001.schemeResulr", groups = {ValidateGroupEdit.class})
    public String getSchemeResulr() {
        return super.getSchemeResulr();
    }

    /**
     * 是否通过
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getSchemeResulrStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否通过, super.getSchemeResulr());
    }

    /**
     * 产品风险等级评定结果
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getPrdRiskLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品风险等级, super.getPrdRiskLevel());
    }

    /**
     * 产品可行性分析结果
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getFeasibilityResultStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.可行性状态, super.getFeasibilityResult());
    }

    @PrimaryKey(Field.PK_1)
    @Override
    public String getSeqNo() {
        return super.getSeqNo();
    }
}
