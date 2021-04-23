/*
 * 文件名：SecCalInfoModel.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.calc.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;


/**
 * 债券基本信息（算法用）
 * 
 * @author zhangliang
 * @version 1.0 2020年5月19日
 * @see SecCalInfoModel
 * @since 1.0
 */
public class SecCalInfoModel extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7602384401999570521L;
    /**
     * 息票品种
     */
    private String couponspecies;
    /**
     * 首次付息日
     */
    private Date firstrateday;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 发行价格
     */
    private BigDecimal issueprice;
    /**
     * 面值
     */
    private BigDecimal facevalue;
    /**
     * 票面利率
     */
    private BigDecimal paperir;
    /**
     * 计息基础
     */
    private String ratebasic;
    /**
     * 利率类型
     */
    private String interestrate;
    /**
     * 付息频率 除A/A以外，其他计息基准不需要传入
     */
    private int interestfrequency;
    /**
     * 利息分配方式，平均分配 J 按实际天数分配 D
     */
    private String intpayrule;
    /**
     * 挂钩利率代码
     */
    private String ratecode;
    
    /**
     * 获取 couponspecies
     * @return couponspecies.
     */
    public String getCouponspecies() {
        return couponspecies;
    }
    
    /**
     * 设置 couponspecies
     * @param couponspecies couponspecies
     */
    public void setCouponspecies(String couponspecies) {
        this.couponspecies = couponspecies;
    }
    
    /**
     * 获取 firstrateday
     * @return firstrateday.
     */
    public Date getFirstrateday() {
        return firstrateday;
    }
    
    /**
     * 设置 firstrateday
     * @param firstrateday firstrateday
     */
    public void setFirstrateday(Date firstrateday) {
        this.firstrateday = firstrateday;
    }
    
    /**
     * 获取 vdate
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }
    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }
    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }
    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
    
    /**
     * 获取 issueprice
     * @return issueprice.
     */
    public BigDecimal getIssueprice() {
        return issueprice;
    }
    
    /**
     * 设置 issueprice
     * @param issueprice issueprice
     */
    public void setIssueprice(BigDecimal issueprice) {
        this.issueprice = issueprice;
    }
    
    /**
     * 获取 facevalue
     * @return facevalue.
     */
    public BigDecimal getFacevalue() {
        return facevalue;
    }
    
    /**
     * 设置 facevalue
     * @param facevalue facevalue
     */
    public void setFacevalue(BigDecimal facevalue) {
        this.facevalue = facevalue;
    }
    
    /**
     * 获取 paperir
     * @return paperir.
     */
    public BigDecimal getPaperir() {
        return paperir;
    }
    
    /**
     * 设置 paperir
     * @param paperir paperir
     */
    public void setPaperir(BigDecimal paperir) {
        this.paperir = paperir;
    }
    
    /**
     * 获取 ratebasic
     * @return ratebasic.
     */
    public String getRatebasic() {
        return ratebasic;
    }
    
    /**
     * 设置 ratebasic
     * @param ratebasic ratebasic
     */
    public void setRatebasic(String ratebasic) {
        this.ratebasic = ratebasic;
    }
    
    /**
     * 获取 interestrate
     * @return interestrate.
     */
    public String getInterestrate() {
        return interestrate;
    }
    
    /**
     * 设置 interestrate
     * @param interestrate interestrate
     */
    public void setInterestrate(String interestrate) {
        this.interestrate = interestrate;
    }
    
    /**
     * 获取 interestfrequency
     * @return interestfrequency.
     */
    public int getInterestfrequency() {
        return interestfrequency;
    }
    
    /**
     * 设置 interestfrequency
     * @param interestfrequency interestfrequency
     */
    public void setInterestfrequency(int interestfrequency) {
        this.interestfrequency = interestfrequency;
    }
    
    /**
     * 获取 intpayrule
     * @return intpayrule.
     */
    public String getIntpayrule() {
        return intpayrule;
    }
    
    /**
     * 设置 intpayrule
     * @param intpayrule intpayrule
     */
    public void setIntpayrule(String intpayrule) {
        this.intpayrule = intpayrule;
    }

    
    /**
     * 获取 ratecode
     * @return ratecode.
     */
    public String getRatecode() {
        return ratecode;
    }

    
    /**
     * 设置 ratecode
     * @param ratecode ratecode
     */
    public void setRatecode(String ratecode) {
        this.ratecode = ratecode;
    }
    
}
