package com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

public class ValPayhsrcsecoption extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 对象ID
     */
    private String objectId;

    /**
     * 债券编码
     */
    private String secid;

    /**
     * 付息周期编号
     */
    private Integer optionnum;

    /**
     * 行权申请起始日
     */
    private String optionstartday;

    /**
     * 行权申请结束日
     */
    private String optionendday;

    /**
     * 行权日
     */
    private String optionpayday;

    /**
     * 行权价(净价)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal optionprice;

    /**
     * 行权类型，CALL赎回,PUT回售
     */
    private String optiontype;

    /**
     * 更新时间
     */
    private String lstmntdate;

    /**
     * 更新人
     */
    private String lstmntuser;

    /**
     * 有效标识
     */
    private String effectflag;

    /**
     * 操作时间
     */
    private String opdate;

    /**
     * 更新模式
     */
    private String opmode;

    /**
     * 取得 对象ID
     * 
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * 
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 债券编码
     * 
     * @return 债券编码
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券编码
     * 
     * @param secid 债券编码
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 付息周期编号
     * 
     * @return 付息周期编号
     */
    public Integer getOptionnum() {
        return optionnum;
    }

    /**
     * 设置 付息周期编号
     * 
     * @param optionnum 付息周期编号
     */
    public void setOptionnum(Integer optionnum) {
        this.optionnum = optionnum;
    }

    /**
     * 取得 行权价(净价)
     * 
     * @return 行权价(净价)
     */
    public BigDecimal getOptionprice() {
        return optionprice;
    }

    /**
     * 设置 行权价(净价)
     * 
     * @param optionprice 行权价(净价)
     */
    public void setOptionprice(BigDecimal optionprice) {
        this.optionprice = optionprice;
    }

    /**
     * 取得 行权类型，CALL赎回,PUT回售
     * 
     * @return 行权类型，CALL赎回,PUT回售
     */
    public String getOptiontype() {
        return optiontype;
    }

    /**
     * 设置 行权类型，CALL赎回,PUT回售
     * 
     * @param optiontype 行权类型，CALL赎回,PUT回售
     */
    public void setOptiontype(String optiontype) {
        this.optiontype = optiontype == null ? null : optiontype.trim();
    }

    /**
     * 取得 更新人
     * 
     * @return 更新人
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 更新人
     * 
     * @param lstmntuser 更新人
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
    }

    /**
     * 取得 有效标识
     * 
     * @return 有效标识
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 有效标识
     * 
     * @param effectflag 有效标识
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 取得 更新模式
     * 
     * @return 更新模式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 更新模式
     * 
     * @param opmode 更新模式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }

    /**
     * 获取 optionstartday
     * 
     * @return optionstartday.
     */
    public String getOptionstartday() {
        return optionstartday;
    }

    /**
     * 设置 optionstartday
     * 
     * @param optionstartday optionstartday
     */
    public void setOptionstartday(String optionstartday) {
        this.optionstartday = optionstartday;
    }

    /**
     * 获取 optionendday
     * 
     * @return optionendday.
     */
    public String getOptionendday() {
        return optionendday;
    }

    /**
     * 设置 optionendday
     * 
     * @param optionendday optionendday
     */
    public void setOptionendday(String optionendday) {
        this.optionendday = optionendday;
    }

    /**
     * 获取 optionpayday
     * 
     * @return optionpayday.
     */
    public String getOptionpayday() {
        return optionpayday;
    }

    /**
     * 设置 optionpayday
     * 
     * @param optionpayday optionpayday
     */
    public void setOptionpayday(String optionpayday) {
        this.optionpayday = optionpayday;
    }

    /**
     * 获取 lstmntdate
     * 
     * @return lstmntdate.
     */
    public String getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 lstmntdate
     * 
     * @param lstmntdate lstmntdate
     */
    public void setLstmntdate(String lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 获取 opdate
     * 
     * @return opdate.
     */
    public String getOpdate() {
        return opdate;
    }

    /**
     * 设置 opdate
     * 
     * @param opdate opdate
     */
    public void setOpdate(String opdate) {
        this.opdate = opdate;
    }

}