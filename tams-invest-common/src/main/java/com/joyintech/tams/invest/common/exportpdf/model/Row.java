package com.joyintech.tams.invest.common.exportpdf.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * PDF行配置
 *
 * @author zcf
 * @version 1.0
 * @date 2021-07-23
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Row {

    /**
     * 数据列
     */
    @XmlElement(name = "Cell")
    private List<Cell> cells;

    /**
     * 样式
     */
    @XmlAttribute(name = "fontSize")
    private String fontSize;

    /**
     * 内边距
     */
    @XmlAttribute(name = "padding")
    private String padding;

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

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
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
}
