package com.joyintech.tams.invest.product.nolimit.maxissuablesize.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.nolimit.maxissuablesize.dto.MaxIssuableSize001Dto;
/**
 * 产品端最大可发行规模查询service
 * 
 * @author qufangxu
 * @version 1.0 2020年1月1日
 * @see MaxIssuableSize001Service
 * @since 1.0
 */
@Service
public class MaxIssuableSize001Service extends CommonExportService<MaxIssuableSize001Dto>{
	
	/**
	 * 最大可发行规模查询 分页查询
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public Page<MaxIssuableSize001Dto> selectForPage(Map<String, Object> pageParam) {
		
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/nolimit/MaxIssuableSize/selectForPage", 
        		pageParam, MaxIssuableSize001Dto.class);
	}
	
	/**
	 * 产品规划系列联想框
	 * <功能详细描述>
	 * @param 
	 * @return
	 * @see
	 * @since 1.0
	 */
    public List<SelectOptionDto> queryPrdPlanSerisIdInfo(Map<String, Object> pageParam) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/nolimit/MaxIssuableSize/queryPrdPlanSerisIdInfo", pageParam,
            SelectOptionDto.class);
    }
    
    /**
     * 最大可发行规模查询 导出
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/nolimit/maxissuablesize/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MaxIssuableSize001Dto> selectPageData(Map<String, Object> selectParam) {
         return selectForPage(selectParam);
    }

}
