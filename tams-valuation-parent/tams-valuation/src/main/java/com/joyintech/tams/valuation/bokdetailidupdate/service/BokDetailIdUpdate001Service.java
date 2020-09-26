package com.joyintech.tams.valuation.bokdetailidupdate.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.BokDetailIdUpdate001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service
public class BokDetailIdUpdate001Service extends CommonExportService<BokDetailIdUpdate001Dto> {
//    /**
//     * 账套代码获取
//     *
//     * @return 返回的数据
//     */
//	public List<SelectOptionDto> getBooksetId(Map<String, Object> params) {
//        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/BokDetailIdUpdate001/getBooksetId",
//                params, SelectOptionDto.class);
//	}
    /**
     * 账务明细代码获取
     *
     * @return 返回的数据
     */
	public List<SelectOptionDto> getBokDetailId(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/BokDetailIdUpdate001/getBokDetailId",
                params, SelectOptionDto.class);
	}
    /**
     * 分页查询
     * @param params
     * @return
     */
    public Page<BokDetailIdUpdate001Dto> queryPageList(Map<String, Object> params){
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/BokDetailIdUpdate001/findPage",
    			params, BokDetailIdUpdate001Dto.class);
    }
    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<BokDetailIdUpdate001Dto> queryAllList(Map<String, Object> params){
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/BokDetailIdUpdate001/queryAllList",
                params, BokDetailIdUpdate001Dto.class);
    }
    
    /**
     * 校验是否存在在途跑批业务
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public int checkExecute() {
    	return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/BokDetailIdUpdate001/checkExecute",
                null, int.class);
    }
    
	/**
	 * 查询接口任务
	 * @param params
	 * @return
	 */
	public int checkInterLog(){
		return restTemplate.postForObject( ServiceApplication.VALUATION_SERVICE_NAME + "/BokDetailIdUpdate001/runAs",
                null, int.class);
	}
    
    /**
     * 更新
     * @param params
     * @return
     * @throws ParseException
     */
    public String update(List<BokDetailIdUpdate001Dto> params) throws ParseException{
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/BokDetailIdUpdate001/update",
        		params, String.class);
    }
}
