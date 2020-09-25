package com.joyintech.tams.invest.product.nolimit.occupy.controller;

import java.util.Date;
import java.util.List;

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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.dto.NonStandardAvailableCredit001Dto;
import com.joyintech.tams.invest.product.nolimit.occupy.dto.OccupyQuery001Dto;
import com.joyintech.tams.invest.product.nolimit.occupy.service.OccupyQuery001Service;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 非标占比查询
 */
@Controller
@PageId("OccupyQuery001")
@RequestMapping("/invest/product/nolimit/occupy/OccupyQuery001")
public class OccupyQuery001Controller extends BaseController{
	
	@Autowired
	private OccupyQuery001Service occupyQuery001Service;
	@Override
	public String init(Model model) {
		setSelectOptionToModel(model,DictCode.查询口径);
		model.addAttribute("QUERY_CALIBER_ALL", DictCodeListExt3.查询口径_全部查询.getValue());
		model.addAttribute("QUERY_CALIBER_PRODSERIES",DictCodeListExt3.查询扣经_按产品系列.getValue());
		model.addAttribute("platDate",DateUtil.platDateFromCache().getTime());
		model.addAttribute("queryDay",DateUtil.addDay(DateUtil.platDateFromCache(),-1));
		return super.init(model);
	}
	
	/**
	 * 全部查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectForPageByAll")
	public List<OccupyQuery001Dto> selectForPageByAll(){
		return occupyQuery001Service.selectForPageByAll(RequestUtil.getPageParam());
	}
	/**
	 * 按产品系列查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectForPageByProdSeries")
	public Page<OccupyQuery001Dto> selectForPageByProdSeries(){
		return occupyQuery001Service.selectForPageByProdSeries(RequestUtil.getPageParam());
	}
	
	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return occupyQuery001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}
	
	/**
	 * 查询区间日期check
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("checkDate")
	@ResponseBody
	public AjaxResult checkDate() {
		AjaxResult result = new AjaxResult();
		Date platDate = DateUtil.platDateFromCache();
		String queryDay = this.getParam("queryDay");
		if (StringUtil.isNotEmpty(queryDay)) {
			if (DateUtil.compareDate(DateUtil.parse(queryDay), platDate) > 0) {
				result.setMessage("查询日不能大于平台日");
		 	}
  
		} else {
			result.setMessage("查询日不能为空");
		}
		result.setMessageType(MessageType.错误消息);
		return result;

	}
}
