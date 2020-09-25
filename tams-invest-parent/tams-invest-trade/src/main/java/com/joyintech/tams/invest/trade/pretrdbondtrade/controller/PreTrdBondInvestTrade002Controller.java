/*
 * 文件名：TrdBondInvestTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.pretrdbondtrade.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
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
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussTransDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.TrdTransfenpeiDto;
import com.joyintech.tams.invest.trade.bond.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade002Dto;
import com.joyintech.tams.invest.trade.pretrdbondtrade.service.PreTrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.TrdInvestPersonInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券交易新建
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see PreTrdBondInvestTrade002Controller
 * @since 1.0
 */
@Controller
@PageId("PreTrdBondInvestTrade002")
@RequestMapping("/invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002")
public class PreTrdBondInvestTrade002Controller extends BaseController {

    /**
     * trdBondInvestTrade002Service
     */
    @Autowired
    private TrdBondInvestTrade002Service trdBondInvestTrade002Service;
    
    @Autowired
    private PreTrdBondInvestTrade002Service preTrdBondInvestTrade002Service;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;


    @Override
    public String init(Model model) {
        setSelectOptions(model);
        PreTrdBondInvestTrade002Dto dto = new PreTrdBondInvestTrade002Dto();
        // 默认指令类型为单组合指令
        dto.setZhilingtype("02");
        dto.setTrader(LoginUserUtil.getUserName());
        String prinamt = this.getParam("prinamt");
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String entrustId = this.getParam("entrustId");
        
        TrdInvestPersonInfoDto trdInvestPersonInfoDto=null;
        
        String isCstp = this.getParam("isCstp");
        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
                || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId)&&!StringUtil.equals(workflowId,"undefined"))) {
            dto = preTrdBondInvestTrade002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            trdInvestPersonInfoDto = new TrdInvestPersonInfoDto();
            trdInvestPersonInfoDto.setCounterTrader(dto.getCounterTrader());
            trdInvestPersonInfoDto.setCounterId(dto.getCounterId());
            trdInvestPersonInfoDto.setTrader(dto.getTrader());
            trdInvestPersonInfoDto.setAcctBankDep(dto.getAcctBankDep());
            trdInvestPersonInfoDto.setAcctNo(dto.getAcctNo());
            trdInvestPersonInfoDto.setAcctName(dto.getAcctName());
            trdInvestPersonInfoDto.setSecAcctId(dto.getSecAcctId());
            trdInvestPersonInfoDto.setSecAcctName(dto.getSecAcctName());
            trdInvestPersonInfoDto.setAcctBankDepoPayno(dto.getAcctBankDepoPayno());
            trdInvestPersonInfoDto.setRegistOrg(dto.getRegistOrg());
            model.addAttribute("validPortfolioId",DictCodeListExt1.是否_否.getValue());
        }else if (StringUtil.isNotEmpty(tradeId)) {
            dto = preTrdBondInvestTrade002Service.getPreTrdBondInvestTradeDto(tradeId);
            Long settleSpeed = dto.getSettleSpeed();
            if (settleSpeed != null && Long.compare(settleSpeed,new Long(0)) == 0){
                dto.setSettleSpeedText(DictCodeList.清算速度_T_0.getValue());
            }else {
                dto.setSettleSpeedText(DictCodeList.清算速度_T_1.getValue());
            }

            

            //构建人员信息
            trdInvestPersonInfoDto = new TrdInvestPersonInfoDto();
            trdInvestPersonInfoDto.setCounterTrader(dto.getCounterTrader());
            trdInvestPersonInfoDto.setCounterId(dto.getCounterId());
            trdInvestPersonInfoDto.setTrader(dto.getTrader());
            trdInvestPersonInfoDto.setAcctBankDep(dto.getAcctBankDep());
            trdInvestPersonInfoDto.setAcctNo(dto.getAcctNo());
            trdInvestPersonInfoDto.setAcctName(dto.getAcctName());
            trdInvestPersonInfoDto.setSecAcctId(dto.getSecManageAcctId());
            trdInvestPersonInfoDto.setSecAcctName(dto.getSecAcctName());
            trdInvestPersonInfoDto.setAcctBankDepoPayno(dto.getAcctBankDepoPayno());
            trdInvestPersonInfoDto.setRegistOrg(dto.getRegistOrg());
            model.addAttribute("validPortfolioId",DictCodeListExt1.是否_否.getValue());
        }else if (StringUtil.isNotEmpty(entrustId)){
            dto = preTrdBondInvestTrade002Service.getPreTrdBondTradeDtoFromEtr (entrustId);

            model.addAttribute("validPortfolioId",DictCodeListExt1.是否_否.getValue());
            
            //构建人员信息
            trdInvestPersonInfoDto = new TrdInvestPersonInfoDto();
            trdInvestPersonInfoDto.setCounterTrader(dto.getCounterTrader());
            trdInvestPersonInfoDto.setCounterId(dto.getCounterId());
            trdInvestPersonInfoDto.setSecAcctId(dto.getSecManageAcctId());
        }
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        if(StringUtils.isEmpty(dto.getCalendarId())) {
        dto.setCalendarId("03");//银行间
        }
      //构建组合信息
        
        List<TrdTransfenpeiDto> listDeailed = dto.getTrdTransfenpeiDto();
        List<TrdTransfenpeiDto> list = new ArrayList<>();
        if (!StringUtil.isEmptyList(listDeailed)){
        	BussTransDto bussTransDto = new BussTransDto();
            Map<String,TrdTransfenpeiDto> map = new HashMap<>();
            listDeailed.forEach(item -> {
                if (StringUtil.isNotEmpty(map.get(item.getPortfolioId()))){
                	TrdTransfenpeiDto trdTransfenpeiDto = map.get(item.getPortfolioId());

                }else {
                    map.put(item.getPortfolioId(),item);
                }
            });
            list = map.values().stream().collect(Collectors.toList());
            bussTransDto.setTrdTransfenpeiDtoList(list);
            dto.setBussTransfenpei(bussTransDto);
        }
        
        if(StringUtils.isEmpty(dto.getEntrustId())&&StringUtils.isNotEmpty(entrustId)) {
        	dto.setEntrustId(entrustId);
        }
        // 从"债券中标结果查询"进来，准备进行缴款 add by gejiaao 2019/07/26 End
        if (StringUtil.isNotBlank(tradeId)) {
            model.addAttribute("tradeId", tradeId);
        }
        else {
            //zhujun BSBS-441@2019-05-20 兼容指令接收交易录单附件不显示问题
            model.addAttribute("tradeId", dto.getTotleTradeId()==null?this.getParam("entrustId"):dto.getTotleTradeId());
        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }
        model.addAttribute("entity_bussTransfenpeiDto",
                dto.getBussTransfenpei() == null ? null : JsonUtils.object2Json(dto.getBussTransfenpei()));
        model.addAttribute("entity", dto);
        model.addAttribute("entity_trdInvestPersonInfo",
        		trdInvestPersonInfoDto == null ? null : JsonUtils.object2Json(
        				trdInvestPersonInfoDto));
//        // 划款信息
//        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
//            model.addAttribute("entity_bussRepFinProductInfoDto",
//                JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
//        }

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
        SelectOptionDto option1 = new SelectOptionDto();
        option1.setId("02");
        option1.setText("多组合指令");
        option1.setCol("ZHILING_TYPE");
        option1.setParentId("");
        
        options.add(option1);
        
        model.addAttribute("ZHILING_TYPE", JsonUtils.objectArray2Json(options.toArray()));
//        this.setSelectOptionToModel(model, DictCode.投资目的);
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        Date sysDate = DateUtil.platDateFromCache();
        String formatDate = DateUtil.format(sysDate);
        model.addAttribute("formatDate",formatDate);
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(this.getParam("fTradeId"))){
            model.addAttribute("fTradeId",this.getParam("fTradeId"));
            model.addAttribute("prinamt",prinamt);
        }
        model.addAttribute("isCstp",isCstp);
        /*if(workflowId!=null){
            return "/invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002";
        }else{*/
            return super.init(model);
        /*}*/
    }

    private void setSelectOptions(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易场所,
            DictCode.有效状态, DictCode.交易平台, DictCode.币种,
            DictCode.投资目的, DictCode.交易市场, DictCode.清算速度, DictCode.是否,
            DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类,// 新增资产二、三、四级分类 王贵奇 2019/7/31
            DictCode.WIND一级分类_含债券一级分类等, BussinessDictCode.缴款方式,DictCode.分配方式,DictCode.清算类型,DictCode.计息基础);

        model.addAttribute("TRADE_PLACE_01", DictCodeListExt1.交易场所_银行间.getValue());
        model.addAttribute("TRADE_PLACE_02", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        model.addAttribute("TRADE_PLACE_03", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        model.addAttribute("TRADE_PLACE_05", DictCodeListExt1.交易场所_机构间私募系统.getValue());
        model.addAttribute("SHARES_TRADE_PLATFORM_01", DictCodeList.交易平台_外汇交易中心.getValue());
        model.addAttribute("SHARES_TRADE_PLATFORM_99", DictCodeList.交易平台_其它.getValue());
        //add by zhangnian 20190910  start
        model.addAttribute("SHARES_TRADE_PLATFORM_02", DictCodeList.交易平台_竞价撮合平台.getValue());
        model.addAttribute("SHARES_TRADE_PLATFORM_03", DictCodeList.交易平台_固定收益平台.getValue());
        model.addAttribute("SHARES_TRADE_PLATFORM_04", DictCodeList.交易平台_大宗交易平台.getValue());
        //add by zhangnian 20190910  end
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
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());

        //--------add by 言伟 2019/05/16------start--------
        model.addAttribute("ETR_PAY_TYPE_01", BussinessDictCodeList.缴款方式_一级分销.getValue());
        model.addAttribute("ETR_PAY_TYPE_02", BussinessDictCodeList.缴款方式_一级缴款.getValue());
        model.addAttribute("ETR_PAY_TYPE_03", BussinessDictCodeList.缴款方式_二级交易.getValue());
        model.addAttribute("WINDTYPEONE_B17", DictCodeList.WIND一级分类_含债券一级分类等_优先股.getValue());
        model.addAttribute("ACCT_TYPE_A01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        model.addAttribute("ACCT_TYPE_A07", DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        // 计息基础_A_360
        model.addAttribute("BASIS_A_360", DictCodeList.计息基础_A_360.getValue());
        // 计息基础_A_365
        model.addAttribute("BASIS_A_365", DictCodeList.计息基础_A_365.getValue());
        model.addAttribute("deliveryType_default",DictCodeListExt1.结算方式_券款对付.getValue());
        model.addAttribute("settleType_default",DictCodeListExt1.清算类型_全额清算.getValue());
        //--------add by 言伟 2019/05/16------End--------
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
        return trdBondInvestTrade002Service.getEntrustInfo(dto.getEntrustId());
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
        List<SelectOptionDto> secManageAcctId = trdBondInvestTrade002Service.getSecManageAcctId(params);
        return secManageAcctId;
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
     * 资金账户下拉框
     * 
     * @param params
     * @return
     */
    @RequestMapping("getacctNo")
    @ResponseBody
    public Page<SelectOptionDto> getacctNo(@RequestParam Map<String, Object> params) {
        return preTrdBondInvestTrade002Service.getacctNo(params);
    }
    /**
     * 证券账户下拉框
     * 
     * @param params
     * @return
     */
    @RequestMapping("getsecAcctId")
    @ResponseBody
    public Page<SelectOptionDto> getsecAcctId(@RequestParam Map<String, Object> params) {
        return preTrdBondInvestTrade002Service.getsecAcctId(params);
    }
    //组合代码
    @ResponseBody
    @RequestMapping("getdeliveryType")
    public List<SelectOptionDto> getdeliveryType(@RequestParam Map<String, Object> params) {
    	return preTrdBondInvestTrade002Service.getdeliveryType(params);
    }
    //组合代码
    @ResponseBody
    @RequestMapping("getclearType")
    public List<SelectOptionDto> getclearType(@RequestParam Map<String, Object> params) {
    	return preTrdBondInvestTrade002Service.getclearType(params);
    }
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
    public AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody PreTrdBondInvestTrade002Dto dto, BindingResult result) {
        
    	// 到期日如果是节假日 要提醒
        AjaxResult ajaxResult = preTrdBondInvestTrade002Service.checkConfirm(dto, result);
        if (ajaxResult != null) {
            return ajaxResult;
        }
        
        return commonCheck(result, dto.getOperateType());  
    }


    
    /**
     * 保存处理
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody PreTrdBondInvestTrade002Dto dto)   throws Exception {

        String msg = "";
//        Object[] fenpeiDto = dto.getBussTrdTransfenpeiDto();
//        List<TrdTransfenpeiDto> list = new ArrayList<TrdTransfenpeiDto>();
//        if(fenpeiDto!=null) {
//        	for(int i=0;i<fenpeiDto.length;i++) {
//             TrdTransfenpeiDto model = (TrdTransfenpeiDto) JsonUtils.map2entity((Map<String,Object>)fenpeiDto[0], TrdTransfenpeiDto.class);
//             list.add(model);
//        	}
//        dto.setTrdTransfenpeiDto(list);
//        }
        if (StringUtil.isEmpty(dto.getTotleTradeId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                    codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
                } else {
                    codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
                }
                dto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            } else {
                dto.setTotleTradeId(IDGenerator.generate());
            }
        }
        // 金融产品类型（投管分类）= 数据字典缓存.金融产品类型（投管用）数据字典（根据交易方向与回购类型来综合判断）
        
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        
        ProcessParameter<PreTrdBondInvestTrade002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<PreTrdBondInvestTrade002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("PreTrdBondInvestTrade002");
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        } else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId("PreTrdBondInvestTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessType(DictCodeListExt1.共通业务类型_债券交易预录入.getValue());
        workFlowParameter.setBusinessCode((entity) -> dto.getTotleTradeId());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdBondInvestTrade002.tradeId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdBondInvestTrade002.finprodName") + GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodName() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdBondInvestTrade002.tradeDate") + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS)
                );
        preTrdBondInvestTrade002Service.prepareEntity(dto);
        msg = preTrdBondInvestTrade002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    //组合代码
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestParam Map<String, Object> params) {
        List<SelectOptionDto> PortfolioI = trdBondInvestTrade002Service.selectPortfolioId(params);
        return PortfolioI;
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

//    @ResponseBody
//    @RequestMapping("querySecCstpInfoByNo")
//    public TrdBondInvestTradeCSTPDto querySecCstpInfoByNo(String fedealno){
//        return trdBondInvestTrade002Service.querySecCstpInfoByNo(fedealno);
//    }

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
    
    // add by zhanggenglin 质押式回购多组合 20190813 start
    @ResponseBody
    @RequestMapping("/selectIndex")
    public String selectIndex() {
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        String index=baseGetBusinessId.buildBussinessCode(codeParameter);
        return index;
    }
    // add by zhanggenglin 质押式回购多组合 20190813 end




    /**
     * 查询委托信息
     *
     * @return 委托信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getEntrustInfos")
    public List<BaseEtrInvestInfoDto> getEntrustInfos(@RequestBody TrdBondInvestTradeDto dto) {
        //中标结果查询进来  add by gejiaao 2019/7/26 start
//        if (StringUtil.isNotBlank(dto.getBondFirstSign())){
//            return trdBondInvestTrade002Service.getEntrustInfoTmp(dto.getEntrustId());
//        }
        //中标结果查询进来  add by gejiaao 2019/7/26 end
        List<BaseEtrInvestInfoDto> entrustInfos = trdBondInvestTrade002Service.getEntrustInfos(dto.getEntrustId());
        return entrustInfos;
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
        return preTrdBondInvestTrade002Service.getFinprodId(params);
    }
}
