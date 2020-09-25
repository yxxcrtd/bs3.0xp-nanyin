/*
 * 文件名：AssetLiabilityStatementQuery001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.financialstatement.assetliailitystatement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.common.AssetLiabilityStatementUtil;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001Dto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001SearchDto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQueryDto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatement.service.AssetLiabilityStatementQuery001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;

@RestController(value = "serviceAssetLiabilityStatementQuery001Controller")
@RequestMapping("/AssetLiabilityStatementQuery001")
public class AssetLiabilityStatementQuery001Controller extends BaseController {

    @Autowired
    private AssetLiabilityStatementQuery001Service service;

    @Autowired
    private SystemToolService systemToolService;

    @Autowired
    private BooksetCommonService booksetCommonService;

    /**
     * 查询条件检查
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid AssetLiabilityStatementQuery001SearchDto dto,
                            BindingResult result) {
        // 当选择单账套时
        if ("01".equals(dto.getReportDimen())) {
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
        }
        if (result != null && !result.hasErrors()) {// 基础验证通过
            String reportTermType = dto.getReportTermType();// 报表期限类型
            String reportTerm = dto.getReportTerm();// 报表期限明细 : 半年，季度明细

            if ((DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)
                 || DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType))
                && StringUtils.isEmpty(reportTerm)) {// 报表期限类型为半年报或季报时，报表期限不能为空
                return new AjaxResult("期限不能为空！", MessageType.错误消息);
            }
        }
        return commonCheck(result, "");
    }

    /**
     * 联动：报表期限类型 - 季度 半年
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("/getTermLink")
    public List<SelectOptionDto> getTradeMarket(@RequestBody Map<String, Object> param)
        throws Exception {
        String reportTermType = (String)param.get("reportTermType");

        param.put("dict_code_parent", DictCode.报表期限类型.getValue());
        param.put("item_code_parent", param.get("reportTermType"));
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            param.put("dict_code_child", DictCode.半年.getValue());
            return service.getLinkSelectOption(param);
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            param.put("dict_code_child", DictCode.季度.getValue());
            return service.getLinkSelectOption(param);
        }
        return null;
    }

    /**
     * 获得 表头信息
     * 
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("getHeadInfo")
    public Map<String, String> getHeadInfo(@RequestBody AssetLiabilityStatementQuery001SearchDto dto)
        throws ParseException {
        Map<String, String> map = Maps.newHashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date reportDate = service.analyseBalanceDate(dto,
            AssetLiabilityStatementUtil.ENDING_BALANCE);

        map.put("statementDate", sdf.format(reportDate));
        map.put("statementType",
            systemToolService.getNameFromDataDict(DictCode.报表期限类型, dto.getReportTermType()));
      //  map.put("org", CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        //多级法人改造start add by hxy 20200612
        if(MultilevelDataAuthorityUtil.isEnabled()) {
	        	 //联社模式
	        	 //根据选择的账套组或者账套，查询出它们的所属机构
	        	String booksetOrg;
	        	if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals(dto.getReportDimen())) {
	        		booksetOrg=dto.getBooksetGroup();
	        		Map<String, Object> orgmap=new HashMap<String, Object>();
	        		orgmap.put("booksetOrg", booksetOrg);
	        		orgmap.put("dataType", "V08");
	        		String orgName=booksetCommonService.selectAssociationModeOrgGrpName(orgmap);
	            	map.put("org", orgName);
	        	}else{
	        		booksetOrg=dto.getBooksetId();
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

    @PostMapping("/findBokBalance")
    public List<AssetLiabilityStatementQueryDto> findBokBalance(@RequestBody AssetLiabilityStatementQuery001SearchDto dto) {

        return service.findBokBalance(dto);
    }

    @PostMapping("/findBokBalanceAll")
    public List<AssetLiabilityStatementQueryDto> findBokBalanceAll(@RequestBody AssetLiabilityStatementQuery001SearchDto dto) {

        return service.findBokBalanceAll(dto);
    }
    
    /**
     * 获取默认日期信息
     * 
     * @return AssetLiabilityStatementQuery001SearchDto
     * @throws Exception
     */
    @PostMapping("getDateInfo")
    public AssetLiabilityStatementQuery001SearchDto getDateInfo(@RequestBody Map<String, Object> pageParam) {
        return service.getDateInfo(pageParam);
    }

}
