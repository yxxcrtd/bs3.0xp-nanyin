/*
 * 文件名：BaseRiskMathCommController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.risktrial.RiskTrialComm;
import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.CreditLinesUpdate;
import com.joyintech.tams.invest.common.trade.CreditLinesUpdateImpl;
import com.joyintech.tams.invest.common.trade.dao.CreditLinesDealsMapper;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesInputDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 风险试算共通结果展示画面
 *
 * @author etliu
 * @version 1.0 2018年12月3日
 * @see BaseRiskMathCommController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/common/BaseRiskMathComm")
public class BaseRiskMathCommController extends BaseController {
    /**
     * 风险试算接口
     */
    @Autowired(required = false)
    private RiskTrialComm riskTrialComm;
    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
    @Autowired
    private FinProductAddMapper finProductAddMapper;

    private static String workflowId_JL;

    @Autowired
    private IPlatformDate iPlatformDate;
    @Autowired
    private CreditLinesDealsMapper creditLineMapper;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 占用、释放授信额度共通处理 类
     * 风险试算时获取授信管理中授信使用条件使用
     * Add 20190827 by xkni
     */
    @Autowired(required = false)
    private CeLinesUpdate ceLinesUpdate;

    private static final IDictCode[] DICT_CODES = new DictCode[]{DictCode.指标类型, DictCode.监督状态, DictCode.单位,
            DictCode.监督类型, DictCode.监督频率, DictCode.金融产品类型_投管用, DictCode.WIND一级分类_含债券一级分类等,
            DictCode.基金类资产二级类型, DictCode.资产评级, DictCode.申万行业一级类型, DictCode.指标类型映射编码};

    /**
     * 初始化
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        SelectDto selectDtoLink = this.getSelectDataFromDataDic(DictCode.指标类型映射编码, null);
        List<SelectOptionDto> listFobditLink = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto entrylink : selectDtoLink.getOptions()) {
            SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.fromValue(entrylink.getText()), null);
            for (SelectOptionDto dto : selectDto1.getOptions()) {
                listFobditLink.add(dto);
            }
        }
        model.addAttribute("IND_TYPE_TOTAL_ENUM", JsonUtils.objectArray2Json(listFobditLink.toArray()));

        model.addAttribute("IND_TYPE_FORBID_ASSETTYPE", DictCodeListExt1.指标类型_禁止投资_资产类型.getValue());
        model.addAttribute("IND_TYPE_FORBID_BONDTYPE", DictCodeListExt1.指标类型_禁止投资_债券二级类型.getValue());
        model.addAttribute("IND_TYPE_FORBID_FUNDTYPE", DictCodeListExt1.指标类型_禁止投资_基金类型.getValue());
        model.addAttribute("IND_TYPE_FORBID_DEBTRATE", DictCodeListExt1.指标类型_禁止投资_债项评级.getValue());
        model.addAttribute("IND_TYPE_FORBID_BODYRATE", DictCodeListExt1.指标类型_禁止投资_主体评级.getValue());
        model.addAttribute("IND_TYPE_FORBID_INDUSTRY", DictCodeListExt1.指标类型_禁止投资_行业负面清单.getValue());
        List<RiskTrialRetDto> li = new ArrayList<>();
        //List<RiskTrialRetDto> li = null;
        model.addAttribute("SUP_TIME_TYPE_01", DictCodeListExt1.监督时分_委托监控);
        model.addAttribute("SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控);
        model.addAttribute("SUP_TIME_TYPE_03", DictCodeListExt1.监督时分_事后监控);
        //model.addAttribute("risk_math_switch", CacheUtil.getSysParam("risk_math_switch"));
        model.addAttribute("risk_math_switch", CacheUtil.getSysParam("pageRedisFlag"));
        String institutionCode = "";
        if(MultilevelDataAuthorityUtil.isEnabled()){
            institutionCode =  MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName());
        }
        model.addAttribute("IS_RISKMATH_SOFTCONTROL", CacheUtil.getSpecialSysParam("IS_RISKMATH_SOFTCONTROL", institutionCode));
        Map<String, Object> map = RequestUtil.getPageParam();
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        // add by caojin 吉林银行风险试算-流程控制显示 2019/09/23 start
        workflowId_JL = getParam(GlobalConstant.WORKFLOW_ID);
        model.addAttribute("bankCode", CacheUtil.getSysParam("CUSTOMER_BANK_CODE"));
        // add by caojin 吉林银行风险试算-流程控制显示 end
        //---modify by 裴家俊 2019/04/01-----start----
        if (/*!WorkFlowUtil.isNotFromToDoList() && */StringUtil.isNotEmpty(workflowId) && !StringUtil.equals(workflowId, "null")) {
            li = basRiskMathCommService.getBusinessEntityByWorkFlowId(workflowId, "riskTrialRetDto");
        }
        //---modify by 裴家俊 2019/04/01-----end----
        if (map.get("entityName") != null) {
            model.addAttribute("entityName", map.get("entityName").toString());
        } else {
            model.addAttribute("entityName", "riskTrialRetDto");
        }
        if (!StringUtil.equals(map.get("tradeId"), "null") && map.get("tradeId") != null && StringUtil.isEmptyList(li) && StringUtil.isNotEmpty(map.get("tradeId").toString())) {
            //----add by zxjjpei 解决从指令委托点击交易录单不能带出风险试算结果逻辑 start-----
            if(StringUtil.equals(map.get("tradeSource"), BussinessDictCodeListRds.事件类型_投资交易.getValue())&&map.get("tradeId").toString().substring(0,2).equals("WT")){
                li=new ArrayList<>();
            }else {
                //--------20200228核新版风险 撤销时去除R带出列表 裴家俊 ------------------
                if (map.get("tradeId").toString().lastIndexOf('R') == map.get("tradeId").toString().length() - 1) {
                    li = basRiskMathCommService.select(map.get("tradeId").toString().substring(0, map.get("tradeId").toString().length() - 1));
                } else {
                    li = basRiskMathCommService.select(map.get("tradeId").toString());
                }
                //--------20200228核新版风险 撤销时去除R带出列表 裴家俊 ------------------
            }
            //----add by zxjjpei 解决从指令委托点击交易录单不能带出风险试算结果逻辑 end-----
        }
        model.addAttribute("RiskTrialRetDto", li);
        //---add by 裴家俊  2019/03/30-------start------
        model.addAttribute("workflowBaskRiskId", workflowId);
        //---add by 裴家俊  2019/03/30-------end------
        return "component/common/BaseRiskMathComm";
    }

    //---add by 张庚林  2019/08/13-------start------
    List<RiskTrialRetDto> resultList2 = null;
    //---add by 张庚林  2019/08/13-------end------

    /**
     * 风险试算
     *
     * @param param 画面参数
     * @return 风险试算结果
     * @throws InterruptedException
     * @see
     * @since 1.0
     */
    @RequestMapping("riskMath")
    @ResponseBody
    public List<RiskTrialRetDto> rickMath(@RequestBody InvestTradeTrialInput param) {
        List<RiskTrialRetDto> resultList = new ArrayList<RiskTrialRetDto>();
//        FinProductValuation finProductValuation = new FinProductValuation();
        Map<String, Object> map = new HashMap<>();
        String date = DateUtil.format(new Date());
        FinProductValuation value = null;
        //add by 张年 20190820  -----------母组合替换子组合  start
        param.setPortfolioCode(AllTradeCommonUtil.exchangePortfolio(param.getPortfolioCode()));
        //add by 张年 20190820  -----------母组合替换子组合  end
        //---add by 裴家俊 20190506-----三级类型与投资性质---start----
        FinProductAdd paInvest = null;
        FinProductAdd paInvestInput = new FinProductAdd();
        paInvestInput.setFinprodId(param.getAssetCode());
        FinProductType finType = new FinProductType();
        finType.setFinprodId(param.getAssetCode());
        finType.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));

        FinProductType fin = finProductTypeMapper.selectByPrimaryKey(finType);
        if (null != fin) {
            param.setAssetThrType(fin.getType7());
            if (!StringUtil.isNotEmpty(param.getAssetSecType()) && StringUtil.isNotEmpty(fin.getType6())) {
                param.setAssetSecType(fin.getType6());
            }
        }
        if (StringUtil.isNotBlank(param.getAssetCode())) {
            paInvest = finProductAddMapper.selectByPrimaryKey(paInvestInput.getFinprodId());
        }
        if (null != paInvest) {
            param.setInvestProporty(paInvest.getInvestNature());
        }
        //如果是债券借贷融入的话，置为999999,融入不需要固定收益指标
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue(),param.getAssetType())){
            param.setInvestProporty("999999");
        }
        //---------add by jjpei 20190727---投资性质---start--------
        // 拆借、回购、债券，存款（存款与债券资产端可能没存）
        if (StringUtil.isBlank(param.getInvestProporty())) {
            if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(), param.getAssetType())
                    && StringUtil.isNotBlank(param.getAssetSecType()) && StringUtil.equals(DictCodeList.WIND一级分类_含债券一级分类等_优先股.getValue(), param.getAssetSecType())) {
                param.setInvestProporty(DictCodeList.投资性质_权益类.getValue());
            }
            else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(), param.getAssetType())
                    && StringUtil.isNotBlank(param.getAssetSecType()) && !StringUtil.equals(DictCodeList.WIND一级分类_含债券一级分类等_优先股.getValue(), param.getAssetSecType())) {
                param.setInvestProporty(DictCodeList.投资性质_固定收益类.getValue());
            }
            else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_拆出.getValue(), param.getAssetType())
                    || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_存款.getValue(), param.getAssetType())
                    || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(), param.getAssetType())
                    || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue(), param.getAssetType())) {
                param.setInvestProporty(DictCodeList.投资性质_固定收益类.getValue());
            }
          //如果是股票的话，存权益性
            else  if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue(),param.getAssetType())){
                param.setInvestProporty(DictCodeList.投资性质_权益类.getValue());
            }
            if (StringUtil.isBlank(param.getInvestProporty())) {
                param.setInvestProporty("999999");//置为999999，排除为空时也显示了固收或收益类的指标
            }
        }
        //---------add by jjpei 20190727---投资性质---end--------

        //---add by 裴家俊 20190506-----三级类型与投资性质---end----

        //传基金和份额时进这个节点
        if (StringUtil.equals(param.getAmtType(), "1") || StringUtil.equals(param.getAssetType(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
            date = DateUtil.getSysDate();
            map.put("valDate", date);
            map.put("finprodId", param.getAssetCode());
            //--modify by 裴家俊 20190506------start------
            //获取估值信息和金融产品信息------
            /* FinProductType finType = new FinProductType();
            finType.setFinprodId(param.getAssetCode());
            finType.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));*/
            //fin = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductTypeMapper.class, finType, FinProductType.class);
            value = basRiskMathCommService.getValue(map);
            //---modify by 裴家俊 20190506-----end-------
            //资产类型为基金时进这个节点
            if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue(), param.getAssetType())) {
                if (fin != null) {
                    //设置资产二级类型----
                    param.setAssetSecType(fin.getType6());//modify by 裴家俊 2019/03/20----
                    //非货币和金额类型为非货币基金时计算交易金额
                    if (StringUtil.equals(fin.getType6(), DictCodeList.基金类资产类型_非货币基金.getValue()) && value != null && StringUtil.equals(param.getAmtType(), "1")) {
                        BigDecimal amt = ArithmeticUtil.multiply(value.getValuation(), param.getTradeAmount());
                        if (amt != null) {
                            param.setTradeAmount(amt);
                        }
                    }
                }
                //资产类型为计划类时进这个节点(此时传入的应该是交易份额)
            } else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue(), param.getAssetType()) && value != null) {
                BigDecimal amt = ArithmeticUtil.multiply(value.getValuation(), param.getTradeAmount());
                if (amt != null) {
                    param.setTradeAmount(amt);
                }
            }
        }
        try {
            //---------add by 张庚林 20190813---质押式回购---start--------
            if (param.getMun() > 0) {
                if (resultList2 != null) {
                    for (int i = 0; i < resultList2.size(); i++) {
                        if (!param.getIndex().equals(resultList2.get(i).getIndex())) {
                            resultList2.remove(i);
                            i--;
                        }
                    }
                } else {
                    resultList2 = new ArrayList<RiskTrialRetDto>();
                }
                resultList = riskTrialComm.actualRiskTrialDeal(param);
                for (int i = 0; i < resultList.size(); i++) {
                    resultList.get(i).setIndex(param.getIndex());
                }
                resultList2.addAll(resultList);
                resultList=resultList2;
            } else {
                //---------add by 张庚林 20190813---质押式回购多组合---end--------
                resultList = riskTrialComm.actualRiskTrialDeal(param);
            }
        } catch (InterruptedException e) {
        	 Thread.currentThread().interrupt();
        }
        return resultList;
    }

    /**
     * 风险试算
     *
     * @param map 画面参数
     * @return 风险试算时获取授信管理中授信使用条件使用
     * @throws InterruptedException
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("getCreditConditionList")
    @ResponseBody
    public AjaxResult getCreditConditionList(@RequestBody Map<String, Object> map) {
        AjaxResult result = new AjaxResult();
        if (StringUtil.equals(BussinessDictCodeList.监控授信开关_关.getValue(), CacheUtil.getSysParam(BussinessConstant.ISCREDIT))){
            return result;
        }
        if (StringUtil.equals(BussinessDictCodeList.授信使用条件开关_关.getValue(), CacheUtil.getSysParam(BussinessConstant.CREDIT_CONDITION_SHOW))){
            return result;
        }
        StringBuilder sbMsg = new StringBuilder();//返回的信息内容
        Map<String, Object> inputData = (Map<String, Object>) map.get("data");
        // 交易入参转换
        CreditLinesInputDto inputDto = JSONObject.parseObject(JSONObject.toJSONString(map), CreditLinesInputDto.class);
        // 交易对手
        if (inputData.get("counterId") != null){
            inputDto.setCounterId(inputData.get("counterId").toString());
        }
        // 交易状态
        inputDto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 交易金额
        if (inputData.get("tradeAmount") != null){
            inputDto.setTradeAmt(new BigDecimal(inputData.get("tradeAmount").toString()));
        }
        // 交易日期
        if (inputDto.getTradeDate() == null){
            inputDto.setTradeDate(iPlatformDate.getSysDate());
        }
        // 交易类金融产品类型
        String assetType = String.valueOf(inputData.get("assetType"));
        if (DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(assetType)){
            inputDto.setTgBusiType(DictCodeList.平安资产类型_质押式回购.getValue());
            inputDto.setPsPayment(DictCodeList.回购类型_质押式协议回购.getValue());
            inputDto.setPs(DictCodeList.交易方向_买入.getValue());
            inputDto.setSaleCode(String.valueOf(inputData.get("tradeDirection")));
            inputDto.setFinprodId("");
        }else if (DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue().equals(assetType)){
            Map<String,Object> counterMap = (Map<String, Object>) map.get("trdOutRightRepoPersonInfo");
            if (counterMap != null){
                inputDto.setCounterId(String.valueOf(counterMap.get("counterId")));
            }
            inputDto.setTgBusiType(DictCodeList.平安资产类型_买断式回购.getValue());
            inputDto.setPsPayment(String.valueOf(inputData.get("tradeDirection")));
            inputDto.setFinprodId("");
        }else if (DictCodeListExt1.金融产品类型_投管用_债券借贷.getValue().equals(assetType)){
            inputDto.setTgBusiType(DictCodeListExt2.平安资产类型_债券借贷.getValue());
            inputDto.setPsPayment(String.valueOf(inputData.get("tradeDirection")));
            inputDto.setFinprodId("");
        }else if (DictCodeListExt1.金融产品类型_投管用_拆出.getValue().equals(assetType)){
            inputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
            inputDto.setTgBusiType(DictCodeList.平安资产类型_拆借.getValue());
            inputDto.setPsPayment(String.valueOf(inputData.get("tradeDirection")));
            inputDto.setFinprodId("");
        }

        if (StringUtil.isEmpty(inputDto.getFinprodId()) && StringUtil.isEmpty(inputDto.getCounterId())){
            return result;
        }
        CreditLinesUpdate creditLinesUpdate = SpringContextUtil.getBean(CreditLinesUpdateImpl.class);
        if (creditLinesUpdate != null){
            List<CreditLines> creditLinesList = creditLinesUpdate.findCreditLinesCondition(inputDto);
            if (StringUtil.isEmptyList(creditLinesList)){
                return result;
            }
            List<CreditLines> newCreditLinesList = creditLinesList.stream().filter(item -> {
                if (item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_机构授信.getValue())
                || item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_系列授信.getValue())
                || item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_资产授信.getValue())) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            newCreditLinesList.forEach(item ->{
                int index = newCreditLinesList.indexOf(item);
                if (item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_机构授信.getValue()) && StringUtil.isNotEmpty(item.getCreditCondition())) {
                    sbMsg.append(index+1).append("、机构授信：").append(item.getCreditCondition()).append(" ");
                } else if (item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_系列授信.getValue()) && StringUtil.isNotEmpty(item.getCreditCondition())) {
                    sbMsg.append(index+1).append("、系列授信：").append(item.getCreditCondition()).append(" ");
                } else if (item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_资产授信.getValue()) && StringUtil.isNotEmpty(item.getCreditCondition())) {
                    sbMsg.append(index+1).append("、资产授信：").append(item.getCreditCondition()).append(" ");
                }
            });
            if (StringUtil.isNotEmpty(sbMsg.toString())) {
                result.setData(sbMsg.toString());
                result.setData("授信使用条件(" + sbMsg.toString() + ")");
            }
        }
        return result;
    }

    /**
     * check检查
     *
     * @param obj       检查得到值
     * @param fieldName 返回消息名
     * @param messages  检查结果信息
     * @see
     * @since 1.0
     */
    private void notEmptyCheck(Object obj, String fieldName, String massage, List<ValidMessage> messages) {
        if (!StringUtil.isNotEmpty(obj)) {
            fieldName = getMessage(fieldName);
            messages.add(new ValidMessage(fieldName, getMessage("FW_E_0006", getMessage(massage))));
        }
    }

    /**
     * 风险结果入库
     *
     * @param dto 风险试算结果
     * @see
     * @since 1.0
     */
    @RequestMapping("insert")
    @ResponseBody
    private void insert(@RequestBody List<RiskTrialRetDto> dto) {
//        basRiskMathCommService.insert(dto,"111111111");
    }

    /**
     * 实体类进行检查
     *
     * @param map 传入的实体类
     * @return 风险结果实体类
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@RequestBody Map<String, Object> map) {
        AjaxResult result = new AjaxResult();
        List<ValidMessage> messages = new ArrayList<>();
        if(!StringUtil.isNotEmpty(map.get("data"))){
            return result;
        }
        dealCheckRiskComm(map,entry->{
            // del by zhangrui1 start
            // if(StringUtil.isNotEmpty(entry)) {
            // map.put("data",(Map) entry);
            // }
            // List<ValidMessage> retMes=dealEachCheck(map);
            Map<String, Object> checkData = map;
            if (StringUtil.isNotEmpty(entry)) {
                checkData = (Map<String, Object>)entry;
            }
            List<ValidMessage> retMes = dealEachCheck(checkData);
            // del by zhangrui1 end
            if(retMes.size()>0){
                result.setErrorList(retMes);
            }
        });
        return result;
    }

    private  List<ValidMessage> dealEachCheck(Map<String, Object> map){
        AjaxResult result = new AjaxResult();
        List<ValidMessage> messages = new ArrayList<>();
        Map<String, String> data = (HashMap<String, String>) map.get("data");
        String[] validParams = {"valDate", "portfolioCode", "tradeAmount", "assetType", "tradeDirection"};
        if (data != null) {
            for (String param : validParams) {
                if (map.get(param) != null) {
                    List<String> fields = (List<String>) map.get(param);
                    notEmptyCheck(data.get(param), fields.get(0).toString(), fields.get(1).toString(), messages);
                }
            }
            //----add by jjpei 2019-07-31-----资产代码也需要验证---
            List<String> 拆借_回购_外汇_利率互换 = Arrays.asList(DictCodeListExt1.金融产品类型_投管用_拆入.getValue(),
                    DictCodeListExt1.金融产品类型_投管用_拆出.getValue(), DictCodeListExt1.金融产品类型_投管用_利率互换.getValue(),
                    DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),
                    DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue(), DictCodeListExt1.金融产品类型_投管用_债券借贷.getValue(),
                    DictCodeListExt1.金融产品类型_投管用_即期.getValue(), DictCodeListExt1.金融产品类型_投管用_远期.getValue(), DictCodeListExt1.金融产品类型_投管用_掉期.getValue(),
                    DictCodeListExt3.金融产品类型_投管用_期权.getValue());
            if (data.get("assetType") != null && !拆借_回购_外汇_利率互换.contains(data.get("assetType"))) {
                if (map.get("assetCode") != null) {
                    List<String> fields = (List<String>) map.get("assetCode");
                    notEmptyCheck(data.get("assetCode"), fields.get(0).toString(), fields.get(1).toString(), messages);
                } else {
                    // 协议回购，不对质押券做校验 modify 20200930 14:53
                    if (StringUtil.isNotEmpty(data.get("flag"))){
                        return messages;
                        /*
                         //&&StringUtil.isNotEmpty(data.get("repPs")) && StringUtil.isNotEmpty(data.get("tradeMarket")
                        if (StringUtil.equals(data.get("repPs").toString(),DictCodeListExt3.交易方向_逆回购.getValue()) && StringUtil.equals(data.get("tradeMarket").toString(),DictCodeListExt1.交易场所_深圳证券交易所.getValue())){

                        }*/
                    }
                    List<String> fields = new ArrayList<String>();
                    fields.add("finprodId");
                    fields.add("TrdBondInvestTrade002.finprodId");
                    notEmptyCheck(data.get("assetCode"), fields.get(0).toString(), fields.get(1).toString(), messages);
                }
            }
            //----add by jjpei 2019-07-31-----资产代码也需要验证---
        }
        /*if (messages.size() > 0) {
            result.setErrorList(messages);
        }*/
        return messages;
    }

    private void dealCheckRiskComm(Map<String, Object> map,Consumer<Object> var){
        if(map.get("data") instanceof List){
            ((List) map.get("data")).forEach(var);
        }else{
            var.accept( null);
        }
    }
    /**************************************************************************************************************
     *                                吉林银行风险试算-流程控制显示   add by caojin   2019/09/23
     **************************************************************************************************************/
    // add by caojin 吉林银行风险试算-流程控制显示 2019/09/23 start
    @ResponseBody
    @RequestMapping("selectRole")
    public String selectRole() {
        String bankCode = CacheUtil.getSysParam("CUSTOMER_BANK_CODE");
        if(bankCode.equals("004")){
            String role = CacheUtil.getSysParam("JL_PERMISSIONS_ROLE");
            String statusValid = RequestUtil.getParam("statusValid");
            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole(role) &&
                    (!WorkFlowUtil.isNotFromToDoList() || DictCodeListExt1.交易流程状态_流程中.getValue().equals(statusValid))
                    && StringUtil.isNotEmpty(workflowId_JL)){
                return "1";
            }
        }
        return "2";
    }
    // add by caojin 吉林银行风险试算-流程控制显示 end
    // add by jiangpeng ZGBUGTWO-257 未配置风险指标的行社不应提示请进行风险试算 20201214 start
    /**
     * 按组合代码检查是否配置风险指标
     * @param portfolioId 组合代码
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("isSetRiskByPortfolioId")
    public AjaxResult isSetRiskByPortfolioId(@RequestBody String portfolioId) {
        portfolioId = portfolioId.replaceAll("\"","");
        AjaxResult result = new AjaxResult();
        Map<String, Object> data = new HashMap<>();
        if (StringUtil.isEmpty(portfolioId)) {
            logger.debug("组合代码为空,可能为 BaseRiskMathComm.js中 portfolioId 取值错误,默认取id为'portfolioId'的控件的值,该业务html页面 组合代码 控件id可能不同");
            data.put("flag", "Y");
        } else {
            data.put("flag", basRiskMathCommService.isSetRiskByPortfolioId(portfolioId));
        }
        result.setData(data);
        return result;
    }
    // add by jiangpeng ZGBUGTWO-257 未配置风险指标的行社不应提示请进行风险试算 20201214 end
}
