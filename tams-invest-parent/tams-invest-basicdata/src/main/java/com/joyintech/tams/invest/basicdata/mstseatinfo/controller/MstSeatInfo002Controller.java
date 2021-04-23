package com.joyintech.tams.invest.basicdata.mstseatinfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.service.MstSaleDepartmentService;
import com.joyintech.tams.invest.basicdata.mstseatinfo.dto.MstCustomerDto;
import com.joyintech.tams.invest.basicdata.mstseatinfo.dto.MstSeatInfoDto;
import com.joyintech.tams.invest.basicdata.mstseatinfo.service.MstSeatInfoService;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("MstSeatInfo002")
@RequestMapping("/invest/basicdata/mstseatinfo/MstSeatInfo002")
public class MstSeatInfo002Controller extends BaseController {
	/**
	 * 席位信息管理service注入
	 */
	@Autowired
	private MstSeatInfoService mstSeatInfoService;
	
	/**
	 * 营业部管理service注入
	 */
	@Autowired
	private MstSaleDepartmentService mstSaleDepartmentService;
	
	 /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;
    
	/**
	 * 新建页面初始化
	 */

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		this.setSelectOptionToModel(model, new DictCode[] {DictCode.交易场所});
		List<SelectOptionDto> brokerIds = new ArrayList<SelectOptionDto>();
		 Map<String, Object> map = new HashMap<String, Object>();
		 try {
				brokerIds = getMstCustomerInfo();
				model.addAttribute("brokerIds", JsonUtils.objectArray2Json(brokerIds.toArray()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		String seatId = getParam("seatId");
		String workflowId = getParam("workflowId");
		 // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(seatId)) {
            // 根据主键获取业务实体
        	MstSeatInfoDto dto = new MstSeatInfoDto();
			dto.setSeatId(seatId);
        	MstSeatInfoDto entity = mstSeatInfoService.selectByPrimaryKey(dto);
            model.addAttribute("entity", entity);
        }
        // 来自代办
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
        	MstSeatInfoDto entity = mstSeatInfoService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
		return super.init(model);
	}
	
	
	/**
	 * 券商
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("getMstCustomerInfo")
    public List<SelectOptionDto> getMstCustomerInfo()
            throws Exception {
		    Map<String, Object> param = RequestUtil.getPageParam();
            List<SelectOptionDto> temp = mstSaleDepartmentService.getMstCustomerInfo(param);
            return temp;
        }
  
    /**
     * 保存按钮点击
     *
     * @param dto dto
     * @param result BindingResult
     * @throws Exception Exception
     * @return check结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("deprecation")
	@ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstSeatInfoDto dto, BindingResult result)
        throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        // 如果席位号为空的情况下，需要自动生成一个席位号，唯一
        if (StringUtil.isEmpty(dto.getSeatId())) {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_席位信息管理));
            String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                param) : IDGenerator.generate();
            dto.setSeatId(code);
        }
        ProcessParameter<MstSeatInfoDto> processParameter = new ProcessParameter<MstSeatInfoDto>();
        WorkFlowParameter<MstSeatInfoDto> params = new WorkFlowParameter<MstSeatInfoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSeatId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_席位信息管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("席位号") + "("+ entity.getSeatId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstSeatInfoService.start(processParameter, dto);
        return new AjaxResult(msg);

    }

	/**
	 * 保存按钮点击时的check
	 *
	 * @param dto
	 *            dto
	 * @param result
	 *            BindingResult
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) MstSeatInfoDto dto,
			BindingResult result) {
		if (result.getAllErrors().isEmpty()) {
			AjaxResult ar = commonCheck(result, dto.getOperateType());
			return ar;
		}
		return commonCheck(result, dto.getOperateType());
	}
	
	
	
	/**
	 * 根据选择的券商信息过滤交易场所
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("getTradeMarketBybrokerId")
    public List<SelectOptionDto> getTradeMarketBybrokerId()
            throws Exception {
			MstCustomerDto customerDto = new MstCustomerDto();
			List<MstCustomerDto> customerDtos = new ArrayList<>();
			Map<String, Object> param = RequestUtil.getParameters(getRequest());
		    if(StringUtil.isNotEmpty((String) param.get("customerId")) ){
		    customerDtos =  mstSeatInfoService.getTradeMarketBybrokerId(param);
		    if(null != customerDtos && customerDtos.size() > 0 ){
		    	customerDto = customerDtos.get(0);
		    }
		    }
	        SelectDto temp = this.getSelectDataFromDataDic(DictCode.交易场所, null);
	        List<SelectOptionDto> listFeeTypeQs = new ArrayList<SelectOptionDto>();
	        for (SelectOptionDto dto : temp.getOptions()) {
	        	//券商
	        	if( null != customerDto.getIsBroker() && customerDto.getIsBroker().equals("Y")){
	        		if(StringUtil.equals(dto.getId(),DictCodeListExt1.交易场所_上海证券交易所.getValue())){
	        			listFeeTypeQs.add(dto);
	        		}
	        		if(StringUtil.equals(dto.getId(),DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
	        			listFeeTypeQs.add(dto);
	        		}
	        	}else{
	        		//期货
	        		if(null != customerDto.getIsFutureCom() && customerDto.getIsFutureCom().equals("Y")) {
/*	        			if(StringUtil.equals(dto.getId(),DictCodeListExt1.交易场所_上海期货交易所.getValue())){
	        				listFeeTypeQs.add(dto);
	        			}*/
//	        			if(StringUtil.equals(dto.getId(),DictCodeListExt1.交易场所_中国金融期货交易所.getValue())){
//	        				listFeeTypeQs.add(dto);
//	        			}
	        		}
	        	}
	        }
		    return listFeeTypeQs;
        }
}
