/*
 * 文件名：DelayVerification001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.svrcom.submitexamination.delayverification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification001Dto;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification002Dto;
import com.joyintech.tams.report.submitexamination.delayverification.service.DelayVerification001Service;


/**
 * 延时报送检查服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see DelayVerificationController
 * @since 1.0
 */
@RestController
@RequestMapping("/DelayVerification")
public class DelayVerificationController{
    /**
     * 延时报送检查service
     */
    @Autowired
    private DelayVerification001Service delayVerification001Service;

    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/searchDate")
    public Map<String,DelayVerification001Dto> search(@RequestBody Map<String, Object> param){
    	/* add by songyawei 20200703 获取系统模式：联社模式true;银行模式false start */
    	Boolean flag = MultilevelDataAuthorityUtil.isEnabled();
    	param.put("flag", flag);
    	List<String> listCode = LoginUserUtil.getTreeInstCode(); // 清空  取的用户的所有有权限机构 
    	param.put("listCode", listCode);
    	/* add by songyawei end */
        return delayVerification001Service.searchDate((Map<String, Object>) param.get("param"),(String)param.get("indexType"));
    }
    /**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/searchTable/{page}/{length}")
    public Page<DelayVerification002Dto> searchTable(@RequestBody Map<String, Object> param){
    	/* add by songyawei 20200703 获取系统模式：联社模式true;银行模式false start */
    	Boolean flag = MultilevelDataAuthorityUtil.isEnabled();
    	param.put("flag", flag);
    	List<String> listCode = LoginUserUtil.getTreeInstCode(); // 清空  取的用户的所有有权限机构  
    	param.put("listCode", listCode);
    	/* add by songyawei end */
        return delayVerification001Service.searchTable(param);
    }
}
