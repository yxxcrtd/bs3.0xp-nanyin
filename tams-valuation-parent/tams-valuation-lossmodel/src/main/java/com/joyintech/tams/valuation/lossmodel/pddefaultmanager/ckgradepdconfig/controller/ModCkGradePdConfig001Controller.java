package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.ckgradepdconfig.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.joyintech.framework.grid.Page;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.ckgradepdconfig.service.ModCkGradePdConfigService;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import javax.validation.Valid;

/**
 * @Description:存款评级PD表查询Controller
 * @auther: yuzj
 * @date: 2021/8/13
 * @param:
 * @return:
 */
@Controller
@PageId("ModCkGradePdConfig001")
@RequestMapping("/model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001")
public class ModCkGradePdConfig001Controller extends BaseController {

    @Autowired
    private ModCkGradePdConfigService modCkGradePdConfigService;

    /**
     * @Description:页面初始化
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @Override
    public String init(Model model) {
        ModGradePdConfigDto dto = new ModGradePdConfigDto();
        this.setSelectOptionToModel(model, DictCode.内部评级);
        //this.setSelectOptionToModel(model, DictCode.评级结果);
        String seqNo = getParam("seqNo");
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        Map<String, Object> params = RequestUtil.getPageParam();

        if(StringUtils.isNotEmpty(seqNo)){
            params.put("seqNo", seqNo);
            List<ModGradePdConfigDto> dtoList = modCkGradePdConfigService.selectByMap(params);
            if(dtoList.size()>0){
                dto = dtoList.get(0);
            }
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = modCkGradePdConfigService.getBussinessEntityByWorkflowId(workflowId);
        }
        model.addAttribute("entity",dto);
        return super.init(model);
    }

    /**
     * @Description:检查校验
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ModGradePdConfigDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return ajaxResult;
    }

    /**
     * @Description:删除检查校验
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult deleteCheck(@RequestBody @Valid ModGradePdConfigDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> map = RequestUtil.getPageParam();
        map.put("seqNo",dto.getSeqNo());
        map.put("configType", ModelDictCodeList.模型_减值配置类型_评级PD配置.getValue());//03为评级PD维护配置类型
        int exists = modCkGradePdConfigService.checkBySeqNoAndType(map);
        //评级PD维护是否被PD生成批处理调用过
        if(exists>0) {
            ajaxResult.setData(this.getMessage("当前PD评级数据已被使用，是否确认删除？", this.getMessage("SysCommon.save")));
            return ajaxResult;
        }
        return new AjaxResult();
    }


    /**
     * @Description:列表分页查询
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ModGradePdConfigDto> selectBalanceStatement()
            throws Exception {
        Page<ModGradePdConfigDto> page = modCkGradePdConfigService.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }

    @ResponseBody
    @RequestMapping("selectSum")
    public Page<ModGradePdConfigDto> selectSum() throws Exception {
        return modCkGradePdConfigService.selectSum(RequestUtil.getParameters(getRequest()));
    }

    /**
     * @Description: excle导出
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        ModGradePdConfigDto dto = new ModGradePdConfigDto();
        String vouchYear = (String)params.get("vouchYear");
        dto.setVouchYear(vouchYear);
        List<ModGradePdConfigDto> listdto;
        listdto = modCkGradePdConfigService.selectExportList(dto);
        File outFile = modCkGradePdConfigService.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/pddefaultmanager/ckgradepdconfig/conf/export_ckgradepdconfig.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(DateUtil.format(new Date())+"_存款评级PD维护记录")
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }


    /**
     * 导入数据文件之前校验
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkGradePdImportData", method = RequestMethod.POST)
    public AjaxResult checkGradePdImportData(String fileName)  throws Exception{
        return modCkGradePdConfigService.checkGradePdImportData(fileName);
    }


    /**
     * @Description:导入(数据处理)
     * @auther: yuzj
     * @date: 2021/7/1
     * @param: type (年份、评级机构、评级类型、评级结果)重复是否覆盖数据，0否，1是
     * @return:
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName, String type)
            throws Exception{
        return this.modCkGradePdConfigService.importFile(fileName,type);
    }



}

