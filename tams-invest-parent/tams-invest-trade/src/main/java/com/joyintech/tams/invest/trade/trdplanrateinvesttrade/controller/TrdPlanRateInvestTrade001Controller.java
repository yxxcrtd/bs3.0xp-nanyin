/*
 * 文件名：TrdPlanRateInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.trdplanrateinvesttrade.controller;
import java.util.*;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.trdplanrateinvesttrade.dto.TrdPlanRateInvestTradeDto;
import com.joyintech.tams.invest.trade.trdplanrateinvesttrade.service.TrdPlanRateInvestTrade001Service;
import com.joyintech.tams.invest.trade.trdplanrateinvesttrade.service.TrdPlanRateInvestTrade002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 计划类资产投资交易查询
 * @author fanshuanghu
 * @version 1.0 2018年11月22日
 * @see TrdPlanRateInvestTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdPlanRateInvestTrade001")
@RequestMapping("/invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade001")
public class TrdPlanRateInvestTrade001Controller extends BaseController{
    /**
     * 注入计划类（利率型）service
     */
    @Autowired
    private TrdPlanRateInvestTrade001Service trdPlanRateInvestTrade001Service;
    /**
     * 注入计划类（利率型）service
     */
    @Autowired
    private TrdPlanRateInvestTrade002Service trdPlanRateInvestTrade002Service;
    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 交易基础组件调用Service
     */
    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    /**
     * 初始化方法
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向,DictCode.交易流程状态,DictCode.计划类资产一级分类
              /*  原因、zhuyongkang 2019/7/30  start */
                ,DictCode.行内资产二级分类
                ,DictCode.行内资产三级分类
                ,DictCode.行内资产四级分类);
              /*    原因、zhuyongkang 2019/7/30  end*/
        model.addAttribute("TRADE_STATUS", DictCodeListExt1.交易流程状态_有效.getValue());
        
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdPlanRateInvestTradeDto> findPage(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return trdPlanRateInvestTrade001Service.findPage(map);
    }
    
    /**
     * 画面导出功能实现
     * 
     * @return ResponseEntity 确认的实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
        // 修改导出错误 zhuyongkang
//    	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return trdPlanRateInvestTrade001Service.export(this.getPageId(),
        		param);
    }
    /**
     * 删除按钮点击时的check
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdPlanRateInvestTradeDto dto, BindingResult result) {
        // 确认提醒 TISHKBS-1113【组合资金账户头寸查询】内转交易撤销提醒 add by hpp 2020-09-17 start
        if (result.getErrorCount() == 0) {
            AjaxResult ajaxResult = new AjaxResult();
            String confirmMsg = "";
            String changeLineMark = "<br/>";

            //根据交易编号查询另一笔未撤销的转仓交易信息
            String tradeId = dto.getSelectedItem().get(0).getTotleTradeId();
            TrdProductDeal res = trdCommonBaseService.selectZCDeal(tradeId);
            if(StringUtil.isNotEmpty(res)){
                confirmMsg = "此交易为转仓交易，撤销后需要将业务编号为["+
                        res.getTradeId()+"]的反向转仓交易也撤销掉，请确认是否继续？";
            }else{
                confirmMsg = "请确认是否撤销？";
            }
            if (StringUtil.isNotBlank(confirmMsg)) {
                ajaxResult.setData(confirmMsg);
            }
            if (ajaxResult != null) {
                return ajaxResult;
            }
        }
        // 确认提醒 TISHKBS-1113【组合资金账户头寸查询】内转交易撤销提醒 add by hpp 2020-09-17 end
        if (StringUtil.equals(dto.getOperateType(), "revoke")) {
            if (trdCommonBaseService.isAlreadyRevoked(dto.getSelectedItem().get(0).getTradeId())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                        getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(validMessage);
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 删除操作
     * @param dto 验证的实体类
     * @param br 错误结果
     * @return 错误信息
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdPlanRateInvestTradeDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
       dto =  dto.getSelectedItem().get(0);
        //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm start 20191015
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("TrdPlanRateInvestTrade002");
        parameter.setBusinessDataPk1(dto.getTotleTradeId());
        if (!mstWorkflowCommonService.isProcessData(parameter)){
            dto = trdPlanRateInvestTrade002Service.getInitEntity(dto.getTotleTradeId());
            //资产名称
            Map<String, Object> map = new HashMap<>();
            map.put("initValue", dto.getFinprodMarketId());
            List<SelectOptionDto> list = trdPlanRateInvestTrade002Service.getFinprodMarketId(map);
            if (StringUtil.isNotEmpty(list)){
                dto.setFinprodName(list.get(0).getText());
            }
            //资产类型
            map.put("finprodId", dto.getFinprodId());
            Map<String, Object> finType = trdPlanRateInvestTrade002Service.getFinprodType(map);             
            dto.setType9((String) finType.get("type9"));
            dto.setType10((String) finType.get("type10"));
            dto.setType11((String) finType.get("type11"));
            
            //划款指令
            if (dto != null && StringUtil.isEmptyList(dto.getBussTrdTransferOrder())){
                List<BussTrdTransferOrderBase> orderDtolist = bussSettleOrderCommService.selectOrdersByBizCode(dto.getTotleTradeId()) ;
                if (!StringUtil.isEmptyList(orderDtolist)){
                    List<BussTrdTransferOrderDto> bussTrdTransferOrderDtoList = new ArrayList<BussTrdTransferOrderDto>();
                    BussTrdTransferOrderDto bussTrdTransferOrderDto = new BussTrdTransferOrderDto();
                    BeanUtil.copyPropertiesIgnoreNull(bussTrdTransferOrderDto, orderDtolist.get(0));
                    bussTrdTransferOrderDtoList.add(bussTrdTransferOrderDto);
                    dto.setBussTrdTransferOrder(bussTrdTransferOrderDtoList);
                }
            }
            dto.setOperateType("delete");
        }
        //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm end 20191015    
        ProcessParameter<TrdPlanRateInvestTradeDto> processParameter = new ProcessParameter<TrdPlanRateInvestTradeDto>();
        WorkFlowParameter<TrdPlanRateInvestTradeDto> params = new WorkFlowParameter<TrdPlanRateInvestTradeDto>();
        params.setPageId("TrdPlanRateInvestTrade002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getTotleTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_利率型_交易.getValue());
        // 业务说明：“交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日 
        params.setComment(
            entity -> this.getMessage("TrdPlanRateInvestTrade001.tradeId") + "("
                      + entity.getTotleTradeId() + ")" + "-"
                      + this.getMessage("TrdPlanRateInvestTrade001.finprodName") + "("
                      + entity.getFinprodName() + ")" 
                      + this.getMessage("TrdPlanRateInvestTrade001.tradeDate") + "("
                          + DateUtil.format(entity.getTradeDate()) + ")" );
        processParameter.setWorkFlowParameter(params);
        String msg = trdPlanRateInvestTrade002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 获取委托代码
     * @return 联想文本框实体类集合
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getEntrustIdByType")
    public List<SuggestDto> getEntrustIdByType() {
        String entrustId = getParam("param");
        Map<String, Object> map = new HashMap<>();
        map.put("param", entrustId);
        map.put("finProdType", Arrays.asList(DictCodeListExt1.金融产品类型_计划类资产.getValue()));
        map.put("ENTRUST_STATUS_LIST", Arrays.asList(DictCodeList.委托状态_已接收.getValue(),
                DictCodeList.委托状态_已完成.getValue(), DictCodeList.委托状态_已关闭.getValue()));
        return trdPlanRateInvestTrade001Service.getEntrustIdByType(map);
    }
}
