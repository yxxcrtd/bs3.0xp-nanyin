/*
 * 文件名：FlowRiskSceneManage001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.tool.JixuUserUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssetsDto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao.FlowRiskFactroManage002Mapper;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage002Dto;
import com.joyintech.tams.common.base.dao.SttFactorTipsMapper;
import com.joyintech.tams.common.base.dao.SttSceneFactorMapper;
import com.joyintech.tams.common.base.dao.SttSceneFtrtipsMapper;
import com.joyintech.tams.common.base.dao.SttSceneLmhMapper;
import com.joyintech.tams.common.base.dao.SttSceneMapper;
import com.joyintech.tams.common.base.dao.SttSceneSuitMapper;
import com.joyintech.tams.common.base.dao.SttSceneTipsMapper;
import com.joyintech.tams.common.base.entity.SttFactorTips;
import com.joyintech.tams.common.base.entity.SttScene;
import com.joyintech.tams.common.base.entity.SttSceneFactor;
import com.joyintech.tams.common.base.entity.SttSceneFtrtips;
import com.joyintech.tams.common.base.entity.SttSceneLmh;
import com.joyintech.tams.common.base.entity.SttSceneSuit;
import com.joyintech.tams.common.base.entity.SttSceneTips;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;

/**
 * 流动性风险-情景新建数据服务层-Service
 * @author wangkang
 * @version 1.0 2019年8月28日
 * @see FlowRiskSceneManage002Service
 * @since 1.0
 */
@Service("backFlowRiskSceneManage002Service")
public class FlowRiskSceneManage002Service extends BaseService<FlowRiskSceneManage001Dto> {
	
	@Autowired
	private FlowRiskFactroManage002Mapper flowRiskFactroManage002Mapper;
	
	/**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    
	/**
	 * 情景mapper
	 */
	@Autowired
	private SttSceneMapper sttSceneMapper;
	/**
     * 情景因子标签mapper
     */
    @Autowired
    private SttSceneFtrtipsMapper sttSceneFtrtipsMapper;
	/**
     * 因子标签mapper
     */
    @Autowired
    private SttFactorTipsMapper sttFactorTipsMapper;
	/**
     * 情景标签表mapper
     */
    @Autowired
    private SttSceneTipsMapper sttSceneTipsMapper;
    /**
     * 情景适用表mapper
     */
    @Autowired
    private SttSceneSuitMapper sttSceneSuitMapper;
    /**
     * 情景因子表mapper
     */
    @Autowired
    private SttSceneFactorMapper sttSceneFactorMapper;
    /**
     * 轻中重度因子值表mapper
     */
    @Autowired
    private SttSceneLmhMapper sttSceneLmhMapper;

    /**
     * 根据指标类型查找指标
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.STRESSTEST)
    public List<FlowRiskSceneManage002Dto> queryIndexByType(Map<String, Object> param){
        return flowRiskFactroManage002Mapper.queryIndexByType(param);
    }
    /**
     * 判断情景名是否重复
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManage002Dto> queryScentNameCount(Map<String, Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<FlowRiskSceneManage002Dto> list= flowRiskFactroManage002Mapper.queryScentNameCount(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    /**
     * 根据情景代码查找情景信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FlowRiskSceneManage002Dto querySceneById(Map<String, Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        FlowRiskSceneManage002Dto dto = flowRiskFactroManage002Mapper.querySceneById(param);
        DynamicDataSourceHolder.clearDataSource();
        return dto;
    }
    /**
     * 根据情景代码查找关联因子
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManage002Dto> queryFtrBySceId(Map<String, Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<FlowRiskSceneManage002Dto> list = flowRiskFactroManage002Mapper.queryFtrBySceId(param);
        if(!StringUtil.equals(list.size(), 0)){
            for(FlowRiskSceneManage002Dto dto:list){
                if(StringUtil.isNotEmpty(dto.getMediumValue())){
                    dto.setMediumValue(dto.getMediumValue().multiply(new BigDecimal(100)));
                }
                if(StringUtil.isNotEmpty(dto.getLightValue())){
                    dto.setLightValue(dto.getLightValue().multiply(new BigDecimal(100)));
                }
                if(StringUtil.isNotEmpty(dto.getHeavyValue())){
                    dto.setHeavyValue(dto.getHeavyValue().multiply(new BigDecimal(100)));
                }
            }
        }
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    
    /**
     * 查询流动性风险的假设情景下的因子
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManage002Dto> queryFlowSttFactor(Map<String, Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<FlowRiskSceneManage002Dto> list = flowRiskFactroManage002Mapper.queryFlowSttFactor(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    /**
     * 查询资产变现能力因子
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManage002Dto> queryFlowSttFactor2(Map<String, Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<FlowRiskSceneManage002Dto> list = flowRiskFactroManage002Mapper.queryFlowSttFactor(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    
    /**
     * 流动性情景模板
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FlowRiskSceneManage002Dto selectSceneTmpl(Map<String, Object> param) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        FlowRiskSceneManage002Dto dto = flowRiskFactroManage002Mapper.selectSceneTmpl(param);
        DynamicDataSourceHolder.clearDataSource();
        return dto;
    }
    /**
     * 查找二级类型和适用产品
     * @param param
     * @return
     */
    public FlowRiskSceneManage002Dto queryScentType2AndMode(Map<String, Object> param) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        FlowRiskSceneManage002Dto dto = flowRiskFactroManage002Mapper.queryScentType2AndMode(param);
        DynamicDataSourceHolder.clearDataSource();
        return dto;
    }
    
    /**
     * 查找模板关联指标
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.STRESSTEST)
    public List<FlowRiskSceneManage002Dto> selectTmplIndByType(Map<String, Object> param) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<FlowRiskSceneManage002Dto> list = flowRiskFactroManage002Mapper.selectTmplIndByType(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    /**
     * 根据因子代码和字典代码查找标签值
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public SttSceneFtrtips queryNetOutflow(Map<String, Object> param) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        SttSceneFtrtips facTips = flowRiskFactroManage002Mapper.queryNetOutflow(param);
        DynamicDataSourceHolder.clearDataSource();
        return facTips;
     }
    /**
     * 情景新建
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
//    @Transactional
//    @DataSource(DataSourceType.STRESSTEST)
    public void insert(FlowRiskSceneManage002Dto dto) {
//        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        String username=JixuUserUtil.getLoginUserName();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date sysDate = iPlatformDate.getSysDate();
        //情景表新增

//    	DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        SttScene sttSc = new SttScene();
        sttSc.setSceneId(dto.getSceneId());//情景代码
        sttSc.setSceneName(dto.getSceneName());//情景名称
        sttSc.setSceneTpmlId(dto.getTmplId());//压测模板代码
        sttSc.setIsTmpl(DictCodeListRsk.是否_否.getValue());//是否模板
        sttSc.setEffStatus("01");//已准备
        sttSc.setRemark(dto.getRemark());//备注
        //wangyang1 风险压力测试二级法人改造 start
        sttSc.setInstitutionCode(MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));
        //wangyang1 风险压力测试二级法人改造 end
        sttSceneMapper.insertSelective(sttSc);
        //情景标签表新增
        //适用风险
        SttSceneTips sttTip = new SttSceneTips();
        int i=0;
        sttTip.setSceneId(dto.getSceneId());//情景代码
        sttTip.setTipsCode(dto.getSceneId() + ++i);
        sttTip.setTipsValue(dto.getRiskType());//标签值
        sttTip.setDictCode(DictCode.情景风险分类.getValue());//字典代码
        sttSceneTipsMapper.insertSelective(sttTip);
        //适用情景
        sttTip.setTipsCode(dto.getSceneId() + ++i);
        sttTip.setTipsValue(dto.getSceneType());//标签值
        sttTip.setDictCode(DictCode.情景类型.getValue());//字典代码
        sttSceneTipsMapper.insertSelective(sttTip);
        //适用情景二级类型
        sttTip.setTipsCode(dto.getSceneId() + ++i);
        sttTip.setTipsValue(dto.getSceneType2());//标签值
        sttTip.setDictCode(DictCode.情景二级类型.getValue());//字典代码
        sttSceneTipsMapper.insertSelective(sttTip);
        //适用产品
        sttTip.setTipsCode(dto.getSceneId() + ++i);
        sttTip.setTipsValue(dto.getOpenmode());//标签值
        sttTip.setDictCode(DictCode.开放模式.getValue());//字典代码
        sttSceneTipsMapper.insertSelective(sttTip);
        //情景适用表
        SttSceneSuit sttSui = new SttSceneSuit();
        sttSui.setSeqNo(username+"-"+(dft.format(sysDate))+"-"+IDGenerator.generate(12).substring(0, 8));//流水号
        sttSui.setSceneId(dto.getSceneId());//情景代码
        sttSui.setParamCode(DictCode.情景风险分类.getValue());//参数代码
        sttSui.setParamValue(dto.getRiskType());//参数值
        sttSui.setDictCode(DictCode.情景风险分类.getValue());//字典代码
        sttSceneSuitMapper.insertSelective(sttSui);
        //情景因子表
        SttSceneFactor sttFac = new SttSceneFactor();
        sttFac.setSceneId(dto.getSceneId());//情景代码
        sttFac.setFtrValueType(DictCodeListRsk.参数类型_数值.getValue());//因子值类型-参数类型
        //轻中重度因子值
        SttSceneLmh sttLmh = new SttSceneLmh();
        sttLmh.setSceneId(dto.getSceneId());//情景代码
        for(FlowRiskSceneManage002Dto facDto:dto.getFtrList1()){
            String id = username+"-"+(dft.format(sysDate))+"-"+IDGenerator.generate(12).substring(0, 8);//情景因子代码
            sttFac.setSceneFtrId(id);//情景因子代码
//            sttFac.setSceneFtrtipsId(id);
            sttFac.setFtrId(facDto.getFtrId());//因子代码
            sttFac.setFtrValueStruct(DictCodeListRsk.风险因子管理_因子值结构_轻中重.getValue());
//            if(StringUtil.equals(facDto.getFtrId(), DictCodeListRsk.流动性风险因子_申赎净流出增加比例.getValue())){//如果为申赎净流出增加比例，增加一个因子值
//                sttFac.setFtrValue(facDto.getNetOutf());
//            }
            sttSceneFactorMapper.insertSelective(sttFac);
            sttLmh.setSceneFtrId(id);//情景因子代码
            sttLmh.setFtrId(facDto.getFtrId());//因子代码
            if(StringUtil.isNotEmpty(facDto.getLightValue())){
                sttLmh.setLightValue(facDto.getLightValue().divide(new BigDecimal(100)));//轻度因子值
            }
            if(StringUtil.isNotEmpty(facDto.getMediumValue())){
                sttLmh.setMediumValue(facDto.getMediumValue().divide(new BigDecimal(100)));//中度因子值
            }
            if(StringUtil.isNotEmpty(facDto.getHeavyValue())){
                sttLmh.setHeavyValue(facDto.getHeavyValue().divide(new BigDecimal(100)));//重度因子值
            }
            sttSceneLmhMapper.insertSelective(sttLmh);
            //如若因子为申赎净流出，情景因子标签表
            if(StringUtil.equals(facDto.getFtrId(), DictCodeListRsk.流动性风险因子_申赎净流出增加比例.getValue())){
                SttSceneFtrtips scFtrTips = new SttSceneFtrtips();
                String tipid = username+"-"+(dft.format(sysDate))+"-"+IDGenerator.generate(12).substring(0, 8);//情景因子标签代码
                //情景因子标签代码
                scFtrTips.setSceneFtrtipsId(tipid);
                //情景因子代码
                scFtrTips.setSceneFtrId(id);
                //情景代码
                scFtrTips.setSceneId(dto.getSceneId());
                //参数类型
                scFtrTips.setParamCode(DictCodeListRsk.参数类型_数据字典.getValue());
                scFtrTips.setParamValue(dto.getNetOutf());
                //参数值
                scFtrTips.setDictCode(DictCode.申赎净流出基础选择.getValue());
                sttSceneFtrtipsMapper.insertSelective(scFtrTips);
//                SttFactorTips sttFacTip = flowRiskFactroManage002Mapper.queryNetOutflow(par);
//                sttFacTip.setTipsCode(facDto.getNetOutf());
//                sttFactorTipsMapper.updateByPrimaryKeySelective(sttFacTip);
            }
            
        }
        SttSceneLmh sttLmh2 = new SttSceneLmh();
        sttLmh2.setSceneId(dto.getSceneId());//情景代码
        for(FlowRiskSceneManage002Dto fac2Dto:dto.getFtrList2()){
            String id = username+"-"+(dft.format(sysDate))+"-"+IDGenerator.generate(12).substring(0, 8);//情景因子代码
            sttFac.setSceneFtrId(id);//情景因子代码
            //sttFac.setSceneFtrtipsId(id);
            sttFac.setFtrId(fac2Dto.getFtrId());
            sttFac.setFtrValueStruct(DictCodeListRsk.风险因子管理_因子值结构_轻中重.getValue());
            sttSceneFactorMapper.insertSelective(sttFac);//轻中重度因子值表
            sttLmh2.setSceneFtrId(id);//情景因子代码
            sttLmh2.setFtrId(fac2Dto.getFtrId());//因子代码
            if(StringUtil.isNotEmpty(fac2Dto.getLightValue())){
                sttLmh2.setLightValue(fac2Dto.getLightValue().divide(new BigDecimal(100)));//轻度因子值
            }
            if(StringUtil.isNotEmpty(fac2Dto.getMediumValue())){
                sttLmh2.setMediumValue(fac2Dto.getMediumValue().divide(new BigDecimal(100)));//中度因子值
            }
            if(StringUtil.isNotEmpty(fac2Dto.getHeavyValue())){
                sttLmh2.setHeavyValue(fac2Dto.getHeavyValue().divide(new BigDecimal(100)));//重度因子值
            }
            sttSceneLmhMapper.insertSelective(sttLmh2);//轻中重度因子值表
        }
        
        
//        DynamicDataSourceHolder.clearDataSource();
    }
    /**
     * 情景编辑
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
//    @Transactional
//    @DataSource(DataSourceType.STRESSTEST)
    public void update(FlowRiskSceneManage002Dto dto) {
        //情景表情景
        SttScene sttSce = new SttScene();
        sttSce.setSceneId(dto.getSceneId());//情景代码
        sttSce.setSceneName(dto.getSceneName());//情景名称
        sttSce.setRemark(dto.getRemark());//备注
        sttSceneMapper.updateByPrimaryKeySelective(sttSce);

        // modify by wj 20200717 start
        SttSceneTips sttTip = new SttSceneTips();
        int i=0;
        sttTip.setSceneId(dto.getSceneId());//情景代码
        sttTip.setTipsCode(dto.getSceneId() + ++i);
        sttTip.setTipsValue(dto.getRiskType());//标签值
        sttTip.setDictCode(DictCode.情景风险分类.getValue());//字典代码
        sttSceneTipsMapper.updateByPrimaryKey(sttTip);
        //适用情景
        sttTip.setTipsCode(dto.getSceneId() + ++i);
        sttTip.setTipsValue(dto.getSceneType());//标签值
        sttTip.setDictCode(DictCode.情景类型.getValue());//字典代码
        sttSceneTipsMapper.updateByPrimaryKey(sttTip);
        //适用情景二级类型
        sttTip.setTipsCode(dto.getSceneId() + ++i);
        sttTip.setTipsValue(dto.getSceneType2());//标签值
        sttTip.setDictCode(DictCode.情景二级类型.getValue());//字典代码
        sttSceneTipsMapper.updateByPrimaryKey(sttTip);
        //适用产品
        sttTip.setTipsCode(dto.getSceneId() + ++i);
        sttTip.setTipsValue(dto.getOpenmode());//标签值
        sttTip.setDictCode(DictCode.开放模式.getValue());//字典代码
        sttSceneTipsMapper.updateByPrimaryKey(sttTip);

        // modify by wj 20200717 end;
        
        for(FlowRiskSceneManage002Dto facDto:dto.getFtrList1()){
            SttSceneLmh sttLmh = new SttSceneLmh();
            sttLmh.setSceneFtrId(facDto.getSceneFtrId());//情景因子代码
            sttLmh.setFtrId(facDto.getFtrId());//因子代码
            sttLmh.setSceneId(dto.getSceneId());//情景代码
            if(StringUtil.isNotEmpty(facDto.getLightValue())){
                sttLmh.setLightValue(facDto.getLightValue().divide(new BigDecimal(100)));//轻度
            }
            if(StringUtil.isNotEmpty(facDto.getMediumValue())){
                sttLmh.setMediumValue(facDto.getMediumValue().divide(new BigDecimal(100)));//中度
            }
            if(StringUtil.isNotEmpty(facDto.getHeavyValue())){
                sttLmh.setHeavyValue(facDto.getHeavyValue().divide(new BigDecimal(100)));//重度
            }
            //情景因子表
            SttSceneFactor sttSceFac = new SttSceneFactor();
            sttSceFac.setSceneFtrId(facDto.getSceneFtrId());//情景因子代码
            sttSceFac.setFtrId(facDto.getFtrId());//因子代码
            sttSceneFactorMapper.updateByPrimaryKeySelective(sttSceFac);
            //轻中重度因子值表
            sttSceneLmhMapper.deleteByPrimaryKey(facDto.getSceneFtrId());
            sttSceneLmhMapper.insertSelective(sttLmh);
            
          //如若因子为申赎净流出，基础选择标签值改变
            if(StringUtil.equals(facDto.getFtrId(), DictCodeListRsk.流动性风险因子_申赎净流出增加比例.getValue())){
                Map<String,Object> par = new HashMap<>();
                par.put("sceneId", dto.getSceneId());
                par.put("dictCode", DictCode.申赎净流出基础选择.getValue());
                SttSceneFtrtips sttFacTip = flowRiskFactroManage002Mapper.queryNetOutflow(par);
                SttSceneFtrtips newSttFacTip = new SttSceneFtrtips();
                newSttFacTip.setSceneFtrtipsId(sttFacTip.getSceneFtrtipsId());
                newSttFacTip.setParamValue(dto.getNetOutf());
                sttSceneFtrtipsMapper.updateByPrimaryKeySelective(newSttFacTip);
            }
        }
        for(FlowRiskSceneManage002Dto fac2Dto:dto.getFtrList2()){
            SttSceneLmh sttLmh = new SttSceneLmh();
            sttLmh.setSceneFtrId(fac2Dto.getSceneFtrId());//情景因子代码
            sttLmh.setFtrId(fac2Dto.getFtrId());//因子代码
            sttLmh.setSceneId(dto.getSceneId());//情景代码
            if(StringUtil.isNotEmpty(fac2Dto.getLightValue())){
                sttLmh.setLightValue(fac2Dto.getLightValue().divide(new BigDecimal(100)));//轻度
            }
            if(StringUtil.isNotEmpty(fac2Dto.getMediumValue())){
                sttLmh.setMediumValue(fac2Dto.getMediumValue().divide(new BigDecimal(100)));//中度
            }
            if(StringUtil.isNotEmpty(fac2Dto.getHeavyValue())){
                sttLmh.setHeavyValue(fac2Dto.getHeavyValue().divide(new BigDecimal(100)));//重度
            }
            //轻中重度因子值表
            sttSceneLmhMapper.deleteByPrimaryKey(fac2Dto.getSceneFtrId());
            sttSceneLmhMapper.insertSelective(sttLmh);
        }
        
    }
    
}
