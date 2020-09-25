/*
 * 文件名：RskValAssetCode002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.controller;

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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCode002Service;

/**
 *  估值表资产映射维护
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月12日
 * @see RskValAssetCode002Controller
 * @since 1.0
 */
@Controller
@PageId("RskValAssetCode002")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode002")
public class RskValAssetCode002Controller extends BaseController{

    @Autowired
    private RskValAssetCode002Service rskValAssetCode002Service;
    
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    @Override
    public String init(Model model) {
        String id = this.getParam("id");
        if(StringUtil.isNotEmpty(id)){
            model.addAttribute(GlobalConstant.ENTITY, rskValAssetCode002Service.selectAssetCodeByPrimaryKey(id));
        }
        return super.init(model);
    }
    
    /**
     * 保存check
     * <功能详细描述>
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskValtblAssetSubMapDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskValtblAssetSubMapDto dto) {
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
         // 业务代码生成参数
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_估值表资产科目映射);
            dto.setId((baseGetBusinessId.buildBussinessCode(parameter)));
        }
        rskValAssetCode002Service.insert(dto);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
}
