/*
 * 文件名：BaseService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.base;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.joyintech.background.component.common.pack.common.grid.IConverter;
import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.common.pack.common.util.GenericsUtils;
import com.joyintech.background.component.common.pack.common.util.PageUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author 何阳阳
 * @version 1.0 2018年1月26日
 * @param <T> 对象类型
 * @see BaseService
 * @since 1.0
 */
public class BaseService<T> {

    /**
     * MessageSource
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * 检索分页数据。返回类型是Service的泛型。
     *
     * @param select 查询方法
     * @param converter 实体类转换
     * @return Page类
     */
    protected Page<T> queryForPage(int pageNum, int pageSize, ISelect select, IConverter<T> converter) {
        com.github.pagehelper.Page<T> page = queryByPage(pageNum, pageSize, select);
        if (null != page && page.size() > 0) {
            Class<?> cls = GenericsUtils.getSuperClassGenricType(this.getClass());
            if (!cls.getName().equals(page.get(0).getClass().getName())) {
                List<T> pageList = new ArrayList<>();
                pageList.addAll(page);
                page.clear();
                pageList.forEach(item -> {
                    page.add(converter.doConverter(item));
                });
            }
        }
        return new Page<T>(page);
    }

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param select 查询方法
     * @return Page类
     */
    protected Page<T> queryForPage(int pageNum, int pageSize, ISelect select) {
        return new Page<T>(queryByPage(pageNum, pageSize, select));
    }

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Page的泛型。
     *
     * @param select 查询方法
     * @return Page类
     */
    protected <O> Page<O> queryForPageForSelectReturn(int pageNum, int pageSize, ISelect select) {
        sortPage();
        com.github.pagehelper.Page<O> result = PageHelper.startPage(pageNum, pageSize).doSelectPage(select);
        return new Page<O>(result);
    }

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示的条数
     * @param select 查询方法
     * @return Page类
     */
    private com.github.pagehelper.Page<T> queryByPage(int pageNum, int pageSize, ISelect select) {
        sortPage();
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(select);
    }

    /**
     * 获取message
     *
     * @param code message code
     * @param args 参数
     * @return message
     * @see
     * @since 1.0
     */
    protected String getMessage(String code, Object... args) {
    	// 由于服务器上的语言环境没有权限修改，这里默认设置为简体中文
        return this.messageSource.getMessage(code, args, Locale.SIMPLIFIED_CHINESE/*LocaleContextHolder.getLocale()*/);
    }

    /**
     * 分页添加排序功能
     */
    private void sortPage() {
        String orderField = PageUtil.getOrderField();/*StringUtil.getOrElse(RequestUtil.getParam(GlobalConstant.SORT_NAME),
            RequestUtil.getCurrentRequest().getHeader(GlobalConstant.SORT_NAME));*/
        String orderDirection = PageUtil.getOrderDirection();/*StringUtil.getOrElse(
            RequestUtil.getParam(GlobalConstant.SORT_ORDER),
            RequestUtil.getCurrentRequest().getHeader(GlobalConstant.SORT_ORDER));*/
        if (StringUtil.isNotEmpty(orderField)) {
            String orderString = orderField;
            if (StringUtil.isNotEmpty(orderDirection)) {
                orderString += StringUtil.SPACE + orderDirection;
            }
            PageHelper.orderBy(orderString);
        }
    }
}
