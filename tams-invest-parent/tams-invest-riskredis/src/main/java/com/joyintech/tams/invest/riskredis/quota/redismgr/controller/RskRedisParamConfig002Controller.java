package com.joyintech.tams.invest.riskredis.quota.redismgr.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.redismgr.dto.RskRedisParamConfigDto;
import com.joyintech.tams.invest.riskredis.quota.redismgr.service.RskRedisParamConfigService;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.service.RskSupervisionIndicator001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * redis参数配置
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskRedisParamConfig002Controller
 * @since 1.0
 */
@Controller
@PageId("RskRedisParamConfig002")
@RequestMapping("/invest/riskredis/quota/redismgr/RskRedisParamConfig002")
public class RskRedisParamConfig002Controller extends BaseController {

    @Autowired(required = false)
    private RskRedisParamConfigService rskRedisParamConfigService;

    @Autowired(required = false)
    private RskSupervisionIndicator001Service rskSupervisionIndicator001Service;
    /**
     * 初始化的查询条件
     */
    private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] {BussinessDictCodeRds.共通业务类型,
            BussinessDictCodeRds.REDIS模块
       };
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        List<SelectOptionDto> paramList = rskSupervisionIndicator001Service.getIndicatorParamList();
        paramList.stream().forEach(item->{
            item.setObject(null);
        });
        model.addAttribute("INDICATOR_PARAM", JsonUtils.objectArray2Json(paramList.toArray()));
        String commonBusinessType = this.getParam("commonBusinessType");
        String paraCode = this.getParam("paraCode");
        String workflowId = getParam("workflowId");
        //不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(commonBusinessType)&& StringUtil.isNotEmpty(paraCode)) {
            RskRedisParamConfigDto rskRedisParamConfigDto = rskRedisParamConfigService.getBasicInfo(
                    commonBusinessType,paraCode);
            model.addAttribute("entity", rskRedisParamConfigDto);
        }//来自于代办
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            //根据工作流与留痕处理提供的共通方法获取业务实体
            RskRedisParamConfigDto rskRedisParamConfigDto = rskRedisParamConfigService.getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            model.addAttribute("entity", rskRedisParamConfigDto);
        }
        return super.init(model);
    }

    /**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RskRedisParamConfigDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskRedisParamConfigDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 监督指标代码生成
        ProcessParameter<RskRedisParamConfigDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<RskRedisParamConfigDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 金融产品代码作为业务编号
        params.setBusinessCode(dto.getCommonBusinessType()+dto.getParaCode());
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_REDIS功能参数配置.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskRedisParamConfig001.commonBusinessType"),
                GlobalConstant.OPEN_PARENTHESIS, entity.getCommonBusinessType(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskRedisParamConfig001.paraName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getParaName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskRedisParamConfigService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
}
    
