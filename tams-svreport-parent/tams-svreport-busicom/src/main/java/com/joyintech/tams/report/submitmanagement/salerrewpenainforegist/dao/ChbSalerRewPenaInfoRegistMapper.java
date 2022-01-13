package com.joyintech.tams.report.submitmanagement.salerrewpenainforegist.dao;

import com.joyintech.tams.report.submitmanagement.salerrewpenainforegist.dto.SalerRewPenaInfoRegistDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbSalerRewPenaInfoRegistMapper {
    /**
     * 查询界面上条件
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SalerRewPenaInfoRegistDto> queryForRegistPage(Map<String,Object> param);
    List<SalerRewPenaInfoRegistDto> searchPage(Map<String,Object> map);

    int insertIntoHis(@Param("registList")List<SalerRewPenaInfoRegistDto> registList);

    int deleteLastSubmitRegist(@Param("registList")List<SalerRewPenaInfoRegistDto> registList);

    void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<SalerRewPenaInfoRegistDto> registList);

    Integer cancelMannultRegist(List<SalerRewPenaInfoRegistDto> list);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<SalerRewPenaInfoRegistDto> registList);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void deleteReportDataHis(@Param("registList")List<SalerRewPenaInfoRegistDto> registList);

    /**
     * 查询
     * @param param
     * @return
     */
    List<SalerRewPenaInfoRegistDto> setDataList(Map<String, Object> param);
}