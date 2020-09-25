/*
 * 文件名：TrdBondInvestTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
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
import com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.dao.UpTrInqBuyBackMapper;
import com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.dto.UpTrInqBuyBackDto;
import com.joyintech.service.report.poc.directtransaction.uptrinqsec.dto.UpTrInqSecDto;

/**
 * 询价交易-现券买卖Service
 *
 * @author pcz
 * @version 1.0 2018年9月7日
 * @see TrdBondInvestTradeService
 * @since 1.0
 */
@Service("backUpTrInqBuyBackService")
public class UpTrInqBuyBackService extends BaseService<UpTrInqSecDto> {

    /**
     * UpTrInqSecMapper
     */
    @Autowired
    private UpTrInqBuyBackMapper upTrInqBuyBackMapper;

    /**
     * 组合代码：下拉框
     *
     * @param param 画面参数
     * @return 组合代码：下拉框
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String, Object> param) {
        List<SelectOptionDto> resultList = upTrInqBuyBackMapper.getPortfolioId(param);
        return resultList;
    }

    /**
     * 分页查询 市场代码
     *
     * @return
     */
    public List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param) {
        List<SelectOptionDto> resultList = upTrInqBuyBackMapper.getFinprodMarketId(param);
        return resultList;
    }

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
        return upTrInqBuyBackMapper.getCounterId(map);
    }
    
    /**
     * 根据主键查询委托信息
     */
    public UpTrInqBuyBackDto selectByPrimryKey(String entrustId){
    	return upTrInqBuyBackMapper.selectByPrimryKey(entrustId);
    }
    
}
