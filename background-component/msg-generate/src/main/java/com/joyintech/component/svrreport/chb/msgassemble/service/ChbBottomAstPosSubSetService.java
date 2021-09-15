/*
 * 文件名：ChbPrdDeclareSubSetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年9月24日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.component.svrreport.chb.msgassemble.service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.component.svrreport.chb.contant.ChbConstant;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.component.svrreport.chb.dto.ChbBondSubmitPara;
import com.joyintech.component.svrreport.chb.dto.ChbBotAstPosSubDto;
import com.joyintech.component.svrreport.chb.dto.ChbMsgBuildInfoDto;
import com.joyintech.component.svrreport.chb.dto.CnBondInterfItemDto;

/**
 * 设置 底层资产登记
 * 
 * @description
 * @author lingxq
 * @version 1.0 2020年9月24日
 * @see ChbBottomAstPosSubSetService
 * @since 1.0
 */
@Service
public class ChbBottomAstPosSubSetService {

	private static Logger logger = LoggerFactory.getLogger(ChbBottomAstPosSubSetService.class);

	@Autowired
	private ChbMsgSetCommonService chbMsgSetCommonService;

	/**
	 * 设置消息
	 * 
	 * @param datas
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ChbMsgBuildInfoDto setMsg(List<ChbBotAstPosSubDto> datas) throws Exception {
		logger.debug("开始组装【底层资产信息登记】报送报文");
		ChbBondSubmitPara chbBondSubmitPara = generateBondMessage(datas);
		ChbMsgBuildInfoDto chbMsgBuildInfoDto = chbMsgSetCommonService.buildMsg(chbBondSubmitPara);
		chbMsgBuildInfoDto.setChbBondSubmitPara(chbBondSubmitPara);
		logger.debug("结束组装【底层资产信息登记】报送报文");

		return chbMsgBuildInfoDto;
	}

	/**
	 * 生成标准接口的数据结构
	 * 
	 * @param datas 业务数据
	 * @return 标准接口的数据结构
	 */
	private ChbBondSubmitPara generateBondMessage(List<ChbBotAstPosSubDto> datas) {
		if(StringUtil.isEmptyList(datas)){
			return new ChbBondSubmitPara();
		}
		ChbBondSubmitPara res = new ChbBondSubmitPara();
		
		List<Map<String, CnBondInterfItemDto>> infos = new ArrayList<>();
		Map<String,String> registSeqKeyMap = new HashMap<String,String>();
		
		List<String> registSeq = new ArrayList<String>();
		Map<String, CnBondInterfItemDto> cnBondItemMap = new LinkedHashMap<String, CnBondInterfItemDto>();
		List<Map<String, CnBondInterfItemDto>> mapList7 = new ArrayList<Map<String, CnBondInterfItemDto>>();
		for (ChbBotAstPosSubDto item : datas) {
			String currRegistSeq = "";
			// modify by zhangliang 20210130 重新发送不需要重生生成流水号 start
			if (StringUtil.isNotEmpty(item.getSumitSeq())){
				registSeq.add(item.getSumitSeq());
				//add by wudebao 20210424 在para里面设置唯一标识符 start
				registSeqKeyMap.put(item.getSumitSeq(), item.getPlanAssetId() + ',' + item.getAssetCode() + ',' + DateUtil.format(item.getPosDate()));				
				//add by wudebao 20210424 在para里面保存唯一标识符 end
			}
			else{
				currRegistSeq = Sid.nextShort(20);
				item.setSumitSeq(currRegistSeq);
				registSeq.add(currRegistSeq);
				//add by wudebao 20210424 在para里面设置唯一标识符 start
				registSeqKeyMap.put(currRegistSeq, item.getPlanAssetId() + ',' + item.getAssetCode() + ',' + DateUtil.format(item.getPosDate()));				
				//add by wudebao 20210424 在para里面设置唯一标识符 end	
			}
//            if(DictCodeList.报送反馈状态_发送失败.getValue().equals(item.getSubmitFeedbackStatus())
//                    ||DictCodeList.报送反馈状态_中债退回.getValue().equals(item.getSubmitFeedbackStatus())){
//                currRegistSeq = Sid.nextShort(20);
//                item.setSumitSeq(currRegistSeq);
//                registSeq.add(currRegistSeq);
//            }
//            else if(StringUtil.isEmpty(item.getSumitSeq())){
//                currRegistSeq = Sid.nextShort(20);
//                item.setSumitSeq(currRegistSeq);
//                registSeq.add(currRegistSeq);
//            }
//            else{
//                registSeq.add(item.getSumitSeq());
//            }
            //同一个父节点只添加一次
            if((cnBondItemMap.containsKey("AssetsLiabilitiesIdentificationCodeOfAssetManagementProductOrOutsourcingAsset")
    				&& cnBondItemMap.get("AssetsLiabilitiesIdentificationCodeOfAssetManagementProductOrOutsourcingAsset").getValue().equals(item.getPlanAssetId()))
    				&& (cnBondItemMap.containsKey("ReportDate") && cnBondItemMap.get("ReportDate").getValue().equals(DateUtil.format(item.getPosDate())))){
            	//nothing to do
            }else{
            	cnBondItemMap = new LinkedHashMap<String, CnBondInterfItemDto>();
            	mapList7 = new ArrayList<Map<String, CnBondInterfItemDto>>();
            	infos.add(cnBondItemMap);
            }
            //父节点 <wemax:UnderlyingAssetsLiabilitiesRegistrationTuple>封装
            buildXmlData(cnBondItemMap, item);
            //子节点 <wemax:UnderlyingAssetTuple>封装
    		buildChildXml(cnBondItemMap,item,mapList7);
		}
		res.setInfos(infos);
		//add by wudebao 20210424 在para里面设置唯一标识符唯一标识符 start		
		res.setRegistSeqKeyMap(registSeqKeyMap);		
		//add by wudebao 20210424 在para里面保存唯一标识符 end		
		res.setDealType(DictCodeList.报表名称_资管计划底层持仓信息登记.getValue());
		res.setSysDate(DateUtil.now());
		res.setRegistSeq(registSeq);
		res.setRegistBankCode(datas.get(0).getIssuePartyId());
		return res;
	}

	/**
	 * 底层资产持仓xml数据格式封装
	 * @param cnBondItemMap1
	 * @param item
	 * 底层资产信息登记 [abstract]
		底层资产信息登记 [tuple]
		    发行机构代码
		    资管及委外资产行内资产/负债编码
		    资管及委外资产当前总数量
		    资管及委外资产当前总折算人民币金额
		    资管及委外资产未投资头寸
		底层资产情况 [tuple]
		    底层资产行内资产/负债编码
		    底层资产持仓数量
		    底层资产折算人民币市值
		    登记流水号
		    持仓日期
	 */
	private void buildXmlData(Map<String, CnBondInterfItemDto> cnBondItemMap1, ChbBotAstPosSubDto item) {
		if((cnBondItemMap1.containsKey("AssetsLiabilitiesIdentificationCodeOfAssetManagementProductOrOutsourcingAsset")
				&& cnBondItemMap1.get("AssetsLiabilitiesIdentificationCodeOfAssetManagementProductOrOutsourcingAsset").getValue().equals(item.getPlanAssetId()))
				&& (cnBondItemMap1.containsKey("ReportDate") && cnBondItemMap1.get("ReportDate").getValue().equals(DateUtil.format(item.getPosDate())))){
			//nothing to do
		}else{
			//发行机构代码
			if(StringUtil.isNotEmpty(item.getIssuePartyId())){
				CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
				cnBondItem1.setValue(item.getIssuePartyId());
				cnBondItemMap1.put("IssuerIdentificationCode", cnBondItem1);
			}
			//对应资管及委外资产行内资产/负债编码
			if(StringUtil.isNotEmpty(item.getPlanAssetId())){
				CnBondInterfItemDto cnBondItem2 = new CnBondInterfItemDto();
				cnBondItem2.setValue(item.getPlanAssetId());
				cnBondItemMap1.put("AssetsLiabilitiesIdentificationCodeOfAssetManagementProductOrOutsourcingAsset", cnBondItem2);
			}
			//资管及委外资产当前总数量
			if(StringUtil.isNotEmpty(item.getTotalNum())){
				CnBondInterfItemDto cnBondItem3 = new CnBondInterfItemDto();
				cnBondItem3.setValue(item.getTotalNum().setScale(5, RoundingMode.HALF_UP).toString());
				cnBondItem3.setUnitRef(ChbConstant.PURE);
				cnBondItem3.setDecimals("5");
				cnBondItemMap1.put("AggregateQuantityOfAssetManagementProductOrOutsourcingAsset", cnBondItem3);
			}
			//资管及委外资产当前总折算人民币金额
			if(StringUtil.isNotEmpty(item.getIssueAmt())){
				CnBondInterfItemDto cnBondItem4 = new CnBondInterfItemDto();
				cnBondItem4.setValue(item.getIssueAmt().setScale(2, RoundingMode.HALF_UP).toString());
				cnBondItem4.setUnitRef(ChbConstant.币种全称_中债_人民币);
				cnBondItem4.setDecimals("2");
				cnBondItemMap1.put("AggregateIssuedAmountOfAssetManagementProductOrOutsourcingAssetCNY", cnBondItem4);
			}
			//资管及委外资产未投资头寸
			if(StringUtil.isNotEmpty(item.getUninvestCash())){
				CnBondInterfItemDto cnBondItem5 = new CnBondInterfItemDto();
				cnBondItem5.setValue(item.getUninvestCash().setScale(2, RoundingMode.HALF_UP).toString());
				cnBondItem5.setUnitRef(ChbConstant.币种全称_中债_人民币);
				cnBondItem5.setDecimals("2");
				cnBondItemMap1.put("NonInvestedAmountOfAssetManagementProductOrOutsourcingAssetCNY", cnBondItem5);
			}
			//持仓日期
			if(StringUtil.isNotEmpty(item.getPosDate())){
				CnBondInterfItemDto cnBondItem6 = new CnBondInterfItemDto();
				cnBondItem6.setValue(DateUtil.format(item.getPosDate()));
				cnBondItemMap1.put("ReportDate", cnBondItem6);
			}
		}
	}

	/**
	 * 子节点封装
	 * @param item
	 * @return
	 */
	private void buildChildXml(Map<String, CnBondInterfItemDto> cnBondItemMap,ChbBotAstPosSubDto item,List<Map<String, CnBondInterfItemDto>> mapList7) {
		//底层资产情况子节点
		Map<String, CnBondInterfItemDto> cnBondItemMap7_1 =  new LinkedHashMap<String, CnBondInterfItemDto>();//子节点map
		CnBondInterfItemDto cnBondItem7 = new CnBondInterfItemDto();
		//底层资产行内资产/负债编码
		if(StringUtil.isNotEmpty(item.getAssetCode())){
			CnBondInterfItemDto cnBondItem7_1 = new CnBondInterfItemDto();//第一个子节点
			cnBondItem7_1.setValue(item.getAssetCode().toString());
			cnBondItemMap7_1.put("AssetsLiabilitiesIdentificationCodeOfUnderlyingAssetsLiabilities",cnBondItem7_1);
		}
		//底层资产持仓数量
		if(StringUtil.isNotEmpty(item.getPosNum())){
			CnBondInterfItemDto cnBondItem7_2 = new CnBondInterfItemDto();//第二个子节点
			cnBondItem7_2.setValue(item.getPosNum().setScale(5, RoundingMode.HALF_UP).toString());
			cnBondItem7_2.setUnitRef(ChbConstant.PURE);
			cnBondItem7_2.setDecimals("5");
			cnBondItemMap7_1.put("QuantityOfUnderlyingAssetsLiabilities",cnBondItem7_2);
		}
		//底层资产折算人民币市值
		if(StringUtil.isNotEmpty(item.getPosAmt())){
			CnBondInterfItemDto cnBondItem7_3 = new CnBondInterfItemDto();//第三个子节点
			cnBondItem7_3.setValue(item.getPosAmt().setScale(2, RoundingMode.HALF_UP).toString());
			cnBondItem7_3.setUnitRef(ChbConstant.币种全称_中债_人民币);
			cnBondItem7_3.setDecimals("2");
			cnBondItemMap7_1.put("MarketValueOfUnderlyingAssetsLiabilitiesCNY",cnBondItem7_3);
		}
		//登记流水号
		if(StringUtil.isNotEmpty(item.getSumitSeq())){
			CnBondInterfItemDto cnBondItem7_4 = new CnBondInterfItemDto();//第四个子节点
			cnBondItem7_4.setValue(item.getSumitSeq());
			cnBondItemMap7_1.put("RegistrationSerialNumber",cnBondItem7_4);
		}
		
		mapList7.add(cnBondItemMap7_1);
		cnBondItem7.setMaps(mapList7);//第一个父子节点组合结束
		cnBondItemMap.put("UnderlyingAssetTuple", cnBondItem7);
	}
}
