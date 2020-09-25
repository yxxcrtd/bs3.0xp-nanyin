/*
 * 文件名：ReportTreeMenuConfiguration001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：huwei
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reporttree.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.disclosure.information.config.reporttree.dto.ReportTreeMenuConfiguration001Dto;




/**
 * 
 * @author huwei
 * @version 1.0 2018年8月2日
 * @see ReportTreeMenuConfiguration001Mapper
 * @since 1.0
 */
@Repository
public interface ReportTreeMenuConfiguration001Mapper {
    
   /**
    * 获取树表数据
    * @param params
    * @return
    */
	List<ReportTreeMenuConfiguration001Dto> findAllData(@Param ("tmpCName") String tmpCName);
    
    /**
     * 增加父级方法
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
	void AddParentNode(Map<String, Object> map);
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param repTree
     * @see
     * @since 1.0
     */
	void getAddAll(ReportTreeMenuConfiguration001Dto reportTreeMenuConfiguration001Dto);
    
    /**
     * 修改方法
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    void getEdit(Map<String, Object> map);
    
    /**
     * 修改Flag方法
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    void edit(Map<String, Object> map);
    
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValLoanMarkquery001Dto>
     * @see
     * @since 1.0
     */
    List<RepInfo> queryPageList(Map<String, Object> params);
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param treeId
     * @return
     * @see
     * @since 1.0
     */
    
    List<ReportTreeMenuConfiguration001Dto> checkData(@Param ("treeId") String treeId);
    
    /**
     * 根据Pid查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param Id
     * @return
     * @see
     * @since 1.0
     */
    List<ReportTreeMenuConfiguration001Dto> checkDataPid(@Param ("Id") String Id);
    
    
    /**
     * 删除方法
     * <一句话功能简述>
     * <功能详细描述>
     * @param Id
     * @return
     * @see
     * @since 1.0
     */
    int delete(@Param ("Id") String Id);
    
    /**
     * 根据treeId，PId查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    ReportTreeMenuConfiguration001Dto selectByKey(Map<String, Object> map);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<ReportTreeMenuConfiguration001Dto> findParentOrSAubTreeList(ReportTreeMenuConfiguration001Dto dto);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<String> getRoleIdList(ReportTreeMenuConfiguration001Dto dto);
    
    
    /**
     * 根据id查询
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<ReportTreeMenuConfiguration001Dto> selectByPrimaryKey(String treeId);

    /**
     * 检查根节点
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<ReportTreeMenuConfiguration001Dto> findAllPNode(@Param ("Id") String Id);
   
}
