/*
 * 文件名：InvestorsHoldInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfo;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.valid.InvestorsHoldInfoValidator;
import com.joyintech.tams.constant.DictCode;


/**
 * 投资者持有信息Dto
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年5月8日
 * @see InvestorsHoldInfo001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = InvestorsHoldInfoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class InvestorsHoldInfo001Dto extends ChbInvestholdInfo implements ITableRow{
    /**
     * 检查序列号
     */
    private String checkSeqno;
    /**
     * 行号
     */
    private String rowId;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * pageModel
     */
    private String pageModel;
    /**
     * 数据状态（name）
     */
    private String statusCode;

    /**
     * 币种（name）
     */
    private String ccyCode;

    /**
     * 币种（导出用）
     */
    private String ccyCodeForExport;

    /**
     * 中债反馈信息 导出中债反馈页面使用
     */
    private String repFailedInfo;

//    /**
//     * 产品代码
//     */
//    private String prodId;
    /**
     * 接入检查结果
     */
    private String interfCheckResult;

    /**
     * 选中的数据
     */
    private List <InvestorsHoldInfo001Dto > selectedItem;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1209472438132307042L;
    /**
     * 重写get方法添加验证规则
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "InvestorsHoldInfo001.prdRegistCode", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "InvestorsHoldInfo001.prdRegistCode", max = 200, groups = {ValidateGroupEdit.class})
    public String getPrdRegistCode() {
        return super.getPrdRegistCode();
    }
    /**
     * 重写get方法添加验证规则
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "InvestorsHoldInfo001.indentifyMark", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "InvestorsHoldInfo001.indentifyMark", max = 200, groups = {ValidateGroupEdit.class})
    public String getIndentifyMark() {
        return super.getIndentifyMark();
    }
    /**
     * 重写get方法添加验证规则
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "InvestorsHoldInfo001.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy() {
        return super.getCcy();
    }
    /**
     * 重写get方法添加验证规则
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "InvestorsHoldInfo001.holdDate", groups = {ValidateGroupEdit.class})
    public Date getHoldDate() {
        return super.getHoldDate();
    }
    /**
     * 重写get方法添加验证规则
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "InvestorsHoldInfo001.qty", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "InvestorsHoldInfo001.qty",canMinus=true, maxIntBit = 13,maxDecBit=5, groups = {ValidateGroupEdit.class})
    public BigDecimal getQty() {
        return super.getQty();
    }    /**
     * 重写get方法添加验证规则
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "InvestorsHoldInfo001.amount", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "InvestorsHoldInfo001.amount",canMinus=true, maxIntBit = 13,maxDecBit=2, groups = {ValidateGroupEdit.class})
    public BigDecimal getAmount() {
        return super.getAmount();
    }
    /**
     * 重写get方法添加验证规则
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "InvestorsHoldInfo001.cnyAmt", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "InvestorsHoldInfo001.cnyAmt",canMinus=true, maxIntBit = 13,maxDecBit=2, groups = {ValidateGroupEdit.class})
    public BigDecimal getCnyAmt() {
        return super.getCnyAmt();
    }

    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 statusCode
     * @return statusCode.
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 设置 statusCode
     * @param statusCode statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<InvestorsHoldInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<InvestorsHoldInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 ccyCode
     * @return ccyCode.
     */
    public String getCcyCode() {
        return ccyCode;
    }

    /**
     * 设置 ccyCode
     * @param ccyCode ccyCode
     */
    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }



    public String getRepFailedInfo() {
        return repFailedInfo;
    }
    public void setRepFailedInfo(String repFailedInfo) {
        this.repFailedInfo = repFailedInfo;
    }
    /**
     * 获取 rowId
     * @return rowId.
     */
    public String getRowId() {
        return rowId;
    }

    /**
     * 设置 rowId
     * @param rowId rowId
     */
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

//    /**
//     * 获取 prodId
//     * @return prodId.
//     */
//    public String getProdId() {
//        return prodId;
//    }
//
//    /**
//     * 设置 prodId
//     * @param prodId prodId
//     */
//    public void setProdId(String prodId) {
//        this.prodId = prodId;
//    }

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
     * 获取 ccyCodeForExport
     * @return ccyCodeForExport.
     */
    public String getCcyCodeForExport() {
        return StringUtil.getOrElse(this.getCcy(), "") + " " + SysDataUtil.getNameFromDataDict(DictCode.币种_中债,
                StringUtil.getOrElse(this.getCcy(), ""));
        //       return StringUtil.getOrElse(this.getCcy(), "") + " " + StringUtil.getOrElse(this.getCcyCode(), "");
    }

    /**
     * 设置 ccyCodeForExport
     * @param ccyCodeForExport ccyCodeForExport
     */
    public void setCcyCodeForExport(String ccyCodeForExport) {
        this.ccyCodeForExport = ccyCodeForExport;
    }

    /**
     * 获取 checkSeqno
     * @return checkSeqno.
     */
    public String getCheckSeqno() {
        return checkSeqno;
    }

    /**
     * 设置 checkSeqno
     * @param checkSeqno checkSeqno
     */
    public void setCheckSeqno(String checkSeqno) {
        this.checkSeqno = checkSeqno;
    }

    /**
     * 获取 interfCheckResult
     * @return interfCheckResult.
     */
    public String getInterfCheckResult() {
        return interfCheckResult;
    }
    /**
     * 设置 interfCheckResult
     * @param interfCheckResult interfCheckResult
     */
    public void setInterfCheckResult(String interfCheckResult) {
        this.interfCheckResult = interfCheckResult;
    }

    // 导出报错新添加的 （徽商） njl  start

    public String getHoldDStr() {
        return DateUtil.format(super.getHoldDate());
    }

    public BigDecimal getQqtyStr() {
        return super.getQty();
    }


    // 导出报错新添加的 （徽商） njl  end

}
