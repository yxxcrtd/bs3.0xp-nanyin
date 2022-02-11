package com.joyintech.tams.exchange.common.utils;

import com.joyintech.tams.exchange.common.bean.page.PageBean;

import java.util.ArrayList;
import java.util.List;

public class ConvertListUtils {

    public static <T> List<T> convertList(int pageNum, int pageSize, List<T> list) {
        int fromIndex = (pageNum - 1) * pageSize;
        int toIndex = pageNum * pageSize;
        if (fromIndex > list.size()) return new ArrayList<>();
        if (toIndex > list.size()) toIndex = list.size();
        return list.subList(fromIndex, toIndex);
    }

    public static <T> PageBean convertPage(int currentPage, int pageSize, List<T> list, Class<T> clazz) {
        PageBean pageBean = new PageBean(pageSize);
        if (list.isEmpty() || currentPage < 1 || pageSize < 1) {
            pageBean.setData(CacheBeanUtils.convertResultSet(list.subList(0, 0), clazz));
            return pageBean;
        }
        pageBean.setTotalCount(list.size());
        if (currentPage > pageBean.getTotalPage()) {
            pageBean.setCurrentPage(pageBean.getTotalPage());
        } else {
            pageBean.setCurrentPage(currentPage);
        }
        //设置当前页的数据
        pageBean.setData(CacheBeanUtils.convertResultSet(list.subList(PageUtils.getFromIndex(pageBean), PageUtils.getToIndex(pageBean)), clazz));
        return pageBean;
    }
}
