package com.joyintech.tams.valuation.lossmodel.phaseparameter.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.PdFactorConfigDto;
import com.joyintech.tams.valuation.lossmodel.phaseparameter.dto.ModStageParaDto;
import com.joyintech.tams.valuation.lossmodel.phaseparameter.service.ModStageParaService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;

/**
 *  减值阶段参数管理
 * @author zhangfei
 * @version 1.0 2021年6月28日14:40:18
 * @see ModStagePara001Controller
 * @since 1.0
 */
@Controller
@PageId("ModStagePara001")
@RequestMapping("/model/impairment/phaseparameter/ModStagePara001")
public class ModStagePara001Controller extends BaseController{
	
	@Autowired
	private ModStageParaService modStageParaService;
	
	/**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.减值阶段);
        return super.init(model);
    }
	
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ModStageParaDto> selectAll()
        throws Exception {
        Page<ModStageParaDto> page = modStageParaService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
	
    
    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = this.getRequestMap();
        List<ModStageParaDto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = modStageParaService.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, ModStageParaDto.class);
        }
        File outFile = modStageParaService.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/phaseparameter/conf/export_conf.xml",
                listdto);
        return ExportUtil.export( SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(DateUtil.parse(DateUtil.getCurrentDate())) + SystemGlobalConstant.CLOSE_PARENTHESIS + this.getMessage(this.getPageId()) +SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        return paraMap;
    }


    /**
     * @Description:工作流，处理数据
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("setStageParaSwitch")
    public AjaxResult setStageParaSwitch(@RequestBody ModStageParaDto dto)
            throws Exception {
        Map<String,Object> params=new HashMap<String,Object>();
        List<ModStageParaDto> selectedItem = dto.getSelectedItem();
        List<String> seqnoList = new ArrayList<>();
        for(ModStageParaDto modStageParaDto:selectedItem){
            seqnoList.add(modStageParaDto.getSeqno());
        }
        params.put("seqnoList",seqnoList);
        params.put("operateType",dto.getOperateType());
        String msg="";
        if(seqnoList.size()>0 && StringUtil.isNotEmpty(dto.getOperateType())){
            modStageParaService.setStageParaSwitch(params);
            msg="操作成功";
        }else{
            msg="其他错误";
        }
        return new AjaxResult(msg);
    }



	
}
