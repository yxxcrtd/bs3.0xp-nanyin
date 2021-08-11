/*
 * 文件名：FileCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.service.Specialassets001Service;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.service.Specialassets002Service;
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
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 附件信息--业务组件
 * @author zhangnian
 * @version 1.0 2018年9月10日
 * @see Specialassets001Controller
 * @since 1.0
 */
@Controller
@PageId("Specialassets001")
@RequestMapping("/model/impairment/assetratingallocation/specialassets/Specialassets001")
public class Specialassets001Controller extends BaseController{

    /**
     * FileAttachmentProcess
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    @Autowired
    private Specialassets001Service specialassets001Service;
    @Autowired
	private Specialassets002Service specialassets002Service;
    
    @Override
    public String init(Model model) {
        String businessId = getParam("businessId");
        if(null != fileAttachmentProcess && StringUtil.isNotEmpty(businessId)) {
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(businessId);
            model.addAttribute("attachmentFiles", JsonUtils.object2Json(files));
        }
        // 画面参数设置
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), "fileCommonDto"));
        return super.init(model);
    }
    @ResponseBody
    @RequestMapping("/searchPage")
    public Page<ModAssetGradeSpecialConfigDto> findPage()
            throws Exception {
        return specialassets001Service.findPage(
                RequestUtil.getParameters(getRequest()));
    }
    @RequestMapping("getFinprodIdForImpairment")
    @ResponseBody
    public List<SelectOptionDto> getFinprodIdForImpairment(@RequestParam Map<String, Object> params) {
      
        return specialassets001Service.getFinprodIdForImpairment(params);
    }
    @SuppressWarnings("deprecation")
	@ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ModAssetGradeSpecialConfigDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ModAssetGradeSpecialConfigDto> processParameter = new ProcessParameter<ModAssetGradeSpecialConfigDto>();
        WorkFlowParameter<ModAssetGradeSpecialConfigDto> params = new WorkFlowParameter<ModAssetGradeSpecialConfigDto>();
        params.setPageId("Specialassets002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_特殊资产评级配置.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("资产代码"+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodId()+GlobalConstant.CLOSE_PARENTHESIS+
                "资产名称"+GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodName()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = specialassets002Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new ModAssetGradeSpecialConfigDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
//    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
//    	param.put("pageId", this.getPageId());
//        return specialassets001Service.export(this.getPageId(),param);
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
        List<ModAssetGradeSpecialConfigDto> listdto = new ArrayList<ModAssetGradeSpecialConfigDto>();
        if (datas == null) {
            listdto = specialassets001Service.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, ModAssetGradeSpecialConfigDto.class);
        }
        File outFile = specialassets001Service.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/assetratingallocation/specialassets/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(DateUtil.format(new Date())+"_特殊资产评级配置")
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

}
