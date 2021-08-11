package com.joyintech.service.risk.creditrisklimitmanagement.linesupervision.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.creditrulemanage.dto.CreditRuleInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linesupervision.dto.LineSupervisionDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linesupervision.service.LineSupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;


/**
 * 额度监测
 * @author gejiaao
 * @version 1.0 2020年03月01日
 * @see LineSupervisionController
 * @since 1.0
 */
@RestController("LineSupervisionCtl_dtx")
@RequestMapping("/LineSupervision")
public class LineSupervisionController {

	/**
	 * LineSupervisionService
	 */
	@Autowired
	private LineSupervisionService lineSupervisionService;
	 

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CreditRuleInfoDto> selectForPage(@RequestBody Map<String, Object> param,
												 @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return lineSupervisionService.selectForPage(page, length, param);
    }

	/**
	 * 主体名称联想
	 * @return 查询结果集
	 */
	@PostMapping("getCustomerName/{page}/{length}")
	public Page<SelectOptionDto> getCustomerName(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
		return lineSupervisionService.getCustomerName(map, page, length);
	}

	/**
	 * 查询分项交易明细
	 * @return 查询结果集
	 */
	@PostMapping("getCreditCustomerMst")
	public List<LineSupervisionDto> getCreditCustomerMst(@RequestBody Map<String, Object> map){
		return lineSupervisionService.getCreditCustomerMst(map);
	}


}
