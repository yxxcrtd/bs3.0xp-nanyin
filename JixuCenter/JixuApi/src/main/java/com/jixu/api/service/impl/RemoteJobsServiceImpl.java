package com.jixu.api.service.impl;

import com.jixu.api.service.JobsService;
import com.jixu.api.util.RestUrlUtil;
import com.jixu.common.apiconst.ApiPathConst;
import com.jixu.common.type.ResultContainer;
import com.joyintech.framework.JoyinRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.List;

/**
 * @auth 何阳阳
 * @date 2021/7/9
 */
@Lazy
@Service
public class RemoteJobsServiceImpl implements JobsService {

    @Autowired
    private JoyinRestTemplate restTemplate;

    @Override
    public ResultContainer executeAppointedJobs(List<String> ports, Date ledgerDate, int[] jobsSteps, int[] dailyJobIDs, boolean redoFlag) {
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("ledgerDate", ledgerDate);
        valueMap.add("redoFlag", redoFlag);
        for (String s : ports) {
            valueMap.add("ports[]", s);
        }
        for (int step : jobsSteps) {
            valueMap.add("jobsSteps[]", step);
        }
        for (int jobId : dailyJobIDs) {
            valueMap.add("dailyJobIDs[]", jobId);
        }
        return restTemplate.postForObject(RestUrlUtil.apiUrl(ApiPathConst.EXECUTE_APPOINTED_JOBS), valueMap, ResultContainer.class);
    }
}
