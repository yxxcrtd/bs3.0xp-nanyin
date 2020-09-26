/*
 * 文件名：ValInertfStartUp001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.inertfStartUp.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.ValInterfTask;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.vasinterf.VasInterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.vasinterf.model.ValInertfBuildXml001Dto;
import com.joyintech.tams.valuation.interf.inertfStartUp.dao.ValInertfStartUp001Mapper;
import com.joyintech.tools.util.HttpClient;

import okhttp3.Response;

/**
 * 接口启动
 * 
 * @author HuangJian
 * @version 1.0 2018年7月5日
 * @see ValInertfStartUp001Service
 * @since 1.0
 */
@Service
@Component
public class ValInertfStartUp001Service extends CommonExportService<ValInterfTask> {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ValInertfStartUp001Mapper valInertfStartUp001Mapper;
    
    @Autowired
    private VasInterfCommonService service;
    
    @Value("${interf.hostname}")
    private String hostName;
    
    @Value("${interf.port}")
    private String port;
    
    @Value("${interf.contextPath}")
    private String contextPath;

//    /**
//     * 查询
//     * 
//     * @param name
//     * @return List<ValInterfTask>
//     * @see
//     * @since 1.0
//     */
//    public List<ValInterfTask> selectInertfTask(String name) {
//        return valInertfStartUp001Mapper.selectInertfTask(name);
//    }
    
    public List<ValInterfTask> selectInertfTask(String name) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValInertfStartUp001/openInertfGroupSelect", 
            name, ValInterfTask.class);
    }

    /**
     * 获取URL
     * 
     * @return url
     * @see
     * @since 1.0
     */
    public String getUrl(String department) {
        String url = "http://";
        url = url + hostName + ":" + port + contextPath + "/public" + InterfCommonConstant.接口消息入口;
        return url;
    }
    
    
    /**
     * 获取URL
     * 
     * @return url
     * @see
     * @since 1.0
     */
    public String getXmlUrl(String department) {
        String url = "http://";
        url = url + hostName + ":" + port + contextPath + "/public" + InterfCommonConstant.组装XMl文件;
        return url;
    }

    /**
     * 封装参数
     * 
     * @param listdto 接口任务
     * @param startDate 开始日
     * @param endDate 结束日
     * @return urlParams
     * @see
     * @since 1.0
     */
    public Map<String, String> getParm(List<ValInterfTask> listdto, String startDate,
                                       String endDate) {
        Map<String, String> urlParams = new HashMap<>();

        if (listdto.isEmpty()) {
            urlParams.put(InterfCommonConstant.接口_批次号列表, null);
        }
        else {
            String orderNos = listdto.get(0).getOrderno().toString();
            for (int i = 1; i < listdto.size(); i++ ) {
                orderNos = listdto.get(i).getOrderno().toString() + GlobalConstant.COMMA
                           + orderNos;
            }
            urlParams.put(InterfCommonConstant.接口_批次号列表, orderNos);
        }
        urlParams.put(InterfCommonConstant.接口_对接系统ID, DictCodeInterfList.接口对接系统ID_资管.getValue());
        urlParams.put(InterfCommonConstant.接口_发送接收, DictCodeInterfList.接口发送接收状态_接收.getValue());
        if (DateUtil.isSameDate(DateUtil.parse(startDate), DateUtil.parse(endDate))) {
            urlParams.put(InterfCommonConstant.接口_接口时间, startDate);
        }
        else {
            urlParams.put(InterfCommonConstant.接口_开始日, startDate);
            urlParams.put(InterfCommonConstant.接口_结束日, endDate);
        }
        return urlParams;
    }

    /**
     * 异步发送请求
     * 
     * @param url
     * @param urlParams
     * @see
     * @since 1.0
     */
    public int runInterf(String url, Map<String, String> urlParams) {
    	final int[] flag = new int[1]; 
        Consumer<Response> onSucess = new Consumer<Response>() {
        	
            @Override
            public void accept(Response t) {
                if (t.isSuccessful()) {
                    logger.info("请求成功：" + url);
                    flag[0] = 0;
                }
            }
            
        };
        
        Consumer<IOException> onException = new Consumer<IOException>() {

            @Override
            public void accept(IOException t) {
                logger.error(t.getMessage());
                flag[0] = 1;

            }};
        
        HttpClient.postAsync(url, urlParams, null, null, onSucess, onException);
        
        return flag[0];
        
    }

    
    
    
    /**
     * 组装数据
     * @param map
     * @throws Exception 
     * @see
     * @since 1.0
     */
	public List<ValInertfBuildXml001Dto> runInterf(Map<String, String> map) throws Exception {
		//主题逻辑
		List<ValInertfBuildXml001Dto> list = service.execute(map.get(InterfCommonConstant.接口_开始日),map.get(InterfCommonConstant.接口_结束日));
		return list;
		
	}
	
	/**
	 * add by hudongyang 获取深证通接口URL 从xybranch20190115版本合并过来 add by yanya on 20191102
	 * <功能详细描述>
	 * @param department
	 * @return
	 * @see
	 * @since 1.0
	 */
	public String getSztUrl(String department) {
	    String url = "http://";
	    url = url + hostName + ":" + port + contextPath + "/public" + InterfCommonConstant.深证通接口消息入口;
	    return url;
	}
}
