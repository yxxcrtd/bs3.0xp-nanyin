/*
 * 文件名：SecAssetStatistics001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.report.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 债券结构统计表
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年10月30日
 * @see SecAssetStatistics001Dto
 * @since 1.0
 */
public class SecAssetStatistics001Dto extends BaseEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
   
    private int orderno;
    private BigDecimal amount;//金额
    private BigDecimal sumamt;//总计金额
    private BigDecimal sumamtNC;//总计金额-年初
    private BigDecimal sumamtTQ;//总计金额-同期
    private BigDecimal sumamtSY;//总计金额-上月
    private BigDecimal proportion;//占比%
    private BigDecimal jncncye;//较年初变化-年初余额
    private BigDecimal jnczj;//较年初变化-增减
    private BigDecimal jncfd;//较年初变化-幅度
    private BigDecimal jtqtqye;//较同期变化-同期余额
    private BigDecimal jtqzj;//较同期变化-增减
    private BigDecimal jtqfd;//较同期变化-幅度
    private BigDecimal jsysyye;//较上月变化-上月余额
    private BigDecimal jsyzj;//较上月变化-增减
    private BigDecimal jsyfd;//较上月变化-幅度
    private BigDecimal sumproportion;//占比汇总
    private BigDecimal sumjnczj;//较年初变化-增减汇总
    private BigDecimal sumjncfd;//较年初变化-幅度汇总
    private BigDecimal sumjtqzj;//较同期变化-增减汇总
    private BigDecimal sumjtqfd;//较同期变化-幅度汇总
    private BigDecimal sumjsyzj;//较上月变化-增减汇总
    private BigDecimal sumjsyfd;//较上月变化-幅度汇总
    
    
    /**
     * 获取 sumamt
     * @return sumamt.
     */
    public BigDecimal getSumamt() {
        return sumamt;
    }


    
    /**
     * 设置 sumamt
     * @param sumamt sumamt
     */
    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
    }

    

    
    /**
     * 获取 sumamtNC
     * @return sumamtNC.
     */
    public BigDecimal getSumamtNC() {
        return sumamtNC;
    }



    
    /**
     * 设置 sumamtNC
     * @param sumamtNC sumamtNC
     */
    public void setSumamtNC(BigDecimal sumamtNC) {
        this.sumamtNC = sumamtNC;
    }



    
    /**
     * 获取 sumamtTQ
     * @return sumamtTQ.
     */
    public BigDecimal getSumamtTQ() {
        return sumamtTQ;
    }



    
    /**
     * 设置 sumamtTQ
     * @param sumamtTQ sumamtTQ
     */
    public void setSumamtTQ(BigDecimal sumamtTQ) {
        this.sumamtTQ = sumamtTQ;
    }



    
    /**
     * 获取 sumamtSY
     * @return sumamtSY.
     */
    public BigDecimal getSumamtSY() {
        return sumamtSY;
    }



    
    /**
     * 设置 sumamtSY
     * @param sumamtSY sumamtSY
     */
    public void setSumamtSY(BigDecimal sumamtSY) {
        this.sumamtSY = sumamtSY;
    }



    /**
     * 获取 orderno
     * @return orderno.
     */
    public int getOrderno() {
        return orderno;
    }

    
    /**
     * 设置 orderno
     * @param orderno orderno
     */
    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    /**
     * 获取 amount
     * @return amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }
    
    /**
     * 设置 amount
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    /**
     * 获取 proportion
     * @return proportion.
     */
    public BigDecimal getProportion() {
        return proportion;
    }
    
    /**
     * 设置 proportion
     * @param proportion proportion
     */
    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }
    
    /**
     * 获取 jncncye
     * @return jncncye.
     */
    public BigDecimal getJncncye() {
        return jncncye;
    }
    
    /**
     * 设置 jncncye
     * @param jncncye jncncye
     */
    public void setJncncye(BigDecimal jncncye) {
        this.jncncye = jncncye;
    }
    
    /**
     * 获取 jnczj
     * @return jnczj.
     */
    public BigDecimal getJnczj() {
        return jnczj;
    }
    
    /**
     * 设置 jnczj
     * @param jnczj jnczj
     */
    public void setJnczj(BigDecimal jnczj) {
        this.jnczj = jnczj;
    }
    
    /**
     * 获取 jncfd
     * @return jncfd.
     */
    public BigDecimal getJncfd() {
        return jncfd;
    }
    
    /**
     * 设置 jncfd
     * @param jncfd jncfd
     */
    public void setJncfd(BigDecimal jncfd) {
        this.jncfd = jncfd;
    }
    
    /**
     * 获取 jtqtqye
     * @return jtqtqye.
     */
    public BigDecimal getJtqtqye() {
        return jtqtqye;
    }
    
    /**
     * 设置 jtqtqye
     * @param jtqtqye jtqtqye
     */
    public void setJtqtqye(BigDecimal jtqtqye) {
        this.jtqtqye = jtqtqye;
    }
    
    /**
     * 获取 jtqzj
     * @return jtqzj.
     */
    public BigDecimal getJtqzj() {
        return jtqzj;
    }
    
    /**
     * 设置 jtqzj
     * @param jtqzj jtqzj
     */
    public void setJtqzj(BigDecimal jtqzj) {
        this.jtqzj = jtqzj;
    }
    
    /**
     * 获取 jtqfd
     * @return jtqfd.
     */
    public BigDecimal getJtqfd() {
        return jtqfd;
    }
    
    /**
     * 设置 jtqfd
     * @param jtqfd jtqfd
     */
    public void setJtqfd(BigDecimal jtqfd) {
        this.jtqfd = jtqfd;
    }
    
    /**
     * 获取 jsysyye
     * @return jsysyye.
     */
    public BigDecimal getJsysyye() {
        return jsysyye;
    }
    
    /**
     * 设置 jsysyye
     * @param jsysyye jsysyye
     */
    public void setJsysyye(BigDecimal jsysyye) {
        this.jsysyye = jsysyye;
    }
    
    /**
     * 获取 jsyzj
     * @return jsyzj.
     */
    public BigDecimal getJsyzj() {
        return jsyzj;
    }
    
    /**
     * 设置 jsyzj
     * @param jsyzj jsyzj
     */
    public void setJsyzj(BigDecimal jsyzj) {
        this.jsyzj = jsyzj;
    }
    
    /**
     * 获取 jsyfd
     * @return jsyfd.
     */
    public BigDecimal getJsyfd() {
        return jsyfd;
    }
    
    /**
     * 设置 jsyfd
     * @param jsyfd jsyfd
     */
    public void setJsyfd(BigDecimal jsyfd) {
        this.jsyfd = jsyfd;
    }


    
    /**
     * 获取 sumproportion
     * @return sumproportion.
     */
    public BigDecimal getSumproportion() {
        return sumproportion;
    }



    
    /**
     * 设置 sumproportion
     * @param sumproportion sumproportion
     */
    public void setSumproportion(BigDecimal sumproportion) {
        this.sumproportion = sumproportion;
    }



    
    /**
     * 获取 sumjnczj
     * @return sumjnczj.
     */
    public BigDecimal getSumjnczj() {
        return sumjnczj;
    }



    
    /**
     * 设置 sumjnczj
     * @param sumjnczj sumjnczj
     */
    public void setSumjnczj(BigDecimal sumjnczj) {
        this.sumjnczj = sumjnczj;
    }



    
    /**
     * 获取 sumjncfd
     * @return sumjncfd.
     */
    public BigDecimal getSumjncfd() {
        return sumjncfd;
    }



    
    /**
     * 设置 sumjncfd
     * @param sumjncfd sumjncfd
     */
    public void setSumjncfd(BigDecimal sumjncfd) {
        this.sumjncfd = sumjncfd;
    }



    
    /**
     * 获取 sumjtqzj
     * @return sumjtqzj.
     */
    public BigDecimal getSumjtqzj() {
        return sumjtqzj;
    }



    
    /**
     * 设置 sumjtqzj
     * @param sumjtqzj sumjtqzj
     */
    public void setSumjtqzj(BigDecimal sumjtqzj) {
        this.sumjtqzj = sumjtqzj;
    }



    
    /**
     * 获取 sumjtqfd
     * @return sumjtqfd.
     */
    public BigDecimal getSumjtqfd() {
        return sumjtqfd;
    }



    
    /**
     * 设置 sumjtqfd
     * @param sumjtqfd sumjtqfd
     */
    public void setSumjtqfd(BigDecimal sumjtqfd) {
        this.sumjtqfd = sumjtqfd;
    }



    
    /**
     * 获取 sumjsyzj
     * @return sumjsyzj.
     */
    public BigDecimal getSumjsyzj() {
        return sumjsyzj;
    }



    
    /**
     * 设置 sumjsyzj
     * @param sumjsyzj sumjsyzj
     */
    public void setSumjsyzj(BigDecimal sumjsyzj) {
        this.sumjsyzj = sumjsyzj;
    }



    
    /**
     * 获取 sumjsyfd
     * @return sumjsyfd.
     */
    public BigDecimal getSumjsyfd() {
        return sumjsyfd;
    }



    
    /**
     * 设置 sumjsyfd
     * @param sumjsyfd sumjsyfd
     */
    public void setSumjsyfd(BigDecimal sumjsyfd) {
        this.sumjsyfd = sumjsyfd;
    }
}
