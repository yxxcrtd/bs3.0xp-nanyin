package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.service;

import java.util.Map;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dao.CreditItemInfoDaoMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;


/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see LimitManage003Service
 * @since 1.0
 */
@Service("backLimitManage003Service")
public class LimitManage003Service extends BaseService<CreditItemInfoDto> {
	

	@Autowired
	private CreditItemInfoDaoMapper creditItemInfoDaoMapper;

	/**
     * 平台日期
     */
	@Autowired(required=false)
	private IPlatformDate iPlatformDate;
  
    public Page<CreditItemInfoDto> getCreditConfig(int page,int length,Map<String, Object> pageParam)
        throws Exception {
    	pageParam.put("ptDate", iPlatformDate.getSysDate());
        return super.queryForPage(page,length,() -> creditItemInfoDaoMapper.getCreditConfig(pageParam));
    }

}
