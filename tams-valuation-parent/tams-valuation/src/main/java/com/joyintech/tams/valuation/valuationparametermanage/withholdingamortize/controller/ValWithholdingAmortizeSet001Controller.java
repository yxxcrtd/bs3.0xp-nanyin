package com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.service.ValWithholdingAmortizeSet001Service;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.service.ValWithholdingAmortizeSet002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author tannxuan
 * @version 1.0 2019年11月29日
 * @see ValWithholdingAmortizeSet001Controller
 * @since 1.0
 */
@Controller
@PageId("ValWithholdingAmortizeSet001")
@RequestMapping("/valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet001")
public class ValWithholdingAmortizeSet001Controller extends BaseController {

    @Autowired
    private ValWithholdingAmortizeSet001Service valWithholdingAmortizeSet001Service;

    @Autowired
    private ValWithholdingAmortizeSet002Service valWithholdingAmortizeSet002Service;

    @Override
    public String init(Model model) {

        super.setSelectOptionToModel(model, DictCode.一次性费用类型);
        return super.init(model);
    }

    /**
     * 检索账套代码(只取理财账套)
     *
     * @return 账套代码联想框内容
     * @throws Exception exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId()
        throws Exception {
        return valWithholdingAmortizeSet001Service.getBooksetIdNew(RequestUtil.getPageParam());
    }

    /**
     * 列表查询
     * 
     * @return 列表信息
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValWithholdingAmortizeSet001Dto> queryPageList() {
        return valWithholdingAmortizeSet001Service.queryPageList(RequestUtil.getPageParam());
    }

    /**
     * 删除 check
     *
     * @param dto
     * @param bindingResult
     * @return ajaxResult
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValWithholdingAmortizeSet001Dto dto,
                            BindingResult bindingResult)
        throws Exception {
        String msg = StringUtil.EMPTY;
        String operateType = dto.getOperateType();
        if (!bindingResult.hasErrors()) {// 基础验证通过
            List<String> errorLlist = new ArrayList<String>();
            List<ValWithholdingAmortizeSet001Dto> list = dto.getListValWithholdingAmortizeSetDto();
            for (ValWithholdingAmortizeSet001Dto item : list) {
                ValWithholdingAmortizeSet002Dto dto2 = new ValWithholdingAmortizeSet002Dto();
                dto2.setFinprodId(item.getFinprodId());
                if (valWithholdingAmortizeSet001Service.check(dto2).size() > 0) {
                    errorLlist.add(item.getBooksetId() + "账套");
                }
            }
            if (!errorLlist.isEmpty()) {
                for (String item : errorLlist) {
                    msg = msg.concat(item + "、");
                }
                if (msg.length() > 0) {
                    msg = msg.substring(0, msg.length() - 1);
                }
                msg = msg.concat("存在预提待摊计提凭证，是否继续删除？");
                return new AjaxResult(msg, MessageType.确认消息);
            }
            else {
                msg = msg.concat("请确认是否要删除?");
                return new AjaxResult(msg, MessageType.确认消息);
            }
        }
        return commonCheck(bindingResult, operateType);
    }

    /**
     * 删除 并启动流程
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult deleteConfig(@RequestBody ValWithholdingAmortizeSet002Dto dto) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<ValWithholdingAmortizeSet002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValWithholdingAmortizeSet002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId("ValWithholdingAmortizeSet002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(item -> item.getBooksetId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_待摊设置.getValue());// 业务类型
            workFlowParameter.setComment(
                entity -> WorkFlowUtil.concatComment(entity.getBooksetId(), "待摊设置"));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            List<ValWithholdingAmortizeSet002Dto> list = dto.getListValWithholdingAmortizeSetDto();
            List<ValWithholdingAmortizeSet002Dto> showList = new ArrayList<>();
            for (ValWithholdingAmortizeSet002Dto item : list) {
                // Map<String, Object> map = Maps.newHashMap();
                // map.put("finprodId2", dto.getBooksetId());
                // item =
                // valWithholdingAmortizeSet002Service.findByPrimaryKey(item.getBooksetId());
                showList.add(item);
            }

            valWithholdingAmortizeSet002Service.start(processParameter,
                showList.toArray(new ValWithholdingAmortizeSet002Dto[showList.size()]));
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动待摊设置流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }

    /**
     * 导出数据
     *
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        List<ValWithholdingAmortizeSet001Dto> listdto;

        listdto = JsonUtils.json2List(datas, ValWithholdingAmortizeSet001Dto.class);

        File outFile = valWithholdingAmortizeSet001Service.makeFile(
            "/com/joyintech/tams/valuation/valuationparametermanage/withholdingamortize/conf/export_withholdingamortize.xml",
            listdto);
        return ExportUtil.export("费用待摊设置"  + SystemGlobalConstant.OPEN_PARENTHESIS
            + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
            + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
}
