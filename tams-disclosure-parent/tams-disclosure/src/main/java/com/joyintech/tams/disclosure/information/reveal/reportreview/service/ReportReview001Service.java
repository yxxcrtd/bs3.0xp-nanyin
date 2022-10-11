package com.joyintech.tams.disclosure.information.reveal.reportreview.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dao.ReportReview001Mapper;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportReview001Service extends CommonExportService<ReportReview001Dto> {
    @Autowired
    private ReportReview001Mapper reportReview001Mapper;

    public Page<SelectOptionDto> getReportNameList(Map<String, Object> param){
        Page<SelectOptionDto> page = queryForPageForSelectReturn(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> reportReview001Mapper.getReportNameList(param));
        return page;
    }

    public Page<ReportReview001Dto> findPage(Map<String, Object> param){
        String loginUser= LoginUserUtil.getUserName();
        param.put("loginUser",loginUser);
        return  queryForPageForSelectReturn(()->reportReview001Mapper.findPage(param));
    }
}
