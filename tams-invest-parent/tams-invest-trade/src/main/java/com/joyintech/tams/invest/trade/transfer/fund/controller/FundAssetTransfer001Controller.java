package com.joyintech.tams.invest.trade.transfer.fund.controller;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.trade.dto.BussTrdAvailableCashQuery;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.transfer.fund.dto.FundAssetTransfer001Dto;
import com.joyintech.tams.invest.trade.transfer.fund.service.FundAssetTransfer001Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基金资产转仓
 * @author liupengfei
 */
@Controller
@PageId("FundAssetTransfer001")
@RequestMapping("/invest/trade/transfer/fund/FundAssetTransfer001")
public class FundAssetTransfer001Controller extends BaseController {
    /**
     * fundAssetTransfer001Service
     */
    @Autowired
    private FundAssetTransfer001Service fundAssetTransfer001Service;
    /**
     * bussCommonMapperDelegateService
     */
    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //下拉框
        this.setSelectOptionToModel(model, DictCode.币种, DictCode.交易方向);
        Map<String,Object> paraMap = RequestUtil.getPageParam();
        String tradeId = (String)paraMap.get("busiId");
        String pageModel = (String)paraMap.get("pageModel");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        FundAssetTransfer001Dto entity = null;
        Map<String,Object> param = RequestUtil.getPageParam();
        //来自代办
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            entity = fundAssetTransfer001Service.getBussinessEntityByWorkflowId(workflowId);
            // 设置到model中
            model.addAttribute(GlobalConstant.WORKFLOW_ID,workflowId);
            model.addAttribute("entityData", JsonUtils.object2Json(entity.getFunAssetTransferDtoList()));
            model.addAttribute(GlobalConstant.ENTITY, entity);
            model.addAttribute("formTodoList", true);
        }
        //初始化界面
        else if (StringUtil.equals(pageModel,GlobalConstant.DETAIL_PAGE_MODEL_NEW) && !StringUtil.isNotEmpty(workflowId)) {
            entity = fundAssetTransfer001Service.setData(param);
            model.addAttribute(GlobalConstant.ENTITY,entity);
        }
        //非货基查询初始单位净值
        if(StringUtil.equals(entity.getAssetType(),DictCodeList.基金类资产类型_非货币基金.getValue())){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("tradeDate",DateUtil.format(entity.getTradeDate()));
            paramMap.put("finprodId",entity.getFinprodIdOrg());
            model.addAttribute("initUnitCprice",fundAssetTransfer001Service.findInitCarryOverQty(paramMap));
        }
        //证券账户
        entity.setSecManageAcctId((String) paraMap.get("secAcctId"));
        model.addAttribute("PS_T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("PS_T19", DictCodeList.交易方向_赎回确认.getValue());
        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("PS_T02", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("ps", entity.getPs());
        model.addAttribute("sysDate", DateUtil.getSysDate());
        model.addAttribute("FUND_ASSET_TYPE_F01", DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("FINPROD_TYPE_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        model.addAttribute("risk_math_switch", CacheUtil.getSysParam("risk_math_switch"));
        model.addAttribute(GlobalConstant.DETAIL_PAGE_MODEL_KEY, pageModel);
        
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
     * 添加校验
     */
    @ResponseBody
    @RequestMapping("addCheck")
    public AjaxResult addCheck(@RequestBody FundAssetTransfer001Dto fundDto) {
        AjaxResult result = new AjaxResult();

        List<ValidMessage> errorList = new ArrayList<>();
        List<FundAssetTransfer001Dto> listInfoDtoList = new ArrayList<>();
        FundAssetTransfer001Dto listInfoDto = new FundAssetTransfer001Dto();
        FundAssetTransfer001Dto dto = new FundAssetTransfer001Dto();

        if(!StringUtil.isNotEmpty(fundDto.getPortfolioId())){
            errorList.add(new ValidMessage("portfolioId","请输入组合代码！"));
        }
        if(!StringUtil.isNotEmpty(fundDto.getTradePrin())){
            errorList.add(new ValidMessage("tradePrin","请输入交易份额！"));
        }
        if(!StringUtil.isNotEmpty(fundDto.getUnitCprice())){
            errorList.add(new ValidMessage("unitCprice","请输入单位净值！"));
        }
        else{
            if(BigDecimal.ZERO.compareTo(fundDto.getTradePrin())>=0){
                errorList.add(new ValidMessage("tradePrin","交易份额必须大于0！"));
            }
        }
        BussTrdAvailableCashQuery bussTrdAvailableCashQuery= new BussTrdAvailableCashQuery();
        if(StringUtil.isNotEmpty(fundDto.getBussTrdAvailableCashQuery())){
            bussTrdAvailableCashQuery = fundDto.getBussTrdAvailableCashQuery();
        }
        if(StringUtil.isNotEmpty(fundDto.getTradePrin()) && StringUtil.isNotEmpty(fundDto.getRollablePrin()) && fundDto.getTradePrin().compareTo(fundDto.getRollablePrin())>0){
            errorList.add(new ValidMessage("tradePrin","交易份额不能大于剩余可转仓份额！"));
        }
        if(errorList!=null && errorList.size()>0){
            result.setStatuCode(500);
            result.setErrorList(errorList);
        }
        else{
            //转仓交易份额不能大于组合剩余本金
            if(StringUtil.isNotEmpty(fundDto.getTradeAmt())&&StringUtil.isNotEmpty(bussTrdAvailableCashQuery)&&StringUtil.isNotEmpty(bussTrdAvailableCashQuery.getAvalibleCash())){
                if(fundDto.getTradeAmt().compareTo(bussTrdAvailableCashQuery.getAvalibleCash())>0){
                    result.setMessage("组合剩余现金不足！", MessageType.确认消息);
                }
            }
            result.setStatuCode(200);
            listInfoDto = fundAssetTransfer001Service.setProjectListInfoDto(fundDto);
            listInfoDtoList.add(listInfoDto);
            dto.setFunAssetTransferDtoList(listInfoDtoList);
            result.setData(dto);
        }
        return result;
    }
    /**
     * 查询组合信息
     * * @return
     */
    @ResponseBody
    @RequestMapping("selectPortfolioId")
    public List<SelectOptionDto> selectPortfolioId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return fundAssetTransfer001Service.selectPortfolioId(param);
    }
    /**
     * 风险侧算
     * @param model 参数
     * @return
     */
    @RequestMapping("riskMathComm")
    public String riskMathPage(Model model){
        Map param = RequestUtil.getPageParam();
        model.addAttribute("param",param);List<RiskTrialRetDto> riskList = new ArrayList<>();
        if(StringUtil.isNotEmpty(param.get("results")) && !StringUtil.equals((String)param.get("results"),"{}")){
            riskList = JSONArray.parseArray((String)param.get("results"),RiskTrialRetDto.class);
        }
        model.addAttribute("riskList",riskList);

        setSelectOptionToModel(model, DictCode.指标类型, DictCode.监督状态, DictCode.单位,
                DictCode.监督类型, DictCode.监督频率, DictCode.金融产品类型_投管用, DictCode.WIND一级分类_含债券一级分类等,
                DictCode.基金类资产二级类型, DictCode.资产评级, DictCode.申万行业一级类型, DictCode.指标类型映射编码);
        return "/trade/resolute/common/RiskMathComm";
    }
    /**
     * 保存时校验
     *
     * @param dto    dto
     * @param rst result
     * @return
     */
    @RequestMapping("saveCheck")
    @ResponseBody
    public AjaxResult check(@RequestBody FundAssetTransfer001Dto dto, BindingResult rst) {
        AjaxResult result = new AjaxResult();
        String confirmMsg = "";
        List<ValidMessage> errorList = new ArrayList<>();
        if(StringUtil.isEmptyList(dto.getFunAssetTransferDtoList())){
            errorList.add(new ValidMessage("列表信息不能空！"));
        }
        else{
            //1) 本次提交的转仓交易中是否存在单位净值不一致的转仓交易数据；
            BigDecimal unitCprice = dto.getFunAssetTransferDtoList().get(0).getUnitCprice();
            for(FundAssetTransfer001Dto fundDto:dto.getFunAssetTransferDtoList()){
                if(unitCprice.compareTo(fundDto.getUnitCprice())!=0){
                    confirmMsg = "多笔转仓交易中单位净值不一致，是否确认提交？";
                    break;
                }
            }
            //2) 本次提交的转仓交易和当天同一产品同一只资产转仓给其他产品组合的交易（包括已生效和新建流程中的交易数据），是否存在单位净值不一致的数据
            //若任何一项检查发现有不一致的转仓交易数据，则系统提示
            //查询单位净价
            if(confirmMsg == ""){
                List<String> unitCpriceList = fundAssetTransfer001Service.findUnitCprice(dto);
                for(String unitCpriceObj:unitCpriceList){
                    BigDecimal Obj = new BigDecimal(unitCpriceObj);
                    if(unitCprice.compareTo(Obj)!=0){
                        confirmMsg = "多笔转仓交易中单位净值不一致，是否确认提交？";
                        break;
                    }
                }
            }
        }
        if(errorList.size()>0){
            result.setErrorList(errorList);
            return result;
        }
        if(confirmMsg.length()>0){
           result.setData(confirmMsg);
           return result;
        }
        else {
            return commonCheck(rst, dto.getOperateType());
        }
    }
    /**
     * 新建/修改流程 <功能详细描述>
     *
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(@RequestBody FundAssetTransfer001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FundAssetTransfer001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FundAssetTransfer001Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getFinprodMarketId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(getMessage("TrdFundInvestTrade002.finprodId"),
                entity.getFinprodMarketId(), getMessage("TrdFundInvestTrade002.finprodName"), GlobalConstant.SPLIT, entity.getFinprodAbbr(),
                getMessage("TrdFundInvestTrade002.tradeDate"), DateUtil.format(entity.getTradeDate())));
        dto.setTrader(LoginUserUtil.getUserName());
        dto.setDeptCode(LoginUserUtil.getDeptCode());
        String msg = fundAssetTransfer001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
