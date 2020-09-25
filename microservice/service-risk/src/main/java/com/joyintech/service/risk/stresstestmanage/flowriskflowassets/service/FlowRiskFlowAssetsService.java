package com.joyintech.service.risk.stresstestmanage.flowriskflowassets.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskflowassets.dao.FlowRiskFlowAssetsMapper;
import com.joyintech.service.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssetsDto;
import com.joyintech.tams.common.base.dao.SttMappingDetMapper;
import com.joyintech.tams.common.base.dao.SttMappingMapper;
import com.joyintech.tams.common.base.entity.SttMapping;
import com.joyintech.tams.common.base.entity.SttMappingDet;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 流动性风险-情景管理
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskFlowAssetsService
 * @since 1.0
 */
@Service
public class FlowRiskFlowAssetsService extends BaseService<FlowRiskFlowAssetsDto> {
	
	@Autowired
	private FlowRiskFlowAssetsMapper flowRiskFlowAssetsMapper;
	
	@Autowired
	private SttMappingMapper sttMappingMapper;
	
	@Autowired
    private SttMappingDetMapper sttMappingDetMapper;
	
	
	/**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public void update(FlowRiskFlowAssetsDto dto) {
//        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        //一级流动性资产
        if(dto.getSelectedFlowAss1().size() > 0){
            Map<String,Object> param = new HashMap<>();
            param.put("dictCode", DictCode.流动性资产分类.getValue());
            param.put("tgtValue", DictCodeListRsk.流动性资产分类_一级流动性资产.getValue());
            FlowRiskFlowAssetsDto flowAssOne = flowRiskFlowAssetsMapper.selectFlowAssetByCode(param);
            //类型映射表
            SttMapping sttMap = new SttMapping();
            sttMap.setMappingId(flowAssOne.getMappingId());
            sttMap.setUpdateUser(dto.getCreateUser());
            if(StringUtil.isNotEmpty(dto.getRemark())){
                sttMap.setRemark(dto.getRemark());
            }
            sttMappingMapper.updateByPrimaryKeySelective(sttMap);
            //已选一级分类
            Map<String,Object> paramDet = new HashMap<>();
            paramDet.put("mappingId", flowAssOne.getMappingId());
            //删除抑一级下的明细
            flowRiskFlowAssetsMapper.deletSttMapDetByMapId(paramDet);
            for(FlowRiskFlowAssetsDto entity:dto.getSelectedFlowAss1()){
                SttMappingDet dto11 = new SttMappingDet();
                dto11.setDetId(flowAssOne.getMappingId() + entity.getId());
                dto11.setMappingId(flowAssOne.getMappingId());
                if(StringUtil.equals(entity.getpId(), DictCodeListExt1.维护数据状态_有效.getValue())){
                    dto11.setSouceCode("TYPE_9"); 
                    dto11.setSouceValue(entity.getId());
                    Long lev=(long)1;
                    dto11.setDictCode(DictCode.行内资产二级分类.getValue());
                    dto11.setLevel(lev);
                    dto11.setfDetId("E");
                }else{
                    dto11.setSouceCode("TYPE_10");
                    dto11.setSouceValue(entity.getId().substring(entity.getId().indexOf("-") + 1, entity.getId().length()));
                    Long lev=(long)2;
                    dto11.setDictCode(DictCode.行内资产三级分类.getValue());
                    dto11.setLevel(lev);
                    dto11.setfDetId(entity.getpId());
                }
                dto11.setSouceType(DictCodeListRsk.参数类型_数据字典.getValue());
                sttMappingDetMapper.insertSelective(dto11);
            }
        }
        //二级流动性资产
        if(dto.getSelectedFlowAss2().size() > 0){
            Map<String,Object> param = new HashMap<>();
            param.put("dictCode", DictCode.流动性资产分类.getValue());
            param.put("tgtValue", DictCodeListRsk.流动性资产分类_二级流动性资产.getValue());
            FlowRiskFlowAssetsDto flowAssTwo = flowRiskFlowAssetsMapper.selectFlowAssetByCode(param);
            //类型映射表
            SttMapping sttMap = new SttMapping();
            sttMap.setMappingId(flowAssTwo.getMappingId());
            sttMap.setUpdateUser(dto.getCreateUser());
            if(StringUtil.isNotEmpty(dto.getRemark())){
                sttMap.setRemark(dto.getRemark());
            }
            sttMappingMapper.updateByPrimaryKeySelective(sttMap);
            //已选一级分类
            Map<String,Object> paramDet = new HashMap<>();
            paramDet.put("mappingId", flowAssTwo.getMappingId());
            //删除抑一级下的明细
            flowRiskFlowAssetsMapper.deletSttMapDetByMapId(paramDet);
            for(FlowRiskFlowAssetsDto entity:dto.getSelectedFlowAss2()){
                SttMappingDet dto21 = new SttMappingDet();
                dto21.setDetId(flowAssTwo.getMappingId() + entity.getId());
                dto21.setMappingId(flowAssTwo.getMappingId());
                if(StringUtil.equals(entity.getpId(), DictCodeListExt1.维护数据状态_有效.getValue())){
                    dto21.setSouceCode("TYPE_9"); 
                    dto21.setSouceValue(entity.getId());
                    Long lev=(long)1;
                    dto21.setDictCode(DictCode.行内资产二级分类.getValue());
                    dto21.setLevel(lev);
                    dto21.setfDetId("0");
                }else{
                    dto21.setSouceCode("TYPE_10");
                    dto21.setSouceValue(entity.getId().substring(entity.getId().indexOf("-") + 1, entity.getId().length()));
                    Long lev=(long)2;
                    dto21.setDictCode(DictCode.行内资产三级分类.getValue());
                    dto21.setLevel(lev);
                    dto21.setfDetId(entity.getpId());
                }
                dto21.setSouceType(DictCodeListRsk.参数类型_数据字典.getValue());
                sttMappingDetMapper.insertSelective(dto21);
            }
        }
//        DynamicDataSourceHolder.clearDataSource();
    }
    
    /**
     * 获取一级流动性资产
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskFlowAssetsDto> getFloAssOne() {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        Map<String,Object> param =new HashMap<>();
        param.put("tgtValue", DictCodeListRsk.流动性资产分类_一级流动性资产.getValue());
        List<FlowRiskFlowAssetsDto> list = flowRiskFlowAssetsMapper.getFloAssOneOrTwo(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    
    /**
     * 获取二级流动性资产
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskFlowAssetsDto> getFloAssTwo() {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        Map<String,Object> param =new HashMap<>();
        param.put("tgtValue", DictCodeListRsk.流动性资产分类_二级流动性资产.getValue());
        List<FlowRiskFlowAssetsDto> list = flowRiskFlowAssetsMapper.getFloAssOneOrTwo(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    
    /**
     * 根据参数代码和参数值得到明细的资产类型（一级二级等）
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskFlowAssetsDto> getFloAssTypDetByCodVal(Map<String,Object> map) {
        return flowRiskFlowAssetsMapper.getFloAssTypDetByCodVal(map);
    }
    /**
     * 获取资产二级和三级分类的树数据
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskFlowAssetsDto> getFloAssTreeData() {
        Map<String,Object> param = new HashMap<>();
        param.put("dictCode", DictCode.行内资产三级分类.getValue());
        return flowRiskFlowAssetsMapper.getFloAssTreeData(param);
    }
    /**
     * 初始化页面的备注信息
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
	public FlowRiskFlowAssetsDto selectFlowAssOneMes() {
	    DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
	    Map<String,Object> param = new HashMap<>();
        param.put("dictCode", DictCode.流动性资产分类.getValue());
        param.put("tgtValue", DictCodeListRsk.流动性资产分类_一级流动性资产.getValue());
        FlowRiskFlowAssetsDto flowAssOne = flowRiskFlowAssetsMapper.selectFlowAssetByCode(param);
        DynamicDataSourceHolder.clearDataSource();
        return flowAssOne;
	}
}
