/*
 * 文件名：IbuQuoteErrlogServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.quoteerrlog.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteErrlogDto;
import com.joyintech.background.component.uplink.interbank.business.quoteerrlog.IbuQuoteErrlogService;
import com.joyintech.background.component.uplink.interbank.database.dao.IbuIntfErrMsgDtoMapper;
import com.joyintech.background.component.uplink.interbank.database.dao.IbuQuoteErrlogMapper;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteErrlog;

/**
 * 报价错误日志服务
 * @author zhengjunping
 * @version 1.0 2020年5月23日
 * @see IbuQuoteErrlogServiceImpl
 * @since 1.0
 */
@Service
public class IbuQuoteErrlogServiceImpl implements IbuQuoteErrlogService{
    
    @Autowired
    private IbuIntfErrMsgDtoMapper ibuIntfErrMsgDtoMapper;
    
    @Autowired
    private IbuQuoteErrlogMapper ibuQuoteErrlogMapper;

    /**
     * 根据报价编号查询错误日志
     * @param quoteId String
     * @return IbuQuoteErrlogDto
     */
    @Override
    public IbuQuoteErrlogDto selectErrlogByQuoteId(String  quoteId){
        return ibuIntfErrMsgDtoMapper.selectErrlogByQuoteId(quoteId);
    }
    
    /**
     * 保存错误信息
     * @param ibuQuoteErrlogDto IbuQuoteErrlogDto
     * @return int
     */
    @Override
    public int insertIbuQuoteErrlog(IbuQuoteErrlogDto ibuQuoteErrlogDto){
    	IbuQuoteErrlog ibuQuoteErrlog = new IbuQuoteErrlog();
    	BeanUtil.copyPropertiesIgnoreNull(ibuQuoteErrlog, ibuQuoteErrlogDto);
        return ibuQuoteErrlogMapper.insert(ibuQuoteErrlog);
    }
    
    /**
     * 删除错误信息
     * @param quoteId String
     * @return int
     */
    @Override
    public int deleteIbuQuoteErrlog(String  quoteId){
        return ibuIntfErrMsgDtoMapper.deleteIbuQuoteErrlog(quoteId);
    }
    
    /**
     * 获取版本号
     * @return Long
     */
    public Long getVersion(){
    	return ibuIntfErrMsgDtoMapper.getVersion();
    }
}
