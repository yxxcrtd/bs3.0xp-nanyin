/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.fundmarketinfo.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
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
import com.joyintech.tams.valuation.basedata.marketdata.fundmarketinfo.dto.ValFundMarketInfo001dto;
import com.joyintech.tams.valuation.basedata.marketdata.fundmarketinfo.service.ValFundMarketInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangqidong
 * @version 1.0 2018年7月17日
 * @see ValBondMarketInfo001
 * @since 1.0
 */
@Controller
@PageId("ValFundMarketInfo001")
@RequestMapping("/valuation/basedata/marketdata/fundmarketinfo/ValFundMarketInfo001")
public class ValFundMarketInfo001Controller extends BaseController {

    /**
     * valFundMarketInfo001Service
     */
    @Autowired
    private ValFundMarketInfo001Service valFundMarketInfo001Service;

    /**
     * 重写init方法 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.基金类型);
        this.setSelectOptionToModel(model, DictCode.估值交易市场);
        return super.init(model);
    }

    /**
     * 加载表格
     * <功能详细描述>
     * 
     * @return List<ValFundMarketInfo001dto>
     * @throws Exception Exception
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValFundMarketInfo001dto> tableLoad()
        throws Exception {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType", DictCodeListExt1.金融产品类型_基金.getValue());
        return valFundMarketInfo001Service.findPage(params);
    }

    /**
     * 导出数据 <一句话功能简述> <功能详细描述>
     * 
     * @return List<ValFundMarketInfo001dto>
     * @throws Exception Exception
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {

        String datas = this.getParam("datas");
        Map<String, Object> params = RequestUtil.getPageParam();
        List<ValFundMarketInfo001dto> listdto;

        if (datas == null) {
            listdto = valFundMarketInfo001Service.tableLoad(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValFundMarketInfo001dto.class);
        }

        File outFile = valFundMarketInfo001Service.makeFile(
            "/com/joyintech/tams/valuation/basedata/marketdata/fundmarketinfo/config/export_valfundmarketinfo001.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(DateUtil.platDateFromCache())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型 <一句话功能简述> <功能详细描述>
     * 
     * @return 日期区间
     * @throws ParseException ParseException
     */
    public Map<String, Object> getRequestMap()
        throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期区间
        String startDate = this.getParam("startDate");
        String endDate = this.getParam("endDate");
        if (StringUtil.isNotEmpty(startDate)) {
            paraMap.put("startDate", DateUtil.parseDate(startDate, "yyyy-MM-dd"));
        }
        if (StringUtil.isNotEmpty(endDate)) {
            paraMap.put("endDate", DateUtil.parseDate(endDate, "yyyy-MM-dd"));
        }

        return paraMap;
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return valFundMarketInfo001Service.checkConfirmMsgForImportData(fileName);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.valFundMarketInfo001Service.importData(fileName);

    }

}
