package com.joyintech.tams.cfets.etrib.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondinfo002Dto;
import com.joyintech.tams.cfets.etrib.service.CfetsEtrIbBondinfo002Service;
import com.joyintech.tams.cfets.etrib.service.CfetsEtrIbBondinfoService;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.bond.BusiFinExercisePlanService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

/**
 * 债券投资委托新建 〈功能详细描述〉
 */
@Controller
@PageId("CfetsEtrIbBondinfo002")
@RequestMapping("/cfets/etrib/cfetsEtrIbBondinfo002")
@Api(tags = "银行间债券指令新建")
public class CfetsEtrIbBondinfo002Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 债券投资委托service
     */
    @Autowired
    private CfetsEtrIbBondinfo002Service cfetsEtrIbBondinfo002Service;

    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    
    /**
     * 行权计划共通service
     */
    @Autowired
    private BusiFinExercisePlanService busiFinExercisePlanService;
    @Autowired
    private CfetsEtrIbBondinfoService cfetsEtrIbBondinfoService;
    
    public static final String ENTRUST_RECORD_NEW = "new";

	public static final String ENTRUST_RECORD_MODIFY = "modify";


    /**
     * 初始化002新建页面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.清算速度);
        this.setSelectOptionToModel(model, DictCode.价格模式);
        this.setSelectOptionToModel(model, DictCode.投资目的);
        this.setSelectOptionToModel(model, DictCode.业务模式);
        model.addAttribute("PRICEMODEL_03", DictCodeListExt2.价格模式_市价指令.getValue());
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        model.addAttribute("T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("T02", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("T03", DictCodeList.交易方向_行权_回售.getValue());
        model.addAttribute("T39", DictCodeList.交易方向_行权_赎回.getValue());
        model.addAttribute("type01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("type03", DictCodeList.收益类型_货币型.getValue());
        model.addAttribute("type02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("N", DictCodeList.是否_否.getValue());
        model.addAttribute("Y", DictCodeList.是否_是.getValue());
        model.addAttribute("CCY", DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE") == null ? "01" : CacheUtil.getSysParam("IS_SHOW_TYPE"));
        
        model.addAttribute("SHARES_TRADE_MARKET_01", DictCodeListExt1.交易市场_一级市场.getValue());
        model.addAttribute("SHARES_TRADE_MARKET_02", DictCodeListExt1.交易市场_二级市场.getValue());
        
        model.addAttribute("ENTRUST_RECORD_NEW", ENTRUST_RECORD_NEW);
        model.addAttribute("ENTRUST_RECORD_MODIFY", ENTRUST_RECORD_MODIFY);

        boolean todoList = false;
        
        String entrustId = getParam("entrustId");
        String finprodId = getParam("finprodId");
        String portfolioId = getParam("portfolioId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            CfetsEtrIbBondinfo002Dto dto = cfetsEtrIbBondinfo002Service.getBussinessEntityByWorkflowId(
                    workflowId);
//            if (StringUtil.isBlank(dto.getParentPortfolioId())) {
//                dto.setParentPortfolioId(etrCommonService.selectParentPortfolioIdByPortfolioId(
//                        dto.getPortfolioId()
//                ));
//            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
            todoList = true;
            model.addAttribute("workflowId", workflowId);         
            model.addAttribute("businessId", dto.getEntrustId());
            model.addAttribute("calculateField",dto.getCalculateField());
        }
        //新建初始化
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
            CfetsEtrIbBondinfo002Dto entity = new CfetsEtrIbBondinfo002Dto();
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            entity.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            entity.setTradeMarket(DictCodeListExt1.交易场所_银行间.getValue());
            entity.setEntrustStatus(DictCodeList.委托状态_新建.getValue());
            entity.setInvManagerId(LoginUserUtil.getUserName());
            model.addAttribute(GlobalConstant.ENTITY, entity);
            model.addAttribute("businessId", IDGenerator.generate());
        }
        //展示投资目的或业务模式参数
        String SHOW_INVAIM_OR_BUSSINSSMODEL = CacheUtil.getSysParam("SHOW_INVAIM_OR_BUSSINSSMODEL");
        model.addAttribute("SHOW_INVAIM_OR_BUSSINSSMODEL", SHOW_INVAIM_OR_BUSSINSSMODEL);
        //展示投资目的或业务模式参数
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("shortSellingSupported", CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));
        model.addAttribute("TodoList", todoList);
        return super.init(model);
    }

    /**
     * 获取新建界面的资产代码联想下拉框框
     *
     * @return
     * @see
     * @since 1.0
     */
/*    @ResponseBody
    @RequestMapping("/getfinprodIdForAdd")
    public Page<SelectOptionDto> getfinprodIdForAdd()
            throws Exception {
        Page<SelectOptionDto> temp = etrIbBondinfo002Service.getfinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
    }*/

//    /**
//     * 得到母组合代码
//     *
//     * @return
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("/parentPortfolioId")
//    public String parentPortfolioId(@RequestBody Map<String, Object> param)
//            throws Exception {
//         String temp = etrCommonService.selectParentPortfolioIdByPortfolioId(param.get("portfolioId").toString());
//        return temp;
//    }

    /**
     * 可用面值计算
     * <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    /*@ResponseBody
    @RequestMapping("/getAvailableNum")
    public BigDecimal getAvailableNum(String finprodId) {
        return etrIbBondinfo002Service.getAvailableNum(finprodId);
    }
*/
    /**
     * 净价、行权收益率、到期收益率
     * <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
   /* @RequestMapping("/calData")
    @ResponseBody
    public AjaxResult calData(@RequestBody EtrIbBondinfo002Dto dto) {
        AjaxResult result = new AjaxResult();
        etrIbBondinfo002Service.calcAmt(dto);
        result.setData(dto);
        return result;
    }*/

    /**
     * 查询行权价
     *
     * @author chaowei
     */
   /* @ResponseBody
    @RequestMapping("getExercisePricebyPara")
    public BigDecimal getExercisePricebyPara(@RequestBody Map<String, Object> param) {
        return etrIbBondinfo002Service.getExercisePricebyPara(param);
    }*/




    /**
     * 计算净价偏离度
     * @param paramMap
     * @return
     */
/*    @RequestMapping("/calDeviationDegree")
    @ResponseBody
    public BigDecimal calDeviationDegree(@RequestBody Map<String, Object> paramMap) {
        BigDecimal result =
        etrIbBondinfo002Service.calDeviationDegree(paramMap);
        return result;*/

    @PostMapping("qryPortfolioSecAcctId")
    public String qryPortfolioSecAcctId(@RequestBody Map<String, Object> param) {
        return cfetsEtrIbBondinfo002Service.qryPortfolioSecAcctId(param);
    }

}
