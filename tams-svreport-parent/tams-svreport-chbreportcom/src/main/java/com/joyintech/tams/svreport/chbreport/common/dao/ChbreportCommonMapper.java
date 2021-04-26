package com.joyintech.tams.svreport.chbreport.common.dao;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 共通Mapper
 *
 * @author WanShiWei
 * @version 1.0 2021年4月19日
 * @see ChbreportCommonMapper
 * @since 1.0
 */
@Repository
public interface ChbreportCommonMapper {

    /**
     * 查询产品代码和产品名称
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> selectAllPrdBaseInfo(Map<String, Object> map);

}
