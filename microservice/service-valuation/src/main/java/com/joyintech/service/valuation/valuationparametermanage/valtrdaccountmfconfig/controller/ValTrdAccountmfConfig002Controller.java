package com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.controller;

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
import com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.dto.ValTrdAccountmfConfig001Dto;
import com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.service.ValTrdAccountmfConfig002Service;
import com.joyintech.system.base.entity.SysWflBusinessList;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 
 * 预提费用配置
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig001
 * @since 1.0
 */
@RestController(value="serviceValTrdAccountmfConfig002Controller")
@RequestMapping("/${application.service}/ValTrdAccountmfConfig002")
public class ValTrdAccountmfConfig002Controller extends BaseController {
	
    /**
     * 费用预提设置service
     */
    @Autowired
    private ValTrdAccountmfConfig002Service valTrdAccountmfConfig002Service;
    
	/**
     * 查询已有的预提费用配置信息
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/duplicateCheck")
    public List<ValTrdAccountmfConfig001Dto> duplicateCheck(@RequestBody ValTrdAccountmfConfig001Dto dto) throws Exception{
            return valTrdAccountmfConfig002Service.duplicateCheck(dto);
    }
    
    /**
     * 检索账套代码列表（只取理财账套）
     * 
     * @param param 过滤参数
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
	@PostMapping("/getBooksetId/{page}/{length}")
	public Page<SelectOptionDto> getBooksetId(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valTrdAccountmfConfig002Service.getBooksetId(page, length, param);

	}

    /**
     * 根据账套代码获取账套日期
     * 
     * @param param 过滤参数
     * @return 账套日期
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetDate")
    public BokBooksetDate getBooksetDate(@RequestBody Map<String, Object> params) throws Exception{
            return valTrdAccountmfConfig002Service.getBooksetDate(params);
    }
    
    /**
     * 根据账套代码获取账套结束日
     * 
     * @param param 过滤参数
     * @return 账套结束日
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetEndDate")
    public BokBookset getBooksetEndDate(@RequestBody Map<String, Object> params) throws Exception{
            return valTrdAccountmfConfig002Service.getBooksetEndDate(params);
    }
    
    /**
     * 根据流水号查询预提费用配置信息
     * 
     * @param param 过滤参数
     * @return 预提费用配置信息
     * @see
     * @since 1.0
     */
    @PostMapping("/findBySeqNo")
    public ValTrdAccountmfConfig001Dto findBySeqNo(@RequestBody Map<String, Object> params) throws Exception{
            return valTrdAccountmfConfig002Service.findBySeqNo(params);
    }
    
	/**
     * 获取流程中的流程Id
     * @param pageId
     * @return 流程代码List
     * @see
     * @since 1.0
     */
    @PostMapping("/getworkflowId")
    public List<SysWflBusinessList> getworkflowId(@RequestBody String pageId) throws Exception{
            return valTrdAccountmfConfig002Service.getworkflowId(pageId);
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
	public void insert(@RequestBody ValTrdAccountmfConfig001Dto dto) throws Exception {
		valTrdAccountmfConfig002Service.doComplete(dto, OperationType.NEW);
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
	public void update(@RequestBody ValTrdAccountmfConfig001Dto dto) throws Exception {
		valTrdAccountmfConfig002Service.doComplete(dto, OperationType.MODIFY);
	}

}
