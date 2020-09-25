package com.joyintech.service.product.productplanning.productpublishcounting.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.PrdIssueplanInfo;
import com.joyintech.tams.common.base.entity.PrdPlanInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品发行规划试算dto
 * @version 1.0 2018年12月18日
 * @see ProductPublishCounting001Dto
 * @since 1.0
 */
public class ProductPublishCounting001Dto extends PrdIssueplanInfo implements ProcessEntity,GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8291666227730929705L;
    
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
    private int order;

    private int orders;
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
	 * 是否创新
	 */
	private  String isNew;
    /**
     * 配置规模
     */
    private BigDecimal configScale;
    /**
     * 资产配置比例
     */
    private BigDecimal ratio;
    /**
     * 存续产品系列规划信息列表
     */
    private List<PrdPlanInfo> prdPlanInfoList;
    /**
     * 规划配置策略List
     */
    private List<ProductPublishCounting001Dto> prdIssuePlanStrategyList;
    /**
     * 规划配置策略明细List
     */
    private  String remark;
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
	public List<PrdPlanInfo> getPrdPlanInfoList() {
		return prdPlanInfoList;
	}
	public void setPrdPlanInfoList(List<PrdPlanInfo> prdPlanInfoList) {
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

	@Override
	public String getRowId() {
		return null;
	}

	@Override
	public void setRowId(String s) {

	}

	public int getOrder() {
		return getOrders();
	}
	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String getId() {
		return getChildInfo();
	}

	@Override
	public void setId(String s) {
		this.childInfo = s;
	}

	@Override
	public String getParentId() {
		return getParentInfo();
	}

	@Override
	public void setParentId(String s) {
		this.parentInfo = s;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String s) {

	}

	@Override
	public boolean isLeaf() {
		return StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), hasChildren);
	}

	@Override
	public void setLeaf(boolean b) {

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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}
}
