package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfOpicsTradRepo extends BaseEntity {
    /**
     * 网下交易编号生成规则：DEPO
     */
    private String tradeid;

    /**
     * 抵押分类标识，BUYOUT  买断式,PLEDGE   质押式
     */
    private String prodtype;

    /**
     * 回购交易方向，  RS正回购、 RB逆回购
     */
    private String ps;

    /**
     * 是否已生成反向交易 Y:是 N:否(是否生成反向交易为是时才启用)
     */
    private String crtrevdealstatus;

    /**
     * 关联的反向交易编号
     */
    private String retradeid;

    /**
     * 1：银行间；2：上交所；3：深交所
     */
    private String market;

    /**
     * 包括：手动录入等等，此处默认为手工录入，1代表录入，0导入
     */
    private String ismanaul;

    /**
     * 资产池
     */
    private String assetpool;

    /**
     * 成交日
     */
    private Date dealdate;

    /**
     * 回购起息日
     */
    private Date vdate;

    /**
     * 回购到期日
     */
    private Date mdate;

    /**
     * 回购到期期限
     */
    private Long term;

    /**
     * 币种

     */
    private String ccy;

    /**
     * 实际利率值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal reporate;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 到期收益
付息后生效 F
当期生效 D
指定条件生效 Z

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repointamt;

    /**
     * 回购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repoamt;

    /**
     * 债券代码
     */
    private String secid;

    /**
     * 债券名称
     */
    private String secname;

    /**
     * 券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal facetotalamt;

    /**
     * 首次净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vcprice;

    /**
     * 到期净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mcprice;

    /**
     * 首次全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vdprice;

    /**
     * 到期全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mdprice;

    /**
     * 首次收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vyield;

    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal myield;

    /**
     * 首次应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vinterest;

    /**
     * 到期应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minterest;

    /**
     * 首次结算金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vdealamt;

    /**
     * 到期结算金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mdealamt;

    /**
     * PUB：见券付款；DUP：见款付券；DVP：券款对付
     */
    private String vclrform;

    /**
     * PUB：见券付款；DUP：见款付券；DVP：券款对付
     */
    private String mclrform;

    /**
     * I 内部客户 O 外部客户
I 内部客户 O 外部客户

     */
    private String clienttype;

    /**
     * 本方COST
     */
    private String buycost;

    /**
     * 交易员代码
     */
    private String buytrader;

    /**
     * 对手方
     */
    private String seller;

    /**
     * 对手方COST
     */
    private String sellcost;

    /**
     * 交易员代码
     */
    private String selltrader;

    /**
     * 资产推介费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetrefrate;

    /**
     * 资产推介费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetamt;

    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hostrate;

    /**
     * 托管费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hostamt;

    /**
     * OPICS拆借回执号
计息期有效 Q
计息年度有效 Y

     */
    private String dealno;

    /**
     * 到期标识,N：未到期；Y：已到期
     */
    private String mflag;

    /**
     * 资金到账标识，N：未到账；Y：已到账
     */
    private String fmflag;

    /**
     * 现金流发生日
     */
    private Date cdate;

    /**
     * D 删除  A 新建  E 有效
     */
    private String effectflag;

    /**
     * 付息日为节假日时必须的调整准则,包括：succeeding，modified，preceding
     */
    private String intdaterule;

    /**
     * 是否套期保值。1--套期保值，0--纯交易
     */
    private String ishedging;

    /**
     * 附件1路径
     */
    private String attch1dir;

    /**
     * 附件2路径
     */
    private String attch2dir;

    /**
     * 附件3路径
     */
    private String attch3dir;

    /**
     * 交易说明
     */
    private String dealtext;

    /**
     * 创建人员
     */
    private String createuser;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * 更新人员
     */
    private String lstmntuser;

    /**
     * 更新时间
     */
    private Date lstmntdate;

    /**
     * 冲销日期
     */
    private Date revdate;

    /**
     * 原交易编号
     */
    private String oritradeid;

    /**
     * 托管账号
     */
    private String ourdepositaccountnumber;

    /**
     * 对方交易员电话
     */
    private String countertelephone;

    /**
     * null
     */
    private String termType;

    /**
     * null
     */
    private String settleSpeed;


    private BigDecimal repoamtcstp;

    public BigDecimal getRepoamtcstp() {
        return repoamtcstp;
    }

    public void setRepoamtcstp(BigDecimal repoamtcstp) {
        this.repoamtcstp = repoamtcstp;
    }

    /**
     * 取得 网下交易编号生成规则：DEPO
     * @return 网下交易编号生成规则：DEPO
     */
    public String getTradeid() {
        return tradeid;
    }

    /**
     * 设置 网下交易编号生成规则：DEPO
     * @param tradeid 网下交易编号生成规则：DEPO
     */
    public void setTradeid(String tradeid) {
        this.tradeid = tradeid == null ? null : tradeid.trim();
    }

    /**
     * 取得 抵押分类标识，BUYOUT  买断式,PLEDGE   质押式
     * @return 抵押分类标识，BUYOUT  买断式,PLEDGE   质押式
     */
    public String getProdtype() {
        return prodtype;
    }

    /**
     * 设置 抵押分类标识，BUYOUT  买断式,PLEDGE   质押式
     * @param prodtype 抵押分类标识，BUYOUT  买断式,PLEDGE   质押式
     */
    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    /**
     * 取得 回购交易方向，  RS正回购、 RB逆回购
     * @return 回购交易方向，  RS正回购、 RB逆回购
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 回购交易方向，  RS正回购、 RB逆回购
     * @param ps 回购交易方向，  RS正回购、 RB逆回购
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 是否已生成反向交易 Y:是 N:否(是否生成反向交易为是时才启用)
     * @return 是否已生成反向交易 Y:是 N:否(是否生成反向交易为是时才启用)
     */
    public String getCrtrevdealstatus() {
        return crtrevdealstatus;
    }

    /**
     * 设置 是否已生成反向交易 Y:是 N:否(是否生成反向交易为是时才启用)
     * @param crtrevdealstatus 是否已生成反向交易 Y:是 N:否(是否生成反向交易为是时才启用)
     */
    public void setCrtrevdealstatus(String crtrevdealstatus) {
        this.crtrevdealstatus = crtrevdealstatus == null ? null : crtrevdealstatus.trim();
    }

    /**
     * 取得 关联的反向交易编号
     * @return 关联的反向交易编号
     */
    public String getRetradeid() {
        return retradeid;
    }

    /**
     * 设置 关联的反向交易编号
     * @param retradeid 关联的反向交易编号
     */
    public void setRetradeid(String retradeid) {
        this.retradeid = retradeid == null ? null : retradeid.trim();
    }

    /**
     * 取得 1：银行间；2：上交所；3：深交所
     * @return 1：银行间；2：上交所；3：深交所
     */
    public String getMarket() {
        return market;
    }

    /**
     * 设置 1：银行间；2：上交所；3：深交所
     * @param market 1：银行间；2：上交所；3：深交所
     */
    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    /**
     * 取得 包括：手动录入等等，此处默认为手工录入，1代表录入，0导入
     * @return 包括：手动录入等等，此处默认为手工录入，1代表录入，0导入
     */
    public String getIsmanaul() {
        return ismanaul;
    }

    /**
     * 设置 包括：手动录入等等，此处默认为手工录入，1代表录入，0导入
     * @param ismanaul 包括：手动录入等等，此处默认为手工录入，1代表录入，0导入
     */
    public void setIsmanaul(String ismanaul) {
        this.ismanaul = ismanaul == null ? null : ismanaul.trim();
    }

    /**
     * 取得 资产池
     * @return 资产池
     */
    public String getAssetpool() {
        return assetpool;
    }

    /**
     * 设置 资产池
     * @param assetpool 资产池
     */
    public void setAssetpool(String assetpool) {
        this.assetpool = assetpool == null ? null : assetpool.trim();
    }

    /**
     * 取得 成交日
     * @return 成交日
     */
    public Date getDealdate() {
        return dealdate;
    }

    /**
     * 设置 成交日
     * @param dealdate 成交日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
    }

    /**
     * 取得 回购起息日
     * @return 回购起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 回购起息日
     * @param vdate 回购起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 回购到期日
     * @return 回购到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 回购到期日
     * @param mdate 回购到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 回购到期期限
     * @return 回购到期期限
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 回购到期期限
     * @param term 回购到期期限
     */
    public void setTerm(Long term) {
        this.term = term;
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
     * 取得 实际利率值
     * @return 实际利率值
     */
    public BigDecimal getReporate() {
        return reporate;
    }

    /**
     * 设置 实际利率值
     * @param reporate 实际利率值
     */
    public void setReporate(BigDecimal reporate) {
        this.reporate = reporate;
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 到期收益
付息后生效 F
当期生效 D
指定条件生效 Z

     * @return 到期收益
付息后生效 F
当期生效 D
指定条件生效 Z

     */
    public BigDecimal getRepointamt() {
        return repointamt;
    }

    /**
     * 设置 到期收益
付息后生效 F
当期生效 D
指定条件生效 Z

     * @param repointamt 到期收益
付息后生效 F
当期生效 D
指定条件生效 Z

     */
    public void setRepointamt(BigDecimal repointamt) {
        this.repointamt = repointamt;
    }

    /**
     * 取得 回购金额
     * @return 回购金额
     */
    public BigDecimal getRepoamt() {
        return repoamt;
    }

    /**
     * 设置 回购金额
     * @param repoamt 回购金额
     */
    public void setRepoamt(BigDecimal repoamt) {
        this.repoamt = repoamt;
    }

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券代码
     * @param secid 债券代码
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 债券名称
     * @return 债券名称
     */
    public String getSecname() {
        return secname;
    }

    /**
     * 设置 债券名称
     * @param secname 债券名称
     */
    public void setSecname(String secname) {
        this.secname = secname == null ? null : secname.trim();
    }

    /**
     * 取得 券面总额
     * @return 券面总额
     */
    public BigDecimal getFacetotalamt() {
        return facetotalamt;
    }

    /**
     * 设置 券面总额
     * @param facetotalamt 券面总额
     */
    public void setFacetotalamt(BigDecimal facetotalamt) {
        this.facetotalamt = facetotalamt;
    }

    /**
     * 取得 首次净价
     * @return 首次净价
     */
    public BigDecimal getVcprice() {
        return vcprice;
    }

    /**
     * 设置 首次净价
     * @param vcprice 首次净价
     */
    public void setVcprice(BigDecimal vcprice) {
        this.vcprice = vcprice;
    }

    /**
     * 取得 到期净价
     * @return 到期净价
     */
    public BigDecimal getMcprice() {
        return mcprice;
    }

    /**
     * 设置 到期净价
     * @param mcprice 到期净价
     */
    public void setMcprice(BigDecimal mcprice) {
        this.mcprice = mcprice;
    }

    /**
     * 取得 首次全价
     * @return 首次全价
     */
    public BigDecimal getVdprice() {
        return vdprice;
    }

    /**
     * 设置 首次全价
     * @param vdprice 首次全价
     */
    public void setVdprice(BigDecimal vdprice) {
        this.vdprice = vdprice;
    }

    /**
     * 取得 到期全价
     * @return 到期全价
     */
    public BigDecimal getMdprice() {
        return mdprice;
    }

    /**
     * 设置 到期全价
     * @param mdprice 到期全价
     */
    public void setMdprice(BigDecimal mdprice) {
        this.mdprice = mdprice;
    }

    /**
     * 取得 首次收益率
     * @return 首次收益率
     */
    public BigDecimal getVyield() {
        return vyield;
    }

    /**
     * 设置 首次收益率
     * @param vyield 首次收益率
     */
    public void setVyield(BigDecimal vyield) {
        this.vyield = vyield;
    }

    /**
     * 取得 到期收益率
     * @return 到期收益率
     */
    public BigDecimal getMyield() {
        return myield;
    }

    /**
     * 设置 到期收益率
     * @param myield 到期收益率
     */
    public void setMyield(BigDecimal myield) {
        this.myield = myield;
    }

    /**
     * 取得 首次应计利息
     * @return 首次应计利息
     */
    public BigDecimal getVinterest() {
        return vinterest;
    }

    /**
     * 设置 首次应计利息
     * @param vinterest 首次应计利息
     */
    public void setVinterest(BigDecimal vinterest) {
        this.vinterest = vinterest;
    }

    /**
     * 取得 到期应计利息
     * @return 到期应计利息
     */
    public BigDecimal getMinterest() {
        return minterest;
    }

    /**
     * 设置 到期应计利息
     * @param minterest 到期应计利息
     */
    public void setMinterest(BigDecimal minterest) {
        this.minterest = minterest;
    }

    /**
     * 取得 首次结算金额
     * @return 首次结算金额
     */
    public BigDecimal getVdealamt() {
        return vdealamt;
    }

    /**
     * 设置 首次结算金额
     * @param vdealamt 首次结算金额
     */
    public void setVdealamt(BigDecimal vdealamt) {
        this.vdealamt = vdealamt;
    }

    /**
     * 取得 到期结算金额
     * @return 到期结算金额
     */
    public BigDecimal getMdealamt() {
        return mdealamt;
    }

    /**
     * 设置 到期结算金额
     * @param mdealamt 到期结算金额
     */
    public void setMdealamt(BigDecimal mdealamt) {
        this.mdealamt = mdealamt;
    }

    /**
     * 取得 PUB：见券付款；DUP：见款付券；DVP：券款对付
     * @return PUB：见券付款；DUP：见款付券；DVP：券款对付
     */
    public String getVclrform() {
        return vclrform;
    }

    /**
     * 设置 PUB：见券付款；DUP：见款付券；DVP：券款对付
     * @param vclrform PUB：见券付款；DUP：见款付券；DVP：券款对付
     */
    public void setVclrform(String vclrform) {
        this.vclrform = vclrform == null ? null : vclrform.trim();
    }

    /**
     * 取得 PUB：见券付款；DUP：见款付券；DVP：券款对付
     * @return PUB：见券付款；DUP：见款付券；DVP：券款对付
     */
    public String getMclrform() {
        return mclrform;
    }

    /**
     * 设置 PUB：见券付款；DUP：见款付券；DVP：券款对付
     * @param mclrform PUB：见券付款；DUP：见款付券；DVP：券款对付
     */
    public void setMclrform(String mclrform) {
        this.mclrform = mclrform == null ? null : mclrform.trim();
    }

    /**
     * 取得 I 内部客户 O 外部客户
I 内部客户 O 外部客户

     * @return I 内部客户 O 外部客户
I 内部客户 O 外部客户

     */
    public String getClienttype() {
        return clienttype;
    }

    /**
     * 设置 I 内部客户 O 外部客户
I 内部客户 O 外部客户

     * @param clienttype I 内部客户 O 外部客户
I 内部客户 O 外部客户

     */
    public void setClienttype(String clienttype) {
        this.clienttype = clienttype == null ? null : clienttype.trim();
    }

    /**
     * 取得 本方COST
     * @return 本方COST
     */
    public String getBuycost() {
        return buycost;
    }

    /**
     * 设置 本方COST
     * @param buycost 本方COST
     */
    public void setBuycost(String buycost) {
        this.buycost = buycost == null ? null : buycost.trim();
    }

    /**
     * 取得 交易员代码
     * @return 交易员代码
     */
    public String getBuytrader() {
        return buytrader;
    }

    /**
     * 设置 交易员代码
     * @param buytrader 交易员代码
     */
    public void setBuytrader(String buytrader) {
        this.buytrader = buytrader == null ? null : buytrader.trim();
    }

    /**
     * 取得 对手方
     * @return 对手方
     */
    public String getSeller() {
        return seller;
    }

    /**
     * 设置 对手方
     * @param seller 对手方
     */
    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    /**
     * 取得 对手方COST
     * @return 对手方COST
     */
    public String getSellcost() {
        return sellcost;
    }

    /**
     * 设置 对手方COST
     * @param sellcost 对手方COST
     */
    public void setSellcost(String sellcost) {
        this.sellcost = sellcost == null ? null : sellcost.trim();
    }

    /**
     * 取得 交易员代码
     * @return 交易员代码
     */
    public String getSelltrader() {
        return selltrader;
    }

    /**
     * 设置 交易员代码
     * @param selltrader 交易员代码
     */
    public void setSelltrader(String selltrader) {
        this.selltrader = selltrader == null ? null : selltrader.trim();
    }

    /**
     * 取得 资产推介费率
     * @return 资产推介费率
     */
    public BigDecimal getAssetrefrate() {
        return assetrefrate;
    }

    /**
     * 设置 资产推介费率
     * @param assetrefrate 资产推介费率
     */
    public void setAssetrefrate(BigDecimal assetrefrate) {
        this.assetrefrate = assetrefrate;
    }

    /**
     * 取得 资产推介费
     * @return 资产推介费
     */
    public BigDecimal getAssetamt() {
        return assetamt;
    }

    /**
     * 设置 资产推介费
     * @param assetamt 资产推介费
     */
    public void setAssetamt(BigDecimal assetamt) {
        this.assetamt = assetamt;
    }

    /**
     * 取得 托管费率
     * @return 托管费率
     */
    public BigDecimal getHostrate() {
        return hostrate;
    }

    /**
     * 设置 托管费率
     * @param hostrate 托管费率
     */
    public void setHostrate(BigDecimal hostrate) {
        this.hostrate = hostrate;
    }

    /**
     * 取得 托管费
     * @return 托管费
     */
    public BigDecimal getHostamt() {
        return hostamt;
    }

    /**
     * 设置 托管费
     * @param hostamt 托管费
     */
    public void setHostamt(BigDecimal hostamt) {
        this.hostamt = hostamt;
    }

    /**
     * 取得 OPICS拆借回执号
计息期有效 Q
计息年度有效 Y

     * @return OPICS拆借回执号
计息期有效 Q
计息年度有效 Y

     */
    public String getDealno() {
        return dealno;
    }

    /**
     * 设置 OPICS拆借回执号
计息期有效 Q
计息年度有效 Y

     * @param dealno OPICS拆借回执号
计息期有效 Q
计息年度有效 Y

     */
    public void setDealno(String dealno) {
        this.dealno = dealno == null ? null : dealno.trim();
    }

    /**
     * 取得 到期标识,N：未到期；Y：已到期
     * @return 到期标识,N：未到期；Y：已到期
     */
    public String getMflag() {
        return mflag;
    }

    /**
     * 设置 到期标识,N：未到期；Y：已到期
     * @param mflag 到期标识,N：未到期；Y：已到期
     */
    public void setMflag(String mflag) {
        this.mflag = mflag == null ? null : mflag.trim();
    }

    /**
     * 取得 资金到账标识，N：未到账；Y：已到账
     * @return 资金到账标识，N：未到账；Y：已到账
     */
    public String getFmflag() {
        return fmflag;
    }

    /**
     * 设置 资金到账标识，N：未到账；Y：已到账
     * @param fmflag 资金到账标识，N：未到账；Y：已到账
     */
    public void setFmflag(String fmflag) {
        this.fmflag = fmflag == null ? null : fmflag.trim();
    }

    /**
     * 取得 现金流发生日
     * @return 现金流发生日
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 现金流发生日
     * @param cdate 现金流发生日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 D 删除  A 新建  E 有效
     * @return D 删除  A 新建  E 有效
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 D 删除  A 新建  E 有效
     * @param effectflag D 删除  A 新建  E 有效
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 取得 付息日为节假日时必须的调整准则,包括：succeeding，modified，preceding
     * @return 付息日为节假日时必须的调整准则,包括：succeeding，modified，preceding
     */
    public String getIntdaterule() {
        return intdaterule;
    }

    /**
     * 设置 付息日为节假日时必须的调整准则,包括：succeeding，modified，preceding
     * @param intdaterule 付息日为节假日时必须的调整准则,包括：succeeding，modified，preceding
     */
    public void setIntdaterule(String intdaterule) {
        this.intdaterule = intdaterule == null ? null : intdaterule.trim();
    }

    /**
     * 取得 是否套期保值。1--套期保值，0--纯交易
     * @return 是否套期保值。1--套期保值，0--纯交易
     */
    public String getIshedging() {
        return ishedging;
    }

    /**
     * 设置 是否套期保值。1--套期保值，0--纯交易
     * @param ishedging 是否套期保值。1--套期保值，0--纯交易
     */
    public void setIshedging(String ishedging) {
        this.ishedging = ishedging == null ? null : ishedging.trim();
    }

    /**
     * 取得 附件1路径
     * @return 附件1路径
     */
    public String getAttch1dir() {
        return attch1dir;
    }

    /**
     * 设置 附件1路径
     * @param attch1dir 附件1路径
     */
    public void setAttch1dir(String attch1dir) {
        this.attch1dir = attch1dir == null ? null : attch1dir.trim();
    }

    /**
     * 取得 附件2路径
     * @return 附件2路径
     */
    public String getAttch2dir() {
        return attch2dir;
    }

    /**
     * 设置 附件2路径
     * @param attch2dir 附件2路径
     */
    public void setAttch2dir(String attch2dir) {
        this.attch2dir = attch2dir == null ? null : attch2dir.trim();
    }

    /**
     * 取得 附件3路径
     * @return 附件3路径
     */
    public String getAttch3dir() {
        return attch3dir;
    }

    /**
     * 设置 附件3路径
     * @param attch3dir 附件3路径
     */
    public void setAttch3dir(String attch3dir) {
        this.attch3dir = attch3dir == null ? null : attch3dir.trim();
    }

    /**
     * 取得 交易说明
     * @return 交易说明
     */
    public String getDealtext() {
        return dealtext;
    }

    /**
     * 设置 交易说明
     * @param dealtext 交易说明
     */
    public void setDealtext(String dealtext) {
        this.dealtext = dealtext == null ? null : dealtext.trim();
    }

    /**
     * 取得 创建人员
     * @return 创建人员
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * 设置 创建人员
     * @param createuser 创建人员
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    /**
     * 取得 创建时间
     * @return 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置 创建时间
     * @param createdate 创建时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 取得 更新人员
     * @return 更新人员
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 更新人员
     * @param lstmntuser 更新人员
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
    }

    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 更新时间
     * @param lstmntdate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 冲销日期
     * @return 冲销日期
     */
    public Date getRevdate() {
        return revdate;
    }

    /**
     * 设置 冲销日期
     * @param revdate 冲销日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevdate(Date revdate) {
        this.revdate = revdate;
    }

    /**
     * 取得 原交易编号
     * @return 原交易编号
     */
    public String getOritradeid() {
        return oritradeid;
    }

    /**
     * 设置 原交易编号
     * @param oritradeid 原交易编号
     */
    public void setOritradeid(String oritradeid) {
        this.oritradeid = oritradeid == null ? null : oritradeid.trim();
    }

    /**
     * 取得 托管账号
     * @return 托管账号
     */
    public String getOurdepositaccountnumber() {
        return ourdepositaccountnumber;
    }

    /**
     * 设置 托管账号
     * @param ourdepositaccountnumber 托管账号
     */
    public void setOurdepositaccountnumber(String ourdepositaccountnumber) {
        this.ourdepositaccountnumber = ourdepositaccountnumber == null ? null : ourdepositaccountnumber.trim();
    }

    /**
     * 取得 对方交易员电话
     * @return 对方交易员电话
     */
    public String getCountertelephone() {
        return countertelephone;
    }

    /**
     * 设置 对方交易员电话
     * @param countertelephone 对方交易员电话
     */
    public void setCountertelephone(String countertelephone) {
        this.countertelephone = countertelephone == null ? null : countertelephone.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 null
     * @param termType null
     */
    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSettleSpeed() {
        return settleSpeed;
    }

    /**
     * 设置 null
     * @param settleSpeed null
     */
    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed == null ? null : settleSpeed.trim();
    }
}