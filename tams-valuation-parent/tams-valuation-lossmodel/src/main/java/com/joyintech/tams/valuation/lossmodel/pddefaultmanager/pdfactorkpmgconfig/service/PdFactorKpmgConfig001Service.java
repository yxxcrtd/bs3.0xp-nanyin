package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto.PdFactorKpmgConfigDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
@Transactional
public class PdFactorKpmgConfig001Service extends CommonExportService<PdFactorKpmgConfigDto> implements BaseProcessService<PdFactorKpmgConfigDto>{
	
	/**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
	public Page<PdFactorKpmgConfigDto> selectForPage(Map<String, Object> pageParam) {
		Page<PdFactorKpmgConfigDto> page = restTemplate.postForPage(
				ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorKpmgConfigInfo/findPage", pageParam, PdFactorKpmgConfigDto.class);
		return page;
	}
	
	/**
     * 根据主键查询
     * @param
     * @return
     */
    public PdFactorKpmgConfigDto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                                          + "/ModPdFactorKpmgConfigInfo/findByPrimaryKey",map, PdFactorKpmgConfigDto.class);
    }
	
	@Override
	public void doComplete(PdFactorKpmgConfigDto entity, OperationType operationType) {
		String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(operationType)) {
            action = "delete";
        } else if (OperationType.NEW.equals(operationType)) {
            action = "insert";
        } else {
            action = "update";
        }
        restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorKpmgConfigInfo/" + action, entity,
                Integer.class);
		
	}
	
	
	@Override
	public void doInsert(PdFactorKpmgConfigDto entity, OperationType type) {

	}

	@Override
	public void doUpdate(PdFactorKpmgConfigDto entity) {

	}

	@Override
	public void doDelete(String workflowId) {

	}
	
	public List<PdFactorKpmgConfigDto> selectByYear(Map<String, Object> map) {
		return restTemplate.postForList(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorKpmgConfigInfo/selectByYear", map,
                PdFactorKpmgConfigDto.class);
	}

	public List<PdFactorKpmgConfigDto> selectForExport(Map<String, Object> paraMap) {
		return restTemplate.postForList(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorKpmgConfigInfo/selectForExport", paraMap,
                PdFactorKpmgConfigDto.class);
	}

	/**
	 * 查找最新的权重配置维护信息
	 * @return
	 */
	public int checkBySeqNo(Map<String, Object> map) {
		int count=0;
		count = restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                + "/ModPdFactorKpmgConfigInfo/checkBySeqNo",map, Integer.class);
		return count;
	}
	
	
	
}
