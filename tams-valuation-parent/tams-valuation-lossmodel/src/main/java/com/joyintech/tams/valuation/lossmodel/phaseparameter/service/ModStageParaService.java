package com.joyintech.tams.valuation.lossmodel.phaseparameter.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import com.joyintech.tams.valuation.lossmodel.phaseparameter.dto.ModStageParaDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
@Transactional
public class ModStageParaService extends CommonExportService<ModStageParaDto> implements BaseProcessService<ModStageParaDto>{

	@Override
	public void doComplete(ModStageParaDto entity, OperationType operationType) {
		String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(operationType)) {
            action = "delete";
        } else if (OperationType.NEW.equals(operationType)) {
            action = "insert";
        } else {
            action = "update";
        }
        restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModStageParaConfigInfo/" + action, entity,
                Integer.class);
		
	}
	/**
	 * 页面查询
	 * @param
	 * @return
	 */
	public Page<ModStageParaDto> selectForPage(Map<String, Object> pageParam) {
		Page<ModStageParaDto> page = restTemplate.postForPage(
				ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModStageParaConfigInfo/findPage", pageParam, ModStageParaDto.class);
		return page;
	}
	
	/**
     * 根据主键查询
     * @param
     * @return
     */
    public ModStageParaDto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                                          + "/ModStageParaConfigInfo/findByPrimaryKey",map, ModStageParaDto.class);
    }
	
    /**
     * 根据减值阶段和要素值查询
     * @param
     * @return
     */
    public ModStageParaDto findByStageAndElements(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                                          + "/ModStageParaConfigInfo/findByStageAndElements",map, ModStageParaDto.class);
    }
	/**
	 * 导出数据查询
	 * @param paraMap
	 * @return
	 */
	public List<ModStageParaDto> selectForExport(Map<String, Object> paraMap) {
		return restTemplate.postForList(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModStageParaConfigInfo/selectForExport", paraMap,
                ModStageParaDto.class);
	}

	/**
	 * 批量启用/停用
	 * @return
	 */
	public Integer setStageParaSwitch(Map<String,Object> params){
		return  restTemplate.postForObject(
				ServiceApplication.LOSS_MODEL_SERVICE_NAME+ "/ModStageParaConfigInfo/setStageParaSwitch",params, Integer.class);
	}
	
	
	
}
