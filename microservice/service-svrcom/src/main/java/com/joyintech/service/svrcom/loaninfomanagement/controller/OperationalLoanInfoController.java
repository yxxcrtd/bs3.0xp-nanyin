package com.joyintech.service.svrcom.loaninfomanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfo001Service;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.svreport.loaninfomanagement.service.OperationalLoanInfo001Service;

/**
 * 贷款明细信息维护 服务Controller
 * @author hpp
 *
 */
@RestController
@RequestMapping("/OperationalLoanInfo")
public class OperationalLoanInfoController {

	/**
	 * 资产要素service
	 */
    @Autowired
    private OperationalInfo001Service operationalInfo001Service;
    
	@Autowired
	private OperationalLoanInfo001Service operationalLoanInfo001Service;
	
	/**
     * 查询列表
     * 
     * @return Page
     */
    @PostMapping("/queryData")
    public String queryData(@RequestBody Map<String, Object> param){
        return operationalInfo001Service.queryData(param);
    }
    
    /**
	 * 根据主键删除数据
	 * @param dto
	 */
	@PostMapping("/doDelete")
	public void doDelete(@RequestBody Map<String, Object> param){
		operationalLoanInfo001Service.doDelete(param);
	}
	
    /**
     * 根据主键查询资产收益权贷款明细基本信息表数据
     * @param param
     * @return
     */
    @PostMapping("/selectByPrimaryKey")
    public OperationalLoanInfo001Dto selectByPrimaryKey(@RequestBody Map<String, Object> param){
    	return operationalLoanInfo001Service.selectByPrimaryKey(param);
    }
    
    /**
     * 查询产品资产代码
     * @param param
     * @return
     */
    @PostMapping("/queryPbcAssetCode")
	public List<SelectOptionDto> queryPbcAssetCode(@RequestBody Map<String, Object> param){
		return operationalLoanInfo001Service.queryPbcAssetCode(param);
	}
    @PostMapping("/queryPbcAssetType")
	public String queryPbcAssetType(@RequestBody Map<String, Object> param){
		return operationalLoanInfo001Service.queryPbcAssetType(param);
	}
    
    /**
     * 保存提交
     * @param dto
     */
    @PostMapping("/doSave")
    public void doSave(@RequestBody OperationalLoanInfo001Dto dto){
    	operationalLoanInfo001Service.doSave(dto);
    }
    
    @PostMapping("/checkAssetCodeExist")
    public int checkAssetCodeExist(@RequestBody String assetCode){
    	return operationalLoanInfo001Service.checkAssetCodeExist(assetCode);
    }
    
    /**
     * 根据计划名称查询计划id以及待维护表ID
     */
    @PostMapping("/queryPlanInfo")
    public OperationalLoanInfo001Dto queryPlanInfo(@RequestBody String pid){
    	return operationalLoanInfo001Service.queryPlanInfo(pid);
    }
    
    /**
     * 
     * 省市县联动
     * <功能详细描述>
     * @param param
     * @return
     */
    @PostMapping("/getAddressSelectOption")
    public List<SelectOptionDto> getAddressSelectOption(@RequestBody Map<String, Object> param){
        return operationalLoanInfo001Service.getAddressSelectOption(param);
    }
}
