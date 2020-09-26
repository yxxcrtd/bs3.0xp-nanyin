/*
 * 文件名：EquiteBaseInfo001Dto.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.submitmaintenance.businessdetailed.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.svreport.chb.constant.ProdConstants;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.base.TableRow;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.common.util.BotCheckUtil;
import com.joyintech.tams.report.common.util.IBDDataCheckUtil;

/**
 * 〈一句话功能简述〉
 *
 * @author zyf
 * @version 1.0 2017年9月14日
 * @see EquityBusiInfo001Dto
 * @since 1.0
 */
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年11月16日
 * @see EquityBusiInfo001Dto
 * @since 1.0
 */
@TableName("CHB_EQUDET_SUB")
public class EquityBusiInfo001Dto extends TableRow {
    /**
     * 报送流水号
     *
     */
    private String sumitSeq;

    /**
     * 获取 sumitSeq
     * @return sumitSeq.
     */
    public String getSumitSeq() {
        return sumitSeq == null?"":sumitSeq;
    }



    /**
     * 设置 sumitSeq
     * @param sumitSeq sumitSeq
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq;
    }
    /**
     * 机构代码
     */
    private String orgCode;


    /**
     * 获取 orgCode
     * @return orgCode.
     */
    public String getOrgCode() {
        return orgCode == null?"":orgCode;
    }


    /**
     * 设置 orgCode
     * @param orgCode orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    /**
     * 数据状态
     */
    private String status;
    
    /**
     * 数据状态名
     */
    private String statusName;

    /**
     * 数据日期
     */
    private Date updateTime;

    /**
     * 报送状态
     */
    private String submitStatus;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;
    /**
     * 报送截止日
     */
    private String submitDeadline;

    /**
     * 反馈状态
     */
    private String submitFeedbackStatus;

    /**
     * 登记银行代码
     */
    @BusinessKey("REGIST_BANK_CODE")
    private String registBankCode;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 业务流水号
     */
    private String busiSeqno;

    /**
     * 权益确认日期
     */
    private Date equityConfDate;
    
    /**
     * 权益确认日期字符串格式
     */
    private String equityConfDateStr;

    /**
     * 识别标号
     */
    @BusinessKey("INDENTIFY_MARK")
    private String indentifyMark;

    /**
     * 购买地
     */
    @DictFormat(DictCode.销售区域_中债)
    private String buyPlace;

    /**
     * 业务种类
     */
    @DictFormat(DictCode.业务种类)
    private String busiType;

    /**
     * 业务种类Key
     */
    private String busiTypeKey;

    /**
     * 币种
     */
    @DictFormat(DictCode.币种_中债)
    private String ccy;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 折算人民币金额（元）
     */
    private BigDecimal converCnyAmount;

    /**
     * 份额
     */
    private BigDecimal shares;
    /**
     * 份额
     */
    private String sharesStr;

    /**
     * 渠道
     */
    @DictFormat(DictCode.投资者明细渠道)
    private String channel;

    /**
     * 银行资金账号
     */
    private String fundAcc;

    /**
     * 银行资金账号开户行
     */
    private String fundAccBank;

    /**
     * 备注
     */
    private String remark;

    /**
     * 产品代码
     */
    private String account;

    /**
     * 产品名称
     */
    private String accountName;

    /**
     * 是否能代销
     */
    private String isConsign;

    /**
     * 登记流水号
     */
    private String regSerialNumber;
    /**
     * 报送状态Name
     */
    private String submitStatusName;

    public String getRegSerialNumber() {
        return regSerialNumber ==null?"":regSerialNumber;
    }

    public void setRegSerialNumber(String regSerialNumber) {
        this.regSerialNumber = regSerialNumber;
    }

    public String getIsConsign() {
        return isConsign == null?"":isConsign;
    }

    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign;
    }

    public String getAccount() {
        return account == null?"":account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountName() {
        return accountName == null?"":accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 获取 fundAcc
     * @return fundAcc.
     */
    public String getFundAcc() {
        return fundAcc == null?"":fundAcc;
    }



    /**
     * 设置 fundAcc
     * @param fundAcc fundAcc
     */
    public void setFundAcc(String fundAcc) {
        this.fundAcc = fundAcc;
    }


    /**
     * 获取 status
     * @return status.
     */
    public String getStatus() {
        return status == null?"":status;
    }


    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * 获取 updateTime
     * @return updateTime.
     */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
     * 设置 updateTime
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * 获取 submitStatus
     * @return submitStatus.
     */
    public String getSubmitStatus() {
        return submitStatus == null?"":submitStatus;
    }


    /**
     * 设置 submitStatus
     * @param submitStatus submitStatus
     */
    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }


    /**
     * 获取 submitTime
     * @return submitTime.
     */
    public Date getSubmitTime() {
        return submitTime ;
    }


    /**
     * 设置 submitTime
     * @param submitTime submitTime
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }


    /**
     * 获取 chbSubmitDeadline
     * @return chbSubmitDeadline.
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }



    /**
     * 设置 chbSubmitDeadline
     * @param chbSubmitDeadline chbSubmitDeadline
     */
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }


    /**
     * 获取 submitFeedbackStatus
     * @return submitFeedbackStatus.
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus == null?"":submitFeedbackStatus;
    }


    /**
     * 设置 submitFeedbackStatus
     * @param submitFeedbackStatus submitFeedbackStatus
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus;
    }


    /**
     * 获取 registBankCode
     * @return registBankCode.
     */
    public String getRegistBankCode() {
        return registBankCode  == null?"":registBankCode;
    }


    /**
     * 设置 registBankCode
     * @param registBankCode registBankCode
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode;
    }


    /**
     * 获取 regCode
     * @return regCode.
     */
    public String getRegCode() {
        return regCode == null?"":regCode;
    }


    /**
     * 设置 regCode
     * @param regCode regCode
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }


    /**
     * 获取 busiSeqno
     * @return busiSeqno.
     */
    public String getBusiSeqno() {
        return busiSeqno == null?"":busiSeqno;
    }


    /**
     * 设置 busiSeqno
     * @param busiSeqno busiSeqno
     */
    public void setBusiSeqno(String busiSeqno) {
        this.busiSeqno = busiSeqno;
    }


    /**
     * 获取 equityConfDate
     * @return equityConfDate.
     */
    public Date getEquityConfDate() {
        return equityConfDate;
    }


    /**
     * 设置 equityConfDate
     * @param equityConfDate equityConfDate
     */
    public void setEquityConfDate(Date equityConfDate) {
        this.equityConfDate = equityConfDate;
    }


    /**
     * 获取 indentifyMark
     * @return indentifyMark.
     */
    public String getIndentifyMark() {
        return indentifyMark == null?"":indentifyMark;
    }


    /**
     * 设置 indentifyMark
     * @param indentifyMark indentifyMark
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark;
    }


    /**
     * 获取 buyPlace
     * @return buyPlace.
     */
    public String getBuyPlace() {
        return buyPlace  == null?"":buyPlace;
    }


    /**
     * 设置 buyPlace
     * @param buyPlace buyPlace
     */
    public void setBuyPlace(String buyPlace) {
        this.buyPlace = buyPlace;
    }


    /**
     * 获取 busiType
     * @return busiType.
     */
    public String getBusiType() {
        return busiType == null?"":busiType ;
    }


    /**
     * 设置 busiType
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }


    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy  == null?"":ccy;
    }


    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }


    /**
     * 获取 amount
     * @return amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }


    /**
     * 设置 amount
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    /**
     * 获取 converCnyAmount
     * @return converCnyAmount.
     */
    public BigDecimal getConverCnyAmount() {
        return converCnyAmount;
    }


    /**
     * 设置 converCnyAmount
     * @param converCnyAmount converCnyAmount
     */
    public void setConverCnyAmount(BigDecimal converCnyAmount) {
        this.converCnyAmount = converCnyAmount;
    }


    /**
     * 获取 shares
     * @return shares.
     */
    public BigDecimal getShares() {
        return shares;
    }



    /**
     * 获取 sharesStr
     * @return sharesStr.
     */
    public String getSharesStr() {
        return sharesStr;
    }




    /**
     * 设置 sharesStr
     * @param sharesStr sharesStr
     */
    public void setSharesStr(String sharesStr) {
        this.sharesStr = sharesStr;
    }



    /**
     * 设置 shares
     * @param shares shares
     */
    public void setShares(BigDecimal shares) {
        this.shares = shares;
        setSharesStr(shares.toString());
    }


    /**
     * 获取 channel
     * @return channel.
     */
    public String getChannel() {
        return channel == null?"":channel;
    }


    /**
     * 设置 channel
     * @param channel channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }


    /**
     * 获取 fundAccBank
     * @return fundAccBank.
     */
    public String getFundAccBank() {
        return fundAccBank == null?"":fundAccBank;
    }


    /**
     * 设置 fundAccBank
     * @param fundAccBank fundAccBank
     */
    public void setFundAccBank(String fundAccBank) {
        this.fundAccBank = fundAccBank;
    }


    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark == null?"":remark;
    }


    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * 获取 submitStatusName
     * @return submitStatusName.
     */
    public String getSubmitStatusName() {
        return submitStatusName == null?"":submitStatusName;
    }




    /**
     * 设置 submitStatusName
     * @param submitStatusName submitStatusName
     */
    public void setSubmitStatusName(String submitStatusName) {
        this.submitStatusName = submitStatusName;
    }



    /**
     * 合法性检查
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult validCheck() {
    	AjaxResult wr = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();

        //1.登记银行代码
        // 必填
        IBDDataCheckUtil.notEmpty(errorList, this.registBankCode, "registBankCode", "登记银行代码要素不可为空。");
        // 文字长度（固定长）6
        IBDDataCheckUtil.fixLength(6, errorList, this.registBankCode, "registBankCode",
                "文字长度（固定长）6");
        /*//该代码必须与登记的银行一致*/
        // 正则表达式：CXXXXX
//        IBDDataCheckUtil.regMatcher("^C.....", errorList, this.registBankCode, "registBankCode",
//                "登记银行代码要素格式不对。正确的格式：CXXXXX（XXXXX为数字）。");
        //2.产品登记编码
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.regCode, "regCode", "产品登记编码要素不可为空。"); //不正确
        //正则表达式：字母C+7位数字+字母A，B，C（三个中的1个或0个）+6位数字
//        IBDDataCheckUtil.regMatcher("^C[0-9]{7}[A,B,C]{0,1}[0-9]{6}", errorList, this.regCode, "regCode",
//                "产品登记编码要素格式不对。正确的格式：字母C+7位数字+字母A，B，C（三个中的1个或0个）+6位数字。");
        //3.业务流水号
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.busiSeqno, "busiSeqno", "业务流水号要素不可为空。");
        //文字长度40（最大长）
        IBDDataCheckUtil.maxLength(40, errorList, this.busiSeqno, "busiSeqno","业务流水号过长。");
        /*//同一个登记银行，对于同一只理财产品，业务流水号不得重复。
*/
        //4.业务确认日期
        //必填
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        IBDDataCheckUtil.notEmpty(errorList, this.equityConfDate, "eQuityConfDate", "业务确认日期要素不可为空。");
        // 日期类型 YYYY-MM-DD
        if(StringUtil.isNotEmpty(this.equityConfDate)){
            BotCheckUtil.dateFormatCheck(DateUtil.DEFAULT_DATE_FORMAT_EN, errorList,
                    sdf.format(this.equityConfDate), "eQuityConfDate", "业务确认日期必须为日期格式（YYYY-MM-DD）。");
        }
        //5.识别标识
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.indentifyMark, "indentifyMark", "识别标识要素不可为空。");
        // 不得含有汉字（只允许白名单中的阿拉伯数字，英文字母，半角符号、全角符号）
        IBDDataCheckUtil.regMatcher("[^\u4e00-\u9fa5]+", errorList, this.indentifyMark, "indentifyMark",
                "识别标识只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。");
        //文字长度30（最大长）
        IBDDataCheckUtil.maxLength(30, errorList, this.indentifyMark, "indentifyMark","识别标识过长。");
        //6.购买地
        //当“业务种类”选择“01 募集期认购”或“02 存续期申购”时，必填。
        if(DictCodeListExt2.业务种类_募集期认购.getValue().equals(this.busiType) || DictCodeListExt2.业务种类_存续期申购.getValue().equals(this.busiType)){
            IBDDataCheckUtil.notEmpty(errorList, this.buyPlace, "buyPlace", "当业务种类为募集期认购时，购买地要素不可为空。");
        }
        //文字长度(固定长)6
        IBDDataCheckUtil.fixLength(6, errorList, this.buyPlace, "buyPlace","文字长度（固定长）6");
        // 枚举范围内、单选
        IBDDataCheckUtil.codeListSimpleCheck(DictCode.销售区域_中债, errorList, this.buyPlace,"buyPlace", "购买地不在值域范围内。");
        //7.业务种类
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.busiType, "busiType", "业务种类要素不可为空。");
        //文字长度固定长2
        IBDDataCheckUtil.fixLength(2, errorList, this.busiType, "busiType","文字长度（固定长）2");
        // 枚举范围内、单选
        IBDDataCheckUtil.codeListSimpleCheck(DictCode.业务种类, errorList, this.busiType,"busiType", "业务种类不在值域范围内。");
        //8.金额
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.amount, "amount", "金额要素不可为空。");
        //数字类型(15,2)
        if(StringUtil.isNotEmpty(this.amount)){
            IBDDataCheckUtil.numberFormatCheck(15,2,errorList,this.amount.toString(), "amount","金额必须为n(15,2)格式。");
            //必须大于等于0。
            IBDDataCheckUtil.numberLargeThanCheck(new BigDecimal(0),errorList,this.amount,"amount","金额必须大于等于0。");
        }
        //9.折算人民币金额（元）
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.converCnyAmount, "converCnyAmount", "折算人民币金额（元）要素不可为空。");
        //数字类型(15,2)
        if(StringUtil.isNotEmpty(this.converCnyAmount)){
            IBDDataCheckUtil.numberFormatCheck(15,2,errorList,this.converCnyAmount.toString(), "converCnyAmount","折算人民币金额（元）必须为n(15,2)格式。");
            //必须大于等于0。
            IBDDataCheckUtil.numberLargeThanCheck(new BigDecimal(0),errorList,this.converCnyAmount,"converCnyAmount","折算人民币金额（元）必须大于等于0。");
            //当金额的币种为“01 人民币(CNY)”时，原币种“金额”与该要素数值应相等。
            if(DictCodeListExt1.币种_中债_人民币.getValue().equals(StringUtil.trim(this.ccy))){
                IBDDataCheckUtil.mustEquals(this.amount,errorList, this.converCnyAmount, "converCnyAmount","当金额的币种为人民币(CNY)时，折算人民币金额（元）与金额必须相等。");
            }
        }
        //10.份额
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.shares, "share", "份额要素不可为空。");
        //数字类型(18,5)
        if(StringUtil.isNotEmpty(this.shares)){
            IBDDataCheckUtil.numberFormatCheck(18,5,errorList,this.shares.toString(), "share","份额必须为n(18,5)格式。");
            //必须大于等于0。
            IBDDataCheckUtil.numberLargeThanCheck(new BigDecimal(0),errorList,this.shares,"share","份额必须大于等于0。");
        }
        //11.渠道
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.channel, "channel", "渠道要素不可为空。");
        //文字长度（固定长）2
        IBDDataCheckUtil.fixLength(2, errorList, this.channel, "channel","文字长度（固定长）2");
        //枚举范围内、单选
        IBDDataCheckUtil.codeListSimpleCheck(DictCode.投资者明细渠道, errorList, this.channel,"channel","渠道不在值域范围内。");
        //12.银行资金账号
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.fundAcc, "fundAcc", "银行资金账号要素不可为空。");
        //文字长度（最大长）100
        IBDDataCheckUtil.maxLength(100, errorList, this.fundAcc, "fundAcc","银行资金账号过长。");
        //13.银行资金账号开户行
        //必填
        IBDDataCheckUtil.notEmpty(errorList, this.fundAccBank, "fundAccBank", "银行资金账号开户行要素不可为空。");
        //文字长度（最大长）100
        IBDDataCheckUtil.maxLength(100, errorList, this.fundAccBank, "fundAccBank","银行资金账号开户行过长。");
        //14.备注
        if(DictCodeListExt2.销售区域_中债_其他国家或地区.getValue().equals(this.buyPlace)){
            IBDDataCheckUtil.notEmpty(errorList, this.remark, "remark", "当购买地为其他国家或地区时，备注要素不可为空。");
        }
        //文字长度（最大长）256
        IBDDataCheckUtil.maxLength(256, errorList, this.remark, "remark","备注过长。");
        /*//15.登记流水号*/
        wr.setErrorList(errorList);
        return wr;
    }


    /**
     * 获取 submitDeadline
     * @return submitDeadline.
     */
    public String getSubmitDeadline() {
        return submitDeadline;
    }


    /**
     * 设置 submitDeadline
     * @param submitDeadline submitDeadline
     */
    public void setSubmitDeadline(String submitDeadline) {
        this.submitDeadline = submitDeadline;
    }



    public String getBusiTypeKey() {
        return busiTypeKey;
    }



    public void setBusiTypeKey(String busiTypeKey) {
        this.busiTypeKey = busiTypeKey;
    }



    
    public String getEquityConfDateStr() {
        return equityConfDateStr;
    }



    
    public void setEquityConfDateStr(String equityConfDateStr) {
        this.equityConfDateStr = equityConfDateStr;
    }



    
    /**
     * 获取 statusName
     * @return statusName.
     */
    public String getStatusName() {
        return statusName;
    }



    
    /**
     * 设置 statusName
     * @param statusName statusName
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
