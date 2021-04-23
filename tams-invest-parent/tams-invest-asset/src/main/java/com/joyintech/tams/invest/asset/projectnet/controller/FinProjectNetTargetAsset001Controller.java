/*
 * 文件名：FinProjectTargetAsset001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.projectnet.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.projectnet.dto.FinProjectNetTargetAssetDto;
import com.joyintech.tams.invest.asset.projectnet.service.FinProjectNetTargetAssetService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
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
 *  权益类资产
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinProjectNetTargetAsset001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProjectNetTargetAsset001")
@RequestMapping("/invest/asset/projectnet/FinProjectNetTargetAsset001")
public class FinProjectNetTargetAsset001Controller extends BaseController{

    @Autowired
    private FinProjectNetTargetAssetService finProjectNetTargetAssetService;
    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * 初始化数据字典下拉框
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.项目类一级分类,DictCode.币种
            // 项目类资产五级分类改造 add by renping 2019-07-25 start
            ,DictCode.行内资产二级分类, DictCode.行内资产三级分类, DictCode.行内资产四级分类, DictCode.行内资产五级分类};
            // 项目类资产五级分类改造 add by renping 2019-07-25 end
    
    @Override
    public String init(Model model){
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("YES",DictCodeList.是否_是.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F36", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));
            model.addAttribute("remType", map.get("remType"));
        }
        return super.init(model);
    }
    
    
    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProjectNetTargetAssetDto> selectAll()
        throws Exception {
        Page<FinProjectNetTargetAssetDto> page = finProjectNetTargetAssetService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
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
        ValidateGroupDelete.class}) FinProjectNetTargetAssetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    @ResponseBody
    @RequestMapping("editCheck")
    public AjaxResult editCheck(@RequestBody FinProjectNetTargetAssetDto dto, BindingResult editResult) {
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
    /**
     * 删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinProjectNetTargetAssetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinProjectNetTargetAssetDto> processParameter = new ProcessParameter<FinProjectNetTargetAssetDto>();
        WorkFlowParameter<FinProjectNetTargetAssetDto> params = new WorkFlowParameter<FinProjectNetTargetAssetDto>();
        params.setPageId("FinProjectNetTargetAsset002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinProjectTargetAsset001.finprodId")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+GlobalConstant.CLOSE_PARENTHESIS+
                getMessage("FinProjectTargetAsset001.finprodName")+GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodName()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = finProjectNetTargetAssetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinProjectNetTargetAssetDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
		param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return finProjectNetTargetAssetService.export(this.getPageId(),param);
    }
}
