package com.joyintech.tams.invest.settle.ordernewbuild.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.settle.ordernewbuild.dto.OrderNewBuild002Dto;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @author yaozhenguo
 * @version 1.0 2019年10月29日 下午4:28:13
 * @ClassName OrderNewBuild001Service
 * @Description
 */
@Service
public class OrderNewBuild002Service extends CommonExportService<OrderNewBuild002Dto>
		implements BaseProcessService<OrderNewBuild002Dto> {

	/**
	 * 下拉框
	 * 
	 * @param map
	 * @return
	 */
	public List<SelectOptionDto> queryCapacctInfo(Map<String, Object> map) {
		return restTemplate.postForList(ServiceApplication.SETTLE_SERVICE_NAME + "/orderNewBuild/queryCapacctInfo", map,
				SelectOptionDto.class);
	}

	@Override
	public void doComplete(OrderNewBuild002Dto entity, OperationType type) {
		if (type == OperationType.NEW) {
			entity.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
			restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/orderNewBuild/insertOrder", entity,
					Integer.class);
		}
	}

	/**
	 * 根据指令Id查询
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderNewBuild002Dto selectByOrderId(String orderId) {
		OrderNewBuild002Dto orderNewBuild002Dto = restTemplate.getForObject(
				ServiceApplication.SETTLE_SERVICE_NAME + "/orderNewBuild/selectByOrderId?orderId=" + orderId,
				OrderNewBuild002Dto.class);
		List<BussTrdTransferOrderDto> list = new ArrayList<>();
		BussTrdTransferOrderDto bussTrdTransferOrderDto = new BussTrdTransferOrderDto();
		BeanUtil.copyPropertiesIgnoreNull(bussTrdTransferOrderDto, orderNewBuild002Dto);
		bussTrdTransferOrderDto.setPayTypeText(SysDatadictUtil.getNameFromDataDict(DictCode.平安划款类型, bussTrdTransferOrderDto.getPayType()));
		// 付款账号
		if (StringUtil.isNotEmpty(orderNewBuild002Dto.getMyAcctNo())) {
			Map<String, Object> map = new HashMap<>();
			map.put("search", orderNewBuild002Dto.getMyAcctNo());
			List<SelectOptionDto> myAcctNo = this.queryCapacctInfo(map);
			for (SelectOptionDto item : myAcctNo) {
				if (StringUtil.equals(orderNewBuild002Dto.getMyAcctNo(), item.getId())) {
					bussTrdTransferOrderDto.setPayAcctNo(item.getId());
					bussTrdTransferOrderDto.setPayAcctName(item.getText());
					break;
				}
			}
			;
		}
		// 收款账号
		if (StringUtil.isNotEmpty(orderNewBuild002Dto.getCounterAcctNo())) {
			Map<String, Object> map = new HashMap<>();
			map.put("search", orderNewBuild002Dto.getCounterAcctNo());
			List<SelectOptionDto> counterAcctNo = this.queryCapacctInfo(map);
			for (SelectOptionDto item : counterAcctNo) {
				if (StringUtil.equals(orderNewBuild002Dto.getCounterAcctNo(), item.getId())) {
					bussTrdTransferOrderDto.setRecAcctNo(item.getId());
					bussTrdTransferOrderDto.setRecAcctName(item.getText());
					if (item.getObject() != null) {
						bussTrdTransferOrderDto.setRecAcctPoint(item.getObject().toString());
					}
					break;
				}
			}
			;
		}
		list.add(bussTrdTransferOrderDto);
		orderNewBuild002Dto.setBussTrdTransferOrder(list);
		return orderNewBuild002Dto;
	}
}
