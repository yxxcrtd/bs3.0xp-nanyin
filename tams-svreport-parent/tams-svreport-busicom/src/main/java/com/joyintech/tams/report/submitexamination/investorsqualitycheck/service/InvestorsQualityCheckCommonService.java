package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class InvestorsQualityCheckCommonService extends BaseService<InvestorsQualityCheck001Dto> {

    /**
     * 查询数据
     * @return 数据结果
     */
    public abstract Page<InvestorsQualityCheck001Dto> queryPage(Map<String, Object> param);


    /**
     * 汇总计算
     * @return 左侧汇总计算结果值
     */
    public abstract InvestorsQualityCheck001Dto cntHGL(Map<String, Object> param);

    /**
     * 设置参数查询
     * @param param 入参
     */
    public abstract void setParamComm(Map<String, Object> param);

    /**
     * 共通方法：查询界面选择的参数处理
     * @param param
     */
    public void dealParamMap(Map<String, Object> param) {
        String orgCode = (String)param.get("orgCode");
        List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
        param.put("orgCodeList", orgCodeList);

        setParamComm(param);
    }

}
