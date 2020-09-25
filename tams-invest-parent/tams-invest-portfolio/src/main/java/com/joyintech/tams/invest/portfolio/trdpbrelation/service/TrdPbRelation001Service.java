package com.joyintech.tams.invest.portfolio.trdpbrelation.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.trdpbrelation.dto.TrdPbRelation001Dto;
/**
 * PB编号管理001Service
 * 〈功能详细描述〉
 * @author caoxue
 * @version 1.0 2019年12月24日
 * @see TrdPbRelation001Service
 * @since 1.0
 *
 */
@Service("TrdPbRelation001Service")
@Transactional(rollbackFor = Exception.class)
public class TrdPbRelation001Service extends CommonExportService<TrdPbRelation001Dto>{

	/**
	 * 分页查询
	 * 
	 * @param parameters
	 * @return
	 */
	public Page<TrdPbRelation001Dto> selectForPage(Map<String, Object> pageParam) {
	    Page<TrdPbRelation001Dto> page =  restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/TrdPbRelation001/selectForPage", pageParam,
				TrdPbRelation001Dto.class);
	    return page;
	}
	/**
	 * 删除
	 * 
	 * @param parameters
	 * @return
	 */
	public Integer delete(TrdPbRelation001Dto entity){
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/TrdPbRelation001/delete", entity,
				Integer.class);
	}
	/**
	 * 导出
	 * 
	 * @param parameters
	 * @return
	 */
	public List<TrdPbRelation001Dto>  selectForExport(Map<String, Object> pageParam){
		return restTemplate.postForList(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/TrdPbRelation001/selectForExport", pageParam,
				TrdPbRelation001Dto.class);
	}
	/**
	 * 查询PB和产品/子组合关联信息
	 * 
	 * @param parameters
	 * @return
	 */
	public TrdPbRelation001Dto getTrdPbRelation(TrdPbRelation001Dto entity){
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/TrdPbRelation001/getTrdPbRelation", entity,
				TrdPbRelation001Dto.class);
	}
}
