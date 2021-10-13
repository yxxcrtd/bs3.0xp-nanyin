package com.joyintech.tams.cfets.dao;

import java.util.List;

import com.joyintech.tams.cfets.entity.CfgGroup;

public interface CfgGroupMapper {
	
	/**
     * 查询配置分组
     * 
     * @param gpcode 配置分组ID
     * @return 配置分组
     */
    public CfgGroup getCfgGroupByGpcode(String gpcode);

    /**
     * 查询配置分组列表
     * 
     * @param CfgGroup 配置分组
     * @return 配置分组集合
     */
    public List<CfgGroup> list(CfgGroup CfgGroup);

    /**
     * 新增配置分组
     * 
     * @param CfgGroup 配置分组
     * @return 结果
     */
    public int insertCfgGroup(CfgGroup CfgGroup);

    /**
     * 修改配置分组
     * 
     * @param CfgGroup 配置分组
     * @return 结果
     */
    public int updateCfgGroup(CfgGroup CfgGroup);

    /**
     * 删除配置分组
     * 
     * @param gpcode 配置分组ID
     * @return 结果
     */
    public int deleteCfgGroupById(String gpcode);

    /**
     * 批量删除配置分组
     * 
     * @param gpcodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteCfgGroupByIds(String[] gpcodes);

    /**
     * 批量前置
     * @param targetGroup
     */
    void updateGroupSortByUp(CfgGroup targetGroup);

    /**
     * 修改排序号
     * @param sourceGroup
     */
    void updateGroupSort(CfgGroup sourceGroup);

    /**
     * 批量后置
     * @param targetGroup
     */
    void updateGroupSortByDown(CfgGroup targetGroup);

}
