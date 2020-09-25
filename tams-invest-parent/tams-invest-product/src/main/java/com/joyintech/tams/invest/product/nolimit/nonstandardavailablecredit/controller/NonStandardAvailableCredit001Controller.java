package com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.dto.NonStandardAvailableCredit001Dto;
import com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.service.NonStandardAvailableCredit001Service;

/**
 * 非标可用额度查询
 * 
 * @author dongzhikang
 * @version 1.0 2019年12月10日
 * @see
 * @since 1.0
 */
@Controller
@PageId(value = "NonStandardAvailableCredit001")
@RequestMapping("/invest/product/nolimit/nonstandardavailablecredit/NonStandardAvailableCredit001")
public class NonStandardAvailableCredit001Controller extends BaseController {

	@Autowired
	NonStandardAvailableCredit001Service nonStandardAvailableCredit001Service;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		/*
		 * setSelectOptionToModel(model, new DictCode[] {DictCode.资产二级分类,
		 * DictCode.资产三级分类, DictCode.资产四级分类, DictCode.资产五级分类,
		 * DictCode.资产一级分类,DictCode.资产大类,DictCode.是否});
		 */
		return super.init(model);
	}

	/**
	 * 查询
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("selectForPage")
	public Page<NonStandardAvailableCredit001Dto> selectAll() throws Exception {

		Page<NonStandardAvailableCredit001Dto> page = nonStandardAvailableCredit001Service
				.selectForPage(RequestUtil.getParameters(getRequest()));
		return page;
	}

	/*
	 * check
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("check") public AjaxResult check(@RequestBody @Validated({
	 * ValidateGroupDelete.class}) NonStandardAvailableCredit001Dto dto) {
	 * AjaxResult result = new AjaxResult(); Map<String,Object> param =
	 * RequestUtil.getParameters(getRequest());
	 * 
	 * if(param.get(key).compareTo( DateUtil.platDateFromCache())<0){
	 * errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0018",
	 * getMessage("FinFloatMgtExpQuotation001.effDate")+DateUtil.format(
	 * finFloatMgtExpQuotation001Dto.getEffDate()), "平台日："+DateUtil.getSysDate())));
	 * } return result; }
	 */
	/**
	 * 查询区间日期
	 * check
	 * 
	 * @param NonStandardAvailableCredit001Dto
	 * @return
	 */
	@RequestMapping(value = "checkDate", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult checkDate(@RequestBody NonStandardAvailableCredit001Dto dto) {

		AjaxResult result = new AjaxResult();

		Date platDate = DateUtil.platDateFromCache();
		Date curveDate = dto.getCurveDate();
		if (curveDate != null) {
		if (curveDate.compareTo(platDate) > 0) {
			
				result.setMessage("查询日不能大于平台日");
		 	}

		} else if (curveDate == null) {
			result.setMessage("查询日不能为空");
		}
		result.setMessageType(MessageType.错误消息);
		return result;

	}
	
	 /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return nonStandardAvailableCredit001Service.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
