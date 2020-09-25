package com.joyintech.tams.invest.product.productplanning.informationMaintenance.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning001Dto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning002DetailDto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning002Dto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 产品规划信息新建
 *
 * @author 代国富
 * @version 1.0 2019年12月17日
 * @see FinProductPlanningEstablish002Service
 * @since 1.0
 */
@Service
public class FinProductPlanningEstablish002Service extends CommonExportService<FinProductPlanning002Dto> implements BaseProcessService<FinProductPlanning002Dto> {

    /**
     * 根据条件查询
     * @param pageParam
     * @return
     */
    public Page<FinProductPlanning001Dto> findPage(Map<String, Object> pageParam) {
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/findPage", pageParam,
                FinProductPlanning001Dto.class);
    }
    /**
     * 下拉框展示
     *  @return 下拉框列
     *   * @throws Exception Exception
     */
    public Page<SelectOptionDto> querySeriesName(Map<String, Object> param)
            throws Exception {
        param.put("loginUserName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/querySeriesName", param,
                SelectOptionDto.class);
    }
    /**
     * 根据流水号查询研发方案详情
     *
     * @param
     * @return 研发方案详情
     * @see
     * @since 1.0
     */
    public FinProductPlanning002Dto getEntityByNo(String planningNo) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                        + "/FinProductPlanning/getEntityByNo?planningNo=" + planningNo,
                FinProductPlanning002Dto.class);
    }
    /**
     * 点击+号操作
     * 根据系列编号查询是否存在该数据
     * @param
     * @return
     */
    public Integer findCountByNo( Map<String, Object> map){
        Integer integer = restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/findCountByNo",
                map,
                Integer.class
        );
        return integer;
    }

    /**
     * 点击+号操作
     * 根据系列名称查询是否存在该数据
     * @param
     * @return
     */
    public Integer findCountByName( Map<String, Object> map){
        Integer integer = restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/findCountByName",
                map,
                Integer.class
        );
        return integer;
    }
    /**
     * 修改页面查询到期份额
     * @param map
     * @return
     */
    public BigDecimal findProductsDue(Map<String, Object> map){
       return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/findProductsDue",
                map,
                BigDecimal.class
        );
    }
    /**
     * 新建页面查询
     * @param pageParam
     * @return FinProductPlanning001Dto
     */
    public List<FinProductPlanning002DetailDto> getEntity(Map<String, Object> pageParam) {
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        Page<FinProductPlanning002DetailDto> entiy = restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/findPage", pageParam,
                FinProductPlanning002DetailDto.class);
        return entiy.getRoot();
    }

    /**
     * 判断规划开始日与结束日在数据库中是否存在
     * @param pageParam
     * @return
     */
    public Integer getConflictCount(Map<String,Object> pageParam){
        Integer count = restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/getConflictCount",
                pageParam,
                Integer.class
        );
        return count;
    }

    /**
     * 判断开始日结束日区间不重叠
     * @return
     */
    public Integer getOverlapCount(Map<String,Object> params){
        Integer integer = restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/getOverlapCount",
                params,
                Integer.class
        );
        return integer;
    }

    /**
     * 获取用于比较的目标值
     * @param params
     * @return
     */
    public List<FinProductPlanning003Dto> getTargetSize(Map<String,Object> params){
        List<FinProductPlanning003Dto> targets = restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/getTargetSize",
                params,
                FinProductPlanning003Dto.class
        );
        return targets;
    }
    /**
     * 	根据系列id 查name
     * @param
     * @return
     */
//    public Page<FinProductPlanning001Dto> queryPrdPlanSerName(Map<String, Object> pageParam) {
//        return restTemplate.postForPage(
//                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/queryPrdPlanSerName", pageParam,
//                FinProductPlanning001Dto.class);
//    }

    @Override
    public void doComplete(FinProductPlanning002Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        if(!type.equals(OperationType.DELETE)){
            //修改或者新增
            restTemplate.postForObject(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/updateOrAdd",
                    entity,
                    Integer.class
            );
        }else{
            //删除
            String planningNo= entity.getPlanningNo();
            Map<String,Object> params = new HashMap<>();
            params.put("planningNo",planningNo);
            restTemplate.postForObject(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPlanning/deleteByPrimary",
                    params,
                    Integer.class
            );
        }
    }
}
