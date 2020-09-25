package com.joyintech.tams.invest.riskredis.quota.entrustedapproval.controller;

import java.util.Map;

import com.joyintech.tams.invest.riskredis.quota.entrustedapproval.dto.RepEntrustedApprovalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.riskredis.quota.entrustedapproval.service.RepEntrustedApprovalService;



/**
 * 委托审批
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RepEntrustedApproval001Controller
 * @since 1.0  
 */
@Controller
@PageId("RepEntrustedApproval001")
@RequestMapping("/invest/riskredis/quota/entrustedapproval/RepEntrustedApproval001")
public class RepEntrustedApproval001Controller extends BaseController {

    /**
     * 接口日志查询service
     */
    @Autowired
    private RepEntrustedApprovalService repEntrustedApprovalService;

    /**
     * 接口日志查询的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
         this.setSelectOptionToModel(model);
         model.addAttribute("entrustId", this.getParam("entrustId"));
         return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepEntrustedApprovalDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RepEntrustedApprovalDto> page = repEntrustedApprovalService.selectForPage(map);
        return page;
    }

}
