package com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.controller;

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
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlRepoPledgeSearchDto;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysisDto;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.service.PtlSingleIssuerPositionAnalysisService;

@RestController
@RequestMapping("/PtlSingleIssuerPositionAnalysis")
public class PtlSingleIssuerPositionAnalysisController {
	
	@Autowired
	private PtlSingleIssuerPositionAnalysisService ptlSingleIssuerPositionAnalysisService;
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
	public Page<PtlSingleIssuerPositionAnalysisDto> selectAll(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
					throws Exception {
		return ptlSingleIssuerPositionAnalysisService.selectForPage(page, length, param);
	}
	
	/**
	 * 导出查询方法
	 *
	 * @param param 画面参数
	 * @param page 页码数
	 * @param length 页面长度
	 * @return 查询结果集
	 * @throws Exception 异常
	 */
	@PostMapping("findPageForExport/{page}/{length}")
	public Page<PtlSingleIssuerPositionAnalysisDto> selectForExport(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
					throws Exception {
		return ptlSingleIssuerPositionAnalysisService.selectForExport(page, length, param);
	}
					
	/**
     * 导出查询方法2
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPageForExportTwo/{page}/{length}")
    public Page<PtlSingleIssuerPositionAnalysisDto> selectForExportTwo(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length)
                    throws Exception {
        return ptlSingleIssuerPositionAnalysisService.selectForExportTwo(page, length, param);
    }
	 /**
     * 获取用户权限下拉框列表
     * @return 用户下拉框列表
     * @see
     * @since 1.0
     */
    @PostMapping("getPublisher/{page}/{length}")
    public Page<SelectOptionDto> getPublisher(@RequestBody Map<String,Object> map,
			@PathVariable("page") int page, @PathVariable("length") int length){
    	Page<SelectOptionDto> selectOptDto = ptlSingleIssuerPositionAnalysisService.getPublisher(map, page, length);
        return selectOptDto;
    }
    
}
