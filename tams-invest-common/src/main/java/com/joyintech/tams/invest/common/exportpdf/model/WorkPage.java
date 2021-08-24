package com.joyintech.tams.invest.common.exportpdf.model;

import javax.xml.bind.annotation.*;

/**
 * PDF页配置
 *
 * @author zcf
 * @version 1.0
 * @date 2021-07-23
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WorkPage")
public class WorkPage {

    /**
     * 单页展示数量
     */
    @XmlAttribute(name = "size")
    private int size;

    /**
     * 文件名称
     */
    @XmlAttribute(name = "name")
    private String name;

    /**
     * 字体大小
     */
    @XmlAttribute(name = "fontSize")
    private String fontSize;

    /**
     * 页面高度
     */
    @XmlAttribute(name = "height")
    private Integer height;

    /**
     * 页面宽度
     */
    @XmlAttribute(name = "width")
    private Integer width;

    /**
     * 数据表格
     */
    @XmlElement(name = "Table")
    private Table table;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
