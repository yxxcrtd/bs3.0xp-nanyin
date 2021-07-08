/*
 * 文件名：EntrustCommonPersonSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.entrust.etrplanassetn.dto.EtrPlanAssetNDto;
import com.joyintech.service.entrust.etrplanassetr.dto.EtrPlanAssetRDto;
import com.joyintech.service.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.entrust.impl.dao.BaseEtrInvestInfoMapper;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.common.base.entity.EtrFinishStatusKey;
import com.joyintech.tams.common.base.entity.EtrQuoteFinishStatus;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.common.dao.EntrustBankCommonMapper;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.etrinvestallinfo.dto.EtrInvestAllInfoDto;
import com.joyintech.service.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 投资委托人员信息下拉框查询
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年11月9日
 * @see EntrustBankCommonService
 * @since 1.0
 */
@Service
public class EntrustBankCommonService extends BaseService<ITableRow> {
	  
	@Autowired
	private EtrEntrustMapper etrEntrustMapper;
	
	@Autowired
	private BaseEtrInvestInfoMapper baseEtrInvestInfoMapper;
	
	@Autowired
	private EntrustBankCommonMapper entrustBankCommonMapper;
	
	/**
	 * 
	 * @param entrustId
	 * @return1为当前委托状态非已接收，已退回，已撤回，或者已经存在委托完成度；2为存在非 已拒绝、已撤销、已终止 的银行间报价状态；
     * 3为check通过
	 */
	public String entrustCheckCloseList(String entrustId) {
		if (StringUtil.isEmpty(entrustId)) {
			return "1";
		}
		EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
		if (etrEntrust == null) {
			return "1";
		}
		// 已接收、已退回和已撤回
		if (!(StringUtil.equals(etrEntrust.getEntrustStatus(),BussinessDictCodeList.委托状态_已接收.getValue())
				||StringUtil.equals(etrEntrust.getEntrustStatus(),BussinessDictCodeList.委托状态_已退回.getValue())
				||StringUtil.equals(etrEntrust.getEntrustStatus(),BussinessDictCodeList.委托状态_已撤回.getValue()))) {
			return "1";
		}
		EtrFinishStatusKey etrFinishStatusKey = new EtrFinishStatusKey();
		etrFinishStatusKey.setEntrustId(entrustId);
		List<EtrFinishStatus> etrFinishStatusList = baseEtrInvestInfoMapper.selectEtrFinishStatusByPrimaryKey(
				etrFinishStatusKey);
		if (!StringUtil.isEmptyList(etrFinishStatusList)) {
			return "2";
		}
		Map<String,Object> param= new HashMap<>();
		param.put("entrustId", entrustId);
		List<EtrQuoteFinishStatus> etrQuoteFinishStatusList =  entrustBankCommonMapper.selectEtrQuoteFinishStatusList(param);  
		if (!StringUtil.isEmptyList(etrQuoteFinishStatusList)) {
			return "2";
		}
		//是否存在流程中或者有效且数据状态不为 已拒绝、已撤销、已终止
		param.put("QUOTE_STUTAS_IBU_08", BussinessDictCodeList.银行间上行报价状态_已终止.getValue());
		param.put("QUOTE_STUTAS_IBU_14", BussinessDictCodeList.银行间上行报价状态_已拒绝.getValue());
		param.put("QUOTE_STUTAS_IBU_15", BussinessDictCodeList.银行间上行报价状态_已撤销.getValue());
		int i =entrustBankCommonMapper.checkDataStatus(param);
		if(i>0){
			return "2";
		}
		return "3";
	}

}
