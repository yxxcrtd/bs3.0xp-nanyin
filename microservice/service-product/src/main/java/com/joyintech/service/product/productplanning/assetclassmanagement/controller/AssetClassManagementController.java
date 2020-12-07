package com.joyintech.service.product.productplanning.assetclassmanagement.controller;

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
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotationDto;
import com.joyintech.service.product.productplanning.assetclassmanagement.dto.AssetClassManagementDto;
import com.joyintech.service.product.productplanning.assetclassmanagement.service.AssetClassManagementService;

/**
 * 资产分类管理
 * 
 * @author wangqidong
 * @version 1.0 2019年12月10日
 * @see AssetClassManagementController
 * @since 1.0
 */
@RestController("backAssetClassManagementController_dtx")
@RequestMapping("/AssetClassManagement")
public class AssetClassManagementController {

    @Autowired
    private AssetClassManagementService assetClassManagementService;

    @PostMapping("selectForPage/{page}/{length}")
    public Page<AssetClassManagementDto> selectAll(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length)
        throws Exception {
        Page<AssetClassManagementDto> pages = assetClassManagementService.selectForPage(page,
            length, param);
        return pages;
    }
    /**
	 * 新建插入
	 * @param productReportRegisterDto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("doCompeleteAdd")
	public void doCompeleteAdd(@RequestBody AssetClassManagementDto assetClassManagementDto) {
		assetClassManagementService.insertSelective(assetClassManagementDto);
		return;
	}	 
	    /**
	     * 修改
	     * 
	     * @param dto
	     *            页面选中的数据
	     * @param br
	     *            br
	     * @return 结果
	     * @throws Exception
	     *             异常
	     */
	    @Transactional
	    @DTXTransaction
	    @PostMapping("doCompeleteUpdate")
	    public int update(@RequestBody AssetClassManagementDto dto) throws Exception {
	        return assetClassManagementService.doupdate(dto);
	    }
	    /**
	     * 双击查看界面和修改界面的初始化 通过点击的数据信息查询相关数据
	     * 
	     * @param param 查询参数
	     * @return 结果
	     * @see
	     * @since 1.0
	     */
	    @PostMapping("/selectByPrimaryKey")
	    public AssetClassManagementDto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
	        return assetClassManagementService.selectByPrimaryKey(param);
	    }
	    /**
	     * 删除时调用
	     * 
	     * @param param 查询参数
	     * @return 结果
	     * @see
	     * @since 1.0
	     */
	    @Transactional
	    @DTXTransaction
	    @PostMapping("doComplete")
	    public void doComplete(@RequestBody AssetClassManagementDto dto){
	    	assetClassManagementService.doComplete(dto);
	    }
	    
 }
