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
import com.joyintech.component.svrreport.chb.dto.ChbAstValueSubDto;
import com.joyintech.component.svrreport.chb.dto.ChbBondSubmitPara;
import com.joyintech.component.svrreport.chb.dto.ChbMsgBuildInfoDto;
import com.joyintech.component.svrreport.chb.dto.CnBondInterfItemDto;

/**
 * 设置 资产估值登记
 * 
 * @description
 * @author lingxq
 * @version 1.0 2020年9月24日
 * @see ChbAssetValSubSetService
 * @since 1.0
 */
@Service
public class ChbAssetValSubSetService {

	private static Logger logger = LoggerFactory.getLogger(ChbAssetValSubSetService.class);

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
	public ChbMsgBuildInfoDto setMsg(List<ChbAstValueSubDto> datas) throws Exception {
		logger.debug("开始组装【资产估值信息登记】报送报文");
		ChbBondSubmitPara chbBondSubmitPara = generateBondMessage(datas);
		ChbMsgBuildInfoDto chbMsgBuildInfoDto = chbMsgSetCommonService.buildMsg(chbBondSubmitPara);
		chbMsgBuildInfoDto.setChbBondSubmitPara(chbBondSubmitPara);
		logger.debug("结束组装【资产估值信息登记】报送报文");

		return chbMsgBuildInfoDto;
	}

	/**
	 * 生成标准接口的数据结构
	 * 
	 * @param datas 业务数据
	 * @return 标准接口的数据结构
	 */
	private ChbBondSubmitPara generateBondMessage(List<ChbAstValueSubDto> datas) {
		if(StringUtil.isEmptyList(datas)){
			return new ChbBondSubmitPara();
		}
		ChbBondSubmitPara res = new ChbBondSubmitPara();
		
		List<Map<String, CnBondInterfItemDto>> infos = new ArrayList<>();
		Map<String,String> registSeqKeyMap = new HashMap<String,String>();
		
		List<String> registSeq = new ArrayList<String>();
		datas.forEach(item -> {
			Map<String, CnBondInterfItemDto> cnBondItemMap = new LinkedHashMap<String, CnBondInterfItemDto>();
			 String currRegistSeq = "";
			// modify by zhangliang 20210130 重新发送不需要重生生成流水号 start
			if (StringUtil.isNotEmpty(item.getSumitSeq())){
				registSeq.add(item.getSumitSeq());
				//add by wudebao 20210424 在para里面设置唯一标识符 start
				registSeqKeyMap.put(item.getSumitSeq(), item.getAssetCode() + ',' + DateUtil.format(item.getValDate()));				
				//add by wudebao 20210424 在para里面保存唯一标识符 end
			}
			else{
				currRegistSeq = Sid.nextShort(20);
				item.setSumitSeq(currRegistSeq);
				registSeq.add(currRegistSeq);
				//add by wudebao 20210424 在para里面设置唯一标识符 start
				registSeqKeyMap.put(currRegistSeq, item.getAssetCode() + ',' + DateUtil.format(item.getValDate()));				
				//add by wudebao 20210424 在para里面设置唯一标识符 end	
			}
//             if(DictCodeList.报送反馈状态_发送失败.getValue().equals(item.getSubmitFeedbackStatus())
//                     ||DictCodeList.报送反馈状态_中债退回.getValue().equals(item.getSubmitFeedbackStatus())){
//                 currRegistSeq = Sid.nextShort(20);
//                 item.setSumitSeq(currRegistSeq);
//                 registSeq.add(currRegistSeq);
//             }
//             else if(StringUtil.isEmpty(item.getSumitSeq())){
//                 currRegistSeq = Sid.nextShort(20);
//                 item.setSumitSeq(currRegistSeq);
//                 registSeq.add(currRegistSeq);
//             }
//             else{
//                 registSeq.add(item.getSumitSeq());
//             }
			buildXmlData(cnBondItemMap, item);
			infos.add(cnBondItemMap);
		});
		
		res.setInfos(infos);
		//add by wudebao 20210424 在para里面设置唯一标识符唯一标识符 start		
		res.setRegistSeqKeyMap(registSeqKeyMap);		
		//add by wudebao 20210424 在para里面保存唯一标识符 end
		res.setDealType(DictCodeList.报表名称_资产估值信息登记.getValue());
		res.setSysDate(DateUtil.now());
		res.setRegistSeq(registSeq);
		res.setRegistBankCode(datas.get(0).getIssuePartyId());
		return res;
	}

	/**
	 * 资产估值登记xml数据格式封装
	 * @param cnBondItemMap1
	 * @param item
	 * 估值信息登记 [abstract]
		   估值信息登记 [tuple]
			行内资产/负债编码
			发行机构代码
			估值日期
			单位估值（净价）
			单位估值（全价）
			估值信息登记备注
			登记流水号
	 */
	private void buildXmlData(Map<String, CnBondInterfItemDto> cnBondItemMap1, ChbAstValueSubDto item) {
		//行内资产/负债编码
		if(StringUtil.isNotEmpty(item.getAssetCode())){
			CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
		    cnBondItem1.setValue(item.getAssetCode().toString());
		    cnBondItemMap1.put("AssetsAndLiabilitiesIdentificationCode", cnBondItem1);
		}
		//发行机构代码
		if(StringUtil.isNotEmpty(item.getIssuePartyId())){
			CnBondInterfItemDto cnBondItem2 = new CnBondInterfItemDto();
			cnBondItem2.setValue(item.getIssuePartyId());
			cnBondItemMap1.put("IssuerIdentificationCode", cnBondItem2);
		}
		//估值日期
		if(StringUtil.isNotEmpty(item.getValDate())){
			CnBondInterfItemDto cnBondItem3 = new CnBondInterfItemDto();
			cnBondItem3.setValue(DateUtil.format(item.getValDate()));
			cnBondItemMap1.put("ValuationDate", cnBondItem3);
		}
		//单位估值（净价）
		if(StringUtil.isNotEmpty(item.getUnitValNet())){
			CnBondInterfItemDto cnBondItem4 = new CnBondInterfItemDto();
			cnBondItem4.setValue(item.getUnitValNet().setScale(4, RoundingMode.HALF_UP).toString());
			cnBondItem4.setUnitRef(ChbConstant.币种全称_中债_人民币);
			cnBondItem4.setDecimals("4");
			cnBondItemMap1.put("PerUnitValuationNet", cnBondItem4);
		}
		//单位估值（全价）
		if(StringUtil.isNotEmpty(item.getUnitValTotal())){
			CnBondInterfItemDto cnBondItem5 = new CnBondInterfItemDto();
			cnBondItem5.setValue(item.getUnitValTotal().setScale(4, RoundingMode.HALF_UP).toString());
			cnBondItem5.setUnitRef(ChbConstant.币种全称_中债_人民币);
			cnBondItem5.setDecimals("4");
			cnBondItemMap1.put("PerUnitValuationFull", cnBondItem5);
		}
		//估值信息登记备注
		if(StringUtil.isNotEmpty(item.getRemark())){
			CnBondInterfItemDto cnBondItem6 = new CnBondInterfItemDto();
			cnBondItem6.setValue(item.getRemark());
			cnBondItemMap1.put("ValuationRegistrationDetails", cnBondItem6);
		}
		//登记流水号
		if(StringUtil.isNotEmpty(item.getSumitSeq())){
			CnBondInterfItemDto cnBondItem7 = new CnBondInterfItemDto();
			cnBondItem7.setValue(item.getSumitSeq());
			cnBondItemMap1.put("RegistrationSerialNumber", cnBondItem7);
		}
	}
}
