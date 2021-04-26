package com.joyintech.tams.settle.common.dao;

import com.joyintech.tams.settle.common.dto.FundInfoDto;
import com.joyintech.tams.settle.common.dto.StlDownDataDealAccountInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 清结算CommonMapper
 * 
 * @author 陈卓
 * @date 2020/7/7
 * @see SettleCommonMapper
 * @since 1.0
 */
@Repository
public interface SettleCommonMapper {
	
    // 数据清算附属信息（核算信息）全量获取
    List<StlDownDataDealAccountInfoDto> selectAllStlDownDataDealAccountInfoDtoList();
    
    // 基金全量信息获取（分类1和分类7）
    List<FundInfoDto> getAllFundInfoDto();

    /**
     * 根据金融产品类型_投管用获取所有的标的代码，用来判断标的类型
     * @param finprodType 金融产品类型_投管用
     * @return
     */
    List<String> getAllAssetInfo(String finprodType);
}
