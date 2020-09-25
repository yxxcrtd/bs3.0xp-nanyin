/*
 * 文件名：SecuritiesAccountInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.component.business.portfolio.SecuritiesAccountService;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;

/**
 * 证券账户信息
 * @author fanshuanghu
 * @version 1.0 2018年9月14日
 * @see SecuritiesAccountInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/portfolio/SecuritiesAccountInfo")
public class SecuritiesAccountInfoController extends BaseController{
    @Override
    public String init(Model model){
        return "component/portfolio/SecuritiesAccountInfo";
    }
    /**
     * 注入资金账号Service
     */
    @Autowired(required=false)
    private SecuritiesAccountService securitiesAccountService;
    /**
     * 获取证券账号
     * @return 资金账号集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSecAcctId")
    public List<SelectOptionDto> getSecAcctId(){
        if(securitiesAccountService != null){
            return securitiesAccountService.getSecAcctId(RequestUtil.getPageParam());
        }else{
            return null;
        }
    }
    /**
     * 获取证券账号信息
     * @return 证券账号信息实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSecAcctInfo")
    public SecuritiesAccountInfoDto getSecAcctInfo(){
        if(securitiesAccountService != null){
            return securitiesAccountService.getSecAcctInfo(getParam("secAcctId"));
        }else{
            return null;
        }
    }
}
