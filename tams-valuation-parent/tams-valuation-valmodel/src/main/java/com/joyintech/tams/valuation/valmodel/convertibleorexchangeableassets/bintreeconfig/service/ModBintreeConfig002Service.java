package com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.AssetInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 二叉树模型参数配置
 * @author yuluan
 * @version 1.0 2021年07月01日
 * @see ModBintreeConfig002Service
 * @since 1.0
 */
@Service
public class ModBintreeConfig002Service extends CommonExportService<ModBintreeConfigDto>
												implements BaseProcessService<ModBintreeConfigDto> {
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ModBintreeConfigDto> findPage(Map<String,Object> map){
        
        return restTemplate.postForPage(
            ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/findPage",
            map, ModBintreeConfigDto.class);
    }
    public String check(@RequestBody Map<String, Object> param) {
        return restTemplate.postForObject(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/check", param,
                String.class);
    }
    public ModBintreeConfigDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/queryDtoBySeqno",
                map, ModBintreeConfigDto.class);
    }
	@Override
	public void doComplete(ModBintreeConfigDto entity, OperationType type) {
		String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
            action = "insert";
        } else {
            action = "update";
            entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
            entity.setUpdateTime(new Date());
        }
        restTemplate.postForObject(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/" + action, entity,
                Integer.class);

	}
   public String checkIfUsed(@RequestParam Map<String, Object> param) {
	   String result = restTemplate.postForObject(
               ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/checkIfUsed",
               param, String.class);
       return result;

   }
   public List<SelectOptionDto> getcompStockidForBintree(Map<String, Object> parameters) {
   	
		List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
		
		AssetInfoDto qAssetInfoDto=new AssetInfoDto();
		if(parameters.get("search")!=null&&!"".equals(parameters.get("search"))){
			qAssetInfoDto.setSearch(parameters.get("search").toString());
		}
		if(parameters.get("initValue")!=null&&!"".equals(parameters.get("initValue"))){
			qAssetInfoDto.setInitValue(parameters.get("initValue").toString());
		}
		qAssetInfoDto.setType8List(Arrays.asList(DictCodeListExt1.金融产品类型_投管用_股票.getValue()));
		List<AssetInfoDto> aseetS = restTemplate.postForList(
				ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/ModelCallOnInvest/getAssetNoClassify", qAssetInfoDto,
				AssetInfoDto.class);
		resultList = aseetS.stream().map(operationStation -> {
			SelectOptionDto stationDictVO = new SelectOptionDto();
			stationDictVO.setId(operationStation.getFinprodId());
			stationDictVO.setText(operationStation.getFinprodAbbr());
			return stationDictVO;
		}).collect(Collectors.toList());

		return resultList;
   }
   public Page<SelectOptionDto> getMarketId(Map<String, Object> parameters) {
	   Page<SelectOptionDto> pageList = restTemplate.postForPage(
				ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/getMarketId", parameters,
				SelectOptionDto.class);
		return pageList;

  }
    
}
