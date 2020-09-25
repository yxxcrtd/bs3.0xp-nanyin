package com.joyintech.tams.disclosure.common.common.util;
///**
// *
// */
//package com.joyintech.tams.disclosure.common.common.util;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.nio.charset.Charset;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import com.joyintech.framework.constant.GlobalConstant;
//import com.joyintech.framework.jdbc.datasource.DatabaseInfo;
//import com.joyintech.framework.jdbc.datasource.DatabaseType;
//import com.joyintech.framework.jdbc.datasource.DatabaseTypeHelper;
//import com.joyintech.framework.jdbc.enums.DataSourceType;
//import com.joyintech.framework.security.SecurityUtil;
//import com.joyintech.framework.security.User;
//import com.joyintech.framework.select.TreeNode;
//import com.joyintech.framework.util.CacheUtil;
//import com.joyintech.framework.util.DateUtil;
//import com.joyintech.framework.util.StringUtil;
//import com.joyintech.framework.validation.ValidationConstant;
//import com.joyintech.tams.disclosure.common.common.base.dao.BaseMapper;
//import com.joyintech.tams.disclosure.common.common.constant.DataIndexSequenceType;
//import com.joyintech.tams.disclosure.common.common.listener.ReportAuthorizationListener;
//import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
//import com.joyintech.system.actorrole.dto.SystemActorRole003Dto;
//import com.joyintech.system.actorrole.service.SystemActorRoleService;
//
///**
// * @author zuoyinyi
// *
// */
//@Component
//public class CommonUtil {
//	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
//	/**
//	 *
//	 */
//	private static Pattern ENGLISH_PATTERN = Pattern.compile(ValidationConstant.PATTERN_EN_NUMBER);
//
//    /**
//    *
//    */
//    private static Pattern FLOAT_NUMERIC_PATTERN = Pattern.compile("^[0-9\\-\\.]+$");
//
//    /**
//     * BaseMapper
//     */
//    private static BaseMapper baseMapper;
//
//    /**
//     * 角色权限service
//     */
//    private static SystemActorRoleService systemActorRoleService;
//
//    /**
//     * 角色权限service
//     */
//    private static BaseCommonService baseCommonService;
//
//    /**
//     * 注入角色权限service
//     *
//     * @param systemActorRoleService 角色权限service
//     */
//    @Autowired
//    public void setBaseMapper(SystemActorRoleService systemActorRoleService) {
//        CommonUtil.systemActorRoleService = systemActorRoleService;
//    }
//    /**
//     * 注入BaseMapper
//     *
//     * @param baseMapper BaseMapper
//     */
//    @Autowired
//    public void setBaseMapper(BaseMapper baseMapper) {
//        CommonUtil.baseMapper = baseMapper;
//    }
//    /**
//     * 注入BaseCommonService
//     *
//     * @param baseCommonService BaseCommonService
//     */
//    @Autowired
//    public void setBaseCommonService(BaseCommonService baseCommonService) {
//        CommonUtil.baseCommonService = baseCommonService;
//    }
//
//    /**
//     * 数据权限用所属机构取得
//     *
//     * @return 所属机构
//     * @see
//     * @since 1.0
//     */
//    public static List<String> getOrgCodeListForSearch() {
//        return new ArrayList<String>();
//    }
//
//    /**
//     * 获取对象的非空字符串<br>
//     * 如果该对象为空，则返回空字符串
//     *
//     * @param value
//     *            对象
//     * @return 字符串
//     */
//    public static final String getNotNullString(Object value) {
//        return StringUtil.getOrElse(value, StringUtil.EMPTY);
//    }
//
//	/**
//	 * 获取当前登录用户的ORG_CODE
//	 *
//	 * @return
//	 */
//	public static final String getOrgCode() {
//	    try {
//	        return SecurityUtil.getLoginUser().getOrgCode();
//        }
//        catch (Exception e) {
//        }
//	    return null;
//	}
//
//	/**
//	 * 获取当前登录用户的DEPT_CODE
//	 *
//	 * @return
//	 */
//	public static final String getDeptCode() {
//	    try {
//	        User user = SecurityUtil.getLoginUser();
//	        return user.getDeptCode();
//        }
//        catch (Exception e) {
//        }
//		return null;
//	}
//
//	/**
//	 * 取得字符串的实际长度（考虑了汉字的情况）
//	 *
//	 * @param srcStr
//	 *            源字符串
//	 * @return 字符串的实际长度
//	 */
//	public static final int getStringLen(String srcStr) {
//		int returnValue = 0;
//		if (srcStr != null) {
//			byte[] byteArr = srcStr.getBytes(Charset.forName("UTF-8"));
//			returnValue = byteArr.length;
//		}
//		return returnValue;
//	}
//
//	/**
//	 * 判断是否数字字母组合
//	 *
//	 * @param src
//	 *            源字符串
//	 * @return 是否数字字母组合的标志
//	 */
//	public static boolean isEnglish(String src) {
//		boolean returnValue = false;
//		if (src != null && src.length() > 0) {
//			Matcher m = ENGLISH_PATTERN.matcher(src);
//			if (m.find()) {
//				returnValue = true;
//			}
//		}
//		return returnValue;
//	}
//
//    /**
//     * 查询登记银行代码（获取当前用户所属机构的登记银行代码）
//     * @return 结果list
//     * @see
//     * @since 1.0
//     */
//    public static String getUserBankCode(){
//        User userInfo = SecurityUtil.getLoginUser();
////        if(userInfo.isAdmin()){
////            return null;
////        }
//        if (StringUtil.isEmpty(userInfo.getOrgCode())) {
//            return null;
//        }
//        String bankCode = baseMapper.selectBankCode(userInfo.getOrgCode());
//        return bankCode;
//    }
//
//    /**
//     * 判断是否浮点数字表示
//     *
//     * @param src
//     *            源字符串
//     * @return 是否数字的标志
//     */
//    public static boolean isFloatNumeric(String src) {
//        boolean returnValue = false;
//        if (src != null && src.length() > 0) {
//            Matcher m = FLOAT_NUMERIC_PATTERN.matcher(src);
//            if (m.find()) {
//                returnValue = true;
//            }
//        }
//        return returnValue;
//    }
//
////    /**
////     * 查询机构代码的list
////     *
////     * @param myOrgCode 当前用户的机构代码
////     * @return 存储了下拉菜单的list
////     * @see
////     * @since 1.0
////     */
////    public static List<SelectOptionDto> getOrgCodeList(String myOrgCode) {
////        List<SelectOptionDto> dtoList = getSelfItem(myOrgCode);
////        List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
////        if (CollectionUtils.isNotEmpty(dtoList)) {
////            resultList.add(dtoList.get(0));
////        }
////        getChildOrgCodeList(myOrgCode, resultList);
////        return resultList;
////    }
//
//
////    /**
////     * 查询机构代码的子机构list
////     *
////     * @param myOrgCode 当前用户的机构代码
////     * @param resultList 存储了结果的List
////     * @see
////     * @since 1.0
////     */
////    public static void getChildOrgCodeList(String myOrgCode, List<SelectOptionDto> resultList){
////        //查询其子机构列表
////        List<SelectOptionDto> tempList = baseMapper.getChildCodeList(myOrgCode);
////        if(StringUtil.isNotEmpty(tempList)){
////            //其存在子机构
////            for(SelectOptionDto dto :tempList){
////                resultList.add(dto);
////                //遍历子机构并查询判断其是否仍是父级机构
////                getChildOrgCodeList(dto.getId(),resultList);
////            }
////        }
////    }
//
////    /**
////     * 查询机构名称
////     *
////     * @param myOrgCode 机构代码
////     * @return 机构名称
////     * @see
////     * @since 1.0
////     */
////    public static List<SelectOptionDto> getSelfItem(String myOrgCode) {
////        return baseMapper.getSelfItem(myOrgCode);
////    }
//
//    /**
//     * 根据採番名採番
//     *
//     * @param sequenceType 採番序列类型
//     * @return 採番值
//     * @see
//     */
//    public static String getSequenceIndexData(DataIndexSequenceType sequenceType) {
//        Map<String, String> param = new HashMap<>();
//        String databaseType = CacheUtil.getAppParamCache(GlobalConstant.SYS_DEPARTMENT_KEY, "database_type_code");
//
//        databaseType = StringUtil.defaultString(databaseType, "Oracle");
//
//        param.put("databaseType", databaseType);
//        param.put("sequenceName", sequenceType.getSequenceName());
//        Long result = baseMapper.getSequenceIndexData(param);
//
//        String sequenceResult = String.format("%018d", result);
//
//        // 加在採番后结果的前缀，以保证各个sequence的採番结果不会出现重复的情况
//        String shortName = sequenceType.getShortName();
//        sequenceResult = shortName + sequenceResult;
//
//        return sequenceResult;
//    }
//
//    /**
//     * 主要功能:判断字符串是否为日期 注意事项:无
//     *
//     * @param str 日期字符串 yyyyMMdd
//     * @return 是否
//     */
//    public static boolean isDateStrE(String str) {
//        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_OTHER_FORMAT);
//        try {
//            format.setLenient(false);
//            format.parse(str);
//        }
//        catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 判断两个字符串是否相等 如果都为null则判断为相等,一个为null另一个not null则判断不相等 否则如果s1=s2则相等
//     *
//     * @param s1
//     *            参数1
//     * @param s2
//     *            参数2
//     * @return 是否
//     */
//    public static boolean equalsIgnoreNull(String s1, String s2) {
//        if (StringUtil.isBlank(s1) && StringUtil.isBlank(s2)) {
//            return true;
//        }
//
//        return StringUtil.equals(s1, s2);
//    }
//
//    /**
//     * 去掉下划线，并把下划线后一个字母大写
//     *
//     * @param value
//     *            String
//     * @param needCase
//     *            是否需要转小写
//     * @return String
//     */
//    public static String dealString(final String value, boolean needCase) {
//        String temp = value;
//        if (StringUtil.isNotEmpty(temp) && needCase) {
//            temp = temp.toLowerCase();
//        }
//        StringBuilder result = new StringBuilder();
//        if (temp.contains("_")) {
//            result.append(temp.substring(0, temp.indexOf("_")));
//            if (temp.indexOf("_") + 1 <= temp.length() && temp.indexOf("_") + 2 <= temp.length()) {
//                result.append(temp.substring(temp.indexOf("_") + 1, temp.indexOf("_") + 2).toUpperCase());
//            }
//            if (temp.indexOf("_") + 2 <= temp.length()) {
//                result.append(temp.substring(temp.indexOf("_") + 2));
//            }
//            temp = dealString(result.toString(), false);
//        } else {
//            return temp;
//        }
//
//        return temp;
//    }
//
//	    /**
//     * 数据权限用所属部门取得
//     *@author add by wm 2018-03-14
//     * @return 所属部门
//     * @see
//     * @since 1.0
//     */
//    public static List<String> getDeptCodeListForSearch() {
//        List<String> deptCodeList = new ArrayList<String>();
//
////        User userInfo = SecurityUtil.getLoginUser();
////        if (userInfo == null || userInfo.isAdmin()) {
////            return deptCodeList;
////        }
////
////        String myDeptCode = userInfo.getDeptCode();
////        if (StringUtil.isBlank(myDeptCode)) {
////            return deptCodeList;
////        }
////        deptCodeList = baseMapper.selectDeptCode(myDeptCode);
//
//        return deptCodeList;
//    }
//
//    /**
//     * 首字母小写
//     *
//     * @param realName
//     *            源字符串
//     * @return 首字母小写
//     */
//    public static String firstLowerCase(String realName) {
//        return StringUtil.replaceOnce(realName, realName.substring(0, 1), realName.substring(0, 1).toLowerCase());
//    }
//    
//    /**
//     * 
//     * 获取class中指定字段名称的值
//     * @param data  实体类
//     * @param fieldName 字段名称
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public static String getValueByClassName(Object data,String fieldName,Class T) throws IntrospectionException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//        Field field = data.getClass().getDeclaredField(fieldName);
//        PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
//            T);
//        Method getMethod = pd.getReadMethod();//获得get方法
//        Object o = getMethod.invoke(data);//执行get方法返回一个Object
//        return o.toString();
//    }
//    /**
//     * 获取用户名下的所有的有权限的机构和部门
//     * @param userName 想要获取的用户的用户名
//     * @return 所属部门
//     * @see
//     * @since 1.0
//     */
//    public static List<String> getAuthorityDeptByUserName(String userName){
//        @SuppressWarnings("unchecked")
//        List<String> deptCodeList = (List<String>)SecurityUtil.getFromSession(
//            ReportAuthorizationListener.USER_AUTHORITY_DEPT_CODE);
//        if (null != deptCodeList) {
//            return deptCodeList;
//        }
//
//        if(StringUtil.isEmpty(userName)){
//            userName = SecurityUtil.getLoginUser().getUserName();
//        }
//        //权限控制
//        List<SystemActorRole003Dto> tempList = systemActorRoleService.selectDataModuleByUserName(userName);
//        Set<String> createDeptList = new HashSet<>();
//        tempList.forEach(action->{
//            if (StringUtil.equals(action.getDeptCode(), "99")) {
//                createDeptList.addAll(baseCommonService.selectDeptCode(action.getInstitutionCode()));
//            }
//            else {
//                createDeptList.add(action.getDeptCode());
//            }
//            createDeptList.add(action.getInstitutionCode());
//        });
//        if (StringUtil.isNotEmpty(SecurityUtil.getLoginUser().getDeptCode())) {
//            createDeptList.add(SecurityUtil.getLoginUser().getDeptCode());
//        }
//
//        List<String> resultList = new ArrayList<String>(createDeptList);
//        // 放入Session
//        SecurityUtil.addSession(ReportAuthorizationListener.USER_AUTHORITY_DEPT_CODE, resultList);
//
//        return resultList;
//    }
//
//    /**
//     * 获取用户名下的所有的有权限的机构
//     * @param userName 想要获取的用户的用户名
//     * @return 用户名下的所有的有权限的机构
//     * @see
//     * @since 1.0
//     */
//    public static List<String> getAuthorityOnlyOrgListByUserName(String userName) {
//        if (StringUtil.isEmpty(userName)) {
//            userName = SecurityUtil.getLoginUser().getUserName();
//        }
//        // 权限控制
//        List<SystemActorRole003Dto> tempList = systemActorRoleService.selectDataModuleByUserName(
//            userName);
//        List<String> resultList = new ArrayList<>();
//        tempList.forEach(action -> {
//            if (StringUtil.isNotEmpty(action.getInstitutionCode())
//                && !resultList.contains(action.getInstitutionCode())) {
//                resultList.add(action.getInstitutionCode());
//            }
//        });
//        return resultList;
//    }
//
//    /**
//     * 获取用户名下的所有的有权限的部门
//     * @param userName 想要获取的用户的用户名
//     * @return 用户名下的所有的有权限的机构
//     * @see
//     * @since 1.0
//     */
//    public static List<String> getAuthorityOnlyDeptListByUserName(String userName) {
//        if (StringUtil.isEmpty(userName)) {
//            userName = SecurityUtil.getLoginUser().getUserName();
//        }
//        // 权限控制
//        List<SystemActorRole003Dto> tempList = systemActorRoleService.selectDataModuleByUserName(
//            userName);
//        List<String> resultList = new ArrayList<>();
//        tempList.forEach(action -> {
//            if (StringUtil.isNotEmpty(action.getDeptCode())
//                && !resultList.contains(action.getDeptCode())) {
//                resultList.add(action.getDeptCode());
//            }
//        });
//        return resultList;
//    }
//
//    /**
//     * 判断系统使用的数据库是否是db2
//     *
//     * @return 系统使用的数据库是否是db2
//     * @see
//     * @since 1.0
//     */
//    public static boolean isDB2DataBase() {
//        try {
//            Map<String, List<DatabaseInfo>> databaseType = DatabaseTypeHelper.getDatabaseType();
//
//            List<DatabaseInfo> dsList = databaseType.get(DataSourceType.MASERT);
//            if (dsList != null && !dsList.isEmpty()) {
//                for (int i = 0; i < dsList.size(); i++ ) {
//                    DatabaseInfo dbInfo = dsList.get(i);
//                    if (StringUtil.equalsIgnoreCase(DatabaseType.DATABASE_TYPE_DB2.getValue(),
//                        dbInfo.getDbType().getValue())) {
//                        return true;
//                    }
//                }
//            }
//        }
//        catch (Exception e) {
//        	logger.error("error:",e);
//        }
//
//        return false;
//    }
//    
//    public static MultipartFile getFileFromRequest(MultipartHttpServletRequest multiRequest, String filePath) {
//        if (multiRequest == null || StringUtil.isBlank(filePath)) {
//            return null;
//        }
//        
//        return multiRequest.getFile(filePath);
//    }
//    /**
//     * 
//     * 获取class中指定字段名称的值
//     * @param data  实体类
//     * @param fieldName 字段名称
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public static Object getValueByClassNameObject(Object data,String fieldName,Class T) throws IntrospectionException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//        Field field = data.getClass().getDeclaredField(fieldName);
//        PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
//            T);
//        Method getMethod = pd.getReadMethod();//获得get方法
//        Object o = getMethod.invoke(data);//执行get方法返回一个Object
//        return o;
//    }
//}
