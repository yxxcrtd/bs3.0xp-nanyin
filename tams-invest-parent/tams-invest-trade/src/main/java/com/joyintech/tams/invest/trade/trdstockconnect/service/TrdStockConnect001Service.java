
package com.joyintech.tams.invest.trade.trdstockconnect.service;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.trdstockconnect.dao.TrdStockConnectMapper;
import com.joyintech.tams.invest.trade.trdstockconnect.dto.TrdStockConnectDto;

/**
 * 港股通交易Service
 *
 * @author hechuan
 * @version 1.0 2021年4月14日
 * @see TrdStockConnect001Service
 * @since 1.0
 */

@Service
public class TrdStockConnect001Service extends CommonExportService<TrdStockConnectDto> {
	
	
	@Autowired
	private TrdStockConnectMapper trdStockConnectMapper;
	

	/**
	 * 港股通交易信息
	 *
	 * @param pageParam
	 *            查询参数
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<TrdStockConnectDto> findPage(Map<String, Object> pageParam) {
		// 多选下拉框值处理
		// 组合代码
		Object portfolioIdObj = pageParam.get("portFolioIdList");
		if (portfolioIdObj != null) {
			pageParam.put("portFolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String) portfolioIdObj));
		}
		Object psListObj = pageParam.get("psList");
		if (psListObj != null) {
			pageParam.put("psList", StringUtil.splitToList(GlobalConstant.COMMA, (String) psListObj));
		}
		Object hkMarketListObj = pageParam.get("hkMarketList");
		if (hkMarketListObj != null) {
			pageParam.put("hkMarketList", StringUtil.splitToList(GlobalConstant.COMMA, (String) hkMarketListObj));
		}
//		return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStock/findPage",
//				pageParam, TrdStockDto.class);
		pageParam.put("VALID_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        pageParam.put("TRADE_PLACE_06", DictCodeListExt1.交易场所_香港联合交易所.getValue());
        List<String> psListDict = new ArrayList<>();
        psListDict.add(DictCodeListExt2.交易方向_配股.getValue());
        psListDict.add(DictCodeListExt2.交易方向_增发.getValue());
        psListDict.add(DictCodeList.交易方向_买入.getValue());
        psListDict.add(DictCodeList.交易方向_卖出.getValue());
        psListDict.add(DictCodeListExt2.交易方向_卖碎股.getValue());
        pageParam.put("psListDict", psListDict);
        
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        
        int page = Integer.parseInt(pageParam.get("page").toString());
        int length = Integer.parseInt(pageParam.get("length").toString());
        Page<TrdStockConnectDto> pageDto = super.queryForPage(page, length,() -> trdStockConnectMapper.selectDeal(pageParam));
        return pageDto;
	}

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/trade/trdstockconnect/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<TrdStockConnectDto> selectPageData(Map<String, Object> selectParam) {
		return findPage(selectParam);
	}

	/**
	 * 获取委托代码
	 * @param map 请求参数
	 * @return 联想文本框实体类
	 * @since 1.0
	 */
	public List<SuggestDto> getEntrustIdByType(Map<String,Object> map){
		return restTemplate.postForList(
				ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getEntrustId", map,
				SuggestDto.class);
	}

	public List<SuggestDto> getFinprodIdNew(Map<String, Object> params) {
		params.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
		params.put("TRADE_PLACE_13", DictCodeListExt1.交易场所_香港联合交易所.getValue());
		params.put("OTH_TYPE_06", DictCodeListExt3.金融产品其他代码类型_配股代码.getValue());
    	return this.queryForSuggest(() -> trdStockConnectMapper.getFinprodIdNew(params), null);
    	
	}
}
