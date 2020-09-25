package com.joyintech.tams.invest.riskredis.quota.medianmanagement.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.medianmanagement.dto.PltMedianManagementDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 指标参数中间值管理
 * @author zhengjunping
 * @version 1.0 2019年11月19日
 * @see PltMedianManagement001Service
 * @since 1.0
 */
@Service
public class PltMedianManagement001Service extends CommonExportService<PltMedianManagementDto> {


    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<PltMedianManagementDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/PltMedianManagement/findPage",
            pageParam, PltMedianManagementDto.class);
    }
    
    public Page<SelectOptionDto> selectMedianManagement(Map<String, Object> map){
    	Page<SelectOptionDto> page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/PltMedianManagement/selectMedianManagement",
                map, SelectOptionDto.class);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/riskredis/quota/medianmanagement/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PltMedianManagementDto> selectPageData(Map<String, Object> map) {
		Object paraCode = map.get("paraCode");
		if (null != paraCode) {
			List<String> paraCodeList = StringUtil.splitToList(GlobalConstant.COMMA, String.valueOf(paraCode));
			map.put("paraCode", paraCodeList);
		}
		Object blank = map.get("blank");
		if(blank != null || blank != ""){
			map.put("blank", blank);
		}
		Object supDate = map.get("supDate");
		Object startSupDate = (supDate).toString().substring(0,10);
		map.put("startSupDate", startSupDate);
		Object endSupDate = (supDate).toString().substring(13,23);
		map.put("endSupDate", endSupDate);
        Page<PltMedianManagementDto> pageList=new Page<PltMedianManagementDto>();
		pageList=selectForPage(map);
		return pageList;
		
    }
}
