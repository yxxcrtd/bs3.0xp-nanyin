package com.joyintech.service.risk.creditmanagement.ceorg.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.creditmanagement.ceorg.dao.CreditConfInfoDaoMapper;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditConfInfoDto;


/**
 * 机构授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeOrg003Service
 * @since 1.0
 */
@Service
public class CeOrg003Service extends BaseService<CreditConfInfoDto> {
	

	@Autowired
	private CreditConfInfoDaoMapper creditConfInfoMapper;
    
  
    public Page<CreditConfInfoDto> getCreditConfig(int page,int length,Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page,length,() -> creditConfInfoMapper.getCreditConfig(pageParam));
    }

}
