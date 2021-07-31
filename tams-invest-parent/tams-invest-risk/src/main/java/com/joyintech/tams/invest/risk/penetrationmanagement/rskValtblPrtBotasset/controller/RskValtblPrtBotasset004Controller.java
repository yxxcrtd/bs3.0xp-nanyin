/*
 * 文件名：RskValtblPrtBotasset004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;

import com.joyintech.tams.component.business.common.service.CommonAutoCouponService;
import com.joyintech.tams.invest.common.risktrial.dto.TreeNode;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.service.RskValtblPrtBotassetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  底层资产批量映射应用
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2021-03-23
 * @see RskValtblPrtBotasset004Controller
 * @since 1.0
 */
@Controller
@PageId("RskValtblPrtBotasset004")
@RequestMapping("/invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset004")
public class RskValtblPrtBotasset004Controller extends BaseController {

    @Autowired
    private RskValtblPrtBotassetService rskValtblPrtBotassetService;
    
    @Autowired
    private CommonAutoCouponService commonAutoCouponService;
    @Override
    public String init(Model model) {
        model.addAttribute("ptlId", this.getParam("ptlId"));
        model.addAttribute("ptlName", this.getParam("ptlName"));
        return super.init(model);
    }

    /***
     * 解析源科目树
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAllTreeSubjectNode")
    public List<TreeNode> getAllTreeSubjectNode(@RequestBody Map<String,Object> param) {
        List<TreeNode> list = new ArrayList<>();
        /*查询未映射科目，除利息科目*/
        List<RskValtblPrtBotassetMapDto> dataList = rskValtblPrtBotassetService.selectUnMapSubject(param);
        list = rskValtblPrtBotassetService.genTreeNodeData(dataList);
        return list;
    }

    /**
    * @Description: 查询已映射科目
    * @Param:
    * @return:
    * @Author: liupengfei
    * @Date: 2021/3/24
    */
    @ResponseBody
    @RequestMapping("selectMapSubjectInfo")
    public Page<RskValtblPrtBotassetMapDto> selectMapSubjectInfo(){
        return rskValtblPrtBotassetService.selectMapSubjectInfoForPage(RequestUtil.getPageParam());
    }
    /**
    * @Description: 资产代码下拉框
    * @Param:
    * @return:
    * @Author:
    * @Date:
    */
    @ResponseBody
    @RequestMapping("selectAllAsset")
    public List<SelectOptionDto> selectAllAsset(){
        return rskValtblPrtBotassetService.selectAllAsset1(RequestUtil.getPageParam());
    }

    
    /**
     * 映射&应用
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("applyMapForSubject")
    public AjaxResult applyMapForSubject(@RequestBody Map<String,Object> paramMap) {
        rskValtblPrtBotassetService.applyMapForSubject(paramMap);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
    
    /**
     * 取消映射
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("cancelApplyMapForSubject")
    public AjaxResult cancelApplyMapForSubject(@RequestBody Map<String,Object> paramMap) {
        rskValtblPrtBotassetService.cancelApplyMapForSubject(paramMap);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
}
