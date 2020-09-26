/*
 * 文件名：AssetMangBaseInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.common;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.util.DatReadUtils;
import com.joyintech.tams.valuation.common.interf.base.util.DbReadUtils;
import com.joyintech.tams.valuation.common.interf.base.util.DbfReadUtils;
import com.joyintech.tams.valuation.common.interf.base.util.StandardTxtReadUtils;
import com.joyintech.tams.valuation.common.interf.base.util.TxtReadUtils;
import com.joyintech.tams.valuation.common.interf.base.util.XmlDom4jUtils;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangwei1
 * @version 1.0 2018年6月22日
 * @see TemplateAnalysisForXMLService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class TemplateAnalysisForXMLService {
    
    @Autowired
    private XmlDom4jUtils xmlDom4jUtils;
    @Autowired
    private TxtReadUtils txtReadUtils;
    @Autowired
    private DbfReadUtils dbfReadUtils;
    @Autowired
    private StandardTxtReadUtils standardTxtReadUtils;
    @Autowired
    private DbReadUtils dbReadUtils;
    @Autowired
    private DatReadUtils datReadUtils;
    
    /**
     * XML解析读取时根节点
     */
    private String rootElement;
    
    /**
     * XML解析数据过滤器
     */
    private Map<String, List<String>> filter;
    
    @Autowired
    private  InterfCommonService interfCommonService;
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param acctEntity
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
	public TemplateCheckReturnResult execute(TemplateOrderInfoReaderResult acctEntity) {
        TemplateCheckReturnResult result=new TemplateCheckReturnResult();
        
        if (DictCodeInterfList.接口文件类型_XML.getValue().equals(acctEntity.getTmpType())) {
            xmlDom4jUtils.setRootElement(rootElement);
            xmlDom4jUtils.setFilter(filter);
            result= xmlDom4jUtils.readStringXml(acctEntity);
        }
        else if (DictCodeInterfList.接口文件类型_TXT.getValue().equals(acctEntity.getTmpType())) {
            result= txtReadUtils.readTxt(acctEntity);
        }
        else if (DictCodeInterfList.接口文件类型_DBF.getValue().equals(acctEntity.getTmpType())) {
            result= dbfReadUtils.readDbf(acctEntity);
        }
        else if (DictCodeInterfList.接口文件类型_DB.getValue().equals(acctEntity.getTmpType())) {
            result= dbReadUtils.readDb(acctEntity);
        }
        else if (DictCodeInterfList.接口文件类型_EXCEL.getValue().equals(acctEntity.getTmpType())) {
        }
        else if (DictCodeInterfList.接口文件类型_STTXT.getValue().equals(acctEntity.getTmpType())) {
            result = standardTxtReadUtils.readTxt(acctEntity);
        }
        else if (DictCodeInterfList.接口文件类型_DAT.getValue().equals(acctEntity.getTmpType())) {
            result = datReadUtils.readTxt(acctEntity);
        }
        /**add by wujie on 20190128 start remark：接口业务check**/
        result = interfCommonService.resolveBusiCheck(acctEntity.getTmpEName(), result);
        /**add by wujie on 20190128 end**/
        return result;
        
    }

    
    /**
     * 设置 rootElement
     * @param rootElement rootElement
     */
    public void setRootElement(String rootElement) {
        this.rootElement = rootElement;
    }

    /**
     * 设置 filter
     * @param filter filter
     */
    public void setFilter(Map<String, List<String>> filter) {
        this.filter = filter;
    }

}
