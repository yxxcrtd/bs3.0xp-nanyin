/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.bondmarketinfo.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.service.ComBondMarketInfo001Service;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.service.ComBondMarketQuotationInfoImportDataService;
import com.joyintech.tams.constant.DictCode;

/**
 * 
 * 每日债券行情
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Controller
 * @since 1.0
 */
@RestController(value="serviceComBondMarketInfo001Controller")
@RequestMapping("/ComBondMarketInfo001")
public class ComBondMarketInfo001Controller extends BaseController {
    
    /**
     *每日债券行情service
     */
    @Autowired
    private ComBondMarketInfo001Service comBondMarketInfo001Service;
    
    @Autowired
    private ComBondMarketQuotationInfoImportDataService comBondMarketQuotationInfoImportDataService;
    
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
    @PostMapping("findPage/{page}/{length}")
    public Page<ComBondMarketInfo001Dto> queryPageList(@RequestBody Map<String, Object> params,
                                                       @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
//        Map<String, Object> params= this.getRequestMap();
//        ValBondMarketInfo001SearchDto dto = (ValBondMarketInfo001SearchDto)JsonUtils.map2entity(params, ValBondMarketInfo001SearchDto.class);
        return comBondMarketInfo001Service.queryPageList(page,length,params);
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
    @PostMapping("/queryAllList")
    public List<ComBondMarketInfo001Dto> queryAllList(@RequestBody Map<String, Object> params) throws Exception{
            return comBondMarketInfo001Service.queryAllList(params);
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param param 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return comBondMarketQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
    }


    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return this.comBondMarketQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }

}

