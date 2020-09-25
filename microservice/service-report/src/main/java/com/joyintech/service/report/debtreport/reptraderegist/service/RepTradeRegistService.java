/*
 * 文件名：RepTradeRegistService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.reptraderegist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.reptraderegist.dao.RepTradeRegistMapper;
import com.joyintech.service.report.debtreport.reptraderegist.dto.RepTradeRegistDto;

/**
 * 交易要素登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepTradeRegistService
 * @since 1.0
 */
@Service
public class RepTradeRegistService extends BaseService<RepTradeRegistDto>{
    @Autowired
    private RepTradeRegistMapper repTradeRegistMapper;
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam Map<String, Object>
     * @return Page<RepFinProductRegistCodeMngDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<RepTradeRegistDto> findPage(int page, int length, Map<String, Object> map)
        throws Exception {
        return super.queryForPage(page, length, () -> repTradeRegistMapper.findPage(map));
    }
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getProductCode(Map<String,Object> map){
        return repTradeRegistMapper.getProductCode(map);
    }
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepFinprodId(Map<String,Object> map){
        return repTradeRegistMapper.getRepFinprodId(map);
    }
    /**
     * 获取行内交易编号下拉框集合
     * @return 行内交易编号下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepBankTradeCode(Map<String,Object> map){
        return repTradeRegistMapper.getRepBankTradeCode(map);
    }
    /**
     * 获取资产代码下拉框集合
     * @return 资产代码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepBankAssetCode(Map<String,Object> map){
        return repTradeRegistMapper.getRepBankAssetCode(map);
    }
    /**
     * 获取金融产品登记编码下拉框集合
     * @return 金融产品登记编码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepFinprodRegId(Map<String,Object> map){
        return repTradeRegistMapper.getRepFinprodRegId(map);
    }
}
