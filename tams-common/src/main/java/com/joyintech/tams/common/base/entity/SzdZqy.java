package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SzdZqy extends BaseEntity {
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
     * 每个产品在交易所有唯一的身份ID即股东代码，也称证券账户。需转换成系统的组合代码代码
     */
    private String gddm;

    /**
     * 总市值，登记日该投资者的股票总市值，单位为分
     */
    private Long zsz;

    /**
     * 主板权益，登记日该投资者主板申购额度，单位为股
     */
    private Long zbqy;

    /**
     * 科创板权益，登记日该投资者科创板申购额度，单位
     */
    private Long kcqy;

    /**
     * 指定席位号（PBU）
     */
    private String gsdm;

    /**
     * 账户组序号
     */
    private String zhxh;

    /**
     * 权益登记日期，格式YYYYMMDD
     */
    private String djrq;

    /**
     * 处理标志，柜台系统自由使用
     */
    private String clbj;

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
     * 取得 每个产品在交易所有唯一的身份ID即股东代码，也称证券账户。需转换成系统的组合代码代码
     * @return 每个产品在交易所有唯一的身份ID即股东代码，也称证券账户。需转换成系统的组合代码代码
     */
    public String getGddm() {
        return gddm;
    }

    /**
     * 设置 每个产品在交易所有唯一的身份ID即股东代码，也称证券账户。需转换成系统的组合代码代码
     * @param gddm 每个产品在交易所有唯一的身份ID即股东代码，也称证券账户。需转换成系统的组合代码代码
     */
    public void setGddm(String gddm) {
        this.gddm = gddm == null ? null : gddm.trim();
    }

    /**
     * 取得 总市值，登记日该投资者的股票总市值，单位为分
     * @return 总市值，登记日该投资者的股票总市值，单位为分
     */
    public Long getZsz() {
        return zsz;
    }

    /**
     * 设置 总市值，登记日该投资者的股票总市值，单位为分
     * @param zsz 总市值，登记日该投资者的股票总市值，单位为分
     */
    public void setZsz(Long zsz) {
        this.zsz = zsz;
    }

    /**
     * 取得 主板权益，登记日该投资者主板申购额度，单位为股
     * @return 主板权益，登记日该投资者主板申购额度，单位为股
     */
    public Long getZbqy() {
        return zbqy;
    }

    /**
     * 设置 主板权益，登记日该投资者主板申购额度，单位为股
     * @param zbqy 主板权益，登记日该投资者主板申购额度，单位为股
     */
    public void setZbqy(Long zbqy) {
        this.zbqy = zbqy;
    }

    /**
     * 取得 科创板权益，登记日该投资者科创板申购额度，单位
     * @return 科创板权益，登记日该投资者科创板申购额度，单位
     */
    public Long getKcqy() {
        return kcqy;
    }

    /**
     * 设置 科创板权益，登记日该投资者科创板申购额度，单位
     * @param kcqy 科创板权益，登记日该投资者科创板申购额度，单位
     */
    public void setKcqy(Long kcqy) {
        this.kcqy = kcqy;
    }

    /**
     * 取得 指定席位号（PBU）
     * @return 指定席位号（PBU）
     */
    public String getGsdm() {
        return gsdm;
    }

    /**
     * 设置 指定席位号（PBU）
     * @param gsdm 指定席位号（PBU）
     */
    public void setGsdm(String gsdm) {
        this.gsdm = gsdm == null ? null : gsdm.trim();
    }

    /**
     * 取得 账户组序号
     * @return 账户组序号
     */
    public String getZhxh() {
        return zhxh;
    }

    /**
     * 设置 账户组序号
     * @param zhxh 账户组序号
     */
    public void setZhxh(String zhxh) {
        this.zhxh = zhxh == null ? null : zhxh.trim();
    }

    /**
     * 取得 权益登记日期，格式YYYYMMDD
     * @return 权益登记日期，格式YYYYMMDD
     */
    public String getDjrq() {
        return djrq;
    }

    /**
     * 设置 权益登记日期，格式YYYYMMDD
     * @param djrq 权益登记日期，格式YYYYMMDD
     */
    public void setDjrq(String djrq) {
        this.djrq = djrq == null ? null : djrq.trim();
    }

    /**
     * 取得 处理标志，柜台系统自由使用
     * @return 处理标志，柜台系统自由使用
     */
    public String getClbj() {
        return clbj;
    }

    /**
     * 设置 处理标志，柜台系统自由使用
     * @param clbj 处理标志，柜台系统自由使用
     */
    public void setClbj(String clbj) {
        this.clbj = clbj == null ? null : clbj.trim();
    }
}