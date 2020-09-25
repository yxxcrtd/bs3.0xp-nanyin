package com.joyintech.service.portfolio.ptlcashflowgapanalysis.controller;
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
import com.joyintech.service.portfolio.ptlcashflowgapanalysis.dto.PtlCashFlowGapAnalysisDto;
import com.joyintech.service.portfolio.ptlcashflowgapanalysis.service.PtlCashFlowGapAnalysisService;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysisDto;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.service.PtlSingleIssuerPositionAnalysisService;

@RestController
@RequestMapping("/PtlCashFlowGapAnalysis")
public class PtlCashFlowGapAnalysisController {
	
	@Autowired
	private PtlCashFlowGapAnalysisService ptlCashFlowGapAnalysisService;
	/**
     * 获取组合代码
     * @param map 请求参数
     * @return 组合代码集合
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map){
        return ptlCashFlowGapAnalysisService.getPortfolioId(map);
    }
	
	 /**
     * 获取用户权限下拉框列表
     * @return 用户下拉框列表
     * @see
     * @since 1.0
     */
    @PostMapping("getFundAccount")
    public List<SelectOptionDto> getFundAccount(@RequestBody Map<String,Object> map){
    	List<SelectOptionDto> selectOptDto = ptlCashFlowGapAnalysisService.getFundAccount(map);
        return selectOptDto;
    }
    
    /**
	 * 画面查询方法
	 *
	 * @param param 画面参数
	 * @param page 页码数
	 * @param length 页面长度
	 * @return 查询结果集
	 * @throws Exception 异常
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<PtlCashFlowGapAnalysisDto> selectAll(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
					throws Exception {
		return ptlCashFlowGapAnalysisService.selectForPage(page, length, param);
	}
	
	/**
	 * 画面查询方法
	 *
	 * @param param 画面参数
	 * @param page 页码数
	 * @param length 页面长度
	 * @return 查询结果集
	 * @throws Exception 异常
	 */
/*	@PostMapping("findPage1/{page}/{length}")
	public Page<PtlCashFlowGapAnalysisDto> selectAll1(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
					throws Exception {
		return ptlCashFlowGapAnalysisService.selectForPage1(page, length, param);
	}*/
	
	@PostMapping("selectForList")
    public List<PtlCashFlowGapAnalysisDto> selectForList(@RequestBody Map<String, Object> param)
                    throws Exception {
        return ptlCashFlowGapAnalysisService.selectForList(param);
    }
	
}
