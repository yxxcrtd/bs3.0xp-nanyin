package com.joyintech.sso.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joyintech.sso.util.ServletUtil;
import com.joyintech.sso.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * web层通用数据处理
 * 
 */
public class BaseController
{
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";
    
    /**
     * 默认每页显示记录数
     */
    public static final Integer DEFAULT_PAGE_SIZE = 10;


    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        Integer pageNum = ServletUtil.getParameterToInt(PAGE_NUM);
        Integer pageSize = ServletUtil.getParameterToInt(PAGE_SIZE);
        if (!StringUtils.isNotNull(pageSize)) {
        	pageSize = DEFAULT_PAGE_SIZE;
        }
        if (StringUtils.isNotNull(pageNum))
        {
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Message<PageInfo<?>> pageMessage(List<?> list)
    {
    	PageInfo<?> pageInfo = new PageInfo(list);
        return new Message(pageInfo);
    }

}
