package com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.tams.common.base.dao.RiskWeightMapper;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.dto.CreditRightsManagementDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * 风险权数管理
 * @author huwenbin
 *
 */
@Service
public class CreditRightsManagementService extends CommonExportService<CreditRightsManagementDto> implements BaseProcessService<CreditRightsManagementDto>{
	
	/**
	 * 风险权数
	 */
	@Autowired
	private RiskWeightMapper riskWeightMapper;
	
	/**
	 * 
	 * 页面查询
	 * <功能详细描述>
	 * @param pageParam Map<String, Object>
	 * @return Page<CreditRightsManagementDto>
	 * @see
	 * @since 1.0
	 */
	public Page<CreditRightsManagementDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(				
            ServiceApplication.RISK_SERVICE_NAME + "/CreditRightsManagement/findForPage",
            pageParam, CreditRightsManagementDto.class);
    }
	
	@Override
	public void doComplete(CreditRightsManagementDto arg0, OperationType arg1) {
		arg0.setOperateType(arg1.toString());
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CreditRightsManagement/doComplete",
            arg0, Integer.class);
		
	}
	
	/**
	 * 
	 * 双击查看详情
	 * <功能详细描述>
	 * @param pageParam Map<String, Object>
	 * @return CreditRightsManagementDto
	 * @see
	 * @since 1.0
	 */
	public CreditRightsManagementDto selectDetail(String seqno){
		return restTemplate.getForObject(
	            ServiceApplication.RISK_SERVICE_NAME + "/CreditRightsManagement/selectDetail?seqno="+seqno,
	            CreditRightsManagementDto.class);
	}
	
	/**
     * 
     * 页面导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/risk/creditmanagement/creditrightsmanagement/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CreditRightsManagementDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 
     * 同一个资产二级类型、同一生效日只能有一条数据
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<CreditRightsManagementDto>
     * @see
     * @since 1.0
     */
    public List<CreditRightsManagementDto> checkalreadyhave(Map<String, Object> pageParam){
    	return restTemplate.postForList(				
                ServiceApplication.RISK_SERVICE_NAME + "/CreditRightsManagement/checkalreadyhave",
                pageParam, CreditRightsManagementDto.class);
    }
    
}
