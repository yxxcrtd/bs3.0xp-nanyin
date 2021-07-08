package com.joyintech.service.entrust.etribrepo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoinfo002Dto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 债券投资委托mapper
 */
@Repository
public interface EtrIbRepoinfo002Mapper {
    /**
     * 
     * 债券投资委托查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrIbRepoinfo002Dto> selectForPage(Map<String, Object> param);
    /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @param map
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
    List<SuggestDto> getfinprodId(Map<String, Object> param);
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
     * 获取新建界面的组合代码联想下拉框框
     * <功能详细描述>
     * @param param 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getentrustIdAdd(Map<String, Object> param);
    /**
     * 
     * 通过主键查询修改信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */ 
    EtrIbRepoinfo002Dto selectByKey(Map<String, Object> map);
    /**
     * 可用面值计算
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getAvailableNum(Map<String, Object> map);
    /**
     * 
     * 查询真实姓名
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    String getUser(@Param("userName") String userName);
    /**
     * 查询估值净价
     * @author wangguiqi
     */
	BigDecimal getClosePriceByPara(Map<String, Object> param);

    /**
     * 查询行权价
     * @author wangguiqi
     */
    BigDecimal getExercisePricebyPara(Map<String, Object> param);

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    List<SuggestDto> getSecManageAcctId(Map<String, Object> param);

    int deleteWflEtrEntrustByEntrustId(String entrustId);

    int deleteWflEtrEntrustAddByEntrustId(String entrustId);
    
    /**
     * 根据组合ID 查询质押库
     * @param portfolioId 组合ID
     * @return
     */
    List<SelectOptionDto> getPledgeStockIdByPortfolioId(Map<String, Object> param);
}
