/*
 * 文件名：PersonalProductReleaseRegist001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductperissregist.controller;

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
import com.joyintech.tams.invest.report.debtreport.repfinproductperissregist.dto.RepFinProductPerIssRegistDto;
import com.joyintech.tams.invest.report.debtreport.repfinproductperissregist.service.RepFinProductPerIssRegistService;

/**
 * 个人产品发行登记
 * 查询支持单条件和多条件组合查询，查询结果支持分页显示，页面默认为空结果，
 * 无符合项纪录，查询结果给出提示。仅对个人产品有效，依照查询条件查询，查询结果不可编辑，可双击查看。
 * @author wangzehui
 * @version 1.0 2018年7月30日
 * @see PersonalProductReleaseRegist001Controller
 * @since 1.0
 */
@Controller
@PageId("RepFinProductPerIssRegist001")
@RequestMapping("/invest/report/debtreport/repfinproductperissregist/RepFinProductPerIssRegist001")
public class RepFinProductPerIssRegist001Controller extends BaseController{
    @Autowired
    private RepFinProductPerIssRegistService repFinProductPerIssRegistService;
     @Override
     @RequestMapping(PAGE_INIT)
     public String init(Model model){
         return super.init(model);
     }
    
     /**
      * 产品名称的联想框
      * @return List List
      * @throws Exception Exception
      */
     @ResponseBody
     @RequestMapping("/getRepFinprodName")
     public List<SelectOptionDto> getRepFinprodName(){
         return repFinProductPerIssRegistService.getRepFinprodName(RequestUtil.getPageParam());
     }
     /**
      * 画面查询方法
      * @return 所有数据
      * @throws Exception 异常
      */
     @ResponseBody
     @RequestMapping("findPage")
     public Page<RepFinProductPerIssRegistDto> selectAll()
         throws Exception {
         return repFinProductPerIssRegistService.selectForPage(
             RequestUtil.getParameters(getRequest()));
     }
     /**
      * 导出信息excel
      * 
      * @return excel
      * @throws Exception 异常
      * @see
      * @since 1.0
      */
     @ResponseBody
     @RequestMapping("export")
     public ResponseEntity<byte[]> export()
         throws Exception {
         return repFinProductPerIssRegistService.export(this.getPageId(),
             RequestUtil.getParameters(getRequest()));
     }
 
}
