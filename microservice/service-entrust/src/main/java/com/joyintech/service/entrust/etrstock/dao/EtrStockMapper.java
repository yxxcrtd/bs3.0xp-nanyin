package com.joyintech.service.entrust.etrstock.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrstock.dto.EtrStockDto;


/**   
 * @title: EtrStockMapper
 * @package com.joyintech.service.entrust.etrstock.dao
 * @description: 股票委托mapper
 * @author: junYin
 * @date: 2019-06-04 11:12
*/
@Component
public interface EtrStockMapper {


    /**
     *@description: 股票委托查询  
     *@author: junYin
     *@date: 2019/06/04 11:12   
     *@param: * @param null 1
     *@return:  
     *@throws:
     */   
    List<EtrStockDto> selectForPage(Map<String, Object> param);

    /**
     *@description: 获取组合代码联想文本框信息,支持组合代码或组合名称进行模糊查询
     *@author: junYin
     *@date: 2019/06/04 11:13   
     *@param: * @param null 1
     *@return:  
     *@throws:
     */   
    List<SuggestDto> getentrustId(Map<String, Object> map);

    /**
     *@description: 获取资产代码联想文本框信息  
     *@author: junYin
     *@date: 2019/06/04 11:13   
     *@param: * @param null 1
     *@return:  
     *@throws:
     */   
    List<SuggestDto> getfinprodId(Map<String, String> map);

    /**
     *@description: 获取新建界面的资产代码联想下拉框  
     *@author: junYin
     *@date: 2019/06/04 11:13   
     *@param: * @param null 1
     *@return:  
     *@throws:
     */   
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, String> map);

    /**
     *@description: 获取新建界面的组合代码联想下拉框  
     *@author: junYin
     *@date: 2019/06/04 11:14   
     *@param: * @param null 1
     *@return:  
     *@throws:
     */   
    List<SelectOptionDto> getentrustIdAdd(Map<String, Object> map);

    /**
     *
     * 新建页面通道下拉框
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return Page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectTongDao(Map<String, Object> map);

    /**
     *@description: 通过主键查询修改信息  
     *@author: junYin
     *@date: 2019/06/04 11:14   
     *@param: * @param null 1
     *@return:  
     *@throws:
     */   
    EtrStockDto selectByKey(@Param("entrustId") String entrustId);

    /**
     *@description: 判断是否内部交易对手  
     *@author: junYin
     *@date: 2019/06/04 11:14   
     *@param: * @param null 1
     *@return:  
     *@throws:
     */   
    String judgeCounterType(@Param("counterId") String counterId);
    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
    BigDecimal getTongDaoCash(Map<String, Object> map);

    /**
     * 证券托管户查询
     * @param param
     * @return
     */
    List<SuggestDto> getSecAcctId(Map<String, Object> param);

    /**
     * 查询估值净价
     * @author wangguiqi
     */
	BigDecimal getClosePriceByPara(Map<String, Object> param);
}
