/*
 * 文件名：FinFundTarget001Controller.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.tams.invest.asset.futures.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.futures.dto.FinFuturesDto;
import com.joyintech.tams.invest.asset.futures.service.FinFuturesService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
import java.util.List;
import java.util.Map;

/**
 * 期货标 的资产 查询
 *
 * @author yufei
 * @version 1.0 2020年11月09日
 * @see FinFutures001Controller
 * @since 1.0
 */
@PageId("FinFutures001")
@Controller
@RequestMapping("/invest/asset/futures/FinFutures001")
public class FinFutures001Controller extends BaseController {

    @Autowired
    private FinFuturesService finFuturesService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.期货类型, DictCode.合约标的);
        return super.init(model);
    }

    /**
     * 画面查询方法
     *
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinFuturesDto> selectAll()
        throws Exception {
        return finFuturesService.selectForPage(RequestUtil.getParameters(getRequest()));
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto    dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(
        @RequestBody
        @Validated({ValidateGroupDelete.class})
            FinFuturesDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(
        @RequestBody
        @Validated({ValidateGroupDelete.class})
            FinFuturesDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinFuturesDto> processParameter = new ProcessParameter<FinFuturesDto>();
        WorkFlowParameter<FinFuturesDto> params = new WorkFlowParameter<FinFuturesDto>();
        params.setPageId("FinFutures002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_期货类资产.getValue());
        dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_期货.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：金融产品代码 + "(" + 金融产品代码+ ")"
        params.setComment(
            entity -> SpringContextUtil.getTextValue("FinFutures001.finprodMarketId") + "("
                      + entity.getFinprodMarketId() + ")" + "-" + SpringContextUtil.getTextValue(
                "FinFutures001.futuresContractId") + "(" + entity.getFuturesContractIdStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finFuturesService.start(processParameter,
            dto.getSelectedItem().toArray(new FinFuturesDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("/getFinprodMarketIdList")
    public List<SuggestDto> getFinprodMarketIdList(String param)
        throws Exception {
        return finFuturesService.getFinprodMarketIdList(param);
    }

    /**
     * 编辑时判断是否为交易数据
     *
     * @param dto    dto
     * @param editResult BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("editCheck")
    public AjaxResult editCheck(
        @RequestBody
            FinFuturesDto dto, BindingResult editResult) {
        //判断是否存在交易
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        AjaxResult wr = new AjaxResult();
        if (null != commonBusinessService) {
            if (commonBusinessService.isExistTransInAsset(dto.getFinprodId()) > 0) {
                JoyinMessage ms = new JoyinMessage(getMessage("FIN_ASSET_W_0002"),
                    MessageType.确认消息);
                joyinMessages.add(ms);
            }
        }
        wr.setJoyinMessages(joyinMessages);
        return wr;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return finFuturesService.export(this.getPageId(),param);

    }
}
