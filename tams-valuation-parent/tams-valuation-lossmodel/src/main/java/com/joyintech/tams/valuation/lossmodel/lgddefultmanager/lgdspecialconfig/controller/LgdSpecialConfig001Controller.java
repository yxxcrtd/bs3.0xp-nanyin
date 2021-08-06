/*
 * 文件名：ModGradePdConfigController.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */

package com.joyintech.tams.valuation.lossmodel.lgddefultmanager.lgdspecialconfig.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.lossmodel.lgddefultmanager.lgdspecialconfig.dto.LgdSpecialConfigDto;
import com.joyintech.tams.valuation.lossmodel.lgddefultmanager.lgdspecialconfig.service.LgdSpecialConfigService;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.gradepdconfig.service.ModGradePdConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;


/**
 * 特殊资产LGD维护查询Controller
 *
 * @author yuzj
 * @version 1.0 2021年6月23日
 * @see LgdSpecialConfig001Controller
 * @since 1.0
 */
@Controller
@PageId("LgdSpecialConfig001")
@RequestMapping("/model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig001")
public class LgdSpecialConfig001Controller extends BaseController {

    @Autowired
    private LgdSpecialConfigService lgdSpecialConfigService;
    @Autowired
    private ModGradePdConfigService modGradePdConfigService;

    /**
     * @Description:初始化页面
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @Override
    public String init(Model model) {
        LgdSpecialConfigDto dto = new LgdSpecialConfigDto();
//        this.setSelectOptionToModel(model, DictCode.评级类型);
//        this.setSelectOptionToModel(model, DictCode.评级机构);
//        this.setSelectOptionToModel(model, DictCode.评级结果);
        String seqNo = getParam("seqNo");
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        Map<String, Object> params = RequestUtil.getPageParam();

        if(StringUtils.isNotEmpty(seqNo)){
            params.put("seqNo", seqNo);
            List<LgdSpecialConfigDto> dtoList = lgdSpecialConfigService.selectByMap(params);
            if(dtoList.size()>0){
                dto = dtoList.get(0);
            }
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = lgdSpecialConfigService.getBussinessEntityByWorkflowId(workflowId);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) LgdSpecialConfigDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return ajaxResult;
    }

    /**
     * @Description:删除校验
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult deleteCheck(@RequestBody LgdSpecialConfigDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
//        //判断当前流程是否存在
//        boolean resultList = modGradePdConfigService.checkBusinessDataInFlow(dto);
//        if(resultList){
//            //流程存在情况
//            return new AjaxResult("该条数据在流程中，不能删除", MessageType.错误消息);
//        }
        Map<String, Object> map = RequestUtil.getPageParam();
        map.put("seqNo",dto.getSeqNo());
        map.put("configType","06");//06为特殊资产LGD维护配置类型
        int exists = modGradePdConfigService.checkBySeqNoAndType(map);
        //特殊资产LGD维护维护是否被PD生成批处理调用过
        if(exists>0) {
            ajaxResult.setData(this.getMessage("当前特殊资产LGD数据已被使用，是否确认删除？", this.getMessage("SysCommon.save")));
            return ajaxResult;
        }
        return new AjaxResult();
    }


    /**
     * @Description:分页查询列表
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<LgdSpecialConfigDto> selectBalanceStatement()
            throws Exception {
        Page<LgdSpecialConfigDto> page = lgdSpecialConfigService.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }


    /**
     * @Description:导出信息excel
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        LgdSpecialConfigDto dto = new LgdSpecialConfigDto();
        //资产代码可以为多个
        String finprodIds = (String)params.get("finprodIds");
        dto.setFinprodIds(finprodIds);
        List<LgdSpecialConfigDto> listdto;
        listdto = lgdSpecialConfigService.selectExportList(dto);
        File outFile = lgdSpecialConfigService.makeFile(
                "/com/joyintech/tams/model/impairment/lgddefultmanager/lgdspecialconfig/conf/export_ldspecialconfig.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(DateUtil.format(new Date())+"_特殊资产LGD维护记录")
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

}

