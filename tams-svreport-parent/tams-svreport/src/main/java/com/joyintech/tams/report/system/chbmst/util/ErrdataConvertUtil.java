package com.joyintech.tams.report.system.chbmst.util;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;

public class ErrdataConvertUtil {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ErrdataConvertUtil.class);

    /**
     * 错误信息表解析共通处理.交易对手信息解析方法
     * @param ChbMstCounterParty 交易对手信息
     * @param errdata 错误信息
     */
    public static ChbMstCounterParty errdataChbMstCounterPartyInfoConvert(ChbMstCounterParty chbMstCounterParty, ChbMstCounterPartyErrodataWithBLOBs errdata){
        if (chbMstCounterParty == null){
            chbMstCounterParty = new ChbMstCounterParty();
        }
        if (errdata != null && StringUtil.isNotEmpty(errdata.getOrgData())){
            logger.info("调用ErrdataConvertUtil.errdataDisRepCashYieldInfoConvert，传入的orgData:" + errdata.getOrgData());
            if (errdata != null && StringUtil.isNotEmpty(errdata.getOrgData())){

                Map<String, Object> dataMap = clobStrToMap(errdata.getOrgData());

                for (String column : dataMap.keySet()) {
                    Object value = dataMap.get(column);
                    if (!StringUtil.isNotEmpty(value)) {
                        continue;
                    }
                    if (StringUtil.equals(column, "CREATE_TIME") || StringUtil.equals(column, "UPDATE_TIME")){
                        continue;
                    }
                    //根据数据库字段获取类的属性字段
                    Field field = ReflectionUtils.findField(chbMstCounterParty.getClass(), subBlank(StringUtil.lowerCase(column)));
                    //如果字段没有则跳过。
                    if(field==null){
                        logger.warn("该字段在所转换的实体类中没有，对象名：{}，字段名：{}", chbMstCounterParty.getClass().getName(), column);
                        continue;
                    }
                    field.setAccessible(true);
                    //获取属性对应类型
                    Type type = field.getGenericType();

                    try {
                        //对于BigDecimal类型处理
                        if ("java.math.BigDecimal".equals(type.getTypeName())) {
                            //对类的实例字段进行赋值
                            field.set(chbMstCounterParty, new BigDecimal(value.toString()));
                            //对于Integer类型处理
                        } else if ("java.lang.Integer".equals(type.getTypeName()) || "int".equals(type.getTypeName())) {
                            //对类的实例字段进行赋值
                            field.set(chbMstCounterParty, Integer.valueOf(value.toString()));
                            //对于Long类型处理
                        } else if ("java.lang.Long".equals(type.getTypeName())) {
                            //对类的实例字段进行赋值
                            field.set(chbMstCounterParty, Long.valueOf(value.toString()));
                            //对于Short类型处理
                        } else if ("java.lang.Short".equals(type.getTypeName())) {
                            //对类的实例字段进行赋值
                            field.set(chbMstCounterParty, Short.valueOf(value.toString()));
                            //对于Date类型处理
                        } else if ("java.util.Date".equals(type.getTypeName())) {
                            //对类的实例字段进行赋值
                            field.set(chbMstCounterParty, DateUtil.parse(value.toString()));
                        } else {
                            //其它类型处理
                            //对类的实例字段进行赋值
                            field.set(chbMstCounterParty, value);
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        continue;
                    }
                }
            }
        }

        return chbMstCounterParty;
    }

    /**
     *  clob字段解析方法
     * @param orgData 待解析数据
     * @return Map<key,value>对象
     */
    private static Map<String, Object> clobStrToMap(String orgData){
        return JsonUtils.stringToMap(orgData);
    }

    /**
     * 功能:将表字段转换成属性字段
     *
     * @since
     * @version
     * @author：
     * @param str
     * @return String
     */
    private static String subBlank(String str) {
        if (str == null) {
            return null;
        }
        String[] strarray = str.split("_");
        if (strarray.length == 0) {
            return null;
        }
        String result = strarray[0];
        for (int i = 1; i < strarray.length; i++)
            result = result + (new StringBuilder()).append(Character.toUpperCase(strarray[i].charAt(0)))
                    .append(strarray[i].substring(1)).toString();
        return result;
    }

}
