package com.jixu.api.service;

import com.jixu.common.type.ResultContainer;

import java.util.Date;
import java.util.List;

/**
 * @auth 何阳阳
 * @date 2021/7/9
 */
public interface JobsService {
    /**
     *
     * @param ports	投资组合列表
     * @param ledgerDate	做账日期
     * @param jobsSteps		需要跑账的步骤, 自动化, 关交易, 日记账,计算净值,关总账。按照顺序传入，如果顺序错误，会返回错误结果
     * @param dailyJobIDs	如果前面配了日记账，这里需要传入跑哪些日记账
     * @param redoFlag		如果已经有了凭证，是否需要撤账
     * @return
     */
    ResultContainer executeAppointedJobs(List<String> ports, Date ledgerDate, int[] jobsSteps, int[] dailyJobIDs, boolean redoFlag);

}
