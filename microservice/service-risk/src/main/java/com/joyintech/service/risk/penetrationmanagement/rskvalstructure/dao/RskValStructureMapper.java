/*
 * 文件名：RskValStructureMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月11日
 * @see RskValStructureMapper
 * @since 1.0
 */
@Repository
public interface RskValStructureMapper {

    /**
     * 查询估值表结构映射信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RskValStructureDto> getStructName(Map<String, Object> map);

    /**
     * 画面查询
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
    List<RskValStructureDto> selectForPage(Map<String, Object> pageParam);
    
    List<RskValStructureDto> selectForList(String structId);

    /**
     * 根据映射名称查询数据
     * <功能详细描述>
     * @param structName
     * @return
     * @see
     * @since 1.0
     */
    List<RskValStructureDto> selectByName(String structName);

    /**
     * 根据映射id删除映射明细表
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    void deleteById(RskValStructureDto dto);

    /**
     * 选中记录在业务表中是否已关联组合关联组合
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int getCountById(RskValStructureDto dto);

}
