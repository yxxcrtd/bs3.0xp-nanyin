/*
 * 文件名：Accountset001Controller.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2016年12月26日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.accountentry.controller;

import java.util.List;
import java.util.Map;

import javax.jws.WebResult;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry002Dto;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry003Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.ValStringUtil;

/**
*
* 文件名：AccountsAdjustAccountEntry002Controller.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* @author wujie
* 修改时间：2018年5月16日
* 修改内容：会计记账场景配置
* 系统名称：估值核算系统V1.0
*/
@RestController(value="serviceValAccountsAdjustAccountEntry002Controller")
@RequestMapping("/ValAccountsAdjustAccountEntry002")
public class ValAccountsAdjustAccountEntry002Controller extends BaseController {

    /**
     * Accountset003Service
     */
    @Autowired
    private ValAccountsAdjustAccountEntry003Service valAccountsAdjustAccountEntry003Service;
    
    /**
     * 检索账套下的叶子科目
     * 
     * @param booksetId 账套代码
     * @return 下拉菜单选项
     * @see
     * @since 1.0
     */
    @PostMapping("/findSubjectByBooksetId")
    public List<SelectOptionDto> findSubjectByBooksetId(@RequestBody String booksetId) {
        return valAccountsAdjustAccountEntry003Service.findSubjectByBooksetId(booksetId);
    }
    
    /**
     * 金额元素控件初期化
     * 
     * @param tableId 会计场景代码
     * @return 金额元素
     * @see
     * @since 1.0
     */
    @PostMapping("/findDictIdsByTableId")
    public List<SelectOptionDto> findDictIdsByTableId(@RequestBody String booksetId) {
        return valAccountsAdjustAccountEntry003Service.findDictIdsByTableId(booksetId);
    }
    
}
