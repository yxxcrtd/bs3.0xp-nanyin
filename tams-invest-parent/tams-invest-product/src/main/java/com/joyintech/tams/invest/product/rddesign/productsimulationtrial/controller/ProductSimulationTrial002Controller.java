package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.controller;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial001Dto;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial002Dto;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service.ProductSimulationTrial002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 新建产品模拟试算controller
 * 
 * @author jiaobo
 * @version 1.0 2019年10月29日
 * @see ProductSimulationTrial001Dto
 * @since 1.0
 */
@Controller
@PageId(value = "ProductSimulationTrial002")
@RequestMapping("/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002")
public class ProductSimulationTrial002Controller extends BaseController {
    
    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    
	@Autowired
	private ProductSimulationTrial002Service productSimulationTrial002Service;
	
	@Autowired
	private BussBenchMarketInfoService bussBenchMarketInfoService;

	@Override
	public String init(Model model) {
	    setSelectOptionToModel(model, DictCode.有效状态,DictCode.资产大类,DictCode.资产风险等级2);
	    
	    String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
	    String seqNo = this.getParam("seqNo");
	    ProductSimulationTrial002Dto entity = new ProductSimulationTrial002Dto();
	    
	    if (StringUtil.isNotBlank(workflowId)) {
	        entity = productSimulationTrial002Service.getBussinessEntityByWorkflowId(workflowId);
	    } else if(WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotBlank(seqNo)) {
	        entity = productSimulationTrial002Service.getProductSimulationTrialBySeqNo(seqNo);
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("seqNo", seqNo);
	        List<ProductSimulationTrial002Dto> list = productSimulationTrial002Service.findTablePage(param).getRoot();
	        if(!StringUtil.isEmptyList(list)) {
	            BigDecimal amount = BigDecimal.ZERO;
	            for(ProductSimulationTrial002Dto dto:list) {
	                amount = ArithmeticUtil.doAdd(amount,dto.getMockAmt());
	            }
	            entity.setMockAvailableAmt(ArithmeticUtil.subtract(entity.getPrdScale(),amount));
	        }
	        entity.setSimulationAssetsList(list);
	    } else {
	        Date platdate = iPlatformDate.getSysDate();
	        entity.setMockDate(platdate);
	    }
	    
	    model.addAttribute("entity",entity);
	    model.addAttribute("INT_TYPE_01",DictCodeList.利率类型_固定利率.getValue());
	    model.addAttribute("INT_TYPE_02",DictCodeList.利率类型_浮动利率.getValue());
		return super.init(model);
	}
    
	/**
	 * 
	 * 获取产品设计系列编号
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getPrdScale")
	public BigDecimal getPrdScale(@RequestBody Map<String , Object> param) {
        return productSimulationTrial002Service.getPrdScale(param);
	}
	
	
	@ResponseBody
	@RequestMapping("/doSimulationTrial")
	public ProductSimulationTrial002Dto doSimulationTrial(@RequestBody ProductSimulationTrial002Dto productSimulationTrial002Dto) {
	    return productSimulationTrial002Service.doSimulationTrial(productSimulationTrial002Dto);
	}
	
	@ResponseBody
	@RequestMapping("/findTablePage")
	public Page<ProductSimulationTrial002Dto> findTablePage(){
	    String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
	    String pageModel =  (String)RequestUtil.getPageParam().get("pageModel");
	    String seqNo = (String)RequestUtil.getPageParam().get("seqNo");
	    if(StringUtil.isNotBlank(workflowId)) {
	        ProductSimulationTrial002Dto entity = productSimulationTrial002Service.getBussinessEntityByWorkflowId(workflowId);
	        Page<ProductSimulationTrial002Dto> page = new Page<ProductSimulationTrial002Dto>();
	        page.setRoot(entity.getSimulationAssetsList());
	        return page;  
	    } else if(StringUtil.isNotBlank(seqNo) && StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
	        return productSimulationTrial002Service.findTablePage(RequestUtil.getPageParam()); 
	    }
	   
        return new Page<ProductSimulationTrial002Dto>();
	}
	
	
    /**
     * 保存提交前数据检查
     *
     * @param dto 画面参数
     * @param br valid结果
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody ProductSimulationTrial002Dto dto, BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        return commonCheck(br, dto.getOperateType());
    }

	
    /**
     * 保存操作
     *
     * @param dto dto
     * @param br 验证信息
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody ProductSimulationTrial002Dto dto, BindingResult br)
        throws Exception {
        ProcessParameter<ProductSimulationTrial002Dto> processParameter = new ProcessParameter<ProductSimulationTrial002Dto>();
        WorkFlowParameter<ProductSimulationTrial002Dto> workFlowParameter = new WorkFlowParameter<ProductSimulationTrial002Dto>();


        if(StringUtil.isBlank(dto.getSeqNo())) {
           dto.setSeqNo(IDGenerator.generate());
        }
        
        if(StringUtil.isBlank(dto.getBatchId())){
            Map<String,Object> param = new HashMap<String,Object>();
            
            param.put("mockDate", DateUtil.format(dto.getMockDate(),DateUtil.DEFAULT_OTHER_FORMAT));
            dto.setBatchId(productSimulationTrial002Service.getNewBacthId(param));
        }
        
        workFlowParameter.setElementId(dto.getElementId());
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setBusinessCode(dto.getSeqNo());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_新研发产品模拟试算.getValue());
        
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("ProductSimulationTrial001.prdDesignId") + GlobalConstant.OPEN_PARENTHESIS + entity.getPrdDesignId()+ GlobalConstant.CLOSE_PARENTHESIS 
            +"_" + GlobalConstant.OPEN_PARENTHESIS + getMessage("ProductSimulationTrial001.mockDate") + DateUtil.format(entity.getMockDate())+ GlobalConstant.CLOSE_PARENTHESIS));
        
        processParameter.setWorkFlowParameter(workFlowParameter);
        String msg = productSimulationTrial002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
}
