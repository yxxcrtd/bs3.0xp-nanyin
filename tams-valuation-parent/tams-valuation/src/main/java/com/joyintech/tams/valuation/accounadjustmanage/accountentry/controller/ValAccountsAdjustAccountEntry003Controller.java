/*
 * 文件名：Accountset001Controller.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2016年12月26日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.definition.ValAccountEntryCodeValue;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry003Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry001Service;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry003Service;
import com.joyintech.tams.common.base.entity.BokSceneconfig;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.ValStringUtil;

/**
*
* 文件名：AccountsAdjustAccountEntry003Controller.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* @author wujie
* 修改时间：2018年5月16日
* 修改内容：会计记账场景配置
* 系统名称：估值核算系统V1.0
*/
@Controller
@RequestMapping("/valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry003")
@PageId("ValAccountsAdjustAccountEntry003")
public class ValAccountsAdjustAccountEntry003Controller extends BaseController {

    /**
     * AccountsAdjustAccountEntry003
     */
    @Autowired
    private ValAccountsAdjustAccountEntry003Service valAccountsAdjustAccountEntry003Service;

    /**
     * AccountsAdjustAccountEntry001
     */
    @Autowired
    private ValAccountsAdjustAccountEntry001Service valAccountsAdjustAccountEntry001Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        Map<String, Object> param = RequestUtil.getPageParam();
        String booksetId = getParam("booksetId");
        String sceneNodeId = getParam("sceneNodeId");
        BokSceneconfig config = valAccountsAdjustAccountEntry003Service.findSceneConfig(
            sceneNodeId);
        String pageModel = getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY);
        model.addAttribute("hasSub", false);
        String tableId = getParam("nodeValue");
        // 1. 会计分录添加子节点
        if (StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_ADD)) {
            SelectOptionDto dto = valAccountsAdjustAccountEntry003Service.findAllFSceneBySceneId(
                config == null ? null : config.getSceneNodeId());
            model.addAttribute("FSCENE_NODEID", "[" + JsonUtils.object2Json(dto) + "]");

            if (config != null) {
                model.addAttribute("level", config.getLevel() + 1);
                model.addAttribute("fSceneNodeId", config.getSceneNodeId());
                initNodeIds(model, sceneNodeId, config);
            }
        }
        // 2. 会计分录新建
        else if (StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            SelectOptionDto dto = valAccountsAdjustAccountEntry003Service.findRootNodeByBooksetId(
                booksetId);
            dto.setText(getMessage(ValAccountEntryCodeValue.根节点.getCode()));
            model.addAttribute("FSCENE_NODEID", "[" + JsonUtils.object2Json(dto) + "]");

            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(ValAccountEntryCodeValue.ACCOUNTEVENTS.getCode());
            dto1.setText(getMessage(ValAccountEntryCodeValue.会计事件.getCode()));
            model.addAttribute("nodeIds", "[" + JsonUtils.object2Json(dto1) + "]");
            model.addAttribute("tableIds",
                JsonUtils.object2Json(valAccountsAdjustAccountEntry001Service.findTableIds(param)));
            model.addAttribute("level", 1);
        }
        // 3. 会计分录修改、复制、查看
        else if (StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
                 || StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_COPY)
                 || StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
            SelectOptionDto dto = valAccountsAdjustAccountEntry003Service.findAllFSceneBySceneId(
                config == null ? null : config.getFsceneNodeid());
            model.addAttribute("FSCENE_NODEID", "[" + JsonUtils.object2Json(dto) + "]");
            // 如果父节点是根节点
            if ("0".equals(dto.getCol())) {
                SelectOptionDto dto1 = new SelectOptionDto();
                dto1.setId(ValAccountEntryCodeValue.ACCOUNTEVENTS.getCode());
                dto1.setText(getMessage(ValAccountEntryCodeValue.会计事件.getCode()));
                model.addAttribute("nodeIds", "[" + JsonUtils.object2Json(dto1) + "]");

                model.addAttribute("tableIds",
                    JsonUtils.object2Json(valAccountsAdjustAccountEntry001Service.findTableIds(param)));
                model.addAttribute("level", config == null ? 1 : config.getLevel());
            }
            if (sceneNodeId != null && config != null && config.getLevel() != 1) {
                initNodeIds(sceneNodeId, model, null);
            }
            model.addAttribute("fSceneNodeId", dto.getId());
            model.addAllAttributes(getPageParam());
            model.addAttribute(GlobalConstant.ENTITY, config);
        }
        if (config != null) {
            model.addAttribute("updateTimeLong", config.getUpdateTime().getTime());
        }
        model.addAttribute("no", DictCodeList.是否_否.getCodeName());
        model.addAttribute("yes", DictCodeList.是否_是.getCodeName());
        model.addAttribute("sceneNodeId", sceneNodeId);
        model.addAttribute("booksetId", booksetId);
        model.addAttribute("tableId", tableId);
        model.addAttribute("ROOTNODE", ValAccountEntryCodeValue.ROOTNODE.getCode());
        model.addAttribute("ACCOUNTEVENTS", ValAccountEntryCodeValue.ACCOUNTEVENTS.getCode());
        model.addAttribute("ACCOUNTENTRY_E_002", getMessage("ACCOUNTENTRY_E_002"));
        return super.init(model);
    }

    /**
     * 获取分页参数 add by wujie on 20180515 remark:功能迁移时BaseController类无以下基础方法
     * 
     * @return 分页参数
     * @see
     */
    protected final Map<String, Object> getPageParam() {
        Map<String, Object> param = this.getRequestParams();
        String page = this.getRequest().getParameter("page");
        String rows = this.getRequest().getParameter("rows");
        // 当前页
        param.put(GlobalConstant.PAGE_NO_FIELD, StringUtil.integerValueOf(page, 1));
        // 模糊查询字符串
        param.put(GlobalConstant.LIKE_STRING,
            ValStringUtil.toLikeString(this.getParam(GlobalConstant.LIKE_STRING), 3));
        // 每页显示的条数
        param.put(GlobalConstant.PAGE_LIMIT_FIELD, StringUtil.integerValueOf(rows, 20));
        return param;
    }

    /**
     * 获取请求参数 add by wujie on 20180515 remark:功能迁移时BaseController类无以下基础方法
     * 
     * @return 请求参数
     * @see
     */
    protected final Map<String, Object> getRequestParams() {
        return RequestUtil.getParameters(this.getRequest(),
            LoggerFactory.getLogger(getClass()).isDebugEnabled());
    }

    /**
     *  初始化影响记账元素下拉列表
     * 
     * @param sceneNodeId 场景分支代码
     * @param model 页面模型
     * @param config 场景科目配置
     * @see
     * @since 1.0
     */
    private void initNodeIds(Model model, String sceneNodeId, BokSceneconfig config) {
        List<SelectOptionDto> subConfigs = valAccountsAdjustAccountEntry003Service.findConfigByFSceneId(
            config.getSceneNodeId());
        if (subConfigs == null || subConfigs.isEmpty()) {
            initNodeIds(sceneNodeId, model, GlobalConstant.DETAIL_PAGE_MODEL_ADD);
        }
        else {
            // 查找二级节点的节点值
            if (sceneNodeId != null && sceneNodeId.length() >= 6) {
                BokSceneconfig level2Node = valAccountsAdjustAccountEntry003Service.findSceneConfig(
                    sceneNodeId.substring(0, 6));
                if (level2Node != null) {
                    String nodeId = level2Node.getNodeValue();
                    model.addAttribute("tableId", nodeId);
                }
            }
            model.addAttribute("hasSub", true);
            model.addAttribute("nodeIds", "[" + JsonUtils.object2Json(subConfigs.get(0)) + "]");
        }
    }

    /**
     * 初始化影响记账元素下拉列表
     * 
     * @param sceneNodeId 场景分支代码
     * @param model 页面模型
     * @param pageModel 页面模式
     * @see
     * @since 1.0
     */
    private void initNodeIds(String sceneNodeId, Model model, String pageModel) {
        // 查找当前账套下，树的二级节点的nodeId
        if (sceneNodeId != null && sceneNodeId.length() >= 6) {
            BokSceneconfig level2Node = valAccountsAdjustAccountEntry003Service.findSceneConfig(
                sceneNodeId.substring(0, 6));
            if (level2Node != null) {
                String nodeId = level2Node.getNodeValue();
                model.addAttribute("tableId", nodeId);
                List<SelectOptionDto> dtos = valAccountsAdjustAccountEntry003Service.findNodeIds(
                    nodeId, sceneNodeId, pageModel, GlobalConstant.DETAIL_PAGE_MODEL_ADD);
                model.addAttribute("nodeIds", JsonUtils.object2Json(dtos));
            }
        }
    }

    /**
     * 检索场景分支与科目配置信息
     * 
     * @return AccountsAdjustAccountEntry002Dto list
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(path = "findAccountEntryConfigData")
    public List<ValAccountsAdjustAccountEntry002Dto> findAccountEntryConfigData() {
        String sceneNodeId = getParam("sceneNodeId");
        if (StringUtil.isEmpty(sceneNodeId)) {
            return new ArrayList<>();
        }
        return valAccountsAdjustAccountEntry003Service.findAccountEntryConfigData(sceneNodeId,
            GlobalConstant.DETAIL_PAGE_MODEL_ADD.equals(getParam("pageModel")));
    }

    /**
     * 保存数据check
     * 
     * @param accountEntryDto 数据
     * @param result check结果
     * @return WebResult
     */
    @ResponseBody
    @RequestMapping(path = "checkSave")
    public AjaxResult checkSave(@RequestBody @Valid ValAccountsAdjustAccountEntry003Dto accountEntryDto,
                               BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult(this.getMessage("VAL_COMMON_C_0010", "保存"), MessageType.确认消息);
    }

    /**
     * 保存数据
     * 
     * @param accountEntryDto 数据
     * @return WebResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(path = "saveEntry")
    public AjaxResult saveEntry(@RequestBody ValAccountsAdjustAccountEntry003Dto accountEntryDto) {
        try {
            valAccountsAdjustAccountEntry003Service.save(accountEntryDto);
        }
        catch (Exception e) {
            getLogger().error(e.getMessage());
            return new AjaxResult(e.getMessage(), MessageType.错误消息);
        }
        return new AjaxResult(getMessage("VAL_COMMON_I_0006"));
    }

    /**
     * 初始化影响值域下拉框
     * 
     * @param dictCode 数据字典code
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(path = "findDictCodeList")
    public List<SelectOptionDto> findDictCodeList(String dictCode) {
        System.out.println("dictCode==" + dictCode);
        List<SelectOptionDto> dtos = new ArrayList<>();
        if(!StringUtil.isNotEmpty(dictCode)){
            return dtos;
        }
        DataDict dict = CacheUtil.getDictCode(dictCode);
        if (dict == null || dict.getItems() == null || dict.getItems().size() == 0) {
            return dtos;
        }
        List<DataDictItem> list = (List<DataDictItem>)dict.getItems();
        if (list != null && list.size() > 0) {
            for (DataDictItem obj : list) {
                SelectOptionDto dto = new SelectOptionDto();
                dto.setId(obj.getItemValue());
                dto.setText(obj.getItemValue());
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
