/*
 * 文件名：CeOrg001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.creditlinemanage.controller;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.dto.CreditTagInfoDto;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.service.CeLine002Service;


/**
 * 额度标签管理
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see CeLine002Controller
 * @since 1.0
 */
@RestController("CeLine002Ctl_dtx")
@RequestMapping("/CeLine002")
public class CeLine002Controller {

	/**
	 * ceLine002Service
	 */
	@Autowired
	private CeLine002Service ceLine002Service;

    /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody CreditTagInfoDto dto) throws Exception {
    	ceLine002Service.insert(dto);
    }
    
    /**
     * 更新
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody CreditTagInfoDto dto) throws Exception {
    	ceLine002Service.update(dto);
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
    public int delete(@RequestBody CreditTagInfoDto dto) throws Exception {
        return ceLine002Service.delete(dto);
    }
	
	
	 /**
	 * 额度信息获取
	 * @param indId 指标代码
	 * @return PrdValParam
	 */
   @PostMapping("queryCeLineBySeqno")
	public CreditTagInfoDto queryCeLineBySeqno(@RequestBody Map<String, Object> map) {
		return ceLine002Service.queryCeLineBySeqno(map);
	}
   
   /**
    * 获取额度标签
    * @return List<SelectOptionDto>
    * @see
    * @since 1.0
    */

   
   @PostMapping("getCreditTagInfoList/{page}/{length}")
   public Page<SelectOptionDto> getCreditTagInfoList(@RequestBody Map<String, Object> pageParam,
                                              @PathVariable("page") int page, @PathVariable("length") int length) {
       return ceLine002Service.getCreditTagInfoList(page, length, pageParam);
   }

   
	  
	/**
	 * 校验标签名称
	 * @param param
	 */
	@PostMapping("/checkTag")
	public Boolean checkTag(@RequestBody Map<String, Object> param) {
		return ceLine002Service.checkTag(param);
	}

	@PostMapping("/checkTagCanDelete")
	public int checkTagCanDelete(@RequestBody CreditTagInfoDto dto) {
		return ceLine002Service.checkTagCanDelete(dto);
	}


	// add by yushilong 20200630 增加额度标签管理导入功能 start
	/**
	 *
	 * 插入数据
	 * @param equityList 参数
	 * @see
	 * @since 1.0
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("/mergeInfoList")
	public void mergeInfoList(@RequestBody List<CreditTagInfoDto> equityList){
		ceLine002Service.mergeInfoList(equityList);
	}
	// add by yushilong 20200630 增加额度标签管理导入功能 end
}
