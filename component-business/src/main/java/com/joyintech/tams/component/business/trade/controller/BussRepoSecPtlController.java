/*
 * 文件名：BussRepoSecPtlController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张庚林
 * 修改时间：2019年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.trade.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussRepoSecService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 添加多组合
 * 〈功能详细描述〉
 *
 * @author 陈志力
 * @version 1.0 2019年01月11日
 * @see BussRepoSecPtlController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/BussRepoSecPtl")
public class BussRepoSecPtlController extends BaseController {

    @Autowired(required = false)
    private BussRepoSecService bussRepoSecService;
   /* @Autowired
    private JoyinRestTemplate restTemplate;*/

    @Override
    public String init(Model model) {
        String portfolioId = this.getParam("portfolioId");
        String ps = this.getParam("ps");
        if (!StringUtil.equals(this.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) || Boolean.valueOf(getParam("isTodoList"))
                || !StringUtil.equals(getParam("statusValid"), DictCodeListExt1.画面数据状态_待处理.getValue())) {
            String entityStr = this.getParam("entity");
            String bizCode = this.getParam("bizCode");
            String entrustId = this.getParam("entrustId");
            BussRepoSecDto entity = StringUtil.isNotEmpty(entityStr) ? JsonUtils.json2Object(entityStr, BussRepoSecDto.class) : null;
            if (entity == null
                    && (StringUtil.isNotEmpty(entrustId) || StringUtil.isNotEmpty(bizCode))) {
                Map<String, Object> initSearchParam = new HashMap<>();
                initSearchParam.put("portfolioId", portfolioId);
                initSearchParam.put("ps", ps);
                initSearchParam.put("bizCode", bizCode);
                // 新建时
                if (StringUtil.isNotEmpty(entrustId)) {
                    initSearchParam.put("entrustId", entrustId);
                    initSearchParam.put("bizCode", null);
                    entity = bussRepoSecService.selectBussRepoSec(initSearchParam);
                    List<PledgeBondInfoDto> PledgeBondList=entity.getPledgeBondList();
                    entity.setPledgeBondListDetailed(PledgeBondList);
                    List<PledgeBondInfoDto> PledgeBondList2=new ArrayList<>();
                    for(int i=0;i<PledgeBondList.size();i++){
                        if(PledgeBondList2.size()>0){
                            int k=0;
                            for(int j=0;j<PledgeBondList2.size();j++){
                                if(PledgeBondList2.get(j).getEntrustId().equals(PledgeBondList.get(i).getEntrustId())){
                                    k=k+1;
                                }
                            }
                            if(k==0){
                                PledgeBondList2.add(PledgeBondList.get(i));
                            }
                        }else{
                            PledgeBondList2.add(PledgeBondList.get(i));
                        }
                    }
                    entity.setPledgeBondList(PledgeBondList2);
                }
                else{
                    entity = bussRepoSecService.selectBussRepoSec(initSearchParam);
                }
            }
            if (entity != null) {
                // 已质押券信息一览
                model.addAttribute("entity", entity);
            }
        }
        model.addAttribute("pageFrom", getParam("pageFrom"));
        model.addAttribute("statusValid", getParam("statusValid"));
        model.addAttribute("bizCode", getParam("bizCode"));
        model.addAttribute("isTodoList", getParam("isTodoList"));
        model.addAttribute("pageModel", this.getPageModel());
        String entityName = StringUtil.defaultString(this.getParam("entityNameBussRepoSecPtl"), BussRepoSecService.DEFAULT_ENTITY_NAME2);
        model.addAttribute("entityNameBussRepoSecPtl", entityName);
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        model.addAttribute("operationType", StringUtil.defaultString(this.getParam("operationType")));
        model.addAttribute("BACK_LEND_DIRECTION_03", DictCodeList.回购拆借方向_正回购.getValue());
        //add by liuwenjuan 费用支持平均分配 20200323 start
        model.addAttribute("IS_AVERAGE_DISTRIBUTION_OF_EXPENSES", CacheUtil.getSysParam(BussinessConstant.IS_AVERAGE_DISTRIBUTION_OF_EXPENSES));
        //add by liuwenjuan 费用支持平均分配 20200323 end
        this.setSelectOptionToModel(model, DictCode.WIND一级分类_含债券一级分类等
                , DictCode.登记托管机构, DictCode.分配方式);

        return "component/trade/BussRepoSecPtl";
    }

    /**
     * 实体类进行检查
     *
     * @param map 传入的实体类
     * @return 风险结果实体类
     * @see
     * @since 1.0
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@RequestBody Map<String, Object> map) {
        AjaxResult result = new AjaxResult();
        List<ValidMessage> messages = new ArrayList<>();
        Map<String, String> data = (HashMap<String, String>) map.get("data");
        if(!(StringUtil.isNotEmpty(data.get("secAcctId")) || StringUtil.isNotEmpty(data.get("chlId")))){
            messages.add(new ValidMessage("BUS_REPO_SEC_001",getMessage("BUS_REPO_SEC_002")));
        }
        if(StringUtils.isNotBlank(data.get("secAcctId"))&&StringUtils.isNotBlank(data.get("chlId"))){
            messages.add(new ValidMessage("BUS_REPO_SEC_001",getMessage("BUS_REPO_SEC_005")));
        }
        String[] validParams = {"portfolioId", "ps"};
        for (String param : validParams) {
            if (map.get(param) != null) {
                List<String> fields = (List<String>) map.get(param);
                notEmptyCheck(data.get(param), fields.get(0).toString(), fields.get(1).toString(), messages);
            }
        }
        if (messages.size() > 0) {
            result.setErrorList(messages);
        }
        return result;
    }
    
    /**
     * 根据组合代码查询证券托管户：联想框文本框
     *
     * @param params 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSecManageAcctId")
    public List<SelectOptionDto> getSecManageAcctId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        //add by wangpeng 一体化版本controller直接调用微服务会抛出异常
        List<SuggestDto> tempList = bussRepoSecService.getSecManageAcctId(param);
        //add by wangpeng end
        List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
        if (!StringUtil.isEmptyList(tempList)) {
            SelectOptionDto selectOptionDto;
            for (SuggestDto suggestDto : tempList) {
                selectOptionDto = new SelectOptionDto();
                selectOptionDto.setId(suggestDto.getCode());
                selectOptionDto.setText(suggestDto.getName());
                if (StringUtil.isNotBlank((String) param.get("portfolioId"))) {
                    selectOptionDto.setParentId((String) param.get("portfolioId"));
                }
                resultList.add(selectOptionDto);
            }
        }
        return resultList;
    }

    /**
     * check检查
     *
     * @param obj       检查得到值
     * @param fieldName 返回消息名
     * @param messages  检查结果信息
     * @see
     * @since 1.0
     */
    private void notEmptyCheck(Object obj, String fieldName, String massage, List<ValidMessage> messages) {
        if (!StringUtil.isNotEmpty(obj)) {
            fieldName = getMessage(fieldName);
            messages.add(new ValidMessage(fieldName, getMessage("FW_E_0006", getMessage(massage))));
        }
    }
    
    /**
     * 获取产品实体类
     * 
     * @return 账号实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("portfolioName")
    public PledgeBondInfoDto getCctInfo() {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        String[] noAndCcy = map.get("portfolioId").toString().split("_");
        map.replace("portfolioId", noAndCcy[0]);
        PledgeBondInfoDto pledgeBondInfoDto = bussRepoSecService.portfolioName(map);
        return pledgeBondInfoDto;
    }
    
}
