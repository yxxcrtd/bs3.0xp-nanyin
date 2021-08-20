package com.joyintech.tams.common.basicdata.commarketmanagement.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.basicdata.commarketmanagement.dto.ComMarketManagementDto;
import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComMarketManagement001Service extends CommonExportService<ComMarketManagementDto>
implements BaseProcessService<ComMarketManagementDto>{
	 /**
     * 分页查询
     * 
     * @param pageParam 传入的查询条件
     * @return Page<MstInterestRateQuotationInfoDto> page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
	  public Page<ComMarketManagementDto> selectForPage(Map<String, Object> pageParam){
/*	        pageParam.put("customerType", DictCodeList.客户类型_法人.getValue());
*/	        return restTemplate.postForPage(
	            ServiceApplication.CommonBasicDataService + "/ComMarketManagement/selectForPage",
	            pageParam, ComMarketManagementDto.class);
	    }

	@Override
	public void doComplete(ComMarketManagementDto arg0, OperationType arg1) {
		 
		
	}

	 /**
     * 
     * 客户名称联想文本框service层实现
     * <功能详细描述>
     * @param map 联想文本框查询信息
     * @return 查询结果
     * @see
     * @since 1.0
     */

	public List<SuggestDto> selectFinprodName(Map<String, Object> param) {
		 return restTemplate.postForList(
		            ServiceApplication.CommonBasicDataService + "/ComMarketManagement/selectFinprodName",
		            param, SuggestDto.class);
	}

	 

	/*public Page<SelectOptionDto> getGradeResult(Map<String, Object> param) {
	        return restTemplate.postForPage(
	        		 ServiceApplication.BASICDATA_SERVICE_NAME + "/MstMarketManagement/getGradeResult",
	                 param,SelectOptionDto.class);
		}
	 */
	/**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/common/basicdata/commarketmanagement/conf/export_conf.xml")
        .dataSource(DataSourceType.MASERT);
    }
    
    @Override
    public Page<ComMarketManagementDto> selectPageData(Map<String, Object> selectParam) {
    	return selectForPage(selectParam);
    }
}
