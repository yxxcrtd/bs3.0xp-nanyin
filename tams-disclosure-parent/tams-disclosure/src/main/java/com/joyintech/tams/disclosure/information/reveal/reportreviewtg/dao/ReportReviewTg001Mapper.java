package com.joyintech.tams.disclosure.information.reveal.reportreviewtg.dao;

import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportReviewTg001Mapper {
    List<ReportReview001Dto> findPage(Map<String, Object> params);

    int updateReviewInfo(Map<String, Object> paraMap);

    int updateReturnStatus(Map<String, Object> paraMap);

}
