package com.joyintech.tams.disclosure.information.reveal.reportreview.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportReview001Mapper {
    List<SelectOptionDto> getReportNameList(Map<String, Object> params);

    List<ReportReview001Dto>findPage(Map<String, Object> params);

    int updateReviewStatus(Map<String, Object> paraMap);

    int updateReviewInfo(Map<String, Object> paraMap);

    List<SelectOptionDto>getTgGroupList(Map<String, Object> paraMap);

    int updateReviewTgInfo(Map<String, Object> paraMap);
}
