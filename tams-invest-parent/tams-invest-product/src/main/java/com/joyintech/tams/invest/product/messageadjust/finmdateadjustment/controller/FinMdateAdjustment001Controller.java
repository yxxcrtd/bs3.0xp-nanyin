/*
 * 文件名：FinMdateAdjustment001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.dto.FinMdateAdjustment001Dto;
import com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.service.FinMdateAdjustment001Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;

/**
 * 到期日调整
 * 
 * @author zhangliang
 * @version 1.0 2018年11月1日
 * @see FinMdateAdjustment001Controller
 * @since 1.0
 */
@Controller
@PageId("FinMdateAdjustment001")
@RequestMapping("/invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment001")
public class FinMdateAdjustment001Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinMdateAdjustment001Service finMdateAdjustment001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, new DictCode[] {DictCode.调整类型, DictCode.发送状态});
        model.addAttribute("MDATEADJUSTTYPE_01", DictCodeListExt2.调整类型_提前终止.getValue());
        model.addAttribute("MDATEADJUSTTYPE_02",DictCodeListExt2.调整类型_产品展期.getValue());
        model.addAttribute("MDATEADJUSTTYPE_03",DictCodeListExt2.调整类型_到期日调整.getValue());
        
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        // 画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);

        model.addAttribute("isEleShow", isEleShow);

        return super.init(model);
    }

    /**
     * 查询到期日调整列表数据
     * 
     * @return 到期日调整列表数据
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<FinMdateAdjustment001Dto> selectForPage() {
        return finMdateAdjustment001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finMdateAdjustment001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinMdateAdjustment001Dto finMdateAdjustment001Dto) {
        AjaxResult ar = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        //未调整数据，说明是初始生成的产品到期日
        //int adjustNum=finMdateAdjustment001Service.getMaxAdjustNum(finMdateAdjustment001Dto.getFinprodId());
        if(finMdateAdjustment001Dto.getAdjustNum()==null){
        	errorList.add(new ValidMessage("adjustNum","产品成立时初始生成的产品到期日不允许修改"));
        }
        if(finMdateAdjustment001Service.queryProductByProdId(finMdateAdjustment001Dto.getFinprodId())==null){
        	errorList.add(new ValidMessage("finprodId","只能对存续产品进行到期日修改"));
        }
        if (StringUtil.isEmptyList(errorList)) {
            String btnName = "";
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,
                finMdateAdjustment001Dto.getOperateType())) {
                btnName = "SysCommon.save";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,
                finMdateAdjustment001Dto.getOperateType())) {
                btnName = "SysCommon.submit";
            }
            ar.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
        }
        else {
            ar.setErrorList(errorList);
        }

        return ar;
    }

    /**
     * 发送销售接口状态
     *
     * @param FinMdateAdjustment001Dto
     * @return 发送结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("sendMdateAdjustmentSale")
    public AjaxResult sendMdateAdjustmentSale(@RequestBody FinMdateAdjustment001Dto finMdateAdjustment001Dto) {
        //或许选择推送的数据，循环调用推送销售系统方法
        List<FinMdateAdjustment001Dto> dtoList = finMdateAdjustment001Dto.getSelectedItem1() ;
        AjaxResult ar = new AjaxResult() ;
        if(dtoList.size() > 0){
            List<ValidMessage> errorList = ar.getErrorList();
            boolean sendFlag = true ;// 默认状态为推送销售系统
            for (FinMdateAdjustment001Dto fin001Dto : dtoList) {
                // 查询是否已经推送销售系统
                if(DictCodeList.发送状态_发送成功.getValue().equals(fin001Dto.getSendStatus())){
                    errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032","未发送和发送失败")));
                    sendFlag = false ;
                    return ar ;
                }
            }
            if (sendFlag) {
                List<String> finProdIds = new ArrayList<>();
                for (FinMdateAdjustment001Dto model : dtoList) {
                    finProdIds.add(model.getFinprodId());
                }
                // 推送产品分红至销售系统
                Map<String, String> map = finMdateAdjustment001Service.sendMdateAdjustmentSale(finProdIds);
                errorList.add(new ValidMessage(map.get("msg")));
            }
        }
        return ar;
    }
}
