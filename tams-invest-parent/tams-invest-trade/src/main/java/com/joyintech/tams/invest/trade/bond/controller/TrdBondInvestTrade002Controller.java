/*
 * 文件名：TrdBondInvestTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.bond.controller;

import static com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service.getBussinessCodeParameter;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade002Dto;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.component.business.bond.BusiFinExercisePlanService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.bond.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.trade.bond.dto.SecCpriceInfoDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeCSTPDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.bond.validator.TrdBondInvestTradeDtoValidator;
import com.joyintech.tams.invest.trade.common.controller.TrdCommonController;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券交易新建
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see TrdBondInvestTrade002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdBondInvestTrade002")
@RequestMapping("/invest/trade/bond/TrdBondInvestTrade002")
public class TrdBondInvestTrade002Controller extends BaseController {
	@Autowired
	private TradeCommonService tradeCommonService;
    /**
     * trdBondInvestTrade002Service
     */
    @Autowired
    private TrdBondInvestTrade002Service trdBondInvestTrade002Service;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    @Autowired
    private TrdBondInvestTradeDtoValidator trdBondInvestTradeDtoValidator;
    // 行权方式check add by renping 20191020 start
    /**
     * 行权计划共通service
     */
    @Autowired
    private BusiFinExercisePlanService busiFinExercisePlanService;
    // 行权方式check add by renping 20191020 end

    @Autowired(required = false)
    private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
    
    @Autowired
    private TrdCommonController trdCommonController;

    @Value("${flag:false}")
    private boolean flag;
    
    @Override
    public String init(Model model) {
        setSelectOptions(model);
        TrdBondInvestTradeDto dto = new TrdBondInvestTradeDto();
        PreTrdBondInvestTrade002Dto preDto = new PreTrdBondInvestTrade002Dto();//预录入的dto
        // 默认指令类型为单组合指令
        dto.setZhilingtype("01");
        String prinamt = this.getParam("prinamt");
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String payType = this.getParam("payType");      // 缴款方式
        String yield = this.getParam("winPrice");       // 中标 "标位"
        String winPrinAmt = this.getParam("winPrinAmt");// 中标 "中标券面总额"
        String bondFirstSign = this.getParam("bondFirstSign");
        String isCstp = this.getParam("isCstp");
        String isCstpSure = this.getParam("isCstpSure");
        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = trdBondInvestTrade002Service.getBussinessEntityByWorkflowId(
                workflowId);
            dto.setYield(ArithmeticUtil.multiply(dto.getYield(), new BigDecimal(100)));
            dto.setExerYield(ArithmeticUtil.multiply(dto.getExerYield(), new BigDecimal(100)));
            if (dto.getTotalFaceValue() != null) {
                dto.setTradeAmt(dto.getTotalFaceValue());
            }
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("workflowId",workflowId);
            if (StringUtil.isNotEmpty(dto.getBondFirstSign())){
                model.addAttribute("bondFirstSign",dto.getBondFirstSign());
            }
        }
        else if (StringUtil.isNotEmpty(tradeId)) {
            dto = trdBondInvestTrade002Service.getTrdBondInvestTradeDto(tradeId);

            //转仓交易目的 显示为转仓交易的 交易目的
            if(dto!=null){
                String turnInvAim = trdBondInvestTrade002Service.getReverseTradeDtoInvAim(tradeId);
                if(StringUtil.isNotEmpty(turnInvAim)) {
                    dto.setTurnInvAim(turnInvAim);
                }
            }
        }
        else {
            String entrustId = this.getParam("entrustId");//委托
            String preTradeId = this.getParam("preTradeId");//预录入
            if (StringUtil.isNotEmpty(entrustId)) {
                model.addAttribute("entrustId",entrustId);
                dto.setEntrustId(entrustId);
            }
            if(StringUtil.isNotBlank(preTradeId)){
                model.addAttribute("preTradeId",preTradeId);
            }
        }
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());

        // 从"债券中标结果查询"进来，准备进行缴款 add by gejiaao 2019/07/26 Start
        if (StringUtil.isNotEmpty(bondFirstSign)){
            // 当"缴款方式"不为空时，表示为从，债券中标结果中进来的
            dto.setPayType(payType);
            dto.setTradeAmt(BigDecimal.valueOf(Double.valueOf(winPrinAmt == null ? "0" : winPrinAmt)));
            dto.setYield(BigDecimal.valueOf(Double.valueOf(yield == null ? "0" : yield)));
            model.addAttribute("bondFirstSign",bondFirstSign);
            model.addAttribute("winPrinAmt",BigDecimal.valueOf(Double.valueOf(winPrinAmt == null ? "0" : winPrinAmt)));
            model.addAttribute("yield",yield);
        }
        // 从"债券中标结果查询"进来，准备进行缴款 add by gejiaao 2019/07/26 End
        if (StringUtil.isNotBlank(tradeId)) {
            model.addAttribute("tradeId", tradeId);
            //---------add by zxjjpei 风险试算需要交易编号 20200325 start------
            model.addAttribute("tradeEntrustId", tradeId);
            //---------add by zxjjpei 风险试算需要交易编号 20200325 ------
        }
        else {
            //zhujun BSBS-441@2019-05-20 兼容指令接收交易录单附件不显示问题
            model.addAttribute("tradeId", dto.getTotleTradeId());
            //---------add by zxjjpei 风险试算需要交易编号 20200325 start------
            model.addAttribute("tradeEntrustId", dto.getTotleTradeId()==null?this.getParam("entrustId"):dto.getTotleTradeId());
            //---------add by zxjjpei 风险试算需要交易编号 20200325 end------
        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }
//       if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            String counterId = dto.getTrdInvestPersonInfo().getCounterId();
//            map.put("counterId", counterId);
//            String isCounterId = trdBondInvestTrade002Service.getCounterId(map);
//            if (StringUtil.equals(isCounterId, "01")) {
//                dto.setTurnInvAim(DictCodeListExt1.投资目的_摊余成本类.getValue());
//            }else if (StringUtil.equals(isCounterId, "02")){
//                dto.setTurnInvAim(DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue());
//            }
//        }

        Date sysDate = DateUtil.platDateFromCache();
        String formatDate = DateUtil.format(sysDate);
        model.addAttribute("formatDate",formatDate);
        model.addAttribute("entity", dto);
        model.addAttribute("entity_trdInvestPersonInfo",
            dto.getTrdInvestPersonInfo() == null ? null : JsonUtils.object2Json(
                dto.getTrdInvestPersonInfo()));
        // 划款信息
        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
        }

        // 净价radio默认选中
        model.addAttribute("calculateField", "unitCprice");

        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        //--------add by 裴家俊 2019/03/28------start--------
        model.addAttribute("FEE_TYPE_05", BussinessDictCodeList.费用类型_佣金.getValue());
        model.addAttribute("FEE_TYPE_02", BussinessDictCodeList.费用类型_印花税.getValue());
        model.addAttribute("FEE_TYPE_06", BussinessDictCodeList.费用类型_交易手续费.getValue());
        model.addAttribute("FEE_TYPE_JSSXF", BussinessDictCodeList.费用类型_结算手续费.getValue());
        //--------add by 裴家俊 2019/03/28------end--------

        //sjj
        List<SelectOptionDto> options = new ArrayList<>();
        SelectOptionDto option = new SelectOptionDto();
        SelectOptionDto option1 = new SelectOptionDto();
        option.setId("01");
        option.setText("单组合指令");
        option.setCol("ZHILING_TYPE");
        option.setParentId("");
        
        option1.setId("02");
        option1.setText("多组合指令");
        option1.setCol("ZHILING_TYPE");
        option1.setParentId("");
        
        options.add(option);
        options.add(option1);
        
        model.addAttribute("ZHILING_TYPE", JsonUtils.objectArray2Json(options.toArray()));
//        this.setSelectOptionToModel(model, DictCode.投资目的);
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        if(StringUtils.isNotBlank(this.getParam("fTradeId"))){
            model.addAttribute("fTradeId",this.getParam("fTradeId"));
            model.addAttribute("prinamt",prinamt);
        }
        model.addAttribute("secManageAcctId",dto.getSecManageAcctId());
        model.addAttribute("isCstp",isCstp);
        model.addAttribute("isCstpSure",isCstpSure);
        model.addAttribute("shortSellingSupported", CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));
        //add by zhangnian 20200213 start
        model.addAttribute("CCY_CNY",DictCodeList.币种_人民币_CNY.getValue());
        //add by zhangnian 20200213 end
        
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        return super.init(model);
    }

    private void setSelectOptions(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易场所,
            DictCode.有效状态, DictCode.交易平台, DictCode.币种,
            DictCode.投资目的, DictCode.交易市场, DictCode.清算速度, DictCode.是否,
            DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类,// 新增资产二、三、四级分类 王贵奇 2019/7/31
            DictCode.WIND一级分类_含债券一级分类等, BussinessDictCode.缴款方式);
	    SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.投资目的, null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions(); 
            for (int index = options.size() - 1; index >= 0; index-- ) {
                SelectOptionDto option = options.get(index);
                if (StringUtil.equals(option.getId(), BaseDictCodeList.数据状态_新建.getValue())
                    || StringUtil.equals(option.getId(),
                        BaseDictCodeList.数据状态_删除中.getValue())|| StringUtil.equals(option.getId(),
                            BaseDictCodeList.数据状态_修改中.getValue())) {
                    options.remove(option);
                } 
                
            }
            model.addAttribute("TURN_INV_AIM", JsonUtils.objectArray2Json(options.toArray())); 
        }

        model.addAttribute("TRADE_PLACE_01", DictCodeListExt1.交易场所_银行间.getValue());
        model.addAttribute("TRADE_PLACE_02", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        model.addAttribute("TRADE_PLACE_03", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        model.addAttribute("SHARES_TRADE_PLATFORM_01", DictCodeList.交易平台_外汇交易中心.getValue());
        model.addAttribute("SHARES_TRADE_PLATFORM_99", DictCodeList.交易平台_其它.getValue());
        model.addAttribute("SHARES_TRADE_MARKET_01", DictCodeListExt1.交易市场_一级市场.getValue());
        model.addAttribute("SHARES_TRADE_MARKET_02", DictCodeListExt1.交易市场_二级市场.getValue());
        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("PS_T02", DictCodeList.交易方向_卖出.getValue());
        // 一体化对应etliu start
//        model.addAttribute("PS_T03", DictCodeList.交易方向_行权.getValue());
        model.addAttribute("PS_T03", DictCodeList.交易方向_行权_回售.getValue());
        model.addAttribute("PS_T39", DictCodeList.交易方向_行权_赎回.getValue());
        // 一体化对应etliu end
        model.addAttribute("ACCOUNT_SPEED_T0", DictCodeList.清算速度_T_0.getValue());
        model.addAttribute("ACCOUNT_SPEED_T1", DictCodeList.清算速度_T_1.getValue());
        model.addAttribute("ACCOUNT_SPEED_T2", DictCodeList.清算速度_T_2.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());

        //--------add by 言伟 2019/05/16------start--------
        model.addAttribute("ETR_PAY_TYPE_01", BussinessDictCodeList.缴款方式_一级分销.getValue());
        model.addAttribute("ETR_PAY_TYPE_02", BussinessDictCodeList.缴款方式_一级缴款.getValue());
        model.addAttribute("ETR_PAY_TYPE_03", BussinessDictCodeList.缴款方式_二级交易.getValue());
        //--------add by 言伟 2019/05/16------End--------
        
        //--------add by wangpeng 2019/08/16------start--------
        model.addAttribute("WINDTYPEONE_B17", DictCodeList.WIND一级分类_含债券一级分类等_优先股.getValue());
        //--------add by wangpeng 2019/08/16------End--------

        //--------add by wangpeng 2019/09/26------start--------
        model.addAttribute("Collect_Type_01", DictCodeListExt2.收取方式_回款前扣除.getValue());
        //--------add by wangpeng 2019/09/26------End--------

        
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        model.addAttribute("isShowCprice", CacheUtil.getSysParam("IS_SHOW_CPRICE")==null?"N":CacheUtil.getSysParam("IS_SHOW_CPRICE"));
        model.addAttribute("bankCode", CacheUtil.getSysParam("CUSTOMER_BANK_CODE"));
        //画面元素是否显示 add by yzg 20200410
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        model.addAttribute("isEleShow", isEleShow);
    }

    /**
     * 查询委托信息
     *
     * @return 委托信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getEntrustInfo")
    public BaseEtrInvestInfoDto getEntrustInfo(@RequestBody TrdBondInvestTradeDto dto) {
        //中标结果查询进来  add by gejiaao 2019/7/26 start
        if (StringUtil.isNotBlank(dto.getBondFirstSign())){
            return trdBondInvestTrade002Service.getEntrustInfoTmp(dto.getEntrustId());
        }
        //中标结果查询进来  add by gejiaao 2019/7/26 end
        BigDecimal wflIntegratePrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), DictCodeList.平安资产类型_债券.getValue(), DictCodeListExt1.投资目的_卖空.getValue());
       // BigDecimal wflOwnPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), DictCodeList.平安资产类型_债券.getValue(), null);
        BaseEtrInvestInfoDto baseDto=trdBondInvestTrade002Service.getEntrustInfo(dto.getEntrustId());
        baseDto.getEtrEntrust().setEntrustNum(baseDto.getEtrEntrust().getEntrustNum());

        // 融入券剩余
        baseDto.getEtrEntrustAdd().setLendinEntNum(ArithmeticUtil.subtract(ArithmeticUtil.multiply(baseDto.getEtrEntrustAdd().getLendinEntNum(),new BigDecimal(100)),
                ArithmeticUtil.multiply(wflIntegratePrinAmt,new BigDecimal(100))));

        // 本方券剩余
        baseDto.getEtrEntrustAdd().setOurEntNum(ArithmeticUtil.multiply(baseDto.getEtrEntrustAdd().getOurEntNum(),new BigDecimal(100)));
        return baseDto;
    }

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param params 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    @ResponseBody
    public List<SelectOptionDto> getSecManageAcctId(@RequestParam Map<String, Object> params) {
        return trdBondInvestTrade002Service.getSecManageAcctId(params);
    }

    @RequestMapping("getSecManageAccId")
    @ResponseBody
    public List<SelectOptionDto> getSecManageAccId(@RequestParam Map<String, Object> params) {
        return trdBondInvestTrade002Service.getSecManageAccId(params);
    }
    
    
    /**
     * 通道：联想框文本框
     *
     * @param params 画面参数
     * @return 通道：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getChannel")
    @ResponseBody
    public List<SelectOptionDto> getChannel(@RequestParam Map<String, Object> params) {
        return trdBondInvestTrade002Service.getChannel(params);
    }

    /**
     * 资产代码：下拉框
     *
     * @param params 画面参数
     * @return 资产代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId(@RequestParam Map<String, Object> params) {
       String bankCode = CacheUtil.getSysParam("CUSTOMER_BANK_CODE");
       if ("004".equals(bankCode)) { 
            if (StringUtil.isNotEmpty(params.get("portfolioId")) && StringUtil.isNotEmpty(params.get("ps"))
                && StringUtil.equals(params.get("ps").toString(), DictCodeList.交易方向_卖出.getValue())) {
                params.put("portfolioId1", params.get("portfolioId"));
            } 
        }
        return trdBondInvestTrade002Service.getFinprodId(params);
    }
//    资产推荐方 新增 zhuyongkang 20191023 start
    /**
     * 构建推荐方下拉框
     *
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("getAssetRecommandOrg")
    @ResponseBody
    public Page<SelectOptionDto> getAssetRecommandOrg() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return trdBondInvestTrade002Service.getAssetRecommandOrg(param);
    }
//    资产推荐方 新增 zhuyongkang 20191023 start
    /**
     * 行权收益率是否显示
     *
     * @param paramMap 画面参数
     * @return 行权收益率是否显示
     * @see
     * @since 1.0
     */
    @RequestMapping("isExerYieldDivShow")
    @ResponseBody
    public String isExerYieldDivShow(@RequestParam Map<String, Object> paramMap) {
        return trdBondInvestTrade002Service.isExerYieldDivShow(paramMap);
    }

    /**
     * 获取下一个工作日
     *
     * @param params 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    @RequestMapping("getNextWorkDay")
    @ResponseBody
    public String getNextWorkDay(@RequestParam Map<String, Object> params) {
        return trdBondInvestTrade002Service.getNextWorkDay(params);
    }
    
  //货币型产品投资目的默认显示摊余成本 add by liupeilin 2020-01-15 begin
    /**
     * 获取产品类型
     *
     * @param params 画面参数
     * @return 产品类型
     * @see
     * @since 1.0
     */
    @RequestMapping("getProfitType")
    @ResponseBody
    public String getProfitType(@RequestParam Map<String, Object> params) {
        return trdBondInvestTrade002Service.getProfitType(params);
    }
   //货币型产品投资目的默认显示摊余成本 add by liupeilin 2020-01-15 end

    /**
     * 1.6计算偏离度
     *
     * @param params 画面参数
     * @return 偏离度
     * @see
     * @since 1.0
     */
    @RequestMapping("calcDeviationDegree")
    @ResponseBody
    public String calcDeviationDegree(@RequestParam Map<String, Object> params) {
        return trdBondInvestTrade002Service.calcDeviationDegree(params);
    }

    /**
     * 理论行权日取得（根据画面交割日取，取不到直接使用交割日作为理论行权日）
     *
     * @param inputDto 画面参数
     * @return 理论行权日
     * @see
     * @since 1.0
     */
    @RequestMapping("getExerciseDate")
    @ResponseBody
    public AjaxResult getExerciseDate(@RequestBody TrdBondInvestTradeDto inputDto) {
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> p = new HashMap<>();
        p.put("finprodId", inputDto.getFinprodId());
        p.put("psT03", DictCodeList.交易方向_行权_回售.getValue());
        p.put("cashType2", DictCodeListExt1.现金流二级类型_本金.getValue());
        p.put("execType01", DictCodeList.行权状态_未行权.getValue());
        p.put("execType02", DictCodeList.行权状态_已行权.getValue());
        List<FinExercisePlan> planList = busiFinExercisePlanService.getfinExercisePlanByProductId(
            p);
        if (!StringUtil.isEmptyList(planList)) {
            for (FinExercisePlan planDto : planList) {
                if (planDto.getActExerciseDate().compareTo(inputDto.getSettleDate()) == 0) {
                    ajaxResult.setData(DateUtil.format(planDto.getExerciseDate()));;
                }
            }
        }
        return ajaxResult;
    }

    /**
     * 1.7计算按钮点击事件
     *
     * @param inputDto 画面参数
     * @return 计算结果
     * @see
     * @since 1.0
     */
    @RequestMapping("calculateData")
    @ResponseBody
    public AjaxResult calculateData(@RequestBody TrdBondInvestTradeDto inputDto) {
        return trdBondInvestTrade002Service.calculateData(inputDto);
    }

    /**
     * 保存提交前的数据检查
     *
     * @param dto 画面输入参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody TrdBondInvestTradeDto dto, BindingResult result) {
        //先报错误信息在报确认信息 add by gejiaao 2019-07-31 start
//        AjaxResult errorResult = new AjaxResult();
//        List<ValidMessage> valid = trdBondInvestTradeDtoValidator.valid(dto);
//        if (!valid.isEmpty()){
//            errorResult.setErrorList(valid);
//            return errorResult;
//        }
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        //先报错误信息在报确认信息 add by gejiaao 2019-07-31 start

        //huwenbin 指令接收交易录单验证数据已存在流程中
        //add by zhujun @2019-05-16 投资委托交易退回时，此check阻塞修改保存操作
        /*if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList() && StringUtil.isEmpty(dto.getBondFirstSign())){
        	String str = tradeCommonService.check(dto.getEntrustId(), this.getPageId());
			  if(StringUtil.isNotEmpty(str)){ 
				  AjaxResult res= new AjaxResult();
				  res.setMessage(str, MessageType.错误消息); 
				  return res; 
			  }
        	// 查询交易流程中录入的总金额	王贵奇	2019/9/3
        	BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dto.getEntrustId());
            BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), dto.getPortfolioId());
            if(entrustNum.compareTo(wflPrinAmt.add(dto.getTradeAmt())) == -1) {
            	AjaxResult res= new AjaxResult();
            	 List<ValidMessage> errorList = new ArrayList<>();
            	 ValidMessage validMessage = new ValidMessage("tradeAmt",
            			 "可用委托份额不足,当前最多可委托份额为:"+ ArithmeticUtil.multiply(entrustNum.subtract(wflPrinAmt),new BigDecimal(100)));
                     errorList.add(validMessage);
                res.setErrorList(errorList);
                res.setMessageType(MessageType.错误消息);
                return res;
            }
        }*/

        // 确认提醒
        if (result.getErrorCount() == 0) {
            AjaxResult ajaxResult = trdBondInvestTrade002Service.checkConfirmInfo(dto, result);
            String confirmMsg = "";
            if (null != ajaxResult) {
                confirmMsg = ajaxResult.getData().toString();
            }
            String changeLineMark = "<br/>";
            //添加托管账户剩余现金校验 add by yjm  2019-07-03 start
            //银行间 债券买入交易和逐笔划款的通道债券买入交易，提交时都没有判断托管户可用剩余现金是否足够
//            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), dto.getPs())) {
//                if ((StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_银行间.getValue()) && StringUtil.isEmpty(dto.getPayType()))
//                        || StringUtil.equals(dto.getPayType(), DictCodeListExt2.划款方式_逐笔划款.getValue())){
//                    if (StringUtil.isNotEmpty(dto.getBussTrdAvailableCashQuery()) && StringUtil.isNotEmpty(dto.getTradeAmt())) {
//                        if (dto.getTradeAmt().compareTo(dto.getBussTrdAvailableCashQuery().getSecAcctBalance()) > 0) {
//                            if (StringUtil.isNotBlank(confirmMsg)) {
//                                confirmMsg += changeLineMark;
//                            }
//                            confirmMsg += this.getMessage("托管户剩余现金不足，请确认是否继续？");
//                        }
//                    }
//                }
//            }
            //添加托管账户剩余现金校验 add by yjm  2019-07-03 end

            // 行权方式check add by renping 20191020 start
            if(StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), dto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), dto.getPs())) {
                Map<String, Object> p = new HashMap<>();
                p.put("finprodId", dto.getFinprodId());
                p.put("psT03", DictCodeList.交易方向_行权_回售.getValue());
                p.put("cashType2", DictCodeListExt1.现金流二级类型_本金.getValue());
                p.put("execType01", DictCodeList.行权状态_未行权.getValue());
                p.put("execType02", DictCodeList.行权状态_已行权.getValue());
                List<FinExercisePlan> planList = busiFinExercisePlanService.getfinExercisePlanByProductId(
                    p);
                boolean checkFlag = false;
                if (!StringUtil.isEmptyList(planList)) {
                    for (FinExercisePlan planDto : planList) {
                        if (planDto.getActExerciseDate().compareTo(
                            dto.getSettleDate()) == 0) {
                            if ((StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(),
                                dto.getPs())
                                 && StringUtil.equals(DictCodeList.行权方式_回售.getValue(),
                                     planDto.getExerciseType()))
                                || (StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(),
                                    dto.getPs())
                                    && StringUtil.equals(DictCodeList.行权方式_赎回.getValue(),
                                        planDto.getExerciseType()))) {
                                checkFlag = false;
                                break;
                            }
                            else {
                                checkFlag = true;
                            }
                        }
                    }
                }
                if(checkFlag){
                    if (StringUtil.isNotBlank(confirmMsg)) {
                      confirmMsg += changeLineMark;
                  }
                  confirmMsg += this.getMessage("交易方向与债券行权计划中行权方式不同，请确认是否继续？");
                }
            }
            // 行权方式check add by renping 20191020 end
            //外部交易对手买入，校验持仓+本金 < 融资金额或者发行金额
//            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) &&
//                    StringUtil.equals(dto.getTrdInvestPersonInfo().getIsOutTrade(), "S")){
//                // 调用基础组件.【组合可用现金和实际现金查询更新组件.查询持仓(可用)】
//                Map<String, Object> paramMap = new HashMap<String, Object>();
//                paramMap.put("portfolioId", dto.getPortfolioId());
//                paramMap.put("tradeDate", DateUtil.format(dto.getTradeDate()));
//                paramMap.put("ccy", dto.getCcy());
//                paramMap.put("calendarId", dto.getCalendarId());
//                paramMap.put("chlId", dto.getChlId());
//                paramMap.put("finprodId", dto.getFinprodId());
//                //获得资产维度的持仓
//                Map<String, Object>  resultMap = bussTrdAvailableCashQueryService.getFinAvaPosition(paramMap);
//
//                BigDecimal position = new BigDecimal((String) resultMap.get("POSITION"));
//                //融资金额
//                BigDecimal issueAmt = new BigDecimal((String)resultMap.get("ISSUEAMT"));
//                position = ArithmeticUtil.doAdd(position, dto.getTradeAmt());
//                //资产 融资金额、发行金额
//                if (issueAmt.compareTo(position) < 0) {
//                    if (StringUtil.isNotBlank(confirmMsg)) {
//                        confirmMsg += changeLineMark;
//                    }
//                    confirmMsg += getMessage("TRD_C_0005");
//                }
//            }

            if (StringUtil.isNotBlank(confirmMsg)) {
                ajaxResult = new AjaxResult();
                ajaxResult.setData(confirmMsg);
            }
            if (ajaxResult != null) {
                return ajaxResult;
            }
        }
        return commonCheck(result, dto.getOperateType());  
    }

    /**
     * 保存提交
     *
     * @param dto
     * @return 画面输入参数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody TrdBondInvestTradeDto dto)
        throws Exception {
    	if(flag) {
			if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = getBussinessCodeParameter(dto.getPs());
                dto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
			}
			else {
				dto.setTotleTradeId(IDGenerator.generate());
			}
    		trdBondInvestTrade002Service.fastSave(dto);
    		return new AjaxResult("成功");
    	}
    	List<TrdBondInvestTradeDto> dtoList = new ArrayList<>();
    	List<BussTrdTransfenpeiDto> fenpeiList = dto.getBussTrdTransfenpeiDto();
    	List<BussTrdTransferOrderDto> orderList = dto.getBussTrdTransferOrder();
    	for(BussTrdTransferOrderDto bussTrdTransferOrder : orderList) {
    		
    		TrdBondInvestTradeDto tempDto = new TrdBondInvestTradeDto();
    		BeanUtil.copyProperties(tempDto, dto);
    		List<BussTrdTransferOrderDto> tmpOrderList = new ArrayList<BussTrdTransferOrderDto>();
    		if(fenpeiList != null && fenpeiList.size()>0){
    			for(BussTrdTransfenpeiDto fenpei : fenpeiList) {
    				if (StringUtils.equals(bussTrdTransferOrder.getPortfolioId(), fenpei.getPortfolioId())
    						&& bussTrdTransferOrder.getSettleAmt().equals(fenpei.getFpriceAmt())) {
    					tmpOrderList.add(bussTrdTransferOrder);
    					
    					tempDto.setPortfolioId(fenpei.getPortfolioId());
    					tempDto.setTradeAmt(fenpei.getTradeAmt());
    					tempDto.setCpriceAmt(fenpei.getCpriceAmt());
    					tempDto.setFpriceAmt(fenpei.getFpriceAmt());
    					tempDto.setBussTrdTransferOrder(tmpOrderList);

    					if (baseGetBusinessId != null) {
                            BussinessCodeParameter codeParameter = getBussinessCodeParameter(tempDto.getPs());
                            tempDto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
    					}
    					else {
    						tempDto.setTotleTradeId(IDGenerator.generate());
    					}
    					dtoList.add(tempDto);
    				}
        		}
    		}else{
    			if (baseGetBusinessId != null) {
                    BussinessCodeParameter codeParameter = getBussinessCodeParameter(tempDto.getPs());
                    tempDto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
				}
				else {
					tempDto.setTotleTradeId(IDGenerator.generate());
				}
    			dtoList.add(tempDto);
    		}
    		
    	}
        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&&CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            for (TrdBondInvestTradeDto bondDto : dtoList) {
                AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                        BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),DictCodeList.平安资产类型_债券.getValue(),
                        (en)->interfRiskRdsService.sendRiskRdsData(bondDto,en));
                if (StringUtil.isNotEmpty(ar)) {
                    return ar;
                }
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------

        ProcessParameter<TrdBondInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTotleTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_债券类资产交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("TrdBondInvestTrade002.tradeId") + GlobalConstant.COLON + entity.getTotleTradeId(),
            getMessage("TrdBondInvestTrade002.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
            getMessage("TrdBondInvestTrade002.tradeDate") + GlobalConstant.COLON + DateUtil.format(entity.getTradeDate())));
        trdBondInvestTrade002Service.setEntity(dtoList);
        String msg = trdBondInvestTrade002Service.start(processParameter, dtoList.toArray(new TrdBondInvestTradeDto[]{}));
        
        //授信占用额度
        //卖出流程中不占用授信
//        if (!StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), dto.getPs())){
//            trdCommonController.delLinesDeal(dto.getRiskCreditListCommonDto(),workFlowParameter.getBusinessCode(dto));
//        }
        return new AjaxResult(msg);
    }

    //组合代码
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestParam Map<String, Object> params) {
        return trdBondInvestTrade002Service.getPortfolioId(params);
    }

    /**
     * 查询债券cstp交易下拉框
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("querySecCstpInfoList")
    public Page<SelectOptionDto> querySecCstpInfoList() {
        Page<SelectOptionDto> suggestDtos = trdBondInvestTrade002Service.querySecCstpInfoList(RequestUtil.getPageParam());
        return suggestDtos;
    }

    @ResponseBody
    @RequestMapping("querySecCstpInfoByNo")
    public TrdBondInvestTradeCSTPDto querySecCstpInfoByNo(String fedealno,String entrustId,String preTradeId, String isCstpSure){
        return trdBondInvestTrade002Service.querySecCstpInfoByNo(fedealno,entrustId,preTradeId,isCstpSure);
    }

    /**
     * 银行间成交编号联动费用设置
     *
     * @param inputDto 画面参数
     * @return 计算结果
     * @see
     * @since 1.0
     */
    @RequestMapping("calculateFeeData")
    @ResponseBody
    public AjaxResult calculateFeeData(@RequestBody TrdBondInvestTradeDto inputDto) {
        return trdBondInvestTrade002Service.calculateFeeData(inputDto);
    }

    /**
     * check接口的劵面总额是否和有效和流程中的总和一致
     * @param params
     * @return
     */
    @RequestMapping("isAllTradeAmtByFTradeId")
    @ResponseBody
    public AjaxResult isAllTradeAmtByFTradeId(@RequestBody Map<String, Object> params){
        AjaxResult ajaxResult = new AjaxResult();
        boolean allTradeAmtByFTradeId = trdBondInvestTrade002Service.isAllTradeAmtByFTradeId(params);
        ajaxResult.setData(allTradeAmtByFTradeId);
        return ajaxResult;
    }
    
    /**
     * 查询交易页面债券的市值净价和摊销净价（吉林银行产品化需求）
     * @param
     * @return
     * @see
     * @since 1.0 malinlin 2019.12.2
     */
    @ResponseBody
    @RequestMapping("getCprice")
    public AjaxResult getCprice(@RequestBody TrdBondInvestTradeDto inputDto){
        AjaxResult ajaxResult = new AjaxResult();
        SecCpriceInfoDto dto = trdBondInvestTrade002Service.getCprice(inputDto);
        ajaxResult.setData(dto);
        return ajaxResult;
    }
    /**
     * 查询交易对手是否为净值高频或者货币
     * @param params
     * @return
     * @see
     * @since 1.0 malinlin 2019.12.2
     */
    @ResponseBody
    @RequestMapping("getCounterId")
    public AjaxResult getCounterId(@RequestBody Map<String, Object> params){
        AjaxResult ajaxResult = new AjaxResult();
        String isCounterId = trdBondInvestTrade002Service.getCounterId(params);
        if (StringUtil.equals(isCounterId, "01")) {
            ajaxResult.setData(DictCodeListExt1.投资目的_摊余成本类.getValue());
        }else if (StringUtil.equals(isCounterId, "02")){ 
            ajaxResult.setData(DictCodeListExt1.投资目的_公允价值变动计入其他综合收益.getValue());
        }
        return ajaxResult;
    }

    /** @MethodName: 预录入判断金额是否和cstp里面的金额是一致的
     * @Description: TODO
     * @Param: [params]
     * @Return: com.joyintech.framework.ajax.AjaxResult
     * @Author: wangxuelei
     * @Date: 2020/2/17 0017
     **/
    @RequestMapping("isAllTradeAmtByPreTradeId")
    @ResponseBody
    public AjaxResult isAllTradeAmtByPreTradeId(@RequestBody Map<String, Object> params){
        AjaxResult ajaxResult = new AjaxResult();
        Boolean allTradeAmtByPreTradeId = trdBondInvestTrade002Service.isAllTradeAmtByPreTradeId(params);
        ajaxResult.setData(allTradeAmtByPreTradeId);
        return ajaxResult;
    }
}
