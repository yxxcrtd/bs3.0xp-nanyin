/*
 * 文件名：Accountset001Controller.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2016年12月26日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.accountentry.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;

import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry002Dto;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry003Dto;

import com.joyintech.service.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry003Service;
import com.joyintech.tams.common.base.entity.BokSceneconfig;


/**
*
* 文件名：AccountsAdjustAccountEntry003Controller.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* @author wujie
* 修改时间：2018年5月16日
* 修改内容：会计记账场景配置
* 系统名称：估值核算系统V1.0
*/
//@RestController(value="serviceValAccountsAdjustAccountEntry003Controller")
@RestController("valAccountsAdjustAccountEntry003Ctl_dtx")
@RequestMapping("ValAccountsAdjustAccountEntry003")
public class ValAccountsAdjustAccountEntry003Controller extends BaseController {

    /**
     * AccountsAdjustAccountEntry003
     */
    @Autowired
    private ValAccountsAdjustAccountEntry003Service valAccountsAdjustAccountEntry003Service;

    
    /**
     * 查询分支配置信息
     * 
     * @param sceneNodeId 场景分支节点代码
     * @return 分支配置信息
     * @see
     * @since 1.0
     */
    @PostMapping("/findSceneConfig")
    public BokSceneconfig findSceneConfig(@RequestBody String sceneNodeId) {
        return valAccountsAdjustAccountEntry003Service.findSceneConfig(sceneNodeId);
    }
    
    
    /**
     * 数据重复性检查
     * 
     * @param dto 参数
     * @return 已存在的数量
     * @see
     * @since 1.0
     */
    @PostMapping("/selectExistsConfig")
    public int selectExistsConfig(@RequestBody ValAccountsAdjustAccountEntry003Dto dto) {
        return valAccountsAdjustAccountEntry003Service.selectExistsConfig(dto);
    }
    
    /**
     * 查询当前节点
     * 
     * @param sceneNodeId 场景配置id
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    @PostMapping("/findAllFSceneBySceneId")
    public SelectOptionDto findAllFSceneBySceneId(@RequestBody String sceneNodeId) {
        return valAccountsAdjustAccountEntry003Service.findAllFSceneBySceneId(sceneNodeId);
    }
    
    
    /**
     * 查询某个账套下的根节点
     * 
     * @param booksetId 账套代码
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    @PostMapping("/findRootNodeByBooksetId")
    public SelectOptionDto findRootNodeByBooksetId(String booksetId) {
        return valAccountsAdjustAccountEntry003Service.findRootNodeByBooksetId(booksetId);
    }
    

    /**
     * 影响记账元素下拉框初始化
     * param
     * @see
     * @since 1.0
     */
    @PostMapping("/findNodeIds")
    public List<SelectOptionDto> findNodeIds(@RequestBody Map<String, Object> param) {
        String nodeId = (String)param.get("nodeId");
        String sceneNodeId = (String)param.get("sceneNodeId");
        String pageModel = (String)param.get("pageModel");
        String detailPageModelAdd = (String)param.get("detailPageModelAdd");
        return valAccountsAdjustAccountEntry003Service.findNodeIds(nodeId,sceneNodeId,pageModel,detailPageModelAdd);
    }
    
    
    /**
     * 查找所有子节点
     * @param sceneNodeId 节点id
     * @return 所有子节点
     * @see
     * @since 1.0
     */
    @PostMapping("/findConfigByFSceneId")
    public List<SelectOptionDto> findConfigByFSceneId(@RequestBody String sceneNodeId) {
        return valAccountsAdjustAccountEntry003Service.findConfigByFSceneId(sceneNodeId);
    }
    

    /**
     * 检索场景分支与科目配置信息
     * 
     * @return AccountsAdjustAccountEntry002Dto list
     * @see
     * @since 1.0
     */
    @PostMapping("/findAccountEntryConfigData")
    public List<ValAccountsAdjustAccountEntry002Dto> findAccountEntryConfigData(@RequestBody Map<String, Object> params) {
        String sceneNodeId = (String)params.get("sceneNodeId");
        String dataType = (String)params.get("dataType");
        boolean isAddModel = (boolean)params.get("isAddModel");
        return valAccountsAdjustAccountEntry003Service.findAccountEntryConfigData(sceneNodeId,dataType,isAddModel);
    }


    /**
     * 保存数据
     * 
     * @param accountEntryDto 数据
     * @return WebResult
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/save")
    public void saveEntry(@RequestBody ValAccountsAdjustAccountEntry003Dto accountEntryDto) {
        valAccountsAdjustAccountEntry003Service.save(accountEntryDto);
    }
    
}
