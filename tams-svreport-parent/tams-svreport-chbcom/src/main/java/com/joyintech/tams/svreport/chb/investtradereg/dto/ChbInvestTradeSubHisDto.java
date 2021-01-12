package com.joyintech.tams.svreport.chb.investtradereg.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_INVEST_TRADE_SUB_HIS表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-05-21
 */
public class ChbInvestTradeSubHisDto {
    /**
     * 履历序号
     */
    private String hisNo;

    /**
     * 行内交易编码
     */
    private String tradeCode;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 发行机构代码
     */
    private String registBankCode;

    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 币种 参考数据字典：CURRENCY_CHB
     */
    private String ccy;

    /**
     * 资金流动类型 01 资产买入/ 02 资产卖出或到期/ 03 存入银行存款/ 04 银行存款到期/ 05拆放同业和买入返售首期/ 06同业拆入和卖出回购首期/ 07拆放同业和买入返售到期/ 08同业拆入和卖出回购到期/09投资资产所得收益/ 10 投资资产支出/ 11 其他收入/ 12 其他支出
     */
    private String curAssetType;

    /**
     * 资金流动类型说明
     */
    private String curAssetdetType;

    /**
     * 交易日
     */
    private Date dealdate;

    /**
     * 交易对手
     */
    private String tradeCounter;

    /**
     * 单位成交价格（全价）
     */
    private BigDecimal unitPriceFull;

    /**
     * 单位成交价格（净价）
     */
    private BigDecimal unitPriceNet;

    /**
     * 到期收益率
     */
    private BigDecimal endRate;

    /**
     * 成交编号/合同号
     */
    private String contractCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 通道代码
     */
    private String chlCpctId;

    /**
     * 交易类型01 资产交易 02通道交易 03产品费用交易 04其他交易 05资管计划交易 06通道费用交易
     */
    private String tradeType;

    /**
     * 通道名称
     */
    private String chlAgrtName;

    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 费用类型 01:本机构费用 02:其他机构费用
     */
    private String feeType;

    /**
     * 费用二级类型01:本机构托管费 02:本机构管理费 03:本机构销售手续费 04:本机构其他产品费用 05:其他机构托管费 06:其他机构管理费 07:其他机构销售手续费 08:投资顾问费用 09:其他机构其他产品费用 10：中收费 11：浮动费
     */
    private String feeSectype;

    /**
     * 数据状态 01 新建/02 修改/03 删除
     */
    private String status;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 创建人
     */
    private String createUser;

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
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 报送状态 01 未报送/03 手工处理/02 接口报送
     */
    private String sendStatus;

    /**
     * 报送反馈状态 01 发送成功 02 发送失败 03 中债通过 04 中债退回 05 审核文件下载失败
     */
    private String submitFeedbackStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 删除原因
     */
    private String deleteRes;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 交易登记日
     */
    private Date dealRegistDate;

    /**
     * 资产是否已登记 01 是/ 02 否
     */
    private String isAssetSub;

    /**
     * 关联交易主键（投资交易、产品费用交易）
     */
    private String refTradeseqno;

    /**
     * 折算人民币金额
     */
    private BigDecimal cnyAmt;

    /**
     * 发生金额
     */
    private BigDecimal amt;

    /**
     * 数量
     */
    private BigDecimal num;

    /**
     * 报送流水号
     */
    private String sumitSeq;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 履历序号
     * @return HIS_NO 履历序号
     */
    public String getHisNo() {
        return hisNo;
    }

    /**
     * 履历序号
     * @param hisNo 履历序号
     */
    public void setHisNo(String hisNo) {
        this.hisNo = hisNo == null ? null : hisNo.trim();
    }

    /**
     * 行内交易编码
     * @return TRADE_CODE 行内交易编码
     */
    public String getTradeCode() {
        return tradeCode;
    }

    /**
     * 行内交易编码
     * @param tradeCode 行内交易编码
     */
    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode == null ? null : tradeCode.trim();
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
     * @return REGIST_BANK_CODE 发行机构代码
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 发行机构代码
     * @param registBankCode 发行机构代码
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }

    /**
     * 行内资产/负债编码
     * @return ASSET_CODE 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 币种 参考数据字典：CURRENCY_CHB
     * @return CCY 币种 参考数据字典：CURRENCY_CHB
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 币种 参考数据字典：CURRENCY_CHB
     * @param ccy 币种 参考数据字典：CURRENCY_CHB
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 资金流动类型 01 资产买入/ 02 资产卖出或到期/ 03 存入银行存款/ 04 银行存款到期/ 05拆放同业和买入返售首期/ 06同业拆入和卖出回购首期/ 07拆放同业和买入返售到期/ 08同业拆入和卖出回购到期/09投资资产所得收益/ 10 投资资产支出/ 11 其他收入/ 12 其他支出
     * @return CUR_ASSET_TYPE 资金流动类型 01 资产买入/ 02 资产卖出或到期/ 03 存入银行存款/ 04 银行存款到期/ 05拆放同业和买入返售首期/ 06同业拆入和卖出回购首期/ 07拆放同业和买入返售到期/ 08同业拆入和卖出回购到期/09投资资产所得收益/ 10 投资资产支出/ 11 其他收入/ 12 其他支出
     */
    public String getCurAssetType() {
        return curAssetType;
    }

    /**
     * 资金流动类型 01 资产买入/ 02 资产卖出或到期/ 03 存入银行存款/ 04 银行存款到期/ 05拆放同业和买入返售首期/ 06同业拆入和卖出回购首期/ 07拆放同业和买入返售到期/ 08同业拆入和卖出回购到期/09投资资产所得收益/ 10 投资资产支出/ 11 其他收入/ 12 其他支出
     * @param curAssetType 资金流动类型 01 资产买入/ 02 资产卖出或到期/ 03 存入银行存款/ 04 银行存款到期/ 05拆放同业和买入返售首期/ 06同业拆入和卖出回购首期/ 07拆放同业和买入返售到期/ 08同业拆入和卖出回购到期/09投资资产所得收益/ 10 投资资产支出/ 11 其他收入/ 12 其他支出
     */
    public void setCurAssetType(String curAssetType) {
        this.curAssetType = curAssetType == null ? null : curAssetType.trim();
    }

    /**
     * 资金流动类型说明
     * @return CUR_ASSETDET_TYPE 资金流动类型说明
     */
    public String getCurAssetdetType() {
        return curAssetdetType;
    }

    /**
     * 资金流动类型说明
     * @param curAssetdetType 资金流动类型说明
     */
    public void setCurAssetdetType(String curAssetdetType) {
        this.curAssetdetType = curAssetdetType == null ? null : curAssetdetType.trim();
    }

    /**
     * 交易日
     * @return DEALDATE 交易日
     */
    public Date getDealdate() {
        return dealdate;
    }

    /**
     * 交易日
     * @param dealdate 交易日
     */
    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
    }

    /**
     * 交易对手
     * @return TRADE_COUNTER 交易对手
     */
    public String getTradeCounter() {
        return tradeCounter;
    }

    /**
     * 交易对手
     * @param tradeCounter 交易对手
     */
    public void setTradeCounter(String tradeCounter) {
        this.tradeCounter = tradeCounter == null ? null : tradeCounter.trim();
    }

    /**
     * 单位成交价格（全价）
     * @return UNIT_PRICE_FULL 单位成交价格（全价）
     */
    public BigDecimal getUnitPriceFull() {
        return unitPriceFull;
    }

    /**
     * 单位成交价格（全价）
     * @param unitPriceFull 单位成交价格（全价）
     */
    public void setUnitPriceFull(BigDecimal unitPriceFull) {
        this.unitPriceFull = unitPriceFull;
    }

    /**
     * 单位成交价格（净价）
     * @return UNIT_PRICE_NET 单位成交价格（净价）
     */
    public BigDecimal getUnitPriceNet() {
        return unitPriceNet;
    }

    /**
     * 单位成交价格（净价）
     * @param unitPriceNet 单位成交价格（净价）
     */
    public void setUnitPriceNet(BigDecimal unitPriceNet) {
        this.unitPriceNet = unitPriceNet;
    }

    /**
     * 到期收益率
     * @return END_RATE 到期收益率
     */
    public BigDecimal getEndRate() {
        return endRate;
    }

    /**
     * 到期收益率
     * @param endRate 到期收益率
     */
    public void setEndRate(BigDecimal endRate) {
        this.endRate = endRate;
    }

    /**
     * 成交编号/合同号
     * @return CONTRACT_CODE 成交编号/合同号
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * 成交编号/合同号
     * @param contractCode 成交编号/合同号
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode == null ? null : contractCode.trim();
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
     * 通道代码
     * @return CHL_CPCT_ID 通道代码
     */
    public String getChlCpctId() {
        return chlCpctId;
    }

    /**
     * 通道代码
     * @param chlCpctId 通道代码
     */
    public void setChlCpctId(String chlCpctId) {
        this.chlCpctId = chlCpctId == null ? null : chlCpctId.trim();
    }

    /**
     * 交易类型01 资产交易 02通道交易 03产品费用交易 04其他交易 05资管计划交易 06通道费用交易
     * @return TRADE_TYPE 交易类型01 资产交易 02通道交易 03产品费用交易 04其他交易 05资管计划交易 06通道费用交易
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 交易类型01 资产交易 02通道交易 03产品费用交易 04其他交易 05资管计划交易 06通道费用交易
     * @param tradeType 交易类型01 资产交易 02通道交易 03产品费用交易 04其他交易 05资管计划交易 06通道费用交易
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 通道名称
     * @return CHL_AGRT_NAME 通道名称
     */
    public String getChlAgrtName() {
        return chlAgrtName;
    }

    /**
     * 通道名称
     * @param chlAgrtName 通道名称
     */
    public void setChlAgrtName(String chlAgrtName) {
        this.chlAgrtName = chlAgrtName == null ? null : chlAgrtName.trim();
    }

    /**
     * 费用代码
     * @return FEE_ID 费用代码
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 费用代码
     * @param feeId 费用代码
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    /**
     * 费用名称
     * @return FEE_NAME 费用名称
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * 费用名称
     * @param feeName 费用名称
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName == null ? null : feeName.trim();
    }

    /**
     * 费用类型 01:本机构费用 02:其他机构费用
     * @return FEE_TYPE 费用类型 01:本机构费用 02:其他机构费用
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 费用类型 01:本机构费用 02:其他机构费用
     * @param feeType 费用类型 01:本机构费用 02:其他机构费用
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 费用二级类型01:本机构托管费 02:本机构管理费 03:本机构销售手续费 04:本机构其他产品费用 05:其他机构托管费 06:其他机构管理费 07:其他机构销售手续费 08:投资顾问费用 09:其他机构其他产品费用 10：中收费 11：浮动费
     * @return FEE_SECTYPE 费用二级类型01:本机构托管费 02:本机构管理费 03:本机构销售手续费 04:本机构其他产品费用 05:其他机构托管费 06:其他机构管理费 07:其他机构销售手续费 08:投资顾问费用 09:其他机构其他产品费用 10：中收费 11：浮动费
     */
    public String getFeeSectype() {
        return feeSectype;
    }

    /**
     * 费用二级类型01:本机构托管费 02:本机构管理费 03:本机构销售手续费 04:本机构其他产品费用 05:其他机构托管费 06:其他机构管理费 07:其他机构销售手续费 08:投资顾问费用 09:其他机构其他产品费用 10：中收费 11：浮动费
     * @param feeSectype 费用二级类型01:本机构托管费 02:本机构管理费 03:本机构销售手续费 04:本机构其他产品费用 05:其他机构托管费 06:其他机构管理费 07:其他机构销售手续费 08:投资顾问费用 09:其他机构其他产品费用 10：中收费 11：浮动费
     */
    public void setFeeSectype(String feeSectype) {
        this.feeSectype = feeSectype == null ? null : feeSectype.trim();
    }

    /**
     * 数据状态 01 新建/02 修改/03 删除
     * @return STATUS 数据状态 01 新建/02 修改/03 删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态 01 新建/02 修改/03 删除
     * @param status 数据状态 01 新建/02 修改/03 删除
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * 报送状态 01 未报送/03 手工处理/02 接口报送
     * @return SEND_STATUS 报送状态 01 未报送/03 手工处理/02 接口报送
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 报送状态 01 未报送/03 手工处理/02 接口报送
     * @param sendStatus 报送状态 01 未报送/03 手工处理/02 接口报送
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 报送反馈状态 01 发送成功 02 发送失败 03 中债通过 04 中债退回 05 审核文件下载失败
     * @return SUBMIT_FEEDBACK_STATUS 报送反馈状态 01 发送成功 02 发送失败 03 中债通过 04 中债退回 05 审核文件下载失败
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 报送反馈状态 01 发送成功 02 发送失败 03 中债通过 04 中债退回 05 审核文件下载失败
     * @param submitFeedbackStatus 报送反馈状态 01 发送成功 02 发送失败 03 中债通过 04 中债退回 05 审核文件下载失败
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
     * 删除原因
     * @return DELETE_RES 删除原因
     */
    public String getDeleteRes() {
        return deleteRes;
    }

    /**
     * 删除原因
     * @param deleteRes 删除原因
     */
    public void setDeleteRes(String deleteRes) {
        this.deleteRes = deleteRes == null ? null : deleteRes.trim();
    }

    /**
     * 数据来源
     * @return DATA_SOURCE 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
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
     * 交易登记日
     * @return DEAL_REGIST_DATE 交易登记日
     */
    public Date getDealRegistDate() {
        return dealRegistDate;
    }

    /**
     * 交易登记日
     * @param dealRegistDate 交易登记日
     */
    public void setDealRegistDate(Date dealRegistDate) {
        this.dealRegistDate = dealRegistDate;
    }

    /**
     * 资产是否已登记 01 是/ 02 否
     * @return IS_ASSET_SUB 资产是否已登记 01 是/ 02 否
     */
    public String getIsAssetSub() {
        return isAssetSub;
    }

    /**
     * 资产是否已登记 01 是/ 02 否
     * @param isAssetSub 资产是否已登记 01 是/ 02 否
     */
    public void setIsAssetSub(String isAssetSub) {
        this.isAssetSub = isAssetSub == null ? null : isAssetSub.trim();
    }

    /**
     * 关联交易主键（投资交易、产品费用交易）
     * @return REF_TRADESEQNO 关联交易主键（投资交易、产品费用交易）
     */
    public String getRefTradeseqno() {
        return refTradeseqno;
    }

    /**
     * 关联交易主键（投资交易、产品费用交易）
     * @param refTradeseqno 关联交易主键（投资交易、产品费用交易）
     */
    public void setRefTradeseqno(String refTradeseqno) {
        this.refTradeseqno = refTradeseqno == null ? null : refTradeseqno.trim();
    }

    /**
     * 折算人民币金额
     * @return CNY_AMT 折算人民币金额
     */
    public BigDecimal getCnyAmt() {
        return cnyAmt;
    }

    /**
     * 折算人民币金额
     * @param cnyAmt 折算人民币金额
     */
    public void setCnyAmt(BigDecimal cnyAmt) {
        this.cnyAmt = cnyAmt;
    }

    /**
     * 发生金额
     * @return AMT 发生金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 发生金额
     * @param amt 发生金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 数量
     * @return NUM 数量
     */
    public BigDecimal getNum() {
        return num;
    }

    /**
     * 数量
     * @param num 数量
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }

    /**
     * 获取 报送流水号
     * 
     * @return 报送流水号
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 报送流水号
     * 
     * @param sumitSeq 报送流水号
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
    }

    /**
     * 获取 产品登记编码
     * 
     * @return 产品登记编码
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 产品登记编码
     * 
     * @param prodRegistCode 产品登记编码
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode == null ? null : prodRegistCode.trim();
    }
}