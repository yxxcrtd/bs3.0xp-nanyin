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
import com.joyintech.tams.common.base.entity.SzdSjsks;
import com.joyintech.tams.common.base.entity.SzdZqy;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealOperatorDto;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealWriteDto;

/**
 * 数据清算step2 processor Service - 处理深中登SJSKS
 * 
 * @author 陈卓
 * @date 2020/11/27
 * @see StlDataDealSzdSjsksProcessService
 * @since 1.0
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StlDataDealSzdSjsksProcessService {

	/**
	 * 生成上中登ZQY数据清算批处理step2 processor的结果Dto
	 * 
	 * @param stlDownDataDealOperatorDto StlDownDataDealOperatorDto
	 */
	public StlDownDataDealWriteDto generatorStlDownDataDealSzdSjsksWriteDto(StlDownDataDealOperatorDto stlDownDataDealOperatorDto) {
		StlDownDataDealWriteDto stlDownDataDealWriteDto = new StlDownDataDealWriteDto();
		if (stlDownDataDealOperatorDto != null && stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto() != null && 
				stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdSjsksList() != null && 
				stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdSjsksList().size() > 0) {
			List<FinProductQuto> finProductQutoList = new ArrayList<FinProductQuto>();
			for (SzdSjsks szdSjsks : stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdSjsksList()) {
				FinProductQuto finProductQuto = new FinProductQuto();
				finProductQuto.setSeqNo(IDGenerator.generate());
				finProductQuto.setOthType(DictCodeListExt3.金融产品其他代码类型_新股申购代码.getValue());
				finProductQuto.setCdate(DateUtil.parse(szdSjsks.getKsfsrq()));
				finProductQuto.setQuto(new BigDecimal(szdSjsks.getKsksge()));
				finProductQuto.setBondId(szdSjsks.getKsgddm());
				finProductQuto.setTradeMarket(DictCodeList.交易流通场所_深圳证券交易所.getValue());
				finProductQutoList.add(finProductQuto);
			}
			stlDownDataDealWriteDto.setFinProductQutoList(finProductQutoList);
		}
		
		return stlDownDataDealWriteDto;
	}
}
