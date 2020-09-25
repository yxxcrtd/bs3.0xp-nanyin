/*
 * 文件名：RskValSumnameMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月17日
 * @see RskValSumnameMapper
 * @since 1.0
 */
@Repository
public interface RskValSumnameMapper {

    /**
     * 查询映射名称
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RskValSumnameDto> getSumName(Map<String, Object> map);

    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
    List<RskValSumnameDto> selectForPage(Map<String, Object> pageParam);
    
    List<RskValSumnameDto> selectForList(@Param("sumId") String sumId);

    /**
     * 根据id删除汇总映射明细表
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    void deleteById(RskValSumnameDto dto);

    /**
     * 根据映射名称查询汇总映射表
     * <功能详细描述>
     * @param name
     * @return
     * @see
     * @since 1.0
     */
    List<RskValSumnameDto> selectBySumName(String name);

    /**
     * 根据id和映射明细名称查询数据
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    RskValSumnameDto selectByName(RskValSumnameDto dto);

    /**
     * 选中记录在业务表中是否已关联组合关联组合
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int getCountById(RskValSumnameDto dto);

}
