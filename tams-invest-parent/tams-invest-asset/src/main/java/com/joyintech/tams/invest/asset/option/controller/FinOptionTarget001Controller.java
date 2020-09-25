package com.joyintech.tams.invest.asset.option.controller; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luo
 * 修改时间：2019-12-19
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.option.dto.FinOptionTargetDto;
import com.joyintech.tams.invest.asset.option.service.FinOptionTargetService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@PageId("FinOptionTarget001")
@Controller
@RequestMapping("/invest/asset/option/FinOptionTarget001")
public class FinOptionTarget001Controller extends BaseController {

    @Autowired FinOptionTargetService finOptionTargetService;

    /**
     * 页面初始化
     * @param model
     * @return
     */
    @Override public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.期权资产类型);
        return super.init(model);
    }

    /**
     * 获取期权标的信息列表
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getFinprodcodeList")
    public List<SuggestDto> getFinprodcodeList(String param)
        throws Exception{
        return finOptionTargetService.getFinprodcodeList(param);
    }

    /**
     * 编辑前的校验
     * @param dto
     * @param editResult
     * @return
     */
    @ResponseBody
    @RequestMapping("editCheck")
    public AjaxResult editCheck(@RequestBody @Validated FinOptionTargetDto dto, BindingResult editResult) {
        AjaxResult wr = new AjaxResult();
        return wr;
    }
    /**
     * 删除选择的标的信息
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinOptionTargetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinOptionTargetDto> processParameter = new ProcessParameter<FinOptionTargetDto>();
        WorkFlowParameter<FinOptionTargetDto> params = new WorkFlowParameter<FinOptionTargetDto>();
        params.setPageId("FinOptionTarget002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getTargetId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_场外期权类资产信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("FinOptionTarget001.targetId") + "(" + entity.getTargetId() + ")" + "-"
                                    + SpringContextUtil.getTextValue("FinOptionTarget001.targetName") + "("
                                    + entity.getTargetName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finOptionTargetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinOptionTargetDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);

    }

    /**
     * 修改提交刪除前的校驗
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinOptionTargetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 分页获取期权标的信息
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinOptionTargetDto> selectAll()
        throws Exception {
        Page<FinOptionTargetDto> page = finOptionTargetService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return page;
    }

    /**
     * 导出Excel文件
     * @return
     * @throws Exception
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return finOptionTargetService.export(this.getPageId(),param);

    }
}
