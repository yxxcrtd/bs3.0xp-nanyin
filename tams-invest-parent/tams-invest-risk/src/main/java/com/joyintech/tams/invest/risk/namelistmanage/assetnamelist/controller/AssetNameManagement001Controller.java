package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagement002Service;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 名单管理--资产名单管理
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagement001Controller
 * @since 1.0
 */

@Controller
@PageId("AssetNameManagement001")
@RequestMapping("/invest/risk/namelistmanage/assetnamelist/AssetNameManagement001")
public class AssetNameManagement001Controller extends BaseController {

    @Autowired
    private AssetNameManagementService assetNameManagementService;

    @Autowired
    private AssetNameManagement002Service assetNameManagement002Service;
    @Override
    public String init(Model model) {
        model.addAttribute("RSK_INPUT_TYPE_MANUAL", DictCodeListExt2.名单操作方式_手工.getValue());
        model.addAttribute("RSK_INPUT_TYPE_BATCH", DictCodeListExt2.名单操作方式_批量.getValue());
        model.addAttribute("RSK_SUBJECT_TYPE_02", DictCodeListExt2.名单主体类型_资产.getValue());
        return super.init(model);
    }

    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskAccessListDto> selectAll()
        throws Exception {
        Page<RskAccessListDto> page = assetNameManagementService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }


    @ResponseBody
    @RequestMapping("selectListId")
    public List<SelectOptionDto> selectListId(@RequestParam Map<String, Object> param) {
        return assetNameManagementService.selectListId(param);
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
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) RskAccessListDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    /**
     * 删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) RskAccessListDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskAccessListDto> processParameter = new ProcessParameter<RskAccessListDto>();
        WorkFlowParameter<RskAccessListDto> params = new WorkFlowParameter<RskAccessListDto>();
        params.setBusinessCode(item -> item.getListId());
        params.setPageId("AssetNameManagement003");
        params.setElementId(dto.getElementId());
        params.setBusinessType(DictCodeList.共通业务类型_资产名单管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("AssetNameManagement001.listId")+
                    GlobalConstant.OPEN_PARENTHESIS+entity.getListId(), GlobalConstant.CLOSE_PARENTHESIS+
                    getMessage("AssetNameManagement001.listName")+GlobalConstant.OPEN_PARENTHESIS+
                    entity.getListName()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = assetNameManagement002Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new RskAccessListDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
}
