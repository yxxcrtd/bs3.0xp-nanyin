/*
 * 文件名：EtrPlanAssetC002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrstock.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.entrust.common.controller.EtrCommonController;
import com.joyintech.tams.invest.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.tams.invest.entrust.etrstock.service.EtrStockService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @title: EtrStock002Controller
 * @package com.joyintech.tams.invest.entrust.etrstock.controller
 * @description: 股票委托新建
 * @author: junYin
 * @date: 2019-06-04 10:10
*/
@Controller
@PageId("EtrStock002")
@RequestMapping("/invest/entrust/etrstock/EtrStock002")
public class EtrStock002Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 股票委托service
     */
    @Autowired
    private EtrStockService etrStockService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.外汇交易模式);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.上市板);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        this.setSelectOptionToModel(model, DictCode.价格模式);
        model.addAttribute("PRICEMODEL_01", DictCodeListExt2.价格模式_指定价格.getValue());
        model.addAttribute("PRICEMODEL_02", DictCodeListExt2.价格模式_限价指令.getValue());
        model.addAttribute("PRICEMODEL_03", DictCodeListExt2.价格模式_市价指令.getValue());
        Date sysDate = CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        //-----add by jjpei 20190718 委托风险试算----start---------
        model.addAttribute("MY_SUP_TIME_TYPE_01", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
        //-----add by jjpei 20190718 委托风险试算----end---------
        model.addAttribute("T46", DictCodeListExt2.交易方向_配股.getValue());
        model.addAttribute("T47", DictCodeListExt2.交易方向_增发.getValue());
        model.addAttribute("T48", DictCodeListExt2.交易方向_新股中签.getValue());
        model.addAttribute("T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("T02", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("dealMode_03", DictCodeListExt2.外汇交易模式_盘后定价交易.getValue());
        String entrustId = getParam("entrustId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            EtrStockDto dto = etrStockService.getBussinessEntityByWorkflowId(workflowId);
            // 为交易方式赋默认值by赵东东 2019.9.4 start
            //  dto.setDealMode(DictCodeListExt2.外汇交易模式_竞价交易.getValue());
            // 为交易方式赋默认值by赵东东 2019.9.4 end
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId",dto.getWorkflowId());
            //---------add by zxjjpei 风险试算需要流程编号 20200325 start------
            model.addAttribute("workflowId",dto.getWorkflowId());
            //---------add by zxjjpei 风险试算需要流程编号 20200325 end------
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(entrustId)) {
            EtrStockDto dto = etrStockService.selectByKey(entrustId);
             // 为交易方式赋默认值by赵东东 2019.9.4 start
            // dto.setDealMode(DictCodeListExt2.外汇交易模式_竞价交易.getValue());
            // 为交易方式赋默认值by赵东东 2019.9.4 end
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId", dto.getEntrustId());
        }
      //新建初始化
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
            EtrStockDto entity = new EtrStockDto();
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            entity.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            entity.setEntrustStatus(DictCodeList.委托状态_新建.getValue());
            // 为交易方式赋默认值by赵东东 2019.9.4 start
            entity.setDealMode(DictCodeListExt2.外汇交易模式_竞价交易.getValue());
            // 为交易方式赋默认值by赵东东 2019.9.4 end
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("BaseRiskMathComm");
        Map<String, String> map = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        isEleShow.putAll(map);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }



    @ResponseBody
    @RequestMapping("/getSecAcctId")
    public List<SelectOptionDto> getSecAcctId(){
        List<SelectOptionDto> temp = etrStockService.getSecAcctId(RequestUtil.getPageParam());
        return temp;
    }
    /**
     *@title: getentrustIdAdd
     *@description: 获取组合代码联想文本框信息
     *@author: junYin
     *@date: 2019/06/04 10:18   
     *@param: * @param  1
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getentrustIdAdd")
    public Page<SelectOptionDto> getentrustIdAdd() throws Exception {
        Page<SelectOptionDto> temp = etrStockService.getentrustIdAdd(RequestUtil.getPageParam());
        return temp;
    }
    /**
     *@title: getProAvailableCash
     *@description: 组合可用现金查询
     *@author: junYin
     *@date: 2019/06/04 10:18   
     *@param: * @param param 1
     *@return: java.util.Map<java.lang.String,java.lang.Object>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
        return etrStockService.getProAvailableCash(param);
    }
    /**
     *@title: getAssetAvailablePosition
     *@description: 组合可用持仓查询
     *@author: junYin
     *@date: 2019/06/04 10:18   
     *@param: * @param dto 1
     *@return: java.math.BigDecimal
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody EtrSecPositionDto dto){
        return etrStockService.getAssetAvailablePosition(dto);
    }
    /**
     *@title: getfinprodIdForAdd
     *@description: 获取新建界面的资产代码联想下拉框
     *@author: junYin
     *@date: 2019/06/04 10:19   
     *@param: * @param  1
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getfinprodIdForAdd")
    public Page<SelectOptionDto> getfinprodIdForAdd() throws Exception {
        Page<SelectOptionDto> temp = etrStockService.getfinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
    }

    /**
     *@title: getSelectTongDao
     *@description: 获取新建界面的通道联想下拉框
     *@author: Daizhiwei
     *@date: 2019/07/03 15:10
     *@param: * @param  1
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getSelectTongDao")
    public Page<SelectOptionDto> getSelectTongDao(){
        Page<SelectOptionDto> page = etrStockService.getSelectTongDao(RequestUtil.getPageParam());
        return page;
    }
    /**
     * 通道可用现金查询
     * @param
     * @return
     * @see
     */
    @ResponseBody
    @RequestMapping("/getTongDaoCash")
    public BigDecimal getTongDaoCash(@RequestBody Map<String,Object> map) throws Exception{
        return etrStockService.getTongDaoCash(map);
    }

    /**
     *@title: check
     *@description: 保存按钮点击时的check
     *@author: junYin
     *@date: 2019/06/04 10:19   
     *@param: * @param dto 1
     * @param result 2
     *@return: com.joyintech.framework.ajax.AjaxResult
     *@throws:
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) EtrStockDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
    	String confirmMsg = "";
        String changeLineMark = "<br/>";
        // 可用持仓软校验
        if(StringUtil.equals(dto.getPs(),DictCodeList.交易方向_卖出.getValue())&&StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
            DictCodeListExt1.是或否_是.getValue())&&dto.getAssetAvailablePosition() != null&& StringUtil.isNotEmpty(dto.getEntrustNum())) {
            if(dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
                confirmMsg = this.getMessage("ETR_X_011", this.getMessage("EtrStock002.entrustNum"))+changeLineMark;
            }
            // 可用现金软校验
        }else if(!(StringUtil.equals(dto.getPs(),DictCodeList.交易方向_卖出.getValue()))&&StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
            DictCodeListExt1.是或否_是.getValue())&& dto.getProAvailableCash() != null &&StringUtil.isNotEmpty(dto.getEntrustAmt())) {
            if(dto.getEntrustAmt().compareTo(dto.getProAvailableCash())>0) {
            confirmMsg = this.getMessage("ETR_X_010", this.getMessage("EtrStock002.entrustAmt"))+changeLineMark;
            }
        }else if(!(StringUtil.equals(dto.getPs(),DictCodeList.交易方向_卖出.getValue()))&&StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
            DictCodeListExt1.是或否_是.getValue())&& dto.getProAvailableCash() != null &&StringUtil.isNotEmpty(dto.getEntrustNum())&&StringUtil.isNotEmpty(dto.getPrice())) {
            BigDecimal amt = dto.getEntrustNum().multiply(dto.getPrice());
            if(amt.compareTo(dto.getProAvailableCash())>0) {
                confirmMsg = this.getMessage("ETR_X_010", this.getMessage("EtrStock002.entrustAmt"))+changeLineMark;
            }
        }
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) && StringUtil.equals(dto.getPriceModel(),
                DictCodeListExt2.价格模式_市价指令.getValue())) {
            if (EtrCommonCashPositionDealService.getLatestPrice(dto.getFinprodId(), DateUtil.platDateFromCache()) == null) {
                confirmMsg = getMessage("该股票当日资产无行情")+changeLineMark;
                
            }
        }
        if(StringUtil.isNotEmpty(confirmMsg)) {
            confirmMsg += getMessage("请确认是否继续?");
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setData(confirmMsg);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody EtrStockDto dto) {
        //授权管理  委托授权额度check
        if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH),"01")){
            EtrCommonController etrCommonController = SpringContextUtil.getBean(
                EtrCommonController.class);
            return etrCommonController.授权是否匹配(dto);
        }
        return new AjaxResult();
    }
    
    /**
     *@title: save
     *@description: 保存/提交
     *@author: junYin
     *@date: 2019/06/04 10:20   
     *@param: * @param dto 1
     * @param br 2
     *@return: com.joyintech.framework.ajax.AjaxResult
     *@throws:
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody EtrStockDto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_股票投资委托));
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param): IDGenerator.generate();
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrStockDto> processParameter = new ProcessParameter<EtrStockDto>();
        WorkFlowParameter<EtrStockDto> params = new WorkFlowParameter<EtrStockDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        if(dto.getEntrustNum() != null){
            params.setComment(
                entity -> this.getMessage("EtrStock001") + "(" + entity.getPortfolioId() + ")"
                      + "-" + this.getMessage("EtrStock001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                      + this.getMessage("EtrStock001.vdate") + ":" + DateUtil.format(entity.getVdate())
                      + ")-(" + this.getMessage("EtrStock001.mdate") + ":"
                      + DateUtil.format(entity.getMdate()) + ")-("
                      + this.getMessage("EtrStock001.entrustNum") + ":"
                      + entity.getEntrustNum() + ")-(" + this.getMessage("EtrStock001.price") + ":" + entity.getPrice() + ")");
        }else{
        	params.setComment(
                    entity -> this.getMessage("EtrStock001") + "(" + entity.getPortfolioId() + ")"
                          + "-" + this.getMessage("EtrStock001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                          + this.getMessage("EtrStock001.vdate") + ":" + DateUtil.format(entity.getVdate())
                          + ")-(" + this.getMessage("EtrStock001.mdate") + ":"
                          + DateUtil.format(entity.getMdate()) + ")-("
                          + this.getMessage("EtrStock001.entrustAmt") + ":"
                          + entity.getEntrustAmt() + ")");
        }
        processParameter.setWorkFlowParameter(params);
        String msg = etrStockService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     * 查询估值净价
     * @author wangguiqi
     */
    @ResponseBody
    @RequestMapping("getClosePriceByPara")
    public BigDecimal getClosePriceByPara(@RequestBody Map<String, Object> param){
        return etrStockService.getClosePriceByPara(param);
    }
    /**
     * 查询估值净价
     * @author wangguiqi
     */
    @ResponseBody
    @RequestMapping("getClosePrice")
    public AjaxResult getClosePrice(){
        return etrStockService.getClosePrice(RequestUtil.getPageParam());
    }

}
