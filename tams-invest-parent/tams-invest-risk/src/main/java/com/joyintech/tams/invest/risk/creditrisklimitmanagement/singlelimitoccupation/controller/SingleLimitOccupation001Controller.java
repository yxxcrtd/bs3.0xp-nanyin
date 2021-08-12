package com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.dto.SingleLimitOccupationDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.service.SingleLimitOccupation001Service;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.service.SingleLimitOccupation002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 单笔资产额度校验规则查询
 * 
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupation001Controller
 * @since 1.0
 */
@Controller
@PageId("SingleLimitOccupation001")
@RequestMapping("/invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation001")
public class SingleLimitOccupation001Controller extends BaseController {

	/**
	 * SingleLimitOccupation001Service
	 */
	@Autowired
	private SingleLimitOccupation001Service singleLimitOccupation001Service;

	/**
	 * SingleLimitOccupation002Service
	 */
	@Autowired(required = false)
	private SingleLimitOccupation002Service singleLimitOccupation002Service;

	@Override
	public String init(Model model) {
		return super.init(model);
	}

	@ResponseBody
	@RequestMapping("/getLimitLabel")
	public Page<SelectOptionDto> getLimitLabel() throws Exception {
		Map<String, Object> map = RequestUtil.getPageParam();
		Page<SelectOptionDto> temp = singleLimitOccupation001Service.getLimitLabel(map);

		return temp;
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
	public Page<SingleLimitOccupationDto> selectAll() throws Exception {
		return singleLimitOccupation001Service.selectForPage(RequestUtil.getParameters(getRequest()));
	}

	/**
	 * 市场代码联想文本框
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getFinprodMarketIds")
	public Page<SelectOptionDto> getFinprodMarketIds(@RequestParam Map<String, Object> params) throws Exception {
		return singleLimitOccupation001Service.getFinprodMarketIds(params);
	}

	/**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) SingleLimitOccupationDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
    
	/**
	 * 删除
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(ACTION_DELETE)
	public AjaxResult delete(@RequestBody @Validated({ ValidateGroupDelete.class }) SingleLimitOccupationDto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<SingleLimitOccupationDto> processParameter = new ProcessParameter<SingleLimitOccupationDto>();
		WorkFlowParameter<SingleLimitOccupationDto> params = new WorkFlowParameter<SingleLimitOccupationDto>();
		params.setPageId("SingleLimitOccupation002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(entity -> entity.getCreditRuleId());
		params.setBusinessType(DictCodeListExt3.共通业务类型_单笔资产额度校验规则.getValue());
		dto.setCreditTagId(dto.getCreditTag());
		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> {
			return WorkFlowUtil.concatComment(
					getMessage("SingleLimitOccupation001.creditTag"), GlobalConstant.OPEN_PARENTHESIS,
					entity.getCreditTag(), GlobalConstant.CLOSE_PARENTHESIS);
		});
		processParameter.setWorkFlowParameter(params);
		String msg = singleLimitOccupation002Service.start(processParameter,
				dto.getSelectedItem().toArray(new SingleLimitOccupationDto[dto.getSelectedItem().size()]));
		return new AjaxResult(msg);
	}

	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 */
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return singleLimitOccupation001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));

	}
}
