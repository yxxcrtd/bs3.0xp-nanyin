package com.joyintech.tams.exchange.common.utils;

import com.joyintech.tams.exchange.common.bean.cache.ColumnType;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CacheBeanUtils {

    /**
     * @param beans 需要返回的集合
     * @param clazz 集合元素的class
     * @return list[]
     */
    public static <T> List<Object> convertResultSet(Collection<T> beans, Class clazz) {
        List<Object> resultList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        int notStatic = getNotStaticCount(fields);
        setResultHead(resultList, fields, notStatic);
        if (!beans.isEmpty()) {
            beans.forEach(item -> {
                Object[] contents = new Object[notStatic];
                int realCount = 0;
                for (int i = 0; i < fields.length; ++i) {
                    if (!Modifier.isStatic(fields[i].getModifiers())) {
                        Object o = ReflectionUtils.getField(fields[realCount], item);
                        if (Objects.nonNull(o)) contents[realCount] = o;
                        else contents[realCount] = null;
                        realCount += 1;
                    }
                }
                resultList.add(contents);
            });
        }
        return resultList;
    }

    private static void setResultHead(List<Object> resultList, Field[] fields, int notStatic) {
        ColumnType[] heads = new ColumnType[notStatic];
        int notStaticField = 0;
        for (Field field : fields) {
            //排除静态字段
            if (!Modifier.isStatic(field.getModifiers())) {
                ColumnType columnType = new ColumnType();
                heads[notStaticField] = columnType;
                columnType.name = field.getName();

                columnType.nullAble = Objects.isNull(field.getAnnotation(NotNull.class));
                String[] ss = field.getType().getName().split("\\.");
                if (ss.length > 1) {
                    columnType.type = ss[ss.length - 1].toLowerCase();
                } else {
                    columnType.type = ss[0].toLowerCase();
                    //基本类型不能为空
                    columnType.nullAble = false;
                }
                Size size = field.getAnnotation(Size.class);
                if (Objects.nonNull(size)) {
                    columnType.constraint = String.valueOf(size.max());
                } else {
                    Digits digits = field.getAnnotation(Digits.class);
                    if (Objects.nonNull(digits)) {
                        int intPart = digits.integer();
                        int fractionPart = digits.fraction();
                        columnType.constraint = intPart + "." + fractionPart;
                    }
                }
                columnType.pk = Objects.nonNull(field.getAnnotation(Id.class));
                field.setAccessible(true);
                notStaticField += 1;
            }
        }
        resultList.add(heads);
    }

    private static int getNotStaticCount(Field[] fields) {
        int notStatic = 0;
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) notStatic += 1;
        }
        return notStatic;
    }

    public static void main(String[] args) {
        Instrument instrument = new Instrument();
        instrument.setId("test");
        instrument.setMarket("12");
        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(instrument);
        List<Object> result = convertResultSet(instrumentList, Instrument.class);
        System.out.println(JsonUtils.getJsonFromObject(result));
    }
}
