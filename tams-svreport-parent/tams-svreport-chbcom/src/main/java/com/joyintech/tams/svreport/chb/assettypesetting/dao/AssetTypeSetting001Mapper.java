package com.joyintech.tams.svreport.chb.assettypesetting.dao;

import com.joyintech.tams.svreport.chb.assettypesetting.dto.AssetTypeSetting001Dto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AssetTypeSetting001Mapper {

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<AssetTypeSetting001Dto> selectForPage (Map<String,Object> params);

    /**
     * 点击左侧李彪数据事件
     * @param params
     * @return
     */
    List<AssetTypeSetting001Dto> selectById (Map<String,Object> params);

    /**
     * 根据映射表uuid查询附属表uuidlist
     * @param typeuuid
     * @return
     */
    List<String> selectTypeUuidExById(@Param(value = "typeuuid") String typeuuid);

    /**
     * 新建校验
     * @param param
     * @return
     */
    AssetTypeSetting001Dto isConfilct(Map<String, Object> param);

    /**
     *
     * @param params
     * @return
     */
    List<AssetTypeSetting001Dto> findExPage (Map<String,Object> params);
}
