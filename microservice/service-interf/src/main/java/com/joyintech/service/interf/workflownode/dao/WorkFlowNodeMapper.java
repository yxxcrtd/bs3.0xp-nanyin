/*
 * 文件名：WorkFlowNodeDao.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.workflownode.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 查询流程节点处理人信息
 * 
 * @author 杨静敏
 * @version 1.0 2018年12月20日
 * @see WorkFlowNodeMapper
 * @since 1.0
 */
@Repository
public interface WorkFlowNodeMapper {

    /**
     * 根据用户名查询用户真实姓名
     * 
     * @param userName 用户名
     * @return 真实姓名
     * @see
     * @since 1.0
     */
    String selectRealname(@Param("userName") String userName);

}
