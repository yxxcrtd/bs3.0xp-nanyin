/*
 * 文件名：RskValAssetCode001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.controller;

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
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCode001Service;

/**
 *  估值表资产科目映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月12日
 * @see RskValAssetCode001
 * @since 1.0
 */
@Controller
@PageId("RskValAssetCode001")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001")
public class RskValAssetCode001Controller extends BaseController{

    @Autowired
    private RskValAssetCode001Service rskValAssetCode001Service;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 初始化查询(不分页)
     * <功能详细描述>
     * @param model
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("list", rskValAssetCode001Service.selectAll(map));
        return "invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode005";
    }
    
    /**
     * 初始化查询(分页)
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskValtblAssetSubMapDto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return rskValAssetCode001Service.selectForPage(param);
    }
    
    /**
     * 初始化明细查询
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPageDtl")
    public Page<RskValtblAssetSubMapDtlDto> findPageDtl(){
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        //估值表导入资产科目映射初始化 
        if(StringUtil.isNotEmpty(this.getParam("assetSubMapId"))){
            param.put("id", this.getParam("assetSubMapId"));
        }
        if(param.containsKey("id")){
            return rskValAssetCode001Service.findPageDtl(param);
        }
        return new Page<RskValtblAssetSubMapDtlDto>();
    }
    
    /**
     * 删除check
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
        ValidateGroupDelete.class}) RskValtblAssetSubMapDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     *  资产科目映射删除
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody RskValtblAssetSubMapDto dto) {
        rskValAssetCode001Service.delete(dto);
        return new AjaxResult(getMessage("RSK_W_0026"));
    }
    
    /**
     * 删除明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteDtl")
    public AjaxResult deleteDtl(@RequestBody RskValtblAssetSubMapDtlDto dto) {
        rskValAssetCode001Service.deleteDtl(dto);
        return new AjaxResult(getMessage("RSK_W_0026"));
    }
    
}
