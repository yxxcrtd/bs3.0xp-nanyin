package com.joyintech.tams.disclosure.information.config.prodfeediscount.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.dto.ProdFeediscountDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProdFeediscountMapper {
    int deleteByPrimaryKey(String prodDiscountId);

    int insert(ProdFeediscountDto record);

    int insertSelective(ProdFeediscountDto record);

    int insertBasicByBasicInfo(ProdFeediscountDto record);

    ProdFeediscountDto selectByPrimaryKey(String prodDiscountId);

    int updateByPrimaryKeySelective(ProdFeediscountDto record);

    int updateByPrimaryKey(ProdFeediscountDto record);


    List<ProdFeediscountDto> searchReports(Map<String, Object> params);

    /**
     * 设置主键
     * @return
     */
    String setId();

    ProdFeediscountDto selectByKeyId(@Param("keyId") String keyId);

    /**
     *  根据产品代码搜索产品信息
     * @param prodId 产品代码
     * @param dataStatus 数据状态
     * @return
     */
    ProdFeediscountDto selectByProdId(@Param("prodId") String prodId, @Param("dataStatus")String dataStatus);


    List<SelectOptionDto> getProdFeenameSelect(Map<String, String> map);

}