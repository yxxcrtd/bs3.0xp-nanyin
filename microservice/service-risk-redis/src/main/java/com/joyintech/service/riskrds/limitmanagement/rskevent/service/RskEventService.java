package com.joyintech.service.riskrds.limitmanagement.rskevent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dao.RskEvent001Mapper;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dao.RskEvent002Mapper;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dao.RskEvent003Mapper;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent001Dto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent002Dto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent003Dto;
import com.joyintech.tams.common.base.dao.RskEventItemRuleMapper;
import com.joyintech.tams.common.base.dao.RskEventMapper;
import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.common.base.entity.RskEventItemRule;
import com.joyintech.tams.common.base.entity.RskEventParaRel;

@Service("RskPageEventService")
public class RskEventService extends BaseService<RskEvent001Dto> {

    @Autowired
    private RskEvent001Mapper rskEvent001Mapper;

    @Autowired
    private RskEvent002Mapper rskEvent002Mapper;

    @Autowired
    private RskEventMapper rskEventMapper;

    @Autowired
    private RskEventItemRuleMapper rskEventItemRuleMapper;

    @Autowired
    private RskEvent003Mapper rskEvent003Mapper;
    

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<RskEvent001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskEvent001Mapper.selectEventList(pageParam));
    }

    /**
     * 使用给定的sqlID为联想框检索分页数据。返回类型是Page<SuggestDto>。
     * 
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<SelectOptionDto> selectMultiOptionEvent(int page, int length,
                                                        Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPageForSelectReturn(page, length,
            () -> rskEvent001Mapper.selectMultiOptionEvent(pageParam));
    }

    /**
     * 根据eventName查询风险事件
     * 
     * @param eventName
     * @return
     * @throws Exception
     */
    public List<RskEvent> selectByEventName(String eventName)
        throws Exception {
        return rskEvent001Mapper.selectByEventName(eventName);
    }

    /**
     * 根据=eventName,!=eventCode查询风险事件
     * 
     * @param eventName
     * @param eventCode
     * @return
     * @throws Exception
     */
    public List<RskEvent> selectByEventNameNotEqCode(String eventName, String eventCode)
        throws Exception {
        return rskEvent001Mapper.selectByEventNameNotEqCode(eventName, eventCode);
    }

    /**
     * 根据风险事件代码查询风险事件信息
     * 
     * @param eventCode
     * @return
     * @throws Exception
     */
    public RskEvent002Dto selectEventDetailInfoByCode(String eventCode)
        throws Exception {
    	RskEvent002Dto dto = rskEvent002Mapper.selectEventDetailInfoByCode(eventCode);
    	if(!StringUtil.isEmptyList(dto.getParamList())){
    		dto.setParamGroup(getGroups(dto.getParamList()));
    	}
        return dto;
    }

    public List<List<RskEventItemRule>> getGroups(List<RskEventItemRule> paramList) {
    	List<List<RskEventItemRule>> result = new ArrayList<>();
    	List<String> groupIds = new ArrayList<>();
    	for(RskEventItemRule e:paramList){
    		if(!groupIds.contains(e.getGroupId())){
    			groupIds.add(e.getGroupId());
    		}
    	}
    	if(!StringUtil.isEmptyList(groupIds)){
    		for(String groupId:groupIds){
    			List<RskEventItemRule> list = new ArrayList<>();
    			for(RskEventItemRule e:paramList){
    				if(StringUtil.equals(groupId, e.getGroupId())){
    					list.add(e);
    				}
    			}
    			result.add(list);
    		}
    	}
		return result;
	}

	/**
     * 根据风险事件代码查询风险事件信息
     * 
     * @param eventCode
     * @return
     * @throws Exception
     */
    public RskEvent selectByEventCode(String eventCode)
        throws Exception {
        return rskEventMapper.selectByPrimaryKey(eventCode);
    }

    /**
     * 根据风险事件代码查询风险事件关联指标参数
     * 
     * @param eventCode
     * @return
     * @throws Exception
     */
    public RskEvent003Dto selectEventRelParaByCode(String eventCode)
        throws Exception {
        return rskEvent003Mapper.selectEventRelParaByCode(eventCode);
    }

    /**
     * 插入风险事件信息
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insert(RskEvent002Dto entity) {
        // 插入风险事件表
        rskEventMapper.insert(entity);
        if(!StringUtil.isEmptyList(entity.getParamGroup())){
        	for(List<RskEventItemRule> list:entity.getParamGroup()){
        		// 插入风险事件规则表
                setEventCode4ItemRule(entity.getEventCode(), list);
                for(RskEventItemRule e:list){
                	rskEventItemRuleMapper.insert(e);
                }
        	}
        }
    }

    /**
     * 更新风险事件信息
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(RskEvent002Dto entity) {
        // 更新风险事件信息
        rskEventMapper.updateByPrimaryKey(entity);
        // 移除风险事件特征值
        rskEvent001Mapper.deleteByEventCode(entity.getEventCode());
        if(!StringUtil.isEmptyList(entity.getParamGroup())){
        	for(List<RskEventItemRule> list:entity.getParamGroup()){
        		// 插入风险事件规则表
                setEventCode4ItemRule(entity.getEventCode(), list);
                for(RskEventItemRule e:list){
                	rskEventItemRuleMapper.insert(e);
                }
        	}
        }
        /*// 插入风险事件特征值
        setEventCode4ItemRule(entity.getEventCode(), entity.getParamList());
        rskEvent001Mapper.insertList(entity.getParamList());*/
    }

    /**
     * 移除风险事件
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(RskEvent002Dto entity) {
        // 移除风险事件
        rskEventMapper.deleteByPrimaryKey(entity.getEventCode());
        // 移除风险特征值
        rskEvent001Mapper.deleteByEventCode(entity.getEventCode());
        // 移除风险关联指标参数
        rskEvent001Mapper.deleteParaByEventCode(entity.getEventCode());
    }

    private void setEventCode4ItemRule(String eventCode, List<RskEventItemRule> list) {
        list.forEach(item -> item.setEventCode(eventCode));
    }

    /**
     * 插入风险事件关联指标参数
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insertRelPara(RskEvent003Dto entity) {
        // 插入风险事件关联指标参数表
        List<RskEventParaRel> list = entity.getParamList();
        list.forEach(item -> item.setEventCode(entity.getEventCode()));
        if (!StringUtil.isEmptyList(list)) {
        	rskEvent001Mapper.insertParaList(list);
        }
    }

    /**
     * 更新风险事件关联指标参数
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void updateRelPara(RskEvent003Dto entity) {
        // 移除风险事件关联指标参数
    	rskEvent001Mapper.deleteParaByEventCode(entity.getEventCode());
        // 插入风险事件关联指标参数
        entity.getParamList().forEach(item -> item.setEventCode(entity.getEventCode()));
        if (!StringUtil.isEmptyList(entity.getParamList())) {
        	rskEvent001Mapper.insertParaList(entity.getParamList());
        }
    }

    /**
     * 移除风险事件关联指标参数
     * 
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void deleteRelPara(RskEvent003Dto entity) {
        // 移除风险关联指标参数
    	rskEvent001Mapper.deleteParaByEventCode(entity.getEventCode());
    }

	public boolean isExistEventCode(String eventCode) {
		return rskEvent001Mapper.isExistEventCode(eventCode)>0;
	}
}
