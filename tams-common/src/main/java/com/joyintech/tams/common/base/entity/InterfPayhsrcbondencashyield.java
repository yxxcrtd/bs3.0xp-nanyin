package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfPayhsrcbondencashyield extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * 曲线日期
     */
    private String stateDate;

    /**
     * 曲线code:原始code
     */
    private String curveCode;

    /**
     * 曲线名称
     */
    private String curveName;

    /**
     * 关键年限点
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mtrty;

    /**
     * 收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yld;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 创建人
     */
    private String createuser;

    /**
     * 最后修改日期
     */
    private Date lstmntdate;

    /**
     * 最后修改人
     */
    private String lstmntuser;

    /**
     * 曲线类型；SP即期；MA远期；FS远期的即期；FM远期的到期
     */
    private String curveType;

    /**
     * N
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal n;

    /**
     * K
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal k;

    /**
     * 操作日期
     */
    private Date opdate;

    /**
     * 操作方式
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
     * 取得 曲线日期
     * @return 曲线日期
     */
    public String getStateDate() {
        return stateDate;
    }

    /**
     * 设置 曲线日期
     * @param stateDate 曲线日期
     */
    public void setStateDate(String stateDate) {
        this.stateDate = stateDate == null ? null : stateDate.trim();
    }

    /**
     * 取得 曲线code:原始code
     * @return 曲线code:原始code
     */
    public String getCurveCode() {
        return curveCode;
    }

    /**
     * 设置 曲线code:原始code
     * @param curveCode 曲线code:原始code
     */
    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode == null ? null : curveCode.trim();
    }

    /**
     * 取得 曲线名称
     * @return 曲线名称
     */
    public String getCurveName() {
        return curveName;
    }

    /**
     * 设置 曲线名称
     * @param curveName 曲线名称
     */
    public void setCurveName(String curveName) {
        this.curveName = curveName == null ? null : curveName.trim();
    }

    /**
     * 取得 关键年限点
     * @return 关键年限点
     */
    public BigDecimal getMtrty() {
        return mtrty;
    }

    /**
     * 设置 关键年限点
     * @param mtrty 关键年限点
     */
    public void setMtrty(BigDecimal mtrty) {
        this.mtrty = mtrty;
    }

    /**
     * 取得 收益率
     * @return 收益率
     */
    public BigDecimal getYld() {
        return yld;
    }

    /**
     * 设置 收益率
     * @param yld 收益率
     */
    public void setYld(BigDecimal yld) {
        this.yld = yld;
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
     * 取得 创建人
     * @return 创建人
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * 设置 创建人
     * @param createuser 创建人
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    /**
     * 取得 最后修改日期
     * @return 最后修改日期
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 最后修改日期
     * @param lstmntdate 最后修改日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 最后修改人
     * @return 最后修改人
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 最后修改人
     * @param lstmntuser 最后修改人
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
    }

    /**
     * 取得 曲线类型；SP即期；MA远期；FS远期的即期；FM远期的到期
     * @return 曲线类型；SP即期；MA远期；FS远期的即期；FM远期的到期
     */
    public String getCurveType() {
        return curveType;
    }

    /**
     * 设置 曲线类型；SP即期；MA远期；FS远期的即期；FM远期的到期
     * @param curveType 曲线类型；SP即期；MA远期；FS远期的即期；FM远期的到期
     */
    public void setCurveType(String curveType) {
        this.curveType = curveType == null ? null : curveType.trim();
    }

    /**
     * 取得 N
     * @return N
     */
    public BigDecimal getN() {
        return n;
    }

    /**
     * 设置 N
     * @param n N
     */
    public void setN(BigDecimal n) {
        this.n = n;
    }

    /**
     * 取得 K
     * @return K
     */
    public BigDecimal getK() {
        return k;
    }

    /**
     * 设置 K
     * @param k K
     */
    public void setK(BigDecimal k) {
        this.k = k;
    }

    /**
     * 取得 操作日期
     * @return 操作日期
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 操作日期
     * @param opdate 操作日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 操作方式
     * @return 操作方式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 操作方式
     * @param opmode 操作方式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}