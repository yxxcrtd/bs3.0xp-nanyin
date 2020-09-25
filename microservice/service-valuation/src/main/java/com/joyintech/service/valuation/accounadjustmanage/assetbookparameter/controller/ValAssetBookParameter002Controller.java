/*
 * 文件名：ValAssetBookParameter002Controller.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.controller;

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
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.service.ValAssetBookParameter002Service;
import com.joyintech.workflow.engine.constant.OperationType;

@RestController(value="serviceValAssetBookParameter002Controller")
@RequestMapping("ValAssetBookParameter002")
public class ValAssetBookParameter002Controller extends BaseController {

    /**
     * ValAssetBookParameter002Service
     */
    @Autowired
    private ValAssetBookParameter002Service valAssetBookParameter002Service;
    
    
	/**
	 * 下拉框 ： 账套参数
	 * 
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/findParam/{page}/{length}")
    public Page<SelectOptionDto> findParam(@RequestBody Map<String, Object> parameters,
    @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valAssetBookParameter002Service.findParam(page,length,parameters);
    }
	
	/**
	 * 通过参数名，获取参数值
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("getParamValue")
	public ValAssetBookParameter002Dto getParamValue(@RequestBody Map<String, Object> pageParam) {
		return valAssetBookParameter002Service.getParamValue(pageParam);
	}
      
      @Transactional
  	  @DTXTransaction
      @PostMapping("/insert")
      public int insert(@RequestBody ValAssetBookParameter002Dto dto) {
          valAssetBookParameter002Service.doComplete(dto,OperationType.NEW); 
          return 1;
      }
      
      @Transactional
  	  @DTXTransaction
      @PostMapping("/update")
      public int update(@RequestBody ValAssetBookParameter002Dto dto) {
          valAssetBookParameter002Service.doComplete(dto,OperationType.MODIFY); 
          return 1;
      }
      
  	/**
  	 * 获取选中记录
  	 * 
  	 * @return
  	 * @throws Exception
  	 */
  	@PostMapping("getByKey")
  	public ValAssetBookParameter002Dto getByKey(@RequestBody Map<String, Object> params) {
  		return valAssetBookParameter002Service.getByKey(params);
  	}

}
