package com.joyintech.tams.cfets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.joyintech.dsp.core.common.Message;
//import com.joyintech.dsp.datarouter.dao.CfgGroupMapper;
//import com.joyintech.dsp.datarouter.dao.CfgRouterClassifyMapper;
//import com.joyintech.dsp.datarouter.dao.CfgTriggerMapper;
//import com.joyintech.dsp.datarouter.dto.GroupNodeSort;
//import com.joyintech.dsp.datarouter.pojo.CfgGroup;
//import com.joyintech.dsp.datarouter.pojo.CfgRouterClassify;
//import com.joyintech.dsp.datarouter.pojo.CfgTrigger;

@Service
public class CfgGroupService {

//	@Autowired
//    private CfgGroupMapper cfgGroupMapper;
//	
//	@Autowired
//    private CfgTriggerMapper cfgTriggerMapper;
//	
//	@Autowired
//    private CfgRouterClassifyMapper cfgRouterClassifyMapper;
//
//    /**
//     * 查询配置分组
//     * 
//     * @param gpcode 配置分组ID
//     * @return 配置分组
//     */
//    public CfgGroup getCfgGroupByGpcode(String gpcode)
//    {
//        return cfgGroupMapper.getCfgGroupByGpcode(gpcode);
//    }
//
//    /**
//     * 查询配置分组列表
//     * 
//     * @param CfgGroup 配置分组
//     * @return 配置分组
//     */
//    public List<CfgGroup> list(CfgGroup cfgGroup)
//    {
//        return cfgGroupMapper.list(cfgGroup);
//    }
//
//    /**
//     * 新增配置分组
//     * 
//     * @param CfgGroup 配置分组
//     * @return 结果
//     */
//    public int insertCfgGroup(CfgGroup cfgGroup)
//    {
//        return cfgGroupMapper.insertCfgGroup(cfgGroup);
//    }
//
//    /**
//     * 修改配置分组
//     * 
//     * @param CfgGroup 配置分组
//     * @return 结果
//     */
//    public int updateCfgGroup(CfgGroup cfgGroup)
//    {
//        return cfgGroupMapper.updateCfgGroup(cfgGroup);
//    }
//
//    /**
//     * 批量删除配置分组
//     * 
//     * @param gpcodes 需要删除的配置分组ID
//     * @return 结果
//     */
//    public Message<String> deleteCfgGroupByIds(String[] gpcodes)
//    {
//    	for(String gpcode : gpcodes) {
//    		CfgTrigger cfgTrigger = new CfgTrigger();
//    		cfgTrigger.setGpcode(gpcode);
//    		List<CfgTrigger> cfgTriggerList = cfgTriggerMapper.selectCfgTriggerList(cfgTrigger);
//    		CfgRouterClassify routerClassify = new CfgRouterClassify();
//    		routerClassify.setGpcode(gpcode);
//    		List<CfgRouterClassify> routerClassifyList = cfgRouterClassifyMapper.selectRouterClassifyList(routerClassify);
//    		if((cfgTriggerList!=null && cfgTriggerList.size()>0) || (routerClassifyList!=null && routerClassifyList.size()>0)) {
//    			return new Message<String>().fail("fail","请先删除组内元素！");
//    		}
//    	}
//    	cfgGroupMapper.deleteCfgGroupByIds(gpcodes);
//    	return new Message<String>().success();
//    }
//
//    /**
//     * 删除配置分组信息
//     * 
//     * @param gpcode 配置分组ID
//     * @return 结果
//     */
//    public int deleteCfgGroupById(String gpcode)
//    {
//        return cfgGroupMapper.deleteCfgGroupById(gpcode);
//    }
//
//    /**
//     * 修改分组排序
//     * @param groupNodeSort
//     * @return
//     */
//    public Message<String> updateGroupSort(GroupNodeSort groupNodeSort) {
//        // 源分组
//        CfgGroup sourceGroup = cfgGroupMapper.getCfgGroupByGpcode(groupNodeSort.getSourceGroupCode());
//        // 目标分组
//        CfgGroup targetGroup = cfgGroupMapper.getCfgGroupByGpcode(groupNodeSort.getTargetGroupCode());
//        // 方向
//        String direction = groupNodeSort.getDirection();// before|after
//        if(direction.equals("before")) {
//            sourceGroup.setSeqno(targetGroup.getSeqno()-1);
//            cfgGroupMapper.updateGroupSortByUp(targetGroup);
//            cfgGroupMapper.updateGroupSort(sourceGroup);
//        } else if(direction.equals("after")){
//            sourceGroup.setSeqno(targetGroup.getSeqno()+1);
//            cfgGroupMapper.updateGroupSortByDown(targetGroup);
//            cfgGroupMapper.updateGroupSort(sourceGroup);
//        } else {
//            return new Message<String>().fail("fail","请按照要求传递移动方向值before或after");
//        }
//
//        return new Message<String>().success();
//    }
}
