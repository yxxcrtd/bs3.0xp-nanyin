/*
 * 文件名：FinFundTarget002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.fund.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.valuation.basicdata.asset.fund.dto.FinFundTargetDto;
import com.joyintech.tams.valuation.basicdata.asset.fund.service.FinFundTargetService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 平安基金类标的信息新建
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月11日
 * @see FinFundTarget002Controller
 * @since 1.0
 */
@PageId("FinFundTarget002")
@Controller
@RequestMapping("/valuation/basicdata/asset/fund/FinFundTarget002")
public class FinFundTarget002Controller extends BaseController{
    @Autowired
    private FinFundTargetService finFundTargetService;
    /**
     * 各种共通组件
     */
    @Autowired(required=false)
    private CommonBusinessService commonBusinessService;

    @Autowired
    private ValAssetCommonService assetCommonService;
    /**
     * 
     * 新建页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String finprodId = getParam("finprodId");
      //设置货币基金的参数
        model.addAttribute("F01", DictCodeList.基金类资产类型_货币基金.getValue());
        /* add by zhangpuyu 2019/20/24 start */
        model.addAttribute("counterType",DictCodeListExt1.交易对手类型_分行.getValue());
        /* add by zhangpuyu 2019/20/24 end */
        model.addAttribute("F06", DictCodeListExt1.金融产品类型_基金.getValue());
        //增加快捷录入 add by zhangnian 2019/10/15 start
        String finprodMarketId=getParam("finprodMarketId");
        boolean isQuickInput = Boolean.parseBoolean(getParam("isQuickInput"));
        if (isQuickInput&&StringUtil.isNotEmpty(finprodMarketId)){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("finprodMarketId",finprodMarketId);
            FinFundTargetDto dto = finFundTargetService.findEntity(map);
            if(null!=dto){
                finprodId = dto.getFinprodId();
            }
        }
        //增加快捷录入 add by zhangnian 2019/10/15 end
        //是否存在与工作流中
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FinFundTargetDto dto = finFundTargetService.getBussinessEntityByWorkflowId(
                workflowId);
//            model.addAttribute("pageModel", dto.getPageModel());
            model.addAttribute("businessId", workflowId);
            //commonBusinessService.setTradeMarketId(dto,model);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)) {
            //获得页面展示元素
            FinFundTargetDto dto = finFundTargetService.selectByFinprodId(finprodId);
            if (Integer.valueOf(dto.getPurSpeed())<10){
                dto.setPurSpeed("0"+dto.getPurSpeed());
            }
            if (Integer.valueOf(dto.getRedSpeed())<10){
                dto.setRedSpeed("0"+dto.getRedSpeed());
            }
            if(null != commonBusinessService && dto!= null){
                if (!isQuickInput){
                    if(commonBusinessService.isTransInAsset(dto.getFinprodId())>0){
                        dto.setIsTransInAsset(DictCodeList.是否_是.getValue());
                    }
                    else{
                        dto.setIsTransInAsset(DictCodeList.是否_否.getValue());
                    }
                }
            }
            if(null != dto){
                model.addAttribute("businessId", dto.getFinprodId());
                //commonBusinessService.setTradeMarketId(dto,model);
            }
            
            //commonBusinessService.setTradeMarketId(dto,model);
            model.addAttribute("entity", dto);
        }
        else if(WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(finprodId)){
            FinFundTargetDto dto = new FinFundTargetDto();
            dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            /*dto.setPurSpeed(DictCodeList.清算速度_T_1.getValue());*/
            dto.setTradeMarket(DictCodeListExt1.交易场所_OTC场外.getValue());
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_基金.getValue());
            /**jira:TWO-61 add by chendijun start**/
            dto.setCapiIncomeFeature(DictCodeList.是否_否.getValue());
            //add by yanya 20190830 增加投资性质默认值
            dto.setInvestNature(DictCodeList.投资性质_固定收益类.getValue());
            dto.setFundType(DictCodeList.基金类型_货币基金.getValue());
            /**jira:TWO-61 add by chendijun end**/
            //commonBusinessService.setTradeMarketId(dto,model);
            model.addAttribute("entity", dto);
        }
        this.setSelectOptionToModel(model, DictCode.基金类资产类型,DictCode.币种
            , DictCode.清算速度, DictCode.关联范围,DictCode.交易场所,DictCode.申购赎回确认速度
            ,DictCode.交易所基金类型,DictCode.分级基金,DictCode.ETF类型,DictCode.基金核算类型,DictCode.增值税资产分类
            ,DictCode.基金类资产二级类型,DictCode.是否, DictCode.投资性质, DictCode.基金类型, DictCode.境内外, DictCode.交易日历,DictCode.交易所基金类型);
      //取系统参数
//        model.addAttribute("CE_MODEL", CacheUtil.getSysParam(BussinessConstant.CEMODEL));
        model.addAttribute("INTI_BONUS_MODE", DictCodeList.分红方式_红利再投); // 默认分红方式-红利再投 王贵奇 2019/7/29
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        model.addAttribute("TRM_FUND_TYPE_01",DictCodeListExt2.交易所基金类型_ETF.getValue());
        model.addAttribute("TRM_FUND_TYPE_02",DictCodeListExt2.交易所基金类型_LOF.getValue());
        model.addAttribute("TRM_FUND_TYPE_03",DictCodeListExt2.交易所基金类型_分级基金.getValue());
        model.addAttribute("TRM_FUND_TYPE_04",DictCodeListExt2.交易所基金类型_封闭式基金.getValue());
        model.addAttribute("TRM_FUND_TYPE_05",DictCodeListExt2.交易所基金类型_实时申赎货币基金.getValue());
        return super.init(model);
    }
    /**
     * 获取基金管理人下拉列表
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getManager")
    public Page<SelectOptionDto> getManager()
        throws Exception{
        Map<String,Object>map = RequestUtil.getPageParam();
        return finFundTargetService.getManager(map);
    }
    /**
     * 新建时根据市场代码查询基金信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/findEntity")
    public FinFundTargetDto findEntity(@RequestBody Map<String,Object> map){
        return finFundTargetService.findEntity(map);
    }
    
    /**
     * 新建时根据市场代码查询wind接口基金信息
     * @param finprodMarketId 市场代码
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getInterfFundDto")
    public AjaxResult getInterfFundDto(@RequestParam("finprodMarketId") String finprodMarketId,
    		@RequestParam("tradeMarketId") String tradeMarketId) throws ParseException{

        AjaxResult ar = new AjaxResult();
        FinFundTargetDto dto = finFundTargetService.getInterfFundDto(finprodMarketId + tradeMarketId);
        if (StringUtil.isNotEmpty(dto)) {
        	dto.setFinprodMarketId(finprodMarketId);
            ar.setData(dto);
        }else {
            List<ValidMessage> errList = new ArrayList<ValidMessage>();
            errList.add(new ValidMessage(this.getMessage("COMMON_E_0008",this.getMessage("FinFundTarget001.finprodMarketId"))));
            ar.setData(errList);
        }
        return ar;
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
        ValidateGroupEdit.class}) FinFundTargetDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        errorList = checkForm(dto);
        if (errorList.size()>0){
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
        // 当交易场所为场内并且场内基金类型为空时进行判断
        if ((StringUtil.equals(dto.getTradeMarket(),DictCodeListExt1.交易场所_上海证券交易所.getValue()) ||
                StringUtil.equals(dto.getTradeMarket(),DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                &&(!StringUtil.isNotEmpty(dto.getTrmFundType()))){
            errorList.add(new ValidMessage("交易场所为场内的时候，场内基金类型不能为空！"));
        }

        String finprodId = DictCodeListExt1.金融产品类型_基金.getValue()+"_"+dto.getFinprodMarketId();
        FinFundTargetDto ft = finFundTargetService.selectByFinprodId(finprodId);
        FinFundTargetDto ft1 = finFundTargetService.selectByFinprodId(dto.getFinprodMarketId());
        FinFundTargetDto circulationCodeDto = finFundTargetService.selectBycirculationCode(dto.getCirculationCode());
        if ((ft!=null && !StringUtil.equals("1",dto.getPageModel())) || (ft1!=null && !StringUtil.equals("1",dto.getPageModel())))
        {
            errorList.add(new ValidMessage("资产代码："+dto.getFinprodMarketId()+"数据不能重复！"));
        }
        if (circulationCodeDto!=null && !StringUtil.equals("1",dto.getPageModel()))
        {
            errorList.add(new ValidMessage("上市流通代码："+dto.getCirculationCode()+"数据不能重复！"));
        }
        ajaxResult.setErrorList(errorList);
        if (errorList.size()>0){
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinFundTargetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinFundTargetDto> processParameter = new ProcessParameter<FinFundTargetDto>();
        WorkFlowParameter<FinFundTargetDto> params = new WorkFlowParameter<FinFundTargetDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产.getValue());
        //修改时为画面所选记录的金融产品代码。新建时调用各种业务共通组件.获取金融产品代码
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(commonBusinessService != null){
              //设置金融产品代码
                dto.setFinprodId(commonBusinessService.getFinProdId(dto.getFinprodMarketId(), dto.getFinprodType2(), null));
//                dto.setFinprodId(DictCodeListExt1.金融产品类型_基金.getValue()+"_"+dto.getFinprodMarketId());
            }
        }
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("FinFundTarget001.fundId") + "(" + entity.getFinprodMarketId() + ")"+ "-"
            + SpringContextUtil.getTextValue("FinFundTarget001.finprodName") + "("
            + entity.getFinprodName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finFundTargetService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 获取核算分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFinanceProductClass")
    public FinFundTargetDto getFinanceProductClass(@RequestBody FinFundTargetDto dto){
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        //资产类型
        fdto.setAssetType(dto.getFundType());
        // 场内基金类型
        fdto.setTrmFundType(dto.getTrmFundType());
        //交易场所
        fdto.setTradeMarket(dto.getTradeMarket());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());
        dto.setHsfl(assetCommonService.getFinanceProductClass(fdto));
        return dto;
    }

    /**
     * 获取税务分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFinanceProductTaxClass")
    public FinFundTargetDto getFinanceProductTaxClass(@RequestBody FinFundTargetDto dto){
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        //资产类型
        fdto.setAssetType(dto.getFundType());
        // 场内基金类型
        fdto.setTrmFundType(dto.getTrmFundType());
        //交易场所
        fdto.setTradeMarket(dto.getTradeMarket());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());
        dto.setSwfl(assetCommonService.getFinanceProductTaxClass(fdto));
        return dto;
    }

    /**
     * 根据交易场所和基金类型来获取场内基金类型
     */
    @ResponseBody
    @RequestMapping("/getTrmFundTypeList")
    public List<SelectOptionDto> getTrmFundTypeList()
            throws Exception{
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("dictCode","TRM_FUND_TYPE");
        List<SelectOptionDto> trmFundTypeList = finFundTargetService.getTrmFundTypeList(param);
        List<SelectOptionDto> trmFundTypeListResult = new ArrayList<>();
        String tradeMarket = (String) param.get("tradeMarket");
        String fundType = (String) param.get("fundType");
        if ((StringUtil.equals("03",tradeMarket) || StringUtil.equals("04",tradeMarket))&&(StringUtil.equals("01",fundType))){
            for (SelectOptionDto sod:trmFundTypeList){
                if (!StringUtil.equals("LOF",sod.getText()) && !StringUtil.equals("分级基金",sod.getText()) && !StringUtil.equals("封闭式基金",sod.getText())){
                    trmFundTypeListResult.add(sod);
                }
            }
        }else if (StringUtil.equals("03",tradeMarket) || StringUtil.equals("04",tradeMarket)){
            for (SelectOptionDto sod:trmFundTypeList){
                if (!StringUtil.equals("实时申赎货币基金",sod.getText())){
                    trmFundTypeListResult.add(sod);
                }
            }
        }
        return trmFundTypeListResult;
    }

    /**
     * 查询交易日历信息
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getCalendarIdList")
    public List<SelectOptionDto> getCalendarIdList()
            throws Exception{
        Map<String, Object> param = RequestUtil.getPageParam();
        return finFundTargetService.getCalendarIdList(param);
    }

    /**
     * 校验表单数据
     */
    private List<ValidMessage> checkForm(FinFundTargetDto dto){
        List<ValidMessage> errorList = new ArrayList<>();
        if (!StringUtil.isNotEmpty(dto.getFinprodMarketId())){
            errorList.add(new ValidMessage("finprodMarketId",this.getMessage("FW_E_0006", getMessage("FinFundTarget001.finprodMarketId"))));
        }
        if (!StringUtil.isNotEmpty(dto.getFinprodAbbr())){
            errorList.add(new ValidMessage("finprodAbbr",this.getMessage("FW_E_0006", getMessage("FinFundTarget001.finprodAbbr"))));
        }
        if (!StringUtil.isNotEmpty(dto.getFinprodName())){
            errorList.add(new ValidMessage("finprodName",this.getMessage("FW_E_0006", getMessage("FinFundTarget001.finprodName"))));
        }
        if (!StringUtil.isNotEmpty(dto.getFundType())){
            errorList.add(new ValidMessage("fundType",this.getMessage("FW_E_0006", getMessage("FinFundTarget001.fundType"))));
        }
        if (!StringUtil.isNotEmpty(dto.getManager())){
            errorList.add(new ValidMessage("manager",this.getMessage("FW_E_0006", getMessage("FinFundTarget001.manager"))));
        }
        if (!StringUtil.isNotEmpty(dto.getIdate())){
            errorList.add(new ValidMessage("idate",this.getMessage("FW_E_0006", getMessage("FinFundTarget002.idate"))));
        }
        if (!StringUtil.isNotEmpty(dto.getVdate())){
            errorList.add(new ValidMessage("vdate",this.getMessage("FW_E_0006", getMessage("FinFundTarget002.vdate"))));
        }
        if (!StringUtil.isNotEmpty(dto.getTradeMarket())){
            errorList.add(new ValidMessage("tradeMarket",this.getMessage("FW_E_0006", getMessage("FinFundTarget001.tradeMarket"))));
        }
        if (!StringUtil.isNotEmpty(dto.getCalendarId())){
            errorList.add(new ValidMessage("calendarId",this.getMessage("FW_E_0006", getMessage("FinFundTarget002.calendarId"))));
        }
        if (!StringUtil.isNotEmpty(dto.getCcy())){
            errorList.add(new ValidMessage("ccy",this.getMessage("FW_E_0006", getMessage("FinFundTarget001.ccy"))));
        }
        if (!StringUtil.isNotEmpty(dto.getCapiIncomeFeature())){
            errorList.add(new ValidMessage("investNature",this.getMessage("FW_E_0006", getMessage("FinFundTarget001.investNature"))));
        }
        if (!StringUtil.isNotEmpty(dto.getPurSpeed())){
            errorList.add(new ValidMessage("purSpeed",this.getMessage("FW_E_0006", getMessage("FinFundTarget002.purSpeed"))));
        }
        if (!StringUtil.isNotEmpty(dto.getRedSpeed())){
            errorList.add(new ValidMessage("redSpeed",this.getMessage("FW_E_0006", getMessage("FinFundTarget002.redSpeed"))));
        }
        if (!StringUtil.isNotEmpty(dto.getBlnArea())){
            errorList.add(new ValidMessage("blnArea",this.getMessage("FW_E_0006", getMessage("FinFundTarget002.blnArea"))));
        }
        return errorList;
    }
}
