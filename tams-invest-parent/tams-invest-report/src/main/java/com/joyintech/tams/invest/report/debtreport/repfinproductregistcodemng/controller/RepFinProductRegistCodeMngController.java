/*
 * 文件名：RepFinProductRegistCodeMng.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductregistcodemng.controller;

import java.util.List;

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
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.report.debtreport.repfinproductregistcodemng.dto.RepFinProductRegistCodeMngDto;
import com.joyintech.tams.invest.report.debtreport.repfinproductregistcodemng.service.RepFinProductRegistCodeMngService;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月19日
 * @see RepFinProductRegistCodeMngController
 * @since 1.0
 */
@Controller
@PageId("RepFinProductRegistCodeMng")
@RequestMapping("/invest/report/debtreport/repfinproductregistcodemng/RepFinProductRegistCodeMng")
public class RepFinProductRegistCodeMngController extends BaseController{
    
    /**
     * 自定义service
     */
    @Autowired
    private RepFinProductRegistCodeMngService repFinProductRegistCodeMngService;
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
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
        return repFinProductRegistCodeMngService.getFinprodMarketId(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 产品登记编码下拉框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getPordRegistCode")
    @ResponseBody
    public List<SelectOptionDto> getPordRegistCode(){
        return repFinProductRegistCodeMngService.getPordRegistCode(RequestUtil.getPageParam());
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
    public Page<RepFinProductRegistCodeMngDto> selectAll()
        throws Exception {
        Page<RepFinProductRegistCodeMngDto> page = repFinProductRegistCodeMngService.select(
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
        return repFinProductRegistCodeMngService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
        throws Exception{
        return this.repFinProductRegistCodeMngService.importData(fileName);
    }
}
