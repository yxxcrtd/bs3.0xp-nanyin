/*
 * 文件名：IbuIntfeErrMsgMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.database.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.background.component.uplink.interbank.business.dto.IbuInrfErrMsgSearchDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuIntfErrMsgDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteErrlogDto;

/**
 * 银行间上行错误代码接口
 * @author zhengjunping
 * @version 1.0 2020年5月18日
 * @see IbuIntfErrMsgDtoMapper
 * @since 1.0
 */
@Repository
public interface IbuIntfErrMsgDtoMapper {

    /**
     * 根据错误码和错误信息模糊查询
     * @param errCode String
     * @param errMsg String
     * @return IbuIntfErrMsgDto
     */
    List<IbuIntfErrMsgDto> selectIbuIntfErrMsg(IbuInrfErrMsgSearchDto ibuInrfErrMsgSearchDto);
    
    /**
     * 根据错误码查询
     * @param errCode String
     * @return IbuIntfErrMsgDto
     */
    IbuIntfErrMsgDto selectByErrCode(@Param("errCode")String errCode);
    
    /**
     * 根据报价编号查询错误日志
     * @param quoteId String
     * @return IbuQuoteErrlogDto
     */
    IbuQuoteErrlogDto selectErrlogByQuoteId(@Param("quoteId")String  quoteId);
    
    /**
     * 删除错误信息
     * @param quoteId String
     * @return int
     */
    int deleteIbuQuoteErrlog(@Param("quoteId")String  quoteId);
    
    /**
     * 获取版本号
     * @return Long
     */
    Long getVersion();
}
