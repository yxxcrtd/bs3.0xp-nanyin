/*
 * 文件名：FinSubmittedModule004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule002Dto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule002Service;

/**
 * 附件明细Controller
 * 可以进行文件下载操作和删除文件
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModule004Controller
 * @since 1.0
 */
@Controller
@PageId("FinSubmittedModule004")
@RequestMapping("/invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule004")
public class FinSubmittedModule004Controller extends BaseController{
    
    /**
     * 申报附件方案管理Service
     */
    @Autowired
    private FinSubmittedModule002Service finSubmittedModule002Service;
    
    /**
     * 附件明细界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        String seqno = RequestUtil.getParam("seqno");
        model.addAttribute("seqno", seqno);
        return super.init(model);
    }
    
    /**
     * 表格查询操作
     * 
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     *//*
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinSubmittedModule002Dto> selectFile()
        throws Exception {  
        Map<String,Object> map = RequestUtil.getPageParam();
        return finSubmittedModule002Service.selectFile(map);
    }*/
    
    /**
     * 表格删除操作
     * 
     * @param dto 删除参数
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody FinSubmittedModule002Dto dto){
        finSubmittedModule002Service.deleteFile(dto.getBusinessId());
        String msg = getMessage("COMMON_I_0011");
        return new AjaxResult(msg);
    }
}
