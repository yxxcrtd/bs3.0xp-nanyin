package com.joyintech.invest.interf.s031.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import com.joyintech.invest.interf.common.core.util.InfMapValUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 股票要素信息 Service
 */
@Service
@INF("S031")
public class FinStockTargetAssetS031Service extends InterfSendBaseService<String> {

    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
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

		// 币种
		body.put("CCY", request.get("ccy"));
		// 发行人
		body.put("ISSUER", request.get("issuer"));

		// 上市日期
		body.put("VDATE", DateUtil.format(new Date((Long)request.get("vdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
		// 退市日期
		body.put("MDATE", DateUtil.format(new Date((Long)request.get("mdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));

		// 上市板
		body.put("BOARD", request.get("board"));
		// 是否深港通或者沪港通
		body.put("SH_OR_ZH", request.get("shOrZh"));
		// 是否融资融券标的
		body.put("IS_MARGIN_FINPROD", request.get("isMarginFinprod"));
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
