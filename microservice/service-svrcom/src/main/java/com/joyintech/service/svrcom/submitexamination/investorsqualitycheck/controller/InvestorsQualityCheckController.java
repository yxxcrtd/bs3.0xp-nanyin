/*
 * 文件名：DelayVerification001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.svrcom.submitexamination.investorsqualitycheck.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification001Dto;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification002Dto;
import com.joyintech.tams.report.submitexamination.delayverification.service.DelayVerification001Service;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheck001Service;


/**
 * 延时报送检查服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see InvestorsQualityCheckController
 * @since 1.0
 */
@RestController
@RequestMapping("/InvestorsQualityCheck")
public class InvestorsQualityCheckController{
    /**
     * 延时报送检查service
     */
    @Autowired
    private InvestorsQualityCheck001Service investorsQualityCheck001Service;

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/readConfig")
    public Map<String,String>  readConfig(){
        return investorsQualityCheck001Service.readConfig();
    }
    /**
     * 查询左侧-核查项合格率信息
     * @return
     */
    @PostMapping("/searchInvestorsPassRate/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchInvestorsPassRate(@RequestBody Map<String, Object> param){
        return investorsQualityCheck001Service.searchInvestorsPassRate(param);
    }
    /**
     * 查询列表 
     * --证件类型合格率
     * @return Page
     */
    @PostMapping("/searchTableZJLX/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableZJLX(@RequestBody Map<String, Object> param){
        return investorsQualityCheck001Service.searchTableZJLX(param);
    }
    /**
     * 查询列表 
     * --证件匹配合格率
     * @return Page
     */
    @PostMapping("/searchTableZJPP/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableZJPP(@RequestBody Map<String, Object> param){
        return investorsQualityCheck001Service.searchTableZJPP(param);
    }
    /**
     * 查询列表 
     * --及时性合格率
     * @return Page
     */
    @PostMapping("/searchTableJSX/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableJSX(@RequestBody Map<String, Object> param){
        return investorsQualityCheck001Service.searchTableJSX(param);
    }
}
