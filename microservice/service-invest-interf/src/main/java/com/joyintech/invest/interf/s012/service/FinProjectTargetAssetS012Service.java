package com.joyintech.invest.interf.s012.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import com.joyintech.invest.interf.common.core.util.InfMapValUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 项目要素信息 Service
 */
@Service
@INF("S012")
public class FinProjectTargetAssetS012Service extends InterfSendBaseService<String> {

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
		// 合同编号
		body.put("CONTRACT_NO", request.get("contractNo"));

		// 币种
		body.put("CCY", request.get("ccy"));
		// 境内外
		body.put("BLN_AREA", request.get("blnArea"));

		//融资人
		body.put("ISSUER", request.get("issuer"));

		// 担保人
		body.put("SPONSOR", request.get("sponsor"));

		// 投资顾问
		body.put("INVEST_ADVISER", request.get("investAdviser"));

		// 起息日
		body.put("VDATE", DateUtil.format(new Date((Long)request.get("vdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
		// 到期日
		body.put("MDATE", DateUtil.format(new Date((Long)request.get("mdate")),
				DateUtil.DEFAULT_OTHER_FORMAT));
		// 融资金额
		body.put("ISSUE_AMT", request.get("issueAmt"));
		// 保本标识
		body.put("CAPI_INCOME_FEATURE", request.get("capiIncomeFeature"));
		// 资产投资性质
		body.put("INVEST_NATURE", request.get("investNature"));

		// 实际授信主体
		body.put("ACTUAL_CREDIT_PROVID", request.get("actualCreditProvid"));

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
		//计息要素
		Map<String, Object> finInterestFactorDto = (Map<String, Object>)request.get(
				"finInterestFactorDto");
		if (finInterestFactorDto!= null ) {

			//付息频率
			if(finInterestFactorDto.get("finPayInterestStdInfoDto")!=null)
			{
				body.put("PAY_CYCLE",((Map<String, Object>) finInterestFactorDto.get("finPayInterestStdInfoDto")).get("payCycle"));
			}
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

		//增信信息
		Map<String, Object> bussFinProductCreditDto = (Map<String, Object>)request.get(
				"bussFinProductCreditDto");
		List<Map<String, Object>> creditList = new ArrayList<>();
		if (bussFinProductCreditDto!= null ) {
			List<Map<String, Object>> finProductCreditList=(List<Map<String, Object>>)bussFinRecommentDto.get("finProductCreditList");
			if(!StringUtil.isEmptyList(finProductCreditList))
			{
				for (Map<String, Object> creditInfoItem : finProductCreditList) {
					Map<String, Object> creditInfoMap = new HashMap<>();
					// 增信主体
					creditInfoMap.put("CREDIT_FLAG", creditInfoItem.get("creditFlag"));
					// 增信方式
					creditInfoMap.put("CREDIT_TYPE", creditInfoItem.get("creditType"));
					// 备注
					creditInfoMap.put("REMARK", creditInfoItem.get("remark"));
					creditList.add(creditInfoMap);
				}
			}
		}
		body.put("CREDIT_INFO", creditList);

		//通道信息
		Map<String, Object> bussParticipantInfoDto = (Map<String, Object>)request.get(
				"bussParticipantInfoDto");
		List<Map<String, Object>> chlInfoList = new ArrayList<>();
		if (bussParticipantInfoDto!= null ) {
			List<Map<String, Object>> bussParticipantInfoDtoList=(List<Map<String, Object>>)bussParticipantInfoDto.get("bussParticipantInfoDtoList");
			if(!StringUtil.isEmptyList(bussParticipantInfoDtoList))
			{
				for (Map<String, Object> chlItem : bussParticipantInfoDtoList) {
					Map<String, Object> chlInfoMap = new HashMap<>();
					// 通道代码
					chlInfoMap.put("CHL_ID", chlItem.get("counterId"));
					// 通道名称
					chlInfoMap.put("CHL_NAME", chlItem.get("counterName"));
					// 备注
					chlInfoMap.put("REMARK", chlItem.get("remark"));
					chlInfoList.add(chlInfoMap);
				}
			}
		}
		body.put("CHL_INFO", chlInfoList);

		//费用信息

		List<Map<String, Object>> feeInfoList = new ArrayList<>();
		if (bussParticipantInfoDto!= null ) {
			List<Map<String, Object>> bussParticipantInfoDtoList=(List<Map<String, Object>>)bussParticipantInfoDto.get("bussParticipantInfoDtoList");
			if(!StringUtil.isEmptyList(bussParticipantInfoDtoList))
			{
				for (Map<String, Object> feeItem : bussParticipantInfoDtoList) {
					Map<String, Object> feeInfoMap = new HashMap<>();
					// 费用类型
					feeInfoMap.put("FEE_TYPE", "11");//默认通道管理费
					// 初始费率
					feeInfoMap.put("FEE_RATE", feeItem.get("managerYield"));

					feeInfoList.add(feeInfoMap);
				}
			}
		}
		body.put("FEE_INFO", feeInfoList);
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
