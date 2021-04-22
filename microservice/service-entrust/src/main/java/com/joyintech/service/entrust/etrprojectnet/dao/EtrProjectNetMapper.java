package com.joyintech.service.entrust.etrprojectnet.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrprojectnet.dto.EtrProjectNetDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EtrProjectNetMapper {
    /**
     *
     * 计划类净值委托查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrProjectNetDto> selectForPage(Map<String, Object> param);
    /**
     *
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getentrustId(Map<String, Object> map);
    /**
     *
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getfinprodId(Map<String, String> map);
    /**
     *
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, String> map);
    /**
     *
     * 获取新建界面的组合代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getentrustIdAdd(Map<String, Object> map);
    /**
     *
     * 通过主键查询修改信息
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrProjectNetDto selectByKey(@Param("entrustId") String entrustId);
    /**
     * 根据资产代码联动资产类型
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    Map<String, String> searchType(@Param("finprodId") String finprodId);
    /**
     *
     * 判断是否内部交易对手
     * <功能详细描述>
     * @param counterId
     * @return
     * @see
     * @since 1.0
     */
    String judgeCounterType(@Param("counterId") String counterId);
}
