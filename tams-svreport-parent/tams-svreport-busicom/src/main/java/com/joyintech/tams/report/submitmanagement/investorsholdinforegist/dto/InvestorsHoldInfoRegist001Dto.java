/*
 * 文件名：InvestorsHoldInfoRegist001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto;

import java.util.Date;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSub;
import com.joyintech.tams.constant.DictCode;

/**
 * 投资者持有信息登记dto
 *
 * @author zuoyinyi
 * @version 1.0 2018年5月8日
 * @see InvestorsHoldInfoRegist001Dto
 * @since 1.0
 */
@TableName("CHB_INVESTHOLD_INFO_SUB")
public class InvestorsHoldInfoRegist001Dto extends ChbInvestholdInfoSub implements ITableRow {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6696998971727547507L;

    /**
     * 登记银行代码
     */
    @BusinessKey("REGIST_BANK_CODE")
    private String registBankCode;

    /**
     * 产品登记编码
     */
    @BusinessKey("PRD_REGIST_CODE")
    private String prdRegistCode;

    /**
     * 识别标识
     */
    @BusinessKey("INDENTIFY_MARK")
    private String indentifyMark;

    /**
     * 币种
     */
    @BusinessKey("CCY")
    private String ccy;

    /**
     * 持有日期
     */
    @BusinessKey("HOLD_DATE")
    private Date holdDate;

    /**
     * 持有日期（导出中债模板用 yyyyMMdd）
     */
    private String holdDateForExportCBExcel;

    /**
     * 持有日期（导出查询结果用 yyyy-MM-dd）
     */
    private String holdDateForExport;

    /**
     * 数据状态名称
     */
    private String statusCode;

    /**
     * 币种名称
     */
    private String ccyCode;

    /**
     * 报送状态名称
     */
    private String sendStatusCode;

    /**
     * 反馈状态
     */
    @DictFormat(DictCode.报送反馈状态)
    private String submitFeedbackStatus;

    /**
     * 标志
     */
    private String flag;

    /**
     * 是否有逻辑校验
     */
    private String isLogicCheck;

    /**
     * 持有金额 导出中债excel用
     */
    private String amountExportExcel;

    /**
     * 持有份额 导出中债excel用
     */
    private String qtyExportExcel;

    /**
     * 转换人民币金额 导出中债excel用
     */
    private String cnyAmtExportExcel;



    public String getAmountExportExcel() {
        return amountExportExcel;
    }

    public void setAmountExportExcel(String amountExportExcel) {
        this.amountExportExcel = amountExportExcel;
    }

    public String getQtyExportExcel() {
        return qtyExportExcel;
    }

    public void setQtyExportExcel(String qtyExportExcel) {
        this.qtyExportExcel = qtyExportExcel;
    }

    public String getCnyAmtExportExcel() {
        return cnyAmtExportExcel;
    }

    public void setCnyAmtExportExcel(String cnyAmtExportExcel) {
        this.cnyAmtExportExcel = cnyAmtExportExcel;
    }

    /**
     * 获取 flag
     *
     * @return flag.
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置 flag
     *
     * @param flag flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 获取 statusCode
     *
     * @return statusCode.
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 设置 statusCode
     *
     * @param statusCode statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 获取 ccyCode
     *
     * @return ccyCode.
     */
    public String getCcyCode() {
        return ccyCode;
    }

    /**
     * 设置 ccyCode
     *
     * @param ccyCode ccyCode
     */
    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    /**
     * 获取 sendStatusCode
     *
     * @return sendStatusCode.
     */
    public String getSendStatusCode() {
        return sendStatusCode;
    }

    /**
     * 设置 sendStatusCode
     *
     * @param sendStatusCode sendStatusCode
     */
    public void setSendStatusCode(String sendStatusCode) {
        this.sendStatusCode = sendStatusCode;
    }

    /**
     * 获取 submitFeedbackStatus
     *
     * @return submitFeedbackStatus.
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    public String getSubmitFeedbackStatusStr() {
        return submitFeedbackStatus==null?null:SysDataUtil.getNameFromDataDict(DictCode.报送反馈状态, this.submitFeedbackStatus);
    }

    /**
     * 设置 submitFeedbackStatus
     *
     * @param submitFeedbackStatus submitFeedbackStatus
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus;
    }

    /**
     * 获取 registBankCode
     *
     * @return registBankCode.
     */
    @Override
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 registBankCode
     *
     * @param registBankCode registBankCode
     */
    @Override
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode;
    }

    /**
     * 获取 prdRegistCode
     *
     * @return prdRegistCode.
     */
    @Override
    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    /**
     * 设置 prdRegistCode
     *
     * @param prdRegistCode prdRegistCode
     */
    @Override
    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode;
    }

    /**
     * 获取 indentifyMark
     *
     * @return indentifyMark.
     */
    @Override
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 indentifyMark
     *
     * @param indentifyMark indentifyMark
     */
    @Override
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark;
    }

    /**
     * 获取 ccy
     *
     * @return ccy.
     */
    @Override
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    @Override
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 holdDate
     *
     * @return holdDate.
     */
    @Override
    public Date getHoldDate() {
        return holdDate;
    }

    /**
     * 设置 holdDate
     *
     * @param holdDate holdDate
     */
    @Override
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
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
     * 获取 holdDateForExportCBExcel
     *
     * @return holdDateForExportCBExcel.
     */
    public String getHoldDateForExportCBExcel() {
        holdDateForExportCBExcel = DateUtil.format(getHoldDate(), DateUtil.DEFAULT_OTHER_FORMAT);
        return holdDateForExportCBExcel;
    }

    /**
     * 设置 holdDateForExportCBExcel
     *
     * @param holdDateForExportCBExcel holdDateForExportCBExcel
     */
    public void setHoldDateForExportCBExcel(String holdDateForExportCBExcel) {
        this.holdDateForExportCBExcel = holdDateForExportCBExcel;
    }

    /**
     * 获取 holdDateForExport
     *
     * @return holdDateForExport.
     */
    public String getHoldDateForExport() {
        holdDateForExport = DateUtil.format(getHoldDate());
        return holdDateForExport;
    }

    /**
     * 设置 holdDateForExport
     *
     * @param holdDateForExport holdDateForExport
     */
    public void setHoldDateForExport(String holdDateForExport) {
        this.holdDateForExport = holdDateForExport;
    }

    /**
     * 获取 isLogicCheck
     *
     * @return isLogicCheck.
     */
    public String getIsLogicCheck() {
        return isLogicCheck;
    }

    /**
     * 设置 isLogicCheck
     *
     * @param isLogicCheck isLogicCheck
     */
    public void setIsLogicCheck(String isLogicCheck) {
        this.isLogicCheck = isLogicCheck;
    }

    /**
     * @Fields submitSendStatus : (发送状态)
     */
    private String submitSendStatus;



    public String getSubmitSendStatus() {
        return submitSendStatus;
    }

    public void setSubmitSendStatus(String submitSendStatus) {
        this.submitSendStatus = submitSendStatus;
    }

    // 导出报错新添加的 （徽商） njl  start
    public String getHoldDateStr() {
        if(StringUtil.isNotEmpty(this.getHoldDate())){
            return DateUtil.formatDateTime(this.getHoldDate());
        }
        return null;
    }

    public String getUpdateTimeStr() {
        return DateUtil.formatDateTime(super.getUpdateTime());
    }


    /**
     * 取得 报送日期
     * @return 报送日期
     */
    public String getSubmitTimeStr() {
        return DateUtil.formatDateTime(super.getSubmitTime());
    }


    /**
     * 取得 中债报送截止日
     * @return 中债报送截止日
     */
    public String getChbSubmitDeadlineStr() {
        return DateUtil.formatDateTime(super.getChbSubmitDeadline());
    }

    // 导出报错新添加的 （徽商） njl  end
}
