/*
 * 文件名：Accountset001Controller.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2016年12月26日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.accountentry.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.definition.ValAccountEntryCodeValue;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ExportEntity;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry001Dto;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry001Service;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
/**
*
* 文件名：AccountsAdjustAccountEntry001Controller.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* @author wujie
* 修改时间：2018年5月14日
* 修改内容：会计记账场景配置
* 系统名称：估值核算系统V1.0
*/
@RestController("valAccountsAdjustAccountEntry001Ctl_dtx")
//@RestController(value="serviceValAccountsAdjustAccountEntry001Controller")
@RequestMapping("ValAccountsAdjustAccountEntry001")
public class ValAccountsAdjustAccountEntry001Controller extends BaseController {

    /**
     * Accountset001Service
     */
    @Autowired
    private ValAccountsAdjustAccountEntry001Service valAccountsAdjustAccountEntry001Service;
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @PostMapping("/findBooksets")
    public List<SelectOptionDto> findBooksets() {
        return valAccountsAdjustAccountEntry001Service.findBooksets();
    }

    /**
     * 场景模块下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @PostMapping("/findTableIds")
    public List<SelectOptionDto> findTableIds(@RequestBody Map<String, Object> param) {
        return valAccountsAdjustAccountEntry001Service.findTableIds(param);
    }
    /**
     * 检索账套代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetId")
    public List<SuggestDto> getBooksetId(@RequestBody Map<String, Object> params)
        throws Exception {
    	String param = (String) params.get("param");
        return valAssetBook001Service.getBooksetIdByBooksetType(param);
    }
    /**
     * 检索数据
     * 
     * param booksetId 账套代码
     * param nodeId 会计场景代码
     * param level 展开的最大级别
     * @return AccountSetDto list
     */
    @PostMapping("/findAccountEntry/{page}/{length}")
    public Page<ValAccountsAdjustAccountEntry001Dto> findAccountEntry(@RequestBody Map<String,Object> param,
                                                                      @PathVariable("page") int page, @PathVariable("length") int length) {
        String booksetId = param.get("booksetId") == null ? "" : (String)param.get("booksetId");
        String nodeId = (String)param.get("nodeId");
        String bookEvent = (String)param.get("bookEvent");        
        Integer level = param.get("level") == null ? -1 : (Integer)param.get("level");
        return valAccountsAdjustAccountEntry001Service.findAccountEntry(booksetId, nodeId, level,bookEvent);
    }

    /**
     * 删除分支节点
     * 
     * @param dto 行数据
     * @return WebResult
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/deleteEntry")
    public AjaxResult deleteEntry(@RequestBody ValAccountsAdjustAccountEntry001Dto dto) {
        valAccountsAdjustAccountEntry001Service.deleteEntry(dto);
        return new AjaxResult(getMessage("VAL_COMMON_I_0007"));
    }
    
    @PostMapping("/findAccountEntryByNodeId")
    public Date findAccountEntryByNodeId(@RequestBody String sceneNodeId) {
        return valAccountsAdjustAccountEntry001Service.findAccountEntryByNodeId(sceneNodeId);
    }
}
