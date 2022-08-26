package com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepFeeTrd;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.validator.SvrProdFeeDealInterfValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@PageId("SvrProdFeeDealInterf002")
@ValidLogic(targetClass = SvrProdFeeDealInterfValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class SvrProdFeeDealInterfDto extends RepFeeTrd implements ITableRow, ProcessEntity {
    /**
     * excle行
     */
    private String excelNum;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面类型
     */
    private String pageModel;

    /**
     * 选中的数据列表
     */
    private transient List<SvrProdFeeDealInterfDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 产品代码
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 支付日
     */
    private Date payDate;
    /**
     * 费用类型
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    private String feeType;
    /**
     * 费用金额
     */
    private BigDecimal paidFee;
    /**
     * 自成立以来发生额（贷方）
     */
    private BigDecimal accrual;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 错误信息
     */
    private String errmsg;

    private String payDateStr;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public List<SvrProdFeeDealInterfDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<SvrProdFeeDealInterfDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(BigDecimal paidFee) {
        this.paidFee = paidFee;
    }

    public BigDecimal getAccrual() {
        return accrual;
    }

    public void setAccrual(BigDecimal accrual) {
        this.accrual = accrual;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    public String getFeeTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品费用交易费用类型, this.getFeeType());
    }
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getPayDateStr() {
        if (StringUtil.isNotEmpty(this.payDateStr)) {
            return this.payDateStr;
        } else {
            return this.getPayDate() == null ? null : DateUtil.format(this.getPayDate(), "yyyy-MM-dd");
        }
    }

    public void setPayDateStr(String payDateStr) {
        this.payDateStr = payDateStr;
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }
    private DecimalFormat df2 = new DecimalFormat("0.00");
//    public String getPaidFeeStr() {
//        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
//        return this.getPaidFee()==null?" ":decimalFormat.format(this.getPaidFee().setScale(2,BigDecimal.ROUND_HALF_UP));
//    }
    public String getPaidFeeStr() {
        return this.getPaidFee()==null?"":df2.format(this.getPaidFee());
    }

    public String getAccrualStr(){
        return this.getAccrual()==null?"":df2.format(this.getAccrual());
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }
}
