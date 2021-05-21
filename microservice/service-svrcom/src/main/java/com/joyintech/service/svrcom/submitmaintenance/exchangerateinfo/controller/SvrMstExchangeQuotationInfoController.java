package com.joyintech.service.svrcom.submitmaintenance.exchangerateinfo.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dto.SvrMstExchangeQuotationInfoDto;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.service.SvrMstExchangeQuotationInfoService;

/**
 * 汇率行情 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see SvrMstExchangeQuotationInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/SvrMstExchangeQuotationInfo")
public class SvrMstExchangeQuotationInfoController {

    /**
     * 汇率行情service
     */
    @Autowired
    private SvrMstExchangeQuotationInfoService svrMstExchangeQuotationInfoService;
    
    /**
     * 汇率行情分页查询 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<SvrMstExchangeQuotationInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam) throws Exception{
        return svrMstExchangeQuotationInfoService.selectForPage(pageParam);
    }
    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectAllList")
    public List<SvrMstExchangeQuotationInfoDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return svrMstExchangeQuotationInfoService.selectAllList(pageParam);
    }
    /**
     * 
     * 保存操作
     * <功能详细描述>
     * @param map 参数
     * @see
     * @since 1.0
     */
    @RequestMapping("/doComplete")
    public void doComplete(@RequestBody SvrMstExchangeQuotationInfoDto dto){
        svrMstExchangeQuotationInfoService.doComplete(dto);
    }
    /**
     * 
     * 保存操作
     * <功能详细描述>
     * @param map 参数
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForExport")
    public List<SvrMstExchangeQuotationInfoDto> selectForExport(@RequestBody Map<String, Object> pageParam){
        return svrMstExchangeQuotationInfoService.selectForExport(pageParam);
    }

    /**
     * 导入数据文件
     *
     * @param  param 导入map，包含fileName
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
        return this.svrMstExchangeQuotationInfoService.importData(dataList,tempList,userName,orgCode,deptCode);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param param 导入map，包含fileName
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return svrMstExchangeQuotationInfoService.checkConfirmMsgForImportData(dataList);
    }
}
