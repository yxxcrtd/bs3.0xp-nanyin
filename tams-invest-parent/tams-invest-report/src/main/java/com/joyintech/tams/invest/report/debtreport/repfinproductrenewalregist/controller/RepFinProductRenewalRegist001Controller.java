/*
 * 文件名：RepFinProductRenewalRegist001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductrenewalregist.controller;

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
import com.joyintech.tams.invest.report.debtreport.repfinproductrenewalregist.dto.RepFinProductRenewalRegistDto;
import com.joyintech.tams.invest.report.debtreport.repfinproductrenewalregist.service.RepFinProductRenewalRegist001Service;

/**
 * 产品存续期登记
 * @author fanshuanghu
 * @version 1.0 2018年12月23日
 * @see RepFinProductRenewalRegist001Controller
 * @since 1.0
 */
@Controller
@PageId("RepFinProductRenewalRegist001")
@RequestMapping("/invest/report/debtreport/repfinproductrenewalregist/RepFinProductRenewalRegist001")
public class RepFinProductRenewalRegist001Controller extends BaseController{
    /**
     * 注入service
     */
    @Autowired
    private RepFinProductRenewalRegist001Service repFinProductRenewalRegist001Service;
    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.收益类型,DictCode.开放类型);
        return super.init(model);
    }
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepFinProductRenewalRegistDto> findPage(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repFinProductRenewalRegist001Service.selectForPage(map);
    }
    /**
     * 获取金融产品代码
     * @return 金融产品代码集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getRepFinprodName")
    public List<SelectOptionDto> getRepFinprodName(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repFinProductRenewalRegist001Service.getRepFinprodName(map);
    }
    /**
     * 获取登记编号
     * @return 登记编号实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getRepFinprodRegId")
    public List<SelectOptionDto> getRepFinprodRegId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repFinProductRenewalRegist001Service.getRepFinprodRegId(map);
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
        return repFinProductRenewalRegist001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
 