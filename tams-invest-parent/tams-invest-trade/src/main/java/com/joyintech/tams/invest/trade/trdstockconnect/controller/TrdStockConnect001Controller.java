
package com.joyintech.tams.invest.trade.trdstockconnect.controller;

import javax.validation.Valid;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.trdstockconnect.dto.TrdStockConnectDto;
import com.joyintech.tams.invest.trade.trdstockconnect.dto.TrdStockConnectRevokeDto;
import com.joyintech.tams.invest.trade.trdstockconnect.service.TrdStockConnect001Service;
import com.joyintech.tams.invest.trade.trdstockconnect.service.TrdStockConnect002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 港股通交易
 *
 * @author hechuan
 * @version 1.0 2021年4月14日
 * @see TrdStockConnect001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdStockConnect001")
@RequestMapping("/invest/trade/trdstockconnect/TrdStockConnect001")
public class TrdStockConnect001Controller extends BaseController {

    @Autowired
    private TrdStockConnect001Service trdStockConnect001Service;
    
    @Autowired
    private TrdStockConnect002Service trdStockConnect002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向,
            DictCode.交易流程状态, DictCode.是否沪港通或深港通);
        return super.init(model);
    }
  
    /**
     * 分页查询
     *
     * @return Page<TrdStock001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdStockConnectDto> findPage()
        throws Exception {
        return trdStockConnect001Service.findPage(RequestUtil.getPageParam());
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
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdStockConnect001Service.export(this.getPageId(), RequestUtil.getPageParam());
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
    public AjaxResult check(@Valid @RequestBody TrdStockConnectRevokeDto inputDto,
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
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody TrdStockConnectDto inputDto)
        throws Exception {

        ProcessParameter<TrdStockConnectDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdStockConnectDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdStockConnect002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdStockConnect002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_股票投资交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("TrdStock002.tradeId") + GlobalConstant.COLON + entity.getTradeId(),
            getMessage("TrdStock002.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
            getMessage("TrdStock002.tradeDate") + GlobalConstant.COLON + DateUtil.format(entity.getTradeDate())));

        String msg = trdStockConnect002Service.start(processParameter, inputDto);

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
        map.put("finProdType", Arrays.asList(DictCodeListExt1.金融产品类型_股票.getValue()));
        map.put("ENTRUST_STATUS_LIST", Arrays.asList(DictCodeList.委托状态_已接收.getValue(),
                DictCodeList.委托状态_已完成.getValue(), DictCodeList.委托状态_已关闭.getValue()));
        return trdStockConnect001Service.getEntrustIdByType(map);
    }
    
    /**
	 * 资产代码：下拉框
	 *
	 * @param params
	 *            画面参数
	 * @return 资产代码：下拉框
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getFinprodIdNew")
	@ResponseBody
	public List<SuggestDto> getFinprodIdNew(@RequestParam Map<String, Object> params) {
		return trdStockConnect001Service.getFinprodIdNew(params);
	}
}
