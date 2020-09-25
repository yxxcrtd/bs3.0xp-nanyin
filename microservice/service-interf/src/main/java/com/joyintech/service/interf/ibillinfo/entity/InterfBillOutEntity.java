package com.joyintech.service.interf.ibillinfo.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 单据信息输出-lc0001成立单据；lc0002到期单据
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see InterfBillOutEntity
 * @since 1.0
 */
public class InterfBillOutEntity extends InterfBillInEntity {

    /**
     * 产品简称
     */
    private String productShortname;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 资产池
     */
    private String assetsPool;

    /**
     * 资产池名称
     */
    private String assetsPoolName;

    /**
     * 类型
     */
    private String type;

    /**
     * 产品类别名称
     */
    private String productTypeName;

    /**
     * 任职部门
     */
    private String deptName;

    /**
     * 认购币种
     */
    private String subscriptionCcy;

    /**
     * 认购币种名称
     */
    private String subscriptionCcyName;

    /**
     * 滚动期数
     */
    private String rollTerm;

    /**
     * 计息方式
     */
    private String interestMode;

    /**
     * 起息日
     */
    private Date valueDate;

    /**
     * 到期日
     */
    private Date expiryDate;

    /**
     * 期限
     */
    private String term;

    /**
     * 认购价格
     */
    private BigDecimal subscriptionAmt;

    /**
     * 本金
     */
    private BigDecimal corpus;

    /**
     * 金额1
     */
    private String amt1;

    /**
     * 发行方式
     */
    private String issueMode;

    /**
     * 发行方式名称
     */
    private String issueModeName;

    /**
     * 账户分行行号
     */
    private String acctBranchId;

    /**
     * 支行名称
     */
    private String branchName;

    /**
     * 分层收益率(字符型)
     */
    private String bedYieldChar;

    /**
     * 销售费率
     */
    private BigDecimal saleFeeRate;

    /**
     * 托管费率
     */
    private BigDecimal consignFeeRate;

    /**
     * 资产管理费率
     */
    private BigDecimal assetsManagementFeeRate;

    /**
     * 其他费率
     */
    private BigDecimal otherFeeRate;

    /**
     * 经办人
     */
    private String agencyClientId;

    /**
     * 经办人名称
     */
    private String agencyClientName;

    /**
     * 复核柜员
     */
    private String apprUserId;

    /**
     * 复核柜员名称
     */
    private String apprTellerName;

    /**
     * 授权柜员
     */
    private String grantTellerNo;

    /**
     * 授权人姓名
     */
    private String ownerName;

    /**
     * 所属部门代码
     */
    private String belongDeptCode;

    /**
     * 所属部门
     */
    private String belongDeptName;

    /**
     * 附件个数
     * 
     * @return
     */
    private String affixFileNum;

    /**
     * 现金流发生日
     */
    private Date cashdate;

    /**
     * 实际计息天数
     */
    private String actterm;

    /**
     * 客户赎回金额
     */
    private BigDecimal prinoutamt;

    /**
     * 滚动到下一期金额
     */
    private BigDecimal nxtprinamt;

    /**
     * 实付理财收益
     */
    private BigDecimal actintamt;

    /**
     * 客户本息合计
     */
    private BigDecimal actprinintamt;

    /**
     * 实际计息天数
     */
    private String levelprinintamt;

    /**
     * 实付销售费
     */
    private BigDecimal paysamt;

    /**
     * 实付托管费
     */
    private BigDecimal payhostamt;

    /**
     * 相关支出费率
     */
    private BigDecimal ftp;

    /**
     * 相关支出费
     */
    private BigDecimal ftpamt;

    /**
     * 应付（相关支出-销售费）
     */
    private BigDecimal recamt;

    /**
     * 获取 产品简称
     * 
     * @return 产品简称
     */
    public String getProductShortname() {
        return productShortname;
    }

    /**
     * 获取 产品名称
     * 
     * @return 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 获取 资产池
     * 
     * @return 资产池
     */
    public String getAssetsPool() {
        return assetsPool;
    }

    /**
     * 获取 资产池名称
     * 
     * @return 资产池名称
     */
    public String getAssetsPoolName() {
        return assetsPoolName;
    }

    /**
     * 获取 类型
     * 
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 获取 产品类别名称
     * 
     * @return 产品类别名称
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * 获取 任职部门
     * 
     * @return 任职部门
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 获取 认购币种
     * 
     * @return 认购币种
     */
    public String getSubscriptionCcy() {
        return subscriptionCcy;
    }

    /**
     * 获取 认购币种名称
     * 
     * @return 认购币种名称
     */
    public String getSubscriptionCcyName() {
        return subscriptionCcyName;
    }

    /**
     * 获取 滚动期数
     * 
     * @return 滚动期数
     */
    public String getRollTerm() {
        return rollTerm;
    }

    /**
     * 获取 计息方式
     * 
     * @return 计息方式
     */
    public String getInterestMode() {
        return interestMode;
    }

    /**
     * 获取 起息日
     * 
     * @return 起息日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 获取 到期日
     * 
     * @return 到期日
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * 获取 期限
     * 
     * @return 期限
     */
    public String getTerm() {
        return term;
    }

    /**
     * 获取 认购价格
     * 
     * @return 认购价格
     */
    public BigDecimal getSubscriptionAmt() {
        return subscriptionAmt;
    }

    /**
     * 获取 本金
     * 
     * @return 本金
     */
    public BigDecimal getCorpus() {
        return corpus;
    }

    /**
     * 获取 分层金额
     * 
     * @return 分层金额
     */
    public String getAmt1() {
        return amt1;
    }

    /**
     * 获取 发行方式
     * 
     * @return 发行方式
     */
    public String getIssueMode() {
        return issueMode;
    }

    /**
     * 获取 发行方式名称
     * 
     * @return 发行方式名称
     */
    public String getIssueModeName() {
        return issueModeName;
    }

    /**
     * 获取 账户分行行号
     * 
     * @return 账户分行行号
     */
    public String getAcctBranchId() {
        return acctBranchId;
    }

    /**
     * 获取 支行名称
     * 
     * @return 支行名称
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * 获取 分层收益率(字符型)
     * 
     * @return 分层收益率(字符型)
     */
    public String getBedYieldChar() {
        return bedYieldChar;
    }

    /**
     * 获取 销售费率
     * 
     * @return 销售费率
     */
    public BigDecimal getSaleFeeRate() {
        return saleFeeRate;
    }

    /**
     * 获取 托管费率
     * 
     * @return 托管费率
     */
    public BigDecimal getConsignFeeRate() {
        return consignFeeRate;
    }

    /**
     * 获取 资产管理费率
     * 
     * @return 资产管理费率
     */
    public BigDecimal getAssetsManagementFeeRate() {
        return assetsManagementFeeRate;
    }

    /**
     * 获取 其他费率
     * 
     * @return 其他费率
     */
    public BigDecimal getOtherFeeRate() {
        return otherFeeRate;
    }

    /**
     * 获取 经办人
     * 
     * @return 经办人
     */
    public String getAgencyClientId() {
        return agencyClientId;
    }

    /**
     * 获取 经办人名称
     * 
     * @return 经办人名称
     */
    public String getAgencyClientName() {
        return agencyClientName;
    }

    /**
     * 获取 复核柜员
     * 
     * @return 复核柜员
     */
    public String getApprUserId() {
        return apprUserId;
    }

    /**
     * 获取 复核柜员名称
     * 
     * @return 复核柜员名称
     */
    public String getApprTellerName() {
        return apprTellerName;
    }

    /**
     * 获取 授权柜员
     * 
     * @return 授权柜员
     */
    public String getGrantTellerNo() {
        return grantTellerNo;
    }

    /**
     * 获取 授权人姓名
     * 
     * @return 授权人姓名
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 获取 所属部门代码
     * 
     * @return 所属部门代码
     */
    public String getBelongDeptCode() {
        return belongDeptCode;
    }

    /**
     * 获取 所属部门
     * 
     * @return 所属部门
     */
    public String getBelongDeptName() {
        return belongDeptName;
    }

    /**
     * 获取 附件个数
     * 
     * @return 附件个数
     */
    public String getAffixFileNum() {
        return affixFileNum;
    }

    /**
     * 设置 产品简称
     * 
     * @param productShortname 产品简称
     */
    public void setProductShortname(String productShortname) {
        this.productShortname = productShortname;
    }

    /**
     * 设置 产品名称
     * 
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 设置 资产池
     * 
     * @param assetsPool 资产池
     */
    public void setAssetsPool(String assetsPool) {
        this.assetsPool = assetsPool;
    }

    /**
     * 设置 资产池名称
     * 
     * @param assetsPoolName 资产池名称
     */
    public void setAssetsPoolName(String assetsPoolName) {
        this.assetsPoolName = assetsPoolName;
    }

    /**
     * 设置 类型
     * 
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 设置 产品类别名称
     * 
     * @param productTypeName 产品类别名称
     */
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    /**
     * 设置 任职部门
     * 
     * @param deptName 任职部门
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 设置 认购币种
     * 
     * @param subscriptionCcy 认购币种
     */
    public void setSubscriptionCcy(String subscriptionCcy) {
        this.subscriptionCcy = subscriptionCcy;
    }

    /**
     * 设置 认购币种名称
     * 
     * @param subscriptionCcyName 认购币种名称
     */
    public void setSubscriptionCcyName(String subscriptionCcyName) {
        this.subscriptionCcyName = subscriptionCcyName;
    }

    /**
     * 设置 滚动期数
     * 
     * @param rollTerm 滚动期数
     */
    public void setRollTerm(String rollTerm) {
        this.rollTerm = rollTerm;
    }

    /**
     * 设置 计息方式
     * 
     * @param interestMode 计息方式
     */
    public void setInterestMode(String interestMode) {
        this.interestMode = interestMode;
    }

    /**
     * 设置 起息日
     * 
     * @param valueDate 起息日
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 设置 到期日
     * 
     * @param expiryDate 到期日
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 设置 期限
     * 
     * @param term 期限
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 设置 认购价格
     * 
     * @param subscriptionAmt 认购价格
     */
    public void setSubscriptionAmt(BigDecimal subscriptionAmt) {
        this.subscriptionAmt = subscriptionAmt;
    }

    /**
     * 设置 本金
     * 
     * @param corpus 本金
     */
    public void setCorpus(BigDecimal corpus) {
        this.corpus = corpus;
    }

    /**
     * 设置 金额1
     * 
     * @param amt1 金额1
     */
    public void setAmt1(String amt1) {
        this.amt1 = amt1;
    }

    /**
     * 设置 发行方式
     * 
     * @param issueMode 发行方式
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode;
    }

    /**
     * 设置 发行方式名称
     * 
     * @param issueModeName 发行方式名称
     */
    public void setIssueModeName(String issueModeName) {
        this.issueModeName = issueModeName;
    }

    /**
     * 设置 账户分行行号
     * 
     * @param acctBranchId 账户分行行号
     */
    public void setAcctBranchId(String acctBranchId) {
        this.acctBranchId = acctBranchId;
    }

    /**
     * 设置 支行名称
     * 
     * @param branchName 支行名称
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * 设置 分层收益率(字符型)
     * 
     * @param bedYieldChar 分层收益率(字符型)
     */
    public void setBedYieldChar(String bedYieldChar) {
        this.bedYieldChar = bedYieldChar;
    }

    /**
     * 设置 销售费率
     * 
     * @param saleFeeRate 销售费率
     */
    public void setSaleFeeRate(BigDecimal saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    /**
     * 设置 托管费率
     * 
     * @param consignFeeRate 托管费率
     */
    public void setConsignFeeRate(BigDecimal consignFeeRate) {
        this.consignFeeRate = consignFeeRate;
    }

    /**
     * 设置 资产管理费率
     * 
     * @param assetsManagementFeeRate 资产管理费率
     */
    public void setAssetsManagementFeeRate(BigDecimal assetsManagementFeeRate) {
        this.assetsManagementFeeRate = assetsManagementFeeRate;
    }

    /**
     * 设置 其他费率
     * 
     * @param otherFeeRate 其他费率
     */
    public void setOtherFeeRate(BigDecimal otherFeeRate) {
        this.otherFeeRate = otherFeeRate;
    }

    /**
     * 设置 经办人
     * 
     * @param agencyClientId 经办人
     */
    public void setAgencyClientId(String agencyClientId) {
        this.agencyClientId = agencyClientId;
    }

    /**
     * 设置 经办人名称
     * 
     * @param agencyClientName 经办人名称
     */
    public void setAgencyClientName(String agencyClientName) {
        this.agencyClientName = agencyClientName;
    }

    /**
     * 设置 复核柜员
     * 
     * @param apprUserId 复核柜员
     */
    public void setApprUserId(String apprUserId) {
        this.apprUserId = apprUserId;
    }

    /**
     * 设置 复核柜员名称
     * 
     * @param apprTellerName 复核柜员名称
     */
    public void setApprTellerName(String apprTellerName) {
        this.apprTellerName = apprTellerName;
    }

    /**
     * 设置 授权柜员
     * 
     * @param grantTellerNo 授权柜员
     */
    public void setGrantTellerNo(String grantTellerNo) {
        this.grantTellerNo = grantTellerNo;
    }

    /**
     * 设置 授权人姓名
     * 
     * @param ownerName 授权人姓名
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 设置 所属部门代码
     * 
     * @param belongDeptCode 所属部门代码
     */
    public void setBelongDeptCode(String belongDeptCode) {
        this.belongDeptCode = belongDeptCode;
    }

    /**
     * 设置 所属部门
     * 
     * @param belongDeptName 所属部门
     */
    public void setBelongDeptName(String belongDeptName) {
        this.belongDeptName = belongDeptName;
    }

    /**
     * 设置 附件个数
     * 
     * @param affixFileNum 附件个数
     */
    public void setAffixFileNum(String affixFileNum) {
        this.affixFileNum = affixFileNum;
    }

    /**
     * 获取 现金流发生日
     * 
     * @return 现金流发生日
     */
    public Date getCashdate() {
        return cashdate;
    }

    /**
     * 设置 现金流发生日
     * 
     * @param cashdate 现金流发生日
     */
    public void setCashdate(Date cashdate) {
        this.cashdate = cashdate;
    }

    /**
     * 获取 实际计息天数
     * 
     * @return 实际计息天数
     */
    public String getActterm() {
        return actterm;
    }

    /**
     * 设置 实际计息天数
     * 
     * @param actterm 实际计息天数
     */
    public void setActterm(String actterm) {
        this.actterm = actterm;
    }

    /**
     * 获取客户赎回金额
     * 
     * @return 客户赎回金额
     */
    public BigDecimal getPrinoutamt() {
        return prinoutamt;
    }

    /**
     * 设置客户赎回金额
     * 
     * @param prinoutamt 客户赎回金额
     */
    public void setPrinoutamt(BigDecimal prinoutamt) {
        this.prinoutamt = prinoutamt;
    }

    /**
     * 获取滚动到下一期金额
     * 
     * @return 滚动到下一期金额
     */
    public BigDecimal getNxtprinamt() {
        return nxtprinamt;
    }

    /**
     * 设置滚动到下一期金额
     * 
     * @param nxtprinamt 滚动到下一期金额
     */
    public void setNxtprinamt(BigDecimal nxtprinamt) {
        this.nxtprinamt = nxtprinamt;
    }

    /**
     * 获取实付理财收益
     * 
     * @return 实付理财收益
     */
    public BigDecimal getActintamt() {
        return actintamt;
    }

    /**
     * 设置实付理财收益
     * 
     * @param actintamt 实付理财收益
     */
    public void setActintamt(BigDecimal actintamt) {
        this.actintamt = actintamt;
    }

    /**
     * 获取客户本息合计
     * 
     * @return 客户本息合计
     */
    public BigDecimal getActprinintamt() {
        return actprinintamt;
    }

    /**
     * 设置客户本息合计
     * 
     * @param actprinintamt 客户本息合计
     */
    public void setActprinintamt(BigDecimal actprinintamt) {
        this.actprinintamt = actprinintamt;
    }

    /**
     * 获取分层客户本息合计
     * 
     * @return 分层客户本息合计
     */
    public String getLevelprinintamt() {
        return levelprinintamt;
    }

    /**
     * 设置分层客户本息合计
     * 
     * @param levelprinintamt 分层客户本息合计
     */
    public void setLevelprinintamt(String levelprinintamt) {
        this.levelprinintamt = levelprinintamt;
    }

    /**
     * 获取实付销售费
     * 
     * @return 实付销售费
     */
    public BigDecimal getPaysamt() {
        return paysamt;
    }

    /**
     * 设置实付销售费
     * 
     * @param paysamt 实付销售费
     */
    public void setPaysamt(BigDecimal paysamt) {
        this.paysamt = paysamt;
    }

    /**
     * 获取实付托管费
     * 
     * @return 实付托管费
     */
    public BigDecimal getPayhostamt() {
        return payhostamt;
    }

    /**
     * 设置实付托管费
     * 
     * @param payhostamt 实付托管费
     */
    public void setPayhostamt(BigDecimal payhostamt) {
        this.payhostamt = payhostamt;
    }

    /**
     * 获取相关支出费率
     * 
     * @return 相关支出费率
     */
    public BigDecimal getFtp() {
        return ftp;
    }

    /**
     * 设置相关支出费率
     * 
     * @param ftp 相关支出费率
     */
    public void setFtp(BigDecimal ftp) {
        this.ftp = ftp;
    }

    /**
     * 获取相关支出费
     * 
     * @return 相关支出费
     */
    public BigDecimal getFtpamt() {
        return ftpamt;
    }

    /**
     * 设置相关支出费
     * 
     * @param ftpamt 相关支出费
     */
    public void setFtpamt(BigDecimal ftpamt) {
        this.ftpamt = ftpamt;
    }

    /**
     * 获取应付（相关支出-销售费）
     * 
     * @return 应付（相关支出-销售费）
     */
    public BigDecimal getRecamt() {
        return recamt;
    }

    /**
     * 设置应付（相关支出-销售费）
     * 
     * @param recamt 应付（相关支出-销售费）
     */
    public void setRecamt(BigDecimal recamt) {
        this.recamt = recamt;
    }

    /**
     * 获取 busiType
     * 
     * @return busiType busiType
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 busiType
     * 
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

}
