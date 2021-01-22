/*
 * 文件名：OwnerEquityChangeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.ownerequitychange.controller;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychange.dto.OwnerEquityChange001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychange.service.OwnerEquityChange001Service;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有者权益变动Controller
 * 
 * @author zhangzhao
 * @version 1.0 2018年9月18日
 * @see OwnerEquityChange001Controller
 * @since 1.0
 */
@Controller
@PageId("OwnerEquityChange001")
@RequestMapping("/valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001")
public class OwnerEquityChange001Controller  extends BaseController {

    @Autowired
    private OwnerEquityChange001Service ownerEquityChange001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    @Autowired
    private ValProfitQuery001Service valProfitQuery001Service;
    
    @Autowired
    private BooksetCommonService booksetCommonService;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.报表期限类型);
        this.setSelectOptionToModel(model, DictCode.上期维度);
        // model.addAttribute("lastDimenVal", DictCodeList.上期维度_环比.getValue());
        model.addAttribute("reportDimen", DictCodeListExt2.批处理账套类型_账套.getValue());
        super.setSelectOptionToModel(model, DictCode.批处理账套类型); // 账套维度
       // 是否显示查看数据字典明细按钮，多级法人改造 add by hxy 20200623
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));
      
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
    public AjaxResult check(@RequestBody @Valid OwnerEquityChange001Dto dto, BindingResult result) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        if (StringUtils.isEmpty(dto.getReportDimen())) {
            return new AjaxResult("报表维度不能为空！", MessageType.错误消息);
        } else {
            if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(dto.getReportDimen())) {
                // 账套代码不能为空
                if (StringUtil.isEmpty(dto.getBooksetId())) {
                    return new AjaxResult("请输入账套代码。", MessageType.错误消息);
                }
                // 账套代码必须存在
                String[] booksetIds = new String[1];
                booksetIds[0] = dto.getBooksetId();
                List<Product> booksetList = booksetCommonService.findProductByIds(booksetIds);
                if (booksetList == null || booksetList.isEmpty()) {
                    return new AjaxResult("账套代码不存在。", MessageType.错误消息);
                }
            } else {
                // 账套组不能为空
                if (StringUtil.isEmpty(dto.getBooksetGroup())) {
                    return new AjaxResult("请输入账套组。", MessageType.错误消息);
                }
            }
        }
        // 处理开始和结束日
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("reportTermType", dto.getReportTermType());
        pageParam.put("valDate", dto.getValDate());
        pageParam.put("reportTerm", dto.getReportTerm());
        pageParam.put("lastDimen", dto.getLastDimen());
        ownerEquityChange001Service.changeMapData(pageParam);
        String valueDateStart = DateUtil.format(
                DateUtil.addDay(DateUtil.parse(String.valueOf(pageParam.get("thisTermVdate"))), 1),
                DateUtil.DEFAULT_DATE_FORMAT_EN);
        String valueDateEnd = DateUtil.format(DateUtil.parse(String.valueOf(pageParam.get("thisTermMdate"))),
                DateUtil.DEFAULT_DATE_FORMAT_EN);
        dto.setValueDateStart(valueDateStart); // 本期日期
        dto.setValueDateEnd(valueDateEnd); // 期初日期
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
	public Map<String, String> getHeadInfo(@RequestBody Map<String, Object> pageParam) {
		Map<String, String> map = Maps.newHashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date reportDate = ownerEquityChange001Service.analyseDate(pageParam);
		
		map.put("statementDate", sdf.format(reportDate));
		map.put("statementType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型, (String)pageParam.get("reportTermType")));
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
    public List<OwnerEquityChange001Dto> selectBalanceStatement() {
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
    public List<SuggestDto> getBooksetId2(){
        return valAssetBook001Service.getBooksetId2(RequestUtil.getPageParam());
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
    
    /**
     * 获取默认日期信息
     * 
     * @return OwnerEquityChange001Dto
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("getDateInfo")
    public OwnerEquityChange001Dto getDateInfo()
        throws Exception {
    	Map<String, Object> params = RequestUtil.getPageParam();
    	params.put("booksetGroup", this.getParam("booksetGroup"));
    	params.put("reportTermType", this.getParam("reportTermType"));
        return ownerEquityChange001Service.getDateInfo(params);
    }
}
