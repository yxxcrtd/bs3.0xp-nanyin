package com.joyintech.tams.invest.common.exportpdf.model;

import javax.xml.bind.annotation.*;

/**
 * PDF列配置
 *
 * @author zcf
 * @version 1.0
 * @date 2021-07-23
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Cell {

    /**
     * 序号
     */
    @XmlAttribute(name = "order")
    private int order;

    /**
     * 标题
     */
    @XmlAttribute(name = "name")
    private String name;

    /**
     * key值
     */
    @XmlValue
    private String value;

    /**
     * 跨列
     */
    @XmlAttribute(name = "colspan")
    private Integer colspan;

    /**
     * 边框
     */
    @XmlAttribute(name = "borderSide")
    private String borderSide;

    /**
     *  单元格类型 1.单key值 2.多key值
     */
    @XmlAttribute(name = "type")
    private String type;

    /**
     * 水平
     */
    @XmlAttribute(name = "horizontal")
    private String horizontal;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }

    public String getBorderSide() {
        return borderSide;
    }

    public void setBorderSide(String borderSide) {
        this.borderSide = borderSide;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(String horizontal) {
        this.horizontal = horizontal;
    }
}
