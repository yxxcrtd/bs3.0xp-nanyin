/*
 * 文件名：TrdRepoTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.entrust.etrstandardbond.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBond003Dto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondInDto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondSearchDto;
import com.joyintech.tams.common.base.entity.EtrSecPriorityDetail;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 标准券微服务TrdStandardBondMapper
 */
@Repository
public interface EtrStandardBondMapper {

    /**
     * 标准券查询画面，查询标准券信息列表
     *
     * @param pageParam pageParam
     * @return List<TrdStandardBondSearchDto>
     */
    List<EtrStandardBondSearchDto> selectSearchDataList(Map<String, Object> pageParam);

    /**
     * 质押券入库画面，查询可质押债券列表
     *
     * @param pageParam pageParam
     * @return List<TrdStandardBondInDto>
     */
    List<EtrStandardBondInDto> selectAvaPledgeBondList(Map<String, Object> pageParam);
    
    /**
     * 质押券入库画面，查询已入库债券列表
     *
     * @param pageParam pageParam
     * @return List<TrdStandardBondInDto>
     */
    List<EtrStandardBondInDto> selectPledgeBondList(Map<String, Object> pageParam);
    
    /**
     * 优先级设置查询
     *
     */
    EtrStandardBond003Dto selectEtrSecPriority() ;
    
    /**
     * 优先级设置明细查询，填充画面列表
     *
     */
    List<EtrSecPriorityDetail> selectEtrSecPriDetailList(String configId) ;
    
    /**
     * 删除优先级数据，全删全插
     *
     */
    int deleteEtrSecPriority();
    /**
     * 删除优先级明细数据，全删全插
     *
     */
    int deleteEtrSecPriDetail();
    
    /**
     * 自动导入质押券 查询
     *
     * @param param 参数
     * @return list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    List<PledgeBondInfoDto> selectAutoPledge(@RequestBody Map<String, Object> param) ;

    /**
     * 根据组合代码，查询证券托管户：联想框文本框
     *
     * @param param
     * @return
     */
    List<SuggestDto> getSecManageAcctId(Map<String, Object> param);
    
    /**
     * 查询登录用户是否满足指定的角色岗位 
     *
     * @param param
     * @return
     */
    Long selectUserRolePosList(Map<String, Object> param);
}
