/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.fundmarketinfo.controller;

import java.io.File;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.service.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;
import com.joyintech.service.basicdata.marketdata.fundmarketinfo.service.ComFundMarketInfo001Service;
import com.joyintech.service.basicdata.marketdata.fundmarketinfo.service.ComFundMarketQuotationInfoImportDataService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangqidong
 * @version 1.0 2018年7月17日
 * @see ComFundMarketInfo001Controller
 * @since 1.0
 */
@RestController("serviceComFundMarketInfo001Controller")
@RequestMapping("/ComFundMarketInfo001")
public class ComFundMarketInfo001Controller extends BaseController {

    /**
     * valFundMarketInfo001Service
     */
    @Autowired
    private ComFundMarketInfo001Service comFundMarketInfo001Service;
    
    @Autowired
    private ComFundMarketQuotationInfoImportDataService comFundMarketQuotationInfoImportDataService;

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
    @PostMapping("/tableLoad")
    public List<ComFundMarketInfo001dto> tableLoad(@RequestBody Map<String, Object> params)
        throws Exception {
//        Map<String, Object> params = RequestUtil.getPageParam();
        return comFundMarketInfo001Service.tableLoad(params);
    }
    @PostMapping("/findPage/{page}/{length}")
    public Page<ComFundMarketInfo001dto> findPage(@RequestBody Map<String, Object> params,
                                                  @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return comFundMarketInfo001Service.findPage(page,length,params);
    }

    //add jinmaodong 导出不在微服务端实现
//    /**
//     * 导出数据 <一句话功能简述> <功能详细描述>
//     *
//     * @return List<ValFundMarketInfo001dto>
//     * @throws Exception Exception
//     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export()
//        throws Exception {
//
//        String datas = this.getParam("datas");
//        Map<String, Object> params = RequestUtil.getPageParam();
//        List<ComFundMarketInfo001dto> listdto;
//
//        if (datas == null) {
//            listdto = comFundMarketInfo001Service.tableLoad(params);
//        }
//        else {
//            listdto = JsonUtils.json2List(datas, ComFundMarketInfo001dto.class);
//        }
//
//        File outFile = comFundMarketInfo001Service.makeFile(
//            "/com/joyintech/tams/valuation/basedata/marketdata/fundmarketinfo/config/export_conf.xml",
//            listdto);
//        return ExportUtil.export(
//            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
//                                 + DateUtil.format(DateUtil.platDateFromCache())
//                                 + GlobalConstant.CLOSE_PARENTHESIS
//                                 + GlobalConstant.POINT
//                                 + FileNameUtil.getFileExtName(outFile),
//            outFile, true);
//    }

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
     * @param param 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return comFundMarketQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
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
        return this.comFundMarketQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }

}
