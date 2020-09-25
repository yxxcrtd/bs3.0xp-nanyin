package com.joyintech.service.valuation.basedata.marketdata.externaloptions.controller;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions001Dto;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions002Dto;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.service.ExternalOptionsService;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.service.ExternalOptionsService001;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.service.ValExternalOptionsQuotationInfoImportDataService;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * @author 姚轶文
 * @create 2019- 12-31 10:42
 */
@RestController(value = "ExternalOptionsController")
@RequestMapping("/ExternalOptions")
public class ExternalOptionsController extends BaseController {

    @Autowired
    ExternalOptionsService externalOptionsService;
    @Autowired
    ExternalOptionsService001 externalOptionsService001;
    @Autowired
    ValExternalOptionsQuotationInfoImportDataService valExternalOptionsQuotationInfoImportDataService;

    @PostMapping("/findFinRateInfoByBenchmarkId")
    public List<SuggestDto> findFinRateInfoByBenchmarkId(@RequestBody Map<String, Object> params)
    {
        return externalOptionsService.findFinRateInfoByBenchmarkId(params);
    }

    @PostMapping("/findByBenchmarkId")
    public String findByBenchmarkId(String finprodId2)
    {
        return externalOptionsService.findByBenchmarkId(finprodId2);
    }

    @PostMapping("/findByPrimaryKey")
    public ExternalOptions002Dto findByPrimaryKey(@RequestBody Map<String, Object> map) {
        return externalOptionsService.findByPrimaryKey(map);
    }

    @PostMapping(value = "doComplete/{action}")
    public void doComplete(@RequestBody ExternalOptions002Dto dto, @PathVariable("action") String action) {
        externalOptionsService.doComplete(dto, OperationType.valueOf(action));
    }


    @PostMapping("/selectList")
    public List<ExternalOptions001Dto> selectList(@RequestBody Map<String, Object> params)
    {
        return externalOptionsService.selectList(params);
    }

    /**
     * 列表查询
     * @return 列表信息
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ExternalOptions001Dto> queryPageList(@RequestBody Map<String, Object> params, @PathVariable("page") int page, @PathVariable("length") int length){
        return externalOptionsService001.queryPageList(page,length,params);
    }



    @PostMapping("/findTargetId")
    public List<SuggestDto> findTargetId(@RequestBody Map<String, Object> params)
    {
        return externalOptionsService.findTargetId(params);
    }

    @PostMapping("/findFinprodId")
    public List<SuggestDto> findFinprodId(@RequestBody Map<String, Object> params)
    {
        return externalOptionsService.findFinprodId(params);
    }

    /**
     * 获取增值税则表 - 不分页
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("queryAllList")
    public List<ExternalOptions001Dto> queryAllList(@RequestBody Map<String, Object> param){
        return externalOptionsService.queryAllList(param);
    }
    
    /**
     * 估值日期验证
     * @param finprodId2
     * @return
     */
    @PostMapping("/dateCheck")
    public ExternalOptions002Dto dateCheck(@RequestBody Map<String, Object> params) {
        return externalOptionsService.dateCheck(params);
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
        return valExternalOptionsQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
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
        return this.valExternalOptionsQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }
    
}
