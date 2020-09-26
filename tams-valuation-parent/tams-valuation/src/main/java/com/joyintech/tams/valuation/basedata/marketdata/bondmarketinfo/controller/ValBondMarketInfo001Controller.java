/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.bondmarketinfo.controller;

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
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basedata.marketdata.bondmarketinfo.dto.ValBondMarketInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.bondmarketinfo.service.ValBondMarketInfo001Service;

/**
 * 
 * 每日债券行情
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ValBondMarketInfo001
 * @since 1.0
 */
@Controller
@PageId("ValBondMarketInfo001")
@RequestMapping("/valuation/basedata/marketdata/bondmarketinfo/ValBondMarketInfo001")
public class ValBondMarketInfo001Controller extends BaseController {
    
    /**
     *每日债券行情service
     */
    @Autowired
    private ValBondMarketInfo001Service valBondMarketInfo001Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        this.setSelectOptionToModel(model, DictCode.估值交易市场);// 估值来源
//        this.setSelectOptionToModel(model, DictCode.估值来源);
        return super.init(model);
    }
    
    /**
     * 页面查询
     * @return 列表信息
     * @throws Exception 异常
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValBondMarketInfo001Dto> queryPageList() throws Exception{
        Map<String, Object> params= RequestUtil.getPageParam();
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
//        ValBondMarketInfo001SearchDto dto = (ValBondMarketInfo001SearchDto)JsonUtils.map2entity(params, ValBondMarketInfo001SearchDto.class);
        return valBondMarketInfo001Service.queryPageList(params);
    }
    
    /**
     * 
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     * 
     * @return paraMap
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期区间
        String valueDateBegin = this.getParam("valueDateBegin");
        String valueDateEnd = this.getParam("valueDateEnd");
        if(StringUtil.isNotEmpty(valueDateBegin)){
            paraMap.put("valueDateBegin", DateUtil.parseDate(valueDateBegin, "yyyy-MM-dd"));
        }else{
            paraMap.remove("valueDateBegin");
        }
        if(StringUtil.isNotEmpty(valueDateEnd)){
            paraMap.put("valueDateEnd", DateUtil.parseDate(valueDateEnd, "yyyy-MM-dd"));
        }else{
            paraMap.remove("valueDateEnd");
        }
        return paraMap;
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
        Map<String, Object> params= RequestUtil.getPageParam();
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        List<ValBondMarketInfo001Dto> listdto ;
        if (datas == null) {
            listdto = valBondMarketInfo001Service.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValBondMarketInfo001Dto.class);
        }
        File outFile = valBondMarketInfo001Service.makeFile(
            "/com/joyintech/tams/valuation/basedata/marketdata/bondmarketinfo/config/export_bondmarket.xml",
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
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return valBondMarketInfo001Service.checkConfirmMsgForImportData(fileName);
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
        return this.valBondMarketInfo001Service.importData(fileName);

    }
}

