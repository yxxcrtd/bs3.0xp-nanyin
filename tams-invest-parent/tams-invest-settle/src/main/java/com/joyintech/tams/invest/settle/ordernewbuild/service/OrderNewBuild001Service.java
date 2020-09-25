package com.joyintech.tams.invest.settle.ordernewbuild.service;

import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.settle.ordernewbuild.dto.OrderNewBuild001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @author yaozhenguo
 * @version 1.0 2019年10月29日 下午4:28:13
 * @ClassName OrderNewBuild001Service
 * @Description
 */
@Service
public class OrderNewBuild001Service extends CommonExportService<OrderNewBuild001Dto>
		implements BaseProcessService<OrderNewBuild001Dto> {

	@Override
	public void doComplete(OrderNewBuild001Dto entity, OperationType type) {
		if (type == OperationType.REVOKE) {
			String orderStatus = DictCodeListExt1.指令状态_撤销.getValue();
			entity.setOrderStatus(orderStatus);
			restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/orderNewBuild/orderRevoke", entity,
					Integer.class);
		}

	}

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/settle/ordernewbuild/config/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	@Override
	public Page<OrderNewBuild001Dto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}

	public Page<OrderNewBuild001Dto> selectForPage(Map<String, Object> param) {
		if (StringUtil.isNotEmpty(param.get("portfolioId"))) {
			param.put("portfolioId", StringUtil.splitToList(null, param.get("portfolioId").toString()));
		}
		if (StringUtil.isNotEmpty(param.get("prdId"))) {
			param.put("prdId", StringUtil.splitToList(null, param.get("prdId").toString()));
		}
		if (StringUtil.isNotEmpty(param.get("busiType"))) {
			param.put("busiType", StringUtil.splitToList(null, param.get("busiType").toString()));
		}
		if (StringUtil.isNotEmpty(param.get("commonBusiType"))) {
			param.put("commonBusiType", param.get("commonBusiType").toString());
		}else{
			param.put("commonBusiType", DictCodeListExt3.共通业务类型_指令新建.getValue());
		}
		Page<OrderNewBuild001Dto> pageDto = restTemplate.postForPage(
				ServiceApplication.SETTLE_SERVICE_NAME + "/orderNewBuild/selectForPage", param,
				OrderNewBuild001Dto.class);
		return pageDto;
	}

}
