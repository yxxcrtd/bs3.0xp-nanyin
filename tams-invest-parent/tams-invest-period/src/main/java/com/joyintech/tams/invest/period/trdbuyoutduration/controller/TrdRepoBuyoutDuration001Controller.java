package com.joyintech.tams.invest.period.trdbuyoutduration.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbuyoutduration.dto.TrdRepoBuyoutDuration001Dto;
import com.joyintech.tams.invest.period.trdbuyoutduration.dto.TrdRepoBuyoutDuration002Dto;
import com.joyintech.tams.invest.period.trdbuyoutduration.service.TrdRepoBuyoutDuration001Service;
import com.joyintech.tams.invest.period.trdbuyoutduration.service.TrdRepoBuyoutDuration002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.controller
 * @Description: 债券存续期 查询
 * @date Date : 2018年09月21日 13:48
 */
@Controller
@PageId("TrdRepoBuyoutDuration001")
@RequestMapping("/invest/period/trdbuyoutduration/TrdRepoBuyoutDuration001")
public class TrdRepoBuyoutDuration001Controller extends BaseController {

    @Autowired
    private TrdRepoBuyoutDuration001Service trdRepoDuration001Service;

    /**
     * TrdRepoBuyoutDuration002Service
     */
    @Autowired
    private TrdRepoBuyoutDuration002Service trdRepoDuration002Service;

    /**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[]{
            DictCode.金融产品类型_投管用, DictCode.画面数据状态};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));
            model.addAttribute("statusValid", "01");
            model.addAttribute("remType", map.get("remType"));
            String quaryDate = (String)map.get("quaryDate");
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
        return trdRepoDuration001Service.getBusiCode();
    }

    /**
     * 获取所有组合代码
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public Page<SelectOptionDto> getPortfolioId() {
        return trdRepoDuration001Service.getPortfolioId();
    }

    /**
     * 分页查询 证券账户代码、名称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getSecManageAcctId")
    public Page<SelectOptionDto> getSecManageAcctId() {
        return trdRepoDuration001Service.getSecManageAcctId();
    }

    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdRepoBuyoutDuration001Dto> findPage() {
        return trdRepoDuration001Service.findPage(RequestUtil.getPageParam());
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
        return trdRepoDuration001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@Valid TrdRepoBuyoutDuration002Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdRepoBuyoutDuration002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdRepoBuyoutDuration002Dto> params = new WorkFlowParameter<>();
        params.setPageId("TrdRepoBuyoutDuration002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeList.共通业务类型_买断式回购存续期.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(dto.getBusiCode(),
                dto.getTradeDate(), DictCodeList.共通业务类型_买断式回购存续期.getCodeName()));
        processParameter.setWorkFlowParameter(params);
        TrdRepoBuyoutDuration002Dto entity = trdRepoDuration002Service.findEffectiveData(BeanUtil.object2Map(dto));
        entity.setRevokeReason(dto.getRevokeReason());
        entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getBusiCode()));
        String msg = trdRepoDuration002Service.start(processParameter, entity);
        return new AjaxResult(msg);
    }

    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeCheck")
    public AjaxResult revokeCheck(@Valid TrdRepoBuyoutDuration002Dto dto, BindingResult result) {
        return commonCheck(result, "revoke");
    }

}
