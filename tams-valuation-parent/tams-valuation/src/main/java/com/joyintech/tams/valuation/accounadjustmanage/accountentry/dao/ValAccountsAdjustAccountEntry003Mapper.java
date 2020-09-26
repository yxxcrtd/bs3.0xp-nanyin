/*
 * 文件名：AccountsAdjustAccountEntry001Mapper.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月14日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry003Dto;
import com.joyintech.tams.common.base.entity.BokSceneconfig;
/**
*
* 文件名：AccountsAdjustAccountEntry003Mapper.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* @author wujie
* 修改时间：2018年5月16日
* 修改内容：会计记账场景配置
* 系统名称：估值核算系统V1.0
*/
@Repository
public interface ValAccountsAdjustAccountEntry003Mapper {

    /**
     * 上级节点控件初期化
     * 
     * @param booksetId 账套代码
     * @param fNodeId 父节点
     * @return 下拉菜单dto list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findAllFSceneNodeId(@Param(value = "booksetId") String booksetId,
                                              @Param(value = "fNodeId") String fNodeId);

    /**
     * 查询分支配置信息
     * 
     * @param sceneNodeId 场景分支节点代码
     * @return 分支配置信息
     * @see
     * @since 1.0
     */
    BokSceneconfig findSceneConfig(String sceneNodeId);

    /**
     * 检索场景分支与科目配置信息
     * 
     * @param sceneNodeId 场景分支节点
     * @param dataType 数据类型
     * @param isAddModel 是否是添加模式
     * @return AccountsAdjustAccountEntry002Dto list
     * @see
     * @since 1.0
     */
    List<ValAccountsAdjustAccountEntry002Dto> findAccountEntryConfigData(@Param(
        value = "sceneNodeId") String sceneNodeId, @Param(
            value = "DATA_TYPEN") String dataType, @Param(
                value = "isAddModel") boolean isAddModel);

    /**
     * 查询当前节点
     * 
     * @param sceneNodeId 场景配置id
     * @param fNodeId 父节点
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    SelectOptionDto findAllFSceneBySceneId(@Param(value = "sceneNodeId") String sceneNodeId,
                                           @Param(value = "fNodeId") String fNodeId);

    /**
     * 检索账套下的叶子科目
     * 
     * @param booksetId 账套代码
     * @return 下拉菜单选项
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findSubjectByBooksetId(String booksetId);

    /**
     * 金额元素控件初期化
     * 
     * @param tableId 会计场景代码
     * @param dictType 数据类型
     * @return 金额元素
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findDictIdsByTableId(@Param(value = "tableId") String tableId,
                                               @Param(value = "DICT_TYPEN") String dictType);

    /**
     * 数据重复性检查
     * 
     * @param dto 参数
     * @return 已存在的数量
     * @see
     * @since 1.0
     */
    int selectExistsConfig(ValAccountsAdjustAccountEntry003Dto dto);

    /**
     * 根据父节点，计算场景分支节点代码
     * 
     * @param params 参数
     * @return 场景分支节点代码
     * @see
     * @since 1.0
     */
    String selectSceneNodeId(Map<String, Object> params);

    /**
     * 判断这个分支是否存在
     * 
     * @param fsceneNodeid 父场景代码
     * @param booksetId 账套代码
     * @param nodeId 节点代码
     * @param nodeValue 节点值
     * @return 数量
     * @see
     * @since 1.0
     */
    int findSceneConfigCount(@Param(value = "fsceneNodeid") String fsceneNodeid,
                             @Param(value = "booksetId") String booksetId,
                             @Param(value = "nodeId") String nodeId,
                             @Param(value = "nodeValue") String nodeValue);

    /**
     * 场景分支配置表
     * 
     * @param accountEntryDto AccountsAdjustAccountEntry003Dto
     * @see
     * @since 1.0
     */
    void updateConfig(ValAccountsAdjustAccountEntry003Dto accountEntryDto);

    /**
     * 场景科目关系表
     * 
     * @param accountEntryDto AccountsAdjustAccountEntry003Dto
     * @see
     * @since 1.0
     */
    void deleteSubject(ValAccountsAdjustAccountEntry003Dto accountEntryDto);

    /**
     * 查询某个账套下的根节点
     * 
     * @param booksetId 账套代码
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    SelectOptionDto findRootNodeByBooksetId(String booksetId);

    /**
     * 查找当前账套下，树的二级节点的nodeId
     * 
     * @param booksetId 账套代码
     * @return 二级节点的nodeId
     * @see
     * @since 1.0
     */
    String findLevel2NodeId(String booksetId);

    /**
     * 影响记账元素下拉框初始化
     * 
     * @param nodeId 所选上级节点所在树的二级节点的节点代码
     * @param sceneNodeId 场景分支节点代码
     * @param dataType 数据类型
     * @param detailPageModelAdd 添加模式
     * @param pageModel 页面模式
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findNodeIds(@Param(value = "nodeId") String nodeId,
                                      @Param(value = "sceneNodeId") String sceneNodeId,
                                      @Param(value = "DATA_TYPEP") String dataType,
                                      @Param(value = "pageModel") String pageModel, @Param(
                                          value = "detailPageModelAdd") String detailPageModelAdd);

    /**
     * 查找所有子节点
     * 
     * @param sceneNodeId 节点id
     * @param dataType 数据类型
     * @return 所有子节点
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findConfigByFSceneId(@Param(value = "sceneNodeId") String sceneNodeId,
                                               @Param(value = "DATA_TYPEP") String dataType);
}
