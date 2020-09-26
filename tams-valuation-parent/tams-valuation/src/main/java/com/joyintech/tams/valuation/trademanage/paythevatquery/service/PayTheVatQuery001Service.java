/*
 * 文件名：TaxBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.paythevatquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dao.PayTheVatQuery001Mapper;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@Service
public class PayTheVatQuery001Service extends CommonExportService<PayTheVatQuery001Dto> {

	@Autowired
	private PayTheVatQuery001Mapper payTheVatQuery001Mapper;

	/**
	 * 根据条件分页查询
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Page<PayTheVatQuery001Dto> findByPage(Map<String, Object> params) throws Exception {

		// 产品代码多选
		if (StringUtil.isNotEmpty(params.get("booksetId"))) {
			String booksetId = (String) params.get("booksetId");
			String[] booksetIdArr = null;
			booksetIdArr = booksetId.split(",");
			params.put("booksetIdArr", booksetIdArr);
		}
		// 纳税类型
		if (StringUtil.isNotEmpty(params.get("taxidenType"))) {
			params.put("taxidenTypes", params.get("taxidenType").toString().split(","));
		}
		// 纳税日期
		if (StringUtil.isNotEmpty(params.get("vatDate"))) {
			params.put("vatDate", params.get("vatDate").toString().split(","));
		}
		params.put("busiType", DictCodeList.业务类型_增值税交易.getValue());
		params.put("feeType1", DictCodeList.增值税类型_利息收入.getValue());
		params.put("feeType2", DictCodeList.增值税类型_红利收入.getValue());
		params.put("feeType3", DictCodeList.增值税类型_价差收入.getValue());
		params.put("feeType4", DictCodeList.附加税类型_城建税.getValue());
		params.put("feeType5", DictCodeList.附加税类型_教育税.getValue());
		params.put("feeType6", DictCodeList.附加税类型_地方教育税.getValue());
		Page<PayTheVatQuery001Dto> page = super.queryForPage(() -> payTheVatQuery001Mapper.selectList(params));
		return page;
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<PayTheVatQuery001Dto> findByList(Map<String, Object> params) throws Exception {

		// 产品编号多选
		if (StringUtil.isNotEmpty(params.get("booksetId"))) {
			String booksetId = (String) params.get("booksetId");
			String[] booksetIdArr = null;
			booksetIdArr = booksetId.split(",");
			params.put("booksetIdArr", booksetIdArr);
		}
		// 资产分类
		if (StringUtil.isNotEmpty(params.get("taxPType"))) {
			params.put("taxidenTypes", params.get("taxidenType").toString().split(","));
		}
		// 收入类型
		if (StringUtil.isNotEmpty(params.get("vatDate"))) {
			params.put("vatDates", params.get("vatDate").toString().split(","));
		}
		params.put("busiType", DictCodeList.业务类型_增值税交易.getValue());
        params.put("feeType1", DictCodeList.增值税类型_利息收入.getValue());
        params.put("feeType2", DictCodeList.增值税类型_红利收入.getValue());
        params.put("feeType3", DictCodeList.增值税类型_价差收入.getValue());
        params.put("feeType4", DictCodeList.附加税类型_城建税.getValue());
        params.put("feeType5", DictCodeList.附加税类型_教育税.getValue());
        params.put("feeType6", DictCodeList.附加税类型_地方教育税.getValue());
		List<PayTheVatQuery001Dto> page = payTheVatQuery001Mapper.selectList(params);
		return page;
	}

	/**
	 * 下拉框分页获取产品
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getBooksetId(String param) {
	    String booksetType = DictCodeList.账套类别_理财账套.getValue();
		return payTheVatQuery001Mapper.getBooksetId(booksetType, param);
	}
	
	/**
	 * 
	 * 根据原交易编号和交易状态查询对应交易信息
     * @param tradeId 交易编号
     * @param tradeStatus 交易状态
	 * @return List<PayTheVatQuery001Dto>
	 * @see
	 * @since 1.0
	 */
	public List<PayTheVatQuery001Dto> findByPTradeId(String pTradeId, String tradeStatus){
	    return payTheVatQuery001Mapper.findByPTradeId(pTradeId, tradeStatus);
	}

}
