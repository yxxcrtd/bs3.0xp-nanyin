package com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage001Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage002Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.service.TaxSchBokCfgManage001Service;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.service.TaxSchBokCfgManage002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 场景计税配置管理
 *
 * @author zhangwei
 * @createTime 2019-07-13 11:33
 */
@Controller
@PageId("TaxSchBokCfgManage001")
@RequestMapping("/valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage001")
public class TaxSchBokCfgManage001Controller extends BaseController {

    @Autowired
    private TaxSchBokCfgManage001Service taxSchBokCfgManage001Service;

    @Autowired
    private TaxSchBokCfgManage002Service taxSchBokCfgManage002Service;

    @Override
    public String init(Model model) {

        super.setSelectOptionToModel(model, DictCode.会计场景代码);
        super.setSelectOptionToModel(model, DictCode.增值税类型);
        return super.init(model);
    }

    /**
     * 获取会计场景计税配置 - 分页
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findTaxSchaBokConfig")
    public Page<TaxSchBokCfgManage001Dto> findTaxSchaBokConfig() {
        return taxSchBokCfgManage001Service.findTaxSchaBokConfigByPage(RequestUtil.getPageParam());
    }

    /**
     * 删除通用 check
     *
     * @param dto
     * @param result
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) TaxSchBokCfgManage002Dto dto, BindingResult bindingResult)
        throws Exception {
        String operateType = dto.getOperateType();
        if (!bindingResult.hasErrors()) {
            int records = taxSchBokCfgManage001Service.countUsed(dto.getConfigId());
            if (records > 0) {
                return new AjaxResult("当前配置已被使用，不可删除!", MessageType.错误消息);
            }
        }

        return commonCheck(bindingResult, dto.getOperateType());
    }

    /**
     * 删除会计场景计税配置 并启动流程
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteTaxSchaBokConfig", method = RequestMethod.POST)
    public AjaxResult deleteTaxSchaBokConfig(@RequestBody @Validated({
        ValidateGroupDelete.class}) TaxSchBokCfgManage002Dto dto, BindingResult br) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<TaxSchBokCfgManage002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxSchBokCfgManage002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            dto = taxSchBokCfgManage002Service.findByPrimaryKey(dto.getConfigId());// dto 为回显值

            workFlowParameter.setPageId("TaxSchBokCfgManage002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getConfigId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(
                entity -> WorkFlowUtil.concatComment("会计场景计税配置", entity.getConfigId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            msg = taxSchBokCfgManage002Service.start(processParameter, dto);

        }
        catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }

    /**
     * 导出：会计场景计税配置
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("exportTaxSchaBokConfig")
    public ResponseEntity<byte[]> exportTaxSchaBokConfig()
        throws Exception {

        List<TaxSchBokCfgManage001Dto> dtoList = taxSchBokCfgManage001Service.findTaxSchaBokConfig(
            RequestUtil.getPageParam());
        File outFile = taxSchBokCfgManage001Service.makeFile(
            "/com/joyintech/tams/valuation/taxparametermanage/taxschbokcfgmanage/conf/export_taxschbokcfgmanage.xml",
            dtoList);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

}
