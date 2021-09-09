package com.joyintech.component.svrreport.chb.msgassemble.service;

import java.math.BigDecimal;
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
import com.joyintech.component.svrreport.chb.dto.ChbMsgBuildInfoDto;
import com.joyintech.component.svrreport.chb.dto.ChbPrdEndSubDto;
import com.joyintech.component.svrreport.chb.dto.CnBondInterfItemDto;
import com.joyintech.component.svrreport.chb.util.ChbXmlUtil;

/**
 * 产品终止登记报文生成
 * @description 
 * @author 赵永生
 * @version 1.0 2020年9月24日
 * @see ChbPrdEndSubSetService
 * @since 1.0
 */
@Service
public class ChbPrdEndSubSetService {

	private static Logger logger = LoggerFactory.getLogger(ChbPrdEndSubSetService.class);

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
	public ChbMsgBuildInfoDto setMsg(List<ChbPrdEndSubDto> datas)  throws Exception {
		logger.debug("开始组装产品终止登记报送报文");
		//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		ChbBondSubmitPara chbBondSubmitPara = generateBondMessage(datas);
		//拼接报文
		ChbMsgBuildInfoDto chbMsgBuildInfoDto = chbMsgSetCommonService.buildMsg(chbBondSubmitPara);
		chbMsgBuildInfoDto.setChbBondSubmitPara(chbBondSubmitPara);
		logger.debug("结束组装产品终止登记报送报文");

		return chbMsgBuildInfoDto;
	}
	
	/**
	 * 生成标准接口的数据结构
	 * 
	 * @param datas 业务数据
	 * @return 标准接口的数据结构
	 */
	private ChbBondSubmitPara generateBondMessage(List<ChbPrdEndSubDto> datas) {
		if(StringUtil.isEmptyList(datas)){
			return new ChbBondSubmitPara();
		}
		ChbBondSubmitPara res = new ChbBondSubmitPara();
		
		List<Map<String, CnBondInterfItemDto>> infos = new ArrayList<>();

		
		List<String> registSeq = new ArrayList<String>();
		Map<String,String> registSeqKeyMap = new HashMap<String,String>();
		datas.forEach(item -> {
			//利率统一除以100
			ChbXmlUtil.rateFormat(item);
			
			Map<String, CnBondInterfItemDto> cnBondItemMap = new LinkedHashMap<String, CnBondInterfItemDto>();
			 String currRegistSeq = "";
			// modify by zhangliang 20210130 重新发送不需要重生生成流水号 start
			if (StringUtil.isNotEmpty(item.getSumitSeq())){
				registSeq.add(item.getSumitSeq());
				//add by wudebao 20210424 在para里面设置唯一标识符 start
				registSeqKeyMap.put(item.getSumitSeq(), item.getProdRegistCode() + ',' + item.getProdId());				
				//add by wudebao 20210424 在para里面保存唯一标识符 end
			}
			else{
				currRegistSeq = Sid.nextShort(20);
				item.setSumitSeq(currRegistSeq);
				registSeq.add(currRegistSeq);
				//add by wudebao 20210424 在para里面设置唯一标识符 start
				registSeqKeyMap.put(currRegistSeq, item.getProdRegistCode() + ',' + item.getProdId());				
				//add by wudebao 20210424 在para里面设置唯一标识符 end	
			}
//            if(DictCodeList.报送反馈状态_发送失败.getValue().equals(item.getSubmitFeedbackStatus())
//                    ||DictCodeList.报送反馈状态_中债退回.getValue().equals(item.getSubmitFeedbackStatus())){
//                currRegistSeq = Sid.nextShort(20);
//                registSeq.add(currRegistSeq);
//            }
//            else if(StringUtil.isEmpty(item.getSumitSeq())){
//                currRegistSeq = Sid.nextShort(20);
//                registSeq.add(currRegistSeq);
//            }
//            else{
//                registSeq.add(item.getSumitSeq());
//            }
			generateNodes(cnBondItemMap, item);
			infos.add(cnBondItemMap);
		});
		
		res.setDealType(DictCodeList.报表名称_产品终止登记.getValue());
		res.setSysDate(DateUtil.now());
	    //res.setEquityConfDate(equityConfDate);
		res.setRegistBankCode(datas.get(0).getIssuePartyId());
	//	List<String> registSeq = new ArrayList<String>();


		
		res.setRegistSeq(registSeq);
		res.setInfos(infos);
		//add by wudebao 20210424 在para里面设置唯一标识符唯一标识符 start		
		res.setRegistSeqKeyMap(registSeqKeyMap);		
		//add by wudebao 20210424 在para里面保存唯一标识符 end
				
		return res;
	}
	/**
	 * 生成标准接口的数据结构
	 * 
	 * @param datas 业务数据
	 * @return 标准接口的数据结构
	 */
	private void generateNodes(Map<String, CnBondInterfItemDto> cnBondItemMap1, ChbPrdEndSubDto sub) {
	    //产品登记编码
		if(StringUtil.isNotEmpty(sub.getProdRegistCode())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getProdRegistCode());
	    cnBondItemMap1.put("ProductRegistrationCode", cnBondItem1);
		}
	    //发行机构代码
		if(StringUtil.isNotEmpty(sub.getIssuePartyId())){
	    CnBondInterfItemDto cnBondItem2 = new CnBondInterfItemDto();
	    cnBondItem2.setValue(sub.getIssuePartyId());
	    cnBondItemMap1.put("IssuerIdentificationCode", cnBondItem2);
		}
		//实际终止日期
		if(sub.getMdate() != null){
	    CnBondInterfItemDto cnBondItem3 = new CnBondInterfItemDto();
	    cnBondItem3.setValue(DateUtil.format(sub.getMdate(), "yyyy-MM-dd"));
	    cnBondItemMap1.put("ActualProductTerminationDate", cnBondItem3);
		}
	    //银行实际实现收入
	    if(sub.getProfit() != null){
	    CnBondInterfItemDto cnBondItem4 = new CnBondInterfItemDto();
	    cnBondItem4.setValue(String.valueOf(sub.getProfit().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem4.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem4.setDecimals("2");
	    cnBondItemMap1.put("RealizedNetIncomeOfBankCNY", cnBondItem4);
	    }
	    //兑付客户收益
	    if(sub.getCustomProfit() != null){
	    CnBondInterfItemDto cnBondItem5 = new CnBondInterfItemDto();
	    cnBondItem5.setValue(String.valueOf(sub.getCustomProfit().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem5.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem5.setDecimals("2");
	    cnBondItemMap1.put("InterestPaymentCNY", cnBondItem5);
	    }
	    //兑付客户总金额
	    if(sub.getCustomAmt() != null){
	    CnBondInterfItemDto cnBondItem6 = new CnBondInterfItemDto();
	    cnBondItem6.setValue(String.valueOf(sub.getCustomAmt().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem6.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem6.setDecimals("2");
	    cnBondItemMap1.put("PrincipalAndInterestPaymentCNY", cnBondItem6);
	    }
	    //兑付总份额
	    if(sub.getCustomQty() != null){
	    CnBondInterfItemDto cnBondItem7 = new CnBondInterfItemDto();
	    cnBondItem7.setValue(String.valueOf(sub.getCustomQty().setScale(5, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem7.setUnitRef(ChbConstant.PURE);
	    cnBondItem7.setDecimals("5");
	    cnBondItemMap1.put("DeliveredUnits", cnBondItem7);
	    }
	    //本机构托管费
	    if(sub.getCustFeeIn() != null){
	    CnBondInterfItemDto cnBondItem8 = new CnBondInterfItemDto();
	    cnBondItem8.setValue(String.valueOf(sub.getCustFeeIn().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem8.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem8.setDecimals("2");
	    cnBondItemMap1.put("InternalCustodianFeeCNY", cnBondItem8);
	    }
	    //本机构管理费
	    if(sub.getManFeeIn() != null){
	    CnBondInterfItemDto cnBondItem9 = new CnBondInterfItemDto();
	    cnBondItem9.setValue(String.valueOf(sub.getManFeeIn().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem9.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem9.setDecimals("2");
	    cnBondItemMap1.put("InternalManagementFeeCNY", cnBondItem9);
	    }
	    //本机构销售手续费
	    if(sub.getCouFeeIn() != null){
	    CnBondInterfItemDto cnBondItem10 = new CnBondInterfItemDto();
	    cnBondItem10.setValue(String.valueOf(sub.getCouFeeIn().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem10.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem10.setDecimals("2");
	    cnBondItemMap1.put("InternalSalesCommissionCNY", cnBondItem10);
	    }
	    //本机构其他产品费用
	    if(sub.getOtherFeeIn() != null){
	    CnBondInterfItemDto cnBondItem11 = new CnBondInterfItemDto();
	    cnBondItem11.setValue(String.valueOf(sub.getOtherFeeIn().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem11.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem11.setDecimals("2");
	    cnBondItemMap1.put("InternalOtherProductFeeCNY", cnBondItem11);
	    }
	    //其他机构托管费
	    if(sub.getCustFeeOut() != null){
	    CnBondInterfItemDto cnBondItem12 = new CnBondInterfItemDto();
	    cnBondItem12.setValue(String.valueOf(sub.getCustFeeOut().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem12.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem12.setDecimals("2");
	    cnBondItemMap1.put("OtherInstitutionCustodianFeeCNY", cnBondItem12);
	    }
	    //其他机构管理费
	    if(sub.getManFeeOut() != null){
	    CnBondInterfItemDto cnBondItem13 = new CnBondInterfItemDto();
	    cnBondItem13.setValue(String.valueOf(sub.getManFeeOut().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem13.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem13.setDecimals("2");
	    cnBondItemMap1.put("OtherInstitutionManagementFeeCNY", cnBondItem13);
	    }
	    //其他机构销售手续费
	    if(sub.getCouFeeOut() != null){
	    CnBondInterfItemDto cnBondItem14 = new CnBondInterfItemDto();
	    cnBondItem14.setValue(String.valueOf(sub.getCouFeeOut().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem14.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem14.setDecimals("2");
	    cnBondItemMap1.put("OtherInstitutionSalesCommissionCNY", cnBondItem14);
	    }
	    //投资顾问费用
	    if(sub.getAdvFeeOut() != null){
	    CnBondInterfItemDto cnBondItem15 = new CnBondInterfItemDto();
	    cnBondItem15.setValue(String.valueOf(sub.getAdvFeeOut().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem15.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem15.setDecimals("2");
	    cnBondItemMap1.put("InvestmentConsultingFeeCNY", cnBondItem15);
	    }
	    //其他机构其他产品费用
	    if(sub.getOtherFeeOut() != null){
	    CnBondInterfItemDto cnBondItem16 = new CnBondInterfItemDto();
	    cnBondItem16.setValue(String.valueOf(sub.getOtherFeeOut().setScale(2, BigDecimal.ROUND_HALF_UP)));
	    cnBondItem16.setUnitRef(ChbConstant.币种全称_中债_人民币);
	    cnBondItem16.setDecimals("2");
	    cnBondItemMap1.put("OtherInstitutionOtherProductFeeCNY", cnBondItem16);
	    }
	    //客户实际年化收益率
	    if(sub.getCusYearYield() != null){
	    CnBondInterfItemDto cnBondItem17 = new CnBondInterfItemDto();
	    cnBondItem17.setValue(String.format("%.7f",sub.getCusYearYield()));
	    cnBondItem17.setUnitRef(ChbConstant.PURE);
	    cnBondItem17.setDecimals("7");
	    cnBondItemMap1.put("RealizedAnnualReturnToInvestors", cnBondItem17);
	    }
	    //产品实际年化收益率
	    if(sub.getPrdYearYield() != null){
	    CnBondInterfItemDto cnBondItem18 = new CnBondInterfItemDto();
	    cnBondItem18.setValue(String.format("%.7f",sub.getPrdYearYield()));
	    cnBondItem18.setUnitRef(ChbConstant.PURE);
	    cnBondItem18.setDecimals("7");
	    cnBondItemMap1.put("RealizedAnnualReturnToProduct", cnBondItem18);
	    }
		//登记流水号
		if(StringUtil.isNotEmpty(sub.getSumitSeq())){
	    CnBondInterfItemDto cnBondItem19 = new CnBondInterfItemDto();
	    cnBondItem19.setValue(sub.getSumitSeq());
	    cnBondItemMap1.put("RegistrationSerialNumber", cnBondItem19);
		}

	}
}
