/*
 * 文件名：BussRepoSecPtlDialogController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张庚林
 * 修改时间：2019年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.trade.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.trade.BussRepoSecService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecPtlDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加质押券
 * 〈功能详细描述〉
 *
 * @author 陈志力
 * @version 1.0 2019年01月11日
 * @see BussRepoSecPtlDialogController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/BussRepoSecPtlDialog")
public class BussRepoSecPtlDialogController extends BaseController {

    @Autowired(required = false)
    private BussRepoSecService bussRepoSecService;

    @SuppressWarnings("unused")
    @Override
    public String init(Model model) {
        String portfolioId = this.getParam("portfolioId");
        String ps = this.getParam("ps");
        String portfolioName = this.getParam("portfolioName");
        String secAcctId = this.getParam("secAcctId");
        String tradeMarket = this.getParam("tradeMarket");
        String chlId = this.getParam("chlId");
        if (!StringUtil.equals(this.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) || !WorkFlowUtil.isNotFromToDoList()
                || !StringUtil.equals(getParam("statusValid"), DictCodeListExt1.画面数据状态_待处理.getValue())) {
            String entityStr = this.getParam("entity");
            String bizCode = this.getParam("bizCode");
            String entrustId = this.getParam("entrustId");
            BussRepoSecDto entity = StringUtil.isNotEmpty(entityStr) ? JsonUtils.json2Object(entityStr, BussRepoSecDto.class) : null;
            List<PledgeBondInfoDto> pledgeBondList=new ArrayList<>();
            if (entity != null)pledgeBondList = entity.getPledgeBondList();
            for(int i=0;i< pledgeBondList.size();i++){
                if(!pledgeBondList.get(i).getPortfolioId().equals(portfolioId)){
                    pledgeBondList.remove(i);
                    i=i-1;
                }
            }
            
            if (entity == null
                    && (StringUtil.isNotEmpty(entrustId) || StringUtil.isNotEmpty(bizCode))) {
                Map<String, Object> initSearchParam = new HashMap<>();
                initSearchParam.put("portfolioId", portfolioId);
                initSearchParam.put("ps", ps);
                initSearchParam.put("secAcctId", secAcctId);
                initSearchParam.put("chlId", chlId);
                initSearchParam.put("bizCode", bizCode);
                // 新建时
                if (StringUtil.equals(this.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    initSearchParam.put("entrustId", entrustId);
                }
                entity = bussRepoSecService.selectBussRepoSec(initSearchParam);
            }
            if (entity != null) {
                // 已质押券信息一览
                model.addAttribute("entity", entity);
            }
        }
        model.addAttribute("portfolioId", portfolioId);
        model.addAttribute("portfolioName", portfolioName);
        model.addAttribute("ps", ps);
        model.addAttribute("secAcctId", secAcctId);
        model.addAttribute("tradeMarket", tradeMarket);
        model.addAttribute("chlId", chlId);
        model.addAttribute("statusValid", getParam("statusValid"));
        model.addAttribute("bizCode", getParam("bizCode"));
//        model.addAttribute("isTodoList", getParam("isTodoList"));
        model.addAttribute("pageModel", getParam("pageModel"));
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussRepoSecService.DEFAULT_ENTITY_NAME2);
        model.addAttribute("entityName", entityName);
        model.addAttribute(GlobalConstant.WORKFLOW_ID, getParam(GlobalConstant.WORKFLOW_ID));
        model.addAttribute("pageId", entityName);
        model.addAttribute("operationType", getParam("operationType"));
        model.addAttribute("BACK_LEND_DIRECTION_04", DictCodeList.回购拆借方向_逆回购.getValue());
        // 回购拆借方向_正回购
        model.addAttribute("BACK_LEND_DIRECTION_03", DictCodeList.回购拆借方向_正回购.getValue());
        setSelectOptionToModel(model, DictCode.WIND一级分类_含债券一级分类等
                , DictCode.登记托管机构_中债);
        /*model.addAttribute("workflowPage", false);*/
        return "component/trade/BussRepoSecPtlDialog";
    }

    /**
     * 债券代码：下拉框
     *
     * @param params 画面参数
     * @return 债券代码：下拉框
     */
    @RequestMapping("getBondCode")
    @ResponseBody
    public List<SelectOptionDto> getBondCode(@RequestParam Map<String, Object> params) {
        return bussRepoSecService.getBondCode(params);
    }

    /**
     * 根据画面条件检索可质押券信息
     *
     * @param params 画面参数
     * @return List<PledgeBondInfoDto>
     */
    @RequestMapping("selectAvaPledgeBondDetailList")
    @ResponseBody
    public List<PledgeBondInfoDto> selectAvaPledgeBondDetailList(@RequestParam Map<String, Object> params) {
        List<PledgeBondInfoDto> list= bussRepoSecService.selectAvaPledgeBondDetailList(params);
        for(int i=0;i<list.size();i++){
            PledgeBondInfoDto pledgeBondInfoDto=list.get(i);
            //质押本方券券面总额
            if(StringUtils.equals(pledgeBondInfoDto.getHodingType(),DictCodeList.持仓类型_实际.getValue())){
                pledgeBondInfoDto.setPledgeBfPrin(pledgeBondInfoDto.getPrin());//质押本方券券面总额
                pledgeBondInfoDto.setAvaPledgeBfPrin(pledgeBondInfoDto.getAvaPledgePrin());//可质押本方券券面总额
            }else if(StringUtils.equals(pledgeBondInfoDto.getHodingType(),DictCodeList.持仓类型_融入.getValue())){
                pledgeBondInfoDto.setPledgeRrPrin(pledgeBondInfoDto.getPrin());//质押融入券券面总额
                pledgeBondInfoDto.setPledgeRrPrin(pledgeBondInfoDto.getAvaPledgePrin());//可质押融入券券面总额
            }
            pledgeBondInfoDto.setPortfolioId((String)params.get("portfolioId"));
            pledgeBondInfoDto.setPortfolioName((String)params.get("portfolioName"));
        }
        return list;
    }

}
