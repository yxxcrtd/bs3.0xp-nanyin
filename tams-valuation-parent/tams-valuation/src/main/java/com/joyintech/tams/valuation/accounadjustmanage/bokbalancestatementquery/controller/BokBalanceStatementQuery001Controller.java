/*
 * 文件名：BokBalanceStatementQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Zane
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.dto.BokBalanceStatementQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.service.BokBalanceStatementQuery001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;

/**
 * 估值余额表查询Controller
 * 
 * @author 杨智
 * @version 1.0 2018年7月25日
 * @see BokBalanceStatementQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("BokBalanceStatementQuery001")
@RequestMapping("/valuation/accounadjustmanage/bokbalancestatementquery/BokBalanceStatementQuery001")
public class BokBalanceStatementQuery001Controller extends BaseController {

    @Autowired
    private BokBalanceStatementQuery001Service bokBalanceStatementQuery001Service;
//    @Autowired
//    private ValAccountHandle001Service valAccountHandle001Service;
//    @Autowired
//    private ValAssetBook001Service valAssetBook001Service;
    
    @Override
    public String init(Model model) {
        Map<String,Object> map=RequestUtil.getPageParam();
        model.addAttribute("balDate", map.get("balDate"));
        model.addAttribute("finprodId", map.get("finprodId"));
        model.addAttribute("isInit", map.get("isInit"));
        return super.init(model);
    }
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid BokBalanceStatementQuery001Dto dto, BindingResult result) {
         AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        BokBookset bookset = bokBalanceStatementQuery001Service.getFinProductByBookset(dto);
        ajaxResult.setData(bookset);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<BokBalanceStatementQuery001Dto> selectBalanceStatement()
        throws Exception {
        Page<BokBalanceStatementQuery001Dto> page = bokBalanceStatementQuery001Service.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception{
        return bokBalanceStatementQuery001Service.exportExcel(RequestUtil.getParameters(getRequest()));
    }
    
    @RequestMapping("checkBokkSetDate")
    @ResponseBody
    public String checkBokkSetDate(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return bokBalanceStatementQuery001Service.checkBokkSetDate(map);
//        String msg=valAccountHandle001Service.checkBokkSetDateForVluTab(map);
//        return msg;
    }
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId2")
    public List<SuggestDto> getBooksetId2()
        throws Exception {
//        String param = super.getRequest().getParameter("param");
        return bokBalanceStatementQuery001Service.getBooksetId2(RequestUtil.getPageParam());
//        return valAssetBook001Service.getBooksetId2(param);
    }
}
