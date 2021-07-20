package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class HkTz extends BaseEntity {
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
     * 通知日期
     */
    private String tzrq;

    /**
     * 结算参与机构的清算编号
     */
    private String qsbh;

    /**
     * 证券代码
     */
    private String zqdm;

    /**
     * 证券类别
     */
    private String zqlb;

    /**
     * 流通类型
     */
    private String ltlx;

    /**
     * 权益类别
     */
    private String qylb;

    /**
     * 挂牌年份
     */
    private String gpnf;

    /**
     * 权益次数
     */
    private String qycs;

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
    private String je1;

    /**
     * 金额2
     */
    private String je2;

    /**
     * 金额3
     */
    private String je3;

    /**
     * 价格1
     */
    private String jg1;

    /**
     * 价格2
     */
    private String jg2;

    /**
     * 币种
     */
    private String bz;

    /**
     * 汇率
     */
    private String wbhl;

    /**
     * 比率1
     */
    private String bl1;

    /**
     * 比率2
     */
    private String bl2;

    /**
     * 数量1
     */
    private String sl1;

    /**
     * 数量2
     */
    private String sl2;

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
     * 取得 通知日期
     * @return 通知日期
     */
    public String getTzrq() {
        return tzrq;
    }

    /**
     * 设置 通知日期
     * @param tzrq 通知日期
     */
    public void setTzrq(String tzrq) {
        this.tzrq = tzrq == null ? null : tzrq.trim();
    }

    /**
     * 取得 结算参与机构的清算编号
     * @return 结算参与机构的清算编号
     */
    public String getQsbh() {
        return qsbh;
    }

    /**
     * 设置 结算参与机构的清算编号
     * @param qsbh 结算参与机构的清算编号
     */
    public void setQsbh(String qsbh) {
        this.qsbh = qsbh == null ? null : qsbh.trim();
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
     * 取得 证券类别
     * @return 证券类别
     */
    public String getZqlb() {
        return zqlb;
    }

    /**
     * 设置 证券类别
     * @param zqlb 证券类别
     */
    public void setZqlb(String zqlb) {
        this.zqlb = zqlb == null ? null : zqlb.trim();
    }

    /**
     * 取得 流通类型
     * @return 流通类型
     */
    public String getLtlx() {
        return ltlx;
    }

    /**
     * 设置 流通类型
     * @param ltlx 流通类型
     */
    public void setLtlx(String ltlx) {
        this.ltlx = ltlx == null ? null : ltlx.trim();
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
     * 取得 挂牌年份
     * @return 挂牌年份
     */
    public String getGpnf() {
        return gpnf;
    }

    /**
     * 设置 挂牌年份
     * @param gpnf 挂牌年份
     */
    public void setGpnf(String gpnf) {
        this.gpnf = gpnf == null ? null : gpnf.trim();
    }

    /**
     * 取得 权益次数
     * @return 权益次数
     */
    public String getQycs() {
        return qycs;
    }

    /**
     * 设置 权益次数
     * @param qycs 权益次数
     */
    public void setQycs(String qycs) {
        this.qycs = qycs == null ? null : qycs.trim();
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
    public String getJe1() {
        return je1;
    }

    /**
     * 设置 金额1
     * @param je1 金额1
     */
    public void setJe1(String je1) {
        this.je1 = je1 == null ? null : je1.trim();
    }

    /**
     * 取得 金额2
     * @return 金额2
     */
    public String getJe2() {
        return je2;
    }

    /**
     * 设置 金额2
     * @param je2 金额2
     */
    public void setJe2(String je2) {
        this.je2 = je2 == null ? null : je2.trim();
    }

    /**
     * 取得 金额3
     * @return 金额3
     */
    public String getJe3() {
        return je3;
    }

    /**
     * 设置 金额3
     * @param je3 金额3
     */
    public void setJe3(String je3) {
        this.je3 = je3 == null ? null : je3.trim();
    }

    /**
     * 取得 价格1
     * @return 价格1
     */
    public String getJg1() {
        return jg1;
    }

    /**
     * 设置 价格1
     * @param jg1 价格1
     */
    public void setJg1(String jg1) {
        this.jg1 = jg1 == null ? null : jg1.trim();
    }

    /**
     * 取得 价格2
     * @return 价格2
     */
    public String getJg2() {
        return jg2;
    }

    /**
     * 设置 价格2
     * @param jg2 价格2
     */
    public void setJg2(String jg2) {
        this.jg2 = jg2 == null ? null : jg2.trim();
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
    public String getWbhl() {
        return wbhl;
    }

    /**
     * 设置 汇率
     * @param wbhl 汇率
     */
    public void setWbhl(String wbhl) {
        this.wbhl = wbhl == null ? null : wbhl.trim();
    }

    /**
     * 取得 比率1
     * @return 比率1
     */
    public String getBl1() {
        return bl1;
    }

    /**
     * 设置 比率1
     * @param bl1 比率1
     */
    public void setBl1(String bl1) {
        this.bl1 = bl1 == null ? null : bl1.trim();
    }

    /**
     * 取得 比率2
     * @return 比率2
     */
    public String getBl2() {
        return bl2;
    }

    /**
     * 设置 比率2
     * @param bl2 比率2
     */
    public void setBl2(String bl2) {
        this.bl2 = bl2 == null ? null : bl2.trim();
    }

    /**
     * 取得 数量1
     * @return 数量1
     */
    public String getSl1() {
        return sl1;
    }

    /**
     * 设置 数量1
     * @param sl1 数量1
     */
    public void setSl1(String sl1) {
        this.sl1 = sl1 == null ? null : sl1.trim();
    }

    /**
     * 取得 数量2
     * @return 数量2
     */
    public String getSl2() {
        return sl2;
    }

    /**
     * 设置 数量2
     * @param sl2 数量2
     */
    public void setSl2(String sl2) {
        this.sl2 = sl2 == null ? null : sl2.trim();
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
}