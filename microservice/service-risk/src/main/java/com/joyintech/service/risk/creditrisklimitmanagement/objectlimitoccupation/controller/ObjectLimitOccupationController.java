/*
 * 文件名：CeAssetGroup001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitoccupation.controller;

import java.util.List;
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
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitoccupation.service.ObjectLimitOccupationService;
import com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.dto.SingleLimitOccupationDto;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditUsingCustomerAjust;


/**
 * 
 * 主体额度占用调整
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see ObjectLimitOccupationController
 * @since 1.0
 */
@RestController
@RequestMapping("/ObjectLimitOccupation")
public class ObjectLimitOccupationController {

	/**
	 * ObjectLimitOccupationService
	 */
	@Autowired
	private ObjectLimitOccupationService objectLimitOccupationService;
	
	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ObjectLimitOccupationDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return objectLimitOccupationService.selectForPage(page, length, param);
    }

    /**
     * 主体名称：下拉框
     *
     * @param params 画面参数
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getObject/{page}/{length}")
    public Page<SelectOptionDto> getObject(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return objectLimitOccupationService.getObject(page, length, param);
    }
    
    @PostMapping("getUserName/{page}/{length}")
    public Page<SelectOptionDto> getUserName(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
        return objectLimitOccupationService.selectUserName(param,page,length);
    }
    
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody CreditUsingCustomerAjust dto) throws Exception {
    	objectLimitOccupationService.insert(dto);
    }
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody CreditUsingCustomerAjust dto) throws Exception {
    	if(dto != null && StringUtils.isNotEmpty(dto.getSeqno())){
    		objectLimitOccupationService.update(dto);
    	}
    }
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("delete")
    public void delete(@RequestBody CreditUsingCustomerAjust dto) throws Exception {
    	if(dto != null && StringUtils.isNotEmpty(dto.getSeqno())){
    		objectLimitOccupationService.delete(dto);
    	}
    }
    
	/**
     * 画面查询主体调整历史
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectHistory/{page}/{length}")
    public Page<ObjectLimitOccupationDto> selectHistory(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return objectLimitOccupationService.selectHistory(page, length, param);
    }
    
    /**
     * 查询额度管理信息
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("selectCreditLine")
    public List<CreditLines> selectCreditLine(@RequestBody Map<String, Object> param) throws Exception {
    	return objectLimitOccupationService.selectCreditLine(param);
    }
}
