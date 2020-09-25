package com.joyintech.service.svrcom.system.instregbankcode.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode002Dto;
import com.joyintech.tams.report.system.instregbankcode.service.SysInstRegBankCode001Service;
import com.joyintech.tams.report.system.instregbankcode.service.SysInstRegBankCode002Service;
import com.joyintech.tams.systemtools.service.ServiceInstitutionService;

/**
 * 机构登记银行代码管理画面
 *
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see SysInstRegBankCodeController
 * @since 1.0
 */
@RestController
@RequestMapping("/SysInstRegBankCode")
public class SysInstRegBankCodeController{

    /**
     * SysInstRegBankCode001Service
     */
    @Autowired
    private SysInstRegBankCode001Service sysInstRegBankCode001Service;
    
    /**
     * SysInstRegBankCode002Service
     */
    @Autowired
    private SysInstRegBankCode002Service sysInstRegBankCode002Service;

    /**
     * 机构管理service
     */
    @Autowired
    private ServiceInstitutionService institutionService;

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<SysInstRegBankCode001Dto> searchPage(@RequestBody Map<String, Object> param) {
        return sysInstRegBankCode001Service.searchForPage(param);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody List<List<String>> dataList) {
        return this.sysInstRegBankCode001Service.checkConfirmMsgForImportData(dataList);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody List<List<String>> dataList) {
        return this.sysInstRegBankCode001Service.importData(dataList);
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @PostMapping("queryExportList")
    public List<SysInstRegBankCode001Dto> queryExportList(@RequestBody Map<String, Object> param) {
        return sysInstRegBankCode001Service.queryExportList(RequestUtil.getPageParam());
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
        sysInstRegBankCode001Service.doDelete(deleteKeys);
    }
    
    /**
     * 根据主键信息查询数据
     *
     * @param paramMap 参数map
     * @return 数据
     */
    @PostMapping("getInfoData")
    public SysInstRegBankCode002Dto getInfoData(@RequestBody Map<String,Object> paramMap) {
        return sysInstRegBankCode002Service.getInfoData(paramMap);
    }
    

    @PostMapping("getInstitutionCode")
    public List<SuggestDto> getInstitutionCode(@RequestBody(required=false) String param) {
    	return institutionService.getInstitutionCode(param);
    }

    @PostMapping("getAllInstitutionCode")
    public List<SuggestDto> getAllInstitutionCode(@RequestBody(required=false) String param) {
        return institutionService.getAllInstitutionCode(param);
    }

    @PostMapping("saveData")
    public void saveData(@RequestBody SysInstRegBankCode002Dto inputDto) {
        sysInstRegBankCode002Service.saveData(inputDto);
    }
        
}
