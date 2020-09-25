package com.joyintech.tams.invest.product.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
//import com.joyintech.tams.constant.DictCodeList;
//import com.joyintech.tams.constant.DictCodeListExt2;
//import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
//import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfo002Dto;

@Controller
@RequestMapping("/invest/product/common")
public class ProductCommonController extends BaseController {

    @Autowired
    private ProductCommonService productCommonService;

    /**
     * 获取所有产品代码
     * 
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProdId")
    public List<SuggestDto> getProdId() {
        return productCommonService.getProdId();
    }

    /**
     * 获取所有组合代码
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getGroupId")
    public List<SuggestDto> getGroupId() {
        return productCommonService.getGroupId();
    }

    /**
     * 查询交易对手下拉框
     * 
     * @return 交易对手
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryCounterpartyInfo")
    public Page<SelectOptionDto> queryCounterpartyInfo() {
        return productCommonService.queryCounterpartyInfo(RequestUtil.getPageParam());
    }

    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo() {
        return productCommonService.queryProdIdInfo(RequestUtil.getPageParam());
    }

    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfoIssueSuc")
    public Page<SelectOptionDto> queryProdIdInfoIssueSuc() {
        return productCommonService.queryProdIdInfoIssueSuc(RequestUtil.getPageParam());
    }
    
    /**
     * 费用代码联想下拉框
     * 
     * @return 费用代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeInfoList")
    public Page<SelectOptionDto> queryFeeInfoList() {
        return productCommonService.queryFeeInfoList(RequestUtil.getPageParam());
    }
    
    /**
     * 托管名称联想下拉框
     * 
     * @return 费用代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryTrusterCustomerName")
    public List<SelectOptionDto> queryTrusterCustomerName() {
        return productCommonService.queryTrusterCustomerName(RequestUtil.getPageParam());
    }
    
    /**
     * 交易日历下拉框查询
     * 
     * @return 交易日历
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryCalendarInfoList")
    public Page<SelectOptionDto> queryCalendarInfoList(){
        return productCommonService.queryCalendarInfoList(RequestUtil.getPageParam());
    }
    
    /**
     * 根据金融产品代码查询产品基本信息
     * 
     * @param finprodMarketId 模板代码
     * @param finprodType 金融产品类型
     * @return 产品基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkFinMarketIdExist")
    public AjaxResult checkTmplIdExist(String finprodMarketId, String finprodType) {
        AjaxResult ar = new AjaxResult();
//        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
//        if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_系列模板.getValue())){
//            FinProductModuleInfo002Dto finProductModuleInfo002Dto = productCommonService.selectTemplateById(finprodMarketId);
//
//            if(StringUtil.isNotEmpty(finProductModuleInfo002Dto)){
//                finProductModuleInfo002Dto.getProductModuleInfo().setCcy(finProductModuleInfo002Dto.getBussRepFinProductInfoDto().getRepRaiseCcy());
//                finProductModuleInfo002Dto.getProductModuleInfo().setIssueType(
//                    finProductModuleInfo002Dto.getBussRepFinProductInfoDto().getRepIssueType());
//                
//                finProductModuleInfo002Dto.getProductModuleInfo().setSameOrg(finProductModuleInfo002Dto.getBussFinProductSaleParamDto().getSameOrg());
//                ar.setData(finProductModuleInfo002Dto);
//            } 
//            else {
//                errorList.add(new ValidMessage(
//                    this.getMessage("COMMON_E_0008", this.getMessage("FinProductBuild002.tmplId"))));
//            }
//        } 
//        else {
//            FinProductBuild002Dto finProductBuild002Dto = productCommonService.queryProdInfoByFinprodMarketId(
//                finprodMarketId, finprodType); 
//            if (finProductBuild002Dto != null) {
//                ar.setData(finProductBuild002Dto);
//            } 
//            else {
//                errorList.add(new ValidMessage(this.getMessage("COMMON_E_0008",
//                    this.getMessage("FinProductBuild002.finprodMarketId"))));
//            }
//        }
//        
//        if(!StringUtil.isEmptyList(errorList)) {
//            ar.setErrorList(errorList); 
//        }
        
        FinProductBuild002Dto finProductBuild002Dto = productCommonService.queryProdInfoByFinprodMarketId(
            finprodMarketId, finprodType);
        if (finProductBuild002Dto != null) {
            ar.setData(finProductBuild002Dto);
        }
        else {
            List<ValidMessage> errorList = new ArrayList<ValidMessage>();
            if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_系列模板.getValue())){
                errorList.add(new ValidMessage(
                    this.getMessage("COMMON_E_0008", this.getMessage("FinProductBuild002.tmplId"))));
            }
            else{
                errorList.add(new ValidMessage(this.getMessage("COMMON_E_0008",
                    this.getMessage("FinProductBuild002.finprodMarketId"))));
            }
            ar.setErrorList(errorList);
        }
        return ar;
    }
  
    
    @ResponseBody
    @RequestMapping("getUserName")
    public Page<SelectOptionDto> getUserName() {
    	return productCommonService.getUserName(RequestUtil.getPageParam());
    }
    //新建产品经理只查本机构
    @ResponseBody
    @RequestMapping("getUserName1")
    public Page<SelectOptionDto> getUserName1() {
    	return productCommonService.getUserName1(RequestUtil.getPageParam());
    }
    
    /**
     * 需求发起人下拉框 yzg 20191024
     * @return
     */
    @ResponseBody
    @RequestMapping("getProductUserName")
    public Page<SelectOptionDto> getProductUserName() {
    	return productCommonService.getProductUserName(RequestUtil.getPageParam());
    }
    
    @ResponseBody
    @RequestMapping("getCustomerName")
    public Page<SelectOptionDto> getCustomerName() {
        return productCommonService.getCustomerName(RequestUtil.getPageParam());
    }
    /**
     * 获取行内标识码
     * @return
     */
    @ResponseBody
    @RequestMapping("queryRepFinprodInsId")
    public List<SelectOptionDto> queryRepFinprodInsId() {
    	return productCommonService.queryRepFinprodInsId(RequestUtil.getPageParam());
    }
    /**
     * 查询机构
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryTrusteeId")
    public Page<SelectOptionDto> queryTrusteeId() {
    	Map<String, Object> map = RequestUtil.getPageParam();
//    	String pageModel = (String) map.get("pageModel");
//    	if(pageModel != null && StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
//    		map.put("search", map.get("initValue"));
//    	}
    	return productCommonService.queryTrusteeId(map);
    }
}