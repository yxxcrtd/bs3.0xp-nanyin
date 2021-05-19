/*
 * 文件名：XpLoggingUserAction.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.system.useraction;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.LogMethod;
import com.joyintech.framework.base.intf.PageElement;
import com.joyintech.framework.cache.ThreadCache;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.logging.ActionLog;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tools.actionlog.dao.ActionLogMapper;
import com.joyintech.tools.actionlog.service.LoggingUserAction;
import com.joyintech.tools.constant.ElementTypeEnum;
import com.joyintech.tools.util.PageUtil;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.ProcessType;
import com.joyintech.workflow.engine.constant.WflConstants;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.ProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ZhangYangFei
 * @version 1.0 2019年1月29日
 * @see XpLoggingUserAction
 * @since 1.0
 */
public class XpLoggingUserAction extends LoggingUserAction {

    /**
     * ActionLogMapper
     */
    private ActionLogMapper mapper;

    /**
     * 工作流Service
     */
    private ProcessService processService;

    /**
     * init
     */
    private static final String INIT = "init";

    /**
     * pageIdForLog
     */
    private static final String PAGE_ID = "pageIdForLog";

    /**
     * elementIdForLog
     */
    private static final String ELEMENT_ID = "elementIdForLog";

    /**
     * 表格重新加载
     */
    private static final String TABLE_RELOAD = "tableReload";
    
    /**
     * 导出按钮ID后缀
     */
    private static final String BTN_EXPORT = "_btn_export";

    /**
     * 工作流特定参数
     */
    private static final List<String> WORK_PARA = Arrays.asList("operType", "operateType",
        GlobalConstant.WORKFLOW_ID, "workflowParameter", "pageModel", "users", "roles",
        GlobalConstant.PAGE_ID, GlobalConstant.ELEMENT_ID, "submit", "isTodoList", PAGE_ID,
        ELEMENT_ID);

    /**
     * 查询特定参数
     */
    private static final List<String> SEARCH_PARA = Arrays.asList("pageSize", "curPage",
        GlobalConstant.ELEMENT_ID, GlobalConstant.PAGE_ID, PAGE_ID, ELEMENT_ID, "length", "page");

    /**
     * mutiApproval
     */
    private static final String MUTIAPPROVAL = "mutiApproval";

    /**
     * 修改操作时数据
     */
    private static final String MODIFYDATA = "_modifyData";

    /**
     * 查询操作时条件数据
     */
    private static final String SEARCHDATA = "_searchData";

    /**
     * elementId
     */
    private static final String HANDLE = "经办";

    /**
     * 
     */
    private static final Map<Object, String> operTypeMap = initOperType();

    /**
     * {@inheritDoc}
     * 
     * @throws WorkFlowException
     */
    @Override
    public void LoggingAction(HttpServletRequest request, Date preDate, Date postDate,
                              Object returnData)
        throws Exception {

        // 请求参数
        Map<String, Object> para = RequestUtil.getParameters(request);
        String url = RequestUtil.getRequestUrl(request);
        // 特殊url
        if (StringUtil.containsIgnoreCase(url, MUTIAPPROVAL)) {
            mutiApproval(request, returnData, preDate, postDate);
            return;
        }
        // 进入工作流
        if (para.containsKey(WflConstants.PROCESS_TASK_ID)
            || para.containsKey(WflConstants.PROCESS_INSTANCE_ID)) {
            this.workFlowAction(request, para, returnData, preDate, postDate);
            return;
        }
        // 按钮操作
        String pageId = StringUtil.getOrElse((String)para.get(PAGE_ID), (String)para.get(GlobalConstant.PAGE_ID));
        String elementId = StringUtil.getOrElse((String)para.get(ELEMENT_ID), (String)para.get(GlobalConstant.ELEMENT_ID));
        String pageModel = (String)para.get(GlobalConstant.DETAIL_PAGE_MODEL_KEY);
        String elementName = StringUtil.EMPTY;
        com.joyintech.framework.base.intf.Page page = PageUtil.getPage(pageId);

        // 待办事项、我的工作查询处理
        if (StringUtil.contains(StringUtil.lowerCase(elementId), "todolist")
            || StringUtil.contains(StringUtil.lowerCase(elementId), "mywork")) {
            todoListOrMyWorkSearchLog(request, preDate, postDate, elementId);
            return;
        }
        if (null == page || StringUtil.isEmpty(elementId)) {
            return;
        }
        PageElement pageElement = PageUtil.getPageElement(pageId, elementId);
        if (null == pageElement && !StringUtil.equals(TABLE_RELOAD, elementId)) {
            return;
        }
        ActionLog log = new ActionLog();
        log.setStartTime(preDate);
        log.setEndTime(postDate);
        log.setPageId(pageId);
        log.setElementId(elementId);
        log.setLogDetailInfo(String.format("画面名称：%s", page.getName()));
        if (null != pageElement) {
            elementName = pageElement.name();
            log.setLogInfo(elementName);
        }
        if (StringUtil.equals(TABLE_RELOAD, elementId)) {
            log.setLogInfo("表格数据重新加载");
        }
        else if (ElementTypeEnum.查询.toString().equals(elementName)) {
            log.setLogDetailInfoSearch(getSearchDataFromCookies(request,pageId));
            SearchAction(log, para, returnData);
        }
        else if (ElementTypeEnum.删除.toString().equals(elementName)) {
            if (StringUtil.containsIgnoreCase(url, "check")) {//如果是验证请求，则不记录行为日志 zhangyf 20190130
                return;
            }
            log.setLogInfo("删除数据");
            if (RequestUtil.isAjaxJsonRequest(request)) {
                log.setLogDetailInfo(JsonUtils.object2Json(ThreadCache.REQUEST_BODY.get()));
            }
            else {
                log.setLogDetailInfo(JsonUtils.object2Json(processParam(para)));
            }
        }
        else if (ElementTypeEnum.撤销.toString().equals(elementName)) {
            log.setLogInfo("撤销数据");
            if (RequestUtil.isAjaxJsonRequest(request)) {
                log.setLogDetailInfo(JsonUtils.object2Json(ThreadCache.REQUEST_BODY.get()));
            }
            else {
                log.setLogDetailInfo(JsonUtils.object2Json(processParam(para)));
            }
        }
        else if (ElementTypeEnum.冻结.toString().equals(elementName)
                 || ElementTypeEnum.解冻.toString().equals(elementName)) {
            log.setLogInfo(elementName + "用户");
            log.setLogDetailInfo("userId=" + RequestUtil.getParam("userId"));
        }
        else if (ElementTypeEnum.报送.toString().equals(elementName)
                 || ElementTypeEnum.手工处理.toString().equals(elementName)) {
            BasicMuliAction(log, returnData, elementName);
        }
        else if (ElementTypeEnum.数据检查.toString().equals(elementName)) {
            if (dataStatusCheck(returnData)) {
                log.setLogInfo(elementName + "完成");
            }
        }
        else if (ElementTypeEnum.保存.toString().equals(elementName)
                 || ElementTypeEnum.提交.toString().equals(elementName)) {
            if(null != ThreadCache.DATA_UPDATE_HOLDER.get() && ThreadCache.DATA_UPDATE_HOLDER.get().booleanValue()) {
                String operType = (String)para.get("operType");
                // 工作流判断
                if (StringUtil.isNotEmpty(operType)) {
                    // 新建
                    if ("new".equalsIgnoreCase(operType)) {
                        log.setLogInfo(elementName + "新建数据");
                    }
                    // 修改
                    else if ("modify".equalsIgnoreCase(operType)) {
                        log.setLogInfo(elementName + "修改数据");
                    }
                    log.setLogDetailInfo(getModifyDataFromCookies(request, pageId));
                }
                else {
                    log.setLogInfo(getPageModelName(pageModel, pageId) + GlobalConstant.COLON + elementName);
                    log.setLogDetailInfo(getModifyDataFromCookies(request, pageId));
                }
            }
        }
        // 增加信批系统、报表确认和报表披露按钮处理 zhangyf 20190129 start
        else if ("披露".equals(elementName) || "取消披露".equals(elementName)
            || "确认".equals(elementName) || "取消确认".equals(elementName) || "退回".equals(elementName) ) {
            if (StringUtil.containsIgnoreCase(url, "check")) {//如果是验证请求，则不记录行为日志 zhangyf 20190130
                return;
            }
            if (RequestUtil.isAjaxJsonRequest(request)) {
                log.setLogDetailInfo(JsonUtils.object2Json(ThreadCache.REQUEST_BODY.get()));
            }
            else {
                log.setLogDetailInfo(JsonUtils.object2Json(processParam(para)));
            }
        }
        // 增加信批系统、报表确认和报表披露按钮处理 zhangyf 20190129 end
        // 导出按钮特殊处理
//        if (!StringUtil.equals(elementId, pageId + BTN_EXPORT)) {
            if (RequestUtil.isAjaxJsonRequest(request)) {
                log.setLogDetailInfo(JsonUtils.object2Json(ThreadCache.REQUEST_BODY.get()));
            }
            else {
                log.setLogDetailInfo(JsonUtils.object2Json(processParam(para)));
            }
            insertLogginAction(log);
//        }
    }
    
    /**
     * 待办事项/我的工作查询日志记录
     * 
     * @param request 请求
     * @param preDate 开始时间
     * @param postDate 结束时间
     * @param elementId 按钮ID
     * @see
     * @since 1.0
     */
    private void todoListOrMyWorkSearchLog(HttpServletRequest request, Date preDate, Date postDate,
                                           String elementId) {
        ActionLog log = new ActionLog();
        log.setPageId("Main");
        log.setStartTime(preDate);
        log.setEndTime(postDate);
        log.setElementId(elementId);
        if (StringUtil.contains(StringUtil.lowerCase(elementId), "todolist")) {
            log.setLogInfo(ElementTypeEnum.查询.name() + "待办事项");
            log.setLogDetailInfo("画面名称：待办事项");
        }
        else if (StringUtil.contains(StringUtil.lowerCase(elementId), "mywork")) {
            log.setLogInfo(ElementTypeEnum.查询.name() + "我的工作");
            log.setLogDetailInfo("画面名称：我的工作");
        }
        else {
            return;
        }
        log.setLogDetailInfoSearch(getSearchDataFromCookies(request, StringUtil.EMPTY));
        this.insertLogginAction(log);
    }

    private Map<String, Object> processParam(Map<String, Object> param) {
        if(null != param) {
            param.remove(PAGE_ID);
            param.remove(GlobalConstant.PAGE_ID);
            param.remove(ELEMENT_ID);
            param.remove(GlobalConstant.ELEMENT_ID);
            param.remove(GlobalConstant.DETAIL_PAGE_MODEL_KEY);
            param.remove(WflConstants.PROCESS_TASK_ID);
            param.remove(WflConstants.PROCESS_INSTANCE_ID);
        }
        return param;
    }

    private static Map<Object, String> initOperType() {
        Map<Object, String> operTypeMap = new HashMap<>(6);
        operTypeMap.put("NEW", "新建");
        operTypeMap.put("MODIFY", "修改");
        operTypeMap.put("DELETE", "删除");
        operTypeMap.put("REVOKE", "撤销");
        operTypeMap.put(ProcessType.SAVE, "保存");
        operTypeMap.put(ProcessType.SUBMIT, "提交");
        return operTypeMap;
    }
    
    private String getPageModelName(String pageModel, String pageId) {
        if(!StringUtil.endsWith(pageId, "001")) {
            if(StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, pageModel)) {
                return ElementTypeEnum.新建.name();
            }
            if(StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, pageModel)) {
                return ElementTypeEnum.修改.name();
            }
        }
        return StringUtil.EMPTY;
    }

    /**
     * 批量审批
     * 
     * @param request request
     * @param returnData 方法返回数据
     * @param preDate 处理前时间
     * @param postDate 处理后时间
     */
    private void mutiApproval(HttpServletRequest request, Object returnData, Date preDate,
                              Date postDate) {
        ActionLog log = new ActionLog();
        // 条数
        if (dataStatusCheck(returnData)) {
            log.setPageId("Main");
            log.setLogInfo(ElementTypeEnum.批量审批.name() + "完成");
            log.setLogDetailInfo(JsonUtils.object2Json(ThreadCache.REQUEST_BODY.get()));
            this.insertLogginAction(log);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void LoggingLogInfo(String pageId, String logInfo, String host) {
        ActionLog log = new ActionLog();

        if (StringUtil.isNotEmpty(pageId)) {
            com.joyintech.framework.base.intf.Page page = PageUtil.getPage(pageId);
            if (null != page) {
                log.setLogDetailInfo(page.getName());
            }
            log.setPageId(pageId);
        }
        if (StringUtil.isNotEmpty(host)) {
            log.setClientIp(host);
        }
        log.setLogInfo(logInfo);
        setRequestParamToLog(log);
        insertLogginAction(log);
    }

    private void setRequestParamToLog(ActionLog log) {
        HttpServletRequest request = RequestUtil.getCurrentRequest();
        if (null != request) {
            Map<String, Object> requestParam = RequestUtil.getParameters(request, false);
            if (null != requestParam && !requestParam.isEmpty()) {
                log.setLogDetailInfo(JsonUtils.object2Json(requestParam));
            }
        }
    }

    /**
     * 日志入库
     */
    private void insertLogginAction(ActionLog log) {
        if (null != log && null != log.getLogInfo()) {
            DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
            mapper.insertMessageLog(log);
            DynamicDataSourceHolder.clearDataSource();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void LoggingImpExpInfo(long size, String impExpInfo, Date preDate, Date endDate) {
        ActionLog log = new ActionLog();
        Map<String, Object> param = RequestUtil.getParameters(RequestUtil.getCurrentRequest());
        String pageId = StringUtil.getOrElse((String)param.get(PAGE_ID), (String)param.get(GlobalConstant.PAGE_ID));
        String elementId = StringUtil.getOrElse((String)param.get(ELEMENT_ID), (String)param.get(GlobalConstant.ELEMENT_ID));
        if(StringUtil.isNoneEmpty(pageId)) {
            log.setPageId(pageId);
            com.joyintech.framework.base.intf.Page page = PageUtil.getPage(pageId);
            if(page != null) {
                log.setLogDetailInfo(page.getName());
            }
        }
        if(StringUtil.isNoneEmpty(elementId)) {
            log.setElementId(elementId);
        }
        log.setLogInfo(impExpInfo + size + "条数据");
        log.setStartTime(preDate);
        log.setEndTime(endDate);
        log.setLogDetailInfoSearch(this.combineSearchInfo(RequestUtil.getCurrentRequest()));
        insertLogginAction(log);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void LoggingAnnotationInfo(HttpServletRequest request, Date preDate, Date postDate,
                                      Object returnData, LogMethod logMethod) {
        ActionLog log = new ActionLog();
        log.setStartTime(preDate);
        log.setEndTime(postDate);
        String pageId = RequestUtil.getParam(PAGE_ID);
        String elementId = RequestUtil.getParam(ELEMENT_ID);
        if (null != logMethod && StringUtil.isNotEmpty(logMethod.pageId())) {
            pageId = logMethod.pageId();
        }
        if (null != logMethod && StringUtil.isNotEmpty(logMethod.elementId())) {
            elementId = logMethod.elementId();
        }
        log.setPageId(pageId);
        // init方法上mapping不是init时
        if(logMethod!=null)
        {
            if (INIT.equalsIgnoreCase(logMethod.action())) {
                log.setLogInfo("访问页面");
                insertLogginAction(log);
                return;
            }
        }

        // 其他特殊按钮
        if (StringUtil.isNotEmpty(pageId) && StringUtil.isNotEmpty(elementId)) {
            PageElement pageElement = PageUtil.getPageElement(pageId, elementId);
            log.setElementId(elementId);
            if (null != pageElement && null != returnData) {
                if (AjaxResult.class.getSimpleName().equals(
                    returnData.getClass().getSimpleName())) {
                    if (HttpStatus.OK.value() == ((AjaxResult)returnData).getStatuCode()) {
                        log.setLogInfo(pageElement.name() + "完成");
                        insertLogginAction(log);
                    }
                }
            }
        }
    }

    /**
     * 工作流中分析
     * 
     * @param request request
     * @param para request参数
     * @param returnData control返回参数
     * @param postDate 处理后时间
     * @param preDate 处理前时间
     * @throws WorkFlowException 异常
     */
    @SuppressWarnings("unchecked")
    private void workFlowAction(HttpServletRequest request, Map<String, Object> para,
                                Object returnData, Date preDate, Date postDate)
        throws WorkFlowException {
        List<String> workFlow = Arrays.asList("doModify", "doSubmit", "doCancel", "doBack", "doRevoke",
            "doAbandon");

        String workFlowUrl = RequestUtil.getRequestPath();
        if (workFlowUrl.contains("details")) {
            return;
        }

        ActionLog log = new ActionLog();
        log.setStartTime(preDate);
        log.setEndTime(postDate);
        log.setPageId(RequestUtil.getParam(PAGE_ID));
        // 工作流实例ID
        String processInstanceId = RequestUtil.getParam(WflConstants.PROCESS_INSTANCE_ID);
        // 获取流程参数
        FlowEntity flowEntity = null;
        if (StringUtil.isNotEmpty(processInstanceId)) {
            flowEntity = this.processService.getHisEntity(processInstanceId);
        }

        if (workFlowUrl.contains(workFlow.get(0)) || workFlowUrl.contains(workFlow.get(1))) {

            if(flowEntity!=null)
            {
                log.setLogInfo(
                        flowEntity.getCurNodeName() + "中" + operTypeMap.get(flowEntity.getProcessType())
                                + operTypeMap.get(flowEntity.getOperType().name().toUpperCase())
                                + "数据");
            }


            // 审批不记录入力值
            if (flowEntity != null && HANDLE.equals(flowEntity.getCurNodeName())) {
                StringBuilder logDetailInfo = new StringBuilder();
                // 业务数据的JSON字符串
                String businessInfo = StringUtil.defaultString((String)para.get("businessInfo"));
                // 将JSON字符串转换为Object
                Object businessObject = JsonUtils.jsonToObject(JsonUtils.toJsonNode(businessInfo));
                Map<String, Object> businessParams = null;
                if (businessObject instanceof Map) {
                    businessParams = (Map<String, Object>)businessObject;
                }
                if (null != businessParams) {
                    businessParams.forEach((k, v) -> {
                        if (!WORK_PARA.contains(k) && StringUtil.isNotEmpty(v)) {
                            logDetailInfo.append(k + GlobalConstant.COLON + v + GlobalConstant.SEMICOLON);
                        }
                    });
                }
                log.setLogDetailInfo(logDetailInfo.toString());
            }
            else {
                log.setLogDetailInfo(getModifyDataFromCookies(request, log.getPageId()));
            }

        }
        else if (workFlowUrl.contains(workFlow.get(2))) {
            log.setLogInfo("作废任务");
        }
        else if (flowEntity!=null && (workFlowUrl.contains(workFlow.get(3)))) {
            log.setLogInfo(
                "退回" + operTypeMap.get(flowEntity.getOperType().name().toUpperCase()) + "流程");
            log.setLogDetailInfo(RequestUtil.getParam("rejectReason"));
        }
        // 放弃任务
        else if (workFlowUrl.contains(workFlow.get(4))) {
            String taskId = RequestUtil.getParam("taskId");
            Map<String, Object> taskMap = WorkflowContext.getTaskService().getVariables(taskId);
            if (null != taskMap && taskMap.containsKey("entity")) {
                flowEntity = (FlowEntity)taskMap.get("entity");
                log.setLogInfo("放弃任务");
            }

        }
        this.insertLogginAction(log);
    }

    /**
     * 查询按钮分析
     * 
     * @param log log
     * @param parameter request param
     * @param returnData object
     */
    private void SearchAction(ActionLog log, Map<String, Object> parameter, Object returnData) {

        // 查询条数
        long size = getSizeByObjectData(returnData);
        log.setLogInfo("查询" + size + "条数据");
//        log.setLogDetailInfoSearch(this.combineSearchInfo(RequestUtil.getCurrentRequest()));
    }

    /**
     * 批量分析
     * 
     * @param log log
     * @param returnData Object
     * @param actionString 行为
     */
    private void BasicMuliAction(ActionLog log, Object returnData, String actionString) {
        // 条数
        long size = getSizeByObjectData(returnData);
        log.setLogInfo(actionString + size + "条数据");
        log.setLogDetailInfo(JsonUtils.object2Json(ThreadCache.REQUEST_BODY.get()));
    }

    /**
     * 取object对象size
     * 
     * @param data object
     * @return size
     */
    @SuppressWarnings("rawtypes")
    private long getSizeByObjectData(Object data) {
        if (null == data) {
            return 0;
        }
        long size = 0;
        String className = data.getClass().getSimpleName();
        if (Page.class.getSimpleName().equalsIgnoreCase(className)) {
            size = ((Page)data).getRecords();
        }
        else if (List.class.getSimpleName().equalsIgnoreCase(className)) {
            size = ((List)data).size();
        }
        else if (Map.class.getSimpleName().equalsIgnoreCase(className)) {
            size = ((Map)data).size();
        }
        else if (AjaxResult.class.getSimpleName().equalsIgnoreCase(className)) {
            Object ajaxData = ((AjaxResult)data).getData();
            if(null != ajaxData) {
                String ajaxDataClass = ajaxData.getClass().getSimpleName();
                if (List.class.getSimpleName().equalsIgnoreCase(ajaxDataClass)) {
                    size = ((List)data).size();
                }
                else if (Map.class.getSimpleName().equalsIgnoreCase(ajaxDataClass)) {
                    size = ((Map)data).size();
                }
                else if (Page.class.getSimpleName().equalsIgnoreCase(ajaxDataClass)) {
                    size = ((Page)data).getRecords();
                }
            }
        }
        return size;
    }

    /**
     * 是否正常返回数据check
     * 
     * @param returnData returnData
     * @return 是否正常
     */
    private boolean dataStatusCheck(Object returnData) {
        if (StringUtil.isNotEmpty(returnData)
            && AjaxResult.class.getSimpleName().equals(returnData.getClass().getSimpleName())) {
            AjaxResult result = (AjaxResult)returnData;
            return HttpStatus.OK.value() == result.getStatuCode();
        }
        return false;
    }

    /**
     * 组合查询条件（查询，导出时用）
     * 
     * @param request request
     * @return 入力条件
     */
    private String combineSearchInfo(HttpServletRequest request) {
        // 请求参数
        Map<String, Object> parameter = RequestUtil.getParameters(request);
        // 查询条件
        StringBuilder searchDetailInfo = new StringBuilder();
        if (null != parameter) {
            parameter.forEach((k, v) -> {
                if (StringUtil.isNotEmpty(v) && !SEARCH_PARA.contains(k)) {
                    searchDetailInfo.append(
                        k + GlobalConstant.COMMA + v + GlobalConstant.SEMICOLON);
                }
            });
        }

        return searchDetailInfo.toString();
    }
//
//    /**
//     * 获取form数据
//     * 
//     * @param request request
//     * @param para request参数
//     * @return form数据
//     */
//    private String getFormData(HttpServletRequest request, Map<String, Object> para) {
//        StringBuilder logDetailInfo = new StringBuilder();
//        if (RequestUtil.isAjaxJsonRequest(request)) {
//            logDetailInfo.append(JsonUtils.object2Json(RequestBodyAdvice.REQUEST_BODY.get()));
//        }
//        else {
//            para.forEach((k, v) -> {
//                if (!WORK_PARA.contains(k) && StringUtil.isNotEmpty(v)) {
//                    logDetailInfo.append("入力项" + k + "输入了" + v + GlobalConstant.SEMICOLON);
//                }
//            });
//        }
//        return logDetailInfo.toString();
//    }

    /**
     * 从cookies中获取更改数据
     * 
     * @param request request
     * @return
     */
    private String getModifyDataFromCookies(HttpServletRequest request, String pageId) {
        return getDataFromCookies(request,pageId,MODIFYDATA);
    }

    private String getDataFromCookies(HttpServletRequest request, String pageId, String type) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            Optional<Cookie> data = Arrays.asList(cookies).stream().filter(
                x -> StringUtil.equalsIgnoreCase(pageId + type, x.getName())).findFirst();
            if (data.isPresent()) {
                try {
                    return URLDecoder.decode(data.get().getValue(), "utf-8");
                }
                catch (UnsupportedEncodingException e) {
                }
            }
        }
        return StringUtil.EMPTY;
    }
    
    /**
     * 从cookies中获取更改数据
     * 
     * @param request request
     * @return
     */
    private String getSearchDataFromCookies(HttpServletRequest request, String pageId) {
        return getDataFromCookies(request,pageId,SEARCHDATA);
    }

    /**
     * 获取 mapper
     * 
     * @return mapper.
     */
    public ActionLogMapper getMapper() {
        return mapper;
    }

    /**
     * 设置 mapper
     * 
     * @param mapper mapper
     */
    public void setMapper(ActionLogMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取 processService
     * 
     * @return processService.
     */
    public ProcessService getProcessService() {
        return processService;
    }

    /**
     * 设置 processService
     * 
     * @param processService processService
     */
    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }

}
