package com.joyintech.tams.report.system.proregistcodemanage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see ProRegistCodeManageMService
 * @since 1.0
 */
@Service
public class ProRegistCodeManageMService extends BaseService<ProRegistCodeManage001Dto>{
    /**
     * 产品下拉框
     * @return 产品代码和名称列表
     */
	public List<SuggestDto> getProd(String param) {
		if(param==null||"".equals(param)){
			return new ArrayList<SuggestDto>();
		}
		String svrSystemType=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PRDRESIST_CODE);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("prodid", param);
		if(svrSystemType!=null&&svrSystemType.equals("01")){
			paramMap.put("type", "O");
		}else{
			paramMap.put("type", "N");
		}
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/getProd", paramMap,
	       		SuggestDto.class);
    } 
    
    
    /**
     * 产品登记编码下拉框
     * @return 产品名称列表
     */
    public List<SelectOptionDto> queryPrdRegList(Map<String,Object> paramMap) {
        return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/getRegCode", paramMap,
	       		SelectOptionDto.class);
    }
    
    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<ProRegistCodeManage001Dto> searchForPage(Map<String,Object> paramMap) {
    	String svrSystemType=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PRDRESIST_CODE);
    	if(svrSystemType!=null&&svrSystemType.equals("01")){
			paramMap.put("type", "O");
		}else{
			paramMap.put("type", "N");
		}
    	return restTemplate.postForPage(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/findPage", paramMap,
	       		ProRegistCodeManage001Dto.class);
    }
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
     	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList1(fileName));
    	param.put("tempList", ExcelUtil.getTempList1(fileName));
    	param.put("orgCode", CommonUtil.getOrgCode());
    	param.put("deptCode", CommonUtil.getDeptCode());
    	param.put("bankCode", CommonUtil.getUserBankCode());
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/importData", 
	       		param,
	       		ExcelValidDto.class);
    }

    /**
     * 导出excel查询
     *
     * @return 结果数据
     */
    public List<ProRegistCodeManage001Dto> queryReportList(Map<String,Object> paramMap) {
    	String svrSystemType=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PRDRESIST_CODE);
		if(svrSystemType!=null&&svrSystemType.equals("01")){
			paramMap.put("type", "O");
		}else{
			paramMap.put("type", "N");
		}
        return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/queryReportList", paramMap,
	       		ProRegistCodeManage001Dto.class);
    }
    /**
     * 检查产品是否存在交易或者存在基期数据
     * @param orgCodes 机构代码
     * @param prodIds 产品ids
     * @return WebResult
     * @see
     * @since 1.0
     */
    public Integer selectProdIsUsed(Map<String,Object> paramMap) {
    	return restTemplate.postForObject(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/selectProdIsUsed", paramMap,
	       		Integer.class);
    }
    /**
     * 删除
     *
     * @param  deleteKeys
     * @return 导出
     */
    public void deleteRegCode(String deleteKeys) {
        restTemplate.postForObject(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/deteteRegCode", deleteKeys,
	       		Integer.class);
    }
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList1(fileName));
    	param.put("orgCode", CommonUtil.getOrgCode());
    	param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/checkConfirmMsgForImportData", 
	       		param,
	       		SvrAjaxResult.class);
    }
    /**
    *
    * 产品代码下拉框取得值
    *
    * @return 下拉框值
    * @see
    * @since 1.0
    */
   public List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param) {
       return restTemplate.postForList(
               SvrServiceApplication.COMMON_SERVICE_NAME + "/ProRegistCodeManage/getProdinfo",param,
               ChbPrdBaseinfo.class);
   }


   /**
    * 导入数据文件之前检查数据
    * @param fileName
    * @return
    */
	public AjaxResult checkConfirmMsgForImportNewData(String fileName) {
		Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("orgCode", CommonUtil.getOrgCode());
    	param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
	       		SvrServiceApplication.CUSTOMER_INTERF_NAME + "/ProRegistCodeManage/checkConfirmMsgForImportNewData", 
	       		param,
	       		SvrAjaxResult.class);
	}


	/**
	 * 导入数据文件
	 * @param fileName
	 * @return
	 */
	public List<ExcelValidDto> importNewFile(String fileName) {
		Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("tempList", ExcelUtil.getTempList(fileName));
    	param.put("orgCode", CommonUtil.getOrgCode());
    	param.put("deptCode", CommonUtil.getDeptCode());
    	return restTemplate.postForList(
	       		SvrServiceApplication.CUSTOMER_INTERF_NAME + "/ProRegistCodeManage/importNewFile", 
	       		param,
	       		ExcelValidDto.class);
	}
	
	
	
}