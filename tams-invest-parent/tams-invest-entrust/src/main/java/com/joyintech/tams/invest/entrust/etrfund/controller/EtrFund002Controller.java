/*
 * 文件名：EtrFund002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrfund.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.common.controller.EtrCommonController;
import com.joyintech.tams.invest.entrust.etrfund.dto.EtrFundDto;
import com.joyintech.tams.invest.entrust.etrfund.service.EtrFundService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 基金委托新建
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月29日
 * @see EtrFund002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrFund002")
@RequestMapping("/invest/entrust/etrfund/EtrFund002")
public class EtrFund002Controller extends BaseController{
    /**
     * 自定义service
     */
    @Autowired
    private EtrFundService etrFundService;
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    
    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种,DictCode.交易所基金类型, DictCode.交易方向, DictCode.交易场所, DictCode.基金类资产类型);
        //新增资产二、三、四级分类	王贵奇	2019/7/29	开始
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类);
        this.setSelectOptionToModel(model, DictCode.行内资产三级分类);
        this.setSelectOptionToModel(model, DictCode.行内资产四级分类);
        //新增资产二、三、四级分类	王贵奇	2019/7/29	结束
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        String entrustId = getParam("entrustId");
        String finprodId = getParam("finprodId");
        String portfolioId = getParam("portfolioId");
        
        EtrFundDto etrFundDto = new EtrFundDto();
        
        etrFundDto.setPs(DictCodeList.交易方向_申购.getValue());
        etrFundDto.setAssetType1(DictCodeList.基金类资产类型_货币基金.getValue());
        etrFundDto.setCcy("CNY");
        etrFundDto.setTradeMarket("08");
        etrFundDto.setVdate(sysDate);
        etrFundDto.setMdate(sysDate);
        model.addAttribute(GlobalConstant.ENTITY, etrFundDto);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            
            EtrFundDto dto = etrFundService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getBussEtrInvestPersonInfoDto()));
            model.addAttribute("businessId", workflowId);
        }
        else if (StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()) {
            EtrFundDto dto = etrFundService.selectByKey(entrustId,finprodId,portfolioId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getBussEtrInvestPersonInfoDto()));
            model.addAttribute("businessId", dto.getEntrustId());
        }if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
            EtrFundDto entity = new EtrFundDto();
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
            model.addAttribute("entity", entity);
        }
        
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        model.addAttribute("T04",DictCodeList.交易方向_申购.getValue());
        model.addAttribute("T06",DictCodeList.交易方向_赎回.getValue());
        model.addAttribute("T01",DictCodeList.交易方向_买入.getValue());
        model.addAttribute("T02",DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("T52",DictCodeListExt2.交易方向_认购.getValue());
        model.addAttribute("T65", DictCodeListExt2.交易方向_转换申请.getValue());
        model.addAttribute("F01",DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("F02",DictCodeList.基金类资产类型_非货币基金.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        model.addAttribute("TRADE_PLACE_03",DictCodeListExt1.交易场所_上海证券交易所.getValue());
        model.addAttribute("TRADE_PLACE_04",DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        model.addAttribute("TRADE_PLACE_06",DictCodeListExt1.交易场所_OTC场外.getValue());
        model.addAttribute("JYDSJYS000000001",BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
        model.addAttribute("JYDSJYS000000002",BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
        model.addAttribute("ETR_CHECK_CASH_SWITCH",CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH)==null?"01":CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH));
        return super.init(model);
    }
    
    /**
     * 获取组合代码联想文本框信息 
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSelectPortfolioId")
    public Page<SelectOptionDto> getSelectPortfolioId()
        throws Exception {
        Page<SelectOptionDto> temp = etrFundService.getSelectPortfolioId(RequestUtil.getPageParam());
        return temp;
    }

    /**
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSelectFinprodMarketId")
    public Page<SelectOptionDto> getSelectFinprodMarketId() 
        throws Exception {
        Page<SelectOptionDto> temp = etrFundService.getSelectFinprodMarketId(RequestUtil.getPageParam());
        return temp;
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto 业务实体
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) EtrFundDto dto, BindingResult result) {
        //可用持仓软check by 赵东东 20200121
        String confirmMsg = "";
        String changeLineMark = "<br/>";
        if(StringUtil.isNotEmpty(dto.getEntrustNum())&&StringUtil.isNotEmpty(dto.getAssetAvailablePosition())
            &&(StringUtil.equals(dto.getPs(),DictCodeList.交易方向_卖出.getValue())||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_赎回.getValue())
                ||StringUtil.equals(dto.getPs(),DictCodeListExt2.交易方向_转换申请.getValue()))&&StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                    DictCodeListExt1.是或否_是.getValue())) {
            if(dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition())>0) {
                confirmMsg= this.getMessage("ETR_X_011",this.getMessage("EtrStock001.entrustNum"));
            }
        }
        if(StringUtil.isNotEmpty(confirmMsg)) {
            confirmMsg +=changeLineMark;
            confirmMsg +=getMessage("请确认是否继续?");
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setData(confirmMsg);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody EtrFundDto dto) {
        //授权管理  委托授权额度check
        if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH),"01")){
            EtrCommonController etrCommonController = SpringContextUtil.getBean(
                EtrCommonController.class);
            return etrCommonController.授权是否匹配(dto);
        }
        return new AjaxResult();
    }
    
    /**
     * 
     * 委托存款保存业务流程
     * <功能详细描述>
     * @param dto 业务实体
     * @param br AjaxResult
     * @return AjaxResul
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) EtrFundDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_基金类资产投资委托);
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrFundDto> processParameter = new ProcessParameter<EtrFundDto>();
        WorkFlowParameter<EtrFundDto> params = new WorkFlowParameter<EtrFundDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getEntrustId());
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产投资委托.getValue());

        // 设置流程业务说明, entity即传入的dto
        //业务说明：基金委托投资+"("+组合代码+")"+"-"+交易方向+"-"+"（委托开始日："+委托开始日+"）-"+"（委托结束日："+委托结束日+"）-"+"金额/份额："+金额"
        params.setComment(
            entity -> getMessage("EtrFund001")+ "(" + entity.getPortfolioId()+ ")-" 
                + getMessage("EtrFund001.ps")+ "(" + entity.getPsStr() + ")"  +  "-("
                +getMessage("EtrFund001.entrustVDate")+"：" + DateUtil.format(entity.getVdate()) + ")-("
                +getMessage("EtrFund001.entrustMDate")+"：" + DateUtil.format(entity.getMdate()) + ")-"+getMessage("EtrFund001.entrustAmt")+"/"+getMessage("EtrFund001.entrustNum")+"："+(entity.getEntrustAmt()==null?entity.getEntrustNumStr():entity.getEntrustAmtStr()));
        processParameter.setWorkFlowParameter(params);
        String msg = etrFundService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String,Object> getProAvailableCash(@RequestBody Map<String,Object> param){
        return etrFundService.getProAvailableCash(param);
    }
    
    /**
     * 
     * 组合T+1可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash1")
    public BigDecimal getProAvailableCash1(@RequestBody PtlCashPositionFlowDto dto){
        return etrFundService.getProAvailableCash1(dto);
    }
    
    /**
     * 
     * 组合可用持仓查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto){
        return etrFundService.getAssetAvailablePosition(dto);
    }
    
    /**
     * 
     * 查询单位净值
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPrice")
    public BigDecimal getPrice(@RequestBody Map<String,Object> map){
        return etrFundService.getPrice(map);
    }
    
    /**
     * 通道查询下拉框查询
     * @param map
     * @return
     * @see
     */
    @ResponseBody
    @RequestMapping("/getSelectTongDao")
    public Page<SelectOptionDto> getSelectTongDao() 
    		throws Exception {
    	        Page<SelectOptionDto> temp =  etrFundService.getSelectTongDao(RequestUtil.getPageParam());
    	        return temp;
    }
    /**
     * 通道可用现金查询
     * @param
     * @return
     * @see
     */
    @ResponseBody
    @RequestMapping("/getTongDaoCash")
    public BigDecimal getTongDaoCash(@RequestBody Map<String,Object> param)
    		throws Exception{
        BigDecimal bigDecimal = etrFundService.getChannelAvailableCash(param);
        if(bigDecimal != null){
            return bigDecimal;
        }
        return BigDecimal.ZERO;
    }

    /**
     * 
     * 查询资产管理人
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("selectManeger")
    @ResponseBody
    public Map<String, String> selectManeger(@RequestBody Map<String, Object> param){
        return etrFundService.selectManeger((String)param.get("issuer"));
    }
}
