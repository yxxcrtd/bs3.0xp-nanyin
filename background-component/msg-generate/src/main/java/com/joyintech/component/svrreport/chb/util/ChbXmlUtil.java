/*
 * 文件名：ChbXmlUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年9月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.component.svrreport.chb.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.component.svrreport.chb.contant.annotate.RateFormat;
import com.joyintech.component.svrreport.chb.dto.XbrliXbrlEntity;

import cn.hutool.core.io.FileUtil;

/**
 * 中债XML文件生成工具
 * @description 
 * @author 王波
 * @version 1.0 2020年9月25日
 * @see ChbXmlUtil
 * @since 1.0
 */
public class ChbXmlUtil {
	
	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ChbXmlUtil.class);
	/**
     * 生成xml文件
     *
     * @param entity 需要转换的Entity
     * @param type 业务数据Entity类型
     * @param filePath 输出xml文件路径
     * @param fileName 输出xml名称
     * @return 转换状态
     * @throws FileNotFoundException
     */
    public static Boolean generationXML(XbrliXbrlEntity entity, Class<?> type, String filePath,
                                        String fileName) {
        // 新建文件
        File file = new File(filePath + fileName);
        // 输出流
        OutputStream ops = null;
        try {
            ops = new FileOutputStream(file);
            // 新建 return false;
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Marshaller jaxMarshaller = jaxbContext.createMarshaller();
            // 数据格式化
            jaxMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // 文件出力
            jaxMarshaller.marshal(entity, ops);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        finally {
            if (ops != null) {
                try {
                    ops.close();
                }
                catch (IOException e) {
                    logger.error("关闭流失败", e);
                }
            }
        }

        /**
         * 以下代码将转义后的<![CDATA[]]>进行还原
         */
        // 读取文件
        String fileContent = FileUtil.readString(file, StandardCharsets.UTF_8);
        // 替换内容
        fileContent = StringUtil.replaceAll(fileContent, "&lt;![CDATA[", "<![CDATA[");
        fileContent = StringUtil.replaceAll(fileContent, "]]&gt;", "]]>");

        // 写入文件
        FileUtil.writeString(fileContent, file, StandardCharsets.UTF_8);
        return true;
    }

    /**
     * xml-->bean
     *
     * @param xml 报文字符串
     * @param c bean类型
     * @return T bean对象
     * @param <T> bean class
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T)unmarshaller.unmarshal(new StringReader(xml));
        }
        catch (Exception e) {
            logger.error("报文：" + xml);
            logger.error(e.getMessage(),e);
            return null;
        }
        return t;
    }

    /**
     * 将数据的利率数值除以100报送
     * @param datas
     * @param item
     */
    public static void rateFormat(Object item) {
		Class<?> targetClass = item.getClass();
		Field[] fields = targetClass.getDeclaredFields();
		for (Field field : fields) {
			RateFormat rateFormat = field.getAnnotation(RateFormat.class);
			if(null != rateFormat){
				 field.setAccessible(true);
				 //获取属性对应类型
				 Type type = field.getGenericType();
				 //对于BigDecimal类型处理
				 if ("java.math.BigDecimal".equals(type.getTypeName())) {
					    Method me = null;
						BigDecimal value = null;
						String name = null;
						try {
							name = field.getName();
							if(null != name && name.length() > 2){
								me = targetClass.getDeclaredMethod("get" + name.substring(0,1).toUpperCase() + name.substring(1));
								value = (BigDecimal) me.invoke(item);
								//对类的实例字段除以100进行赋值
								if(null != value){
								    field.set(item,value.divide(new BigDecimal("100"),7,RoundingMode.HALF_UP));
								}
							}
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
				 } 
				 field.setAccessible(false);
			}
		}
	}
}
