package com.joyintech.tams.report.submitmanagement.agentprdinforegist.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmanagement.agentprdinforegist.dto.AgentPrdInfoRegistDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbAgentPrdSubMapper {
    /**
     * 查询界面上条件
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<AgentPrdInfoRegistDto> queryForRegistPage(Map<String,Object> param);
    List<AgentPrdInfoRegistDto> searchPage(Map<String,Object> map);

    int insertIntoHis(@Param("registList")List<AgentPrdInfoRegistDto> registList);

    int deleteLastSubmitRegist(@Param("registList")List<AgentPrdInfoRegistDto> registList);

    void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<AgentPrdInfoRegistDto> registList);

    Integer cancelMannultRegist(List<AgentPrdInfoRegistDto> list);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<AgentPrdInfoRegistDto> registList);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void deleteReportDataHis(@Param("registList")List<AgentPrdInfoRegistDto> registList);


    /**
     * 查询
     * @param param
     * @return
     */
    List<AgentPrdInfoRegistDto> setDataList(Map<String, Object> param);

    /**
     * 代理销售机构代码下拉框
     */
    List<SuggestDto> getAgentInstitutionCode(Map<String, Object> map);

    /**
     * 产品代码下拉框
     */
    List<SuggestDto> getProdId(Map<String, Object> map);

    /**
     * 产品登记编码下拉框
     */
    List<SuggestDto> getProdRegistCode(Map<String, Object> map);

}