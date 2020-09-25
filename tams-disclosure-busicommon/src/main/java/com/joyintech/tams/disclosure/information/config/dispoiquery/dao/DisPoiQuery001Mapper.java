package com.joyintech.tams.disclosure.information.config.dispoiquery.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dto.DisPoiQuery001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * poi查询sql管理Mapper（微服务层）
 *
 * @author kx
 */
@Repository
public interface DisPoiQuery001Mapper {
    int deleteByPrimaryKey(String id);

    int insert(DisPoiQuery001Dto record);

    int insertSelective(DisPoiQuery001Dto record);

    int insertBasicByBasicInfo(DisPoiQuery001Dto record);

    DisPoiQuery001Dto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DisPoiQuery001Dto record);

    int updateByPrimaryKey(DisPoiQuery001Dto record);

    /**
     * 查询列表数据
     *
     * @param param
     * @return
     */
    List<DisPoiQuery001Dto> queryForPage(Map<String, Object> param);

    /**
     * 获取关联编号下拉框初始化数据
     *
     * @param param
     * @return
     */
    List<SelectOptionDto> queryPoiIdList(Map<String, Object> param);


    /**
     * 根据id查询新增编辑详情
     *
     * @param id
     * @return
     */
    DisPoiQuery001Dto selectById(String id);

    
    /**
     * 批量删除poi查询sql管理数据
     *
     * @param keyList
     * @return
     */
    int deleteByKeyList(@Param("keyList") List<DisPoiQuery001Dto> keyList);


}