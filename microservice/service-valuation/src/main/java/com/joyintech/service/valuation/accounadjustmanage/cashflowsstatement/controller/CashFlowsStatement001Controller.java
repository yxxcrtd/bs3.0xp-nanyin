/*
 * 文件名：CashFlowsStatement001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：QuJianZhong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.cashflowsstatement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.service.SystemToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.cashflowsstatement.dto.CashFlowsStatement001Dto;
import com.joyintech.service.valuation.accounadjustmanage.cashflowsstatement.service.CashFlowsStatement001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;

/**
 * 现金流量表Controller
 * 
 * @author QuJianZhong
 * @version 1.0 2018年10月22日
 * @see CashFlowsStatement001Controller
 * @since 1.0
 */
@RestController(value="serviceCashFlowsStatement001Controller")
@RequestMapping("CashFlowsStatement001")
public class CashFlowsStatement001Controller  extends BaseController {

    @Autowired
    private CashFlowsStatement001Service cashFlowsStatement001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    @Autowired
    private SystemToolService systemToolService;
   
    @Autowired
    private BooksetCommonService booksetCommonService;

    @PostMapping("/findPage")
    public List<CashFlowsStatement001Dto> selectCashFlowsStatement(@RequestBody Map<String, Object> pageParam)
        throws Exception {
        return cashFlowsStatement001Service.selectList(pageParam);
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
    @PostMapping("/getBooksetId2")
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
        Date reportDate = cashFlowsStatement001Service.analyseDate(pageParam);
        
        map.put("statementDate", sdf.format(reportDate));
        map.put("statementType", systemToolService.getNameFromDataDict(DictCode.报表期限类型, (String)pageParam.get("reportTermType")));
        //map.put("org", CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        //多级法人改造start add by hxy 20200612
        if(MultilevelDataAuthorityUtil.isEnabled()) {
	        	 //联社模式
	        	 //根据选择的账套组或者账套，查询出它们的所属机构
	        	String booksetOrg;
	        	if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals((String)pageParam.get("reportDimen"))) {
	        		booksetOrg=(String)pageParam.get("booksetGroup");
	        		Map<String, Object> orgmap=new HashMap<String, Object>();
	        		orgmap.put("booksetOrg", booksetOrg);
	        		orgmap.put("dataType", "V08");
	        		String orgName=booksetCommonService.selectAssociationModeOrgGrpName(orgmap);
	            	map.put("org", orgName);
	        	}else{
	        		booksetOrg=(String)pageParam.get("booksetId");
	        		Map<String, Object> orgmap=new HashMap<String, Object>();
	        		orgmap.put("booksetOrg", booksetOrg);
	        		orgmap.put("dataType", "V08");
	        		String orgName=booksetCommonService.selectAssociationModeOrgName(orgmap);
	            	map.put("org", orgName);
	        	}
        	}else{
        	//银行模式
        	String orgName=booksetCommonService.selectBankModeOrgName();
        	map.put("org", orgName);
        	
           }
        //多级法人改造end add by hxy 20200612
        return map;
    }
    
    /**
     * 联动：报表期限类型 - 季度 半年
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("/getTradeMarket")
    public List<SelectOptionDto> getTradeMarket(Map<String, Object> param) throws Exception {
        String reportTermType = (String) param.get("reportTermType");

        param.put("dict_code_parent", DictCode.报表期限类型.getValue());
        param.put("item_code_parent", param.get("reportTermType"));
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            param.put("dict_code_child", DictCode.半年.getValue());
            return cashFlowsStatement001Service.getLinkSelectOption(param);
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            param.put("dict_code_child", DictCode.季度.getValue());
            return cashFlowsStatement001Service.getLinkSelectOption(param);
        }
        return null;
    }

    /**
     * 查询现金流量表数据list
     *
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectListp")
    public List<CashFlowsStatement001Dto> selectListp(Map<String, Object> pageParam) throws ParseException {
        CashFlowsStatement001Dto dto = (CashFlowsStatement001Dto) pageParam.get("CashFlowsStatement001Dto");
        return cashFlowsStatement001Service.selectListp(pageParam,dto);
    }

    /**
     * 获取默认日期信息
     * 
     * @return CashFlowsStatement001Dto
     * @throws Exception
     */
    @PostMapping("getDateInfo")
    public CashFlowsStatement001Dto getDateInfo(@RequestBody Map<String, Object> pageParam) {
        return cashFlowsStatement001Service.getDateInfo(pageParam);
    }
    
}
