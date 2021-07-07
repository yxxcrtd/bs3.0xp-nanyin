
package com.joyintech.tams.invest.entrust.etrstockconnect.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectPoDto;
import com.joyintech.tams.invest.entrust.etrstockconnect.service.EtrStockConnect001Service;
import com.joyintech.tams.invest.entrust.etrstockconnect.service.EtrStockConnect002Service;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 港股通
 * @see EtrStockConnect001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrStockConnect001")
@RequestMapping("/invest/entrust/etrstockconnect/EtrStockConnect001")
public class EtrStockConnect001Controller extends BaseController {

    @Autowired
    private EtrStockConnect001Service etrStockConnect001Service;

    @Autowired
    private EtrStockConnect002Service etrStockConnect002Service;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.是否沪港通或深港通);
        return super.init(model);
    }


    /**
     * 初始化行情
     * @return
     */
    @RequestMapping(value = "/initHq")
    public String initHq(Model model){
        return "invest/entrust/etrstockconnect/EtrStockConnectHq";
    }

    /**
     * 查询指令
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectEtrStockConnect")
    public Page<EtrStockConnectDto> selectEtrStockConnect(){
        List<EtrStockConnectDto>  result = etrStockConnect001Service.selectEtrStockConnect(RequestUtil.getPageParam());
        Page<EtrStockConnectDto> res = new Page<EtrStockConnectDto>(result);
        return res;
    }
    
    /**
     * 查询持仓
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPositionsPage")
    public Page<EtrStockConnectPoDto> findPositionsPage(){
        return etrStockConnect001Service.findPositionsPage(RequestUtil.getPageParam());
    }

    /**
     * 查询持仓
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPositionsPage2")
    public Page<EtrStockConnectPoDto> findPositionsPage2(@RequestBody Map<String, Object> param){
        return etrStockConnect001Service.findPositionsPage(param);
    }

    /**
     * 撤回委托 <功能详细描述>
     *
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrStockConnectDto dto) {
        List<EtrStockConnectDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrStockConnectDto entity : dtolist){
                etrStockConnect002Service.recallDelegate(entity);
            }
            return new AjaxResult(this.getMessage("ETR_X_001"));
        }
        return new AjaxResult(this.getMessage("ETR_X_002"));
    }
    /**
     * 关闭委托 
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrStockConnectDto dto) {
        List<EtrStockConnectDto> dtolist = dto.getSelectedItem();
        List<String> errorEntrusts = new ArrayList<>();
        if(dtolist != null && dtolist.size()>0){
            for(EtrStockConnectDto entity : dtolist){
                boolean res = etrStockConnect002Service.closeDelegate(entity.getEntrustId(), entity.getCloseWay(), entity.getEntrustStatus());
                if (!res) {
                    errorEntrusts.add(entity.getEntrustId());
                }
            }
            if (errorEntrusts.size() == 0) {
                return new AjaxResult(this.getMessage("ETR_X_003"));
            } else {
                String errorMsg = errorEntrusts.toString() + this.getMessage("ETR_X_004");
                return new AjaxResult(errorMsg, MessageType.错误消息);
            }
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
    }
    /**
     * 按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) EtrStockConnectDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 港股通行情信息
     */
    @RequestMapping(value="findQuotations")
    @ResponseBody
    public Map<String, Object> findQuotations() {
        return etrStockConnect001Service.findQuotationsPage(RequestUtil.getPageParam());
    }

}
