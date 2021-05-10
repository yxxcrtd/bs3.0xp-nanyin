/*
 * 文件名：ValManualVoucher003CheckValidatorDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dto;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.validator.ValManualVoucher003CheckValidator;

/**
 * 手工凭证添加到表格校验Dto
 * 
 * @author weipeng
 * @version 1.0 2018年7月11日
 * @see ValManualVoucher003SearchDto
 * @since 1.0
 */
@ValidLogic(targetClass = ValManualVoucher003CheckValidator.class)
public class ValManualVoucher003CheckValidatorDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqno;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 科目号
     */
    @NotEmpty(fieldName = "ValManualVoucher.subjectNo")
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目尾号
     */
    private String detailSubjectNo;

    /**
     * 科目尾部名称
     */
    private String detailSubjectName;

    /**
     * 币种
     */
    @NotEmpty(fieldName = "ValManualVoucher.ccy")
    private String ccy;

    /**
     * 借方金额
     */
    private BigDecimal dAmt;

    /**
     * 贷方金额
     */
    private BigDecimal cAmt;

    /**
     * 发生数量
     */
    private BigDecimal happenNumber;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 资金帐户代码
     */
    private String acctCode;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 资产代码
     */
    private String busiId;

    /**
     * 资产代码对应的业务明细类型
     */
    private String busiDetailType;
    
    /**
     * 收益类型
     */
    private String profitType;//add by wujie on 20201111
    

    /**
     * 获取 seqno
     * 
     * @return seqno.
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 seqno
     * 
     * @param seqno seqno
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 subjectNo
     * 
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return StringUtils.isEmpty(subjectNo) ? null : subjectNo.trim();
    }

    /**
     * 设置 subjectNo
     * 
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 获取 subjectName
     * 
     * @return subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 subjectName
     * 
     * @param subjectName subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 获取 detailSubjectNo
     * 
     * @return detailSubjectNo.
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    /**
     * 设置 detailSubjectNo
     * 
     * @param detailSubjectNo detailSubjectNo
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo;
    }

    /**
     * 获取 detailSubjectName
     * 
     * @return detailSubjectName.
     */
    public String getDetailSubjectName() {
        return detailSubjectName;
    }

    /**
     * 设置 detailSubjectName
     * 
     * @param detailSubjectName detailSubjectName
     */
    public void setDetailSubjectName(String detailSubjectName) {
        this.detailSubjectName = detailSubjectName;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 dAmt
     * 
     * @return dAmt.
     */
    public BigDecimal getdAmt() {
        return dAmt;
    }

    /**
     * 设置 dAmt
     * 
     * @param dAmt dAmt
     */
    public void setdAmt(BigDecimal dAmt) {
        this.dAmt = dAmt;
    }

    /**
     * 获取 cAmt
     * 
     * @return cAmt.
     */
    public BigDecimal getcAmt() {
        return cAmt;
    }

    /**
     * 设置 cAmt
     * 
     * @param cAmt cAmt
     */
    public void setcAmt(BigDecimal cAmt) {
        this.cAmt = cAmt;
    }

    /**
     * 获取 happenNumber
     * 
     * @return happenNumber.
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 设置 happenNumber
     * 
     * @param happenNumber happenNumber
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    /**
     * 获取 baseSubjectNature
     * 
     * @return baseSubjectNature.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 baseSubjectNature
     * 
     * @param baseSubjectNature baseSubjectNature
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }

    /**
     * 获取 invAim
     * 
     * @return invAim.
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 invAim
     * 
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 branch
     * 
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     * 
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 acctCode
     * 
     * @return acctCode.
     */
    public String getAcctCode() {
        return acctCode;
    }

    /**
     * 设置 acctCode
     * 
     * @param acctCode acctCode
     */
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 业务明细类型
     * 
     * @return 业务明细类型.
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    /**
     * 设置 业务明细类型
     * 
     * @param busiDetailType 业务明细类型
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
    
    

}
