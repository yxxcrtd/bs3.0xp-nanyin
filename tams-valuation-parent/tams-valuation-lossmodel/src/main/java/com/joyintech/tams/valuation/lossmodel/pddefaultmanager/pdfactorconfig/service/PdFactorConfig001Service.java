package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.PdFactorConfigDto;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.PdFactorWeightConfigDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
@Transactional
public class PdFactorConfig001Service extends CommonExportService<PdFactorConfigDto> implements BaseProcessService<PdFactorConfigDto>{
	
	/**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
	public Page<PdFactorConfigDto> selectForPage(Map<String, Object> pageParam) {
		Page<PdFactorConfigDto> page = restTemplate.postForPage(
				ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorConfigInfo/findPage", pageParam, PdFactorConfigDto.class);
		return page;
	}
	
	/**
     * 根据主键查询
     * @param
     * @return
     */
    public PdFactorConfigDto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                                          + "/ModPdFactorConfigInfo/findByPrimaryKey",map, PdFactorConfigDto.class);
    }
	
	@Override
	public void doComplete(PdFactorConfigDto entity, OperationType operationType) {
		String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(operationType)) {
            action = "delete";
        } else if (OperationType.NEW.equals(operationType)) {
            action = "insert";
        } else {
            action = "update";
        }
        restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorConfigInfo/" + action, entity,
                Integer.class);
		
	}
	
	
	@Override
	public void doInsert(PdFactorConfigDto entity, OperationType type) {

	}

	@Override
	public void doUpdate(PdFactorConfigDto entity) {

	}

	@Override
	public void doDelete(String workflowId) {

	}
	
	public List<PdFactorConfigDto> selectByYearAndQuarter(Map<String, Object> map) {
		return restTemplate.postForList(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorConfigInfo/selectByYearAndQuarter", map,
                PdFactorConfigDto.class);
	}

	public List<PdFactorConfigDto> selectForExport(Map<String, Object> paraMap) {
		return restTemplate.postForList(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorConfigInfo/selectForExport", paraMap,
                PdFactorConfigDto.class);
	}
	/**
	 * 查找最新的权重配置维护信息
	 * @return
	 */
	public PdFactorWeightConfigDto findLastWeightInfo(Map<String, Object> map) {
		return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                + "/ModPdFactorConfigInfo/findLastWeightInfo",map, PdFactorWeightConfigDto.class);
	}
	
	/**
	 * 查找最新的权重配置维护信息
	 * @return
	 */
	public int checkBySeqNo(Map<String, Object> map) {
		int count=0;
		count = restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                + "/ModPdFactorConfigInfo/checkBySeqNo",map, Integer.class);
		return count;
	}
	
	
	
}
