package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto.PdFactorKpmgConfigDto;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.service.PdFactorKpmgConfig001Service;
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
 * @see PdFactorKpmgConfig001Controller
 * @since 1.0
 */
@Controller
@PageId("PdFactorKpmgConfig001")
@RequestMapping("/model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001")
public class PdFactorKpmgConfig001Controller extends BaseController{
	
	@Autowired
	private PdFactorKpmgConfig001Service pdFactorKpmgConfig001Service;
	
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
        return super.init(model);
    }
	
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PdFactorKpmgConfigDto> selectAll()
        throws Exception {
        Page<PdFactorKpmgConfigDto> page = pdFactorKpmgConfig001Service.selectForPage(
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) PdFactorKpmgConfigDto dto, BindingResult result) {
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
                        if (StringUtil.isNotEmpty(item.getVouchYear()) ){
                        	//wflValidInputParameter.setBusinessDataPk1(item.getSeqno());
							wflValidInputParameter.setBusinessDataPk1(item.getVouchYear());
                            if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                                errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"+" 年份: "+item.getVouchYear()));
                            }
                        }
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("seqno", item.getSeqno());
                       	int exists = pdFactorKpmgConfig001Service.checkBySeqNo(map);
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
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) PdFactorKpmgConfigDto dto, BindingResult br)
            throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<PdFactorKpmgConfigDto> processParameter = new ProcessParameter<PdFactorKpmgConfigDto>();
        WorkFlowParameter<PdFactorKpmgConfigDto> params = new WorkFlowParameter<PdFactorKpmgConfigDto>();
        params.setPageId("PdFactorConfig002");
        params.setElementId(dto.getElementId());
        //业务编号
        //String businessCode = dto.getVouchYear()+"_"+dto.getVouchQuarter();
        params.setBusinessCode(item -> item.getVouchYear()+"_"+DateUtil.format(new Date(),"yyyyMMdd_HH:mm:ss"));
        params.setBusinessType(DictCodeListExt3.共通业务类型_前瞻因子维护.getValue());
        // 设置流程业务说明:年份 + "(" +年份+ ")" + "-"  + "季度" + "-"  + "季度"
        params.setComment(
                entity -> "年份" + "(" + entity.getVouchYear() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = pdFactorKpmgConfig001Service.start(
                processParameter, dto.getSelectedItem().toArray(new PdFactorKpmgConfigDto[dto.getSelectedItem().size()]));
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
        List<PdFactorKpmgConfigDto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = pdFactorKpmgConfig001Service.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, PdFactorKpmgConfigDto.class);
        }
        File outFile = pdFactorKpmgConfig001Service.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/pddefaultmanager/pdfactorkpmgconfig/conf/export_conf.xml",
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
