package com.joyintech.tams.invest.period.trdoptionduration.controller; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luocheng
 * 修改时间：2019/12/31
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.period.trdoptionduration.dto.OptionDurationDetailDto;
import com.joyintech.tams.invest.period.trdoptionduration.service.TrdOptionDuration002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@PageId("TrdOptionDuration003")
@RequestMapping("/invest/period/trdoptionduration/TrdOptionDuration003")
@Controller
public class TrdOptionDuration003Controller extends BaseController {

    @Autowired
    private TrdOptionDuration002Service trdOptionDuration002Service;
    @Autowired
    private IPlatformDate iPlatformDate;

    @Override public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.交易方向,DictCode.行权方式,DictCode.期权费支付类型,DictCode.是否,DictCode.收付方向,DictCode.结构类型);
        OptionDurationDetailDto entity = new OptionDurationDetailDto();
        Map<String, Object> map = RequestUtil.getPageParam();
        String workflowId = getParam("workflowId");
        String finprodId = this.getParam("finprodId");
        String xqType = getParam("xqType");
        model.addAttribute("finprodId", finprodId);
        model.addAttribute("workflowId", workflowId);
        if(StringUtil.isNotEmpty(workflowId)){ //流程中的页面
            entity = trdOptionDuration002Service.getBussinessEntityByWorkflowId(workflowId);
          //update zhangweiqi 2020-02-12 流程中显示原编号begin
            if(StringUtil.equals(entity.getOperateType(), "revoke")){
            if(StringUtil.isNotEmpty(entity.getFinprodId()))
            entity.setTotleTradeId(entity.getFinprodId());
            if(StringUtil.isNotEmpty(entity.getpTradeId()))
            entity.setBusiCode(entity.getpTradeId());
            if(entity.getZfDate()==null)
            entity.setZfDate(entity.getXqDate());
            }
            entity.setIsfqxq(DictCodeList.是否_是.getValue());
            if(entity.getFqxqDate() == null){
                entity.setFqxqDate(iPlatformDate.getSysDate());
            }
            //update zhangweiqi 2020-02-12 流程中显示原编号end
            entity.setMdate(DateUtil.parse(entity.getMdateStr()));
            model.addAttribute("totleTradeId", entity.getTotleTradeId());
        }
        else {//查看和编辑页面
            map.put("sysDate", DateUtil.getSysDate());
            entity = trdOptionDuration002Service.getMyEntity(map);
            entity.setIsfqxq(DictCodeList.是否_是.getValue());
            if(StringUtil.isEmpty(entity.getSffx())){
                entity.setSffx(DictCodeList.收付方向_收入.getValue());
            }
            if(entity.getFqxqDate() == null){
                entity.setFqxqDate(iPlatformDate.getSysDate());
            }
            if(StringUtil.isNotEmpty(xqType)){
                entity.setXqType(xqType);
            }

            model.addAttribute("entity", entity);
        }
        if(!"待处理".equals(entity.getDataStatus())){
            model.addAttribute("totleTradeId", entity.getTotleTradeId());
        }
        model.addAttribute(GlobalConstant.ENTITY,entity);
        return super.init(model);
    }


}
