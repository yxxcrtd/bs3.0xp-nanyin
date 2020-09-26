package com.joyintech.tams.svreport.pbc.assetposition.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;

/**
 * 功能:人行报表-资产头寸
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年3月11日
 * @修改记录：
 */
public interface PbcMonthAssetReportMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(PbcMonthAssetPositionDto record);

    int insertSelective(PbcMonthAssetPositionDto record);

    PbcMonthAssetPositionDto selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(PbcMonthAssetPositionDto record);

    int updateByPrimaryKey(PbcMonthAssetPositionDto record);
    
    /**
     * 功能:根据日期查询资产头寸
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @param bookDate
     * @return 
     * List<PbcMonthAssetPositionDto>
     */
    List<PbcMonthAssetPositionDto> selectByDateAndProdid(@Param("prodList")List<String> prodList,@Param("bookDate")String bookDate,
            @Param("modelType") String modelType);
    
    /**
     * 功能:根据数据状态查询资产要素(仅供中债使用)
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月19日
     * @param status
     * @return 
     * List<AssetInfoDto>
     */
    List<ChbAssetInfo> selectAssetByStatus(@Param("status")String status);
    /**
     * 功能:根据日期查询资产头寸
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @param bookDate
     * @return 
     * List<PbcMonthAssetPositionDto>
     */
    List<PbcMonthAssetPositionDto> selectByDateAndProdidWithAssetPoolCode(@Param("prodList")List<String> prodList,@Param("bookDate")String bookDate);

    PbcMonthPrdPostionDto  selectChbPrdBalance(@Param("prodId")String prodId,@Param("endDate")Date  endDate); 

}