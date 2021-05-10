/*
 * 文件名：CashFlowsStatement001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：QuJianZhong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.apache.commons.lang.StringUtils;
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
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dto.CashFlowsStatement001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.service.CashFlowsStatement001Service;
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
@Controller
@PageId("CashFlowsStatement001")
@RequestMapping("/valuation/accounadjustmanage/cashflowsstatement/CashFlowsStatement001")
public class CashFlowsStatement001Controller  extends BaseController {

    @Autowired
    private CashFlowsStatement001Service cashFlowsStatement001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private BooksetCommonService booksetCommonService;

    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.报表期限类型);
        this.setSelectOptionToModel(model, DictCode.上期维度);

        model.addAttribute("reportDimen", DictCodeListExt2.批处理账套类型_账套组.getValue());
        super.setSelectOptionToModel(model, DictCode.批处理账套类型); // 账套维度
		model.addAttribute("LAST_DIMEN_01", DictCodeListExt1.上期维度_环比.getValue());
        // 是否显示查看数据字典明细按钮，多级法人改造 add by hxy 20200623
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));
      
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid CashFlowsStatement001Dto dto, BindingResult result) {
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

        return commonCheck(result, "");
    }


    @ResponseBody
    @RequestMapping("findPage")
    public List<CashFlowsStatement001Dto> selectCashFlowsStatement()
        throws Exception {
        //modify by yanya on 20201218 增加样式处理类型
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        param.put("STYLE_TYPE", "HTML");
        return cashFlowsStatement001Service.selectList(param);
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
        return valAssetBook001Service.getBooksetId2(RequestUtil.getPageParam());
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
     * 联动：报表期限类型 - 季度 半年
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getTermLink")
    public List<SelectOptionDto> getTradeMarket() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        
        //update by leipeng 【估值管理】-【现金流量表】：点击筛选条件为季度或者年时，期限字段无法展示 
        //return cashFlowsStatement001Service.getTradeMarket(param);
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
        //modify by yanya on 20201218 增加样式处理类型
        params.put("STYLE_TYPE", "EXCEL");
        return cashFlowsStatement001Service.exportExcel(params);
    }
    
    /**
     * 获取默认日期信息
     * 
     * @return CashFlowsStatement001Dto
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("getDateInfo")
    public CashFlowsStatement001Dto getDateInfo()
        throws Exception {
        return cashFlowsStatement001Service.getDateInfo(RequestUtil.getPageParam());
    }
    
}
