/*
 * 文件名：PageElementConfigMapper.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/6/27 15:23
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.systemtools.dao;

import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王波
 * @version 1.0 2019年06月27日
 * @see
 * @since 1.0
 */
@Repository
public interface PageElementConfigMapper {
    /**
     * 获取所有画面元素权限
     * @return
     */
    public List<PageElementConfigDto> getAllPageElementConfig();
}
