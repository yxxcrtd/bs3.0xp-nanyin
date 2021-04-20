/*
 * 文件名：IbuIntfErrMsgServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.intferrmsgservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuInrfErrMsgSearchDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuIntfErrMsgDto;
import com.joyintech.background.component.uplink.interbank.business.intferrmsgservice.IbuIntfErrMsgService;
import com.joyintech.background.component.uplink.interbank.business.routeconfigservice.impl.RouteConfigSubServiceImpl;
import com.joyintech.background.component.uplink.interbank.common.CommonUtils;
import com.joyintech.background.component.uplink.interbank.database.dao.IbuIntfErrMsgDtoMapper;

/**
 * 路由配置子组件实现类 〈功能详细描述〉
 * 
 * @author caoguo
 * @version 1.0 2020年5月9日
 * @see RouteConfigSubServiceImpl
 * @since 1.0
 */
@Service
public class IbuIntfErrMsgServiceImpl implements IbuIntfErrMsgService{
    
    @Autowired
    IbuIntfErrMsgDtoMapper ibuIntfErrMsgDtoMapper;

    @Override
    public Page<IbuIntfErrMsgDto> selectForPage(int pageNum, int pageSize,
                                                IbuInrfErrMsgSearchDto ibuInrfErrMsgSearchDto) {
        Page<IbuIntfErrMsgDto> pageData = new Page<>();
        CommonUtils.sortList(ibuInrfErrMsgSearchDto.getOrderField(), ibuInrfErrMsgSearchDto.getOrderDirection());
        com.github.pagehelper.Page<IbuIntfErrMsgDto> pageList = PageHelper.startPage(pageNum, pageSize)
            .doSelectPage(() -> ibuIntfErrMsgDtoMapper.selectIbuIntfErrMsg(ibuInrfErrMsgSearchDto));
        pageData = new Page<>(pageList);
        return pageData;
    }

    @Override
    public List<IbuIntfErrMsgDto> selectIbuIntfErrMsgList(IbuInrfErrMsgSearchDto ibuInrfErrMsgSearchDto) {
        CommonUtils.sortList(ibuInrfErrMsgSearchDto.getOrderField(), ibuInrfErrMsgSearchDto.getOrderDirection());
        return ibuIntfErrMsgDtoMapper.selectIbuIntfErrMsg(ibuInrfErrMsgSearchDto);
    }

    @Override
    public IbuIntfErrMsgDto selectByErrCode(String errCode) {
        return ibuIntfErrMsgDtoMapper.selectByErrCode(errCode);
    }
    
}
