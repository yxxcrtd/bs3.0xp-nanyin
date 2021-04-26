package com.joyintech.tams.common.dbf;

/**
 * @author 何阳阳
 * @date 2020/7/1
 * @see DBFRow
 * @since 1.0
 */
public class Field {

    private String name;
    private Object value;
    private int length;

    public Field(String name, int length, Object value) {
        this.name = name;
        this.value = value;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Class<?> getType() {
        if(null == value)
            return null;
        return value.getClass();
    }
}
