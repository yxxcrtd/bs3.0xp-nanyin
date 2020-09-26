package com.joyintech.tams.svreport.chb.prodraisetotalregist.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;

/**
 * 描述:CHB_PRD_RAISETOT_SUB表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-22
 */
@TableName("CHB_PRD_RAISETOT_SUB")
public class RaiseTotalRegist001Dto extends BaseEntity implements ITableRow {
	/**
	 * 发行状态
	 */
	private String issuingStatus;
	
	private String prodRegCode;
    /**
     * 登记序列
     */
    private String registSeqno;

    /**
     * 产品代码
     */
    @BusinessKey("PROD_ID")
    private String prodId;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 个人投资者总数
     */
    private Integer singleTotal;

    /**
     * 法人投资者总数
     */
    private Integer legalTotal;

    /**
     * 非法人投资者总数
     */
    private Integer unlegalTotal;

    /**
     * 认购币种及金额
     */
    private String subscribeCcyAmt;

    /**
     * 产品销售区域及募集金额
     */
    private String saleAreaAmt;

    /**
     * 实际募集金额
     */
    private BigDecimal totalAmt;

    /**
     * 募集总份额
     */
    private BigDecimal raiseQty;

    /**
     * 是否代销 参照数据字典
     */
    @DictFormat(DictCode.是否_中债)
    private String isConsign;

    /**
     * 代销总金额
     */
    private BigDecimal consignAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 报送状态
     */
    private String sendStatus;
    
	/**
     * 反馈状态
     */
    @DictFormat(DictCode.报送反馈状态)
    private String submitFeedbackStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 数据状态 参照数据字典:STATUS
     */
    private String status;

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
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;
    //产品名称
    private String prodName;
    //产品登记编码
    private String regCode;
    //报送截止日开始
    private Date submitendDateStart;
    //报送截止日结束
    private Date submitendDateEnd;
    //数据日期开始
    private Date dataDateStart;
    //数据日期结束
    private Date dataDateEnd;
    //报送日期开始
    private Date submitDateStart;
    //报送日期结束
    private Date submitDateEnd;
    //目标客户类型（个人，机构）
    private String saletarget;
    //募集结束日
    private Date raiseEnddate;
    //募集总量已登记序列
    private String regseq;
    //募集总量未登记序列
    private String unRegseq;
	private List<ChbPrdPosition> poslist;
    
    public String getRegseq() {
		return regseq;
	}

	public void setRegseq(String regseq) {
		this.regseq = regseq;
	}

	public String getUnRegseq() {
		return unRegseq;
	}

	public void setUnRegseq(String unRegseq) {
		this.unRegseq = unRegseq;
	}

	public Date getRaiseEnddate() {
		return raiseEnddate;
	}

	public void setRaiseEnddate(Date raiseEnddate) {
		this.raiseEnddate = raiseEnddate;
	}

	public String getSaletarget() {
		return saletarget;
	}

	public void setSaletarget(String saletarget) {
		this.saletarget = saletarget;
	}
	private List<ChbPrdDeal> deallist;
    
    private String fprodId;
    
    public String getFprodId() {
		return fprodId;
	}

	public void setFprodId(String fprodId) {
		this.fprodId = fprodId;
	}

	public List<ChbPrdDeal> getDeallist() {
		return deallist;
	}

	public void setDeallist(List<ChbPrdDeal> deallist) {
		this.deallist = deallist;
	}

	public Date getSubmitendDateStart() {
		return submitendDateStart;
	}

	public void setSubmitendDateStart(Date submitendDateStart) {
		this.submitendDateStart = submitendDateStart;
	}

	public Date getSubmitendDateEnd() {
		return submitendDateEnd;
	}

	public void setSubmitendDateEnd(Date submitendDateEnd) {
		this.submitendDateEnd = submitendDateEnd;
	}

	public Date getDataDateStart() {
		return dataDateStart;
	}

	public void setDataDateStart(Date dataDateStart) {
		this.dataDateStart = dataDateStart;
	}

	public Date getDataDateEnd() {
		return dataDateEnd;
	}

	public void setDataDateEnd(Date dataDateEnd) {
		this.dataDateEnd = dataDateEnd;
	}

	public Date getSubmitDateStart() {
		return submitDateStart;
	}

	public void setSubmitDateStart(Date submitDateStart) {
		this.submitDateStart = submitDateStart;
	}

	public Date getSubmitDateEnd() {
		return submitDateEnd;
	}

	public void setSubmitDateEnd(Date submitDateEnd) {
		this.submitDateEnd = submitDateEnd;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	/**
     * 登记序列
     * @return REGIST_SEQNO 登记序列
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 登记序列
     * @param registSeqno 登记序列
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 个人投资者总数
     * @return SINGLE_TOTAL 个人投资者总数
     */
    public Integer getSingleTotal() {
        return singleTotal;
    }

    /**
     * 个人投资者总数
     * @param singleTotal 个人投资者总数
     */
    public void setSingleTotal(Integer singleTotal) {
        this.singleTotal = singleTotal;
    }

    /**
     * 法人投资者总数
     * @return LEGAL_TOTAL 法人投资者总数
     */
    public Integer getLegalTotal() {
        return legalTotal;
    }

    /**
     * 法人投资者总数
     * @param legalTotal 法人投资者总数
     */
    public void setLegalTotal(Integer legalTotal) {
        this.legalTotal = legalTotal;
    }

    /**
     * 非法人投资者总数
     * @return UNLEGAL_TOTAL 非法人投资者总数
     */
    public Integer getUnlegalTotal() {
        return unlegalTotal;
    }

    /**
     * 非法人投资者总数
     * @param unlegalTotal 非法人投资者总数
     */
    public void setUnlegalTotal(Integer unlegalTotal) {
        this.unlegalTotal = unlegalTotal;
    }

    /**
     * 认购币种及金额
     * @return SUBSCRIBE_CCY_AMT 认购币种及金额
     */
    public String getSubscribeCcyAmt() {
        return subscribeCcyAmt;
    }

    /**
     * 认购币种及金额
     * @param subscribeCcyAmt 认购币种及金额
     */
    public void setSubscribeCcyAmt(String subscribeCcyAmt) {
        this.subscribeCcyAmt = subscribeCcyAmt == null ? null : subscribeCcyAmt.trim();
    }

    /**
     * 产品销售区域及募集金额
     * @return SALE_AREA_AMT 产品销售区域及募集金额
     */
    public String getSaleAreaAmt() {
        return saleAreaAmt;
    }

    /**
     * 产品销售区域及募集金额
     * @param saleAreaAmt 产品销售区域及募集金额
     */
    public void setSaleAreaAmt(String saleAreaAmt) {
        this.saleAreaAmt = saleAreaAmt == null ? null : saleAreaAmt.trim();
    }

    /**
     * 实际募集金额
     * @return TOTAL_AMT 实际募集金额
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    /**
     * 实际募集金额
     * @param totalAmt 实际募集金额
     */
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    /**
     * 募集总份额
     * @return RAISE_QTY 募集总份额
     */
    public BigDecimal getRaiseQty() {
        return raiseQty;
    }

    /**
     * 募集总份额
     * @param raiseQty 募集总份额
     */
    public void setRaiseQty(BigDecimal raiseQty) {
        this.raiseQty = raiseQty;
    }

    /**
     * 是否代销 参照数据字典
     * @return IS_CONSIGN 是否代销 参照数据字典
     */
    public String getIsConsign() {
        return isConsign;
    }

    /**
     * 是否代销 参照数据字典
     * @param isConsign 是否代销 参照数据字典
     */
    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign == null ? null : isConsign.trim();
    }

    /**
     * 代销总金额
     * @return CONSIGN_AMT 代销总金额
     */
    public BigDecimal getConsignAmt() {
        return consignAmt;
    }

    /**
     * 代销总金额
     * @param consignAmt 代销总金额
     */
    public void setConsignAmt(BigDecimal consignAmt) {
        this.consignAmt = consignAmt;
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 报送状态
     * @return SEND_STATUS 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 反馈状态
     * @return SUBMIT_FEEDBACK_STATUS 反馈状态
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 反馈状态
     * @param submitFeedbackStatus 反馈状态
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 数据日期
     * @return STATUS_DATE 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 数据状态 参照数据字典:STATUS
     * @return STATUS 数据状态 参照数据字典:STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态 参照数据字典:STATUS
     * @param status 数据状态 参照数据字典:STATUS
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 报送日期
     * @return SUBMIT_TIME 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 报送日期
     * @param submitTime 报送日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 中债报送截止日
     * @return CHB_SUBMIT_DEADLINE 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 行内报送截止日
     * @return SUBMIT_DEADLINE 行内报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 行内报送截止日
     * @param submitDeadline 行内报送截止日
     */
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }
    //报送状态
    public String getSendStatusStr() {
		return SvrDataUtil.getNameFromDataDict(DictCode.报送状态,getSendStatus());
	}
    //是否代销
    public String getIsConsignStr() {
		return getIsConsign()==null?null:getIsConsign()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债,getIsConsign());
	}
    //反馈状态
    public String getSubmitFeedbackStatusStr() {
		return SvrDataUtil.getNameFromDataDict(DictCode.报送反馈状态,getSubmitFeedbackStatus());
	}
    //数据状态
    public String getStatusStr() {
		return SvrDataUtil.getNameFromDataDict(DictCode.报送数据状态,getStatus());
	}
	public List<ChbPrdPosition> getPoslist() {
		return poslist;
	}

	public void setPoslist(List<ChbPrdPosition> poslist) {
		this.poslist = poslist;
	}

	public String getProdRegCode() {
		return prodRegCode;
	}

	public void setProdRegCode(String prodRegCode) {
		this.prodRegCode = prodRegCode;
	}

	public String getIssuingStatus() {
		return issuingStatus;
	}

	public void setIssuingStatus(String issuingStatus) {
		this.issuingStatus = issuingStatus;
	}
	
}