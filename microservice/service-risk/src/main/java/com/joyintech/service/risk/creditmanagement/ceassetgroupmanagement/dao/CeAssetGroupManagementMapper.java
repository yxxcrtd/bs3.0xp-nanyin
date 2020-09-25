/*
 * 文件名：CeAssetGroupManagementMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement002Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月11日
 * @see CeAssetGroupManagementMapper
 * @since 1.0
 */
@Repository
public interface CeAssetGroupManagementMapper {

    /**
     * <一句话功能简述>
     * 查看资产系列明细
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    CeAssetGroupManagement001Dto getBusinessInfoBySeqno(@Param("seqno") String seqno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<CeAssetGroupManagement001Dto> getAssetName(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryAssetInfo(Map<String, Object> map);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryAssetInfoPub(Map<String, Object> map);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<CeAssetGroupManagement002Dto> selectAssetInfo(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param selectedItem
     * @see
     * @since 1.0
     */
    void insertAssetPool(List<CeAssetGroupManagement002Dto> selectedItem);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<CeAssetGroupManagement001Dto> getAssetDetailBySeqno(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteAssetPool(@Param("seqno") String seqno, @Param("finprodId") String finprodId);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<CeAssetGroupManagement001Dto> selectForPage(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<CeAssetGroupManagement002Dto> getExistAssetBySeqno(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @param assetpoolName
     * @return
     * @see
     * @since 1.0
     */
    int checkIsExistByassetpoolName(Map<String, Object> map);

    /**
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int checkIsExistAssetPool(@Param("finprodId") String finprodId);
    
    
    /**
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加到其他系列中
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    String checkIsExistAssetPoolNew(@Param("finprodId") String finprodId,  @Param(value = "institutionCode")String institutionCode);
    
    /**
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int checkisExistAssetByCredit(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * 判断是否关联授信客户编号
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    int checkisExistId(@Param("seqno") String seqno);

    /**
     * <一句话功能简述>
     * 判断该资产系列是否存在明细
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    int checkIsExistDetail(@Param("seqno") String seqno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageId
     * @return
     * @see
     * @since 1.0
     */
    List<CeAssetGroupManagement002Dto> getBusinessWorkflowId(@Param("pageId") String pageId);

}
