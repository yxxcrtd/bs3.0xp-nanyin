/*
 * 文件名：PtlInvPortfolioStrategy001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.rddesgin.proddevelopmanage.dto;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.MockResultInfo;
import com.joyintech.tams.common.base.entity.PrdResearchSchemeWithBLOBs;
import com.joyintech.tams.common.base.entity.ProductBaseInfo;
import com.joyintech.tams.constant.DictCode;

public class ProdDevelopManageDto extends PrdResearchSchemeWithBLOBs {

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
     * 产品要素设计信息
     */
    private ProductBaseInfo productBaseInfo;

    /**
     * 模拟试算结果
     */
    private MockResultInfo mockResultInfo;

    /**
     * 更新时间
     */
    private String updateTimeStr;

    /**
     * 生成日
     */
    private String generateTimeStr;

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
     * 获取 mockResultInfo
     * 
     * @return mockResultInfo.
     */
    public MockResultInfo getMockResultInfo() {
        return mockResultInfo;
    }

    /**
     * 设置 mockResultInfo
     * 
     * @param mockResultInfo mockResultInfo
     */
    public void setMockResultInfo(MockResultInfo mockResultInfo) {
        this.mockResultInfo = mockResultInfo;
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
     * 获取 productBaseInfo
     * 
     * @return productBaseInfo.
     */
    public ProductBaseInfo getProductBaseInfo() {
        return productBaseInfo;
    }

    /**
     * 设置 productBaseInfo
     * 
     * @param productBaseInfo productBaseInfo
     */
    public void setProductBaseInfo(ProductBaseInfo productBaseInfo) {
        this.productBaseInfo = productBaseInfo;
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
     * 产品要素设计信息Object>>String
     * 
     * @return 产品要素设计信息
     * @see
     * @since 1.0
     */
    public String getPrdDesignResultStr() {
        StringBuilder sb = new StringBuilder();
        if (StringUtil.isNotEmpty(productBaseInfo)) {
            sb.append("产品设计编号:" + productBaseInfo.getPrdDesignId() + GlobalConstant.SEMICOLON);
            sb.append("产品需求编号:" + productBaseInfo.getRequirementId() + GlobalConstant.SEMICOLON);
            sb.append(
                "产品设计系列名称:" + productBaseInfo.getPrdDesignSerialName() + GlobalConstant.SEMICOLON);
            sb.append("产品基本目标:" + productBaseInfo.getPrdBaseGoal() + GlobalConstant.SEMICOLON);
            sb.append("产品特点:" + productBaseInfo.getRequirementId() + GlobalConstant.SEMICOLON);
            sb.append("募集方式:" + SysDataUtil.getNameFromDataDict(DictCode.募集方式,
                productBaseInfo.getRaiseType()) + GlobalConstant.SEMICOLON);
            sb.append("投资性质:" + SysDataUtil.getNameFromDataDict(DictCode.投资性质,
                productBaseInfo.getInvestNature()) + GlobalConstant.SEMICOLON);
            sb.append("产品运作模式:" + SysDataUtil.getNameFromDataDict(DictCode.产品运作模式,
                productBaseInfo.getOperateMode()) + GlobalConstant.SEMICOLON);
            sb.append("目标客户类型:" + SysDataUtil.getNameFromDataDict(DictCode.目标客户类型,
                productBaseInfo.getTarCustomerType()) + GlobalConstant.SEMICOLON);
            sb.append("目标投资者:" + productBaseInfo.getTarInvestor() + GlobalConstant.SEMICOLON);
            sb.append("产品风险等级:" + SysDataUtil.getNameFromDataDict(DictCode.产品风险等级,
                productBaseInfo.getRskLevel()) + GlobalConstant.SEMICOLON);
            sb.append("开放期描述:" + productBaseInfo.getOpenDescribe() + GlobalConstant.SEMICOLON);
            sb.append("产品期限:" + productBaseInfo.getPrdTerm() + GlobalConstant.SEMICOLON);
            sb.append("计划发行时间:" + productBaseInfo.getPlanIssueDate() + GlobalConstant.SEMICOLON);
            sb.append("计划发行量:" + productBaseInfo.getPlanIssueScale() + GlobalConstant.SEMICOLON);
            sb.append("销售地区:" + productBaseInfo.getSaleArea() + GlobalConstant.SEMICOLON);
            sb.append("业绩比较基准:" + productBaseInfo.getBaseRuleInfo() + GlobalConstant.SEMICOLON);
            sb.append("产品费率:" + productBaseInfo.getFeeRate() + GlobalConstant.SEMICOLON);

            sb.append("认购起点:" + productBaseInfo.getToffStart() + GlobalConstant.SEMICOLON);
            sb.append("巨额赎回比例:" + productBaseInfo.getHugeRedRatio() + GlobalConstant.SEMICOLON);
            sb.append("投资资产及比例情况:" + productBaseInfo.getInvAssetRate() + GlobalConstant.SEMICOLON);
            sb.append(
                "意向投资策略:" + productBaseInfo.getIntenInvStrategy() + GlobalConstant.SEMICOLON);
            sb.append("其他:" + productBaseInfo.getOther() + GlobalConstant.SEMICOLON);
            /*sb.append("数据状态:"
                      + SysDataUtil.getNameFromDataDict(DictCode.数据状态, productBaseInfo.getStatus())
                      + GlobalConstant.SEMICOLON);
            sb.append("创建人:" + productBaseInfo.getCreateUser() + GlobalConstant.SEMICOLON);
            sb.append("创建部门:" + productBaseInfo.getCreateDept() + GlobalConstant.SEMICOLON);
            sb.append("创建时间:" + DateUtil.format(productBaseInfo.getCreateTime(),
                DateUtil.DEFAULT_DATETIME_FORMAT) + GlobalConstant.SEMICOLON);
            sb.append("更新人:" + productBaseInfo.getUpdateUser() + GlobalConstant.SEMICOLON);
            sb.append("更新时间:" + DateUtil.format(productBaseInfo.getUpdateTime(),
                DateUtil.DEFAULT_DATETIME_FORMAT) + GlobalConstant.SEMICOLON);*/
        }
        return sb.toString();
    }

    /**
     * 模拟试算结果Object>>String
     * 
     * @return 模拟试算结果
     * @see
     * @since 1.0
     */
    public String getPrdMockResultStr() {
        StringBuilder sb = new StringBuilder();
        if (StringUtil.isNotEmpty(mockResultInfo)) {
            /*guyunfei 20191111 BSTOZ-1235 产品研发方案管理bug start*/
            /*sb.append("流水号:" + mockResultInfo.getSeqNo() + GlobalConstant.SEMICOLON);
            sb.append("生成时间:" + DateUtil.format(mockResultInfo.getGenerateTime(),
                DateUtil.DEFAULT_DATETIME_FORMAT) + GlobalConstant.SEMICOLON);
            sb.append(
                "测算日:" + DateUtil.format(mockResultInfo.getMockDate()) + GlobalConstant.SEMICOLON);
            sb.append("产品设计系列编号:" + mockResultInfo.getPrdDesignId() + GlobalConstant.SEMICOLON);
            sb.append("产品规模:" + mockResultInfo.getPrdScale() + GlobalConstant.SEMICOLON);
            sb.append("产品期限:" + mockResultInfo.getPrdPeriod() + GlobalConstant.SEMICOLON);*/
            sb.append("收益率上限:" + mockResultInfo.getRateMax() + GlobalConstant.SEMICOLON);
            sb.append("收益率下限:" + mockResultInfo.getRateMix() + GlobalConstant.SEMICOLON);
            /*sb.append("需求编号:" + mockResultInfo.getRequirementId() + GlobalConstant.SEMICOLON);
            sb.append("批次号:" + mockResultInfo.getBatchId() + GlobalConstant.SEMICOLON);
            sb.append("数据状态:"
                      + SysDataUtil.getNameFromDataDict(DictCode.数据状态, mockResultInfo.getStatus())
                      + GlobalConstant.SEMICOLON);
            sb.append("创建人:" + mockResultInfo.getCreateUser() + GlobalConstant.SEMICOLON);
            sb.append("创建部门:" + mockResultInfo.getCreateDept() + GlobalConstant.SEMICOLON);
            sb.append("创建时间:" + DateUtil.format(mockResultInfo.getCreateTime(),
                DateUtil.DEFAULT_DATETIME_FORMAT) + GlobalConstant.SEMICOLON);
            sb.append("更新人:" + mockResultInfo.getUpdateUser() + GlobalConstant.SEMICOLON);
            sb.append("更新时间:" + DateUtil.format(mockResultInfo.getUpdateTime(),
                DateUtil.DEFAULT_DATETIME_FORMAT) + GlobalConstant.SEMICOLON);*/
            /*guyunfei 20191111 BSTOZ-1235 产品研发方案管理bug end*/
        }
        return sb.toString();
    }
}
