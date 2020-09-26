/*
 * 文件名：AccountsAdjustAccountEntry001Service.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.system.user.dto.User;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.definition.ValAccountEntryCodeValue;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry001Dto;

import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
*
* 文件名：AccountsAdjustAccountEntry001Service.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：会计分录管理用service
* 系统名称：估值核算系统V1.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ValAccountsAdjustAccountEntry001Service extends CommonExportService<ValAccountsAdjustAccountEntry001Dto> {

    
    
    /**
     * 查询所有账套 <br/>
     * 用于所属账套控件初期化
     * 
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findBooksets() {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
            + "/ValAccountsAdjustAccountEntry001/findBooksets",null, SelectOptionDto.class);
    }
    
    
    /**
     * 查询所有会计场景 <br/>
     * 用于场景模块控件初期化
     * 
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findTableIds(Map<String, Object> param) {
        
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
            + "/ValAccountsAdjustAccountEntry001/findTableIds",param
             , SelectOptionDto.class);
    }
    
    /**
     * 检索数据
     * 
     * @param booksetId 账套代码
     * @param nodeId 会计场景代码
     * @param level 级别
     * @return AccountSetDto list
     */
    public Page<ValAccountsAdjustAccountEntry001Dto> findAccountEntry(String booksetId, String nodeId,
                                                                   Integer level) {
        
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("booksetId", booksetId);
        pageParam.put("nodeId", nodeId);
        pageParam.put("level", level);
        pageParam.put("bookEvent",getMessage(ValAccountEntryCodeValue.会计事件.getCode()));
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
            + "/ValAccountsAdjustAccountEntry001/findAccountEntry",pageParam, ValAccountsAdjustAccountEntry001Dto.class);
    }
    
    
    /**
     * 删除场景配置信息及其相关数据
     * 
     * @param dto 行数据
     * @see
     * @since 1.0
     */
    public void deleteEntry(ValAccountsAdjustAccountEntry001Dto dto) {
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountsAdjustAccountEntry001/deleteEntry",dto,AjaxResult.class);
    }
    /**
     * 从session中取出用户
     *
     * @return SysUser
     */
    public User getUserFromSession() {
        Session session = SecurityUtils.getSubject().getSession(false);
        User user = (User)session.getAttribute(GlobalConstant.CURRENT_USER);
        return user;
    }
    
    public List<SuggestDto> getBooksetId(Map<String, Object> param) {
       
    	
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry001/getBooksetId", param, SuggestDto.class);
    }
    
    /**
     * 查询更新时间
     * 
     * @param sceneNodeId 场景分支节点
     * @return 更新时间
     * @see
     * @since 1.0
     */
    public Date findAccountEntryByNodeId(String sceneNodeId) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry001/findAccountEntryByNodeId", sceneNodeId, Date.class);
    }
}
