/*
 * 文件名：RevDealBookReaderReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prodchannelfeecal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.prodchannelfeecal.dao.ProdChannelFeeCalBookMapper;
import com.joyintech.tams.valuation.batch.prodchannelfeecal.dto.ProdChannelFeeCalBookReaderDto;
import com.joyintech.tams.valuation.batch.prodchannelfeecal.dto.ProdChannelFeeCalKey;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 理财产品通道费补提处理 - reader相关业务处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see ProdChannelFeeCalBookReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProdChannelFeeCalBookReaderService {

	/**
	 * reader方法的 Service
	 */
	@Autowired
	private ProdChannelFeeCalBookMapper prodChannelFeeCalBookMapper;

	/**
	 * 记账公共类
	 */
	@Autowired
	private BookCommonLogic bookCommonLogic;

	/**
	 * 获取数据库数据
	 * 
	 * @param param
	 *            传递参数
	 * @return List<BookDetailCompareIntoReadResult>
	 * @see
	 * @since 1.0
	 */
	public List<ProdChannelFeeCalBookReaderDto> execute(Map<String, Object> param) {

		param.put("acctMode", DictCodeList.收益类型_货币型.getValue());
		param.put("acctSubjectType", DictCodeListExt1.金融产品类型_通道.getValue());
		List<ProdChannelFeeCalBookReaderDto> dtos = prodChannelFeeCalBookMapper.searchProdChannelFeeTodo(param);

		if (dtos != null && dtos.size() > 0) {
			Map<ProdChannelFeeCalKey, Object> resultMap = new HashMap<>();
			for (ProdChannelFeeCalBookReaderDto dto : dtos) {
				ProdChannelFeeCalKey key = new ProdChannelFeeCalKey();
				key.setBokDetailId(dto.getBokDetailId());
				key.setBookDate(dto.getBookDate());
				key.setBooksetDate(dto.getBooksetDate());
				key.setBooksetId(dto.getBooksetId());
				key.setBranch(dto.getBranch());
				key.setBusiDetailType(dto.getBusiDetailType());
				key.setBusiId(dto.getBusiId());
				key.setHappenDate(dto.getHappenDate());
				key.setStandardCcy(dto.getStandardCcy());
				key.setTaskId(dto.getTaskId());
				// 根据其他参数合并除子账套代码以外的参数
				if (resultMap.get(key) == null) {
					List<String> sonList = new ArrayList<String>();
					sonList.add(dto.getBooksetIdSon());
					dto.setBooksetIdSonList(sonList);
					resultMap.put(key, dto);
				} else {
					ProdChannelFeeCalBookReaderDto addDto = (ProdChannelFeeCalBookReaderDto) resultMap.get(key);
					addDto.getBooksetIdSonList().add(dto.getBooksetIdSon());
					resultMap.put(key, addDto);
				}
			}
			List<ProdChannelFeeCalBookReaderDto> restList = new ArrayList<ProdChannelFeeCalBookReaderDto>();

			for (ProdChannelFeeCalKey skey : resultMap.keySet()) {
				restList.add((ProdChannelFeeCalBookReaderDto) resultMap.get(skey));
			}
			// 获取分录信息
			Set<String> bookSetIds = new HashSet<String>();
			restList.forEach(item -> {
				if (StringUtil.isNotEmpty(item.getBooksetId())) {
					bookSetIds.add(item.getBooksetId());
				}
			});
			Map<String, TreeNode> nodes = bookCommonLogic.commonForGetTreeNodeMap(bookSetIds,
					restList.get(0).getTaskId());
			for (ProdChannelFeeCalBookReaderDto currDTO : restList) {
				currDTO.setBookSetTreeNode(nodes.get(currDTO.getBooksetId()));
			}
			return restList;
		}

		return dtos;

	}

}
