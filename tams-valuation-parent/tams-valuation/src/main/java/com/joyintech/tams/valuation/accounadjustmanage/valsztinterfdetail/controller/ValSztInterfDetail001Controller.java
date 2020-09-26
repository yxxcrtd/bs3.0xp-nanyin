package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfdetail.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfdetail.dto.ValSztInterfDetail001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfdetail.service.ValSztInterfDetail001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 返回结果明细 从xybranch20190115版本合并过来 by yanya on 20191102
 * @author hudongyang
 *
 */
@Controller
@PageId("ValSztInterfDetail001")
@RequestMapping("/valuation/accounadjustmanage/valsztinterfdetail/ValSztInterfDetail001")
public class ValSztInterfDetail001Controller  extends BaseController{

	@Autowired
    private ValSztInterfDetail001Service valSztInterfDetail001Service;
	
	@Autowired
	private BaseCommonService baseCommonService;
	
	@Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.深圳通对账状态);
        this.setSelectOptionToModel(model, DictCode.深圳通对账类型);
		String sysDate=baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        
        return super.init(model);
	}
	
	/**
     * 深圳通对账结果明细页面查询方法
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
	@RequestMapping("findPage")
    @ResponseBody
    public Page<ValSztInterfDetail001Dto> findPage() throws Exception {
		Page<ValSztInterfDetail001Dto> page = valSztInterfDetail001Service.findPage(RequestUtil.getPageParam());
		return page;
	}
}
