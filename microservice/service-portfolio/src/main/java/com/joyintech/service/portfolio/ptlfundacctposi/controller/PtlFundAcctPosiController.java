/*
 * 文件名：PtlFundAcctPosiController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlfundacctposi.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlfundacctposi.dto.PtlFundAcctPosiDto;
import com.joyintech.service.portfolio.ptlfundacctposi.service.PtlFundAcctPosiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 
 * 组合资金账户
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月20日
 * @see PtlFundAcctPosiController
 * @since 1.0
 */
@RestController
@RequestMapping("/PtlFundAcctPosi")
public class PtlFundAcctPosiController {
	
	@Autowired
	private PtlFundAcctPosiService ptlFundAcctPosiService;
	
	/**
     * 画面查询方法
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<PtlFundAcctPosiDto> selectAll(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return ptlFundAcctPosiService.selectForPage(page,length,param);
    }
	
    /**
     * 画面查询方法
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage1/{page}/{length}")
    public Page<PtlFundAcctPosiDto> selectAll1(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return ptlFundAcctPosiService.selectForPage1(page,length,param);
    }
    
    /**
     * 画面查询方法
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage2/{page}/{length}")
    public Page<PtlFundAcctPosiDto> selectAll2(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return ptlFundAcctPosiService.selectForPage2(page,length,param);
    }
    
    /**
     * 
     * 获取组合代码下拉列表
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> pageParam,
                                                @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return ptlFundAcctPosiService.getPortfolioId(pageParam,page,length);
    }
    
}
