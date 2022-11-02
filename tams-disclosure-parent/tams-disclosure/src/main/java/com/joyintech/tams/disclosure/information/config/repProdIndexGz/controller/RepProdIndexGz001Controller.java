package com.joyintech.tams.disclosure.information.config.repProdIndexGz.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
import com.joyintech.tams.disclosure.information.config.repProdIndexGz.dto.RepProdIndexGzDto;
import com.joyintech.tams.disclosure.information.config.repProdIndexGz.service.RepProdIndexGzService;
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
 * 产品基础指标信息维护
 * @author shengning
 *
 */

@Controller
@PageId("RepProdIndexGz001")
@RequestMapping("/disclosure/information/config/repProdIndexGz/RepProdIndexGz001")
public class RepProdIndexGz001Controller extends BaseController {

    @Autowired
    private RepProdIndexGzService repProdIndexGzService;
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
     * 根据产品代码模糊查询子产品下拉框
     *
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public Page<SelectOptionDto> queryProdIdInfo() {
        return repProdIndexGzService.queryProdIdInfo(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("queryList")
    public Page<RepProdIndexGzDto> selectInfo() {
        Map<String,Object> pageParam = RequestUtil.getPageParam();
        return repProdIndexGzService.selectInfo(pageParam);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepProdIndexGzDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupDelete.class}) RepProdIndexGzDto dto, BindingResult br)
            throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        List<RepProdIndexGzDto> selectedItem = dto.getSelectedItem();
        selectedItem.forEach(e->{
            e.setOperateType("DELETE");
        });
        ProcessParameter<RepProdIndexGzDto> processParameter = new ProcessParameter<RepProdIndexGzDto>();
        WorkFlowParameter<RepProdIndexGzDto> params = new WorkFlowParameter<RepProdIndexGzDto>();
        params.setPageId("RepProdIndexGz002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品净值信息_估值.getValue());
        // 业务说明：产品代码 + "(" + 产品代码 + ")" + "-" + "合作机构" + "-" + "生效日"
        params.setComment(entity -> "子产品代码" + "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = repProdIndexGzService.start(processParameter, selectedItem.toArray(new RepProdIndexGzDto[selectedItem.size()]));
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
        return repProdIndexGzService.export(this.getPageId(),
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
        return repProdIndexGzService.checkImportData(fileName);
    }

    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName) {
        return repProdIndexGzService.importData(fileName);
    }
}
