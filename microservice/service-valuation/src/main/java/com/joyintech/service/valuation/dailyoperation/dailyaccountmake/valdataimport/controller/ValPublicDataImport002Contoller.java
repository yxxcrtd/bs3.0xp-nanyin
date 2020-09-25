/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001LogDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.service.ValPublicDataImport001Service;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValPublicDataImport002Contoller
 * @since 1.0
 */

@RestController(value="serviceValPublicDataImport002Contoller")
@RequestMapping("/ValPublicDataImport002")
public class ValPublicDataImport002Contoller extends BaseController {
	
	
	@Autowired
	private ValPublicDataImport001Service valPublicDataImport001Service;
    
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        model.addAttribute("seqno", getParam("seqno"));
        return super.init(model);
    }

    /**
     * 获取日志详情
     *
     * @return 记账手工凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValPublicDataImport001LogDto> searchList(@RequestBody Map<String,Object> map) {
        // 日志seqno
//    	Map<String,Object> map = RequestUtil.getPageParam();
//    	map.put("seqno", getParam("seqno"));
    	return valPublicDataImport001Service.getInterActionLogDetail(map);
//        return result;
    }
    
  
}