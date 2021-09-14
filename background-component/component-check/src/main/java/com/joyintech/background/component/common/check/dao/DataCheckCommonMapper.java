package com.joyintech.background.component.common.check.dao;

import com.joyintech.background.component.common.check.dto.CheckRepType;
import com.joyintech.background.component.common.check.dto.CheckRule;
import com.joyintech.background.component.common.check.dto.CheckCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCheckCommonMapper {

    /**
     *  根据画面Id查询中债前置规则表
     * @param checkruleid 画面Id
     * @return 中债前置规则表
     */
    List<CheckCondition> queryChbCheckCondition(@Param("checkruleid") String checkruleid, @Param("pageFlag") String pageFlag);

    /**
     *  根据画面Id查询中债匹配规则表
     * @param pageId 画面Id
     * @return 中债匹配规则表
     */
    List<CheckRule> queryChbCheckRule(@Param("pageId") String pageId, @Param("pageFlag") String pageFlag);

    /**
     *  根据画面Id查询数据检查中债匹配类型表
     * @param pageId 画面Id
     * @param repType 匹配类型
     * @return 数据检查中债匹配类型表
     */
    List<CheckRepType> queryChbCheckRepTypeList(@Param("pageId") String pageId, @Param("repType") String repType);
}
