/*
 * 文件名：ValAssetBook001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.disclosure.busicommon.common.dto.ValAssetbook001Dto;

/**
 * 〈一句话功能简述〉 套帐管理
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetBook001Mapper
 * @since 1.0
 */
@Repository
public interface DisValAssetBook001Mapper {
    
    /**
     * 检索账套代码
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetId();
    /**
     * 检索账套代码
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetIdByBooksetType();
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetId2(@Param("booksetType") String booksetType, @Param("param") String param);

    /**
     * 查询数据 <一句话功能简述> <功能详细描述>
     * 
     * @param booksetType 账套类别
     * @param booksetName 账套名称
     * @return List<ValAssetbook001SearchDto>
     * @see
     * @since 1.0
     */
    List<ValAssetbook001Dto> getBookSet02(@Param("booksetType") String booksetType,
                                          @Param("booksetName") String booksetName);

    /**
     * 获取账套名称
     * 
     * @return List<ValAssetbook001Dto>
     * @see
     * @since 1.0
     */
    List<ValAssetbook001Dto> getBookSetName();
    //多级法人改造start add by hxy 20200619
    /**
     * 银行模式时，返回机构
     * @return
     */
    public String selectBankModeOrgName();
    /**
     * 联社机构时，选择账套，返回机构
     * @return
     */
    public String selectAssociationModeOrgName(@Param("booksetOrg") String  booksetOrg);
    /**
     * 联社机构时，选择账套组返回机构
     * @return
     */
    public String selectAssociationModeOrgGrpName(@Param("booksetOrg") String  booksetOrg);
    
    //多级法人改造start add by hxy 20200619
    
}
