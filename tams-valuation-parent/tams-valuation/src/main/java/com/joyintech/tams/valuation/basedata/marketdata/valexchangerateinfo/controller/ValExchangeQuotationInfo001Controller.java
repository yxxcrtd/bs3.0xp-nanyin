
package com.joyintech.tams.valuation.basedata.marketdata.valexchangerateinfo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.valuation.basedata.marketdata.valexchangerateinfo.dto.ValExchangeQuotationInfoDto;
import com.joyintech.tams.valuation.basedata.marketdata.valexchangerateinfo.service.ValExchangeQuotationInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 汇率行情 
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ValExchangeQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ValExchangeQuotationInfo001")
@RequestMapping("/valuation/basedata/marketdata/valexchangerateinfo/ValExchangeQuotationInfo001")
public class ValExchangeQuotationInfo001Controller extends BaseController {
    /**
     * 汇率行情service
     */
    @Autowired
    private ValExchangeQuotationInfoService valExchangeQuotationInfoService;
    /**
     * 
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.货币对);
        return super.init(model);
    }
    /**
     * 
     * 汇率行情查询
     * <功能详细描述>
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValExchangeQuotationInfoDto> selectAll()
        throws Exception {
        Page<ValExchangeQuotationInfoDto> page = valExchangeQuotationInfoService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValExchangeQuotationInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 工作流发起删除操作
     * <功能详细描述>
     * @param dto 删除数据
     * @param br 报错信息
     * @return 删除结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValExchangeQuotationInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ValExchangeQuotationInfoDto> processParameter = new ProcessParameter<ValExchangeQuotationInfoDto>();
        WorkFlowParameter<ValExchangeQuotationInfoDto> params = new WorkFlowParameter<ValExchangeQuotationInfoDto>();
        params.setPageId("ValExchangeQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getExchargeCode());
        params.setBusinessType(DictCodeList.共通业务类型_汇率行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> "货币对" + "("
                      + SysDataUtil.getNameFromDataDict(DictCode.货币对,
                          entity.getExchargeCode())
                      + ")" + "-" + "生效日" + "(" + DateUtil.format(entity.getTradeDate()) + ")" + "收盘价" + "("
                      + entity.getClosePrice() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = valExchangeQuotationInfoService.start(processParameter,
            dto.getSelectedItem().toArray(
                new ValExchangeQuotationInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
//        List<ValExchangeQuotationInfoDto> dtoList = valExchangeQuotationInfoService.selectAllList(
//            RequestUtil.getParameters(getRequest()));
//        File outFile = valExchangeQuotationInfoService.makeFile(
//            "/com/joyintech/tams/valuation/basedata/marketdata/exchangerateinfo/conf/export_conf.xml", dtoList);
//        return ExportUtil.export(
//            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
//                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
//                                 + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//            outFile, true);
        return valExchangeQuotationInfoService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));

    }

}
