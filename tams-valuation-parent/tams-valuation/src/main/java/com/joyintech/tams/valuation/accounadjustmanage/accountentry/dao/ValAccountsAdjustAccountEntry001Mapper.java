/*
 * 文件名：AccountsAdjustAccountEntry001Mapper.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月14日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry001Dto;

/**
*
* 文件名：AccountsAdjustAccountEntry001Mapper.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* @author wujie
* 修改时间：2018年5月16日
* 修改内容：会计记账场景配置
* 系统名称：估值核算系统V1.0
*/
@Repository
public interface ValAccountsAdjustAccountEntry001Mapper {
	
	/**
     * 查询所有账套
     * <br/> 用于所属账套控件初期化
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findBooksets();
    
    /**
     * 查询所有会计场景
     * <br/> 用于场景模块控件初期化
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findTableIds(Map<String, Object> param);
    
    /**
     * 根据条件查询会计分录数据
     * 
     * @param booksetId 所属账套
     * @param nodeId 场景模块
     * @param bookEvent 会计事件
     * @param DictType 数据类型
     * @param level 展开的最大级别
     * @param ACCOUNTEVENTS 会计事件的key
     * @return 数据
     * @see
     * @since 1.0
     */
    List<ValAccountsAdjustAccountEntry001Dto> findAccountEntry(@Param(value = "booksetId") String booksetId,
                                                            @Param(value = "nodeId") String nodeId,
                                                            @Param(value = "bookEvent") String bookEvent,
                                                            @Param(value = "DICT_TYPEP") String DictType,
                                                            @Param(value = "level") Integer level,
                                                            @Param(value = "ACCOUNTEVENTS") String ACCOUNTEVENTS);
    
    
    /**
     * 更新状态至删除
     * 
     * @param sceneNodeId 场景分支节点代码
     * @see
     * @since 1.0
     */
    void deleteConfig(@Param(value = "sceneNodeId") String sceneNodeId);
	
    /**
     * 更新状态至删除
     * 
     * @param sceneNodeId 场景分支节点代码
     * @param updateTime 更新时间
     * @param updateUser 更新人
     * @see
     * @since 1.0
     */
//    void updateSceneSubjectToD(@Param(value = "sceneNodeId") String sceneNodeId,
//                               @Param(value = "updateUser") String updateUser,
//                               @Param(value = "updateTime") Date updateTime);
    
    /**
     * 查询更新时间
     * 
     * @param sceneNodeId 场景分支节点
     * @return 更新时间
     * @see
     * @since 1.0
     */
    Date findAccountEntryByNodeId(String sceneNodeId);
}
