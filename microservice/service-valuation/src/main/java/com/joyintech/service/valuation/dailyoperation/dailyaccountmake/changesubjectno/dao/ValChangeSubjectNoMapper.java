package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.changesubjectno.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.BokSubject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yanya
 * @create 2020-12-12 10:20
 */
@Repository(value="serviceValChangeSubjectNoMapper")
public interface ValChangeSubjectNoMapper {

    /**
     * 根据科目号list查询科目数据
     * @param list
     * @return
     */
    List<BokSubject> getSubjectList(@Param("list") List<String> list);

    /**
     * 获取所有账套
     * @param map
     * @return
     */
    List<String> getBooksetIds(Map<String, Object> map);

    /**
     * 科目信息下拉框
     * @param map
     * @return
     */
    List<SelectOptionDto> getSubjectId(Map<String, Object> map);

    /**
     * 账套信息下拉框
     * @param map
     * @return
     */
    List<SelectOptionDto> findBooksetId(Map<String, Object> map);

    /**
     * 查询核算分类
     * @return
     */
    List<SelectOptionDto> selectDataDictType(Map<String, Object> params);
}
