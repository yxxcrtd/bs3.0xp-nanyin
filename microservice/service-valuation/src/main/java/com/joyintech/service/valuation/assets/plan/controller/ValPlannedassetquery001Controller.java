/*
 * 文件名：ValPlannedassetquery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：zhangzhao
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.assets.plan.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.valuation.assets.fund.dto.ValAssetsFundQuery001Dto;
import com.joyintech.service.valuation.assets.plan.dto.ValPlannedassetquery001Dto;
import com.joyintech.service.valuation.assets.plan.service.ValPlannedassetquery001Service;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 模板示例<br>
 * 计划类资产查询
 * 
 * @author zhangzhao
 * @version 2.0 2018年7月19日
 * @see ValPlannedassetquery001Controller
 * @since 2.0
 */
@RestController(value="serviceValPlannedassetquery001Controller")
@RequestMapping("/ValPlannedassetquery001")
public class ValPlannedassetquery001Controller extends BaseController {
    
    /**
     * ValPlannedassetquery001Service
     */
    @Autowired
    private ValPlannedassetquery001Service valPlannedassetquery001Service;

 

    /**
     * 债券获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("getBondId/{page}/{length}")
    public Page<SelectOptionDto> getBondId(@RequestBody Map<String,Object> map,
			@PathVariable("page") int page, @PathVariable("length") int length) {
        return valPlannedassetquery001Service.getBondId(page,length,map);
    }




    /**
     * 债券类资产数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValPlannedassetquery001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valPlannedassetquery001Service.findPage(page, length, param);
    }
    
    
    @PostMapping("/findAll")
    public List<ValPlannedassetquery001Dto> findAll(@RequestBody Map<String, Object> param){
    	 return valPlannedassetquery001Service.findAll(param);
    }

}
