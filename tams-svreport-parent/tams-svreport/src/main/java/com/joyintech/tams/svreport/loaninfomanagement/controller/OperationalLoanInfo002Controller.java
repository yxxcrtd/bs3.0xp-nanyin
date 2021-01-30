package com.joyintech.tams.svreport.loaninfomanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemDictCode;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.svreport.loaninfomanagement.service.OperationalLoanInfoMService;

@Controller
@PageId("OperationalLoanInfo002")
@RequestMapping("/svreport/loaninfomanagement/OperationalLoanInfo002")
public class OperationalLoanInfo002Controller extends BaseController{
	
	@Autowired
	private OperationalLoanInfoMService operationalLoanInfo001Service;
	
	/**
	 * 重写init方法，加载页面
	 */
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.客户一级类型,DictCode.贷款种类,DictCode.区域划分_省,
        		DictCode.区域划分_市,DictCode.区域划分_县,DictCode.借款人类型,DictCode.行业门内编码,DictCode.所属国家或地区,
        		DictCode.经济成分一级,DictCode.经济成分二级,DictCode.经济成分三级,DictCode.企业规模,DictCode.贷款产品类别,
        		DictCode.行业大类编码,DictCode.利率是否固定,DictCode.贷款担保方式,DictCode.贷款质量,DictCode.贷款状态,DictCode.币种_中债,DictCode.人行资产三级分类,DictCode.人行资产一级分类,
                DictCode.中债贷款分类);
        model.addAttribute("assetUuid", this.getParam("ASSET_UUID"));
        
        model.addAttribute("SVR_LOAN_TYPE_1", DictCodeListExt3.贷款种类_发放贷款.getValue());
        model.addAttribute("SVR_LOAN_TYPE_2", DictCodeListExt3.贷款种类_转让贷款.getValue());
        model.addAttribute("SVR_LOAN_TYPE_3", DictCodeListExt3.贷款种类_其他债权收益权.getValue());
        model.addAttribute("SVR_INDUSTRY_DOOR_CODE_1", DictCodeListExt3.行业门内编码_境内个人.getValue());
        model.addAttribute("SVR_INDUSTRY_DOOR_CODE_2", DictCodeListExt3.行业门内编码_非居民.getValue());
        model.addAttribute("SVR_DEPT_TYPE_1", DictCodeListExt3.借款人类型_住户.getValue());
        model.addAttribute("SVR_DEPT_TYPE_2", DictCodeListExt3.借款人类型_广义政府.getValue());
        model.addAttribute("SVR_DEPT_TYPE_3", DictCodeListExt3.借款人类型_非金融企业.getValue());
        model.addAttribute("SVR_DEPT_TYPE_4", DictCodeListExt3.借款人类型_金融机构.getValue());
        model.addAttribute("SVR_DEPT_TYPE_5", DictCodeListExt3.借款人类型_特定目的载体.getValue());
        model.addAttribute("SVR_DEPT_TYPE_6", DictCodeListExt3.借款人类型_境外.getValue());
        model.addAttribute("SVR_LOAN_PEOD_TYPE_F99", DictCodeListExt3.贷款产品类别_其他贷款.getValue());
        model.addAttribute("SVR_LOAN_STATUS_FS01", DictCodeListExt3.贷款状态_正常.getValue());
        model.addAttribute("SVR_LOAN_STATUS_FS02", DictCodeListExt3.贷款状态_展期.getValue());
        model.addAttribute("SVR_LOAN_STATUS_FS03", DictCodeListExt3.贷款状态_逾期.getValue());
        
        Map<String, Object> map = RequestUtil.getPageParam();
        //pageMode不为空且其不为新建时
        if(map.containsKey(GlobalConstant.DETAIL_PAGE_MODEL_KEY)&&
            !StringUtil.equals(map.get(GlobalConstant.DETAIL_PAGE_MODEL_KEY), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	OperationalLoanInfo001Dto entity = operationalLoanInfo001Service.selectByPrimaryKey(map);
        	entity.setAssetsInvest(entity.getAssetsInvest().replaceAll(";", ","));
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }
	
	/**
	 * 查询产品资产代码
	 * @return
	 */
	@ResponseBody
    @RequestMapping("queryPbcAssetCode")
    public List<SelectOptionDto> queryPbcAssetCode() {
        return operationalLoanInfo001Service.queryPbcAssetCode(RequestUtil.getPageParam());
    }
	@ResponseBody
    @RequestMapping("queryPbcAssetType")
    public String queryAssetType() {
        return operationalLoanInfo001Service.queryPbcAssetType(RequestUtil.getPageParam());
    }
	
	
	/**
	 * 保存按钮点击时check
	 * @param dto
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) OperationalLoanInfo001Dto dto, BindingResult result){
		return commonCheck(result, dto.getOperateType());
	}
	
	/**
	 * 保存提交
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doSave")
	public AjaxResult doSave(@RequestBody OperationalLoanInfo001Dto dto){
		operationalLoanInfo001Service.doSave(dto);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0006"));
	}
	
	/**
	 * 贷款合同原始发放机构所在地市县联动
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("/getLoanCounties")
    public List<SelectOptionDto> getLoanCounties() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("dict_code_parent",DictCode.区域划分_市.getValue());
        param.put("item_code_child", param.get("loanContractOrgCity"));
        param.put("item_code_field", DictCode.区域划分_县.getValue());
        return operationalLoanInfo001Service.getAddressSelectOption(param);
    }
	
	/**
	 * 借款人地区市县联动
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("/getBorrowerCounties")
    public List<SelectOptionDto> getBorrowerCounties() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("dict_code_parent",DictCode.区域划分_市.getValue());
        param.put("item_code_child", param.get("borrowerCity"));
        param.put("item_code_field", DictCode.区域划分_县.getValue());
        return operationalLoanInfo001Service.getAddressSelectOption(param);
    }
	
	/**
	 * 借款人地区省市联动
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("/getBorrowerCities")
    public List<SelectOptionDto> getBorrowerCities() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("dict_code_parent",DictCode.区域划分_省.getValue());
        param.put("item_code_child", param.get("borrowerProvince"));
        param.put("item_code_field", DictCode.区域划分_市.getValue());
        return operationalLoanInfo001Service.getAddressSelectOption(param);
    }
	
	/**
	 * 贷款合同原始发放机构所在地市县联动
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("/getLoanCities")
    public List<SelectOptionDto> getLoanCities() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("dict_code_parent",DictCode.区域划分_省.getValue());
        param.put("item_code_child", param.get("loanContractOrgProvince"));
        param.put("item_code_field", DictCode.区域划分_市.getValue());
        return operationalLoanInfo001Service.getAddressSelectOption(param);
    }
	
}
