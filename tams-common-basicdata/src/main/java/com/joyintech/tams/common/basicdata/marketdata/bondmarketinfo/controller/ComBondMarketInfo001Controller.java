/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.controller;

import java.io.File;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.service.ComBondMarketInfo001Service;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
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

/**
 * 
 * 每日债券行情
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ComBondMarketInfo001")
@RequestMapping("/common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001")
public class ComBondMarketInfo001Controller extends BaseController {
    
    /**
     *每日债券行情service
     */
    @Autowired
    private ComBondMarketInfo001Service comBondMarketInfo001Service;
    
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
    public Page<ComBondMarketInfo001Dto> queryPageList() throws Exception{
        Map<String, Object> params= RequestUtil.getPageParam();
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
//        ValBondMarketInfo001SearchDto dto = (ValBondMarketInfo001SearchDto)JsonUtils.map2entity(params, ValBondMarketInfo001SearchDto.class);
        return comBondMarketInfo001Service.queryPageList(params);
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
        List<ComBondMarketInfo001Dto> listdto ;
        if (datas == null) {
            listdto = comBondMarketInfo001Service.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ComBondMarketInfo001Dto.class);
        }
        File outFile = comBondMarketInfo001Service.makeFile(
                "/com/joyintech/tams/common/basicdata/marketdata/bondmarketinfo/config/export_conf.xml",
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
        return comBondMarketInfo001Service.checkConfirmMsgForImportData(fileName);
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
        return this.comBondMarketInfo001Service.importData(fileName);

    }
}

