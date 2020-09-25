/*
 * 文件名：EtrIRS001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月11
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrirs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrirs.dto.EtrIRSDto;
import com.joyintech.tams.invest.entrust.etrirs.service.EtrIRSService;

/**
 * 利率互换委托查询页面 〈功能详细描述〉
 * 
 * @author wangpeng
 * @version 1.0 2019年7月11
 * @see EtrIRS001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrIRS001")
@RequestMapping("/invest/entrust/etrirs/EtrIRS001")
public class EtrIRS001Controller extends BaseController {

    /**
     * 利率互换委托service
     */
    @Autowired
    private EtrIRSService etrIRSService;

    /**
     * 初始化页面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.利率行情);
        this.setSelectOptionToModel(model, DictCode.互换方式);
        this.setSelectOptionToModel(model, DictCode.期限品种_利率互换);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        this.setSelectOptionToModel(model, DictCode.币种);
        return super.init(model);
    }

    /**
     * 利率互换委托分页查询 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrIRSDto> selectForPage() {
        Page<EtrIRSDto> page = etrIRSService.selectForPage(RequestUtil.getPageParam());
        return page;
    }

    /**
     * 按钮点击时的check
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
        ValidateGroupDelete.class}) EtrIRSDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 撤回委托 <功能详细描述>
     * 
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrIRSDto dto) {
        List<EtrIRSDto> dtolist = dto.getSelectedItem();
        if (dtolist != null && dtolist.size() > 0) {
            for (EtrIRSDto entity : dtolist) {
            	etrIRSService.recallDelegate(entity);
            }
            return new AjaxResult(this.getMessage("ETR_X_001"));
        }
        return new AjaxResult(this.getMessage("ETR_X_002"));
    }

    /**
     * 关闭委托
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrIRSDto dto) {
        List<EtrIRSDto> dtolist = dto.getSelectedItem();
        if (dtolist != null && dtolist.size() > 0) {
            for (EtrIRSDto entity : dtolist) {
            	etrIRSService.closeDelegate(entity.getEntrustId(), entity.getCloseWay());
            }
            return new AjaxResult(this.getMessage("ETR_X_003"));
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
    }

    /**
     * 删除投资委托 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult deleteDelegate(@RequestBody EtrIRSDto dto) {
        List<EtrIRSDto> list = dto.getSelectedItem();
        if (list != null && list.size() > 0) {
            for (EtrIRSDto entity : list) {
                String entrustId = entity.getEntrustId();
                etrIRSService.deleteDelegate(entrustId);
            }
            return new AjaxResult("删除成功");
        }
        return new AjaxResult("删除失败");
    }

    /**
     * 文件导出 <功能详细描述>
     * 
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return etrIRSService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
