package com.joyintech.tams.exchange.common.utils;

import com.joyintech.tams.exchange.common.bean.page.PageBean;

public class PageUtils {
    public static int getFromIndex(PageBean pageBean) {
        return (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();
    }

    public static int getToIndex(PageBean pageBean) {
        return getFromIndex(pageBean) + pageBean.getPageSize() < pageBean.getTotalCount() ? getFromIndex(pageBean) + pageBean.getPageSize() : pageBean.getTotalCount();
    }
}
