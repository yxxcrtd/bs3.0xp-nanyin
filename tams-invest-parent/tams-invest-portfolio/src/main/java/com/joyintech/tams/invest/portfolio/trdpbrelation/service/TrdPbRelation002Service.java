package com.joyintech.tams.invest.portfolio.trdpbrelation.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.trdpbrelation.dto.TrdPbRelation001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * PB编号管理002Service
 * 〈功能详细描述〉
 * @author caoxue
 * @version 1.0 2019年12月24日
 * @see TrdPbRelation002Service
 * @since 1.0
 *
 */
@Service
public class TrdPbRelation002Service extends CommonExportService<TrdPbRelation001Dto> implements BaseProcessService<TrdPbRelation001Dto> {

    @Override
    public void doComplete(TrdPbRelation001Dto entity, OperationType type) {
        
    }

    /**
     * 查询产品/子组合代码
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryPrdPortfolioId(Map<String, Object> map) {
        Page<SelectOptionDto> list = restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/TrdPbRelation001/queryPrdPortfolioId",
            map, SelectOptionDto.class);
        return list;
    }
    
    /**
     * 查询产品/子组合名称
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public SelectOptionDto queryPrdPortfolioName(Map<String, Object> map) {
    	Page<SelectOptionDto> list = restTemplate.postForPage(
    			ServiceApplication.PORTFOLIO_SERVICE_NAME + "/TrdPbRelation001/queryPrdPortfolioId",
    			map, SelectOptionDto.class);
    	String prdPortfolioId = (String)map.get("prdPortfolioId");
    	for(SelectOptionDto selectOptionDto:list.getRoot()){
    		if(prdPortfolioId.equals(selectOptionDto.getId())){
    			return selectOptionDto;
    		}
    	}
    	return new SelectOptionDto();
    }
	/**
	 * 新增
	 * 
	 * @param parameters
	 * @return
	 */
	public Integer insert(TrdPbRelation001Dto entity){
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/TrdPbRelation001/insert", entity,
				Integer.class);
	}
}
