/*
 * 文件名：RskValSumname001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.controller;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.service.RskValSumname001Service;

/**
 * 估值表组合汇总科目映射
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月14日
 * @see RskValSumname001Controller
 * @since 1.0
 */
@Controller
@PageId("RskValSumname001")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalsumname/RskValSumname001")
public class RskValSumname001Controller  extends BaseController {
    /**
     * rskValSumname001Service
     */
    @Autowired
    private RskValSumname001Service rskValSumname001Service;
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    /**
     * 
     * 查询映射名称
     * @return String
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/getSumName")
    public String getSumName(Model model) {
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("list", rskValSumname001Service.getSumName(map));
        return "invest/risk/penetrationmanagement/rskvalsumname/RskValSumname004";
    }
    /**
     * 
     * 估值表指标结构映射 查询
     * <功能详细描述>
     * @return Page<FinPayPlanAdjustmentDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskValSumnameDto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        //估值表导入汇总科目映射初始化 
        if(StringUtil.isNotEmpty(this.getParam("sumsubMapId"))){
            param.put("sumId", this.getParam("sumsubMapId"));
        }
        if(param.containsKey("sumId")){
            return rskValSumname001Service.selectForPage(param);
        }
        return new Page<RskValSumnameDto>();
    }
    /**
     * 新建按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) RskValSumnameDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}
