
package com.joyintech.tams.invest.portfolio.ptlmock.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.calculation.CalculationException;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTest002Dto;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTestTradeDto;
import com.joyintech.tams.invest.portfolio.ptlmock.service.PtlMockTest002Service;
import com.joyintech.tams.invest.portfolio.ptlmock.service.PtlMockTestImportService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模拟试算新建
 *
 * @author yjm
 * @version 1.0 2020/04/13
 * @see PtlMockTest002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlMockTest002")
@RequestMapping("/invest/portfolio/ptlmock/PtlMockTest002")
public class PtlMockTest002Controller extends BaseController {

    @Autowired(required = false)
    private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;

    @Autowired
    private PtlMockTest002Service ptlMockTest002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private PtlMockTestImportService importService;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益类型, DictCode.组合类型, DictCode.币种,
            DictCode.平安资产类型, DictCode.交易方向, DictCode.投资目的);
        // 平台日期
        model.addAttribute("platDate", iPlatformDate.getSysDateStr());
        model.addAttribute("profitType01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("profitType03", DictCodeList.收益类型_货币型.getValue());
        model.addAttribute("portfolioType04", DictCodeListExt3.组合类型_虚拟组合.getValue());
        model.addAttribute("ps_T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("CCY_CNY", DictCodeList.币种_人民币_CNY.getValue());
        
        // 判断是否存在于留痕表
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        PtlMockTest002Dto entity = null;
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            entity = ptlMockTest002Service.getBussinessEntityByWorkflowId(
                workflowId);
        }
        else {
            Map<String, Object> map = RequestUtil.getParameters(getRequest());
            if (StringUtil.isNotEmpty(map.get("mockId"))) {
                entity = ptlMockTest002Service.getInitInfo(map);
            }
        }
        if (entity != null) {
            model.addAttribute("mockProductDealList", entity.getPageMockDealList());
            model.addAttribute("mockTestRskList", entity.getMockTestRskList());
            model.addAttribute("preMockTestTaaList", entity.getPreMockTestTaaList());
            model.addAttribute("afterMockTestTaaList", entity.getAfterMockTestTaaList());
            model.addAttribute("mockTestNetTrendList", entity.getMockTestNetTrendList());
            if (entity.getMockRangeStartDate() != null && entity.getMockRangeEndDate() != null) {
                model.addAttribute("mockRangeStr", DateUtil.format(entity.getMockRangeStartDate()) + " - "
                                    + DateUtil.format(entity.getMockRangeEndDate()));
            }
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }

    /**
     * 组合代码下拉框选项获取
     * @param param 画面参数
     * @return 组合代码下拉框选项
     */
    @RequestMapping("getPtlInfo")
    @ResponseBody
    public List<SelectOptionDto> getPtlInfo(@RequestParam Map<String, Object> param) {
        // 查询组合添加权限限制
        param.put("loginUserName", LoginUserUtil.getUserName());
        return ptlMockTest002Service.getPtlInfo(param);
    }
    
    /**
     * 根据组合代码获取组合详细信息
     * @param portfolioId 组合代码
     * @return 组合详细信息
     */
    @RequestMapping("getPtlDetailInfo")
    @ResponseBody
    public MockPtlPortfolioDto getPtlDetailInfo(String portfolioId) {
        return ptlMockTest002Service.getPtlDetailInfo(portfolioId);
    }

    /**
     * 根据资产代码获取资产基本信息
     */
    @RequestMapping("getFinProdId")
    @ResponseBody
    public List<SelectOptionDto> getFinProdId(@RequestParam Map<String, Object> param) {
        // 查询组合添加权限限制
        param.put("loginUserName", LoginUserUtil.getUserName());
        return ptlMockTest002Service.getFinProdId(param);
    }

    /**
     * 根据组合关联的估值方案查询对应资产的投资目的
     */
    @ResponseBody
    @RequestMapping("/findInvAim")
    public String findInvAim() {
        return ptlMockTest002Service.findInvAim(RequestUtil.getPageParam());
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<PtlMockTestTradeDto> importData(String fileName) {
        return importService.importData(fileName);
    }

    /**
     * 校验导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/checkForImportFile")
    public List<ExcelValidDto> checkForImportFile(String fileName) {
        return importService.checkForImportFile(fileName);
    }
    
//    /**
//     * 计算模拟交易列表净价、到期收益率、行权收益率
//     * @param inputTradeDto 模拟交易
//     * @return 计算结果
//     */
//    @ResponseBody
//    @RequestMapping("/calcYield")
//    public PtlMockTestTradeDto calcYield(PtlMockTestTradeDto inputTradeDto) {
//        return ptlMockTest002Service.calcYield(inputTradeDto);
//    }

    /**
     //     * 计算模拟交易列表净价、到期收益率、行权收益率
     //     * @param inputTradeDto 模拟交易
     //     * @return 计算结果
     //     */
    @ResponseBody
    @RequestMapping("/calcByPrice")
    public PtlMockTestTradeDto calcByPrice(PtlMockTestTradeDto inputTradeDto) {
        try {
            return ptlMockTest002Service.calcYield(inputTradeDto, "01");
        }catch(CalculationException e){
            getLogger().error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     //     * 计算模拟交易列表净价、到期收益率、行权收益率
     //     * @param inputTradeDto 模拟交易
     //     * @return 计算结果
     //     */
    @ResponseBody
    @RequestMapping("/calcByYield")
    public PtlMockTestTradeDto calcByYield(PtlMockTestTradeDto inputTradeDto) {
        try{
            return ptlMockTest002Service.calcYield(inputTradeDto,"02");
        }catch(CalculationException e){
            getLogger().error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     //     * 计算模拟交易列表净价、到期收益率、行权收益率
     //     * @param inputTradeDto 模拟交易
     //     * @return 计算结果
     //     */
    @ResponseBody
    @RequestMapping("/calcByExerYield")
    public PtlMockTestTradeDto calcByExerYield(PtlMockTestTradeDto inputTradeDto) {
        try{
            return ptlMockTest002Service.calcYield(inputTradeDto,"03");
        }catch(CalculationException e){
            getLogger().error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 模拟测算按钮点击时的处理
     *
     * @param inputDto PtlMockTest002Dto
     * @param result BindingResult
     * @return 处理结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doMockTest")
    public AjaxResult doMockTest(@Validated({
        ValidateGroupEdit.class}) @RequestBody PtlMockTest002Dto inputDto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }

        AjaxResult ajaxResult = new AjaxResult();
        try {
            ajaxResult.setData(ptlMockTest002Service.doMockTest(inputDto));
        }
        catch (Exception e) {
            getLogger().error("模拟试算发生异常：" + e.getMessage()+"=========="+e);
            e.printStackTrace();
            ajaxResult.setData("exceptionOccured");
        }
        return ajaxResult;
    }

    /**
     * 保存按钮点击时的check
     *
     * @param allDto PtlMockTest002Dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Validated({
        ValidateGroupEdit.class}) @RequestBody PtlMockTest002Dto allDto, BindingResult result) {
        return commonCheck(result, allDto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("checkSave")
    public AjaxResult checkSave(@Validated({
            ValidateGroupEdit.class}) @RequestBody PtlMockTest002Dto allDto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        // 可选资产的买入 (有组合代码的)
        // 模拟交易列表不能为空
        if (StringUtil.isNotEmpty(allDto.getPageMockDealList())
                && allDto.getPageMockDealList().size() != 0) {
            // 校验交易列表数据
            List<PtlMockTestTradeDto> list = allDto.getPageMockDealList();
            Map<String,BigDecimal> 已经统计数量map = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                PtlMockTestTradeDto tradeDto = list.get(i);
                if (StringUtil.isNotEmpty(tradeDto.getPortfolioId())
                        && StringUtil.isNotEmpty(tradeDto.getAmount()) && StringUtil.equals(DictCodeList.交易方向_买入.getValue(), tradeDto.getPs())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("tradeDate", iPlatformDate.getSysDateStr());
                    map.put("finprodId", tradeDto.getFinprodId());
                    map.put("portfolioId", tradeDto.getPortfolioId());
                    if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                            tradeDto.getType8())) {
                        map.put("invAim",tradeDto.getInvAim());
                    }
                    BigDecimal position = bussTrdAvailableCashQueryService.getSecPosition(map);
                    BigDecimal 试算占用数量 = ptlMockTest002Service.getUsedMockPosition(map);
                    position = ArithmeticUtil.subtract(position,试算占用数量);
                    BigDecimal amount = tradeDto.getAmount();
                    if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                            tradeDto.getType8())) {
                        amount = ArithmeticUtil.divide(amount,new BigDecimal(100),2);
                    }
                    String key = tradeDto.getFinprodId();
                    if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                            tradeDto.getType8())) {
                        key = key +"_"+tradeDto.getInvAim();
                    }
                    BigDecimal 已经统计数量 = 已经统计数量map.get(key) ==null ?BigDecimal.ZERO:已经统计数量map.get(key);
                    position = position.subtract(已经统计数量);
                    已经统计数量map.put(key,已经统计数量.add(amount));
                    if (StringUtil.isNotEmpty(amount) && position.compareTo(amount) < 0) {
                        AjaxResult ar = new AjaxResult();
                        ar.setData(this.getMessage("PtlMockTest_E_0034", "模拟交易",
                                String.valueOf(i + 1), tradeDto.getFinprodMarketId()));
                        ar.setMessageType(MessageType.确认消息);
                        return ar;
                    }
                }
            }
        }

        return commonCheck(result, allDto.getOperateType());
    }



    /**
     * 模拟试算保存业务流程 <功能详细描述>
     *
     * @param dto 业务实体
     * @param br AjaxResult
     * @return AjaxResul
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlMockTest002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        dto.setMockId(IDGenerator.generate(32));

        ProcessParameter<PtlMockTest002Dto> processParameter = new ProcessParameter<PtlMockTest002Dto>();
        WorkFlowParameter<PtlMockTest002Dto> params = new WorkFlowParameter<PtlMockTest002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId("PtlMockTest002_btn_save");
        params.setBusinessCode(item -> item.getMockId());
        // params.setBusinessType(DictCodeList.共通业务类型_基金类资产投资委托.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> getMessage("PtlMockTest002.portfolioId") + "("
                                    + entity.getPortfolioId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = ptlMockTest002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 净值走势、万份收益走势、七日年化收益率走势图表导出
     * @param request request
     * @param response response
     * @return 导出结果
     */
    @ResponseBody
    @RequestMapping("chartExport")
    public ResponseEntity<byte[]> chartExport(HttpServletRequest request, HttpServletResponse response) {
       return   ptlMockTest002Service.chartExport(request,request.getParameter("picinfo"), response); 
    }
    
}
