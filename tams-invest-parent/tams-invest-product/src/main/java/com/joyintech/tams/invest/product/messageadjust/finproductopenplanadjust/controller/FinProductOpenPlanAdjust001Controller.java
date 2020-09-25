/*
 * 文件名：FinProductOpenPlan001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finproductopenplanadjust.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.messageadjust.finproductopenplanadjust.dto.FinProductOpenPlanAdjustDto;
import com.joyintech.tams.invest.product.messageadjust.finproductopenplanadjust.service.FinProductOpenPlanAdjustService;

/**
 * 信息调整管理_开放计划查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月17日
 * @see FinProductOpenPlanAdjust001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductOpenPlanAdjust001")
@RequestMapping("/invest/product/messageadjust/finproductopenplanadjust/FinProductOpenPlanAdjust001")
public class FinProductOpenPlanAdjust001Controller extends BaseController{
    
    /**
     * 
     * 开放计划查询service
     */
    @Autowired
    private FinProductOpenPlanAdjustService finProductOpenPlanAdjustService;
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 下拉框初始化
        setSelectOptionToModel(model, DictCode.申赎开放类型);
        model.addAttribute("RANSOM_ISSUE_MODE_03", DictCodeList.申赎开放类型_申赎开放.getValue());
        return super.init(model);
    }
    
    /**
     * 产品代码联想下拉框
     * @return List<SelectOptionDto>
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
    public List<SelectOptionDto> getProdId() {
        return finProductOpenPlanAdjustService.getFinprodIds(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 开放计划查询
     * <功能详细描述>
     * @return Page<FinProductOpenPlanAdjustDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductOpenPlanAdjustDto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return finProductOpenPlanAdjustService.selectForPage(param);
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
        return finProductOpenPlanAdjustService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
