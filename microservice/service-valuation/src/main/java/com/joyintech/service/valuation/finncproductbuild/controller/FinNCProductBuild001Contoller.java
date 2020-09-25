/*
 * 文件名：FinNCProductBuild001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.finncproductbuild.controller;

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
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;
import com.joyintech.service.valuation.finncproductbuild.dto.ValProductInfoAcctDto;
import com.joyintech.service.valuation.finncproductbuild.dto.ValProductInfoFeeDto;
import com.joyintech.service.valuation.finncproductbuild.service.FinNCProductBuild001Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 产品信息查询
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see FinNCProductBuild001Contoller
 * @since 1.0
 */
@RestController(value="serviceFinNCProductBuild001Contoller")
@RequestMapping("/FinNCProductBuild001")
public class FinNCProductBuild001Contoller extends BaseController {
    
    /**
     * FinNCProductBuild001Service
     */
    @Autowired
    private FinNCProductBuild001Service finNCProductBuild001Service;
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FinNCProductBuild001Dto> findPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return finNCProductBuild001Service.findPage(page,length,params);
    }
    
    /**
     * 
     * 数据查询
     * @return FinNCProductBuild001Dto
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findByFinprodId")
    public FinNCProductBuild001Dto findByFinprodId(@RequestBody Map<String, Object> params) {
        return finNCProductBuild001Service.findByFinprodId(params);
    }

    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String,Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
        return finNCProductBuild001Service.getFinprodId(page,length,map);
    }
    
    /**
     * 查找产品的资金账户信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getFinprodAcct")
    public List<ValProductInfoAcctDto> getFinprodAcct(@RequestBody Map<String, Object> map) {
        return finNCProductBuild001Service.getFinprodAcct(map);
    }
    
    /**
     * 查找资金账户名称列表
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getAcctName")
    public List<SelectOptionDto> getAcctName(@RequestBody Map<String, Object> map) {
        return finNCProductBuild001Service.getAcctName(map);
    }
    
    /**
     * 查找产品的证券账户信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getSecAccount")
    public List<ValProductInfoAcctDto> getSecAccount(@RequestBody Map<String, Object> map) {
        return finNCProductBuild001Service.getSecAccount(map);
    }
    
    @RequestMapping("/findBankdesc")
    public List<ValProductInfoAcctDto> findBankdesc(@RequestBody Map<String, Object> map) {
        return finNCProductBuild001Service.findBankdesc(map);
    }
    /**
     * 
     * 费用查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFeeList")
    public List<ValProductInfoFeeDto> queryFeeList(@RequestBody Map<String, Object> map) throws Exception{
        return finNCProductBuild001Service.queryFeeList(map);
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
    @PostMapping("/findAll")
    public List<FinNCProductBuild001Dto> findAll(Map<String, Object> params){
            return finNCProductBuild001Service.findAll(params);
        
    }
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryfloatFeeList")
    public List<ValProductInfoFeeDto> queryfloatFeeList(@RequestBody Map<String, Object> map) throws Exception{
        return finNCProductBuild001Service.queryfloatFeeList(map);
    }
    
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryfloatFeeList1")
    public List<ValProductInfoFeeDto> queryfloatFeeList1(@RequestBody Map<String, Object> map) throws Exception{
        return finNCProductBuild001Service.queryfloatFeeList1(map);
    }
    
    /**
     * 
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryfloatFeeList2")
    public List<ValProductInfoFeeDto> queryfloatFeeList2(@RequestBody Map<String, Object> map) throws Exception{
        return finNCProductBuild001Service.queryfloatFeeList2(map);
    }
    
    /**
     * 
     * 删除check
     * @return List
     * @throws Exception 异常
     * @param finprodId
     * @see
     * @since 1.0
     */
    @PostMapping("/deleteCheck")
    public List<FinNCProductBuild001Dto> deleteCheck(@RequestBody String finprodId) throws Exception{
        return finNCProductBuild001Service.deleteCheck(finprodId);
    }
    
	/**
	 * 新建
	 * 
	 * @param dto
	 * @throws Exception
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("insert")
	public void insert(@RequestBody FinNCProductBuild001Dto dto) throws Exception {
		finNCProductBuild001Service.doComplete(dto, OperationType.NEW);
	}

	/**
	 * 修改
	 * 
	 * @param dto
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("update")
	public void update(@RequestBody FinNCProductBuild001Dto dto) throws Exception {
		finNCProductBuild001Service.doComplete(dto, OperationType.MODIFY);
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("delete")
	public void delete(@RequestBody FinNCProductBuild001Dto dto) throws Exception {
		finNCProductBuild001Service.doComplete(dto, OperationType.DELETE);
	}
	
    /**
     * 
     * 费率调整
     * @throws Exception 异常
     * @param dto
     * @see
     * @since 1.0
     */
    @PostMapping("/adjustRate")
    public void adjustRate(@RequestBody ValProductInfoFeeDto dto) throws Exception{
        finNCProductBuild001Service.adjustRate(dto);
    }
    
}
