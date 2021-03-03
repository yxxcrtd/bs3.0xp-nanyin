/**
 *
 */
package com.joyintech.tams.svreport.chb.common;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 提供数据处理的工具类
 *
 * @author dubaojun
 * @since 2020-11-06
 *
 */
public abstract class SvrDataStatusUtil extends AutoSendCBBaseService {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SvrDataStatusUtil.class);

    /**
     * 功能:中债报送系统报表登记批统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月8日
     * @param executeDate 执行日期
     * @param prodList 产品list
     * @param assetList 资产list
     * @throws BusinessException 业务异常
     * void
     */
    public abstract void execute(Date executeDate, List<String> prodList, List<String> assetList, Map<String,List<String>> param) throws BusinessException;

    /**
     * 需要插入的数据KEY
     */
    public static final String INSERT_DATA = "insert";

    /**
     * 需要删除的数据KEY
     */
    public static final String DELETE_DATA = "delete";

    /**
     * 数据状态对应的字段名称
     */
    private static final String DATA_STATUS_FIELD_NAME = "status";

    /**
     * 不需要比较的共通字段
     */
    private static List<String> EXCLUDE_COMMON_METHODS = null;

    static {
        EXCLUDE_COMMON_METHODS = new ArrayList<>();
        EXCLUDE_COMMON_METHODS.add("setRegistSeqno");
        EXCLUDE_COMMON_METHODS.add("setSubmitDeadline");
        EXCLUDE_COMMON_METHODS.add("setChbSubmitDeadline");
        EXCLUDE_COMMON_METHODS.add("setSubmitTime");
        EXCLUDE_COMMON_METHODS.add("setStatusDate");
        EXCLUDE_COMMON_METHODS.add("setStatus");
        EXCLUDE_COMMON_METHODS.add("setCreateUser");
        EXCLUDE_COMMON_METHODS.add("setCreateTime");
        EXCLUDE_COMMON_METHODS.add("setUpdateUser");
        EXCLUDE_COMMON_METHODS.add("setUpdateTime");
        EXCLUDE_COMMON_METHODS.add("setOrgCode");
        EXCLUDE_COMMON_METHODS.add("setDeptCode");
        EXCLUDE_COMMON_METHODS.add("setSendStatus");
        EXCLUDE_COMMON_METHODS.add("setSumitSeq");
        EXCLUDE_COMMON_METHODS.add("setSubmitFeedbackStatus");
        EXCLUDE_COMMON_METHODS.add("setCreateDept");
        EXCLUDE_COMMON_METHODS.add("setDataSource");
        EXCLUDE_COMMON_METHODS.add("setDeleteRes");
    }

    public abstract <T> boolean compareData(T info, T sub);

    /**
     * 处理数据状态和报送状态等字段，根据新进业务数据和登记表数据处理需要入库的数据。
     * 处理结果的KEY参考全局变量。
     *
     * @param info 业务数据
     * @param sub 登记数据
     * @param excludeCompareFields 除了共通字段意外需要忽略比较的字段
     * @return 处理后的结果（不会返回null）
     */
    public <T> Map<String, T> handleDataStatus(T info, T sub, String... excludeCompareFields) {
        Map<String, T> resultMap = new HashMap<>();
        if(info == null) {
            return resultMap;
        }
        // 获取业务数据状态
        Field infoStatusField = ReflectionUtils.findField(info.getClass(), DATA_STATUS_FIELD_NAME);
        infoStatusField.setAccessible(true);
        String infoStatus = (String) ReflectionUtils.getField(infoStatusField, info);
        // 不存在已登记的数据时。
        if(sub == null) {
            if(DictCodeListExt1.维护数据状态_删除.getValue().equals(infoStatus)) {
                resultMap.put(DELETE_DATA, info);
            }
            else {
                ReflectionUtils.setField(infoStatusField, info, DictCodeListExt2.报送数据状态_新建.getValue());
                resultMap.put(DELETE_DATA, info);
                resultMap.put(INSERT_DATA, info);
            }
            return resultMap;
        }
        // 存在已登记的数据时
        // 获取登记数据状态
        Field subStatusField = ReflectionUtils.findField(sub.getClass(), DATA_STATUS_FIELD_NAME);
        subStatusField.setAccessible(true);
        String subStatus = (String) ReflectionUtils.getField(subStatusField, sub);
        // 当业务数据的数据状态为删除时
        if (DictCodeListExt1.维护数据状态_删除.getValue().equals(infoStatus)) {
            // 否则生成删除未报送
            if (DictCodeListExt2.报送数据状态_新建.getValue().equals(subStatus) || DictCodeListExt2.报送数据状态_修改.getValue().equals(subStatus)) {
                ReflectionUtils.setField(infoStatusField, info, DictCodeListExt2.报送数据状态_删除.getValue());
                resultMap.put(DELETE_DATA, info);
                resultMap.put(INSERT_DATA, info);
            }
            return resultMap;
        }
        else if (DictCodeListExt1.维护数据状态_修改.getValue().equals(infoStatus) || DictCodeListExt1.维护数据状态_有效.getValue().equals(infoStatus)){
            if (compareData(info, sub)){
                if (DictCodeListExt2.报送数据状态_新建.getValue().equals(subStatus) || DictCodeListExt2.报送数据状态_修改.getValue().equals(subStatus)){
                    ReflectionUtils.setField(infoStatusField, info, DictCodeListExt2.报送数据状态_修改.getValue());
                    resultMap.put(DELETE_DATA, info);
                    resultMap.put(INSERT_DATA, info);
                }
                else if (DictCodeListExt2.报送数据状态_删除.getValue().equals(subStatus)){
                    ReflectionUtils.setField(infoStatusField, info, DictCodeListExt2.报送数据状态_新建.getValue());
                    resultMap.put(DELETE_DATA, info);
                    resultMap.put(INSERT_DATA, info);
                }
            }
            else{
                // 已登记数据是删除状态，则生成新建未报送
                if (DictCodeListExt2.报送数据状态_删除.getValue().equals(subStatus)){
                    ReflectionUtils.setField(infoStatusField, info, DictCodeListExt2.报送数据状态_新建.getValue());
                    resultMap.put(DELETE_DATA, info);
                    resultMap.put(INSERT_DATA, info);
                }else{
                    resultMap.put(DELETE_DATA, info);
                }
            }
            return resultMap;
        }
        logger.info("数据维护状态有误，应为：D|A|M!");
        return resultMap;
    }

    /**
     * 判断两个对象是否有变更。
     *
     * @param info 业务数据
     * @param sub 登记数据
     * @param excludeCompareFields 除了共通字段意外需要忽略比较的字段
     * @return 是否变更
     */
    public <T> boolean isChange(T info, T sub, String... excludeCompareFields) {
        T cloneInfo = cloneBean(info);
        T cloneSub = cloneBean(sub);
        if(cloneInfo == null || cloneSub == null) {
            if(cloneInfo == null && cloneSub == null) {
                return false;
            }
            else {
                return true;
            }
        }
        // 解析不需要比较的字段，并添加的
        for (String excludeCompareField : excludeCompareFields) {
            EXCLUDE_COMMON_METHODS.add("set" + excludeCompareField.substring(0, 1).toUpperCase() + excludeCompareField.substring(1));
        }
        // 将不需要比较的字段设置为null
        Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(info.getClass());
        for (Method method : allDeclaredMethods) {
            String methodName = method.getName();
            if(EXCLUDE_COMMON_METHODS.contains(methodName)) {
                try {
                    String paramType = method.getParameterTypes()[0].getName();
                    if (StringUtil.equals(paramType, "java.lang.String")){
                        ReflectionUtils.invokeMethod(method, cloneInfo, "");
                        ReflectionUtils.invokeMethod(method, cloneSub, "");
                    }
                    else if (StringUtil.equals(paramType, "java.util.Date")){
                        ReflectionUtils.invokeMethod(method, cloneInfo, DateUtil.parse("2099-12-30"));
                        ReflectionUtils.invokeMethod(method, cloneSub, DateUtil.parse("2099-12-30"));
                    }
                } catch (Exception e) {
                    logger.warn("方法【{}】在类【{}】中不存在！", methodName, cloneInfo.getClass().getName());
                }
            }
        }
        // 比较两个数据
        String infoStr = JSON.toJSONString(cloneInfo);
        String subStr = JSON.toJSONString(cloneSub);
        if(!infoStr.equals(subStr)) {
            logger.info("业务表对应数据为：{}", infoStr);
            logger.info("登记表对应数据为：{}", subStr);
            return true;
        }
        return false;
    }

    /**
     * 深度克隆Bean
     *
     * @param t 被克隆对象
     * @return 克隆结果
     */
    @SuppressWarnings("unchecked")
    private static <T> T cloneBean(T t) {
        T result = null;
        try {
            result = (T) BeanUtil.cloneBean(t);
        } catch (IllegalAccessException e) {
            logger.error("获取不到指定类型，{}",e.getMessage(),e);
        } catch (InstantiationException e) {
            logger.error("获取不到指定类型，{}",e.getMessage(),e);
        } catch (InvocationTargetException e) {
            logger.error("获取不到指定类型，{}",e.getMessage(),e);
        } catch (NoSuchMethodException e) {
            logger.error("获取不到指定方法，{}",e.getMessage(),e);
        }
        return result;
    }
}
