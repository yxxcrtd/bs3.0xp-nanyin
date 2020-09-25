/*
 * 文件名：EtrForwex002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrforwex.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
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
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.common.controller.EtrCommonController;
import com.joyintech.tams.invest.entrust.etrforwex.dto.EtrForwexDto;
import com.joyintech.tams.invest.entrust.etrforwex.service.EtrForwexService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 外汇远期委托
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年6月18日
 * @see EtrForwex002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrForwex002")
@RequestMapping("/invest/entrust/etrforwex/EtrForwex002")
public class EtrForwex002Controller extends BaseController{

	/**
     * 自定义service
     */
    @Autowired
    private EtrForwexService etrForwexService;
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    
    /**
     * 初始化
     */
	@Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.外汇交易类型, DictCode.货币对,
                DictCode.外汇期限, DictCode.交易方向,DictCode.币种, DictCode.是否,
                DictCode.投资目的});   
        String entrustId = getParam("entrustId");
        
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
        	EtrForwexDto dto = etrForwexService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("settleDate",dto.getSettleDate());
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getBussEtrInvestPersonInfoDto()));
            model.addAttribute("businessId", workflowId);
        }
        else if (StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()) {
        	EtrForwexDto dto = etrForwexService.selectByKey(entrustId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("settleDate",dto.getSettleDate());
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getBussEtrInvestPersonInfoDto()));
            model.addAttribute("businessId", dto.getEntrustId());
        }if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
        	EtrForwexDto entity = new EtrForwexDto();
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        //----add by jjpei 20190718--------委托监控风险试算-----------start--------
        model.addAttribute("FINPROD_TYPE_TG_F12", DictCodeListExt1.金融产品类型_投管用_远期.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_01", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
        //----add by jjpei 20190718--------委托监控风险试算-----------end--------
        model.addAttribute("PINAN_ASSET_TYPE_F14",DictCodeListExt2.平安资产类型_外汇远期.getValue());
        return super.init(model);
    }
	  
	
    /**
     * 保存按钮点击时的check
     * @param dto 业务实体
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) EtrForwexDto dto, BindingResult result) {
    	if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        // modified by wangguoxian 修复YUCHUBS-98投资端-外汇远掉期委托及交易，买入外汇时判断持仓提示错误 20190809 start
        // 货币对
        String curPair = dto.getCurPair();
        // 交易货币
        String dealCur = dto.getCcy();
        // 基准货币金额
        BigDecimal baseCurAmt = null;
        // 基准货币可用持仓
        BigDecimal baseCurPos = null;
        // 非基准货币金额
        BigDecimal noBaseCurAmt = null;
        // 非基准货币可用持仓
        BigDecimal noBaseCurPos = null;

        if (StringUtil.isNotEmpty(curPair) && curPair.indexOf(dealCur) == 0){
            // 交易货币是基准货币
            baseCurAmt = dto.getEntrustAmt();
            baseCurPos = dto.getProAvailableCash();
            // 对应货币是非基准货币
            noBaseCurAmt = dto.getExpCorAmt();
            noBaseCurPos = dto.getCorCurPos();

        }
        else if (StringUtil.isNotEmpty(curPair) && curPair.indexOf(dealCur) > 0){
            // 交易货币是非基准货币
            noBaseCurAmt = dto.getEntrustAmt();
            noBaseCurPos = dto.getProAvailableCash();
            // 对应货币是基准货币
            baseCurAmt = dto.getExpCorAmt();
            baseCurPos = dto.getCorCurPos();
        }

        // 当交易方向为买入时，非基准货币对应的金额不大于可用持仓；否则提交时弹框提醒但不禁止提交
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
            if (noBaseCurAmt != null && noBaseCurPos != null && noBaseCurAmt.compareTo(noBaseCurPos) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                        this.getMessage("ETR_SPOTEX_001", this.getMessage("EtrForwex002.t01"),
                                this.getMessage("EtrForwex002.noBaseCur")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }

        // 当交易方向为卖出时，基准货币对应的金额不大于可用持仓；否则提交时弹框提醒但不禁止提交
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            if (baseCurAmt != null && baseCurPos != null && baseCurAmt.compareTo(baseCurPos) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(
                        this.getMessage("ETR_SPOTEX_001", this.getMessage("EtrForwex002.t02"),
                                this.getMessage("EtrForwex002.baseCur")));
                ar.setMessageType(MessageType.确认消息);
                return ar;
            }
        }
    	
    	/*if(StringUtil.isNotEmpty(dto.getCurPair())){
    		AjaxResult ar = new AjaxResult();
            //根据交易方向，货币对，交易币种，对应币种  校验可用持仓
            String[] curArr = dto.getCurPair().split("/");
            String cur1 = "";
            //交易方向为买/卖时
            if(DictCodeList.交易方向_买入.getValue().equals(dto.getPs())) {
            	//非基准货币（货币对后一个币种）金额不大于可用持仓 
                cur1 = curArr[1];
            }
            //交易方向为卖/买时
            if(DictCodeList.交易方向_卖出.getValue().equals(dto.getPs())) {
            	//基准货币（货币对前一个币种）金额不大于可用持仓
            	cur1 = curArr[0];
            }
            //交易币种  需要判断交易
            if(cur1.equals(dto.getCcy())) {
            	//可用持仓 小于金额
            	if(dto.getProAvailableCash()!=null&&dto.getEntrustAmt()!=null
            			&&dto.getProAvailableCash().compareTo(dto.getEntrustAmt())<0) {
            		ar.setData(getMessage("ETR_FORWEX_003", getMessage("EtrForwex001.entrustAmt"), getMessage("EtrForwex002.proAvailableCash")));
            		ar.setMessageType(MessageType.确认消息);
                    return ar;
            	};
            }
            
            //对应币种	
            if(cur1.equals(dto.getCorCcy())) {
            	//可用持仓 小于金额
            	if(dto.getCorCurPos()!=null&&dto.getExpCorAmt()!=null
            			&&dto.getCorCurPos().compareTo(dto.getExpCorAmt())<0) {
            		ar.setData(getMessage("ETR_FORWEX_003", getMessage("EtrForwex002.expCorAmt"), getMessage("EtrForwex002.corCurPos")));
            		ar.setMessageType(MessageType.确认消息);
                    return ar;
            	};
            }
        }*/
        // modified by wangguoxian 修复YUCHUBS-98投资端-外汇远掉期委托及交易，买入外汇时判断持仓提示错误 20190809 end
        return commonCheck(result, dto.getOperateType());
    }
    
    @RequestMapping("checkAuthorization")
    @ResponseBody
    public AjaxResult checkAuthorization(@RequestBody EtrForwexDto dto) {
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) EtrForwexDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_外汇远期投资委托));
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrForwexDto> processParameter = new ProcessParameter<EtrForwexDto>();
        WorkFlowParameter<EtrForwexDto> params = new WorkFlowParameter<EtrForwexDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getEntrustId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_外汇远期投资委托.getValue());

        // 设置流程业务说明, entity即传入的dto
        //业务说明：外汇远期委托投资+"("+组合代码+")"+"-"+(交易方向)+"-"+"（委托日开始日："+委托开始日+"）-"+"（委托结束日："+委托结束日+"）-"+"金额："+金额+"币种："+币种
        params.setComment(
            entity -> getMessage("EtrForwex001")+ "(" + entity.getPortfolioId()+ ")-" 
                + getMessage("EtrForwex001.ps")+ "(" + entity.getPsStr() + ")"  +  "-("
                + getMessage("EtrForwex001.vdate")+"：" + DateUtil.format(entity.getVdate()) + ")-("
                + getMessage("EtrForwex001.mdate")+"：" + DateUtil.format(entity.getMdate()) + ")-"
                + getMessage("EtrForwex001.entrustAmt")+"："+ entity.getEntrustAmt()
                + getMessage("EtrForwex001.ccy")+"："+ entity.getCcyStr());
        processParameter.setWorkFlowParameter(params);
        String msg = etrForwexService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
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
        return etrForwexService.getProAvailableCash(param);
    }
    
    
    /**
     * 获取托管清算账户
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/selectPtlCapAcctNo")
    public List<SelectOptionDto> selectPtlCapAcctNo()
        throws Exception {
        List<SelectOptionDto> temp = etrForwexService.selectPtlCapAcctNo(RequestUtil.getPageParam());
        return temp;
    }
    
	  
}
