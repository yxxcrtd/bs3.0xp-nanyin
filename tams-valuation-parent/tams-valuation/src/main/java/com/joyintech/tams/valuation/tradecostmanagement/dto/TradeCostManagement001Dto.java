package com.joyintech.tams.valuation.tradecostmanagement.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 交易费用管理 查询 Dto
 *
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
@PageId("TradeCostManagement001")
public class TradeCostManagement001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    // 交易编号
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    private String tradeId;

    // 账套代码
    private String bookSetId;

    // 账套名称
    private String bookSetName;

    // 费用类型
    private String feeType;

    // 支付日期
    private String payDate;

    // 应付日期
    private String mDate;

    // 币种
    private String ccy;

    // 费用金额
    private BigDecimal feeAmt;

    // 登记托管机构
    private String registOrg;

    // 是否待摊
    private String isPrepaid;

    // 备注
    private String remark;

    //
    private transient List<TradeCostManagement001Dto> tradeCostManagementDtoList;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getBookSetId() {
        return bookSetId;
    }

    public void setBookSetId(String bookSetId) {
        this.bookSetId = bookSetId;
    }

    public String getBookSetName() {
        return bookSetName;
    }

    public void setBookSetName(String bookSetName) {
        this.bookSetName = bookSetName;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getRegistOrg() {
        return registOrg;
    }

    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    public String getIsPrepaid() {
        return isPrepaid;
    }

    public void setIsPrepaid(String isPrepaid) {
        this.isPrepaid = isPrepaid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<TradeCostManagement001Dto> getTradeCostManagementDtoList() {
        return tradeCostManagementDtoList;
    }

    public void setTradeCostManagementDtoList(List<TradeCostManagement001Dto> tradeCostManagementDtoList) {
        this.tradeCostManagementDtoList = tradeCostManagementDtoList;
    }

    /**
     * 币种对应数据字典值
     * @return 数据字典值
     * @see
     * @since 1.0
     */
    public String getCcyFmt(){
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
            SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
    }
    
    /**
     * 登记托管机构对应数据字典值
     * @return 数据字典值
     * @see
     * @since 1.0
     */
    public String getRegistOrgFmt(){
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, registOrg))?
            SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, registOrg):registOrg;
    }
    
    /**
     * 一次性费用类型
     * @return 数据字典值
     * @see
     * @since 1.0
     */
    public String getFeeTypeFmt(){
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型, feeType))?
            SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型, feeType):feeType;
    }
    
    /**
     * 是否待摊
     * @return 数据字典值
     * @see
     * @since 1.0
     */
    public String getIsPrepaidFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, isPrepaid))?
            SysDataUtil.getNameFromDataDict(DictCode.是否, isPrepaid):isPrepaid;
    }
}
