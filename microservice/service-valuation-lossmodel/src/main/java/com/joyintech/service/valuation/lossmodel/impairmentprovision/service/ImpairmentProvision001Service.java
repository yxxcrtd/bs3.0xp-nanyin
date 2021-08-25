/*
 * 文件名：ImpairmentProvision001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-24
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.lossmodel.impairmentprovision.service;

import com.github.pagehelper.ISelect;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.lossmodel.impairmentprovision.dao.ImpairmentProvision001Mapper;
import com.joyintech.service.valuation.lossmodel.impairmentprovision.dto.ImpairmentProvision001Dto;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 减值情况计提表
 * @author xutong
 * @createTime 2021-07-05
 * @see ImpairmentProvision001Service
 * @since 1.0
 */
@Service(value="serviceImpairmentProvision001Service")
public class ImpairmentProvision001Service extends CommonExportService<ImpairmentProvision001Dto>{
	
	@Autowired
	private ImpairmentProvision001Mapper impairmentProvision001Mapper;

	public Page<ImpairmentProvision001Dto> selectForPage(int page, int length, Map<String, Object> params)
			throws Exception {
		return super.queryForPage(page, length, ()-> queryDate(params));
	}


	public List<ImpairmentProvision001Dto> queryDate(Map<String, Object> params) {
		List<ImpairmentProvision001Dto> listImpairmentProvision001Dto;
		params.put("stage", DictCodeListExt3.阶段_初始买入时_P1.getValue());
		List<ImpairmentProvision001Dto> listImpairmentProvision001DtoP1 = impairmentProvision001Mapper.queryDate(params);
		params.put("stage", DictCodeListExt3.阶段_买入后风险显著提升_P2.getValue());
		List<ImpairmentProvision001Dto> listImpairmentProvision001DtoP2 = impairmentProvision001Mapper.queryDate(params);
		params.put("stage", DictCodeListExt3.阶段_买入后出现减值_P3.getValue());
		List<ImpairmentProvision001Dto> listImpairmentProvision001DtoP3 = impairmentProvision001Mapper.queryDate(params);

		Map<String, ImpairmentProvision001Dto> dataList = new HashMap<>();
		//一阶段数据转换为Map
		for(ImpairmentProvision001Dto dto : listImpairmentProvision001DtoP1){
			dataList.put(dto.getProdId() + "," + dto.getProdName() + "," + dto.getType9() + "," + dto.getDecDateFmt(), dto);
		}
		//二阶段数据插入
		for(ImpairmentProvision001Dto dto : listImpairmentProvision001DtoP2){
			String key = dto.getProdId() + "," + dto.getProdName() + "," + dto.getType9() + "," + dto.getDecDateFmt();
			ImpairmentProvision001Dto keyDto = dataList.get(key);
			if(keyDto != null){
				//当前key存在一阶段数据
				keyDto.setEclP2(dto.getEclP1());
				keyDto.setDecRatioP2(dto.getDecRatioP1());
				keyDto.setDecBalanceP2(dto.getDecBalanceP1());
				keyDto.setPerEclP2(dto.getPerEclP1());
				keyDto.setPerDecRatioP2(dto.getPerDecRatioP1());
				keyDto.setBondCountP2(dto.getBondCountP1());
				dataList.put(key, keyDto);
			}else{
				//不存在
				dto.setEclP2(dto.getEclP1());
				dto.setDecRatioP2(dto.getDecRatioP1());
				dto.setDecBalanceP2(dto.getDecBalanceP1());
				dto.setPerEclP2(dto.getPerEclP1());
				dto.setPerDecRatioP2(dto.getPerDecRatioP1());
				dto.setBondCountP2(dto.getBondCountP1());
				dto.setEclP1(BigDecimal.ZERO);
				dto.setDecRatioP1(BigDecimal.ZERO);
				dto.setDecBalanceP1(BigDecimal.ZERO);
				dto.setPerEclP1(BigDecimal.ZERO);
				dto.setPerDecRatioP1(BigDecimal.ZERO);
				dto.setBondCountP1(BigDecimal.ZERO);
				dataList.put(key, dto);
			}
		}

		//三阶段数据插入
		for(ImpairmentProvision001Dto dto : listImpairmentProvision001DtoP3){
			String key = dto.getProdId() + "," + dto.getProdName() + "," + dto.getType9() + "," + dto.getDecDateFmt();
			ImpairmentProvision001Dto keyDto = dataList.get(key);
			if(keyDto != null){
				//当前key存在一阶段及二阶段数据
				keyDto.setEclP3(dto.getEclP1());
				keyDto.setDecRatioP3(dto.getDecRatioP1());
				keyDto.setDecBalanceP3(dto.getDecBalanceP1());
				keyDto.setPerEclP3(dto.getPerEclP1());
				keyDto.setPerDecRatioP3(dto.getPerDecRatioP1());
				keyDto.setBondCountP3(dto.getBondCountP1());
				dataList.put(key, keyDto);
			}else{
				//不存在，一二阶段数据赋 0
				dto.setEclP3(dto.getEclP1());
				dto.setDecRatioP3(dto.getDecRatioP1());
				dto.setDecBalanceP3(dto.getDecBalanceP1());
				dto.setPerEclP3(dto.getPerEclP1());
				dto.setPerDecRatioP3(dto.getPerDecRatioP1());
				dto.setBondCountP3(dto.getBondCountP1());
				dto.setEclP1(BigDecimal.ZERO);
				dto.setDecRatioP1(BigDecimal.ZERO);
				dto.setDecBalanceP1(BigDecimal.ZERO);
				dto.setPerEclP1(BigDecimal.ZERO);
				dto.setPerDecRatioP1(BigDecimal.ZERO);
				dto.setBondCountP1(BigDecimal.ZERO);
				dataList.put(key, dto);
			}
		}
		listImpairmentProvision001Dto = new ArrayList<>(dataList.values());

		for(ImpairmentProvision001Dto dto : listImpairmentProvision001Dto){
			dto.setEclSum(ArithmeticUtil.doAdd(dto.getEclP1(), dto.getEclP2(), dto.getEclP3()));
			dto.setDecRatioSum(ArithmeticUtil.doAdd(dto.getDecRatioP1(), dto.getDecRatioP2(), dto.getDecRatioP3()));
			dto.setDecBalanceSum(ArithmeticUtil.doAdd(dto.getDecBalanceP1(), dto.getDecBalanceP2(), dto.getDecBalanceP3()));
			dto.setPerEclSum(ArithmeticUtil.doAdd(dto.getPerEclP1(), dto.getPerEclP2(), dto.getPerEclP3()));
			dto.setPerDecRatioSum(ArithmeticUtil.doAdd(dto.getPerDecRatioP1(), dto.getPerDecRatioP2(), dto.getPerDecRatioP3()));
			dto.setBondCountSum(ArithmeticUtil.doAdd(dto.getBondCountP1(), dto.getBondCountP2(), dto.getBondCountP3()));
		}

		return listImpairmentProvision001Dto;
	}

}
