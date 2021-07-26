package com.joyintech.tams.invest.riskredis.quota.redismgr.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
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
 * @see RskRedisParamConfig001Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/riskredis/quota/redismgr/RskRedisParamConfig001")
public class RskRedisParamConfig001Controller extends BaseController {

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
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<RskRedisParamConfigDto> selectForPage(){
        return rskRedisParamConfigService.selectForPage(
                RequestUtil.getParameters(getRequest()));
    }


    /**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) RskRedisParamConfigDto dto, BindingResult result ) {
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
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) RskRedisParamConfigDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 监督指标代码生成
        ProcessParameter<RskRedisParamConfigDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<RskRedisParamConfigDto> params = new WorkFlowParameter<>();
        params.setPageId("RskRedisParamConfig002");
        params.setElementId(dto.getElementId());
        // 金融产品代码作为业务编号
        params.setBusinessCode(IDGenerator.generate());
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_REDIS功能参数配置.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(BussinessDictCodeListRds.共通业务类型_REDIS功能参数配置.getValue());
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskRedisParamConfigService.start(processParameter,
                dto.getSelectedItem().toArray(new RskRedisParamConfigDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
}
    
