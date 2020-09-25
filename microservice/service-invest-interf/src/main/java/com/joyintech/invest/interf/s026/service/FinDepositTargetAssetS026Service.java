package com.joyintech.invest.interf.s026.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import com.joyintech.invest.interf.common.core.util.InfMapValUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 存款类资产要素信息 Service
 */
@Service
@INF("S026")
public class FinDepositTargetAssetS026Service extends InterfSendBaseService<String> {

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
		// 存款本金规模
		body.put("ISSUE_AMT", request.get("issueAmt"));

		// 币种
		body.put("CCY", request.get("ccy"));
		// 境内外
		body.put("BLN_AREA", request.get("blnArea"));

		// 存/借款机构
		body.put("ISSUER", request.get("issuer"));

		// 起息日
		body.put("VDATE", DateUtil.format(new Date((Long)request.get("vdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
		// 到期日
		body.put("MDATE", DateUtil.format(new Date((Long)request.get("mdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));

		// 保本标识
		body.put("CAPI_INCOME_FEATURE", request.get("capiIncomeFeature"));
		// 原资产代码
		body.put("O_FINPROD_ID", request.get("oFinprodId"));
		// 担保人
		body.put("SPONSOR", request.get("sponsor"));


		//计息要素
		Map<String, Object> finInterestFactorDto = (Map<String, Object>)request.get(
				"finInterestFactorDto");

		if (finInterestFactorDto != null ) {
			// 利率类型
			body.put("INT_TYPE", finInterestFactorDto.get("intType"));
			// 计息基础
			body.put("BASIS", finInterestFactorDto.get("basis"));
			// 初始利率
			body.put("RATE", finInterestFactorDto.get("rate"));
			// 挂钩利率代码
			body.put("BENCHMARK_ID", finInterestFactorDto.get("benchmarkId"));

			// 利率系数
			body.put("COEFFICIENT", finInterestFactorDto.get("coefficient"));
			// 利差
			body.put("SPREAD_RATE", finInterestFactorDto.get("spreadRate"));
			// 利率上限
			body.put("HIGHEST_RATE", finInterestFactorDto.get("highestRate"));
			// 利率下限
			body.put("LOWEST_RATE", finInterestFactorDto.get("lowestRate"));
			// 浮动利率调整规则
			body.put("RESET_TYPE", finInterestFactorDto.get("resetType"));
			// 固定间隔数值
			body.put("OBSERVE_BEF_DAY", finInterestFactorDto.get("observeBefDay"));
			// 固定间隔单位
			body.put("OBSERVE_BEF_UNIT", finInterestFactorDto.get("observeBefUnit"));
		}
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
