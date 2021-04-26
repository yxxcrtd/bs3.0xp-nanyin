/*
 * 文件名：RskValStructure003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalstructure.controller;

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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.util.ExcelUtilForRisk;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalstructure.service.RskValStructure003Service;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalstructure.service.RskValStructureService;

/**
 * 估值表指标结构映射 新建明细
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月10日
 * @see RskValStructure003Controller
 * @since 1.0
 */
@Controller
@PageId("RskValStructure003")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003")
public class RskValStructure003Controller extends BaseController {
    @Autowired
    private RskValStructure003Service rskValStructure003Service;
    
    @Autowired
    private RskValStructureService rskValStructureService;
    
    @Override
    public String init(Model model) {
        RskValStructureDto dto = new RskValStructureDto();
        String id = getParam("id");
        dto.setId(id);
        String srcColIndex = getParam("srcColIndex");
        if(srcColIndex != null && !"".equals(srcColIndex)){
            dto.setSrcColIndex(srcColIndex);
            RskValStructureDto entity =  rskValStructure003Service.select(dto);
        	if(StringUtil.isNotEmpty(entity.getSrcColIndex())&&entity.getSrcColIndex().matches("^[\\d]+$")) {
        		try {
					entity.setSrcColIndex(ExcelUtilForRisk.indexToColumn(Integer.parseInt(dto.getSrcColIndex())));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        else{
            dto.setId(id);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        this.setSelectOptionToModel(model, DictCode.估值字段类型, DictCode.估值字段);
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
        ValidateGroupEdit.class}) RskValStructureDto dto, BindingResult result) {
    	if (!result.hasErrors()) {
    		if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)&&!StringUtil.equals(dto.getOperateType(), "submit")){
    			//选中记录在业务表中已关联组合时
                if(rskValStructureService.getCountById(dto)){
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
    public AjaxResult insert(@RequestBody RskValStructureDto dto) throws Exception {
        rskValStructure003Service.insert(dto);
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
    public AjaxResult update(@RequestBody RskValStructureDto dto) throws Exception {
        rskValStructure003Service.update(dto);
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
    public AjaxResult delete(@RequestBody RskValStructureDto dto) {
        rskValStructure003Service.delete(dto);
        return new AjaxResult(getMessage("RSK_W_0026"));
    }
}
