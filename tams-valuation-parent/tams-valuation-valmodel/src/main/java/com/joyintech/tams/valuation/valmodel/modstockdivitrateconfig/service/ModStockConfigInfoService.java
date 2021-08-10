package com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.dto.ModAseetMappingDto;
import com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.dto.ModStockDivitrateConfigInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Objects;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
@Transactional
public class ModStockConfigInfoService extends CommonExportService<ModStockDivitrateConfigInfoDto> implements BaseProcessService<ModStockDivitrateConfigInfoDto>{
	
	/**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
	public Page<ModStockDivitrateConfigInfoDto> selectForPage(Map<String, Object> pageParam) {
		Page<ModStockDivitrateConfigInfoDto> page = restTemplate.postForPage(
				ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModStockDivitrateConfigInfo/findPage", pageParam, ModStockDivitrateConfigInfoDto.class);
		return page;
	}
	
	/**
     * 根据主键查询
     * @param seqno
     * @return
     */
    public ModStockDivitrateConfigInfoDto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VAL_MODEL_SERVICE_NAME
                                          + "/ModStockDivitrateConfigInfo/findByPrimaryKey",map, ModStockDivitrateConfigInfoDto.class);
    }
	
    /**
     * 查询资产分类映射信息
     * @param output5
     * @return
     */
    public ModAseetMappingDto findModAseetMappingByOutput(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VAL_MODEL_SERVICE_NAME
                                          + "/Modelvaluation/findModAseetMappingByFiveClass",map, ModAseetMappingDto.class);
    }
	@Override
	public void doComplete(ModStockDivitrateConfigInfoDto entity, OperationType operationType) {
		String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(operationType)) {
            action = "delete";
        } else if (OperationType.NEW.equals(operationType)) {
            action = "insert";
        } else {
            action = "update";
        }
        restTemplate.postForObject(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModStockDivitrateConfigInfo/" + action, entity,
                Integer.class);
		
	}
	
	
	@Override
	public void doInsert(ModStockDivitrateConfigInfoDto entity, OperationType type) {

	}

	@Override
	public void doUpdate(ModStockDivitrateConfigInfoDto entity) {

	}

	@Override
	public void doDelete(String workflowId) {

	}
	
	/**
	 * 导出
	 * @param paraMap
	 * @return
	 */
	public List<ModStockDivitrateConfigInfoDto> selectForExport(Map<String, Object> paraMap) {
		return restTemplate.postForList(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModStockDivitrateConfigInfo/selectForExport", paraMap,
                ModStockDivitrateConfigInfoDto.class);
	}
	/**
	 * 查询资产代码
	 * @param param
	 * @return
	 */
	public Page<SelectOptionDto> getAssetCodeList(Map<String, Object> param) {
		 param.put("IS_RIGHT_N",DictCodeListExt1.是否_否.getValue());
		 param.put("IS_SUS_Y",DictCodeListExt1.是否_是.getValue());
		 param.put("FIN_TYPE_F01",DictCodeListExt1.金融产品类型_投管用_债券.getValue());
		 param.put("CUSTOM_ASSET_TYPE_FOUR_023",DictCodeListExt3.资产四级分类_优先股.getValue());
		 return restTemplate.postForPage(ServiceApplication.CUSTOMER_INTERF_NAME
                 + "/ModStockDivitrateConfigInfo/getAssetCodeList",param,SelectOptionDto.class);
	}
	
	/**
	 * 查找最新的权重配置维护信息
	 * @return
	 */
	public int checkByFinprodId(Map<String, Object> map) {
		int count=0;
		count = restTemplate.postForObject(ServiceApplication.VAL_MODEL_SERVICE_NAME
                + "/ModStockDivitrateConfigInfo/checkByFinprodId",map, Integer.class);
		return count;
	}
	/**
	 * 查询资产代码不包含已维护的资产
	 * @param param
	 * @return
	 */
	public Page<SelectOptionDto> getAssetCodeListExceptExsits(Map<String, Object> param) {
		Page<SelectOptionDto> returnList  = this.getAssetCodeList(param);
		List<SelectOptionDto> filterRecods = returnList.getRoot();
		Map<String, Object> map = new HashMap<String, Object>();
		List<ModStockDivitrateConfigInfoDto> exceptList = this.selectForExport(map);
		
		filterRecods = filterRecods.stream().filter( s -> 
						exceptList.stream().noneMatch(f -> Objects.equal(f.getFinprodId(), s.getId()))).collect(Collectors.toList());
		returnList.setRoot(filterRecods);
		return returnList;
	}
	
}
