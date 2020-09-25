package com.joyintech.service.riskrdsinf.http.service;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.http.dao.HttpReceiveJsonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理http消息业务逻辑
 * Created by 裴家俊 on 2019/11/12.
 */
@Service
public class HttpUserRelPortService {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(HttpUserRelPortService.class);

    @Autowired
    private HttpReceiveJsonDao httpReceiveJsonDao;

    /**
     * 处理用户关联组合信息的数据
     * @param result
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DataSource(DataSourceType.MASERT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleUserRelPortInfo(Map result){
        logger.info("正在处理用户关联组合数据");
        List<HashMap> list = (List<HashMap>) result.get("groupUsers");
        if(StringUtil.isNotEmpty((String)result.get("groupId"))){
            httpReceiveJsonDao.deleteUserGroup(result);
        }
        if (StringUtil.isNotEmpty(list)){
            list.stream().forEach(item->{
                result.put("userId", item.get("userId"));
                result.put("userName", item.get("userName"));
                result.put("dept", item.get("dept"));
                result.put("deptName", item.get("deptName"));
                if(httpReceiveJsonDao.selectUserGroupById(result)<=0){
                    httpReceiveJsonDao.insertUserGroup(result);
                }else {
                    httpReceiveJsonDao.updateUserGroup(result);
                }
            });
        }
    }

}
