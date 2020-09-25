package com.joyintech.service.basicdata.mstmarketmanagement.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.MstInnerCreditRating;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class MstMarketManagementDto  extends MstInnerCreditRating implements ProcessEntity{
	 /**
     * 对象类型
     */
	private String oGreadeType;
	

	/**
     * 客户代码
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String customerName;
	/**
	   * 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
	  */
	private String finprodMarketId;
	/**
	 * 金融产品全称
	 */
	private String finprodName;
		/**
	 * 长短期
	 */
	private String shortLongTerm;

	/**
	 * 评级结果
	 */
	private String gradeResult;

	/**
	 * 债券发行人
	 */
	private String secIssueId;

	/**
	 * 录入方式
	 */
	private String inputType;

	/**
	 * null
	 */
	private String isRecommand;
	/**
	 * 债券代码（市场代码）
	 */
	private String secId;

	/**
	 * 评级机构
	 */
	private String gradeOrgId;

	/**
	 * 评级日期
	 */
	private Date gradeDate;

	/**
	 * 评级类型，主体评级、债项评级
	 */
	private String gradeType;
	 
	

	 /**
     * 取对象类型
     */
	 public String getoGreadeType() {
		return oGreadeType;
	}

	 /**
	     * 设置对象类型
	   */

	public void setoGreadeType(String oGreadeType) {
		 
		  this.oGreadeType = oGreadeType == null ? null : oGreadeType.trim();
	}
	 /**
     * 取得 客户代码
     * @return 客户代码
     */
    public String getCustomerId() {
        return customerId;
    }

   

	/**
     * 设置 客户代码
     * @param customerId 客户代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 客户名称
     * @return 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置 客户名称
     * @param customerName 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

	/**
	 * 取得 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
	 * @return 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
	 */
	public String getFinprodMarketId() {
	    return finprodMarketId;
	}

	/**
	 * 设置 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
	 * @param finprodMarketId 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
	 */
	public void setFinprodMarketId(String finprodMarketId) {
	    this.finprodMarketId = finprodMarketId == null ? null : finprodMarketId.trim();
	}
	/**
	 * 取得 金融产品全称
	 * @return 金融产品全称
	 */
	public String getFinprodName() {
	    return finprodName;
	}

	/**
	 * 设置 金融产品全称
	 * @param finprodName 金融产品全称
	 */
	public void setFinprodName(String finprodName) {
	    this.finprodName = finprodName == null ? null : finprodName.trim();
	}
	/**
	 * 取得 债券代码（市场代码）
	 * @return 债券代码（市场代码）
	 */
	public String getSecId() {
	    return secId;
	}

	/**
	 * 设置 债券代码（市场代码）
	 * @param secId 债券代码（市场代码）
	 */
	public void setSecId(String secId) {
	    this.secId = secId == null ? null : secId.trim();
	}

	/**
	 * 取得 评级机构
	 * @return 评级机构
	 */
	public String getGradeOrgId() {
	    return gradeOrgId;
	}

	/**
	 * 设置 评级机构
	 * @param gradeOrgId 评级机构
	 */
	public void setGradeOrgId(String gradeOrgId) {
	    this.gradeOrgId = gradeOrgId == null ? null : gradeOrgId.trim();
	}

	/**
	 * 取得 评级日期
	 * @return 评级日期
	 */
	public Date getGradeDate() {
	    return gradeDate;
	}

	/**
	 * 设置 评级日期
	 * @param gradeDate 评级日期
	 */
	public void setGradeDate(Date gradeDate) {
	    this.gradeDate = gradeDate;
	}

	/**
	 * 取得 评级类型，主体评级、债项评级
	 * @return 评级类型，主体评级、债项评级
	 */
	public String getGradeType() {
	    return gradeType;
	}

	/**
	 * 设置 评级类型，主体评级、债项评级
	 * @param gradeType 评级类型，主体评级、债项评级
	 */
	public void setGradeType(String gradeType) {
	    this.gradeType = gradeType == null ? null : gradeType.trim();
	}

	/**
	 * 取得 长短期
	 * @return 长短期
	 */
	public String getShortLongTerm() {
	    return shortLongTerm;
	}

	/**
	 * 设置 长短期
	 * @param shortLongTerm 长短期
	 */
	public void setShortLongTerm(String shortLongTerm) {
	    this.shortLongTerm = shortLongTerm == null ? null : shortLongTerm.trim();
	}

	/**
	 * 取得 评级结果
	 * @return 评级结果
	 */
	public String getGradeResult() {
	    return gradeResult;
	}

	/**
	 * 设置 评级结果
	 * @param gradeResult 评级结果
	 */
	public void setGradeResult(String gradeResult) {
	    this.gradeResult = gradeResult == null ? null : gradeResult.trim();
	}

	/**
	 * 取得 债券发行人
	 * @return 债券发行人
	 */
	public String getSecIssueId() {
	    return secIssueId;
	}

	/**
	 * 设置 债券发行人
	 * @param secIssueId 债券发行人
	 */
	public void setSecIssueId(String secIssueId) {
	    this.secIssueId = secIssueId == null ? null : secIssueId.trim();
	}

	/**
	 * 取得 录入方式
	 * @return 录入方式
	 */
	public String getInputType() {
	    return inputType;
	}

	/**
	 * 设置 录入方式
	 * @param inputType 录入方式
	 */
	public void setInputType(String inputType) {
	    this.inputType = inputType == null ? null : inputType.trim();
	}

	/**
	 * 取得 null
	 * @return null
	 */
	public String getIsRecommand() {
	    return isRecommand;
	}

	/**
	 * 设置 null
	 * @param isRecommand null
	 */
	public void setIsRecommand(String isRecommand) {
	    this.isRecommand = isRecommand == null ? null : isRecommand.trim();
	}
	}


