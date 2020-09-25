/*
 * 文件名：FlowRiskStressTestService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dao.MarketRiskStressTest002Mapper;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest002Dto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年9月5日
 * @see MarketRiskStressTest002Service
 * @since 1.0
 */
@Service
public class MarketRiskStressTest002Service extends BaseService<MarketRiskStressTest002Dto> {
    
    @Autowired
    private MarketRiskStressTest002Mapper marketRiskStressTest002Mapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    
    /**
     * 产品持仓
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<MarketRiskStressTest002Dto> selectProdForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
    	
    	SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date sysDate = iPlatformDate.getSysDate();
        Calendar date = Calendar.getInstance();
        date.setTime(sysDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
        Date endDate = new Date();
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
		//根据情景代码查找起止日期
		MarketRiskStressTest002Dto dto = marketRiskStressTest002Mapper.selectDateBySceId(pageParam);
		if(StringUtil.isNotEmpty(dto)){
			pageParam.put("startDate", DateUtil.format(dto.getStartDate()));
			pageParam.put("endDate", DateUtil.format(dto.getEndDate()));
		}
//		if(StringUtil.isNotEmpty(pageParam.get("baseDate"))){
//			pageParam.remove("baseDate");
//		}
//		pageParam.put("baseDate", DateUtil.format(endDate));
		//解决ojdbc版本问题，字符太长装clob报错,将产品分组
		if(StringUtil.isNotEmpty(pageParam.get("prodIds")) && pageParam.get("prodIds").toString().length() > 2000){
			List<String> li = getStrList(pageParam.get("prodIds").toString(),2000);
			pageParam.put("proIdsList", li);
		}
		else{
			List<String> li = new ArrayList<String>();
			li.add(pageParam.get("prodIds").toString());
			pageParam.put("proIdsList", li);
		}
		
		
		Page<MarketRiskStressTest002Dto> pag = super.queryForPage(page, length, () -> marketRiskStressTest002Mapper.selectProdForPage(pageParam));
		DynamicDataSourceHolder.clearDataSource();
		return pag;
    }
    
    /**
     * 未准备因子
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<MarketRiskStressTest002Dto> selectFacForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
    	
    	SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date sysDate = iPlatformDate.getSysDate();
        Calendar date = Calendar.getInstance();
        date.setTime(sysDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
        Date endDate = new Date();
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
		//根据情景代码查找起止日期
		MarketRiskStressTest002Dto dto = marketRiskStressTest002Mapper.selectDateBySceId(pageParam);
		if(StringUtil.isNotEmpty(dto)){
			pageParam.put("startDate", DateUtil.format(dto.getStartDate()));
			pageParam.put("endDate", DateUtil.format(dto.getEndDate()));
		}
//		if(StringUtil.isNotEmpty(pageParam.get("baseDate"))){
//			pageParam.remove("baseDate");
//		}
//		pageParam.put("baseDate", DateUtil.format(endDate));
		//解决ojdbc版本问题，字符太长装clob报错,将产品分组
		if(StringUtil.isNotEmpty(pageParam.get("prodIds")) && pageParam.get("prodIds").toString().length() > 2000){
			List<String> li = getStrList(pageParam.get("prodIds").toString(),2000);
			pageParam.put("proIdsList", li);
		}
		else{
			List<String> li = new ArrayList<String>();
			li.add(pageParam.get("prodIds").toString());
			pageParam.put("proIdsList", li);
		}
		Page<MarketRiskStressTest002Dto> pag = super.queryForPage(page, length, () -> marketRiskStressTest002Mapper.selectFacForPage(pageParam));
		DynamicDataSourceHolder.clearDataSource();
		return pag;
    }
    
    /**
     * 截取字符串根据长度
     * @param inputString 输入字符串
     * @param length 分组截取的长度
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     * 
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @param size
     *            指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
            int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     * 
     * @param str
     *            原始字符串
     * @param f
     *            开始位置
     * @param t
     *            结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }
}
