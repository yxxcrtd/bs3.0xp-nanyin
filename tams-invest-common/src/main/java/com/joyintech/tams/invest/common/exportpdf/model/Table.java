package com.joyintech.tams.invest.common.exportpdf.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * PDF页配置
 *
 * @author zcf
 * @version 1.0
 * @date 2021-07-23
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Tables")
public class Table {

    /**
     * 单页展示数量
     */
    @XmlAttribute(name = "size")
    private int size;

    /**
     * 类型 1.横向展示数据 2.纵向展示数据
     */
    @XmlAttribute(name = "type")
    private String type;

    /**
     * 表格列数
     */
    @XmlAttribute(name = "span")
    private int span;

    /**
     * 表格行数据
     */
    @XmlElement(name = "Row")
    private List<Row> rows;

    /**
     * 样式
     */
    @XmlAttribute(name = "fontSize")
    private String fontSize;

    /**
     * 垂直
     */
    @XmlAttribute(name = "vertical")
    private String vertical;

    /**
     * 水平
     */
    @XmlAttribute(name = "horizontal")
    private String horizontal;

    /**
     * 内边距
     */
    @XmlAttribute(name = "padding")
    private String padding;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(String horizontal) {
        this.horizontal = horizontal;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }
}
