///*
// * 文件名：ExcelImpHandle001Contoller.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：weipeng
// * 修改时间：2018年5月9日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
// 
//package com.joyintech.tams.valuation.system.excelimphandle.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.joyintech.framework.annotation.PageId;
//import com.joyintech.framework.base.BaseController;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelImpFileInfoDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelImpResultDto;
//import com.joyintech.tams.valuation.system.excelimphandle.service.ValExcelImpHandle001Service;
//
///**
// * 导入管理
// * @author weipeng
// * @version 1.0 2018年5月9日
// * @see ValExcelImpHandle001Contoller
// * @since 1.0
// */
//@Controller
//@PageId("ValExcelImpHandle001")
//@RequestMapping("/valuation/system/excelimphandle/ValExcelImpHandle001")
//public class ValExcelImpHandle001Contoller extends BaseController {
//    
//    /**
//     * ValExcelImpHandle001Service
//     */
//    @Autowired
//    private ValExcelImpHandle001Service valExcelImpHandle001Service;
//    
//    /**
//     * 文件上传
//     *
//     * @param request 请求
//     * @param response 响应
//     * @param model ModelAndView
//     * @return 保存文件信息
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping(value = "/uploadFile")
//    @ResponseBody
//    public ValExcelImpFileInfoDto uploadFile(HttpServletRequest request,
//            HttpServletResponse response, ModelMap model) throws Exception {
//        return valExcelImpHandle001Service.readFile(request);
//    }
//
//    /**
//     * 保存
//     * @param request request
//     * @param impFileInfo 导入文件信息
//     * @return 画面结果
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
//    public ValExcelImpResultDto save(HttpServletRequest request,
//                                  @RequestBody ValExcelImpFileInfoDto impFileInfo)
//        throws Exception {
//        return valExcelImpHandle001Service.impHandle(request, impFileInfo);
//    }
//}
