package com.joyintech.tams.invest.product.productplanning.tacticconfig.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.TacticConfigOptDetail;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.dto.TacticConfig002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@Service
public class TacticConfig002Service extends BaseService<TacticConfig002Dto>
		implements BaseProcessService<TacticConfig002Dto> {

	/**
	 * 资产大类下拉框查询
	 * 
	 * @param param
	 * @return
	 */
	public Page<SelectOptionDto> selectAllAssetType(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/TacticConfig/selectAllAssetType",
				param, SelectOptionDto.class);
	}

	/**
	 * 战术配置明细列表
	 * 
	 * @param tacticConfigCode
	 * @return
	 */
	public List<TacticConfigOptDetail> getTacticConfigOptDetailList(String tacticConfigCode) {
		return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
								+ "/TacticConfig/getTacticConfigOptDetailList?tacticConfigCode=" + tacticConfigCode,
						TacticConfigOptDetail.class);
	}

	@Override
	public void doComplete(TacticConfig002Dto entity, OperationType type) {
		if(!StringUtil.isEmptyList(entity.getTacticConfigOptDetails())) {
			//战术配置明细百分率除100入库
			for(TacticConfigOptDetail tacticConfigOptDetail : entity.getTacticConfigOptDetails()) {
				tacticConfigOptDetail.setTheoConfigRate(ArithmeticUtil.divide(tacticConfigOptDetail.getTheoConfigRate(),new BigDecimal(100),8));
				tacticConfigOptDetail.setSamleAverRate(ArithmeticUtil.divide(tacticConfigOptDetail.getSamleAverRate(),new BigDecimal(100),8));
				tacticConfigOptDetail.setVix(ArithmeticUtil.divide(tacticConfigOptDetail.getVix(),new BigDecimal(100),8));
				tacticConfigOptDetail.setCorRate(ArithmeticUtil.divide(tacticConfigOptDetail.getCorRate(),new BigDecimal(100),8));
				tacticConfigOptDetail.setCorRatio(ArithmeticUtil.divide(tacticConfigOptDetail.getCorRatio(),new BigDecimal(100),8));
			}
		}
		if (type == OperationType.NEW) {
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/TacticConfig/insertTacticConfig", entity,
					Integer.class);
		}else if(type == OperationType.MODIFY) {
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/TacticConfig/updateTacticConfig", entity,
					Integer.class);
		}

	}
	
	/**
	 * 查询最新一条战术配置
	 * @return
	 */
	public TacticConfig002Dto selectFirstTacticConfigOpt() {
		return restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/TacticConfig/selectFirstTacticConfigOpt",
				TacticConfig002Dto.class);
	}
	
	/**
	 * 根据战术配置ID查询战术配置
	 * @param tacticConfigCode
	 * @return
	 */
	public TacticConfig002Dto selectByTacticConfigCode(String tacticConfigCode) {
		return restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/TacticConfig/selectByTacticConfigCode?tacticConfigCode=" + tacticConfigCode,
				TacticConfig002Dto.class);
	}

}
