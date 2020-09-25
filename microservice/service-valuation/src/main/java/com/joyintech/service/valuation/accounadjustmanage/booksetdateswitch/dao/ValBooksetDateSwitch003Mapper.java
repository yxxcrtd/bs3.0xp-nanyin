/*
 * 文件名：ValBooksetDateSwitch003Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zhangwei
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch003Dto;

/**
 * 账套日期管理 - 批量修改
 * @author zhangwei
 * @version 1.0 2019年2月18日
 * @see ValBooksetDateSwitch003Mapper
 * @since 1.0
 */
@Repository(value="serviceValBooksetDateSwitch003Mapper")
public interface ValBooksetDateSwitch003Mapper {

    /**
     * 页面初始化查询列表
     * 
     * @param params 参数
     * @return List<ValVoucherQuery003Dto>
     * @see
     * @since 1.0
     */
    List<ValBooksetDateSwitch003Dto> queryPageList(Map<String, Object> params);

    int updateByPrimaryKeySelective(ValBooksetDateSwitch003Dto dto);
}
