package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class SzhkTz extends BaseEntity {
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
     * 市场代码
     */
    private String scdm;

    /**
     * 通知类别
     */
    private String tzlb;

    /**
     * 通知发送日期
     */
    private String fsrq;

    /**
     * 证券代码
     */
    private String zqdm;

    /**
     * 股份性质
     */
    private String gfxz;

    /**
     * 上市状态
     */
    private String sszt;

    /**
     * 权益类别
     */
    private String qylb;

    /**
     * 权益编号
     */
    private String qybh;

    /**
     * 账户1
     */
    private String zh1;

    /**
     * 账户2
     */
    private String zh2;

    /**
     * 日期1
     */
    private String rq1;

    /**
     * 日期2
     */
    private String rq2;

    /**
     * 日期3
     */
    private String rq3;

    /**
     * 金额1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal je1;

    /**
     * 金额2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal je2;

    /**
     * 金额3
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal je3;

    /**
     * 价格1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jg1;

    /**
     * 价格2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jg2;

    /**
     * 币种
     */
    private String bz;

    /**
     * 汇率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hl;

    /**
     * 比率1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bl1;

    /**
     * 比率2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bl2;

    /**
     * 数量1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sl1;

    /**
     * 数量2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sl2;

    /**
     * 类型1
     */
    private String lx1;

    /**
     * 类型2
     */
    private String lx2;

    /**
     * 辅助代码1
     */
    private String fzdm1;

    /**
     * 辅助代码2
     */
    private String fzdm2;

    /**
     * 附加说明1
     */
    private String fjsm1;

    /**
     * 附加说明2
     */
    private String fjsm2;

    /**
     * 状态
     */
    private String zt;

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
     * 取得 市场代码
     * @return 市场代码
     */
    public String getScdm() {
        return scdm;
    }

    /**
     * 设置 市场代码
     * @param scdm 市场代码
     */
    public void setScdm(String scdm) {
        this.scdm = scdm == null ? null : scdm.trim();
    }

    /**
     * 取得 通知类别
     * @return 通知类别
     */
    public String getTzlb() {
        return tzlb;
    }

    /**
     * 设置 通知类别
     * @param tzlb 通知类别
     */
    public void setTzlb(String tzlb) {
        this.tzlb = tzlb == null ? null : tzlb.trim();
    }

    /**
     * 取得 通知发送日期
     * @return 通知发送日期
     */
    public String getFsrq() {
        return fsrq;
    }

    /**
     * 设置 通知发送日期
     * @param fsrq 通知发送日期
     */
    public void setFsrq(String fsrq) {
        this.fsrq = fsrq == null ? null : fsrq.trim();
    }

    /**
     * 取得 证券代码
     * @return 证券代码
     */
    public String getZqdm() {
        return zqdm;
    }

    /**
     * 设置 证券代码
     * @param zqdm 证券代码
     */
    public void setZqdm(String zqdm) {
        this.zqdm = zqdm == null ? null : zqdm.trim();
    }

    /**
     * 取得 股份性质
     * @return 股份性质
     */
    public String getGfxz() {
        return gfxz;
    }

    /**
     * 设置 股份性质
     * @param gfxz 股份性质
     */
    public void setGfxz(String gfxz) {
        this.gfxz = gfxz == null ? null : gfxz.trim();
    }

    /**
     * 取得 上市状态
     * @return 上市状态
     */
    public String getSszt() {
        return sszt;
    }

    /**
     * 设置 上市状态
     * @param sszt 上市状态
     */
    public void setSszt(String sszt) {
        this.sszt = sszt == null ? null : sszt.trim();
    }

    /**
     * 取得 权益类别
     * @return 权益类别
     */
    public String getQylb() {
        return qylb;
    }

    /**
     * 设置 权益类别
     * @param qylb 权益类别
     */
    public void setQylb(String qylb) {
        this.qylb = qylb == null ? null : qylb.trim();
    }

    /**
     * 取得 权益编号
     * @return 权益编号
     */
    public String getQybh() {
        return qybh;
    }

    /**
     * 设置 权益编号
     * @param qybh 权益编号
     */
    public void setQybh(String qybh) {
        this.qybh = qybh == null ? null : qybh.trim();
    }

    /**
     * 取得 账户1
     * @return 账户1
     */
    public String getZh1() {
        return zh1;
    }

    /**
     * 设置 账户1
     * @param zh1 账户1
     */
    public void setZh1(String zh1) {
        this.zh1 = zh1 == null ? null : zh1.trim();
    }

    /**
     * 取得 账户2
     * @return 账户2
     */
    public String getZh2() {
        return zh2;
    }

    /**
     * 设置 账户2
     * @param zh2 账户2
     */
    public void setZh2(String zh2) {
        this.zh2 = zh2 == null ? null : zh2.trim();
    }

    /**
     * 取得 日期1
     * @return 日期1
     */
    public String getRq1() {
        return rq1;
    }

    /**
     * 设置 日期1
     * @param rq1 日期1
     */
    public void setRq1(String rq1) {
        this.rq1 = rq1 == null ? null : rq1.trim();
    }

    /**
     * 取得 日期2
     * @return 日期2
     */
    public String getRq2() {
        return rq2;
    }

    /**
     * 设置 日期2
     * @param rq2 日期2
     */
    public void setRq2(String rq2) {
        this.rq2 = rq2 == null ? null : rq2.trim();
    }

    /**
     * 取得 日期3
     * @return 日期3
     */
    public String getRq3() {
        return rq3;
    }

    /**
     * 设置 日期3
     * @param rq3 日期3
     */
    public void setRq3(String rq3) {
        this.rq3 = rq3 == null ? null : rq3.trim();
    }

    /**
     * 取得 金额1
     * @return 金额1
     */
    public BigDecimal getJe1() {
        return je1;
    }

    /**
     * 设置 金额1
     * @param je1 金额1
     */
    public void setJe1(BigDecimal je1) {
        this.je1 = je1;
    }

    /**
     * 取得 金额2
     * @return 金额2
     */
    public BigDecimal getJe2() {
        return je2;
    }

    /**
     * 设置 金额2
     * @param je2 金额2
     */
    public void setJe2(BigDecimal je2) {
        this.je2 = je2;
    }

    /**
     * 取得 金额3
     * @return 金额3
     */
    public BigDecimal getJe3() {
        return je3;
    }

    /**
     * 设置 金额3
     * @param je3 金额3
     */
    public void setJe3(BigDecimal je3) {
        this.je3 = je3;
    }

    /**
     * 取得 价格1
     * @return 价格1
     */
    public BigDecimal getJg1() {
        return jg1;
    }

    /**
     * 设置 价格1
     * @param jg1 价格1
     */
    public void setJg1(BigDecimal jg1) {
        this.jg1 = jg1;
    }

    /**
     * 取得 价格2
     * @return 价格2
     */
    public BigDecimal getJg2() {
        return jg2;
    }

    /**
     * 设置 价格2
     * @param jg2 价格2
     */
    public void setJg2(BigDecimal jg2) {
        this.jg2 = jg2;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getBz() {
        return bz;
    }

    /**
     * 设置 币种
     * @param bz 币种
     */
    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    /**
     * 取得 汇率
     * @return 汇率
     */
    public BigDecimal getHl() {
        return hl;
    }

    /**
     * 设置 汇率
     * @param hl 汇率
     */
    public void setHl(BigDecimal hl) {
        this.hl = hl;
    }

    /**
     * 取得 比率1
     * @return 比率1
     */
    public BigDecimal getBl1() {
        return bl1;
    }

    /**
     * 设置 比率1
     * @param bl1 比率1
     */
    public void setBl1(BigDecimal bl1) {
        this.bl1 = bl1;
    }

    /**
     * 取得 比率2
     * @return 比率2
     */
    public BigDecimal getBl2() {
        return bl2;
    }

    /**
     * 设置 比率2
     * @param bl2 比率2
     */
    public void setBl2(BigDecimal bl2) {
        this.bl2 = bl2;
    }

    /**
     * 取得 数量1
     * @return 数量1
     */
    public BigDecimal getSl1() {
        return sl1;
    }

    /**
     * 设置 数量1
     * @param sl1 数量1
     */
    public void setSl1(BigDecimal sl1) {
        this.sl1 = sl1;
    }

    /**
     * 取得 数量2
     * @return 数量2
     */
    public BigDecimal getSl2() {
        return sl2;
    }

    /**
     * 设置 数量2
     * @param sl2 数量2
     */
    public void setSl2(BigDecimal sl2) {
        this.sl2 = sl2;
    }

    /**
     * 取得 类型1
     * @return 类型1
     */
    public String getLx1() {
        return lx1;
    }

    /**
     * 设置 类型1
     * @param lx1 类型1
     */
    public void setLx1(String lx1) {
        this.lx1 = lx1 == null ? null : lx1.trim();
    }

    /**
     * 取得 类型2
     * @return 类型2
     */
    public String getLx2() {
        return lx2;
    }

    /**
     * 设置 类型2
     * @param lx2 类型2
     */
    public void setLx2(String lx2) {
        this.lx2 = lx2 == null ? null : lx2.trim();
    }

    /**
     * 取得 辅助代码1
     * @return 辅助代码1
     */
    public String getFzdm1() {
        return fzdm1;
    }

    /**
     * 设置 辅助代码1
     * @param fzdm1 辅助代码1
     */
    public void setFzdm1(String fzdm1) {
        this.fzdm1 = fzdm1 == null ? null : fzdm1.trim();
    }

    /**
     * 取得 辅助代码2
     * @return 辅助代码2
     */
    public String getFzdm2() {
        return fzdm2;
    }

    /**
     * 设置 辅助代码2
     * @param fzdm2 辅助代码2
     */
    public void setFzdm2(String fzdm2) {
        this.fzdm2 = fzdm2 == null ? null : fzdm2.trim();
    }

    /**
     * 取得 附加说明1
     * @return 附加说明1
     */
    public String getFjsm1() {
        return fjsm1;
    }

    /**
     * 设置 附加说明1
     * @param fjsm1 附加说明1
     */
    public void setFjsm1(String fjsm1) {
        this.fjsm1 = fjsm1 == null ? null : fjsm1.trim();
    }

    /**
     * 取得 附加说明2
     * @return 附加说明2
     */
    public String getFjsm2() {
        return fjsm2;
    }

    /**
     * 设置 附加说明2
     * @param fjsm2 附加说明2
     */
    public void setFjsm2(String fjsm2) {
        this.fjsm2 = fjsm2 == null ? null : fjsm2.trim();
    }

    /**
     * 取得 状态
     * @return 状态
     */
    public String getZt() {
        return zt;
    }

    /**
     * 设置 状态
     * @param zt 状态
     */
    public void setZt(String zt) {
        this.zt = zt == null ? null : zt.trim();
    }
}