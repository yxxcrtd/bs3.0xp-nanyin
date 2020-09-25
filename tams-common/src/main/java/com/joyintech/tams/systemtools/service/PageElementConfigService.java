/*
 * 文件名：PageElementConfigService.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/6/27 15:59
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.systemtools.service;

import com.joyintech.tams.systemtools.dao.PageElementConfigMapper;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王波
 * @version 1.0 2019年06月27日
 * @see
 * @since 1.0
 */
@Service
public class PageElementConfigService {
    @Autowired
    private PageElementConfigMapper pageElementConfigMapper;

    public List<PageElementConfigDto> getAllElementConfig(){
        return pageElementConfigMapper.getAllPageElementConfig();
    }
}
