package com.joyintech.tams.business.common.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.business.common.entity.CfgDataTableShow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 公共方法mapper
 */
@Repository
public interface JoyinbusinessCommonMapper {

    /**
     * 根据条件查询证券账户
     *
     * @param param
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getSecManageAcctId(Map<String, Object> param);

    /**
     * 委托代码的联想文本框的查询方法
     *
     * @param param
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getEntrustId(Map<String, Object> param);
}
