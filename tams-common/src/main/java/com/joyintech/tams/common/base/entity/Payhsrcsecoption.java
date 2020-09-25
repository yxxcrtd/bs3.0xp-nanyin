package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class Payhsrcsecoption extends BaseEntity {
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
    private Date optionstartday;

    /**
     * 行权申请结束日
     */
    private Date optionendday;

    /**
     * 行权日
     */
    private Date optionpayday;

    /**
     * 行权价(净价)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optionprice;

    /**
     * 行权类型，CALL赎回,PUT回售,对应参数表的OPTIONTYPE
     */
    private String optiontype;

    /**
     * 更新时间
     */
    private Date lstmntdate;

    /**
     * 更新人
     */
    private String lstmntuser;

    /**
     * 有效标识 E为有效，D为删除
     */
    private String effectflag;

    /**
     * null
     */
    private Date opdate;

    /**
     * null
     */
    private String opmode;

    /**
     * 取得 对象ID
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 债券编码
     * @return 债券编码
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券编码
     * @param secid 债券编码
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 付息周期编号
     * @return 付息周期编号
     */
    public Integer getOptionnum() {
        return optionnum;
    }

    /**
     * 设置 付息周期编号
     * @param optionnum 付息周期编号
     */
    public void setOptionnum(Integer optionnum) {
        this.optionnum = optionnum;
    }

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
     * 取得 行权日
     * @return 行权日
     */
    public Date getOptionpayday() {
        return optionpayday;
    }

    /**
     * 设置 行权日
     * @param optionpayday 行权日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOptionpayday(Date optionpayday) {
        this.optionpayday = optionpayday;
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
     * 取得 行权类型，CALL赎回,PUT回售,对应参数表的OPTIONTYPE
     * @return 行权类型，CALL赎回,PUT回售,对应参数表的OPTIONTYPE
     */
    public String getOptiontype() {
        return optiontype;
    }

    /**
     * 设置 行权类型，CALL赎回,PUT回售,对应参数表的OPTIONTYPE
     * @param optiontype 行权类型，CALL赎回,PUT回售,对应参数表的OPTIONTYPE
     */
    public void setOptiontype(String optiontype) {
        this.optiontype = optiontype == null ? null : optiontype.trim();
    }

    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 更新时间
     * @param lstmntdate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 更新人
     * @return 更新人
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 更新人
     * @param lstmntuser 更新人
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
    }

    /**
     * 取得 有效标识 E为有效，D为删除
     * @return 有效标识 E为有效，D为删除
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 有效标识 E为有效，D为删除
     * @param effectflag 有效标识 E为有效，D为删除
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 null
     * @param opdate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 null
     * @param opmode null
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}