package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class SzdSjsjg extends BaseEntity {
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
     * 结算账号
     */
    private String jgjszh;

    /**
     * 备付金账户
     */
    private String jgbfzh;

    /**
     * 数据类型
     */
    private String jgsjlx;

    /**
     * 业务类别
     */
    private String jgywlb;

    /**
     * 证券代码
     */
    private String jgzqdm;

    /**
     * 交易单元
     */
    private String jgjydy;

    /**
     * 托管单元
     */
    private String jgtgdy;

    /**
     * 证券账户号码
     */
    private String jgzqzh;

    /**
     * 客户订单编号
     */
    private String jgddbh;

    /**
     * 营业部代码
     */
    private String jgyyb;

    /**
     * 执行编号
     */
    private String jgzxbh;

    /**
     * 业务流水号
     */
    private String jgywlsh;

    /**
     * 成交数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgcjsl;

    /**
     * 清算数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgqssl;

    /**
     * 交收数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgjssl;

    /**
     * 成交价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgcjjg;

    /**
     * 清算价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgqsjg;

    /**
     * 信用交易标识
     */
    private String jgxyjy;

    /**
     * 平仓标识
     */
    private String jgpcbs;

    /**
     * 证券类别
     */
    private String jgzqlb;

    /**
     * 证券子类
     */
    private String jgzqzl;

    /**
     * 股份性质
     */
    private String jggfxz;

    /**
     * 流通类型
     */
    private String jgltlx;

    /**
     * 交收方式
     */
    private String jgjsfs;

    /**
     * 货币代号
     */
    private String jghbdh;

    /**
     * 清算本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgqsbj;

    /**
     * 印花税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgyhs;

    /**
     * 交易经手费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgjyjsf;

    /**
     * 监管规费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgjggf;

    /**
     * 过户费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgghf;

    /**
     * 结算费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgjsf;

    /**
     * 手续费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgsxf;

    /**
     * 结算参与人佣金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgqsyj;

    /**
     * 其他费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgqtfy;

    /**
     * 资金金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgzjje;

    /**
     * 收付净额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal jgsfje;

    /**
     * 交收标志
     */
    private String jgjsbz;

    /**
     * 摘要代号
     */
    private String jgzydh;

    /**
     * 成交日期
     */
    private String jgcjrq;

    /**
     * 清算日期
     */
    private String jgqsrq;

    /**
     * 交收日期
     */
    private String jgjsrq;

    /**
     * 发送日期
     */
    private String jgfsrq;

    /**
     * 其它日期
     */
    private String jgqtrq;

    /**
     * 市场代码
     */
    private String jgscdm;

    /**
     * 交易方式
     */
    private String jgjyfs;

    /**
     * 证券代码2
     */
    private String jgzqdm2;

    /**
     * 托管单元2
     */
    private String jgtgdy2;

    /**
     * 订单编号2
     */
    private String jgddbh2;

    /**
     * 附加说明
     */
    private String jgfjsm;

    /**
     * 备用标志
     */
    private String jgbybz;

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
     * 取得 结算账号
     * @return 结算账号
     */
    public String getJgjszh() {
        return jgjszh;
    }

    /**
     * 设置 结算账号
     * @param jgjszh 结算账号
     */
    public void setJgjszh(String jgjszh) {
        this.jgjszh = jgjszh == null ? null : jgjszh.trim();
    }

    /**
     * 取得 备付金账户
     * @return 备付金账户
     */
    public String getJgbfzh() {
        return jgbfzh;
    }

    /**
     * 设置 备付金账户
     * @param jgbfzh 备付金账户
     */
    public void setJgbfzh(String jgbfzh) {
        this.jgbfzh = jgbfzh == null ? null : jgbfzh.trim();
    }

    /**
     * 取得 数据类型
     * @return 数据类型
     */
    public String getJgsjlx() {
        return jgsjlx;
    }

    /**
     * 设置 数据类型
     * @param jgsjlx 数据类型
     */
    public void setJgsjlx(String jgsjlx) {
        this.jgsjlx = jgsjlx == null ? null : jgsjlx.trim();
    }

    /**
     * 取得 业务类别
     * @return 业务类别
     */
    public String getJgywlb() {
        return jgywlb;
    }

    /**
     * 设置 业务类别
     * @param jgywlb 业务类别
     */
    public void setJgywlb(String jgywlb) {
        this.jgywlb = jgywlb == null ? null : jgywlb.trim();
    }

    /**
     * 取得 证券代码
     * @return 证券代码
     */
    public String getJgzqdm() {
        return jgzqdm;
    }

    /**
     * 设置 证券代码
     * @param jgzqdm 证券代码
     */
    public void setJgzqdm(String jgzqdm) {
        this.jgzqdm = jgzqdm == null ? null : jgzqdm.trim();
    }

    /**
     * 取得 交易单元
     * @return 交易单元
     */
    public String getJgjydy() {
        return jgjydy;
    }

    /**
     * 设置 交易单元
     * @param jgjydy 交易单元
     */
    public void setJgjydy(String jgjydy) {
        this.jgjydy = jgjydy == null ? null : jgjydy.trim();
    }

    /**
     * 取得 托管单元
     * @return 托管单元
     */
    public String getJgtgdy() {
        return jgtgdy;
    }

    /**
     * 设置 托管单元
     * @param jgtgdy 托管单元
     */
    public void setJgtgdy(String jgtgdy) {
        this.jgtgdy = jgtgdy == null ? null : jgtgdy.trim();
    }

    /**
     * 取得 证券账户号码
     * @return 证券账户号码
     */
    public String getJgzqzh() {
        return jgzqzh;
    }

    /**
     * 设置 证券账户号码
     * @param jgzqzh 证券账户号码
     */
    public void setJgzqzh(String jgzqzh) {
        this.jgzqzh = jgzqzh == null ? null : jgzqzh.trim();
    }

    /**
     * 取得 客户订单编号
     * @return 客户订单编号
     */
    public String getJgddbh() {
        return jgddbh;
    }

    /**
     * 设置 客户订单编号
     * @param jgddbh 客户订单编号
     */
    public void setJgddbh(String jgddbh) {
        this.jgddbh = jgddbh == null ? null : jgddbh.trim();
    }

    /**
     * 取得 营业部代码
     * @return 营业部代码
     */
    public String getJgyyb() {
        return jgyyb;
    }

    /**
     * 设置 营业部代码
     * @param jgyyb 营业部代码
     */
    public void setJgyyb(String jgyyb) {
        this.jgyyb = jgyyb == null ? null : jgyyb.trim();
    }

    /**
     * 取得 执行编号
     * @return 执行编号
     */
    public String getJgzxbh() {
        return jgzxbh;
    }

    /**
     * 设置 执行编号
     * @param jgzxbh 执行编号
     */
    public void setJgzxbh(String jgzxbh) {
        this.jgzxbh = jgzxbh == null ? null : jgzxbh.trim();
    }

    /**
     * 取得 业务流水号
     * @return 业务流水号
     */
    public String getJgywlsh() {
        return jgywlsh;
    }

    /**
     * 设置 业务流水号
     * @param jgywlsh 业务流水号
     */
    public void setJgywlsh(String jgywlsh) {
        this.jgywlsh = jgywlsh == null ? null : jgywlsh.trim();
    }

    /**
     * 取得 成交数量
     * @return 成交数量
     */
    public BigDecimal getJgcjsl() {
        return jgcjsl;
    }

    /**
     * 设置 成交数量
     * @param jgcjsl 成交数量
     */
    public void setJgcjsl(BigDecimal jgcjsl) {
        this.jgcjsl = jgcjsl;
    }

    /**
     * 取得 清算数量
     * @return 清算数量
     */
    public BigDecimal getJgqssl() {
        return jgqssl;
    }

    /**
     * 设置 清算数量
     * @param jgqssl 清算数量
     */
    public void setJgqssl(BigDecimal jgqssl) {
        this.jgqssl = jgqssl;
    }

    /**
     * 取得 交收数量
     * @return 交收数量
     */
    public BigDecimal getJgjssl() {
        return jgjssl;
    }

    /**
     * 设置 交收数量
     * @param jgjssl 交收数量
     */
    public void setJgjssl(BigDecimal jgjssl) {
        this.jgjssl = jgjssl;
    }

    /**
     * 取得 成交价格
     * @return 成交价格
     */
    public BigDecimal getJgcjjg() {
        return jgcjjg;
    }

    /**
     * 设置 成交价格
     * @param jgcjjg 成交价格
     */
    public void setJgcjjg(BigDecimal jgcjjg) {
        this.jgcjjg = jgcjjg;
    }

    /**
     * 取得 清算价格
     * @return 清算价格
     */
    public BigDecimal getJgqsjg() {
        return jgqsjg;
    }

    /**
     * 设置 清算价格
     * @param jgqsjg 清算价格
     */
    public void setJgqsjg(BigDecimal jgqsjg) {
        this.jgqsjg = jgqsjg;
    }

    /**
     * 取得 信用交易标识
     * @return 信用交易标识
     */
    public String getJgxyjy() {
        return jgxyjy;
    }

    /**
     * 设置 信用交易标识
     * @param jgxyjy 信用交易标识
     */
    public void setJgxyjy(String jgxyjy) {
        this.jgxyjy = jgxyjy == null ? null : jgxyjy.trim();
    }

    /**
     * 取得 平仓标识
     * @return 平仓标识
     */
    public String getJgpcbs() {
        return jgpcbs;
    }

    /**
     * 设置 平仓标识
     * @param jgpcbs 平仓标识
     */
    public void setJgpcbs(String jgpcbs) {
        this.jgpcbs = jgpcbs == null ? null : jgpcbs.trim();
    }

    /**
     * 取得 证券类别
     * @return 证券类别
     */
    public String getJgzqlb() {
        return jgzqlb;
    }

    /**
     * 设置 证券类别
     * @param jgzqlb 证券类别
     */
    public void setJgzqlb(String jgzqlb) {
        this.jgzqlb = jgzqlb == null ? null : jgzqlb.trim();
    }

    /**
     * 取得 证券子类
     * @return 证券子类
     */
    public String getJgzqzl() {
        return jgzqzl;
    }

    /**
     * 设置 证券子类
     * @param jgzqzl 证券子类
     */
    public void setJgzqzl(String jgzqzl) {
        this.jgzqzl = jgzqzl == null ? null : jgzqzl.trim();
    }

    /**
     * 取得 股份性质
     * @return 股份性质
     */
    public String getJggfxz() {
        return jggfxz;
    }

    /**
     * 设置 股份性质
     * @param jggfxz 股份性质
     */
    public void setJggfxz(String jggfxz) {
        this.jggfxz = jggfxz == null ? null : jggfxz.trim();
    }

    /**
     * 取得 流通类型
     * @return 流通类型
     */
    public String getJgltlx() {
        return jgltlx;
    }

    /**
     * 设置 流通类型
     * @param jgltlx 流通类型
     */
    public void setJgltlx(String jgltlx) {
        this.jgltlx = jgltlx == null ? null : jgltlx.trim();
    }

    /**
     * 取得 交收方式
     * @return 交收方式
     */
    public String getJgjsfs() {
        return jgjsfs;
    }

    /**
     * 设置 交收方式
     * @param jgjsfs 交收方式
     */
    public void setJgjsfs(String jgjsfs) {
        this.jgjsfs = jgjsfs == null ? null : jgjsfs.trim();
    }

    /**
     * 取得 货币代号
     * @return 货币代号
     */
    public String getJghbdh() {
        return jghbdh;
    }

    /**
     * 设置 货币代号
     * @param jghbdh 货币代号
     */
    public void setJghbdh(String jghbdh) {
        this.jghbdh = jghbdh == null ? null : jghbdh.trim();
    }

    /**
     * 取得 清算本金
     * @return 清算本金
     */
    public BigDecimal getJgqsbj() {
        return jgqsbj;
    }

    /**
     * 设置 清算本金
     * @param jgqsbj 清算本金
     */
    public void setJgqsbj(BigDecimal jgqsbj) {
        this.jgqsbj = jgqsbj;
    }

    /**
     * 取得 印花税
     * @return 印花税
     */
    public BigDecimal getJgyhs() {
        return jgyhs;
    }

    /**
     * 设置 印花税
     * @param jgyhs 印花税
     */
    public void setJgyhs(BigDecimal jgyhs) {
        this.jgyhs = jgyhs;
    }

    /**
     * 取得 交易经手费
     * @return 交易经手费
     */
    public BigDecimal getJgjyjsf() {
        return jgjyjsf;
    }

    /**
     * 设置 交易经手费
     * @param jgjyjsf 交易经手费
     */
    public void setJgjyjsf(BigDecimal jgjyjsf) {
        this.jgjyjsf = jgjyjsf;
    }

    /**
     * 取得 监管规费
     * @return 监管规费
     */
    public BigDecimal getJgjggf() {
        return jgjggf;
    }

    /**
     * 设置 监管规费
     * @param jgjggf 监管规费
     */
    public void setJgjggf(BigDecimal jgjggf) {
        this.jgjggf = jgjggf;
    }

    /**
     * 取得 过户费
     * @return 过户费
     */
    public BigDecimal getJgghf() {
        return jgghf;
    }

    /**
     * 设置 过户费
     * @param jgghf 过户费
     */
    public void setJgghf(BigDecimal jgghf) {
        this.jgghf = jgghf;
    }

    /**
     * 取得 结算费
     * @return 结算费
     */
    public BigDecimal getJgjsf() {
        return jgjsf;
    }

    /**
     * 设置 结算费
     * @param jgjsf 结算费
     */
    public void setJgjsf(BigDecimal jgjsf) {
        this.jgjsf = jgjsf;
    }

    /**
     * 取得 手续费
     * @return 手续费
     */
    public BigDecimal getJgsxf() {
        return jgsxf;
    }

    /**
     * 设置 手续费
     * @param jgsxf 手续费
     */
    public void setJgsxf(BigDecimal jgsxf) {
        this.jgsxf = jgsxf;
    }

    /**
     * 取得 结算参与人佣金
     * @return 结算参与人佣金
     */
    public BigDecimal getJgqsyj() {
        return jgqsyj;
    }

    /**
     * 设置 结算参与人佣金
     * @param jgqsyj 结算参与人佣金
     */
    public void setJgqsyj(BigDecimal jgqsyj) {
        this.jgqsyj = jgqsyj;
    }

    /**
     * 取得 其他费用
     * @return 其他费用
     */
    public BigDecimal getJgqtfy() {
        return jgqtfy;
    }

    /**
     * 设置 其他费用
     * @param jgqtfy 其他费用
     */
    public void setJgqtfy(BigDecimal jgqtfy) {
        this.jgqtfy = jgqtfy;
    }

    /**
     * 取得 资金金额
     * @return 资金金额
     */
    public BigDecimal getJgzjje() {
        return jgzjje;
    }

    /**
     * 设置 资金金额
     * @param jgzjje 资金金额
     */
    public void setJgzjje(BigDecimal jgzjje) {
        this.jgzjje = jgzjje;
    }

    /**
     * 取得 收付净额
     * @return 收付净额
     */
    public BigDecimal getJgsfje() {
        return jgsfje;
    }

    /**
     * 设置 收付净额
     * @param jgsfje 收付净额
     */
    public void setJgsfje(BigDecimal jgsfje) {
        this.jgsfje = jgsfje;
    }

    /**
     * 取得 交收标志
     * @return 交收标志
     */
    public String getJgjsbz() {
        return jgjsbz;
    }

    /**
     * 设置 交收标志
     * @param jgjsbz 交收标志
     */
    public void setJgjsbz(String jgjsbz) {
        this.jgjsbz = jgjsbz == null ? null : jgjsbz.trim();
    }

    /**
     * 取得 摘要代号
     * @return 摘要代号
     */
    public String getJgzydh() {
        return jgzydh;
    }

    /**
     * 设置 摘要代号
     * @param jgzydh 摘要代号
     */
    public void setJgzydh(String jgzydh) {
        this.jgzydh = jgzydh == null ? null : jgzydh.trim();
    }

    /**
     * 取得 成交日期
     * @return 成交日期
     */
    public String getJgcjrq() {
        return jgcjrq;
    }

    /**
     * 设置 成交日期
     * @param jgcjrq 成交日期
     */
    public void setJgcjrq(String jgcjrq) {
        this.jgcjrq = jgcjrq == null ? null : jgcjrq.trim();
    }

    /**
     * 取得 清算日期
     * @return 清算日期
     */
    public String getJgqsrq() {
        return jgqsrq;
    }

    /**
     * 设置 清算日期
     * @param jgqsrq 清算日期
     */
    public void setJgqsrq(String jgqsrq) {
        this.jgqsrq = jgqsrq == null ? null : jgqsrq.trim();
    }

    /**
     * 取得 交收日期
     * @return 交收日期
     */
    public String getJgjsrq() {
        return jgjsrq;
    }

    /**
     * 设置 交收日期
     * @param jgjsrq 交收日期
     */
    public void setJgjsrq(String jgjsrq) {
        this.jgjsrq = jgjsrq == null ? null : jgjsrq.trim();
    }

    /**
     * 取得 发送日期
     * @return 发送日期
     */
    public String getJgfsrq() {
        return jgfsrq;
    }

    /**
     * 设置 发送日期
     * @param jgfsrq 发送日期
     */
    public void setJgfsrq(String jgfsrq) {
        this.jgfsrq = jgfsrq == null ? null : jgfsrq.trim();
    }

    /**
     * 取得 其它日期
     * @return 其它日期
     */
    public String getJgqtrq() {
        return jgqtrq;
    }

    /**
     * 设置 其它日期
     * @param jgqtrq 其它日期
     */
    public void setJgqtrq(String jgqtrq) {
        this.jgqtrq = jgqtrq == null ? null : jgqtrq.trim();
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getJgscdm() {
        return jgscdm;
    }

    /**
     * 设置 市场代码
     * @param jgscdm 市场代码
     */
    public void setJgscdm(String jgscdm) {
        this.jgscdm = jgscdm == null ? null : jgscdm.trim();
    }

    /**
     * 取得 交易方式
     * @return 交易方式
     */
    public String getJgjyfs() {
        return jgjyfs;
    }

    /**
     * 设置 交易方式
     * @param jgjyfs 交易方式
     */
    public void setJgjyfs(String jgjyfs) {
        this.jgjyfs = jgjyfs == null ? null : jgjyfs.trim();
    }

    /**
     * 取得 证券代码2
     * @return 证券代码2
     */
    public String getJgzqdm2() {
        return jgzqdm2;
    }

    /**
     * 设置 证券代码2
     * @param jgzqdm2 证券代码2
     */
    public void setJgzqdm2(String jgzqdm2) {
        this.jgzqdm2 = jgzqdm2 == null ? null : jgzqdm2.trim();
    }

    /**
     * 取得 托管单元2
     * @return 托管单元2
     */
    public String getJgtgdy2() {
        return jgtgdy2;
    }

    /**
     * 设置 托管单元2
     * @param jgtgdy2 托管单元2
     */
    public void setJgtgdy2(String jgtgdy2) {
        this.jgtgdy2 = jgtgdy2 == null ? null : jgtgdy2.trim();
    }

    /**
     * 取得 订单编号2
     * @return 订单编号2
     */
    public String getJgddbh2() {
        return jgddbh2;
    }

    /**
     * 设置 订单编号2
     * @param jgddbh2 订单编号2
     */
    public void setJgddbh2(String jgddbh2) {
        this.jgddbh2 = jgddbh2 == null ? null : jgddbh2.trim();
    }

    /**
     * 取得 附加说明
     * @return 附加说明
     */
    public String getJgfjsm() {
        return jgfjsm;
    }

    /**
     * 设置 附加说明
     * @param jgfjsm 附加说明
     */
    public void setJgfjsm(String jgfjsm) {
        this.jgfjsm = jgfjsm == null ? null : jgfjsm.trim();
    }

    /**
     * 取得 备用标志
     * @return 备用标志
     */
    public String getJgbybz() {
        return jgbybz;
    }

    /**
     * 设置 备用标志
     * @param jgbybz 备用标志
     */
    public void setJgbybz(String jgbybz) {
        this.jgbybz = jgbybz == null ? null : jgbybz.trim();
    }
}