/*
 * 文件名：OwnerEquityChangeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCode;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.common.service.DisValAssetBook001Service;
import com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.dto.DisOwnerEquityChange001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.service.DisOwnerEquityChange001Service;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.service.DisValProfitQuery001Service;

/**
 * 所有者权益变动Controller
 * 
 * @author zhangzhao
 * @version 1.0 2018年9月18日
 * @see OwnerEquityChange001Controller
 * @since 1.0
 */
@Controller
@PageId("DisOwnerEquityChange001")
@RequestMapping("/disclosure/accounadjustmanage/ownerequitychange/DisOwnerEquityChange001")
public class DisOwnerEquityChange001Controller  extends BaseController {

    @Autowired
    private DisOwnerEquityChange001Service ownerEquityChange001Service;
    
    @Autowired
    private DisValAssetBook001Service valAssetBook001Service;
    @Autowired
    private DisValProfitQuery001Service valProfitQuery001Service;
    
//    @Autowired
//    private BooksetCommonService booksetCommonService;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.报表期限类型);
        this.setSelectOptionToModel(model, DictCode.上期维度);
        model.addAttribute("lastDimenVal", DictCodeListExt1.上期维度_环比.getValue());
        return super.init(model);
    }
    /**
     * 查询条件检查
     * 
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid DisOwnerEquityChange001Dto dto, BindingResult result) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        // 账套代码必须存在
    	String[] booksetIds = new String[1];
    	booksetIds[0] = dto.getBooksetId();
    	
    	//TODO
//    	List<Product> booksetList = booksetCommonService.findProductByIds(booksetIds);
//    	if (booksetList == null || booksetList.isEmpty()) {
//    		return new AjaxResult("账套代码不存在。", MessageType.错误消息);
//    	}
        // 处理开始和结束日
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("reportTermType", dto.getReportTermType());
        pageParam.put("valDate", dto.getValDate());
        pageParam.put("reportTerm", dto.getReportTerm());
        pageParam.put("lastDimen", dto.getLastDimen());
        ownerEquityChange001Service.changeMapData(pageParam);
        String valueDateStart = DateUtil.format(DateUtil.addDay(DateUtil.parse(String.valueOf(pageParam.get("thisTermVdate"))), 1), DateUtil.DEFAULT_DATE_FORMAT_EN);
        String valueDateEnd = DateUtil.format(DateUtil.parse(String.valueOf(pageParam.get("thisTermMdate"))), DateUtil.DEFAULT_DATE_FORMAT_EN);
        dto.setValueDateStart(valueDateStart);  //本期日期
        dto.setValueDateEnd(valueDateEnd);    //期初日期
        ajaxResult.setData(dto);
        return ajaxResult;
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
		Date reportDate = ownerEquityChange001Service.analyseDate(pageParam);
		
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
     * 
     * 分頁查詢
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<DisOwnerEquityChange001Dto> selectBalanceStatement()
        throws Exception {
        return ownerEquityChange001Service.selectList(RequestUtil.getParameters(getRequest()));
         
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
     * 导出信息
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> exportExcel()
        throws Exception {
    	Map<String, Object> params = RequestUtil.getParameters(getRequest());
    	return ownerEquityChange001Service.exportExcel(params);
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
