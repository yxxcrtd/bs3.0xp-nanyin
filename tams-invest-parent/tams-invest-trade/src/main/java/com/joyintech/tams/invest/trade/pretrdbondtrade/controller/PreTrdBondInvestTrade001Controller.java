/*
 * 文件名：PreTrdBondInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wang wei
 * 修改时间：2019年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.pretrdbondtrade.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade001Dto;
import com.joyintech.tams.invest.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade002Dto;
import com.joyintech.tams.invest.trade.pretrdbondtrade.service.PreTrdBondInvestTrade001Service;
import com.joyintech.tams.invest.trade.pretrdbondtrade.service.PreTrdBondInvestTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券交易预录入
 * @author wang wei
 * @version 1.0 2019年12月12日
 * @see PreTrdBondInvestTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("PreTrdBondInvestTrade001")
@RequestMapping("/invest/trade/pretrdbondtrade/PreTrdBondInvestTrade001")
public class PreTrdBondInvestTrade001Controller extends BaseController {

    @Autowired
    private PreTrdBondInvestTrade001Service bondTrade001Service;
    
    @Autowired
    private PreTrdBondInvestTrade001Service preTrdBondInvestTrade001Service;
    
    @Autowired
    PreTrdBondInvestTrade002Service preTrdBondInvestTrade002Service;
    
    @Autowired
    private TrdBondInvestTrade002Service trdBondInvestTrade002Service;

    /**
     * 划款指令组件
     */
    @Autowired
    private BussSettleOrderCommService bussSettleOrderCommService;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        // 初始化界面下拉框参数
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.清算速度, BussinessDictCode.登记托管机构,
                DictCode.交易流程状态);
        model.addAttribute("TRADE_STATUS", DictCodeListExt1.交易流程状态_有效.getValue());
        return super.init(model);
    }
    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<PreTrdBondInvestTrade001Dto> findPage()
        throws Exception {
        return preTrdBondInvestTrade001Service.findPage(RequestUtil.getPageParam());
    }

//    @RequestMapping("getEntrustId")
//    @ResponseBody
//    public List<SuggestDto> getEntrustId()
//            throws Exception {
//        return trdBondInvestTrade001Service.getEntrustId(RequestUtil.getParameters(this.getRequest()));
//    }

//    @RequestMapping("getPortfolioId")
//    @ResponseBody
//    public List<SuggestDto> getPortfolioId()
//            throws Exception {
//        return trdBondInvestTrade001Service.getPortfolioId(RequestUtil.getParameters(this.getRequest()));
//    }

//    @RequestMapping("getFinprodMarketId")
//    @ResponseBody
//    public List<SuggestDto> getFinprodMarketId()
//            throws Exception {
//        return trdBondInvestTrade001Service.getFinprodMarketId(RequestUtil.getParameters(this.getRequest()));
//    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @param
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        return preTrdBondInvestTrade001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }

    /**
     * 撤销前check
     *
     * @param inputDto 画面输入参数dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @RequestMapping("checkRevoke")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody TrdBondInvestTradeRevokeDto inputDto,
                            BindingResult result) {
        return commonCheck(result, inputDto.getOperateType());
    }

    /**
     * 撤销
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
  	@RequestMapping("doRevoke")
      @ResponseBody
      AjaxResult doRevoke(@RequestBody PreTrdBondInvestTrade002Dto dto)   throws Exception {

          String msg = "";
/*          Object[] fenpeiDto = dto.getBussTrdTransfenpeiDto();
          List<TrdTransfenpeiDto> list = new ArrayList<TrdTransfenpeiDto>();
          if(fenpeiDto!=null) {
          	for(int i=0;i<fenpeiDto.length;i++) {
               TrdTransfenpeiDto model = (TrdTransfenpeiDto) JsonUtils.map2entity((Map<String,Object>)fenpeiDto[0], TrdTransfenpeiDto.class);
               list.add(model);
          	}
          dto.setTrdTransfenpeiDto(list);
          }
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
                  dto.setTradeId(IDGenerator.generate());
              }
          }
          // 金融产品类型（投管分类）= 数据字典缓存.金融产品类型（投管用）数据字典（根据交易方向与回购类型来综合判断）
          
          dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());*/
          
          ProcessParameter<PreTrdBondInvestTrade002Dto> processParameter = new ProcessParameter<>();
          WorkFlowParameter<PreTrdBondInvestTrade002Dto> workFlowParameter = new WorkFlowParameter<>();
          processParameter.setWorkFlowParameter(workFlowParameter);
          processParameter.setPageId("PreTrdBondInvestTrade002");
          processParameter.setOperationType(OperationType.REVOKE);
          workFlowParameter.setPageId("PreTrdBondInvestTrade002");
          workFlowParameter.setElementId(this.getParam("elementId"));
          workFlowParameter.setBusinessType(DictCodeListExt1.共通业务类型_债券交易预录入.getValue());
          workFlowParameter.setBusinessCode((entity) -> dto.getTotleTradeId());
          workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                  getMessage("TrdBondInvestTrade002.tradeId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
                  getMessage("TrdBondInvestTrade002.finprodName") + GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodName() + GlobalConstant.CLOSE_PARENTHESIS,
                  getMessage("TrdBondInvestTrade002.tradeDate") + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS)
                  );
          msg = preTrdBondInvestTrade002Service.start(processParameter, dto);
          return new AjaxResult(msg);
      }
/*    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody TrdBondInvestTradeDto inputDto)
        throws Exception {
        //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm start 20191015
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("TrdBondInvestTrade002");
        parameter.setBusinessDataPk1(inputDto.getTradeId());
        if (!mstWorkflowCommonService.isProcessData(parameter)){
            inputDto = trdBondInvestTrade002Service.getTrdBondInvestTradeDto(inputDto.getTradeId());
            //划款指令
            if (inputDto != null && StringUtil.isEmptyList(inputDto.getBussTrdTransferOrder())){
                List<BussTrdTransferOrderBase> orderDtolist = bussSettleOrderCommService.selectOrdersByBizCode(inputDto.getTradeId()) ;
                if (!StringUtil.isEmptyList(orderDtolist)){
                   List<BussTrdTransferOrderDto> bussTrdTransferOrderDtoList = new ArrayList<BussTrdTransferOrderDto>();
                    BussTrdTransferOrderDto bussTrdTransferOrderDto = new BussTrdTransferOrderDto();
                    BeanUtil.copyPropertiesIgnoreNull(bussTrdTransferOrderDto, orderDtolist.get(0));
                    bussTrdTransferOrderDtoList.add(bussTrdTransferOrderDto);
                    inputDto.setBussTrdTransferOrder(bussTrdTransferOrderDtoList);
                }                
            }            
        }
        //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm end 20191015
        ProcessParameter<TrdBondInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdBondInvestTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdBondInvestTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_债券类资产交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("TrdBondInvestTrade002.tradeId") + GlobalConstant.COLON + entity.getTradeId(),
            getMessage("TrdBondInvestTrade002.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
            getMessage("TrdBondInvestTrade002.tradeDate") + GlobalConstant.COLON + DateUtil.format(entity.getTradeDate())));

        String msg = trdBondInvestTrade002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }*/
}
