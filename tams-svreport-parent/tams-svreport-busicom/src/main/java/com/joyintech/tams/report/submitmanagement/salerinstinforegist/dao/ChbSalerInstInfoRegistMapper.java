package com.joyintech.tams.report.submitmanagement.salerinstinforegist.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmanagement.salerinstinforegist.dto.SalerInstInfoRegistDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbSalerInstInfoRegistMapper {
    /**
     * 查询界面上条件
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SalerInstInfoRegistDto> queryForRegistPage(Map<String,Object> param);
    List<SalerInstInfoRegistDto> searchPage(Map<String,Object> map);

    int insertIntoHis(@Param("registList")List<SalerInstInfoRegistDto> registList);

    int deleteLastSubmitRegist(@Param("registList")List<SalerInstInfoRegistDto> registList);

    void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<SalerInstInfoRegistDto> registList);

    Integer cancelMannultRegist(List<SalerInstInfoRegistDto> list);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<SalerInstInfoRegistDto> registList);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @see
     * @since 1.0
     */
    void deleteReportDataHis(@Param("registList")List<SalerInstInfoRegistDto> registList);

    /**
     * 查询
     * @param param
     * @return
     */
    List<SalerInstInfoRegistDto> setDataList(Map<String, Object> param);

    /**
     * 销售机构代码下拉框
     */
    List<SuggestDto> getSalerInstCode(Map<String, Object> map);

    /**
     * 总部或分支机构名称下拉框
     */
    List<SuggestDto> getSalerOrgName(Map<String, Object> map);

}