package com.joyintech.tams.invest.entrust.etrbondbiddingresult.service;

import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.EtrCashPositionFlowMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.group.impl.dao.BasePtlCashPositionInfoMapper;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.entrust.etrbondbiddingresult.dto.Etrbondbiddingresult001Dto;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EtrbondbiddingresultService extends CommonExportService<Etrbondbiddingresult001Dto> implements BaseProcessService<Etrbondbiddingresult001Dto> {

	@Autowired
	private EtrCommonCashPositionDealService etrCommonCashPositionDealService;

	@Autowired
	private BasePtlCashPositionInfoMapper basePtlCashPositionInfoMapper;

	@Autowired
	private EtrCashPositionFlowMapper etrCashPositionFlowMapper;
	/**
	 * 基金委托查询 <功能详细描述>
	 *
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<Etrbondbiddingresult001Dto> selectForPage(Map<String, Object> param) {
		User user = SecurityUtil.getLoginUser();
		//配置单位 edit by yeming 20210802 start
		Page<Etrbondbiddingresult001Dto> page = restTemplate.postForPage(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/Etrbondbiddingresult/findPage", param,
				Etrbondbiddingresult001Dto.class);
		for(Etrbondbiddingresult001Dto dto:page.getRoot()){
			if(dto.getBidPrinAmt() != null) {
				dto.setBidPrinAmt(dto.getBidPrinAmt().divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP));//转成万元
			}
			if(dto.getWinPrinAmt() != null) {
				dto.setWinPrinAmt(dto.getWinPrinAmt().divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP));//转成万元
			}
		}
		return page;
		//配置单位 edit by yeming 20210802 end
//		return restTemplate.postForPage(
//				ServiceApplication.ENTRUST_SERVICE_NAME + "/Etrbondbiddingresult/findPage", param,
//				Etrbondbiddingresult001Dto.class);
	}

	public List<FinProduct> findFinProductByOFinprodId(Map<String, Object> param) {
		return restTemplate.postForList(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/Etrbondbiddingresult/findFinProductByOFinprodId", param,
				FinProduct.class);
	}

	@Override
	public void doComplete(Etrbondbiddingresult001Dto entity, OperationType type) {

	}

	@Override
	public String start(ProcessParameter<Etrbondbiddingresult001Dto> params, Etrbondbiddingresult001Dto... entities) throws WorkFlowException {
		return null;
	}

	@Override
	public Etrbondbiddingresult001Dto getBussinessEntity(ProcessMark processMark) {
		return null;
	}

	@Override
	public Etrbondbiddingresult001Dto getBussinessEntityByWorkflowId(String workflowId) {
		return null;
	}

	@Override
	public String start(WorkFlowParameter<Etrbondbiddingresult001Dto> params, Etrbondbiddingresult001Dto... entities) throws WorkFlowException {
		return null;
	}

	@Override
	public void doInsert(Etrbondbiddingresult001Dto entity, OperationType type) {

	}

	@Override
	public void doUpdate(Etrbondbiddingresult001Dto entity) {

	}

	@Override
	public void doDelete(String workflowId) {

	}

	@Override
	public ProcessMark getBusinessInfo(String workflowId) {
		return null;
	}

	@Override
	public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
		return null;
	}

	@Override
	public BaseProcessService<Etrbondbiddingresult001Dto> getMyBusinessService() {
		return null;
	}

	@Override
	public String checkForSubmit(FlowEntity entity, boolean isMutiApproval) {
		return null;
	}

	@Override
	public boolean checkBusinessDataInFlow(Etrbondbiddingresult001Dto dto) {
		return false;
	}

	@Override
	public Etrbondbiddingresult001Dto getBussinessEntityByPk(ProcessMark param) {
		return null;
	}

	/**
	 * 导出
	 *
	 * @param exportParam 导出信息
	 * @see
	 * @since 1.0
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/entrust/etrbondbiddingresult/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Page<Etrbondbiddingresult001Dto> selectPageData(Map<String, Object> selectParam) {
//		return selectForPage(selectParam);
//	}


	@Override
	public Page<Etrbondbiddingresult001Dto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}

	@Override
	public void setCellValue(String beanName, Cell cell, String propName, Object value) {
		if(StringUtil.equals(propName,"transactioncom")){
			cell.setCellValue(value+"%");
		}
	}

	public Integer checkFrozenPosition(Map<String, Object> map){
		return restTemplate.postForObject(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/Etrbondbiddingresult/checkFrozenPosition", map,
				Integer.class);
	}

	public BigDecimal getHasFrozenPosition(Map<String, Object> map){
		EntrustCommonDto entrustCommonDto = etrCommonCashPositionDealService.findEntrust((String)map.get("entrustId"));
		String acctId = etrCommonCashPositionDealService.select组合托管户(entrustCommonDto.getEtrEntrust().getPortfolioId(),
				entrustCommonDto.getEtrEntrust().getCcy(),
				DictCodeListExt2.资金账户类型_托管户.getValue());
		PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
		ptlCashPositionFlowDto.setPortfolioId(entrustCommonDto.getEtrEntrust().getPortfolioId());
		ptlCashPositionFlowDto.setCcy(entrustCommonDto.getEtrEntrust().getCcy());
		ptlCashPositionFlowDto.setCdate(entrustCommonDto.getEtrEntrust().getVdate());
		ptlCashPositionFlowDto.setAcctId(acctId);
		ptlCashPositionFlowDto.setOrderId(entrustCommonDto.getEtrEntrust().getEntrustId());
		ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_可用.getValue());//冻结的现金
		//根据ptlCashPositionFlowDto查询出冻结的持仓，用于后面指令持仓反增
		List<String> acctTypeList2 = new ArrayList<String>();
		acctTypeList2.add(DictCodeListExt2.资金账户类型_托管户.getValue());
		ptlCashPositionFlowDto.setAcctType(acctTypeList2);
		ptlCashPositionFlowDto.setIsFrozenPositionFlag(BussinessDictCodeList.是否_是.getValue());
		BigDecimal isFrozenPosition = basePtlCashPositionInfoMapper.selectCashPositionFlow(ptlCashPositionFlowDto);
		return isFrozenPosition;
	}

	public void doManualRelease(Map<String, Object> map) throws ParseException {
		//1.组合可用现金
		//释放组合可用现金开始（删除记录）
		EntrustCommonDto entrustCommonDto = etrCommonCashPositionDealService.findEntrust((String)map.get("entrustId"));
		String acctId = etrCommonCashPositionDealService.select组合托管户(entrustCommonDto.getEtrEntrust().getPortfolioId(),
				entrustCommonDto.getEtrEntrust().getCcy(),
				DictCodeListExt2.资金账户类型_托管户.getValue());
		PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
		ptlCashPositionFlowDto.setPortfolioId(entrustCommonDto.getEtrEntrust().getPortfolioId());
		ptlCashPositionFlowDto.setCcy(entrustCommonDto.getEtrEntrust().getCcy());
		ptlCashPositionFlowDto.setCdate(entrustCommonDto.getEtrEntrust().getVdate());
		ptlCashPositionFlowDto.setAcctId(acctId);
		ptlCashPositionFlowDto.setOrderId(entrustCommonDto.getEtrEntrust().getEntrustId());
		ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_可用.getValue());//冻结的现金
		//根据ptlCashPositionFlowDto查询出冻结的持仓，用于后面指令持仓反增
		List<String> acctTypeList2 = new ArrayList<String>();
		acctTypeList2.add(DictCodeListExt2.资金账户类型_托管户.getValue());
		ptlCashPositionFlowDto.setAcctType(acctTypeList2);
		ptlCashPositionFlowDto.setIsFrozenPositionFlag(BussinessDictCodeList.是否_是.getValue());
//		BigDecimal isFrozenPosition = basePtlCashPositionInfoMapper.selectCashPositionFlow(ptlCashPositionFlowDto);
//		BigDecimal amount = basePtlCashPositionInfoMapper.selectPtlCashPositionFlowAmount(ptlCashPositionFlowDto);
		basePtlCashPositionInfoMapper.deletePtlCashPositionFlow(ptlCashPositionFlowDto);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ptlCashPositionFlowDto.setCdate(new Date((Long)map.get("payDate")));
		basePtlCashPositionInfoMapper.deletePtlCashPositionFlow(ptlCashPositionFlowDto);
//		if(amount!=null){
//			Map<String,Object> positionMap = new HashMap<>();
//			positionMap.put("minDate", DateUtil.format(entrustCommonDto.getEtrEntrust().getVdate()));
//			positionMap.put("maxDate",DateUtil.format(entrustCommonDto.getEtrEntrust().getVdate()));
//			positionMap.put("amount",amount.negate());
//			positionMap.put("hodingType",DictCodeList.持仓类型_实际.getValue());
//			positionMap.put("portfolioId",entrustCommonDto.getEtrEntrust().getPortfolioId());
//			positionMap.put("acctId",acctId);
//			positionMap.put("ccy",entrustCommonDto.getEtrEntrust().getCcy());
//			positionMap.put("orderId",entrustCommonDto.getEtrEntrust().getEntrustId());
//			basePtlCashPositionInfoMapper.updatePosition(positionMap);
//		}
		//释放组合可用现金结束
		//2.指令可用现金
		//释放指令可用现金开始（反塞入）
//		if(isFrozenPosition != null && isFrozenPosition.compareTo(BigDecimal.ZERO) != 0){
//			EtrCashPositionFlowDto dto = new EtrCashPositionFlowDto();
//			BeanUtil.copyPropertiesIgnoreNull(dto, ptlCashPositionFlowDto);
//			dto.setSeqno(IDGenerator.generate());
//			dto.setOffsetFlag(DictCodeList.冲正标识_正常交易.getValue());
//			dto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
//			dto.setAmount(ArithmeticUtil.multiply(isFrozenPosition,new BigDecimal("-1")));
//			EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
//			List<EtrCashPositionFlowDto> etrCashPositionFlows = new ArrayList<>();
//			etrCashPositionFlows.add(dto);
//			etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);
//			EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
////			etrCashPositionFlowMapper.insertSelective(dto);
//		}
		//释放指令可用现金结束
	}
}
