package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SzdSjsks extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 导入日期
     */
    private Date importDate;

    /**
     * 数据文件
     */
    private String dataFile;

    /**
     * 是否本次数据清算对象
     */
    private String isSettleTarget;

    /**
     * 托管编码
     */
    private String ksxwdm;

    /**
     * 证券账户
     */
    private String ksgddm;

    /**
     * 业务类别，‘A9’：新股发行投资者可申购额度数据
     */
    private String ksywlb;

    /**
     * 可申购额度，按市值计算的可申购额度，单位股，>0
     */
    private Long ksksge;

    /**
     * 发送日期，格式YYYYMMDD
     */
    private String ksfsrq;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 导入日期
     * @return 导入日期
     */
    public Date getImportDate() {
        return importDate;
    }

    /**
     * 设置 导入日期
     * @param importDate 导入日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * 取得 数据文件
     * @return 数据文件
     */
    public String getDataFile() {
        return dataFile;
    }

    /**
     * 设置 数据文件
     * @param dataFile 数据文件
     */
    public void setDataFile(String dataFile) {
        this.dataFile = dataFile == null ? null : dataFile.trim();
    }

    /**
     * 取得 是否本次数据清算对象
     * @return 是否本次数据清算对象
     */
    public String getIsSettleTarget() {
        return isSettleTarget;
    }

    /**
     * 设置 是否本次数据清算对象
     * @param isSettleTarget 是否本次数据清算对象
     */
    public void setIsSettleTarget(String isSettleTarget) {
        this.isSettleTarget = isSettleTarget == null ? null : isSettleTarget.trim();
    }

    /**
     * 取得 托管编码
     * @return 托管编码
     */
    public String getKsxwdm() {
        return ksxwdm;
    }

    /**
     * 设置 托管编码
     * @param ksxwdm 托管编码
     */
    public void setKsxwdm(String ksxwdm) {
        this.ksxwdm = ksxwdm == null ? null : ksxwdm.trim();
    }

    /**
     * 取得 证券账户
     * @return 证券账户
     */
    public String getKsgddm() {
        return ksgddm;
    }

    /**
     * 设置 证券账户
     * @param ksgddm 证券账户
     */
    public void setKsgddm(String ksgddm) {
        this.ksgddm = ksgddm == null ? null : ksgddm.trim();
    }

    /**
     * 取得 业务类别，‘A9’：新股发行投资者可申购额度数据
     * @return 业务类别，‘A9’：新股发行投资者可申购额度数据
     */
    public String getKsywlb() {
        return ksywlb;
    }

    /**
     * 设置 业务类别，‘A9’：新股发行投资者可申购额度数据
     * @param ksywlb 业务类别，‘A9’：新股发行投资者可申购额度数据
     */
    public void setKsywlb(String ksywlb) {
        this.ksywlb = ksywlb == null ? null : ksywlb.trim();
    }

    /**
     * 取得 可申购额度，按市值计算的可申购额度，单位股，>0
     * @return 可申购额度，按市值计算的可申购额度，单位股，>0
     */
    public Long getKsksge() {
        return ksksge;
    }

    /**
     * 设置 可申购额度，按市值计算的可申购额度，单位股，>0
     * @param ksksge 可申购额度，按市值计算的可申购额度，单位股，>0
     */
    public void setKsksge(Long ksksge) {
        this.ksksge = ksksge;
    }

    /**
     * 取得 发送日期，格式YYYYMMDD
     * @return 发送日期，格式YYYYMMDD
     */
    public String getKsfsrq() {
        return ksfsrq;
    }

    /**
     * 设置 发送日期，格式YYYYMMDD
     * @param ksfsrq 发送日期，格式YYYYMMDD
     */
    public void setKsfsrq(String ksfsrq) {
        this.ksfsrq = ksfsrq == null ? null : ksfsrq.trim();
    }
}