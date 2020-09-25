/*
 * 文件名：SendEmailMessageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.iemail.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.InterfEmailconfig;
import com.joyintech.service.interf.iemail.entity.InterfEmailInEntity;

/**
 * 发送邮件查询mapper
 * 
 * @author 杨静敏
 * @version 1.0 2018年12月4日
 * @see SendEmailMessageMapper
 * @since 1.0
 */
@Repository
public interface SendEmailMessageMapper {

    /**
     * 更新邮件发送状态
     * 
     * @param iEmailEntity 邮件接口入参
     * @see
     * @since 1.0
     */
    void updateMsgStatus(InterfEmailInEntity iEmailEntity);

    /**
     * 查询发送邮件信息
     * 
     * @param serialno 邮件序号
     * @return 邮件信息
     * @see
     * @since 1.0
     */
    InterfEmailInEntity selectEmailMessage(@Param("serialno") String serialno);

    /**
     * 获取邮件接收人和发送人信息
     * 
     * @param busitype 业务类型
     * @return 返回邮件接收人和发送人信息
     * @see
     * @since 1.0
     */
    InterfEmailconfig selectEmailConfig(@Param("busitype") String busitype);

}
