/*
 * 文件名：TrdLendDuration001Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：dingzhipeng
 * 修改时间：2018年9月14日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdlendduration.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdlendduration.dto.TrdLendDuration001Dto;
import com.joyintech.tams.invest.period.trdlendduration.service.TrdLendDuration001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆借存续期查询 〈功能详细描述〉
 * 
 * @author dingzhipeng
 * @version 1.0 2018年9月14日
 * @see TrdLendDuration001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdLendDuration001")
@RequestMapping("/invest/period/trdlendduration/TrdLendDuration001")
public class TrdLendDuration001Controller extends BaseController {

    /**
     * service
     */
    @Autowired
    private TrdLendDuration001Service trdLendDuration001Service;

    /**
     * service
     */
    @Autowired
    private PeriodCommonService periodCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.画面数据状态, DictCode.回购拆借方向);
        model.addAttribute("PAGE_DATA_STATUS_01", DictCodeListExt1.画面数据状态_待处理.getValue());
        model.addAttribute("PAGE_DATA_STATUS_05", DictCodeListExt1.画面数据状态_有效.getValue());
        return super.init(model);
    }

    /**
     * 获取组合代码 <功能详细描述>
     * 
     * @return 结果值
     * @see
     * @since 1.0
     */

    @RequestMapping("/getPortfolioId")
    @ResponseBody
    public List<SelectOptionDto> getPortfolioId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return trdLendDuration001Service.getPortfolioId(map);
    }

    /**
     * 交易对手下拉框 <功能详细描述>
     * 
     * @return 结果值
     * @see
     * @since 1.0
     */

    @RequestMapping("/getCounterId")
    @ResponseBody
    public List<SelectOptionDto> getCounterId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return trdLendDuration001Service.getCounterId(map);
    }

    /**
     * 查询画面数据 <功能详细描述>
     * 
     * @return 结果值
     * @see
     * @since 1.0
     */

    @RequestMapping("/selectForPage")
    @ResponseBody
    public Page<TrdLendDuration001Dto> selectForPage() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return trdLendDuration001Service.selectForPage(map);
    }

    /**
     * 查询画面数据 <功能详细描述>
     * 
     * @return 结果值
     * @see
     * @since 1.0
     */

    @RequestMapping("/check")
    @ResponseBody
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) TrdLendDuration001Dto dto, BindingResult result,
                            String operateType) {
        if (StringUtil.equals(dto.getMode(), "revoke")) {
            if (periodCommonService.isAlreadyRevoked(dto.getBusiCode())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                        getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(validMessage);
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 撤销操作 <功能详细描述>
     * 
     * @return 结果值
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("/revoke")
    @ResponseBody
    public AjaxResult revoke(@RequestBody @Validated({ValidateGroupDelete.class}) TrdLendDuration001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("statusValid", dto.getStatusValid());
        paramMap.put("busiCode", dto.getpTradeId());
        TrdLendDuration001Dto tempDto = trdLendDuration001Service.getMyEntity(paramMap);
        tempDto.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getpTradeId()));
        tempDto.setMode(OperationType.REVOKE.toString());
        tempDto.setpTradeId(tempDto.getBusiCode());
        tempDto.setBusiCode(tempDto.getBusiCode() + 'R');
        tempDto.setTotleTradeId(tempDto.getBusiCode());
        tempDto.setActPortfolioId(dto.getActPortfolioId());
        tempDto.setOperateType("submit");
        ProcessParameter<TrdLendDuration001Dto> processParameter = new ProcessParameter<TrdLendDuration001Dto>();
        WorkFlowParameter<TrdLendDuration001Dto> params = new WorkFlowParameter<TrdLendDuration001Dto>();
        params.setBusinessCode(tempDto.getTotleTradeId());
        params.setPageId("TrdLendDuration002");
        params.setElementId(dto.getElementId());
        params.setBusinessType(DictCodeList.共通业务类型_拆借存续期交易.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(item -> WorkFlowUtil.concatComment(getMessage("TrdLendDuration001.tradeId") + GlobalConstant.COLON
                                          + item.getTotleTradeId() + GlobalConstant.OPEN_PARENTHESIS,
            DictCodeList.共通业务类型_拆借存续期交易.getCodeName() + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
//        params.setIsNeedWorkflow(item -> StringUtil.equals(item.getCapiIncomeFeature(), DictCodeListExt1.本金收益特征_保本.getValue()));
        String msg = trdLendDuration001Service.start(processParameter, tempDto);
        return new AjaxResult(msg);
    }

    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdLendDuration001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }

    /**
     * 获取所有交易编号
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getBusiCode")
    public Page<SelectOptionDto> getBusiCode() {
        return trdLendDuration001Service.getBusiCode(RequestUtil.getPageParam());
    }
}
