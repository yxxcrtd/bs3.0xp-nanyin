/*
 * 文件名：ValAssetReclassification001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.controller;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;

/**
 * 模板示例<br>
 * 资产重分类
 * @author 何阳阳
 * @version 1.0 2018年10月24日
 * @see ValAssetReclassification002Contoller
 * @since 1.0
 */

@RestController(value="serviceValAssetReclassification002Contoller")
@RequestMapping("ValAssetReclassification002")
public class ValAssetReclassification002Contoller extends BaseController {
	
	
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public String init(Model model) {
        Map<String,Object> params = RequestUtil.getPageParam();
        model.addAttribute("resultDate",params.get("detailList"));
        return super.init(model);
    }

}