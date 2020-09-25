package com.joyintech.service.riskrdsinf.http.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.PasswordHelper;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.common.dto.HttpResult;
import com.joyintech.service.riskrdsinf.http.dao.HttpReceiveJsonDao;
import com.joyintech.system.user.dto.SysUserDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理http消息业务逻辑
 * Created by zy_wangjunteng on 2019/10/17.
 */
@Service
public class HttpReceiveJsonService {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(HttpReceiveJsonService.class);

    /**
     * 核心 接口成功标志
     */
    private static final String CORE_MSG_CODE = "AAAAAAA";

    /**
     * 核心报文 是否 空
     */
    private static final String CORE_MSG = "数据处理成功";

    /**
     * 校验消息
     */
    private static final String CHECK_CODE_01 = "01";

    /**
     * 校验消息
     */
    private static final String CHECK_MSG_01 = "报文内容为空";

    /**
     * 校验消息
     */
    private static final String CHECK_MSG_02 = "报文格式解析异常";

    /**
     * 校验消息
     */
    private static final String CHECK_CODE_02 = "02";

    /**
     * 校验消息
     */
    private static final String CHECK_CODE_03 = "03";

    /**
     * 校验消息
     */
    private static final String CHECK_MSG_03 = "报文体为空";

    /**
     * 用户信息
     */
    private static final String SYS_USER  = "01";

    /**
     * 角色信息
     */
    private static final String SYS_ROLE = "02";

    /**
     * 用户角色信息
     */
    private static final String SYS_USER_ROLE = "03";

    /**
     * 用户关联组合信息
     */
    private static final String SYS_PORTFOLIO_INFO= "04";

    @Autowired
    private HttpReceiveJsonDao httpReceiveJsonDao;

    @Autowired
    private HttpUserRelPortService httpUserRelPortService;

    /**
     * 资管用户信息同步
     *
     * @param msg 接收消息
     * @param url 请求地址
     * @return 返回报文
     * @see
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
	@DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String ziguanSysUserInfo(String msg, String url) {
        // 信息码
        String statusCode = CORE_MSG_CODE;
        // 信息描述
        String dealMsg = CORE_MSG;
        // 成功标志
        Boolean flag = true;
        if (StringUtils.isEmpty(msg)) {
            statusCode = CHECK_CODE_01;
            dealMsg = CHECK_MSG_01;
            flag = false;
        }
        // 判断前一步是否执行成功
        if (flag) {
            try {
                HashMap jsonMap = JsonUtils.json2Object(msg, HashMap.class);
                Object body = jsonMap.get("body");
                // 校验报文体是否为空
                if (!StringUtil.isNotEmpty(body)) {
                    statusCode = CHECK_CODE_03;
                    dealMsg = CHECK_MSG_03;
                }else {
                    // 业务处理逻辑
                    Map result = body instanceof String ? JsonUtils.json2Object((String)body, Map.class) : (Map)body;
                    String interfType = (String)result.get("interfType");
                    if(!SYS_PORTFOLIO_INFO.equals(interfType)) {
                        this.handleSysUserInfo(result, interfType);
                    }else {
                        httpUserRelPortService.handleUserRelPortInfo(result);
                    }
                    logger.info("报文格式解析成功");
                }
            }
            catch (Exception e) {
                logger.error(CHECK_MSG_02, e);
                statusCode = CHECK_CODE_02;
                dealMsg = CHECK_MSG_02;
            }
        }
        // 返回数据处理结果
        HttpResult result = new HttpResult();
        result.setStatus(statusCode);
        result.setReturnMsg(dealMsg);
        String repStr = JsonUtils.object2Json(result);
        logger.info(repStr);
        return repStr;
    }

    /**
     * 处理用户信息的数据
     * @param result
     * @param interfType
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void handleSysUserInfo(Map result,String interfType){
        // 处理用户信息数据
        if(SYS_USER.equals(interfType)){
            logger.info("正在处理用户信息数据");
            if (httpReceiveJsonDao.selectUserByUserId(result) <= 0){
            	SysUserDto user = new SysUserDto();
            	String password = CacheUtil.getSysParam(GlobalConstant.INIT_PASSWORD);
            	user.setPassword(password);
            	PasswordHelper.encryptPassword(user);
            	result.put("password", user.getPassword());
            	result.put("salt",user.getSalt());
                httpReceiveJsonDao.insertUser(result);
            }else {
            	String userName = httpReceiveJsonDao.getUserName((String)result.get("userId"));
            	if(StringUtil.isNotEmpty(userName)){
            		result.put("oldUserName", userName);
                	httpReceiveJsonDao.updateUserName(result);
            	}
                httpReceiveJsonDao.updateUser(result);
            }
        }
        // 处理角色信息数据
        else if(SYS_ROLE.equals(interfType)){
            logger.info("正在处理角色信息数据");
            if (httpReceiveJsonDao.selectRoleByRoleId(result) <= 0){
                httpReceiveJsonDao.insertRole(result);
            }else {
                httpReceiveJsonDao.updateRole(result);
            }
        }
        // 处理用户角色信息数据
        else if(SYS_USER_ROLE.equals(interfType)){
            logger.info("正在处理用户角色信息数据");
            List<HashMap> list = (List<HashMap>) result.get("roles");
            if (StringUtil.isNotEmpty(list)){
            	String userName = httpReceiveJsonDao.getUserName((String)result.get("userId"));
            	if(StringUtil.isNotEmpty(userName)){
            		result.put("userName", userName);
                	httpReceiveJsonDao.deleteAllUserRole(result);
            	}
                for(HashMap roleMap : list){
                    result.put("roleId",roleMap.get("roleId"));
                    httpReceiveJsonDao.insertUserRole(result);
                }
            }
        } // 处理用户关联组合信息数据
        else if(SYS_PORTFOLIO_INFO.equals(interfType)){
            logger.info("正在处理用户关联组合数据");
            List<HashMap> list = (List<HashMap>) result.get("groupUsers");
            if (StringUtil.isNotEmpty(list)){
                list.stream().forEach(item->{
                    result.put("userName", item.get("userId"));
                    if(httpReceiveJsonDao.selectUserGroupById(result)<=0){
                        httpReceiveJsonDao.insertUserGroup(result);
                    }else {
                        httpReceiveJsonDao.updateUserGroup(result);
                    }
                });
            }
        }
    }

}
