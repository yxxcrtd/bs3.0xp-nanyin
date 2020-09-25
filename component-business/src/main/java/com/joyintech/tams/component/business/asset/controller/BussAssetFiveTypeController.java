/*
 * 文件名：BussAssetFiveTypeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.asset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.component.business.asset.BussAssetFiveTypeService;

/**
 * 资产五级分类
 *  
 * @author renping
 * @version 1.0 2019年7月11日
 * @see BussAssetFiveTypeController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/asset/BussAssetFiveType")
public class BussAssetFiveTypeController {
	
	@Autowired
	private BussAssetFiveTypeService bussAssetFiveTypeService;
	
    /**
     * 系统配置资产五级分类联动处理
     * 
     * @return
     */
    @RequestMapping("doAssetFiveTypeChange")
    @ResponseBody
    public FinProductTypeConfig doAssetFiveTypeChange(@RequestBody FinProductTypeConfig finProductTypeConfig) {
        return bussAssetFiveTypeService.doAssetFiveTypeChange(finProductTypeConfig);
    }
}
