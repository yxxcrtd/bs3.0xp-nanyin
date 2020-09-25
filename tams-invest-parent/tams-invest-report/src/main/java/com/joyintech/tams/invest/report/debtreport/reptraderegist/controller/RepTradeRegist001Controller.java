/*
 * 文件名：RepTradeRegist.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.reptraderegist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.report.debtreport.reptraderegist.dto.RepTradeRegistDto;
import com.joyintech.tams.invest.report.debtreport.reptraderegist.service.RepTradeRegist001Service;

/**
 * 交易要素登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepTradeRegist001Controller
 * @since 1.0
 */
@Controller
@PageId("RepTradeRegist001")
@RequestMapping("/invest/report/debtreport/reptraderegist/RepTradeRegist001")
public class RepTradeRegist001Controller extends BaseController{
    /**
     * 注入Service
     */
    @Autowired
    private RepTradeRegist001Service repTradeRegist001Service;
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)   
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.是否);
        return super.init(model);
    }
    
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepTradeRegistDto> findPage(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repTradeRegist001Service.findPage(map);
    }
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getRepFinprodId")
    public List<SelectOptionDto> getRepFinprodId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repTradeRegist001Service.getRepFinprodId(map);
    }
    /**
     * 获取行内交易编号下拉框集合
     * @return 行内交易编号下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getRepBankTradeCode")
    public List<SelectOptionDto> getRepBankTradeCode(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repTradeRegist001Service.getRepBankTradeCode(map);
    }
    /**
     * 获取资产代码下拉框集合
     * @return 资产代码下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getRepBankAssetCode")
    public List<SelectOptionDto> getRepBankAssetCode(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repTradeRegist001Service.getRepBankAssetCode(map);
    }
    /**
     * 获取金融产品登记编码下拉框集合
     * @return 金融产品登记编码下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getRepFinprodRegId")
    public List<SelectOptionDto> getRepFinprodRegId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repTradeRegist001Service.getRepFinprodRegId(map);
    }
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return repTradeRegist001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
