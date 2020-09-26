/*
 * 文件名：ValExchangeRateInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.exchangerateinfo.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
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
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basedata.marketdata.exchangerateinfo.dto.ValExchangeRateInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.exchangerateinfo.service.ValExchangeRateInfo001Service;

/**
 * 汇率信息
 * 
 * @author wangqidong
 * @version 1.0 2018年8月7日
 * @see ValExchangeRateInfo001Controller
 * @since 1.0
 */

@Controller
@PageId("ValExchangeRateInfo001")
@RequestMapping("/valuation/basedata/marketdata/exchange/ValExchangeRateInfo001")
public class ValExchangeRateInfo001Controller extends BaseController {

    /**
     * valExchangeRateInfo001Service
     */
    @Autowired
    private ValExchangeRateInfo001Service valExchangeRateInfo001Service;
    /**
     * 重写init方法 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.基金类型);
        //this.setSelectOptionToModel(model, DictCode.估值交易市场);
        return super.init(model);
    }
    
    /**
     * 加载表格
     * <功能详细描述>
     * 
     * @return List<ValExchangeRateInfo001Dto>
     * @throws Exception Exception
     */
    @RequestMapping("tableLoad")
    @ResponseBody
    public Page<ValExchangeRateInfo001Dto> tableLoad() throws Exception {
        Map<String, Object> params = RequestUtil.getPageParam();
        //ValExchangeRateInfo001Dto valExchangeRateInfo001Dto = (ValExchangeRateInfo001Dto)JsonUtils.map2entity(params, ValExchangeRateInfo001Dto.class);
        return valExchangeRateInfo001Service.findPage(params);
    }
    
    

    /**
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型 
     * 
     * @return 日期区间
     * @throws ParseException ParseException
     */
//    public Map<String, Object> getRequestMap()
//        throws ParseException {
//        Map<String, Object> paraMap = RequestUtil.getPageParam();
//        // 估值日期区间
//        String startDate = this.getParam("startDate");
//        String endDate = this.getParam("endDate");
//        if (StringUtil.isNotEmpty(startDate)) {
//            paraMap.put("startDate", DateUtil.parseDate(startDate, "yyyy-MM-dd"));
//        }
//        if (StringUtil.isNotEmpty(endDate)) {
//            paraMap.put("endDate", DateUtil.parseDate(endDate, "yyyy-MM-dd"));
//        }
//
//        return paraMap;
//    }
    
    /**
     * 导出数据
     * 
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> params = RequestUtil.getPageParam();
//        ValExchangeRateInfo001Dto valExchangeRateInfo001Dto = (ValExchangeRateInfo001Dto)JsonUtils.map2entity(
//            params, ValExchangeRateInfo001Dto.class);
        List<ValExchangeRateInfo001Dto> listdto;
        if (datas == null) {
            listdto = valExchangeRateInfo001Service.tableLoad(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValExchangeRateInfo001Dto.class);
        }
        File outFile = valExchangeRateInfo001Service.makeFile(
            "/com/joyintech/tams/valuation/basedata/marketdata/exchangerateinfo/config/export_valexchangerateinfo001.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
}
