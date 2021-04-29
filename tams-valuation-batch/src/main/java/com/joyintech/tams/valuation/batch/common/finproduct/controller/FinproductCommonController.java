package com.joyintech.tams.valuation.batch.common.finproduct.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.MarketPriceDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.ValFinProductValuationDto;

@RestController("finproductCommonController")
@RequestMapping("FinproductCommon")
public class FinproductCommonController {

	@Autowired
	private FinProductCommonService finProductCommonService;
	
	/**
	 * 市价查询-计划类净值型
	 * @param happenDate
	 * @param finProdId
	 * @param booksetId
	 * @return
	 */
	@GetMapping("getFundMarketPriceForPlanAsset")
	public Date getFundMarketPriceForPlanAsset(@RequestParam("happenDate") String happenDateStr, 
			@RequestParam("finProdId")  String finProdId, @RequestParam("booksetId") String booksetId) {
		Date happenDate = DateUtil.parse(happenDateStr);
		MarketPriceDto dto = finProductCommonService.getFundMarketPriceForPlanAsset(happenDate, finProdId, booksetId);
		if(dto!=null) {
			return dto.getValDate()!=null?dto.getValDate():dto.getSchemeValDate();
		}
        return null;
    }
	
	/**
	 * 市价查询-债券
	 * @param happenDateStr
	 * @param booksetId
	 * @param finProdId
	 * @param invAim
	 * @return
	 */
	@GetMapping("getBondMarketPrice")
	public Date getBondMarketPrice(@RequestParam("happenDate") String happenDateStr, 
			  @RequestParam("booksetId") String booksetId,@RequestParam("finProdId") String finProdId,
			  @RequestParam("invAim") String invAim) {
		  	Date happenDate= DateUtil.parse(happenDateStr);
		  	MarketPriceDto dto = finProductCommonService.getBondMarketPrice(happenDate,booksetId,finProdId,invAim);
		  	if(dto!=null) {
		  		return dto.getValDate()!=null?dto.getValDate():dto.getSchemeValDate();
		  	}
		  	return null;
	}
	
	/**
	 * 市价查询-基金
	 * @param happenDateStr
	 * @param finProdId
	 * @param booksetId
	 * @return
	 */
	@GetMapping("getFundMarketPrice")
	public Date getFundMarketPrice(@RequestParam("happenDate") String happenDateStr, 
				@RequestParam("finProdId")  String finProdId, @RequestParam("booksetId") String booksetId) {
		 	Date happenDate= DateUtil.parse(happenDateStr);
		 	MarketPriceDto dto = finProductCommonService.getFundMarketPrice(happenDate,booksetId,finProdId);
		 	if(dto!=null) {
		 		return dto.getValDate()!=null?dto.getValDate():dto.getSchemeValDate();
		 	}
		  	return null;
	}
	
	/**
	 * 万分收益-计划类货币型
	 * @param finprodMarketId
	 * @param calenderId
	 * @param happenDateStr
	 * @param booksetId
	 * @return
	 */
	@GetMapping("queryFinProductValuation")
	public Date queryFinProductValuation(@RequestParam("finprodMarketId") String finprodMarketId,
				@RequestParam("calenderId") String calenderId,@RequestParam("happenDate") String happenDateStr,
				@RequestParam("booksetId") String booksetId){
		Date happenDate= DateUtil.parse(happenDateStr);
		ValFinProductValuationDto dto = finProductCommonService.queryFinProductValuation(finprodMarketId, calenderId, happenDate, booksetId);
		if(dto!=null) {
			return dto.getValDate();
		}
		return null;
	}
}
