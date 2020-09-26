/*
 * 文件名：ValSharePriceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.valshareprice.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.basedata.marketdata.valshareprice.dto.ValSharePriceDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 股票行情Service
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ValSharePriceService
 * @since 1.0
 */
@Service
public class ValSharePriceService extends CommonExportService<ValSharePriceDto> implements BaseProcessService<ValSharePriceDto>{

    
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<ValSharePriceDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValSharePrice/selectForPage",
            pageParam,ValSharePriceDto.class);
    }
    
    /**
     * 获取下拉选资产代码
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getFinprodIdForAdd(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                                       + "/ValSharePrice/getFinprodIdForAdd",map,SelectOptionDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/valuation/basedata/marketdata/valshareprice/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * 
     * 导出数据查询
     */
    @Override
    public Page<ValSharePriceDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 工作流（留痕处理进行的操作）
     */
    @Override
    public void doComplete(ValSharePriceDto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValSharePrice/doComplete/" + type, entity, Integer.class);
    }

    /**
     * 通过id获取最近指定日期的股票行情信息   
     * @param pageParam
     * @return
     */
	public ValSharePriceDto getLatelyShareById(Map<String, Object> pageParam) {
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValSharePrice/getLatelyShareById",pageParam,ValSharePriceDto.class);
}

	/**
	 * 获取所有查询结果list
	 * @param pageParam
	 * @return
	 */
	public List<ValSharePriceDto> selectAllList(Map<String, Object> pageParam) {
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValSharePrice/selectAllList",pageParam,ValSharePriceDto.class);
	}

}
