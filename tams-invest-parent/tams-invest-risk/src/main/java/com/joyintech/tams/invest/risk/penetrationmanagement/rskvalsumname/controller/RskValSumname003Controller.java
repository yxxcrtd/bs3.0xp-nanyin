/*
 * 文件名：RskValSumname003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.service.RskValSumname001Service;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.service.RskValSumname003Service;

/**
 * 估值表组合汇总科目映射明细新建
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月14日
 * @see RskValSumname003Controller
 * @since 1.0
 */
@Controller
@PageId("RskValSumname003")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003")
public class RskValSumname003Controller  extends BaseController {
    @Autowired
    private RskValSumname003Service rskValSumname003Service;
    
    @Autowired
    private RskValSumname001Service rskValSumname001Service;
    
    @Override
    public String init(Model model) {
        RskValSumnameDto dto = new RskValSumnameDto();
        String id = getParam("id");
        dto.setId(id);
        String sumCode = getParam("sumCode");
        //编辑页面
        if(sumCode != null && !"".equals(sumCode)){
            dto.setSumCode(sumCode);
            model.addAttribute(GlobalConstant.ENTITY, rskValSumname003Service.selectByKey(dto));
        }
        else{
            dto.setId(id);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        this.setSelectOptionToModel(model, DictCode.估值字段类型);
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        return super.init(model);
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
        ValidateGroupEdit.class}) RskValSumnameDto dto, BindingResult result) {
    	if (!result.hasErrors()) {
    		if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)&&!StringUtil.equals(dto.getOperateType(), "submit")){
    			//选中记录在业务表中已关联组合时
                if(rskValSumname001Service.getCountById(dto)){
                	return new AjaxResult("该映射关联了组合，是否确定修改？",MessageType.确认消息);
                }	
    		}
        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("insert")
    public AjaxResult insert(@RequestBody RskValSumnameDto dto) {
    	dto.setSumName(dto.getSumName().trim());
        rskValSumname003Service.insert(dto);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }

    /**
     * 编辑操作
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("update")
    public AjaxResult update(@RequestBody RskValSumnameDto dto) {
        rskValSumname003Service.update(dto);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
    /**
     * 删除操作
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody RskValSumnameDto dto) {
        rskValSumname003Service.delete(dto);
        return new AjaxResult(getMessage("RSK_W_0026"));
    }
    @ResponseBody
    @RequestMapping("selecctRskValtblSubjectCodeList")
    public List<SelectOptionDto> selecctRskValtblSubjectCodeList(){
        return rskValSumname003Service.selecctRskValtblSubjectCodeList();
    }
}
