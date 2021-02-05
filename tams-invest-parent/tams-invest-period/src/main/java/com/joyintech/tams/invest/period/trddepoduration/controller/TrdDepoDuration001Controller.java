/*
 * 文件名：TrdDepoDuration001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trddepoduration.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
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
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trddepoduration.dto.TrdDepoDuration001Dto;
import com.joyintech.tams.invest.period.trddepoduration.service.TrdDepoDuration001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 存款类存续期
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年10月11日
 * @see TrdDepoDuration001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdDepoDuration001")
@RequestMapping("/invest/period/trddepoduration/TrdDepoDuration001")
public class TrdDepoDuration001Controller extends BaseController{
    /**
     * service
     */
    @Autowired
    private TrdDepoDuration001Service trdDepoDuration001Service;
    /**
     * 存续期共通service
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * 画面初始化
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, BussinessDictCode.平安业务类型,BussinessDictCode.画面数据状态);
        model.addAttribute("PAGE_DATA_STATUS_01", BussinessDictCodeList.画面数据状态_待处理.getValue());
        model.addAttribute("PAGE_DATA_STATUS_05", BussinessDictCodeList.画面数据状态_有效.getValue());
        model.addAttribute("PA_TQZQ", BussinessDictCodeList.平安业务类型_存款提前支取.getValue());
        model.addAttribute("CASH_CLASS_TYPE_ZJSX", BussinessDictCodeList.现金流类型_中间收息.getValue());
        model.addAttribute("assetType_tyjk",BussinessDictCodeList.存款类资产类型_同业借款.getValue());
        model.addAttribute("DEPO_ASSET_TYPE_03", DictCodeListExt3.存款类资产类型_活期存放.getValue());
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));
            model.addAttribute("statusValid", "01,03");
            model.addAttribute("remType", map.get("remType"));
            String quaryDate = (String)map.get("quaryDate");

            String mdate = (String)map.get("mdate");
            if(StringUtil.isNotEmpty(mdate)){
                model.addAttribute("quaryDate", DateUtil.addDay(mdate,0)+" - "+DateUtil.addDay(mdate,0));
            }
        }
        return super.init(model);
    }
    
    /**
     * 获取所有组合代码
     *
     * @return 下拉框集合
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public Page<SelectOptionDto> getPortfolioId() {
        return trdDepoDuration001Service.getPortfolioId();
    }
    /**
     * 分页查询 市场代码
     *
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdDepoDuration001Service.getFinprodMarketId();
    }
    
    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdDepoDuration001Dto> findPage() {
        Map<String,Object> map = RequestUtil.getPageParam();
        return trdDepoDuration001Service.findPage(map);
    }
    
    /**
     * 导出
     *
     * @return 文件流
     * @throws Exception 异常
     * @since 1.0
     */
    /* add by zhangpuyu 2019/12/9 start */
   @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        return trdDepoDuration001Service.export(this.getPageId(),
            map);
    }
    /* add by zhangpuyu 2019/12/9 start */
    /**
     * 撤销
     *
     * @return 撤销结果
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdDepoDuration001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Map<String,Object>paramMap = new HashMap<>();
        paramMap.put("statusValid", dto.getStatusValid());
        paramMap.put("busiCode", dto.getpTradeId());
        TrdDepoDuration001Dto temp;
        if(StringUtil.equals(DictCodeListExt1.平安业务类型_存款提前支取.getValue(), dto.getPingAnCodeSin())) {
        	temp = trdDepoDuration001Service.findEffectiveData(paramMap);
        	temp.setBussTrdTransferOrder(periodCommonService.transformOrderDtoBack(periodCommonService.selectPayOrders(temp.getBusiCode())));
        }else {
//            查询非提前支取
        	paramMap.put("isFindEnd","true");
        	temp = trdDepoDuration001Service.findEffectiveData(paramMap);
        	temp.setBussTrdTransferOrder(periodCommonService.transformOrderDtoBack(periodCommonService.selectPayOrders(temp.getBusiCode())));
        	temp.setFinAssetIntInfoList(trdDepoDuration001Service.findAssetIntInfo(paramMap));
        }
        ProcessParameter<TrdDepoDuration001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdDepoDuration001Dto> params = new WorkFlowParameter<>();
        if (DictCodeList.交易方向_到期.getValue().equals(temp.getCashType()) || DictCodeList.交易方向_收息.getValue().equals(temp.getCashType())) {
            params.setPageId("TrdDepoDuration003");
        }
        else if(StringUtil.equals(temp.getAssetType(),DictCodeListExt3.存款类资产类型_活期存放.getValue())){
            params.setPageId("TrdDepoDuration004");
        }
        else {
            params.setPageId("TrdDepoDuration002");
        }
        params.setElementId(dto.getElementId());
//        模式为撤销
        temp.setMode(OperationType.REVOKE.toString());
        temp.setpTradeId(temp.getBusiCode());
        temp.setBusiCode(temp.getBusiCode()+'R');
        temp.setTotleTradeId(temp.getBusiCode());
        params.setBusinessCode(temp.getTotleTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_存款类资产存续期.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(temp.getBusiCode(),
            DateUtil.format(temp.getTradeDate()), DictCodeList.共通业务类型_存款类资产存续期.getCodeName()));
        processParameter.setWorkFlowParameter(params);
        String msg = trdDepoDuration001Service.start(processParameter, temp);
        return new AjaxResult(msg);
    }
    /**
     * 撤销check
     *
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdDepoDuration001Dto dto, BindingResult result) {
        if (StringUtil.equals(dto.getOperateType(), "revoke")) {
            if (periodCommonService.isAlreadyRevoked(dto.getBusiCode())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                        getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(validMessage);
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        return commonCheck(result, dto.getOpType());
    }
    /**
     * 获取所有交易编号
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getBusiCode")
    public Page<SelectOptionDto> getBusiCode() {
        return trdDepoDuration001Service.getBusiCode(RequestUtil.getPageParam());
    }
}
