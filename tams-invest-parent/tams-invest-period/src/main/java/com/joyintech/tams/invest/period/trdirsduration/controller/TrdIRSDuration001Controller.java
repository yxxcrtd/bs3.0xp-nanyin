package com.joyintech.tams.invest.period.trdirsduration.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdirsduration.service.TrdIRSDuration001Service;
import com.joyintech.tams.invest.period.trdirsduration.service.TrdIRSDuration002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 利率互换存续期
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.controller
 * @Description: 利率互换存续期 查询
 * @date Date : 2019年08月10日
 */
@Controller
@PageId("TrdIRSDuration001")
@RequestMapping("/invest/period/trdirsduration/TrdIRSDuration001")
public class TrdIRSDuration001Controller extends BaseController {

    @Autowired
    private TrdIRSDuration001Service trdIRSDuration001Service;

    @Autowired
    private TrdIRSDuration002Service trdIRSDuration002Service;

    /**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.平安业务类型, DictCode.画面数据状态,
        DictCode.利率互换收付息类型, DictCode.利率互换存续期类型};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        return super.init(model);
    }

    /**
     * 获取所有交易编号（业务编号）
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getBusiCode")
    public Page<SelectOptionDto> getBusiCode() {
        return trdIRSDuration001Service.getBusiCode();
    }

    /**
     * 获取所有金融产品代码（交易编号）
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getTradeId")
    public Page<SelectOptionDto> getTradeId() {
        return trdIRSDuration001Service.getTradeId();
    }

    /**
     * 分页查询 有效、撤销或待处理的利率互换存续期交易数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdIRSDuration002ProcessDto> findPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        if (StringUtil.isNotEmpty(param.get("busiCode"))
            && DictCodeListExt1.画面数据状态_待处理.getValue().equals(param.get("statusValid"))) {
            return new Page<>();
        }
        return trdIRSDuration001Service.findPage(param);
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
        return trdIRSDuration001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@Validated({
        ValidateGroupDelete.class}) TrdIRSDuration002ProcessDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdIRSDuration002ProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdIRSDuration002ProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());

        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换存续期交易.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(trdIRSDuration002Service.生成业务说明());
        processParameter.setWorkFlowParameter(params);
        Map<String, Object> object2Map = new HashMap<String, Object>();
        object2Map.put("statusValid", dto.getStatusValid());
        object2Map.put("busiCode", dto.getBusiCode());
        TrdIRSDuration002ProcessDto entity = trdIRSDuration002Service.findEffectiveData(object2Map);
        entity.setRevokeReason(dto.getRevokeReason());
        entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getBusiCode()));
        entity.setBaseTotalTradeInfoDto(trdIRSDuration002Service.genFinProductTradeDto2(entity));
        String msg = trdIRSDuration002Service.start(processParameter, entity);
        return new AjaxResult(msg);
    }

    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeCheck")
    public AjaxResult revokeCheck(@Validated({
        ValidateGroupDelete.class}) TrdIRSDuration002ProcessDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}
