/*
 * 文件名：FinProductDurDealService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.rddesgin.productelementdes.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.rddesgin.productelementdes.dao.ProductelementdesMapper;
import com.joyintech.service.product.rddesgin.productelementdes.dto.Productelementdes001Dto;
import com.joyintech.service.product.rddesgin.productelementdes.dto.Productelementdes002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 产品存续期交易查询微服务Service
 *
 * @author etliu
 * @version 1.0 2018年12月18日
 * @see ProductelementdesService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service("backProductelementdesService")
public class ProductelementdesService extends BaseService {

    /**
     * FinProductDurDealMapper
     */
    @Autowired
    private ProductelementdesMapper productelementdesMapper;


/**
     * 查询产品存续期交易信息
     *
     * @param page page
     * @param length length
     * @param pageParam 参数
     * @return 产品存续期交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<Productelementdes002Dto> selectForPage(int page, int length,
                                                           Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("PROCESS_STATE_NEW", DictCodeList.流程状态_新建.getValue());
        Page<Productelementdes002Dto> resultPage = this.queryForPage(page, length,
            () -> productelementdesMapper.selectMainData(pageParam));

       return resultPage;
    }

    @SuppressWarnings("unchecked")
	public Page<Productelementdes001Dto> findDetailPage(int page, int length,
                                                            Map<String, Object> pageParam)
    	throws Exception {
    	 pageParam.put("STATUS_VALID_01", DictCodeListExt1.有效状态_有效.getValue());
         pageParam.put("REPORT_STATUS2_1", DictCodeListExt1.初评结果_初评通过.getValue());
       return this.queryForPage(page, length,
        		() -> productelementdesMapper.selectDetailData(pageParam));
    }
/**
 * 插入产品要素设计信息
 */
 public void insertProductelementdes(Productelementdes002Dto productelementdes002Dto){
     productelementdesMapper.insertProductelementdes(productelementdes002Dto);
 }
 //查找产品需求信息
// public Productelementdes001Dto findSinglePage(String requirementId){
//     return productelementdesMapper.findSinglePage(requirementId);
// }
    public Productelementdes001Dto findSinglePage(String pageParam)
             {
         String requirementId=pageParam;

        return productelementdesMapper.findSinglePage(requirementId);
    }
 //查找产品设计要素信息
 public Productelementdes002Dto findprdDesignId(String prdDesignId){
     Productelementdes002Dto dto=productelementdesMapper.findprdDesignId(prdDesignId);
     System.out.println(dto);
     return productelementdesMapper.findprdDesignId(prdDesignId);
 }
 //创建产品要素信息流程数据
    public void createProductelementdes002(Productelementdes002Dto productelementdes002Dto){
   productelementdesMapper.createProductelementdes002(productelementdes002Dto);
    }
  //删除流程表
    public void deleteProductelementdes002(String prdDesignId){
        productelementdesMapper.deleteProductelementdes002Dto(prdDesignId);
    }
    //删除要素信息
    public void deletePro002Dto(String prdDesignId){
        productelementdesMapper.deletePro002Dto(prdDesignId);
    }
    //修改设计要素信息
    public void updateProductelementdes(Productelementdes002Dto productelementdes002Dto){
     productelementdesMapper.updateProductelementdes(productelementdes002Dto);
    }
}
