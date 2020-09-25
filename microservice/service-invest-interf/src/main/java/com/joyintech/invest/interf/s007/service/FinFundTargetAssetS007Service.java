package com.joyintech.invest.interf.s007.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import com.joyintech.invest.interf.common.core.util.InfMapValUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 基金要素信息 Service
 */
@Service
@INF("S007")
public class FinFundTargetAssetS007Service extends InterfSendBaseService<String> {

    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		// 消息头
		BusinessUtil.createSendInterfHeader(request, response, getInfId());

		// 消息体
		Map<String, Object> body = response.getBody();
		// 资产代码
		body.put("FINPROD_ID", request.get("finprodId"));
		// 市场代码
		body.put("FINPROD_MARKET_ID", request.get("finprodMarketId"));

		// 资产名称
		body.put("FINPROD_NAME", request.get("finprodName"));
		// 资产简称
		body.put("FINPROD_ABBR", request.get("finprodAbbr"));

		// 申购赎回简称
		body.put("PUR_RED_ABBR", request.get("purRedAbbr"));
		// 交易所基金简称
		body.put("TRM_FUND_ABBR", request.get("trmFundAbbr"));
		// 交易场所
		body.put("TRADE_MARKET", request.get("tradeMarket"));


		// 币种
		body.put("CCY", request.get("ccy"));
		// 境内外
		body.put("BLN_AREA", request.get("blnArea"));
		// 发行方式
		body.put("ISSUE_TYPE", request.get("issueType"));
		// 基金管理人
		body.put("ISSUER", request.get("issuer"));
		// 发行日
		body.put("IDATE", DateUtil.format(new Date((Long)request.get("idate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
		// 起息日
		body.put("VDATE", DateUtil.format(new Date((Long)request.get("vdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
		// 到期日
		body.put("MDATE", DateUtil.format(new Date((Long)request.get("mdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
        // 保本标识
		body.put("CAPI_INCOME_FEATURE", request.get("capiIncomeFeature"));
		// 申购确认速度
		body.put("PUR_SPEED", request.get("purSpeed"));
		// 赎回确认速度
		body.put("RED_SPEED", request.get("redSpeed"));
		// 资产投资性质
		body.put("INVEST_NATURE", request.get("investNature"));
		// 单笔申购金额下限
		body.put("PUR_START_LOW", request.get("purStartLow"));
		// 单笔赎回金额下限
		body.put("RED_START_LOW", request.get("redStartLow"));
		// 场内申赎代码
		body.put("PUR_RED_ID", request.get("purRedId"));
		// 最小申赎单位
		body.put("MIN_PRUNIT", request.get("etfMinPrunit"));

		//分红计划组件
		Map<String, Object> bussFinIntPayDto = (Map<String, Object>)request.get(
				"bussFinIntPayDto");
		if(bussFinIntPayDto!=null) {
			// 分红方式
			body.put("PAY_TYPE", bussFinIntPayDto.get("sponsor"));

			// 结转频率
			body.put("PAY_CYCLE", bussFinIntPayDto.get("isFinOrg"));
		}
		// 是否按日复利
		body.put("IS_COMPOUND_INT", request.get("isCompoundInt"));
		// 备注
		body.put("REMARK", request.get("remark"));

		// 资产分类ASSET_TYPE
		List<Map<String, Object>> assetTypeList = new ArrayList<>();
		//一级分类
		Map<String, Object> assetTypeEntity001 = new HashMap<>();
		assetTypeEntity001.put("FINPROD_TYPE_TG","");
		assetTypeList.add(assetTypeEntity001);
		//二级分类

		Map<String, Object> assetTypeEntity002 = new HashMap<>();
		assetTypeEntity002.put("CUSTOM_ASSET_TYPE_TWO",request.get("type9"));
		assetTypeList.add(assetTypeEntity002);
		//三级分类

		Map<String, Object> assetTypeEntity003 = new HashMap<>();
		assetTypeEntity003.put("CUSTOM_ASSET_TYPE_THREE",request.get("type10"));
		assetTypeList.add(assetTypeEntity003);
		//四级分类

		Map<String, Object> assetTypeEntity004 = new HashMap<>();
		assetTypeEntity004.put("CUSTOM_ASSET_TYPE_FOUR",request.get("type11"));
		assetTypeList.add(assetTypeEntity004);
		//五级分类

		Map<String, Object> assetTypeEntity005 = new HashMap<>();
		assetTypeEntity005.put("CUSTOM_ASSET_TYPE_FIVE",request.get("type12"));
		assetTypeList.add(assetTypeEntity005);
		body.put("ASSET_TYPE", assetTypeList);
		return response;
	}

	@Override
	protected void beforeHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void afterHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
		
	}
}
