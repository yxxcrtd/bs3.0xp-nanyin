/*
 * 文件名：FundAccountManagementController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstsaledepartment.controller;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstCustomerDto;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto2;
import com.joyintech.service.basicdata.mstsaledepartment.service.MstSaleDepartmentService;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

/**
 * 营业部查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年7月23日
 * @see MstSaleDepartmentController
 * @since 1.0
 */
@RestController("MstSaleDepartmentCtr_dtx")
@RequestMapping("/MstSaleDepartment")
public class MstSaleDepartmentController {
    /**
     * {@Autowired}
     */
    @Autowired
    private MstSaleDepartmentService mstSaleDepartmentService;
    
    /**
     * 
     * 查询上传文件
     * <功能详细描述>
     * @param businessId businessId
     * @return List<FileArray>
     * @see
     * @since 1.0
     */
    @GetMapping("/selectFileByBusinessId")
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId){
        return mstSaleDepartmentService.selectFileByBusinessId(businessId);
    }
    
    @PostMapping("/selectForInterf")
    public List<MstSaleDepartmentDto2> selectForInterf(@RequestBody Map<String, Object> map) {
        return mstSaleDepartmentService.selectForInterf(map);
    }
    
	/**
	 * 营业部信息分页查询 <功能详细描述>
	 * 
	 * @param pageParam
	 *            查询参数
	 * @return 返回查询结果
	 * @throws Exception
	 *             抛出异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/selectForPage/{page}/{length}")
	public Page<MstSaleDepartmentDto> selectForPage(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		Page<MstSaleDepartmentDto> Page = mstSaleDepartmentService.selectForPage(page, length, pageParam);
		return Page;
	}
	
	/**
	 * 营业部详情、修改、查看、唯一性验证
	 * @param dto
	 * @return
	 */
	@PostMapping("/selectByPrimaryKey")
	public MstSaleDepartmentDto selectByPrimaryKey(@RequestBody MstSaleDepartmentDto dto) {
		return mstSaleDepartmentService.selectByPrimaryKey(dto);
	}

	/**
	 * 营业部名称联想查询
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	@PostMapping("/getMstSaleDepartmentName")
	public List<SuggestDto> getMstSaleDepartmentName(@RequestBody Map<String, String> map) {
		return mstSaleDepartmentService.getMstSaleDepartmentName(map);
	}

	/**
	 * 获取券商
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	@PostMapping("/getMstCustomerInfo")
	public List<SelectOptionDto> getMstCustomerInfo(@RequestBody Map<String, Object> map) {
		return mstSaleDepartmentService.getMstCustomerInfo(map);
	}
	
	
	/**
	 * 获取席位号
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	@PostMapping("/getSeatInfoByCustomer")
	public List<SelectOptionDto> getSeatInfoByCustomer(@RequestBody Map<String, Object> map) {
		return mstSaleDepartmentService.getSeatInfoByCustomer(map);
	}
	
	/**
	 * 删除
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("delete")
	public int delete(@RequestBody MstSaleDepartmentDto dto) throws Exception {
		return mstSaleDepartmentService.delete(dto);
	}

	/**
	 * 插入
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("insert")
	public int insert(@RequestBody MstSaleDepartmentDto dto) throws Exception {
		return mstSaleDepartmentService.insert(dto);
	}

	/**
	 * 更新
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("update")
	public int update(@RequestBody MstSaleDepartmentDto dto) throws Exception {
		return mstSaleDepartmentService.update(dto);
	}
	
	//add by huanghao 20201117 获取券商详情 start
		/**
		 * 获取券商详情
		 *
		 * @param map
		 *            
		 * @return 
		 */
		@PostMapping("/getMstCustomerForTradeMarket")
		public List<MstCustomerDto> getMstCustomerForTradeMarket(@RequestBody Map<String, Object> map) {
			return mstSaleDepartmentService.getMstCustomerForTradeMarket(map);
		}
		
		//add by huanghao 20201117 获取券商详情 end
}
