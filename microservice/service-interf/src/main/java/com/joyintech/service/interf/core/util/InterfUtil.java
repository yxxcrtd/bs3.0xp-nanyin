/*
 * 文件名：InterUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.dc.eai.data.Array;
import com.dc.eai.data.CompositeData;
import com.dc.eai.data.Field;
import com.dc.eai.data.FieldAttr;
import com.dc.eai.data.FieldType;
import com.joyintech.tams.common.base.entity.InterfFileLog;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;
import com.joyintech.service.interf.core.constant.EsbHeadEnum;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.pingan.bfts.bftscore.client.FileAccessConfig;

/**
 * 接口工具类
 * 
 * @author zhangrui1
 * @version 1.0 2018年9月26日
 * @see InterfUtil
 * @since 1.0
 */
public final class InterfUtil {

    /**
     * 私有构造
     */
    private InterfUtil() {

    }

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InterfUtil.class);

    /**
     * 将本地类型转换成ESB类型
     * 
     * @param type 本地类型
     * @return FieldType ESB类型
     * @see
     * @since 1.0
     */
    public static FieldType changeStringToFieldType(String type) {
        if (EsbFieldType.STRING.equals(type)) {
            return FieldType.FIELD_STRING;
        }
        else if (EsbFieldType.DOUBLE.equals(type)) {
            return FieldType.FIELD_DOUBLE;
        }
        else if (EsbFieldType.INT.equals(type)) {
            return FieldType.FIELD_INT;
        }
        return FieldType.FIELD_STRING;
    }

    /**
     * Entity转化成报文体
     * 
     * @param entity Entity实例
     * @return 报文体
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static CompositeData changeEntityToESBData(Object entity)
        throws Exception {
        // 源数据为空时返回空
        if (entity == null) {
            return null;
        }
        // 取得源数据类型和方法列表
        Class<?> clz = entity.getClass();
        Method[] fList = clz.getDeclaredMethods();
        // 初始化返回结果
        CompositeData bodyData = new CompositeData();
        for (Method md : fList) {
            EsbName nm = md.getAnnotation(EsbName.class);
            // 非get方法或者未注解方法跳过
            if (!md.getName().startsWith("get") || nm == null) {
                continue;
            }
            String name = nm.value();
            EsbFieldType type = md.getAnnotation(EsbFieldType.class);
            // 未设置类型注解方法抛出异常
            if (type == null) {
                LOGGER.error("{}没有设置类型属性@FieldTypeS", name);
                throw new InterfException(md.getName() + "未设置@FieldTypeS注解!!!");
            }
            // 解析数组
            if (EsbFieldType.ARRAY.equals(type.value())) {
                bodyData.addArray(name, InterfUtil.getArray(md, entity));
            }
            // 解析域
            else {
                bodyData.addField(name, InterfUtil.getField(md, entity));
            }
        }
        return bodyData;
    }

    /**
     * 报文体转化成Entity
     * 
     * @param data 报文体
     * @param clz Entity类型
     * @param <T> 返回类型
     * @return Entity实例
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static <T> T parseESBDataToEntity(CompositeData data, Class<T> clz)
        throws Exception {
        // 数据为空或者受体类不存在时返回空
        if (data == null || data.size() == 0 || clz == null) {
            return null;
        }
        // 实例化受体类
        T entity = clz.newInstance();
        // 受体类的方法列表
        Method[] fList = clz.getDeclaredMethods();
        for (Method md : fList) {
            // 受体类的方法注解
            EsbName nm = md.getAnnotation(EsbName.class);
            // 受体类是否必须注解
            Required rqd = md.getAnnotation(Required.class);
            // 受体类的方法不是set方法或者没有注解时跳过
            if (!StringUtil.startsWith(md.getName(), "set") || nm == null) {
                continue;
            }
            // 报文名取得
            String esbName = nm.value();
            EsbFieldType type = md.getAnnotation(EsbFieldType.class);
            String paramType = md.getGenericParameterTypes()[0].getTypeName();
            // 如果是Array类型时
            if (checkArray(type, paramType, md.getName())) {
                // 解析List元素类型
                String clsName = StringUtil.replaceAll(paramType, List.class.getName(),
                    StringUtil.EMPTY);
                clsName = StringUtil.substringBetween(clsName, "<", ">");
                Class<?> mdClz = Class.forName(clsName);
                Array arr = data.getArray(esbName);
                // 设置List
                md.invoke(entity, getArrayList(arr, mdClz));
            }
            // 如果不是Array类型时
            else if (checkField(data, esbName, rqd)) {
                // 设置值
                invoke(md, entity, data.getField(esbName));
            }
        }
        return entity;
    }

    /**
     * 校验对象是否是Array类型
     * 
     * @param type 类型
     * @param paramType 受体属性类型
     * @param mdName 方法名
     * @return 结果：true（正常是Array）、false（其他）
     * @throws InterfException 异常
     * @see
     * @since 1.0
     */
    private static boolean checkArray(EsbFieldType type, String paramType, String mdName)
        throws InterfException {
        // 没有设置类型注解时抛出异常
        if (type == null) {
            LOGGER.error("{}没有设置类型属性@FieldTypeS", mdName);
            throw new InterfException(mdName + "未设置@FieldTypeS注解!!!");
        }
        // 如果是Array类型时
        if (EsbFieldType.ARRAY.equals(type.value())
            && !StringUtil.startsWith(paramType, List.class.getName())) {
            // 如果定义的受体类属性不是List类型时抛出异常
            throw new InterfException(mdName + "参数要定义成java.util.List类型!!!");
        }
        else if (EsbFieldType.ARRAY.equals(type.value())) {
            return true;
        }
        return false;
    }

    /**
     * 校验报文对象状态
     * 
     * @param data 校验报文对象
     * @param esbName 域名称
     * @param rqd 必须标识
     * @return 结果：true（正常）、false（其他）
     * @throws InterfException 报文值异常
     * @see
     * @since 1.0
     */
    private static boolean checkField(CompositeData data, String esbName, Required rqd)
        throws InterfException {
        Field field = data.getField(esbName);
        if (field == null) {
            LOGGER.info("{}标签对应的属性不存在", esbName);
        }
        else if (field.getValue() == null && rqd != null) {
            throw new InterfException(esbName + "不能为空!");
        }
        else {
            return true;
        }
        return false;
    }

    /**
     * 将报文Array转成List
     * 
     * @param arr 报文Array
     * @param mdClz List元素类型
     * @return List<Object>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    private static List<Object> getArrayList(Array arr, Class<?> mdClz)
        throws Exception {
        List<Object> eList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++ ) {
            Object e = parseESBDataToEntity(arr.getStruct(i), mdClz);
            eList.add(e);
        }
        return eList;
    }

    /**
     * 将ESB类型的值转化为本地类型的值
     * 
     * @param md 方法
     * @param entity 实例
     * @param field 参数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    private static void invoke(Method md, Object entity, Field field)
        throws Exception {
        Class<?>[] clzs = md.getParameterTypes();
        if (clzs == null || clzs.length == 0) {
            md.invoke(entity);
            return;
        }
        FieldType type = field.getFieldType();
        Object obj = field.getValue();
        Class<?> clz = clzs[0];
        if (clz.isInstance(obj)) {
            md.invoke(entity, obj);
        }
        else if (clz == BigDecimal.class) {
            if (FieldType.FIELD_DOUBLE.equals(type)) {
                md.invoke(entity, BigDecimal.valueOf((Double)obj));
            }
            else if (FieldType.FIELD_FLOAT.equals(type)) {
                md.invoke(entity, BigDecimal.valueOf((Float)obj));
            }
            else if (FieldType.FIELD_INT.equals(type)) {
                md.invoke(entity, BigDecimal.valueOf((Integer)obj));
            }
            else if (FieldType.FIELD_LONG.equals(type)) {
                md.invoke(entity, BigDecimal.valueOf((Long)obj));
            }
            else if (FieldType.FIELD_SHORT.equals(type)) {
                md.invoke(entity, BigDecimal.valueOf((Short)obj));
            }
            else if (FieldType.FIELD_INT24.equals(type)) {
                md.invoke(entity, new BigDecimal((BigInteger)obj));
            }
            else if (FieldType.FIELD_STRING.equals(type)) {
                md.invoke(entity, new BigDecimal((String)obj));
            }
        }
        else if (clz == Date.class && FieldType.FIELD_STRING.equals(type)) {
            md.invoke(entity, DateUtil.parse((String)obj));
        }
    }

    /**
     * 获取实例的报文数组结构
     * 
     * @param md 实例的方法
     * @param entity 实例对象
     * @return Array 报文数组结构
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static Array getArray(Method md, Object entity)
        throws Exception {
        if (!md.getName().startsWith("get")) {
            return null;
        }
        EsbFieldType tp = md.getAnnotation(EsbFieldType.class);
        if (tp == null || !EsbFieldType.ARRAY.equals(tp.value())) {
            return null;
        }
        Type gType = md.getGenericReturnType();
        Array arr = new Array();
        if (StringUtil.startsWith(gType.toString(), List.class.getName())) {
            List<Object> fdList = (List<Object>)md.invoke(entity);
            if (StringUtil.isEmptyList(fdList)) {
                return arr;
            }
            for (Object obj : fdList) {
                arr.addStruct(changeEntityToESBData(obj));
            }
        }
        else if (md.getReturnType().isArray()) {
            Object[] fdArr = (Object[])md.invoke(entity);
            if (fdArr == null || fdArr.length == 0) {
                return arr;
            }
            for (Object obj : fdArr) {
                arr.addStruct(changeEntityToESBData(obj));
            }
        }
        return arr;
    }

    /**
     * 获取实例的报文Field结构
     * 
     * @param md 实例方法
     * @param entity 实例对象
     * @return Field 报文Field结构
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static Field getField(java.lang.reflect.Method md, Object entity)
        throws Exception {
        EsbFieldType tp = md.getAnnotation(EsbFieldType.class);
        if (tp == null || EsbFieldType.ARRAY.equals(tp.value())) {
            return null;
        }
        EsbLength len = md.getAnnotation(EsbLength.class);
        EsbNumber nmA = md.getAnnotation(EsbNumber.class);
        Required rqd = md.getAnnotation(Required.class);
        EsbName nm = md.getAnnotation(EsbName.class);
        String type = tp.value();
        String name = nm.value();
        if (rqd != null && !StringUtil.isNotEmpty(md.invoke(entity))) {
            LOGGER.error("{}没有设值", name);
            throw new InterfException(name + "注解定义的字段不能为空!!!");
        }
        if (EsbFieldType.STRING.equals(type)) {
            if (len == null) {
                LOGGER.error("{}没有设置长度属性@Length", name);
                throw new InterfException(md.getName() + "未设置@Length注解!!!");
            }
            return newStringField(len.value(), (String)md.invoke(entity));
        }
        else if (EsbFieldType.DOUBLE.equals(type)) {
            if (nmA == null) {
                LOGGER.error("{}没有设置精度属性@NumberA", name);
                throw new InterfException(md.getName() + "未设置@NumberA注解!!!");
            }
            return newDoubleField(nmA.length(), nmA.scale(), md.invoke(entity));
        }
        else if (EsbFieldType.INT.equals(type)) {
            if (nmA == null) {
                LOGGER.error("{}没有设置精度属性@NumberA", name);
                throw new InterfException(md.getName() + "未设置@NumberA注解!!!");
            }
            return newIntField(nmA.length(), nmA.scale(), md.invoke(entity));
        }
        return null;
    }

    /**
     * 创建字符型报文结构
     * 
     * @param len 长度
     * @param value 值
     * @return Field 字符型报文Field结构
     * @see
     * @since 1.0
     */
    public static Field newStringField(int len, String value) {
        Field field = new Field(new FieldAttr(FieldType.FIELD_STRING, len, 0));
        field.setValue(value);
        return field;
    }

    /**
     * 创建金额型报文结构
     * 
     * @param len 长度
     * @param scale 精度
     * @param value 值
     * @return Field 金额型报文Field结构
     * @see
     * @since 1.0
     */
    public static Field newDoubleField(int len, int scale, Object value) {
        Field field = new Field(new FieldAttr(FieldType.FIELD_DOUBLE, len, scale));
        if (value instanceof BigDecimal) {
            field.setValue(((BigDecimal)value).doubleValue());
        }
        else if (value instanceof Short) {
            field.setValue(((Short)value).doubleValue());
        }
        else if (value instanceof Long) {
            field.setValue(((Long)value).doubleValue());
        }
        else if (value instanceof Float) {
            field.setValue(((Float)value).doubleValue());
        }
        else {
            field.setValue((Double)value);
        }
        return field;
    }

    /**
     * 创建数字型报文结构
     * 
     * @param len 长度
     * @param scale 精度
     * @param value 值
     * @return Field 数字型报文Field结构
     * @see
     * @since 1.0
     */
    public static Field newIntField(int len, int scale, Object value) {
        Field field = new Field(new FieldAttr(FieldType.FIELD_INT, len, scale));
        if (value instanceof BigDecimal) {
            field.setValue(((BigDecimal)value).intValue());
        }
        else if (value instanceof Short) {
            field.setValue(((Short)value).intValue());
        }
        else if (value instanceof Long) {
            field.setValue(((Long)value).intValue());
        }
        else if (value instanceof Float) {
            field.setValue(((Float)value).intValue());
        }
        else {
            field.setValue((Integer)value);
        }
        return field;
    }

    /**
     * 取得 i18n配置文件中的消息
     * 
     * @param key 消息名
     * @return 消息内容
     * @see
     * @since 1.0
     */
    public static String getMessage(String key) {
        return SpringContextUtil.getTextValue(key);
    }

    /**
     * 创建下载文件夹
     * 
     * @param path 文件夹路径
     * @return 结果
     * @see
     * @since 1.0
     */
    public static boolean createFolder(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            try {
                if (file.mkdirs()) {
                    LOGGER.info("文件夹创建成功！路径：{}", path);
                    return true;
                }
                else {
                    LOGGER.info("文件夹创建失败！路径：{}", path);
                    return false;
                }
            }
            catch (Exception e) {
                LOGGER.error("文件夹创建异常！", e);
                return false;
            }
        }
        LOGGER.info("文件夹已存在！路径：{}", path);
        return true;
    }

    /**
     * 设置Field
     * 
     * @param data 数据
     * @param esbHead 字段定义
     * @param value 值
     * @see
     * @since 1.0
     */
    public static void addField(CompositeData data, EsbHeadEnum esbHead, String value) {
        data.addField(esbHead.colName(), InterfUtil.newStringField(esbHead.colLength(), value));
    }

    /**
     * 给文件夹路径结尾加上分隔符
     * 
     * @param path 文件夹路径
     * @return 结尾带有分隔的符文件夹路径
     * @see
     * @since 1.0
     */
    public static String pathEndAsSeparator(String path) {
        if (!path.endsWith("\\") && !path.endsWith("/")) {
            return path + "/";
        }
        return path;
    }

    /**
     * 取得金融产品代码
     * 
     * @param type 金融产品类型
     * @param marketId 市场代码
     * @param chl 通道代码
     * @return 金融产品代码
     * @see
     * @since 1.0
     */
    public static String getFinProdId(String type, String marketId, String chl) {
        StringBuilder sb = new StringBuilder(type);
        sb.append(GlobalConstant.UNDER_LINE);
        sb.append(marketId);
        sb.append(chl);
        return sb.length() > 32 ? sb.substring(0, 32) : sb.toString();
    }

    /**
     * 修改文件夹权限
     * 
     * @param dirFile 文件夹
     * @throws IOException 异常
     * @see
     * @since 1.0
     */
    public static void changeFolderPermission(File dirFile)
        throws IOException {
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        try {
            Path path = Paths.get(dirFile.getAbsolutePath());
            Files.setPosixFilePermissions(path, perms);
        }
        catch (Exception e) {
            LOGGER.error("Change folder " + dirFile.getAbsolutePath() + " permission failed.", e);
        }
    }

    /**
     * 取得连接参数
     * 
     * @param interfCahe 系统参数列表
     * @param type 连接类别（BFTS，ODS等）
     * @return 连接参数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static InterfFileLog getInterfFileLog(Map<String, Object> interfCahe, String type) {
        InterfFileLog entity = new InterfFileLog();
        int index = StringUtil.ordinalIndexOf(type + InterfConstant.UNDERLINE,
            InterfConstant.UNDERLINE, 2);
        String prefix = StringUtil.substring(type, 0, index);
        // 设置文件下载连接配置
        // 服务主机
        String server = (String)interfCahe.get(
            prefix + InterfConstant.UNDERLINE + InterfConstant.KEY_SERVER);
        entity.setIp(server);
        // 服务端口
        String port = (String)interfCahe.get(
            prefix + InterfConstant.UNDERLINE + InterfConstant.KEY_PORT);
        entity.setPort(port);
        // 服务登录用户
        String username = (String)interfCahe.get(
            prefix + InterfConstant.UNDERLINE + InterfConstant.KEY_USERNAME);
        entity.setUserName(username);
        // 服务登录密码
        String password = (String)interfCahe.get(
            prefix + InterfConstant.UNDERLINE + InterfConstant.KEY_PASS);
        entity.setPassword(password);
        // 本地文件路径
        String localPath = (String)interfCahe.get(
            type + InterfConstant.UNDERLINE + InterfConstant.KEY_LOCALPATH);
        entity.setLocalFullPath(localPath);
        // 服务文件路径
        String path = (String)interfCahe.get(
            type + InterfConstant.UNDERLINE + InterfConstant.KEY_PATH);
        entity.setFtpPath(path);
        return entity;
    }

    /**
     * 取得连接实例
     * 
     * @param entity 连接参数
     * @return 连接实例
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static FileAccessConfig getFileAccessConfig(InterfFileLog entity)
        throws Exception {
        // 设置文件下载连接配置
        String server = entity.getIp();
        int port = Integer.parseInt(entity.getPort());
        String username = entity.getUserName();
        String password = entity.getPassword();
        FileAccessConfig config = new FileAccessConfig(server, port, username, password);
        StringBuilder buffer = new StringBuilder();
        buffer.append("Access:{server:").append(server).append(",port:").append(port).append(
            ",username:").append(username).append(",password:").append(password).append("}");
        String logInfo = buffer.toString();
        LOGGER.info(logInfo);
        return config;
    }
}
