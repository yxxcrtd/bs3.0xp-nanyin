package com.joyintech.tams.invest.riskredis.quota.medianmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.medianmanagement.dto.PltMedianManagementDto;
import com.joyintech.tams.invest.riskredis.quota.medianmanagement.service.PltMedianManagement001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 指标参数中间值管理
 * @author zhengjunping
 * @version 1.0 2019年11月19日
 * @see PltMedianManagement001Controller
 * @since 1.0
 */
@Controller
@PageId("PltMedianManagement001")
@RequestMapping("/invest/riskredis/quota/medianmanagement/PltMedianManagement001")
public class PltMedianManagement001Controller extends BaseController{
	@Autowired
	PltMedianManagement001Service pltMedianManagement001Service;
	
	/**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, BussinessDictCodeRds.参数粒度);
        model.addAttribute("platDate", DateUtil.format(DateUtil.platDateFromCache())+" - "+DateUtil.format(DateUtil.platDateFromCache()));
        return super.init(model);
    }
    
    /**
     * 分页查询
     * @return Page<PltMedianManagementDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PltMedianManagementDto> findPage() throws Exception {
    	Map<String, Object> map = RequestUtil.getParameters(getRequest());
		Object paraCode = map.get("paraCode");
		if (null != paraCode) {
			List<String> paraCodeList = StringUtil.splitToList(GlobalConstant.COMMA, String.valueOf(paraCode));
			map.put("paraCode", paraCodeList);
		}
		Object blank = map.get("blank");
		if(blank != null || blank != ""){
			map.put("blank", blank);
		}
		Object supDate = map.get("supDate");
		Object startSupDate = (supDate).toString().substring(0,10);
		map.put("startSupDate", startSupDate);
		Object endSupDate = (supDate).toString().substring(13,23);
		map.put("endSupDate", endSupDate);
		Page<PltMedianManagementDto> page = pltMedianManagement001Service.selectForPage(map);
		return page;
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0 
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return pltMedianManagement001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
    /**
	 * 查询画面风险事件名称联想框内容
	 * 
	 * @return 检索数据
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping(value = "selectMedianManagement", method = RequestMethod.GET)
	public Page<SelectOptionDto> selectMultiOptionEvent() throws Exception {
		Map<String, Object> map = RequestUtil.getParameters(getRequest());
		Page<SelectOptionDto> page = pltMedianManagement001Service.selectMedianManagement(map);
		return page;
	}
	
	/**
     * 查询按钮点击时的check
     *
     * @param dto dto
     * @return check结果
     * @see
     * @since 1.0
     */
	@ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) PltMedianManagementDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
}
