/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrexchangetrade.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade003Dto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.service.EtrExchangeTrade001Service;
import com.joyintech.tams.invest.entrust.etrexchangetrade.service.EtrExchangeTrade003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade002Dto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.service.EtrExchangeTrade002Service;

/**
 * 交易所指令交易（债券股票回购基金场内指令交易）
 * @author chendijun
 * @version 1.0 2020年7月1日
 * @see EtrExchangeTrade002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrExchangeTrade002")
@RequestMapping("/invest/entrust/etrexchangetrade/EtrExchangeTrade002")
public class EtrExchangeTrade002Controller extends BaseController{

    /**
     * 可转债委托处理详细
     */
    @Autowired
    private EtrExchangeTrade002Service etrExchangeTrade002Service;

    @Autowired
    private EtrExchangeTrade001Service etrExchangeTrade001Service;

    @Autowired
    private EtrExchangeTrade003Service etrExchangeTrade003Service;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,BussinessDictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.金融产品类型_投管用);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        EtrExchangeTrade002Dto exchangeTradeDto002 = new EtrExchangeTrade002Dto();
        model.addAttribute("entity",exchangeTradeDto002);
        model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("DECLA_TYPE_PRM05", DictCodeListExt3.申报类型_不指定.getValue());
        model.addAttribute("DECLA_TYPE_PRM14", DictCodeListExt3.申报类型_指定对手方成交申报.getValue());
        model.addAttribute("ENTRUST_BUSS_TYPE_15", DictCodeListExt3.上农商_指令业务类型_债券质押式回购竞价指令.getValue());
        model.addAttribute("PS_T02", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("PS_T69", DictCodeListExt3.交易方向_质押券出库.getValue());
        model.addAttribute("TARGET_CLASS_01", DictCodeListExt3.目标类型_数量.getValue());
        model.addAttribute("PRICE_MODEL_PRM01", DictCodeListExt2.价格模式_指定价格.getValue());
        
        // 场内交易指令画面持仓列表自动刷新时间（毫秒）
        long etrExchange_posTable_refresh_period = 20000;
        String tempSysParaVal = CacheUtil.getSysParam(
            BussinessConstant.ETREXCHANGE_POSTABLE_REFRESH_PERIOD);
        if (StringUtil.isNotBlank(tempSysParaVal)
            && !StringUtil.equals(BussinessConstant.ETREXCHANGE_POSTABLE_REFRESH_PERIOD,
                tempSysParaVal)) {
            try {
                etrExchange_posTable_refresh_period = Long.parseLong(tempSysParaVal);
            }
            catch (Exception e) {
            }
        }
        model.addAttribute("etrExchange_posTable_refresh_period", etrExchange_posTable_refresh_period);
        /*add by chenjiaxing 更改交易方向枚举值 start*/
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.交易方向, null);
        for(int i = 0 ; i < selectDto1.getOptions().size() ; i++){
            if(StringUtil.equals(selectDto1.getOptions().get(i).getId(),"T03")){
                selectDto1.getOptions().get(i).setText("债回售");
            }
        }
        List<SelectOptionDto> optionsGradeResult = selectDto1.getOptions();
        model.addAttribute("PS_ETR", JsonUtils.objectArray2Json(optionsGradeResult.toArray()));
        /*add by chenjiaxing 更改交易方向枚举值 end*/
        return super.init(model);
    }
    
    /**
     * 查询持仓
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPositionsPage")
    public Page<EtrExchangeTrade002Dto> findPositionsPage(){
        return etrExchangeTrade002Service.findPositionsPage(RequestUtil.getPageParam());
    }

    /**
     * 查询债券质押持仓
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findZqzyPositionsPage")
    public Page<EtrExchangeTrade002Dto> findZqzyPositionsPage(){
        return etrExchangeTrade002Service.findZqzyPositionsPage(RequestUtil.getPageParam());
    }

    /**
     * 查询证券代码联想框文本框
     *
     * @param params 画面参数
     * @return 证券代码联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecAcctId")
    @ResponseBody
    public Page<SelectOptionDto> getSecAcctId(@RequestParam Map<String, Object> params) {
        return etrExchangeTrade002Service.getSecAcctId(params);
    }

    /**
     * 查询上交所和深交所标准券数量
     *
     * @return 上交所和深交所标准券数量
     * @see
     * @since 1.0
     */
    @RequestMapping("loadSecNum")
    @ResponseBody
    public Map<String, String> loadSecNum(Map<String, Object> params) {
        return etrExchangeTrade002Service.loadSecNum(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("selectEtrExchange")
    public Page<EtrExchangeTrade003Dto> selectEtrExchange(){
        Page<EtrExchangeTrade003Dto>  result = etrExchangeTrade001Service.selectEtrExchange(RequestUtil.getPageParam());
        return result;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        /*指令执行导出*/
        String datas = this.getParam("datas");
        List<EtrExchangeTrade003Dto> param1;
        if (datas == null) {
            param1 = etrExchangeTrade001Service.selectEtrExchange2(RequestUtil.getPageParam());
        } else {
            param1 = JsonUtils.json2List(datas, EtrExchangeTrade003Dto.class);
        }
        File outFile = etrExchangeTrade003Service.makeFile(
                "/com/joyintech/tams/invest/entrust/etrexchangetrade/conf/export_conf1.xml",
                param1);
        return ExportUtil.export(
                "场内交易指令管理" + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(DateUtil.platDateFromCache())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
