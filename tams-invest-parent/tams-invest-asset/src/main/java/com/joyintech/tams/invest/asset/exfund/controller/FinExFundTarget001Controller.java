/*
 * 文件名：FinExFundTarget001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V3.0
 * @author meifaxin
 * @version 1.0 2021年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V3.0
 */
 
package com.joyintech.tams.invest.asset.exfund.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.exfund.dto.FinExFundTargetDto;
import com.joyintech.tams.invest.asset.exfund.service.FinExFundTargetService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 场内基金标的														
 * 查询
 * @author  meifaxin
 * @version 1.0 2021年7月8日
 * @see FinExFundTarget001Controller
 * @since 1.0
 */
@PageId("FinExFundTarget001")
@Controller
@RequestMapping("/invest/asset/exfund/FinExFundTarget001")
public class FinExFundTarget001Controller extends BaseController{
    @Autowired
    private FinExFundTargetService finExFundTargetService;
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    /**
     * 
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.ETF类型
            ,DictCode.交易场所,DictCode.交易所基金类型);
        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeList.分红方式_红利再投.getValue());
        dto1.setText(DictCodeList.分红方式_红利再投.getCodeName());
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(DictCodeList.分红方式_现金分红.getValue());
        dto2.setText(DictCodeList.分红方式_现金分红.getCodeName());
        listtype.add(dto1);
        listtype.add(dto2);
        model.addAttribute("BONUS_MODE", JsonUtils.objectArray2Json(listtype.toArray()));
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("/getFinprodMarketIdList")
    public List<SuggestDto> getFinprodMarketIdList(String param)
        throws Exception{
        return finExFundTargetService.getFinprodMarketIdList(param);
    }
    
    /*@ResponseBody
    @RequestMapping("/getIssuer")
    public List<SelectOptionDto> getIssuer()
        throws Exception{
        return finExFundTargetService.getIssuer();
    }*/
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinExFundTargetDto> selectAll()
        throws Exception {
        return finExFundTargetService.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 删除按钮点击时的check
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
        ValidateGroupDelete.class}) FinExFundTargetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 编辑时判断是否为交易数据
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("editCheck")
    public AjaxResult editCheck(@RequestBody  FinExFundTargetDto dto, BindingResult editResult) {
        //判断是否存在交易
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        AjaxResult wr = new AjaxResult();
        if(null != commonBusinessService){
            if(commonBusinessService.isTransInAsset(dto.getFinprodId())>0){
                JoyinMessage ms = new JoyinMessage(getMessage("FIN_ASSET_W_0002"),
                    MessageType.确认消息);
                joyinMessages.add(ms);
            }
        }
        wr.setJoyinMessages(joyinMessages);
        return wr;
    }
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinExFundTargetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinExFundTargetDto> processParameter = new ProcessParameter<FinExFundTargetDto>();
        WorkFlowParameter<FinExFundTargetDto> params = new WorkFlowParameter<FinExFundTargetDto>();
        params.setPageId("FinExFundTarget002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产.getValue());
        //金融产品类型（投管分类）：数据字典.金融产品类型(投管用).存款类资产
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：金融产品代码 + "(" + 金融产品代码+ ")" 
        params.setComment(entity -> SpringContextUtil.getTextValue("FinExFundTarget001.fundId") + "(" + entity.getFinprodMarketId() + ")"+ "-"
            + SpringContextUtil.getTextValue("FinExFundTarget001.finprodName") + "("
            + entity.getFinprodName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finExFundTargetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinExFundTargetDto[dto.getSelectedItem().size()]));
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
    	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return finExFundTargetService.export(this.getPageId(),param);

    }
}
