/*
 * 文件名：SppiTest001Controller
 * 版权：Copyright 2017-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020-10-12
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.asset.sppi.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.sppi.dto.SppiImportDto;
import com.joyintech.service.asset.sppi.dto.SppiTest001Dto;
import com.joyintech.service.asset.sppi.service.SppiTest001Service;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 资产标的SPPI测试结果查询控制类（微服务端）
 *
 * @author Jiaoxujin
 * @version 1.0 2020-10-12
 */
@RestController("SppiTest001Controller_dtx")
@RequestMapping("/SppiTest001")
public class SppiTest001Controller {
    @Autowired
    private SppiTest001Service sppiTest001Service;

    /**
     * 分页查询SPPI测试结果列表
     * @param pageParam Map&lt;String, Object&gt;
     * @param page int
     * @param length int
     * @return Page&lt;SppiTest001Dto&gt;
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<SppiTest001Dto> findPage(@RequestBody Map<String, Object> pageParam,
                                         @PathVariable("page") int page, @PathVariable("length") int length) {
        return sppiTest001Service.selectForPage(page, length, pageParam);
    }

    /**
     * 查询适用于SPPI测试的资产标的列表<br />
     * 仅能查询出债券类资产、项目类资产、计划类利率型资产
     * @param paramMap Map&lt;String, Object&gt;
     * @return List&lt;SelectOptionDto&gt;
     */
    @PostMapping("querySppiAssetList")
    public List<SelectOptionDto> querySppiAssetList(@RequestBody Map<String, Object> paramMap) {
        return sppiTest001Service.querySppiAssetList(paramMap);
    }
    
    /**
     * 查询债券ID是否重复
     * @param list
     * @return
     */
    @PostMapping("getBondId")
    public List<SppiImportDto> getBondId(@RequestBody List<SppiImportDto> list){
    	return sppiTest001Service.getBondId(list);
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
	@PostMapping("importData")
	@Transactional
    @DTXTransaction
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
		String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
		String bankCode=param.get("bankCode")==null?null:param.get("bankCode").toString();
        return this.sppiTest001Service.importData(dataList,tempList,bankCode,orgCode,deptCode);
    }
	
	/**
	 * 查询中债导入详情
	 * @param finprodId
	 * @return
	 */
	@GetMapping("selectByBondId")
	public SppiImportDto selectByBondId(String finprodId) {
		return sppiTest001Service.selectByBondId(finprodId);
	}
}
