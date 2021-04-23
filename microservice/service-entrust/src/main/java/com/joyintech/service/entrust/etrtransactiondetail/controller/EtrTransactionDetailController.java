package com.joyintech.service.entrust.etrtransactiondetail.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrtransactiondetail.dto.EtrTransactionDetailDto;
import com.joyintech.service.entrust.etrtransactiondetail.dto.TrdProductDealDetailDto;
import com.joyintech.service.entrust.etrtransactiondetail.service.EtrTransactionDetail001Service;
import com.joyintech.service.entrust.etrtransactiondetail.service.EtrTransactionDetail002Service;

@RestController
@RequestMapping("/EtrTransactionDetail")
public class EtrTransactionDetailController {
	/**
	 * 所有投资委托微服务service
	 */
	@Autowired
	private EtrTransactionDetail001Service etrTransactionDetail001Service;

	/**
	 * 所有投资委托微服务service
	 */
	@Autowired
	private EtrTransactionDetail002Service etrTransactionDetail002Service;

	/**
	 * 
	 * 所有投资委托分页查询 <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<EtrTransactionDetailDto> selectForPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		Page<EtrTransactionDetailDto> result = etrTransactionDetail001Service.selectForPage(page, length, param);
		return result;
	}

	/**
	 * 
	 * 获取组合代码联想文本框信息 <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@GetMapping("getentrustId")
	public List<SuggestDto> getentrustId(String entrustId) throws Exception {
		List<SuggestDto> temp = etrTransactionDetail001Service.getentrustId(entrustId);
		return temp;
	}

	/**
	 * 
	 * 获取资产代码联想文本框信息 <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@GetMapping("getfinprodId")
	public List<SuggestDto> getfinprodId(String finprodId) throws Exception {
		List<SuggestDto> temp = etrTransactionDetail001Service.getfinprodId(finprodId);
		return temp;
	}

	/**
	 * 
	 * 所有投资委托分页查询 <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("selectTransDetailPage/{page}/{length}")
	public Page<TrdProductDealDetailDto> selectTransactionDetailPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		Page<TrdProductDealDetailDto> result = etrTransactionDetail001Service.selectTransDetailPage(page, length, param);
		return result;
	}
}
