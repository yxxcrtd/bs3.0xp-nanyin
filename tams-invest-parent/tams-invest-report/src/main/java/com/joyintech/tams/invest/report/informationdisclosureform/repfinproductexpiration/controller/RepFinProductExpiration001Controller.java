/*
 * 文件名：RepFinProductExpiration001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.informationdisclosureform.repfinproductexpiration.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.report.informationdisclosureform.repfinproductestablish.dto.RepFinProductEstablishDto;
import com.joyintech.tams.invest.report.informationdisclosureform.repfinproductexpiration.dto.RepFinProductExpirationDto;
import com.joyintech.tams.invest.report.informationdisclosureform.repfinproductexpiration.service.RepFinProductExpirationService;

/**
 * 产品到期公告
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月14日
 * @see RepFinProductExpiration001Controller
 * @since 1.0
 */
@Controller
@PageId("RepFinProductExpiration001")
@RequestMapping("/invest/report/informationdisclosureform/repfinproductexpiration/RepFinProductExpiration001")
public class RepFinProductExpiration001Controller extends BaseController{
    
    /**
     * 自定义service
     */
    @Autowired
    private RepFinProductExpirationService repFinProductExpirationService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.销售部门,DictCode.文件类型);
        return super.init(model);
    }
    
    /**
     * 
     * 页面组合代码下拉联想框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPortfolioId")
    public List<SelectOptionDto> getPortfolioId()
        throws Exception {
        
        return repFinProductExpirationService.getSelectOption();
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @return Page<RepFinProductEstablishDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepFinProductExpirationDto> selectAll()
        throws Exception {
        Page<RepFinProductExpirationDto> page = repFinProductExpirationService.select(
            RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 生成公告时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) RepFinProductExpirationDto dto, BindingResult result) {
        return commonCheck(result, "");
    }
    
    /**
     * 
     * 生成公告
     * <功能详细描述>
     * @param dto RepFinProductEstablishDto
     * @throws IOException  
     * @see
     * @since 1.0
     */
    @RequestMapping("/generateBulletin")
    public ResponseEntity<byte[]> generateBulletin() throws IOException  {
        Map<String,Object> param = RequestUtil.getPageParam();
        RepFinProductExpirationDto temp = JsonUtils.json2Object((String)param.get("dtoStr"),RepFinProductExpirationDto.class);
        return repFinProductExpirationService.generateBulletin(temp);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return repFinProductExpirationService.export("RepFinProductEstablish001",
            RequestUtil.getParameters(getRequest()));
    }
}
