/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.BokFileInfoDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.BokFileTransfer003Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.BokNetValueCfmFileDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm001Dto;

/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Mapper
 * @since 1.0
 */
@RestController(value="serviceValNetValueConfirm001Mapper")
public interface ValNetValueConfirm001Mapper {
    
    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ValNetValueConfirm001Dto> queryPageList(Map<String, Object> params);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String,Object> map);

    /**
     * 根据产品findprodid获取产品详细信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    ValNetValueConfirm001Dto queryProductByfinprodId(Map<String, Object> map);

    /**
     * 估值表余额表状态表 - 确认状态更新
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int updateConfirmStatus(ValNetValueConfirm001Dto dto);
    
    /**
     * 估值表余额表状态表 - 下发状态更新
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int updateNoticeStatus(ValNetValueConfirm001Dto dto);
    
    /**
     * 文件信息入库
     * @param dto
     * @return
     */
    int insertBokFileTransfer(BokFileTransfer003Dto dto);
    
    /**
     * 查询是否已存在产品的文件信息
     * @param dto
     * @return
     */
    int quryBokFileTransfer(BokFileTransfer003Dto dto);
    
    /**
     * 删除重复的文件信息
     * @param dto
     */
    void deleteBokFileTransfer(BokFileTransfer003Dto dto);
    
    
    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<BokFileTransfer003Dto> queryBokFileTransferPageList(Map<String, Object> params);
    
    /**
     * 根据主键查询文件信息
     * @param seqNo
     * @return
     */
    BokFileTransfer003Dto qryBokFileTransferBySeqNo(String seqNo);
    
    
    /**
     * 文件信息入库
     * @param dto
     * @return
     */
    int insertBokFileInfo(BokFileInfoDto dto);
    
    
    /**
     * 净值确认附件信息入库
     * @param dto
     * @return
     */
    int insertBokNetValueCfmFile(BokNetValueCfmFileDto dto);
    
    /**
     * 查询是否已存在附件信息
     * @param dto
     * @return
     */
    List<BokFileTransfer003Dto> quryBokFileInfo(BokFileTransfer003Dto dto);
    
    /**
     * 查询已存在的附件uuid
     * @param dto
     * @return
     */
    String quryBokFileInfoKey(BokFileInfoDto dto);
    /**
     * 删除重复的文件信息
     * @param dto
     */
    void deleteBokFileInfo(BokFileInfoDto dto);
    /**
     * 删除净值确认附件信息
     * @param dto
     */
    void deleteBokNetValueCfmFile(BokNetValueCfmFileDto dto);
    
    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<BokFileTransfer003Dto> queryBokFileInfoPageList(Map<String, Object> params);
    
    /**
     * 根据主键查询文件信息
     * @param seqNo
     * @return
     */
    BokFileTransfer003Dto qryBokFileInfoByFileId(String fileId);
  
    
    /**
     * 
     * 账套日期日切
     * @param booksets
     * @see
     * @since 1.0
     */
    void updateBooksetDate(@Param(value = "finprodId") String finprodId);
    
    /**
     * 
     * 账套日期日切（取消确认）
     * @param booksets
     * @see
     * @since 1.0
     */
    void updateBooksetDateRevoke(@Param(value = "finprodId") String finprodId);
}
