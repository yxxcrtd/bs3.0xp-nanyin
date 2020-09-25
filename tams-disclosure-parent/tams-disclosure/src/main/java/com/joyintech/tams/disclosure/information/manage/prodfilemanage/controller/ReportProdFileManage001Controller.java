package com.joyintech.tams.disclosure.information.manage.prodfilemanage.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.ReportModelManage001Dto;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.service.ReportModelManage001Service;
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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto.ReportProdFileManage001Dto;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.service.ReportProdFileManage001Service;

/**
 * 产品文件管理 Controller层
 * @author hpp
 *
 */
@Controller
@PageId("ReportProdFileManage001")
@RequestMapping("disclosure/information/manage/prodfilemanage/ReportProdFileManage001")
public class ReportProdFileManage001Controller extends BaseController{
	
	@Autowired
	private ReportProdFileManage001Service reportProdFileManage001Service;

	@Autowired
	private ReportModelManage001Service reportModelManage001Service;
	
	/**
	 * 重写init方法，加载页面
	 */
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.报表文件类别,DictCode.收益类型,DictCode.开放类型,
        		DictCode.维护数据状态,DictCode.募集方式,DictCode.币种,DictCode.产品收益标识);
        model.addAttribute("reportType", DictCodeListExt1.报表文件类别_产品说明书.getValue());
        return super.init(model);
    }
	
	/**
	 * 分页查询条件-产品代码下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPrdInfoSelectPF")
	@ResponseBody
	public List<SelectOptionDto> getPrdInfoSelectPF() throws Exception {
		List<SelectOptionDto> lists = reportProdFileManage001Service.getPrdInfoSelectPF(RequestUtil.getPageParam());
		return lists;
	}
	
	/**
	 * 分页查询条件-产品系列下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getProdSeriesSelectPF")
	@ResponseBody
	public List<SelectOptionDto> getProdSeriesSelectPF() throws Exception {
		List<SelectOptionDto> lists = reportProdFileManage001Service.getProdSeriesSelectPF(RequestUtil.getPageParam());
		return lists;
	}
	
	/**
    *
    * 业务画面分页查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    @ResponseBody
    @RequestMapping("searchPage")
	public Page<ReportProdFileManage001Dto> searchPage() {
		Map<String, Object> param = RequestUtil.getPageParam();
		return reportProdFileManage001Service.searchPage(param);
	}
    
    /**
	 * 编辑删除前的check
	 * @param dto
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult Check(@RequestBody @Validated({ValidateGroupEdit.class}) ReportProdFileManage001Dto dto, BindingResult result){
		return commonCheck(result, dto.getOperateType());
	}
	
	/**
	 * 根据主键删除数据
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult doDelete(@RequestBody ReportProdFileManage001Dto dto){
		AjaxResult ar = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
		reportProdFileManage001Service.doDelete(dto);
		return ar;
	}

	/**
	 * 导出合并文档
	 *
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("mergeExport")
	public ResponseEntity<byte[]> mergeExport() throws Exception {
		Map<String, Object> param = RequestUtil.getPageParam();
		ReportModelManage001Dto reportModelManage001Dto = (ReportModelManage001Dto) JsonUtils.map2entity(param,
				ReportModelManage001Dto.class);
		reportModelManage001Dto.setParam(param);
		return reportModelManage001Service.mergeExportSingle(reportModelManage001Dto);
	}
	
}
