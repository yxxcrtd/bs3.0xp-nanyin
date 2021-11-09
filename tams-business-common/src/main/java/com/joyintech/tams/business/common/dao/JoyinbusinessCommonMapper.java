package com.joyintech.tams.business.common.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.business.common.dto.CfetsOprHisInfoRequest;
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
    /**
     *
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param);
    /**
     *
     * 获取新建界面的组合联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> selectPortfolioIdList(Map<String, Object> param);
    /**
     * 获取投资经理
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getManagers(Map<String, Object> param);
    /**
     *
     * 获取新建界面的行权债资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRightfinprodIdForAdd(Map<String, Object> param);

    /**
     * 更新操作记录
     * @param record
     * @return
     */
    int insertCfetsOprHis(CfetsOprHisInfoRequest record);
}
