package com.joyintech.service.valuation.feetrade.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.feetrade.dto.FeeTradeManagementDto;
import com.joyintech.service.valuation.feetrade.service.FeeTradeManagementService;
/**
 * 费用交易管理
 * @version 1.0 20200121
 * @see FeeTradeManagementController
 * @since 1.0
 */
@RestController("FeeTradeManagementController_dtx")
@RequestMapping("/FeeTradeManagement")
public class FeeTradeManagementController {
	
	@Autowired
    private FeeTradeManagementService feeTradeManagementService;
	
	/**
     * 产品系列列表查询
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FeeTradeManagementDto> findPage(@RequestBody Map<String, Object> pageParam, 
    		@PathVariable("page") int page, @PathVariable("length") int length){
        Page<FeeTradeManagementDto> prdPage = feeTradeManagementService.findPage(pageParam, page, length);
        return prdPage;
    }
    
    /**
     * 查询账套数据
     */
    @RequestMapping("/getBookSetList")
    public List<SelectOptionDto> getBookSetList(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.getBookSetList(params);
    }
    
    /**
     * 查询账套数据
     */
    @RequestMapping("/getShouldPayAmt")
    public BigDecimal getShouldPayAmt(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.getShouldPayAmt(params);
    }
    
    /**
     * 基础科目性质查询
     */
    @RequestMapping("/getBaseSubjectNature")
    public String getBaseSubjectNature(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.getBaseSubjectNature(params);
    }
    
    /**
     * 账务明细代码查询
     */
    @RequestMapping("/getBokDetailId")
    public String getBokDetailId(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.getBokDetailId(params);
    }
    
    /**
     * 获取交易类金融产品
     */
    @RequestMapping("/getFinProductByBookSetId")
    public Map<String, Object> getFinProductByBookSetId(@RequestBody String bookSetId){
        return feeTradeManagementService.getFinProductByBookSetId(bookSetId);
    }
    
    @RequestMapping("/getCcyByBokSetId")
    public String getCcyByBokSetId(@RequestBody String bookSetId){
        return feeTradeManagementService.getCcyByBokSetId(bookSetId);
    }
    
    @RequestMapping("/updateBokDealAccounting")
    public int updateBokDealAccounting(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.updateBokDealAccounting(params);
    }
    
    @RequestMapping("/updateStlOrder")
    public int updateStlOrder(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.updateStlOrder(params);
    }
    
    @RequestMapping("/deleteBokDealAccounting")
    public int deleteBokDealAccounting(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.deleteBokDealAccounting(params);
    }
    
    @RequestMapping("/deleteStlOrder")
    public int deleteStlOrder(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.deleteStlOrder(params);
    }
    
    /**
     * 查询子账套数据
     */
    @RequestMapping("/getSubBookSetList")
    public List<SelectOptionDto> getSubBookSetList(@RequestBody Map<String, Object> params){
        return feeTradeManagementService.getSubBookSetList(params);
    }
}
