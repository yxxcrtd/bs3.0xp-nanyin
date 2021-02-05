package com.joyintech.tams.invest.period.trdbondduration.controller;

import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration003ProcessDto;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration003Service;
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
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration001Service;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import java.util.ArrayList;
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
@PageId("TrdBondDuration001")
@RequestMapping("/invest/period/trdbondduration/TrdBondDuration001")
public class TrdBondDuration001Controller extends BaseController {

    @Autowired
    private TrdBondDuration001Service trdBondDuration001Service;

    @Autowired
    private TrdBondDuration002Service trdBondDuration002Service;

    @Autowired
    private TrdBondDuration003Service trdBondDuration003Service;

    /**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.平安业务类型, DictCode.画面数据状态};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));

        String remType = getParam("remType");
        String finprodId = getParam("finprodId");
        Integer days = StringUtil.isNotEmpty(getParam("days"))? Integer.parseInt(getParam("days")) :0 ;
        String quaryDate = getParam("quaryDate");
        if(StringUtil.isNotEmpty(remType) && StringUtil.equals(BussinessDictCodeList.提醒类型_小分类_债券存续期.getValue(), remType)){
            model.addAttribute("remType", remType);
            model.addAttribute("finprodId", finprodId);
            String mdate = getParam("mdate");
            String dDate = getParam("dDate");
            if(StringUtil.isEmpty(dDate)){
                dDate = mdate;
            }
            model.addAttribute("quaryDate", DateUtil.addDay(dDate,0)+" - "+DateUtil.addDay(mdate,0));
            model.addAttribute("statusValid", "01,03");
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
        return trdBondDuration001Service.getBusiCode();
    }

    /**
     * 分页查询 证券账户代码、名称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getSecManageAcctId")
    public Page<SelectOptionDto> getSecManageAcctId() {
        return trdBondDuration001Service.getSecManageAcctId();
    }

    /**
     * 分页查询 市场代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdBondDuration001Service.getFinprodMarketId();
    }

    /**
     * 分页查询 有效、撤销或待处理的债券存续期交易数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdBondDuration002ProcessDto> findPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        if (StringUtil.isNotEmpty(param.get("busiCode")) && DictCodeListExt1.画面数据状态_待处理.getValue().equals(param.get("statusValid"))) {
            return new Page<>();
        }
        return trdBondDuration001Service.findPage(param);
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
        return trdBondDuration001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@Validated({ValidateGroupDelete.class}) TrdBondDuration002ProcessDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        String msg = "";
        if ("TrdBondDuration003".equals(RequestUtil.getParam("pageId"))) {
            ProcessParameter<TrdBondDuration003ProcessDto> processParameter = new ProcessParameter<>();
            WorkFlowParameter<TrdBondDuration003ProcessDto> params = new WorkFlowParameter<>();
            params.setPageId("TrdBondDuration003");
            params.setElementId(dto.getElementId());

            params.setBusinessCode(dto.getBusiCode());
            params.setBusinessType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
            params.setComment(trdBondDuration003Service.生成业务说明());
            processParameter.setWorkFlowParameter(params);
            TrdBondDuration003ProcessDto entity = trdBondDuration003Service.getTrdBondDuration003ProcessDto(BeanUtil.object2Map(dto));
            entity.setRevokeReason(dto.getRevokeReason());
            msg = trdBondDuration003Service.start(processParameter, entity);
        }
        else {
            ProcessParameter<TrdBondDuration002ProcessDto> processParameter = new ProcessParameter<>();
            WorkFlowParameter<TrdBondDuration002ProcessDto> params = new WorkFlowParameter<>();
            params.setPageId(RequestUtil.getParam("pageId"));
            params.setElementId(dto.getElementId());

            params.setBusinessCode(dto.getBusiCode());
            params.setBusinessType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
            // 设置流程业务说明, entity即传入的dto
            params.setComment(trdBondDuration002Service.生成业务说明());
            processParameter.setWorkFlowParameter(params);
            TrdBondDuration002ProcessDto entity = trdBondDuration002Service.findEffectiveData(BeanUtil.object2Map(dto));
            entity.setRevokeReason(dto.getRevokeReason());
            entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getBusiCode()));
            entity.setFinAssetIntInfoList(trdBondDuration002Service.findAssetIntInfo(trdBondDuration002Service.genParams(entity)));
            entity.setBaseTotalTradeInfoDto(trdBondDuration002Service.genFinProductTradeDto2(entity));
            msg = trdBondDuration002Service.start(processParameter, entity);
        }

        return new AjaxResult(msg);
    }

    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeCheck")
    public AjaxResult revokeCheck(@Validated({ValidateGroupDelete.class}) TrdBondDuration002ProcessDto dto,
                                  BindingResult result) {
        if (StringUtil.equals(dto.getOperateType(), "revoke")) {
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
        return commonCheck(result, dto.getOperateType());
    }
}
