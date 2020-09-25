package com.joyintech.service.entrust.etrbondtostock.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.entrust.etrbondtostock.dto.EtrBondToStockDto;
import com.joyintech.framework.grid.Page;


/**
 * 可转债转股委托mapper
 * 〈功能详细描述〉
 * @author gaopeng
 * @version 1.0 2019年10月24日
 * @see EtrBondToStockMapper
 * @since 1.0
 */
@Component
public interface EtrBondToStockMapper {
	
    /**
     * 可转债转股委托分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrBondToStockDto> selectForPage(Map<String, Object> param);
    
    /**
     * 可持仓分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrBondToStockDto> selectOtherPage(Map<String, Object> param);
    
    /**
     * 
     * 新建页面转债代码联想
     * <功能详细描述>
     * @param map 用户输入值
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodIdForAdd(Map<String,Object> map);
    
    /**
     * 
     * 正股代码联想
     * <功能详细描述>
     * @param map 用户输入值
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getShareId(Map<String,Object> map);
    
    /**
     * 
     * 获取转债代码联想文本框信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> map);
    
    /**
     * 
     * 通过主键查询修改信息
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrBondToStockDto selectByKey(@Param("entrustId") String entrustId,@Param("sysDate")String sysDate,@Param("hodingType")String hodingType);
    
    /**
     * 持仓查询
     * @param pageParam
     * @return
     */
    BigDecimal getPositionInfo(Map<String, Object> pageParam);
    
    /**
     * 获取交易对手通过资产代码
     */
    String getCounterId(@Param("finprodId") String finprodId);
    
    /**
     * 判断是否证券托管户
     */
	boolean getYesOrNo(@Param("secAcctId") String secAcctId);
}
