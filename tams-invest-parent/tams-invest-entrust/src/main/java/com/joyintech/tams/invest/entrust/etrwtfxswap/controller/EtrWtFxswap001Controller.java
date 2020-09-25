/*
 * 文件名：EtrWtFxswap001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2019年6月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrwtfxswap.controller;

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
import com.joyintech.tams.invest.entrust.etrwtfxswap.dto.EtrWtFxswapDto;
import com.joyintech.tams.invest.entrust.etrwtfxswap.service.EtrWtFxswapService;

/**
 * 外汇掉期委托查询Controller
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年6月12日
 * @see EtrWtFxswap001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrWtFxswap001")
@RequestMapping("/invest/entrust/etrwtfxswap/EtrWtFxswap001")
public class EtrWtFxswap001Controller extends BaseController {

    @Autowired
    private EtrWtFxswapService etrWtFxswapService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.货币对);
        setSelectOptionToModel(model, DictCode.外汇交易类型);
        setSelectOptionToModel(model, DictCode.交易方向);
        setSelectOptionToModel(model, DictCode.委托状态);
        setSelectOptionToModel(model, DictCode.外汇掉期期限类型);
        return super.init(model);
    }
    
    /**
     * 
     * 项目类委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrWtFxswapDto> selectForPage(){
        Page<EtrWtFxswapDto> page = etrWtFxswapService.selectForPage(
            RequestUtil.getPageParam());
        return page;
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
    public AjaxResult closeDelegate(@RequestBody EtrWtFxswapDto dto) {
        List<EtrWtFxswapDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrWtFxswapDto entity : dtolist){
                etrWtFxswapService.closeDelegate(entity.getEntrustId(), entity.getCloseWay());
            }
            return new AjaxResult(this.getMessage("ETR_X_003"));
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
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
    public AjaxResult recallDelegate(@RequestBody EtrWtFxswapDto dto) {
        List<EtrWtFxswapDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrWtFxswapDto entity : dtolist){
                etrWtFxswapService.recallDelegate(entity);
            }
            return new AjaxResult(this.getMessage("ETR_X_001"));
        }
        return new AjaxResult(this.getMessage("ETR_X_002"));
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
        ValidateGroupDelete.class}) EtrWtFxswapDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
        return etrWtFxswapService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    /**
     * 
     * 删除投资委托
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult deleteDelegate(@RequestBody EtrWtFxswapDto dto) {
        List<EtrWtFxswapDto> list = dto.getSelectedItem();
        if(list!=null && list.size()>0){
            for(EtrWtFxswapDto entity : list){
                String entrustId = entity.getEntrustId();
                etrWtFxswapService.deleteDelegate(entrustId);
            }
            return new AjaxResult("删除成功");
        }
        return new AjaxResult("删除失败");
    }
    
}
