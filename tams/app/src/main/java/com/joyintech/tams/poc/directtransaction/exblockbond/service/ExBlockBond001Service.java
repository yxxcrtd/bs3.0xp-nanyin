package com.joyintech.tams.poc.directtransaction.exblockbond.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.exblockbond.dto.ExBlockBond001Dto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExBlockBond001Service extends BaseService<ExBlockBond001Dto> {

	public ExBlockBond001Dto search(String entrustId){
		  return	restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME + "/ExBlockBond/search?entrustId="+entrustId,
				  ExBlockBond001Dto.class);
		}
	
	/**
     * 新建页面资产代码联想下拉框 <功能详细描述>
     * 
     * @param search
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
                                       + "/ExBlockBond/getfinprodIdForAdd" , map,
            SelectOptionDto.class);
    }
}
