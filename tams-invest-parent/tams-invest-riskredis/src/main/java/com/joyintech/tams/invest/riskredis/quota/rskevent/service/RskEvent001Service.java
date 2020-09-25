package com.joyintech.tams.invest.riskredis.quota.rskevent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent001Dto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskEvent;

/**
 * 风险事件管理service
 *
 * @author wangzhen
 * @see RskEvent001Service
 * @since 1.0
 */
@Service
public class RskEvent001Service extends CommonExportService<RskEvent001Dto> {
	  /**
     * 查询界面询事件
     *
     * @param map 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<RskEvent001Dto> selectForPage(Map<String, Object> map){
        Page<RskEvent001Dto> page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/findPage",
                map, RskEvent001Dto.class);
        return page;
    }
    
    public Page<SelectOptionDto> selectMultiOptionEvent(Map<String, Object> map){
    	Page<SelectOptionDto> page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/selectMultiOptionEvent",
                map, SelectOptionDto.class);
        return page;
    }
    
    
    /**
     * 根据风险事件名称查询风险事件
     * @param eventName
     * @return
     */
    public List<RskEvent> selectByEventName(String eventName) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("eventName", eventName);
    	List<RskEvent> list =  restTemplate.postForList(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/selectRskEventList",
                map, RskEvent.class);
		return list;
    }
    
    /**
     * 根据风险事件名称查询不包含此风险事件代码的风险事件
     * @param eventName
     * @return
     */
    public List<RskEvent> selectByEventNameNotEqCode(String eventName, String eventCode) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("eventName", eventName);
    	map.put("eventCode", eventCode);
    	List<RskEvent> list =  restTemplate.postForList(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/selectByEventNameNotEqCode",
                map, RskEvent.class);
		return list;
    }
    
    /**
	 * 导出
	 */
	@Override
	public void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/riskredis/quota/rskevent/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
	}
	
	@Override
	public Page<RskEvent001Dto> selectPageData(Map<String, Object> selectParam) {
//		selectParam = RequestUtil.getParameters(getRequest());
		Object eventCode = selectParam.get("eventCode");
		if (null != eventCode) {
			List<String> eventCodeList = StringUtil.splitToList(GlobalConstant.COMMA, String.valueOf(eventCode));
			selectParam.put("eventCode", eventCodeList);
		}
		 Page<RskEvent001Dto> page = restTemplate.postForPage(
	                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/findPage",
	                selectParam, RskEvent001Dto.class);
		 
	        return page;
	}

	public boolean isExistEventCode(String eventCode) {
		return restTemplate.getForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/isExistEventCode?eventCode="+eventCode,
                boolean.class);
	}


}
