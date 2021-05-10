package com.joyintech.service.valuation.bokdetailidupdate.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokDetailIdUpdate003Dto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.ResultData;
import com.joyintech.service.valuation.bokdetailidupdate.service.BokDetailIdUpdate002Service;

@RestController(value="serviceBokDetailIdUpdate002Controller")
@RequestMapping("/BokDetailIdUpdate002")
public class BokDetailIdUpdate002Controller extends BaseController{

	@Autowired
    private BokDetailIdUpdate002Service bokDetailIdUpdate002Service;
	
	/**
	 * 账务明细代码加密
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getBokdetailId")
    public String getBokdetailId(@RequestBody BokDetailIdUpdate003Dto dto)
        throws Exception {
    	return bokDetailIdUpdate002Service.getBokdetailId(dto);
    }
	
	/**
	 * 账务明细代码调整
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/doSubmit")
    public ResultData doSubmit(@RequestBody BokDetailIdUpdate003Dto dto)
        throws Exception {
    	return bokDetailIdUpdate002Service.doSubmit(dto);
    }
	
	@PostMapping("/getBusiId")
    public List<SuggestDto> getBusiId(@RequestBody Map<String, Object> params) {
        // 业务类型
        String finprodType = (String)params.get("finprodType");
        // 帐套代码
        String booksetId = (String)params.get("booksetId");
        String param = (String)params.get("param");
        return bokDetailIdUpdate002Service.getBusiId(finprodType, booksetId, param);
    }

}
