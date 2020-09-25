package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfProdLadderDetail extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 系统平台日期
     */
    private String cdate;

    /**
     * 449192接口.开始日期
     */
    private String startdate;

    /**
     * null
     */
    private String enddate;

    /**
     * 449181接口.参考收益率、449183接口.参考收益率、449283接口.参考收益率、449188接口.参考收益率、449194接口.参考收益率、449189接口.参考收益率、449195接口.参考收益率、449192接口.收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal refaddrate;

    /**
     * 449188接口.起点金额、449189接口.起点金额、449195接口.起点金额、449192接口.起点金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal startamount;

    /**
     * 449188接口.终点金额、449189接口.终点金额、449195接口.终点金额、449192接口.终点金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endamount;

    /**
     * 449191接口.客户层级、449195接口.起点客户等级、449192接口.起点客户等级
     */
    private String startcustomerlevel;

    /**
     * 449195接口.终点客户等级、449192接口.终点客户等级
     */
    private String endcustomerlevel;

    /**
     * 449195接口.起点持有天数、449192接口.起点持有天数
     */
    private Short startdaynum;

    /**
     * 449195接口.终点持有天数、449192接口.终点持有天数
     */
    private Short enddaynum;

    /**
     * 449192接口.实际持有天数
     */
    private Short stickdaynum;

    /**
     * 449192接口.计息天数
     */
    private Short trandaynum;

    /**
     * 到期的计息笔数
449192接口.笔数
     */
    private Integer totalcount;

    /**
     * 当日剩余的金额
449181接口.参考募集金额、449183接口.参考总本金、449283接口.参考总本金、449188接口.参考募集金额、449191接口.分层金额、449194接口.分层金额、449189接口.分层总金额、449195接口.分层总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accountamountbyrate;

    /**
     * 到期用来计算收益的本金
449189接口.分层总成本金额、449195接口.分层总成本金额、449192接口.分层总成本金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sourceamountbyrate;

    /**
     * 产品到期的收益
449189接口.分层收益、449195接口.分层收益、449192接口.分层收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalprofitamount;

    /**
     * 当日分层总本金 - 上一期分层总本金 > 0时，差额为分层申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyamount;

    /**
     * 当日分层总本金 - 上一期分层总本金 < 0时，差额绝对值为分层赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal drawamount;

    /**
     * 接口ID
     */
    private String interfId;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 系统平台日期
     * @return 系统平台日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 系统平台日期
     * @param cdate 系统平台日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }

    /**
     * 取得 449192接口.开始日期
     * @return 449192接口.开始日期
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * 设置 449192接口.开始日期
     * @param startdate 449192接口.开始日期
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate == null ? null : startdate.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 null
     * @param enddate null
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    /**
     * 取得 449181接口.参考收益率、449183接口.参考收益率、449283接口.参考收益率、449188接口.参考收益率、449194接口.参考收益率、449189接口.参考收益率、449195接口.参考收益率、449192接口.收益率
     * @return 449181接口.参考收益率、449183接口.参考收益率、449283接口.参考收益率、449188接口.参考收益率、449194接口.参考收益率、449189接口.参考收益率、449195接口.参考收益率、449192接口.收益率
     */
    public BigDecimal getRefaddrate() {
        return refaddrate;
    }

    /**
     * 设置 449181接口.参考收益率、449183接口.参考收益率、449283接口.参考收益率、449188接口.参考收益率、449194接口.参考收益率、449189接口.参考收益率、449195接口.参考收益率、449192接口.收益率
     * @param refaddrate 449181接口.参考收益率、449183接口.参考收益率、449283接口.参考收益率、449188接口.参考收益率、449194接口.参考收益率、449189接口.参考收益率、449195接口.参考收益率、449192接口.收益率
     */
    public void setRefaddrate(BigDecimal refaddrate) {
        this.refaddrate = refaddrate;
    }

    /**
     * 取得 449188接口.起点金额、449189接口.起点金额、449195接口.起点金额、449192接口.起点金额
     * @return 449188接口.起点金额、449189接口.起点金额、449195接口.起点金额、449192接口.起点金额
     */
    public BigDecimal getStartamount() {
        return startamount;
    }

    /**
     * 设置 449188接口.起点金额、449189接口.起点金额、449195接口.起点金额、449192接口.起点金额
     * @param startamount 449188接口.起点金额、449189接口.起点金额、449195接口.起点金额、449192接口.起点金额
     */
    public void setStartamount(BigDecimal startamount) {
        this.startamount = startamount;
    }

    /**
     * 取得 449188接口.终点金额、449189接口.终点金额、449195接口.终点金额、449192接口.终点金额
     * @return 449188接口.终点金额、449189接口.终点金额、449195接口.终点金额、449192接口.终点金额
     */
    public BigDecimal getEndamount() {
        return endamount;
    }

    /**
     * 设置 449188接口.终点金额、449189接口.终点金额、449195接口.终点金额、449192接口.终点金额
     * @param endamount 449188接口.终点金额、449189接口.终点金额、449195接口.终点金额、449192接口.终点金额
     */
    public void setEndamount(BigDecimal endamount) {
        this.endamount = endamount;
    }

    /**
     * 取得 449191接口.客户层级、449195接口.起点客户等级、449192接口.起点客户等级
     * @return 449191接口.客户层级、449195接口.起点客户等级、449192接口.起点客户等级
     */
    public String getStartcustomerlevel() {
        return startcustomerlevel;
    }

    /**
     * 设置 449191接口.客户层级、449195接口.起点客户等级、449192接口.起点客户等级
     * @param startcustomerlevel 449191接口.客户层级、449195接口.起点客户等级、449192接口.起点客户等级
     */
    public void setStartcustomerlevel(String startcustomerlevel) {
        this.startcustomerlevel = startcustomerlevel == null ? null : startcustomerlevel.trim();
    }

    /**
     * 取得 449195接口.终点客户等级、449192接口.终点客户等级
     * @return 449195接口.终点客户等级、449192接口.终点客户等级
     */
    public String getEndcustomerlevel() {
        return endcustomerlevel;
    }

    /**
     * 设置 449195接口.终点客户等级、449192接口.终点客户等级
     * @param endcustomerlevel 449195接口.终点客户等级、449192接口.终点客户等级
     */
    public void setEndcustomerlevel(String endcustomerlevel) {
        this.endcustomerlevel = endcustomerlevel == null ? null : endcustomerlevel.trim();
    }

    /**
     * 取得 449195接口.起点持有天数、449192接口.起点持有天数
     * @return 449195接口.起点持有天数、449192接口.起点持有天数
     */
    public Short getStartdaynum() {
        return startdaynum;
    }

    /**
     * 设置 449195接口.起点持有天数、449192接口.起点持有天数
     * @param startdaynum 449195接口.起点持有天数、449192接口.起点持有天数
     */
    public void setStartdaynum(Short startdaynum) {
        this.startdaynum = startdaynum;
    }

    /**
     * 取得 449195接口.终点持有天数、449192接口.终点持有天数
     * @return 449195接口.终点持有天数、449192接口.终点持有天数
     */
    public Short getEnddaynum() {
        return enddaynum;
    }

    /**
     * 设置 449195接口.终点持有天数、449192接口.终点持有天数
     * @param enddaynum 449195接口.终点持有天数、449192接口.终点持有天数
     */
    public void setEnddaynum(Short enddaynum) {
        this.enddaynum = enddaynum;
    }

    /**
     * 取得 449192接口.实际持有天数
     * @return 449192接口.实际持有天数
     */
    public Short getStickdaynum() {
        return stickdaynum;
    }

    /**
     * 设置 449192接口.实际持有天数
     * @param stickdaynum 449192接口.实际持有天数
     */
    public void setStickdaynum(Short stickdaynum) {
        this.stickdaynum = stickdaynum;
    }

    /**
     * 取得 449192接口.计息天数
     * @return 449192接口.计息天数
     */
    public Short getTrandaynum() {
        return trandaynum;
    }

    /**
     * 设置 449192接口.计息天数
     * @param trandaynum 449192接口.计息天数
     */
    public void setTrandaynum(Short trandaynum) {
        this.trandaynum = trandaynum;
    }

    /**
     * 取得 到期的计息笔数
449192接口.笔数
     * @return 到期的计息笔数
449192接口.笔数
     */
    public Integer getTotalcount() {
        return totalcount;
    }

    /**
     * 设置 到期的计息笔数
449192接口.笔数
     * @param totalcount 到期的计息笔数
449192接口.笔数
     */
    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }

    /**
     * 取得 当日剩余的金额
449181接口.参考募集金额、449183接口.参考总本金、449283接口.参考总本金、449188接口.参考募集金额、449191接口.分层金额、449194接口.分层金额、449189接口.分层总金额、449195接口.分层总金额
     * @return 当日剩余的金额
449181接口.参考募集金额、449183接口.参考总本金、449283接口.参考总本金、449188接口.参考募集金额、449191接口.分层金额、449194接口.分层金额、449189接口.分层总金额、449195接口.分层总金额
     */
    public BigDecimal getAccountamountbyrate() {
        return accountamountbyrate;
    }

    /**
     * 设置 当日剩余的金额
449181接口.参考募集金额、449183接口.参考总本金、449283接口.参考总本金、449188接口.参考募集金额、449191接口.分层金额、449194接口.分层金额、449189接口.分层总金额、449195接口.分层总金额
     * @param accountamountbyrate 当日剩余的金额
449181接口.参考募集金额、449183接口.参考总本金、449283接口.参考总本金、449188接口.参考募集金额、449191接口.分层金额、449194接口.分层金额、449189接口.分层总金额、449195接口.分层总金额
     */
    public void setAccountamountbyrate(BigDecimal accountamountbyrate) {
        this.accountamountbyrate = accountamountbyrate;
    }

    /**
     * 取得 到期用来计算收益的本金
449189接口.分层总成本金额、449195接口.分层总成本金额、449192接口.分层总成本金额
     * @return 到期用来计算收益的本金
449189接口.分层总成本金额、449195接口.分层总成本金额、449192接口.分层总成本金额
     */
    public BigDecimal getSourceamountbyrate() {
        return sourceamountbyrate;
    }

    /**
     * 设置 到期用来计算收益的本金
449189接口.分层总成本金额、449195接口.分层总成本金额、449192接口.分层总成本金额
     * @param sourceamountbyrate 到期用来计算收益的本金
449189接口.分层总成本金额、449195接口.分层总成本金额、449192接口.分层总成本金额
     */
    public void setSourceamountbyrate(BigDecimal sourceamountbyrate) {
        this.sourceamountbyrate = sourceamountbyrate;
    }

    /**
     * 取得 产品到期的收益
449189接口.分层收益、449195接口.分层收益、449192接口.分层收益
     * @return 产品到期的收益
449189接口.分层收益、449195接口.分层收益、449192接口.分层收益
     */
    public BigDecimal getTotalprofitamount() {
        return totalprofitamount;
    }

    /**
     * 设置 产品到期的收益
449189接口.分层收益、449195接口.分层收益、449192接口.分层收益
     * @param totalprofitamount 产品到期的收益
449189接口.分层收益、449195接口.分层收益、449192接口.分层收益
     */
    public void setTotalprofitamount(BigDecimal totalprofitamount) {
        this.totalprofitamount = totalprofitamount;
    }

    /**
     * 取得 当日分层总本金 - 上一期分层总本金 > 0时，差额为分层申购金额
     * @return 当日分层总本金 - 上一期分层总本金 > 0时，差额为分层申购金额
     */
    public BigDecimal getBuyamount() {
        return buyamount;
    }

    /**
     * 设置 当日分层总本金 - 上一期分层总本金 > 0时，差额为分层申购金额
     * @param buyamount 当日分层总本金 - 上一期分层总本金 > 0时，差额为分层申购金额
     */
    public void setBuyamount(BigDecimal buyamount) {
        this.buyamount = buyamount;
    }

    /**
     * 取得 当日分层总本金 - 上一期分层总本金 < 0时，差额绝对值为分层赎回金额
     * @return 当日分层总本金 - 上一期分层总本金 < 0时，差额绝对值为分层赎回金额
     */
    public BigDecimal getDrawamount() {
        return drawamount;
    }

    /**
     * 设置 当日分层总本金 - 上一期分层总本金 < 0时，差额绝对值为分层赎回金额
     * @param drawamount 当日分层总本金 - 上一期分层总本金 < 0时，差额绝对值为分层赎回金额
     */
    public void setDrawamount(BigDecimal drawamount) {
        this.drawamount = drawamount;
    }

    /**
     * 取得 接口ID
     * @return 接口ID
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口ID
     * @param interfId 接口ID
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId == null ? null : interfId.trim();
    }
}