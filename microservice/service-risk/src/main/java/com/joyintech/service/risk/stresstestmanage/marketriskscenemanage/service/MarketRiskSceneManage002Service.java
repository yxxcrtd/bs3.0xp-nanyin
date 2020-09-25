/*
 * 文件名：MarketRiskSceneManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dao.MarketRiskSceneManage002Mapper;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage003Dto;
import com.joyintech.tams.common.base.dao.SttSceneMapper;
import com.joyintech.tams.common.base.dao.SttSceneSuitMapper;
import com.joyintech.tams.common.base.dao.SttSceneTipsMapper;
import com.joyintech.tams.common.base.entity.SttScene;
import com.joyintech.tams.common.base.entity.SttSceneFactor;
import com.joyintech.tams.common.base.entity.SttSceneFtrtips;
import com.joyintech.tams.common.base.entity.SttSceneSuit;
import com.joyintech.tams.common.base.entity.SttSceneTips;
import com.joyintech.tams.common.base.entity.SttSceneTipsKey;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;


/**
 * 市场性风险-情景新建数据服务层-Service
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月4日
 * @see MarketRiskSceneManage002Service
 * @since 1.0
 */
@Service
public class MarketRiskSceneManage002Service extends BaseService<MarketRiskSceneManage002Dto> {
	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(MarketRiskSceneManage002Service.class);

    @Autowired
    private MarketRiskSceneManage002Mapper marketRiskSceneManage002Mapper;

    /**
     * sqlSessionFactory
     */
    @Autowired(required = false)
    private SqlSessionFactory sessionFactory;

    /**
     * 情景mapper
     */
    @Autowired
    private SttSceneMapper sttSceneMapper;
    
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
     * 流动性情景模板
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.STRESSTEST)
    @Transactional
    public MarketRiskSceneManage002Dto selectSceneTmpl(Map<String, Object> param) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        MarketRiskSceneManage002Dto dto = marketRiskSceneManage002Mapper.selectSceneTmpl(param);
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
    @Transactional
    public List<MarketRiskSceneManage002Dto> selectTmplIndByType(Map<String, Object> param) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<MarketRiskSceneManage002Dto> list = marketRiskSceneManage002Mapper.selectTmplIndByType(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    
    /**
     * 根据指标类型查找指标
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.STRESSTEST)
    @Transactional
    public List<MarketRiskSceneManage002Dto> queryIndexByType(Map<String, Object> param){
        return marketRiskSceneManage002Mapper.queryIndexByType(param);
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
    @DataSource(DataSourceType.STRESSTEST)
    @Transactional
    public List<MarketRiskSceneManage002Dto> queryScentNameCount(Map<String, Object> param){
        return marketRiskSceneManage002Mapper.queryScentNameCount(param);
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
    @DataSource(DataSourceType.STRESSTEST)
    @Transactional
    public MarketRiskSceneManage002Dto querySceneById(Map<String, Object> param){
    	MarketRiskSceneManage002Dto dto = marketRiskSceneManage002Mapper.querySceneById(param);
        return dto;
    }
    
    /**
     * 查询所有因子二级分类
     * <功能详细描述>
     * @param param sceneId传入则表示需要判断哪些分类已被选中
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.STRESSTEST)
    @Transactional
    public List<MarketRiskSceneFactorTypeDto> queryFactorTypeList(Map<String, Object> param){
    	String sceneId = (String) param.get("sceneId");
    	if(StringUtil.isEmpty(sceneId)){
    		return marketRiskSceneManage002Mapper.queryFactorTypeList(param);
    	}else{
    		return marketRiskSceneManage002Mapper.queryFactorTypeListById(param);
    	}
    }
    
    /**
     * 情景新建
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void insert(MarketRiskSceneManage002Dto dto) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        //情景表新增
        SttScene sttSc = new SttScene();
        sttSc.setSceneId(dto.getSceneId());//情景代码
        sttSc.setSceneName(dto.getSceneName());//情景名称
        sttSc.setSceneTpmlId(dto.getTmplId());
        sttSc.setIsTmpl(DictCodeList.是否_否.getValue());//是否模板
        sttSc.setStartDate(dto.getStartDate());     //区间开始时间
        sttSc.setEndDate(dto.getEndDate());         //区间结束时间
        sttSc.setRemark(dto.getRemark());//备注
        sttSc.setEffStatus(DictCodeListRsk.情景启用状态_启用.getValue());//启用
        sttSc.setInstitutionCode(dto.getInstitutionCode());
        sttSceneMapper.insertSelective(sttSc);
        
        //情景标签表新增-适用风险分类
        SttSceneTips sttTip = new SttSceneTips();
        sttTip.setSceneId(dto.getSceneId());//情景代码
        sttTip.setTipsCode(dto.getSceneId() + "01");
        sttTip.setTipsValue(dto.getRiskType());//标签值
        sttTip.setDictCode(DictCode.情景风险分类.getValue());//字典代码
        sttSceneTipsMapper.insertSelective(sttTip);
        //情景标签表新增-情景类型
        SttSceneTips sttTip2 = new SttSceneTips();
        sttTip2.setSceneId(dto.getSceneId());//情景代码
        sttTip2.setTipsCode(dto.getSceneId() + "02");
        sttTip2.setTipsValue(dto.getSceneType());//标签值
        sttTip2.setDictCode(DictCode.情景类型.getValue());//字典代码
        sttSceneTipsMapper.insertSelective(sttTip2);
        //适用产品
        if(StringUtil.isNotEmpty(dto.getSuitProduct())){
        	SttSceneTips sttTip3 = new SttSceneTips();
            sttTip3.setSceneId(dto.getSceneId());//情景代码
            sttTip3.setTipsCode(dto.getSceneId() + "03");
            sttTip3.setTipsValue(dto.getSuitProduct());//标签值
            sttTip3.setDictCode(DictCode.开放模式.getValue());//字典代码
            sttSceneTipsMapper.insertSelective(sttTip3);
        }
        for(MarketRiskSceneFactorTypeDto factorType : dto.getFtrTypeList()){		//遍历因子二级分类
        	if(factorType.isFtrChecked()){
        		SttSceneTips ftrTypeTip = new SttSceneTips();
            	ftrTypeTip.setSceneId(dto.getSceneId());//情景代码
            	ftrTypeTip.setTipsCode(IDGenerator.generate(12)+dto.getSceneId() + "04");
            	ftrTypeTip.setTipsValue(factorType.getFtrType2Code());//标签值
            	ftrTypeTip.setDictCode(DictCode.因子二级分类.getValue());//字典代码
                sttSceneTipsMapper.insertSelective(ftrTypeTip);
        	}
        	
        }
        //情景适用表新增
        SttSceneSuit sttSui = new SttSceneSuit();
        sttSui.setSeqNo(IDGenerator.generate(12).substring(0, 8));//流水号
        sttSui.setSceneId(dto.getSceneId());//情景代码
        sttSui.setParamCode(DictCode.情景风险分类.getValue());//参数代码
        sttSui.setParamValue(dto.getRiskType());//参数值
        sttSui.setDictCode(DictCode.情景风险分类.getValue());//字典代码
        sttSceneSuitMapper.insertSelective(sttSui);
        //关联情景因子
        insertSceneFactor(dto.getFtrInfoList(), sttSc);
        
        DynamicDataSourceHolder.clearDataSource();
    }
    
    /**
     * 关联情景因子
     * <功能详细描述>
     * @param mrkRiskSc003List
     * @param sttSc
     * @see
     * @since 1.0
     */
    public void insertSceneFactor(List<MarketRiskSceneManage003Dto> mrkRiskSc003List, SttScene sttSc){
    	//获取所有因子信息
    	if(mrkRiskSc003List == null){
    		return;
    	}
        List<SttSceneFactor> sttFacList = new ArrayList<SttSceneFactor>();
        List<SttSceneFtrtips> scFtrTipList = new ArrayList<>();
        for(MarketRiskSceneManage003Dto factorDto : mrkRiskSc003List){		//遍历因子
        	if("数据未准备".equals(factorDto.getChangeValue())){
        		factorDto.setChangeValue("");
        	}
        	//新建情景因子
        	SttSceneFactor sttFac = new SttSceneFactor();
        	sttFac.setSceneId(sttSc.getSceneId());//情景代码
        	sttFac.setFtrValueType(DictCodeListRsk.参数类型_数值.getValue());//因子值类型-参数类型
        	String sttFacId = IDGenerator.generate();//情景因子代码
        	sttFac.setSceneFtrId(sttFacId);//情景因子代码   
        	sttFac.setFtrId(factorDto.getFtrId());//因子代码(市场性风险是因子分类无具体因子代码)
        	sttFac.setFtrValueStruct(factorDto.getFtrValueStruct());
        	sttFac.setFtrValue(factorDto.getChangeValue());
        	sttFacList.add(sttFac);
        	//增加因子分类标签
        	SttSceneFtrtips scFtrTypeTips = new SttSceneFtrtips();     //情景因子分类
        	scFtrTypeTips.setSceneId(sttSc.getSceneId());
        	scFtrTypeTips.setSceneFtrId(sttFacId);//情景因子代码   
        	String sttFacTipsId = IDGenerator.generate();//情景因子代码
        	scFtrTypeTips.setSceneFtrtipsId(sttFacTipsId);		//情景因子标签id
        	scFtrTypeTips.setParamCode(DictCodeListRsk.参数类型_数据字典.getValue());
        	scFtrTypeTips.setParamValue(factorDto.getFtrTypeCode());
        	scFtrTypeTips.setDictCode(DictCode.风险因子管理_市场风险因子分类.getValue());
//        	scFtrTypeTips.setLevel();	//级别代码？？？,目前没用吧
        	scFtrTipList.add(scFtrTypeTips);
        	//增加因子二级分类标签
        	SttSceneFtrtips scFtrType2Tips = new SttSceneFtrtips();     //情景因子分类
        	scFtrType2Tips.setSceneId(sttSc.getSceneId());
        	scFtrType2Tips.setSceneFtrId(sttFacId);//情景因子代码   
        	String sttFac2TipsId = IDGenerator.generate();//情景因子代码
        	scFtrType2Tips.setSceneFtrtipsId(sttFac2TipsId);		//情景因子标签id
        	scFtrType2Tips.setParamCode(DictCodeListRsk.参数类型_数据字典.getValue());
        	scFtrType2Tips.setParamValue(factorDto.getFtrType2Code());
        	scFtrType2Tips.setDictCode(DictCode.因子二级分类.getValue());
//        	scFtrTypeTips.setLevel();	//级别代码？？？,目前没用吧
        	scFtrTipList.add(scFtrType2Tips);
        	//添加资产分类标签
        	//资产大类
        	if(StringUtil.isNotEmpty(factorDto.getAssetType1())){
        		SttSceneFtrtips scAssetTypeTip = new SttSceneFtrtips();
        		scAssetTypeTip.setSceneId(sttSc.getSceneId());
        		scAssetTypeTip.setSceneFtrId(sttFacId);//情景因子代码   
            	String scAssetTypeTipId = IDGenerator.generate();//情景因子代码
            	scAssetTypeTip.setSceneFtrtipsId(scAssetTypeTipId);		//情景因子标签id
            	scAssetTypeTip.setParamCode(DictCodeListRsk.参数类型_数据字典.getValue());
            	scAssetTypeTip.setParamValue(factorDto.getAssetType1());
            	scAssetTypeTip.setDictCode("ASSETTYPE1");
//            	scFtrMonthsTips.setLevel();	//级别代码,目前没用吧
            	scFtrTipList.add(scAssetTypeTip);
        	}
        	//一级资产
        	if(StringUtil.isNotEmpty(factorDto.getAssetType2())){
        		SttSceneFtrtips scAssetTypeTip = new SttSceneFtrtips();
        		scAssetTypeTip.setSceneId(sttSc.getSceneId());
        		scAssetTypeTip.setSceneFtrId(sttFacId);//情景因子代码   
            	String scAssetTypeTipId = IDGenerator.generate();//情景因子代码
            	scAssetTypeTip.setSceneFtrtipsId(scAssetTypeTipId);		//情景因子标签id
            	scAssetTypeTip.setParamCode(DictCodeListRsk.参数类型_数据字典.getValue());
            	scAssetTypeTip.setParamValue(factorDto.getAssetType2());
            	scAssetTypeTip.setDictCode("ASSETTYPE2");
//            	scFtrMonthsTips.setLevel();	//级别代码,目前没用吧
            	scFtrTipList.add(scAssetTypeTip);
        	}
        	//二级资产
        	if(StringUtil.isNotEmpty(factorDto.getAssetType3())){
        		SttSceneFtrtips scAssetTypeTip = new SttSceneFtrtips();
        		scAssetTypeTip.setSceneId(sttSc.getSceneId());
        		scAssetTypeTip.setSceneFtrId(sttFacId);//情景因子代码   
            	String scAssetTypeTipId = IDGenerator.generate();//情景因子代码
            	scAssetTypeTip.setSceneFtrtipsId(scAssetTypeTipId);		//情景因子标签id
            	scAssetTypeTip.setParamCode(DictCodeListRsk.参数类型_数据字典.getValue());
            	scAssetTypeTip.setParamValue(factorDto.getAssetType3());
            	scAssetTypeTip.setDictCode("ASSETTYPE3");
//            	scFtrMonthsTips.setLevel();	//级别代码,目前没用吧
            	scFtrTipList.add(scAssetTypeTip);
        	}
        	//添加评级代码
        	if(StringUtil.isNotEmpty(factorDto.getGradeCode())){
        		SttSceneFtrtips scGradeTip = new SttSceneFtrtips();
        		scGradeTip.setSceneId(sttSc.getSceneId());
        		scGradeTip.setSceneFtrId(sttFacId);//情景因子代码   
            	String scGradeTipId = IDGenerator.generate();//情景因子代码
            	scGradeTip.setSceneFtrtipsId(scGradeTipId);		//情景因子标签id
            	scGradeTip.setParamCode(DictCodeListRsk.参数类型_数据字典.getValue());
            	scGradeTip.setParamValue(factorDto.getGradeCode());
            	scGradeTip.setDictCode(DictCode.压测用债券评级结果.getValue());
//            	scFtrMonthsTips.setLevel();	//级别代码,目前没用吧
            	scFtrTipList.add(scGradeTip);
        	}
        	
        	//添加匹配时间和匹配天数标签，不管数据有没有准备
    		factorDto.setSceneId(sttSc.getSceneId());
    		factorDto.setSceneFtrId(sttFacId);
        	scFtrTipList.addAll(getSceneFtrTips(factorDto));
        	//添加因子变更标志
        	if(factorDto.isMixFtr()){
        		//添加因子是否变更标签
            	SttSceneFtrtips scFtrMixTips = new SttSceneFtrtips();     //情景因子分类
            	scFtrMixTips.setSceneId(sttSc.getSceneId());
            	scFtrMixTips.setSceneFtrId(sttFacId);//情景因子代码   
            	String scFtrMixTipsId = IDGenerator.generate();//情景因子代码
            	scFtrMixTips.setSceneFtrtipsId(scFtrMixTipsId);		//情景因子标签id
            	scFtrMixTips.setParamCode(DictCodeListRsk.参数类型_数值.getValue());
            	scFtrMixTips.setParamValue(DictCodeListRsk.是否_是.getValue());
            	scFtrMixTips.setDictCode("SCENE_FACTOR_MIX");
//            	scFtrMonthsTips.setLevel();	//级别代码,目前没用吧
            	scFtrTipList.add(scFtrMixTips);
        	}
        	//如果是曲线值，需要保存到曲线因子值表中,目前不需要
//        	if(DictCodeListRsk.风险因子管理_因子值结构_曲线.getValue().equals(sttFac.getFtrValueStruct())){
//        		insertSceneCurve(factorDto);
//        	}
        }
        //新增数据
        if(!StringUtil.isEmptyList(sttFacList))
        marketRiskSceneManage002Mapper.insertSceneFactorList(sttFacList);

        this.insertSceneFtrTipsByBatch(scFtrTipList);
    }

    /**
     * 情景编辑
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void update(MarketRiskSceneManage002Dto dto) {
      //情景表情景
        SttScene sttSce = new SttScene();
        sttSce.setSceneId(dto.getSceneId());//情景代码
        sttSce.setSceneName(dto.getSceneName());//情景名称
        sttSce.setStartDate(dto.getStartDate());     //区间开始时间
        sttSce.setEndDate(dto.getEndDate());         //区间结束时间
        sttSce.setRemark(dto.getRemark());//备注
        sttSceneMapper.updateByPrimaryKeySelective(sttSce);
        //因子二级分类不能修改，因此不需要更新

        //删除已关联的因子、因子标签、曲线因子值
        marketRiskSceneManage002Mapper.deleteSceneFactor(dto.getSceneId());
        insertSceneFactor(dto.getFtrInfoList(), sttSce);
    }
    
//    /*
//     * 保存到曲线因子表
//     */
//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param sttFac
//     * @see
//     * @since 1.0
//     */
//    public void insertSceneCurve(MarketRiskSceneManage003Dto sttFac){
//    	Map<String, Object> param = new HashMap<>();
//    	param.put("ftrId", sttFac.getFtrId());
//    	List<RiskFactor003Dto> facorValueList = queryFactorValueList(param);
//    	//保存数据到曲线因子表中
//		Map<String, Object> insertSceneCurveParam = new HashMap<>();
//		insertSceneCurveParam.put("sceneId", sttFac.getSceneId());
//		insertSceneCurveParam.put("sceneFtrId", sttFac.getSceneFtrId());
//		insertSceneCurveParam.put("ftrId", sttFac.getFtrId());
//		insertSceneCurveParam.put("list", facorValueList);
//		marketRiskSceneManage002Mapper.insertSceneCurveList(insertSceneCurveParam);
//    }
    
    /*
     * 获取需要保存到情景因子标签表的数据--已准备的因子的标签数据
     */
    public List<SttSceneFtrtips> getSceneFtrTips(MarketRiskSceneManage003Dto scFtrDto){
    	List<SttSceneFtrtips> scFtrTipList = new ArrayList<>();
    	//添加情景因子开始时间标签
    	SttSceneFtrtips scFtrStDateTips = new SttSceneFtrtips();     //情景因子分类
    	scFtrStDateTips.setSceneId(scFtrDto.getSceneId());
    	scFtrStDateTips.setSceneFtrId(scFtrDto.getSceneFtrId());//情景因子代码   
    	String scFtrStDateTipsId = IDGenerator.generate();//情景因子代码
    	scFtrStDateTips.setSceneFtrtipsId(scFtrStDateTipsId);		//情景因子标签id
    	scFtrStDateTips.setParamCode(DictCodeListRsk.参数类型_日期.getValue());
    	scFtrStDateTips.setParamValue(scFtrDto.getStartDate());
    	scFtrStDateTips.setDictCode("SCENE_FACTOR_START_DATE");
//    	scFtrStDateTips.setLevel();	//级别代码,目前没用吧
    	scFtrTipList.add(scFtrStDateTips);
    	//添加情景因子结束时间标签
    	SttSceneFtrtips scFtrEdDateTips = new SttSceneFtrtips();     //情景因子分类
    	scFtrEdDateTips.setSceneId(scFtrDto.getSceneId());
    	scFtrEdDateTips.setSceneFtrId(scFtrDto.getSceneFtrId());//情景因子代码   
    	String scFtrEdDateTipsId = IDGenerator.generate();//情景因子代码
    	scFtrEdDateTips.setSceneFtrtipsId(scFtrEdDateTipsId);		//情景因子标签id
    	scFtrEdDateTips.setParamCode(DictCodeListRsk.参数类型_日期.getValue());
    	scFtrEdDateTips.setParamValue(scFtrDto.getEndDate());
    	scFtrEdDateTips.setDictCode("SCENE_FACTOR_END_DATE");
//    	scFtrEdDateTips.setLevel();	//级别代码,目前没用吧
    	scFtrTipList.add(scFtrEdDateTips);
    	//添加情景因子天数标签
    	SttSceneFtrtips scFtrDaysTips = new SttSceneFtrtips();     //情景因子分类
    	scFtrDaysTips.setSceneId(scFtrDto.getSceneId());
    	scFtrDaysTips.setSceneFtrId(scFtrDto.getSceneFtrId());//情景因子代码   
    	String scFtrDaysTipsId = IDGenerator.generate();//情景因子代码
    	scFtrDaysTips.setSceneFtrtipsId(scFtrDaysTipsId);		//情景因子标签id
    	scFtrDaysTips.setParamCode(DictCodeListRsk.参数类型_数值.getValue());
    	scFtrDaysTips.setParamValue(scFtrDto.getDays());
    	scFtrDaysTips.setDictCode("SCENE_FACTOR_DAYS");
//    	scFtrMonthsTips.setLevel();	//级别代码,目前没用吧
    	scFtrTipList.add(scFtrDaysTips);
    	return scFtrTipList;
    }
    
    /**
     * 将大量数据分批执行插入操作。但是事务方面问题还未测试。
     * <功能详细描述>
     * @param scFtrTipList
     * @see
     * @since 1.0
     */
    public void insertSceneFtrTipsByBatch(List<SttSceneFtrtips> scFtrTipList){
        try (SqlSession session = sessionFactory.openSession(ExecutorType.BATCH, false)) {
            MarketRiskSceneManage002Mapper batchMapper = session.getMapper(MarketRiskSceneManage002Mapper.class);

            for(SttSceneFtrtips item : scFtrTipList){
                batchMapper.insertSceneFtrtipsList(item);
            }

            session.commit();
            session.clearCache();
            SqlSessionUtils.closeSqlSession(session, sessionFactory);
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("批量插入情景因子标签出错");
            throw e;
        }
    }

}
