package com.joyintech.service.basicdata.marketdata.mstinterestratequotationinfo.dao;
import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstinterestratequotationinfo.dto.ComInterestRateQuotationInfoDto;
import org.springframework.stereotype.Repository;

/**
 * 利率行情信息Mapper
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see ComInterestRateQuotationInfoMapper
 * @since 1.0
 */
@Repository
public interface ComInterestRateQuotationInfoMapper {

    /**
     * 根据条件查找用户
     * @param param 查询条件
     * @return 用户信息
     */
    List<ComInterestRateQuotationInfoDto> select(Map<String, Object> param);
    /**
     * 根据逐渐进行查询
     * @param map 查询条件
     * @return MstInterestRateQuotationInfoDto 实体类
     * @see
     * @since 1.0
     */
    ComInterestRateQuotationInfoDto selectByPrimaryKey(Map<String, Object> map);
    
  //hsgexinghua add by zhangxing1 20200228 start
    /**
     * 查询新增数据主键是否重复
     * @param list 产品list

     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );
    /**
     * 查询导入利率代码
     * @param list 产品list

     * @return list
     * @see
     * @since 1.0
     */
    ComInterestRateQuotationInfoDto getIntRateIdByName(Map<String,Object> param );
    
    /**
     * 查询导入利率代码
     * @param list 产品list

     * @return list
     * @see
     * @since 1.0
     */
    String checkCcy( Map<String,Object> param );
}
