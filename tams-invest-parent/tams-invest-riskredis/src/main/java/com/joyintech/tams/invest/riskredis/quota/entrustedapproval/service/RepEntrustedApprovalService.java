package com.joyintech.tams.invest.riskredis.quota.entrustedapproval.service;

import java.util.Map;

import com.joyintech.tams.invest.riskredis.quota.entrustedapproval.dto.RepEntrustedApprovalDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 委托审批
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RepEntrustedApprovalService
 * @since 1.0  
 */
@Service
public class RepEntrustedApprovalService extends CommonExportService<RepEntrustedApprovalDto>
	implements BaseProcessService<RepEntrustedApprovalDto>{
    /**
     * 查询界面询事件
     *
     * @param map 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<RepEntrustedApprovalDto> selectForPage(Map<String, Object> map){
    	
        Page<RepEntrustedApprovalDto> page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepEntrustedApproval/findPage",
                map, RepEntrustedApprovalDto.class);
        return page;
    }
    
    /**
     * 提交界面
     * @param dto
     * @return
     */
    public int insertRule(RepEntrustedApprovalDto dto) {
		return restTemplate.postForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME +
                "/RepEntrustedApproval/insertRule",
                dto,int.class);
	}
    
    public Page<RepEntrustedApprovalDto> selectForPageDel(Map<String, Object> pageParam){
    	Page<RepEntrustedApprovalDto> page = new Page<>();
			page = restTemplate.postForPage(
	                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepEntrustedApproval/findPageDel",
	                pageParam, RepEntrustedApprovalDto.class);
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
    public RepEntrustedApprovalDto selectInfo(String eventId) {
        return restTemplate.getForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepEntrustedApproval/selectInfo?eventId="+
                		eventId, RepEntrustedApprovalDto.class);
    }

	public void doComplete(RepEntrustedApprovalDto dto, OperationType type) {
		// TODO Auto-generated method stub
		String action = StringUtil.EMPTY;
        if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepEntrustedApproval/" + action, dto,
            Integer.class);
	}
	
}
