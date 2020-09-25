/*
 * 文件名：ValProductAssetsQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.productdata.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.productdata.dto.ValProductAssetsQuery001Dto;
import com.joyintech.service.valuation.basedata.productdata.service.ValProductAssetsQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValProductAssetsQuery001Contoller
 * @since 1.0
 */
@RestController(value="serviceValProductAssetsQuery001Contoller")
@RequestMapping("/ValProductAssetsQuery001")
public class ValProductAssetsQuery001Contoller extends BaseController {
    
    /**
     * ValProductAssetsQuery001Service
     */
    @Autowired
    private ValProductAssetsQuery001Service valProductAssetsQuery001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //业务类别下拉
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        this.setSelectOptionToModel(model, DictCode.境内外);
        this.setSelectOptionToModel(model, DictCode.交易流通场所);
        return super.init(model);
    }
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValProductAssetsQuery001Dto> findPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valProductAssetsQuery001Service.findPage(page,length,params);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryPageList")
    public List<ValProductAssetsQuery001Dto> queryPageList(@RequestBody Map<String, Object> params) {
    	return valProductAssetsQuery001Service.queryPageList(params);
    }
            
}

    
    
    