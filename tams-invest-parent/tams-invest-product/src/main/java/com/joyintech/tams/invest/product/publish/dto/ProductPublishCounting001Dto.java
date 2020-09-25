package com.joyintech.tams.invest.product.publish.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PrdIssueplanInfo;
import com.joyintech.tams.common.base.entity.PrdPlanDetail;
import com.joyintech.tams.common.base.entity.PrdPlanInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.publish.validator.ProductPublishCountingValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品发行规划试算dto
 * @version 1.0 2018年12月18日
 * @see ProductPublishCounting001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ProductPublishCountingValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class ProductPublishCounting001Dto extends PrdIssueplanInfo implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8291666227730929705L;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;
    
    /**
     * 资产大类
     */
    private String assetType;
    /**
     * 资产大类名称
     */
    private String assetTypeStr;
    
    /**
     * 规划变动值
     */
    private BigDecimal planChangeAmt;

    /**
     * 规划目标值
     */
    private BigDecimal planTargetAmt;
    /**
     * 计算顺序
     */
    private Long order;
    /**
     * 产品规划编号
     */
    private String prdPlanCode;
    /**
     * 产品规划系列编号
     */
    private String prdPlanSerisId;
    /**
     * 产品规划系列名称
     */
    private String prdPlanSerisName;
    /**
     * 配置策略id
     */
    private String strategyId;
    /**
     * 配置规模
     */
    private BigDecimal configScale;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 配置规模
	 */
	private BigDecimal configScale1;
    /**
     * 资产配置比例
     */
    private BigDecimal ratio;
    /**
     * 存续产品系列规划信息列表
     */
    private List<PrdPlanDetail> prdPlanInfoList;
	/**
	 * 是否创新
	 */
	private  String isNewStr;
	/**
	 * 是否创新
	 */
	private  String isNew;
    /**
     * 规划配置策略List
     */
    private List<ProductPublishCounting001Dto> prdIssuePlanStrategyList;
    /**
     * 规划配置策略明细List
     */
    private List<ProductPublishCounting001Dto> prdIssuePlanStrategyDetailList;
    /**
     * 产品大类List
     */
    private List<ProductPublishCounting001Dto> prdIssuePlanAssetList;
    private String vdateStr;
    private String edateStr;
    private String planTargetAmtStr;
    private String planChangeAmtStr;
    private String configScaleStr;
    private String assetConfigScaleStr;
    private String childInfo;
    private String parentInfo;
    private String hasChildren;
	private boolean leaf;

	public boolean getLeaf() {
		return StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), hasChildren);
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
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
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public BigDecimal getPlanChangeAmt() {
		return planChangeAmt;
	}
	public void setPlanChangeAmt(BigDecimal planChangeAmt) {
		this.planChangeAmt = planChangeAmt;
	}
	public BigDecimal getPlanTargetAmt() {
		return planTargetAmt;
	}
	public void setPlanTargetAmt(BigDecimal planTargetAmt) {
		this.planTargetAmt = planTargetAmt;
	}
	public String getAssetTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资产大类, assetType);
	}
	public List<PrdPlanDetail> getPrdPlanInfoList() {
		return prdPlanInfoList;
	}
	public void setPrdPlanInfoList(List<PrdPlanDetail> prdPlanInfoList) {
		this.prdPlanInfoList = prdPlanInfoList;
	}
	public List<ProductPublishCounting001Dto> getPrdIssuePlanStrategyList() {
		return prdIssuePlanStrategyList;
	}
	public void setPrdIssuePlanStrategyList(List<ProductPublishCounting001Dto> prdIssuePlanStrategyList) {
		this.prdIssuePlanStrategyList = prdIssuePlanStrategyList;
	}
	public List<ProductPublishCounting001Dto> getPrdIssuePlanStrategyDetailList() {
		return prdIssuePlanStrategyDetailList;
	}
	public void setPrdIssuePlanStrategyDetailList(List<ProductPublishCounting001Dto> prdIssuePlanStrategyDetailList) {
		this.prdIssuePlanStrategyDetailList = prdIssuePlanStrategyDetailList;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public String getPrdPlanCode() {
		return prdPlanCode;
	}
	public void setPrdPlanCode(String prdPlanCode) {
		this.prdPlanCode = prdPlanCode;
	}
	public String getPrdPlanSerisId() {
		return prdPlanSerisId;
	}
	public void setPrdPlanSerisId(String prdPlanSerisId) {
		this.prdPlanSerisId = prdPlanSerisId;
	}
	public String getPrdPlanSerisName() {
		return prdPlanSerisName;
	}
	public void setPrdPlanSerisName(String prdPlanSerisName) {
		this.prdPlanSerisName = prdPlanSerisName;
	}
	public String getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}
	public BigDecimal getConfigScale() {
		return configScale;
	}
	public void setConfigScale(BigDecimal configScale) {
		this.configScale = configScale;
	}
	public BigDecimal getConfigScale1() {
		return configScale1;
	}
	public void setConfigScale1(BigDecimal configScale1) {
		this.configScale1 = configScale1;
	}

	public BigDecimal getRatio() {
		return ratio;
	}
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	public List<ProductPublishCounting001Dto> getPrdIssuePlanAssetList() {
		return prdIssuePlanAssetList;
	}
	public void setPrdIssuePlanAssetList(List<ProductPublishCounting001Dto> prdIssuePlanAssetList) {
		this.prdIssuePlanAssetList = prdIssuePlanAssetList;
	}
	public String getVdateStr() {
		return vdateStr;
	}
	public void setVdateStr(String vdateStr) {
		this.vdateStr = vdateStr;
	}
	public String getEdateStr() {
		return edateStr;
	}
	public void setEdateStr(String edateStr) {
		this.edateStr = edateStr;
	}
	public String getPlanTargetAmtStr() {
		return planTargetAmtStr;
	}
	public void setPlanTargetAmtStr(String planTargetAmtStr) {
		this.planTargetAmtStr = planTargetAmtStr;
	}
	public String getPlanChangeAmtStr() {
		return planChangeAmtStr;
	}
	public void setPlanChangeAmtStr(String planChangeAmtStr) {
		this.planChangeAmtStr = planChangeAmtStr;
	}
	public String getConfigScaleStr() {
		return configScaleStr;
	}
	public void setConfigScaleStr(String configScaleStr) {
		this.configScaleStr = configScaleStr;
	}
	public String getAssetConfigScaleStr() {
		return assetConfigScaleStr;
	}
	public void setAssetConfigScaleStr(String assetConfigScaleStr) {
		this.assetConfigScaleStr = assetConfigScaleStr;
	}
	public String getChildInfo() {
		return childInfo;
	}
	public void setChildInfo(String childInfo) {
		this.childInfo = childInfo;
	}
	public String getParentInfo() {
		return parentInfo;
	}
	public void setParentInfo(String parentInfo) {
		this.parentInfo = parentInfo;
	}
	public String getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}
	@Override
	@PrimaryKey(value=Field.PK_1)
	public String getPrdIssueplanCode() {
		// TODO Auto-generated method stub
		return super.getPrdIssueplanCode();
	}
	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public void setIsNewStr(String isNewStr) {
		this.isNewStr = isNewStr;
	}
	/**
	 * 获取
	 * @return
	 */
	public String getIsNewStr(){
		if("N".equals(isNew)){
			isNewStr="否";
		}
		if("Y".equals(isNew)){
			isNewStr="是";
		}
		return isNewStr;
	}

	private List<ProductPublishCounting001Dto>children;

	public List<ProductPublishCounting001Dto> getChildren() {
		return children;
	}

	public void setChildren(List<ProductPublishCounting001Dto> children) {
		this.children = children;
	}
}
