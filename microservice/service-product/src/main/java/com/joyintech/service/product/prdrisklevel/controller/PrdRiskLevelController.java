/*
 * 文件名：FinProductPayIntController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.prdrisklevel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.product.prdrisklevel.dto.Page2Dto;
import com.joyintech.service.product.prdrisklevel.dto.PrdRiskLevelDto;
import com.joyintech.service.product.prdrisklevel.service.PrdRiskLevelService;

/**
 * 新发产品风险等级评定查询
 * 2019-10-28
 * yangxiaofeng
 */
@RestController("prdRiskLevelController_dtx")
@RequestMapping("/PrdRiskLevel")
public class PrdRiskLevelController {

    @Autowired
    private PrdRiskLevelService prdRiskLevelService;

    /**
     * 产品代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @PostMapping("productDesignCode/{page}/{length}")
    public Page<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> pageParam,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
    	Page<SelectOptionDto> p = prdRiskLevelService.productDesignCode(page, length,pageParam);
    	return p;
    }

    /**
     * 风险等级评定查询
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PrdRiskLevelDto> queryForPagePrdRiskLevel(@RequestBody Map<String, Object> pageParam,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return prdRiskLevelService.selectForPagePrdRiskLevel(page, length, pageParam);
    }
    
    /**
     * 系统生成风险等级
     * 参数：产品设计编号 平台日期
     */
    @PostMapping("calcRiskGrade")
    public String calcRiskGrade(@RequestBody String prdDesignId) {
    	Map<String,Object> paraMap = new HashMap<String,Object>();
    	paraMap.put("prdDesignId", prdDesignId);
    	paraMap.put("mockDate", DateUtil.platDateFromCache());
    	
		return prdRiskLevelService.calcRiskGrade(paraMap);
    }
    
    /**
     * 新建风险等级评定
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody PrdRiskLevelDto dto) throws Exception {
        return prdRiskLevelService.insertPrdRiskLevel(dto);
    }
    
    /**
     * 删除
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody PrdRiskLevelDto dto) throws Exception {
        return prdRiskLevelService.deletePrdRiskLevel(dto);
    }
    
    /**
     * 修改
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody PrdRiskLevelDto dto) throws Exception {
        return prdRiskLevelService.updatePrdRiskLevel(dto);
    }

    /**
     * 主键查询一条记录
     */
    @PostMapping("selectByPk")
    public PrdRiskLevelDto selectByPk(@RequestBody String pk){
        return prdRiskLevelService.selectByPk(pk);
    }
    
    @PostMapping("selectPageParam")
    public Page2Dto selectPageParam(@RequestBody String prdDesignId){
        return prdRiskLevelService.selectPageParam(prdDesignId);
    }
    
    /**
     * 产品设计系列名称模糊查询
     */
    @PostMapping("selectFinProductName")
    public List<SelectOptionDto> selectFinProductName(@RequestBody Map<String, Object> pageParam){
        return prdRiskLevelService.selectFinProductName(pageParam);
    }
}
