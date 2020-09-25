package com.joyintech.service.product.resultscovering.controller;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.joyintech.service.product.resultscovering.dto.FinResultsCovering001Dto;
import com.joyintech.service.product.resultscovering.dto.FinResultsCovering002Dto;
import com.joyintech.service.product.resultscovering.service.FinResultsCoveringService;

@RestController("backFinResultsCoveringController")
@RequestMapping("/FinResultsCovering")
public class FinResultsCoveringController {
	@Autowired
	private FinResultsCoveringService finResultsCoveringService;
	/**
	 * 画面查询方法
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("selectForPage/{page}/{length}")
	public Page<FinResultsCovering001Dto> selectForPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return finResultsCoveringService.selectForPage(page, length, param);
	}
	
	@PostMapping("selectBaseInfo")
	public FinResultsCovering002Dto selectBaseInfo(@RequestBody Map<String, Object> param) {
		return finResultsCoveringService.selectBaseInfo(param);
	}
	
	@PostMapping("getSumAmt")
	public BigDecimal getSumAmt(@RequestBody FinResultsCovering002Dto dto) throws ParseException {
		return finResultsCoveringService.getSumAmt(dto);
	}
	
	@PostMapping("selectInfo")
	public FinResultsCovering002Dto selectInfo(@RequestBody Map<String, Object> param) {
		return finResultsCoveringService.selectInfo(param);
	}
	
	
	@PostMapping("findPtradeid")
	public List<FinResultsCovering001Dto> findPtradeid(@RequestBody String tradeId) {
		return finResultsCoveringService.findPtradeid(tradeId);
	}
	@PostMapping("selectInfoCovering")
	 public List<FinResultsCovering002Dto> selectInfoCovering( @RequestBody Map<String, Object> pageParam){
			return finResultsCoveringService.selectInfoCovering(pageParam);
   }
	
	
    /**
     * 产品代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("queryProdIdInfo/{page}/{length}")
    public Page<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> paramMap,
                                                 @PathVariable int page,@PathVariable int length) {
        return finResultsCoveringService.queryProdIdInfo(paramMap,page,length);
    }
}
