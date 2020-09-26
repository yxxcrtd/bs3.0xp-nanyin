package com.joyintech.tams.svreport.chb.assetinforeg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.ChbAssetInfoSubHisDto;

@Repository
public interface ChbAssetInfoSubHisMapper {
    int deleteByPrimaryKey(String hisNo);

    int insert(ChbAssetInfoSubHisDto record);

    int insertSelective(ChbAssetInfoSubHisDto record);

    ChbAssetInfoSubHisDto selectByPrimaryKey(String hisNo);

    int updateByPrimaryKeySelective(ChbAssetInfoSubHisDto record);

    int updateByPrimaryKey(ChbAssetInfoSubHisDto record);
    
    /**
     * 功能:根据资产代码查询资产要素登记表数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月21日
     * @param assetList
     * @return 
     * List<AssetInfoRegist001Dto>
     */
    List<AssetInfoRegist001Dto> selectAssetInfoRegByAssetCode(@Param("assetList")List<String> assetList);
    
    /**
     * 功能:根据履历序号删除的资产要素履历信息
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月20日
     * @param deleteList 删除的数据
     * @param sendStatusList 报送状态
     * @return 
     * int
     */
    int deleteByHisno(@Param("deleteList")List<String> deleteList);

}