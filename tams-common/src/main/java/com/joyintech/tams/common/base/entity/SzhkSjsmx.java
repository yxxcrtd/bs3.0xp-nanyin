package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class SzhkSjsmx extends BaseEntity {
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
    private String ywlb;

    /**
     * 证券类别
     */
    private String zqlb;

    /**
     * 清算流水号
     */
    private String qslsh;

    /**
     * 成交编号
     */
    private String cjbh;

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
     * 交易单元
     */
    private String jydy;

    /**
     * 托管单元
     */
    private String tgdy;

    /**
     * 结算账号
     */
    private String jszh;

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
     * 成交数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cjsl;

    /**
     * 成交价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cjjg;

    /**
     * 价格2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jg2;

    /**
     * 清算数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qssl;

    /**
     * 外币清算金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal wbje;

    /**
     * 资金账号
     */
    private String zjzh;

    /**
     * 印花税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yhs;

    /**
     * 交易征费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jyzf;

    /**
     * 交易费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jyf;

    /**
     * 交易系统使用费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal syf;

    /**
     * 股份交收费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jsf;

    /**
     * 其他金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtje;

    /**
     * 应收付金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal wbysf;

    /**
     * 清算币种
     */
    private String qsbz;

    /**
     * 汇率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hl;

    /**
     * 人民币应收付
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ysfje;

    /**
     * 错误代号
     */
    private String cwdh;

    /**
     * 附加说明
     */
    private String fjsm;

    /**
     * 发送日期
     */
    private String fsrq;

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
    public String getYwlb() {
        return ywlb;
    }

    /**
     * 设置 业务类型
     * @param ywlb 业务类型
     */
    public void setYwlb(String ywlb) {
        this.ywlb = ywlb == null ? null : ywlb.trim();
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
     * 取得 交易单元
     * @return 交易单元
     */
    public String getJydy() {
        return jydy;
    }

    /**
     * 设置 交易单元
     * @param jydy 交易单元
     */
    public void setJydy(String jydy) {
        this.jydy = jydy == null ? null : jydy.trim();
    }

    /**
     * 取得 托管单元
     * @return 托管单元
     */
    public String getTgdy() {
        return tgdy;
    }

    /**
     * 设置 托管单元
     * @param tgdy 托管单元
     */
    public void setTgdy(String tgdy) {
        this.tgdy = tgdy == null ? null : tgdy.trim();
    }

    /**
     * 取得 结算账号
     * @return 结算账号
     */
    public String getJszh() {
        return jszh;
    }

    /**
     * 设置 结算账号
     * @param jszh 结算账号
     */
    public void setJszh(String jszh) {
        this.jszh = jszh == null ? null : jszh.trim();
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
     * 取得 成交数量
     * @return 成交数量
     */
    public BigDecimal getCjsl() {
        return cjsl;
    }

    /**
     * 设置 成交数量
     * @param cjsl 成交数量
     */
    public void setCjsl(BigDecimal cjsl) {
        this.cjsl = cjsl;
    }

    /**
     * 取得 成交价格
     * @return 成交价格
     */
    public BigDecimal getCjjg() {
        return cjjg;
    }

    /**
     * 设置 成交价格
     * @param cjjg 成交价格
     */
    public void setCjjg(BigDecimal cjjg) {
        this.cjjg = cjjg;
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
     * 取得 清算数量
     * @return 清算数量
     */
    public BigDecimal getQssl() {
        return qssl;
    }

    /**
     * 设置 清算数量
     * @param qssl 清算数量
     */
    public void setQssl(BigDecimal qssl) {
        this.qssl = qssl;
    }

    /**
     * 取得 外币清算金额
     * @return 外币清算金额
     */
    public BigDecimal getWbje() {
        return wbje;
    }

    /**
     * 设置 外币清算金额
     * @param wbje 外币清算金额
     */
    public void setWbje(BigDecimal wbje) {
        this.wbje = wbje;
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
     * 取得 印花税
     * @return 印花税
     */
    public BigDecimal getYhs() {
        return yhs;
    }

    /**
     * 设置 印花税
     * @param yhs 印花税
     */
    public void setYhs(BigDecimal yhs) {
        this.yhs = yhs;
    }

    /**
     * 取得 交易征费
     * @return 交易征费
     */
    public BigDecimal getJyzf() {
        return jyzf;
    }

    /**
     * 设置 交易征费
     * @param jyzf 交易征费
     */
    public void setJyzf(BigDecimal jyzf) {
        this.jyzf = jyzf;
    }

    /**
     * 取得 交易费
     * @return 交易费
     */
    public BigDecimal getJyf() {
        return jyf;
    }

    /**
     * 设置 交易费
     * @param jyf 交易费
     */
    public void setJyf(BigDecimal jyf) {
        this.jyf = jyf;
    }

    /**
     * 取得 交易系统使用费
     * @return 交易系统使用费
     */
    public BigDecimal getSyf() {
        return syf;
    }

    /**
     * 设置 交易系统使用费
     * @param syf 交易系统使用费
     */
    public void setSyf(BigDecimal syf) {
        this.syf = syf;
    }

    /**
     * 取得 股份交收费
     * @return 股份交收费
     */
    public BigDecimal getJsf() {
        return jsf;
    }

    /**
     * 设置 股份交收费
     * @param jsf 股份交收费
     */
    public void setJsf(BigDecimal jsf) {
        this.jsf = jsf;
    }

    /**
     * 取得 其他金额
     * @return 其他金额
     */
    public BigDecimal getQtje() {
        return qtje;
    }

    /**
     * 设置 其他金额
     * @param qtje 其他金额
     */
    public void setQtje(BigDecimal qtje) {
        this.qtje = qtje;
    }

    /**
     * 取得 应收付金额
     * @return 应收付金额
     */
    public BigDecimal getWbysf() {
        return wbysf;
    }

    /**
     * 设置 应收付金额
     * @param wbysf 应收付金额
     */
    public void setWbysf(BigDecimal wbysf) {
        this.wbysf = wbysf;
    }

    /**
     * 取得 清算币种
     * @return 清算币种
     */
    public String getQsbz() {
        return qsbz;
    }

    /**
     * 设置 清算币种
     * @param qsbz 清算币种
     */
    public void setQsbz(String qsbz) {
        this.qsbz = qsbz == null ? null : qsbz.trim();
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
     * 取得 人民币应收付
     * @return 人民币应收付
     */
    public BigDecimal getYsfje() {
        return ysfje;
    }

    /**
     * 设置 人民币应收付
     * @param ysfje 人民币应收付
     */
    public void setYsfje(BigDecimal ysfje) {
        this.ysfje = ysfje;
    }

    /**
     * 取得 错误代号
     * @return 错误代号
     */
    public String getCwdh() {
        return cwdh;
    }

    /**
     * 设置 错误代号
     * @param cwdh 错误代号
     */
    public void setCwdh(String cwdh) {
        this.cwdh = cwdh == null ? null : cwdh.trim();
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

    /**
     * 取得 发送日期
     * @return 发送日期
     */
    public String getFsrq() {
        return fsrq;
    }

    /**
     * 设置 发送日期
     * @param fsrq 发送日期
     */
    public void setFsrq(String fsrq) {
        this.fsrq = fsrq == null ? null : fsrq.trim();
    }
}