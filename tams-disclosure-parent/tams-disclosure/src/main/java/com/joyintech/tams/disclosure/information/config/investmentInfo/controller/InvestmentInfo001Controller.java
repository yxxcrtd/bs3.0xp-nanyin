package com.joyintech.tams.disclosure.information.config.investmentInfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dto.InvestmentInfo001Dto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.service.InvestmentInfo001Service;
import com.joyintech.tams.systemtools.util.CommonUtil;
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

import java.util.Date;
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
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) InvestmentInfo001Dto dto, BindingResult br) throws WorkFlowException {
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());
        ProcessParameter<InvestmentInfo001Dto> processParameter = new ProcessParameter<InvestmentInfo001Dto>();
        WorkFlowParameter<InvestmentInfo001Dto> params = new WorkFlowParameter<InvestmentInfo001Dto>();
        params.setElementId(dto.getElementId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_前十大投资者信息维护.getValue());
        params.setPageId("InvestmentInfo002");
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "产品主代码"+ "(" + entity.getBooksetId() + ")");
        InvestmentInfo001Dto[] investmentInfo001Dtos = dto.getSelectedItem().toArray(new InvestmentInfo001Dto[dto.getSelectedItem().size()]);
        processParameter.setWorkFlowParameter(params);
        //String msg = investmentInfo001Service.start(processParameter,investmentInfo001Dtos);
        for (InvestmentInfo001Dto investmentInfo001Dto : dto.getSelectedItem()) {
            investmentInfo001Service.doComplete(investmentInfo001Dto, OperationType.DELETE);
        }
        return new AjaxResult("处理成功");
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
