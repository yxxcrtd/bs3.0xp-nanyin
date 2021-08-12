package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.controller;

import java.util.Map;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.service.ObjectLimitOccupation001Service;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.service.ObjectLimitOccupation002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 主体额度占用调整
 * 
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see ObjectLimitOccupation003Controller
 * @since 1.0
 */
@Controller
@PageId("ObjectLimitOccupation003")
@RequestMapping("/invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation003")
public class ObjectLimitOccupation003Controller extends BaseController {

	/**
	 * ObjectLimitOccupation001Service
	 */
	@Autowired
	private ObjectLimitOccupation001Service objectLimitOccupation001Service;

	/**
	 * 初始化的查询条件
	 */
	private static final IDictCode[] DICT_CODES = new DictCode[] { DictCode.额度分类 };
	
	@Autowired
    private IPlatformDate iPlatformDate;
	
	/**
	 * SingleLimitOccupation002Service
	 */
	@Autowired(required = false)
	private ObjectLimitOccupation002Service objectLimitOccupation002Service;
	
	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		ObjectLimitOccupationDto dto = new ObjectLimitOccupationDto();
		dto.setCustomerId(StringUtil.isEmpty(this.getParam("customerId")) ? null : this.getParam("customerId"));
		dto.setCreditMstId(StringUtil.isEmpty(this.getParam("creditMstId")) ? null : this.getParam("creditMstId"));
		dto.setCreateUser(SecurityUtil.getLoginUser().getUserName());
		model.addAttribute(GlobalConstant.ENTITY, dto);
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
	public Page<ObjectLimitOccupationDto> selectAll() throws Exception {
		Map<String, Object> param = RequestUtil.getParameters(getRequest());
		String platDate = iPlatformDate.getSysDateStr();
		param.put("ptDate", platDate);
		return objectLimitOccupation001Service.selectHistory(param);
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
	public AjaxResult delete(@RequestBody @Validated({ ValidateGroupDelete.class }) ObjectLimitOccupationDto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		// BussinessCodeParameter parameter = new BussinessCodeParameter();
		// parameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_单笔资产额度校验规则);
		ProcessParameter<ObjectLimitOccupationDto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ObjectLimitOccupationDto> params = new WorkFlowParameter<>();
		params.setPageId(this.getPageId());
		params.setElementId(dto.getElementId());
		// 授权代码作为业务编号
		String businessCode = dto.getSeqno();
		params.setBusinessCode(businessCode);
		params.setBusinessType(DictCodeListExt3.共通业务类型_主体额度占用调整.getValue());
		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> {
			return WorkFlowUtil.concatComment(getMessage("ObjectLimitOccupation001.customerId"),
					GlobalConstant.OPEN_PARENTHESIS, dto.getCustomerId(), GlobalConstant.CLOSE_PARENTHESIS,
					getMessage("ObjectLimitOccupation001.creditType"), GlobalConstant.OPEN_PARENTHESIS,
					entity.getCreditType(), GlobalConstant.CLOSE_PARENTHESIS);
		});
		processParameter.setWorkFlowParameter(params);
		String msg = objectLimitOccupation002Service.start(processParameter,
				dto.getSelectedItem().toArray(new ObjectLimitOccupationDto[dto.getSelectedItem().size()]));
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
		return objectLimitOccupation001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));

	}
	
	/**
	 * 查询操作人员
	 * @return
	 */
    @ResponseBody
    @RequestMapping("getUserName")
    public Page<SelectOptionDto> getUserName() {
    	return objectLimitOccupation001Service.getUserName(RequestUtil.getPageParam());
    }
    
}
