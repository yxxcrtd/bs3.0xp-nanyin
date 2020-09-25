/*
 * 文件名：FinProductBmQuotationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.bmquotation.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 产品业绩基准报价管理
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月11日
 * @see FinProductBmQuotationService
 * @since 1.0
 */
@Service
public class FinProductBmQuotationService extends CommonExportService<FinProductBmQuotationDto> 
    implements BaseProcessService<FinProductBmQuotationDto> {
    /**
     * 
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;
    /**
     * 业绩基准组件
     */
    @Autowired(required = false)
    private BussBenchMarketInfoService bussBenchMarketInfoService;
    /**
     * 产品代码联想框 
     * @param finprodMarketId finprodMarketId
     * @return List List
     * @throws Exception Exception
     */
    public List<SelectOptionDto> getFinprodMarketIdList(String finprodMarketId)
        throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("finprodMarketId", finprodMarketId);
        User user = SecurityUtil.getLoginUser();
        map.put("userName", user.getUserName());
      //获取平台日
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME +
            "/FinProductBmQuotation/getFinprodMarketIdList",map,
            SelectOptionDto.class);
    }
    
    /**
     * 画面查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<FinProductBmQuotationDto> selectForPage(Map<String, Object> pageParam) throws Exception {
        //获取平台日
        pageParam.put("SESSION_SYS_DATE", platformDate != null ? platformDate.getSysDateStr():DateUtil.format(DateUtil.now()));
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userName", user.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBmQuotation/findPage",
            pageParam, FinProductBmQuotationDto.class);
    }
    
    /**
     * 导出查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<FinProductBmQuotationDto> selectForList(Map<String, Object> pageParam) throws Exception {
        //获取平台日
        pageParam.put("SESSION_SYS_DATE", platformDate != null ? platformDate.getSysDateStr():DateUtil.format(DateUtil.now()));
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userName", user.getUserName());
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBmQuotation/selectForList",
            pageParam, FinProductBmQuotationDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductBmQuotationDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // entity.setSystemCode("SYS");
        if (OperationType.DELETE.equals(type) || OperationType.MODIFY.equals(type)) {
        	if(OperationType.MODIFY.equals(type)) {
        		action = "insert";
        		String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
        		entity.setBranch(Long.parseLong(branch));
        	}else {
        		action = "delete";
        	}
            //删除业绩基准信息
            if(bussBenchMarketInfoService != null){
                BussBenchMarkInfoDto dto;
                if(entity.getBussBenchMarkInfoDto() != null){
                    dto = entity.getBussBenchMarkInfoDto();
                    dto.setEffDate(entity.getEffDate());
                    if(null == dto.getBaseRuleId() && null != entity.getBaseRuleId()){
                        dto.setBaseRuleId(entity.getBaseRuleId());
                    }
                }
                else{
                    dto = new BussBenchMarkInfoDto();
                    dto.setEffDate(entity.getEffDate());
                    dto.setBaseRuleId(entity.getBaseRuleId());
                }
                
                bussBenchMarketInfoService.deleteByPrimaryKey(dto);
            }
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBmQuotation/" + action, entity,
            Integer.class);
        
        
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/product/messageadjust/bmquotation/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductBmQuotationDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新建产品代码联想框 
     * <功能详细描述>
     * @param map finprodMarketId
     * @return Object
     * @exception Exception Exception
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketIdToNew(Map<String,Object> map) 
        throws Exception {
        if(!StringUtil.isNotEmpty(map.get("initValue"))){
            User user = SecurityUtil.getLoginUser();
            map.put("userName", user.getUserName());
        }
      //获取平台日
        map.put("SESSION_SYS_DATE", platformDate != null ? platformDate.getSysDateStr():DateUtil.format(DateUtil.now()));
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME +
            "/FinProductBmQuotation/getFinprodMarketIdToNew",map,
            SelectOptionDto.class);
    }

    /**
     * 根据金融产品代码和生效日获得页面展示元素
     * <功能详细描述>
     * @param map map
     * @return FinProductBmQuotationDto
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public FinProductBmQuotationDto selectByFinprodId(Map<String,Object> map){
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBmQuotation/selectByFinprodId",
            map, FinProductBmQuotationDto.class);
    }

    /**
     * 根据基准代码和生效日查询数据
     * <功能详细描述>
     * @param dto dto
     * @return list
     * @see
     * @since 1.0
     */
    public List<FinProductBmQuotationDto> selectByFinprodIdAndEffDate(FinProductBmQuotationDto dto) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBmQuotation/selectByFinprodIdAndEffDate",
            dto, FinProductBmQuotationDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId finprodId
     * @return Date Date
     * @see
     * @since 1.0
     */
    public FinProductBmQuotationDto getMdate(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBmQuotation/getMdate?finprodId="
            +finprodId, FinProductBmQuotationDto.class);
    }

    /**
     * 判断生效日后是否发生了浮动管理费计提、支付交易
     * <功能详细描述>
     * @param dto dto
     * @return Integer Integer
     * @see
     * @since 1.0
     */
    public Integer getCount(FinProductBmQuotationDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBmQuotation/getCount"
            ,dto, Integer.class);
    }
    /**
     * 获取分层产品产品款型
	* @param finprodId
	* @return
	*/
    public List<SelectOptionDto> queryLayerProdStyleList(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryLayerProdStyleList",
            map, SelectOptionDto.class);
    }
    
    
    
    @SuppressWarnings("unchecked")
    public Map<String,String> sendProductBmQuotation(List<Map<String,String>> listMap) {
        return restTemplate.postForObject(
                ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendProductBmQuotation", listMap,
                Map.class);
    }
}
