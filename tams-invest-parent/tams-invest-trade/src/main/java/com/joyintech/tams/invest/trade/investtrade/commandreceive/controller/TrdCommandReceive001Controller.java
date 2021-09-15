/*
 * 文件名：TrdCommandDispatcher001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.investtrade.commandreceive.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.component.business.riskrds.constant.PageFieldToInterfFactory;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.trade.common.aop.TradeRdsCommonAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.invest.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto;
import com.joyintech.tams.invest.trade.investtrade.commandreceive.service.TrdCommandReceive001Service;

/**
 * 指令接收页面
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年9月26日
 * @see TrdCommandReceive001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdCommandReceive001")
@RequestMapping("/invest/trade/investtrade/commandreceive/TrdCommandReceive001")
public class TrdCommandReceive001Controller extends BaseController{

    /**
     * 指令接收service注入
     */
    @Autowired
    private TrdCommandReceive001Service trdCommandReceive001Service;

    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoService;
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // TODO Auto-generated method stub
        setSelectOptionToModel(model,DictCode.币种,DictCode.委托状态,DictCode.平安资产类型,DictCode.交易场所,DictCode.是否);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.委托状态, null);
        List<SelectOptionDto> selectOption2=selectDto.getOptions();
        List<SelectOptionDto> selectOption3=new ArrayList<>();
        for(SelectOptionDto selectOpt:selectOption2){
            selectOption3.add(selectOpt);
        }
        SelectOptionDto sod1 = new SelectOptionDto();
        sod1.setId("99");
        sod1.setText("未完成");
        sod1.setCol("ENTRUST_STATUS");
        selectOption3.add(sod1);
        List<SelectOptionDto> psList = makeAllFloatRate();
        model.addAttribute("psList", JsonUtils.objectArray2Json(psList.toArray()));
        model.addAttribute("OTHER_STATUS", JsonUtils.objectArray2Json(selectOption3.toArray()));
        model.addAttribute("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F02", DictCodeList.平安资产类型_基金.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F03", DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F04", DictCodeList.平安资产类型_计划类资产_净值型.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F05", DictCodeList.平安资产类型_存款.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F06", DictCodeList.平安资产类型_项目类资产.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F08", DictCodeList.平安资产类型_拆借.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F09", DictCodeList.平安资产类型_计划类资产_货币型.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F10", DictCodeList.平安资产类型_买断式回购.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F11", DictCodeListExt2.平安资产类型_股票.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F13", DictCodeListExt2.平安资产类型_外汇即期.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F14", DictCodeListExt2.平安资产类型_外汇远期.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F15", DictCodeListExt2.平安资产类型_外汇掉期.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F12", DictCodeListExt2.平安资产类型_债券借贷.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F16", DictCodeListExt2.平安资产类型_利率互换.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F36", DictCodeListExt1.金融产品类型_投管用_立项指令.getValue());

        // add by etliu 20210421 权益类资产相关代码合并 start
        model.addAttribute("PINAN_ASSET_TYPE_F19", DictCodeListExt3.平安资产类型_权益类资产.getValue());
        // add by etliu 20210421 权益类资产相关代码合并 end

        //add by gejiaao start
        model.addAttribute("PINAN_ASSET_TYPE_F17", DictCodeListExt2.平安资产类型_一级市场债.getValue());
        //add by gejiaao end
        //add by zuoyinyi start
        model.addAttribute("PINAN_ASSET_TYPE_F18", DictCodeListExt3.平安资产类型_期权.getValue());
        //add by zuoyinyi end
        //add by wangpeng start
        model.addAttribute("IRS_TYPE", DictCode.互换方式.getValue());
        model.addAttribute("PS_T04", DictCodeList.交易方向_申购.getValue());
        //add by gaopeng 20191125 start
        model.addAttribute("PS_T49", DictCodeListExt3.交易方向_债转股.getValue());
        //add by gaopeng 20191125 end
        //add by wangpeng end
        model.addAttribute("entrustStatus", "99");
        
        /**add by chendijun 查询条数时没有带上完成状态 start**/
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustStatus", "99");
        /**add by chendijun 查询条数时没有带上完成状态 end**/
        model.addAttribute("NUMBER", trdCommandReceive001Service.otherSelectList(map,false).size());
        map.put("secondaryMarketTrade", DictCodeList.是否_否.getValue());
        model.addAttribute("NUMBERRECEIVE", trdCommandReceive001Service.otherSelectList(map,true).size());
        model.addAttribute("ISTRADERECOMMANDrECEIVE",trdCommandReceive001Service.isTradeRdCommandReceive());
        //add by daizhiwei 2020-02-04,交易场所：银行间 start
        model.addAttribute("TRADE_PLACE_01",DictCodeListExt1.交易场所_银行间.getValue());
        //add by daizhiwei 2020-02-04,交易场所：银行间 end
        model.addAttribute("YES_OR_NO_N",DictCodeList.是否_否.getValue());
        return super.init(model);
    }
    /**
     *
     * 组交易方向下拉框值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private List<SelectOptionDto> makeAllFloatRate() {
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();

        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.交易方向, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.回购拆借方向, null);
        for (SelectOptionDto dto : selectDto2.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto3 = this.getSelectDataFromDataDic(DictCode.互换方式, null);
        for (SelectOptionDto dto : selectDto3.getOptions()) {
            listFloatRate.add(dto);
        }
        return listFloatRate;
    }
    /**
     * 指令已接收分页查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdCommandReceive001Dto> selectAllCommandReceive() {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        Page<TrdCommandReceive001Dto> trdCommandReceive001DtoPage = trdCommandReceive001Service.selectForPage(param);
        return trdCommandReceive001DtoPage;
    }
    /** @MethodName: 投资指令和预录入
    * @Description: TODO
    * @Param: []
    * @Return: com.joyintech.framework.grid.Page<com.joyintech.tams.invest.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto>
    * @Author: wangxuelei
    * @Date: 2020/1/9 0009
    **/
    @ResponseBody
    @RequestMapping("selectForPageFromCstp")
    public Page<TrdCommandReceive001Dto> selectForPageFromCstp() {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        Page<TrdCommandReceive001Dto> trdCommandReceive001DtoPage = trdCommandReceive001Service.selectForPageFromCstp(param);
        return trdCommandReceive001DtoPage;
    }
    /**
     * 指令未接收查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("otherFindPage")
    public Page<TrdCommandReceive001Dto> otherSelectAllCommandReceive() {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return trdCommandReceive001Service.otherSelectForPage(param);
    }
    
    /**
     * 指令未接收数量查询
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("otherSelectList")
    public int otherSelectList(){
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        return trdCommandReceive001Service.otherSelectList(map,false).size();
    }
    
    /**
     * 指令未接收数量查询
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("SelectList")
    public int SelectList(){
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        return trdCommandReceive001Service.otherSelectList(map,true).size();
    }
    
    /**
     * 市场代码联想文本框
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketIds")
    public List<SuggestDto> getFinprodMarketIds() {
        String finprodMarketId=super.getRequest().getParameter("param");
        List<SuggestDto> list = trdCommandReceive001Service.getFinprodMarketIds(finprodMarketId);
        return list;
    }
    /**
     * 查看明细查询
     * @return
     */
    @ResponseBody
    @RequestMapping("selectEtrInvest")
    public List<TrdCommandReceive001Dto> selectEtrInvest(){
        return trdCommandReceive001Service.getEtrInvest(RequestUtil.getParam("entrustId"));
    }
    /**
     * 指令接收
     * @return
     */
    @ResponseBody
    @RequestMapping("receiveEntrust")
    public AjaxResult receiveEntrust(){
        Map<String,Object> param=RequestUtil.getPageParam();
        param.put("entrustIds",StringUtil.splitToList(GlobalConstant.COMMA, RequestUtil.getParam("entrustIds")));
        param.put("trader", LoginUserUtil.getUserName());
        AjaxResult result=new AjaxResult();
        result.setErrorList(trdCommandReceive001Service.receiveEntrust(param));
        return result;
    }
    /**
     * 指令拒收
     */
    @ResponseBody
    @RequestMapping("refuseEntrust")
    public AjaxResult refuseEntrust(){
        Map<String,Object> param=RequestUtil.getPageParam();
        param.put("entrustIds",StringUtil.splitToList(GlobalConstant.COMMA, RequestUtil.getParam("entrustIds")));
        param.put("entrustStatus",StringUtil.splitToList(GlobalConstant.COMMA, RequestUtil.getParam("entrustStatus")));
        param.put("trader", LoginUserUtil.getUserName());
        AjaxResult result=new AjaxResult();
        result.setErrorList(trdCommandReceive001Service.returnEntrust(param));
        // add by zhangrui1 指令退回撤销预占 start
        // 若退回正常，则撤销预占
        if (StringUtil.isEmptyList(result.getErrorList())) {
            @SuppressWarnings("unchecked")
            List<String> enTrustIds = (List<String>)param.get("entrustIds");
            List<Map<String, Object>> list = new ArrayList<>();
            List<String> counterIdList = new ArrayList<>();
            Map<String, String> counterIdMap = splitParams("counterIds",
                    str -> counterIdList.add(str));
            Map<String, PtlPortfolio> innerIds = AllTradeCommonUtil.selectPortfolioIds(
                    counterIdList);
            Map<String, String> psMap = splitParams("psList", null);
            if (!StringUtil.isEmptyList(enTrustIds)) {
                String ps = BussinessDictCodeList.交易方向_买入.getValue();
                enTrustIds.forEach(id -> {
                    Map<String, Object> finDto = new HashMap<>();
                    finDto.put(PageFieldToInterfFactory.INF_EVENTID, id);
                    finDto.put(PageFieldToInterfFactory.INF_PS, psMap.get(id));
                    finDto.put(PageFieldToInterfFactory.INF_EVENTDATE, DateUtil.format(
                            DateUtil.platDateFromCache(), DateUtil.DEFAULT_DATE_FORMAT_EN));
                    finDto.put(PageFieldToInterfFactory.INF_REVOKEFLG, BussinessDictCodeListRds.是否_是.getValue());
                    finDto.put(PageFieldToInterfFactory.INF_ISINNERTRD, innerIds.containsKey(counterIdMap.get(
                            id)) ? BussinessDictCodeListRds.是否_是.getValue() : BussinessDictCodeListRds.是否_否.getValue());
                    TradeRdsCommonAop.getInterfRiskRdsService().changeEventId(finDto);
                    list.add(finDto);
                    // 生成方向指令
                    if (innerIds.containsKey(counterIdMap.get(id))) {
                        finDto = new HashMap<>();
                        finDto.put(PageFieldToInterfFactory.INF_EVENTID, id + ps);
                        finDto.put(PageFieldToInterfFactory.INF_EVENTDATE, DateUtil.format(
                                DateUtil.platDateFromCache(),
                                DateUtil.DEFAULT_DATE_FORMAT_EN));
                        finDto.put(PageFieldToInterfFactory.INF_PS, ps);
                        finDto.put(PageFieldToInterfFactory.INF_REVOKEFLG, BussinessDictCodeListRds.是否_是.getValue());
                        finDto.put(PageFieldToInterfFactory.INF_ISINNERTRD, BussinessDictCodeListRds.是否_是.getValue());
                        list.add(finDto);
                    }
                    // 转换申请生成买入方向指令
                    else if (CommonUtil.isType(psMap.get(id), DictCodeListExt2.交易方向_转换申请)
                            || CommonUtil.isType(psMap.get(id), DictCodeListExt1.交易方向_债转股)) {
                        finDto.put(PageFieldToInterfFactory.INF_PS,
                                BussinessDictCodeList.交易方向_卖出.getValue());
                        finDto.put(PageFieldToInterfFactory.INF_EVENTID,
                                id + BussinessDictCodeList.交易方向_卖出.getValue());
                        finDto = new HashMap<>();
                        finDto.put(PageFieldToInterfFactory.INF_EVENTID, id + ps);
                        finDto.put(PageFieldToInterfFactory.INF_EVENTDATE, DateUtil.format(
                                DateUtil.platDateFromCache(), DateUtil.DEFAULT_DATE_FORMAT_EN));
                        finDto.put(PageFieldToInterfFactory.INF_PS, ps);
                        finDto.put(PageFieldToInterfFactory.INF_REVOKEFLG,
                                BussinessDictCodeListRds.是否_是.getValue());
                        finDto.put(PageFieldToInterfFactory.INF_ISINNERTRD,
                                BussinessDictCodeListRds.是否_是.getValue());
                        list.add(finDto);
                    }
                });
            }
            try {
                ClientData data = TradeRdsCommonAop.getInterfRiskRdsService().sendListDataCommon(
                        list, BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue());
                if (StringUtil.equals(data.getHead().get(InterfUtil.std400msgt), InterfUtil.E)) {
                    result.setMessage(data.getHead().get(InterfUtil.stdrtninfo).toString());
                }
            }
            catch (Exception e) {
                result.setMessage("指令撤销异常");
            }
        }
        // add by zhangrui1 指令退回撤销预占 end
        return result;
    }

    // add by zhangrui1 指令退回撤销预占 start
    private Map<String, String> splitParams(String param, Consumer<String> consumer) {
        List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,
                RequestUtil.getParam(param));
        Map<String, String> map = new HashMap<>();
        if (!StringUtil.isEmptyList(list)) {
            list.forEach(str -> {
                String[] arr = StringUtil.split(str, GlobalConstant.COLON);
                if (arr == null || arr.length == 0) {
                    return;
                }
                map.put(arr[0], arr.length == 2 ? arr[1] : StringUtil.EMPTY);
                if (consumer != null && arr.length == 2 && !StringUtil.isEmpty(arr[1])) {
                    consumer.accept(arr[1]);
                }
            });
        }
        return map;
    }
    // add by zhangrui1 指令退回撤销预占 end

    /**
     * 导出
     * @return
     * @throws Exception
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
        return trdCommandReceive001Service.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
    /**
     * 操作的check
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody TrdCommandReceive001Dto dto){
        AjaxResult result=new AjaxResult();
        result.appendMessageList(trdCommandReceive001Service.check(dto));
        return result;
    }

    /**
     * @param
     * @return
     */
    @RequestMapping("entrustCheck")
    @ResponseBody
    public AjaxResult entrustCheck(@RequestBody Map<String, Object> objectMap) {
        AjaxResult at = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        String entrustId = String.valueOf(objectMap.get("entrustId"));
        BaseEtrInvestInfoDto etrInvestInfoDto = baseEtrInvestInfoService.queryActiveMandate(entrustId);
        List<BaseEtrInvestInfoDto> list = etrInvestInfoDto.getBaseEtrInvestInfoDtoList();
        List<BaseEtrInvestInfoDto> etrInvestInfoDtoList = new ArrayList<>();
        if(StringUtil.isEmptyList(list)){
            etrInvestInfoDtoList.add(etrInvestInfoDto);
        }else {
            etrInvestInfoDtoList.addAll(list);
        }
        BaseEtrInvestInfoDto basicData = etrInvestInfoDtoList.get(0);
        Long settleSpeed = basicData.getEtrEntrustAdd().getSettleSpreed();
        Long termDays = basicData.getEtrEntrustAdd().getTermDays();
        BigDecimal price = basicData.getEtrEntrust().getPrice();
        String assetType = etrInvestInfoDtoList.get(0).getEtrEntrustAdd().getAssetType();
        for (BaseEtrInvestInfoDto baseEtrInvestInfoDto : etrInvestInfoDtoList){

            if (StringUtil.equals(DictCodeList.委托状态_已完成.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                || StringUtil.equals(DictCodeList.委托状态_已关闭.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getEntrustStatus())){
                errorList.add(new ValidMessage("已完成或已关闭状态不能预录入交易"));
                break;
            }

            if (!(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getFinprodType2())
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getFinprodType2()))
                && !StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getFinprodType2())){
                errorList.add(new ValidMessage("所选对象存在不支持预录入交易的资产类型"));
                break;
            }
            if (!StringUtil.equals(DictCodeListExt1.交易场所_银行间.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getTradeMarket())){
                errorList.add(new ValidMessage("预录入交易只支持银行间交易市场"));
                break;
            }
            //下面校验项为 选择多条指令时校验，所以当选择一条指令时，退出后续校验
            if (etrInvestInfoDtoList.size() == 1){
                break;
            }
            if (!StringUtil.equals(assetType,baseEtrInvestInfoDto.getEtrEntrustAdd().getAssetType())){
                errorList.add(new ValidMessage("请选择同一资产类型指令对象进行操作"));
                break;
            }
            if ((StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getFinprodType2())
                    || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getFinprodType2()))){
                if (!StringUtil.equals(basicData.getEtrEntrust().getPs(),baseEtrInvestInfoDto.getEtrEntrust().getPs())){
                    errorList.add(new ValidMessage("交易方向不匹配，无法组合新建"));
                }
                if(settleSpeed != null && baseEtrInvestInfoDto.getEtrEntrustAdd().getSettleSpreed() != null
                    && Long.compare(settleSpeed,baseEtrInvestInfoDto.getEtrEntrustAdd().getSettleSpreed()) != 0){
                    errorList.add(new ValidMessage("清算速度不匹配，无法组合新建"));
                }
                if(termDays != null && baseEtrInvestInfoDto.getEtrEntrustAdd().getTermDays() != null
                    && Long.compare(termDays,baseEtrInvestInfoDto.getEtrEntrustAdd().getTermDays()) != 0){
                    errorList.add(new ValidMessage("期限天数不匹配，无法组合新建"));
                }
                // 去掉多余的check by 赵东东 20200327 start
//              if (price != null && baseEtrInvestInfoDto.getEtrEntrust().getPrice() != null
//                  &&price.compareTo(baseEtrInvestInfoDto.getEtrEntrust().getPrice()) != 0){
//                  errorList.add(new ValidMessage("委托价格不匹配，无法组合新建"));
//              }
                // 去掉多余的check by 赵东东 20200327 end
              if (!StringUtil.equals(basicData.getEtrEntrustAdd().getSecAcctId(),baseEtrInvestInfoDto.getEtrEntrustAdd().getSecAcctId())){
                  errorList.add(new ValidMessage("证券账户不匹配，无法组合新建"));
              }
              // 去掉多余的check by 赵东东 20200327 start 
//              if(!StringUtil.equals(basicData.getEtrEntrust().getCounterId(),baseEtrInvestInfoDto.getEtrEntrust().getCounterId())){
//                  errorList.add(new ValidMessage("交易对手不匹配，无法组合新建"));
//              }
//              if(!StringUtil.equals(basicData.getEtrEntrust().getCounterTrader(),baseEtrInvestInfoDto.getEtrEntrust().getCounterTrader())){
//                  errorList.add(new ValidMessage("对手方交易员不匹配，无法组合新建"));
//              }
              // 去掉多余的check by 赵东东 20200327 end 
          }
            if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(),baseEtrInvestInfoDto.getEtrEntrust().getFinprodType2())){
                if (price != null && baseEtrInvestInfoDto.getEtrEntrust().getPrice() != null
                        &&price.compareTo(baseEtrInvestInfoDto.getEtrEntrust().getPrice()) != 0){
                    errorList.add(new ValidMessage("委托价格不匹配，无法组合新建"));
                }
                if (!StringUtil.equals(basicData.getEtrEntrustAdd().getSecAcctId(),baseEtrInvestInfoDto.getEtrEntrustAdd().getSecAcctId())){
                    errorList.add(new ValidMessage("证券账户不匹配，无法组合新建"));
                }
                if(!StringUtil.equals(basicData.getEtrEntrust().getCounterId(),baseEtrInvestInfoDto.getEtrEntrust().getCounterId())){
                    errorList.add(new ValidMessage("交易对手不匹配，无法组合新建"));
                }
                if(!StringUtil.equals(basicData.getEtrEntrust().getCounterTrader(),baseEtrInvestInfoDto.getEtrEntrust().getCounterTrader())){
                    errorList.add(new ValidMessage("对手方交易员不匹配，无法组合新建"));
                }
                if (!StringUtil.equals(basicData.getEtrEntrust().getPs(),baseEtrInvestInfoDto.getEtrEntrust().getPs())){
                    errorList.add(new ValidMessage("交易方向不匹配，无法组合新建"));
                }
                if(settleSpeed != null && baseEtrInvestInfoDto.getEtrEntrustAdd().getSettleSpreed() != null
                        && Long.compare(settleSpeed,baseEtrInvestInfoDto.getEtrEntrustAdd().getSettleSpreed()) != 0){
                    errorList.add(new ValidMessage("清算速度不匹配，无法组合新建"));
                }
                if(!StringUtil.equals(basicData.getEtrEntrust().getFinprodId(),baseEtrInvestInfoDto.getEtrEntrust().getFinprodId())){
                    errorList.add(new ValidMessage("金融资产代码不匹配，无法组合新建"));
                }
                if(!StringUtil.equals(basicData.getEtrEntrust().getTradeMarketMode(),baseEtrInvestInfoDto.getEtrEntrust().getTradeMarketMode())){
                    errorList.add(new ValidMessage("交易市场不匹配，无法组合新建"));
                }
            }
        }
        if (!StringUtil.isEmptyList(errorList)){
            at.setErrorList(errorList);
        }
        return at;
    }
}
