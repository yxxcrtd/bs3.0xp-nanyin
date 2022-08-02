package com.joyintech.tams.disclosure.information.config.repInvestAccount.controller;

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
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dto.RepInvestAccountDto;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.service.RepInvestAccountService;
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
 * 投资账户信息维护
 * @author shengning
 *
 */

@Controller
@PageId("RepInvestAccount001")
@RequestMapping("/disclosure/information/config/repInvestAccount/RepInvestAccount001")
public class RepInvestAccount001Controller extends BaseController {

    @Autowired
    private RepInvestAccountService repInvestAccountService;
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
        return repInvestAccountService.queryProdIdInfo(RequestUtil.getPageParam());
    }

    /**
     * 投资账户信息维护查询
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("queryList")
    public Page<RepInvestAccountDto> selectInfo() {
        Map<String,Object> pageParam = RequestUtil.getPageParam();

        return repInvestAccountService.selectInfo(pageParam);
    }
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepInvestAccountDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupDelete.class}) RepInvestAccountDto dto, BindingResult br)
            throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto.setUpdateUser(LoginUserUtil.getUserName());
        ProcessParameter<RepInvestAccountDto> processParameter = new ProcessParameter<RepInvestAccountDto>();
        WorkFlowParameter<RepInvestAccountDto> params = new WorkFlowParameter<RepInvestAccountDto>();
        params.setPageId("RepInvestAccount002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_投资账户信息维护.getValue());
        params.setComment(entity ->  "产品主代码" + "(" + entity.getProdId() + ")");
        processParameter.setOperationType(OperationType.DELETE);
        processParameter.setWorkFlowParameter(params);
        String msg =repInvestAccountService.start(processParameter, dto.getSelectedItem().toArray(new RepInvestAccountDto[dto.getSelectedItem().size()]));
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
        return repInvestAccountService.export(this.getPageId(),
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
        return repInvestAccountService.checkImportData(fileName);
    }

    @ResponseBody
    @RequestMapping("importFile")
    public AjaxResult importFile(@RequestBody Map<String, Object> param)  {
        return repInvestAccountService.importFile(param);
    }
}
