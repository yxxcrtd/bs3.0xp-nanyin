package com.joyintech.tams.invest.riskredis.quota.tradeapproval.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.tradeapproval.dto.RepTradeApprovalDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @program: tams-invest-parent
 * @description: RepTradeApprovalService
 * @author: liqianqian
 * @create: 2019-09-18 14:30
 **/
@Service
public class RepTradeApprovalService extends CommonExportService<RepTradeApprovalDto> implements BaseProcessService<RepTradeApprovalDto>{
    /**
     * 查询界面询事件
     *
     * @param map 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<RepTradeApprovalDto> selectForPage(Map<String, Object> map){
    	  
        Page<RepTradeApprovalDto> page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepTradeApproval/findPage",
                map, RepTradeApprovalDto.class);
        return page;
    }
    
    /**
     * 提交界面
     * @param dto
     * @return
     */
	public int insertRule(RepTradeApprovalDto dto) {
		return restTemplate.postForObject(
				ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepTradeApproval/insertRule", dto, int.class);
	}
    
	public Page<RepTradeApprovalDto> selectForPageDel(Map<String, Object> pageParam) {
		Page<RepTradeApprovalDto> page = new Page<>();
		page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepTradeApproval/findPageDel",
                pageParam, RepTradeApprovalDto.class);
	return page;

	}

    
    /**
     * 修改或查看界面
     *
     * @param paraCode 参数代码
     * @return 结果
     * @see
     * @since 1.0
     */
    public RepTradeApprovalDto selectInfo(String eventId) {
        return restTemplate.getForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepTradeApproval/selectInfo?eventId="+
                		eventId, RepTradeApprovalDto.class);
    }
    
    public void doComplete(RepTradeApprovalDto dto, OperationType type) {
    	  // TODO Auto-generated method stub
    	  String action = StringUtil.EMPTY;
    	        if (OperationType.NEW.equals(type)) {
    	            action = "insert";
    	        }
    	        restTemplate.postForObject(
    	            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepTradeApproval/" + action, dto,
    	            Integer.class);
    	 }
}
