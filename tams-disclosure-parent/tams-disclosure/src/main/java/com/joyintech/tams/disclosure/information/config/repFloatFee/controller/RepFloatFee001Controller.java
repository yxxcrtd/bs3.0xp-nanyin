package com.joyintech.tams.disclosure.information.config.repFloatFee.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dto.RepFloatFeeDto;
import com.joyintech.tams.disclosure.information.config.repFloatFee.service.RepFloatFeeService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 周期型产品超额业绩报酬信息维护
 * @author shengning
 *
 */

@Controller
@PageId("RepFloatFee001")
@RequestMapping("/disclosure/information/config/repFloatFee/RepFloatFee001")
public class RepFloatFee001Controller extends BaseController {
    @Autowired
    RepFloatFeeService repFloatFeeService;


    /**
     * 初始化页面信息
     *
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.维护数据状态);
        return super.init(model);
    }
    /**
     * 根据产品代码模糊查询联系下拉框
     *
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public Page<SelectOptionDto> queryProdIdInfo() {
        return repFloatFeeService.queryProdIdInfo(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("queryList")
    public Page<RepFloatFeeDto> selectInfo() {
        Map<String,Object> pageParam = RequestUtil.getPageParam();
        return repFloatFeeService.selectInfo(pageParam);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepFloatFeeDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupDelete.class}) RepFloatFeeDto dto, BindingResult br)
            throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto.setUpdateUser(LoginUserUtil.getUserName());
        ProcessParameter<RepFloatFeeDto> processParameter = new ProcessParameter<RepFloatFeeDto>();
        WorkFlowParameter<RepFloatFeeDto> params = new WorkFlowParameter<RepFloatFeeDto>();
        params.setPageId("RepFloatFee002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品超额业绩报酬信息维护.getValue());
        params.setComment(entity ->  "产品代码" + "(" + entity.getProdId() + ")");
        processParameter.setOperationType(OperationType.DELETE);
        processParameter.setWorkFlowParameter(params);
        String msg = repFloatFeeService.start(processParameter, dto.getSelectedItem().toArray(new RepFloatFeeDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     *
     * 文件导出
     * <功能详细描述>
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
        return repFloatFeeService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return repFloatFeeService.checkImportData(fileName);
    }

    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName)  {
        return repFloatFeeService.importData(fileName);
    }
}
