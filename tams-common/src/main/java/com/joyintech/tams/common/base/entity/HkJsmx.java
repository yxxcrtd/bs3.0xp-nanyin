package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class HkJsmx extends BaseEntity {
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
     * 记录格式
     */
    private String jlgs;

    /**
     * 市场代码
     */
    private String scdm;

    /**
     * 记录类型
     */
    private String jllx;

    /**
     * 交易方式
     */
    private String jyfs;

    /**
     * 交收方式
     */
    private String jsfs;

    /**
     * 业务类型
     */
    private String ywlx;

    /**
     * 清算标志
     */
    private String qsbz;

    /**
     * 变动类型
     */
    private String bdlx;

    /**
     * 清算流水号
     */
    private String qslsh;

    /**
     * 成交编号
     */
    private String cjbh;

    /**
     * 申请编号
     */
    private String sqbh;

    /**
     * 委托编号
     */
    private String wtbh;

    /**
     * 交易日期
     */
    private String jyrq;

    /**
     * 清算日期
     */
    private String qsrq;

    /**
     * 交收日期
     */
    private String jsrq;

    /**
     * 其他日期
     */
    private String qtrq;

    /**
     * 成交时间
     */
    private String cjsj;

    /**
     * 业务单元1
     */
    private String xwh1;

    /**
     * 业务单元2
     */
    private String xwh2;

    /**
     * 业务单元1所属结算参与机构的清算编号
     */
    private String xwhy;

    /**
     * 结算参与机构的清算编号
     */
    private String jshy;

    /**
     * 指定交易的清算编号
     */
    private String tghy;

    /**
     * 证券账号
     */
    private String zqzh;

    /**
     * 证券代码1
     */
    private String zqdm1;

    /**
     * 证券代码2
     */
    private String zqdm2;

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
     * 买卖标志
     */
    private String mmbz;

    /**
     * 交收数量
     */
    private String sl;

    /**
     * 成交数量
     */
    private String cjsl;

    /**
     * 资金账号
     */
    private String zjzh;

    /**
     * 币种
     */
    private String bz;

    /**
     * 价格1
     */
    private String jg1;

    /**
     * 价格2
     */
    private String jg2;

    /**
     * 清算金额
     */
    private String wbje;

    /**
     * 印花税
     */
    private String yhs;

    /**
     * 交易征费
     */
    private String jyzf;

    /**
     * 交易费
     */
    private String jyf;

    /**
     * 交易系统使用费
     */
    private String syf;

    /**
     * 股份交收费
     */
    private String jsf;

    /**
     * 其他金额
     */
    private String qtje;

    /**
     * 应收付金额
     */
    private String wbysf;

    /**
     * 汇率
     */
    private String wbhl;

    /**
     * 人民币应收付
     */
    private String ysfje;

    /**
     * 结果代码
     */
    private String jgdm;

    /**
     * 附加说明
     */
    private String fjsm;

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
     * 取得 记录格式
     * @return 记录格式
     */
    public String getJlgs() {
        return jlgs;
    }

    /**
     * 设置 记录格式
     * @param jlgs 记录格式
     */
    public void setJlgs(String jlgs) {
        this.jlgs = jlgs == null ? null : jlgs.trim();
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
     * 取得 记录类型
     * @return 记录类型
     */
    public String getJllx() {
        return jllx;
    }

    /**
     * 设置 记录类型
     * @param jllx 记录类型
     */
    public void setJllx(String jllx) {
        this.jllx = jllx == null ? null : jllx.trim();
    }

    /**
     * 取得 交易方式
     * @return 交易方式
     */
    public String getJyfs() {
        return jyfs;
    }

    /**
     * 设置 交易方式
     * @param jyfs 交易方式
     */
    public void setJyfs(String jyfs) {
        this.jyfs = jyfs == null ? null : jyfs.trim();
    }

    /**
     * 取得 交收方式
     * @return 交收方式
     */
    public String getJsfs() {
        return jsfs;
    }

    /**
     * 设置 交收方式
     * @param jsfs 交收方式
     */
    public void setJsfs(String jsfs) {
        this.jsfs = jsfs == null ? null : jsfs.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getYwlx() {
        return ywlx;
    }

    /**
     * 设置 业务类型
     * @param ywlx 业务类型
     */
    public void setYwlx(String ywlx) {
        this.ywlx = ywlx == null ? null : ywlx.trim();
    }

    /**
     * 取得 清算标志
     * @return 清算标志
     */
    public String getQsbz() {
        return qsbz;
    }

    /**
     * 设置 清算标志
     * @param qsbz 清算标志
     */
    public void setQsbz(String qsbz) {
        this.qsbz = qsbz == null ? null : qsbz.trim();
    }

    /**
     * 取得 变动类型
     * @return 变动类型
     */
    public String getBdlx() {
        return bdlx;
    }

    /**
     * 设置 变动类型
     * @param bdlx 变动类型
     */
    public void setBdlx(String bdlx) {
        this.bdlx = bdlx == null ? null : bdlx.trim();
    }

    /**
     * 取得 清算流水号
     * @return 清算流水号
     */
    public String getQslsh() {
        return qslsh;
    }

    /**
     * 设置 清算流水号
     * @param qslsh 清算流水号
     */
    public void setQslsh(String qslsh) {
        this.qslsh = qslsh == null ? null : qslsh.trim();
    }

    /**
     * 取得 成交编号
     * @return 成交编号
     */
    public String getCjbh() {
        return cjbh;
    }

    /**
     * 设置 成交编号
     * @param cjbh 成交编号
     */
    public void setCjbh(String cjbh) {
        this.cjbh = cjbh == null ? null : cjbh.trim();
    }

    /**
     * 取得 申请编号
     * @return 申请编号
     */
    public String getSqbh() {
        return sqbh;
    }

    /**
     * 设置 申请编号
     * @param sqbh 申请编号
     */
    public void setSqbh(String sqbh) {
        this.sqbh = sqbh == null ? null : sqbh.trim();
    }

    /**
     * 取得 委托编号
     * @return 委托编号
     */
    public String getWtbh() {
        return wtbh;
    }

    /**
     * 设置 委托编号
     * @param wtbh 委托编号
     */
    public void setWtbh(String wtbh) {
        this.wtbh = wtbh == null ? null : wtbh.trim();
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public String getJyrq() {
        return jyrq;
    }

    /**
     * 设置 交易日期
     * @param jyrq 交易日期
     */
    public void setJyrq(String jyrq) {
        this.jyrq = jyrq == null ? null : jyrq.trim();
    }

    /**
     * 取得 清算日期
     * @return 清算日期
     */
    public String getQsrq() {
        return qsrq;
    }

    /**
     * 设置 清算日期
     * @param qsrq 清算日期
     */
    public void setQsrq(String qsrq) {
        this.qsrq = qsrq == null ? null : qsrq.trim();
    }

    /**
     * 取得 交收日期
     * @return 交收日期
     */
    public String getJsrq() {
        return jsrq;
    }

    /**
     * 设置 交收日期
     * @param jsrq 交收日期
     */
    public void setJsrq(String jsrq) {
        this.jsrq = jsrq == null ? null : jsrq.trim();
    }

    /**
     * 取得 其他日期
     * @return 其他日期
     */
    public String getQtrq() {
        return qtrq;
    }

    /**
     * 设置 其他日期
     * @param qtrq 其他日期
     */
    public void setQtrq(String qtrq) {
        this.qtrq = qtrq == null ? null : qtrq.trim();
    }

    /**
     * 取得 成交时间
     * @return 成交时间
     */
    public String getCjsj() {
        return cjsj;
    }

    /**
     * 设置 成交时间
     * @param cjsj 成交时间
     */
    public void setCjsj(String cjsj) {
        this.cjsj = cjsj == null ? null : cjsj.trim();
    }

    /**
     * 取得 业务单元1
     * @return 业务单元1
     */
    public String getXwh1() {
        return xwh1;
    }

    /**
     * 设置 业务单元1
     * @param xwh1 业务单元1
     */
    public void setXwh1(String xwh1) {
        this.xwh1 = xwh1 == null ? null : xwh1.trim();
    }

    /**
     * 取得 业务单元2
     * @return 业务单元2
     */
    public String getXwh2() {
        return xwh2;
    }

    /**
     * 设置 业务单元2
     * @param xwh2 业务单元2
     */
    public void setXwh2(String xwh2) {
        this.xwh2 = xwh2 == null ? null : xwh2.trim();
    }

    /**
     * 取得 业务单元1所属结算参与机构的清算编号
     * @return 业务单元1所属结算参与机构的清算编号
     */
    public String getXwhy() {
        return xwhy;
    }

    /**
     * 设置 业务单元1所属结算参与机构的清算编号
     * @param xwhy 业务单元1所属结算参与机构的清算编号
     */
    public void setXwhy(String xwhy) {
        this.xwhy = xwhy == null ? null : xwhy.trim();
    }

    /**
     * 取得 结算参与机构的清算编号
     * @return 结算参与机构的清算编号
     */
    public String getJshy() {
        return jshy;
    }

    /**
     * 设置 结算参与机构的清算编号
     * @param jshy 结算参与机构的清算编号
     */
    public void setJshy(String jshy) {
        this.jshy = jshy == null ? null : jshy.trim();
    }

    /**
     * 取得 指定交易的清算编号
     * @return 指定交易的清算编号
     */
    public String getTghy() {
        return tghy;
    }

    /**
     * 设置 指定交易的清算编号
     * @param tghy 指定交易的清算编号
     */
    public void setTghy(String tghy) {
        this.tghy = tghy == null ? null : tghy.trim();
    }

    /**
     * 取得 证券账号
     * @return 证券账号
     */
    public String getZqzh() {
        return zqzh;
    }

    /**
     * 设置 证券账号
     * @param zqzh 证券账号
     */
    public void setZqzh(String zqzh) {
        this.zqzh = zqzh == null ? null : zqzh.trim();
    }

    /**
     * 取得 证券代码1
     * @return 证券代码1
     */
    public String getZqdm1() {
        return zqdm1;
    }

    /**
     * 设置 证券代码1
     * @param zqdm1 证券代码1
     */
    public void setZqdm1(String zqdm1) {
        this.zqdm1 = zqdm1 == null ? null : zqdm1.trim();
    }

    /**
     * 取得 证券代码2
     * @return 证券代码2
     */
    public String getZqdm2() {
        return zqdm2;
    }

    /**
     * 设置 证券代码2
     * @param zqdm2 证券代码2
     */
    public void setZqdm2(String zqdm2) {
        this.zqdm2 = zqdm2 == null ? null : zqdm2.trim();
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
     * 取得 买卖标志
     * @return 买卖标志
     */
    public String getMmbz() {
        return mmbz;
    }

    /**
     * 设置 买卖标志
     * @param mmbz 买卖标志
     */
    public void setMmbz(String mmbz) {
        this.mmbz = mmbz == null ? null : mmbz.trim();
    }

    /**
     * 取得 交收数量
     * @return 交收数量
     */
    public String getSl() {
        return sl;
    }

    /**
     * 设置 交收数量
     * @param sl 交收数量
     */
    public void setSl(String sl) {
        this.sl = sl == null ? null : sl.trim();
    }

    /**
     * 取得 成交数量
     * @return 成交数量
     */
    public String getCjsl() {
        return cjsl;
    }

    /**
     * 设置 成交数量
     * @param cjsl 成交数量
     */
    public void setCjsl(String cjsl) {
        this.cjsl = cjsl == null ? null : cjsl.trim();
    }

    /**
     * 取得 资金账号
     * @return 资金账号
     */
    public String getZjzh() {
        return zjzh;
    }

    /**
     * 设置 资金账号
     * @param zjzh 资金账号
     */
    public void setZjzh(String zjzh) {
        this.zjzh = zjzh == null ? null : zjzh.trim();
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
     * 取得 清算金额
     * @return 清算金额
     */
    public String getWbje() {
        return wbje;
    }

    /**
     * 设置 清算金额
     * @param wbje 清算金额
     */
    public void setWbje(String wbje) {
        this.wbje = wbje == null ? null : wbje.trim();
    }

    /**
     * 取得 印花税
     * @return 印花税
     */
    public String getYhs() {
        return yhs;
    }

    /**
     * 设置 印花税
     * @param yhs 印花税
     */
    public void setYhs(String yhs) {
        this.yhs = yhs == null ? null : yhs.trim();
    }

    /**
     * 取得 交易征费
     * @return 交易征费
     */
    public String getJyzf() {
        return jyzf;
    }

    /**
     * 设置 交易征费
     * @param jyzf 交易征费
     */
    public void setJyzf(String jyzf) {
        this.jyzf = jyzf == null ? null : jyzf.trim();
    }

    /**
     * 取得 交易费
     * @return 交易费
     */
    public String getJyf() {
        return jyf;
    }

    /**
     * 设置 交易费
     * @param jyf 交易费
     */
    public void setJyf(String jyf) {
        this.jyf = jyf == null ? null : jyf.trim();
    }

    /**
     * 取得 交易系统使用费
     * @return 交易系统使用费
     */
    public String getSyf() {
        return syf;
    }

    /**
     * 设置 交易系统使用费
     * @param syf 交易系统使用费
     */
    public void setSyf(String syf) {
        this.syf = syf == null ? null : syf.trim();
    }

    /**
     * 取得 股份交收费
     * @return 股份交收费
     */
    public String getJsf() {
        return jsf;
    }

    /**
     * 设置 股份交收费
     * @param jsf 股份交收费
     */
    public void setJsf(String jsf) {
        this.jsf = jsf == null ? null : jsf.trim();
    }

    /**
     * 取得 其他金额
     * @return 其他金额
     */
    public String getQtje() {
        return qtje;
    }

    /**
     * 设置 其他金额
     * @param qtje 其他金额
     */
    public void setQtje(String qtje) {
        this.qtje = qtje == null ? null : qtje.trim();
    }

    /**
     * 取得 应收付金额
     * @return 应收付金额
     */
    public String getWbysf() {
        return wbysf;
    }

    /**
     * 设置 应收付金额
     * @param wbysf 应收付金额
     */
    public void setWbysf(String wbysf) {
        this.wbysf = wbysf == null ? null : wbysf.trim();
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
     * 取得 人民币应收付
     * @return 人民币应收付
     */
    public String getYsfje() {
        return ysfje;
    }

    /**
     * 设置 人民币应收付
     * @param ysfje 人民币应收付
     */
    public void setYsfje(String ysfje) {
        this.ysfje = ysfje == null ? null : ysfje.trim();
    }

    /**
     * 取得 结果代码
     * @return 结果代码
     */
    public String getJgdm() {
        return jgdm;
    }

    /**
     * 设置 结果代码
     * @param jgdm 结果代码
     */
    public void setJgdm(String jgdm) {
        this.jgdm = jgdm == null ? null : jgdm.trim();
    }

    /**
     * 取得 附加说明
     * @return 附加说明
     */
    public String getFjsm() {
        return fjsm;
    }

    /**
     * 设置 附加说明
     * @param fjsm 附加说明
     */
    public void setFjsm(String fjsm) {
        this.fjsm = fjsm == null ? null : fjsm.trim();
    }
}