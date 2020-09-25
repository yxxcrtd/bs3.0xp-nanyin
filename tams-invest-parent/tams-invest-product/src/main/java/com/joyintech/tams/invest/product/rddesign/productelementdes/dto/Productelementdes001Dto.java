package com.joyintech.tams.invest.product.rddesign.productelementdes.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 产品要素设计查询dto
 *
 * @author jiangyunlong
 * @version 1.0 2019年9月25日
 * @see Productelementdes001Dto
 * @since 1.0
 */
public class Productelementdes001Dto extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8291666227730929705L;
    /**
     *数据状态（有效状态）
     */
    private  String dataStatus;
    /**
     *产品需求编号
     */
    private  String requirementId;
    /**
     *产品形成依据
     */
    private  String requirementBasis;
   /**
    * 需求发起时间
   */
   private Date requiremenDate;
    /**
     * 目标客户
     */
   private String tarCustomer;
    /**
     * 规模
     */
    private String prdScale;
    /**
     * 期限
     */
    private String prdPeriod;
    /**
     * 收益率
     */
    private String prdRate;
    /**
     * 产品详细需求
     */
    private String prdRequirementDetial;
    /**
     * 需求发起端
     */
    private String requirementSource;
    /**
     * 可行性状态（分析状态）
     */
    private String feasibilityStatus;
    /**
     * 可行需求分类
     */
    private String feasibilitType;
    /**
     * 产品设计系列名称
     */
    private String prdDesignSerialName;
    /**
     * 产品基本目标
     */
    private String prdBaseGoal;
    /**
     *产品特点
     */
    private String prdCharater;
    /**
     *募集方式
     */
    private String raiseType;
    /**
     * 投资性质
     */
  private String investNature;
    /**
     * 产品运作模式OPERATE_MODE
     */
    private String operateMode;
    /**
     * 目标客户类型
     */
    private String tarCustomerType;
    /**
     * 目标投资者
     */
    private String tarInvestor;
    /**
     * 产品风险等级
     */
    private String iskLevel;
    /**
     * 开放期描述
     */
    private String openDescribe;
    /**
     * 产品期限
     */
    private String prdTerm;
    /**
     * 计划发行时间
     */
    private String planIssueDate;
    /**
     * 计划发行量
     */
    private String planIssueScale;
    /**
     * 销售地区
     */
    private String saleArea;
    /**
     * 业绩比较基准
     */
    private String baseRuleInfo;
    /**
     * 产品费率
     */
    private String peeRate;
    /**
     * 认购起点
     */
    private String toffStart;
    /**
     * 巨额赎回比例
     */
    private String hugeRedRatto;
    /**
     * 其他
     */
    private String other;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建部门
     */
    private String createDept;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 工作流状态
     */
    private String workFlowId;
    /**
     * 操作
     */
    private String operateType;
    private  String elementId;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDataStatus() {
        return dataStatus;
    }
    public String getDataStatusStr() {
        return  SysDataUtil.getNameFromDataDict(DictCode.有效状态,dataStatus);
    }


    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }
    public String getWorkFlowIdStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.流程状态,workFlowId);
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getRequirementBasis() {
        return requirementBasis;
    }

    public void setRequirementBasis(String requirementBasis) {
        this.requirementBasis = requirementBasis;
    }

    public Date getrequiremenDate() {
        return requiremenDate;
    }

    public void setrequiremenDate(Date requiremenDate) {
        this.requiremenDate = requiremenDate;
    }

    public String getTarCustomer() {
        return tarCustomer;
    }

    public void setTarCustomer(String tarCustomer) {
        this.tarCustomer = tarCustomer;
    }

    public String getPrdScale() {
        return prdScale;
    }

    public void setPrdScale(String prdScale) {
        this.prdScale = prdScale;
    }

    public String getPrdPeriod() {
        return prdPeriod;
    }

    public void setPrdPeriod(String prdPeriod) {
        this.prdPeriod = prdPeriod;
    }

    public String getPrdRate() {
        return prdRate;
    }

    public void setPrdRate(String prdRate) {
        this.prdRate = prdRate;
    }

    public String getPrdRequirementDetial() {
        return prdRequirementDetial;
    }

    public void setPrdRequirementDetial(String prdRequirementDetial) {
        this.prdRequirementDetial = prdRequirementDetial;
    }

    public String getRequirementSource() {
        return requirementSource;
    }
    public String getRequirementSourceStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.需求发起端,requirementSource);
    }

    public void setRequirementSource(String requirementSource) {
        this.requirementSource = requirementSource;
    }

    public String getFeasibilityStatus() {
        return feasibilityStatus;
    }
    public String getFeasibilityStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.可行性状态,feasibilityStatus);
    }

    public void setFeasibilityStatus(String feasibilityStatus) {
        this.feasibilityStatus = feasibilityStatus;
    }

    public String getFeasibilitType() {
        return feasibilitType;
    }
    public String getFeasibilitTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.可行需求分类,feasibilitType);
    }

    public void setFeasibilitType(String feasibilitType) {
        this.feasibilitType = feasibilitType;
    }

    public String getPrdDesignSerialName() {
        return prdDesignSerialName;
    }

    public void setPrdDesignSerialName(String prdDesignSerialName) {
        this.prdDesignSerialName = prdDesignSerialName;
    }

    public String getPrdBaseGoal() {
        return prdBaseGoal;
    }

    public void setPrdBaseGoal(String prdBaseGoal) {
        this.prdBaseGoal = prdBaseGoal;
    }

    public String getPrdCharater() {
        return prdCharater;
    }

    public void setPrdCharater(String prdCharater) {
        this.prdCharater = prdCharater;
    }

    public String getRaiseType() {
        return raiseType;
    }
    public String getRaiseTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.募集方式,raiseType);
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getInvestNature() {
        return investNature;
    }
    public String getInvestNatureStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资性质,investNature);
    }

    public void setInvestNature(String investNature) {
        this.investNature = investNature;
    }

    public String getOperateMode() {
        return operateMode;
    }
    public String getOperateModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品运作模式,operateMode);
    }
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public String getTarCustomerType() {
        return tarCustomerType;
    }
    public String getTarCustomerTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.目标客户类型,tarCustomerType);
    }

    public void setTarCustomerType(String tarCustomerType) {
        this.tarCustomerType = tarCustomerType;
    }

    public String getTarInvestor() {
        return tarInvestor;
    }
    public String getTarInvestorStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.目标投资者,tarInvestor);
    }

    public void setTarInvestor(String tarInvestor) {
        this.tarInvestor = tarInvestor;
    }

    public String getIskLevel() {
        return iskLevel;
    }

    public void setIskLevel(String iskLevel) {
        this.iskLevel = iskLevel;
    }

    public String getOpenDescribe() {
        return openDescribe;
    }

    public void setOpenDescribe(String openDescribe) {
        this.openDescribe = openDescribe;
    }

    public String getPrdTerm() {
        return prdTerm;
    }

    public void setPrdTerm(String prdTerm) {
        this.prdTerm = prdTerm;
    }

    public String getPlanIssueDate() {
        return planIssueDate;
    }

    public void setPlanIssueDate(String planIssueDate) {
        this.planIssueDate = planIssueDate;
    }

    public String getPlanIssueScale() {
        return planIssueScale;
    }

    public void setPlanIssueScale(String planIssueScale) {
        this.planIssueScale = planIssueScale;
    }

    public String getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea;
    }

    public String getBaseRuleInfo() {
        return baseRuleInfo;
    }

    public void setBaseRuleInfo(String baseRuleInfo) {
        this.baseRuleInfo = baseRuleInfo;
    }

    public String getPeeRate() {
        return peeRate;
    }

    public void setPeeRate(String peeRate) {
        this.peeRate = peeRate;
    }

    public String getToffStart() {
        return toffStart;
    }

    public void setToffStart(String toffStart) {
        this.toffStart = toffStart;
    }

    public String getHugeRedRatto() {
        return hugeRedRatto;
    }

    public void setHugeRedRatto(String hugeRedRatto) {
        this.hugeRedRatto = hugeRedRatto;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    @Override
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
