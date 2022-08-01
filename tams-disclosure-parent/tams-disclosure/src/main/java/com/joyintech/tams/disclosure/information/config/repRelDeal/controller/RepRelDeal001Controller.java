package com.joyintech.tams.disclosure.information.config.repRelDeal.controller;

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
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
import com.joyintech.tams.disclosure.information.config.repRelDeal.dto.RepRelDealDto;
import com.joyintech.tams.disclosure.information.config.repRelDeal.service.RepRelDealService;
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

import java.util.Map;

/**
 * 关联交易情况维护
 * @author shengning
 *
 */

@Controller
@PageId("RepRelDeal001")
@RequestMapping("/disclosure/information/config/repRelDeal/RepRelDeal001")
public class RepRelDeal001Controller extends BaseController {

    @Autowired
    private RepRelDealService repRelDealService;
    /**
     * 初始化页面信息
     *
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
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
        return repRelDealService.queryProdIdInfo(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("queryList")
    public Page<RepRelDealDto> selectInfo() {
        Map<String,Object> pageParam = RequestUtil.getPageParam();
        return repRelDealService.selectInfo(pageParam);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepProdIndexDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupDelete.class}) RepRelDealDto dto, BindingResult br)
            throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto.setUpdateUser(LoginUserUtil.getUserName());
        ProcessParameter<RepRelDealDto> processParameter = new ProcessParameter<RepRelDealDto>();
        WorkFlowParameter<RepRelDealDto> params = new WorkFlowParameter<RepRelDealDto>();
        params.setPageId("RepRelDeal002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_关联交易情况维护.getValue());
        params.setComment(entity ->  "产品主代码" + "(" + entity.getProdId() + ")");
        processParameter.setOperationType(OperationType.DELETE);
        processParameter.setWorkFlowParameter(params);
        String msg =repRelDealService.start(processParameter, dto.getSelectedItem().toArray(new RepRelDealDto[dto.getSelectedItem().size()]));
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
        return repRelDealService.export(this.getPageId(),
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
        return repRelDealService.checkImportData(fileName);
    }

    @ResponseBody
    @RequestMapping("importFile")
    public AjaxResult importFile(@RequestBody Map<String, Object> param)  {
        return repRelDealService.importFile(param);
    }
}
