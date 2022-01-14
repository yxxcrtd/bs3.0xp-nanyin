package com.joyintech.tams.report.submitmanagement.salerideninforegist.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmanagement.salerideninforegist.dto.SalerIdenInfoRegistDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbSalerIdenInfoRegistMapper {
    /**
     * 查询界面上条件
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SalerIdenInfoRegistDto> queryForRegistPage(Map<String,Object> param);
    List<SalerIdenInfoRegistDto> searchPage(Map<String,Object> map);

    int insertIntoHis(@Param("registList")List<SalerIdenInfoRegistDto> registList);

    int deleteLastSubmitRegist(@Param("registList")List<SalerIdenInfoRegistDto> registList);

    void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<SalerIdenInfoRegistDto> registList);

    Integer cancelMannultRegist(List<SalerIdenInfoRegistDto> list);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<SalerIdenInfoRegistDto> registList);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void deleteReportDataHis(@Param("registList")List<SalerIdenInfoRegistDto> registList);

    /**
     * 查询
     * @param param
     * @return
     */
    List<SalerIdenInfoRegistDto> setDataList(Map<String, Object> param);

    /**
     * 姓名下拉框
     */
    List<SuggestDto> getSalerName(Map<String, Object> map);

}