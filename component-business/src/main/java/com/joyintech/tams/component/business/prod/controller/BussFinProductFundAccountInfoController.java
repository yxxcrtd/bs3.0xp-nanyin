/*
 * 文件名：FundAccountInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.component.business.portfolio.FundAccountService;
import com.joyintech.tams.constant.DictCode;

/**
 * 资金账户信息组件
 * @author fanshuanghu
 * @version 1.0 2018年9月13日
 * @see BussFinProductFundAccountInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductFundAccountInfo")
public class BussFinProductFundAccountInfoController extends BaseController{
    /**
     * 注入资金账号Service
     */
    @Autowired(required=false)
    private FundAccountService fundAccountService;
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	
        this.setSelectOptionToModel(model, DictCode.资金账户类型);
        model.addAttribute("entityName", StringUtil.defaultString(getParam("entityName"),"bussFinProductFundAccountInfoDto"));
        model.addAttribute("finprodMarketIdElement", getParam("finprodMarketIdElement"));
        model.addAttribute("profitTypeElement", getParam("profitTypeElement"));
        return "component/prod/BussFinProductFundAccountInfo";
    }
    /**
     * 获取资金账号
     * @return 资金账号集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAcctNo")
    public Page<SelectOptionDto> getAcctNo(){
        if(fundAccountService != null){
            Map<String, Object> param = RequestUtil.getPageParam();
            return fundAccountService.getAcctNo(param);
        }else{
            return null;
        }
    }
    /**
     * 获取账号信息
     * @return 资金账户实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCctInfo")
    public StlCapacctInfo getCctInfo(){
        if(fundAccountService != null){
            return fundAccountService.getCctInfo(getParam("acctNo"));
        }else{
            return null;
        }
    }
}
