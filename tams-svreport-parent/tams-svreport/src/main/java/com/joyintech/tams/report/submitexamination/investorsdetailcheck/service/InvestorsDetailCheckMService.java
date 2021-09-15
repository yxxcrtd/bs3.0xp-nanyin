package com.joyintech.tams.report.submitexamination.investorsdetailcheck.service;

import java.util.Map;

import com.joyintech.tams.report.submitexamination.investorsdetailcheck.dto.InvestorsDetailCheck001Dto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;

/**
 * 投资者明细核查画面
 */
@Service
public class InvestorsDetailCheckMService extends CommonExportService<InvestorsDetailCheck001Dto>{

    /**
     * 查询列表
     *
     * @return Page
     */
    public Page<InvestorsDetailCheck001Dto> searchTable(Map<String, Object> param){
    	return restTemplate.postForPage(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestorsDetailCheck/searchTable",param,
				InvestorsDetailCheck001Dto.class);
    }

}
