/*
 * 文件名：ChannelAssetQueryController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.channel.controller;

import java.util.HashMap;
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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.asset.channel.dto.ChannelAssetDto;
import com.joyintech.tams.invest.asset.channel.service.ChannelAssetService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 通道资产查询
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年3月13日
 * @see ChannelAsset001Controller
 * @since 1.0
 */
@Controller
@PageId("ChannelAsset001")
@RequestMapping("/invest/asset/channel/ChannelAsset001")
public class ChannelAsset001Controller extends BaseController{

    /**
     * 通道资产查询-处理详细
     */
    @Autowired
    private ChannelAssetService channelAssetService;
    
    /**
     * 界面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        return super.init(model);
        
    }

    /**
     * 分页查询
     * @return 通道资产分页查询
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ChannelAssetDto> findPage() throws Exception{
        return channelAssetService.selectForPage(RequestUtil.getPageParam());
        
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
        return channelAssetService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    /**
     * 通道代码模糊查询
     * @param search 查询条件
     * @return 通道代码 金融产品代码
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSecAcctId")
    public List<SelectOptionDto> getSecAcctId(String search){
        Map<String,Object> map = new HashMap<>();
        map.put("search", search);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        return channelAssetService.getSecAcctId(map);
        
    }
    
   /**
    * 通道名称模糊查询
    * @param search 查询条件
    * @return 通道名称 金融产品代码
    * @see
    * @since 1.0
    */
    @ResponseBody
    @RequestMapping("getSecAcctName")
    public List<SelectOptionDto> getSecAcctName(String search){
        Map<String,Object> map = new HashMap<>();
        map.put("search", search);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        return channelAssetService.getSecAcctName(map);
        
    }
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ChannelAssetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
        ValidateGroupDelete.class}) ChannelAssetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ChannelAssetDto> processParameter = new ProcessParameter<ChannelAssetDto>();
        WorkFlowParameter<ChannelAssetDto> params = new WorkFlowParameter<ChannelAssetDto>();
        params.setPageId("ChannelAsset002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_通道管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("invest.channelId")+getMessage("ChannelAsset002.finprodIdBak")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+GlobalConstant.CLOSE_PARENTHESIS+
                getMessage("invest.channelId")+getMessage("ChannelAsset002.finprodNameBak")+GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodName()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = channelAssetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new ChannelAssetDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
}
