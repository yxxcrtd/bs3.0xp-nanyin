package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfNetProdBuyDraw extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 查询日期
     */
    private String cdate;

    /**
     * 母产品代码
     */
    private String motherProdId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 单位净值
     */
    private String netValue;

    /**
     * 上日净份额
     */
    private String netLot;

    /**
     * 上日申购金额
     */
    private String buyAmt;

    /**
     * 上日申购份额
     */
    private String buyLot;

    /**
     * 上日总赎回份额
     */
    private String totalDrawLot;

    /**
     * 上日总赎回市值金额
     */
    private String totalDrawValAmt;

    /**
     * 上日总赎回给客户的金额
     */
    private String totalDrawCltAmt;

    /**
     * 上日总赎回手续费
     */
    private String totalDrawCommission;

    /**
     * 上日总产品存留手续费
     */
    private String totalLeftCommission;

    /**
     * 赎回净值日期
     */
    private String drawNetDate;

    /**
     * 赎回净值
     */
    private String drawNet;

    /**
     * 上日赎回份额
     */
    private String drawLot;

    /**
     * 上日赎回市值金额
     */
    private String drawValAmt;

    /**
     * 上日赎回给客户的金额
     */
    private String drawCltAmt;

    /**
     * 上日赎回手续费
     */
    private String drawCommission;

    /**
     * 上日产品存留手续费
     */
    private String leftCommission;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 查询日期
     * @return 查询日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 查询日期
     * @param cdate 查询日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }

    /**
     * 取得 母产品代码
     * @return 母产品代码
     */
    public String getMotherProdId() {
        return motherProdId;
    }

    /**
     * 设置 母产品代码
     * @param motherProdId 母产品代码
     */
    public void setMotherProdId(String motherProdId) {
        this.motherProdId = motherProdId == null ? null : motherProdId.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
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
     * 取得 单位净值
     * @return 单位净值
     */
    public String getNetValue() {
        return netValue;
    }

    /**
     * 设置 单位净值
     * @param netValue 单位净值
     */
    public void setNetValue(String netValue) {
        this.netValue = netValue == null ? null : netValue.trim();
    }

    /**
     * 取得 上日净份额
     * @return 上日净份额
     */
    public String getNetLot() {
        return netLot;
    }

    /**
     * 设置 上日净份额
     * @param netLot 上日净份额
     */
    public void setNetLot(String netLot) {
        this.netLot = netLot == null ? null : netLot.trim();
    }

    /**
     * 取得 上日申购金额
     * @return 上日申购金额
     */
    public String getBuyAmt() {
        return buyAmt;
    }

    /**
     * 设置 上日申购金额
     * @param buyAmt 上日申购金额
     */
    public void setBuyAmt(String buyAmt) {
        this.buyAmt = buyAmt == null ? null : buyAmt.trim();
    }

    /**
     * 取得 上日申购份额
     * @return 上日申购份额
     */
    public String getBuyLot() {
        return buyLot;
    }

    /**
     * 设置 上日申购份额
     * @param buyLot 上日申购份额
     */
    public void setBuyLot(String buyLot) {
        this.buyLot = buyLot == null ? null : buyLot.trim();
    }

    /**
     * 取得 上日总赎回份额
     * @return 上日总赎回份额
     */
    public String getTotalDrawLot() {
        return totalDrawLot;
    }

    /**
     * 设置 上日总赎回份额
     * @param totalDrawLot 上日总赎回份额
     */
    public void setTotalDrawLot(String totalDrawLot) {
        this.totalDrawLot = totalDrawLot == null ? null : totalDrawLot.trim();
    }

    /**
     * 取得 上日总赎回市值金额
     * @return 上日总赎回市值金额
     */
    public String getTotalDrawValAmt() {
        return totalDrawValAmt;
    }

    /**
     * 设置 上日总赎回市值金额
     * @param totalDrawValAmt 上日总赎回市值金额
     */
    public void setTotalDrawValAmt(String totalDrawValAmt) {
        this.totalDrawValAmt = totalDrawValAmt == null ? null : totalDrawValAmt.trim();
    }

    /**
     * 取得 上日总赎回给客户的金额
     * @return 上日总赎回给客户的金额
     */
    public String getTotalDrawCltAmt() {
        return totalDrawCltAmt;
    }

    /**
     * 设置 上日总赎回给客户的金额
     * @param totalDrawCltAmt 上日总赎回给客户的金额
     */
    public void setTotalDrawCltAmt(String totalDrawCltAmt) {
        this.totalDrawCltAmt = totalDrawCltAmt == null ? null : totalDrawCltAmt.trim();
    }

    /**
     * 取得 上日总赎回手续费
     * @return 上日总赎回手续费
     */
    public String getTotalDrawCommission() {
        return totalDrawCommission;
    }

    /**
     * 设置 上日总赎回手续费
     * @param totalDrawCommission 上日总赎回手续费
     */
    public void setTotalDrawCommission(String totalDrawCommission) {
        this.totalDrawCommission = totalDrawCommission == null ? null : totalDrawCommission.trim();
    }

    /**
     * 取得 上日总产品存留手续费
     * @return 上日总产品存留手续费
     */
    public String getTotalLeftCommission() {
        return totalLeftCommission;
    }

    /**
     * 设置 上日总产品存留手续费
     * @param totalLeftCommission 上日总产品存留手续费
     */
    public void setTotalLeftCommission(String totalLeftCommission) {
        this.totalLeftCommission = totalLeftCommission == null ? null : totalLeftCommission.trim();
    }

    /**
     * 取得 赎回净值日期
     * @return 赎回净值日期
     */
    public String getDrawNetDate() {
        return drawNetDate;
    }

    /**
     * 设置 赎回净值日期
     * @param drawNetDate 赎回净值日期
     */
    public void setDrawNetDate(String drawNetDate) {
        this.drawNetDate = drawNetDate == null ? null : drawNetDate.trim();
    }

    /**
     * 取得 赎回净值
     * @return 赎回净值
     */
    public String getDrawNet() {
        return drawNet;
    }

    /**
     * 设置 赎回净值
     * @param drawNet 赎回净值
     */
    public void setDrawNet(String drawNet) {
        this.drawNet = drawNet == null ? null : drawNet.trim();
    }

    /**
     * 取得 上日赎回份额
     * @return 上日赎回份额
     */
    public String getDrawLot() {
        return drawLot;
    }

    /**
     * 设置 上日赎回份额
     * @param drawLot 上日赎回份额
     */
    public void setDrawLot(String drawLot) {
        this.drawLot = drawLot == null ? null : drawLot.trim();
    }

    /**
     * 取得 上日赎回市值金额
     * @return 上日赎回市值金额
     */
    public String getDrawValAmt() {
        return drawValAmt;
    }

    /**
     * 设置 上日赎回市值金额
     * @param drawValAmt 上日赎回市值金额
     */
    public void setDrawValAmt(String drawValAmt) {
        this.drawValAmt = drawValAmt == null ? null : drawValAmt.trim();
    }

    /**
     * 取得 上日赎回给客户的金额
     * @return 上日赎回给客户的金额
     */
    public String getDrawCltAmt() {
        return drawCltAmt;
    }

    /**
     * 设置 上日赎回给客户的金额
     * @param drawCltAmt 上日赎回给客户的金额
     */
    public void setDrawCltAmt(String drawCltAmt) {
        this.drawCltAmt = drawCltAmt == null ? null : drawCltAmt.trim();
    }

    /**
     * 取得 上日赎回手续费
     * @return 上日赎回手续费
     */
    public String getDrawCommission() {
        return drawCommission;
    }

    /**
     * 设置 上日赎回手续费
     * @param drawCommission 上日赎回手续费
     */
    public void setDrawCommission(String drawCommission) {
        this.drawCommission = drawCommission == null ? null : drawCommission.trim();
    }

    /**
     * 取得 上日产品存留手续费
     * @return 上日产品存留手续费
     */
    public String getLeftCommission() {
        return leftCommission;
    }

    /**
     * 设置 上日产品存留手续费
     * @param leftCommission 上日产品存留手续费
     */
    public void setLeftCommission(String leftCommission) {
        this.leftCommission = leftCommission == null ? null : leftCommission.trim();
    }
}