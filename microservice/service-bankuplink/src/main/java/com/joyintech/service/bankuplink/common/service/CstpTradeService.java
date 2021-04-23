/*
 * 文件名：CstpTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.common.service;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.bankuplink.common.dao.CstpTradeMapper;
import com.joyintech.service.bankuplink.common.dto.SecurityEntity;
import com.joyintech.tams.common.base.dao.InterfCstpTradeMapper;
import com.joyintech.tams.common.base.dao.InterfOpicsTradRepoMapper;
import com.joyintech.tams.common.base.dao.InterfTradRepoasstMapper;
import com.joyintech.tams.common.base.dao.InterfTradSecdealMapper;
import com.joyintech.tams.common.base.entity.InterfTradRepoasst;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * cstp交易入库
 */
@Service
public class CstpTradeService{

	/**
	 * 库表生成的cstp交易处理
	 */
	@Autowired
	private InterfCstpTradeMapper mapper;

	/**
	 * 接口处理cstp
	 */
	@Autowired
	private CstpTradeMapper cstpMapper;

	/**
	 * 库表生成的债券交易处理
	 */
	@Autowired
	private InterfTradSecdealMapper secMapper;

	/**
	 * 库表生成的回购交易处理
	 */
	@Autowired
	private InterfOpicsTradRepoMapper repoMapper;

	/**
	 * 库表生成的质押式回购关联
	 */
	@Autowired
	private InterfTradRepoasstMapper repoAsstMapper;

	/**
	 * cstp交易入库服务
	 */
	@Autowired
	private CstpTradeService cstpTradeSvc;

	/**
	 * 文件扩展名xml
	 */
	private static final String XML = "xml";

	/**
	 * 回购交易下行自动录单
	 */
	@Autowired
	private CstpRepoAutoTradeService repoAutoTradeService;

	/**
	 * 债券交易下行自动录单
	 */
	@Autowired
	private CstpSecAutoTradeService secAutoTradeService;


	/**
	 * 插入或更新质押品信息
	 *
	 * @param seList 抵押品信息
	 * @param execid 成交编号
	 * @return 条数
	 * @see
	 * @since 1.0
	 */
	private int insertOrUpdateRepoAsset(List<SecurityEntity> seList, String execid) {
		int count = 0;
		for (SecurityEntity sEntity : seList) {
			InterfTradRepoasst interfTradRepoasst = new InterfTradRepoasst();
			BeanUtil.copyPropertiesIgnoreNull(interfTradRepoasst, sEntity);
			interfTradRepoasst.setTradeid(execid);
			// 折算比例 * 100
			interfTradRepoasst.setConvrate(new BigDecimal(100).multiply(sEntity.getConvrate()));
			// 成交金额=折算比例*券面总额
			interfTradRepoasst.setDealamt(sEntity.getConvrate().multiply(sEntity.getFacetotalamt()));
			// 判断抵押品信息是否存在，若存在则更新，否则插入
			String tradeid = cstpMapper.selectrepoAsset(sEntity.getSecid(), execid);
			if (StringUtil.isNotEmpty(tradeid)) {
				// 更新
				count += cstpMapper.updateRepoAsst(interfTradRepoasst);
			} else {
				count += repoAsstMapper.insertSelective(interfTradRepoasst);
			}

		}
		return count;
	}


	/**
	 * 获取组合代码
	 *
	 * @param ourcapitalaccountnumber 资金账号
	 * @return 组合代码
	 * @see
	 * @since 1.0
	 */
	private String selectPortfolioId(String ourcapitalaccountnumber) {
		Map<String, Object> map = new HashMap<>();
		map.put("A08", BussinessDictCodeList.资金账户类型_甲类户.getValue());
		map.put("A09", BussinessDictCodeList.资金账户类型_上清所DVP账户.getValue());
		map.put("A10", BussinessDictCodeList.资金账户类型_中债登DVP账户.getValue());
		map.put("ourcapitalaccountnumber", ourcapitalaccountnumber);

		return cstpMapper.selectPortfolioId(map);

	}

}
