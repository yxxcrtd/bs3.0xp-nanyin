package com.joyintech.tams.svreport.chb.datacheck.totalcheck.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDetailDto;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDto;


/**
 * 功能：总体核查服务
 * @author weiyuqiang
 * @version 1.6.0 2019年04月24日
 * @see TotalCheckMService
 * @since 1.0
 */
@Service
public class TotalCheckMService extends BaseService<ChbTotalCheckDetailDto>{
    


    /**
     * 延时报送检查的查询方法
     * @param param 存储了查询参数的Map
     * @return Map 存储了查询结果的Map
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	public Map<String,ChbTotalCheckDto> searchData(Map<String, Object> param) {
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/TotalCheck/searchData",param,
        		Map.class);
    }
    /**
     * 根据页面的参数查询Table的数据
     * <功能详细描述>
     * @param pageParam 存储了画面参数的MAP
     * @return 存储了查询结果
     * @see
     * @since 1.0
     */
    public Page<ChbTotalCheckDetailDto> searchTable(Map<String, Object> pageParam) {
    	return restTemplate.postForPage(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/TotalCheck/findPage",pageParam,
        		ChbTotalCheckDetailDto.class);
    }
}
