/*
 * 文件名：CeOrg002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.controller;

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
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.service.LimitManage002Service;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.service.LimitManage003Service;


/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see LimitManage002Controller
 * @since 1.0
 */
@RestController("backLimitManage002Controller")
@RequestMapping("/LimitManage002")
public class LimitManage002Controller  extends BaseController {

	/**
	 * limitManage002Service
	 */
	@Autowired
	private LimitManage002Service limitManage002Service;
	
	/**
	 * ceOrg003Service
	 */
	@Autowired
	private LimitManage003Service limitManage003Service;
	/**
     * 客户代码联想
     * @return 查询结果集
     * @throws Exception 异常
     */
	 @PostMapping("/getCustomerId")
	    public List<SelectOptionDto> getCustomerId(@RequestBody Map<String, Object> map){
	        return limitManage002Service.getCustomerId(map);
	    }
	 

    /**
	 * 额度品种子
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/getAllCeOrgQuote")
	public List<TreeNode> getAllCeOrgQuote(String rank) {
	    return limitManage002Service.getAllCeOrgQuote(rank);
	}
    
   /**
	 * 额度品种父
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/getParentCeOrgQuote")
	public List<TreeNode> getParentCeOrgQuote(String rank) {
	    return limitManage002Service.getParentCeOrgQuote(rank);
	}
	
	/**
	 * 额度品种父
	 * @param param
	 * @return
	 */
/*	 @PostMapping("/getTreeCeOrgQuote/{page}/{length}")
	public Page<CreditMstInfoDto> getTreeCeOrgQuote(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		 length=100000000;
	    return limitManage002Service.getTreeCeOrgQuote(page, length,param);
	}*/
	
	 /**
	 * 额度信息获取
	 * @param indId 指标代码
	 * @return PrdValParam
	 */
    @PostMapping("queryCeOrgBySeqno")
	public LimitManageInfoDto queryCeOrgBySeqno(@RequestBody Map<String, Object> map) {
		return limitManage002Service.queryCeOrgBySeqno(map);
	}
    
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param Map
     */
    @PostMapping("/searchRepeatByVmDate")
	public int searchRepeatByVmDate(@RequestBody Map<String, Object> param) {
		return limitManage002Service.searchRepeatByVmDate(param);
	}
	
    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param Map
     */
    @PostMapping("/searchRepeatByVmDateArea")
	public int searchRepeatByVmDateArea(@RequestBody Map<String, Object> param) {
		return limitManage002Service.searchRepeatByVmDateArea(param);
	}
    /**
     * 新建编辑场合时查询批复号是否重复
     * @param Map
     */
    @GetMapping("/searchRepeatByPflsh")
	public int searchRepeatByPflsh(String pflsh,String seqno,String creditMstId) {
		return limitManage002Service.searchRepeatByPflsh(pflsh,seqno,creditMstId);
	}
	
	/**
	 * 如果当前数据额度已被占用则不允许操作
	 * by nixiukuan 20191024
	 * @param param
	 */
	@PostMapping("/searchIsUse")
	public Boolean searchIsUse(@RequestBody Map<String, Object> param) {
		return limitManage002Service.searchIsUse(param);
	}

	/**
	 * 获取机构相关信息
	 * @param param
	 */   
   @PostMapping("getOrgInfo")
   public String getOrgInfo(@RequestBody String customerId) throws Exception {
   	    return  limitManage002Service.getOrgInfo(customerId);
   }
	
	/**
	 * 获取机构标签列表
	 * @param param
	 */
	  @PostMapping("getCreditConfig/{page}/{length}")
	  public Page<CreditItemInfoDto> getCreditConfig(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
	      return limitManage003Service.getCreditConfig(page,length,pageParam);
	  }
	
	  
	  
	  /**
	     * 插入
	     * @param dto
	     * @throws Exception
	     * @since 1.0
	     */
	    @PostMapping("insert")
	    public void insert(@RequestBody LimitManageInfoDto dto) throws Exception {
	    	limitManage002Service.insert(dto);
	    }
	    
	    /**
	     * 更新
	     * @param dto
	     * @throws Exception
	     * @since 1.0
	     */
	    @PostMapping("update")
	    public void update(@RequestBody LimitManageInfoDto dto) throws Exception {
	    	limitManage002Service.update(dto);
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
	    public int delete(@RequestBody LimitManageInfoDto dto) throws Exception {
	        return limitManage002Service.delete(dto);
	    }
		
		
		 /**
		 * 额度信息获取
		 * @param indId 指标代码
		 * @return PrdValParam
		 */
	    @PostMapping("queryCreditItemInfoBySeqno")
		public CreditItemInfoDto queryCreditItemInfoBySeqno(@RequestBody Map<String, Object> map) {
			return limitManage002Service.queryCreditItemInfoBySeqno(map);
		}
		
}
