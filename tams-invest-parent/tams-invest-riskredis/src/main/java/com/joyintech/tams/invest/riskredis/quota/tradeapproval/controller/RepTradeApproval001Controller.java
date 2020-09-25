package com.joyintech.tams.invest.riskredis.quota.tradeapproval.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.riskredis.quota.tradeapproval.dto.RepTradeApprovalDto;
import com.joyintech.tams.invest.riskredis.quota.tradeapproval.service.RepTradeApprovalService;

/**
 * @program: tams-invest-parent
 * @description: RepTradeApproval001
 * @author: liqianqian
 * @create: 2019-09-18 14:29
 **/
@Controller
@PageId("RepTradeApproval001")
@RequestMapping("/invest/riskredis/quota/tradeapproval/RepTradeApproval001")
public class RepTradeApproval001Controller extends BaseController  {

    /**
     * 交易查询service
     */
    @Autowired
    private RepTradeApprovalService repTradeApprovalService;
    
    /**
	 * 初始化的查询条件
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		this.setSelectOptionToModel(model);
		model.addAttribute("entrustId",this.getParam("entrustId"));
		return super.init(model);
	}

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepTradeApprovalDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RepTradeApprovalDto> page = repTradeApprovalService.selectForPage(map);
        return page;
    }

}
