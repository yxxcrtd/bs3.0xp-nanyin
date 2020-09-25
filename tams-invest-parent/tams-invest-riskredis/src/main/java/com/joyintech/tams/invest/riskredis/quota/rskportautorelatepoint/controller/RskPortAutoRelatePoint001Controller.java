package com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.controller;

import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.dto.RskPortAutoRelatePointDeleteDto;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.dto.RskPortAutoRelatePointDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.service.RskPortAutoRelatePointService;
/**
 * 组合自动关联监控指标
 * @author 王泽慧
 * @version 1.0 2019年8月1日
 * @see RskPortAutoRelatePoint001Controller
 * @since 1.0
 */
@Controller
@PageId("RskPortAutoRelatePoint001")
@RequestMapping("/invest/riskredis/quota/rskportautorelatepoint/RskPortAutoRelatePoint001")
public class RskPortAutoRelatePoint001Controller extends BaseController {
	@Autowired
	private RskPortAutoRelatePointService rskPortAutoRelatePointService;
	
	@Override
    public String init(Model model) {

		this.setSelectOptionToModel(model, BussinessDictCodeRds.指标类型,BussinessDictCodeRds.单位,BussinessDictCodeRds.监督类型,BussinessDictCodeRds.操作符,BussinessDictCodeRds.运算符,BussinessDictCodeRds.连接符);
		model.addAttribute("NONE", BussinessDictCodeListRds.单位_无.getValue());
		return super.init(model);
	}
	
	
	/**
     * 查询指标名称
     * @return 所有数据
     * @throws Exception 异常
     */
    @RequestMapping("/getRuleName")
    public String getRuleName(Model model) {
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("list", rskPortAutoRelatePointService.getRuleName(map));
        return "invest/riskredis/quota/rskportautorelatepoint/RskPortAutoRelatePoint004";
    } 
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskPortAutoRelatePointDto> selectForPage() throws Exception{
    	return rskPortAutoRelatePointService.selectForPage(RequestUtil.getPageParam());
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) RskPortAutoRelatePointDeleteDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}
