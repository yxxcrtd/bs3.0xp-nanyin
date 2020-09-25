/*
 * 文件名：NonStandardCreditAssetDetails001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.informationdisclosureform.nonstandardcreditassetdetails.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 非标准化债权资产明细
 * 统计组合中非标资产的明细，
 * 包括：融资客户、剩余融资期限（月）、投资品年化收益率、投资模式、合作机构、对应产品代码、占投资组合的比例
 * @author Wangzehui
 * @version 1.0 2018年7月30日
 * @see NonStandardCreditAssetDetails001Controller
 * @since 1.0
 */
@Controller
@PageId("NonStandardCreditAssetDetails001")
@RequestMapping("/invest/report/informationdisclosureform/nonstandardcreditassetdetails/NonStandardCreditAssetDetails001")
public class NonStandardCreditAssetDetails001Controller extends BaseController{

    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        return super.init(model);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
