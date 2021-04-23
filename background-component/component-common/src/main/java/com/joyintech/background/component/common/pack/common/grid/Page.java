/*
 * 文件名：Page.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.common.pack.common.grid;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * 分页对象
 * @author 何阳阳
 * @version 1.0 2018年1月31日
 * @param <T>
 * @see Page
 * @since 1.0
 */
public class Page<T> {

    /**
     * 当前页码
     */
    private int page;

    /**
     * 总页数
     */
    private int total;

    /**
     * 总记录数
     */
    private long records;

    /**
     * 异常消息
     */
    private List<String> message;

    /**
     * 分页数据
     */
    private List<T> root;

    /**
     * 状态
     */
    private int code;

    /**
     * 所有页的汇总数据<br />
     * Added by JIAOXUJIN on 2020-03-10
     */
    private T summary;

    /**
     * 当前页的汇总数据<br />
     * Added by JIAOXUJIN on 2020-03-10
     */
    private T pageSummary;

    public Page() {
        this.page = 1;
        this.total = 0;
        this.records = 0;
        this.code = 0;
    }

    public Page(com.github.pagehelper.Page<T> page) {
        this.page = page.getPageNum();
        this.total = page.getPages();
        this.root = page;
        this.records = page.getTotal();
    }

    public Page(PageInfo<T> pageInfo) {
        this.page = pageInfo.getPageNum();
        this.total = pageInfo.getPages();
        this.root = pageInfo.getList();
        this.records = pageInfo.getTotal();
    }

    public Page(List<T> root) {
        this.root = root;
    }

    /**
     * @return the records
     */
    public long getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(long records) {
        this.records = records;
    }

    /**
     * @return the root
     */
    public List<T> getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(List<T> root) {
        this.root = root;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取 message
     *
     * @return message.
     */
    public List<String> getMessage() {
        return message;
    }

    /**
     * 设置 message
     *
     * @param message message
     */
    public void setMessage(List<String> message) {
        this.code = 1;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getSummary() {
        return summary;
    }

    public void setSummary(T summary) {
        this.summary = summary;
    }

    public T getPageSummary() {
        return pageSummary;
    }

    public void setPageSummary(T pageSummary) {
        this.pageSummary = pageSummary;
    }
}
