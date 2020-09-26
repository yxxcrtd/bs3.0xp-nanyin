
package com.joyintech.tams.valuation.feetrade.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingDetailMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingMapper;
import com.joyintech.tams.common.base.dao.BokDetailIdMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.TrdTradeFeeDetailMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetailKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.feetrade.dto.FeeTradeDetailDto;
import com.joyintech.tams.valuation.feetrade.dto.FeeTradeManagementDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @version 1.0 20200121
 * @see FeeTradeManagement002Service
 * @since 1.0
 */
@Service
public class FeeTradeManagement002Service extends BaseService<FeeTradeManagementDto> 
				implements BaseProcessService<FeeTradeManagementDto>{
    
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;
    
    @Autowired
    private	TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;
    
    @Autowired
    private BokDealAccountingMapper bokDealAccountingMapper;
    
    @Autowired
    private BokDealAccountingDetailMapper bokDealAccountingDetailMapper;
    
    @Autowired
    private BokDetailIdMapper bokDetailIdMapper;
    
    @Autowired
    private BokBooksetMapper bokBooksetMapper;
    
    @Autowired
    private InterfCommonService interfCommonService;
    
	@Override
	public void doComplete(FeeTradeManagementDto dto, OperationType operationType) {
		// TODO Auto-generated method stub
		if (operationType == OperationType.NEW) {
			//数据新建
            doCompleteInsert(dto);
        }
		else if(operationType == OperationType.MODIFY) {
			//数据修改
			doCompleteModify(dto);
		}
		else if(operationType == OperationType.DELETE) {
			//数据删除
			doCompleteDelete(dto);
		}
	}

	private void doCompleteInsert(FeeTradeManagementDto dto) {
		List<FeeTradeDetailDto> feeTradeList = dto.getFeeTradeList();
		for(FeeTradeDetailDto tradeDetail : feeTradeList) {
			//获取交易类金融产品dto
			Map<String, Object> finproduct = restTemplate.postForObject(
							ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/getFinProductByBookSetId",
								tradeDetail.getSubBookSetId(),Map.class);
			//获取本位币币种
			String ccy = restTemplate.postForObject(
							ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/getCcyByBokSetId",
								tradeDetail.getBookSetId(),String.class);
			//金融产品交易表入库
			TrdProductDeal trdProductDeal = new TrdProductDeal();
			//交易编号
			String tradeId = IDGenerator.generate();
			trdProductDeal.setTradeId(tradeId);	
			trdProductDeal.setBusiType(DictCodeList.业务类型_产品费用交易.getValue());	//业务类型
			trdProductDeal.setFinprodId(finproduct.get("FINPRODID").toString());	//金融产品代码
			String feeType = dto.getFeeType();
			if(feeType.indexOf("B") != -1) {
				//一次性费用
				trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_交易费用.getValue()); 		//金融产品类型
			}
			else {
				//产品端费用
				trdProductDeal.setFinprodType(dto.getFeeType()); 		//金融产品类型
			}
			trdProductDeal.setBranch(0L); 		//分支序号
			trdProductDeal.setTradeDate(dto.getPayDate()); 		//交易日
			trdProductDeal.setMdate(DateUtil.addDay(tradeDetail.getFeeEndDate(), 1));		//到期日
			trdProductDeal.setSettleDate(dto.getPayDate());		//交割日
			trdProductDeal.setCcy(StringUtil.isNotEmpty(ccy) ? ccy : "CNY"); 		//币种
			trdProductDeal.setTradeAmt(tradeDetail.getPayAmt());	//交易金额
			trdProductDeal.setPs(DictCodeList.交易方向_付费.getValue());		//交易方向
			trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue()); 		//交易状态
			trdProductDeal.setIsClean(tradeDetail.getIsCountOver()); 		//是否结清
			trdProductDeal.setRegistOrg(dto.getRegistOrg()); 		//登记托管机构
			trdProductDealMapper.insertSelective(trdProductDeal);
			//金融产品交易附属表入库
			TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
			trdProductDealAdd.setTradeId(tradeId);
			trdProductDealAdd.setPortfolioId(finproduct.get("PORTFOLIOID").toString());
			trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue()); 		//生成方式
			trdProductDealAddMapper.insertSelective(trdProductDealAdd);
			//交易费用明细表入库
			TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
			trdTradeFeeDetail.setTradeId(tradeId); 		//交易编号
			trdTradeFeeDetail.setFeeId(dto.getFeeType());		//一次性费用类型
			trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_否.getValue()); 	//是否随交易支付
			trdTradeFeeDetail.setFeeAmt(tradeDetail.getPayAmt());	//费用金额
			trdTradeFeeDetail.setFeeId(dto.getFeeType());	//费用代码
			trdTradeFeeDetail.setFeeType(dto.getFeeType());
			trdTradeFeeDetail.setIsPrepaid(tradeDetail.getIsNeedTan()); 	//是否待摊
			trdTradeFeeDetailMapper.insertSelective(trdTradeFeeDetail);
			//入账信息表
			InterfProductDealBookCommonDto interfProductDealBookCommonDto = interfCommonService.dealAccounting(trdProductDeal, finproduct.get("FINPRODID").toString());
			bokDealAccountingMapper.insertSelective(interfProductDealBookCommonDto.getBokDealAccounting());
			bokDealAccountingDetailMapper.insertSelective(interfProductDealBookCommonDto.getBokDealAccountingDetail());
			interfProductDealBookCommonDto.getBokDetailId().setBranch(0L);
			bokDetailIdMapper.deleteByPrimaryKey(interfProductDealBookCommonDto.getBokDetailId());
			bokDetailIdMapper.insertSelective(interfProductDealBookCommonDto.getBokDetailId());
			//结算指令信息
			interfCommonService.instructionGenerate(trdProductDeal, finproduct.get("FINPRODID").toString(), 
					finproduct.get("FINPRODTYPE").toString(), null);
		}
	}
	
	private void doCompleteModify(FeeTradeManagementDto dto) {
		List<FeeTradeDetailDto> feeTradeList = dto.getFeeTradeList();
		FeeTradeDetailDto tradeDetail = feeTradeList.get(0);
		BokBookset bokSet = bokBooksetMapper.selectByPrimaryKey(tradeDetail.getBookSetId());
		//金融产品交易表
		TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(dto.getTradeId());
		trdProductDeal.setTradeDate(dto.getPayDate());
		trdProductDeal.setMdate(tradeDetail.getFeeEndDate());
		trdProductDeal.setSettleDate(dto.getPayDate());
		trdProductDeal.setCcy(bokSet.getStandardCcy());
		trdProductDeal.setTradeAmt(tradeDetail.getPayAmt());
		trdProductDeal.setRegistOrg(dto.getRegistOrg());
		trdProductDealMapper.updateByPrimaryKey(trdProductDeal);
		//交易费用明细表
		TrdTradeFeeDetailKey trdTradeFeeDetailKey = new TrdTradeFeeDetailKey();
		trdTradeFeeDetailKey.setTradeId(dto.getTradeId());
		trdTradeFeeDetailKey.setFeeType(dto.getFeeType());
		TrdTradeFeeDetail trdTradeFeeDetail = trdTradeFeeDetailMapper.selectByPrimaryKey(trdTradeFeeDetailKey);
		trdTradeFeeDetail.setFeeAmt(tradeDetail.getPayAmt());
		trdTradeFeeDetail.setIsPrepaid(tradeDetail.getIsNeedTan());
		trdTradeFeeDetailMapper.updateByPrimaryKey(trdTradeFeeDetail);
		//入账信息表
		Map<String, Object> bokDealMap = new HashMap<>();
		bokDealMap.put("tradeId", dto.getTradeId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bokDealMap.put("payDate", sdf.format(dto.getPayDate()));
		restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/updateBokDealAccounting",
					bokDealMap,Integer.class);
		//指令表
		Map<String, Object> stlOrderMap = new HashMap<>();
		stlOrderMap.put("tradeId", dto.getTradeId());
		stlOrderMap.put("payDate", sdf.format(dto.getPayDate()));
		stlOrderMap.put("feeAmt", tradeDetail.getPayAmt());
		stlOrderMap.put("bccy", bokSet.getStandardCcy());
		restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/updateStlOrder",
				stlOrderMap,Integer.class);
	}
	
	private void doCompleteDelete(FeeTradeManagementDto dto) {
		//金融产品交易表
		trdProductDealMapper.deleteByPrimaryKey(dto.getTradeId());
		//金融产品交易附属表
		trdProductDealAddMapper.deleteByPrimaryKey(dto.getTradeId());
		//交易费用明细表
		TrdTradeFeeDetailKey trdTradeFeeDetailKey = new TrdTradeFeeDetailKey();
		trdTradeFeeDetailKey.setTradeId(dto.getTradeId());
		trdTradeFeeDetailKey.setFeeType(dto.getFeeType());
		trdTradeFeeDetailMapper.deleteByPrimaryKey(trdTradeFeeDetailKey);
		//入账信息表
		Map<String, Object> bokDealMap = new HashMap<>();
		bokDealMap.put("tradeId", dto.getTradeId());
		restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/deleteBokDealAccounting",
					bokDealMap,Integer.class);
		//指令表
		Map<String, Object> stlOrderMap = new HashMap<>();
		stlOrderMap.put("tradeId", dto.getTradeId());
		restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/deleteStlOrder",
					bokDealMap,Integer.class);
	}
	
	public List<SelectOptionDto> getBookSetList(Map<String, Object> params){
		return restTemplate.postForList(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/getBookSetList"
	            	, params, SelectOptionDto.class);
	}
	/**
	 * 理论费用查询
	 * @param params
	 * @return
	 */
	public BigDecimal getShouldPayAmt(Map<String, Object> params){
		//基础科目性质
		String baseSubjectNature = "";
		//if(支付信息.费用类型 == 结算手续费 && 支付信息.登记托管机构不为空)																			
		if(DictCodeListExt1.一次性费用类型_结算手续费.getValue().equals(params.get("feeType")) && 
				StringUtil.isNotEmpty(params.get("registerOrg"))) {
			if(DictCodeList.登记托管机构_上海清算所.getValue().equals(params.get("registerOrg"))) {
				baseSubjectNature = DictCodeBaseSubjectNatrue.应付上清所结算手续费.getValue();
			}
			else if(DictCodeList.登记托管机构_中央国债登记结算有限责任公司.getValue().equals(params.get("registerOrg"))) {
				baseSubjectNature = DictCodeBaseSubjectNatrue.应付中债登结算手续费.getValue();
			}
			else if(DictCodeList.登记托管机构_中国证券登记结算有限责任公司.getValue().equals(params.get("registerOrg"))) {
				baseSubjectNature = DictCodeBaseSubjectNatrue.应付中登结算手续费.getValue();
			}
			else {
				baseSubjectNature = getBaseSubjectNature(params.get("feeType").toString());
			}
		}
		else {
			baseSubjectNature = getBaseSubjectNature(params.get("feeType").toString());
		}
		params.put("baseSubjectNature", baseSubjectNature);
		//获取账务明细代码
		params.put("busiDetailType", DictCodeListExt1.金融产品类型_理财产品.getValue());
		String bokDetailId = getBokDetailId(params);
		params.put("bokDetailId", bokDetailId);		//账务明细代码
		return restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/getShouldPayAmt"
	            	, params, BigDecimal.class);
	}
	/**
	 * 基础科目性质查询
	 */
	private String getBaseSubjectNature(String feeType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("feeType", feeType);		//费用类型
		map.put("PS1", DictCodeList.交易方向_付费.getValue());		//交易方向
		map.put("PS2", DictCodeList.交易方向_产品付费.getValue());		//交易方向
		return restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/getBaseSubjectNature"
	            	, map, String.class);
	}
	
	/**
	 * 账务明细代码查询
	 */
	private String getBokDetailId(Map<String, Object> params) {
		return restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/getBokDetailId"
	            	, params, String.class);
	}
	
	public List<SelectOptionDto> getSubBookSetList(Map<String, Object> params){
		params.put("busiDetailType", DictCodeListExt1.金融产品类型_理财产品.getValue());
		return restTemplate.postForList(
				ServiceApplication.VALUATION_SERVICE_NAME + "/FeeTradeManagement/getSubBookSetList"
	            	, params, SelectOptionDto.class);
	}
}
