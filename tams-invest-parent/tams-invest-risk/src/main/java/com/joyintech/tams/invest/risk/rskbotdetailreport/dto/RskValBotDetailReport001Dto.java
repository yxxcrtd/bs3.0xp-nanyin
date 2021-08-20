package com.joyintech.tams.invest.risk.rskbotdetailreport.dto;

import com.joyintech.framework.base.BaseEntity;

public class RskValBotDetailReport001Dto extends BaseEntity {
    //资产代码:底层资产的资产代码
    private String aColumn;
    //资产名称:取底层资产的资产名称；OD的名称为其他负债；活期存款的资产代码为CASH，名称为活期存款
    private String bColumn;
    //所属通道：资管计划名称
    private String cColumn;
    //所属资管产品资产净值
    private String dColumn;
    //日末持有通道的资产价值
    private String eColumn;
    //资产负债类别
    private String fColumn;
    //资产负债类别（二级）
    private String gColumn;
    //估值表内数量
    private String hColumn;
    //估值表内行情价格
    private String iColumn;
    //估值表内净价资产总值
    private String jColumn;
    //估值表内应收利息/股利
    private String kColumn;
    //估值表内全价资产总值
    private String lColumn;
    //穿透后底层资产持有价值:固定公式“=E列/D列*L列”
    private String mColumn;
    //穿透后底层资产持有价值（系统）:系统折算后的资产持有价值。系统公式=底层资产的总价值*（产品持有资管计划的实收资本/资管计划的总实收资本）
    //private String nColumn;
    //G06科目
    private String oColumn;
    //最新债项评级
    private String pColumn;
    //最新主体评级
    private String qColumn;
    //报表使用评级
    private String rColumn;
    //估值表内实收资本
    private String sColumn;

    public String getaColumn() {
        return aColumn;
    }

    public void setaColumn(String aColumn) {
        this.aColumn = aColumn;
    }

    public String getbColumn() {
        return bColumn;
    }

    public void setbColumn(String bColumn) {
        this.bColumn = bColumn;
    }

    public String getcColumn() {
        return cColumn;
    }

    public void setcColumn(String cColumn) {
        this.cColumn = cColumn;
    }

    public String getdColumn() {
        return dColumn;
    }

    public void setdColumn(String dColumn) {
        this.dColumn = dColumn;
    }

    public String geteColumn() {
        return eColumn;
    }

    public void seteColumn(String eColumn) {
        this.eColumn = eColumn;
    }

    public String getfColumn() {
        return fColumn;
    }

    public void setfColumn(String fColumn) {
        this.fColumn = fColumn;
    }

    public String getgColumn() {
        return gColumn;
    }

    public void setgColumn(String gColumn) {
        this.gColumn = gColumn;
    }

    public String gethColumn() {
        return hColumn;
    }

    public void sethColumn(String hColumn) {
        this.hColumn = hColumn;
    }

    public String getiColumn() {
        return iColumn;
    }

    public void setiColumn(String iColumn) {
        this.iColumn = iColumn;
    }

    public String getjColumn() {
        return jColumn;
    }

    public void setjColumn(String jColumn) {
        this.jColumn = jColumn;
    }

    public String getkColumn() {
        return kColumn;
    }

    public void setkColumn(String kColumn) {
        this.kColumn = kColumn;
    }

    public String getlColumn() {
        return lColumn;
    }

    public void setlColumn(String lColumn) {
        this.lColumn = lColumn;
    }

    public String getmColumn() {
        return mColumn;
    }

    public void setmColumn(String mColumn) {
        this.mColumn = mColumn;
    }

   /* public String getnColumn() {
        return nColumn;
    }

    public void setnColumn(String nColumn) {
        this.nColumn = nColumn;
    }*/

    public String getoColumn() {
        return oColumn;
    }

    public void setoColumn(String oColumn) {
        this.oColumn = oColumn;
    }

    public String getpColumn() {
        return pColumn;
    }

    public void setpColumn(String pColumn) {
        this.pColumn = pColumn;
    }

    public String getqColumn() {
        return qColumn;
    }

    public void setqColumn(String qColumn) {
        this.qColumn = qColumn;
    }

    public String getrColumn() {
        return rColumn;
    }

    public void setrColumn(String rColumn) {
        this.rColumn = rColumn;
    }

    public String getsColumn() {
        return sColumn;
    }

    public void setsColumn(String sColumn) {
        this.sColumn = sColumn;
    }
}
