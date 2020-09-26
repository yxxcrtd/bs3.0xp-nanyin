/*
 * 文件名：InterestsBusinessDetailed.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zyf
 * 修改时间：2017年9月13日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitmaintenance.businessdetailed.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.BotCheckUtil;
import com.joyintech.tams.report.common.util.IBDDataCheckUtil;

/**
 * 权益业务明细信息Entity 〈功能详细描述〉
 *
 * @author zyf
 * @version 1.0 2017年9月13日
 * @see InterestsBusinessDetailed
 * @since 1.0
 */
public class InterestsBusinessDetailed extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6819079933771114473L;

    /**
     * 业务流水号
     */
    private String busiSeqno;

    /**
     * 产品代码
     */
    private String account;

    /**
     * 产品名称
     */
    private String accountName;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 权益确认日期
     */
    private Date eQuityConfDate;

    /**
     * 权益确认日期Str
     */
    private String eQuityConfDateStr;

    /**
     * 购买地
     */
    private String buyPlace;

    /**
     * 业务种类
     */
    private String busiType;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 折算人民币金额（元）
     */
    private Double converCnyAmount;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 份额
     */
    private Double shares;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 银行资金账号
     */
    private String fundAcc;

    /**
     * 银行资金账户开户行
     */
    private String fundAccBank;

    /**
     * 是否报送
     */
    private String isSubmit;

    /**
     * 是否能代销
     */
    private String isConsign;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * type
     */
    private String type;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 报送流水号
     */
    private String sumitSeq;

    /**
     * 报送 数据状态
     */
    private String subStatus;

    /**
     * 份额确认时间戳
     */
    private Date shareConfDatetime;

    /**
     * 份额确认时间戳Str
     */
    private String shareConfDatetimeStr;

    /**
     * 获取 shareConfDatetime
     *
     * @return shareConfDatetime.
     */
    public Date getShareConfDatetime() {
        return shareConfDatetime;
    }

    /**
     * 设置 shareConfDatetime
     *
     * @param shareConfDatetime shareConfDatetime
     */
    public void setShareConfDatetime(Date shareConfDatetime) {
        this.shareConfDatetime = shareConfDatetime;
    }

    /**
     * 获取 shareConfDatetimeStr
     *
     * @return shareConfDatetimeStr.
     */
    public String getShareConfDatetimeStr() {
        return shareConfDatetimeStr;
    }

    /**
     * 设置 shareConfDatetimeStr
     *
     * @param shareConfDatetimeStr shareConfDatetimeStr
     */
    public void setShareConfDatetimeStr(String shareConfDatetimeStr) {
        this.shareConfDatetimeStr = shareConfDatetimeStr;
    }

    /**
     *
     */
    private String orgCode;

    /**
     * 经办人
     */
    private String agent;

    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 busiSeqno
     *
     * @return busiSeqno.
     */
    public String getBusiSeqno() {
        return busiSeqno;
    }

    /**
     * 设置 busiSeqno
     *
     * @param busiSeqno busiSeqno
     */
    public void setBusiSeqno(String busiSeqno) {
        this.busiSeqno = busiSeqno;
    }

    /**
     * 获取 account
     *
     * @return account.
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 account
     *
     * @param account account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取 accountName
     *
     * @return accountName.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 设置 accountName
     *
     * @param accountName accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 获取 registBankCode
     *
     * @return registBankCode.
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 registBankCode
     *
     * @param registBankCode registBankCode
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode;
    }

    /**
     * 获取 indentifyMark
     *
     * @return indentifyMark.
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 indentifyMark
     *
     * @param indentifyMark indentifyMark
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark;
    }

    /**
     * 获取 eQuityConfDate
     *
     * @return eQuityConfDate.
     */
    public Date geteQuityConfDate() {
        return eQuityConfDate;
    }

    /**
     * 设置 eQuityConfDate
     *
     * @param eQuityConfDate eQuityConfDate
     */
    public void seteQuityConfDate(Date eQuityConfDate) {
        this.eQuityConfDate = eQuityConfDate;
    }

    /**
     * 获取 eQuityConfDateStr
     *
     * @return eQuityConfDateStr.
     */
    public String geteQuityConfDateStr() {
        return eQuityConfDateStr;
    }

    /**
     * 设置 eQuityConfDateStr
     *
     * @param eQuityConfDateStr eQuityConfDateStr
     */
    public void seteQuityConfDateStr(String eQuityConfDateStr) {
        this.eQuityConfDateStr = eQuityConfDateStr;
    }

    /**
     * 获取 shares
     *
     * @return shares.
     */
    public Double getShares() {
        return shares;
    }

    /**
     * 设置 shares
     *
     * @param shares shares
     */
    public void setShares(Double shares) {
        this.shares = shares;
    }

    /**
     * 获取 buyPlace
     *
     * @return buyPlace.
     */
    public String getBuyPlace() {
        return buyPlace;
    }

    /**
     * 设置 buyPlace
     *
     * @param buyPlace buyPlace
     */
    public void setBuyPlace(String buyPlace) {
        this.buyPlace = buyPlace;
    }

    /**
     * 获取 busiType
     *
     * @return busiType.
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
     * 获取 deptCode
     *
     * @return deptCode.
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 deptCode
     *
     * @param deptCode deptCode
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * 获取 amount
     *
     * @return amount.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置 amount
     *
     * @param amount amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 获取 converCnyAmount
     *
     * @return converCnyAmount.
     */
    public Double getConverCnyAmount() {
        return converCnyAmount;
    }

    /**
     * 设置 converCnyAmount
     *
     * @param converCnyAmount converCnyAmount
     */
    public void setConverCnyAmount(Double converCnyAmount) {
        this.converCnyAmount = converCnyAmount;
    }

    /**
     * 获取 channel
     *
     * @return channel.
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置 channel
     *
     * @param channel channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 获取 fundAcc
     *
     * @return fundAcc.
     */
    public String getFundAcc() {
        return fundAcc;
    }

    /**
     * 设置 fundAcc
     *
     * @param fundAcc fundAcc
     */
    public void setFundAcc(String fundAcc) {
        this.fundAcc = fundAcc;
    }

    /**
     * 获取 fundAccBank
     *
     * @return fundAccBank.
     */
    public String getFundAccBank() {
        return fundAccBank;
    }

    /**
     * 设置 fundAccBank
     *
     * @param fundAccBank fundAccBank
     */
    public void setFundAccBank(String fundAccBank) {
        this.fundAccBank = fundAccBank;
    }

    /**
     * 获取 isSubmit
     *
     * @return isSubmit.
     */
    public String getIsSubmit() {
        return isSubmit;
    }

    /**
     * 设置 isSubmit
     *
     * @param isSubmit isSubmit
     */
    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    /**
     * 获取 isConsign
     *
     * @return isConsign.
     */
    public String getIsConsign() {
        return isConsign;
    }

    /**
     * 设置 isConsign
     *
     * @param isConsign isConsign
     */
    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign;
    }

    /**
     * 获取 remark
     *
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 workflowId
     *
     * @return workflowId.
     */
    @Override
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 workflowId
     *
     * @param workflowId workflowId
     */
    @Override
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 dataSource
     *
     * @return dataSource.
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 dataSource
     *
     * @param dataSource dataSource
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取 regCode
     *
     * @return regCode.
     */
    public String getRegCode() {
        return regCode;
    }

    /**
     * 设置 regCode
     *
     * @param regCode regCode
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    /**
     * 获取 orgCode
     *
     * @return orgCode.
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 orgCode
     *
     * @param orgCode orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取 sumitSeq
     *
     * @return sumitSeq.
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 sumitSeq
     *
     * @param sumitSeq sumitSeq
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq;
    }

    /**
     * 获取 subStatus
     *
     * @return subStatus.
     */
    public String getSubStatus() {
        return subStatus;
    }

    /**
     * 设置 subStatus
     *
     * @param subStatus subStatus
     */
    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    /**
     * 获取 agent
     *
     * @return agent.
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 agent
     *
     * @param agent agent
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * 获取 createDept
     *
     * @return createDept.
     */
    @Override
    public String getCreateDept() {
        if (StringUtil.isNotBlank(super.getCreateDept())) {
            return super.getCreateDept();
        }

        String tempCreateDept = getDeptCode();
        if (StringUtil.isEmpty(tempCreateDept)) {
            tempCreateDept = getOrgCode();
        }
        return tempCreateDept;
    }

    /**
     * 合法性检查 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult validCheck() {
        AjaxResult wr = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();

        // 1.登记银行代码
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.registBankCode, "registBankCode",
            "登记银行代码要素不可为空。");
        // 文字长度（固定长）6
        IBDDataCheckUtil.fixLength(6, errorList, this.registBankCode, "registBankCode",
            "文字长度（固定长）6");
        /* //该代码必须与登记的银行一致 */
        // 正则表达式：CXXXXX
//        IBDDataCheckUtil.regMatcher("^C.....", errorList, this.registBankCode, "registBankCode",
//            "登记银行代码要素格式不对。正确的格式：CXXXXX（XXXXX为数字）。");
        // 2.产品登记编码
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.regCode, "regCode", "产品登记编码要素不可为空。"); // 不正确
        // 正则表达式：字母C+7位数字+字母A，B，C（三个中的1个或0个）+6位数字
//        IBDDataCheckUtil.regMatcher("^C[0-9]{7}[A,B,C]{0,1}[0-9]{6}", errorList, this.regCode,
//            "regCode", "产品登记编码要素格式不对。正确的格式：字母C+7位数字+字母A，B，C（三个中的1个或0个）+6位数字。");
        // 3.业务流水号
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.busiSeqno, "busiSeqno", "业务流水号要素不可为空。");
        // 文字长度40（最大长）
        IBDDataCheckUtil.maxLength(40, errorList, this.busiSeqno, "busiSeqno", "业务流水号过长。");
        /*
         * //同一个登记银行，对于同一只理财产品，业务流水号不得重复。
         */
        // 4.业务确认日期
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.eQuityConfDate, "eQuityConfDate",
            "业务确认日期要素不可为空。");
        // 日期类型 YYYY-MM-DD
        BotCheckUtil.dateFormatCheck(DateUtil.DEFAULT_DATE_FORMAT_EN, errorList,
            this.eQuityConfDateStr, "eQuityConfDate", "业务确认日期必须为日期格式（YYYY-MM-DD）。");

        // 5.识别标识
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.indentifyMark, "indentifyMark", "识别标识要素不可为空。");
        // 不得含有汉字（只允许白名单中的阿拉伯数字，英文字母，半角符号、全角符号）
        IBDDataCheckUtil.regMatcher("[^\u4e00-\u9fa5]+", errorList, this.indentifyMark,
            "indentifyMark", "识别标识只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。");
        // 文字长度30（最大长）
        IBDDataCheckUtil.maxLength(30, errorList, this.indentifyMark, "indentifyMark", "识别标识过长。");
        // 6.购买地
        // 当“业务种类”选择“01 募集期认购”或“02 存续期申购”时，必填。
        if (DictCodeListExt2.业务种类_募集期认购.getValue().equals(this.busiType)
            || DictCodeListExt2.业务种类_存续期申购.getValue().equals(this.busiType)) {
            IBDDataCheckUtil.notEmpty(errorList, this.buyPlace, "buyPlace",
                "当业务种类为募集期认购时，购买地要素不可为空。");
        }
        // 文字长度(固定长)6
        IBDDataCheckUtil.fixLength(6, errorList, this.buyPlace, "buyPlace", "文字长度（固定长）6");
        // 枚举范围内、单选
        IBDDataCheckUtil.codeListSimpleCheck(DictCode.销售区域_中债, errorList, this.buyPlace,
            "buyPlace", "购买地不在值域范围内。");
        // 7.业务种类
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.busiType, "busiType", "业务种类要素不可为空。");
        // 文字长度固定长2
        IBDDataCheckUtil.fixLength(2, errorList, this.busiType, "busiType", "文字长度（固定长）2");
        // 枚举范围内、单选
        IBDDataCheckUtil.codeListSimpleCheck(DictCode.业务种类, errorList, this.busiType,
            "busiType", "业务种类不在值域范围内。");
        // 8.金额
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.amount, "amount", "金额要素不可为空。");
        // 数字类型(15,2)
        IBDDataCheckUtil.numberFormatCheck(15, 2, errorList, this.amount.toString(), "amount",
            "金额必须为n(15,2)格式。");
        // 必须大于等于0。
        IBDDataCheckUtil.numberLargeThanCheck(new BigDecimal(0), errorList,
            new BigDecimal(this.amount), "amount", "金额必须大于等于0。");
        // 9.折算人民币金额（元）
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.converCnyAmount, "converCnyAmount",
            "折算人民币金额（元）要素不可为空。");
        // 数字类型(15,2)
        IBDDataCheckUtil.numberFormatCheck(15, 2, errorList, this.converCnyAmount.toString(),
            "converCnyAmount", "折算人民币金额（元）必须为n(15,2)格式。");
        /*
         * //币种必须为“01 人民币(CNY)” if(DictCodeList.币种_人民币.getValue().equals(this.ccy)){ }
         */
        // 必须大于等于0。
        IBDDataCheckUtil.numberLargeThanCheck(new BigDecimal(0), errorList,
            new BigDecimal(this.converCnyAmount), "converCnyAmount", "折算人民币金额（元）必须大于等于0。");
        // 当金额的币种为“01 人民币(CNY)”时，原币种“金额”与该要素数值应相等。
        if (DictCodeListExt1.币种_中债_人民币.getValue().equals(this.ccy)) {
            IBDDataCheckUtil.mustEquals(this.amount, errorList, this.converCnyAmount,
                "converCnyAmount", "当金额的币种为人民币(CNY)时，折算人民币金额（元）与金额必须相等。");
        }
        // 10.份额
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.shares, "share", "份额要素不可为空。");
        // 数字类型(18,5)
        IBDDataCheckUtil.numberFormatCheck(18, 5, errorList, this.shares.toString(), "share",
            "份额必须为n(18,5)格式。");
        // 必须大于等于0。
        IBDDataCheckUtil.numberLargeThanCheck(new BigDecimal(0), errorList,
            new BigDecimal(this.shares), "share", "份额必须大于等于0。");
        // 11.渠道
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.channel, "channel", "渠道要素不可为空。");
        // 文字长度（固定长）2
        IBDDataCheckUtil.fixLength(2, errorList, this.channel, "channel", "文字长度（固定长）2");
        // 枚举范围内、单选
        IBDDataCheckUtil.codeListSimpleCheck(DictCode.投资者明细渠道, errorList, this.channel, "channel",
            "渠道不在值域范围内。");
        // 12.银行资金账号
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.fundAcc, "fundAcc", "银行资金账号要素不可为空。");
        // 文字长度（最大长）100
        IBDDataCheckUtil.maxLength(100, errorList, this.fundAcc, "fundAcc", "银行资金账号过长。");
        // 13.银行资金账号开户行
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.fundAccBank, "fundAccBank", "银行资金账号开户行要素不可为空。");
        // 文字长度（最大长）100
        IBDDataCheckUtil.maxLength(100, errorList, this.fundAccBank, "fundAccBank",
            "银行资金账号开户行过长。");
        // 14.备注
        if (DictCodeListExt2.销售区域_中债_其他国家或地区.getValue().equals(this.buyPlace)) {
            IBDDataCheckUtil.notEmpty(errorList, this.remark, "remark", "当购买地为其他国家或地区时，备注要素不可为空。");
        }
        // 文字长度（最大长）256
        IBDDataCheckUtil.maxLength(256, errorList, this.remark, "remark", "备注过长。");
        /* //15.登记流水号 */
        wr.setErrorList(errorList);
        return wr;
    }
}