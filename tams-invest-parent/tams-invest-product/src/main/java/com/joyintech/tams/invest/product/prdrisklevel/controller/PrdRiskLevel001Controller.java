/*
 * 文件名：FinProductPayInt001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.prdrisklevel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.prdrisklevel.dto.PrdRiskLevel001Dto;
import com.joyintech.tams.invest.product.prdrisklevel.service.PrdRiskLevel001Service;

/**
 * 2019-10-28
 * yangxiaofeng
 * 新发产品风险等级评定查询
 */
@Controller
@PageId("PrdRiskLevel001")
@RequestMapping("/invest/product/rddesign/prdrisklevel/PrdRiskLevel001")
public class PrdRiskLevel001Controller extends BaseController {

    @Autowired
    private PrdRiskLevel001Service prdRiskLevel001Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 产品设计编码下拉框
     */
    @ResponseBody
    @RequestMapping("productDesignCode")
    public Page<SelectOptionDto> productDesignCode() throws Exception {
    	return prdRiskLevel001Service.productDesignCode(RequestUtil.getPageParam());
    }
    
    /**
     * 产品设计系列名称下拉框
     */
    @ResponseBody
    @RequestMapping("finProductName")
    public List<SelectOptionDto> finProductName() throws Exception {
    	return prdRiskLevel001Service.selectFinProductName(RequestUtil.getPageParam());
    }

    /**
     * 分页查询
     *
     * @return Page<FinProductPayInt001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<PrdRiskLevel001Dto> findPage() throws Exception {
    	
    	Map<String,Object> map = RequestUtil.getPageParam();
    	
    	Map<String,Object> newMap = new HashMap<String,Object>();
    	
    	String date = (String) map.get("generateTime");
    	
    	newMap.put("finProductName", map.get("finProductName"));
    	newMap.put("prdDesignId", map.get("id"));
    	if(null!=date && date.length()>10) {
    		newMap.put("startDate", date.substring(0,10));
        	newMap.put("endDate", date.substring(date.length()-10,date.length()));
    	}
    	//20200102 add 查询条件   by cxq
    	newMap.put("isLast", DictCodeList.是否_否.getValue());
    	int branch= 0;
    	newMap.put("branch", branch);
        return prdRiskLevel001Service.findPage(newMap);
    }
    
    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return prdRiskLevel001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }
    
    
}
