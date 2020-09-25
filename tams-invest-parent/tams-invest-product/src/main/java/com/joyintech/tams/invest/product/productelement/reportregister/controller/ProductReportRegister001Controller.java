package com.joyintech.tams.invest.product.productelement.reportregister.controller;

import java.util.List;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.productelement.reportregister.dto.ProductReportRegister001Dto;
import com.joyintech.tams.invest.product.productelement.reportregister.service.ProductReportRegister001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 产品申报登记要素管理controller
 * 
 * @author yaozhenguo
 * @version 1.0 2019年10月8日
 * @see ProductReportRegister001Controller
 * @since 1.0
 */
@Controller
@PageId("ProductReportRegister001")
@RequestMapping("/invest/product/productelement/reportregister/ProductReportRegister001")
public class ProductReportRegister001Controller extends BaseController {

	@Autowired
	private ProductReportRegister001Service productReportRegister001Service;
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.产品期限,DictCode.产品运作模式);
		return super.init(model);
	}

	/**
	 * 页面查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectForPage")
	public Page<ProductReportRegister001Dto> selectForPage() {
		return productReportRegister001Service.selectForPage(RequestUtil.getPageParam());
	}
	
	/**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return productReportRegister001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
	 * 画面点击保存按钮时check
	 * 
	 * @param dto
	 *            实体类
	 * @param result
	 * @return check结果
	 */
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) ProductReportRegister001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}
    
    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo() {
        return productReportRegister001Service.queryProdIdInfo(RequestUtil.getPageParam());
    }
    
    /**
     * 行内标识码联想下拉框
     * 
     * @return 行内标识码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFinprodInsId")
    public List<SelectOptionDto> queryFinprodInsId() {
        return productReportRegister001Service.queryFinprodInsId(RequestUtil.getPageParam());
    }
    
    /**
	 * 删除
	 * 
	 * @param dto
	 * @param br
	 * @return 结果
	 * @throws Exception 异常
	 */
	@ResponseBody
	@RequestMapping(ACTION_DELETE)
	public AjaxResult save(@RequestBody ProductReportRegister001Dto dto, BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		// 生成产品ID
		if (!StringUtil.isNotEmpty(dto.getRepFinprodId())) {
			String repFinprodId = IDGenerator.generate();
			dto.setRepFinprodId(repFinprodId);
		}
		ProcessParameter<ProductReportRegister001Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ProductReportRegister001Dto> params = new WorkFlowParameter<>();
		params.setPageId(dto.getPageId());
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getRepFinprodId());
		params.setBusinessType(DictCodeListExt3.共通业务类型_产品申报登记要素管理.getValue());
		params.setComment(entity -> this.getMessage("ProductReportRegister001.repFinprodId") + "("
				+ entity.getRepFinprodId() + ")-" + this.getMessage("ProductReportRegister001.createTime") + "("
				+ DateUtil.format(entity.getCreateTime()) + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = productReportRegister001Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

}
