package com.joyintech.tams.invest.settle.balance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.util.StringUtil;
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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;
import com.joyintech.tams.invest.common.util.SettleCommonUtil;
import com.joyintech.tams.invest.settle.balance.dto.OutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.settle.balance.dto.OutflowManualMatchDto;
import com.joyintech.tams.invest.settle.balance.service.OutflowFundsCheckManagement001Service;

/**
 * @program: tams-invest-parent
 * @description: 流出现金流
 * @author: 强成西
 * @create: 2018-11-15 11:28
 **/
@Controller
@PageId("OutflowFundsCheckManagement001")
@RequestMapping("/invest/settle/balance/OutflowFundsCheckManagement001")
public class OutflowFundsCheckManagement001Controller extends BaseController {

    @Autowired
    private OutflowFundsCheckManagement001Service outflowFundsCheckManagement001Service;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.画面数据状态, DictCode.是否,
        DictCode.指令状态, DictCode.平安业务类型};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        Map<String, Object> params = new HashMap<>();
        SettleCommonUtil.setParams(params);
        model.addAllAttributes(params);
        return super.init(model);
    }

    /**
     * 分页获取非保本组合托管账户
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findEscrowAcctNo")
    public Page<SelectOptionDto> findEscrowAcctNo() {
        return outflowFundsCheckManagement001Service.findEscrowAcctNo();
    }

    /**
     * 分页获取非保本组合托管账户
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public Page<SelectOptionDto> getPortfolioId() {
        return outflowFundsCheckManagement001Service.getPortfolioId();
    }

    /**
     * 分页获取账户下组合的未匹配流水数量
     *
     * @return grid
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPortfolioUnmatched")
    public Page<PortfolioUnmatchedInfo> findPortfolioUnmatched() {
        return outflowFundsCheckManagement001Service.findPortfolioUnmatched();
    }

    /**
     * 查询系统流出流水
     *
     * @return grid
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findSysPage")
    public Page<OutflowFundsSystemFlowDto> findSysPage() {
        if (com.github.pagehelper.util.StringUtil.isEmpty(RequestUtil.getParam("acctNo"))) {
            return new Page<>();
        }
        return outflowFundsCheckManagement001Service.findSysPage(RequestUtil.getPageParam());
    }

    /**
     * 查询托管流出流水
     *
     * @return grid
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findEnPage")
    public Page<OutflowFundsSystemFlowDto> findEnPage() {
        if (com.github.pagehelper.util.StringUtil.isEmpty(RequestUtil.getParam("acctNo"))) {
            return new Page<>();
        }
        return outflowFundsCheckManagement001Service.findEnPage(RequestUtil.getPageParam());
    }

    /**
     * 手工匹配时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("manualMatchCheck")
    public AjaxResult manualMatchCheck(@RequestBody @Validated({ValidateGroupEdit.class}) OutflowManualMatchDto dto,
                                       BindingResult result) {
        return commonCheck(result, null);
    }

    /**
     * 手工匹配，打开匹配明细页面
     *
     * @param dtoString 实体类
     * @return check结果
     */
    @RequestMapping("OutflowFundsCheckManagement002")
    public String manualMatch(String dtoString, Model model) {
        OutflowManualMatchDto dto = JsonUtils.json2Object(dtoString, OutflowManualMatchDto.class);
        model.addAttribute("remark", dto.getEnFlow().get(0).getRemark());
        model.addAttribute("sysFlow", JsonUtils.object2Json(dto.getSysFlow()));
        model.addAttribute("enFlow", JsonUtils.object2Json(dto.getEnFlow()));
        model.addAttribute("match", true);
        return "invest/settle/balance/OutflowFundsCheckManagement002";
    }

    /**
     * 系统流水或托管流水区域，行取消匹配按下时，打开匹配明细页面
     *
     * @param seqNo 实体类
     * @return check结果
     */
    @RequestMapping("manualMatchCancel")
    public String manualMatchCancel(String seqNo, String sysOrEn, Model model) {
        OutflowManualMatchDto dto = outflowFundsCheckManagement001Service.selectMatchDtoForCancel(seqNo, sysOrEn);
        model.addAttribute("remark", dto.getEnFlow().get(0).getRemark());
        model.addAttribute("sysFlow", JsonUtils.object2Json(dto.getSysFlow()));
        model.addAttribute("enFlow", JsonUtils.object2Json(dto.getEnFlow()));
        model.addAttribute("match", false);
        model.addAttribute("groupId", dto.getGroupId());
        return "invest/settle/balance/OutflowFundsCheckManagement002";
    }

    /**
     * 明细页面，匹配按钮按下
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("manualMatch")
    public AjaxResult manualMatch(@RequestBody @Valid OutflowManualMatchDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return commonCheck(result, null);
        }
        outflowFundsCheckManagement001Service.manualMatch(dto);
        return new AjaxResult("操作完成。");
    }

    /**
     * 托管流水设置备注
     *
     * @param enSeqNos
     * @param remark
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("remarkSet")
    public AjaxResult remarkSet(String enSeqNos, String remark) {
        if (StringUtil.isNotEmpty(enSeqNos)) {
            outflowFundsCheckManagement001Service.remarkSet(StringUtil.splitToList(",", enSeqNos), remark);
        }
        return new AjaxResult("操作完成。");
    }

    /**
     * 明细页面，取消匹配按钮按下
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("cancelMatch")
    public AjaxResult cancelMatch(@RequestBody @Valid OutflowManualMatchDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return commonCheck(result, null);
        }
        outflowFundsCheckManagement001Service.cancelMatch(dto);
        return new AjaxResult("操作完成。");
    }

    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        Map<String, Object> searchParams = RequestUtil.getPageParam();
        String fileName = "流出资金核对-托管流水" + "(" + DateUtil.getCurrentDate() + ").xlsx";
        if (searchParams.get("isSys") != null && Boolean.parseBoolean((String)searchParams.get("isSys"))) {
            fileName = "系统流出资金" + "(" + DateUtil.getCurrentDate() + ").xlsx";
        }
        return outflowFundsCheckManagement001Service.export(fileName, RequestUtil.getParameters(getRequest()));
    }
}
