/*
 * 文件名：VersionSwitch001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：曹锦
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.invest.basicdata.versionswitch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.versionswitch.service.VersionSwitch001Service;


/**
 * 版本切换Controller
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author caojin
 * @version 1.0 2020年5月23日
 * @see VersionSwitch001Controller
 * @since 1.0
 */                                                        

@Controller
@PageId("VersionSwitch001")
@RequestMapping("/invest/basicdata/versionswitch/VersionSwitch001")
public class VersionSwitch001Controller extends BaseController {
	
	@Autowired
    private VersionSwitch001Service versionSwitch001Service;

    /**
     * 初始化页面信息
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @Override
    public String init(Model model) {
        
    	super.setSelectOptionToModel(model, DictCode.版本类型);
    	
    	//初始化版本类型
    	String vesionType = versionSwitch001Service.selectVersionType();
    	
    	model.addAttribute("vesionType", vesionType);
        return super.init(model);
    }
    
    
    /**
     * 切换版本
     *
     * @param Map<String,Object>  map
     *  
     * @return Map<String,Object>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public Map<String,Object> save(@RequestBody Map<String,Object>  map) {
    	return versionSwitch001Service.save(map);       
    }
        
}
