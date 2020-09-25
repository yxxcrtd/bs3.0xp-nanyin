package com.joyintech.tams.invest.riskredis.quota.rskevent.contorller;

import java.util.List;
import java.util.Map;

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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent001Dto;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent002Dto;
import com.joyintech.tams.invest.riskredis.quota.rskevent.service.RskEvent001Service;
import com.joyintech.tams.invest.riskredis.quota.rskevent.service.RskEvent002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 风险事件列表
 * 
 * @author 王朕
 * @version 1.0 2019年8月22日
 * @see RskEvent001Controller
 * @since 1.0
 */
@Controller
@PageId("RskEvent001")
@RequestMapping("/invest/riskredis/quota/rskevent/RskEvent001")
public class RskEvent001Controller extends BaseController {

	@Autowired
	private RskEvent001Service rskEvent001Service;

	@Autowired
	private RskEvent002Service rskEvent002Service;

	/**
	 * 初始化的查询条件
	 */
	private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] { BussinessDictCodeRds.风险事件特征值类型,
			BussinessDictCodeRds.运算符, BussinessDictCodeRds.操作符, BussinessDictCodeRds.特征值偏移单位 };

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		model.addAttribute("eventCode", this.getParam("eventCode"));
		return super.init(model);
	}

	/**
	 * 画面查询方法
	 * 
	 * @return 所有数据
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<RskEvent001Dto> selectAll() throws Exception {
		Map<String, Object> map = RequestUtil.getParameters(getRequest());
		Object eventCode = map.get("eventCode");
		if (StringUtil.isNotEmpty(eventCode)) {
			List<String> eventCodeList = StringUtil.splitToList(GlobalConstant.COMMA, String.valueOf(eventCode));
			map.put("eventCode", eventCodeList);
		}
		else{
			map.put("eventCode",null);
		}
		Page<RskEvent001Dto> page = rskEvent001Service.selectForPage(map);
		return page;
	}

	/**
	 * 查询画面风险事件名称联想框内容
	 * 
	 * @return 检索数据
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping(value = "selectMultiOptionEvent", method = RequestMethod.GET)
	public Page<SelectOptionDto> selectMultiOptionEvent() throws Exception {
		Map<String, Object> map = RequestUtil.getParameters(getRequest());
		if(StringUtil.isNotEmpty(map.get("search"))&&StringUtil.byteLength(map.get("search").toString())>200){
			return null;
		}
		else {
			Page<SelectOptionDto> page = rskEvent001Service.selectMultiOptionEvent(map);
			return page;
		}
	}

	/**
	 * 保存按钮点击时的check
	 * 
	 * @return check结果
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupDelete.class }) RskEvent002Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@DTXTransaction
	@ResponseBody
	@RequestMapping(ACTION_DELETE)
	public AjaxResult delete(@RequestBody @Validated({ ValidateGroupDelete.class }) RskEvent002Dto dto,
			BindingResult br) throws Exception {

		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<RskEvent002Dto> processParameter = new ProcessParameter<RskEvent002Dto>();
		WorkFlowParameter<RskEvent002Dto> params = new WorkFlowParameter<RskEvent002Dto>();
		params.setPageId("RskEvent002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getEventCode());
		params.setBusinessType(BussinessDictCodeListRds.共同业务类型_风险事件管理.getValue());
		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> "风控事项:" + "("+ entity.getEventName()+")"
		/*{
			return WorkFlowUtil.concatComment(getMessage("RskEvent001.eventCode"), GlobalConstant.OPEN_PARENTHESIS,
					entity.getEventCode(), GlobalConstant.CLOSE_PARENTHESIS, getMessage("RskEvent002.eventName"),
					GlobalConstant.OPEN_PARENTHESIS, entity.getEventName(), GlobalConstant.CLOSE_PARENTHESIS);
		}*/
		);
		processParameter.setWorkFlowParameter(params);
		String msg = rskEvent002Service.start(processParameter,
				dto.getSelectedItem().toArray(new RskEvent002Dto[dto.getSelectedItem().size()]));
		return new AjaxResult(msg);
	}

	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 */
//	@RequestMapping(ACTION_EXPORT)
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return rskEvent001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}

}
