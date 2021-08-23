/*
 * 文件名：CeOrg002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceorg.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditConfInfoDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.service.risk.creditmanagement.ceorg.service.CeOrg002Service;
import com.joyintech.service.risk.creditmanagement.ceorg.service.CeOrg003Service;


/**
 * 机构授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeOrg002Controller
 * @since 1.0
 */
@RestController("backCeOrg002Controller")
@RequestMapping("/CeOrg002")
public class CeOrg002Controller {

	/**
	 * ceOrg002Service
	 */
	@Autowired
	private CeOrg002Service ceOrg002Service;
	
	/**
	 * ceOrg003Service
	 */
	@Autowired
	private CeOrg003Service ceOrg003Service;
	/**
     * 客户代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	 @PostMapping("/getCustomerId")
	    public List<SelectOptionDto> getCustomerId(@RequestBody Map<String, Object> map){
	        return ceOrg002Service.getCustomerId(map);
	    }
	 

    /**
	 * 额度品种子
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/getAllCeOrgQuote")
	public List<TreeNode> getAllCeOrgQuote(String rank) {
	    return ceOrg002Service.getAllCeOrgQuote(rank);
	}
    
   /**
	 * 额度品种父
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/getParentCeOrgQuote")
	public List<TreeNode> getParentCeOrgQuote(String rank) {
	    return ceOrg002Service.getParentCeOrgQuote(rank);
	}
	
	/**
	 * 额度品种父
	 * @param param
	 * @return
	 */
/*	 @PostMapping("/getTreeCeOrgQuote/{page}/{length}")
	public Page<CreditMstInfoDto> getTreeCeOrgQuote(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) {
		 length=100000000;
	    return ceOrg002Service.getTreeCeOrgQuote(page, length,param);
	}*/
	
	 /**
	 * 额度信息获取
	 * @param indId 指标代码
	 * @return PrdValParam
	 */
    @PostMapping("queryCeOrgBySeqno")
	public CreditLinesOrgDto queryCeOrgBySeqno(@RequestBody Map<String, Object> map) {
		return ceOrg002Service.queryCeOrgBySeqno(map);
	}
    
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param Map
     */
    @PostMapping("/searchRepeatByVmDate")
	public int searchRepeatByVmDate(@RequestBody Map<String, Object> param) {
		return ceOrg002Service.searchRepeatByVmDate(param);
	}
	
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param Map
     */
    @PostMapping("/searchRepeatByVmDateArea")
	public int searchRepeatByVmDateArea(@RequestBody Map<String, Object> param) {
		return ceOrg002Service.searchRepeatByVmDateArea(param);
	}
    /**
     * 新建编辑场合时查询批复号是否重复
     * @param Map
     */
    @GetMapping("/searchRepeatByPflsh")
	public int searchRepeatByPflsh(String pflsh,String seqno,String creditMstId) {
		return ceOrg002Service.searchRepeatByPflsh(pflsh,seqno,creditMstId);
	}
	// add by yushilong 查询是否重复校验 20200629 start
	/**
	 * 新建编辑场合时查询数据是否重复
	 * @param param
	 */
	@PostMapping("/searchRepeat")
	public int searchRepeat(@RequestBody Map<String, Object> param) {
		return ceOrg002Service.searchRepeat(param);
	}
	// add by yushilong 查询是否重复校验 20200629 end
	 /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody CreditLinesOrgDto dto) {
    	ceOrg002Service.insert(dto);
    }
    
    /**
     * 更新
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody CreditLinesOrgDto dto) {
    	ceOrg002Service.update(dto);
    }
	    
	/**
     * 删除
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     */
    @PostMapping("delete")
    public int delete(@RequestBody CreditLinesOrgDto dto) {
        return ceOrg002Service.delete(dto);
    }

	/**
	 * 如果当前数据额度已被占用则不允许操作
	 * by nixiukuan 20191024
	 * @param param
	 */
	@PostMapping("/searchIsUse")
	public Boolean searchIsUse(@RequestBody Map<String, Object> param) {
		return ceOrg002Service.searchIsUse(param);
	}

	/**
	 * 获取机构相关信息
	 * @param param
	 */   
   @PostMapping("getOrgInfo")
   public String getOrgInfo(@RequestBody String customerId) throws Exception {
   	    return  ceOrg002Service.getOrgInfo(customerId);
   }
	
	/**
	 * 获取机构标签列表
	 * @param param
	 */
	  @PostMapping("getCreditConfig/{page}/{length}")
	  public Page<CreditConfInfoDto> getCreditConfig(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
	      return ceOrg003Service.getCreditConfig(page,length,pageParam);
	  }
}
