package com.joyintech.tams.report.system.chbfeedbackfilemanagement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.component.svrreport.chb.dto.ChbInfoReportDto;
import com.joyintech.component.svrreport.chb.dto.ChbSubmitStatusDto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagementDto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileMatchDetailDto;

@Repository
public interface ChbFeedbackFileManagementMapper {

    /**
     * 查询中债反馈问列表
     * @param params
     * @return
     */
    List<ChbFeedbackFileManagementDto> queryChbFeedbackFileList(Map<String, Object> params);

    /**
     * 查询资产估值信息登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbAssetValSubStatus(List<String> registSeqs);

    /**
     * 查询产品发行登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbPrdIssueSubStatus(List<String> registSeqs);

    /**
     * 查询产品申报登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbPrdDeclareSubStatus(List<String> registSeqs);

    /**
     * 查询募集总量登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbPrdRaisetotSubStatus(List<String> registSeqs);

    /**
     * 查询产品存续期登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbPrdDealSubStatus(List<String> registSeqs);

    /**
     * 查询产品终止登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbPrdEndSubStatus(List<String> registSeqs);

    /**
     * 查询资管计划底层持仓信息登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbBottomAstPosSubStatus(List<String> registSeqs);

    /**
     * 查询资产交易信息登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbInvestTradeSubStatus(List<String> registSeqs);

    /**
     * 查询资产要素信息登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbAssetInfoSubStatus(List<String> registSeqs);

    /**
     * 查询从业人员登记状态信息
     * @param registSeqs
     * @return
     */
    List<ChbFeedbackFileMatchDetailDto> queryChbEmployeeInfoSubStatus(List<String> registSeqs);

    int delChbInfoReportByMesNo(@Param("repMesNo")String repMesNo);

    int mergeInfoChbInfoReport(@Param("newDataList")List<ChbInfoReportDto> newDataList);

    int mergeInfoChbSubmitStatusTwo(@Param("newDataList")List<ChbSubmitStatusDto> newDataList);
}
