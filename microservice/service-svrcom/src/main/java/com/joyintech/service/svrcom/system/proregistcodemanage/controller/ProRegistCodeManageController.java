package com.joyintech.service.svrcom.system.proregistcodemanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitexamination.checkreport.service.CheckReport001Service;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
import com.joyintech.tams.report.system.proregistcodemanage.service.ProRegistCodeManage001Service;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see ProRegistCodeManageController
 * @since 1.0
 */
@RestController
@RequestMapping("/ProRegistCodeManage")
public class ProRegistCodeManageController{
    /**
     * ProRegistCodeManage001Service
     */
    @Autowired
    private ProRegistCodeManage001Service proRegistCodeManage001Service;
    /**
     * 报送前检查service//使用机构代码下拉框
     */
    @Autowired
    private CheckReport001Service checkReport001Service;


    /**
     * 产品下拉框
     * @return 产品代码和名称列表
     */
    @PostMapping("getProd")
    public List<SuggestDto> getProd(@RequestBody  Map<String,Object> paramMap) {
        return checkReport001Service.queryPrdNameList(paramMap);
    }
    
    /**
     * 产品登记编码新建产品代码下拉框
     * @return 产品代码和名称列表
     */
    @PostMapping("getProdInfo")
    public List<SuggestDto> getProdInfo(@RequestBody  Map<String,Object> paramMap) {
        return checkReport001Service.getProdInfo(paramMap);
    }
    
    /**
     * 产品登记编码下拉框
     * @return 产品名称列表
     */
    @PostMapping("getRegCode")
    public List<SelectOptionDto> getRegCode(@RequestBody Map<String,Object> paramMap) {
        return proRegistCodeManage001Service.queryPrdRegList(paramMap);
    }
    
    /**
     * 查询列表
     *
     * @return DtPage
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ProRegistCodeManage001Dto> search(@RequestBody Map<String,Object> paramMap) {
        return proRegistCodeManage001Service.searchForPage(paramMap);
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
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
		String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
		String bankCode=param.get("bankCode")==null?null:param.get("bankCode").toString();
        return this.proRegistCodeManage001Service.importData(dataList,tempList,bankCode,orgCode,deptCode);
    }

    /**
     * 导出excel查询
     *
     * @return 结果数据
     */
    @PostMapping("queryReportList")
    public List<ProRegistCodeManage001Dto> queryReportList(@RequestBody Map<String,Object> paramMap) {
        return proRegistCodeManage001Service.queryReportList(paramMap);
        
    }
    /**
     * 检查产品是否存在交易或者存在基期数据
     * @param orgCodes 机构代码
     * @param prodIds 产品ids
     * @return WebResult
     * @see
     * @since 1.0
     */
    @PostMapping("selectProdIsUsed")
    public Integer selectProdIsUsed(@RequestBody Map<String,Object> paramMap) {
    	return proRegistCodeManage001Service.selectProdIsUsed(paramMap);
    }
    /**
     * 删除
     *
     * @param  deleteKeys
     * @return 导出
     */
    @PostMapping("deteteRegCode")
    public void deleteRegCode(@RequestBody String deleteKeys) {
        proRegistCodeManage001Service.deteteRegCode(deleteKeys);
       
    }
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
    	List<List<String>> dataList = (List<List<String>>) param.get("dataList");
    	String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
		String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return proRegistCodeManage001Service.checkConfirmMsgForImportData(dataList,orgCode,deptCode);
    }

    /**
     *
     * 产品代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @PostMapping("getProdinfo")
    public List<ChbPrdBaseinfo> getProdinfo(@RequestBody Map<String, Object> param) {
        List<ChbPrdBaseinfo> list = proRegistCodeManage001Service.getProdinfo(param);
        return list;
    }
}
