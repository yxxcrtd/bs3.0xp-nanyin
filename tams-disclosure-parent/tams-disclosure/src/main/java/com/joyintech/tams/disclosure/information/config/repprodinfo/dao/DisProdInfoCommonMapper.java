package com.joyintech.tams.disclosure.information.config.repprodinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface DisProdInfoCommonMapper {

    /**
     * 获取产品登记编码
     * @param map
     * @return
     */
    List<SelectOptionDto> getRegistNo(Map<String, Object> map);

    /**
     * 根据产品登记编码 获取产品主代码和产品名称,支持联动
     * @param map
     * @return
     */
    List<SelectOptionDto> getProdIdAndNameByRegistNo(Map<String, Object> map);



}
