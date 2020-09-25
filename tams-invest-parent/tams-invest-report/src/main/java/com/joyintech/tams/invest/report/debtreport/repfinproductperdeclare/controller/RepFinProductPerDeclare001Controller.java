/*
 * 文件名：PersonalProductDeclearRegister001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductperdeclare.controller;

import java.util.List;

import org.apache.commons.lang.WordUtils;
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
import com.joyintech.tams.invest.report.debtreport.repfinproductperdeclare.dto.RepFinProductPerDeclareDto;
import com.joyintech.tams.invest.report.debtreport.repfinproductperdeclare.service.RepFinProductPerDeclareService;
import com.joyintech.tams.invest.report.debtreport.repfinproductregistcodemng.dto.RepFinProductRegistCodeMngDto;
/**
 * 
 * 个人产品申报登记
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年7月30日
 * @see RepFinProductPerDeclare001Controller
 * @since 1.0
 */
@Controller
@PageId("RepFinProductPerDeclare001")
@RequestMapping("/invest/report/debtreport/repfinproductperdeclare/RepFinProductPerDeclare001")
public class RepFinProductPerDeclare001Controller extends BaseController{
    /**
     * 自定义service
     */
    @Autowired
    private RepFinProductPerDeclareService repFinProductPerDeclareService;
    
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        return super.init(model);
    }
    
    /**
     * 
     * 产品名称下拉框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getFinprodMarketId")
    @ResponseBody
    public List<SelectOptionDto> getFinprodMarketId(){
        return repFinProductPerDeclareService.getFinprodMarketId(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @return Page<RepFinProductRegistCodeMngDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepFinProductPerDeclareDto> selectAll()
        throws Exception {
        Page<RepFinProductPerDeclareDto> page = repFinProductPerDeclareService.select(
            RequestUtil.getParameters(getRequest()));
        return page;
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
        return repFinProductPerDeclareService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
    /**
     * 
     * 生成申报附件
     * <功能详细描述>
     * @throws Exception  Exception
     * @return ResponseEntity<byte[]>
     * @see
     * @since 1.0
     */
    @RequestMapping("createDeclaration")
    public ResponseEntity<byte[]> createDeclaration() throws Exception{
        return repFinProductPerDeclareService.createDeclaration(
            RequestUtil.getParameters(getRequest()));
    }
    
}
