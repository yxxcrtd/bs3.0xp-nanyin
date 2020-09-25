package com.joyintech.tams.invest.product.productelement.netvalueproduct.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductInvestPeriodDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild003Dto;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild005Dto;

@Service
public class FinNetValueProductBuild005Service   extends BaseService<FinNetValueProductBuild005Dto> {
	//单一固定期限生成
	public List<FinNetValueProductBuild005Dto> getTermType(Map<String,Object> map) {
        return  restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductBuild/getTermType",map,
                                         FinNetValueProductBuild005Dto.class);
    }
	//多固定期限生成
	public List<FinNetValueProductBuild005Dto> getTermType02(Map<String,Object> map) {
		 return  restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                 + "/FinProductBuild/getTermType02",map,
                 FinNetValueProductBuild005Dto.class);
		 
	}
	
	public List<FinNetValueProductBuild005Dto> getTermType03(Map<String,Object> map) {
        return  restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinProductBuild/getTermType03",map,
                FinNetValueProductBuild005Dto.class);
        
   }
	
	public List<BussFinProductInvestPeriodDto> queryPeriodList(Map<String,Object> map) {
        return  restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/BussFinProductInvestPeriod/queryPeriodList",map,
                BussFinProductInvestPeriodDto.class);
        
   }
	
	//判断是否高频开放
	public String getOpenMode(String finprodId) {
		 return  restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                 + "/FinProductBuild/getOpenMode?finprodId="
                 + finprodId,
                 String.class);
	}
	
	
}