/*
 * 文件名：FileCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.service.ModBintreeConfig001Service;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.service.ModBintreeConfig002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 二叉树模型参数配置
 * @author yuluan
 * @version 1.0 2021年7月1日
 * @see ModBintreeConfig001Controller
 * @since 1.0
 */
@Controller
@PageId("ModBintreeConfig001")
@RequestMapping("/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig001")
public class ModBintreeConfig001Controller extends BaseController {

    @Autowired
    private ModBintreeConfig001Service modBintreeConfig001Service;
    @Autowired
	private ModBintreeConfig002Service modBintreeConfig002Service;
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 画面参数设置
    	super.setSelectOptionToModel(model, DictCode.上市状态);
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), "fileCommonDto"));
        return super.init(model);
    }
    @ResponseBody
    @RequestMapping("/searchPage")
    public Page<ModBintreeConfigDto> findPage()
            throws Exception {
        return modBintreeConfig001Service.findPage(
                RequestUtil.getParameters(getRequest()));
    }
    @RequestMapping("getFinprodIdForBintree")
    @ResponseBody
    public List<SelectOptionDto> getFinprodIdForBintree(@RequestParam Map<String, Object> params) {
      
        return modBintreeConfig001Service.getFinprodIdForBintree(params);
    }
    @SuppressWarnings("deprecation")
	@ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ModBintreeConfigDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ModBintreeConfigDto> processParameter = new ProcessParameter<ModBintreeConfigDto>();
        WorkFlowParameter<ModBintreeConfigDto> params = new WorkFlowParameter<ModBintreeConfigDto>();
        params.setPageId("Specialassets002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_二叉树模型参数配置.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("资产代码"+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodId()+ GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = modBintreeConfig002Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new ModBintreeConfigDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	param.put("pageId", this.getPageId());
        return modBintreeConfig001Service.export(this.getPageId(),param);
    }
    
}
