package com.joyintech.service.chb3.maintenance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.maintenance.dto.EquiteBaseInfo001Dto;
import com.joyintech.tams.report.maintenance.service.EquityBaseInfo001Service;
import com.joyintech.tams.report.submitexamination.checkreport.service.CheckReport001Service;

/**
 * 权益基期信息维护服务
 * 
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see EquityBaseInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/EquityBaseInfo")
public class EquityBaseInfoController extends BaseController {

    /**
     * EquityBaseInfo001Service
     */
    @Autowired
    private EquityBaseInfo001Service equityBaseInfo001Service;

    /**
     * 报送前检查service
     */
    @Autowired
    private CheckReport001Service checkReport001Service;



    /**
     * 产品名称下拉框
     * 
     * @return 产品名称列表
     */
    @PostMapping("getPrdName")
    public List<SuggestDto> getPrdName(@RequestBody  Map<String,Object> paramMap) {
        return checkReport001Service.queryPrdNameList(paramMap);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EquiteBaseInfo001Dto> search(@RequestBody Map<String, Object> param) {
        return equityBaseInfo001Service.searchForPage(param);
    }

    /**
     * 导入数据文件
     * 
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
	@PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
    	 @SuppressWarnings("rawtypes")
		List<List<String>> dataList= (List)param.get("list");
    			 String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
    			 String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        return this.equityBaseInfo001Service.importData(dataList, deptCode,
        		orgCode);
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @PostMapping("queryReportList")
    public List<EquiteBaseInfo001Dto> queryReportList(@RequestBody Map<String, Object> param){
        return equityBaseInfo001Service.queryReportList(param);
    }

    /**
     * 删除数据
     * <p>
     * 逻辑删除数据，并返回处理结果
     * </p>
     * 
     * @return 处理结果
     */
    @PostMapping("doDelete")
    public void doDelete(@RequestBody String deleteKeys) {
        equityBaseInfo001Service.doDelete(deleteKeys);
    }
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody List<List<String>> dataList) {
        return equityBaseInfo001Service.checkConfirmMsgForImportData(dataList);
    }
   
}
