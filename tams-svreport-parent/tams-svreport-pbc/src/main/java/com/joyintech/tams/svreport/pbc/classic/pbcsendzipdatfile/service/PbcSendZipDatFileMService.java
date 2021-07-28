package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcSendZipDatFileMService
 * @since 1.0
 */
@Service
public class PbcSendZipDatFileMService extends BaseService<PbcSendZipDatFile>{
 
	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<PbcMonthReport> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcSendZipDatFile/findPage",param,
        		PbcMonthReport.class);
	}


    /**
     *
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    public PbcMonthReport searchPageBysubmitDate(Map<String, Object> param) {
        return restTemplate.postForObject(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcSendZipDatFile/searchPageBysubmitDate",param,
        		PbcMonthReport.class);
    }

	

}
