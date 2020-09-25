package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfSendOpics extends BaseEntity {
    /**
     * GUID
     */
    private String uuid;

    /**
     * 资产池
     */
    private String assetpool;

    /**
     * 现金流编号：产品代码、资产代码、现金流编号（提前赎回）
     */
    private String cashno;

    /**
     * 业务代码：产品代码、资产代码
     */
    private String businessid;

    /**
     * 发送信息功能模块ID
     */
    private String sendmodule;

    /**
     * 发送方式：ESB
     */
    private String sendmode;

    /**
     * 记录信息接收系统：OPICS
     */
    private String senddirection;

    /**
     * 处理日期
     */
    private Date cdate;

    /**
     * 是否发送OPICS标识：Y是 N否
     */
    private String sendflag;

    /**
     * 业务批次号
     */
    private String sn;

    /**
     * 交易类型
     */
    private String dealtype;

    /**
     * BRANCH
     */
    private String branch;

    /**
     * 交易员
     */
    private String trad;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 到期日
     */
    private String mdate;

    /**
     * 交易对手
     */
    private String cno;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amt;

    /**
     * 利率代码
     */
    private String ratecode;

    /**
     * 计息规则
     */
    private String basis;

    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intrate;

    /**
     * 点差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spread;

    /**
     * 是否修复
     */
    private String brok;

    /**
     * 投资组合
     */
    private String port;

    /**
     * 成本中心
     */
    private String cost;

    /**
     * 交易日
     */
    private String dealdate;

    /**
     * 交易时间
     */
    private String dealtime;

    /**
     * 期限
     */
    private String tenor;

    /**
     * 是否自动复核
     */
    private String verind;

    /**
     * 交易备注
     */
    private String dealtext;

    /**
     * 备注
     */
    private String remark;

    /**
     * RET_DEALNO
     */
    private String retDealno;

    /**
     * RET_BR
     */
    private String retBr;

    /**
     * RET_PRODUCT
     */
    private String retProduct;

    /**
     * RET_PRODTYPE
     */
    private String retProdtype;

    /**
     * 平盘收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 发送次数
     */
    private Integer sendnum;

    /**
     * 发送时间
     */
    private Date sendDate;

    /**
     * 发送状态
     */
    private String sendStatus;

    /**
     * 指令接收状态
     */
    private String orderRecStatus;

    /**
     * 处理状态
     */
    private String orderRetStatus;

    /**
     * 最后发送成功人员
     */
    private String lstsenduser;

    /**
     * 最后发送成功时间
     */
    private Date lstsenddate;

    /**
     * 更新人员
     */
    private String lstmntuser;

    /**
     * 更新时间
     */
    private Date lstmntdate;

    /**
     * 取得 GUID
     * @return GUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 GUID
     * @param uuid GUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
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
     * 取得 现金流编号：产品代码、资产代码、现金流编号（提前赎回）
     * @return 现金流编号：产品代码、资产代码、现金流编号（提前赎回）
     */
    public String getCashno() {
        return cashno;
    }

    /**
     * 设置 现金流编号：产品代码、资产代码、现金流编号（提前赎回）
     * @param cashno 现金流编号：产品代码、资产代码、现金流编号（提前赎回）
     */
    public void setCashno(String cashno) {
        this.cashno = cashno == null ? null : cashno.trim();
    }

    /**
     * 取得 业务代码：产品代码、资产代码
     * @return 业务代码：产品代码、资产代码
     */
    public String getBusinessid() {
        return businessid;
    }

    /**
     * 设置 业务代码：产品代码、资产代码
     * @param businessid 业务代码：产品代码、资产代码
     */
    public void setBusinessid(String businessid) {
        this.businessid = businessid == null ? null : businessid.trim();
    }

    /**
     * 取得 发送信息功能模块ID
     * @return 发送信息功能模块ID
     */
    public String getSendmodule() {
        return sendmodule;
    }

    /**
     * 设置 发送信息功能模块ID
     * @param sendmodule 发送信息功能模块ID
     */
    public void setSendmodule(String sendmodule) {
        this.sendmodule = sendmodule == null ? null : sendmodule.trim();
    }

    /**
     * 取得 发送方式：ESB
     * @return 发送方式：ESB
     */
    public String getSendmode() {
        return sendmode;
    }

    /**
     * 设置 发送方式：ESB
     * @param sendmode 发送方式：ESB
     */
    public void setSendmode(String sendmode) {
        this.sendmode = sendmode == null ? null : sendmode.trim();
    }

    /**
     * 取得 记录信息接收系统：OPICS
     * @return 记录信息接收系统：OPICS
     */
    public String getSenddirection() {
        return senddirection;
    }

    /**
     * 设置 记录信息接收系统：OPICS
     * @param senddirection 记录信息接收系统：OPICS
     */
    public void setSenddirection(String senddirection) {
        this.senddirection = senddirection == null ? null : senddirection.trim();
    }

    /**
     * 取得 处理日期
     * @return 处理日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 处理日期
     * @param cdate 处理日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 是否发送OPICS标识：Y是 N否
     * @return 是否发送OPICS标识：Y是 N否
     */
    public String getSendflag() {
        return sendflag;
    }

    /**
     * 设置 是否发送OPICS标识：Y是 N否
     * @param sendflag 是否发送OPICS标识：Y是 N否
     */
    public void setSendflag(String sendflag) {
        this.sendflag = sendflag == null ? null : sendflag.trim();
    }

    /**
     * 取得 业务批次号
     * @return 业务批次号
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置 业务批次号
     * @param sn 业务批次号
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    /**
     * 取得 交易类型
     * @return 交易类型
     */
    public String getDealtype() {
        return dealtype;
    }

    /**
     * 设置 交易类型
     * @param dealtype 交易类型
     */
    public void setDealtype(String dealtype) {
        this.dealtype = dealtype == null ? null : dealtype.trim();
    }

    /**
     * 取得 BRANCH
     * @return BRANCH
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 BRANCH
     * @param branch BRANCH
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * 取得 交易员
     * @return 交易员
     */
    public String getTrad() {
        return trad;
    }

    /**
     * 设置 交易员
     * @param trad 交易员
     */
    public void setTrad(String trad) {
        this.trad = trad == null ? null : trad.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate == null ? null : vdate.trim();
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate == null ? null : mdate.trim();
    }

    /**
     * 取得 交易对手
     * @return 交易对手
     */
    public String getCno() {
        return cno;
    }

    /**
     * 设置 交易对手
     * @param cno 交易对手
     */
    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
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
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 金额
     * @param amt 金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
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
     * 取得 计息规则
     * @return 计息规则
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息规则
     * @param basis 计息规则
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 利率
     * @return 利率
     */
    public BigDecimal getIntrate() {
        return intrate;
    }

    /**
     * 设置 利率
     * @param intrate 利率
     */
    public void setIntrate(BigDecimal intrate) {
        this.intrate = intrate;
    }

    /**
     * 取得 点差
     * @return 点差
     */
    public BigDecimal getSpread() {
        return spread;
    }

    /**
     * 设置 点差
     * @param spread 点差
     */
    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    /**
     * 取得 是否修复
     * @return 是否修复
     */
    public String getBrok() {
        return brok;
    }

    /**
     * 设置 是否修复
     * @param brok 是否修复
     */
    public void setBrok(String brok) {
        this.brok = brok == null ? null : brok.trim();
    }

    /**
     * 取得 投资组合
     * @return 投资组合
     */
    public String getPort() {
        return port;
    }

    /**
     * 设置 投资组合
     * @param port 投资组合
     */
    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    /**
     * 取得 成本中心
     * @return 成本中心
     */
    public String getCost() {
        return cost;
    }

    /**
     * 设置 成本中心
     * @param cost 成本中心
     */
    public void setCost(String cost) {
        this.cost = cost == null ? null : cost.trim();
    }

    /**
     * 取得 交易日
     * @return 交易日
     */
    public String getDealdate() {
        return dealdate;
    }

    /**
     * 设置 交易日
     * @param dealdate 交易日
     */
    public void setDealdate(String dealdate) {
        this.dealdate = dealdate == null ? null : dealdate.trim();
    }

    /**
     * 取得 交易时间
     * @return 交易时间
     */
    public String getDealtime() {
        return dealtime;
    }

    /**
     * 设置 交易时间
     * @param dealtime 交易时间
     */
    public void setDealtime(String dealtime) {
        this.dealtime = dealtime == null ? null : dealtime.trim();
    }

    /**
     * 取得 期限
     * @return 期限
     */
    public String getTenor() {
        return tenor;
    }

    /**
     * 设置 期限
     * @param tenor 期限
     */
    public void setTenor(String tenor) {
        this.tenor = tenor == null ? null : tenor.trim();
    }

    /**
     * 取得 是否自动复核
     * @return 是否自动复核
     */
    public String getVerind() {
        return verind;
    }

    /**
     * 设置 是否自动复核
     * @param verind 是否自动复核
     */
    public void setVerind(String verind) {
        this.verind = verind == null ? null : verind.trim();
    }

    /**
     * 取得 交易备注
     * @return 交易备注
     */
    public String getDealtext() {
        return dealtext;
    }

    /**
     * 设置 交易备注
     * @param dealtext 交易备注
     */
    public void setDealtext(String dealtext) {
        this.dealtext = dealtext == null ? null : dealtext.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 RET_DEALNO
     * @return RET_DEALNO
     */
    public String getRetDealno() {
        return retDealno;
    }

    /**
     * 设置 RET_DEALNO
     * @param retDealno RET_DEALNO
     */
    public void setRetDealno(String retDealno) {
        this.retDealno = retDealno == null ? null : retDealno.trim();
    }

    /**
     * 取得 RET_BR
     * @return RET_BR
     */
    public String getRetBr() {
        return retBr;
    }

    /**
     * 设置 RET_BR
     * @param retBr RET_BR
     */
    public void setRetBr(String retBr) {
        this.retBr = retBr == null ? null : retBr.trim();
    }

    /**
     * 取得 RET_PRODUCT
     * @return RET_PRODUCT
     */
    public String getRetProduct() {
        return retProduct;
    }

    /**
     * 设置 RET_PRODUCT
     * @param retProduct RET_PRODUCT
     */
    public void setRetProduct(String retProduct) {
        this.retProduct = retProduct == null ? null : retProduct.trim();
    }

    /**
     * 取得 RET_PRODTYPE
     * @return RET_PRODTYPE
     */
    public String getRetProdtype() {
        return retProdtype;
    }

    /**
     * 设置 RET_PRODTYPE
     * @param retProdtype RET_PRODTYPE
     */
    public void setRetProdtype(String retProdtype) {
        this.retProdtype = retProdtype == null ? null : retProdtype.trim();
    }

    /**
     * 取得 平盘收益率
     * @return 平盘收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 平盘收益率
     * @param yield 平盘收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 发送次数
     * @return 发送次数
     */
    public Integer getSendnum() {
        return sendnum;
    }

    /**
     * 设置 发送次数
     * @param sendnum 发送次数
     */
    public void setSendnum(Integer sendnum) {
        this.sendnum = sendnum;
    }

    /**
     * 取得 发送时间
     * @return 发送时间
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * 设置 发送时间
     * @param sendDate 发送时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * 取得 发送状态
     * @return 发送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 发送状态
     * @param sendStatus 发送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 指令接收状态
     * @return 指令接收状态
     */
    public String getOrderRecStatus() {
        return orderRecStatus;
    }

    /**
     * 设置 指令接收状态
     * @param orderRecStatus 指令接收状态
     */
    public void setOrderRecStatus(String orderRecStatus) {
        this.orderRecStatus = orderRecStatus == null ? null : orderRecStatus.trim();
    }

    /**
     * 取得 处理状态
     * @return 处理状态
     */
    public String getOrderRetStatus() {
        return orderRetStatus;
    }

    /**
     * 设置 处理状态
     * @param orderRetStatus 处理状态
     */
    public void setOrderRetStatus(String orderRetStatus) {
        this.orderRetStatus = orderRetStatus == null ? null : orderRetStatus.trim();
    }

    /**
     * 取得 最后发送成功人员
     * @return 最后发送成功人员
     */
    public String getLstsenduser() {
        return lstsenduser;
    }

    /**
     * 设置 最后发送成功人员
     * @param lstsenduser 最后发送成功人员
     */
    public void setLstsenduser(String lstsenduser) {
        this.lstsenduser = lstsenduser == null ? null : lstsenduser.trim();
    }

    /**
     * 取得 最后发送成功时间
     * @return 最后发送成功时间
     */
    public Date getLstsenddate() {
        return lstsenddate;
    }

    /**
     * 设置 最后发送成功时间
     * @param lstsenddate 最后发送成功时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstsenddate(Date lstsenddate) {
        this.lstsenddate = lstsenddate;
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
}