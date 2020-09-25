package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividConditionDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividSpecialAssetDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividSpecialStageDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividUniteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividAssetDelDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskSupervisionIndividuationDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.service.RskSupervisionIndividuation001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 个性化参数设置
 * @author 王泽慧
 * @version 1.0 2019年8月16日
 * @see RskSupervisionIndividuation001Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionIndividuation001")
@RequestMapping("/invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation001")
public class RskSupervisionIndividuation001Controller extends BaseController{
	@Autowired
	private RskSupervisionIndividuation001Service rskSupervisionIndividuation001Service;
	/**
     * 初始化的查询条件
     */
    private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] {BussinessDictCodeRds.指标类型,
        BussinessDictCodeRds.监督类型, BussinessDictCodeRds.运算符, BussinessDictCodeRds.连接符,BussinessDictCodeRds.操作符,BussinessDictCodeRds.监控方向,
        BussinessDictCodeRds.单位, BussinessDictCodeRds.监督时分,  BussinessDictCodeRds.组合层次,BussinessDictCodeRds.监督频率,
        BussinessDictCodeRds.分类维度,BussinessDictCodeRds.申万行业一级类型,BussinessDictCodeRds.收益类型,BussinessDictCodeRds.开放类型,BussinessDictCodeRds.募集方式,
        BussinessDictCodeRds.投资性质,BussinessDictCodeRds.监控方向,BussinessDictCodeRds.触警操作
       };
	@Override
    public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		Map<String,Object > uniteMap=new HashMap<>();

		if(StringUtil.isNotEmpty(this.getParam("workflowId"))){
			RskSupervisionIndividuationDto dto = rskSupervisionIndividuation001Service.getBussinessEntityByWorkflowId((String)this.getParam("workflowId"));
			model.addAttribute("entity", dto);
			model.addAttribute("workflowId", this.getParam("workflowId"));

			uniteMap.put("indId",dto.getIndId());
			uniteMap.put("prtBagId",dto.getPrtBagId());
		}else{
			String portfolioId = this.getParam("portfolioId");
			String indId = this.getParam("indId");
			model.addAttribute("prtBagId", portfolioId);
			model.addAttribute("indId", indId);
			model.addAttribute("IND_TYPE", rskSupervisionIndividuation001Service.getIndType(indId));
			RskSupervisionIndividuationDto dto = new RskSupervisionIndividuationDto();
			dto.setIndId(indId);
			dto.setPrtBagId(portfolioId);
			dto.setIndType(rskSupervisionIndividuation001Service.getIndType(indId));
			model.addAttribute("entity", dto);
			uniteMap.put("indId",this.getParam("indId"));
			uniteMap.put("prtBagId",this.getParam("portfolioId"));
		}
		if(StringUtil.equals(this.getParam("portfolioLevel"), BussinessDictCodeListRds.组合层次_单组合.getValue())){
			String vdate = rskSupervisionIndividuation001Service.getVdate(this.getParam("portfolioId"));
			model.addAttribute("VDATE", vdate);
		}

		model.addAttribute("UNITE_SOURCE", JsonUtils.objectArray2Json(rskSupervisionIndividuation001Service.queryUniteIndIdList(uniteMap).toArray()));
		model.addAttribute("NONE", BussinessDictCodeListRds.单位_无.getValue());
		model.addAttribute("CONDITION_ITEM_03", BussinessDictCodeListRds.条件项_建仓期时间.getValue());
		model.addAttribute("CONDITION_ITEM_02", BussinessDictCodeListRds.条件项_单位净值控制.getValue());
		return super.init(model);
    }
	
	
	/**
     * 画面标的类金融产品代码
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("queryFinprodIdList")
	public Page<SelectOptionDto> queryFinprodIdList() {
		return rskSupervisionIndividuation001Service.queryFinprodIdList(RequestUtil.getPageParam());
	}
	/**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPageForCondition")
	public List<RskIndividConditionDto> findPageForCondition(){
		return rskSupervisionIndividuation001Service.findPageForCondition(RequestUtil.getPageParam());
	}
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPageForUnite")
	public List<RskIndividUniteDto> findPageForUnite(){
		return rskSupervisionIndividuation001Service.findPageForUnite(RequestUtil.getPageParam());
	}
	/**
     * 画面查询方法-剔除资产
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPageForAssetDel")
	public List<RskIndividAssetDelDto> findPageForAssetDel(){
		return rskSupervisionIndividuation001Service.findPageForAssetDel(RequestUtil.getPageParam());
	}
	/**
     * 画面查询方法-特殊阶段
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPageForSpecial")
	public List<RskIndividSpecialStageDto> findPageForSpecial(){
		return rskSupervisionIndividuation001Service.findPageForSpecial(RequestUtil.getPageParam());
	}
	/**
     * 画面查询方法-特殊证券阙值
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPageForSpecialAsset")
	public List<RskIndividSpecialAssetDto> findPageForSpecialAsset(){
		return rskSupervisionIndividuation001Service.findPageForSpecialAsset(RequestUtil.getPageParam());
	}
    
    /**
     * 画面联合指标名称
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("queryUniteIndIdList")
	public List<SelectOptionDto> queryUniteIndIdList(){
		Map<String, Object> map = RequestUtil.getPageParam();
		if(StringUtil.isNotEmpty(map.get("search"))&&StringUtil.byteLength(map.get("search").toString())>200){
			return null;
		}
		else {
			return rskSupervisionIndividuation001Service.queryUniteIndIdList(map);
		}
	}
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskSupervisionIndividuationDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskSupervisionIndividuationDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskSupervisionIndividuationDto> processParameter = new ProcessParameter<RskSupervisionIndividuationDto>();
        WorkFlowParameter<RskSupervisionIndividuationDto> params = new WorkFlowParameter<RskSupervisionIndividuationDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
      //修改时为画面所选记录的规则编号。
        params.setBusinessCode(dto.getIndId());
        params.setBusinessType(BussinessDictCodeListRds.共同业务类型_个性化参数.getValue());
     // 设置流程业务说明, entity即传入的dto
        // 业务说明：“规则编号：”+画面.规则编号t
        params.setComment(entity -> "指标编号:" + "(" + entity.getIndId() + ")"+",组合代码:"+"(" + entity.getPrtBagId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndividuation001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
