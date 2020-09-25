/*
 * 文件名：ReportTreeMenuConfiguration001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.disclosure.information.reportpower.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.disclosure.information.reportpower.dao.ReportMenuPowerConfig001Mapper;
import com.joyintech.service.disclosure.information.reportpower.dto.ReportMenuPowerConfig001Dto;
import com.joyintech.service.disclosure.information.reportpower.dto.SysRole;
import com.joyintech.tams.common.base.entity.RepPower;

/**
 * 报表菜单权限配置
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月10日
 * @see ReportMenuPowerConfig001Service
 * @since 1.0
 */
@Service(value="serviceReportMenuPowerConfig001Service")
@Transactional(rollbackFor = Exception.class)
public class ReportMenuPowerConfig001Service extends CommonExportService<ReportMenuPowerConfig001Dto>{
    
    @Autowired
    private ReportMenuPowerConfig001Mapper reportMenuPowerConfig001Mapper;
    
    
   /**
    * 查询按钮的查询方法
    * <一句话功能简述>
    * <功能详细描述>
    * @param params
    * @return
    * @throws Exception
    * @see
    * @since 1.0
    */
    public Page<ReportMenuPowerConfig001Dto> findPage(int page, int length, Map<String, Object> params)
        throws Exception {
        return super.queryForPageForSelectReturn(page, length, () -> reportMenuPowerConfig001Mapper.queryPageList(params));
    }
    
    /**
     * 导出按钮的查询方法
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
     public List<ReportMenuPowerConfig001Dto> findAll(Map<String, Object> params)
         throws Exception {
         List<ReportMenuPowerConfig001Dto> list = reportMenuPowerConfig001Mapper.queryPageList(params);
         return list;
     }
    
    /**
     * 分页检索角色信息
     * 
     * @param pageParam 分页参数
     * @return 角色信息
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public List<SysRole> selectForPage(){
        return reportMenuPowerConfig001Mapper.select();
    }
    
    /**
     * 分页检索角色信息
     * 
     * @param pageParam 分页参数
     * @return 角色信息
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<SysRole> findUserRole(Map<String, Object> params){
//    	List<SysRole> list=new ArrayList<>();
//        String repId= RequestUtil.getParam("repId");
//        if(repId.isEmpty()){
//            return list;
//        }
        return reportMenuPowerConfig001Mapper.findUserRole(params);
    }
    
    /**
     * 保存
     * @param pageParam 分页参数
     * @return 角色信息
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
//    public void save(List<ReportMenuPowerConfig001Dto> listdto,String repId){
//        reportMenuPowerConfig001Mapper.deleteByKey(repId);
//        for(ReportMenuPowerConfig001Dto dto : listdto){
//            RepPower  repPower= new RepPower();
//            repPower.setId(dto.getRoleId());
//            repPower.setReportId(repId);
//            repPower.setRoleName(dto.getRoleName());
//            repPower.setType("Role");
//            reportMenuPowerConfig001Mapper.save(repPower);
//        }
//    }
    
    public void save(RepPower repPower){
    	reportMenuPowerConfig001Mapper.save(repPower);
    }
    /**
     * 批量配置权限保存方法 
     * @param pageParam 分页参数
     * @return 角色信息
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
//    public void saveAll(List<ReportMenuPowerConfig001Dto> listarrdto,List<ReportMenuPowerConfig001Dto> listdatasdto){
//           for(ReportMenuPowerConfig001Dto dto : listdatasdto ){
//               reportMenuPowerConfig001Mapper.deleteByKey(dto.getRepId());
//               for(ReportMenuPowerConfig001Dto arrdto : listarrdto){
//                   RepPower  repPower= new RepPower();
//                   repPower.setId(arrdto.getRoleId());
//                   repPower.setReportId(dto.getRepId());
//                   repPower.setRoleName(arrdto.getRoleName());
//                   repPower.setType("Role");
//                   reportMenuPowerConfig001Mapper.save(repPower);
//               }
//           }
//    }
    /**
     * 删除
     * @param pageParam 分页参数
     * @return 角色信息
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public void getDelete(String repId){
        reportMenuPowerConfig001Mapper.deleteByKey(repId);
    }
}
