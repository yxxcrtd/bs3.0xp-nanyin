/*
 * 文件名：AccountsAdjustAccountEntry001Service.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

import com.joyintech.tams.valuation.accounadjustmanage.accountentry.definition.ValAccountEntryCodeValue;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry003Dto;

import com.joyintech.tams.common.base.entity.BokSceneconfig;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
*
* 文件名：AccountsAdjustAccountEntry003Service.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：会计分录管理新建修改用service
* 系统名称：估值核算系统V1.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ValAccountsAdjustAccountEntry003Service extends CommonExportService<ValAccountsAdjustAccountEntry003Dto> {

    



    /**
     * 查询分支配置信息
     * 
     * @param sceneNodeId 场景分支节点代码
     * @return 分支配置信息
     * @see
     * @since 1.0
     */
    public BokSceneconfig findSceneConfig(String sceneNodeId) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry003/findSceneConfig", sceneNodeId, BokSceneconfig.class);
    }
    
    /**
     * 数据重复性检查
     * 
     * @param dto 参数
     * @return 已存在的数量
     * @see
     * @since 1.0
     */
    public int selectExistsConfig(ValAccountsAdjustAccountEntry003Dto dto) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry003/selectExistsConfig",dto,Integer.class);
    }
    
    /**
     * 查询当前节点
     * 
     * @param sceneNodeId 场景配置id
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    public SelectOptionDto findAllFSceneBySceneId(String sceneNodeId) {
        if (StringUtil.isEmpty(sceneNodeId)) {
            SelectOptionDto dto = new SelectOptionDto();
            dto.setId(StringUtil.EMPTY);
            dto.setText(getMessage(ValAccountEntryCodeValue.根节点.getCode()));
            dto.setCol("0");
            return dto;
        }
        
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry003/findAllFSceneBySceneId",sceneNodeId,SelectOptionDto.class);
    }
    
    /**
     * 查询某个账套下的根节点
     * 
     * @param booksetId 账套代码
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    public SelectOptionDto findRootNodeByBooksetId(String booksetId) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry003/findRootNodeByBooksetId",booksetId,SelectOptionDto.class);
    }
    
    /**
     * 影响记账元素下拉框初始化
     * 
     * @param nodeId 所选上级节点所在树的二级节点的节点代码
     * @param sceneNodeId 场景分支节点代码
     * @param detailPageModelAdd 添加模式
     * @param pageModel 页面模式
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findNodeIds(String nodeId, String sceneNodeId, String pageModel, String detailPageModelAdd) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("nodeId", nodeId);
        param.put("sceneNodeId", sceneNodeId);
        param.put("pageModel", pageModel);
        param.put("detailPageModelAdd", detailPageModelAdd);
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry003/findNodeIds", param, SelectOptionDto.class);
    }
    
    /**
     * 查找所有子节点
     * @param sceneNodeId 节点id
     * @return 所有子节点
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findConfigByFSceneId(String sceneNodeId) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry003/findConfigByFSceneId",sceneNodeId, SelectOptionDto.class);
    }
    
    /**
     * 检索场景分支与科目配置信息
     * 
     * @param sceneNodeId 场景分支节点代码
     * @param isAddModel 
     * @return AccountsAdjustAccountEntry002Dto list
     * @see
     * @since 1.0
     */
    public List<ValAccountsAdjustAccountEntry002Dto> findAccountEntryConfigData(String sceneNodeId, boolean isAddModel) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sceneNodeId", sceneNodeId);
        params.put("dataType", DictCodeListExt1.元素数据类型_数值型.getValue());
        params.put("isAddModel", isAddModel);
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry003/findAccountEntryConfigData",params,ValAccountsAdjustAccountEntry002Dto.class);
    }
    
    /**
     * 检索账套下的叶子科目
     * 
     * @param booksetId 账套代码
     * @return 下拉菜单选项
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findSubjectByBooksetId(String booksetId) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry002/findSubjectByBooksetId",booksetId, SelectOptionDto.class);
    }
    
    /**
     * 金额元素控件初期化
     * 
     * @param tableId 会计场景代码
     * @return 金额元素
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findDictIdsByTableId(String tableId) {
        
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry002/findDictIdsByTableId",tableId, SelectOptionDto.class);
    }
    
    /**
     * 保存会计场景配置信息
     * 
     * @param accountEntryDto AccountsAdjustAccountEntry003Dto
     * @see
     * @since 1.0
     */
    public void save(ValAccountsAdjustAccountEntry003Dto accountEntryDto) {
        
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry003/save",accountEntryDto,String.class);
    }
    
    
   
}
