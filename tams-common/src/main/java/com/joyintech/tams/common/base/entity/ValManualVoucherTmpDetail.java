package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ValManualVoucherTmpDetail extends BaseEntity {
    /**
     * 主键
     */
    private String uuid;

    /**
     * 模板编号
     */
    private String tmpCode;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 借贷
     */
    private String cdFlag;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oHappenAmt;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenNumber;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 主键
     * @param uuid 主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 模板编号
     * @return 模板编号
     */
    public String getTmpCode() {
        return tmpCode;
    }

    /**
     * 设置 模板编号
     * @param tmpCode 模板编号
     */
    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode == null ? null : tmpCode.trim();
    }

    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 取得 科目名称
     * @return 科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 科目名称
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    /**
     * 取得 借贷
     * @return 借贷
     */
    public String getCdFlag() {
        return cdFlag;
    }

    /**
     * 设置 借贷
     * @param cdFlag 借贷
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag == null ? null : cdFlag.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getoHappenAmt() {
        return oHappenAmt;
    }

    /**
     * 设置 金额
     * @param oHappenAmt 金额
     */
    public void setoHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 设置 数量
     * @param happenNumber 数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }
}