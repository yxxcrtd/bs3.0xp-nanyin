package com.joyintech.tams.report.system.reportstatusinfo.service;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagementDto;
import com.joyintech.tams.report.system.reportstatusinfo.dao.ReportStatusInfoMapper;

/**
 * @program: tams-svreport
 * @description:
 * @author: wanghai
 * @create: 2021-05-10 11:28
 **/
@Service("reportStatusInfoService")
public class ReportStatusInfoService extends BaseService {

    @Autowired
    private ReportStatusInfoMapper reportStatusInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(ReportStatusInfoService.class);

    /**
     * 页面查询列表
     * @param param
     * @return
     */
    public Page<ChbFeedbackFileManagementDto> findPage(int pagenum, int length, Map<String, Object> param) {
        return queryForPage(pagenum, length, ()->reportStatusInfoMapper.queryReportStatusInfoList(param));
    }

}
