/*
 * 文件名：FinFundTarget001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzehui
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.fund.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.tams.invest.asset.fund.dto.FinFundTargetDto;
import com.joyintech.tams.invest.asset.fund.service.FinFundTargetService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 平安基金类标 的资产
 * 查询
 * @author wangzehui
 * @version 1.0 2018年9月7日
 * @see FinFundTarget001Controller
 * @since 1.0
 */
@PageId("FinFundTarget001")
@Controller
@RequestMapping("/invest/asset/fund/FinFundTarget001")
public class FinFundTarget001Controller extends BaseController{
    @Autowired
    private FinFundTargetService finFundTargetService;
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    /**
     * 
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.基金类资产类型,DictCode.币种
            , DictCode.清算速度, DictCode.关联范围, DictCode.收益结转频率
            // 新增资产二、三、四级分类 wangguiqi 2019-07-23 start
            ,DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类
        	// 新增资产二、三、四级分类 wangguiqi 2019-07-23 end	
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
        return finFundTargetService.getFinprodMarketIdList(param);
    }
    
    /*@ResponseBody
    @RequestMapping("/getIssuer")
    public List<SelectOptionDto> getIssuer()
        throws Exception{
        return finFundTargetService.getIssuer();
    }*/
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinFundTargetDto> selectAll()
        throws Exception {
        return finFundTargetService.selectForPage(
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
        ValidateGroupDelete.class}) FinFundTargetDto dto, BindingResult result) {
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
    public AjaxResult editCheck(@RequestBody  FinFundTargetDto dto, BindingResult editResult) {
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
        ValidateGroupDelete.class}) FinFundTargetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinFundTargetDto> processParameter = new ProcessParameter<FinFundTargetDto>();
        WorkFlowParameter<FinFundTargetDto> params = new WorkFlowParameter<FinFundTargetDto>();
        params.setPageId("FinFundTarget002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产.getValue());
        //金融产品类型（投管分类）：数据字典.金融产品类型(投管用).存款类资产
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：金融产品代码 + "(" + 金融产品代码+ ")" 
        params.setComment(entity -> SpringContextUtil.getTextValue("FinFundTarget001.fundId") + "(" + entity.getFinprodMarketId() + ")"+ "-"
            + SpringContextUtil.getTextValue("FinFundTarget001.finprodName") + "("
            + entity.getFinprodName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finFundTargetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinFundTargetDto[dto.getSelectedItem().size()]));
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
        return finFundTargetService.export(this.getPageId(),param);

    }
}
