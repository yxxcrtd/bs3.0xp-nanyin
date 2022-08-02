package com.joyintech.tams.disclosure.busicommon.report.dto;

import java.util.Date;
import java.util.List;

/**
 * 每周净值公告（公募）/（私募）
 */
public class ReportJzfhbg001Dto {
   /*下面字段用于报表展示用的start*/
   /**标题名：普通产品：产品名称 分级产品：产品名称+（子产品代码）*/
   private String titleName;
   /*业绩基准日*/
   private String settDateT;
   /*产品名称：若“是否是分级产品”为“是”，取产品名称+子产品名称；若“是否分级产品”为“否”取产品名称*/
   private String prodName;
   /*产品登记编码*/
   private String regCode;
   /*子产品代码*/
   private String prodId;
   /*子产品成立日：XXXX年XX月XX日*/
   private String setDateT;
   /*收益分配基准日份额净值（元）*/
   private String cUnitValue;
   /*本次分红方案（元/1份份额）*/
   private String cUnitProfit;
   /*分红权益登记日*/
   private String settDate;
   /*现金分红日*/
   private String paymentDate;
   /*期末每份额净值（元）*/
   private String unitValue;
   /*每份额累计净值（元）*/
   private String unitValueT;
   /*本期投资运作参考年化收益率*/
   private String yield;
   /*当期封闭期*/
   private String range;
   /*披露日*/
   private String announceT;
   /*下面字段用于报表展示用的end*/

   /*赎回价格*/
   private String redValue;
   /*申购价格*/
   private String subValue;

   /**
    * 后续开放期安排
    */
   private List<ReportOpenPlanDto> openPlanList;

   /*下面字段用于查询转换用的start*/
   /*产品成立日*/
   private Date setDate;

   public String getTitleName() {
      return titleName;
   }

   public void setTitleName(String titleName) {
      this.titleName = titleName;
   }

   public String getSettDateT() {
      return settDateT;
   }

   public void setSettDateT(String settDateT) {
      this.settDateT = settDateT;
   }

   public String getProdName() {
      return prodName;
   }

   public void setProdName(String prodName) {
      this.prodName = prodName;
   }

   public String getRegCode() {
      return regCode;
   }

   public void setRegCode(String regCode) {
      this.regCode = regCode;
   }

   public String getProdId() {
      return prodId;
   }

   public void setProdId(String prodId) {
      this.prodId = prodId;
   }

   public String getSetDateT() {
      return setDateT;
   }

   public void setSetDateT(String setDateT) {
      this.setDateT = setDateT;
   }

   public String getcUnitValue() {
      return cUnitValue;
   }

   public void setcUnitValue(String cUnitValue) {
      this.cUnitValue = cUnitValue;
   }

   public String getcUnitProfit() {
      return cUnitProfit;
   }

   public void setcUnitProfit(String cUnitProfit) {
      this.cUnitProfit = cUnitProfit;
   }

   public String getSettDate() {
      return settDate;
   }

   public void setSettDate(String settDate) {
      this.settDate = settDate;
   }

   public String getPaymentDate() {
      return paymentDate;
   }

   public void setPaymentDate(String paymentDate) {
      this.paymentDate = paymentDate;
   }

   public String getUnitValue() {
      return unitValue;
   }

   public void setUnitValue(String unitValue) {
      this.unitValue = unitValue;
   }

   public String getUnitValueT() {
      return unitValueT;
   }

   public void setUnitValueT(String unitValueT) {
      this.unitValueT = unitValueT;
   }

   public String getYield() {
      return yield;
   }

   public void setYield(String yield) {
      this.yield = yield;
   }

   public String getRange() {
      return range;
   }

   public void setRange(String range) {
      this.range = range;
   }

   public String getAnnounceT() {
      return announceT;
   }

   public void setAnnounceT(String announceT) {
      this.announceT = announceT;
   }

   public String getRedValue() {
      return redValue;
   }

   public void setRedValue(String redValue) {
      this.redValue = redValue;
   }

   public String getSubValue() {
      return subValue;
   }

   public void setSubValue(String subValue) {
      this.subValue = subValue;
   }

   public List<ReportOpenPlanDto> getOpenPlanList() {
      return openPlanList;
   }

   public void setOpenPlanList(List<ReportOpenPlanDto> openPlanList) {
      this.openPlanList = openPlanList;
   }

   public Date getSetDate() {
      return setDate;
   }

   public void setSetDate(Date setDate) {
      this.setDate = setDate;
   }
}
