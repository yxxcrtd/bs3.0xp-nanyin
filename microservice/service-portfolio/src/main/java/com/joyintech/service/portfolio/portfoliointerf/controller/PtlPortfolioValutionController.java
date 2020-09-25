package com.joyintech.service.portfolio.portfoliointerf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.portfolio.portfoliointerf.service.PtlPortfolioValutionService;

/**
 * 投管接口组合相关信息
 * @author wangqidong
 *
 */
@RestController("portfolioValutionController_dtx")
@RequestMapping("/PtlPortfolioValution")
public class PtlPortfolioValutionController {

	@Autowired
    private PtlPortfolioValutionService PtlPortfolioValutionService;
	
	/**
	 * 投管接口（组合估值信息）
	 * @param paramMap
	 * @return
	 */
	@Transactional
    @DTXTransaction
    @PostMapping("/insertPortfolioValution")
    public String insertPortfolioValution(@RequestBody Map<String,Object> paramMap) {
        return PtlPortfolioValutionService.insertPortfolioValution(paramMap);
    }
}
