package com.joyintech.tams.invest.period.trdsecoverdueduration.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration002Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration003Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.service.TrdSecOverdueDuration003Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@PageId("TrdSecOverdueDuration003")
@RequestMapping("/invest/period/trdsecoverdueduration/TrdSecOverdueDuration003")
public class TrdSecOverdueDuration003Controller extends BaseController {
    /**
     * 资产到期结转Service
     */
    @Autowired
    private TrdSecOverdueDuration003Service trdSecOverdueDuration003Service;
    @Override
    public String init(Model model) {
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String pageModel = getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY);
        String type8 = getParam("finprodType2");
        Map<String,Object> map = RequestUtil.getPageParam();
        TrdSecOverdueDuration003Dto entity = null;
//        流程中
        if(StringUtil.isNotEmpty(workflowId)){
            entity = trdSecOverdueDuration003Service.getBussinessEntityByWorkflowId(workflowId);
            entity.setPortfolioId(entity.getActPortfolioId());
        }
//        转让新建画面 //        传入参数：本金分支序号，利息分支序号，金融产品代码，证券管理账户/通道代码，分类8
        else if (StringUtil.equals(pageModel,GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            entity = BeanUtil.mapToObject(map,TrdSecOverdueDuration003Dto.class);
            entity.setTransferDate(DateUtil.platDateFromCache());
            entity.setType8(type8);
            entity.setFinprodType2(trdSecOverdueDuration003Service.gen分类(type8,DictCode.金融产品类型_投管用));
        }
        model.addAttribute(GlobalConstant.ENTITY,entity);
        if(StringUtil.isNotEmpty((entity==null?null:entity.getSecAcctId()))&&trdSecOverdueDuration003Service.isSecManage(entity==null?null:entity.getSecAcctId())){
            entity.setSecManageAcctId(entity.getSecAcctId());
            entity.setChannel(false);
        }
        else if(!StringUtil.equals(entity.getSecAcctId(),"999999")){
            entity.setChlId(entity.getCashId());
            entity.setChannel(true);
        }
        TrdInvestPersonInfo002Dto trdInvestPersonInfo = new TrdInvestPersonInfo002Dto();
        BeanUtil.copyPropertiesIgnoreNull(trdInvestPersonInfo,entity.getTrdInvestPersonInfo()==null?entity:entity.getTrdInvestPersonInfo());
        if(StringUtil.isEmpty(trdInvestPersonInfo.getTrader())){
            trdInvestPersonInfo.setTrader(LoginUserUtil.getUserName());
        }
        model.addAttribute("entity_trdInvestPersonInfo", JsonUtils.object2Json(trdInvestPersonInfo));
        model.addAttribute("businessId",entity.getTotleTradeId());
        // 划款信息
        if (entity != null && !StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                    JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
        }
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("PS_MC",DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("PS_MR",DictCodeList.交易方向_买入.getValue());
        model.addAttribute("CCY_CNY",DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("ACCT_TYPE_A01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        model.addAttribute("ACCT_TYPE_A07", DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();//风险试算开关
        pageElementConfigDto.setPageId("BaseRiskMathComm");
        Map<String, String> riskMap = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        isEleShow.putAll(riskMap);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("selectPortfolioId")
    public Page<SelectOptionDto> selectPortfolioId(){
         Map<String, Object> param =RequestUtil.getPageParam();
        // 查询组合添加权限限制
        param.put("loginUserName", LoginUserUtil.getUserName());
        param.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return trdSecOverdueDuration003Service.selectPortfolioId(param);
    }
    @ResponseBody
    @RequestMapping("genAssetTfInfo")
    public AjaxResult genAssetTfInfo(){
        AjaxResult aj = new AjaxResult();
        Map<String,Object> param = RequestUtil.getPageParam();
        aj.setData(trdSecOverdueDuration003Service.genAssetTfInfo(param));
        return aj;
    }
    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdSecOverdueDuration003Dto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdSecOverdueDuration003Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdSecOverdueDuration003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdSecOverdueDuration003Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
//        params.setElementId(dto.getElementId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_已到期资产转让.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> getMessage("TrdSecOverTransfer001.portfolioId") + "("
                + entity.getPortfolioId() + ")-" +getMessage("TrdSecOverTransfer001.transferDate") + "("
                + DateUtil.format(entity.getTransferDate()) + ")-"+ getMessage("TrdSecOverTransfer001.finprodId") + "("+entity.getFinprodId()+")");
        dto.setTotleTradeId(trdSecOverdueDuration003Service.genBusiCode());
        params.setBusinessCode(dto.getTotleTradeId());
        processParameter.setWorkFlowParameter(params);
//            trdBondDuration002Service.genDataForInsertDto(dto);
//        	2.0
        dto.setBaseTotalTradeInfoDto(trdSecOverdueDuration003Service.genFinProductTradeDto(dto));
        String msg = trdSecOverdueDuration003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
