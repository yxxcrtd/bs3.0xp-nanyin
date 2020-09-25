/*
 * 文件名：PtlSubFundsAllocate002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：adrian.zhou
 * 修改时间：2019年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlsubfundsmange.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocateDataDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocateDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.service.PtlSubFundsAllocate001Service;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author adrian.zhou
 * @version 1.0 2019年8月16日
 * @see PtlSubFundsAllocate002Controller
 * @since 1.0
 */
@RestController("ptlSubFundsAllocate002Controller_dtx")
@RequestMapping("/PtlSubFundsAllocate002")
public class PtlSubFundsAllocate002Controller {

    @Autowired
    private PtlSubFundsAllocate001Service ptlSubFundsAllocate001Service;

    /**
     * 子组合间资金调配-组合代码联想框
     *
     * @param pageParam
     * @return
     */
    @PostMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> param) throws Exception {
        return ptlSubFundsAllocate001Service.getPortfolioIds(param);
    }

    @PostMapping("selectOther/{page}/{length}")
    public Page<PtlSubFundsAllocateDto> selectOther(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        String portfolioId = (String) param.get("portfolioId");
        if (StringUtil.isNotEmpty(portfolioId)) {
            return ptlSubFundsAllocate001Service.selectOther(page, length, param);
        }
        return new Page<PtlSubFundsAllocateDto>();
    }

    @PostMapping("getSubFundsAllocate/{page}/{length}")
    public Page<PtlSubFundsAllocateDto> getSubFundsAllocate(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ptlSubFundsAllocate001Service.getSubFundsAllocate(page, length, param);
    }

    @PostMapping("insertBatch")
    @Transactional
    @DTXTransaction
    public String insertBatch(@RequestBody FinProductTradeDto finProductTradeDto) {
        return ptlSubFundsAllocate001Service.insertBatch(finProductTradeDto);
    }
    @Transactional
    @DTXTransaction
    @DeleteMapping("deleteWflData")
    public String deleteWflData(String workflowId) {
        return ptlSubFundsAllocate001Service.deleteWflData(workflowId);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("handleBussFlowData")
    public String handleBussFlowData(@RequestBody PtlSubFundsAllocateDataDto entity) {
        return ptlSubFundsAllocate001Service.handleBussFlowData(entity);
    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("getProfitType")
    public String getProfitType(@RequestBody Map<String, Object> map) {
        return ptlSubFundsAllocate001Service.getProfitTypeById(map);
    }
}
