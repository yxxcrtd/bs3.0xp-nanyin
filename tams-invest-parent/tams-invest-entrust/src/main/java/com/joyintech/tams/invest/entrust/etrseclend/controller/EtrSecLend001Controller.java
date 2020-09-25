/*
 * 文件名：EtrSecLend001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yjm
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrseclend.controller;

import java.util.List;

import com.joyintech.tams.invest.entrust.etrseclend.dto.EtrSecLendDto;
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
import com.joyintech.tams.invest.entrust.etrseclend.service.EtrSecLendService;

/**
 * 债券投资委托查询
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年7月5日
 * @see EtrSecLend001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrSecLend001")
@RequestMapping("/invest/entrust/etrseclend/EtrSecLend001")
public class EtrSecLend001Controller extends BaseController {

    /**
     * 债券借贷投资委托service
     */
    @Autowired
    private EtrSecLendService etrSecLendService;
    /**
     * 初始化001查询页面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.回购拆借方向);
        this.setSelectOptionToModel(model, DictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        return super.init(model);
    }
    /**
     *
     * 债券拆借投资委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrSecLendDto> selectForPage(){
        Page<EtrSecLendDto> page = etrSecLendService.selectForPage(
                RequestUtil.getPageParam());
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
            ValidateGroupDelete.class}) EtrSecLendDto dto, BindingResult result) {
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
    public AjaxResult recallDelegate(@RequestBody EtrSecLendDto dto) {
        List<EtrSecLendDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrSecLendDto entity : dtolist){
                etrSecLendService.recallDelegate(entity);
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
    public AjaxResult closeDelegate(@RequestBody EtrSecLendDto dto) {
        List<EtrSecLendDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrSecLendDto entity : dtolist){
                etrSecLendService.closeDelegate(entity.getEntrustId(), entity.getCloseWay());
            }
            return new AjaxResult(this.getMessage("ETR_X_003"));
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
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
    public AjaxResult deleteDelegate(@RequestBody EtrSecLendDto dto) {
        List<EtrSecLendDto> list = dto.getSelectedItem();
        if(list!=null && list.size()>0){
            for(EtrSecLendDto entity : list){
                String entrustId = entity.getEntrustId();
                etrSecLendService.deleteDelegate(entrustId);
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
        return etrSecLendService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}
