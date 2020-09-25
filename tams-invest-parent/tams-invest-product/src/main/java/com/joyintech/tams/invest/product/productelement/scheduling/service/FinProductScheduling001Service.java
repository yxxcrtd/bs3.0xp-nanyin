/*
 * 文件名：FinProductScheduling001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.scheduling.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.scheduling.dto.PrdIssueScheduleDto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 产品排期管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月6日
 * @see FinProductScheduling001Service
 * @since 1.0
 */
@Service
public class FinProductScheduling001Service extends CommonExportService<PrdIssueScheduleDto>
		implements BaseProcessService<PrdIssueScheduleDto> {

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void doComplete(PrdIssueScheduleDto arg0, OperationType arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询排期所有数据
	 * 
	 * @param map
	 *            参数map
	 * @return 排期所有数据
	 * @see
	 * @since 1.0
	 */
	public Page<PrdIssueScheduleDto> selectForPage(Map<String, Object> map) {
		Page<PrdIssueScheduleDto> page = restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductScheduling/selectForPage", map,
				PrdIssueScheduleDto.class);
		List<PrdIssueScheduleDto> list = page.getRoot();
		if (!StringUtil.isEmptyList(list)) {
			list.stream().forEach(entity -> {
				entity.setIssueMode(SysDataUtil.getNameFromDataDict(DictCode.排期发行方式, entity.getIssueMode()));
			});
		}
		return page;
	}

	/**
	 * 根据产品代码模糊查询联系下拉框
	 * 
	 * @param param
	 *            参数map
	 * @return 产品代码和简称
	 * @see
	 * @since 1.0
	 */
	public List<SuggestDto> querySchedulingProdInfo(String param) {
		return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/FinProductScheduling/querySchedulingProdInfo?param=" + param, SuggestDto.class);
	}

	/**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/product/productelement/scheduling/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PrdIssueScheduleDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

	/**
	 * 查询产品登记编码是否已存在
	 * 
	 * @param finprodId
	 *            产品登记编码
	 * @return 是否已存在
	 * @see
	 * @since 1.0
	 */
	public int checkRegistCodeExists(String finprodId) {
		return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/FinProductScheduling/checkRegistCodeExists?finprodId=" + finprodId, Integer.class);
	}
}
