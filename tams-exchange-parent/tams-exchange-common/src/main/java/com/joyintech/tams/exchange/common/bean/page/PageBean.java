package com.joyintech.tams.exchange.common.bean.page;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName PageBean
 * @Description 分页对象。用于封装当前页的分页相关的所有数据
 * @Author swwan
 * @Date 2020/11/25 15:43
 * @Version 1.0
 **/
public class PageBean {
    /**
     * 首页
     */
    private Integer firstPage;
    /**
     * 上一页
     */
    private Integer prePage;
    /**
     * 下一页
     */
    private Integer nextPage;
    /**
     * 末页、总页数
     */
    private Integer totalPage;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 每页显示的记录数
     */
    private Integer pageSize;
    /**
     * 当前页的数据
     */
    private List<Object> data;

    public PageBean(int pageSize) {
        this.firstPage = 1;
        this.prePage = 1;
        this.nextPage = 1;
        this.totalPage = 1;
        this.currentPage = 1;
        this.totalCount = 1;
        this.pageSize = pageSize;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Integer getFirstPage() {
        return Objects.isNull(firstPage) ? 1 : this.firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getPrePage() {
        return this.getCurrentPage().equals(this.getFirstPage()) ? 1 : this.getCurrentPage() - 1;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return this.getCurrentPage().equals(this.getTotalPage()) ?
                this.getTotalPage()
                : this.getCurrentPage() + 1;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getTotalPage() {
        return this.getTotalCount() % this.getPageSize() == 0 ?
                this.getTotalCount() / this.getPageSize()
                : this.getTotalCount() / this.getPageSize() + 1;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
