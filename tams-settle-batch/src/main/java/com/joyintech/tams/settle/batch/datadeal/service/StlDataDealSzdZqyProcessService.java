package com.joyintech.tams.settle.batch.datadeal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.common.base.entity.FinProductQuto;
import com.joyintech.tams.common.base.entity.SzdZqy;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealOperatorDto;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealWriteDto;

/**
 * 数据清算step2 processor Service - 处理上中登ZQY
 * 
 * @author 陈卓
 * @date 2020/11/27
 * @see StlDataDealSzdZqyProcessService
 * @since 1.0
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StlDataDealSzdZqyProcessService {

	/**
	 * 生成上中登ZQY数据清算批处理step2 processor的结果Dto
	 * 
	 * @param stlDownDataDealOperatorDto StlDownDataDealOperatorDto
	 */
	public StlDownDataDealWriteDto generatorStlDownDataDealSzdZqyWriteDto(StlDownDataDealOperatorDto stlDownDataDealOperatorDto) {
		StlDownDataDealWriteDto stlDownDataDealWriteDto = new StlDownDataDealWriteDto();
		if (stlDownDataDealOperatorDto != null && stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto() != null && 
				stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdZqyList() != null && 
				stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdZqyList().size() > 0) {
			List<FinProductQuto> finProductQutoList = new ArrayList<FinProductQuto>();
			for (SzdZqy szdZqy : stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdZqyList()) {
				FinProductQuto finProductQuto = new FinProductQuto();
				finProductQuto.setSeqNo(IDGenerator.generate());
				finProductQuto.setOthType(DictCodeListExt3.金融产品其他代码类型_新股申购代码.getValue());
				finProductQuto.setCdate(DateUtil.parse(szdZqy.getDjrq()));
				finProductQuto.setQuto(new BigDecimal(szdZqy.getZbqy()));
				finProductQuto.setIpoQuto(new BigDecimal(szdZqy.getKcqy()));
				finProductQuto.setBondId(szdZqy.getGddm());
				finProductQuto.setTradeMarket(DictCodeList.交易流通场所_上海证券交易所.getValue());
				finProductQutoList.add(finProductQuto);
			}
			stlDownDataDealWriteDto.setFinProductQutoList(finProductQutoList);
		}
		
		return stlDownDataDealWriteDto;
	}
}
