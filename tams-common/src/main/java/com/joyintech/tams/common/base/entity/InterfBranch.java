package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfBranch extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 系统平台日期
     */
    private String cdate;

    /**
     * 分行号
     */
    private String branchid;

    /**
     * 分行名称
     */
    private String bankname;

    /**
     * 449188接口.分行认购客户数、449191接口.分行认购客户数、449194接口.分行认购客户数、449186接口.分行认购客户数
     */
    private Short branchbuynum;

    /**
     * 449188接口.分行持有金额、449191接口.分行持有金额、449194接口.分行持有金额、449183接口.本金、449283接口.本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal branchaccountamount;

    /**
     * 449181接口.分行募集金额、449183接口.募集、449283接口.募集、449186接口.分行募集份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal branchbuyamount;

    /**
     * 449181接口.分行总赎回金额、449183接口.赎回、449283接口.赎回、449186接口.分行总赎回份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal branchdrawamount;

    /**
     * 接口ID
     */
    private String interfId;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 系统平台日期
     * @return 系统平台日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 系统平台日期
     * @param cdate 系统平台日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }

    /**
     * 取得 分行号
     * @return 分行号
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     * 设置 分行号
     * @param branchid 分行号
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid == null ? null : branchid.trim();
    }

    /**
     * 取得 分行名称
     * @return 分行名称
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * 设置 分行名称
     * @param bankname 分行名称
     */
    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    /**
     * 取得 449188接口.分行认购客户数、449191接口.分行认购客户数、449194接口.分行认购客户数、449186接口.分行认购客户数
     * @return 449188接口.分行认购客户数、449191接口.分行认购客户数、449194接口.分行认购客户数、449186接口.分行认购客户数
     */
    public Short getBranchbuynum() {
        return branchbuynum;
    }

    /**
     * 设置 449188接口.分行认购客户数、449191接口.分行认购客户数、449194接口.分行认购客户数、449186接口.分行认购客户数
     * @param branchbuynum 449188接口.分行认购客户数、449191接口.分行认购客户数、449194接口.分行认购客户数、449186接口.分行认购客户数
     */
    public void setBranchbuynum(Short branchbuynum) {
        this.branchbuynum = branchbuynum;
    }

    /**
     * 取得 449188接口.分行持有金额、449191接口.分行持有金额、449194接口.分行持有金额、449183接口.本金、449283接口.本金
     * @return 449188接口.分行持有金额、449191接口.分行持有金额、449194接口.分行持有金额、449183接口.本金、449283接口.本金
     */
    public BigDecimal getBranchaccountamount() {
        return branchaccountamount;
    }

    /**
     * 设置 449188接口.分行持有金额、449191接口.分行持有金额、449194接口.分行持有金额、449183接口.本金、449283接口.本金
     * @param branchaccountamount 449188接口.分行持有金额、449191接口.分行持有金额、449194接口.分行持有金额、449183接口.本金、449283接口.本金
     */
    public void setBranchaccountamount(BigDecimal branchaccountamount) {
        this.branchaccountamount = branchaccountamount;
    }

    /**
     * 取得 449181接口.分行募集金额、449183接口.募集、449283接口.募集、449186接口.分行募集份额
     * @return 449181接口.分行募集金额、449183接口.募集、449283接口.募集、449186接口.分行募集份额
     */
    public BigDecimal getBranchbuyamount() {
        return branchbuyamount;
    }

    /**
     * 设置 449181接口.分行募集金额、449183接口.募集、449283接口.募集、449186接口.分行募集份额
     * @param branchbuyamount 449181接口.分行募集金额、449183接口.募集、449283接口.募集、449186接口.分行募集份额
     */
    public void setBranchbuyamount(BigDecimal branchbuyamount) {
        this.branchbuyamount = branchbuyamount;
    }

    /**
     * 取得 449181接口.分行总赎回金额、449183接口.赎回、449283接口.赎回、449186接口.分行总赎回份额
     * @return 449181接口.分行总赎回金额、449183接口.赎回、449283接口.赎回、449186接口.分行总赎回份额
     */
    public BigDecimal getBranchdrawamount() {
        return branchdrawamount;
    }

    /**
     * 设置 449181接口.分行总赎回金额、449183接口.赎回、449283接口.赎回、449186接口.分行总赎回份额
     * @param branchdrawamount 449181接口.分行总赎回金额、449183接口.赎回、449283接口.赎回、449186接口.分行总赎回份额
     */
    public void setBranchdrawamount(BigDecimal branchdrawamount) {
        this.branchdrawamount = branchdrawamount;
    }

    /**
     * 取得 接口ID
     * @return 接口ID
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口ID
     * @param interfId 接口ID
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId == null ? null : interfId.trim();
    }
}