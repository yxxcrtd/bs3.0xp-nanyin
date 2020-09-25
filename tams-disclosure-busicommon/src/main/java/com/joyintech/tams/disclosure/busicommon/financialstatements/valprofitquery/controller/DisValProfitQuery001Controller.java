/*
 * 文件名：ValProfitQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCode;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.common.service.DisValAssetBook001Service;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisValProfitQuery001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.service.DisValProfitQuery001Service;

/**
 * 利润表
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("DisValProfitQuery001")
@RequestMapping("/disclosure/accounadjustmanage/valprofitquery/DisValProfitQuery001")
public class DisValProfitQuery001Controller extends BaseController {

    @Autowired
    private DisValProfitQuery001Service valProfitQuery001Service;
    
    @Autowired
    private DisValAssetBook001Service valAssetBook001Service;
    
//    @Autowired
//    private BooksetCommonService booksetCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.报表期限类型);
        this.setSelectOptionToModel(model, DictCode.上期维度);
        this.setSelectOptionToModel(model, DictCode.报表维度);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid DisValProfitQuery001Dto dto, BindingResult result) {
    	// 当选择单账套时
        if ("01".equals(dto.getReportDimen())) {
        	// 账套代码不能为空
        	if (StringUtil.isEmpty(dto.getBooksetId())) {
        		return new AjaxResult("请输入账套代码。", MessageType.错误消息);
        	}
	    	// 账套代码必须存在
	    	String[] booksetIds = new String[1];
	    	booksetIds[0] = dto.getBooksetId();
	    	//TODO
//	    	List<Product> booksetList = booksetCommonService.findProductByIds(booksetIds);
//	    	if (booksetList == null || booksetList.isEmpty()) {
//	    		return new AjaxResult("账套代码不存在。", MessageType.错误消息);
//	    	}
        }
        return commonCheck(result, "");
    }

    @ResponseBody
    @RequestMapping("findPage")
    public List<DisValProfitQuery001Dto> selectBalanceStatement()
        throws Exception {
    	Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
    	// 报表维度为单账套
    	if ("01".equals(String.valueOf(paraMap.get("reportDimen")))) {
    		return valProfitQuery001Service.findPage(paraMap);
    	}
    	// 报表维度为全账套
    	paraMap.put("accounts", valAssetBook001Service.getBooksetId2(super.getRequest().getParameter("param")));
        return valProfitQuery001Service.findPageAll(paraMap);
    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId2")
    public List<SuggestDto> getBooksetId2()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        return valAssetBook001Service.getBooksetId2(param);
    }

	/**
	 * 获得 表头信息
	 * 
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("getHeadInfo")
	public Map<String, String> getHeadInfo(@RequestBody Map<String, Object> pageParam)
			throws ParseException {
		Map<String, String> map = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date reportDate = valProfitQuery001Service.analyseDate(pageParam);
		
		map.put("statementDate", sdf.format(reportDate));
		map.put("statementType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型, (String)pageParam.get("reportTermType")));
		//map.put("org", CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
	    //多级法人改造start add by hxy 20200619
        if(MultilevelDataAuthorityUtil.isEnabled()) {
        	 //联社模式,查询登录人的机构
        	/*String org =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            String orgName=CacheUtil.getAppCacheInstitutionCodeName(org).toString();
        	map.put("org", orgName);*/
        	 //联社模式
	       	 //根据选择的账套组或者账套，查询出它们的所属机构
	       	String booksetOrg;
	       	if ("02".equals((String)pageParam.get("reportTerm"))) {
	            	booksetOrg=(String)pageParam.get("booksetGroup");
		         String orgName=valAssetBook001Service.selectAssociationModeOrgName(booksetOrg);
		         map.put("org", orgName);
		         
	       	}else{
	       		booksetOrg=(String)pageParam.get("booksetId");
	       		String orgName=valAssetBook001Service.selectAssociationModeOrgName(booksetOrg);
	           	map.put("org", orgName);
	       	}
        }else{
        	//银行模式
        	String orgName=valAssetBook001Service.selectBankModeOrgName();
        	map.put("org", orgName);
        	
        }
        //多级法人改造end add by hxy 20200619
		return map;
	}
    
	/**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception{
    	Map<String, Object> paraMap = RequestUtil.getPageParam();
    	if ("02".equals(paraMap.get("reportDimen"))) {
    	List<SuggestDto> accountList = valAssetBook001Service.getBooksetId2(super.getRequest().getParameter("param"));
    		paraMap.put("accounts", accountList);
    	}
        return valProfitQuery001Service.exportExcel(paraMap);
    }
    

	/**
	 * 联动：报表期限类型 - 季度 半年
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getTermLink")
	public List<SelectOptionDto> getTradeMarket() throws Exception {
		Map<String, Object> param = RequestUtil.getPageParam();
		String reportTermType = (String) param.get("reportTermType");

		param.put("dict_code_parent", DictCode.报表期限类型.getValue());
		param.put("item_code_parent", param.get("reportTermType"));
		if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
			param.put("dict_code_child", DictCode.半年.getValue());
			return valProfitQuery001Service.getLinkSelectOption(param);
		}
		if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
			param.put("dict_code_child", DictCode.季度.getValue());
			return valProfitQuery001Service.getLinkSelectOption(param);
		}
		return null;
	}
    
}
