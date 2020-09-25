/*
 * 文件名：ReportTreeMenuConfiguration001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.information.config.reporttree.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepTreeMapper;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.common.base.entity.RepTree;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.reporttree.dao.ReportTreeMenuConfiguration001Mapper;
import com.joyintech.tams.disclosure.information.config.reporttree.dto.ReportTreeMenuConfiguration001Dto;

/**
 * 报表树形菜单配置
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月7日
 * @see ReportTreeMenuConfiguration001Service
 * @since 1.0
 */
@Service
public class ReportTreeMenuConfiguration001Service extends CommonExportService<RepInfo>{
    
    @Autowired
    private ReportTreeMenuConfiguration001Mapper reportTreeMenuConfiguration001Mapper;
    
    @Autowired
    private RepTreeMapper repTreeMapper;
 
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportTreeMenuConfiguration001Service.class);
    /**
     * 初始化查询方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
    public List<TreeNode> getTreeNodeList(String tmpCName){//@Param(value="tmpcname")
      	/* List<RepTree> list=reportTreeMenuConfiguration001Mapper.findAllData(tmpCName);*/
//    	List<ReportTreeMenuConfiguration001Dto> list=reportTreeMenuConfiguration001Mapper.findAllData(tmpCName);
    	List<ReportTreeMenuConfiguration001Dto> list= restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + 
    			"/ReportTreeMenuConfiguration001/findAllData?tmpCName="+tmpCName,
    			ReportTreeMenuConfiguration001Dto.class);
    	List<TreeNode> treeList = new ArrayList<TreeNode>();
        TreeNode node=null;
        for (ReportTreeMenuConfiguration001Dto dto : list) {
            node = new TreeNode();
            node.setId(dto.getTreeId());
            node.setpId(dto.getpId());
            node.setName(dto.getTreeName());
            treeList.add(node);
        }
        treeList.sort((node1, node2) -> node1.getId().compareTo(node2.getId()));
        return treeList;
    }
    
    /**
     * 批量增加报表
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult checkData(Map<String, Object> map) throws Exception{
        String datas = (String)map.get("datas");
        List<RepInfo> listdto= JsonUtils.json2List(datas, RepInfo.class);
        if(listdto.size()==0){
            return new AjaxResult("至少选择一个报表", MessageType.错误消息);
        }
        //判断是不是在报表上操作
        Map<String, Object> mapmap=new HashMap<String, Object>();
   
        mapmap.put("PId", (String)map.get("PId"));
        mapmap.put("treeId",(String)map.get("Id"));
        /*RepTree dtos=reportTreeMenuConfiguration001Mapper.selectByKey(mapmap);*/
//        ReportTreeMenuConfiguration001Dto dtos=reportTreeMenuConfiguration001Mapper.selectByKey(mapmap);
        ReportTreeMenuConfiguration001Dto dtos =restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + 
    			"/ReportTreeMenuConfiguration001/selectByKey",mapmap,
    			ReportTreeMenuConfiguration001Dto.class);
        
        
        
        
        
       
        /* if("Y".equals(dtos.getFlag())){
            return new AjaxResult("不能在报表上操作", MessageType.错误消息);
        }*/
        if("Y".equals(dtos.getSpflag())){
            return new AjaxResult("不能在报表上操作", MessageType.错误消息);
        }
        
        
        //先判断是否存在已有数据
        for(RepInfo dto:listdto){
            Map<String, Object> maps=new HashMap<String, Object>();
            maps.put("PId", (String)map.get("Id"));
            maps.put("treeId", dto.getRepId());
//            ReportTreeMenuConfiguration001Dto repTree=reportTreeMenuConfiguration001Mapper.selectByKey(maps);
            
            ReportTreeMenuConfiguration001Dto repTree=restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + 
        			"/ReportTreeMenuConfiguration001/selectByKey",maps,
        			ReportTreeMenuConfiguration001Dto.class);
            
            
            
            
            if(repTree!=null){
                return new AjaxResult("该数据已经存在", MessageType.错误消息);
            }
        }
        //check全部过了再插入数据
        for(RepInfo dto:listdto){
            Map<String, Object> maps=new HashMap<String, Object>();
            maps.put("PId", (String)map.get("Id"));
            maps.put("treeId", dto.getRepId());
            maps.put("treeName", dto.getReportName());
            maps.put("flag","Y");
//            reportTreeMenuConfiguration001Mapper.AddParentNode(maps);
            restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + 
        			"/ReportTreeMenuConfiguration001/AddParentNode",maps,
        			Integer.class);
            
        }
        return null;
    }
    /**
     * 设置为报表集按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult edit(Map<String, Object> map) throws Exception{
        String Id = (String)map.get("Id");
//        List<ReportTreeMenuConfiguration001Dto> dtolist=reportTreeMenuConfiguration001Mapper.selectByPrimaryKey(Id);
        
        List<ReportTreeMenuConfiguration001Dto> dtolist=restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + 
    			"/ReportTreeMenuConfiguration001/selectByPrimaryKey?Id="+Id,
    			ReportTreeMenuConfiguration001Dto.class);
        
        
        
        
        for(ReportTreeMenuConfiguration001Dto repTree:dtolist){
                if(StringUtils.isEmpty(repTree.getpId())|| "Y".equals(repTree.getSpflag())
                		||repTree.getChildNum()==null
                		||repTree.getChildNum()==0){
                    return new AjaxResult("此节点不能设为报表集", MessageType.错误消息);
                } 
            }
            //校验成功
//            reportTreeMenuConfiguration001Mapper.edit(map);
            restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + 
        			"/ReportTreeMenuConfiguration001/edit",map,
        			Integer.class);
        return null;       
    }
    /**
     * 增加父节点
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult AddParentNode(Map<String, Object> map){
        String treeId=(String)map.get("treeId");
        String treeName=(String)map.get("treeName");
        if("".equals(treeId) || "".equals(treeName)){
            return new AjaxResult("必填项不能为空", MessageType.错误消息);
        }
        /*RepTree dto=repTreeMapper.selectByPrimaryKey(treeId);*/
        RepTree dto= repTreeMapper.selectByPrimaryKey(treeId);
        if(dto!=null){
            return new AjaxResult("该数据已经存在", MessageType.错误消息);
        }
//        reportTreeMenuConfiguration001Mapper.AddParentNode(map);
        
        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportTreeMenuConfiguration002/AddParentNode",
                map, Integer.class);
        
        
        
        return null;
    }
    
    /**
     * 增加子节点
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult AddSonNode(Map<String, Object> map){
        String treeId=(String)map.get("treeId");
        String treeName=(String)map.get("treeName");
        if("".equals(treeId) || "".equals(treeName)){
            return new AjaxResult("必填项不能为空", MessageType.错误消息);
        }  
        Map<String, Object> keymap = new HashMap<String,Object>();
        keymap.put("treeId", treeId);// key为treeId，不需要pId作为查询条件  zhangyf 20181219
//        ReportTreeMenuConfiguration001Dto dto=reportTreeMenuConfiguration001Mapper.selectByKey(keymap);
        ReportTreeMenuConfiguration001Dto dto =restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + 
    			"/ReportTreeMenuConfiguration001/selectByKey",keymap,
    			ReportTreeMenuConfiguration001Dto.class);
        
        
        if(dto!=null){
            return new AjaxResult("该数据已经存在", MessageType.错误消息);
        }
//        reportTreeMenuConfiguration001Mapper.AddParentNode(map);
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + 
    			"/ReportTreeMenuConfiguration001/AddParentNode",map,
    			Integer.class);
        
        return null;
    }
    /**
     * 修改方法
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public void getEdit(Map<String, Object> map){
//        reportTreeMenuConfiguration001Mapper.getEdit(map);
    	 restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + 
     			"/ReportTreeMenuConfiguration004/getEdit",map,
     			Integer.class);
        
        
    }
    
    /**
     * 页面初始化查询列表
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<RepInfo> findPage(Map<String, Object> params)
        throws Exception {
    	 return restTemplate.postForPage(
                 ServiceApplication.VALUATION_SERVICE_NAME + "/ReportTreeMenuConfiguration001/findPage",
                 params, RepInfo.class);
    	
//        return super.queryForPage(() -> reportTreeMenuConfiguration001Mapper.queryPageList(params));
    }
    
    /**
     * 删除方法
     * <一句话功能简述>
     * <功能详细描述>
     * @param Id
     * @param PId
     * @param repTreeMapper 
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult getDelete(String Id,String PId){
        if("".equals(PId)){
            //检查剩余根节点数量，只有1个则不能删除。
//            List<ReportTreeMenuConfiguration001Dto> pNodeList=reportTreeMenuConfiguration001Mapper.findAllPNode(Id);
            
            List<ReportTreeMenuConfiguration001Dto> pNodeList=
            restTemplate.getForList(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ReportTreeMenuConfiguration001/findAllPNode?Id="+Id,
                     ReportTreeMenuConfiguration001Dto.class);
            
            
            
            
            if(pNodeList != null && pNodeList.size() <=1){
                return new AjaxResult("至少要保留一个根节点，故不能删除该节点！", MessageType.错误消息);
            }
        }
        
//        if("".equals(PId)){//PId
//            return new AjaxResult("不能删除主节点", MessageType.错误消息);
//        }else{
            //判断该节点的父节点是否为节点
            /*List<RepTree> repTrees=reportTreeMenuConfiguration001Mapper.checkDataPid(Id);*/
//        	List<ReportTreeMenuConfiguration001Dto> repTrees=reportTreeMenuConfiguration001Mapper.checkDataPid(Id);
//        	
//        	//为父节点
//            if(repTrees.size()>0){
//               //删除该节点的子节点
//                reportTreeMenuConfiguration001Mapper.delete(Id);
//            }
//            //删除该节点
//            repTreeMapper.deleteByPrimaryKey(Id);
        
        restTemplate.getForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportTreeMenuConfiguration001/getDelete?Id="+Id+"&PId="+PId,
                Integer.class);
//        } 
     return null;   
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<TreeNode> getTreeNodeListByTreeName(String tmpCName) {
    	return  restTemplate.getForList(
                 ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/getTreeNodeListByTreeName?tmpCName="+tmpCName,
                 TreeNode.class);
    	
    	
    	
//        List<TreeNode> treeList = new ArrayList<TreeNode>();
//        // 查出报表树节点list
//        ReportTreeMenuConfiguration001Dto para = new ReportTreeMenuConfiguration001Dto();
//        para.setParaTreeName(tmpCName);
//        List<String> roleIdList = getRoleIdList(para);
//        para.setRoleIdList(roleIdList);//增加当前用户的权限过滤
//
//        List<ReportTreeMenuConfiguration001Dto> repTreeList = reportTreeMenuConfiguration001Mapper.findParentOrSAubTreeList(para);
//        if(StringUtil.isEmptyList(repTreeList)){
//            return treeList;
//        }
//        // 保存当前节点、父节点、子节点
//        List<ReportTreeMenuConfiguration001Dto> temptreeList = new ArrayList<ReportTreeMenuConfiguration001Dto>();
//        for (ReportTreeMenuConfiguration001Dto dto : repTreeList) {
//        	dto.setObject(dto.getFlag());
//            dto.setRoleIdList(roleIdList);//增加当前用户的权限过滤
//            // 添加dto的子节点们
//            getSubTreeList(dto, temptreeList);
//            // 添加dto的父节点们
//            getParentTreeList(dto, temptreeList);
//        }
////        // 排序
////        Collections.sort(temptreeList, new Comparator<ReportTreeMenuConfiguration001Dto>() {
////            @Override
////            public int compare(ReportTreeMenuConfiguration001Dto r1, ReportTreeMenuConfiguration001Dto r2) {
////                return (!StringUtil.isNotEmpty(((ReportTreeMenuConfiguration001Dto) r1).getRepOrder()) ? "0" : ((ReportTreeMenuConfiguration001Dto) r1).getRepOrder()).compareTo(
////                    !StringUtil.isNotEmpty(((ReportTreeMenuConfiguration001Dto) r2).getRepOrder()) ? "0" : ((ReportTreeMenuConfiguration001Dto) r2).getRepOrder());
////            }
////        });
//        // 组装返回
//        TreeNode node = null;
//        for (ReportTreeMenuConfiguration001Dto dto : temptreeList) {
//            if(!dto.isShow()){
//                continue;
//            }
//            
//            if(StringUtil.isEmpty(dto.getObject())){
//            	dto.setObject(dto.getFlag());
//            }
//            
//            node = new TreeNode();
//            node.setId(dto.getTreeId());
//            node.setpId(dto.getpId());
//            node.setName(dto.getTreeName());
//            node.setObject(dto.getObject());
//            treeList.add(node);
//        }
//        treeList.sort((node1, node2) -> node1.getId().compareTo(node2.getId()));
//        return treeList;
    }
   
    /**
     * 查询父节点们
     * @param dto
     * @param parentTreeList
     * @return
     * @see
     * @since 1.0
     */
    private void getParentTreeList(ReportTreeMenuConfiguration001Dto dto,List<ReportTreeMenuConfiguration001Dto> treeList) {
        if(!dto.isShow()){//对于不展示的节点，不找其父节点
            return;
        }
        addRepTree(dto,treeList);
        dto.setSpflag("PAR");
        List<ReportTreeMenuConfiguration001Dto> repTreeListp1 = reportTreeMenuConfiguration001Mapper.findParentOrSAubTreeList(dto);
        if(repTreeListp1 != null && repTreeListp1.size() != 0){
            for(ReportTreeMenuConfiguration001Dto repTree : repTreeListp1){
                getParentTreeList(repTree,treeList);
            }
        }
    }
    
    /**
     * 查询子节点们
     * <功能详细描述>
     * @param parentTreeList
     * @return
     * @see
     * @since 1.0
     */
    private void getSubTreeList(ReportTreeMenuConfiguration001Dto dto,List<ReportTreeMenuConfiguration001Dto> treeList) {
      //是报表的场景：
        if ("Y".equals(dto.getFlag())) {
            addRepTree(dto,treeList);
            return;
        }
        
        //是节点的场景：
        dto.setSpflag("SUB");
        List<ReportTreeMenuConfiguration001Dto> repTreeListp1 = reportTreeMenuConfiguration001Mapper.findParentOrSAubTreeList(dto);
        if(repTreeListp1 != null && repTreeListp1.size() != 0){
            addRepTree(dto,treeList);
            for(ReportTreeMenuConfiguration001Dto repTree : repTreeListp1){
                repTree.setRoleIdList(dto.getRoleIdList());
                getSubTreeList(repTree,treeList);
            }
        }else{
            //dto节点:不是报表、没有子节点的=不展示
            dto.setShow(false);
        }
    }
    
    
    private void addRepTree(ReportTreeMenuConfiguration001Dto dto,List<ReportTreeMenuConfiguration001Dto> treeList){
        if(!isContainReport(dto,treeList)){
            treeList.add(dto);
        }
    }
    
    private boolean isContainReport(ReportTreeMenuConfiguration001Dto dto,List<ReportTreeMenuConfiguration001Dto> treeList){
        for (ReportTreeMenuConfiguration001Dto rt : treeList) {
            if((dto.getTreeId()+dto.getpId()).equals(rt.getTreeId()+rt.getpId())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * 获取当前用户的角色list
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<String> getRoleIdList(ReportTreeMenuConfiguration001Dto dto) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<String> roleIdList = new ArrayList<String>();
        try {
            roleIdList = reportTreeMenuConfiguration001Mapper.getRoleIdList(dto);
        } catch (Exception e) {
//            e.printStackTrace();
        	logger.error("error:",e);
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
        if(StringUtil.isEmptyList(roleIdList)){
            //roleIdList.add("no_role_id");
        }
        return roleIdList;
    }
    
    /**
     * 通过pid查子节点下的报表
     * <一句话功能简述>
     * <功能详细描述>
     * @param pId
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportTreeMenuConfiguration001Dto> getTreeNodeListByPid(String pId) {
        List<ReportTreeMenuConfiguration001Dto> temptreeList = new ArrayList<ReportTreeMenuConfiguration001Dto>();
        List<ReportTreeMenuConfiguration001Dto> treeList = new ArrayList<ReportTreeMenuConfiguration001Dto>();
        // 查出报表树节点list
        ReportTreeMenuConfiguration001Dto para = new ReportTreeMenuConfiguration001Dto();
        para.setpId(pId);
        List<String> roleIdList = getRoleIdList(para);
        para.setRoleIdList(roleIdList);//增加当前用户的权限过滤
        List<ReportTreeMenuConfiguration001Dto> repTreeList = reportTreeMenuConfiguration001Mapper.findParentOrSAubTreeList((ReportTreeMenuConfiguration001Dto) para);
        if(StringUtil.isEmptyList(repTreeList)){
            return temptreeList;
        }
        // 保存当前节点、子节点
        for (ReportTreeMenuConfiguration001Dto dto : repTreeList) {
            // 添加dto的子节点们
            getSubTreeList(dto, temptreeList);
        }
        // 组装返回
        for (ReportTreeMenuConfiguration001Dto dto : temptreeList) {
            if(!dto.isShow() || !"Y".equals(((ReportTreeMenuConfiguration001Dto) dto).getSpflag())){
                continue;
            }
            treeList.add(dto);
        }
        return treeList;
    }
}
