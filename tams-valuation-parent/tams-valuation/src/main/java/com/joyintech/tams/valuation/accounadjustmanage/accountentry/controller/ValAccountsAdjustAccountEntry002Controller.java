/*
 * 文件名：Accountset001Controller.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2016年12月26日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry003Service;
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
@Controller
@RequestMapping("/valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry002")
@PageId("ValAccountsAdjustAccountEntry002")
public class ValAccountsAdjustAccountEntry002Controller extends BaseController {

    /**
     * Accountset003Service
     */
    @Autowired
    private ValAccountsAdjustAccountEntry003Service valAccountsAdjustAccountEntry003Service;
    
    /**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public String init(Model model) {
    	
        model.addAllAttributes(getPageParam());
        
        String booksetId = getParam("booksetId");
    	String tableId = getParam("tableId");
        
        //科目控件初始化
        List<SelectOptionDto> subjects = valAccountsAdjustAccountEntry003Service.findSubjectByBooksetId(booksetId);
        model.addAttribute("subjects", JsonUtils.object2Json(subjects));
        
        //金额元素控件初始化
        List<SelectOptionDto> formEles = valAccountsAdjustAccountEntry003Service.findDictIdsByTableId(tableId);
        model.addAttribute("formEles", JsonUtils.object2Json(formEles));
        
        //借贷方向控件
        setSelectOptionToModel(model, DictCode.借贷方向);
        
        
        //记账网点控件初始化
        String accountNet = getParam("accountNet");
        if (StringUtil.isNotEmpty(accountNet)) {
            model.addAttribute("accountNetReal", accountNet);
        }
        else {
            // TODO
//            model.addAttribute("accountNetReal", DictCodeList.记账网点_资管网点.getValue());
        }
        
//        setSelectOptionToModel(model, DictCode.记账网点);
        
        setStaticDictCodeValueToModel(model, new String[] {"CD_FLAGC", "ACCOUNTSET_TYPE02"},
                DictCodeList.借贷方向_贷, DictCodeList.账套类别_理财账套);
        return super.init(model);
    }
    
    
    /**
     * 设置数据字典的固定值到前台
     *
     * @param model 画面
     * @param names 字典明细别名
     * @param codeLists 数据字典明细
     */
    protected void setStaticDictCodeValueToModel(Model model, String[] names, IDictCodeList... codeLists) {
        if(null == names || (names != null && names.length != codeLists.length)) {
            throw new RuntimeException("数据字典别名和数据字典明细的个数不一致");
        }
        for (int i=0;i<codeLists.length;i++) {
            IDictCodeList dictCodeList = codeLists[i];
            model.addAttribute(names[i], dictCodeList.getValue());
        }
    }
   
   
    /**
     * 获取分页参数
     * add by wujie on 20180515 remark:功能迁移时BaseController类无以下基础方法
     * @return 分页参数
     * @see
     */
    protected final Map<String, Object> getPageParam() {
        Map<String, Object> param = this.getRequestParams();
        String page = this.getRequest().getParameter("page");
        String rows = this.getRequest().getParameter("rows");
        // 当前页
        param.put(GlobalConstant.PAGE_NO_FIELD, StringUtil.integerValueOf(page, 1));
        // 模糊查询字符串
        param.put(GlobalConstant.LIKE_STRING, ValStringUtil.toLikeString(this.getParam(GlobalConstant.LIKE_STRING), 3));
        // 每页显示的条数
        param.put(GlobalConstant.PAGE_LIMIT_FIELD, StringUtil.integerValueOf(rows, 20));
        return param;
    }
    /**
     * 获取请求参数
     *add by wujie on 20180515 remark:功能迁移时BaseController类无以下基础方法
     * @return 请求参数
     * @see
     */
    protected final Map<String, Object> getRequestParams() {
        return RequestUtil.getParameters(this.getRequest(), LoggerFactory.getLogger(getClass()).isDebugEnabled());
    }

    /**
     * 保存的check
     * 
     * @param dto 数据
     * @param result 结果
     * @return WebResult
     * @see
     * @since 1.0
     */
    @RequestMapping("saveCheck")
    @ResponseBody
    public AjaxResult saveCheck(@RequestBody @Valid ValAccountsAdjustAccountEntry002Dto dto,
                               BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }
    
}
