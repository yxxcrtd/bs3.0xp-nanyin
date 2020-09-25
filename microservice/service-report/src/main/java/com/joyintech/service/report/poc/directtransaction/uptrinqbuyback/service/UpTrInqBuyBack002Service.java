/*
 * 文件名：UpTrInqBuyBack002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.dao.UpTrInqBuyBack002Mapper;

/**
 * 询价交易-质押式回购
 * @author wanghb
 * @version 1.0 2019年2月15日
 * @see UpTrInqBuyBack002Service
 * @since 1.0
 */
@Service("backUpTrInqBuyBack002Service")
public class UpTrInqBuyBack002Service extends BaseService{
    /**
     * upTrInqBuyBack002Mapper
     */
    @Autowired
    private UpTrInqBuyBack002Mapper upTrInqBuyBack002Mapper;
    /**
     * 
     * 查询交易对手
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterId(Map<String, Object> map){
        map.put("COUNTER_TYPE", BussinessDictCodeList.交易对手类型_内部交易对手.getValue());
        map.put("YES_NO_Y", BussinessDictCodeList.是否_是.getValue());
        map.put("YES_NO_N", BussinessDictCodeList.是否_否.getValue());
        return upTrInqBuyBack002Mapper.getCounterId(map);
    }
    /**
     * 组合代码：下拉框
     *
     * @param param 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String, Object> param) {
        List<SelectOptionDto> resultList = upTrInqBuyBack002Mapper.getPortfolioId(param);
        return resultList;
    }
    /**
     * 托管账户信息
     *
     * @param param 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctNo(Map<String, Object> param) {
        List<SelectOptionDto> resultList = upTrInqBuyBack002Mapper.getAcctNo(param);
        return resultList;
    }
    /**
     * 资金账户信息
     * @param param 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getTrdAcctId(Map<String, Object> param) {
        List<SelectOptionDto> resultList = upTrInqBuyBack002Mapper.getTrdAcctId(param);
        return resultList;
    }
}
