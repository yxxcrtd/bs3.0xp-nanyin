package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCstpTrade extends BaseEntity {
    /**
     * ????
     */
    private String msgtype;

    /**
     * ?????
     */
    private String msgseqnum;

    /**
     * ????
     */
    private String sendingtime;

    /**
     * ???????
     */
    private String onbehalfofcompid;

    /**
     * ????
     */
    private String datasource;

    /**
     * ???????BOND?REPO?
     */
    private String businesstype;

    /**
     * ????
     */
    private String execid;

    /**
     * ?????? ???????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastqty;

    /**
     * ???? ???%????4?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal price;

    /**
     * ????/??ID
     */
    private String securityid;

    /**
     * ????
     */
    private String side;

    /**
     * ????
     */
    private String symbol;

    /**
     * ????
     */
    private String text;

    /**
     * ??????
     */
    private String transacttime;

    /**
     * ???
     */
    private String settldate;

    /**
     * ????
     */
    private String tradedate;

    /**
     * ???? ???????????????????????????????
     */
    private String quoteid;

    /**
     * ???? F-?? 4-?? 5-?? 101-??
     */
    private String exectype;

    /**
     * ?????
     */
    private String settldate2;

    /**
     * ?????? 0-DVP 4-PUD 5-DUP
     */
    private String deliverytype;

    /**
     * ?????? ????????2?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accruedinteresttotalamt;

    /**
     * ?????? ???????
     */
    private Integer cashholdingdays;

    /**
     * ?????? 0-DVP 4-PUD 5-DUP
     */
    private String deliverytype2;

    /**
     * ????: 9-?????
     */
    private String marketindicator;

    /**
     * ??????
     */
    private String remarkindicator;

    /**
     * ??????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settlcurramt2;

    /**
     * ???? ????????2?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradecashamt;

    /**
     * ???? ???????
     */
    private Integer tradelimitdays;

    /**
     * ????
     */
    private String tradetime;

    /**
     * ?????? 0-???? 2-??????
     */
    private String datacategoryindicator;

    /**
     * ??????
     */
    private String ourinstitutioncode;

    /**
     * 119-??? 120-???
     */
    private String ourinstitutionrole;

    /**
     * ?????
     */
    private String ourtelephone;

    /**
     * ?????
     */
    private String ourtradername;

    /**
     * ??
     */
    private String ouraddress;

    /**
     * ???????
     */
    private String ourtraderchinesename;

    /**
     * ??????
     */
    private String ourinstitutionchineseshortname;

    /**
     * ??????
     */
    private String ourinstitutionchinesefullname;

    /**
     * ????
     */
    private String ourinstitutionenglishshortname;

    /**
     * ??????
     */
    private String ourcapitalaccountname;

    /**
     * ?????
     */
    private String ourcapitalbankname;

    /**
     * ????
     */
    private String ourcapitalaccountnumber;

    /**
     * ??????
     */
    private String ourpaymentsystemcode;

    /**
     * ??????
     */
    private String ourdepositaccountname;

    /**
     * ????
     */
    private String ourdepositinstitution;

    /**
     * ????
     */
    private String ourdepositaccountnumber;

    /**
     * ??????
     */
    private String ourtrademembergroup;

    /**
     * ??????
     */
    private String counterinstitutioncode;

    /**
     * ???????
     */
    private String counterinstitutionrole;

    /**
     * ???????
     */
    private String countertelephone;

    /**
     * ???????
     */
    private String countertradername;

    /**
     * ????
     */
    private String counteraddress;

    /**
     * ?????????
     */
    private String countertraderchinesename;

    /**
     * ????????
     */
    private String institutionchineseshortname;

    /**
     * ????????
     */
    private String institutionchinesefullname;

    /**
     * ??????
     */
    private String institutionenglishshortname;

    /**
     * ????????
     */
    private String countercapitalaccountname;

    /**
     * ???????
     */
    private String countercapitalbankname;

    /**
     * ??????
     */
    private String countercapitalaccountnumber;

    /**
     * ????????
     */
    private String counterpaymentsystemcode;

    /**
     * ????????
     */
    private String counterdepositaccountname;

    /**
     * ??????
     */
    private String counterdepositinstitution;

    /**
     * ??????
     */
    private String counterdepositaccountnumber;

    /**
     * ????????
     */
    private String countertrademembergroup;

    /**
     * ????
     */
    private String settlcurrency;

    /**
     * ????
     */
    private String orderid;

    /**
     * ???
     */
    private String termtomaturity;

    /**
     * ????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settlcurramt;

    /**
     * ???? ????????5?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accruedinterestamt;

    /**
     * ?? ????????4?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dirtyprice;

    /**
     * ???? 1-????? 2-???? 3-??????
     */
    private String tradetype;

    /**
     * ????? N-?
     */
    private String marginrequired;

    /**
     * null
     */
    private String marginchangeindicator;

    /**
     * ????
     */
    private String underlyingsecurityid;

    /**
     * ????2
     */
    private String underlyingsymbol;

    /**
     * ??????? ???????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingqty;

    /**
     * ???? ????????4?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingpx;

    /**
     * ???? ????????4?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingpx2;

    /**
     * ?????? ????????5?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingaccruedinterestamt;

    /**
     * ?????? ????????5?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingaccruedinterestamt2;

    /**
     * ???? ????????4?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingdirtyprice;

    /**
     * ???? ????????4?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingdirtyprice2;

    /**
     * ????? ???%????4?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingyield;

    /**
     * ????? ???%????4?
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingyield2;

    /**
     * ??????
     */
    private Date updatetime;

    /**
     * ?????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal strikeyield;

    /**
     * ?????2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield2;

    /**
     * ????
     */
    private String trademethod;

    /**
     * ????
     */
    private String settltype;

    /**
     * ??????????
     */
    private String dealtranstype;

    /**
     * ???????
     */
    private String clordid;

    /**
     * ????
     */
    private String contingencyindicator;

    /**
     * ??????
     */
    private String reference;

    /**
     * ????  6-???? 13-????
     */
    private String clearingmethod;

    /**
     * ABS???????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal principal;

    /**
     * ABS??????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalprincipal;

    /**
     * Y-?? N-ABS?
     */
    private String cashbondindicator;

    /**
     * 取得 ????
     * @return ????
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * 设置 ????
     * @param msgtype ????
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public String getMsgseqnum() {
        return msgseqnum;
    }

    /**
     * 设置 ?????
     * @param msgseqnum ?????
     */
    public void setMsgseqnum(String msgseqnum) {
        this.msgseqnum = msgseqnum == null ? null : msgseqnum.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getSendingtime() {
        return sendingtime;
    }

    /**
     * 设置 ????
     * @param sendingtime ????
     */
    public void setSendingtime(String sendingtime) {
        this.sendingtime = sendingtime == null ? null : sendingtime.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getOnbehalfofcompid() {
        return onbehalfofcompid;
    }

    /**
     * 设置 ???????
     * @param onbehalfofcompid ???????
     */
    public void setOnbehalfofcompid(String onbehalfofcompid) {
        this.onbehalfofcompid = onbehalfofcompid == null ? null : onbehalfofcompid.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getDatasource() {
        return datasource;
    }

    /**
     * 设置 ????
     * @param datasource ????
     */
    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    /**
     * 取得 ???????BOND?REPO?
     * @return ???????BOND?REPO?
     */
    public String getBusinesstype() {
        return businesstype;
    }

    /**
     * 设置 ???????BOND?REPO?
     * @param businesstype ???????BOND?REPO?
     */
    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getExecid() {
        return execid;
    }

    /**
     * 设置 ????
     * @param execid ????
     */
    public void setExecid(String execid) {
        this.execid = execid == null ? null : execid.trim();
    }

    /**
     * 取得 ?????? ???????
     * @return ?????? ???????
     */
    public BigDecimal getLastqty() {
        return lastqty;
    }

    /**
     * 设置 ?????? ???????
     * @param lastqty ?????? ???????
     */
    public void setLastqty(BigDecimal lastqty) {
        this.lastqty = lastqty;
    }

    /**
     * 取得 ???? ???%????4?
     * @return ???? ???%????4?
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置 ???? ???%????4?
     * @param price ???? ???%????4?
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 取得 ????/??ID
     * @return ????/??ID
     */
    public String getSecurityid() {
        return securityid;
    }

    /**
     * 设置 ????/??ID
     * @param securityid ????/??ID
     */
    public void setSecurityid(String securityid) {
        this.securityid = securityid == null ? null : securityid.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getSide() {
        return side;
    }

    /**
     * 设置 ????
     * @param side ????
     */
    public void setSide(String side) {
        this.side = side == null ? null : side.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * 设置 ????
     * @param symbol ????
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getText() {
        return text;
    }

    /**
     * 设置 ????
     * @param text ????
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getTransacttime() {
        return transacttime;
    }

    /**
     * 设置 ??????
     * @param transacttime ??????
     */
    public void setTransacttime(String transacttime) {
        this.transacttime = transacttime == null ? null : transacttime.trim();
    }

    /**
     * 取得 ???
     * @return ???
     */
    public String getSettldate() {
        return settldate;
    }

    /**
     * 设置 ???
     * @param settldate ???
     */
    public void setSettldate(String settldate) {
        this.settldate = settldate == null ? null : settldate.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getTradedate() {
        return tradedate;
    }

    /**
     * 设置 ????
     * @param tradedate ????
     */
    public void setTradedate(String tradedate) {
        this.tradedate = tradedate == null ? null : tradedate.trim();
    }

    /**
     * 取得 ???? ???????????????????????????????
     * @return ???? ???????????????????????????????
     */
    public String getQuoteid() {
        return quoteid;
    }

    /**
     * 设置 ???? ???????????????????????????????
     * @param quoteid ???? ???????????????????????????????
     */
    public void setQuoteid(String quoteid) {
        this.quoteid = quoteid == null ? null : quoteid.trim();
    }

    /**
     * 取得 ???? F-?? 4-?? 5-?? 101-??
     * @return ???? F-?? 4-?? 5-?? 101-??
     */
    public String getExectype() {
        return exectype;
    }

    /**
     * 设置 ???? F-?? 4-?? 5-?? 101-??
     * @param exectype ???? F-?? 4-?? 5-?? 101-??
     */
    public void setExectype(String exectype) {
        this.exectype = exectype == null ? null : exectype.trim();
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public String getSettldate2() {
        return settldate2;
    }

    /**
     * 设置 ?????
     * @param settldate2 ?????
     */
    public void setSettldate2(String settldate2) {
        this.settldate2 = settldate2 == null ? null : settldate2.trim();
    }

    /**
     * 取得 ?????? 0-DVP 4-PUD 5-DUP
     * @return ?????? 0-DVP 4-PUD 5-DUP
     */
    public String getDeliverytype() {
        return deliverytype;
    }

    /**
     * 设置 ?????? 0-DVP 4-PUD 5-DUP
     * @param deliverytype ?????? 0-DVP 4-PUD 5-DUP
     */
    public void setDeliverytype(String deliverytype) {
        this.deliverytype = deliverytype == null ? null : deliverytype.trim();
    }

    /**
     * 取得 ?????? ????????2?
     * @return ?????? ????????2?
     */
    public BigDecimal getAccruedinteresttotalamt() {
        return accruedinteresttotalamt;
    }

    /**
     * 设置 ?????? ????????2?
     * @param accruedinteresttotalamt ?????? ????????2?
     */
    public void setAccruedinteresttotalamt(BigDecimal accruedinteresttotalamt) {
        this.accruedinteresttotalamt = accruedinteresttotalamt;
    }

    /**
     * 取得 ?????? ???????
     * @return ?????? ???????
     */
    public Integer getCashholdingdays() {
        return cashholdingdays;
    }

    /**
     * 设置 ?????? ???????
     * @param cashholdingdays ?????? ???????
     */
    public void setCashholdingdays(Integer cashholdingdays) {
        this.cashholdingdays = cashholdingdays;
    }

    /**
     * 取得 ?????? 0-DVP 4-PUD 5-DUP
     * @return ?????? 0-DVP 4-PUD 5-DUP
     */
    public String getDeliverytype2() {
        return deliverytype2;
    }

    /**
     * 设置 ?????? 0-DVP 4-PUD 5-DUP
     * @param deliverytype2 ?????? 0-DVP 4-PUD 5-DUP
     */
    public void setDeliverytype2(String deliverytype2) {
        this.deliverytype2 = deliverytype2 == null ? null : deliverytype2.trim();
    }

    /**
     * 取得 ????: 9-?????
     * @return ????: 9-?????
     */
    public String getMarketindicator() {
        return marketindicator;
    }

    /**
     * 设置 ????: 9-?????
     * @param marketindicator ????: 9-?????
     */
    public void setMarketindicator(String marketindicator) {
        this.marketindicator = marketindicator == null ? null : marketindicator.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getRemarkindicator() {
        return remarkindicator;
    }

    /**
     * 设置 ??????
     * @param remarkindicator ??????
     */
    public void setRemarkindicator(String remarkindicator) {
        this.remarkindicator = remarkindicator == null ? null : remarkindicator.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public BigDecimal getSettlcurramt2() {
        return settlcurramt2;
    }

    /**
     * 设置 ??????
     * @param settlcurramt2 ??????
     */
    public void setSettlcurramt2(BigDecimal settlcurramt2) {
        this.settlcurramt2 = settlcurramt2;
    }

    /**
     * 取得 ???? ????????2?
     * @return ???? ????????2?
     */
    public BigDecimal getTradecashamt() {
        return tradecashamt;
    }

    /**
     * 设置 ???? ????????2?
     * @param tradecashamt ???? ????????2?
     */
    public void setTradecashamt(BigDecimal tradecashamt) {
        this.tradecashamt = tradecashamt;
    }

    /**
     * 取得 ???? ???????
     * @return ???? ???????
     */
    public Integer getTradelimitdays() {
        return tradelimitdays;
    }

    /**
     * 设置 ???? ???????
     * @param tradelimitdays ???? ???????
     */
    public void setTradelimitdays(Integer tradelimitdays) {
        this.tradelimitdays = tradelimitdays;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getTradetime() {
        return tradetime;
    }

    /**
     * 设置 ????
     * @param tradetime ????
     */
    public void setTradetime(String tradetime) {
        this.tradetime = tradetime == null ? null : tradetime.trim();
    }

    /**
     * 取得 ?????? 0-???? 2-??????
     * @return ?????? 0-???? 2-??????
     */
    public String getDatacategoryindicator() {
        return datacategoryindicator;
    }

    /**
     * 设置 ?????? 0-???? 2-??????
     * @param datacategoryindicator ?????? 0-???? 2-??????
     */
    public void setDatacategoryindicator(String datacategoryindicator) {
        this.datacategoryindicator = datacategoryindicator == null ? null : datacategoryindicator.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getOurinstitutioncode() {
        return ourinstitutioncode;
    }

    /**
     * 设置 ??????
     * @param ourinstitutioncode ??????
     */
    public void setOurinstitutioncode(String ourinstitutioncode) {
        this.ourinstitutioncode = ourinstitutioncode == null ? null : ourinstitutioncode.trim();
    }

    /**
     * 取得 119-??? 120-???
     * @return 119-??? 120-???
     */
    public String getOurinstitutionrole() {
        return ourinstitutionrole;
    }

    /**
     * 设置 119-??? 120-???
     * @param ourinstitutionrole 119-??? 120-???
     */
    public void setOurinstitutionrole(String ourinstitutionrole) {
        this.ourinstitutionrole = ourinstitutionrole == null ? null : ourinstitutionrole.trim();
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public String getOurtelephone() {
        return ourtelephone;
    }

    /**
     * 设置 ?????
     * @param ourtelephone ?????
     */
    public void setOurtelephone(String ourtelephone) {
        this.ourtelephone = ourtelephone == null ? null : ourtelephone.trim();
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public String getOurtradername() {
        return ourtradername;
    }

    /**
     * 设置 ?????
     * @param ourtradername ?????
     */
    public void setOurtradername(String ourtradername) {
        this.ourtradername = ourtradername == null ? null : ourtradername.trim();
    }

    /**
     * 取得 ??
     * @return ??
     */
    public String getOuraddress() {
        return ouraddress;
    }

    /**
     * 设置 ??
     * @param ouraddress ??
     */
    public void setOuraddress(String ouraddress) {
        this.ouraddress = ouraddress == null ? null : ouraddress.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getOurtraderchinesename() {
        return ourtraderchinesename;
    }

    /**
     * 设置 ???????
     * @param ourtraderchinesename ???????
     */
    public void setOurtraderchinesename(String ourtraderchinesename) {
        this.ourtraderchinesename = ourtraderchinesename == null ? null : ourtraderchinesename.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getOurinstitutionchineseshortname() {
        return ourinstitutionchineseshortname;
    }

    /**
     * 设置 ??????
     * @param ourinstitutionchineseshortname ??????
     */
    public void setOurinstitutionchineseshortname(String ourinstitutionchineseshortname) {
        this.ourinstitutionchineseshortname = ourinstitutionchineseshortname == null ? null : ourinstitutionchineseshortname.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getOurinstitutionchinesefullname() {
        return ourinstitutionchinesefullname;
    }

    /**
     * 设置 ??????
     * @param ourinstitutionchinesefullname ??????
     */
    public void setOurinstitutionchinesefullname(String ourinstitutionchinesefullname) {
        this.ourinstitutionchinesefullname = ourinstitutionchinesefullname == null ? null : ourinstitutionchinesefullname.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getOurinstitutionenglishshortname() {
        return ourinstitutionenglishshortname;
    }

    /**
     * 设置 ????
     * @param ourinstitutionenglishshortname ????
     */
    public void setOurinstitutionenglishshortname(String ourinstitutionenglishshortname) {
        this.ourinstitutionenglishshortname = ourinstitutionenglishshortname == null ? null : ourinstitutionenglishshortname.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getOurcapitalaccountname() {
        return ourcapitalaccountname;
    }

    /**
     * 设置 ??????
     * @param ourcapitalaccountname ??????
     */
    public void setOurcapitalaccountname(String ourcapitalaccountname) {
        this.ourcapitalaccountname = ourcapitalaccountname == null ? null : ourcapitalaccountname.trim();
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public String getOurcapitalbankname() {
        return ourcapitalbankname;
    }

    /**
     * 设置 ?????
     * @param ourcapitalbankname ?????
     */
    public void setOurcapitalbankname(String ourcapitalbankname) {
        this.ourcapitalbankname = ourcapitalbankname == null ? null : ourcapitalbankname.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getOurcapitalaccountnumber() {
        return ourcapitalaccountnumber;
    }

    /**
     * 设置 ????
     * @param ourcapitalaccountnumber ????
     */
    public void setOurcapitalaccountnumber(String ourcapitalaccountnumber) {
        this.ourcapitalaccountnumber = ourcapitalaccountnumber == null ? null : ourcapitalaccountnumber.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getOurpaymentsystemcode() {
        return ourpaymentsystemcode;
    }

    /**
     * 设置 ??????
     * @param ourpaymentsystemcode ??????
     */
    public void setOurpaymentsystemcode(String ourpaymentsystemcode) {
        this.ourpaymentsystemcode = ourpaymentsystemcode == null ? null : ourpaymentsystemcode.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getOurdepositaccountname() {
        return ourdepositaccountname;
    }

    /**
     * 设置 ??????
     * @param ourdepositaccountname ??????
     */
    public void setOurdepositaccountname(String ourdepositaccountname) {
        this.ourdepositaccountname = ourdepositaccountname == null ? null : ourdepositaccountname.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getOurdepositinstitution() {
        return ourdepositinstitution;
    }

    /**
     * 设置 ????
     * @param ourdepositinstitution ????
     */
    public void setOurdepositinstitution(String ourdepositinstitution) {
        this.ourdepositinstitution = ourdepositinstitution == null ? null : ourdepositinstitution.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getOurdepositaccountnumber() {
        return ourdepositaccountnumber;
    }

    /**
     * 设置 ????
     * @param ourdepositaccountnumber ????
     */
    public void setOurdepositaccountnumber(String ourdepositaccountnumber) {
        this.ourdepositaccountnumber = ourdepositaccountnumber == null ? null : ourdepositaccountnumber.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getOurtrademembergroup() {
        return ourtrademembergroup;
    }

    /**
     * 设置 ??????
     * @param ourtrademembergroup ??????
     */
    public void setOurtrademembergroup(String ourtrademembergroup) {
        this.ourtrademembergroup = ourtrademembergroup == null ? null : ourtrademembergroup.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getCounterinstitutioncode() {
        return counterinstitutioncode;
    }

    /**
     * 设置 ??????
     * @param counterinstitutioncode ??????
     */
    public void setCounterinstitutioncode(String counterinstitutioncode) {
        this.counterinstitutioncode = counterinstitutioncode == null ? null : counterinstitutioncode.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getCounterinstitutionrole() {
        return counterinstitutionrole;
    }

    /**
     * 设置 ???????
     * @param counterinstitutionrole ???????
     */
    public void setCounterinstitutionrole(String counterinstitutionrole) {
        this.counterinstitutionrole = counterinstitutionrole == null ? null : counterinstitutionrole.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getCountertelephone() {
        return countertelephone;
    }

    /**
     * 设置 ???????
     * @param countertelephone ???????
     */
    public void setCountertelephone(String countertelephone) {
        this.countertelephone = countertelephone == null ? null : countertelephone.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getCountertradername() {
        return countertradername;
    }

    /**
     * 设置 ???????
     * @param countertradername ???????
     */
    public void setCountertradername(String countertradername) {
        this.countertradername = countertradername == null ? null : countertradername.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getCounteraddress() {
        return counteraddress;
    }

    /**
     * 设置 ????
     * @param counteraddress ????
     */
    public void setCounteraddress(String counteraddress) {
        this.counteraddress = counteraddress == null ? null : counteraddress.trim();
    }

    /**
     * 取得 ?????????
     * @return ?????????
     */
    public String getCountertraderchinesename() {
        return countertraderchinesename;
    }

    /**
     * 设置 ?????????
     * @param countertraderchinesename ?????????
     */
    public void setCountertraderchinesename(String countertraderchinesename) {
        this.countertraderchinesename = countertraderchinesename == null ? null : countertraderchinesename.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getInstitutionchineseshortname() {
        return institutionchineseshortname;
    }

    /**
     * 设置 ????????
     * @param institutionchineseshortname ????????
     */
    public void setInstitutionchineseshortname(String institutionchineseshortname) {
        this.institutionchineseshortname = institutionchineseshortname == null ? null : institutionchineseshortname.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getInstitutionchinesefullname() {
        return institutionchinesefullname;
    }

    /**
     * 设置 ????????
     * @param institutionchinesefullname ????????
     */
    public void setInstitutionchinesefullname(String institutionchinesefullname) {
        this.institutionchinesefullname = institutionchinesefullname == null ? null : institutionchinesefullname.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getInstitutionenglishshortname() {
        return institutionenglishshortname;
    }

    /**
     * 设置 ??????
     * @param institutionenglishshortname ??????
     */
    public void setInstitutionenglishshortname(String institutionenglishshortname) {
        this.institutionenglishshortname = institutionenglishshortname == null ? null : institutionenglishshortname.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getCountercapitalaccountname() {
        return countercapitalaccountname;
    }

    /**
     * 设置 ????????
     * @param countercapitalaccountname ????????
     */
    public void setCountercapitalaccountname(String countercapitalaccountname) {
        this.countercapitalaccountname = countercapitalaccountname == null ? null : countercapitalaccountname.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getCountercapitalbankname() {
        return countercapitalbankname;
    }

    /**
     * 设置 ???????
     * @param countercapitalbankname ???????
     */
    public void setCountercapitalbankname(String countercapitalbankname) {
        this.countercapitalbankname = countercapitalbankname == null ? null : countercapitalbankname.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getCountercapitalaccountnumber() {
        return countercapitalaccountnumber;
    }

    /**
     * 设置 ??????
     * @param countercapitalaccountnumber ??????
     */
    public void setCountercapitalaccountnumber(String countercapitalaccountnumber) {
        this.countercapitalaccountnumber = countercapitalaccountnumber == null ? null : countercapitalaccountnumber.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getCounterpaymentsystemcode() {
        return counterpaymentsystemcode;
    }

    /**
     * 设置 ????????
     * @param counterpaymentsystemcode ????????
     */
    public void setCounterpaymentsystemcode(String counterpaymentsystemcode) {
        this.counterpaymentsystemcode = counterpaymentsystemcode == null ? null : counterpaymentsystemcode.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getCounterdepositaccountname() {
        return counterdepositaccountname;
    }

    /**
     * 设置 ????????
     * @param counterdepositaccountname ????????
     */
    public void setCounterdepositaccountname(String counterdepositaccountname) {
        this.counterdepositaccountname = counterdepositaccountname == null ? null : counterdepositaccountname.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getCounterdepositinstitution() {
        return counterdepositinstitution;
    }

    /**
     * 设置 ??????
     * @param counterdepositinstitution ??????
     */
    public void setCounterdepositinstitution(String counterdepositinstitution) {
        this.counterdepositinstitution = counterdepositinstitution == null ? null : counterdepositinstitution.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getCounterdepositaccountnumber() {
        return counterdepositaccountnumber;
    }

    /**
     * 设置 ??????
     * @param counterdepositaccountnumber ??????
     */
    public void setCounterdepositaccountnumber(String counterdepositaccountnumber) {
        this.counterdepositaccountnumber = counterdepositaccountnumber == null ? null : counterdepositaccountnumber.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getCountertrademembergroup() {
        return countertrademembergroup;
    }

    /**
     * 设置 ????????
     * @param countertrademembergroup ????????
     */
    public void setCountertrademembergroup(String countertrademembergroup) {
        this.countertrademembergroup = countertrademembergroup == null ? null : countertrademembergroup.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getSettlcurrency() {
        return settlcurrency;
    }

    /**
     * 设置 ????
     * @param settlcurrency ????
     */
    public void setSettlcurrency(String settlcurrency) {
        this.settlcurrency = settlcurrency == null ? null : settlcurrency.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * 设置 ????
     * @param orderid ????
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    /**
     * 取得 ???
     * @return ???
     */
    public String getTermtomaturity() {
        return termtomaturity;
    }

    /**
     * 设置 ???
     * @param termtomaturity ???
     */
    public void setTermtomaturity(String termtomaturity) {
        this.termtomaturity = termtomaturity == null ? null : termtomaturity.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public BigDecimal getSettlcurramt() {
        return settlcurramt;
    }

    /**
     * 设置 ????
     * @param settlcurramt ????
     */
    public void setSettlcurramt(BigDecimal settlcurramt) {
        this.settlcurramt = settlcurramt;
    }

    /**
     * 取得 ???? ????????5?
     * @return ???? ????????5?
     */
    public BigDecimal getAccruedinterestamt() {
        return accruedinterestamt;
    }

    /**
     * 设置 ???? ????????5?
     * @param accruedinterestamt ???? ????????5?
     */
    public void setAccruedinterestamt(BigDecimal accruedinterestamt) {
        this.accruedinterestamt = accruedinterestamt;
    }

    /**
     * 取得 ?? ????????4?
     * @return ?? ????????4?
     */
    public BigDecimal getDirtyprice() {
        return dirtyprice;
    }

    /**
     * 设置 ?? ????????4?
     * @param dirtyprice ?? ????????4?
     */
    public void setDirtyprice(BigDecimal dirtyprice) {
        this.dirtyprice = dirtyprice;
    }

    /**
     * 取得 ???? 1-????? 2-???? 3-??????
     * @return ???? 1-????? 2-???? 3-??????
     */
    public String getTradetype() {
        return tradetype;
    }

    /**
     * 设置 ???? 1-????? 2-???? 3-??????
     * @param tradetype ???? 1-????? 2-???? 3-??????
     */
    public void setTradetype(String tradetype) {
        this.tradetype = tradetype == null ? null : tradetype.trim();
    }

    /**
     * 取得 ????? N-?
     * @return ????? N-?
     */
    public String getMarginrequired() {
        return marginrequired;
    }

    /**
     * 设置 ????? N-?
     * @param marginrequired ????? N-?
     */
    public void setMarginrequired(String marginrequired) {
        this.marginrequired = marginrequired == null ? null : marginrequired.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getMarginchangeindicator() {
        return marginchangeindicator;
    }

    /**
     * 设置 null
     * @param marginchangeindicator null
     */
    public void setMarginchangeindicator(String marginchangeindicator) {
        this.marginchangeindicator = marginchangeindicator == null ? null : marginchangeindicator.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getUnderlyingsecurityid() {
        return underlyingsecurityid;
    }

    /**
     * 设置 ????
     * @param underlyingsecurityid ????
     */
    public void setUnderlyingsecurityid(String underlyingsecurityid) {
        this.underlyingsecurityid = underlyingsecurityid == null ? null : underlyingsecurityid.trim();
    }

    /**
     * 取得 ????2
     * @return ????2
     */
    public String getUnderlyingsymbol() {
        return underlyingsymbol;
    }

    /**
     * 设置 ????2
     * @param underlyingsymbol ????2
     */
    public void setUnderlyingsymbol(String underlyingsymbol) {
        this.underlyingsymbol = underlyingsymbol == null ? null : underlyingsymbol.trim();
    }

    /**
     * 取得 ??????? ???????
     * @return ??????? ???????
     */
    public BigDecimal getUnderlyingqty() {
        return underlyingqty;
    }

    /**
     * 设置 ??????? ???????
     * @param underlyingqty ??????? ???????
     */
    public void setUnderlyingqty(BigDecimal underlyingqty) {
        this.underlyingqty = underlyingqty;
    }

    /**
     * 取得 ???? ????????4?
     * @return ???? ????????4?
     */
    public BigDecimal getUnderlyingpx() {
        return underlyingpx;
    }

    /**
     * 设置 ???? ????????4?
     * @param underlyingpx ???? ????????4?
     */
    public void setUnderlyingpx(BigDecimal underlyingpx) {
        this.underlyingpx = underlyingpx;
    }

    /**
     * 取得 ???? ????????4?
     * @return ???? ????????4?
     */
    public BigDecimal getUnderlyingpx2() {
        return underlyingpx2;
    }

    /**
     * 设置 ???? ????????4?
     * @param underlyingpx2 ???? ????????4?
     */
    public void setUnderlyingpx2(BigDecimal underlyingpx2) {
        this.underlyingpx2 = underlyingpx2;
    }

    /**
     * 取得 ?????? ????????5?
     * @return ?????? ????????5?
     */
    public BigDecimal getUnderlyingaccruedinterestamt() {
        return underlyingaccruedinterestamt;
    }

    /**
     * 设置 ?????? ????????5?
     * @param underlyingaccruedinterestamt ?????? ????????5?
     */
    public void setUnderlyingaccruedinterestamt(BigDecimal underlyingaccruedinterestamt) {
        this.underlyingaccruedinterestamt = underlyingaccruedinterestamt;
    }

    /**
     * 取得 ?????? ????????5?
     * @return ?????? ????????5?
     */
    public BigDecimal getUnderlyingaccruedinterestamt2() {
        return underlyingaccruedinterestamt2;
    }

    /**
     * 设置 ?????? ????????5?
     * @param underlyingaccruedinterestamt2 ?????? ????????5?
     */
    public void setUnderlyingaccruedinterestamt2(BigDecimal underlyingaccruedinterestamt2) {
        this.underlyingaccruedinterestamt2 = underlyingaccruedinterestamt2;
    }

    /**
     * 取得 ???? ????????4?
     * @return ???? ????????4?
     */
    public BigDecimal getUnderlyingdirtyprice() {
        return underlyingdirtyprice;
    }

    /**
     * 设置 ???? ????????4?
     * @param underlyingdirtyprice ???? ????????4?
     */
    public void setUnderlyingdirtyprice(BigDecimal underlyingdirtyprice) {
        this.underlyingdirtyprice = underlyingdirtyprice;
    }

    /**
     * 取得 ???? ????????4?
     * @return ???? ????????4?
     */
    public BigDecimal getUnderlyingdirtyprice2() {
        return underlyingdirtyprice2;
    }

    /**
     * 设置 ???? ????????4?
     * @param underlyingdirtyprice2 ???? ????????4?
     */
    public void setUnderlyingdirtyprice2(BigDecimal underlyingdirtyprice2) {
        this.underlyingdirtyprice2 = underlyingdirtyprice2;
    }

    /**
     * 取得 ????? ???%????4?
     * @return ????? ???%????4?
     */
    public BigDecimal getUnderlyingyield() {
        return underlyingyield;
    }

    /**
     * 设置 ????? ???%????4?
     * @param underlyingyield ????? ???%????4?
     */
    public void setUnderlyingyield(BigDecimal underlyingyield) {
        this.underlyingyield = underlyingyield;
    }

    /**
     * 取得 ????? ???%????4?
     * @return ????? ???%????4?
     */
    public BigDecimal getUnderlyingyield2() {
        return underlyingyield2;
    }

    /**
     * 设置 ????? ???%????4?
     * @param underlyingyield2 ????? ???%????4?
     */
    public void setUnderlyingyield2(BigDecimal underlyingyield2) {
        this.underlyingyield2 = underlyingyield2;
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置 ??????
     * @param updatetime ??????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public BigDecimal getStrikeyield() {
        return strikeyield;
    }

    /**
     * 设置 ?????
     * @param strikeyield ?????
     */
    public void setStrikeyield(BigDecimal strikeyield) {
        this.strikeyield = strikeyield;
    }

    /**
     * 取得 ?????2
     * @return ?????2
     */
    public BigDecimal getYield2() {
        return yield2;
    }

    /**
     * 设置 ?????2
     * @param yield2 ?????2
     */
    public void setYield2(BigDecimal yield2) {
        this.yield2 = yield2;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getTrademethod() {
        return trademethod;
    }

    /**
     * 设置 ????
     * @param trademethod ????
     */
    public void setTrademethod(String trademethod) {
        this.trademethod = trademethod == null ? null : trademethod.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getSettltype() {
        return settltype;
    }

    /**
     * 设置 ????
     * @param settltype ????
     */
    public void setSettltype(String settltype) {
        this.settltype = settltype == null ? null : settltype.trim();
    }

    /**
     * 取得 ??????????
     * @return ??????????
     */
    public String getDealtranstype() {
        return dealtranstype;
    }

    /**
     * 设置 ??????????
     * @param dealtranstype ??????????
     */
    public void setDealtranstype(String dealtranstype) {
        this.dealtranstype = dealtranstype == null ? null : dealtranstype.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getClordid() {
        return clordid;
    }

    /**
     * 设置 ???????
     * @param clordid ???????
     */
    public void setClordid(String clordid) {
        this.clordid = clordid == null ? null : clordid.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getContingencyindicator() {
        return contingencyindicator;
    }

    /**
     * 设置 ????
     * @param contingencyindicator ????
     */
    public void setContingencyindicator(String contingencyindicator) {
        this.contingencyindicator = contingencyindicator == null ? null : contingencyindicator.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getReference() {
        return reference;
    }

    /**
     * 设置 ??????
     * @param reference ??????
     */
    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }

    /**
     * 取得 ????  6-???? 13-????
     * @return ????  6-???? 13-????
     */
    public String getClearingmethod() {
        return clearingmethod;
    }

    /**
     * 设置 ????  6-???? 13-????
     * @param clearingmethod ????  6-???? 13-????
     */
    public void setClearingmethod(String clearingmethod) {
        this.clearingmethod = clearingmethod == null ? null : clearingmethod.trim();
    }

    /**
     * 取得 ABS???????
     * @return ABS???????
     */
    public BigDecimal getPrincipal() {
        return principal;
    }

    /**
     * 设置 ABS???????
     * @param principal ABS???????
     */
    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    /**
     * 取得 ABS??????
     * @return ABS??????
     */
    public BigDecimal getTotalprincipal() {
        return totalprincipal;
    }

    /**
     * 设置 ABS??????
     * @param totalprincipal ABS??????
     */
    public void setTotalprincipal(BigDecimal totalprincipal) {
        this.totalprincipal = totalprincipal;
    }

    /**
     * 取得 Y-?? N-ABS?
     * @return Y-?? N-ABS?
     */
    public String getCashbondindicator() {
        return cashbondindicator;
    }

    /**
     * 设置 Y-?? N-ABS?
     * @param cashbondindicator Y-?? N-ABS?
     */
    public void setCashbondindicator(String cashbondindicator) {
        this.cashbondindicator = cashbondindicator == null ? null : cashbondindicator.trim();
    }
}