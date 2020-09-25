package com.joyintech.tams.invest.entrust.etrbondbiddingresult.service;

import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrbondbiddingresult.dto.Etrbondbiddingresult001Dto;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.tams.invest.entrust.etrfund.dto.EtrFundDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EtrbondbiddingresultService extends CommonExportService<Etrbondbiddingresult001Dto> implements BaseProcessService<Etrbondbiddingresult001Dto> {


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
		return restTemplate.postForPage(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/Etrbondbiddingresult/findPage", param,
				Etrbondbiddingresult001Dto.class);
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
}
