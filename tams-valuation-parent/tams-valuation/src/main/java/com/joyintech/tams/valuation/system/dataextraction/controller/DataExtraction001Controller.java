/*
 * 文件名：DataExtraction001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.system.dataextraction.controller;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.WebResult;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ExportEntity;
import com.joyintech.tams.valuation.system.dataextraction.service.DataExtraction001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 数据提取
 * @author wangqidong
 * @version 1.0 2019年2月18日
 * @see DataExtraction001Controller
 * @since 1.0
 */
@Controller
@PageId("DataExtraction001")
@RequestMapping("/valuation/system/dataextraction/DataExtraction001")
public class DataExtraction001Controller extends BaseController{
    
    @Autowired
    private DataExtraction001Service dataExtraction001Service;
	/**
     * {@inheritDoc}
     */
    @RequestMapping("init")
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.是否);
        
        return super.init(model);
    }
    
    @RequestMapping("checkSearch")
    @ResponseBody
    public WebResult checkSearch(HttpSession session) throws Exception {
        WebResult webResult = new WebResult();
        try {
            String sqlText = this.getRequest().getParameter("sqlText").replace(";", "");
            if (StringUtil.isEmpty(sqlText) || StringUtil.isEmpty(sqlText.trim())) {
                webResult.appendMessage(new ValidMessage("sql不能为空！"));
            }else {
                if (sqlText.trim().toUpperCase().indexOf("SELECT") == 0 || sqlText.trim().toUpperCase().indexOf("WITH") == 0) {
                	Page<LinkedHashMap<String, Object>> resultListPage = this.dataExtraction001Service.querySelectAllDataPage(this.getParam("dataSource"), sqlText);
                    List<LinkedHashMap<String, Object>> resultList = resultListPage.getRoot();
                    if (resultList.size()==0) {
                        webResult.appendMessage(new ValidMessage("没有符合条件的记录！"));
                    } else {
                    	webResult.setData(resultListPage.getRecords());
                    }
                }else{
                    webResult.appendMessage(new ValidMessage("非法执行语句"));
                }
            }
            session.setAttribute("search", sqlText);
        }
        catch (Exception e) {
            webResult.setMessage("操作异常，请尝试切换数据源！", MessageType.错误消息);
            getLogger().error(e.getMessage());
        }
        return webResult;
    }
    
    @RequestMapping("search")
    @ResponseBody
    public Page<ExportEntity> search() throws Exception {
        String sqlText = this.getRequest().getParameter("sqlText").replace(";", "");
        String queryHead = this.getRequest().getParameter("queryHead");
        Page<ExportEntity> page = new Page<ExportEntity>();
        List<ExportEntity> allData = new ArrayList<>();
        try {
            if (sqlText.trim().toUpperCase().indexOf("SELECT") == 0 || sqlText.trim().toUpperCase().indexOf("WITH") == 0) {
            	Page<LinkedHashMap<String, Object>> resultListPage = this.dataExtraction001Service.querySelectAllDataPage(this.getParam("dataSource"), sqlText);
                List<LinkedHashMap<String, Object>> resultList = resultListPage.getRoot();
                // 所有查询结果，最外面的List为行数据，里面的List为列数据     
                if (resultList.size()>0) {
                	//设置全局变量表头
                	List<String> resultColumns = new ArrayList<>(resultList.get(0).keySet());
                    //看查询参数是否只需要表头还是不需要表头，需要内容
                	if(DictCodeList.是否_是.getValue().equals(queryHead)){
                		allData.add(this.listToEntity(resultColumns));
                	}else{
                        for (LinkedHashMap<String, Object> map : resultList) {
                            List<String> rowList = new ArrayList<>();
                            for (String columnName : resultColumns) {
                                Object value = map.get(columnName.trim());
                                rowList.add(String.valueOf(value));
                            }
                            allData.add(listToEntity(rowList));
                        }
                        page.setPage(resultListPage.getPage());
                    	page.setTotal(resultListPage.getTotal());
                    	page.setRecords(resultListPage.getRecords());

                	}
                	page.setRoot(allData);
                }      
            }
            else{
                ExportEntity ee2 = new ExportEntity();
                ee2.setColumn1("不支持的sql类型，请检查后重新输入");
                allData.add(ee2);
            }
        }
        catch (Exception e) {
            ExportEntity ee = new ExportEntity();
            ee.setColumn1(e.getMessage());
            allData.add(ee);
            ExportEntity ee2 = new ExportEntity();
            ee2.setColumn1(e.getMessage());
            allData.add(ee2);
        }
        
        return page;
    }
    
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    @SuppressWarnings("unchecked")
    public ResponseEntity<byte[]> export()
        throws Exception {
        String sqlText = this.getRequest().getParameter("sqlText").replace(";", "");
    	Page<LinkedHashMap<String, Object>> resultListPage = this.dataExtraction001Service.querySelectAllDataPage(this.getParam("dataSource"), sqlText);
        List<LinkedHashMap<String, Object>> resultList = resultListPage.getRoot();  
        List<String> resultColumns = new ArrayList<>(resultList.get(0).keySet());
        String datas = this.getParam("datas");
        List<ExportEntity> allData = new ArrayList<>();
        if (StringUtils.isEmpty(datas) ) {            
            allData.add(this.listToEntity(resultColumns));
            for (LinkedHashMap<String, Object> map : resultList) {
                List<String> rowList = new ArrayList<>();
                for (String columnName : resultColumns) {
                    Object value = map.get(columnName.trim());
                    rowList.add(String.valueOf(value));
                }
                allData.add(listToEntity(rowList));
            }
        }
        else {
             allData.add(this.listToEntity(resultColumns));             
             List<ExportEntity> allData1 = new ArrayList<>();
             allData1 = JsonUtils.json2List(datas, ExportEntity.class);
             allData.addAll(allData1);
           
        }
        File outFile = dataExtraction001Service.makeFile(
            "/com/joyintech/tams/valuation/system/dataextraction/conf/export_DataExtraction001.xml",
            allData);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT_SSS)
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    private ExportEntity listToEntity(List<String> allColumns)
        throws Exception {
        ExportEntity entity = new ExportEntity();
        int index = 0;
        while (index < 100 && index < allColumns.size()) {
            Method method = entity.getClass().getDeclaredMethod("setColumn" + (index + 1), String.class);
            method.invoke(entity, allColumns.get(index));
            index ++ ;
        }
        while (index < 150 && index < allColumns.size()) {
            Method method = entity.getClass().getDeclaredMethod("setName" + (index - 100 + 1), String.class);
            method.invoke(entity, allColumns.get(index));
            index ++ ;
        }
        return entity;
    }
 
}






