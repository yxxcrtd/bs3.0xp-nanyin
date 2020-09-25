package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfOpicsTgtSecinfo extends BaseEntity {
    /**
     * 债券代码
     */
    private String secid;

    /**
     * 票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal couprate8;

    /**
     * 利率代码
     */
    private String ratecode;

    /**
     * 附加固定利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadrate8;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 描述
     */
    private String descr;

    /**
     * 利息基本代码
     */
    private String basis;

    /**
     * 发行者
     */
    private String issuer;

    /**
     * 支付利息周期
     */
    private String intpaycycle;

    /**
     * 产品
     */
    private String product;

    /**
     * 业务类型
     */
    private String prodtype;

    /**
     * 计息基础
Actual/Actual A/A
Actual/365 A/365
Actual/360 A/360

     */
    private String ratebasic;

    /**
     * 息票品种
贴现 DIS
零息 ZCO
附息 NPV
到期一次还本付息 IAM

     */
    private String couponspecies;

    /**
     * 利率类型
固定利率 FI
浮动利率 FL

     */
    private String interestrate;

    /**
     * 发行日
     */
    private Date issdate;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 更新时间
     */
    private Date lstmntdate;

    /**
     * 面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal facevalue;

    /**
     * 发行价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueprice;

    /**
     * 利息分配方式(只针对固定利率债券)
平均分配 J
按实际天数分配 D

     */
    private String intpayrule;

    /**
     * 付息生成规则(即付息计划推算方法)
起息日向后  VF
到期日向前 MB
首次付息日 FD

     */
    private String schecalrule;

    /**
     * 首次付息日
     */
    private Date firstrateday;

    /**
     * 营业日准则
向后                 next
向前                 last
调整的向后     adjust
不调整             un

     */
    private String workdayrule;

    /**
     * 基准利率生效方式
付息后生效 F
当期生效 D
指定条件生效 Z

     */
    private String ratevaluetype;

    /**
     * 利率生效时期:
计息期有效 Q
计息年度有效 Y

     */
    private String ratevalueperiod;

    /**
     * 利率生效条件
基准利率变动后固定间隔时间生效 H
付息前指定日期的有效基准利率 Q

     */
    private String ratevaluecdtn;

    /**
     * 生效条件时间差
     */
    private Integer ratevaluedays;

    /**
     * 发行总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueamt;

    /**
     * 债券类型
国债 00
政策性金融债 01
央行票据 02
普通金融债 03
普通企业债 04
公司债 05
中期 06
短期融资券 07
次级债票据 08
地方政府债券 09
资产支持证券 10

     */
    private String sectype;

    /**
     * 有效标识
     */
    private String effectflag;

    /**
     * null
     */
    private String sn;

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
     * 取得 票面利率
     * @return 票面利率
     */
    public BigDecimal getCouprate8() {
        return couprate8;
    }

    /**
     * 设置 票面利率
     * @param couprate8 票面利率
     */
    public void setCouprate8(BigDecimal couprate8) {
        this.couprate8 = couprate8;
    }

    /**
     * 取得 利率代码
     * @return 利率代码
     */
    public String getRatecode() {
        return ratecode;
    }

    /**
     * 设置 利率代码
     * @param ratecode 利率代码
     */
    public void setRatecode(String ratecode) {
        this.ratecode = ratecode == null ? null : ratecode.trim();
    }

    /**
     * 取得 附加固定利率
     * @return 附加固定利率
     */
    public BigDecimal getSpreadrate8() {
        return spreadrate8;
    }

    /**
     * 设置 附加固定利率
     * @param spreadrate8 附加固定利率
     */
    public void setSpreadrate8(BigDecimal spreadrate8) {
        this.spreadrate8 = spreadrate8;
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
     * 取得 描述
     * @return 描述
     */
    public String getDescr() {
        return descr;
    }

    /**
     * 设置 描述
     * @param descr 描述
     */
    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    /**
     * 取得 利息基本代码
     * @return 利息基本代码
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 利息基本代码
     * @param basis 利息基本代码
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 发行者
     * @return 发行者
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置 发行者
     * @param issuer 发行者
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    /**
     * 取得 支付利息周期
     * @return 支付利息周期
     */
    public String getIntpaycycle() {
        return intpaycycle;
    }

    /**
     * 设置 支付利息周期
     * @param intpaycycle 支付利息周期
     */
    public void setIntpaycycle(String intpaycycle) {
        this.intpaycycle = intpaycycle == null ? null : intpaycycle.trim();
    }

    /**
     * 取得 产品
     * @return 产品
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置 产品
     * @param product 产品
     */
    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getProdtype() {
        return prodtype;
    }

    /**
     * 设置 业务类型
     * @param prodtype 业务类型
     */
    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    /**
     * 取得 计息基础
Actual/Actual A/A
Actual/365 A/365
Actual/360 A/360

     * @return 计息基础
Actual/Actual A/A
Actual/365 A/365
Actual/360 A/360

     */
    public String getRatebasic() {
        return ratebasic;
    }

    /**
     * 设置 计息基础
Actual/Actual A/A
Actual/365 A/365
Actual/360 A/360

     * @param ratebasic 计息基础
Actual/Actual A/A
Actual/365 A/365
Actual/360 A/360

     */
    public void setRatebasic(String ratebasic) {
        this.ratebasic = ratebasic == null ? null : ratebasic.trim();
    }

    /**
     * 取得 息票品种
贴现 DIS
零息 ZCO
附息 NPV
到期一次还本付息 IAM

     * @return 息票品种
贴现 DIS
零息 ZCO
附息 NPV
到期一次还本付息 IAM

     */
    public String getCouponspecies() {
        return couponspecies;
    }

    /**
     * 设置 息票品种
贴现 DIS
零息 ZCO
附息 NPV
到期一次还本付息 IAM

     * @param couponspecies 息票品种
贴现 DIS
零息 ZCO
附息 NPV
到期一次还本付息 IAM

     */
    public void setCouponspecies(String couponspecies) {
        this.couponspecies = couponspecies == null ? null : couponspecies.trim();
    }

    /**
     * 取得 利率类型
固定利率 FI
浮动利率 FL

     * @return 利率类型
固定利率 FI
浮动利率 FL

     */
    public String getInterestrate() {
        return interestrate;
    }

    /**
     * 设置 利率类型
固定利率 FI
浮动利率 FL

     * @param interestrate 利率类型
固定利率 FI
浮动利率 FL

     */
    public void setInterestrate(String interestrate) {
        this.interestrate = interestrate == null ? null : interestrate.trim();
    }

    /**
     * 取得 发行日
     * @return 发行日
     */
    public Date getIssdate() {
        return issdate;
    }

    /**
     * 设置 发行日
     * @param issdate 发行日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setIssdate(Date issdate) {
        this.issdate = issdate;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
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
     * 取得 面值
     * @return 面值
     */
    public BigDecimal getFacevalue() {
        return facevalue;
    }

    /**
     * 设置 面值
     * @param facevalue 面值
     */
    public void setFacevalue(BigDecimal facevalue) {
        this.facevalue = facevalue;
    }

    /**
     * 取得 发行价格
     * @return 发行价格
     */
    public BigDecimal getIssueprice() {
        return issueprice;
    }

    /**
     * 设置 发行价格
     * @param issueprice 发行价格
     */
    public void setIssueprice(BigDecimal issueprice) {
        this.issueprice = issueprice;
    }

    /**
     * 取得 利息分配方式(只针对固定利率债券)
平均分配 J
按实际天数分配 D

     * @return 利息分配方式(只针对固定利率债券)
平均分配 J
按实际天数分配 D

     */
    public String getIntpayrule() {
        return intpayrule;
    }

    /**
     * 设置 利息分配方式(只针对固定利率债券)
平均分配 J
按实际天数分配 D

     * @param intpayrule 利息分配方式(只针对固定利率债券)
平均分配 J
按实际天数分配 D

     */
    public void setIntpayrule(String intpayrule) {
        this.intpayrule = intpayrule == null ? null : intpayrule.trim();
    }

    /**
     * 取得 付息生成规则(即付息计划推算方法)
起息日向后  VF
到期日向前 MB
首次付息日 FD

     * @return 付息生成规则(即付息计划推算方法)
起息日向后  VF
到期日向前 MB
首次付息日 FD

     */
    public String getSchecalrule() {
        return schecalrule;
    }

    /**
     * 设置 付息生成规则(即付息计划推算方法)
起息日向后  VF
到期日向前 MB
首次付息日 FD

     * @param schecalrule 付息生成规则(即付息计划推算方法)
起息日向后  VF
到期日向前 MB
首次付息日 FD

     */
    public void setSchecalrule(String schecalrule) {
        this.schecalrule = schecalrule == null ? null : schecalrule.trim();
    }

    /**
     * 取得 首次付息日
     * @return 首次付息日
     */
    public Date getFirstrateday() {
        return firstrateday;
    }

    /**
     * 设置 首次付息日
     * @param firstrateday 首次付息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstrateday(Date firstrateday) {
        this.firstrateday = firstrateday;
    }

    /**
     * 取得 营业日准则
向后                 next
向前                 last
调整的向后     adjust
不调整             un

     * @return 营业日准则
向后                 next
向前                 last
调整的向后     adjust
不调整             un

     */
    public String getWorkdayrule() {
        return workdayrule;
    }

    /**
     * 设置 营业日准则
向后                 next
向前                 last
调整的向后     adjust
不调整             un

     * @param workdayrule 营业日准则
向后                 next
向前                 last
调整的向后     adjust
不调整             un

     */
    public void setWorkdayrule(String workdayrule) {
        this.workdayrule = workdayrule == null ? null : workdayrule.trim();
    }

    /**
     * 取得 基准利率生效方式
付息后生效 F
当期生效 D
指定条件生效 Z

     * @return 基准利率生效方式
付息后生效 F
当期生效 D
指定条件生效 Z

     */
    public String getRatevaluetype() {
        return ratevaluetype;
    }

    /**
     * 设置 基准利率生效方式
付息后生效 F
当期生效 D
指定条件生效 Z

     * @param ratevaluetype 基准利率生效方式
付息后生效 F
当期生效 D
指定条件生效 Z

     */
    public void setRatevaluetype(String ratevaluetype) {
        this.ratevaluetype = ratevaluetype == null ? null : ratevaluetype.trim();
    }

    /**
     * 取得 利率生效时期:
计息期有效 Q
计息年度有效 Y

     * @return 利率生效时期:
计息期有效 Q
计息年度有效 Y

     */
    public String getRatevalueperiod() {
        return ratevalueperiod;
    }

    /**
     * 设置 利率生效时期:
计息期有效 Q
计息年度有效 Y

     * @param ratevalueperiod 利率生效时期:
计息期有效 Q
计息年度有效 Y

     */
    public void setRatevalueperiod(String ratevalueperiod) {
        this.ratevalueperiod = ratevalueperiod == null ? null : ratevalueperiod.trim();
    }

    /**
     * 取得 利率生效条件
基准利率变动后固定间隔时间生效 H
付息前指定日期的有效基准利率 Q

     * @return 利率生效条件
基准利率变动后固定间隔时间生效 H
付息前指定日期的有效基准利率 Q

     */
    public String getRatevaluecdtn() {
        return ratevaluecdtn;
    }

    /**
     * 设置 利率生效条件
基准利率变动后固定间隔时间生效 H
付息前指定日期的有效基准利率 Q

     * @param ratevaluecdtn 利率生效条件
基准利率变动后固定间隔时间生效 H
付息前指定日期的有效基准利率 Q

     */
    public void setRatevaluecdtn(String ratevaluecdtn) {
        this.ratevaluecdtn = ratevaluecdtn == null ? null : ratevaluecdtn.trim();
    }

    /**
     * 取得 生效条件时间差
     * @return 生效条件时间差
     */
    public Integer getRatevaluedays() {
        return ratevaluedays;
    }

    /**
     * 设置 生效条件时间差
     * @param ratevaluedays 生效条件时间差
     */
    public void setRatevaluedays(Integer ratevaluedays) {
        this.ratevaluedays = ratevaluedays;
    }

    /**
     * 取得 发行总额
     * @return 发行总额
     */
    public BigDecimal getIssueamt() {
        return issueamt;
    }

    /**
     * 设置 发行总额
     * @param issueamt 发行总额
     */
    public void setIssueamt(BigDecimal issueamt) {
        this.issueamt = issueamt;
    }

    /**
     * 取得 债券类型
国债 00
政策性金融债 01
央行票据 02
普通金融债 03
普通企业债 04
公司债 05
中期 06
短期融资券 07
次级债票据 08
地方政府债券 09
资产支持证券 10

     * @return 债券类型
国债 00
政策性金融债 01
央行票据 02
普通金融债 03
普通企业债 04
公司债 05
中期 06
短期融资券 07
次级债票据 08
地方政府债券 09
资产支持证券 10

     */
    public String getSectype() {
        return sectype;
    }

    /**
     * 设置 债券类型
国债 00
政策性金融债 01
央行票据 02
普通金融债 03
普通企业债 04
公司债 05
中期 06
短期融资券 07
次级债票据 08
地方政府债券 09
资产支持证券 10

     * @param sectype 债券类型
国债 00
政策性金融债 01
央行票据 02
普通金融债 03
普通企业债 04
公司债 05
中期 06
短期融资券 07
次级债票据 08
地方政府债券 09
资产支持证券 10

     */
    public void setSectype(String sectype) {
        this.sectype = sectype == null ? null : sectype.trim();
    }

    /**
     * 取得 有效标识
     * @return 有效标识
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 有效标识
     * @param effectflag 有效标识
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置 null
     * @param sn null
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }
}