/*
 * 文件名：TotalCollectionPeriod001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repraisesumregist.controller;

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
import com.joyintech.tams.invest.report.debtreport.repraisesumregist.dto.RepRaiseSumRegistDto;
import com.joyintech.tams.invest.report.debtreport.repraisesumregist.service.RepRaiseSumRegist001Service;

/**
 * 募集期总量登记
 * @author qiangchengxi
 * @version 1.0 2018年7月31日
 * @see RepRaiseSumRegist001Controller
 * @since 1.0
 */
@Controller
@PageId("RepRaiseSumRegist001")
@RequestMapping("/invest/report/debtreport/repraisesumregist/RepRaiseSumRegist001")
public class RepRaiseSumRegist001Controller extends BaseController{
    /**
     * 注入service
     */
    @Autowired
    private RepRaiseSumRegist001Service repRaiseSumRegist001Service;
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)   
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.目标客户类型);
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
    public Page<RepRaiseSumRegistDto> findPage(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repRaiseSumRegist001Service.findPage(map);
    }
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProductCode")
    public List<SelectOptionDto> getProductCode(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return repRaiseSumRegist001Service.getProductCode(map);
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
        return repRaiseSumRegist001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
