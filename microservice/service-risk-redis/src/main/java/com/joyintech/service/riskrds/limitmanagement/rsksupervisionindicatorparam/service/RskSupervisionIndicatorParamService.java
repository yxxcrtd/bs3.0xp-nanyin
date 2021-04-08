package com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dao.RskEvent001Mapper;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent002Dto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.service.RskEventService;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dao.RskSupervisionIndicatorParamMapper;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.ParamConditionDto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam003Dto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam005Dto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.SqlDto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dao.RskSupervisionIndicatorMapper;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicatorDto;
import com.joyintech.tams.common.base.dao.RskIsupFixRecodeMapper;
import com.joyintech.tams.common.base.dao.RskIsupIndicatorMapper;
import com.joyintech.tams.common.base.dao.RskIsupIndicatorParaDtlMapper;
import com.joyintech.tams.common.base.dao.RskIsupIndicatorParaMapper;
import com.joyintech.tams.common.base.entity.RskEventItemRule;
import com.joyintech.tams.common.base.entity.RskEventParaRel;
import com.joyintech.tams.common.base.entity.RskIsupIndicatorPara;
import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtl;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 指数参数管理Service
 *
 * @author liuwei
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicatorParamService
 * @since 1.0
 */
@Service("backRskSupervisionIndicatorParamService")
public class RskSupervisionIndicatorParamService extends BaseService<RskSupervisionIndicatorParamDto> {

    /**
     * 指数参数管理mapper
     */
    @Autowired
    private RskSupervisionIndicatorParamMapper rskSupervisionIndicatorParamMapper;

    /**
     * 监督指标参数mapper
     */
    @Autowired
    private RskIsupIndicatorParaMapper rskIsupIndicatorParaMapper;

    /**
     * 监督指标参数明细mapper
     */
    @Autowired
    private RskIsupIndicatorParaDtlMapper rskIsupIndicatorParaDtlMapper;

    @Autowired
    private RskSupervisionIndicatorMapper rskSupervisionIndicatorMapper;

    @Autowired
    private RskIsupFixRecodeMapper rskIsupFixRecodeMapper;

    @Autowired
    private RskIsupIndicatorMapper rskIsupIndicatorMapper;
    /**
     * 风险事件service
     */
    @Autowired
    private RskEventService rskEventService;
    @Autowired
    private RskEvent001Mapper rskEvent001Mapper;

    private static final String paramPref = "${";

    private static final String paramSuff = "}";
    
    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<RskSupervisionIndicatorParamDto> selectForPage(int page, int length,
                                                               Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("FIX", BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
        pageParam.put("PLUGIN", BussinessDictCodeListRds.指标参数实现类型_类名.getValue());
        pageParam.put("SQL", BussinessDictCodeListRds.指标参数实现类型_SQL.getValue());
        pageParam.put("sysDate", DateUtil.format(DateUtil.platDateFromCache()));
        // wngayang1 二级法人改造  start
        if(MultilevelDataAuthorityUtil.isEnabled()){  
            if (!StringUtil.equals(LoginUserUtil.getUserName(), "admin")) {
                List<String> institutionLists = new ArrayList<>();
                institutionLists.addAll(MultilevelDataAuthorityUtil.getService().queryAuthorizedOrgCodeByUserName(LoginUserUtil.getUserName()));
                String institutionCode =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
                pageParam.put("institutionCode",institutionCode);
                pageParam.put("institutionList",institutionLists);
            }
        }
        Page<RskSupervisionIndicatorParamDto>  pageList = super.queryForPage(page, length,() -> rskSupervisionIndicatorParamMapper.select(pageParam));
        //不是admin用户不能修改 机构号为null的指标
        if (MultilevelDataAuthorityUtil.isEnabled() && !StringUtil.equals(LoginUserUtil.getUserName(), "admin")) {
            for (RskSupervisionIndicatorParamDto dto : pageList.getRoot()) {
                if (StringUtil.equals(dto.getParaType(), "FIX")) {
                    dto.setUpdateFlg(com.joyintech.framework.constant.DictCodeList.是否_是.getValue());
                } else {
                    dto.setUpdateFlg(com.joyintech.framework.constant.DictCodeList.是否_否.getValue());
                }
            }
        }else {
            for (RskSupervisionIndicatorParamDto dto : pageList.getRoot()) {
                 dto.setUpdateFlg(com.joyintech.framework.constant.DictCodeList.是否_是.getValue());
            }
        }  
        //wangyang1 二级法人改造 end 
        return pageList;
    }

    /**
     * 删除事件
     * 
     * @param dto dto
     * @return 删除结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public int delete(RskSupervisionIndicatorParamDto dto) {
        int i = 0;
        i = i + rskIsupIndicatorParaMapper.deleteByPrimaryKey(dto.getParaCode());
        i = i + rskSupervisionIndicatorParamMapper.delete(dto.getParaCode());
        Map<String, Object> map =new HashMap<String, Object>();
        map.put("paraCode", dto.getParaCode()); 
        i = i + rskSupervisionIndicatorParamMapper.deleteFixRecode(map);
        //---------add by 裴家俊 2019-10-21 start---------------
        if(StringUtil.isNotEmpty(dto.getParaCode())&&StringUtil.isNotEmpty(dto.getParaType())&&dto.getParaType().equals(BussinessDictCodeListRds.指标参数实现类型_自定义.getValue())){
			List<RskEvent002Dto> eventList = getEventCodeList(dto.getParaCode());
			if (!StringUtil.isEmptyList(eventList)) {
				for (RskEvent002Dto e : eventList) {
					//删除风险事件，风险事件特征值，风险关联指标参数
					rskEventService.delete(e);
				}
			}
		}
		//---------add by 裴家俊 2019-10-21 end---------------
		else {
			rskSupervisionIndicatorParamMapper.deleteEventRelPara(dto.getParaCode());
		}
        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeList.是否_是)) {
            redisRriter.dealRiskEvent();
        }
        return i;
    }

    private List<RskEvent002Dto> getEventCodeList(String paraCode) {
    	List<RskEvent002Dto> list = new ArrayList<>();
    	RskEvent002Dto event1 = new RskEvent002Dto();
    	event1.setEventCode("EVENT_ID_"+paraCode+"_05");
    	list.add(event1);
    	RskEvent002Dto event2 = new RskEvent002Dto();
    	event2.setEventCode("EVENT_ID_"+paraCode+"_02");
    	list.add(event2);
    	RskEvent002Dto event3 = new RskEvent002Dto();
    	event3.setEventCode("EVENT_ID_"+paraCode+"_06");
    	list.add(event3);
    	RskEvent002Dto event4 = new RskEvent002Dto();
    	event4.setEventCode("EVENT_ID_"+paraCode+"_08");
    	list.add(event4);
    	RskEvent002Dto event5 = new RskEvent002Dto();
    	event5.setEventCode("EVENT_ID_"+paraCode+"_03");
    	list.add(event5);
    	RskEvent002Dto event6 = new RskEvent002Dto();
    	event6.setEventCode("EVENT_ID_"+paraCode+"_01");
    	list.add(event6);
    	RskEvent002Dto event7 = new RskEvent002Dto();
    	event7.setEventCode("EVENT_ID_"+paraCode+"_07");
    	list.add(event7);
    	RskEvent002Dto event8 = new RskEvent002Dto();
    	event8.setEventCode("EVENT_ID_"+paraCode+"_04");
    	list.add(event8);
		return list;
	}

	/**
     * 保存事件
     * 
     * @param dto dto
     * @return 保存结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public int save(RskSupervisionIndicatorParamDto dto) {
        int i = 0;
        RskIsupIndicatorPara rskIsupIndicatorPara = new RskIsupIndicatorPara();
        BeanUtil.copyPropertiesIgnoreNull(rskIsupIndicatorPara, dto);
        i = i + rskIsupIndicatorParaMapper.insertSelective(rskIsupIndicatorPara);
        if (dto.getRskIsupIndicatorParaDtlList() != null
            && dto.getRskIsupIndicatorParaDtlList().size() > 0) {
            // 循环插入语言参数明细
            for (RskIsupIndicatorParaDtl e : dto.getRskIsupIndicatorParaDtlList()) {
            	//特殊字符转换
				e.setContent(RiskUtil.specialCharacterConversion(e.getContent(),true));
            	i = i + rskIsupIndicatorParaDtlMapper.insertSelective(e);
            }
        }
        return i;
    }

    /**
     * 修改或查看界面
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    public List<RskSupervisionIndicatorParamDto> selectInfo(String paraCode) {
    	List<RskSupervisionIndicatorParamDto> listRet = rskSupervisionIndicatorParamMapper.selectInfo(paraCode);
		listRet.forEach(en->{
				//特殊字符转换
			en.setContent(RiskUtil.specialCharacterConversion(en.getContent(),false));
		});
		return listRet;
    }

    /**
     * 保存事件
     * 
     * @param dto dto
     * @return 保存结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public int update(RskSupervisionIndicatorParamDto dto) {
    	//删除参数
    	rskIsupIndicatorParaMapper.deleteByPrimaryKey(dto.getParaCode());
    	//删除参数明细
        rskSupervisionIndicatorParamMapper.delete(dto.getParaCode());
        save(dto);
        return 1;
    }

    private RskSupervisionIndicatorDto getParamAssetType(RskSupervisionIndicatorDto entity,
                                                         RskSupervisionIndicatorParamDto paramEntity) {
        // 构建资产类型
        if (StringUtil.isNotEmpty(entity.getExpr())) {
            Set<String> paramList = new HashSet<String>();
            parseParamName(paramList, entity.getExpr());
            List<String> assetTypeT = new ArrayList<String>();
            List<String> assetSecTypeT = new ArrayList<String>();
            List<String> assetThrTypeT = new ArrayList<String>();

        }
        return entity;
    }

    // -----------add by 裴家俊 20190506-----start--增加分类维度-------
    private RskSupervisionIndicatorDto getParamDisplayClassify(RskSupervisionIndicatorDto entity,
                                                               RskSupervisionIndicatorParamDto paramEntity) {
        // 构建分类维度
        if (StringUtil.isNotEmpty(entity.getExpr())) {
            Set<String> paramList = new HashSet<String>();
            parseParamName(paramList, entity.getExpr());
        }
        return entity;
    }
    // -----------add by 裴家俊 20190506-----end--增加分类维度-------

    private void parseParamName(Set<String> paramList, String param) {

        if (StringUtil.isNotEmpty(param) && param.indexOf(paramPref) != -1
            && param.indexOf(paramSuff) != -1) {
            String paramPre = param.substring(param.indexOf(paramPref) + 2,
                param.indexOf(paramSuff));
            String exprNew = param.substring(param.indexOf(paramSuff) + 1);
            paramList.add(paramPre);
            parseParamName(paramList, exprNew);
        }
    }

    /**
     * 查询参数代码
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    public int selectParaCode(String paraCode) {
        return rskSupervisionIndicatorParamMapper.selectParaCode(paraCode);
    }

    /**
     * 是否在监督指标里配置
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    public int isConfigure(String paraCode) {
        return rskSupervisionIndicatorParamMapper.isConfigure(paraCode);
    }

    @Transactional(rollbackFor = Throwable.class)
    public int insertFixRecode(RskSupervisionIndicatorParam003Dto dto) {
    	 RskIsupIndicatorPara record = new RskIsupIndicatorPara();
         BeanUtil.copyPropertiesIgnoreNull(record, dto);
         record.setIsForbid(DictCodeList.是否_否.getValue());
 		 record.setIsUpRedis(DictCodeList.是否_否.getValue());
 		 record.setParaType(BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
         rskIsupIndicatorParaMapper.insert(record);
         if (MultilevelDataAuthorityUtil.isEnabled() && StringUtil.isNotEmpty(dto.getInstitutionCode())) {
             String [] institutionCodes = dto.getInstitutionCode().split(",");
             for (String institutionCode : institutionCodes) {
               dto.setInstitutionCode(institutionCode);
               rskIsupFixRecodeMapper.insert(dto);
             }
         }else { 
             dto.setInstitutionCode(BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
             rskIsupFixRecodeMapper.insert(dto);
         } 
         return 1;
    }

    @Transactional(rollbackFor = Throwable.class)
    public int updateFixRecode(RskSupervisionIndicatorParam003Dto dto) {
    	 rskIsupIndicatorParaMapper.deleteByPrimaryKey(dto.getParaCode());
         RskIsupIndicatorPara record = new RskIsupIndicatorPara();
         BeanUtil.copyPropertiesIgnoreNull(record, dto);
         record.setIsForbid(DictCodeList.是否_否.getValue());
         record.setParaType(BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
         rskIsupIndicatorParaMapper.insert(record); 
         if (!StringUtil.isEmptyList(dto.getSelectedItem())) {
             for (RskSupervisionIndicatorParam003Dto e : dto.getSelectedItem()) {
                 Map<String, Object> map =new HashMap<String, Object>();
                 map.put("paraCode", e.getParaCode()); 
                 map.put("institutionCode",StringUtil.isNotEmpty(e.getInstitutionCode())?e.getInstitutionCode():BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
                 rskSupervisionIndicatorParamMapper.deleteFixRecode(map);
             }
             for (RskSupervisionIndicatorParam003Dto e : dto.getSelectedItem()) {
                 e.setInstitutionCode(StringUtil.isNotEmpty(e.getInstitutionCode())?e.getInstitutionCode():BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
                 rskIsupFixRecodeMapper.insert(e);
             }
         }
         return 1;
     }

    public List<RskSupervisionIndicatorParam003Dto> selectFixRecode(String paraCode) {
        Map<String, Object> param = new HashMap<>();
        // wngayang1 二级法人改造  start
        if(MultilevelDataAuthorityUtil.isEnabled()){  
            List<String> instCodeList = LoginUserUtil.getTreeInstCode();
            String institutionCode =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            param.put("institutionCode",institutionCode);
            param.put("institutionList",instCodeList);
        }
        //wangyang1 二级法人改造 end
        param.put("paraCode", paraCode);
        return rskSupervisionIndicatorParamMapper.selectFixRecode(param);
    }
    
    public List<RskSupervisionIndicatorParam003Dto> selectFixRecode2(String paraCode) {
        List<RskSupervisionIndicatorParam003Dto> list = new ArrayList<>();
        RskSupervisionIndicatorParam003Dto dto003= new RskSupervisionIndicatorParam003Dto();
        RskIsupIndicatorPara para = rskIsupIndicatorParaMapper.selectByPrimaryKey(paraCode);
        BeanUtil.copyPropertiesIgnoreNull(dto003,para);
        list.add(dto003);
        return list;
    }
    
    public boolean isExistParaName(Map<String, Object> param) {
        return rskSupervisionIndicatorParamMapper.isExistParaName(param) > 0;
    }

    @Transactional(rollbackFor = Throwable.class)
    public int insertCustomParam(RskSupervisionIndicatorParam005Dto dto) {
    	 RskIsupIndicatorPara record = new RskIsupIndicatorPara();
         BeanUtil.copyPropertiesIgnoreNull(record, dto);
         record.setIsForbid(DictCodeList.是否_否.getValue());
         // 插入参数表
        // 插入参数表
        rskIsupIndicatorParaMapper.insert(record);
        List<RskSupervisionIndicatorParam005Dto> paraDtl = getParaDtlList(dto);
        for(RskSupervisionIndicatorParam005Dto e:paraDtl){
        	rskIsupIndicatorParaDtlMapper.insert(e);
        }
        //生成风险事件
        Map<String,Object> params = getListEvent(dto);
        List<RskEvent002Dto> eventList = (List)params.get("eventList");
        List<RskEventParaRel> relList = (List)params.get("relList");
        if(!StringUtil.isEmptyList(eventList)&&!StringUtil.isEmptyList(relList)){
        	//插入风险事件
        	for(RskEvent002Dto e:eventList){
            	rskEventService.insert(e);
            }
        	//插入风险事件关联指标参数表
        	rskEvent001Mapper.insertParaList(relList);
        }
        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeList.是否_是)) {
            redisRriter.dealRiskEvent();
         // 更新Redis
            ImplCond cond2 = new ImplCond();
            cond2.setParamCodeArray(dto.getParaCode());
            cond2.setDel(false);
            cond2.setIsUpdParVal(true);
            cond2.setPlateDate(DateUtil.parse(DateUtil.getSysDate()));
            redisRriter.dealRiskIndParam(cond2);
        }
        return 1;
    }
    public List<RskSupervisionIndicatorParam005Dto> getParaDtlList(RskSupervisionIndicatorParam005Dto dto) {
		String content = dto.getContent();
		List<RskSupervisionIndicatorParam005Dto> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JsonUtils.json2Object(content,Map.class);
		if(map!=null){
			Map<String,Object> paramSql = getParam(map);
			paramSql.forEach((key,value) -> {
				RskSupervisionIndicatorParam005Dto e = new RskSupervisionIndicatorParam005Dto();
				dto.getContent();
				@SuppressWarnings("unchecked")
				Map<String,Object> para = JsonUtils.json2Object(content,Map.class);
				para.put("sql", (String)value);
				dto.setContent(JsonUtils.object2Json(para));
				BeanUtil.copyPropertiesIgnoreNull(e, dto);
				e.setParaGranularity(key);
				list.add(e);
			});
		}
		return list;
	}

	private Map<String,Object> getParam(Map<String, Object> map) {
		List<SqlDto> fromList  = getSql((String)map.get("modelType"));
		Map<String,Object> sqlMap = new HashMap<>();
		if(!StringUtil.isEmptyList(fromList)){
			String select = "";
			List<ParamConditionDto> whereList = new ArrayList<>();
			if(StringUtil.equals(map.get("modelType"), BussinessDictCodeListRds.参数模板_复合模板.getValue())){
				select = (String)map.get("value");
				whereList = getWhereList(map);
			}else if(StringUtil.equals(map.get("modelType"), BussinessDictCodeListRds.参数模板_前N日净资产市值.getValue())){
				select = BussinessDictCodeListRds.复合模板取值类型_市值.getValue();
				//生成条件
				ParamConditionDto where = new ParamConditionDto();
				String code = "cdate";
				//获取变量值
				String variable = (String)map.get("valueRange");
				String value = getValue(variable,(String)map.get("customDate"));
				where.setCode(code);
				where.setValue(value);
				where.setLinkType(BussinessDictCodeListRds.运算符_等于.getValue());
				whereList.add(where);
				
			}else{
				select = BussinessDictCodeListRds.复合模板取值类型_成本.getValue();
				//生成条件
				ParamConditionDto where = new ParamConditionDto();
				String code = "cdate";
				//获取变量值
				String variable = (String)map.get("valueRange");
				String value = getValue(variable,(String)map.get("customDate"));
				where.setCode(code);
				where.setValue(value);
				where.setLinkType(BussinessDictCodeListRds.运算符_等于.getValue());
				whereList.add(where);
			}
			String sqlWhere = "1=1";
			//所有条件项不为空
			if(!StringUtil.isEmptyList(whereList)){
				List<ParamConditionDto> assetList = new ArrayList<ParamConditionDto>();
				List<ParamConditionDto> asset2List = new ArrayList<ParamConditionDto>();
				List<ParamConditionDto> deptTypeList = new ArrayList<ParamConditionDto>();
				List<ParamConditionDto> deptType2List = new ArrayList<ParamConditionDto>();
				List<ParamConditionDto> windAssetTypeList = new ArrayList<ParamConditionDto>();
				List<ParamConditionDto> windAssetType2List = new ArrayList<ParamConditionDto>();
				List<ParamConditionDto> industryList = new ArrayList<ParamConditionDto>();
				List<ParamConditionDto> industry2List = new ArrayList<ParamConditionDto>();
				List<ParamConditionDto> otherList = new ArrayList<ParamConditionDto>();
				
				for(int i=0;i<whereList.size();i++){
					ParamConditionDto e = whereList.get(i);
					//将所有资产类型的条件取出(由于要用or判断)
					if(StringUtil.equals(e.getCode(), "assetType")){
						assetList.add(e);
						asset2List.add(whereList.get(i+1));
					}//将所有中债类型类型的条件取出(由于要用or判断)
					else if(StringUtil.equals(e.getCode(), "deptType")){
						deptTypeList.add(e);
						deptType2List.add(whereList.get(i+1));
					}//将所有wind类型的条件取出(由于要用or判断)
					else if(StringUtil.equals(e.getCode(), "windAssetType")){
						windAssetTypeList.add(e);
						windAssetType2List.add(whereList.get(i+1));
					}//将所有行业类型的条件取出(由于要用or判断)
					else if(StringUtil.equals(e.getCode(), "industry")){
						industryList.add(e);
						industry2List.add(whereList.get(i+1));
					}//将剩下的条件取出(由于要用and判断)
					else if(!StringUtil.equals(e.getCode(), "assetType")&&!StringUtil.equals(e.getCode(), "assetType2")
							&&!StringUtil.equals(e.getCode(), "industry")&&!StringUtil.equals(e.getCode(), "industry2")
							&&!StringUtil.equals(e.getCode(), "deptType")&&!StringUtil.equals(e.getCode(), "deptType2")
							&&!StringUtil.equals(e.getCode(), "windAssetType")&&!StringUtil.equals(e.getCode(), "windAssetType2")){
						otherList.add(e);
					}
				}
				
				if(!StringUtil.isEmptyList(assetList)){
					sqlWhere = getSqlWhere(sqlWhere,assetList,asset2List);
				}
				if(!StringUtil.isEmptyList(deptTypeList)){
					sqlWhere = getSqlWhere(sqlWhere,deptTypeList,deptType2List);
				}
				if(!StringUtil.isEmptyList(windAssetTypeList)){
					sqlWhere = getSqlWhere(sqlWhere,windAssetTypeList,windAssetType2List);
				}
				if(!StringUtil.isEmptyList(industryList)){
					sqlWhere = getSqlWhere(sqlWhere,industryList,industry2List);
				}
				if(!StringUtil.isEmptyList(otherList)){
					for(ParamConditionDto e:otherList){
						sqlWhere = StringUtil.isNotEmpty(e.getValue())?sqlWhere+" and "+e.getCode()+" "+e.getLinkType()+" "+e.getValue():sqlWhere;
					}
				}
			}
			/*if(!StringUtil.isEmptyList(whereList)){
				for(ParamConditionDto e:whereList){
					sqlWhere = sqlWhere+" and "+e.getCode()+" "+e.getLinkType()+" "+e.getValue();
				}
			}*/
			for(SqlDto from:fromList){
				if(StringUtil.isNotEmpty(from.getContent())){
					from.setContent(from.getContent().replaceAll("\n", " "));
					from.setContent(from.getContent().replaceAll("\r", " "));
				}
				String sql = "";
				if(StringUtil.equals(from.getParaGranularity(), BussinessDictCodeListRds.参数粒度_事后_日终.getValue())){
					sql = "select nvl(sum("+select+"),0) from ("+from.getContent()+") where "+sqlWhere;
				}else if(StringUtil.equals(from.getParaGranularity(), BussinessDictCodeListRds.参数粒度_事后_日间.getValue())){
					sql = "select nvl(sum("+select+"),0)"+" from ("+from.getContent()+") where "+sqlWhere;
				}else{
					sql = "select portfolioId RKEY ,nvl(sum("+select+"),0) RVALUE from ("+from.getContent()+") where "+sqlWhere+" group by portfolioId";
				}
				sqlMap.put(from.getParaGranularity(), sql);
			}
		}
		
		return sqlMap;
	}

	private String getSqlWhere(String sqlWhere, List<ParamConditionDto> list, List<ParamConditionDto> list2) {
		for(int i=0;i<list.size();i++){
			//一级类型
			ParamConditionDto e = list.get(i);
			//二级类型
			ParamConditionDto d = list2.get(i);
			//如果资产类型的条件有一个以上，则用or连接
			if(list.size()>1){
				if(i==0){
					sqlWhere = StringUtil.isNotEmpty(e.getValue())?sqlWhere+" and (("+e.getCode()+" "+e.getLinkType()+" "+e.getValue():sqlWhere;
					sqlWhere = StringUtil.isNotEmpty(d.getValue())?sqlWhere+" and "+d.getCode()+" "+d.getLinkType()+" "+d.getValue()+")":sqlWhere+")";
				}else if(i==list.size()-1){
					sqlWhere = StringUtil.isNotEmpty(e.getValue())?sqlWhere+" or ("+e.getCode()+" "+e.getLinkType()+" "+e.getValue():sqlWhere;
					sqlWhere = StringUtil.isNotEmpty(d.getValue())?sqlWhere+" and "+d.getCode()+" "+d.getLinkType()+" "+d.getValue()+"))":sqlWhere+"))";
				}else{
					sqlWhere = StringUtil.isNotEmpty(e.getValue())?sqlWhere+" or ("+e.getCode()+" "+e.getLinkType()+" "+e.getValue():sqlWhere;
					sqlWhere = StringUtil.isNotEmpty(d.getValue())?sqlWhere+" and "+d.getCode()+" "+d.getLinkType()+" "+d.getValue()+")":sqlWhere+")";
				}
			}
			else{
				sqlWhere = StringUtil.isNotEmpty(e.getValue())?sqlWhere+" and "+e.getCode()+" "+e.getLinkType()+" "+e.getValue():sqlWhere;
				sqlWhere = StringUtil.isNotEmpty(d.getValue())?sqlWhere+" and "+d.getCode()+" "+d.getLinkType()+" "+d.getValue():sqlWhere;
			}
		}
		return sqlWhere;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<ParamConditionDto> getWhereList(Map<String, Object> map) {
		List<ParamConditionDto> whereList = new ArrayList<>();
		if(StringUtil.isNotEmpty(map.get("assetType"))&&StringUtil.isNotEmpty(map.get("assetDetailType"))){
			Map<String,Object> assetDetailTypeMap = (Map)map.get("assetDetailType");
    		List listAssetDetailType = (List)assetDetailTypeMap.get("value");
    		for(int i=0;i<listAssetDetailType.size();i++){
    			List<ParamConditionDto> list = new ArrayList<>();
    			if(StringUtil.equals(map.get("assetType"),BussinessDictCodeListRds.资产类型分类_资管分类.getValue())){
    				Map<String,Object> mmp = (Map)listAssetDetailType.get(i);
    				String parent = getString((String)mmp.get("parent"));
    				String id = getString((String)mmp.get("id"));
    				if(StringUtil.equals((String)mmp.get("parent"), BussinessDictCodeListRds.资管_计划类资产.getValue())){
    					// 一级分类条件
	    				ParamConditionDto where1 = new ParamConditionDto();
	    				// 二级分类条件
	    				ParamConditionDto where2 = new ParamConditionDto();
	    				where1.setValue("("+"'"+BussinessDictCodeListRds.系统资产大类_计划类资产_净值型.getValue()+"'"+","+"'"+BussinessDictCodeListRds.系统资产大类_计划类资产_利率型.getValue()+"'"+","+"'"+BussinessDictCodeListRds.系统资产大类_计划类资产_货币型.getValue()+"'"+")");
	    				if(StringUtil.isNotEmpty(id)){
	    					where2.setValue("("+id+")");
	    				}
	    				where1.setCode("assetType");
	    				where2.setCode("investNature");
	    				list.add(where1);
	    				list.add(where2);
	    				
					}else if(StringUtil.equals((String)mmp.get("parent"), BussinessDictCodeListRds.资管_基金类资产.getValue())){
						// 一级分类条件
	    				ParamConditionDto where1 = new ParamConditionDto();
	    				// 二级分类条件
	    				ParamConditionDto where2 = new ParamConditionDto();
	    				where1.setValue("("+"'"+BussinessDictCodeListRds.系统资产大类_货币基金资产.getValue()+"'"+","+"'"+BussinessDictCodeListRds.系统资产大类_非货币基金资产.getValue()+"'"+")");
	    				if(StringUtil.isNotEmpty(id)){
	    					where2.setValue("("+id+")");
	    				}
	    				where1.setCode("assetType");
	    				where2.setCode("assetType2");
	    				list.add(where1);
	    				list.add(where2);
					}else if(StringUtil.equals((String)mmp.get("parent"), BussinessDictCodeListRds.资管_存款类资产.getValue())){
						// 一级分类条件
	    				ParamConditionDto where1 = new ParamConditionDto();
	    				// 二级分类条件
	    				ParamConditionDto where2 = new ParamConditionDto();
	    				where1.setValue("("+"'"+BussinessDictCodeListRds.系统资产大类_存款类资产.getValue()+"'"+")");
	    				if(StringUtil.isNotEmpty(id)){
	    					where2.setValue("("+id+")");
	    				}
	    				where1.setCode("assetType");
	    				where2.setCode("assetType2");
	    				list.add(where1);
	    				list.add(where2);
					}else if(StringUtil.equals((String)mmp.get("parent"), BussinessDictCodeListRds.资管_债券类资产.getValue())){
						// 一级分类条件
	    				ParamConditionDto where1 = new ParamConditionDto();
	    				// 二级分类条件
	    				ParamConditionDto where2 = new ParamConditionDto();
	    				where1.setValue("("+"'"+BussinessDictCodeListRds.系统资产大类_债券类资产.getValue()+"'"+")");
	    				if(StringUtil.isNotEmpty(id)){
	    					where2.setValue("("+id+")");
	    				}
	    				where1.setCode("assetType");
	    				where2.setCode("assetType2");
	    				list.add(where1);
	    				list.add(where2);
					}else if(StringUtil.equals((String)mmp.get("parent"), BussinessDictCodeListRds.资管_项目类资产.getValue())){
						// 一级分类条件
	    				ParamConditionDto where1 = new ParamConditionDto();
	    				// 二级分类条件
	    				ParamConditionDto where2 = new ParamConditionDto();
	    				where1.setValue("("+"'"+BussinessDictCodeListRds.系统资产大类_项目类资产.getValue()+"'"+")");
	    				if(StringUtil.isNotEmpty(id)){
	    					where2.setValue("("+id+")");
	    				}
	    				where1.setCode("assetType");
	    				where2.setCode("assetType2");
	    				list.add(where1);
	    				list.add(where2);
					}else if(StringUtil.equals((String)mmp.get("parent"), BussinessDictCodeListRds.资管_质押式回购.getValue())){
						// 一级分类条件
	    				ParamConditionDto where1 = new ParamConditionDto();
	    				// 二级分类条件
	    				ParamConditionDto where2 = new ParamConditionDto();
	    				where1.setValue("("+"'"+BussinessDictCodeListRds.系统资产大类_债券质押式回购交易.getValue()+"'"+")");
	    				if(StringUtil.isNotEmpty(id)){
	    					where2.setValue("("+id+")");
	    				}
	    				where1.setCode("assetType");
	    				where2.setCode("assetType2");
	    				list.add(where1);
	    				list.add(where2);
					}
    				for(ParamConditionDto e:list){
    					e.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
    					whereList.add(e);
    				}
    			}else{
    				Map<String,Object> mmp = (Map)listAssetDetailType.get(i);
    				// 一级分类条件
    				ParamConditionDto where1 = new ParamConditionDto();
    				// 二级分类条件
    				ParamConditionDto where2 = new ParamConditionDto();
    				String parent = getString((String)mmp.get("parent"));
    				String id = getString((String)mmp.get("id"));
    				where1.setValue("("+parent+")");
    				if(StringUtil.isNotEmpty(id)){
    					where2.setValue("("+id+")");
    				}
    				where1.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
    				where2.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
    				if(StringUtil.equals(map.get("assetType"),BussinessDictCodeListRds.资产类型分类_授权分类.getValue())){
    					
    				}else if(StringUtil.equals(map.get("assetType"),BussinessDictCodeListRds.资产类型分类_中债分类.getValue())){
    					where1.setCode("deptType");
    					where2.setCode("deptType2");
    					// 一级分类条件
        				ParamConditionDto where3 = new ParamConditionDto();
        				// 二级分类条件
        				ParamConditionDto where4 = new ParamConditionDto();
        				where3.setCode("assetType");
        				where4.setCode("assetType2");
        				where3.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
        				where4.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
        				where3.setValue("("+"'"+BussinessDictCodeListRds.系统资产大类_项目类资产.getValue()+"'"+")");
        				whereList.add(where3);
        				whereList.add(where4);
    				}else if(StringUtil.equals(map.get("assetType"),BussinessDictCodeListRds.资产类型分类_债券wind分类.getValue())){
    					where1.setCode("windAssetType");
    					where2.setCode("windAssetType2");
    				}
    				whereList.add(where1);
    				whereList.add(where2);
    			}
			}
		}
		if(StringUtil.isNotEmpty(map.get("category"))&&StringUtil.isNotEmpty(map.get("industryType"))){
			Map<String,Object> industryType = (Map)map.get("industryType");
    		List industry = (List)industryType.get("value");
    		for(int i=0;i<industry.size();i++){
    			Map<String,Object> mmp = (Map)industry.get(i);
    			ParamConditionDto where1 = new ParamConditionDto();
    			ParamConditionDto where2 = new ParamConditionDto();
    			String parent = getString((String)mmp.get("parent"));
				String id = getString((String)mmp.get("id"));
				if(StringUtil.isNotEmpty(parent)){
					where1.setValue("("+parent+")");
				}
				if(StringUtil.isNotEmpty(id)){
					where2.setValue("("+id+")");
				}
				where1.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
				where2.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
    			where1.setCode("industry");
    			where2.setCode("industry2");
    			whereList.add(where1);
				whereList.add(where2);
    		}
		}
		if(StringUtil.isNotEmpty(map.get("bodyGradeType"))&&StringUtil.isNotEmpty(map.get("bodyGrade"))){
			List<String> bodyGradeList = (List)map.get("bodyGrade");
    		String bodyGrade = bodyGradeList.toString();
    		bodyGrade = bodyGrade.substring(bodyGrade.indexOf("[")+1,bodyGrade.length());
    		bodyGrade = bodyGrade.substring(0, bodyGrade.lastIndexOf("]"));
    		bodyGrade = getString(bodyGrade);
			ParamConditionDto where = new ParamConditionDto();
			where.setCode("bodyGradeType");
			where.setValue("("+bodyGrade+")");
			where.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
			whereList.add(where);
		}
		if(StringUtil.isNotEmpty(map.get("bondGradeType"))&&StringUtil.isNotEmpty(map.get("bondGrade"))){
			List<String> bondGradeList = (List)map.get("bondGrade");
    		String bondGrade = bondGradeList.toString();
    		//去除[]
    		bondGrade = bondGrade.substring(bondGrade.indexOf("[")+1,bondGrade.length());
    		bondGrade = bondGrade.substring(0, bondGrade.lastIndexOf("]"));
    		bondGrade = getString(bondGrade);
    		ParamConditionDto where = new ParamConditionDto();
			where.setCode("bondGradeType");
			where.setValue("("+bondGrade+")");
			where.setLinkType(BussinessDictCodeListRds.运算符_包含.getValue());
			whereList.add(where);
		}
		if(StringUtil.isNotEmpty(map.get("mdateRange"))){
			String variable = (String)map.get("mdateRange");
			String value = "";
			if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_自定义.getValue(), variable)){
				if(StringUtil.equals((String)map.get("mdateType"), BussinessDictCodeListRds.日期类型_自然日.getValue())){
					value = getValue(variable,(String)map.get("customDate"));
				}else if(StringUtil.equals((String)map.get("mdateType"), BussinessDictCodeListRds.日期类型_交易日.getValue())){
					//如果是交易日，日期要向后移
					value = "(SELECT cdate FROM (SELECT CDATE,ROW_NUMBER() OVER (ORDER BY CDATE ASC  ) aaa "+
				"FROM MST_HOLIDAY LEFT JOIN FIN_PRODUCT ft ON ft.CALENDAR_ID = MST_HOLIDAY.CALENDAR_ID WHERE CDATE >=date'${valDate}' AND IS_HOLIDAY ='0') "+
							"WHERE  aaa="+(String)map.get("customDate")+")";
				}
			}else{
				value = getValue(variable,(String)map.get("customDate"));
			}
			
			String code = "mdate";
			ParamConditionDto where = new ParamConditionDto();
			where.setCode(code);
			where.setValue(value);
			where.setLinkType(BussinessDictCodeListRds.运算符_小于等于.getValue());
			whereList.add(where);
		}
		return whereList;
	}

	private String getString(String string) {
		if(StringUtil.isNotEmpty(string)){
			String[] strs = string.split(",");
			List<String> list = new ArrayList<>();
			for(String s:strs){
				list.add("'"+s+"'");
			}
			string = list.toString();
			string = string.substring(string.indexOf("[")+1,string.length());
			string = string.substring(0, string.lastIndexOf("]"));
		}
		return string;
	}

	private String getValue(String variable,String customDate) {
		String str = "";
		if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_上一日.getValue(), variable)){
			str = "date'"+"${valDate}"+"'"+"-1";
		}else if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_上月末.getValue(), variable)){
			str = "last_day(add_months(date'"+"${valDate}"+"',-1))";
		}else if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_上季度末.getValue(), variable)){
			str = "add_months(trunc(date'"+"${valDate}"+"','q'),0)-1";
		}else if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_一年内.getValue(), variable)){
			str = "add_months(date'"+"${valDate}"+"',12)";
		}else if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_半年内.getValue(), variable)){
			str = "add_months(date'"+"${valDate}"+"',6)";
		}else if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_一季度内.getValue(), variable)){
			str = "add_months(date'"+"${valDate}"+"',3)";
		}else if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_一月内.getValue(), variable)){
			str = "add_months(date'"+"${valDate}"+"',1)";
		}else if(StringUtil.equals(BussinessDictCodeListRds.日期范围类型_自定义.getValue(), variable)){
			str = "date'"+"${valDate}"+"'"+"-"+customDate;
		}
		String value = str;
		return value;
	}


    private Map<String,Object> getListEvent(RskSupervisionIndicatorParam005Dto dto) {
    	String content = dto.getContent();
    	Map<String,Object> params = new HashMap<>();
    	//{modelType=01, value=QTY, assetType=01, category=WIND, 
//    	"assetDetailType":{"text":{"id":["SUPERVISE_ASSET_TYPE.1","CHB_DEPO_ASSET_TYPE.1002", "CHB_DEPO_ASSET_TYPE.1003","CHB_DEPO_ASSET_TYPE.1004","CHB_DEPO_ASSET_TYPE.1005","SUPERVISE_ASSET_TYPE.2","CHB_INTET_BANK_DEPO.2501","CHB_INTET_BANK_DEPO.2502"],
//    	                            "name":["现金及银行存款","本行存款","他行存款","本行发行的大额存单","他行发行的大额存单","同业存单","本行发行的同业存单","他行发行的同业存单"]},
//    	                    "value":[{"parent":"1","id":"1002,1003,1004,1005" },{"parent":"2", "id":"2501,2502" }]},
//    	"industryType":{"text":{"id":["01","A01","A02","A03","A04","A05","02","B06","B07","B08","B09","B10","B11","B12"],
//    	                        "name":["农、林、牧、渔业", "农业","林业","畜牧业","渔业","农、林、牧、渔服务业","采矿业","煤炭开采和洗选业","石油和天然气开采业","黑色金属矿采选业","有色金属矿采选业","非金属矿采选业","开采辅助活动","其他采矿业"]},
//    	                 "value":[{"parent":"01","id":"A01,A02,A03,A04,A05" },{"parent":"02","id":"B06,B07,B08,B09,B10,B11,B12" }]}, 
        //bodyGradeType=01, bodyGrade=[02, 03, 05], bondGradeType=01, bondGrade=[04, 06, 07], mdateType=01, mdateRange=Y}
        @SuppressWarnings("unchecked")
		Map<String,Object> map = JsonUtils.json2Object(content,Map.class);
        //插入风险事件
        if(map!=null&&StringUtil.equals(map.get("modelType"), BussinessDictCodeListRds.参数模板_复合模板.getValue())){
        	List<RskEvent002Dto> list = new ArrayList<>();
        	List<RskEventParaRel> relList = new ArrayList<>();
        	RskEvent002Dto event1 = new RskEvent002Dto();
        	event1.setEventCode("EVENT_ID_"+dto.getParaCode()+"_05");
        	event1.setEventName(dto.getParaName()+"-买入预占");
        	event1.setIsCalcIndicator(BussinessDictCodeListRds.是否_是.getValue());
        	String eventBuyCalc = CacheUtil.getSysParam(BussinessConstantRds.event_buy_calc);
        	if(StringUtil.isNotEmpty(eventBuyCalc)){
        		//生成买入预占风险事件
        		map.put("eventCode", event1.getEventCode());
            	List<List<RskEventItemRule>> listItem1 = getItemList(map,eventBuyCalc);
            	for(int i=0;i<listItem1.size();i++){
            		RskEventItemRule rule1 = getEventTypeItem(event1.getEventCode(),"0,3",String.valueOf(i+1));
                	listItem1.get(i).add(rule1);
            	}
            	event1.setParamGroup(listItem1);
        	}
        	list.add(event1);
        	//生成关联参数
        	RskEventParaRel rel1 = new RskEventParaRel();
        	rel1.setEventCode(event1.getEventCode());
        	rel1.setParaCode(dto.getParaCode());
        	rel1.setParaGranularity("02");
        	rel1.setRelType(BussinessDictCodeListRds.操作符_加.getValue());
        	if(StringUtil.equals(map.get("value"), BussinessDictCodeListRds.复合模板取值类型_数量.getValue())){
        		rel1.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_数量.getValue());
        		
        	}else{
        		rel1.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	}
        	relList.add(rel1);
        	//生成买入实占风险事件
        	RskEvent002Dto event2 = new RskEvent002Dto();
        	event2.setEventCode("EVENT_ID_"+dto.getParaCode()+"_07");
        	event2.setEventName(dto.getParaName()+"-买入实占");
        	event2.setIsCalcIndicator(BussinessDictCodeListRds.是否_是.getValue());
        	if(StringUtil.isNotEmpty(eventBuyCalc)){
        		map.put("eventCode", event2.getEventCode());
        		List<List<RskEventItemRule>> listItem2 = getItemList(map,eventBuyCalc); 
        		for(int i=0;i<listItem2.size();i++){
            		RskEventItemRule rule1 = getEventTypeItem(event2.getEventCode(),"1,2",String.valueOf(i+1));
            		listItem2.get(i).add(rule1);
            	}
            	event2.setParamGroup(listItem2);
        	}
        	list.add(event2);
        	//生成关联参数
        	RskEventParaRel rel2 = new RskEventParaRel();
        	rel2.setEventCode(event2.getEventCode());
        	rel2.setParaCode(dto.getParaCode());
        	rel2.setParaGranularity("02");
        	rel2.setRelType(BussinessDictCodeListRds.操作符_加.getValue());
        	rel2.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel2);
        	RskEventParaRel rel22 = new RskEventParaRel();
        	rel22.setEventCode(event2.getEventCode());
        	rel22.setParaCode(dto.getParaCode());
        	rel22.setParaGranularity("01");
        	rel22.setRelType(BussinessDictCodeListRds.操作符_加.getValue());
        	rel22.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel22);
        	RskEventParaRel rel222 = new RskEventParaRel();
        	rel222.setEventCode(event2.getEventCode());
        	rel222.setParaCode(dto.getParaCode());
        	rel222.setParaGranularity("03");
        	rel222.setRelType(BussinessDictCodeListRds.操作符_加.getValue());
        	rel222.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel222);
        	//生成卖出预占风险事件
        	RskEvent002Dto event3 = new RskEvent002Dto();
        	event3.setEventCode("EVENT_ID_"+dto.getParaCode()+"_06");
        	event3.setEventName(dto.getParaName()+"-卖出预占");
        	event3.setIsCalcIndicator(BussinessDictCodeListRds.是否_是.getValue());
        	String eventSellCalc = CacheUtil.getSysParam(BussinessConstantRds.event_sell_calc);
        	if(StringUtil.isNotEmpty(eventSellCalc)){
        		map.put("eventCode", event3.getEventCode());
            	List<List<RskEventItemRule>> listItem3 = getItemList(map,eventSellCalc); 
            	for(int i=0;i<listItem3.size();i++){
            		RskEventItemRule rule1 = getEventTypeItem(event3.getEventCode(),"0,3",String.valueOf(i+1));
            		listItem3.get(i).add(rule1);
            	}
            	event3.setParamGroup(listItem3);
        	}
        	list.add(event3);
        	//生成关联参数
        	RskEventParaRel rel3 = new RskEventParaRel();
        	rel3.setEventCode(event3.getEventCode());
        	rel3.setParaCode(dto.getParaCode());
        	rel3.setParaGranularity("03");
        	rel3.setRelType(BussinessDictCodeListRds.操作符_减.getValue());
        	rel3.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel3);
        	//生成卖出实占风险事件
        	RskEvent002Dto event4 = new RskEvent002Dto();
        	event4.setEventCode("EVENT_ID_"+dto.getParaCode()+"_08");
        	event4.setEventName(dto.getParaName()+"-卖出实占");
        	event4.setIsCalcIndicator(BussinessDictCodeListRds.是否_是.getValue());
        	if(StringUtil.isNotEmpty(eventSellCalc)){
        		map.put("eventCode", event4.getEventCode());
        		List<List<RskEventItemRule>> listItem4 = getItemList(map,eventSellCalc); 
            	for(int i=0;i<listItem4.size();i++){
            		RskEventItemRule rule1 = getEventTypeItem(event4.getEventCode(),"1,2",String.valueOf(i+1));
            		listItem4.get(i).add(rule1);
            	}
            	event4.setParamGroup(listItem4);
        	}
        	list.add(event4);
        	//生成关联参数
        	RskEventParaRel rel4 = new RskEventParaRel();
        	rel4.setEventCode(event4.getEventCode());
        	rel4.setParaCode(dto.getParaCode());
        	rel4.setParaGranularity("02");
        	rel4.setRelType(BussinessDictCodeListRds.操作符_减.getValue());
        	rel4.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel4);
        	//生成关联参数
        	RskEventParaRel rel44 = new RskEventParaRel();
        	rel44.setEventCode(event4.getEventCode());
        	rel44.setParaCode(dto.getParaCode());
        	rel44.setParaGranularity("01");
        	rel44.setRelType(BussinessDictCodeListRds.操作符_减.getValue());
        	rel44.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel44);
        	//生成关联参数
        	RskEventParaRel rel444 = new RskEventParaRel();
        	rel444.setEventCode(event4.getEventCode());
        	rel444.setParaCode(dto.getParaCode());
        	rel444.setParaGranularity("03");
        	rel444.setRelType(BussinessDictCodeListRds.操作符_减.getValue());
        	rel444.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel444);
        	//生成买入预占(不占指标)风险事件
        	RskEvent002Dto event5 = new RskEvent002Dto();
        	event5.setEventCode("EVENT_ID_"+dto.getParaCode()+"_03");
        	event5.setEventName(dto.getParaName()+"-买入预占(不算指标)");
        	event5.setIsCalcIndicator(BussinessDictCodeListRds.是否_否.getValue());
        	String eventBuyNocalc = CacheUtil.getSysParam(BussinessConstantRds.event_buy_nocalc);
        	if(StringUtil.isNotEmpty(eventBuyNocalc)){
        		map.put("eventCode", event5.getEventCode());
        		List<List<RskEventItemRule>> listItem5 = getItemList(map,eventBuyNocalc);
            	for(int i=0;i<listItem5.size();i++){
            		RskEventItemRule rule1 = getEventTypeItem(event5.getEventCode(),"1,2",String.valueOf(i+1));
            		listItem5.get(i).add(rule1);
            	}
            	event5.setParamGroup(listItem5);
        	}
        	list.add(event5);
        	//生成关联参数
        	RskEventParaRel rel5 = new RskEventParaRel();
        	rel5.setEventCode(event5.getEventCode());
        	rel5.setParaCode(dto.getParaCode());
        	rel5.setParaGranularity("02");
        	rel5.setRelType(BussinessDictCodeListRds.操作符_加.getValue());
        	rel5.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel5);
        	//生成买入实占(不占指标)风险事件
        	RskEvent002Dto event6 = new RskEvent002Dto();
        	event6.setEventCode("EVENT_ID_"+dto.getParaCode()+"_01");
        	event6.setEventName(dto.getParaName()+"-买入实占(不算指标)");
        	event6.setIsCalcIndicator(BussinessDictCodeListRds.是否_否.getValue());
        	if(StringUtil.isNotEmpty(eventBuyNocalc)){
        		map.put("eventCode", event6.getEventCode());
        		List<List<RskEventItemRule>> listItem6 = getItemList(map,eventBuyNocalc);
            	for(int i=0;i<listItem6.size();i++){
            		RskEventItemRule rule1 = getEventTypeItem(event6.getEventCode(),"1,2",String.valueOf(i+1));
            		listItem6.get(i).add(rule1);
            	}
            	event6.setParamGroup(listItem6);
        	}
        	list.add(event6);
        	//生成关联参数
        	RskEventParaRel rel6 = new RskEventParaRel();
        	rel6.setEventCode(event6.getEventCode());
        	rel6.setParaCode(dto.getParaCode());
        	rel6.setParaGranularity("03");
        	rel6.setRelType(BussinessDictCodeListRds.操作符_加.getValue());
        	rel6.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel6);
        	//生成关联参数
        	RskEventParaRel rel66 = new RskEventParaRel();
        	rel66.setEventCode(event6.getEventCode());
        	rel66.setParaCode(dto.getParaCode());
        	rel66.setParaGranularity("02");
        	rel66.setRelType(BussinessDictCodeListRds.操作符_加.getValue());
        	rel66.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel66);
        	//生成关联参数
        	RskEventParaRel rel666 = new RskEventParaRel();
        	rel666.setEventCode(event6.getEventCode());
        	rel666.setParaCode(dto.getParaCode());
        	rel666.setParaGranularity("01");
        	rel666.setRelType(BussinessDictCodeListRds.操作符_加.getValue());
        	rel666.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel666);
        	//生成卖出预占(不占指标)风险事件
        	RskEvent002Dto event7 = new RskEvent002Dto();
        	event7.setEventCode("EVENT_ID_"+dto.getParaCode()+"_02");
        	event7.setEventName(dto.getParaName()+"-卖出预占(不算指标)");
        	event7.setIsCalcIndicator(BussinessDictCodeListRds.是否_否.getValue());
        	String eventSellNocalc = CacheUtil.getSysParam(BussinessConstantRds.event_sell_nocalc);
        	if(StringUtil.isNotEmpty(eventSellNocalc)){
        		map.put("eventCode", event7.getEventCode());
        		List<List<RskEventItemRule>> listItem7 = getItemList(map,eventSellNocalc);
            	for(int i=0;i<listItem7.size();i++){
            		RskEventItemRule rule1 = getEventTypeItem(event7.getEventCode(),"0,3",String.valueOf(i+1));
            		listItem7.get(i).add(rule1);
            	}
            	event7.setParamGroup(listItem7);
        	}
        	list.add(event7);
        	//生成关联参数
        	RskEventParaRel rel7 = new RskEventParaRel();
        	rel7.setEventCode(event7.getEventCode());
        	rel7.setParaCode(dto.getParaCode());
        	rel7.setParaGranularity("03");
        	rel7.setRelType(BussinessDictCodeListRds.操作符_减.getValue());
        	rel7.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel7);
        	//生成卖出实占(不占指标)风险事件
        	RskEvent002Dto event8 = new RskEvent002Dto();
        	event8.setEventCode("EVENT_ID_"+dto.getParaCode()+"_04");
        	event8.setEventName(dto.getParaName()+"-卖出实占(不算指标)");
        	event8.setIsCalcIndicator(BussinessDictCodeListRds.是否_否.getValue());
        	if(StringUtil.isNotEmpty(eventSellNocalc)){
        		List<List<RskEventItemRule>> listList8 = new ArrayList<>();
        		map.put("eventCode", event8.getEventCode());
        		List<List<RskEventItemRule>> listItem8 = getItemList(map,eventSellNocalc);
            	for(int i=0;i<listItem8.size();i++){
            		RskEventItemRule rule1 = getEventTypeItem(event8.getEventCode(),"1,2",String.valueOf(i+1));
            		listItem8.get(i).add(rule1);
            	}
            	event8.setParamGroup(listItem8);
        	}
        	list.add(event8);
        	//生成关联参数
        	RskEventParaRel rel8 = new RskEventParaRel();
        	rel8.setEventCode(event8.getEventCode());
        	rel8.setParaCode(dto.getParaCode());
        	rel8.setParaGranularity("01");
        	rel8.setRelType(BussinessDictCodeListRds.操作符_减.getValue());
        	rel8.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel8);
        	//生成关联参数
        	RskEventParaRel rel88 = new RskEventParaRel();
        	rel88.setEventCode(event8.getEventCode());
        	rel88.setParaCode(dto.getParaCode());
        	rel88.setParaGranularity("03");
        	rel88.setRelType(BussinessDictCodeListRds.操作符_减.getValue());
        	rel88.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel88);
        	//生成关联参数
        	RskEventParaRel rel888 = new RskEventParaRel();
        	rel888.setEventCode(event8.getEventCode());
        	rel888.setParaCode(dto.getParaCode());
        	rel888.setParaGranularity("02");
        	rel888.setRelType(BussinessDictCodeListRds.操作符_减.getValue());
        	rel888.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
        	relList.add(rel888);
        	params.put("eventList", list);
        	params.put("relList", relList);
        }
		return params;
	}

	private RskEventItemRule getEventTypeItem(String eventCode, String value, String groupId) {
		RskEventItemRule rule = new RskEventItemRule();
    	rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_事件类型.getValue());
    	rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
    	rule.setItemValue(value);
    	rule.setGroupId(groupId);
    	rule.setEventCode(eventCode);
    	return rule;
	}

	@SuppressWarnings("unchecked")
	private List<List<RskEventItemRule>> getItemList(Map<String, Object> map,String param) {
//    	"assetDetailType":{"text":{"id":["SUPERVISE_ASSET_TYPE.1","CHB_DEPO_ASSET_TYPE.1002", "CHB_DEPO_ASSET_TYPE.1003","CHB_DEPO_ASSET_TYPE.1004","CHB_DEPO_ASSET_TYPE.1005","SUPERVISE_ASSET_TYPE.2","CHB_INTET_BANK_DEPO.2501","CHB_INTET_BANK_DEPO.2502"],
//        "name":["现金及银行存款","本行存款","他行存款","本行发行的大额存单","他行发行的大额存单","同业存单","本行发行的同业存单","他行发行的同业存单"]},
//		  "value":[{"parent":"1","id":"1002,1003,1004,1005" },{"parent":"2", "id":"2501,2502" }]},
//		"industryType":{"text":{"id":["01","A01","A02","A03","A04","A05","02","B06","B07","B08","B09","B10","B11","B12"],
//    	  "name":["农、林、牧、渔业", "农业","林业","畜牧业","渔业","农、林、牧、渔服务业","采矿业","煤炭开采和洗选业","石油和天然气开采业","黑色金属矿采选业","有色金属矿采选业","非金属矿采选业","开采辅助活动","其他采矿业"]},
//	 	  "value":[{"parent":"01","id":"A01,A02,A03,A04,A05" },{"parent":"02","id":"B06,B07,B08,B09,B10,B11,B12" }]}, 
		//bodyGradeType=01, bodyGrade=[02, 03, 05], bondGradeType=01, bondGrade=[04, 06, 07], mdateType=01, mdateRange=Y}
		List<List<RskEventItemRule>> listItem = new ArrayList<>();
		if(StringUtil.isNotEmpty(map.get("assetType"))&&StringUtil.isNotEmpty(map.get("assetDetailType"))){
			Map<String,Object> assetDetailTypeMap = (Map)map.get("assetDetailType");
    		List listAssetDetailType = (List)assetDetailTypeMap.get("value");
			for(int i=0;i<listAssetDetailType.size();i++){
				List<RskEventItemRule> list = new ArrayList<>();
				Map<String,Object> par = (Map)listAssetDetailType.get(i);
				if(StringUtil.equals(map.get("assetType"),BussinessDictCodeListRds.资产类型分类_资管分类.getValue())){
					if(StringUtil.equals(((String)par.get("parent")), BussinessDictCodeListRds.资管_存款类资产.getValue())){
						RskEventItemRule rule = new RskEventItemRule();
	        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_系统资产大类.getValue());
	        			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
	        			rule.setItemValue(BussinessDictCodeListRds.系统资产大类_存款类资产.getValue());
	        			rule.setGroupId(String.valueOf(i+1));
	        			list.add(rule);
	        			if(StringUtil.isNotEmpty((String)par.get("id"))){
	        				RskEventItemRule rule33 = new RskEventItemRule();
		        			rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资管细类.getValue());
		        			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
		        			rule33.setItemValue((String)par.get("id"));
		        			rule33.setGroupId(String.valueOf(i+1));
		        			list.add(rule33);
	        			}
	    			}
	    			if(StringUtil.equals(((String)par.get("parent")), BussinessDictCodeListRds.资管_债券类资产.getValue())){
						RskEventItemRule rule = new RskEventItemRule();
	        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_系统资产大类.getValue());
	        			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
	        			rule.setItemValue(BussinessDictCodeListRds.系统资产大类_债券类资产.getValue());
	        			rule.setGroupId(String.valueOf(i+1));
	        			list.add(rule);
	        			if(StringUtil.isNotEmpty((String)par.get("id"))){
	        				RskEventItemRule rule33 = new RskEventItemRule();
		        			rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资管细类.getValue());
		        			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
		        			rule33.setItemValue((String)par.get("id"));
		        			rule33.setGroupId(String.valueOf(i+1));
		        			list.add(rule33);
	        			}
	    			}
	    			if(StringUtil.equals(((String)par.get("parent")), BussinessDictCodeListRds.资管_基金类资产.getValue())){
						RskEventItemRule rule = new RskEventItemRule();
	        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_系统资产大类.getValue());
	        			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
	        			rule.setItemValue(BussinessDictCodeListRds.系统资产大类_货币基金资产.getValue()+","+BussinessDictCodeListRds.系统资产大类_非货币基金资产.getValue());
	        			rule.setGroupId(String.valueOf(i+1));
	        			list.add(rule);
	        			if(StringUtil.isNotEmpty((String)par.get("id"))){
	        				RskEventItemRule rule33 = new RskEventItemRule();
	        				rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资管细类.getValue());
		        			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
		        			rule33.setItemValue((String)par.get("id"));
		        			rule33.setGroupId(String.valueOf(i+1));
		        			list.add(rule33);
	        			}
	        			
	    			}
	    			if(StringUtil.equals(((String)par.get("parent")), BussinessDictCodeListRds.资管_项目类资产.getValue())){
						RskEventItemRule rule = new RskEventItemRule();
	        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_系统资产大类.getValue());
	        			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
	        			rule.setItemValue(BussinessDictCodeListRds.系统资产大类_项目类资产.getValue());
	        			rule.setGroupId(String.valueOf(i+1));
	        			list.add(rule);
	        			if(StringUtil.isNotEmpty((String)par.get("id"))){
	        				RskEventItemRule rule33 = new RskEventItemRule();
		        			rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资管细类.getValue());
		        			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
		        			rule33.setItemValue((String)par.get("id"));
		        			rule33.setGroupId(String.valueOf(i+1));
		        			list.add(rule33);
	        			}
	    			}
	    			if(StringUtil.equals(((String)par.get("parent")), BussinessDictCodeListRds.资管_计划类资产.getValue())){
						RskEventItemRule rule = new RskEventItemRule();
	        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_系统资产大类.getValue());
	        			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
	        			rule.setItemValue(BussinessDictCodeListRds.系统资产大类_计划类资产_净值型.getValue()+","+BussinessDictCodeListRds.系统资产大类_计划类资产_货币型.getValue()+","+BussinessDictCodeListRds.系统资产大类_计划类资产_利率型.getValue());
	        			rule.setGroupId(String.valueOf(i+1));
	        			list.add(rule);
	        			if(StringUtil.isNotEmpty((String)par.get("id"))){
	        				RskEventItemRule rule33 = new RskEventItemRule();
		        			rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_投资性质.getValue());
		        			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
		        			rule33.setItemValue((String)par.get("id"));
		        			rule33.setGroupId(String.valueOf(i+1));
		        			list.add(rule33);
	        			}
	    			}
	    			if(StringUtil.equals(((String)par.get("parent")), BussinessDictCodeListRds.资管_质押式回购.getValue())){
	    				RskEventItemRule rule = new RskEventItemRule();
	        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_系统资产大类.getValue());
	        			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
	        			rule.setItemValue(BussinessDictCodeListRds.系统资产大类_债券质押式回购交易.getValue());
	        			rule.setGroupId(String.valueOf(i+1));
	        			list.add(rule);
	        			if(StringUtil.isNotEmpty((String)par.get("id"))){
	        				RskEventItemRule rule33 = new RskEventItemRule();
		        			rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资管细类.getValue());
		        			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
		        			rule33.setItemValue((String)par.get("id"));
		        			rule33.setGroupId(String.valueOf(i+1));
		        			list.add(rule33);
	        			}
	    			}
				}else if(StringUtil.equals(map.get("assetType"),BussinessDictCodeListRds.资产类型分类_中债分类.getValue())){
					RskEventItemRule rule = new RskEventItemRule();
        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_监管一级分类.getValue());
        			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
        			rule.setItemValue((String)par.get("parent"));
        			rule.setGroupId(String.valueOf(i+1));
        			list.add(rule);
        			if(StringUtil.isNotEmpty((String)par.get("id"))){
        				RskEventItemRule rule33 = new RskEventItemRule();
            			rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_监管二级分类.getValue());
            			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
            			rule33.setItemValue((String)par.get("id"));
            			rule33.setGroupId(String.valueOf(i+1));
            			list.add(rule33);
        			}
        			RskEventItemRule rule333 = new RskEventItemRule();
        			rule333.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_系统资产大类.getValue());
        			rule333.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
        			rule333.setItemValue(BussinessDictCodeListRds.系统资产大类_项目类资产.getValue());
        			rule333.setGroupId(String.valueOf(i+1));
        			list.add(rule333);
				}else if(StringUtil.equals(map.get("assetType"),BussinessDictCodeListRds.资产类型分类_授权分类.getValue())){
					
				}else if(StringUtil.equals(map.get("assetType"),BussinessDictCodeListRds.资产类型分类_债券wind分类.getValue())){
					RskEventItemRule rule = new RskEventItemRule();
        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_wind一级分类.getValue());
        			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
        			rule.setItemValue((String)par.get("parent"));
        			rule.setGroupId(String.valueOf(i+1));
        			list.add(rule);
        			if(StringUtil.isNotEmpty((String)par.get("id"))){
        				RskEventItemRule rule33 = new RskEventItemRule();
            			rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_wind二级分类.getValue());
            			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
            			rule33.setItemValue((String)par.get("id"));
            			rule33.setGroupId(String.valueOf(i+1));
            			list.add(rule33);
        			}
				}
				
				listItem.add(list);
			}
    	}
		if(!StringUtil.isEmptyList(listItem)){
			for(int i=0;i<listItem.size();i++){
				listItem.get(i).addAll(getOtherItemList(map,param,i+1));
			}
		}
		return listItem;
	}
	private List<RskEventItemRule> getOtherItemList(Map<String, Object> map,String param,int j){
		List<RskEventItemRule> list = new ArrayList<>();
		if(StringUtil.isNotEmpty(map.get("category"))&&StringUtil.isNotEmpty(map.get("industryType"))){
    		Map<String,Object> industryType = (Map)map.get("industryType");
    		List industry = (List)industryType.get("value");
    		//定义行业1级类型
    		String industry1 = "";
    		//定义行业2级类型
			String industry2 = "";
			if(!StringUtil.isEmptyList(industry)){
				for(int i=0;i<industry.size();i++){
					Map<String,Object> mmp = (Map)industry.get(i);
					industry1 = industry1+(String)mmp.get("parent")+",";
					industry2 = industry2+(String)mmp.get("id")+",";
				}
			}
			industry1 = industry1.substring(0, industry1.lastIndexOf(","));
			industry2 = industry2.substring(0, industry2.lastIndexOf(","));
    		if(StringUtil.equals(map.get("category"), BussinessDictCodeListRds.所属行业分类_所属WIND行业.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_所属wind行业一级分类.getValue());
    			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
    			rule.setItemValue(industry1);
    			list.add(rule);
    			RskEventItemRule rule33 = new RskEventItemRule();
    			rule33.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_所属wind行业二级分类.getValue());
    			rule33.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
    			rule33.setItemValue(industry2);
    			list.add(rule33);
    		}
    	}
    	if(StringUtil.isNotEmpty(map.get("bodyGradeType"))&&StringUtil.isNotEmpty(map.get("bodyGrade"))){
    		List<String> bodyGradeList = (List)map.get("bodyGrade");
    		String bodyGrade = bodyGradeList.toString();
    		bodyGrade = bodyGrade.substring(bodyGrade.indexOf("[")+1,bodyGrade.length());
    		bodyGrade = bodyGrade.substring(0, bodyGrade.lastIndexOf("]"));
    		if(StringUtil.equals(map.get("bodyGradeType"), BussinessDictCodeListRds.评级分类_外部评级.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_主体外部评级.getValue());
    			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
    			rule.setItemValue(bodyGrade);
    			list.add(rule);
    		}
    		if(StringUtil.equals(map.get("bodyGradeType"), BussinessDictCodeListRds.评级分类_内部评级.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode("bondInsideType");
    			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
    			rule.setItemValue(bodyGrade);
    			list.add(rule);
    		}
    	}
    	if(StringUtil.isNotEmpty(map.get("bondGradeType"))&&StringUtil.isNotEmpty(map.get("bondGrade"))){
    		List<String> bondGradeList = (List)map.get("bondGrade");
    		String bondGrade = bondGradeList.toString();
    		//去除[]
    		bondGrade = bondGrade.substring(bondGrade.indexOf("[")+1,bondGrade.length());
    		bondGrade = bondGrade.substring(0, bondGrade.lastIndexOf("]"));
    		if(StringUtil.equals(map.get("bondGradeType"), BussinessDictCodeListRds.评级分类_外部评级.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_债券外部评级.getValue());
    			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
    			rule.setItemValue(bondGrade);
    			list.add(rule);
    		}
    		if(StringUtil.equals(map.get("bondGradeType"), BussinessDictCodeListRds.评级分类_内部评级.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode("bondInsideType");
    			rule.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
    			rule.setItemValue(bondGrade);
    			list.add(rule);
    		}
    	}
    	if(StringUtil.isNotEmpty(map.get("mdateRange"))){
    		if(StringUtil.equals(map.get("mdateRange"), BussinessDictCodeListRds.日期范围类型_一年内.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资产到期日.getValue());
    			rule.setMatchType(BussinessDictCodeListRds.运算符_小于等于.getValue());
    			rule.setItemValue("${sysDate}");
    			rule.setOffsetVal(Long.valueOf("1"));
    			rule.setOffsetType(BussinessDictCodeListRds.操作符_加.getValue());
    			rule.setOffsetUnit(BussinessDictCodeListRds.特征值偏移单位_年.getValue());
    			list.add(rule);
    		}else if(StringUtil.equals(map.get("mdateRange"), BussinessDictCodeListRds.日期范围类型_半年内.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资产到期日.getValue());
    			rule.setMatchType(BussinessDictCodeListRds.运算符_小于等于.getValue());
    			rule.setItemValue("${sysDate}");
    			rule.setOffsetVal(Long.valueOf("6"));
    			rule.setOffsetType(BussinessDictCodeListRds.操作符_加.getValue());
    			rule.setOffsetUnit(BussinessDictCodeListRds.特征值偏移单位_月.getValue());
    			list.add(rule);
    		}else if(StringUtil.equals(map.get("mdateRange"), BussinessDictCodeListRds.日期范围类型_一季度内.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资产到期日.getValue());
    			rule.setMatchType(BussinessDictCodeListRds.运算符_小于等于.getValue());
    			rule.setItemValue("${sysDate}");
    			rule.setOffsetVal(Long.valueOf("3"));
    			rule.setOffsetType(BussinessDictCodeListRds.操作符_加.getValue());
    			rule.setOffsetUnit(BussinessDictCodeListRds.特征值偏移单位_月.getValue());
    			list.add(rule);
    		}else if(StringUtil.equals(map.get("mdateRange"), BussinessDictCodeListRds.日期范围类型_一月内.getValue())){
    			RskEventItemRule rule = new RskEventItemRule();
    			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资产到期日.getValue());
    			rule.setMatchType(BussinessDictCodeListRds.运算符_小于等于.getValue());
    			rule.setItemValue("${sysDate}");
    			rule.setOffsetVal(Long.valueOf("1"));
    			rule.setOffsetType(BussinessDictCodeListRds.操作符_加.getValue());
    			rule.setOffsetUnit(BussinessDictCodeListRds.特征值偏移单位_月.getValue());
    			list.add(rule);
    		}else if(StringUtil.equals(map.get("mdateRange"), BussinessDictCodeListRds.日期范围类型_自定义.getValue())){
    			if(StringUtil.equals(map.get("mdateType"), BussinessDictCodeListRds.日期类型_自然日.getValue())){
    				RskEventItemRule rule = new RskEventItemRule();
        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资产到期日.getValue());
        			rule.setMatchType(BussinessDictCodeListRds.运算符_小于等于.getValue());
        			rule.setItemValue("${sysDate}");
        			rule.setOffsetVal(Long.valueOf((String)map.get("customDate")));
        			rule.setOffsetType(BussinessDictCodeListRds.操作符_加.getValue());
        			rule.setOffsetUnit(BussinessDictCodeListRds.特征值偏移单位_自然日.getValue());
        			list.add(rule);
    			}
    			if(StringUtil.equals(map.get("mdateType"), BussinessDictCodeListRds.日期类型_交易日.getValue())){
    				RskEventItemRule rule = new RskEventItemRule();
        			rule.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_资产到期日.getValue());
        			rule.setMatchType(BussinessDictCodeListRds.运算符_小于等于.getValue());
        			rule.setItemValue("${sysDate}");
        			rule.setOffsetVal(Long.valueOf((String)map.get("customDate")));
        			rule.setOffsetType(BussinessDictCodeListRds.操作符_加.getValue());
        			rule.setOffsetUnit(BussinessDictCodeListRds.特征值偏移单位_工作日.getValue());
        			list.add(rule);
    			}
    		}
    	}
    	//生成交易方向的风险事件特征值
    	RskEventItemRule rulePs = new RskEventItemRule();
    	rulePs.setItemCode(BussinessDictCodeListRds.风险事件特征值类型_交易方向.getValue());
    	rulePs.setMatchType(BussinessDictCodeListRds.运算符_包含.getValue());
    	rulePs.setItemValue(param);
    	list.add(rulePs);
    	if(!StringUtil.isEmptyList(list)){
    		for(RskEventItemRule e:list){
        		e.setEventCode((String)map.get("eventCode"));
        		e.setGroupId(String.valueOf(j));
        	}
    	}
    	return list;
	}
    @Transactional(rollbackFor = Throwable.class)
	public int updateCustomParam(RskSupervisionIndicatorParam005Dto dto) {
        RskIsupIndicatorPara record = new RskIsupIndicatorPara();
        BeanUtil.copyPropertiesIgnoreNull(record, dto);
        record.setIsForbid("N");
        rskIsupIndicatorParaMapper.deleteByPrimaryKey(dto.getParaCode());
        //删除参数明细
        rskSupervisionIndicatorParamMapper.delete(dto.getParaCode());
        // 插入参数表
        rskIsupIndicatorParaMapper.insert(record);
        List<RskSupervisionIndicatorParam005Dto> paraDtl = getParaDtlList(dto);
        for(RskSupervisionIndicatorParam005Dto e:paraDtl){
        	rskIsupIndicatorParaDtlMapper.insert(e);
        }
        
        //删除风险事件相关
        List<RskEvent002Dto> eventCodeList = getEventCodeList(dto.getParaCode()); 
        if(!StringUtil.isEmptyList(eventCodeList)){
        	for(RskEvent002Dto e:eventCodeList){
        		//删除风险事件，风险事件特征值，风险关联指标参数
        		rskEventService.delete(e);
        	}
        }
      //生成风险事件
        Map<String,Object> params = getListEvent(dto);
        List<RskEvent002Dto> eventList = (List)params.get("eventList");
        List<RskEventParaRel> relList = (List)params.get("relList");
        if(!StringUtil.isEmptyList(eventList)&&!StringUtil.isEmptyList(relList)){
        	//插入风险事件
        	for(RskEvent002Dto e:eventList){
            	rskEventService.insert(e);
            }
        	//插入风险事件关联指标参数表
        	rskEvent001Mapper.insertParaList(relList);
        }
        return 1;
    }

    public List<RskSupervisionIndicatorParam005Dto> selectCustomParam(String paraCode) {
        return rskSupervisionIndicatorParamMapper.selectCustomParam(paraCode);
    }

	public List<SqlDto> getSql(String paraCode) {
		return rskSupervisionIndicatorParamMapper.getSql(paraCode);
	}

	public String getModelValue(String modelCode) {
		//获取所有的模板变量值
		List<String> modelValueList = rskSupervisionIndicatorParamMapper.getModelValue(modelCode);
		String modelValue = StringUtil.EMPTY;
		if(!StringUtil.isEmptyList(modelValueList)){
			for(String value:modelValueList){
				modelValue = modelValue+value+";";
			}
			modelValue = modelValue.substring(0, modelValue.length()-1);
		}
		return modelValue;
	}
	
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getInstitutionId(Map<String, Object> param){ 
	    com.joyintech.tams.systemtools.util.CommonUtil.setInstitutionMapByType_ms(param);
	    if (StringUtil.isNotEmpty(param.get("initValue"))) { 
	        param.put("search", param.get("initValue"));
        }
	    List<SelectOptionDto> institutionIdList = rskSupervisionIndicatorParamMapper.getInstitutionId(param);
	    return institutionIdList;
	}
}
