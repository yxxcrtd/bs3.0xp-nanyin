package com.joyintech.tams.disclosure.information.manage.prodfilemanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto.ReportProdFileManage001Dto;

/**
 * 产品文件管理 Service层
 * @author hpp
 *
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportProdFileManage001Service extends CommonExportService<ReportProdFileManage001Dto>{

	/**
	 * 分页查询条件-产品代码下拉框
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public List<SelectOptionDto> getPrdInfoSelectPF(Map<String, Object> paraMap) throws Exception {
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportProdFileManage001/getPrdInfoSelectPF", 
				paraMap, SelectOptionDto.class);
	}
	
	/**
	 * 分页查询条件-产品系列下拉框
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public List<SelectOptionDto> getProdSeriesSelectPF(Map<String, Object> paraMap) throws Exception {
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportProdFileManage001/getProdSeriesSelectPF", 
				paraMap, SelectOptionDto.class);
	}
	
	/**
	 * 分页查询事件
	 * @param param
	 * @return
	 */
	public Page<ReportProdFileManage001Dto> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportProdFileManage001/searchPage", 
				param, ReportProdFileManage001Dto.class);
	}
	
	/**
	 * 根据主键删除数据
	 * @param dto
	 */
	public void doDelete(ReportProdFileManage001Dto dto){
		restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportProdFileManage001/doDelete", 
				dto, Integer.class);
	}
	
}
