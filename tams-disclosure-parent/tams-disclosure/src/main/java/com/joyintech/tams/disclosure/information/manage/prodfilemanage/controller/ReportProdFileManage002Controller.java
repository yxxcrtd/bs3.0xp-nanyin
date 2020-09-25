package com.joyintech.tams.disclosure.information.manage.prodfilemanage.controller;

import java.util.Collections;
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
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto.ReportProdFileManage001Dto;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.service.ReportProdFileManage002Service;

/**
 * 产品文件新建 Controller层
 * @author hpp
 *
 */
@Controller
@PageId("ReportProdFileManage002")
@RequestMapping("disclosure/information/manage/prodfilemanage/ReportProdFileManage002")
public class ReportProdFileManage002Controller extends BaseController{
	@Autowired
	private ReportProdFileManage002Service reportProdFileManage002Service;
	/**
	 * 重写init方法，加载页面
	 */
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益类型,DictCode.处理模式,DictCode.开放类型,
        		DictCode.产品运作模式,DictCode.产品收益标识,DictCode.投资性质,DictCode.币种,DictCode.计息基础,
        		DictCode.募集方式,DictCode.产品风险等级,DictCode.投资风险偏好,DictCode.分红方式,DictCode.是否,
        		DictCode.期限类型,DictCode.赎回原则,DictCode.投资本金_收益_到账日,DictCode.销售渠道,
        		DictCode.计费基数_费用品种,DictCode.平安资产类型);
        model.addAttribute("produuid", this.getParam("produuid"));
        
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
        model.addAttribute("DISPOSE_PATTERN_01", DictCodeList.处理模式_普通.getValue());
        model.addAttribute("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());
        model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
        model.addAttribute("ISSUE_MODE_01", DictCodeList.开放类型_封闭式.getValue());
        model.addAttribute("ISSUE_MODE_02", DictCodeList.开放类型_开放式.getValue());
        model.addAttribute("SVR_OPERATEMODE_DECLARE_01", "01");
        model.addAttribute("SVR_OPERATEMODE_DECLARE_02", "02");
        model.addAttribute("SVR_OPERATEMODE_DECLARE_03", "03");
        model.addAttribute("SVR_OPERATEMODE_DECLARE_04", "04");
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        model.addAttribute("TERM_TYPE_01", "01");
        model.addAttribute("TERM_TYPE_02", "02");
        model.addAttribute("TERM_TYPE_03", "03");
        
        Map<String, Object> map = RequestUtil.getPageParam();
        List<SelectOptionDto> feeinfoList=reportProdFileManage002Service.queryFeeInfoList(map);
        model.addAttribute("FEEINFO_LIST",JsonUtils.objectArray2Json(feeinfoList.toArray()));
        
        //pageMode不为空且其不为新建时
        if(map.containsKey(GlobalConstant.DETAIL_PAGE_MODEL_KEY)&&
            !StringUtil.equals(map.get(GlobalConstant.DETAIL_PAGE_MODEL_KEY), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	ReportProdFileManage001Dto entity = reportProdFileManage002Service.selectByPrimaryKey(map);
            model.addAttribute("entity", entity);
            model.addAttribute("prodId", entity.getProdId());
        }
        
        return super.init(model);
    }
	
	/**
	 * 保存按钮点击时的check
	 * @param dto
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult Check(@RequestBody @Validated({ValidateGroupEdit.class}) ReportProdFileManage001Dto dto, BindingResult result){
		return commonCheck(result, dto.getOperateType());
	}
	
	/**
	 * 保存提交
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doSave")
	public AjaxResult doSave(@RequestBody ReportProdFileManage001Dto dto){
		reportProdFileManage002Service.doSave(dto);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0006"));
	}
	
	/**
     * 查询费用品种信息
     * 
     * @param finprodMarketId 金融产品代码
     * @return 费用品种信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return reportProdFileManage002Service.queryFeeInfoList(map);
    }
	
	/**
	 * 查询费用信息列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryFeeInfoPF")
	public List<ReportProdFileManage001Dto> queryFeeInfoPF(){
		String prodId = this.getParam("prodId");
		if (StringUtil.isNotEmpty(prodId)) {
            return reportProdFileManage002Service.queryFeeInfoPF(prodId);
        }
        return Collections.emptyList();
	}
	/**
	 * 查询投资比例区间信息列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryInvestProporPF")
	public List<ReportProdFileManage001Dto> queryInvestProporPF(){
		String prodId = this.getParam("prodId");
		if (StringUtil.isNotEmpty(prodId)) {
            return reportProdFileManage002Service.queryInvestProporPF(prodId);
        }
        return Collections.emptyList();
	}
	
}
