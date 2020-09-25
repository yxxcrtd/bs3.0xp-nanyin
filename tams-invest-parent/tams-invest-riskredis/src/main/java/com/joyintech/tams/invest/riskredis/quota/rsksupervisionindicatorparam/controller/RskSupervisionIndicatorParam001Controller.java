package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * 指标参数管理controller
 *
 * @author liuwei
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicatorParam001Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionIndicatorParam001")
@RequestMapping("/invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam001")
public class RskSupervisionIndicatorParam001Controller extends BaseController {

    /**
     * 指标参数管理service
     */
    @Autowired
    private RskSupervisionIndicatorParam001Service rskSupervisionIndicatorParamService;

    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] { BussinessDictCodeRds.指标参数实现类型
    };
    /**
     * 指标参数管理的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	setSelectOptionToModel(model, DICT_CODES);
    	model.addAttribute("FIX", BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
    	model.addAttribute("PLUGIN", BussinessDictCodeListRds.指标参数实现类型_类名.getValue());
    	model.addAttribute("SQL", BussinessDictCodeListRds.指标参数实现类型_SQL.getValue());
    	model.addAttribute("CUSTOM", BussinessDictCodeListRds.指标参数实现类型_自定义.getValue());
        model.addAttribute("ipatFormDate",iPlatformDate.getSysDateStr());
        return super.init(model);
    }
    /**
     * 从资管跳转监指标参数查询
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @RequestMapping("outInit")
    public String outInit(Model model, HttpServletRequest request){
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String key=paramNames.nextElement();
            model.addAttribute(key,request.getParameter(key));
        }
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("FIX", BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
        model.addAttribute("PLUGIN", BussinessDictCodeListRds.指标参数实现类型_类名.getValue());
        model.addAttribute("SQL", BussinessDictCodeListRds.指标参数实现类型_SQL.getValue());
        model.addAttribute("CUSTOM", BussinessDictCodeListRds.指标参数实现类型_自定义.getValue());
        model.addAttribute("ipatFormDate",iPlatformDate.getSysDateStr());
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskSupervisionIndicatorParamDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RskSupervisionIndicatorParamDto> page = rskSupervisionIndicatorParamService.selectForPage(map);
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
    public AjaxResult check(@RequestBody  @Validated({
        ValidateGroupDelete.class}) RskSupervisionIndicatorParamDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) RskSupervisionIndicatorParamDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskSupervisionIndicatorParamDto> processParameter = new ProcessParameter<RskSupervisionIndicatorParamDto>();
        WorkFlowParameter<RskSupervisionIndicatorParamDto> params = new WorkFlowParameter<RskSupervisionIndicatorParamDto>();
        params.setPageId("RskSupervisionIndicatorParam002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getParaCode());
        if(StringUtil.equals(dto.getParaType(), BussinessDictCodeListRds.指标参数实现类型_固定值.getValue())){
        	params.setBusinessType(BussinessDictCodeListRds.共通业务类型_固定值参数管理.getValue());
        }else{
        	params.setBusinessType(BussinessDictCodeListRds.共通业务类型_语言参数管理.getValue());
        }
     // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionIndicatorParam002.paraCode"),
                GlobalConstant.OPEN_PARENTHESIS, dto.getParaCode(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionIndicatorParam003.paraName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getParaName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndicatorParamService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return rskSupervisionIndicatorParamService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
