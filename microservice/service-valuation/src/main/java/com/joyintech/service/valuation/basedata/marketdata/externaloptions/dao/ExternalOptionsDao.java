package com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions001Dto;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions002Dto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 12-31 10:22
 */
@Repository("serviceExternalOptionsDao")
public interface ExternalOptionsDao {

    ExternalOptions002Dto selectByPrimaryKey(Map<String, Object> map);

    public List<SuggestDto> findFinRateInfoByBenchmarkId(Map<String, Object> params);

    public String findByBenchmarkId(String finprodId2);

    public List<ExternalOptions001Dto> selectList(Map<String, Object> map);


    public List<SuggestDto> findTargetId(Map<String, Object> params);

    public List<SuggestDto> findFinprodId(Map<String, Object> params);

    ExternalOptions002Dto dateCheck(Map<String, Object> params);
    
    /**
     * 查询新增数据主键是否重复
     * @param list 产品list
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );

    /**
     * 查询新增数据合约标的是否存在
     * @param 
     * @param 
     * @return 
     * @see
     * @since 1.0
     */

    String  checkTargetId( Map<String,Object> param);
    
    /**
     * 查询新增数据交易编号是否存在
     * @param 
     * @param 
     * @return 
     * @see
     * @since 1.0
     */

    String  checkTradeId( Map<String,Object> param);
    
    /**
     * 验证交易编号与外汇类型是否匹配
     * @param finprodId 产品代码
     * @param finprodType2 金融产品类型
     * @return list
     * @see
     * @since 1.0
     */

    String  checkTargetIdAndTradeId( Map<String,Object> param);

}
