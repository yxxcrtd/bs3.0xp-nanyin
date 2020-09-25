package com.joyintech.tams.invest.riskredis.quota.interlog.service;

import java.util.Date;
import java.util.Map;

import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.interlog.dto.RepInterLogDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;

/**
 * 接口日志管理
 * @author 郑俊平
 * @version 1.0 2019年9月4日
 * @see RepInterLogService
 * @since 1.0
 */
@Service
public class RepInterLogService extends CommonExportService<RepInterLogDto> {
    /**
     * 查询界面询事件
     *
     * @param map 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<RepInterLogDto> selectForPage(Map<String, Object> map){
    	
        Page<RepInterLogDto> page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepInterLog/findPage",
                map, RepInterLogDto.class);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	exportParam.config(
				 "/com/joyintech/tams/invest/riskredis/quota/interlog/conf/export_conf.xml").dataSource(
		 	                DataSourceType.MASERT);
    	
    
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("supDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepInterLogDto> selectPageData(Map<String, Object> selectParam) {
        Page<RepInterLogDto> pageList=new Page<RepInterLogDto>();
		pageList=selectForPage(selectParam);
		for(RepInterLogDto dto:pageList.getRoot()) {
			if(null!=dto.getIpAddress())
				dto.setIpAddress(splice(dto.getIpAddress(),dto.getPort()));
		}
		return pageList;
		
    }
    
    private String splice(String ipAddress,Integer port){
    	return ipAddress+":"+port;
    }

    /**
     * 修改或查看界面
     *
     * @param uuid 参数代码
     * @return 结果
     * @see
     * @since 1.0
     */
    public RepInterLogDto selectInfo(String uuid) {
        return restTemplate.getForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RepInterLog/selectInfo?uuid="+
                uuid, RepInterLogDto.class);
    }
    
}
