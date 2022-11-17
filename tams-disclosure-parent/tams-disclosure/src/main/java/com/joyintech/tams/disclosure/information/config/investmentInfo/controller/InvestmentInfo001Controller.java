package com.joyintech.tams.disclosure.information.config.investmentInfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dto.InvestmentInfo001Dto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.service.InvestmentInfo001Service;
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

@PageId("InvestmentInfo001")
@Controller
@RequestMapping("/disclosure/information/config/investmentInfo/InvestmentInfo001")
public class InvestmentInfo001Controller extends BaseController {

    @Autowired
    private InvestmentInfo001Service investmentInfo001Service;

    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        this.setSelectOptionToModel(model, DictCode.前十大投资者信息类别);
        this.setSelectOptionToModel(model, DictCode.前十大投资者信息指标类型);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<InvestmentInfo001Dto> selectAll(){
        Page<InvestmentInfo001Dto> page = investmentInfo001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) InvestmentInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(){
        String pageId = this.getPageId();
        Map<String, Object> parameters = RequestUtil.getParameters(getRequest());
        return investmentInfo001Service.export(this.getPageId(),
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
        return investmentInfo001Service.checkImportData(fileName);
    }


    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName)  {
        return investmentInfo001Service.importData(fileName);
    }
}
