package com.joyintech.tams.invest.entrust.etrbondfirstlbidding.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.listener.FileAttachmentProcess;


import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.service.EtrBondFirstBiddingService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 债券一级投标多指令
 * @author gejiaao
 * @version 1.0 2019年12月30日
 * @see EtrBondFirstLBidding003Controller
 * @since 1.0
 */
@Controller
@PageId("EtrBondFirstLBidding003")
@RequestMapping("/invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding003")
public class EtrBondFirstLBidding003Controller extends BaseController {
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 债券一级委托service
     */
    @Autowired
    private EtrBondFirstBiddingService firstService;

    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        String operateType = getParam("operateType");
        //如果是导入
        if (StringUtil.equals(operateType,"import")) {
            String tempList = getParam("tempList");
            List<EtrBondFirstLBiddingDto> entrustListData = firstService.getEntrustListData(tempList);
            model.addAttribute("etrBondFirstLBiddingDtoList", StringUtil.isEmptyList(entrustListData) ? null : JsonUtils.object2Json(entrustListData));

        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", BussinessDictCodeList.监督时分_委托监控.getValue());
        model.addAttribute("fintype", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
        model.addAttribute("etrPs", BussinessDictCodeList.交易方向_买入.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("BaseRiskMathComm");
        Map<String, String> map = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        isEleShow.putAll(map);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public Map<String,Object> importData(String fileName) {
        return this.firstService.importData(fileName);
    }


    /**
     * 保存按钮点击时的check
     *
     * @param dtoList dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody List<EtrBondFirstLBiddingDto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++ ) {
            ValidationUtil.ValidResult itemResult = ValidationUtil.validateBean(dtoList.get(i),
                    ValidateGroupEdit.class);
            if (!StringUtil.isEmptyList(itemResult.getAllErrors())) {
                StringBuffer message = new StringBuffer();
                message.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                for (ValidationUtil.ErrorMessage errorMessage : itemResult.getAllErrors()) {
                    message.append("<br />");
                    message.append(errorMessage.getMessage());
                }
                messages.add(new ValidMessage(message.toString()));
            }
        }
        if (messages.size() > 0) {
            AjaxResult errorRs = new AjaxResult();
            errorRs.setErrorList(messages);
            return errorRs;
        }
        return commonCheck(result, dtoList.get(0).getOperateType());
    }

    /**
     * 导入文件时的check
     * @param map 数据
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return firstService.checkImportData(fileName);
    }


    /**
     * 保存/提交
     * 
     * @param dtoList dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody List<EtrBondFirstLBiddingDto> dtoList, BindingResult br)
        throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for (EtrBondFirstLBiddingDto dto : dtoList) {
            if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共同业务类型_债券一级投标委托);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
            dto.setWorkflowParameter("new|||1|EtrBondFirstLBidding002|EtrBondFirstLBidding002_btn_submit|true");
        }

        ProcessParameter<EtrBondFirstLBiddingDto> processParameter = new ProcessParameter<EtrBondFirstLBiddingDto>();
        WorkFlowParameter<EtrBondFirstLBiddingDto> params = new WorkFlowParameter<EtrBondFirstLBiddingDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrBondFirstLBiddingDto::getEntrustId);
        params.setBusinessType(BussinessDictCodeList.共同业务类型_债券一级投标委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrBondFirstLBidding001") + "(" + entity.getPortfolioId() + ")"
                      + "-" +  this.getMessage("EtrInvestAllInfo001.vdate") + ":" + DateUtil.format(entity.getVdate())
                      + ")-(" +  this.getMessage("EtrInvestAllInfo001.entrustDate") + ":" + DateUtil.format(entity.getEntrustDate())
                      + ")-(" + this.getMessage("EtrInvestAllInfo001.mdate") + ":"
                      + DateUtil.format(entity.getMdate())+ ")-"
                      + this.getMessage("EtrBondFirstLBidding002.finprodId") + ":"
                      + entity.getFinprodMarketId());
        processParameter.setWorkFlowParameter(params);
        String msg = firstService.start(processParameter,  dtoList.toArray(new EtrBondFirstLBiddingDto[dtoList.size()]));
        return new AjaxResult(msg);
    }
}
