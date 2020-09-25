/*
 * 文件名：AssetCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.common.service.AssetCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 资产共通controller
 * @author zhangnian
 * @version 1.0 2018年9月21日
 * @see AssetCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/asset/common")
public class AssetCommonController extends BaseController{
    
    @Autowired
    private AssetCommonService assetCommonService;

    /**
     * 各种共通组件
     */
    @Autowired(required=false)
    private CommonBusinessService commonBusinessService;

    @Autowired
    private DateCalService dateCalService;
    
    /**
     * 获取所有资产id--联想文本框 
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAssetId")
    public List<SuggestDto> getAssetId(){
        String search = this.getParam("param")==null?StringUtil.EMPTY:this.getParam("param");
        String finprodType2 = this.getParam("finprodType2")==null?StringUtil.EMPTY:this.getParam("finprodType2");
        String profitType = this.getParam("profitType")==null?StringUtil.EMPTY:this.getParam("profitType");
        return assetCommonService.getAssetId(search,finprodType2,profitType);
    }
    
    
    @ResponseBody
    @RequestMapping("getAssetIdOption")
    public Page<SelectOptionDto> getAssetIdOption(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return assetCommonService.getAssetIdOption(param);
    }

    @ResponseBody
    @RequestMapping("getSysDictCode")
    public Page<SelectOptionDto> getSysDictCode(){
//        String dictCode = this.getParam("dictCode")==null?StringUtil.EMPTY:this.getParam("dictCode");
//        RequestUtil.getPageParam().getOrDefault("PAGE_ID","");

        return assetCommonService.getSysDictCode(RequestUtil.getPageParam());
    }

    /**
     * 交易日历下拉框查询
     * 
     * @return 交易日历
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryCalendarInfoList")
    public List<SelectOptionDto> queryCalendarInfoList(){
        Map<String,Object> param = RequestUtil.getPageParam();
        param.put("isTradeCalendar", DictCodeList.是否_是.getValue());
        return assetCommonService.queryCalendarInfoList(param);
    }
    
    /**
     * 资产提供行下拉框查询
     * 
     * @return 交易日历
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getTradeOpponent")
    public List<SelectOptionDto> getTradeOpponent(){
        Map<String,Object> param = RequestUtil.getPageParam();
        param.put("counterType", DictCodeListExt1.交易对手类型_分行.getValue());
        return assetCommonService.getTradeOpponent(param);
    }
    
    /**
     * 客户信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCustomerName")
    public List<SelectOptionDto> getCustomerName(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return assetCommonService.getCustomerName(param);
    }

    /**
     * 市场机构客户信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody


    @RequestMapping("getMarketInstitutionCustomerName")
    public List<SelectOptionDto> getMarketInstitutionCustomerName(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return assetCommonService.getMarketInstitutionCustomerName(param);
    }
    /**
     * 批复号
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPflshList")
    public Page<SelectOptionDto> getPflshList(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return assetCommonService.getPflshList(param);
    }
    /**
     * 实际授信主体
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getActualCreditProvid")
    public Page<SelectOptionDto> getActualCreditProvid(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return assetCommonService.getActualCreditProvid(param);
    }
    @ResponseBody
    @RequestMapping("getRepInfoByIssuer")
    public MstCustomerInfoAdd getRepInfoByIssuer(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return assetCommonService.getRepInfoByIssuer(param);
    }

    @ResponseBody
    @RequestMapping("editCheck")
    public AjaxResult editCheck(){
        Map<String,Object> param = RequestUtil.getPageParam();
        //判断是否存在交易
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        AjaxResult wr = new AjaxResult();
        if(null != commonBusinessService){
            if(commonBusinessService.isTransInAsset((String)param.get("finprodId"))>0){
                JoyinMessage ms = new JoyinMessage(getMessage("FIN_ASSET_W_0002"),
                        MessageType.确认消息);
                joyinMessages.add(ms);
            }
        }
        wr.setJoyinMessages(joyinMessages);
        return wr;
    }

    /**
     * 标的到期日是否为节假日
     * @param finProduct
     * @return
     */
    @ResponseBody
    @RequestMapping("isHoliday")
    public Boolean isHoliday(@RequestBody FinProduct finProduct){
        if(null==finProduct.getMdate()||StringUtil.isEmpty(finProduct.getCalendarId())){
            return false;
        }
        return dateCalService.isDealCalendarHoliday(finProduct.getMdate(), finProduct.getCalendarId());
    }
}
