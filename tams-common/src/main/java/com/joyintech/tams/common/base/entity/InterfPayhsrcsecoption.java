package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfPayhsrcsecoption extends InterfPayhsrcsecoptionKey {
    /**
     * 行权申请起始日
     */
    private Date optionstartday;

    /**
     * 行权申请结束日
     */
    private Date optionendday;

    /**
     * 行权价(净价)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optionprice;

    /**
     * 行权类型
     */
    private String optiontype;

    /**
     * 行权条款
     */
    private String optionclause;

    /**
     * 取得 行权申请起始日
     * @return 行权申请起始日
     */
    public Date getOptionstartday() {
        return optionstartday;
    }

    /**
     * 设置 行权申请起始日
     * @param optionstartday 行权申请起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOptionstartday(Date optionstartday) {
        this.optionstartday = optionstartday;
    }

    /**
     * 取得 行权申请结束日
     * @return 行权申请结束日
     */
    public Date getOptionendday() {
        return optionendday;
    }

    /**
     * 设置 行权申请结束日
     * @param optionendday 行权申请结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOptionendday(Date optionendday) {
        this.optionendday = optionendday;
    }

    /**
     * 取得 行权价(净价)
     * @return 行权价(净价)
     */
    public BigDecimal getOptionprice() {
        return optionprice;
    }

    /**
     * 设置 行权价(净价)
     * @param optionprice 行权价(净价)
     */
    public void setOptionprice(BigDecimal optionprice) {
        this.optionprice = optionprice;
    }

    /**
     * 取得 行权类型
     * @return 行权类型
     */
    public String getOptiontype() {
        return optiontype;
    }

    /**
     * 设置 行权类型
     * @param optiontype 行权类型
     */
    public void setOptiontype(String optiontype) {
        this.optiontype = optiontype == null ? null : optiontype.trim();
    }

    /**
     * 取得 行权条款
     * @return 行权条款
     */
    public String getOptionclause() {
        return optionclause;
    }

    /**
     * 设置 行权条款
     * @param optionclause 行权条款
     */
    public void setOptionclause(String optionclause) {
        this.optionclause = optionclause == null ? null : optionclause.trim();
    }
}