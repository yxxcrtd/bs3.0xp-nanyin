package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial001Dto;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial002Dto;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service.ProductSimulationTrial001Service;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service.ProductSimulationTrial002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 产品模拟试算controller
 * 
 * @author jiaobo
 * @version 1.0 2019年10月29日
 * @see ProductSimulationTrial001Dto
 * @since 1.0
 */
@Controller
@PageId(value = "ProductSimulationTrial001")
@RequestMapping("/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial001")
public class ProductSimulationTrial001Controller extends BaseController {

	@Autowired
	private ProductSimulationTrial001Service productSimulationTrial001Service;

	@Autowired
	private ProductSimulationTrial002Service productSimulationTrial002Service;
	
    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
	
    @SuppressWarnings("rawtypes")
    @Autowired(required=false)
    private LeaveMarkProcessService leaveMarkProcessService;

	@Override
	public String init(Model model) {
		SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.有效状态, null);
		List<SelectOptionDto> options = selectDto.getOptions();
		List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
		for (SelectOptionDto dto : options) {
			if (StringUtil.equals(DictCodeListExt1.有效状态_新建.getValue(), dto.getId())
					|| StringUtil.equals(DictCodeListExt1.有效状态_有效.getValue(), dto.getId())) {
				optionsNew.add(dto);
			}
		}
		ProductSimulationTrial001Dto entity = new ProductSimulationTrial001Dto();
        Date platdate = iPlatformDate.getSysDate();
        entity.setMockDate(platdate);
		model.addAttribute(DictCode.有效状态.getValue(),
				JsonUtils.objectArray2Json(optionsNew.toArray()));
		model.addAttribute("requirementUser", SecurityUtil.getLoginUser().getUid());
		model.addAttribute("entity", entity);
		return super.init(model);
	}

	/**
	 * 产品设计系列编号下拉框
	 * @return 下拉框列表
	 * @throws Exception Exception
	 */
	@RequestMapping("queryDesignIdInfo")
	@ResponseBody
	public Page<SelectOptionDto> queryDesignIdInfo(@RequestParam Map<String, Object> param)
			throws Exception {
		return productSimulationTrial001Service.queryDesignIdInfo(param);
	}

	/**
	 * 页面查询
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findPage")
	public Page<ProductSimulationTrial001Dto> findPage() {
		Page<ProductSimulationTrial001Dto> page = productSimulationTrial001Service.findPage(RequestUtil.getPageParam());
		return page;
	}

	/**
	 * 删除按钮点击时的check
	 *
	 * @param dto
	 *            dto
	 * @param br
	 *            BindingResult
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
    @ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody ProductSimulationTrial001Dto dto,
							BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        AjaxResult result = new AjaxResult();
        //判断是否在流程中已经存在正在撤销的数据
        if (WorkFlowUtil.isNotFromToDoList()) {
            ProcessMark processMark = new ProcessMark();
            processMark.setBusinessDataPk1(dto.getSeqNo());
            processMark.setBusinessDataPk2(DateUtil.format(dto.getMockDate()));
            processMark.setBusinessDataPk3(dto.getPrdDesignId());
            
            List<String> resultList =leaveMarkProcessService.getBusinessDataInFlow(processMark);
            
            if (!StringUtil.isEmptyList(resultList)) {
                errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getSeqNo())));
            }
        }
        if(!StringUtil.isEmptyList(errorList)) {
            result.setErrorList(errorList);  
        } 
       
        return result;
	}

	/**
	 * 删除操作
	 *
	 * @param dto dto
	 * @param br 验证信息
	 * @return 结果
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult delete(@RequestBody ProductSimulationTrial001Dto dto, BindingResult br)
			throws Exception {
	    ProductSimulationTrial002Dto productSimulationTrial002Dto = productSimulationTrial002Service.getProductSimulationTrialBySeqNo(dto.getSeqNo());
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("seqNo", dto.getSeqNo());
	    productSimulationTrial002Dto.setSimulationAssetsList(productSimulationTrial002Service.findTablePage(param).getRoot());
		ProcessParameter<ProductSimulationTrial002Dto> processParameter = new ProcessParameter<ProductSimulationTrial002Dto>();
		WorkFlowParameter<ProductSimulationTrial002Dto> workFlowParameter = new WorkFlowParameter<ProductSimulationTrial002Dto>();
		processParameter.setOperationType(OperationType.REVOKE);

		workFlowParameter.setElementId(dto.getElementId());
		workFlowParameter.setPageId("ProductSimulationTrial002");
		workFlowParameter.setBusinessCode(dto.getSeqNo());
		workFlowParameter.setBusinessType(DictCodeList.共通业务类型_新研发产品模拟试算.getValue());
		

        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("ProductSimulationTrial001.prdDesignId") + GlobalConstant.OPEN_PARENTHESIS + entity.getPrdDesignId()+ GlobalConstant.CLOSE_PARENTHESIS 
            +"_" + GlobalConstant.OPEN_PARENTHESIS + getMessage("ProductSimulationTrial001.mockDate") + DateUtil.format(entity.getMockDate())+ GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(workFlowParameter);
        String msg = productSimulationTrial002Service.start(processParameter, productSimulationTrial002Dto);
		return new AjaxResult(msg);
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
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		return productSimulationTrial001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}

}
