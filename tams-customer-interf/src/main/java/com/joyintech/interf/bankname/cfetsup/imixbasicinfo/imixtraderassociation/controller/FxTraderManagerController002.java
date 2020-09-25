/*
 * 文件名：PerformanceBenchmark001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
/**
 * 
 */
package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dto.FxTraderManagerDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.service.FxTraderManager001Service;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 交易员信息管理新增界面
 * 
 * @author zhangjun
 * @version 1.0 2020年1月12日
 * @see FxTraderManagerController002
 * @since 1.0
 */
@Controller
@PageId("FxTraderManager002")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002")
public class FxTraderManagerController002 extends BaseController {

    /**
     * 交易员管理service注入
     */
    @Autowired
    private FxTraderManager001Service fxTraderManagerService;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 新建页面初始化
     */

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 根据主键获取业务实体
        String seqno =getParam("seqno");
        FxTraderManagerDto entity = fxTraderManagerService.selectByPrimaryKey(seqno);
        // 设置到model中(这种塞进去entity，indexId需要在html中写entity？indexId这种形式)
        if(null != entity){
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }
    /**
     * 保存按钮点击时的check
     *
     * @param dto 业务实体
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FxTraderManagerDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    /**
     * 保存按钮点击
     *
     * @param dto dto
     * @throws Exception Exception
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FxTraderManagerDto dto)
        throws Exception {
        dto.setCreatorid(dto.getCreateUser());
        int result = fxTraderManagerService.insert(dto);
        String msg = "success";
        if(result == 0){
            msg="false";
        }
        return new AjaxResult(msg);
    }
    /**
     * 行删除与删除按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete() throws Exception{
        Map param = RequestUtil.getPageParam();
        String seqno = (String) param.get("seqno");
        int result = fxTraderManagerService.deleteByPrimaryKey(seqno);
        String msg = "success";
        if(result == 0){
            msg="false";
        }
        return new AjaxResult(msg);
    }
    /**
     * 行编辑与编辑按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("update")
    public AjaxResult update(@RequestBody @Validated({ValidateGroupDelete.class}) FxTraderManagerDto dto) throws Exception{
        int result = fxTraderManagerService.updateByPrimaryKey(dto);
        String msg = "success";
        if(result == 0){
            msg="false";
        }
        return new AjaxResult(msg);
    }
}
