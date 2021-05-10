package com.joyintech.tams.valuation.basicdata.asset.common.controller;

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
import com.joyintech.tams.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.tams.valuation.component.business.service.DateCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ValAssetCommonController
 * @Description: 资产共通service
 * @Author yanya
 * @Date 2021/3/5 0005 
 * @Version V1.0
 **/
@Controller
@RequestMapping("/valuation/basicdata/asset/ValAssetCommon")
public class ValAssetCommonController extends BaseController {

    @Autowired
    private ValAssetCommonService assetCommonService;

    /**
     * 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Mapper
     */
    @Autowired(required = false)
    private DateCalService dateCalService;

    /**
     * 各种共通组件
     */
    @Autowired(required=false)
    private CommonBusinessService commonBusinessService;

    /**
     * 获取所有资产id--联想文本框
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAssetId")
    public List<SuggestDto> getAssetId(){
        String search = this.getParam("param")==null? StringUtil.EMPTY:this.getParam("param");
        String finprodType = this.getParam("finprodType")==null?StringUtil.EMPTY:this.getParam("finprodType");
        String profitType = this.getParam("profitType")==null?StringUtil.EMPTY:this.getParam("profitType");
        return assetCommonService.getAssetId(search,finprodType,profitType);
    }


    /**
     * 资产联想框
     * @return
     */
    @ResponseBody
    @RequestMapping("getAssetIdOption")
    public Page<SelectOptionDto> getAssetIdOption(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return assetCommonService.getAssetIdOption(param);
    }


    /**
     * 交易日历
     * @return
     */
    @ResponseBody
    @RequestMapping("selectCalendarIdForPge")
    public Page<SelectOptionDto> selectCalendarIdForPge(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return assetCommonService.selectCalendarIdForPge(param);
    }

    /**
     * 发行人的联想下拉框事件
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getIssuerId")
    public Page<SelectOptionDto> getIssuerId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        //sql中参数不是search，页面控件传回是search，做转换 yjm 20190731
        map.put("param", map.get("search"));
        Page<SelectOptionDto> temp = assetCommonService.getIssuerId(map);
        return temp;
    }

    /**
     * 标的到期日是否为节假日
     * @param finProduct
     * @return
     */
    @ResponseBody
    @RequestMapping("isHoliday")
    public Boolean isHoliday(@RequestBody FinProduct finProduct){
        if(null== finProduct.getMdate()||StringUtil.isEmpty(finProduct.getCalendarId())){
            return false;
        }
        return dateCalService.isDealCalendarHoliday(finProduct.getMdate(), finProduct.getCalendarId());
    }

    /**
     * 资产编辑判定，是否存在交易
     * @return
     */
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
}
