package com.joyintech.service.valuation.component.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.valuation.component.microservice.dto.ValBussFinIntPayDto;
import com.joyintech.service.valuation.component.microservice.service.ValFinFundTargetService;

@RestController("ValFinFundTargetController_dtx")
@RequestMapping("/ValFinFundTarget")
public class ValFinFundTargetController {
	
	@Autowired
    private ValFinFundTargetService finFundTargetService;
	
	/**
     * 
     * 给分红计划页面赋值
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
     @GetMapping("/getIntPayByFinprodId")
     public ValBussFinIntPayDto getIntPayByFinprodId(String finprodId) 
         throws Exception {
         return finFundTargetService.getIntPayByFinprodId(finprodId);
     }   

}
