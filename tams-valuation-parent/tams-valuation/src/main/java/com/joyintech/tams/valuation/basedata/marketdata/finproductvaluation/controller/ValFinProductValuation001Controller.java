/*
 * 文件名：ValFinProductValuation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.finproductvaluation.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.basedata.marketdata.finproductvaluation.dto.ResultDto;
import com.joyintech.tams.valuation.basedata.marketdata.finproductvaluation.service.ValFinProductValuation001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年7月20日
 * @see ValFinProductValuation001Controller
 * @since 1.0
 */
@Controller
@PageId("ValFinProductValuation001")
@RequestMapping("/valuation/basedata/marketdata/finproductvaluation/ValFinProductValuation001")
public class ValFinProductValuation001Controller extends BaseController {

    /**
     * ValFinProductValuation001Service
     */
    @Autowired
    private ValFinProductValuation001Service valFinProductValuation001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.基金类型);
        this.setSelectOptionToModel(model, DictCode.估值来源);
        //model.addAttribute("defaultFundType", DictCodeList.基金类型_货币基金.getValue());
        return super.init(model);
    }

    /**
     * 表格加载
     * 
     * @return List<ResultDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping("tableLoading")
    @ResponseBody
    public Page<ResultDto> tableLoad() throws Exception {
//        String finprodId = this.getParam("finprodId");
//        String fundtype = this.getParam("fundtype");
//        String fundAbbr = this.getParam("fundAbbr");
//        String valuationStartDate = this.getParam("valuationStartDate");
//        String valuationEndDate = this.getParam("valuationEndDate");
//        Map<String, Object> params = valFinProductValuation001Service.getParams(finprodId, fundtype,fundAbbr,
//            valuationStartDate, valuationEndDate);
//        ValFinProductValuation001SearchDto dto = (ValFinProductValuation001SearchDto)JsonUtils.map2entity(
//                params, ValFinProductValuation001SearchDto.class);
    	Map<String, Object> params = RequestUtil.getPageParam();
    	params.put("finprodType", DictCodeListExt1.金融产品类型_基金.getValue());
        return valFinProductValuation001Service.tableLoadPage(params);
    }

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
        String finprodId = this.getParam("finprodId");
        String fundtype = this.getParam("fundtype");
        String fundAbbr = this.getParam("fundAbbr");
        String valuationStartDate = this.getParam("valuationStartDate");
        String valuationEndDate = this.getParam("valuationEndDate");
        Map<String, Object> params = valFinProductValuation001Service.getParams(finprodId, fundtype,fundAbbr,
            valuationStartDate, valuationEndDate);
//        ValFinProductValuation001SearchDto dto = (ValFinProductValuation001SearchDto)JsonUtils.map2entity(
//                params, ValFinProductValuation001SearchDto.class);
        List<ResultDto> listdto;
        if (datas == null) {
            listdto = valFinProductValuation001Service.tableLoad(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ResultDto.class);
        }
        File outFile = valFinProductValuation001Service.makeFile(
            "/com/joyintech/tams/valuation/basedata/marketdata/finproductvaluation/conf/export_finproductvaluation.xml",
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
