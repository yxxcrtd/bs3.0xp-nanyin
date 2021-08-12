package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linesupervision.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditRuleInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.validator.CreditRuleValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@PageId("LineSupervision001")
@ValidLogic(targetClass = CreditRuleValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class LineSupervisionDto extends CreditLines implements ProcessEntity{

    /**
     * 画面处理模式
     */
    private String pageModel;

   /**
	* 选中列表
	*/
	 private List<LineSupervisionDto> selectedItem;
	
	 public List<LineSupervisionDto> getSelectedItem() {
		return selectedItem;
	 }

	 public void setSelectedItem(List<LineSupervisionDto> selectedItem) {
		this.selectedItem = selectedItem;
	 }
	
    public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
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

	/**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;

    /**
     * 额度名称
     */
    private String creditName;

    /**
     * 主体名称
     */
    private String subjectName;

	/**
	 * 最长可投期限
	 */
	private String maxTimeLimit;

	/**
	 * 预警比例
	 */
	private String warningRatio;

	/**
	 * 可用额度
	 */
	private String availableCredit;

	/**
	 * 调整额度
	 */
	private String adjustCredit;

	/**
	 * 交易面值
	 */
	private String tradeFace;

	/**
	 * 机构类型
	 */
	private String orgType;

	/**
	 * 机构层级
	 */
	private String orgTier;

	/**
	 * 监控状态
	 */
	private String supStatus;

	/**
	 * 监控状态名称
	 */
	private String supStatusName;

	/**
	 * 资产名称
	 */
	private String finprodName;

	/**
	 * 资产代码
	 */
	private String finprodId;

	/**
	 * 交易编号
	 */
	private String tradeId;

	/**
	 * 额度标签
	 */
	private String creditTagName;

	/**
	 * 占用额度
	 */
	private String usingCredit;

	/**
	 * 调整金额
	 */
	private String adjustAmt;

	/**
	 * 扣减规则
	 */
	private String deductRule;

	/**
	 * 交易日期
	 */
	private String tradeDate;

	/**
	 * 调整后到期日期
	 */
	private String adjustVdate;

	/**
	 * 期限超限
	 */
	private String timeLimitOut;

	/**
	 * 交易币种
	 */
	private String tradeCcy;

	/**
	 * 折算汇率
	 */
	private String discountRate;

	/**
	 * 资管计划代码
	 */
	private String astPlanId;

	/**
	 * 资管计划名称
	 */
	private String astPlanName;

	/**
	 * 获取 额度名称
	 *
	 * @return creditName 额度名称
	 */
	public String getCreditName() {
		return SysDataUtil.getNameFromDataDict(DictCode.额度分类, super.getCreditType());
	}

	/**
	 * 设置 额度名称
	 *
	 * @param creditName 额度名称
	 */
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	/**
	 * 获取 最长可投期限
	 *
	 * @return maxTimeLimit 最长可投期限
	 */
	public String getMaxTimeLimit() {
		return this.maxTimeLimit;
	}

	/**
	 * 设置 最长可投期限
	 *
	 * @param maxTimeLimit 最长可投期限
	 */
	public void setMaxTimeLimit(String maxTimeLimit) {
		this.maxTimeLimit = maxTimeLimit;
	}

	/**
	 * 获取 预警比例
	 *
	 * @return warningRatio 预警比例
	 */
	public String getWarningRatio() {
		return this.warningRatio;
	}

	/**
	 * 设置 预警比例
	 *
	 * @param warningRatio 预警比例
	 */
	public void setWarningRatio(String warningRatio) {
		this.warningRatio = warningRatio;
	}

	/**
	 * 获取 可用额度
	 *
	 * @return availableCredit 可用额度
	 */
	public String getAvailableCredit() {
		return this.availableCredit;
	}

	/**
	 * 设置 可用额度
	 *
	 * @param availableCredit 可用额度
	 */
	public void setAvailableCredit(String availableCredit) {
		this.availableCredit = availableCredit;
	}

	/**
	 * 获取 调整额度
	 *
	 * @return adjustCredit 调整额度
	 */
	public String getAdjustCredit() {
		return this.adjustCredit;
	}

	/**
	 * 设置 调整额度
	 *
	 * @param adjustCredit 调整额度
	 */
	public void setAdjustCredit(String adjustCredit) {
		this.adjustCredit = adjustCredit;
	}

	/**
	 * 获取 交易面值
	 *
	 * @return tradeFace 交易面值
	 */
	public String getTradeFace() {
		return this.tradeFace;
	}

	/**
	 * 设置 交易面值
	 *
	 * @param tradeFace 交易面值
	 */
	public void setTradeFace(String tradeFace) {
		this.tradeFace = tradeFace;
	}

	/**
	 * 获取 监控状态
	 *
	 * @return supStatus 监控状态
	 */
	public String getSupStatus() {
		return this.supStatus;
	}

	/**
	 * 设置 监控状态
	 *
	 * @param supStatus 监控状态
	 */
	public void setSupStatus(String supStatus) {
		this.supStatus = supStatus;
	}

	/**
	 * 获取 监控状态名称
	 *
	 * @return supStatusName 监控状态名称
	 */
	public String getSupStatusName() {
		return SysDataUtil.getNameFromDataDict(DictCode.监控状态, this.getSupStatus());
	}

	/**
	 * 设置 监控状态名称
	 *
	 * @param supStatusName 监控状态名称
	 */
	public void setSupStatusName(String supStatusName) {
		this.supStatusName = supStatusName;
	}


	/**
	 * 获取 主体名称
	 *
	 * @return subjectName 主体名称
	 */
	public String getSubjectName() {
		return this.subjectName;
	}

	/**
	 * 设置 主体名称
	 *
	 * @param subjectName 主体名称
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	/**
	 * 获取 资产名称
	 *
	 * @return finprodName 资产名称
	 */
	public String getFinprodName() {
		return this.finprodName;
	}

	/**
	 * 设置 资产名称
	 *
	 * @param finprodName 资产名称
	 */
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	/**
	 * 获取 资产代码
	 *
	 * @return finprodId 资产代码
	 */
	public String getFinprodId() {
		return this.finprodId;
	}

	/**
	 * 设置 资产代码
	 *
	 * @param finprodId 资产代码
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	/**
	 * 获取 交易编号
	 *
	 * @return tradeId 交易编号
	 */
	public String getTradeId() {
		return this.tradeId;
	}

	/**
	 * 设置 交易编号
	 *
	 * @param tradeId 交易编号
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * 获取 额度标签
	 *
	 * @return creditTagName 额度标签
	 */
	public String getCreditTagName() {
		return this.creditTagName;
	}

	/**
	 * 设置 额度标签
	 *
	 * @param creditTagName 额度标签
	 */
	public void setCreditTagName(String creditTagName) {
		this.creditTagName = creditTagName;
	}

	/**
	 * 获取 占用额度
	 *
	 * @return usingCredit 占用额度
	 */
	public String getUsingCredit() {
		return this.usingCredit;
	}

	/**
	 * 设置 占用额度
	 *
	 * @param usingCredit 占用额度
	 */
	public void setUsingCredit(String usingCredit) {
		this.usingCredit = usingCredit;
	}

	/**
	 * 获取 调整金额
	 *
	 * @return adjustAmt 调整金额
	 */
	public String getAdjustAmt() {
		return this.adjustAmt;
	}

	/**
	 * 设置 调整金额
	 *
	 * @param adjustAmt 调整金额
	 */
	public void setAdjustAmt(String adjustAmt) {
		this.adjustAmt = adjustAmt;
	}

	/**
	 * 获取 扣减规则
	 *
	 * @return deductRule 扣减规则
	 */
	public String getDeductRule() {
		return this.deductRule;
	}

	/**
	 * 设置 扣减规则
	 *
	 * @param deductRule 扣减规则
	 */
	public void setDeductRule(String deductRule) {
		this.deductRule = deductRule;
	}

	/**
	 * 获取 交易日期
	 *
	 * @return tradeDate 交易日期
	 */
	public String getTradeDate() {
		return this.tradeDate;
	}

	/**
	 * 设置 交易日期
	 *
	 * @param tradeDate 交易日期
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * 获取 调整后到期日期
	 *
	 * @return adjustVdate 调整后到期日期
	 */
	public String getAdjustVdate() {
		return this.adjustVdate;
	}

	/**
	 * 设置 调整后到期日期
	 *
	 * @param adjustVdate 调整后到期日期
	 */
	public void setAdjustVdate(String adjustVdate) {
		this.adjustVdate = adjustVdate;
	}

	/**
	 * 获取 期限超限
	 *
	 * @return timeLimitOut 期限超限
	 */
	public String getTimeLimitOut() {
		return this.timeLimitOut;
	}

	/**
	 * 设置 期限超限
	 *
	 * @param timeLimitOut 期限超限
	 */
	public void setTimeLimitOut(String timeLimitOut) {
		this.timeLimitOut = timeLimitOut;
	}

	/**
	 * 获取 交易币种
	 *
	 * @return tradeCcy 交易币种
	 */
	public String getTradeCcy() {
		return this.tradeCcy;
	}

	/**
	 * 设置 交易币种
	 *
	 * @param tradeCcy 交易币种
	 */
	public void setTradeCcy(String tradeCcy) {
		this.tradeCcy = tradeCcy;
	}

	/**
	 * 获取 折算汇率
	 *
	 * @return discountRate 折算汇率
	 */
	public String getDiscountRate() {
		return this.discountRate;
	}

	/**
	 * 设置 折算汇率
	 *
	 * @param discountRate 折算汇率
	 */
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	/**
	 * 获取 资管计划代码
	 *
	 * @return astPlanId 资管计划代码
	 */
	public String getAstPlanId() {
		return this.astPlanId;
	}

	/**
	 * 设置 资管计划代码
	 *
	 * @param astPlanId 资管计划代码
	 */
	public void setAstPlanId(String astPlanId) {
		this.astPlanId = astPlanId;
	}

	/**
	 * 获取 资管计划名称
	 *
	 * @return astPlanName 资管计划名称
	 */
	public String getAstPlanName() {
		return this.astPlanName;
	}

	/**
	 * 设置 资管计划名称
	 *
	 * @param astPlanName 资管计划名称
	 */
	public void setAstPlanName(String astPlanName) {
		this.astPlanName = astPlanName;
	}


	/**
	 * 获取 机构类型
	 *
	 * @return orgType 机构类型
	 */
	public String getOrgType() {
		return this.orgType;
	}

	/**
	 * 设置 机构类型
	 *
	 * @param orgType 机构类型
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * 获取 机构层级
	 *
	 * @return orgTier 机构层级
	 */
	public String getOrgTier() {
		return this.orgTier;
	}

	/**
	 * 设置 机构层级
	 *
	 * @param orgTier 机构层级
	 */
	public void setOrgTier(String orgTier) {
		this.orgTier = orgTier;
	}
}