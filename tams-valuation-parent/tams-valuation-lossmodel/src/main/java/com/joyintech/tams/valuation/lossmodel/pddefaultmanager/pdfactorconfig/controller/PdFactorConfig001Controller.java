package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.PdFactorConfigDto;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.service.PdFactorConfig001Service;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 *  前瞻因子维护
 * 〈功能详细描述〉
 * @author zhangfei
 * @version 1.0 2021年6月23日
 * @see PdFactorConfig001Controller
 * @since 1.0
 */
@Controller
@PageId("PdFactorConfig001")
@RequestMapping("/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001")
public class PdFactorConfig001Controller extends BaseController{
	
	@Autowired
	private PdFactorConfig001Service pdFactorConfig001Service;
	
	 /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
	/**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.季度);
		/*
		 * Date reportStartDate = DateUtil.platDateFromCache(); SimpleDateFormat sdf
		 * =new SimpleDateFormat("yyyy"); model.addAttribute("searchDataEnd",
		 * sdf.format(reportStartDate));
		 */
        return super.init(model);
    }
	
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PdFactorConfigDto> selectAll()
        throws Exception {
        Page<PdFactorConfigDto> page = pdFactorConfig001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
	
    
    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) PdFactorConfigDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("PdFactorConfig002");
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if(StringUtil.isNotEmpty(dto) && !StringUtil.isEmptyList(dto.getSelectedItem())){
                    dto.getSelectedItem().forEach(item->{
                        if (StringUtil.isNotEmpty(item.getVouchYear()) && StringUtil.isNotEmpty(item.getVouchQuarter())){
                        	//wflValidInputParameter.setBusinessDataPk1(item.getSeqno());
							wflValidInputParameter.setBusinessDataPk1(item.getVouchYear());
							wflValidInputParameter.setBusinessDataPk2(item.getVouchQuarter());
                            if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                                errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"+" 年份: "+item.getVouchYear()+" 季度: "+item.getVouchQuarter()));
                            }
                        }
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("seqno", item.getSeqno());
                       	int exists = pdFactorConfig001Service.checkBySeqNo(map);
                       	//配置因子只要被PD生成批处理调用过
                       	if(exists>0) {
                       	 	ajaxResult.setData(this.getMessage("当前前瞻因子数据已被使用，是否确认删除？", this.getMessage("SysCommon.delete")));
                       	 	return;
                       	 }
                        
                    });
                }
            }
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }else if(ajaxResult.getData()!=null) {
        	return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    
    /**
     * 删除操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) PdFactorConfigDto dto, BindingResult br)
            throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<PdFactorConfigDto> processParameter = new ProcessParameter<PdFactorConfigDto>();
        WorkFlowParameter<PdFactorConfigDto> params = new WorkFlowParameter<PdFactorConfigDto>();
        params.setPageId("PdFactorConfig002");
        params.setElementId(dto.getElementId());
        //业务编号
        //String businessCode = dto.getVouchYear()+"_"+dto.getVouchQuarter();
        params.setBusinessCode(item -> item.getVouchYear()+"_"+item.getVouchQuarter());
        params.setBusinessType(DictCodeListExt3.共通业务类型_前瞻因子维护.getValue());
        // 设置流程业务说明:年份 + "(" +年份+ ")" + "-"  + "季度" + "-"  + "季度"
        params.setComment(
                entity -> "年份" + "(" + entity.getVouchYear() + ")"
                        + "季度" + "(" + entity.getVouchQuarter() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = pdFactorConfig001Service.start(
                processParameter, dto.getSelectedItem().toArray(new PdFactorConfigDto[dto.getSelectedItem().size()]));
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
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = this.getRequestMap();
        List<PdFactorConfigDto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = pdFactorConfig001Service.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, PdFactorConfigDto.class);
        }
        File outFile = pdFactorConfig001Service.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/pddefaultmanager/pdfactorconfig/conf/export_conf.xml",
                listdto);
        return ExportUtil.export( SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(DateUtil.parse(DateUtil.getSysDate())) + SystemGlobalConstant.CLOSE_PARENTHESIS + this.getMessage(this.getPageId()) + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        return paraMap;
    }
	
	
}
