/*
 * 文件名：FieldEtrInfoQueryMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：msk
 * 修改时间：2021年12月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.fieldetrinfoquery.dao;

import com.joyintech.tams.exchange.entrust.etrexchange.fieldetrinfoquery.dto.FieldEtrInfoOperateListResponse;
import com.joyintech.tams.exchange.entrust.etrexchange.fieldetrinfoquery.dto.FieldEtrInfoQueryListResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 场内指令查询
 * @author msk
 * @version 1.0 2021年12月22日
 * @see FieldEtrInfoQueryMapper
 * @since 1.0
 */
@Repository
public interface FieldEtrInfoQueryMapper {

    /**
     * 指令列表分页查询
     * @param paraMap 参数
     * @return List<FieldEtrInfoQueryListResponse>
     */
    List<FieldEtrInfoQueryListResponse> findAllData(Map<String, Object> paraMap);

    /**
     * 操作记录查询
     *
     * @param pageParam
     * @return List<FieldEtrInfoOperateListResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    List<FieldEtrInfoOperateListResponse> getEtrInfoQueryOperate(Map<String, Object> pageParam);

    //查询登录用户是否是领导、交易处长岗位
    int queryRolePosByLoginUser(Map<String, Object> map);
}
