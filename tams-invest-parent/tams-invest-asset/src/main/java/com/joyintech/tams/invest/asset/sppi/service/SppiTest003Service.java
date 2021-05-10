package com.joyintech.tams.invest.asset.sppi.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.asset.sppi.dto.SppiImportDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

@Service
public class SppiTest003Service extends BaseService<SppiImportDto>{

	public SppiImportDto selectByBondId(String finprodId) {
		return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest001/selectByBondId?finprodId="
                        + finprodId, SppiImportDto.class);
	}
}
