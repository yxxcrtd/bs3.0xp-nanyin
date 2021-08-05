package com.joyintech.tams.invest.basicdata.futuresdepositpercent.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto;
import com.joyintech.tams.invest.basicdata.futuresdepositpercent.service.FuturesDepositPercentService;
import com.joyintech.tams.invest.basicdata.mstcalendartype.dto.MstCalendarType001Dto;
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

import java.util.List;
import java.util.Map;

/**
 * @Classname FuturesDepositPercent001Controller
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/12 14:30 
 * @version 1.0 
 */
@Controller
@PageId("FuturesDepositPercent001")
@RequestMapping("/invest/basicdata/futuresdepositpercent/FuturesDepositPercent001")
public class FuturesDepositPercent001Controller extends BaseController {

    @Autowired
    private FuturesDepositPercentService futuresDepositPercentService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.合约标的,DictCode.期货类型);
        return super.init(model);
    }

    /**
     * 查询操作
     *
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FuturesDepositPercentDto> findPage()
            throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        return futuresDepositPercentService.selectForPage(map);
    }


    /**
     *
     * @Description //删除check
     * @Param [dto, result]
     * @return com.joyintech.framework.ajax.AjaxResult
     * @version 1.0
     * @see
     **/
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) FuturesDepositPercentDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    /**
     * 删除按钮点击
     * @return AjaxResult
     * @param dto FundGoalDto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  FuturesDepositPercentDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FuturesDepositPercentDto> processParameter = new ProcessParameter<FuturesDepositPercentDto>();
        WorkFlowParameter<FuturesDepositPercentDto> params = new WorkFlowParameter<FuturesDepositPercentDto>();
        params.setPageId("FuturesDepositPercent002");
//        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFuturesCompany()+"-"+item.getFuturesContractId()+ DateUtil.format(item.getEffDate()));
        params.setBusinessType(DictCodeListExt2.共通业务类型_期货保证金比例设置.getValue());
//        params.setBusinessType("MST31");
        // 设置流程业务说明, entity即传入的dtoSpringContextUtil.getTextValue("MstBondQuotationInfo001.secId")
        params.setComment(
                entity -> SpringContextUtil.getTextValue("FuturesDepositPercent001.futuresType")+"("+ entity.getFuturesTypeStr() +")"+SpringContextUtil.getTextValue("FuturesDepositPercent001.futuresCompany")+"("+entity.getFuturesCompanyName()
                        +")"+SpringContextUtil.getTextValue("FuturesDepositPercent001.effDate")+"("+ DateUtil.format(entity.getEffDate())+")"+SpringContextUtil.getTextValue("FuturesDepositPercent001.futuresContractId")+"("+entity.getFuturesContractIdStr()+")"
        );
        processParameter.setWorkFlowParameter(params);
        String msg = futuresDepositPercentService.start(processParameter,
                dto.getSelectedItem().toArray(
                        new FuturesDepositPercentDto[dto.getSelectedItem().size()]));
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

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return futuresDepositPercentService.export(this.getPageId(),param);

    }

}
