package com.joyintech.service.chb3.submitmaintenance.salestransactionmaintenance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dto.SalesTransactionMaintenanceDto;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.service.SalesTransactionMaintenance001Service;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.service.SalesTransactionMaintenance002Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 销售结算资金交易信息维护
 */
@RestController
@RequestMapping("/SalesTransactionMaintenance")
public class SalesTransactionMaintenanceController {

    /**
     * dao
     */
    @Autowired
    private SalesTransactionMaintenance001Service salestransactionmaintenance001Service;
    
    @Autowired
    private SalesTransactionMaintenance002Service salestransactionmaintenance002Service;
    
    /**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
  
    /**
     * 分页查询列表
     *
     * @return Page
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<SalesTransactionMaintenanceDto> findPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length)
    {
        return salestransactionmaintenance001Service.selectForPage(page,length,pageParam);
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
    public List<SalesTransactionMaintenanceDto> queryAllList(@RequestBody Map<String, Object> params) throws Exception{
        return salestransactionmaintenance001Service.queryAllList(params);
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
        return salestransactionmaintenance001Service.checkConfirmMsgForImportData(dataList);
    }

    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return salestransactionmaintenance001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }

    /**
     *从info表取值设置页面
     * selectDetail
     */
    @PostMapping("selectDetail")
    public SalesTransactionMaintenanceDto selectDetail(@RequestBody Map<String, Object> param){
        return  salestransactionmaintenance002Service.selectDetail(param);
    }

    /**
     *从erro表取值设置页面
     * selectErroDetail
     */
    @PostMapping("selectErroDetail")
    public SalesTransactionMaintenanceDto selectErroDetail(@RequestBody Map<String, Object> param){
        return  salestransactionmaintenance002Service.selectErroDetail(param);
    }
    /**
     *
     * 保存
     */
    @PostMapping("Save")
    public void Save(@RequestBody SalesTransactionMaintenanceDto dto) {
        salestransactionmaintenance002Service.save(dto);
    }

    /**
     *
     * 保存
     */
    @PostMapping("update")
    public void update(@RequestBody SalesTransactionMaintenanceDto dto) {
        salestransactionmaintenance002Service.update(dto);
    }

    /**
     *
     * 删除err表并保存在info表
     */
    @PostMapping("deleteAndSave")
    public void deleteAndSave(@RequestBody SalesTransactionMaintenanceDto dto) {
        salestransactionmaintenance002Service.deleteAndSave(dto);
    }
    /**
     *
     * 删除erro
     */
    @PostMapping("deleteByPrimaryKey")
    public boolean deleteByPrimaryKey(@RequestBody SalesTransactionMaintenanceDto dto) {
        return salestransactionmaintenance002Service.deleteByPrimaryKey(dto);
    }

    /**
     * 提供销售结算资金服务机构代码下拉框
     */
    @PostMapping("getInstitutionCode")
    public List<SuggestDto> getCustomerName(@RequestBody Map<String, Object> map) {
        return salestransactionmaintenance002Service.getInstitutionCode(map);
    }

    /**
     * 登记编码下拉框
     */
    @PostMapping("getPrdRegistCode")
    public List<SuggestDto> getPrdRegistCode(@RequestBody Map<String, Object> map) {
        return salestransactionmaintenance002Service.getPrdRegistCode(map);
    }
}
