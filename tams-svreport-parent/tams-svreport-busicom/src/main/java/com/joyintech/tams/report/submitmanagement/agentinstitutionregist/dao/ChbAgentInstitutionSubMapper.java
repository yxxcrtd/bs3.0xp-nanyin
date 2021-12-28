package com.joyintech.tams.report.submitmanagement.agentinstitutionregist.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmanagement.agentinstitutionregist.dto.AgentInstitutionRegistDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbAgentInstitutionSubMapper {
    /**
     * 查询界面上条件
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<AgentInstitutionRegistDto> queryForRegistPage(Map<String,Object> param);
    List<AgentInstitutionRegistDto> searchPage(Map<String,Object> map);

    int insertIntoHis(@Param("registList")List<AgentInstitutionRegistDto> registList);

    int deleteLastSubmitRegist(@Param("registList")List<AgentInstitutionRegistDto> registList);

    void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<AgentInstitutionRegistDto> registList);

    Integer cancelMannultRegist(List<AgentInstitutionRegistDto> list);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<AgentInstitutionRegistDto> registList);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void deleteReportDataHis(@Param("registList")List<AgentInstitutionRegistDto> registList);

    /**
     * 查询
     * @param param
     * @return
     */
    List<AgentInstitutionRegistDto> setDataList(Map<String, Object> param);

    /**
     * 代理销售机构代码下拉框
     */
    List<SuggestDto> getAgentInstitutionCode(Map<String, Object> map);

}