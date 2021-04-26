/*
 * 文件名：TrdRepoTrade003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.trade.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.trade.BussRepoSecService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加质押券
 * 〈功能详细描述〉
 *
 * @author 陈志力
 * @version 1.0 2019年01月11日
 * @see BussRepoSecDialogController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/BussRepoSecDialog")
public class BussRepoSecDialogController extends BaseController {

    @Autowired(required = false)
    private BussRepoSecService bussRepoSecService;

    @Override
    public String init(Model model) {
        String portfolioId = this.getParam("portfolioId");
        String ps = this.getParam("ps");
        String secAcctId = this.getParam("secAcctId");
        String chlId = this.getParam("chlId");
        String securitiesCust = this.getParam("securitiesCust");
        String repoType = this.getParam("repoType");
        String assetType1 = this.getParam("assetType1");
        //add by 张年  2019/08/05 start
        String tradeMarket = this.getParam("tradeMarket");
        //add by 张年  2019/08/05 end
        //Modify by gaopeng 2019/10/12 start
        String entrustId = this.getParam("entrustId");
        //Modify by gaopeng 2019/10/12 end
        String isDzh = this.getParam("isDzh");
        String quoteId = this.getParam("quoteId");
        if (!StringUtil.equals(this.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) || !WorkFlowUtil.isNotFromToDoList()
                || !StringUtil.equals(getParam("statusValid"), DictCodeListExt1.画面数据状态_待处理.getValue())) {
            String entityStr = this.getParam("entity");
            String bizCode = this.getParam("bizCode");
            BussRepoSecDto entity = StringUtil.isNotEmpty(entityStr) ? JsonUtils.json2Object(entityStr, BussRepoSecDto.class) : null;
            if (entity == null
                    && (StringUtil.isNotEmpty(entrustId) || StringUtil.isNotEmpty(bizCode))) {
                Map<String, Object> initSearchParam = new HashMap<>();
                initSearchParam.put("portfolioId", portfolioId);
                initSearchParam.put("ps", ps);
                initSearchParam.put("secAcctId", secAcctId);
                initSearchParam.put("chlId", chlId);
                initSearchParam.put("bizCode", bizCode);
                initSearchParam.put("securitiesCust", securitiesCust);
                initSearchParam.put("repoType", repoType);
                initSearchParam.put("assetType1", assetType1);
                //add by 张年  2019/08/05 start
                initSearchParam.put("tradeMarket", tradeMarket);
                //add by 张年  2019/08/05 end
                // 新建时
                if (StringUtil.isEmpty(bizCode)) {
                    initSearchParam.put("entrustId", entrustId);
                }
                entity = bussRepoSecService.selectBussRepoSec(initSearchParam);
            }
            if (entity != null) {
                // 已质押券信息一览
                model.addAttribute("entity", entity);
            }
        }
        //add by 张年  2019/08/05 start
        model.addAttribute("tradeMarket", tradeMarket);
        //add by 张年  2019/08/05 end
        model.addAttribute("portfolioId", portfolioId);
        //add by gaopeng  2019/10/12 start 
        model.addAttribute("entrustId", entrustId);
        //add by gaopeng  2019/10/12 end 
        //产品化_质押式回购交易界面,正回购时：点击添加后的可质押券范围为投资经理移入可质押库的、该组合下的债券，不可选不在质押库里债券 add by zhangpei 20201125 start
        String pageFromTz = this.getParam("pageFromTz");
        model.addAttribute("pageFromTz", pageFromTz);	
        //产品化_质押式回购交易界面,正回购时：点击添加后的可质押券范围为投资经理移入可质押库的、该组合下的债券，不可选不在质押库里债券 add by zhangpei 20201125 end
        model.addAttribute("ps", ps);
        model.addAttribute("secAcctId", secAcctId);
        model.addAttribute("chlId", chlId);
        model.addAttribute("securitiesCust", securitiesCust);
        model.addAttribute("repoType", repoType);
        model.addAttribute("assetType1", assetType1);
        model.addAttribute("statusValid", getParam("statusValid"));
        model.addAttribute("bizCode", getParam("bizCode"));
//        model.addAttribute("isTodoList", getParam("isTodoList"));
        model.addAttribute("pageModel", getParam("pageModel"));
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussRepoSecService.DEFAULT_ENTITY_NAME);
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
        model.addAttribute("ibuFlag_repoOverAvaCanContinue",
                CacheUtil.getSysParam("ibuFlag_repoOverAvaCanContinue"));
        // add by etliu 20210303 银行间上下行 start
        model.addAttribute("ignoreFlowTradeId", this.getParam("ignoreFlowTradeId"));
        // add by etliu 20210303 银行间上下行 end
        model.addAttribute("isDzh",isDzh);
        model.addAttribute("quoteId",quoteId);
        return "component/trade/BussRepoSecDialog";
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
    public Page<PledgeBondInfoDto> selectAvaPledgeBondDetailList(@RequestParam Map<String, Object> params) {
        return bussRepoSecService.selectAvaPledgeBondDetailPage(params);
    }

}
