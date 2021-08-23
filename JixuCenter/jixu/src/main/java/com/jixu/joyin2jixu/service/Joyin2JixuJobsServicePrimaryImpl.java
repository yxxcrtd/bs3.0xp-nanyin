package com.jixu.joyin2jixu.service;

import com.jixu.api.service.JobsService;
import com.jixu.common.type.ResultContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @auth 何阳阳
 * @date 2021/7/28
 */
@Lazy
@Service
@Primary
public class Joyin2JixuJobsServicePrimaryImpl implements JobsService {

    @Autowired
    private com.jixu.mvc.control.service.JobsService jobsService;

    @Override
    public ResultContainer executeAppointedJobs(List<String> ports, Date ledgerDate, int[] jobsSteps, int[] dailyJobIDs, boolean redoFlag) {
        return jobsService.executeAppointedJobs(ports, ledgerDate, jobsSteps, dailyJobIDs, redoFlag);
    }
}
