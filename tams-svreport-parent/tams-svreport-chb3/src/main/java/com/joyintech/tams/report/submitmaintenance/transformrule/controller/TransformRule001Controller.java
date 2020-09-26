package com.joyintech.tams.report.submitmaintenance.transformrule.controller;

import java.io.File;
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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.report.submitmaintenance.transformrule.service.TransformRuleMService;

/**
 * 变形转换规则界面Controller 〈功能详细描述〉
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年12月10日
 * @see TransformRule001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrTransformRule001")
@RequestMapping("/svreport/submitmaintenance/transformrule/SvrTransformRule001")
public class TransformRule001Controller extends BaseController {
	/**
	 * transformRule001Service
	 */
	@Autowired
	private TransformRuleMService transformRuleMService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.维护数据状态, DictCode.生僻字类型);
        if(RequestUtil.getPageParam().containsKey("status")){
            model.addAttribute("isFromMain","01");
        }
        else{
            model.addAttribute("isFromMain","02");
        }
		model.addAttribute("TRANS_TYPE_UNC", DictCodeListExt3.生僻字类型_生僻字.getValue());
		return super.init(model);
	}

	/**
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("searchPage")
	public Page<TransformRule001Dto> searchPage() {
		Page<TransformRule001Dto> page = transformRuleMService.searchPage(RequestUtil.getPageParam());
		return page;
	}
	
    /**
     * 删除 <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult doDelete(@RequestBody TransformRule001Dto dto) {
        AjaxResult wb = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
        List<TransformRule001Dto> list = dto.getDeleteList();
        transformRuleMService.doDelete(list);
        return wb;
    }
    
    /**
    *
    * 业务画面导出事件
    * @return 结果
    * @see
    * @since 1.0
    */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
        List<TransformRule001Dto> dtoList = transformRuleMService.setDataList(RequestUtil.getParameters(getRequest()));
        File outFile = transformRuleMService.makeFile(
            "/com/joyintech/tams/report/submitmaintenance/transformrule/conf/export_conf.xml", dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                     + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                     + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    
}
