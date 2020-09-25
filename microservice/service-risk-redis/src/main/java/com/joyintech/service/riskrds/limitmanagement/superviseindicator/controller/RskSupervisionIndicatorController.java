/*
 * 文件名：RskSupervisionIndicatorController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.riskrds.limitmanagement.superviseindicator.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskPortfolioIndicatorDto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskPortfolioPackageDto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicatorDto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.service.RskSupervisionIndicatorService;
import com.joyintech.tams.constant.DictCode;


/**
 * 监督指标
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionIndicatorController
 * @since 1.0
 */
@RestController("backRskSupervisionIndicatorController_dtx")
@RequestMapping("/RskSupervisionIndicator")
public class RskSupervisionIndicatorController {

	/**
	 * RskSupervisionIndicatorService
	 */
	@Autowired
	private RskSupervisionIndicatorService rskSupervisionIndicatorService;
	

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<RskSupervisionIndicator002Dto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        Page<RskSupervisionIndicator002Dto> pageList = rskSupervisionIndicatorService.selectForPage(page, length, param); 
        //不是admin用户不能修改 机构号为null的指标
        if (MultilevelDataAuthorityUtil.isEnabled() && !StringUtil.equals(LoginUserUtil.getUserName(), "admin")) {
            for (RskSupervisionIndicator002Dto dto : pageList.getRoot()) {
                if (StringUtil.isNotEmpty(dto.getInstitutionCode())) {
                    dto.setUpdateFlg(DictCodeList.是否_是.getValue());
                } else {
                    dto.setUpdateFlg(DictCodeList.是否_否.getValue());
                }
            }
        }else{
            for (RskSupervisionIndicator002Dto dto : pageList.getRoot()) {
                dto.setUpdateFlg(DictCodeList.是否_是.getValue());
            } 
        }
        return pageList;
    }

    /**
	 * 指标参数下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@GetMapping("/getIndicatorParamList")
	public List<SelectOptionDto> getIndicatorParamList() {
	    return rskSupervisionIndicatorService.getIndicatorParamList();
	}

	/**
	 * 动态获取禁投或可投项--add by jjpei 20190811---
	 * @param map
	 * @return
	 */
	@PostMapping("/getForbidEnumInfo")
	public List<SelectOptionDto> getForbidEnumInfo(@RequestBody Map<String, Object> map) {
		return rskSupervisionIndicatorService.getForbidEnumInfo(map);
	}

	/**
	 * 组合树
	 * @param param
	 * @return
	 */
	@GetMapping("/getAllPortfolioByProfit")
	public List<TreeNode<RskSupervisionIndicatorDto>> getAllPortfolioByProfit(String indId,String portfName,String profitType,String issueMode,String raiseTypeCond,String investTypeCond) {
	    return rskSupervisionIndicatorService.getAllPortfolioByProfit(indId,portfName,profitType,issueMode,raiseTypeCond,investTypeCond);
	}
	@GetMapping("/getAllPortfolioByOther")
	public List<TreeNode<RskSupervisionIndicatorDto>> getAllPortfolioByOther(String fields,String indId,String portfName,String profitType,String issueMode,String raiseTypeCond,String investTypeCond) {
	    return rskSupervisionIndicatorService.getAllPortfolioByOther(fields,indId,portfName,profitType,issueMode,raiseTypeCond,investTypeCond);
	}
	
	/**
	 * 组合包
	 * @param param
	 * @return
	 */
	@GetMapping("/getAllPortfolioBag")
	public List<TreeNode<RskPortfolioPackageDto>> getAllPortfolioBag(String bagName,String indId) {
	    return rskSupervisionIndicatorService.getAllPortfolioBag(bagName,indId);
	}
	
	 /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
	@DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody RskSupervisionIndicator002Dto dto) throws Exception {
    	rskSupervisionIndicatorService.insert(dto);
    }
    
    /**
     * 更新
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
	@DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody RskSupervisionIndicator002Dto dto) throws Exception {
    	rskSupervisionIndicatorService.update(dto);
    }
    
    /**
     * 更新启用状态
     * @param dto
     * @throws Exception
     */
    @Transactional
	@DTXTransaction
    @PostMapping("updateIsEnabled")
    public void updateIsEnabled(@RequestBody List<RskSupervisionIndicator002Dto> listEntity) throws Exception {
    	for(RskSupervisionIndicator002Dto dto:listEntity){
    	 	rskSupervisionIndicatorService.updateIsEnabled(dto);
    	}
   
    }
    
    /**
	 * 监督指标信息获取
	 * @param indId 指标代码
	 * @return PrdValParam
	 */
    @PostMapping("querySuperIndicatorInfoByIndId")
	public RskSupervisionIndicator002Dto querySuperIndicatorInfoByIndId(@RequestBody Map<String, Object> map) {
		return rskSupervisionIndicatorService.querySuperIndicatorInfoByIndId(map);
	}
    /**
	 * 查询组合关联指标
	 * @param indId 指标代码
	 * @return PrdValParam
	 */
    @PostMapping("queryPortfIndicatorInfoByIndId")
	public List<RskPortfolioIndicatorDto> queryPortfIndicatorInfoByIndId(@RequestBody Map<String, Object> map) {
		return rskSupervisionIndicatorService.queryPortfIndicatorInfoByIndId(map);
	}
    
	
    /**
     * 新建编辑场合时查询监督指标名称是否重复
     * @param indName indId
     */
	@GetMapping("/searchRepeatByIndName")
	public int searchRepeatByIndName(String indName,String indId) {
		return rskSupervisionIndicatorService.searchRepeatByIndName(indName,indId);
	}
	@GetMapping("/isConfigure")
	public int isConfigure(String indId) {
		return rskSupervisionIndicatorService.isConfigure(indId);
	}
	
	/**
     * 删除场合时查询指标是否关联指标
     * @param indId
     */
	@GetMapping("/searchReleteCountByIndId")
	public int searchReleteCountByIndId(String indId) {
		return rskSupervisionIndicatorService.searchReleteCountByIndId(indId);
	}

	/**
	 * 删除场合时查询指标是否关联规则
	 * @param indId
	 */
	@GetMapping("/searchReleteCountByRuleId")
	public int searchReleteCountByRuleId(String indId) {
		return rskSupervisionIndicatorService.searchReleteCountByRuleId(indId);
	}
	@GetMapping("/getAllPortfolioId")
	public List<String> getAllPortfolioId(String indId) {
		return rskSupervisionIndicatorService.getAllPortfolioId(indId);
	}

	/**
     * 删除
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     */
    @Transactional
	@DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody RskSupervisionIndicator002Dto dto) throws Exception {
        return rskSupervisionIndicatorService.delete(dto);
    }
}
