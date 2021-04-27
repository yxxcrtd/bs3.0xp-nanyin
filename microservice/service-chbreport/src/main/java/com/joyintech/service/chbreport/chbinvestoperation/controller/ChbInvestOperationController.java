package com.joyintech.service.chbreport.chbinvestoperation.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.chbreport.chbinvestoperation.dto.ChbInvestPrdInfo;
import com.joyintech.service.chbreport.chbinvestoperation.dto.ChbInvsetAssetInfo;
import com.joyintech.service.chbreport.chbinvestoperation.service.ChbInvestOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbInvestOperationController
 * @Description: 非标投资运行报告
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@RestController
@RequestMapping("/ChbInvestOperation")
public class ChbInvestOperationController {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ChbInvestOperationService chbInvestOperationService;


	/**
	 * 查询产品分页下拉框
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	@RequestMapping("queryPrdInfoPage/{page}/{length}")
	public Page<SelectOptionDto> queryPrdInfoPage(@RequestBody Map<String, Object> param,
												  @PathVariable("page") int page, @PathVariable("length") int length){
		return chbInvestOperationService.queryPrdInfoPage(page, length, param);
	}


	/**
	 * 分页查询非标投资运行报告
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 * @throws Exception
	 */
    @PostMapping("findPage/{page}/{length}")
	public Page<ChbInvestPrdInfo> findPage(@RequestBody Map<String, Object> param,
												@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return chbInvestOperationService.selectForPage(page, length, param);
	}

	/**
	 * 查询投资运行报告中产品相关信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("qryChbInvestPrdInfo")
	public ChbInvestPrdInfo qryChbInvestPrdInfo(@RequestBody Map<String, Object> param) throws Exception {
		return chbInvestOperationService.qryChbInvestPrdInfo(param);
	}

	/**
	 * 查询投资运行报告中产品相关信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("qryChbInvsetAssetInfo")
	public List<ChbInvsetAssetInfo> qryChbInvsetAssetInfo(@RequestBody Map<String, Object> param) throws Exception {
		return chbInvestOperationService.qryChbInvsetAssetInfo(param);
	}

}
