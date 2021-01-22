package com.joyintech.tams.invest.entrust.etrbrowdepoinfo.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.service.EtrBrowDepoInfoService;
import com.joyintech.tams.invest.entrust.etrconbond.dto.EtrConBondDto;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.tools.util.ValidationUtil.ErrorMessage;
import com.joyintech.tools.util.ValidationUtil.ValidResult;
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
 * 存款类资产多对多指令新建
 *
 * @author gaopeng
 * @version 1.0 2019年12月18日
 * @see EtrBrowDepoInfo003Controller
 * @since 1.0
 */

@Controller
@PageId("EtrBrowDepoInfo003")
@RequestMapping("/invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo003")
public class EtrBrowDepoInfo003Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 存款委托service
     */
    @Autowired
    private EtrBrowDepoInfoService etrBrowDepoInfoService;


    @Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        //如果是导入
        String tempList = getParam("tempList");
        EtrBrowDepoInfoDto dto = etrBrowDepoInfoService.getEntrustListData(tempList);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        model.addAttribute("etrBrowDepoInfoDtoList", StringUtil.isEmptyList(
                dto.getSelectedItem()) ? null : JsonUtils.object2Json(dto.getSelectedItem()));
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("T01",DictCodeList.交易方向_买入.getValue());
        model.addAttribute("T02",DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
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
     * 导入时的check
     *
     * @param map dto
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return etrBrowDepoInfoService.checkImportData(fileName);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param List<EtrConBondDto> dtoList
     * @param BindingResult result
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody List<EtrBrowDepoInfoDto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++ ) {
            ValidResult itemResult = ValidationUtil.validateBean(dtoList.get(i),
                    ValidateGroupEdit.class);
            if (!StringUtil.isEmptyList(itemResult.getAllErrors())) {
                StringBuffer message = new StringBuffer();
                message.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                for (ErrorMessage errorMessage : itemResult.getAllErrors()) {
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
     * 批量提交
     *
     * @param List<EtrConBondDto> dtoList
     * @param String msg
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody List<EtrBrowDepoInfoDto> dtoList, BindingResult br)
            throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for(EtrBrowDepoInfoDto dto: dtoList) {
            if(!StringUtil.isNotEmpty(dto.getEntrustId())){
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_存款类资产投资委托);
                param.setType1(StringUtil.equals(dto.getFinprodType2(),DictCodeList.存款类资产类型_同业借款.getValue())
                        ?DictCodeList.业务编号一级分类_同业借款:DictCodeList.业务编号一级分类_存放同业);
                String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
                //画面.资产类型==数据字典缓存.存款类资产类型.同业借款？数据字典缓存.业务编号一级分类.同业借款:数据字典缓存.业务编号一级分类.存放同业
                dto.setEntrustId(code);
            }
            dto.getBussEtrInvestPersonInfoDto().setInvManagerId(dto.getCreateUser());
            dto.getBussEtrInvestPersonInfoDto().setRoom(dto.getCreateDept());
            dto.setWorkflowParameter("new|||1|EtrBrowDepoInfo003|EtrBrowDepoInfo003_btn_submit|true");
        }
        ProcessParameter<EtrBrowDepoInfoDto> processParameter = new ProcessParameter<EtrBrowDepoInfoDto>();
        WorkFlowParameter<EtrBrowDepoInfoDto> params = new WorkFlowParameter<EtrBrowDepoInfoDto>();
        params.setPageId("EtrBrowDepoInfo002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(item->item.getEntrustId());
        params.setBusinessType(DictCodeList.共通业务类型_存款类资产投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        //业务说明：存款委托投资+"("+组合代码+")"+"-"+交易方向+"-"+"（委托开始日："+委托开始日+"）-"+"（委托结束日："+委托结束日+"）-"+"金额："+金额"
        params.setComment(
                entity -> getMessage("EtrBrowDepoInfo001")+ "(" + entity.getPortfolioId()+ ")"
                        +  "-" + getMessage("EtrBrowDepoInfo001.ps")+ "(" + entity.getPsStr() + ")"  +  "-" +
                        "("+getMessage("EtrBrowDepoInfo001.entrustVDate")+"：" + DateUtil.format(entity.getVdate()) + ")"
                        +  "-("+getMessage("EtrBrowDepoInfo001.entrustMDate")+"：" + DateUtil.format(entity.getMdate()) + ")-"+getMessage("EtrBrowDepoInfo001.entrustAmt")+"："+entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        //dto.getBussEtrInvestPersonInfoDto().setInvManagerId(SecurityUtil.getLoginUser().getUserName());
        String msg = etrBrowDepoInfoService.start(processParameter, dtoList.toArray(new EtrBrowDepoInfoDto[dtoList.size()]));
        return new AjaxResult(msg);
    }

}
