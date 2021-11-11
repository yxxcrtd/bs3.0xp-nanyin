/*
 * 文件名：Navbar.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改时间：2018年1月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.framework.navbar;

import java.util.List;

/**
 * 导航
 * 
 * @version 1.0 2018年1月24日
 * @see Navbar
 * @since 1.0
 */
public class Navbar {

    private String id;

    private String title;

    private String icon = "";

    private boolean spread;

    private String url;

    private String pageId;

    private int order;

    private List<Navbar> children;
    
    private String pageType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        if (null != icon && !icon.equals("")) {
            this.icon = icon;
        }
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Navbar> getChildren() {
        return children;
    }

    public void setChildren(List<Navbar> children) {
        this.children = children;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取 order
     * 
     * @return order.
     */
    public int getOrder() {
        return order;
    }

    /**
     * 设置 order
     * 
     * @param order order
     */
    public void setOrder(int order) {
        this.order = order;
    }

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}


}
