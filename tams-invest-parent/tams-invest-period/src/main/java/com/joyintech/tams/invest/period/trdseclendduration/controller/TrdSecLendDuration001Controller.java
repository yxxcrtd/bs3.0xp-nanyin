package com.joyintech.tams.invest.period.trdseclendduration.controller;

import com.joyintech.framework.util.BeanUtil;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.period.trdseclendduration.dto.TrdSecLendDuration001Dto;
import com.joyintech.tams.invest.period.trdseclendduration.dto.TrdSecLendDuration002Dto;
import com.joyintech.tams.invest.period.trdseclendduration.service.TrdSecLendDuration001Service;
import com.joyintech.tams.invest.period.trdseclendduration.service.TrdSecLendDuration002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdrepoduration.dto.TrdRepoDuration001Dto;
import com.joyintech.tams.invest.period.trdrepoduration.dto.TrdRepoDuration002Dto;
import com.joyintech.tams.invest.period.trdrepoduration.service.TrdRepoDuration001Service;
import com.joyintech.tams.invest.period.trdrepoduration.service.TrdRepoDuration002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.util.Map;

import javax.validation.Valid;

/**
 * @author : 裴家俊
 * @version V1.0
 * @Project: tams-invest-parent
 * @Description: 债券借贷存续期 查询
 * @date  2019年07月01日
 */
@Controller
@PageId("TrdSecLendDuration001")
@RequestMapping("/invest/period/trdseclendduration/TrdSecLendDuration001")
public class TrdSecLendDuration001Controller extends BaseController {

    @Autowired
    private TrdSecLendDuration001Service trdSecLendDuration001Service;

    /**
     * TrdSecLendDuration002Service
     */
    @Autowired
    private TrdSecLendDuration002Service trdSecLendDuration002Service;

    /**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.回购拆借方向,  DictCode.画面数据状态};
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));
            model.addAttribute("remType", map.get("remType"));
            String mdate = (String)map.get("mdate");
            if(StringUtil.isNotEmpty(mdate)){
            	model.addAttribute("quaryDate", DateUtil.addDay(mdate,0)+" - "+DateUtil.addDay(mdate,0));
            }
        }
        return super.init(model);
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
        return trdSecLendDuration001Service.getBusiCode();
    }

    /**
     * 分页查询 有效、撤销或待处理的债券借贷存续期交易数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdSecLendDuration001Dto> findPage() {
        return trdSecLendDuration001Service.findPage(RequestUtil.getPageParam());
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
        return trdSecLendDuration001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@Valid TrdSecLendDuration002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdSecLendDuration002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdSecLendDuration002Dto> params = new WorkFlowParameter<>();
        params.setPageId("TrdSecLendDuration002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeListExt2.共通业务类型_债券借贷存续期.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(dto.getBusiCode(),
            DateUtil.format(dto.getTradeDate()), DictCodeListExt2.共通业务类型_债券借贷存续期.getCodeName()));
        processParameter.setWorkFlowParameter(params);
        TrdSecLendDuration002Dto entity = trdSecLendDuration002Service.findEffectiveData(BeanUtil.object2Map(dto));
        entity.setCancelRemark(dto.getCancelRemark());
        entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getBusiCode()));
        String msg = trdSecLendDuration002Service.start(processParameter, entity);
        return new AjaxResult(msg);
    }

    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeCheck")
    public AjaxResult revokeCheck(@Valid TrdSecLendDuration002Dto dto, BindingResult result) {
        return commonCheck(result, "revoke");
    }

}
