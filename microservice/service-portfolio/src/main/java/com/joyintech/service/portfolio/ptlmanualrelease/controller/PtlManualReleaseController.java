/*
 * 文件名：PtlCashFlowController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlmanualrelease.controller;

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
import com.joyintech.service.portfolio.ptlmanualrelease.dto.PtlManualRelease001Dto;
import com.joyintech.service.portfolio.ptlmanualrelease.service.PtlManualReleaseService;

/**
 * 自定义现金流
 * 〈功能详细描述〉
 * @author chenlei1
 * @version 1.0 2019年12月10日
 * @see PtlManualReleaseController
 * @since 1.0
 */
@RestController
@RequestMapping("/PtlManualRelease")
public class PtlManualReleaseController {
    /**
     * ptlCashFlowService
     */
    @Autowired
    private PtlManualReleaseService ptlManualReleaseService;

   
    /**
     * 
     * 获得组合代码
     * <功能详细描述>
     * @param param param
     * @param page page
     * @param length length
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */

    @PostMapping("/getPortfolioId")
    public List<SuggestDto> getPortfolioId(@RequestBody Map<String, Object> param) {
        return ptlManualReleaseService.getPortfolioId(param);
    }
    
    /**
     * 获取组合代码的下拉框
     * <功能详细描述>
     * @param param param
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    @PostMapping("/getPortfolioIdToNew")
    public List<SelectOptionDto> getPortfolioIdToNew(@RequestBody Map<String, Object> param) {
        return ptlManualReleaseService.getPortfolioIdToNew(param);
    }
  
    /**
     * 
     * 获取交易对手
     * <功能详细描述>
     * @param param param
     * @param page page
     * @param length length
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */

    @PostMapping("/getCounterId")
    public List<SuggestDto> getCounterId(@RequestBody Map<String, Object> param) {
        return ptlManualReleaseService.getCounterId(param);
    }
    /**
     * 证券账户账号联想文本框 <功能详细描述>
     * 
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getSecAcctId")
    public List<SuggestDto> getSecAcctId(String secAcctId)
        throws Exception {
        List<SuggestDto> temp = ptlManualReleaseService.getSecAcctId(secAcctId);
        return temp;
    }
    
    
    /**
     * 质押式正回购查询
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPageRs/{page}/{length}")
    public Page<PtlManualRelease001Dto> selectForPageRs(@RequestBody Map<String, Object> param,
        @PathVariable("page")int page,@PathVariable("length")int length)
        throws Exception {
        return ptlManualReleaseService.selectForPageRs(page,length,param);
    }
    
    /**
     * 质押券查询
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPageRepInfo/{page}/{length}")
    public Page<PtlManualRelease001Dto> findPageRepInfo(@RequestBody Map<String, Object> param,
        @PathVariable("page")int page,@PathVariable("length")int length)
        throws Exception {
        return ptlManualReleaseService.findPageRepInfo(page,length,param);
    }
    @PostMapping("findPageRepInfoList")
    public List<PtlManualRelease001Dto> findPageRepInfoList(@RequestBody Map<String, Object> param)
        throws Exception {
        return ptlManualReleaseService.findPageRepInfoList(param);
    }
    /**
     * 质押式逆回购查询
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPageRb/{page}/{length}")
    public Page<PtlManualRelease001Dto> selectForPageRb(@RequestBody Map<String, Object> param,
        @PathVariable("page")int page,@PathVariable("length")int length)
        throws Exception {
        return ptlManualReleaseService.selectForPageRb(page,length,param);
    }
    
  
    @PostMapping("/selectTradeInfo")
    public PtlManualRelease001Dto selectTradeInfo(@RequestBody Map<String, Object> param)
            throws Exception {
            return ptlManualReleaseService.selectTradeInfo(param);
     }
    
    
    
    /**
     * 质押式正回购解冻
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/revokeRs")
    public String revokeRs(@RequestBody PtlManualRelease001Dto dto) {
        return ptlManualReleaseService.revokeRs(dto);
    }
    
    /**
     * 质押式逆回购解冻
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
   @PostMapping("/revokeRb")
   public String revokeRb(@RequestBody PtlManualRelease001Dto dto) {
       return ptlManualReleaseService.revokeRb(dto);
   }
   

   @RequestMapping("/doCompleteRs")
   public String doCompleteRs(@RequestBody PtlManualRelease001Dto dto) {
	     return ptlManualReleaseService.revokeRs(dto);
   }
   @RequestMapping("/doCompleteRb")
   public String doCompleteRb(@RequestBody PtlManualRelease001Dto dto) {
	     return ptlManualReleaseService.revokeRb(dto);
   }
   
   
   @Transactional
   @DTXTransaction
   @GetMapping("doDelete")
   public void doDelete(String workflowId) {
	   
   }
   
}
