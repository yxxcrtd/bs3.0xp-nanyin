package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class CreditLinesSecPositionKey extends BaseEntity {
    /**
     * 授信额度代码
     */
    private String seqno;

    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 组合代码
     */
    private String ptlId;

    /**
     * 科目代码

     */
    private String subCode;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 取得 授信额度代码
     * @return 授信额度代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 授信额度代码
     * @param seqno 授信额度代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 组合代码
     * @param ptlId 组合代码
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId == null ? null : ptlId.trim();
    }

    /**
     * 取得 科目代码

     * @return 科目代码

     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * 设置 科目代码

     * @param subCode 科目代码

     */
    public void setSubCode(String subCode) {
        this.subCode = subCode == null ? null : subCode.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }
}