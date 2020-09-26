/*
 * 文件名：InvestorsHoldInfo001FileReadParaDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年8月10日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto;

import java.util.Map;

/**
 * 投资者持有信息上游接口接入多线程处理，可以事先查询的内容dto
 *
 * @author 刘二通
 * @version 1.0 2018年8月10日
 * @see InvestorsHoldInfo001FileReadParaDto
 * @since 1.0
 */
public class InvestorsHoldInfo001FileReadParaDto {

    /**
     * 所有的登记银行编码
     */
    private Map<String, String> registMap;

    /**
     * 所有的产品登记编码
     */
    private Map<String, String> prdRegistMap;

    /**
     * 当天的持有数据是否存在
     */
    private boolean isTodayDataExist;

    /**
     * 获取 isTodayDataExist
     * @return isTodayDataExist.
     */
    public boolean isTodayDataExist() {
        return isTodayDataExist;
    }


    /**
     * 设置 isTodayDataExist
     * @param isTodayDataExist isTodayDataExist
     */
    public void setTodayDataExist(boolean isTodayDataExist) {
        this.isTodayDataExist = isTodayDataExist;
    }

    /**
     * 获取 registMap
     *
     * @return registMap.
     */
    public Map<String, String> getRegistMap() {
        return registMap;
    }

    /**
     * 设置 registMap
     *
     * @param registMap registMap
     */
    public void setRegistMap(Map<String, String> registMap) {
        this.registMap = registMap;
    }

    /**
     * 获取 prdRegistMap
     *
     * @return prdRegistMap.
     */
    public Map<String, String> getPrdRegistMap() {
        return prdRegistMap;
    }

    /**
     * 设置 prdRegistMap
     *
     * @param prdRegistMap prdRegistMap
     */
    public void setPrdRegistMap(Map<String, String> prdRegistMap) {
        this.prdRegistMap = prdRegistMap;
    }

}
