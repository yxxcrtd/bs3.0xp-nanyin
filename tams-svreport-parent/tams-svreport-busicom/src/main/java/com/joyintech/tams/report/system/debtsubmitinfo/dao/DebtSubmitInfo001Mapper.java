/*
 * 文件名：SystemFeedback001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.debtsubmitinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.system.debtsubmitinfo.dto.DebtSubmitInfo001Dto;


/**
 * 报送信息反馈
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年9月21日
 * @see DebtSubmitInfo001Mapper
 * @since 1.0
 */
@Repository
public interface DebtSubmitInfo001Mapper {

    /**
     * <一句话功能简述>
     * 查询报送信息反馈的方法
     * @param param 查询所需的参数
     * @return 存储了数据的列表
     * @see
     * @since 1.0
     */
    List<DebtSubmitInfo001Dto> searchInfo(Map<String, Object> param);
    /**
     * <一句话功能简述>
     * 查询报送信息反馈的方法
     * @param param 查询所需的参数
     * @return 存储了数据的列表
     * @see
     * @since 1.0
     */
    List<DebtSubmitInfo001Dto> searchInfoNew(Map<String, Object> param);
    
    List<DebtSubmitInfo001Dto> viewDetailPage(Map<String, Object> param);
}
