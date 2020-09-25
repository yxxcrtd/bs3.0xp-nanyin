package com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo001Dto;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.service.ValFxValuztionInfo001Service;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.service.ValFxValuztionQuotationInfoImportDataService;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;

/**
 * 外汇估值信息
 *
 * @author zhangwei
 * @createTime 2019-07-26 11:21
 */
@RestController(value="serviceValFxValuztionInfo001Controller")
@RequestMapping("/ValFxValuztionInfo001")
public class ValFxValuztionInfo001Controller extends BaseController {

    @Autowired
    private ValFxValuztionInfo001Service valFxValuztionInfo001Service;
    
    @Autowired
    private ValFxValuztionQuotationInfoImportDataService valFxValuztionQuotationInfoImportDataService;
    
    
    /**
     * 交易编号获取
     * @param param
     *
     * @return 返回的数据
     */
    @PostMapping("getFinprodId2")
	public List<SelectOptionDto> getFinprodId2(@RequestBody Map<String, Object> param){
		return valFxValuztionInfo001Service.getFinprodId2(param);
	}

    /**
     * 列表查询
     * @return 列表信息
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValFxValuztionInfo001Dto> queryPageList(@RequestBody Map<String, Object> params, @PathVariable("page") int page, @PathVariable("length") int length){
        return valFxValuztionInfo001Service.queryPageList(page,length,params);
    }

    /**
     * 获取增值税则表 - 不分页
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("queryAllList")
    public List<ValFxValuztionInfo001Dto> queryAllList(@RequestBody Map<String, Object> param){
        return valFxValuztionInfo001Service.queryAllList(param);
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
        return valFxValuztionQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
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
        return this.valFxValuztionQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }
}
