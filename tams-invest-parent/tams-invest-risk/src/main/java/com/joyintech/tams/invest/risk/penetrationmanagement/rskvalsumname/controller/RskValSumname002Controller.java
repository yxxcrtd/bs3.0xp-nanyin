/*
 * 文件名：RskValSumname002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.controller;

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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.service.RskValSumname002Service;

/**
 * 估值表组合汇总科目映射新建
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月14日
 * @see RskValSumname002Controller
 * @since 1.0
 */
@Controller
@PageId("RskValSumname002")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalsumname/RskValSumname002")
public class RskValSumname002Controller  extends BaseController {
    @Autowired
    private RskValSumname002Service rskValSumname002Service;
    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;
    @Override
    public String init(Model model) {
        String id = RequestUtil.getParam("id");
        //编辑页面显示
        if (StringUtil.isNotEmpty(id)) {
            RskValSumnameDto dto = new RskValSumnameDto();
            dto.setId(id);
            RskValSumnameDto entity = rskValSumname002Service.selectById(dto);
            model.addAttribute("entity", entity);
        }
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
        // 业务代码生成参数
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_估值表汇总科目映射);
        // 新建，系统生成申报附件方案代码
        dto.setId(( bussGetBussinessIdService.buildBussinessCode(parameter)));
        rskValSumname002Service.insert(dto);
        return new AjaxResult(getMessage("FIN_PROD_E_0065"));
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
        rskValSumname002Service.update(dto);
        return new AjaxResult(getMessage("FIN_PROD_E_0065"));
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
        rskValSumname002Service.delete(dto);
        return new AjaxResult(getMessage("RSK_W_0026"));
    }
    
}
