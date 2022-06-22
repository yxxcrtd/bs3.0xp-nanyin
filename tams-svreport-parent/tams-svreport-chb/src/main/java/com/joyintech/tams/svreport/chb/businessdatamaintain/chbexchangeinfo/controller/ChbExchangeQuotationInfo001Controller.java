
package com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.dto.ChbExchangeQuotationInfoDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.service.ChbExchangeQuotationInfoService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 汇率行情 
 * 
 * @author zhoubin
 * @version 1.0 2022年03月30日
 * @see ChbExchangeQuotationInfo001Controller
 */
@Controller
@PageId("ChbExchangeQuotationInfo001")
@RequestMapping("/chb/businessdatamaintain/chbexchangeinfo/ChbExchangeQuotationInfo001")
public class ChbExchangeQuotationInfo001Controller extends BaseController {
    /**
     * 汇率行情service
     */
    @Resource
    private ChbExchangeQuotationInfoService chbExchangeQuotationInfoService;
    /**
     * 
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        //货币对
        this.setSelectOptionToModel(model, DictCode.货币对);
        //数据的状态
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }
    /**
     * 汇率行情查询
     * @return 返回查询结果
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ChbExchangeQuotationInfoDto> selectAll(){
        Page<ChbExchangeQuotationInfoDto> page = chbExchangeQuotationInfoService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ChbExchangeQuotationInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 工作流发起删除操作
     * <功能详细描述>
     * @param dto 删除数据
     * @param br 报错信息
     * @return 删除结果
     * @throws WorkFlowException 抛出异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ChbExchangeQuotationInfoDto dto, BindingResult br) throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<ChbExchangeQuotationInfoDto> params = new WorkFlowParameter<>();
        params.setPageId("ChbExchangeQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getExchargeCode());
        params.setBusinessType(DictCodeList.共通业务类型_汇率行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "货币对" + "("
                        + SysDataUtil.getNameFromDataDict(DictCode.货币对,
                        entity.getExchargeCode())
                        + ")" + "-" + "生效日" + "(" + DateUtil.format(entity.getTradeDate()) + ")"  + "-" + "数据来源" + "(" +
                        SysDataUtil.getNameFromDataDict(DictCode.估值来源,
                                entity.getDatasourceType())+")");
        // 给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<ChbExchangeQuotationInfoDto> parameter = new ProcessParameter<>();
        parameter.setWorkFlowParameter(params);
        String msg = chbExchangeQuotationInfoService.start(parameter,
                dto.getSelectedItem().toArray(
                        new ChbExchangeQuotationInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export(){

        return chbExchangeQuotationInfoService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));

    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return chbExchangeQuotationInfoService.checkConfirmMsgForImportData(fileName);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return chbExchangeQuotationInfoService.importData(fileName);

    }

}
