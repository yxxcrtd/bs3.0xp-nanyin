/*
 * 文件名：RepCheckInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年3月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.dao.RepCheckInfoDao;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.dao.RepCheckInfoMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity.CheckConfirmMessage;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity.RepCheckInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 信批内容校验
 * 
 * @author wanghb
 * @version 1.0 2019年3月20日
 * @see RepCheckInfoService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RepCheckInfoService {
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RepCheckInfoMapper repCheckInfoMapper;

    @Autowired
    private RepCheckInfoDao repCheckInfoDao;

    /**
     * 根据报表编号和校验位置查询校验内容
     * 
     * @param param 条件
     * @return 校验内容
     * @see
     * @since 1.0
     */
    public List<RepCheckInfoDto> getCheckInfo(Map<String, Object> param) {
        List<RepCheckInfoDto> list = repCheckInfoMapper.getCheckInfo(param);
        return list;
    }

    /**
     * 替换sql中参数
     * 
     * @param sql
     * @param reg
     * @param replacement
     * @return
     * @see
     * @since 1.0
     */
    public String replaceForString(String sql, String reg, String replacement) {
        return sql.replaceAll("\\$" + reg.trim() + "", "'" + replacement + "'");
    }

    /**
     * sql判断方法
     * 
     * @param checkInfo 校验内容
     * @param prod 带校验dto
     * @param T dto对应class
     * @return 查询结果
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @see
     * @since 1.0
     */
    public List<Object> sqlCheck(RepCheckInfoDto checkInfo, Object info, Class<?> T)
        throws NoSuchFieldException, SecurityException, IllegalAccessException,
        IllegalArgumentException, InvocationTargetException, IntrospectionException {
        // 获取带替换参数
        String queryParam = checkInfo.getQueryParam();
        // 获取查询sql
        String querySql = checkInfo.getQueryConten();
        // 当查询sql是枚举sql时
        if (StringUtil.equals(checkInfo.getParamType(),
            DictCodeListExt2.参数类型_枚举sql.getValue())) {
            // 设置参数1中的值为sql，并且设置设置查询条件中itemCode
            querySql = checkInfo.getParamValue1();
            querySql = replaceForString(querySql, "itemCode", (String)CommonUtil.getValueByClassName(info, checkInfo.getQueryConten(), T));
        }
        else {
            if (StringUtil.isNotEmpty(queryParam)) {
                // 拆分参数
                String[] strs = queryParam.split(",");
                for (int i = 0; i < strs.length; i++ ) {
                    String[] params = strs[i].split("=");
                    // 替换sql中的参数
                    querySql = replaceForString(querySql, params[0],
                        (String)CommonUtil.getValueByClassName(info, params[1], T));
                }
            }
        }
        // 执行sql，查询数据返回list
        List<Object> list = repCheckInfoDao.selectOne(querySql);
        if (!StringUtil.isEmptyList(list)) {
            // 查询出的数据和已知数据比较
            return list;
        }
        return null;
    }

    /**
     * 非空判断
     * 
     * @param ccm 验证结果消息
     * @param obj 验证对象
     * @param checkInfo 验证条件
     * @see
     * @since 1.0
     */
    public void notNullCheck(CheckConfirmMessage ccm, Object obj, RepCheckInfoDto checkInfo) {
        // 如果校验数据为空则设置校验结果
        if (!StringUtil.isNotEmpty(obj) || obj == null) {
            ccm.setFlag(false);
            ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                       + checkInfo.getQueryContenDescr() + "数据为空");
        }
    }

    /**
     * 空判断
     * 
     * @param ccm 验证结果消息
     * @param obj 验证对象
     * @param checkInfo 验证条件
     * @see
     * @since 1.0
     */
    public void nullCheck(CheckConfirmMessage ccm, Object obj, RepCheckInfoDto checkInfo) {
        // 如果校验数据为非空则设置校验结果
        if (StringUtil.isNotEmpty(obj) && obj != null) {
            ccm.setFlag(false);
            ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                       + checkInfo.getQueryContenDescr() + "数据不为空");
        }
    }

    /**
     * 判断大于,小于,等于,小于等于,大于等于
     * 
     * @param ccm 验证结果消息
     * @param obj 验证对象
     * @param checkInfo 验证内容
     * @param prod 待校验dto
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @see
     * @since 1.0
     */
    public void compare(CheckConfirmMessage ccm, Object obj, RepCheckInfoDto checkInfo,
                        Object prod, Class<?> T)
        throws NoSuchFieldException, SecurityException, IllegalAccessException,
        IllegalArgumentException, InvocationTargetException, IntrospectionException,Exception {
        if (StringUtil.isNotEmpty(obj) && obj != null) {
            Object data = new Object();
            Object data2 = new Object();
            // 如果校验对象是dto字段，则从dto中获取待比较值
            if (StringUtil.equals(checkInfo.getParamType(),
                DictCodeListExt2.参数类型_产品信息字段.getValue())) {
                data = CommonUtil.getValueByClassName(prod, checkInfo.getParamValue1(), T);
                if(!StringUtils.isEmpty(checkInfo.getParamValue2())){
                	data2 = CommonUtil.getValueByClassName(prod, checkInfo.getParamValue2(), T);
                }
            }
            else if (StringUtil.equals(checkInfo.getParamType(),
                DictCodeListExt2.参数类型_直接数值.getValue())) {
                // 日期需要特殊处理,加上年份
                if (StringUtil.equals(checkInfo.getDataType(),
                    DictCodeListExt2.查询数据类型_Date.getValue())) {
                    data = DateUtil.getYear((String)obj) + "-" + checkInfo.getParamValue1();
                    data2 = DateUtil.getYear((String)obj) + "-" + checkInfo.getParamValue2();
                }
                else {
                    data = checkInfo.getParamValue1();
                    data2 = checkInfo.getParamValue2();
                }
            }
            else if (StringUtil.equals(checkInfo.getParamType(),
                DictCodeListExt2.参数类型_枚举sql.getValue())) {
                // TODO 后期扩展
            }
            // 待比较的数据是日期类型
            if (StringUtil.equals(checkInfo.getDataType(),
                DictCodeListExt2.查询数据类型_Date.getValue())) {
            	
            	if(StringUtil.isEmpty((String)obj) || StringUtil.isEmpty((String)data)){
            		logger.error("日期为空，不能进行比较");
            		throw new Exception("日期为空，不能进行比较");
            	}
            	
                if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_大于.getValue())) {
                    if (DateUtil.compareDate((String)obj, (String)data) <= 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不大于" + (String)data);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_大于等于.getValue())) {
                    if (DateUtil.compareDate((String)obj, (String)data) < 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "小于" + (String)data);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_小于.getValue())) {
                    if (DateUtil.compareDate((String)obj, (String)data) >= 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不小于" + (String)data);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_小于等于.getValue())) {
                    if (DateUtil.compareDate((String)obj, (String)data) > 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "大于" + (String)data);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_等于.getValue())) {
                    if (DateUtil.compareDate((String)obj, (String)data) != 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不等于" + (String)data);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_区间.getValue())) {
                    if (DateUtil.compareDate((String)obj, (String)data) < 0
                        || DateUtil.compareDate((String)obj, (String)data2) > 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不在" + (String)data + "与"
                                   + (String)data2 + "区间内!");
                    }
                }

            } // 待比较的数据是int类型
            else if (StringUtil.equals(checkInfo.getDataType(),
                DictCodeListExt2.查询数据类型_int.getValue())) {
            	
            	Integer objint = (obj == null ? 0 : Integer.parseInt(obj+""));
            	Integer dataint = (data == null ? 0 : Integer.parseInt(data+""));
            	
                if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_大于.getValue())) {
                    if (objint <= dataint) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不大于" + dataint);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_大于等于.getValue())) {
                    if (objint < dataint) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "小于" + dataint);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_小于.getValue())) {
                    if (objint >= dataint) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不小于" + dataint);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_小于等于.getValue())) {
                    if (objint > dataint) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "大于" + dataint);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_等于.getValue())) {
                    if (objint != dataint) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不等于" + dataint);
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_区间.getValue())) {
                    if (objint < dataint || objint > Integer.parseInt(data2+"")) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不在" + dataint + "与"
                                   + Integer.parseInt(data2+"") + "区间内!");
                    }
                }
            } // 待比较的数据是String类型
            else if (StringUtil.equals(checkInfo.getDataType(),
                DictCodeListExt2.查询数据类型_String.getValue())) {

            } // 待比较的数据是BigDecimal类型
            else if (StringUtil.equals(checkInfo.getDataType(),
                DictCodeListExt2.查询数据类型_BigDecimal.getValue())) {
                if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_大于.getValue())) {
                    if ((new BigDecimal(obj.toString())).compareTo(
                        (new BigDecimal(data.toString()))) <= 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不大于"
                                   + (new BigDecimal(data.toString())));
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_大于等于.getValue())) {
                    if ((new BigDecimal(obj.toString())).compareTo(
                        (new BigDecimal(data.toString()))) < 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "小于"
                                   + (new BigDecimal(data.toString())));
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_小于.getValue())) {
                    if ((new BigDecimal(obj.toString())).compareTo(
                        (new BigDecimal(data.toString()))) >= 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不小于"
                                   + (new BigDecimal(data.toString())));
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_小于等于.getValue())) {
                    if ((new BigDecimal(obj.toString())).compareTo(
                        (new BigDecimal(data.toString()))) > 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "大于"
                                   + (new BigDecimal(data.toString())));
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_等于.getValue())) {
                    if ((new BigDecimal(obj.toString())).compareTo(
                        (new BigDecimal(data.toString()))) != 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不等于"
                                   + (new BigDecimal(data.toString())));
                    }
                }
                else if (StringUtil.equals(checkInfo.getQueryType(),
                    DictCodeListExt2.查询类型_区间.getValue())) {
                    if ((new BigDecimal(obj.toString())).compareTo((new BigDecimal(data.toString()))) < 0
                        || (new BigDecimal(obj.toString())).compareTo(
                            (new BigDecimal(data2.toString()))) > 0) {
                        ccm.setFlag(false);
                        ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                                   + checkInfo.getQueryContenDescr() + "不在" + (new BigDecimal(data.toString())) + "与"
                                   + (new BigDecimal(data2.toString())) + "区间内!");
                    }
                }
            }
        }
        else {
            ccm.setFlag(false);
            ccm.setMsg(
                (StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "") + "校验对象为空，执行错误！");
        }
    }

    /**
     * 判断是否符合正则表达式
     * 
     * @param ccm 验证结果消息
     * @param obj 验证对象
     * @param checkInfo 验证条件
     * @see
     * @since 1.0
     */
    public void regularCheck(CheckConfirmMessage ccm, Object obj, RepCheckInfoDto checkInfo) {
        if (obj != null && StringUtil.isNotEmpty(obj)) {
            if (!((String)obj).matches(checkInfo.getParamValue1())) {
                ccm.setFlag(false);
                ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                           + checkInfo.getQueryContenDescr() + "正则验证" + checkInfo.getParamValue1()
                           + "失败!");
            }
        }
        else {
            ccm.setFlag(false);
            ccm.setMsg(
                (StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "") + "校验对象为空，执行错误！");
        }
    }

    /**
     * 枚举范围判断
     * 
     * @param ccm 验证结果消息
     * @param obj 验证对象
     * @param checkInfo 验证条件
     * @param info 带校验dto
     * @param T dto对应class
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @see
     * @since 1.0
     */
    public void enumCheck(CheckConfirmMessage ccm, Object obj, RepCheckInfoDto checkInfo,
                          Object info, Class<?> T)
        throws NoSuchFieldException, SecurityException, IllegalAccessException,
        IllegalArgumentException, InvocationTargetException, IntrospectionException {
        List<Object> list = sqlCheck(checkInfo, info, T);
        if (StringUtil.isEmptyList(list) && list.size() <= 0) {
            ccm.setFlag(false);
            ccm.setMsg((StringUtil.isNotEmpty(ccm.getMsg()) ? ccm.getMsg() + ";" : "")
                       + checkInfo.getQueryContenDescr() + "枚举值范围" + checkInfo.getParamValue1()
                       + "失败!");
        }
    }

    /**
     * 单条校验
     * 
     * @param ccm 校验结果
     * @param obj 待校验数据
     * @param checkInfo 校验内容
     * @param prod 带校验dto
     * @param T dto对应class
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @see
     * @since 1.0
     */
    public void singleCheck(CheckConfirmMessage ccm, Object obj, RepCheckInfoDto checkInfo,
                            Object prod, Class<?> T)
        throws NoSuchFieldException, SecurityException, IllegalAccessException,
        IllegalArgumentException, InvocationTargetException, IntrospectionException {
    	
    	try {
    		if (StringUtil.equals(checkInfo.getQueryType(), DictCodeListExt2.查询类型_大于.getValue())
    	            || StringUtil.equals(checkInfo.getQueryType(),
    	                DictCodeListExt2.查询类型_大于等于.getValue())
    	            || StringUtil.equals(checkInfo.getQueryType(),
    	                DictCodeListExt2.查询类型_小于.getValue())
    	            || StringUtil.equals(checkInfo.getQueryType(),
    	                DictCodeListExt2.查询类型_小于等于.getValue())
    	            || StringUtil.equals(checkInfo.getQueryType(),
    	                DictCodeListExt2.查询类型_区间.getValue())
    	            || StringUtil.equals(checkInfo.getQueryType(),
    	                DictCodeListExt2.查询类型_等于.getValue())) {
    	            this.compare(ccm, obj, checkInfo, prod, T);
    	        }
    	        else if (StringUtil.equals(checkInfo.getQueryType(),
    	            DictCodeListExt2.查询类型_为空.getValue())) {
    	            this.nullCheck(ccm, obj, checkInfo);
    	        }
    	        else if (StringUtil.equals(checkInfo.getQueryType(),
    	            DictCodeListExt2.查询类型_非空.getValue())) {
    	            this.notNullCheck(ccm, obj, checkInfo);
    	        }
    	        else if (StringUtil.equals(checkInfo.getQueryType(),
    	            DictCodeListExt2.查询类型_枚举值范围.getValue())) {
    	            this.enumCheck(ccm, obj, checkInfo, prod, T);
    	        }
    	        else if (StringUtil.equals(checkInfo.getQueryType(),
    	            DictCodeListExt2.查询类型_正则.getValue())) {
    	            this.regularCheck(ccm, obj, checkInfo);
    	        }
		}catch (Exception e) {
			ccm.setFlag(false);
			ccm.setMsg(ccm.getMsg()+e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
    	
    }

    /**
     * 内容校验
     * 
     * @param info 包含校验参数的实体类
     * @param T 实体类class
     * @return 校验后信息
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @see
     * @since 1.0
     */
    public CheckConfirmMessage check(Object info, Class<?> T) {
        Map<String, Object> param = new HashMap<>();
        CheckConfirmMessage msg = new CheckConfirmMessage();
        msg.setFlag(true);
        try {
            param.put("repId", (String)CommonUtil.getValueByClassName(info, "repId", T));
            param.put("checkFor", (String)CommonUtil.getValueByClassName(info, "checkFor", T));
            if (param.get("repId") == null || param.get("checkFor") == null) {
                msg.setFlag(false);
                msg.setMsg("报表信息或校验位置为空");
            }
            List<RepCheckInfoDto> rciList = this.getCheckInfo(param);
            for (RepCheckInfoDto dto : rciList) {
                if (StringUtil.equals(dto.getBaseDataType(),
                    DictCodeListExt2.基础数据类型_产品信息.getValue())) {
                    Object obj = CommonUtil.getValueByClassName(info, dto.getQueryConten(), T);
                    // 数据校验
                    this.singleCheck(msg, obj, dto, info, T);
                    return msg;
                }
                else if (StringUtil.equals(dto.getBaseDataType(),
                    DictCodeListExt2.基础数据类型_sql.getValue())) {
                    List<Object> list = this.sqlCheck(dto, info, T);
                    if (!StringUtil.isEmptyList(list)) {
                        for (Object obj : list) {
                            // 单条数据校验
                            this.singleCheck(msg, obj, dto, info, T);
                            if (!msg.isFlag()) {
                                return msg;
                            }
                        }
                    }

                }
            }
        }
        catch (Exception e) {
            msg.setFlag(false);
            msg.setMsg("阀值校验出现异常,请联系管理员!");
            e.printStackTrace();
        }
        return msg;
    }
}
