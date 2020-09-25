package com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.DisPoiSub;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto.PoiReportModelManageChildren001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 报表子表Mapper
 *
 * @author kx
 */
@Repository
public interface PoiReportModelManageChildren001Mapper {
    int deleteByPrimaryKey(String id);

    int insert(PoiReportModelManageChildren001Dto record);

    int insertSelective(PoiReportModelManageChildren001Dto record);

    int insertBasicByBasicInfo(PoiReportModelManageChildren001Dto record);

    DisPoiSub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PoiReportModelManageChildren001Dto record);

    int updateByPrimaryKey(PoiReportModelManageChildren001Dto record);

    /**
     * 查询列表数据
     *
     * @param param
     * @return
     */
    List<PoiReportModelManageChildren001Dto> queryForPage(Map<String, Object> param);


    /**
     * 获取模板编号下拉框初始化数据
     *
     * @param param
     * @return
     */
    List<SelectOptionDto> queryPoiIdList(Map<String, Object> param);

    /**
     * 根据id查询有没有相同的
     *
     * @param id
     * @return
     */
    int countById(String id);

    /**
     * 批量删除poi报表子表
     *
     * @param keyList
     * @return
     */
    int deleteByKeyList(@Param("keyList") List<PoiReportModelManageChildren001Dto> keyList);


    /**
     * 根据id查询新增编辑详情
     *
     * @param id
     * @return
     */
    PoiReportModelManageChildren001Dto selectById(String id);


}