package com.joyintech.invest.interf.s001.service;

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
 * 债券要素信息 Service
 */
@Service
@INF("S001")
public class FinBondTargetAssetS001Service extends InterfSendBaseService<String> {

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
		// 交易场所
		body.put("TRADE_MARKET", request.get("tradeMarket"));

		// 发行价
		body.put("ISSUE_PRICE", request.get("issuePrice"));
		// 发行规模
		body.put("ISSUE_AMT", request.get("issueAmt"));

		// 币种
		body.put("CCY", request.get("ccy"));
		// 境内外
		body.put("BLN_AREA", request.get("blnArea"));
		// 发行方式
		body.put("ISSUE_TYPE", request.get("issueType"));
		// 发行人
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

		// 是否永续
		body.put("IS_SUS", request.get("isSus"));
		// 永续条款
		body.put("SUSTAINABLE_REMARK", request.get("sustainableRemark"));
		// 是否含权
		body.put("IS_RIGHT", request.get("isRight"));
		// 登记托管机构
		body.put("REGIST_ORG", request.get("registOrg"));
		// 保本标识
		body.put("CAPI_INCOME_FEATURE", request.get("capiIncomeFeature"));
		// 原资产代码
		body.put("O_FINPROD_ID", request.get("oFinprodId"));
		// 是否金融机构发行
		body.put("IS_FIN_ORG", request.get("isFinOrg"));
		// 是否城投债
		body.put("IS_CITY_BOND", request.get("isCityBond"));
		// 担保人
		body.put("SPONSOR", request.get("sponsor"));


		//计息要素
		Map<String, Object> finInterestFactorDto = (Map<String, Object>)request.get(
				"finInterestFactorDto");

		//付息信息
		Map<String, Object> finPayInterestStdInfoDto = (Map<String, Object>)request.get(
				"finPayInterestStdInfoDto");
		if (finPayInterestStdInfoDto != null ) {
			// 付息频率
			body.put("PAY_CYCLE", finPayInterestStdInfoDto.get("payCycle"));
		}

		if (finInterestFactorDto != null ) {
			// 利率类型
			if(finInterestFactorDto.get("intType") != null) {
				body.put("INT_TYPE", finInterestFactorDto.get("intType"));
			}
			// 息票品种
			body.put("COUPON_SPECIES", finInterestFactorDto.get("couponSpecies"));
			// 计息基础
			body.put("BASIS", finInterestFactorDto.get("basis"));
			// 初始票面利率
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
		assetTypeEntity001.put("FINPROD_TYPE_TG",request.get("type4"));
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
