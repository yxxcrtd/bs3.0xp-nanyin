/*
 * 文件名：EtrLendSpecInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrlendspecinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
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
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.pagemenusplit.PageMenuSplitUtil;
//import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.dto.EtrLendSpecInfoDto;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.service.EtrLendSpecInfoService;
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

import java.util.Date;
import java.util.Map;

/**
 * 拆借委托新建页面
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月17日
 * @see EtrLendSpecInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrLendSpecInfo002")
@RequestMapping("/invest/entrust/etrlendspecinfo/EtrLendSpecInfo002")
public class EtrLendSpecInfo002Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 画面端service
     */
    @Autowired
    private EtrLendSpecInfoService etrLendSpecInfoService;
    /**
     * 初始化拆借委托新建页面
     */
    @Override
    public String init(Model model) {
        
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.金融产品类型_投管用);
        this.setSelectOptionToModel(model, DictCode.拆借类型);
        this.setSelectOptionToModel(model, DictCode.回购拆借方向);
        this.setSelectOptionToModel(model, DictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.期限品种_内部拆借);
        this.setSelectOptionToModel(model, DictCode.清算速度);
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        model.addAttribute("S01", DictCodeList.拆借类型_普通拆借.getValue());
        model.addAttribute("S02", DictCodeList.拆借类型_特殊目的载体.getValue());
        model.addAttribute("T32", DictCodeList.回购拆借方向_拆入.getValue());
        model.addAttribute("T32name", DictCodeList.回购拆借方向_拆入.getCodeName());
        model.addAttribute("T33", DictCodeList.回购拆借方向_拆出.getValue());
        model.addAttribute("T33name", DictCodeList.回购拆借方向_拆出.getCodeName());

        
        String entrustId = getParam("entrustId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            EtrLendSpecInfoDto dto = etrLendSpecInfoService.getBussinessEntityByWorkflowId(
                workflowId);
            if(dto.getSettleSpreed()!=null){
                if(dto.getSettleSpreed()==1){
                    dto.setSettleSpreedStr(DictCodeList.清算速度_T_0.getValue());
                }else if(dto.getSettleSpreed()==2){
                    dto.setSettleSpreedStr(DictCodeList.清算速度_T_1.getValue());
                }
            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId",dto.getWorkflowId());
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(entrustId)) {
            EtrLendSpecInfoDto dto = etrLendSpecInfoService.selectByKey(entrustId);
            if(dto.getSettleSpreed()!=null){
                if(dto.getSettleSpreed()==1){
                    dto.setSettleSpreedStr(DictCodeList.清算速度_T_0.getValue());
                }else if(dto.getSettleSpreed()==2){
                    dto.setSettleSpreedStr(DictCodeList.清算速度_T_1.getValue());
                }
            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("businessId", StringUtil.isNotEmpty(dto.getWorkflowId()) ? dto.getWorkflowId() : dto.getEntrustId());
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
        }
      //新建初始化
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
            EtrLendSpecInfoDto entity = new EtrLendSpecInfoDto();
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            entity.setEntrustStatus(DictCodeList.委托状态_新建.getValue());
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype_cr", DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
        model.addAttribute("fintype_cc", DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
        model.addAttribute("fintype_tccr", DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue());
        
        // add by wangpeng 20210525 银行间场内场外拆分 start
        model.addAttribute("parentPageId", this.getParam("parentPageId"));
        PageMenuSplitUtil.handlePageSelectDictList(model, DictCode.交易场所, this.getParam("parentPageId"));
        // add by wangpeng 20210525 银行间场内场外拆分 end
        
        return super.init(model);
    }
    
    /**
     * 新建页面组合代码联想下拉框 <功能详细描述>
     * 
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getentrustIdAdd")
    public Page<SelectOptionDto> getentrustIdAdd() {
        return etrLendSpecInfoService.getentrustIdAdd(RequestUtil.getPageParam());
    }
    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
        return etrLendSpecInfoService.getProAvailableCash(param);
    }
    /**
     * 保存按钮点击时的check
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
        ValidateGroupEdit.class}) EtrLendSpecInfoDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        
        // 可用现金软校验
        if (dto.getEntrustAmt() != null && dto.getProAvailableCash() != null
            && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_拆出.getValue())) {
            if (dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(this.getMessage("TRD_C_0002"));
                return ar;
            }
        }
        
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存/提交
     * 
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody EtrLendSpecInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_拆借投资委托);
            param.setType1(dto.getAssetType1().equals(DictCodeList.拆借类型_普通拆借.getValue()) ? DictCodeList.业务编号一级分类_普通拆借:DictCodeList.业务编号一级分类_特殊拆借);
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
        }
        // ----------add by yushilong 20200721 预占功能 start----------
        if (StringUtil.equals(BussinessConstant.SUBMIT, dto.getOperateType())) {
            AjaxResult ar = EntrustRdsCommonAop.doPreOccupy(dto,
                    BussinessDictCodeListRds.接口系统ID_预占接口.getValue(),
                    DictCodeList.平安资产类型_拆借.getValue());
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        // ----------add by yushilong 20200721 预占功能 end----------
        ProcessParameter<EtrLendSpecInfoDto> processParameter = new ProcessParameter<EtrLendSpecInfoDto>();
        WorkFlowParameter<EtrLendSpecInfoDto> params = new WorkFlowParameter<EtrLendSpecInfoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
        params.setBusinessType(DictCodeList.共通业务类型_拆借投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrLendSpecInfo001") + "(" + entity.getPortfolioId() + ")"
                      + "-" + this.getMessage("EtrLendSpecInfo001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, entity.getPs())+")" + "-" + "("
                      + this.getMessage("EtrLendSpecInfo001.vdate") + ":" + DateUtil.format(entity.getVdate())
                      + ")-(" + this.getMessage("EtrLendSpecInfo001.mdate") + ":"
                      + DateUtil.format(entity.getMdate()) + ")-"
                      + this.getMessage("EtrLendSpecInfo001.entrustAmt") + ":"
                      + entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        String msg = etrLendSpecInfoService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 判断是否节假日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("judgeIsHday")
    @ResponseBody
    public AjaxResult judgeIsHday(@RequestBody Map<String, Object> param){
        return etrLendSpecInfoService.judgeIsHday(param);
    }

    /**
     * 是否为节假日 是：日期顺延  否：不做处理
     * @param param
     * @return
     */
    @RequestMapping("isHoliday")
    @ResponseBody
    public Date isHoliday(@RequestBody Map<String, Object> param){
        return etrLendSpecInfoService.isHoliday(param);
    }
    
}
