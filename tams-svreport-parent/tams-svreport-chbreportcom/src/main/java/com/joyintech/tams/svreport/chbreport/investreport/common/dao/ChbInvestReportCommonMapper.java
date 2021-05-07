package com.joyintech.tams.svreport.chbreport.investreport.common.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.svreport.chbreport.investreport.common.dto.ReportGenerate001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbInvestReportCommonMapper
 * @Description: 报告查询共通
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@Repository
public interface ChbInvestReportCommonMapper {
    /**
     * 查询产品下拉框
     * @param param
     * @return
     */
    List<SelectOptionDto> queryPrdInfoList(Map<String, Object> param);

    /**
     * 查询报告类型
     * @param param
     * @return
     */
    List<RepFtpPath> queryRepFtpPathList(Map<String, Object> param);

    /**
     * 查询是否已经确认的报表
     * @param params
     * @return
     */
    RepReveal selectReprevealByParams(Map<String, Object> params);

    void updateReprevealByParams(Map<String, Object> params);

    /**
     * 查询成立公告
     * @param param
     * @return
     */
    List<ReportGenerate001Dto> queryDetailData01(Map<String, Object> param);

    /**
     * 查询非标投资运作报告
     * @param param
     * @return
     */
    List<ReportGenerate001Dto> queryDetailData02(Map<String, Object> param);

    /**
     * 查询到期公告
     * @param param
     * @return
     */
    List<ReportGenerate001Dto> queryDetailData03(Map<String, Object> param);

    /**
     * 查询产品净值披露
     * @param param
     * @return
     */
    List<ReportGenerate001Dto> queryDetailData05(Map<String, Object> param);

}
