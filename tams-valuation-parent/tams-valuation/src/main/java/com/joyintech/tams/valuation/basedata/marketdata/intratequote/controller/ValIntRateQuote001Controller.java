/*
】 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.intratequote.controller;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.basedata.marketdata.intratequote.dto.ResultDto;
import com.joyintech.tams.valuation.basedata.marketdata.intratequote.service.ValIntRateQuote001Service;

/**
 * 利率信息
 * 
 * @author HuangJian
 * @version 1.0 2018年7月17日
 * @see ValBondValuationInfo001
 * @since 1.0
 */
@Controller
@PageId("ValIntRateQuote001")
@RequestMapping("/valuation/basedata/marketdata/intratequote/ValIntRateQuote001")
public class ValIntRateQuote001Controller extends BaseController {

    /**
     * ValIntRateQuote001Service
     */
    @Autowired
    private ValIntRateQuote001Service valIntRateQuote001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        String intRateId = this.getParam("intRateId");
        if(StringUtil.isNotEmpty(intRateId)){
            model.addAttribute("intRateId", intRateId);
        }else{
            model.addAttribute("intRateId", null);
        }
        return super.init(model);
    }

    /**
     * 表格加载
     * 
     * @return List<ResultDto>
     * @throws ParseException ParseException
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ResultDto> tableLoad()
        throws ParseException {
//        String rateType = this.getParam("rateType");
//        String rateName = this.getParam("rateName");
//        String intRateId = this.getParam("intRateId");
//        String relaeseStartDate = this.getParam("relaeseStartDate");
//        String relaeseEndDate = this.getParam("relaeseEndDate");
//        String effStartDate = this.getParam("effStartDate");
//        String effEndDate = this.getParam("effEndDate");
//        Map<String, Object> map = valIntRateQuote001Service.getParams(rateName,rateType,intRateId, relaeseStartDate,
//            relaeseEndDate, effStartDate, effEndDate);
        Map<String, Object> params = RequestUtil.getPageParam();
        return valIntRateQuote001Service.findPage(params);
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
        String rateType = this.getParam("rateType");
        String rateName = this.getParam("rateName");
        String intRateId = this.getParam("intRateId");
        String issueStartDate = this.getParam("issueStartDate");
        String issueEndDate = this.getParam("issueEndDate");
        String effStartDate = this.getParam("effStartDate");
        String effEndDate = this.getParam("effEndDate");
        Map<String, Object> map = valIntRateQuote001Service.getParams(rateName,rateType,intRateId, issueStartDate,
            issueEndDate, effStartDate, effEndDate);
        List<ResultDto> listdto;
        //没有选中导出所有
        if (datas == null) {
            listdto = valIntRateQuote001Service.tableLoad(map);
        }
        //导出选中
        else {
            listdto = JsonUtils.json2List(datas, ResultDto.class);
        }
        File outFile = valIntRateQuote001Service.makeFile(
            "/com/joyintech/tams/valuation/basedata/marketdata/intratequote/conf/export_intratequote.xml",
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
