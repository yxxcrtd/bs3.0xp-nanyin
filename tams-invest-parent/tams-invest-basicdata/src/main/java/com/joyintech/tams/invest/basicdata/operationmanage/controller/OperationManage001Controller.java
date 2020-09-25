/*
 * 文件名：OperationManage.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.operationmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.basicdata.operationmanage.dto.OperationManageDto;
import com.joyintech.tams.invest.basicdata.operationmanage.service.OperationManageService;
import com.joyintech.tams.constant.DictCode;
/**
 * 
 * 运营管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月18日
 * @see OperationManage001Controller
 * @since 1.0
 */
@Controller
@PageId("OperationManage001")
@RequestMapping("/invest/basicdata/operationmanage/OperationManage001")
public class OperationManage001Controller extends BaseController{
	
	@Autowired
	private OperationManageService operationManageService;
	
	/**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model,DictCode.币种);
    	this.setSelectOptionToModel(model,DictCode.平安业务类型);
    	this.setSelectOptionToModel(model,DictCode.收支方向);
    	this.setSelectOptionToModel(model,DictCode.持仓类型);
    	this.setSelectOptionToModel(model,DictCode.是否);
        return super.init(model);
    }
    
    /**
     * 
     * 组合代码下拉框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getPortfolioId")
    @ResponseBody
    public List<SuggestDto> getPortfolioId(){
        return operationManageService.getPortfolioId(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 组合代码下拉框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getPortfolioId1")
    @ResponseBody
    public List<SuggestDto> getPortfolioId1(){
        return operationManageService.getPortfolioId1(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 页面查询，表格分页
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<OperationManageDto> selectAllMstCaptialAccount()
        throws Exception {
        Page<OperationManageDto> page = operationManageService.selectForPage(RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return operationManageService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));

    }
}
