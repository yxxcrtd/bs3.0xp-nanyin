package com.joyintech.invest.interf.s017.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import com.joyintech.invest.interf.common.core.util.InfMapValUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 计划类资产要素信息 Service
 */
@Service
@INF("S017")
public class FinPlanTargetAssetS017Service extends InterfSendBaseService<String> {

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

		// 期次号
		body.put("BRANCH", request.get("branch"));
		// 市场代码
		body.put("FINPROD_MARKET_ID", request.get("finprodMarketId"));

		// 合同编号
		body.put("CONTRACT_NO", request.get("contractNo"));
       // 资产名称
		body.put("FINPROD_NAME", request.get("finprodName"));

		// 发行价
		body.put("ISSUE_PRICE", request.get("issuePrice"));
		// 发行规模
		body.put("ISSUE_AMT", request.get("issueAmt"));
		// 收益类型
		body.put("PROFIT_TYPE", request.get("profitType"));
		// 币种
		body.put("CCY", request.get("ccy"));
		// 境内外
		body.put("BLN_AREA", request.get("blnArea"));
		//管理人
		body.put("MANAGER", request.get("manager"));

		// 托管人
		body.put("TRUSTEE_ID", request.get("trusteeId"));

		// 投资顾问
		body.put("INVEST_ADVISER", request.get("investAdviser"));

		// 成立日
		body.put("VDATE", DateUtil.format(new Date((Long)request.get("vdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
		// 终止日
		body.put("MDATE", DateUtil.format(new Date((Long)request.get("mdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
		// 清算速度
		body.put("SETTLE_SPEED", request.get("settleSpeed"));
		// 申购确认速度
		body.put("PUR_SPEED", request.get("purSpeed"));
		// 赎回确认速度
		body.put("RED_SPEED", request.get("redSpeed"));

		//分红方式组件
		Map<String, Object> bussFinIntPayDto = (Map<String, Object>)request.get(
				"bussFinIntPayDto");
		if (bussFinIntPayDto!= null ) {
			//付息频率/结转频率
			body.put("PAY_CYCLE", request.get("payCycle"));
			//分红方式
			body.put("PAY_TYPE", request.get("payType"));
		}

		//计息要素
		Map<String, Object> finInterestFactorDto = (Map<String, Object>)request.get(
				"finInterestFactorDto");
		if (finInterestFactorDto!= null ) {

			// 利率类型
			body.put("INT_TYPE", finInterestFactorDto.get("intType"));
			// 计息利率方式
			body.put("RATING_TYPE", finInterestFactorDto.get("calRateType"));
			// 计息基础
			body.put("BASIS", finInterestFactorDto.get("basis"));
			// 初始净投资收益率
			body.put("INVEST_RATE", finInterestFactorDto.get("investRate"));
			// 初始借款利率
			body.put("LOAD_RATE", finInterestFactorDto.get("loadRate"));
			// 初始买断利率
			body.put("BUYOUT_RATE", finInterestFactorDto.get("buyOutRate"));

		}
       //实际管理人
		body.put("ACTUAL_MANAGER", request.get("buyOutRate"));
       //资产投资性质
		body.put("INVEST_NATURE", request.get("investmentAttributes"));
		//保本标识
		body.put("CAPI_INCOME_FEATURE", request.get("capiIncomeFeature"));
		//备注
		body.put("REMARK", request.get("remark"));
		//资产推荐方及费率信息
		Map<String, Object> bussFinRecommentDto = (Map<String, Object>)request.get(
				"bussFinRecommentDto");
		List<Map<String, Object>> recommenList = new ArrayList<>();
		if (bussFinRecommentDto!= null ) {
			List<Map<String, Object>> finProductRecommentList=(List<Map<String, Object>>)bussFinRecommentDto.get("finProductRecommentList");
			if(!StringUtil.isEmptyList(finProductRecommentList))
			{
				for (Map<String, Object> item : finProductRecommentList) {
					Map<String, Object> recommenMap = new HashMap<>();
					// 资产推荐方
					recommenMap.put("RECOMMENDER", item.get("recommender"));
					// 资产推荐费率
					recommenMap.put("RECFEE_RATE", item.get("recfeeRate"));
					recommenList.add(recommenMap);
				}
			}
		}
		body.put("RECOMMENDER_INFO", recommenList);
		// 备注
		body.put("REMARK", request.get("remark"));

		//投后信息
		Map<String, Object> investMap=new HashMap<>();
	      //预警线
		investMap.put("WARN_LINE", request.get("warnLine"));
		// 预警线
		investMap.put("FLAT_LINE", request.get("flatLine"));
		//业绩基准
		investMap.put("BASE_RULE_INFO", request.get("baseRule"));
		//超额分成方式
		investMap.put("PROFIT_DIS_TYPE", request.get("overallocation"));

		body.put("INVEST_INFO", investMap);


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
