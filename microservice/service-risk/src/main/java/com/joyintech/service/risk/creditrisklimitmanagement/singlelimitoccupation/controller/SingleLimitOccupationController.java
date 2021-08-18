/*
 * 文件名：CeAssetGroup001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.controller;

import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.dto.SingleLimitOccupationDto;
import com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.service.SingleLimitOccupationService;
import com.joyintech.tams.common.base.entity.CreditRuleInfo;
import com.joyintech.tams.common.base.entity.FinProductType;


/**
 * 
 * 单笔资产额度校验规则
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupationDto
 * @since 1.0
 */
@RestController
@RequestMapping("/SingleLimitOccupation")
public class SingleLimitOccupationController {

	/**
	 * SingleLimitOccupationService
	 */
	@Autowired
	private SingleLimitOccupationService singleLimitOccupationService;
	 
	/**
     * 客户代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	@PostMapping("getLimitLabel/{page}/{length}")
	public Page<SelectOptionDto> getLimitLabel(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
		return singleLimitOccupationService.getLimitLabel(map, page, length);
	}
	
	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<SingleLimitOccupationDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return singleLimitOccupationService.selectForPage(page, length, param);
    }

    /**
     * 资产代码：下拉框
     *
     * @param params 画面参数
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodMarketIds/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return singleLimitOccupationService.getFinprodMarketIds(page, length, param);
    }
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody CreditRuleInfo dto) throws Exception {
    	singleLimitOccupationService.insert(dto);
    }
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody CreditRuleInfo dto) throws Exception {
    	if(dto != null && StringUtils.isNotEmpty(dto.getCreditRuleId())){
    		singleLimitOccupationService.update(dto);
    	}
    }
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("delete")
    public void delete(@RequestBody CreditRuleInfo dto) throws Exception {
    	if(dto != null && StringUtils.isNotEmpty(dto.getCreditRuleId())){
    		singleLimitOccupationService.delete(dto);
    	}
    }
    
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("getFinProductType")
    public FinProductType getFinProductType(@RequestBody Map<String, Object> map) throws Exception {
    	String finprodId = (String) map.get("FINPROD_ID");
    	return singleLimitOccupationService.getFinProductType(finprodId);
    }
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("getBussinessEntityByIndId")
    public SingleLimitOccupationDto getBussinessEntityByIndId(@RequestBody Map<String, Object> map) throws Exception {

    	return singleLimitOccupationService.getBussinessEntityByIndId(map);
    }
    
}
