/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.assets.bondasset.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.tams.valuation.assets.bondasset.service.ValBondassetquery001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 模板示例<br>
 * 债券类资产查询
 * 
 * @author QuJianZhong
 * @version 2.0 2018年11月29日
 * @see ValBondassetquery003Controller
 * @since 2.0
 */
@Controller
@PageId("ValBondassetquery003")
@RequestMapping("/valuation/assets/bondasset/ValBondassetquery003")
public class ValBondassetquery003Controller extends BaseController {
    
    /**
     * ValBondassetquery001Service
     */
    @Autowired
    private ValBondassetquery001Service valBondassetquery001Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
        	model.addAttribute(GlobalConstant.WORKFLOW_ID, workflowId);
        }
        return super.init(model);
    }

    /**
     * 债券类资产数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("searchList")
    @ResponseBody
    public List<ValBondassetquery001Dto> searchList() {
    	List<ValBondassetquery001Dto> list = new ArrayList<ValBondassetquery001Dto>();
    	String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
    	if(StringUtil.isNotEmpty(workflowId)) {
    		ValBondassetquery001Dto dto = valBondassetquery001Service.getBussinessEntityByWorkflowId(workflowId);
    		if (dto != null) {
    			list.add(dto);
    		}
    	}
        return list;
    }
}
