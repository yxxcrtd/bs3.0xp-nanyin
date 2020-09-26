package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfcommon.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfcommon.service.ValSztInterfCommon001Service;

/**
 * 科目信息表
 * @author hudongyang
 *
 */
@Controller
@PageId("ValSztInterfCommon001")
@RequestMapping("/valuation/accounadjustmanage/valsztinterfcommon/ValSztInterfCommon001")
public class ValSztInterfCommon001Controller extends BaseController{
	
	@Autowired
	private ValSztInterfCommon001Service valSztInterfCommon001Service;
	
/*	@Autowired
    private BaseCommonService baseCommonService;
	
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.深证通报文状态);
		String sysDate = baseCommonService.getSysDateString();
        if(sysDate == null || "".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        
        return super.init(model);
	}*/

	@RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId() {
        Map<String,Object> map= RequestUtil.getPageParam();
        return valSztInterfCommon001Service.getFinprodId(map);
    }
	
}
