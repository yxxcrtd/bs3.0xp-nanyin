package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCmsTcmsInfo extends BaseEntity {
    /**
     * 放款指令编号
     */
    private String putoutno;

    /**
     * 币种
     */
    private String currency;

    /**
     * 放款指令总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal businesssum;

    /**
     * 已使用金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amountsum;

    /**
     * 剩余可用金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balancesum;

    /**
     * 分行核心机构号
     */
    private String branch;

    /**
     * BECIF号
     */
    private String becifno;

    /**
     * 信贷客户编号
     */
    private String customerid;

    /**
     * 客户名称
     */
    private String customername;

    /**
     * 信贷业务品种编号
     */
    private String businesstype;

    /**
     * 信贷业务品种中文
     */
    private String businesstypename;

    /**
     * 业务合同编号
     */
    private String artificialno;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 执行利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal businessrate;

    /**
     * 有效标识，A-新建 E-有效 D-删除
     */
    private String effectflag;

    /**
     * 最后更新时间
     */
    private Date lstmntdate;

    /**
     * 最后更新用户
     */
    private String lstmntuser;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 撤销原因
     */
    private String reason;

    /**
     * 卖出余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sellbalance;

    /**
     * 卖出日期
     */
    private Date selldate;

    /**
     * 还本日期
     */
    private Date returndate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 放款指令编号
     * @return 放款指令编号
     */
    public String getPutoutno() {
        return putoutno;
    }

    /**
     * 设置 放款指令编号
     * @param putoutno 放款指令编号
     */
    public void setPutoutno(String putoutno) {
        this.putoutno = putoutno == null ? null : putoutno.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置 币种
     * @param currency 币种
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     * 取得 放款指令总金额
     * @return 放款指令总金额
     */
    public BigDecimal getBusinesssum() {
        return businesssum;
    }

    /**
     * 设置 放款指令总金额
     * @param businesssum 放款指令总金额
     */
    public void setBusinesssum(BigDecimal businesssum) {
        this.businesssum = businesssum;
    }

    /**
     * 取得 已使用金额
     * @return 已使用金额
     */
    public BigDecimal getAmountsum() {
        return amountsum;
    }

    /**
     * 设置 已使用金额
     * @param amountsum 已使用金额
     */
    public void setAmountsum(BigDecimal amountsum) {
        this.amountsum = amountsum;
    }

    /**
     * 取得 剩余可用金额
     * @return 剩余可用金额
     */
    public BigDecimal getBalancesum() {
        return balancesum;
    }

    /**
     * 设置 剩余可用金额
     * @param balancesum 剩余可用金额
     */
    public void setBalancesum(BigDecimal balancesum) {
        this.balancesum = balancesum;
    }

    /**
     * 取得 分行核心机构号
     * @return 分行核心机构号
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 分行核心机构号
     * @param branch 分行核心机构号
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * 取得 BECIF号
     * @return BECIF号
     */
    public String getBecifno() {
        return becifno;
    }

    /**
     * 设置 BECIF号
     * @param becifno BECIF号
     */
    public void setBecifno(String becifno) {
        this.becifno = becifno == null ? null : becifno.trim();
    }

    /**
     * 取得 信贷客户编号
     * @return 信贷客户编号
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * 设置 信贷客户编号
     * @param customerid 信贷客户编号
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    /**
     * 取得 客户名称
     * @return 客户名称
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * 设置 客户名称
     * @param customername 客户名称
     */
    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    /**
     * 取得 信贷业务品种编号
     * @return 信贷业务品种编号
     */
    public String getBusinesstype() {
        return businesstype;
    }

    /**
     * 设置 信贷业务品种编号
     * @param businesstype 信贷业务品种编号
     */
    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
    }

    /**
     * 取得 信贷业务品种中文
     * @return 信贷业务品种中文
     */
    public String getBusinesstypename() {
        return businesstypename;
    }

    /**
     * 设置 信贷业务品种中文
     * @param businesstypename 信贷业务品种中文
     */
    public void setBusinesstypename(String businesstypename) {
        this.businesstypename = businesstypename == null ? null : businesstypename.trim();
    }

    /**
     * 取得 业务合同编号
     * @return 业务合同编号
     */
    public String getArtificialno() {
        return artificialno;
    }

    /**
     * 设置 业务合同编号
     * @param artificialno 业务合同编号
     */
    public void setArtificialno(String artificialno) {
        this.artificialno = artificialno == null ? null : artificialno.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 执行利率
     * @return 执行利率
     */
    public BigDecimal getBusinessrate() {
        return businessrate;
    }

    /**
     * 设置 执行利率
     * @param businessrate 执行利率
     */
    public void setBusinessrate(BigDecimal businessrate) {
        this.businessrate = businessrate;
    }

    /**
     * 取得 有效标识，A-新建 E-有效 D-删除
     * @return 有效标识，A-新建 E-有效 D-删除
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 有效标识，A-新建 E-有效 D-删除
     * @param effectflag 有效标识，A-新建 E-有效 D-删除
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 取得 最后更新时间
     * @return 最后更新时间
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 最后更新时间
     * @param lstmntdate 最后更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 最后更新用户
     * @return 最后更新用户
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 最后更新用户
     * @param lstmntuser 最后更新用户
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
    }

    /**
     * 取得 创建日期
     * @return 创建日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置 创建日期
     * @param createdate 创建日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 取得 撤销原因
     * @return 撤销原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置 撤销原因
     * @param reason 撤销原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 取得 卖出余额
     * @return 卖出余额
     */
    public BigDecimal getSellbalance() {
        return sellbalance;
    }

    /**
     * 设置 卖出余额
     * @param sellbalance 卖出余额
     */
    public void setSellbalance(BigDecimal sellbalance) {
        this.sellbalance = sellbalance;
    }

    /**
     * 取得 卖出日期
     * @return 卖出日期
     */
    public Date getSelldate() {
        return selldate;
    }

    /**
     * 设置 卖出日期
     * @param selldate 卖出日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSelldate(Date selldate) {
        this.selldate = selldate;
    }

    /**
     * 取得 还本日期
     * @return 还本日期
     */
    public Date getReturndate() {
        return returndate;
    }

    /**
     * 设置 还本日期
     * @param returndate 还本日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}