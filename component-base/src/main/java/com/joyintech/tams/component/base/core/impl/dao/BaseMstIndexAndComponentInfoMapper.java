package com.joyintech.tams.component.base.core.impl.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstIndexInfo;
import com.joyintech.tams.component.base.core.dto.BaseMstIndexAndComponentInfoDto;
import com.joyintech.tams.component.base.core.dto.BaseMstIndexInfoDto;
/**
 * 基准信息组件Mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年9月4日
 * @see BaseMstIndexAndComponentInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseMstIndexAndComponentInfoMapper {
 
    /**
     * 查询基准信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param mstIndexInfo
     * @return
     * @see
     * @since 1.0
     */
    List<MstIndexInfo> queryIndexInfo(MstIndexInfo mstIndexInfo);
    /**
     * 删除基准信息（含基准成分）
     * <一句话功能简述>
     * <功能详细描述>
     * @param componentId
     * @param componentType
     * @return
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(String indexId,String componentId,String componentType);
    /**
     * 查询基准信息（含基准成分）
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseMstIndexInfoDto
     * @return
     * @see
     * @since 1.0
     */
    List<BaseMstIndexAndComponentInfoDto> queryIndexInfoContainsElement(BaseMstIndexInfoDto baseMstIndexInfoDto);

}