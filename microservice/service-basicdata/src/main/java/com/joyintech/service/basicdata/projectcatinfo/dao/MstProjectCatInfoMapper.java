/*
 * 文件名：MstProjectCatInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.716
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.projectcatinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import org.springframework.stereotype.Repository;
import com.joyintech.service.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;

/**
 * 项目类资产估值行情信息
 * 
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.716
 * @see MstProjectCatInfoMapper
 * @since 1.0
 */
@Repository
public interface MstProjectCatInfoMapper {

    /**
     * 分页查询
     *
     * @param param 查询条件
     * @return 分页数据数据
     */
    List<MstProjectCatInfoDto> selectForPage(Map<String, Object> pageParam);

    /**
     * 查询详细数据
     * 
     * @param param 查询条件
     * @return 详细数据
     */
    MstProjectCatInfoDto selectInfo(Map<String, Object> param);

    /**
     * 查询界面资产代码的联想文本框事件
     * @param map
     *
     * @return 资产名称和资产代码列表
     */
    List<SuggestDto> getAssetCode(Map<String, Object> map);
    
    /**
     * 新建界面资产代码的联想文本框事件
     * @param map 
     * 
     * @return 资产名称和资产代码列表
     */
    List<SelectOptionDto> getAssetCodeList(Map<String, Object> map);

    public int updateByPrimaryKeySelective(FinProductValuation finProductValuation);

    public int deleteByPrimaryKey(FinProductValuation finProductValuation);
    
    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(FinProductValuation record);
    
}
