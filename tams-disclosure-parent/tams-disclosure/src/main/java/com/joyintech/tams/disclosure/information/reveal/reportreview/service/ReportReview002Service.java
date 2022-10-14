package com.joyintech.tams.disclosure.information.reveal.reportreview.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dao.ReportReview001Mapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportReview002Service extends BaseService {
    @Autowired
    private ReportReview001Mapper reportReview001Mapper;

    public Page<SelectOptionDto> findPage(Map<String, Object> param){
        Page<SelectOptionDto>page = new Page<SelectOptionDto>();
        List<SelectOptionDto> userList=reportReview001Mapper.getTgGroupList(param);
        if(!CollectionUtils.isEmpty(userList)){
            page.setRoot(userList);
        }
        return page;
    }
}
