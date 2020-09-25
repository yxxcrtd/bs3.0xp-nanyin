package com.jixu.msg.dto;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class ValManualVoucherDetailDto {

    /**
     * 	流水号
     */
    private String seqno;

    /**
     * 	账套代码
     */
    private String booksetId;

    /**
     * 	科目号
     */
    private String subjectNo;

    /**
     * 	科目名称
     */
    private String subjectName;

    /**
     * 	科目尾号
     */
    private String detailSubjectNo;

    /**
     * 	科目尾号名称
     */
    private String detailSubjectName;

    /**
     * 	资产业务代码
     */
    private String busiId;

    /**
     * 	币种
     */
    private String ccy;
    
    /**
     * 	币种
     */
    private String ccyVal;

    /**
     * 	借贷方向
     */
    private String cdFlag;

    /**
     * 	借方金额
     */
    private BigDecimal dAmt;
    
    /**
     * 	贷方金额
     */
    private BigDecimal cAmt;

    /**
     * 	发生数量
     */
    private BigDecimal happenNumber;

    /**
     * 	基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 	投资目的
     */
    private String invAim;
    
    /**
     * 	分支序号
     */
    private Long branch;
    
    /**
     * 	资金帐户
     */
    private String acctCode;
    
    /**
     * 	账务明细代码
     */
    private String bokDetailId;
    
    /**
     * 	业务明细代码
     */
    private String busiDetailId;
    
    /**
     * 	业务明细类型
     */
    private String busiDetailType;
    
    /**
     * 	数据状态
     */
    private String status;
    
    /**
     * 	数据行
     */
    private String index;
    
    /**
     * 	本币种
     */
    private String bCcy;
    
    /**
     * 	本币贷方金额
     */
    private BigDecimal bcHappenAmt;
    /**
     * 	本币借方金额
     */
    private BigDecimal bdHappenAmt;

    /**
     * 	获取 科目号
     * 
     * @return 科目号
     */
    public String getSubjectNo() {
        return StringUtils.isEmpty(subjectNo)?null:subjectNo.trim();
    }

    /**
     * 	设置 科目号
     * 
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 	获取 科目名称
     * 
     * @return 科目名称.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 	设置 科目名称
     * 
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     *	 获取 detailSubjectNo
     * 
     * @return detailSubjectNo.
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    /**
     * 	设置 detailSubjectNo
     * 
     * @param detailSubjectNo detailSubjectNo
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo;
    }

    /**
     * 	获取 detailSubjectName
     * 
     * @return detailSubjectName.
     */
    public String getDetailSubjectName() {
        return detailSubjectName;
    }

    /**
     * 	设置 detailSubjectName
     * 
     * @param detailSubjectName detailSubjectName
     */
    public void setDetailSubjectName(String detailSubjectName) {
        this.detailSubjectName = detailSubjectName;
    }

    /**
     * 	获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 	设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 	获取 cdFlag
     * 
     * @return cdFlag.
     */
    public String getCdFlag() {
        return cdFlag;
    }

    /**
     * 	设置 cdFlag
     * 
     * @param cdFlag cdFlag
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    /**
     * 	获取 happenNumber
     * 
     * @return happenNumber.
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 	设置 happenNumber
     * 
     * @param happenNumber happenNumber
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    /**
     *	 获取 业务代码
     * 
     * @return 业务代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 	设置 业务代码
     * 
     * @param busiId 业务代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 	获取 流水号
     * 
     * @return 流水号.
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 	设置 流水号
     * 
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    /**
     * 	获取 账套代码
     * 
     * @return 账套代码.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 	设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 	获取 基础科目性质
     * 
     * @return 基础科目性质.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 	设置 基础科目性质
     * 
     * @param baseSubjectNature 基础科目性质
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }

    /**
     *	 获取 投资目的
     * 
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 	设置 投资目的
     * 
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 	获取 dAmt
     * @return dAmt.
     */
    public BigDecimal getdAmt() {
        return dAmt;
    }
    
    /**
     * 	设置 dAmt
     * @param dAmt dAmt
     */
    public void setdAmt(BigDecimal dAmt) {
        this.dAmt = dAmt;
    }

    /**
     * 	获取 cAmt
     * @return cAmt.
     */
    public BigDecimal getcAmt() {
        return cAmt;
    }
    
    /**
     * 	设置 cAmt
     * @param cAmt cAmt
     */
    public void setcAmt(BigDecimal cAmt) {
        this.cAmt = cAmt;
    }
    
    
    /**
     * 	获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    
    /**
     *	 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     *	 获取 acctCode
     * @return acctCode.
     */
    public String getAcctCode() {
        return acctCode;
    }

    /**
     * 	设置 acctCode
     * @param acctCode acctCode
     */
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    
    /**
     * 	获取 bokDetailId
     * @return bokDetailId.
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    
    /**
     * 	设置 bokDetailId
     * @param bokDetailId bokDetailId
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    
    /**
     * 	获取 busiDetailId
     * @return busiDetailId.
     */
    public String getBusiDetailId() {
        return busiDetailId;
    }

    
    /**
     *	 设置 busiDetailId
     * @param busiDetailId busiDetailId
     */
    public void setBusiDetailId(String busiDetailId) {
        this.busiDetailId = busiDetailId;
    }

    
    /**
     * 	获取 busiDetailType
     * @return busiDetailType.
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    
    /**
     * 	设置 busiDetailType
     * @param busiDetailType busiDetailType
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

    
    /**
     * 	获取 status
     * @return status.
     */
    public String getStatus() {
        return status;
    }

    
    /**
     * 	设置 status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    /**
     * 	获取 index
     * @return index.
     */
    public String getIndex() {
        return index;
    }

    
    /**
     * 	设置 index
     * @param index index
     */
    public void setIndex(String index) {
        this.index = index;
    }

    
    /**
     * 	获取 ccyVal
     * @return ccyVal.
     */
    public String getCcyVal() {
        return ccyVal;
    }

    
    /**
     * 	设置 ccyVal
     * @param ccyVal ccyVal
     */
    public void setCcyVal(String ccyVal) {
        this.ccyVal = ccyVal;
    }
    /**
     * 	取得 本币种
     * @return 本币种
     */
	public String getBCcy() {
		return bCcy;
	}
	
    /**
     * 	设置 本币种
     * @param bCcy 本币种
     */
	public void setBCcy(String bCcy) {
		this.bCcy = bCcy;
	}

	public BigDecimal getBcHappenAmt() {
		return bcHappenAmt;
	}

	public void setBcHappenAmt(BigDecimal bcHappenAmt) {
		this.bcHappenAmt = bcHappenAmt;
	}

	public BigDecimal getBdHappenAmt() {
		return bdHappenAmt;
	}

	public void setBdHappenAmt(BigDecimal bdHappenAmt) {
		this.bdHappenAmt = bdHappenAmt;
	}
}
