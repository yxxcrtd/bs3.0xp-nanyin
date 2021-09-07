package com.joyintech.service.risk.rskbotdetailreport.dto;

import com.joyintech.framework.base.BaseEntity;

public class RskValBotDetailReportDto extends BaseEntity {
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
    private String nColumn;
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

    /**
     * 资产二级分类
     */
    private String assettype2;

    /**
     * 所属行业
     */
    private String industry1;

    /**
     * 行类一级分类
     */
    private String inAssetType;

    /**
     * 是否投向地方政府平台
     */
    private String monitType;

    /**
     * 公共PPP
     */
    private String pppflag;

    /**
     * 是否政府投资基金
     */
    private String isGovernFund1;

    /**
     * 是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    private String privatefundFlag;

    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String privateprdFlag;

    /**
     * 是否投向市场化债转股相关产品的部分
     */
    private String bondtostockFlag;

    /**
     * 管理方式
     */
    private String manageType;

    /**
     * 是否非标转标
     */
    private  String  isFbzb;

    /**
     * 是否专项债券
     */
    private String bankSecZxzq;

    /**
     * 是否商业银行可转债
     */
    private String bankSecKzzq;

    /**
     * 是否标准化票据
     */
    private String isBzhpj;

    /**
     * 是否未上市企业-政府出资产业投资基金
     */
    private String isunlisted;

    private String rating;

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

    public String getnColumn() {
        return nColumn;
    }

    public void setnColumn(String nColumn) {
        this.nColumn = nColumn;
    }

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

    public String getAssettype2() {
        return assettype2;
    }

    public void setAssettype2(String assettype2) {
        this.assettype2 = assettype2;
    }

    public String getIndustry1() {
        return industry1;
    }

    public void setIndustry1(String industry1) {
        this.industry1 = industry1;
    }

    public String getInAssetType() {
        return inAssetType;
    }

    public void setInAssetType(String inAssetType) {
        this.inAssetType = inAssetType;
    }

    public String getMonitType() {
        return monitType;
    }

    public void setMonitType(String monitType) {
        this.monitType = monitType;
    }

    public String getPppflag() {
        return pppflag;
    }

    public void setPppflag(String pppflag) {
        this.pppflag = pppflag;
    }

    public String getIsGovernFund1() {
        return isGovernFund1;
    }

    public void setIsGovernFund1(String isGovernFund1) {
        this.isGovernFund1 = isGovernFund1;
    }

    public String getPrivatefundFlag() {
        return privatefundFlag;
    }

    public void setPrivatefundFlag(String privatefundFlag) {
        this.privatefundFlag = privatefundFlag;
    }

    public String getPrivateprdFlag() {
        return privateprdFlag;
    }

    public void setPrivateprdFlag(String privateprdFlag) {
        this.privateprdFlag = privateprdFlag;
    }

    public String getBondtostockFlag() {
        return bondtostockFlag;
    }

    public void setBondtostockFlag(String bondtostockFlag) {
        this.bondtostockFlag = bondtostockFlag;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getIsFbzb() {
        return isFbzb;
    }

    public void setIsFbzb(String isFbzb) {
        this.isFbzb = isFbzb;
    }

    public String getBankSecZxzq() {
        return bankSecZxzq;
    }

    public void setBankSecZxzq(String bankSecZxzq) {
        this.bankSecZxzq = bankSecZxzq;
    }

    public String getBankSecKzzq() {
        return bankSecKzzq;
    }

    public void setBankSecKzzq(String bankSecKzzq) {
        this.bankSecKzzq = bankSecKzzq;
    }

    public String getIsBzhpj() {
        return isBzhpj;
    }

    public void setIsBzhpj(String isBzhpj) {
        this.isBzhpj = isBzhpj;
    }

    public String getIsunlisted() {
        return isunlisted;
    }

    public void setIsunlisted(String isunlisted) {
        this.isunlisted = isunlisted;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
